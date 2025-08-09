package org.apache.hadoop.hive.metastore.hbase.stats;

import java.util.Map;
import org.apache.hadoop.hive.metastore.api.ColumnStatisticsData;

public interface IExtrapolatePartStatus {
   void extrapolate(ColumnStatisticsData var1, int var2, int var3, Map var4, Map var5, double var6);
}
