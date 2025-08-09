package org.apache.datasketches.kll;

import org.apache.datasketches.quantilescommon.QuantilesGenericSketchIterator;

public final class KllItemsSketchIterator extends KllSketchIterator implements QuantilesGenericSketchIterator {
   private final Object[] quantiles;

   KllItemsSketchIterator(Object[] quantiles, int[] levelsArr, int numLevels) {
      super(levelsArr, numLevels);
      this.quantiles = quantiles;
   }

   public Object getQuantile() {
      return this.quantiles[this.index];
   }
}
