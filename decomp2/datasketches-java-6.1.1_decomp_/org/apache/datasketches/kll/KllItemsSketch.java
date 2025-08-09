package org.apache.datasketches.kll;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Objects;
import org.apache.datasketches.common.ArrayOfItemsSerDe;
import org.apache.datasketches.common.SketchesArgumentException;
import org.apache.datasketches.common.Util;
import org.apache.datasketches.memory.Memory;
import org.apache.datasketches.memory.MemoryRequestServer;
import org.apache.datasketches.memory.WritableMemory;
import org.apache.datasketches.quantilescommon.GenericPartitionBoundaries;
import org.apache.datasketches.quantilescommon.ItemsSketchSortedView;
import org.apache.datasketches.quantilescommon.QuantileSearchCriteria;
import org.apache.datasketches.quantilescommon.QuantilesGenericAPI;
import org.apache.datasketches.quantilescommon.QuantilesGenericSketchIterator;

public abstract class KllItemsSketch extends KllSketch implements QuantilesGenericAPI {
   private ItemsSketchSortedView itemsSV = null;
   final Comparator comparator;
   final ArrayOfItemsSerDe serDe;

   KllItemsSketch(KllSketch.SketchStructure skStructure, Comparator comparator, ArrayOfItemsSerDe serDe) {
      super(KllSketch.SketchType.ITEMS_SKETCH, skStructure);
      Objects.requireNonNull(comparator, "Comparator must not be null.");
      Objects.requireNonNull(serDe, "SerDe must not be null.");
      this.comparator = comparator;
      this.serDe = serDe;
   }

   public static KllItemsSketch newHeapInstance(Comparator comparator, ArrayOfItemsSerDe serDe) {
      KllItemsSketch<T> itmSk = new KllHeapItemsSketch(200, 8, comparator, serDe);
      return itmSk;
   }

   public static KllItemsSketch newHeapInstance(int k, Comparator comparator, ArrayOfItemsSerDe serDe) {
      return new KllHeapItemsSketch(k, 8, comparator, serDe);
   }

   public static KllItemsSketch heapify(Memory srcMem, Comparator comparator, ArrayOfItemsSerDe serDe) {
      return new KllHeapItemsSketch(srcMem, comparator, serDe);
   }

   public static KllItemsSketch wrap(Memory srcMem, Comparator comparator, ArrayOfItemsSerDe serDe) {
      KllMemoryValidate memVal = new KllMemoryValidate(srcMem, KllSketch.SketchType.ITEMS_SKETCH, serDe);
      return new KllDirectCompactItemsSketch(memVal, comparator, serDe);
   }

   public double[] getCDF(Object[] splitPoints, QuantileSearchCriteria searchCrit) {
      if (this.isEmpty()) {
         throw new SketchesArgumentException("The sketch must not be empty for this operation. ");
      } else {
         this.refreshSortedView();
         return this.itemsSV.getCDF(splitPoints, searchCrit);
      }
   }

   public Class getClassOfT() {
      return this.serDe.getClassOfT();
   }

   public Comparator getComparator() {
      return this.comparator;
   }

   public GenericPartitionBoundaries getPartitionBoundariesFromNumParts(int numEquallySizedParts, QuantileSearchCriteria searchCrit) {
      if (this.isEmpty()) {
         throw new IllegalArgumentException("The sketch must not be empty for this operation. ");
      } else {
         this.refreshSortedView();
         return this.itemsSV.getPartitionBoundariesFromNumParts(numEquallySizedParts, searchCrit);
      }
   }

   public GenericPartitionBoundaries getPartitionBoundariesFromPartSize(long nominalPartSizeItems, QuantileSearchCriteria searchCrit) {
      if (this.isEmpty()) {
         throw new IllegalArgumentException("The sketch must not be empty for this operation. ");
      } else {
         this.refreshSortedView();
         return this.itemsSV.getPartitionBoundariesFromPartSize(nominalPartSizeItems, searchCrit);
      }
   }

   public double[] getPMF(Object[] splitPoints, QuantileSearchCriteria searchCrit) {
      if (this.isEmpty()) {
         throw new SketchesArgumentException("The sketch must not be empty for this operation. ");
      } else {
         this.refreshSortedView();
         return this.itemsSV.getPMF(splitPoints, searchCrit);
      }
   }

