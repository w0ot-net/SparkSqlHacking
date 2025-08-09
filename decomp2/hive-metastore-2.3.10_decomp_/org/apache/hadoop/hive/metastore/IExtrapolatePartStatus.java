package org.apache.hadoop.hive.metastore;

import java.util.HashMap;
import java.util.Map;

public interface IExtrapolatePartStatus {
   String[] colStatNames = new String[]{"LONG_LOW_VALUE", "LONG_HIGH_VALUE", "DOUBLE_LOW_VALUE", "DOUBLE_HIGH_VALUE", "BIG_DECIMAL_LOW_VALUE", "BIG_DECIMAL_HIGH_VALUE", "NUM_NULLS", "NUM_DISTINCTS", "AVG_COL_LEN", "MAX_COL_LEN", "NUM_TRUES", "NUM_FALSES", "AVG_NDV_LONG", "AVG_NDV_DOUBLE", "AVG_NDV_DECIMAL", "SUM_NUM_DISTINCTS"};
   HashMap indexMaps = new HashMap() {
      {
         this.put("bigint", new Integer[]{0, 1, 6, 7, 12, 15});
         this.put("int", new Integer[]{0, 1, 6, 7, 12, 15});
         this.put("smallint", new Integer[]{0, 1, 6, 7, 12, 15});
         this.put("tinyint", new Integer[]{0, 1, 6, 7, 12, 15});
         this.put("date", new Integer[]{0, 1, 6, 7, 12, 15});
         this.put("timestamp", new Integer[]{0, 1, 6, 7, 12, 15});
         this.put("long", new Integer[]{0, 1, 6, 7, 12, 15});
         this.put("double", new Integer[]{2, 3, 6, 7, 13, 15});
         this.put("float", new Integer[]{2, 3, 6, 7, 13, 15});
         this.put("varchar", new Integer[]{8, 9, 6, 7, 15});
         this.put("char", new Integer[]{8, 9, 6, 7, 15});
         this.put("string", new Integer[]{8, 9, 6, 7, 15});
         this.put("boolean", new Integer[]{10, 11, 6, 15});
         this.put("binary", new Integer[]{8, 9, 6, 15});
         this.put("decimal", new Integer[]{4, 5, 6, 7, 14, 15});
         this.put("default", new Integer[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 15});
      }
   };
   ColStatType[] colStatTypes = new ColStatType[]{IExtrapolatePartStatus.ColStatType.Long, IExtrapolatePartStatus.ColStatType.Long, IExtrapolatePartStatus.ColStatType.Double, IExtrapolatePartStatus.ColStatType.Double, IExtrapolatePartStatus.ColStatType.Decimal, IExtrapolatePartStatus.ColStatType.Decimal, IExtrapolatePartStatus.ColStatType.Long, IExtrapolatePartStatus.ColStatType.Long, IExtrapolatePartStatus.ColStatType.Double, IExtrapolatePartStatus.ColStatType.Long, IExtrapolatePartStatus.ColStatType.Long, IExtrapolatePartStatus.ColStatType.Long, IExtrapolatePartStatus.ColStatType.Double, IExtrapolatePartStatus.ColStatType.Double, IExtrapolatePartStatus.ColStatType.Double, IExtrapolatePartStatus.ColStatType.Long};
   AggrType[] aggrTypes = new AggrType[]{IExtrapolatePartStatus.AggrType.Min, IExtrapolatePartStatus.AggrType.Max, IExtrapolatePartStatus.AggrType.Min, IExtrapolatePartStatus.AggrType.Max, IExtrapolatePartStatus.AggrType.Min, IExtrapolatePartStatus.AggrType.Max, IExtrapolatePartStatus.AggrType.Sum, IExtrapolatePartStatus.AggrType.Max, IExtrapolatePartStatus.AggrType.Max, IExtrapolatePartStatus.AggrType.Max, IExtrapolatePartStatus.AggrType.Sum, IExtrapolatePartStatus.AggrType.Sum, IExtrapolatePartStatus.AggrType.Avg, IExtrapolatePartStatus.AggrType.Avg, IExtrapolatePartStatus.AggrType.Avg, IExtrapolatePartStatus.AggrType.Sum};

   Object extrapolate(Object[] var1, Object[] var2, int var3, Map var4);

   public static enum ColStatType {
      Long,
      Double,
      Decimal;
   }

   public static enum AggrType {
      Min,
      Max,
      Sum,
      Avg;
   }
}
