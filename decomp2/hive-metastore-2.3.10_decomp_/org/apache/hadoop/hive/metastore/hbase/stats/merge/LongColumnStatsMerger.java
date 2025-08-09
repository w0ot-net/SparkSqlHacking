package org.apache.hadoop.hive.metastore.hbase.stats.merge;

import org.apache.hadoop.hive.metastore.NumDistinctValueEstimator;
import org.apache.hadoop.hive.metastore.api.ColumnStatisticsObj;
import org.apache.hadoop.hive.metastore.api.LongColumnStatsData;

public class LongColumnStatsMerger extends ColumnStatsMerger {
   public void merge(ColumnStatisticsObj aggregateColStats, ColumnStatisticsObj newColStats) {
      LongColumnStatsData aggregateData = aggregateColStats.getStatsData().getLongStats();
      LongColumnStatsData newData = newColStats.getStatsData().getLongStats();
      aggregateData.setLowValue(Math.min(aggregateData.getLowValue(), newData.getLowValue()));
      aggregateData.setHighValue(Math.max(aggregateData.getHighValue(), newData.getHighValue()));
      aggregateData.setNumNulls(aggregateData.getNumNulls() + newData.getNumNulls());
      if (this.ndvEstimator != null && newData.isSetBitVectors() && newData.getBitVectors().length() != 0) {
         this.ndvEstimator.mergeEstimators(new NumDistinctValueEstimator(aggregateData.getBitVectors(), this.ndvEstimator.getnumBitVectors()));
         this.ndvEstimator.mergeEstimators(new NumDistinctValueEstimator(newData.getBitVectors(), this.ndvEstimator.getnumBitVectors()));
         long ndv = this.ndvEstimator.estimateNumDistinctValues();
         this.LOG.debug("Use bitvector to merge column " + aggregateColStats.getColName() + "'s ndvs of " + aggregateData.getNumDVs() + " and " + newData.getNumDVs() + " to be " + ndv);
         aggregateData.setNumDVs(ndv);
         aggregateData.setBitVectors(this.ndvEstimator.serialize().toString());
      } else {
         aggregateData.setNumDVs(Math.max(aggregateData.getNumDVs(), newData.getNumDVs()));
      }

   }
}
