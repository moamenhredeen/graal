/*
 * Copyright (c) 2022, 2022, Oracle and/or its affiliates. All rights reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This code is free software; you can redistribute it and/or modify it
 * under the terms of the GNU General Public License version 2 only, as
 * published by the Free Software Foundation.  Oracle designates this
 * particular file as subject to the "Classpath" exception as provided
 * by Oracle in the LICENSE file that accompanied this code.
 *
 * This code is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE.  See the GNU General Public License
 * version 2 for more details (a copy is included in the LICENSE file that
 * accompanied this code).
 *
 * You should have received a copy of the GNU General Public License version
 * 2 along with this work; if not, write to the Free Software Foundation,
 * Inc., 51 Franklin St, Fifth Floor, Boston, MA 02110-1301 USA.
 *
 * Please contact Oracle, 500 Oracle Parkway, Redwood Shores, CA 94065 USA
 * or visit www.oracle.com if you need additional information or have any
 * questions.
 */
package com.oracle.svm.graal.stubs;

import static org.graalvm.compiler.core.common.StrideUtil.S1;
import static org.graalvm.compiler.core.common.StrideUtil.S2;
import static org.graalvm.compiler.core.common.StrideUtil.S4;

import org.graalvm.compiler.replacements.nodes.ArrayRegionCompareToNode;
import org.graalvm.nativeimage.Platform.AMD64;
import org.graalvm.nativeimage.Platforms;

import com.oracle.svm.core.annotate.Uninterruptible;
import com.oracle.svm.core.cpufeature.Stubs;
import com.oracle.svm.core.snippets.SubstrateForeignCallTarget;
import com.oracle.svm.graal.RuntimeCPUFeatureRegion;

@Platforms(AMD64.class)
class SVMArrayRegionCompareToForeignCalls {

    // GENERATED CODE BEGIN

    // GENERATED FROM:
    // compiler/src/org.graalvm.compiler.hotspot.amd64/src/org/graalvm/compiler/hotspot/amd64/AMD64ArrayRegionCompareToStub.java
    // BY: "mx svm-sync-graal-stubs"

    @Uninterruptible(reason = "Must not do a safepoint check.")
    @SubstrateForeignCallTarget(stubCallingConvention = false, fullyUninterruptible = true)
    private static int arrayRegionCompareToS1S1(Object arrayA, long offsetA, Object arrayB, long offsetB, int length) {
        return ArrayRegionCompareToNode.compare(arrayA, offsetA, arrayB, offsetB, length, S1, S1);
    }

    @Uninterruptible(reason = "Must not do a safepoint check.")
    @SubstrateForeignCallTarget(stubCallingConvention = false, fullyUninterruptible = true)
    private static int arrayRegionCompareToS1S2(Object arrayA, long offsetA, Object arrayB, long offsetB, int length) {
        return ArrayRegionCompareToNode.compare(arrayA, offsetA, arrayB, offsetB, length, S1, S2);
    }

    @Uninterruptible(reason = "Must not do a safepoint check.")
    @SubstrateForeignCallTarget(stubCallingConvention = false, fullyUninterruptible = true)
    private static int arrayRegionCompareToS1S4(Object arrayA, long offsetA, Object arrayB, long offsetB, int length) {
        return ArrayRegionCompareToNode.compare(arrayA, offsetA, arrayB, offsetB, length, S1, S4);
    }

    @Uninterruptible(reason = "Must not do a safepoint check.")
    @SubstrateForeignCallTarget(stubCallingConvention = false, fullyUninterruptible = true)
    private static int arrayRegionCompareToS2S1(Object arrayA, long offsetA, Object arrayB, long offsetB, int length) {
        return ArrayRegionCompareToNode.compare(arrayA, offsetA, arrayB, offsetB, length, S2, S1);
    }

    @Uninterruptible(reason = "Must not do a safepoint check.")
    @SubstrateForeignCallTarget(stubCallingConvention = false, fullyUninterruptible = true)
    private static int arrayRegionCompareToS2S2(Object arrayA, long offsetA, Object arrayB, long offsetB, int length) {
        return ArrayRegionCompareToNode.compare(arrayA, offsetA, arrayB, offsetB, length, S2, S2);
    }

    @Uninterruptible(reason = "Must not do a safepoint check.")
    @SubstrateForeignCallTarget(stubCallingConvention = false, fullyUninterruptible = true)
    private static int arrayRegionCompareToS2S4(Object arrayA, long offsetA, Object arrayB, long offsetB, int length) {
        return ArrayRegionCompareToNode.compare(arrayA, offsetA, arrayB, offsetB, length, S2, S4);
    }

