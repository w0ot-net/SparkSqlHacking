package org.apache.datasketches.quantilescommon;

import org.apache.datasketches.common.SketchesArgumentException;

public class FloatsSketchSortedView implements FloatsSortedView {
   private final float[] quantiles;
   private final long[] cumWeights;
   private final long totalN;

   public FloatsSketchSortedView(float[] quantiles, long[] cumWeights, QuantilesFloatsAPI sk) {
      IncludeMinMax.FloatsPair fPair = IncludeMinMax.includeFloatsMinMax(quantiles, cumWeights, sk.getMaxItem(), sk.getMinItem());
      this.quantiles = fPair.quantiles;
      this.cumWeights = fPair.cumWeights;
      this.totalN = sk.getN();
   }

   FloatsSketchSortedView(float[] quantiles, long[] cumWeights, long totalN, float maxItem, float minItem) {
      IncludeMinMax.FloatsPair fPair = IncludeMinMax.includeFloatsMinMax(quantiles, cumWeights, maxItem, minItem);
      this.quantiles = fPair.quantiles;
      this.cumWeights = fPair.cumWeights;
      this.totalN = totalN;
   }

   public long[] getCumulativeWeights() {
      return (long[])this.cumWeights.clone();
   }

   public float getMaxItem() {
      int top = this.quantiles.length - 1;
      return this.quantiles[top];
   }

   public float getMinItem() {
      return this.quantiles[0];
   }

   public long getN() {
      return this.totalN;
   }

   public int getNumRetained() {
      return this.quantiles.length;
   }

   public float getQuantile(double rank, QuantileSearchCriteria searchCrit) {
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

   public float[] getQuantiles() {
      return (float[])this.quantiles.clone();
   }

   public double getRank(float quantile, QuantileSearchCriteria searchCrit) {
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

   public FloatsSortedViewIterator iterator() {
      return new FloatsSortedViewIterator(this.quantiles, this.cumWeights);
   }
}
