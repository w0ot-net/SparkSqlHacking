package org.apache.hadoop.hive.metastore.hbase.stats.merge;

import org.apache.hadoop.hive.metastore.NumDistinctValueEstimator;
import org.apache.hadoop.hive.metastore.api.ColumnStatisticsObj;
import org.apache.hadoop.hive.metastore.api.Decimal;
import org.apache.hadoop.hive.metastore.api.DecimalColumnStatsData;

public class DecimalColumnStatsMerger extends ColumnStatsMerger {
   public void merge(ColumnStatisticsObj aggregateColStats, ColumnStatisticsObj newColStats) {
      DecimalColumnStatsData aggregateData = aggregateColStats.getStatsData().getDecimalStats();
      DecimalColumnStatsData newData = newColStats.getStatsData().getDecimalStats();
      Decimal lowValue = aggregateData.getLowValue() != null && aggregateData.getLowValue().compareTo(newData.getLowValue()) > 0 ? aggregateData.getLowValue() : newData.getLowValue();
      aggregateData.setLowValue(lowValue);
      Decimal highValue = aggregateData.getHighValue() != null && aggregateData.getHighValue().compareTo(newData.getHighValue()) > 0 ? aggregateData.getHighValue() : newData.getHighValue();
      aggregateData.setHighValue(highValue);
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
