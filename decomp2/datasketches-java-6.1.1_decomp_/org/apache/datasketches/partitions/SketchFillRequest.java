package org.apache.datasketches.partitions;

import org.apache.datasketches.quantilescommon.QuantilesGenericAPI;

public interface SketchFillRequest {
   QuantilesGenericAPI getRange(Object var1, Object var2, BoundsRule var3);
}
