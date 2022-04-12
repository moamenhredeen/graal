package com.oracle.truffle.api.operation;

import com.oracle.truffle.api.TruffleLanguage;
import com.oracle.truffle.api.frame.VirtualFrame;
import com.oracle.truffle.api.source.Source;
import com.oracle.truffle.api.source.SourceSection;

public abstract class OperationsInstrumentableNode extends OperationsNode {

    @Children OperationsInstrumentTreeNode[] instrumentTree;

    protected OperationsInstrumentableNode(
                    Object parseContext,
                    int[][] sourceInfo,
                    Source[] sources,
                    int buildOrder,
                    int maxStack,
                    int maxLocals,
                    OperationsInstrumentTreeNode[] instrumentTree) {
        super(parseContext, sourceInfo, sources, buildOrder, maxStack, maxLocals);
        this.instrumentTree = instrumentTree;
    }

}