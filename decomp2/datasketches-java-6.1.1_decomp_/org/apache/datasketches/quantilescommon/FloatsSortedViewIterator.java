package org.apache.datasketches.quantilescommon;

public final class FloatsSortedViewIterator extends SortedViewIterator {
   private final float[] quantiles;

   public FloatsSortedViewIterator(float[] quantiles, long[] cumWeights) {
      super(cumWeights);
      this.quantiles = quantiles;
   }

   public float getQuantile() {
      return this.quantiles[this.index];
   }
}
