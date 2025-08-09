package org.apache.hadoop.hive.metastore.hbase.stats.merge;

import org.apache.hadoop.hive.metastore.NumDistinctValueEstimator;
import org.apache.hadoop.hive.metastore.api.ColumnStatisticsObj;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class ColumnStatsMerger {
   protected final Logger LOG = LoggerFactory.getLogger(ColumnStatsMerger.class.getName());
   NumDistinctValueEstimator ndvEstimator = null;

   public abstract void merge(ColumnStatisticsObj var1, ColumnStatisticsObj var2);
}
