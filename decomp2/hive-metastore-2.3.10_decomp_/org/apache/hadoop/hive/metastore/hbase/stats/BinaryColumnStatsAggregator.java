package org.apache.hadoop.hive.metastore.hbase.stats;

import java.util.List;
import org.apache.hadoop.hive.metastore.api.BinaryColumnStatsData;
import org.apache.hadoop.hive.metastore.api.ColumnStatistics;
import org.apache.hadoop.hive.metastore.api.ColumnStatisticsData;
import org.apache.hadoop.hive.metastore.api.ColumnStatisticsObj;
import org.apache.hadoop.hive.metastore.api.MetaException;

public class BinaryColumnStatsAggregator extends ColumnStatsAggregator {
   public ColumnStatisticsObj aggregate(String colName, List partNames, List css) throws MetaException {
      ColumnStatisticsObj statsObj = null;
      BinaryColumnStatsData aggregateData = null;
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

         BinaryColumnStatsData newData = cso.getStatsData().getBinaryStats();
         if (aggregateData == null) {
            aggregateData = newData.deepCopy();
         } else {
            aggregateData.setMaxColLen(Math.max(aggregateData.getMaxColLen(), newData.getMaxColLen()));
            aggregateData.setAvgColLen(Math.max(aggregateData.getAvgColLen(), newData.getAvgColLen()));
            aggregateData.setNumNulls(aggregateData.getNumNulls() + newData.getNumNulls());
         }
      }

      ColumnStatisticsData columnStatisticsData = new ColumnStatisticsData();
      columnStatisticsData.setBinaryStats(aggregateData);
      statsObj.setStatsData(columnStatisticsData);
      return statsObj;
   }
}
