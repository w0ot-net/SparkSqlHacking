package org.apache.datasketches.quantilescommon;

public interface QuantilesLongsAPI extends QuantilesAPI {
   default double[] getCDF(long[] splitPoints) {
      return this.getCDF(splitPoints, QuantileSearchCriteria.INCLUSIVE);
   }

   double[] getCDF(long[] var1, QuantileSearchCriteria var2);

   long getMaxItem();

   long getMinItem();

   default double[] getPMF(long[] splitPoints) {
      return this.getPMF(splitPoints, QuantileSearchCriteria.INCLUSIVE);
   }

   double[] getPMF(long[] var1, QuantileSearchCriteria var2);

   default long getQuantile(double rank) {
      return this.getQuantile(rank, QuantileSearchCriteria.INCLUSIVE);
   }

   long getQuantile(double var1, QuantileSearchCriteria var3);

   long getQuantileLowerBound(double var1);

   long getQuantileUpperBound(double var1);

   default long[] getQuantiles(double[] ranks) {
      return this.getQuantiles(ranks, QuantileSearchCriteria.INCLUSIVE);
   }

   long[] getQuantiles(double[] var1, QuantileSearchCriteria var2);

   default double getRank(long quantile) {
      return this.getRank(quantile, QuantileSearchCriteria.INCLUSIVE);
   }

   double getRank(long var1, QuantileSearchCriteria var3);

   default double[] getRanks(long[] quantiles) {
      return this.getRanks(quantiles, QuantileSearchCriteria.INCLUSIVE);
   }

   double[] getRanks(long[] var1, QuantileSearchCriteria var2);

   int getSerializedSizeBytes();

   LongsSortedView getSortedView();

   QuantilesLongsSketchIterator iterator();

   byte[] toByteArray();

   void update(long var1);
}
