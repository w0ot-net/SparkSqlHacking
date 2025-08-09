package org.apache.hadoop.hive.metastore.hbase.stats;

import java.util.List;
import org.apache.hadoop.hive.metastore.api.BooleanColumnStatsData;
import org.apache.hadoop.hive.metastore.api.ColumnStatistics;
import org.apache.hadoop.hive.metastore.api.ColumnStatisticsData;
import org.apache.hadoop.hive.metastore.api.ColumnStatisticsObj;
import org.apache.hadoop.hive.metastore.api.MetaException;

public class BooleanColumnStatsAggregator extends ColumnStatsAggregator {
   public ColumnStatisticsObj aggregate(String colName, List partNames, List css) throws MetaException {
      ColumnStatisticsObj statsObj = null;
      BooleanColumnStatsData aggregateData = null;
      String colType = null;

      for(ColumnStatistics cs : css) {
         if (cs.getStatsObjSize() != 1) {
            throw new MetaException("The number of columns should be exactly one in aggrStats, but found " + cs.getStatsObjSize());
         }

         ColumnStatisticsObj cso = (ColumnStatisticsObj)cs.getStatsObjIterator().next();
         if (statsObj == null) {
            colType = cso.getColType();
            statsObj = ColumnStatsAggregatorFactory.newColumnStaticsObj(colName, colType, (ColumnStatisticsData._Fields)cso.getStatsData().getSetField());
         }

         BooleanColumnStatsData newData = cso.getStatsData().getBooleanStats();
         if (aggregateData == null) {
            aggregateData = newData.deepCopy();
         } else {
            aggregateData.setNumTrues(aggregateData.getNumTrues() + newData.getNumTrues());
            aggregateData.setNumFalses(aggregateData.getNumFalses() + newData.getNumFalses());
            aggregateData.setNumNulls(aggregateData.getNumNulls() + newData.getNumNulls());
         }
      }

      ColumnStatisticsData columnStatisticsData = new ColumnStatisticsData();
      columnStatisticsData.setBooleanStats(aggregateData);
      statsObj.setStatsData(columnStatisticsData);
      return statsObj;
   }
}