    @Uninterruptible(reason = "Must not do a safepoint check.")
    @SubstrateForeignCallTarget(stubCallingConvention = false, fullyUninterruptible = true)
    private static int arrayRegionCompareToS4S1(Object arrayA, long offsetA, Object arrayB, long offsetB, int length) {
        return ArrayRegionCompareToNode.compare(arrayA, offsetA, arrayB, offsetB, length, S4, S1);
    }

    @Uninterruptible(reason = "Must not do a safepoint check.")
    @SubstrateForeignCallTarget(stubCallingConvention = false, fullyUninterruptible = true)
    private static int arrayRegionCompareToS4S2(Object arrayA, long offsetA, Object arrayB, long offsetB, int length) {
        return ArrayRegionCompareToNode.compare(arrayA, offsetA, arrayB, offsetB, length, S4, S2);
    }

    @Uninterruptible(reason = "Must not do a safepoint check.")
    @SubstrateForeignCallTarget(stubCallingConvention = false, fullyUninterruptible = true)
    private static int arrayRegionCompareToS4S4(Object arrayA, long offsetA, Object arrayB, long offsetB, int length) {
        return ArrayRegionCompareToNode.compare(arrayA, offsetA, arrayB, offsetB, length, S4, S4);
    }

    @Uninterruptible(reason = "Must not do a safepoint check.")
    @SubstrateForeignCallTarget(stubCallingConvention = false, fullyUninterruptible = true)
    private static int arrayRegionCompareToDynamicStrides(Object arrayA, long offsetA, Object arrayB, long offsetB, int length, int dynamicStrides) {
        return ArrayRegionCompareToNode.compare(arrayA, offsetA, arrayB, offsetB, length, dynamicStrides);
    }

    @Uninterruptible(reason = "Must not do a safepoint check.")
    @SubstrateForeignCallTarget(stubCallingConvention = false, fullyUninterruptible = true)
    private static int arrayRegionCompareToS1S1RTC(Object arrayA, long offsetA, Object arrayB, long offsetB, int length) {
        RuntimeCPUFeatureRegion region = RuntimeCPUFeatureRegion.enterSet(Stubs.getRuntimeCheckedCPUFeatures());
        try {
            return ArrayRegionCompareToNode.compare(arrayA, offsetA, arrayB, offsetB, length, S1, S1, Stubs.getRuntimeCheckedCPUFeatures());
        } finally {
            region.leave();
        }
    }

    @Uninterruptible(reason = "Must not do a safepoint check.")
    @SubstrateForeignCallTarget(stubCallingConvention = false, fullyUninterruptible = true)
    private static int arrayRegionCompareToS1S2RTC(Object arrayA, long offsetA, Object arrayB, long offsetB, int length) {
        RuntimeCPUFeatureRegion region = RuntimeCPUFeatureRegion.enterSet(Stubs.getRuntimeCheckedCPUFeatures());
        try {
            return ArrayRegionCompareToNode.compare(arrayA, offsetA, arrayB, offsetB, length, S1, S2, Stubs.getRuntimeCheckedCPUFeatures());
        } finally {
            region.leave();
        }
    }

    @Uninterruptible(reason = "Must not do a safepoint check.")
    @SubstrateForeignCallTarget(stubCallingConvention = false, fullyUninterruptible = true)
    private static int arrayRegionCompareToS1S4RTC(Object arrayA, long offsetA, Object arrayB, long offsetB, int length) {
        RuntimeCPUFeatureRegion region = RuntimeCPUFeatureRegion.enterSet(Stubs.getRuntimeCheckedCPUFeatures());
        try {
            return ArrayRegionCompareToNode.compare(arrayA, offsetA, arrayB, offsetB, length, S1, S4, Stubs.getRuntimeCheckedCPUFeatures());
        } finally {
            region.leave();
        }
    }

    @Uninterruptible(reason = "Must not do a safepoint check.")
    @SubstrateForeignCallTarget(stubCallingConvention = false, fullyUninterruptible = true)
    private static int arrayRegionCompareToS2S1RTC(Object arrayA, long offsetA, Object arrayB, long offsetB, int length) {
        RuntimeCPUFeatureRegion region = RuntimeCPUFeatureRegion.enterSet(Stubs.getRuntimeCheckedCPUFeatures());
        try {
            return ArrayRegionCompareToNode.compare(arrayA, offsetA, arrayB, offsetB, length, S2, S1, Stubs.getRuntimeCheckedCPUFeatures());
        } finally {
            region.leave();
        }
    }

