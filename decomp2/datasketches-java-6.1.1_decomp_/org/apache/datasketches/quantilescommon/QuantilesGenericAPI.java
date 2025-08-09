package org.apache.datasketches.quantilescommon;

import java.util.Comparator;

public interface QuantilesGenericAPI extends QuantilesAPI, PartitioningFeature, SketchPartitionLimits {
   default double[] getCDF(Object[] splitPoints) {
      return this.getCDF(splitPoints, QuantileSearchCriteria.INCLUSIVE);
   }

   double[] getCDF(Object[] var1, QuantileSearchCriteria var2);

   Class getClassOfT();

   Comparator getComparator();

   Object getMaxItem();

   default int getMaxPartitions() {
      return (int)Math.min((double)1.0F / this.getNormalizedRankError(true), (double)this.getNumRetained() / (double)2.0F);
   }

   Object getMinItem();

   default double[] getPMF(Object[] splitPoints) {
      return this.getPMF(splitPoints, QuantileSearchCriteria.INCLUSIVE);
   }

   double[] getPMF(Object[] var1, QuantileSearchCriteria var2);

   default Object getQuantile(double rank) {
      return this.getQuantile(rank, QuantileSearchCriteria.INCLUSIVE);
   }

   Object getQuantile(double var1, QuantileSearchCriteria var3);

   Object getQuantileLowerBound(double var1);

   Object getQuantileUpperBound(double var1);

   default Object[] getQuantiles(double[] ranks) {
      return this.getQuantiles(ranks, QuantileSearchCriteria.INCLUSIVE);
   }

   Object[] getQuantiles(double[] var1, QuantileSearchCriteria var2);

   default double getRank(Object quantile) {
      return this.getRank(quantile, QuantileSearchCriteria.INCLUSIVE);
   }

   double getRank(Object var1, QuantileSearchCriteria var2);

   default double[] getRanks(Object[] quantiles) {
      return this.getRanks(quantiles, QuantileSearchCriteria.INCLUSIVE);
   }

   double[] getRanks(Object[] var1, QuantileSearchCriteria var2);

   GenericSortedView getSortedView();

   QuantilesGenericSketchIterator iterator();

   void update(Object var1);
}
