package org.apache.datasketches.kll;

import org.apache.datasketches.quantilescommon.QuantilesFloatsSketchIterator;

public final class KllFloatsSketchIterator extends KllSketchIterator implements QuantilesFloatsSketchIterator {
   private final float[] quantiles;

   KllFloatsSketchIterator(float[] quantiles, int[] levelsArr, int numLevels) {
      super(levelsArr, numLevels);
      this.quantiles = quantiles;
   }

   public float getQuantile() {
      return this.quantiles[this.index];
   }
}