    @Uninterruptible(reason = "Must not do a safepoint check.")
    @SubstrateForeignCallTarget(stubCallingConvention = false, fullyUninterruptible = true)
    private static int arrayRegionCompareToS2S2RTC(Object arrayA, long offsetA, Object arrayB, long offsetB, int length) {
        RuntimeCPUFeatureRegion region = RuntimeCPUFeatureRegion.enterSet(Stubs.getRuntimeCheckedCPUFeatures());
        try {
            return ArrayRegionCompareToNode.compare(arrayA, offsetA, arrayB, offsetB, length, S2, S2, Stubs.getRuntimeCheckedCPUFeatures());
        } finally {
            region.leave();
        }
    }

    @Uninterruptible(reason = "Must not do a safepoint check.")
    @SubstrateForeignCallTarget(stubCallingConvention = false, fullyUninterruptible = true)
    private static int arrayRegionCompareToS2S4RTC(Object arrayA, long offsetA, Object arrayB, long offsetB, int length) {
        RuntimeCPUFeatureRegion region = RuntimeCPUFeatureRegion.enterSet(Stubs.getRuntimeCheckedCPUFeatures());
        try {
            return ArrayRegionCompareToNode.compare(arrayA, offsetA, arrayB, offsetB, length, S2, S4, Stubs.getRuntimeCheckedCPUFeatures());
        } finally {
            region.leave();
        }
    }

    @Uninterruptible(reason = "Must not do a safepoint check.")
    @SubstrateForeignCallTarget(stubCallingConvention = false, fullyUninterruptible = true)
    private static int arrayRegionCompareToS4S1RTC(Object arrayA, long offsetA, Object arrayB, long offsetB, int length) {
        RuntimeCPUFeatureRegion region = RuntimeCPUFeatureRegion.enterSet(Stubs.getRuntimeCheckedCPUFeatures());
        try {
            return ArrayRegionCompareToNode.compare(arrayA, offsetA, arrayB, offsetB, length, S4, S1, Stubs.getRuntimeCheckedCPUFeatures());
        } finally {
            region.leave();
        }
    }

    @Uninterruptible(reason = "Must not do a safepoint check.")
    @SubstrateForeignCallTarget(stubCallingConvention = false, fullyUninterruptible = true)
    private static int arrayRegionCompareToS4S2RTC(Object arrayA, long offsetA, Object arrayB, long offsetB, int length) {
        RuntimeCPUFeatureRegion region = RuntimeCPUFeatureRegion.enterSet(Stubs.getRuntimeCheckedCPUFeatures());
        try {
            return ArrayRegionCompareToNode.compare(arrayA, offsetA, arrayB, offsetB, length, S4, S2, Stubs.getRuntimeCheckedCPUFeatures());
        } finally {
            region.leave();
        }
    }

    @Uninterruptible(reason = "Must not do a safepoint check.")
    @SubstrateForeignCallTarget(stubCallingConvention = false, fullyUninterruptible = true)
    private static int arrayRegionCompareToS4S4RTC(Object arrayA, long offsetA, Object arrayB, long offsetB, int length) {
        RuntimeCPUFeatureRegion region = RuntimeCPUFeatureRegion.enterSet(Stubs.getRuntimeCheckedCPUFeatures());
        try {
            return ArrayRegionCompareToNode.compare(arrayA, offsetA, arrayB, offsetB, length, S4, S4, Stubs.getRuntimeCheckedCPUFeatures());
        } finally {
            region.leave();
        }
    }

    @Uninterruptible(reason = "Must not do a safepoint check.")
    @SubstrateForeignCallTarget(stubCallingConvention = false, fullyUninterruptible = true)
    private static int arrayRegionCompareToDynamicStridesRTC(Object arrayA, long offsetA, Object arrayB, long offsetB, int length, int dynamicStrides) {
        RuntimeCPUFeatureRegion region = RuntimeCPUFeatureRegion.enterSet(Stubs.getRuntimeCheckedCPUFeatures());
        try {
            return ArrayRegionCompareToNode.compare(arrayA, offsetA, arrayB, offsetB, length, dynamicStrides, Stubs.getRuntimeCheckedCPUFeatures());
        } finally {
            region.leave();
        }
    }

    // GENERATED CODE END
}
