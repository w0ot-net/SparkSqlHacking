package org.apache.hadoop.hive.metastore.hbase.stats;

import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import org.apache.hadoop.hive.metastore.NumDistinctValueEstimator;
import org.apache.hadoop.hive.metastore.api.ColumnStatistics;
import org.apache.hadoop.hive.metastore.api.ColumnStatisticsData;
import org.apache.hadoop.hive.metastore.api.ColumnStatisticsObj;
import org.apache.hadoop.hive.metastore.api.DoubleColumnStatsData;
import org.apache.hadoop.hive.metastore.api.MetaException;

public class DoubleColumnStatsAggregator extends ColumnStatsAggregator implements IExtrapolatePartStatus {
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

            if (this.numBitVectors > 0 && cso.getStatsData().getDoubleStats().isSetBitVectors() && cso.getStatsData().getDoubleStats().getBitVectors().length() != 0) {
               continue;
            }

            isNDVBitVectorSet = false;
         }

         ColumnStatisticsData columnStatisticsData = new ColumnStatisticsData();
         if (!doAllPartitionContainStats && css.size() >= 2) {
            Map<String, Integer> indexMap = new HashMap();

            for(int index = 0; index < partNames.size(); ++index) {
               indexMap.put(partNames.get(index), index);
            }

            Map<String, Double> adjustedIndexMap = new HashMap();
            Map<String, ColumnStatisticsData> adjustedStatsMap = new HashMap();
            double densityAvgSum = (double)0.0F;
            if (!isNDVBitVectorSet) {
               for(ColumnStatistics cs : css) {
                  String partName = cs.getStatsDesc().getPartName();
                  ColumnStatisticsObj cso = (ColumnStatisticsObj)cs.getStatsObjIterator().next();
                  DoubleColumnStatsData newData = cso.getStatsData().getDoubleStats();
                  if (this.useDensityFunctionForNDVEstimation) {
                     densityAvgSum += (newData.getHighValue() - newData.getLowValue()) / (double)newData.getNumDVs();
                  }

                  adjustedIndexMap.put(partName, (double)(Integer)indexMap.get(partName));
                  adjustedStatsMap.put(partName, cso.getStatsData());
               }
            } else {
               NumDistinctValueEstimator ndvEstimator = new NumDistinctValueEstimator(this.numBitVectors);
               StringBuilder pseudoPartName = new StringBuilder();
               double pseudoIndexSum = (double)0.0F;
               int length = 0;
               int curIndex = -1;
               DoubleColumnStatsData aggregateData = null;

               for(ColumnStatistics cs : css) {
                  String partName = cs.getStatsDesc().getPartName();
                  ColumnStatisticsObj cso = (ColumnStatisticsObj)cs.getStatsObjIterator().next();
                  DoubleColumnStatsData newData = cso.getStatsData().getDoubleStats();
                  if ((Integer)indexMap.get(partName) != curIndex) {
                     if (length > 0) {
                        adjustedIndexMap.put(pseudoPartName.toString(), pseudoIndexSum / (double)length);
                        aggregateData.setNumDVs(ndvEstimator.estimateNumDistinctValues());
                        ColumnStatisticsData csd = new ColumnStatisticsData();
                        csd.setDoubleStats(aggregateData);
                        adjustedStatsMap.put(pseudoPartName.toString(), csd);
                        if (this.useDensityFunctionForNDVEstimation) {
                           densityAvgSum += (aggregateData.getHighValue() - aggregateData.getLowValue()) / (double)aggregateData.getNumDVs();
                        }

                        pseudoPartName = new StringBuilder();
                        pseudoIndexSum = (double)0.0F;
                        length = 0;
                     }

                     aggregateData = null;
                  }

                  curIndex = (Integer)indexMap.get(partName);
                  pseudoPartName.append(partName);
                  pseudoIndexSum += (double)curIndex;
                  ++length;
                  ++curIndex;
                  if (aggregateData == null) {
                     aggregateData = newData.deepCopy();
                  } else {
                     aggregateData.setLowValue(Math.min(aggregateData.getLowValue(), newData.getLowValue()));
                     aggregateData.setHighValue(Math.max(aggregateData.getHighValue(), newData.getHighValue()));
                     aggregateData.setNumNulls(aggregateData.getNumNulls() + newData.getNumNulls());
                  }

                  ndvEstimator.mergeEstimators(new NumDistinctValueEstimator(newData.getBitVectors(), ndvEstimator.getnumBitVectors()));
               }

               if (length > 0) {
                  adjustedIndexMap.put(pseudoPartName.toString(), pseudoIndexSum / (double)length);
                  aggregateData.setNumDVs(ndvEstimator.estimateNumDistinctValues());
                  ColumnStatisticsData csd = new ColumnStatisticsData();
                  csd.setDoubleStats(aggregateData);
                  adjustedStatsMap.put(pseudoPartName.toString(), csd);
                  if (this.useDensityFunctionForNDVEstimation) {
                     densityAvgSum += (aggregateData.getHighValue() - aggregateData.getLowValue()) / (double)aggregateData.getNumDVs();
                  }
               }
            }

            this.extrapolate(columnStatisticsData, partNames.size(), css.size(), adjustedIndexMap, adjustedStatsMap, densityAvgSum / (double)adjustedStatsMap.size());
         } else {
            DoubleColumnStatsData aggregateData = null;
            long lowerBound = 0L;
            long higherBound = 0L;
            double densityAvgSum = (double)0.0F;
            NumDistinctValueEstimator ndvEstimator = null;
            if (isNDVBitVectorSet) {
               ndvEstimator = new NumDistinctValueEstimator(this.numBitVectors);
            }

            for(ColumnStatistics cs : css) {
               ColumnStatisticsObj cso = (ColumnStatisticsObj)cs.getStatsObjIterator().next();
               DoubleColumnStatsData newData = cso.getStatsData().getDoubleStats();
               if (this.useDensityFunctionForNDVEstimation) {
                  lowerBound = Math.max(lowerBound, newData.getNumDVs());
                  higherBound += newData.getNumDVs();
                  densityAvgSum += (newData.getHighValue() - newData.getLowValue()) / (double)newData.getNumDVs();
               }

               if (isNDVBitVectorSet) {
                  ndvEstimator.mergeEstimators(new NumDistinctValueEstimator(newData.getBitVectors(), ndvEstimator.getnumBitVectors()));
               }

               if (aggregateData == null) {
                  aggregateData = newData.deepCopy();
               } else {
                  aggregateData.setLowValue(Math.min(aggregateData.getLowValue(), newData.getLowValue()));
                  aggregateData.setHighValue(Math.max(aggregateData.getHighValue(), newData.getHighValue()));
                  aggregateData.setNumNulls(aggregateData.getNumNulls() + newData.getNumNulls());
                  aggregateData.setNumDVs(Math.max(aggregateData.getNumDVs(), newData.getNumDVs()));
               }
            }

            if (isNDVBitVectorSet) {
               aggregateData.setNumDVs(ndvEstimator.estimateNumDistinctValues());
            } else if (this.useDensityFunctionForNDVEstimation) {
               double densityAvg = densityAvgSum / (double)partNames.size();
               long estimation = (long)((aggregateData.getHighValue() - aggregateData.getLowValue()) / densityAvg);
               if (estimation < lowerBound) {
                  aggregateData.setNumDVs(lowerBound);
               } else if (estimation > higherBound) {
                  aggregateData.setNumDVs(higherBound);
               } else {
                  aggregateData.setNumDVs(estimation);
               }
            }

            columnStatisticsData.setDoubleStats(aggregateData);
         }

         statsObj.setStatsData(columnStatisticsData);
         return statsObj;
      }
   }

   public void extrapolate(ColumnStatisticsData extrapolateData, int numParts, int numPartsWithStats, Map adjustedIndexMap, Map adjustedStatsMap, double densityAvg) {
      DoubleColumnStatsData extrapolateDoubleData = new DoubleColumnStatsData();
      Map<String, DoubleColumnStatsData> extractedAdjustedStatsMap = new HashMap();

      for(Map.Entry entry : adjustedStatsMap.entrySet()) {
         extractedAdjustedStatsMap.put(entry.getKey(), ((ColumnStatisticsData)entry.getValue()).getDoubleStats());
      }

      List<Map.Entry<String, DoubleColumnStatsData>> list = new LinkedList(extractedAdjustedStatsMap.entrySet());
      Collections.sort(list, new Comparator() {
         public int compare(Map.Entry o1, Map.Entry o2) {
            return ((DoubleColumnStatsData)o1.getValue()).getLowValue() < ((DoubleColumnStatsData)o2.getValue()).getLowValue() ? -1 : 1;
         }
      });
      double minInd = (Double)adjustedIndexMap.get(((Map.Entry)list.get(0)).getKey());
      double maxInd = (Double)adjustedIndexMap.get(((Map.Entry)list.get(list.size() - 1)).getKey());
      double lowValue = (double)0.0F;
      double min = ((DoubleColumnStatsData)((Map.Entry)list.get(0)).getValue()).getLowValue();
      double max = ((DoubleColumnStatsData)((Map.Entry)list.get(list.size() - 1)).getValue()).getLowValue();
      if (minInd == maxInd) {
         lowValue = min;
      } else if (minInd < maxInd) {
         lowValue = max - (max - min) * maxInd / (maxInd - minInd);
      } else {
         lowValue = max - (max - min) * ((double)numParts - maxInd) / (minInd - maxInd);
      }

      Collections.sort(list, new Comparator() {
         public int compare(Map.Entry o1, Map.Entry o2) {
            return ((DoubleColumnStatsData)o1.getValue()).getHighValue() < ((DoubleColumnStatsData)o2.getValue()).getHighValue() ? -1 : 1;
         }
      });
      minInd = (Double)adjustedIndexMap.get(((Map.Entry)list.get(0)).getKey());
      maxInd = (Double)adjustedIndexMap.get(((Map.Entry)list.get(list.size() - 1)).getKey());
      double highValue = (double)0.0F;
      min = ((DoubleColumnStatsData)((Map.Entry)list.get(0)).getValue()).getHighValue();
      max = ((DoubleColumnStatsData)((Map.Entry)list.get(list.size() - 1)).getValue()).getHighValue();
      if (minInd == maxInd) {
         highValue = min;
      } else if (minInd < maxInd) {
         highValue = min + (max - min) * ((double)numParts - minInd) / (maxInd - minInd);
      } else {
         highValue = min + (max - min) * minInd / (minInd - maxInd);
      }

      long numNulls = 0L;

      for(Map.Entry entry : extractedAdjustedStatsMap.entrySet()) {
         numNulls += ((DoubleColumnStatsData)entry.getValue()).getNumNulls();
      }

      numNulls = numNulls * (long)numParts / (long)numPartsWithStats;
      long ndv = 0L;
      long ndvMin = 0L;
      long ndvMax = 0L;
      Collections.sort(list, new Comparator() {
         public int compare(Map.Entry o1, Map.Entry o2) {
            return ((DoubleColumnStatsData)o1.getValue()).getNumDVs() < ((DoubleColumnStatsData)o2.getValue()).getNumDVs() ? -1 : 1;
         }
      });
      long lowerBound = ((DoubleColumnStatsData)((Map.Entry)list.get(list.size() - 1)).getValue()).getNumDVs();
      long higherBound = 0L;

      for(Map.Entry entry : list) {
         higherBound += ((DoubleColumnStatsData)entry.getValue()).getNumDVs();
      }

      if (this.useDensityFunctionForNDVEstimation && densityAvg != (double)0.0F) {
         ndv = (long)((highValue - lowValue) / densityAvg);
         if (ndv < lowerBound) {
            ndv = lowerBound;
         } else if (ndv > higherBound) {
            ndv = higherBound;
         }
      } else {
         minInd = (Double)adjustedIndexMap.get(((Map.Entry)list.get(0)).getKey());
         maxInd = (Double)adjustedIndexMap.get(((Map.Entry)list.get(list.size() - 1)).getKey());
         ndvMin = ((DoubleColumnStatsData)((Map.Entry)list.get(0)).getValue()).getNumDVs();
         ndvMax = ((DoubleColumnStatsData)((Map.Entry)list.get(list.size() - 1)).getValue()).getNumDVs();
         if (minInd == maxInd) {
            ndv = ndvMin;
         } else if (minInd < maxInd) {
            ndv = (long)((double)ndvMin + (double)(ndvMax - ndvMin) * ((double)numParts - minInd) / (maxInd - minInd));
         } else {
            ndv = (long)((double)ndvMin + (double)(ndvMax - ndvMin) * minInd / (minInd - maxInd));
         }
      }

      extrapolateDoubleData.setLowValue(lowValue);
      extrapolateDoubleData.setHighValue(highValue);
      extrapolateDoubleData.setNumNulls(numNulls);
      extrapolateDoubleData.setNumDVs(ndv);
      extrapolateData.setDoubleStats(extrapolateDoubleData);
   }
}
