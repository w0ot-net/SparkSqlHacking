package org.apache.hadoop.hive.metastore.hbase.stats.merge;

import org.apache.commons.lang3.StringUtils;
import org.apache.hadoop.hive.metastore.NumDistinctValueEstimator;
import org.apache.hadoop.hive.metastore.api.BinaryColumnStatsData;
import org.apache.hadoop.hive.metastore.api.BooleanColumnStatsData;
import org.apache.hadoop.hive.metastore.api.ColumnStatisticsData;
import org.apache.hadoop.hive.metastore.api.ColumnStatisticsObj;
import org.apache.hadoop.hive.metastore.api.DecimalColumnStatsData;
import org.apache.hadoop.hive.metastore.api.DoubleColumnStatsData;
import org.apache.hadoop.hive.metastore.api.LongColumnStatsData;
import org.apache.hadoop.hive.metastore.api.StringColumnStatsData;

public class ColumnStatsMergerFactory {
   private ColumnStatsMergerFactory() {
   }

   private static int countNumBitVectors(String s) {
      return s != null ? StringUtils.countMatches(s, "{") : 0;
   }

   public static ColumnStatsMerger getColumnStatsMerger(ColumnStatisticsObj statsObjNew, ColumnStatisticsObj statsObjOld) {
      ColumnStatisticsData._Fields typeNew = (ColumnStatisticsData._Fields)statsObjNew.getStatsData().getSetField();
      ColumnStatisticsData._Fields typeOld = (ColumnStatisticsData._Fields)statsObjOld.getStatsData().getSetField();
      typeNew = typeNew == typeOld ? typeNew : null;
      int numBitVectors = 0;
      ColumnStatsMerger agg;
      switch (typeNew) {
         case BOOLEAN_STATS:
            agg = new BooleanColumnStatsMerger();
            break;
         case LONG_STATS:
            agg = new LongColumnStatsMerger();
            int nbvNew = countNumBitVectors(statsObjNew.getStatsData().getLongStats().getBitVectors());
            int nbvOld = countNumBitVectors(statsObjOld.getStatsData().getLongStats().getBitVectors());
            numBitVectors = nbvNew == nbvOld ? nbvNew : 0;
            break;
         case DOUBLE_STATS:
            agg = new DoubleColumnStatsMerger();
            int nbvNew = countNumBitVectors(statsObjNew.getStatsData().getDoubleStats().getBitVectors());
            int nbvOld = countNumBitVectors(statsObjOld.getStatsData().getDoubleStats().getBitVectors());
            numBitVectors = nbvNew == nbvOld ? nbvNew : 0;
            break;
         case STRING_STATS:
            agg = new StringColumnStatsMerger();
            int nbvNew = countNumBitVectors(statsObjNew.getStatsData().getStringStats().getBitVectors());
            int nbvOld = countNumBitVectors(statsObjOld.getStatsData().getStringStats().getBitVectors());
            numBitVectors = nbvNew == nbvOld ? nbvNew : 0;
            break;
         case BINARY_STATS:
            agg = new BinaryColumnStatsMerger();
            break;
         case DECIMAL_STATS:
            agg = new DecimalColumnStatsMerger();
            int nbvNew = countNumBitVectors(statsObjNew.getStatsData().getDecimalStats().getBitVectors());
            int nbvOld = countNumBitVectors(statsObjOld.getStatsData().getDecimalStats().getBitVectors());
            numBitVectors = nbvNew == nbvOld ? nbvNew : 0;
            break;
         default:
            throw new RuntimeException("Woh, bad.  Unknown stats type " + typeNew.toString());
      }

      if (numBitVectors > 0) {
         agg.ndvEstimator = new NumDistinctValueEstimator(numBitVectors);
      }

      return agg;
   }

   public static ColumnStatisticsObj newColumnStaticsObj(String colName, String colType, ColumnStatisticsData._Fields type) {
      ColumnStatisticsObj cso = new ColumnStatisticsObj();
      ColumnStatisticsData csd = new ColumnStatisticsData();
      cso.setColName(colName);
      cso.setColType(colType);
      switch (type) {
         case BOOLEAN_STATS:
            csd.setBooleanStats(new BooleanColumnStatsData());
            break;
         case LONG_STATS:
            csd.setLongStats(new LongColumnStatsData());
            break;
         case DOUBLE_STATS:
            csd.setDoubleStats(new DoubleColumnStatsData());
            break;
         case STRING_STATS:
            csd.setStringStats(new StringColumnStatsData());
            break;
         case BINARY_STATS:
            csd.setBinaryStats(new BinaryColumnStatsData());
            break;
         case DECIMAL_STATS:
            csd.setDecimalStats(new DecimalColumnStatsData());
            break;
         default:
            throw new RuntimeException("Woh, bad.  Unknown stats type!");
      }

      cso.setStatsData(csd);
      return cso;
   }
}
