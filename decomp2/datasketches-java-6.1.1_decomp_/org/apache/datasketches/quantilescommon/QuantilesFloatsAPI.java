package org.apache.datasketches.quantilescommon;

public interface QuantilesFloatsAPI extends QuantilesAPI {
   default double[] getCDF(float[] splitPoints) {
      return this.getCDF(splitPoints, QuantileSearchCriteria.INCLUSIVE);
   }

   double[] getCDF(float[] var1, QuantileSearchCriteria var2);

   float getMaxItem();

   float getMinItem();

   default double[] getPMF(float[] splitPoints) {
      return this.getPMF(splitPoints, QuantileSearchCriteria.INCLUSIVE);
   }

   double[] getPMF(float[] var1, QuantileSearchCriteria var2);

   default float getQuantile(double rank) {
      return this.getQuantile(rank, QuantileSearchCriteria.INCLUSIVE);
   }

   float getQuantile(double var1, QuantileSearchCriteria var3);

   float getQuantileLowerBound(double var1);

   float getQuantileUpperBound(double var1);

   default float[] getQuantiles(double[] ranks) {
      return this.getQuantiles(ranks, QuantileSearchCriteria.INCLUSIVE);
   }

   float[] getQuantiles(double[] var1, QuantileSearchCriteria var2);

   default double getRank(float quantile) {
      return this.getRank(quantile, QuantileSearchCriteria.INCLUSIVE);
   }

   double getRank(float var1, QuantileSearchCriteria var2);

   default double[] getRanks(float[] quantiles) {
      return this.getRanks(quantiles, QuantileSearchCriteria.INCLUSIVE);
   }

   double[] getRanks(float[] var1, QuantileSearchCriteria var2);

   int getSerializedSizeBytes();

   FloatsSortedView getSortedView();

   QuantilesFloatsSketchIterator iterator();

   byte[] toByteArray();

   void update(float var1);
}
