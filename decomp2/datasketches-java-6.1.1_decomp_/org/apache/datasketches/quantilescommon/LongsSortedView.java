package org.apache.datasketches.quantilescommon;

public interface LongsSortedView extends SortedView {
   default double[] getCDF(long[] splitPoints, QuantileSearchCriteria searchCrit) {
      QuantilesUtil.checkLongsSplitPointsOrder(splitPoints);
      int len = splitPoints.length + 1;
      double[] buckets = new double[len];

      for(int i = 0; i < len - 1; ++i) {
         buckets[i] = this.getRank(splitPoints[i], searchCrit);
      }

      buckets[len - 1] = (double)1.0F;
      return buckets;
   }

   long getMaxItem();

   long getMinItem();

   default double[] getPMF(long[] splitPoints, QuantileSearchCriteria searchCrit) {
      double[] buckets = this.getCDF(splitPoints, searchCrit);
      int len = buckets.length;

      for(int i = len; i-- > 1; buckets[i] -= buckets[i - 1]) {
      }

      return buckets;
   }

   long getQuantile(double var1, QuantileSearchCriteria var3);

   long[] getQuantiles();

   double getRank(long var1, QuantileSearchCriteria var3);

   LongsSortedViewIterator iterator();
}
