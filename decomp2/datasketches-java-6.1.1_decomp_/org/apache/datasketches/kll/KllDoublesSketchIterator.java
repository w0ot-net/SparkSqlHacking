package org.apache.datasketches.kll;

import org.apache.datasketches.quantilescommon.QuantilesDoublesSketchIterator;

public final class KllDoublesSketchIterator extends KllSketchIterator implements QuantilesDoublesSketchIterator {
   private final double[] quantiles;

   KllDoublesSketchIterator(double[] quantiles, int[] levelsArr, int numLevels) {
      super(levelsArr, numLevels);
      this.quantiles = quantiles;
   }

   public double getQuantile() {
      return this.quantiles[this.index];
   }
}
