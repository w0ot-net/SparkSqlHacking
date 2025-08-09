package org.apache.datasketches.quantilescommon;

import org.apache.datasketches.common.SketchesArgumentException;

public interface SketchPartitionLimits {
   int getMaxPartitions();

   default long getMinPartitionSizeItems() {
      long totalN = this.getN();
      if (totalN <= 0L) {
         throw new SketchesArgumentException("The sketch must not be empty for this operation. ");
      } else {
         return totalN / (long)this.getMaxPartitions();
      }
   }

   long getN();
}
