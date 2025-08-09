package org.apache.hadoop.hive.metastore.hbase.stats.merge;

import org.apache.hadoop.hive.metastore.api.BinaryColumnStatsData;
import org.apache.hadoop.hive.metastore.api.ColumnStatisticsObj;

public class BinaryColumnStatsMerger extends ColumnStatsMerger {
   public void merge(ColumnStatisticsObj aggregateColStats, ColumnStatisticsObj newColStats) {
      BinaryColumnStatsData aggregateData = aggregateColStats.getStatsData().getBinaryStats();
      BinaryColumnStatsData newData = newColStats.getStatsData().getBinaryStats();
      aggregateData.setMaxColLen(Math.max(aggregateData.getMaxColLen(), newData.getMaxColLen()));
      aggregateData.setAvgColLen(Math.max(aggregateData.getAvgColLen(), newData.getAvgColLen()));
      aggregateData.setNumNulls(aggregateData.getNumNulls() + newData.getNumNulls());
   }
}