   public Object getQuantile(double rank, QuantileSearchCriteria searchCrit) {
      if (this.isEmpty()) {
         throw new SketchesArgumentException("The sketch must not be empty for this operation. ");
      } else {
         this.refreshSortedView();
         return this.itemsSV.getQuantile(rank, searchCrit);
      }
   }

   public Object[] getQuantiles(double[] ranks, QuantileSearchCriteria searchCrit) {
      if (this.isEmpty()) {
         throw new SketchesArgumentException("The sketch must not be empty for this operation. ");
      } else {
         this.refreshSortedView();
         int len = ranks.length;
         T[] quantiles = (T[])((Object[])((Object[])Array.newInstance(this.getMinItem().getClass(), len)));

         for(int i = 0; i < len; ++i) {
            quantiles[i] = this.itemsSV.getQuantile(ranks[i], searchCrit);
         }

         return quantiles;
      }
   }

   public Object getQuantileLowerBound(double rank) {
      return this.getQuantile(Math.max((double)0.0F, rank - KllHelper.getNormalizedRankError(this.getMinK(), false)));
   }

   public Object getQuantileUpperBound(double rank) {
      return this.getQuantile(Math.min((double)1.0F, rank + KllHelper.getNormalizedRankError(this.getMinK(), false)));
   }

   public double getRank(Object quantile, QuantileSearchCriteria searchCrit) {
      if (this.isEmpty()) {
         throw new SketchesArgumentException("The sketch must not be empty for this operation. ");
      } else {
         this.refreshSortedView();
         return this.itemsSV.getRank(quantile, searchCrit);
      }
   }

   public double getRankLowerBound(double rank) {
      return Math.max((double)0.0F, rank - KllHelper.getNormalizedRankError(this.getMinK(), false));
   }

   public double getRankUpperBound(double rank) {
      return Math.min((double)1.0F, rank + KllHelper.getNormalizedRankError(this.getMinK(), false));
   }

   public double[] getRanks(Object[] quantiles, QuantileSearchCriteria searchCrit) {
      if (this.isEmpty()) {
         throw new SketchesArgumentException("The sketch must not be empty for this operation. ");
      } else {
         this.refreshSortedView();
         int len = quantiles.length;
         double[] ranks = new double[len];

         for(int i = 0; i < len; ++i) {
            ranks[i] = this.itemsSV.getRank(quantiles[i], searchCrit);
         }

         return ranks;
      }
   }

   public final ItemsSketchSortedView getSortedView() {
      if (this.isEmpty()) {
         throw new SketchesArgumentException("The sketch must not be empty for this operation. ");
      } else {
         return this.refreshSortedView();
      }
   }

   public QuantilesGenericSketchIterator iterator() {
      return new KllItemsSketchIterator(this.getTotalItemsArray(), this.getLevelsArray(KllSketch.SketchStructure.UPDATABLE), this.getNumLevels());
   }

   public final void merge(KllSketch other) {
      if (!this.readOnly && this.sketchStructure == KllSketch.SketchStructure.UPDATABLE) {
         if (this == other) {
            throw new SketchesArgumentException("A sketch cannot merge with itself. ");
         } else {
            KllItemsSketch<T> othItmSk = (KllItemsSketch)other;
            if (!othItmSk.isEmpty()) {
               KllItemsHelper.mergeItemImpl(this, othItmSk, this.comparator);
               this.itemsSV = null;
            }
         }
      } else {
         throw new SketchesArgumentException("Target sketch is Read Only, cannot write. ");
      }
   }

   public void reset() {
      if (this.readOnly) {
         throw new SketchesArgumentException("Target sketch is Read Only, cannot write. ");
      } else {
         int k = this.getK();
         this.setN(0L);
         this.setMinK(k);
         this.setNumLevels(1);
         this.setLevelZeroSorted(false);
         this.setLevelsArray(new int[]{k, k});
         this.setMinItem((Object)null);
         this.setMaxItem((Object)null);
         this.setItemsArray(new Object[k]);
         this.itemsSV = null;
      }
   }

   public byte[] toByteArray() {
      return KllHelper.toByteArray(this, false);
   }

   public String toString(boolean withLevels, boolean withLevelsAndItems) {
      KllSketch sketch = this;
      if (this.hasMemory()) {
         Memory mem = this.getWritableMemory();

         assert mem != null;

         sketch = heapify(this.getWritableMemory(), this.comparator, this.serDe);
      }

      return KllHelper.toStringImpl(sketch, withLevels, withLevelsAndItems, this.getSerDe());
   }

