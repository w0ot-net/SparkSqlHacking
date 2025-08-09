package org.apache.hadoop.hive.metastore.hbase.stats.merge;

import org.apache.hadoop.hive.metastore.api.BooleanColumnStatsData;
import org.apache.hadoop.hive.metastore.api.ColumnStatisticsObj;

public class BooleanColumnStatsMerger extends ColumnStatsMerger {
   public void merge(ColumnStatisticsObj aggregateColStats, ColumnStatisticsObj newColStats) {
      BooleanColumnStatsData aggregateData = aggregateColStats.getStatsData().getBooleanStats();
      BooleanColumnStatsData newData = newColStats.getStatsData().getBooleanStats();
      aggregateData.setNumTrues(aggregateData.getNumTrues() + newData.getNumTrues());
      aggregateData.setNumFalses(aggregateData.getNumFalses() + newData.getNumFalses());
      aggregateData.setNumNulls(aggregateData.getNumNulls() + newData.getNumNulls());
   }
}
