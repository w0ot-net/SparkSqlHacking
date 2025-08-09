package org.apache.hadoop.hive.metastore;

import java.math.BigDecimal;
import java.util.Map;

public class LinearExtrapolatePartStatus implements IExtrapolatePartStatus {
   public Object extrapolate(Object[] min, Object[] max, int colStatIndex, Map indexMap) {
      int rightBorderInd = indexMap.size() - 1;
      int minInd = (Integer)indexMap.get((String)min[1]);
      int maxInd = (Integer)indexMap.get((String)max[1]);
      if (minInd == maxInd) {
         return min[0];
      } else {
         double decimalmin = (double)0.0F;
         double decimalmax = (double)0.0F;
         if (colStatTypes[colStatIndex] == IExtrapolatePartStatus.ColStatType.Decimal) {
            BigDecimal bdmin = new BigDecimal(min[0].toString());
            decimalmin = bdmin.doubleValue();
            BigDecimal bdmax = new BigDecimal(max[0].toString());
            decimalmax = bdmax.doubleValue();
         }

         if (aggrTypes[colStatIndex] == IExtrapolatePartStatus.AggrType.Max) {
            if (minInd < maxInd) {
               if (colStatTypes[colStatIndex] == IExtrapolatePartStatus.ColStatType.Long) {
                  return (Long)min[0] + ((Long)max[0] - (Long)min[0]) * (long)(rightBorderInd - minInd) / (long)(maxInd - minInd);
               } else if (colStatTypes[colStatIndex] == IExtrapolatePartStatus.ColStatType.Double) {
                  return (Double)min[0] + ((Double)max[0] - (Double)min[0]) * (double)(rightBorderInd - minInd) / (double)(maxInd - minInd);
               } else {
                  double ret = decimalmin + (decimalmax - decimalmin) * (double)(rightBorderInd - minInd) / (double)(maxInd - minInd);
                  return String.valueOf(ret);
               }
            } else if (colStatTypes[colStatIndex] == IExtrapolatePartStatus.ColStatType.Long) {
               return (Long)min[0] + ((Long)max[0] - (Long)min[0]) * (long)minInd / (long)(minInd - maxInd);
            } else if (colStatTypes[colStatIndex] == IExtrapolatePartStatus.ColStatType.Double) {
               return (Double)min[0] + ((Double)max[0] - (Double)min[0]) * (double)minInd / (double)(minInd - maxInd);
            } else {
               double ret = decimalmin + (decimalmax - decimalmin) * (double)minInd / (double)(minInd - maxInd);
               return String.valueOf(ret);
            }
         } else if (minInd < maxInd) {
            if (colStatTypes[colStatIndex] == IExtrapolatePartStatus.ColStatType.Long) {
               Long ret = (Long)max[0] - ((Long)max[0] - (Long)min[0]) * (long)maxInd / (long)(maxInd - minInd);
               return ret;
            } else if (colStatTypes[colStatIndex] == IExtrapolatePartStatus.ColStatType.Double) {
               Double ret = (Double)max[0] - ((Double)max[0] - (Double)min[0]) * (double)maxInd / (double)(maxInd - minInd);
               return ret;
            } else {
               double ret = decimalmax - (decimalmax - decimalmin) * (double)maxInd / (double)(maxInd - minInd);
               return String.valueOf(ret);
            }
         } else if (colStatTypes[colStatIndex] == IExtrapolatePartStatus.ColStatType.Long) {
            Long ret = (Long)max[0] - ((Long)max[0] - (Long)min[0]) * (long)(rightBorderInd - maxInd) / (long)(minInd - maxInd);
            return ret;
         } else if (colStatTypes[colStatIndex] == IExtrapolatePartStatus.ColStatType.Double) {
            Double ret = (Double)max[0] - ((Double)max[0] - (Double)min[0]) * (double)(rightBorderInd - maxInd) / (double)(minInd - maxInd);
            return ret;
         } else {
            double ret = decimalmax - (decimalmax - decimalmin) * (double)(rightBorderInd - maxInd) / (double)(minInd - maxInd);
            return String.valueOf(ret);
         }
      }
   }
}
