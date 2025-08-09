package org.apache.datasketches.quantilescommon;

import java.lang.reflect.Array;
import java.util.Comparator;
import org.apache.datasketches.common.SketchesArgumentException;

public class ItemsSketchSortedView implements GenericSortedView {
   private final Object[] quantiles;
   private final long[] cumWeights;
   private final long totalN;
   private final Comparator comparator;
   private final Class clazz;
   private final double normRankError;
   private final int numRetItems;

   public ItemsSketchSortedView(Object[] quantiles, long[] cumWeights, QuantilesGenericAPI sk) {
      this.comparator = sk.getComparator();
      IncludeMinMax.ItemsPair<T> iPair = IncludeMinMax.includeItemsMinMax(quantiles, cumWeights, sk.getMaxItem(), sk.getMinItem(), this.comparator);
      this.quantiles = iPair.quantiles;
      this.cumWeights = iPair.cumWeights;
      this.totalN = sk.getN();
      this.clazz = sk.getClassOfT();
      this.normRankError = sk.getNormalizedRankError(true);
      this.numRetItems = sk.getNumRetained();
   }

   ItemsSketchSortedView(Object[] quantiles, long[] cumWeights, long totalN, Comparator comparator, Object maxItem, Object minItem, Class clazz, double normRankError, int numRetItems) {
      this.comparator = comparator;
      IncludeMinMax.ItemsPair<T> iPair = IncludeMinMax.includeItemsMinMax(quantiles, cumWeights, maxItem, minItem, comparator);
      this.quantiles = iPair.quantiles;
      this.cumWeights = iPair.cumWeights;
      this.totalN = totalN;
      this.clazz = clazz;
      this.normRankError = normRankError;
      this.numRetItems = numRetItems;
   }

   public Comparator getComparator() {
      return this.comparator;
   }

   public long[] getCumulativeWeights() {
      return (long[])this.cumWeights.clone();
   }

   public Object getMaxItem() {
      int top = this.quantiles.length - 1;
      return this.quantiles[top];
   }

   public Object getMinItem() {
      return this.quantiles[0];
   }

   public long getN() {
      return this.totalN;
   }

   public int getNumRetained() {
      return this.quantiles.length;
   }

   public int getMaxPartitions() {
      return (int)Math.min((double)1.0F / this.normRankError, (double)this.numRetItems / (double)2.0F);
   }

   public GenericPartitionBoundaries getPartitionBoundariesFromPartSize(long nominalPartitionSize, QuantileSearchCriteria searchCrit) {
      if (this.isEmpty()) {
         throw new SketchesArgumentException("The sketch must not be empty for this operation. ");
      } else {
         long minPartSizeItems = this.getMinPartitionSizeItems();
         if (nominalPartitionSize < minPartSizeItems) {
            throw new SketchesArgumentException("Unsupported operation for this Sketch Type.  The requested nominal partition size is too small for this sketch.");
         } else {
            long totalN = this.totalN;
            int numEquallySizedParts = (int)Math.min(totalN / minPartSizeItems, (long)this.getMaxPartitions());
            return this.getPartitionBoundariesFromNumParts(numEquallySizedParts);
         }
      }
   }

   public GenericPartitionBoundaries getPartitionBoundariesFromNumParts(int numEquallySizedParts, QuantileSearchCriteria searchCrit) {
      if (this.isEmpty()) {
         throw new SketchesArgumentException("The sketch must not be empty for this operation. ");
      } else {
         int maxParts = this.getMaxPartitions();
         if (numEquallySizedParts > maxParts) {
            throw new SketchesArgumentException("Unsupported operation for this Sketch Type.  The requested number of partitions is too large for this sketch.");
         } else {
            double[] searchNormRanks = QuantilesUtil.evenlySpacedDoubles((double)0.0F, (double)1.0F, numEquallySizedParts + 1);
            int partArrLen = searchNormRanks.length;
            T[] partQuantiles = (T[])((Object[])((Object[])Array.newInstance(this.clazz, partArrLen)));
            long[] partNatRanks = new long[partArrLen];
            double[] partNormRanks = new double[partArrLen];

            for(int i = 0; i < partArrLen; ++i) {
               int index = this.getQuantileIndex(searchNormRanks[i], this.cumWeights, searchCrit);
               partQuantiles[i] = this.quantiles[index];
               long cumWt = this.cumWeights[index];
               partNatRanks[i] = cumWt;
               partNormRanks[i] = (double)cumWt / (double)this.totalN;
            }

            GenericPartitionBoundaries<T> gpb = new GenericPartitionBoundaries(this.totalN, partQuantiles, partNatRanks, partNormRanks, this.getMaxItem(), this.getMinItem(), searchCrit);
            return gpb;
         }
      }
   }

   public Object getQuantile(double rank, QuantileSearchCriteria searchCrit) {
      if (this.isEmpty()) {
         throw new SketchesArgumentException("The sketch must not be empty for this operation. ");
      } else {
         QuantilesUtil.checkNormalizedRankBounds(rank);
         int index = this.getQuantileIndex(rank, this.cumWeights, searchCrit);
         return this.quantiles[index];
      }
   }

   private int getQuantileIndex(double normRank, long[] localCumWeights, QuantileSearchCriteria searchCrit) {
      int len = localCumWeights.length;
      double naturalRank = QuantilesUtil.getNaturalRank(normRank, this.totalN, searchCrit);
      InequalitySearch crit = searchCrit == QuantileSearchCriteria.INCLUSIVE ? InequalitySearch.GE : InequalitySearch.GT;
      int index = InequalitySearch.find((long[])localCumWeights, 0, len - 1, naturalRank, crit);
      return index == -1 ? len - 1 : index;
   }

   public Object[] getQuantiles(double[] ranks, QuantileSearchCriteria searchCrit) {
      if (this.isEmpty()) {
         throw new IllegalArgumentException("The sketch must not be empty for this operation. ");
      } else {
         int len = ranks.length;
         T[] quants = (T[])((Object[])((Object[])Array.newInstance(this.clazz, len)));

         for(int i = 0; i < len; ++i) {
            quants[i] = this.getQuantile(ranks[i], searchCrit);
         }

         return quants;
      }
   }

   public Object[] getQuantiles() {
      return this.quantiles.clone();
   }

   public double getRank(Object quantile, QuantileSearchCriteria searchCrit) {
      if (this.isEmpty()) {
         throw new SketchesArgumentException("The sketch must not be empty for this operation. ");
      } else {
         int len = this.quantiles.length;
         GenericInequalitySearch.Inequality crit = searchCrit == QuantileSearchCriteria.INCLUSIVE ? GenericInequalitySearch.Inequality.LE : GenericInequalitySearch.Inequality.LT;
         int index = GenericInequalitySearch.find(this.quantiles, 0, len - 1, quantile, crit, this.comparator);
         return index == -1 ? (double)0.0F : (double)this.cumWeights[index] / (double)this.totalN;
      }
   }

   public boolean isEmpty() {
      return this.totalN == 0L;
   }

   public GenericSortedViewIterator iterator() {
      return new GenericSortedViewIterator(this.quantiles, this.cumWeights);
   }
}
