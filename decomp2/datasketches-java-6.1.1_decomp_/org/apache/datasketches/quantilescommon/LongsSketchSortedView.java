package org.apache.datasketches.quantilescommon;

import org.apache.datasketches.common.SketchesArgumentException;

public final class LongsSketchSortedView implements LongsSortedView {
   private final long[] quantiles;
   private final long[] cumWeights;
   private final long totalN;

   public LongsSketchSortedView(long[] quantiles, long[] cumWeights, QuantilesLongsAPI sk) {
      IncludeMinMax.LongsPair dPair = IncludeMinMax.includeLongsMinMax(quantiles, cumWeights, sk.getMaxItem(), sk.getMinItem());
      this.quantiles = dPair.quantiles;
      this.cumWeights = dPair.cumWeights;
      this.totalN = sk.getN();
   }

   LongsSketchSortedView(long[] quantiles, long[] cumWeights, long totalN, long maxItem, long minItem) {
      IncludeMinMax.LongsPair dPair = IncludeMinMax.includeLongsMinMax(quantiles, cumWeights, maxItem, minItem);
      this.quantiles = dPair.quantiles;
      this.cumWeights = dPair.cumWeights;
      this.totalN = totalN;
   }

   public long[] getCumulativeWeights() {
      return (long[])this.cumWeights.clone();
   }

   public long getMaxItem() {
      int top = this.quantiles.length - 1;
      return this.quantiles[top];
   }

   public long getMinItem() {
      return this.quantiles[0];
   }

   public long getN() {
      return this.totalN;
   }

   public int getNumRetained() {
      return this.quantiles.length;
   }

   public long getQuantile(double rank, QuantileSearchCriteria searchCrit) {
      if (this.isEmpty()) {
         throw new SketchesArgumentException("The sketch must not be empty for this operation. ");
      } else {
         QuantilesUtil.checkNormalizedRankBounds(rank);
         int len = this.cumWeights.length;
         double naturalRank = QuantilesUtil.getNaturalRank(rank, this.totalN, searchCrit);
         InequalitySearch crit = searchCrit == QuantileSearchCriteria.INCLUSIVE ? InequalitySearch.GE : InequalitySearch.GT;
         int index = InequalitySearch.find((long[])this.cumWeights, 0, len - 1, naturalRank, crit);
         return index == -1 ? this.quantiles[len - 1] : this.quantiles[index];
      }
   }

   public long[] getQuantiles() {
      return (long[])this.quantiles.clone();
   }

   public double getRank(long quantile, QuantileSearchCriteria searchCrit) {
      if (this.isEmpty()) {
         throw new SketchesArgumentException("The sketch must not be empty for this operation. ");
      } else {
         int len = this.quantiles.length;
         InequalitySearch crit = searchCrit == QuantileSearchCriteria.INCLUSIVE ? InequalitySearch.LE : InequalitySearch.LT;
         int index = InequalitySearch.find(this.quantiles, 0, len - 1, quantile, crit);
         return index == -1 ? (double)0.0F : (double)this.cumWeights[index] / (double)this.totalN;
      }
   }

   public boolean isEmpty() {
      return this.totalN == 0L;
   }

   public LongsSortedViewIterator iterator() {
      return new LongsSortedViewIterator(this.quantiles, this.cumWeights);
   }
}
