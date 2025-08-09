package org.apache.datasketches.quantilescommon;

public interface DoublesSortedView extends SortedView {
   default double[] getCDF(double[] splitPoints, QuantileSearchCriteria searchCrit) {
      QuantilesUtil.checkDoublesSplitPointsOrder(splitPoints);
      int len = splitPoints.length + 1;
      double[] buckets = new double[len];

      for(int i = 0; i < len - 1; ++i) {
         buckets[i] = this.getRank(splitPoints[i], searchCrit);
      }

      buckets[len - 1] = (double)1.0F;
      return buckets;
   }

   double getMaxItem();

   double getMinItem();

   default double[] getPMF(double[] splitPoints, QuantileSearchCriteria searchCrit) {
      double[] buckets = this.getCDF(splitPoints, searchCrit);
      int len = buckets.length;

      for(int i = len; i-- > 1; buckets[i] -= buckets[i - 1]) {
      }

      return buckets;
   }

   double getQuantile(double var1, QuantileSearchCriteria var3);

   double[] getQuantiles();

   double getRank(double var1, QuantileSearchCriteria var3);

   DoublesSortedViewIterator iterator();
}
