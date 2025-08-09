package org.apache.datasketches.quantilescommon;

import org.apache.datasketches.common.SketchesArgumentException;

public final class DoublesSketchSortedView implements DoublesSortedView {
   private final double[] quantiles;
   private final long[] cumWeights;
   private final long totalN;

   public DoublesSketchSortedView(double[] quantiles, long[] cumWeights, QuantilesDoublesAPI sk) {
      IncludeMinMax.DoublesPair dPair = IncludeMinMax.includeDoublesMinMax(quantiles, cumWeights, sk.getMaxItem(), sk.getMinItem());
      this.quantiles = dPair.quantiles;
      this.cumWeights = dPair.cumWeights;
      this.totalN = sk.getN();
   }

   DoublesSketchSortedView(double[] quantiles, long[] cumWeights, long totalN, double maxItem, double minItem) {
      IncludeMinMax.DoublesPair dPair = IncludeMinMax.includeDoublesMinMax(quantiles, cumWeights, maxItem, minItem);
      this.quantiles = dPair.quantiles;
      this.cumWeights = dPair.cumWeights;
      this.totalN = totalN;
   }

   public long[] getCumulativeWeights() {
      return (long[])this.cumWeights.clone();
   }

   public double getMaxItem() {
      int top = this.quantiles.length - 1;
      return this.quantiles[top];
   }

   public double getMinItem() {
      return this.quantiles[0];
   }

   public long getN() {
      return this.totalN;
   }

   public int getNumRetained() {
      return this.quantiles.length;
   }

   public double getQuantile(double rank, QuantileSearchCriteria searchCrit) {
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

   public double[] getQuantiles() {
      return (double[])this.quantiles.clone();
   }

   public double getRank(double quantile, QuantileSearchCriteria searchCrit) {
      if (this.isEmpty()) {
         throw new SketchesArgumentException("The sketch must not be empty for this operation. ");
      } else {
         int len = this.quantiles.length;
         InequalitySearch crit = searchCrit == QuantileSearchCriteria.INCLUSIVE ? InequalitySearch.LE : InequalitySearch.LT;
         int index = InequalitySearch.find((double[])this.quantiles, 0, len - 1, quantile, crit);
         return index == -1 ? (double)0.0F : (double)this.cumWeights[index] / (double)this.totalN;
      }
   }

   public boolean isEmpty() {
      return this.totalN == 0L;
   }

   public DoublesSortedViewIterator iterator() {
      return new DoublesSortedViewIterator(this.quantiles, this.cumWeights);
   }
}
