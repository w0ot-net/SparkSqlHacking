package org.apache.datasketches.quantilescommon;

public final class LongsSortedViewIterator extends SortedViewIterator {
   private final long[] quantiles;

   public LongsSortedViewIterator(long[] quantiles, long[] cumWeights) {
      super(cumWeights);
      this.quantiles = quantiles;
   }

   public long getQuantile() {
      return this.quantiles[this.index];
   }
}
