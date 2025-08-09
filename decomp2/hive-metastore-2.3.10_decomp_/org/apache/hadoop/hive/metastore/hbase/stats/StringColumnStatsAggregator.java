package org.apache.hadoop.hive.metastore.hbase.stats;

import java.util.Iterator;
import java.util.List;
import org.apache.hadoop.hive.metastore.NumDistinctValueEstimator;
import org.apache.hadoop.hive.metastore.api.ColumnStatistics;
import org.apache.hadoop.hive.metastore.api.ColumnStatisticsData;
import org.apache.hadoop.hive.metastore.api.ColumnStatisticsObj;
import org.apache.hadoop.hive.metastore.api.MetaException;
import org.apache.hadoop.hive.metastore.api.StringColumnStatsData;

public class StringColumnStatsAggregator extends ColumnStatsAggregator {
   public ColumnStatisticsObj aggregate(String colName, List partNames, List css) throws MetaException {
      ColumnStatisticsObj statsObj = null;
      boolean doAllPartitionContainStats = partNames.size() == css.size();
      boolean isNDVBitVectorSet = true;
      String colType = null;
      Iterator var8 = css.iterator();

      while(true) {
         if (var8.hasNext()) {
            ColumnStatistics cs = (ColumnStatistics)var8.next();
            if (cs.getStatsObjSize() != 1) {
               throw new MetaException("The number of columns should be exactly one in aggrStats, but found " + cs.getStatsObjSize());
            }

            ColumnStatisticsObj cso = (ColumnStatisticsObj)cs.getStatsObjIterator().next();
            if (statsObj == null) {
               colType = cso.getColType();
               statsObj = ColumnStatsAggregatorFactory.newColumnStaticsObj(colName, colType, (ColumnStatisticsData._Fields)cso.getStatsData().getSetField());
            }

            if (this.numBitVectors > 0 && cso.getStatsData().getStringStats().isSetBitVectors() && cso.getStatsData().getStringStats().getBitVectors().length() != 0) {
               continue;
            }

            isNDVBitVectorSet = false;
         }

         ColumnStatisticsData columnStatisticsData = new ColumnStatisticsData();
         if (doAllPartitionContainStats && isNDVBitVectorSet) {
            StringColumnStatsData aggregateData = null;
            NumDistinctValueEstimator ndvEstimator = new NumDistinctValueEstimator(this.numBitVectors);

            for(ColumnStatistics cs : css) {
               ColumnStatisticsObj cso = (ColumnStatisticsObj)cs.getStatsObjIterator().next();
               StringColumnStatsData newData = cso.getStatsData().getStringStats();
               ndvEstimator.mergeEstimators(new NumDistinctValueEstimator(newData.getBitVectors(), ndvEstimator.getnumBitVectors()));
               if (aggregateData == null) {
                  aggregateData = newData.deepCopy();
               } else {
                  aggregateData.setMaxColLen(Math.max(aggregateData.getMaxColLen(), newData.getMaxColLen()));
                  aggregateData.setAvgColLen(Math.max(aggregateData.getAvgColLen(), newData.getAvgColLen()));
                  aggregateData.setNumNulls(aggregateData.getNumNulls() + newData.getNumNulls());
               }
            }

            aggregateData.setNumDVs(ndvEstimator.estimateNumDistinctValues());
            columnStatisticsData.setStringStats(aggregateData);
         } else {
            StringColumnStatsData aggregateData = null;

            for(ColumnStatistics cs : css) {
               ColumnStatisticsObj cso = (ColumnStatisticsObj)cs.getStatsObjIterator().next();
               StringColumnStatsData newData = cso.getStatsData().getStringStats();
               if (aggregateData == null) {
                  aggregateData = newData.deepCopy();
               } else {
                  aggregateData.setMaxColLen(Math.max(aggregateData.getMaxColLen(), newData.getMaxColLen()));
                  aggregateData.setAvgColLen(Math.max(aggregateData.getAvgColLen(), newData.getAvgColLen()));
                  aggregateData.setNumNulls(aggregateData.getNumNulls() + newData.getNumNulls());
                  aggregateData.setNumDVs(Math.max(aggregateData.getNumDVs(), newData.getNumDVs()));
               }
            }

            columnStatisticsData.setStringStats(aggregateData);
         }

         statsObj.setStatsData(columnStatisticsData);
         return statsObj;
      }
   }
}
