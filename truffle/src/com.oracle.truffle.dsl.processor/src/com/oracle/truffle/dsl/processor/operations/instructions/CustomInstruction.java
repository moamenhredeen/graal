package com.oracle.truffle.dsl.processor.operations.instructions;

import javax.lang.model.element.ExecutableElement;
import javax.lang.model.type.TypeKind;

import com.oracle.truffle.dsl.processor.java.model.CodeTree;
import com.oracle.truffle.dsl.processor.java.model.CodeTreeBuilder;
import com.oracle.truffle.dsl.processor.java.model.CodeTypeMirror;
import com.oracle.truffle.dsl.processor.operations.SingleOperationData;
import com.oracle.truffle.dsl.processor.operations.Operation.BuilderVariables;
import com.oracle.truffle.dsl.processor.operations.SingleOperationData.MethodProperties;
import com.oracle.truffle.dsl.processor.operations.SingleOperationData.ParameterKind;

public class CustomInstruction extends Instruction {
    public enum DataKind {
        BITS,
        CONST,
        CHILD,
        CONTINUATION
    }

    private final SingleOperationData data;
    private ExecutableElement executeMethod;
    private DataKind[] dataKinds = null;

    public SingleOperationData getData() {
        return data;
    }

    public void setExecuteMethod(ExecutableElement executeMethod) {
        this.executeMethod = executeMethod;
    }

    private static InputType[] createInputs(SingleOperationData data) {
        MethodProperties props = data.getMainProperties();
        InputType[] inputs = new InputType[props.numStackValues];
        for (int i = 0; i < inputs.length; i++) {
            inputs[i] = InputType.STACK_VALUE;
        }

        if (props.isVariadic) {
            inputs[inputs.length - 1] = InputType.VARARG_VALUE;
        }

        return inputs;
    }

    public CustomInstruction(String name, int id, SingleOperationData data) {
        super(name, id, data.getMainProperties().returnsValue
                        ? new ResultType[]{ResultType.STACK_VALUE}
                        : new ResultType[]{}, createInputs(data));
        this.data = data;
    }

    @Override
    protected CodeTree createInitializeAdditionalStateBytes(BuilderVariables vars, CodeTree[] arguments) {
        if (getAdditionalStateBytes() == 0) {
            return null;
        }

        CodeTreeBuilder b = CodeTreeBuilder.createBuilder();

        int lengthWithoutState = lengthWithoutState();

        for (int i = 0; i < dataKinds.length; i++) {
            CodeTree index = b.create().variable(vars.bci).string(" + " + lengthWithoutState + " + " + i).build();
            switch (dataKinds[i]) {
                case BITS:
                    b.startStatement();
                    b.variable(vars.bc).string("[").tree(index).string("] = 0");
                    b.end();
                    break;
                case CHILD:
                    b.startStatement();
                    b.startCall("LE_BYTES", "putShort");
                    b.variable(vars.bc);
                    b.tree(index);
                    b.startGroup().cast(new CodeTypeMirror(TypeKind.SHORT)).variable(vars.numChildNodes).string("++").end();
                    b.end();
                    break;
                case CONST:
                    b.startStatement();
                    b.startCall("LE_BYTES", "putShort");
                    b.variable(vars.bc);
                    b.tree(index);
                    b.startGroup().cast(new CodeTypeMirror(TypeKind.SHORT)).startCall(vars.consts, "reserve").end(2);
                    b.end();
                    break;
                case CONTINUATION:
                    break;
            }

            b.end();
        }

        return b.build();
    }

    @Override
    public CodeTree createExecuteCode(ExecutionVariables vars) {
        CodeTreeBuilder b = CodeTreeBuilder.createBuilder();

        if (results.length > 0) {
            b.startAssign(vars.results[0]);
        } else {
            b.startStatement();
        }

        int inputIndex = 0;
        b.startCall("this", executeMethod);
        b.variable(vars.bci);
        for (ParameterKind kind : data.getMainProperties().parameters) {
            switch (kind) {
                case STACK_VALUE:
                case VARIADIC:
                    b.variable(vars.inputs[inputIndex++]);
                    break;
                case VIRTUAL_FRAME:
                    b.variable(vars.frame);
                    break;
                default:
                    throw new IllegalArgumentException("Unexpected value: " + kind);
            }
        }

        b.end(2);

        return b.build();
    }

    @Override
    public int getAdditionalStateBytes() {
        if (dataKinds == null) {
            throw new UnsupportedOperationException("state bytes not yet initialized");
        }

        return dataKinds.length;
    }

    public void setDataKinds(DataKind[] dataKinds) {
        this.dataKinds = dataKinds;
    }

    @Override
    public String dumpInfo() {
        StringBuilder sb = new StringBuilder(super.dumpInfo());

        sb.append("  Additional Data:\n");
        int ofs = -1;
        for (DataKind kind : dataKinds) {
            ofs += 1;
            if (kind == DataKind.CONTINUATION) {
                continue;
            }
            sb.append("    ").append(ofs).append(" ").append(kind).append("\n");
        }

        return sb.toString();
    }
}