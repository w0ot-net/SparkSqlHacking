package org.apache.datasketches.quantilescommon;

public interface FloatsSortedView extends SortedView {
   default double[] getCDF(float[] splitPoints, QuantileSearchCriteria searchCrit) {
      QuantilesUtil.checkFloatsSplitPointsOrder(splitPoints);
      int len = splitPoints.length + 1;
      double[] buckets = new double[len];

      for(int i = 0; i < len - 1; ++i) {
         buckets[i] = this.getRank(splitPoints[i], searchCrit);
      }

      buckets[len - 1] = (double)1.0F;
      return buckets;
   }

   float getMaxItem();

   float getMinItem();

   default double[] getPMF(float[] splitPoints, QuantileSearchCriteria searchCrit) {
      double[] buckets = this.getCDF(splitPoints, searchCrit);
      int len = buckets.length;

      for(int i = len; i-- > 1; buckets[i] -= buckets[i - 1]) {
      }

      return buckets;
   }

   float getQuantile(double var1, QuantileSearchCriteria var3);

   float[] getQuantiles();

   double getRank(float var1, QuantileSearchCriteria var2);

   FloatsSortedViewIterator iterator();
}
