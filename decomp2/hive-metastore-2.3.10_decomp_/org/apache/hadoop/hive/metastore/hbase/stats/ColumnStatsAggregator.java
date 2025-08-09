package org.apache.hadoop.hive.metastore.hbase.stats;

import java.util.List;
import org.apache.hadoop.hive.metastore.api.ColumnStatisticsObj;
import org.apache.hadoop.hive.metastore.api.MetaException;

public abstract class ColumnStatsAggregator {
   public int numBitVectors;
   public boolean useDensityFunctionForNDVEstimation;

   public abstract ColumnStatisticsObj aggregate(String var1, List var2, List var3) throws MetaException;
}