   public void update(Object item) {
      if (item != null) {
         if (this.readOnly) {
            throw new SketchesArgumentException("Target sketch is Read Only, cannot write. ");
         } else {
            KllItemsHelper.updateItem(this, item);
            this.itemsSV = null;
         }
      }
   }

   public void update(Object item, long weight) {
      if (item != null) {
         if (this.readOnly) {
            throw new SketchesArgumentException("Target sketch is Read Only, cannot write. ");
         } else if (weight < 1L) {
            throw new SketchesArgumentException("Weight is less than one.");
         } else {
            if (weight == 1L) {
               KllItemsHelper.updateItem(this, item);
            } else {
               KllItemsHelper.updateItem(this, item, weight);
            }

            this.itemsSV = null;
         }
      }
   }

   MemoryRequestServer getMemoryRequestServer() {
      return null;
   }

   abstract byte[] getMinMaxByteArr();

   abstract int getMinMaxSizeBytes();

   abstract Object[] getRetainedItemsArray();

   abstract byte[] getRetainedItemsByteArr();

   abstract int getRetainedItemsSizeBytes();

   ArrayOfItemsSerDe getSerDe() {
      return this.serDe;
   }

   abstract Object getSingleItem();

   abstract byte[] getSingleItemByteArr();

   abstract int getSingleItemSizeBytes();

   abstract Object[] getTotalItemsArray();

   byte[] getTotalItemsByteArr() {
      throw new SketchesArgumentException("Unsupported operation for this Sketch Type. ");
   }

   int getTotalItemsNumBytes() {
      throw new SketchesArgumentException("Unsupported operation for this Sketch Type. ");
   }

   void incNumLevels() {
   }

   abstract void setItemsArray(Object[] var1);

   abstract void setItemsArrayAt(int var1, Object var2);

   abstract void setMaxItem(Object var1);

   abstract void setMinItem(Object var1);

   void setNumLevels(int numLevels) {
   }

   void setWritableMemory(WritableMemory wmem) {
      throw new SketchesArgumentException("Unsupported operation for this Sketch Type. Sketch not writable.");
   }

   void updateMinMax(Object item) {
      if (this.isEmpty()) {
         this.setMinItem(item);
         this.setMaxItem(item);
      } else {
         this.setMinItem(Util.minT(this.getMinItem(), item, this.comparator));
         this.setMaxItem(Util.maxT(this.getMaxItem(), item, this.comparator));
      }

   }

   private final ItemsSketchSortedView refreshSortedView() {
      if (this.itemsSV == null) {
         KllItemsSketch<T>.CreateSortedView csv = new CreateSortedView();
         this.itemsSV = csv.getSV();
      }

      return this.itemsSV;
   }

   private static void blockyTandemMergeSort(Object[] quantiles, long[] weights, int[] levels, int numLevels, Comparator comp) {
      if (numLevels != 1) {
         Object[] quantilesTmp = Arrays.copyOf(quantiles, quantiles.length);
         long[] weightsTmp = Arrays.copyOf(weights, quantiles.length);
         blockyTandemMergeSortRecursion(quantilesTmp, weightsTmp, quantiles, weights, levels, 0, numLevels, comp);
      }
   }

   private static void blockyTandemMergeSortRecursion(Object[] quantilesSrc, long[] weightsSrc, Object[] quantilesDst, long[] weightsDst, int[] levels, int startingLevel, int numLevels, Comparator comp) {
      if (numLevels != 1) {
         int numLevels1 = numLevels / 2;
         int numLevels2 = numLevels - numLevels1;

         assert numLevels1 >= 1;

         assert numLevels2 >= numLevels1;

         int startingLevel2 = startingLevel + numLevels1;
         blockyTandemMergeSortRecursion(quantilesDst, weightsDst, quantilesSrc, weightsSrc, levels, startingLevel, numLevels1, comp);
         blockyTandemMergeSortRecursion(quantilesDst, weightsDst, quantilesSrc, weightsSrc, levels, startingLevel2, numLevels2, comp);
         tandemMerge(quantilesSrc, weightsSrc, quantilesDst, weightsDst, levels, startingLevel, numLevels1, startingLevel2, numLevels2, comp);
      }
   }

