package org.apache.datasketches.quantilescommon;

public interface QuantilesDoublesAPI extends QuantilesAPI {
   default double[] getCDF(double[] splitPoints) {
      return this.getCDF(splitPoints, QuantileSearchCriteria.INCLUSIVE);
   }

   double[] getCDF(double[] var1, QuantileSearchCriteria var2);

   double getMaxItem();

   double getMinItem();

   default double[] getPMF(double[] splitPoints) {
      return this.getPMF(splitPoints, QuantileSearchCriteria.INCLUSIVE);
   }

   double[] getPMF(double[] var1, QuantileSearchCriteria var2);

   default double getQuantile(double rank) {
      return this.getQuantile(rank, QuantileSearchCriteria.INCLUSIVE);
   }

   double getQuantile(double var1, QuantileSearchCriteria var3);

   double getQuantileLowerBound(double var1);

   double getQuantileUpperBound(double var1);

   default double[] getQuantiles(double[] ranks) {
      return this.getQuantiles(ranks, QuantileSearchCriteria.INCLUSIVE);
   }

   double[] getQuantiles(double[] var1, QuantileSearchCriteria var2);

   default double getRank(double quantile) {
      return this.getRank(quantile, QuantileSearchCriteria.INCLUSIVE);
   }

   double getRank(double var1, QuantileSearchCriteria var3);

   default double[] getRanks(double[] quantiles) {
      return this.getRanks(quantiles, QuantileSearchCriteria.INCLUSIVE);
   }

   double[] getRanks(double[] var1, QuantileSearchCriteria var2);

   int getSerializedSizeBytes();

   DoublesSortedView getSortedView();

   QuantilesDoublesSketchIterator iterator();

   byte[] toByteArray();

   void update(double var1);
}
