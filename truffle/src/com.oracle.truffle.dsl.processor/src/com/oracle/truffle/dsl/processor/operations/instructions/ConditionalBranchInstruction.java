package com.oracle.truffle.dsl.processor.operations.instructions;

import javax.lang.model.type.DeclaredType;
import javax.lang.model.type.TypeMirror;

import com.oracle.truffle.dsl.processor.ProcessorContext;
import com.oracle.truffle.dsl.processor.generator.GeneratorUtils;
import com.oracle.truffle.dsl.processor.java.model.CodeTree;
import com.oracle.truffle.dsl.processor.java.model.CodeTreeBuilder;
import com.oracle.truffle.dsl.processor.operations.OperationGeneratorUtils;
import com.oracle.truffle.dsl.processor.operations.OperationsContext;

public class ConditionalBranchInstruction extends Instruction {

    private final ProcessorContext context = ProcessorContext.getInstance();
    private final DeclaredType ConditionProfile = context.getDeclaredType("com.oracle.truffle.api.profiles.ConditionProfile");
    private final OperationsContext ctx;
    private final boolean boxed;

    public ConditionalBranchInstruction(OperationsContext ctx, int id, boolean boxed) {
        super(boxed ? "branch.false.boxed" : "branch.false", id, ResultType.BRANCH, InputType.BRANCH_TARGET, InputType.STACK_VALUE, InputType.BRANCH_PROFILE);
        this.ctx = ctx;
        this.boxed = boxed;
    }

    @Override
    public TypeMirror[] expectedInputTypes(ProcessorContext c) {
        return new TypeMirror[]{
                        context.getType(short.class),
                        context.getType(boolean.class),
                        ConditionProfile
        };
    }

    @Override
    public boolean standardPrologue() {
        return false;
    }

    @Override
    public boolean isBranchInstruction() {
        return true;
    }

    @Override
    public CodeTree createExecuteCode(ExecutionVariables vars) {
        CodeTreeBuilder b = CodeTreeBuilder.createBuilder();

        b.declaration(ConditionProfile, "profile", "conditionProfiles[LE_BYTES.getShort(bc, bci + " + getArgumentOffset(1) + ")]");

        if (boxed) {
            b.declaration("boolean", "cond", "(boolean) frame.getObject(sp - 1)");
        } else {
            b.declaration("boolean", "cond", (CodeTree) null);

            b.startTryBlock();
            b.statement("cond = frame.getBoolean(sp - 1)");
            b.end().startCatchBlock(context.getDeclaredType("com.oracle.truffle.api.frame.FrameSlotTypeException"), "ex");
            b.tree(GeneratorUtils.createTransferToInterpreterAndInvalidate());

            b.statement("cond = (boolean) frame.getObject(sp - 1)");

            // TODO lock
            b.startStatement().startCall("doSetResultBoxed").variable(vars.bci).string("0").end(2);
            b.tree(OperationGeneratorUtils.createWriteOpcode(vars.bc, vars.bci, ctx.commonBranchFalseBoxed.opcodeIdField));
            b.end();
        }
        b.statement("sp -= 1");

        b.startIf().startCall("profile", "profile").string("cond").end(2);
        b.startBlock();
        b.startAssign(vars.bci).variable(vars.bci).string(" + " + length()).end();
        b.statement("continue loop");
        b.end().startElseBlock();
        b.startAssign(vars.bci).string("LE_BYTES.getShort(bc, bci + " + getArgumentOffset(0) + ")").end();
        b.statement("continue loop");
        b.end();

        return b.build();
    }

    @Override
    public CodeTree createSetResultBoxed(ExecutionVariables vars) {
        return null;
    }

    @Override
    public CodeTree createSetInputBoxed(ExecutionVariables vars, int index) {
        if (boxed) {
            return null;
        }

        return OperationGeneratorUtils.createWriteOpcode(vars.bc, vars.bci, ctx.commonBranchFalseBoxed.opcodeIdField);
    }

    @Override
    public boolean isInputAlwaysBoxed(int index) {
        return boxed;
    }
}