   private static void tandemMerge(Object[] quantilesSrc, long[] weightsSrc, Object[] quantilesDst, long[] weightsDst, int[] levelStarts, int startingLevel1, int numLevels1, int startingLevel2, int numLevels2, Comparator comp) {
      int fromIndex1 = levelStarts[startingLevel1];
      int toIndex1 = levelStarts[startingLevel1 + numLevels1];
      int fromIndex2 = levelStarts[startingLevel2];
      int toIndex2 = levelStarts[startingLevel2 + numLevels2];
      int iSrc1 = fromIndex1;
      int iSrc2 = fromIndex2;

      int iDst;
      for(iDst = fromIndex1; iSrc1 < toIndex1 && iSrc2 < toIndex2; ++iDst) {
         if (Util.lt(quantilesSrc[iSrc1], quantilesSrc[iSrc2], comp)) {
            quantilesDst[iDst] = quantilesSrc[iSrc1];
            weightsDst[iDst] = weightsSrc[iSrc1];
            ++iSrc1;
         } else {
            quantilesDst[iDst] = quantilesSrc[iSrc2];
            weightsDst[iDst] = weightsSrc[iSrc2];
            ++iSrc2;
         }
      }

      if (iSrc1 < toIndex1) {
         System.arraycopy(quantilesSrc, iSrc1, quantilesDst, iDst, toIndex1 - iSrc1);
         System.arraycopy(weightsSrc, iSrc1, weightsDst, iDst, toIndex1 - iSrc1);
      } else if (iSrc2 < toIndex2) {
         System.arraycopy(quantilesSrc, iSrc2, quantilesDst, iDst, toIndex2 - iSrc2);
         System.arraycopy(weightsSrc, iSrc2, weightsDst, iDst, toIndex2 - iSrc2);
      }

   }

   private final class CreateSortedView {
      Object[] quantiles;
      long[] cumWeights;

      private CreateSortedView() {
      }

      ItemsSketchSortedView getSV() {
         if (!KllItemsSketch.this.isEmpty() && KllItemsSketch.this.getN() != 0L) {
            T[] srcQuantiles = (T[])KllItemsSketch.this.getTotalItemsArray();
            int[] srcLevelsArr = KllItemsSketch.this.levelsArr;
            int srcNumLevels = KllItemsSketch.this.getNumLevels();
            if (!KllItemsSketch.this.isLevelZeroSorted()) {
               Arrays.sort(srcQuantiles, srcLevelsArr[0], srcLevelsArr[1], KllItemsSketch.this.comparator);
               if (!KllItemsSketch.this.hasMemory()) {
                  KllItemsSketch.this.setLevelZeroSorted(true);
               }
            }

            int numQuantiles = KllItemsSketch.this.getNumRetained();
            this.quantiles = Array.newInstance(KllItemsSketch.this.serDe.getClassOfT(), numQuantiles);
            this.cumWeights = new long[numQuantiles];
            this.populateFromSketch(srcQuantiles, srcLevelsArr, srcNumLevels, numQuantiles);
            QuantilesGenericAPI<T> sk = KllItemsSketch.this;
            return new ItemsSketchSortedView(this.quantiles, this.cumWeights, sk);
         } else {
            throw new SketchesArgumentException("The sketch must not be empty for this operation. ");
         }
      }

      private void populateFromSketch(Object[] srcQuantiles, int[] srcLevelsArr, int srcNumLevels, int numItems) {
         int[] myLevelsArr = new int[srcLevelsArr.length];
         int offset = srcLevelsArr[0];
         System.arraycopy(srcQuantiles, offset, this.quantiles, 0, numItems);
         int srcLevel = 0;
         int dstLevel = 0;

         for(long weight = 1L; srcLevel < srcNumLevels; weight *= 2L) {
            int fromIndex = srcLevelsArr[srcLevel] - offset;
            int toIndex = srcLevelsArr[srcLevel + 1] - offset;
            if (fromIndex < toIndex) {
               Arrays.fill(this.cumWeights, fromIndex, toIndex, weight);
               myLevelsArr[dstLevel] = fromIndex;
               myLevelsArr[dstLevel + 1] = toIndex;
               ++dstLevel;
            }

            ++srcLevel;
         }

         KllItemsSketch.blockyTandemMergeSort(this.quantiles, this.cumWeights, myLevelsArr, dstLevel, KllItemsSketch.this.comparator);
         KllHelper.convertToCumulative(this.cumWeights);
      }
   }
}
