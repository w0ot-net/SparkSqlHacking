package org.apache.datasketches.quantilescommon;

public final class DoublesSortedViewIterator extends SortedViewIterator {
   private final double[] quantiles;

   public DoublesSortedViewIterator(double[] quantiles, long[] cumWeights) {
      super(cumWeights);
      this.quantiles = quantiles;
   }

   public double getQuantile() {
      return this.quantiles[this.index];
   }
}
