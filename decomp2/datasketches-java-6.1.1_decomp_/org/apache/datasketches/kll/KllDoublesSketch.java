package org.apache.datasketches.kll;

import java.util.Arrays;
import java.util.Objects;
import org.apache.datasketches.common.ArrayOfItemsSerDe;
import org.apache.datasketches.common.ByteArrayUtil;
import org.apache.datasketches.common.SketchesArgumentException;
import org.apache.datasketches.common.SuppressFBWarnings;
import org.apache.datasketches.memory.DefaultMemoryRequestServer;
import org.apache.datasketches.memory.Memory;
import org.apache.datasketches.memory.MemoryRequestServer;
import org.apache.datasketches.memory.WritableMemory;
import org.apache.datasketches.quantilescommon.DoublesSketchSortedView;
import org.apache.datasketches.quantilescommon.QuantileSearchCriteria;
import org.apache.datasketches.quantilescommon.QuantilesDoublesAPI;
import org.apache.datasketches.quantilescommon.QuantilesDoublesSketchIterator;

public abstract class KllDoublesSketch extends KllSketch implements QuantilesDoublesAPI {
   private DoublesSketchSortedView doublesSV = null;
   static final int ITEM_BYTES = 8;

   KllDoublesSketch(KllSketch.SketchStructure sketchStructure) {
      super(KllSketch.SketchType.DOUBLES_SKETCH, sketchStructure);
   }

   public static KllDoublesSketch newHeapInstance() {
      return newHeapInstance(200);
   }

   public static KllDoublesSketch newHeapInstance(int k) {
      return new KllHeapDoublesSketch(k, 8);
   }

   public static KllDoublesSketch newDirectInstance(WritableMemory dstMem, MemoryRequestServer memReqSvr) {
      return newDirectInstance(200, dstMem, memReqSvr);
   }

   public static KllDoublesSketch newDirectInstance(int k, WritableMemory dstMem, MemoryRequestServer memReqSvr) {
      Objects.requireNonNull(dstMem, "Parameter 'dstMem' must not be null");
      Objects.requireNonNull(memReqSvr, "Parameter 'memReqSvr' must not be null");
      return KllDirectDoublesSketch.newDirectUpdatableInstance(k, 8, dstMem, memReqSvr);
   }

   public static KllDoublesSketch heapify(Memory srcMem) {
      Objects.requireNonNull(srcMem, "Parameter 'srcMem' must not be null");
      return KllHeapDoublesSketch.heapifyImpl(srcMem);
   }

   public static KllDoublesSketch wrap(Memory srcMem) {
      Objects.requireNonNull(srcMem, "Parameter 'srcMem' must not be null");
      KllMemoryValidate memVal = new KllMemoryValidate(srcMem, KllSketch.SketchType.DOUBLES_SKETCH, (ArrayOfItemsSerDe)null);
      if (memVal.sketchStructure == KllSketch.SketchStructure.UPDATABLE) {
         MemoryRequestServer memReqSvr = new DefaultMemoryRequestServer();
         return new KllDirectDoublesSketch(memVal.sketchStructure, (WritableMemory)srcMem, memReqSvr, memVal);
      } else {
         return new KllDirectDoublesSketch.KllDirectCompactDoublesSketch(memVal.sketchStructure, srcMem, memVal);
      }
   }

   public static KllDoublesSketch writableWrap(WritableMemory srcMem, MemoryRequestServer memReqSvr) {
      Objects.requireNonNull(srcMem, "Parameter 'srcMem' must not be null");
      Objects.requireNonNull(memReqSvr, "Parameter 'memReqSvr' must not be null");
      KllMemoryValidate memVal = new KllMemoryValidate(srcMem, KllSketch.SketchType.DOUBLES_SKETCH);
      return (KllDoublesSketch)(memVal.sketchStructure == KllSketch.SketchStructure.UPDATABLE ? new KllDirectDoublesSketch(KllSketch.SketchStructure.UPDATABLE, srcMem, memReqSvr, memVal) : new KllDirectDoublesSketch.KllDirectCompactDoublesSketch(memVal.sketchStructure, srcMem, memVal));
   }

   public double[] getCDF(double[] splitPoints, QuantileSearchCriteria searchCrit) {
      if (this.isEmpty()) {
         throw new SketchesArgumentException("The sketch must not be empty for this operation. ");
      } else {
         this.refreshSortedView();
         return this.doublesSV.getCDF(splitPoints, searchCrit);
      }
   }

   public double[] getPMF(double[] splitPoints, QuantileSearchCriteria searchCrit) {
      if (this.isEmpty()) {
         throw new SketchesArgumentException("The sketch must not be empty for this operation. ");
      } else {
         this.refreshSortedView();
         return this.doublesSV.getPMF(splitPoints, searchCrit);
      }
   }

   public double getQuantile(double rank, QuantileSearchCriteria searchCrit) {
      if (this.isEmpty()) {
         throw new SketchesArgumentException("The sketch must not be empty for this operation. ");
      } else {
         this.refreshSortedView();
         return this.doublesSV.getQuantile(rank, searchCrit);
      }
   }

   public double[] getQuantiles(double[] ranks, QuantileSearchCriteria searchCrit) {
      if (this.isEmpty()) {
         throw new SketchesArgumentException("The sketch must not be empty for this operation. ");
      } else {
         this.refreshSortedView();
         int len = ranks.length;
         double[] quantiles = new double[len];

         for(int i = 0; i < len; ++i) {
            quantiles[i] = this.doublesSV.getQuantile(ranks[i], searchCrit);
         }

         return quantiles;
      }
   }

   public double getQuantileLowerBound(double rank) {
      return this.getQuantile(Math.max((double)0.0F, rank - KllHelper.getNormalizedRankError(this.getMinK(), false)));
   }

   public double getQuantileUpperBound(double rank) {
      return this.getQuantile(Math.min((double)1.0F, rank + KllHelper.getNormalizedRankError(this.getMinK(), false)));
   }

   public double getRank(double quantile, QuantileSearchCriteria searchCrit) {
      if (this.isEmpty()) {
         throw new SketchesArgumentException("The sketch must not be empty for this operation. ");
      } else {
         this.refreshSortedView();
         return this.doublesSV.getRank(quantile, searchCrit);
      }
   }

   public double getRankLowerBound(double rank) {
      return Math.max((double)0.0F, rank - KllHelper.getNormalizedRankError(this.getMinK(), false));
   }

   public double getRankUpperBound(double rank) {
      return Math.min((double)1.0F, rank + KllHelper.getNormalizedRankError(this.getMinK(), false));
   }

   public double[] getRanks(double[] quantiles, QuantileSearchCriteria searchCrit) {
      if (this.isEmpty()) {
         throw new SketchesArgumentException("The sketch must not be empty for this operation. ");
      } else {
         this.refreshSortedView();
         int len = quantiles.length;
         double[] ranks = new double[len];

         for(int i = 0; i < len; ++i) {
            ranks[i] = this.doublesSV.getRank(quantiles[i], searchCrit);
         }

         return ranks;
      }
   }

   public QuantilesDoublesSketchIterator iterator() {
      return new KllDoublesSketchIterator(this.getDoubleItemsArray(), this.getLevelsArray(KllSketch.SketchStructure.UPDATABLE), this.getNumLevels());
   }

   public final void merge(KllSketch other) {
      if (!this.readOnly && this.sketchStructure == KllSketch.SketchStructure.UPDATABLE) {
         if (this == other) {
            throw new SketchesArgumentException("A sketch cannot merge with itself. ");
         } else {
            KllDoublesSketch othDblSk = (KllDoublesSketch)other;
            if (!othDblSk.isEmpty()) {
               KllDoublesHelper.mergeDoubleImpl(this, othDblSk);
               this.doublesSV = null;
            }
         }
      } else {
         throw new SketchesArgumentException("Target sketch is Read Only, cannot write. ");
      }
   }

   public final void reset() {
      if (this.readOnly) {
         throw new SketchesArgumentException("Target sketch is Read Only, cannot write. ");
      } else {
         int k = this.getK();
         this.setN(0L);
         this.setMinK(k);
         this.setNumLevels(1);
         this.setLevelZeroSorted(false);
         this.setLevelsArray(new int[]{k, k});
         this.setMinItem(Double.NaN);
         this.setMaxItem(Double.NaN);
         this.setDoubleItemsArray(new double[k]);
         this.doublesSV = null;
      }
   }

   public byte[] toByteArray() {
      return KllHelper.toByteArray(this, false);
   }

   public String toString(boolean withLevels, boolean withLevelsAndItems) {
      KllSketch sketch = this;
      if (withLevelsAndItems && this.sketchStructure != KllSketch.SketchStructure.UPDATABLE) {
         Memory mem = this.getWritableMemory();

         assert mem != null;

         sketch = heapify(this.getWritableMemory());
      }

      return KllHelper.toStringImpl(sketch, withLevels, withLevelsAndItems, this.getSerDe());
   }

   public void update(double item) {
      if (!Double.isNaN(item)) {
         if (this.readOnly) {
            throw new SketchesArgumentException("Target sketch is Read Only, cannot write. ");
         } else {
            updateDouble(this, item);
            this.doublesSV = null;
         }
      }
   }

   static void updateDouble(KllDoublesSketch dblSk, double item) {
      dblSk.updateMinMax(item);
      int freeSpace = dblSk.levelsArr[0];

      assert freeSpace >= 0;

      if (freeSpace == 0) {
         KllDoublesHelper.compressWhileUpdatingSketch(dblSk);
         freeSpace = dblSk.levelsArr[0];

         assert freeSpace > 0;
      }

      dblSk.incN(1);
      dblSk.setLevelZeroSorted(false);
      int nextPos = freeSpace - 1;
      dblSk.setLevelsArrayAt(0, nextPos);
      dblSk.setDoubleItemsArrayAt(nextPos, item);
   }

   final void updateMinMax(double item) {
      if (!this.isEmpty() && !Double.isNaN(this.getMinItemInternal())) {
         this.setMinItem(Math.min(this.getMinItemInternal(), item));
         this.setMaxItem(Math.max(this.getMaxItemInternal(), item));
      } else {
         this.setMinItem(item);
         this.setMaxItem(item);
      }

   }

   public void update(double item, long weight) {
      if (!Double.isNaN(item)) {
         if (this.readOnly) {
            throw new SketchesArgumentException("Target sketch is Read Only, cannot write. ");
         } else if (weight < 1L) {
            throw new SketchesArgumentException("Weight is less than one.");
         } else {
            if (weight == 1L) {
               updateDouble(this, item);
            } else if (weight < (long)this.levelsArr[0]) {
               for(int i = 0; i < (int)weight; ++i) {
                  updateDouble(this, item);
               }
            } else {
               KllHeapDoublesSketch tmpSk = new KllHeapDoublesSketch(this.getK(), 8, item, weight);
               this.merge(tmpSk);
            }

            this.doublesSV = null;
         }
      }
   }

   public void update(double[] items, int offset, int length) {
      if (this.readOnly) {
         throw new SketchesArgumentException("Target sketch is Read Only, cannot write. ");
      } else if (length != 0) {
         if (!hasNaN(items, offset, length)) {
            this.updateDouble(items, offset, length);
            this.doublesSV = null;
         } else {
            int end = offset + length;

            for(int i = offset; i < end; ++i) {
               double v = items[i];
               if (!Double.isNaN(v)) {
                  updateDouble(this, v);
                  this.doublesSV = null;
               }
            }

         }
      }
   }

   private void updateDouble(double[] srcItems, int srcOffset, int length) {
      if (this.isEmpty() || Double.isNaN(this.getMinItemInternal())) {
         this.setMinItem(srcItems[srcOffset]);
         this.setMaxItem(srcItems[srcOffset]);
      }

      int count = 0;

      while(count < length) {
         if (this.levelsArr[0] == 0) {
            KllDoublesHelper.compressWhileUpdatingSketch(this);
         }

         int spaceNeeded = length - count;
         int freeSpace = this.levelsArr[0];

         assert freeSpace > 0;

         int numItemsToCopy = Math.min(spaceNeeded, freeSpace);
         int dstOffset = freeSpace - numItemsToCopy;
         int localSrcOffset = srcOffset + count;
         this.setDoubleItemsArrayAt(dstOffset, srcItems, localSrcOffset, numItemsToCopy);
         this.updateMinMax(srcItems, localSrcOffset, numItemsToCopy);
         count += numItemsToCopy;
         this.incN(numItemsToCopy);
         this.setLevelsArrayAt(0, dstOffset);
      }

      this.setLevelZeroSorted(false);
   }

   private void updateMinMax(double[] srcItems, int srcOffset, int length) {
      int end = srcOffset + length;

      for(int i = srcOffset; i < end; ++i) {
         this.setMinItem(Math.min(this.getMinItemInternal(), srcItems[i]));
         this.setMaxItem(Math.max(this.getMaxItemInternal(), srcItems[i]));
      }

   }

   private static boolean hasNaN(double[] items, int offset, int length) {
      int end = offset + length;

      for(int i = offset; i < end; ++i) {
         if (Double.isNaN(items[i])) {
            return true;
         }
      }

      return false;
   }

   abstract double[] getDoubleItemsArray();

   abstract double[] getDoubleRetainedItemsArray();

   abstract double getDoubleSingleItem();

   abstract double getMaxItemInternal();

   abstract void setMaxItem(double var1);

   abstract double getMinItemInternal();

   abstract void setMinItem(double var1);

   abstract byte[] getMinMaxByteArr();

   int getMinMaxSizeBytes() {
      return 16;
   }

   abstract byte[] getRetainedItemsByteArr();

   int getRetainedItemsSizeBytes() {
      return this.getNumRetained() * 8;
   }

   ArrayOfItemsSerDe getSerDe() {
      return null;
   }

   final byte[] getSingleItemByteArr() {
      byte[] bytes = new byte[8];
      ByteArrayUtil.putDoubleLE(bytes, 0, this.getDoubleSingleItem());
      return bytes;
   }

   int getSingleItemSizeBytes() {
      return 8;
   }

   abstract byte[] getTotalItemsByteArr();

   int getTotalItemsNumBytes() {
      return this.levelsArr[this.getNumLevels()] * 8;
   }

   abstract void setDoubleItemsArray(double[] var1);

   abstract void setDoubleItemsArrayAt(int var1, double var2);

   abstract void setDoubleItemsArrayAt(int var1, double[] var2, int var3, int var4);

   @SuppressFBWarnings(
      value = {"EI_EXPOSE_REP"},
      justification = "OK in this case."
   )
   public DoublesSketchSortedView getSortedView() {
      this.refreshSortedView();
      return this.doublesSV;
   }

   private final DoublesSketchSortedView refreshSortedView() {
      if (this.doublesSV == null) {
         CreateSortedView csv = new CreateSortedView();
         this.doublesSV = csv.getSV();
      }

      return this.doublesSV;
   }

   private static void blockyTandemMergeSort(double[] quantiles, long[] weights, int[] levels, int numLevels) {
      if (numLevels != 1) {
         double[] quantilesTmp = Arrays.copyOf(quantiles, quantiles.length);
         long[] weightsTmp = Arrays.copyOf(weights, quantiles.length);
         blockyTandemMergeSortRecursion(quantilesTmp, weightsTmp, quantiles, weights, levels, 0, numLevels);
      }
   }

   private static void blockyTandemMergeSortRecursion(double[] quantilesSrc, long[] weightsSrc, double[] quantilesDst, long[] weightsDst, int[] levels, int startingLevel, int numLevels) {
      if (numLevels != 1) {
         int numLevels1 = numLevels / 2;
         int numLevels2 = numLevels - numLevels1;

         assert numLevels1 >= 1;

         assert numLevels2 >= numLevels1;

         int startingLevel2 = startingLevel + numLevels1;
         blockyTandemMergeSortRecursion(quantilesDst, weightsDst, quantilesSrc, weightsSrc, levels, startingLevel, numLevels1);
         blockyTandemMergeSortRecursion(quantilesDst, weightsDst, quantilesSrc, weightsSrc, levels, startingLevel2, numLevels2);
         tandemMerge(quantilesSrc, weightsSrc, quantilesDst, weightsDst, levels, startingLevel, numLevels1, startingLevel2, numLevels2);
      }
   }

   private static void tandemMerge(double[] quantilesSrc, long[] weightsSrc, double[] quantilesDst, long[] weightsDst, int[] levelStarts, int startingLevel1, int numLevels1, int startingLevel2, int numLevels2) {
      int fromIndex1 = levelStarts[startingLevel1];
      int toIndex1 = levelStarts[startingLevel1 + numLevels1];
      int fromIndex2 = levelStarts[startingLevel2];
      int toIndex2 = levelStarts[startingLevel2 + numLevels2];
      int iSrc1 = fromIndex1;
      int iSrc2 = fromIndex2;

      int iDst;
      for(iDst = fromIndex1; iSrc1 < toIndex1 && iSrc2 < toIndex2; ++iDst) {
         if (quantilesSrc[iSrc1] < quantilesSrc[iSrc2]) {
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
      double[] quantiles;
      long[] cumWeights;

      private CreateSortedView() {
      }

      DoublesSketchSortedView getSV() {
         if (KllDoublesSketch.this.isEmpty()) {
            throw new SketchesArgumentException("The sketch must not be empty for this operation. ");
         } else {
            double[] srcQuantiles = KllDoublesSketch.this.getDoubleItemsArray();
            int[] srcLevels = KllDoublesSketch.this.levelsArr;
            int srcNumLevels = KllDoublesSketch.this.getNumLevels();
            if (!KllDoublesSketch.this.isLevelZeroSorted()) {
               Arrays.sort(srcQuantiles, srcLevels[0], srcLevels[1]);
               if (!KllDoublesSketch.this.hasMemory()) {
                  KllDoublesSketch.this.setLevelZeroSorted(true);
               }
            }

            int numQuantiles = KllDoublesSketch.this.getNumRetained();
            this.quantiles = new double[numQuantiles];
            this.cumWeights = new long[numQuantiles];
            this.populateFromSketch(srcQuantiles, srcLevels, srcNumLevels, numQuantiles);
            return new DoublesSketchSortedView(this.quantiles, this.cumWeights, KllDoublesSketch.this);
         }
      }

      private void populateFromSketch(double[] srcQuantiles, int[] srcLevels, int srcNumLevels, int numItems) {
         int[] myLevels = new int[srcNumLevels + 1];
         int offset = srcLevels[0];
         System.arraycopy(srcQuantiles, offset, this.quantiles, 0, numItems);
         int srcLevel = 0;
         int dstLevel = 0;

         for(long weight = 1L; srcLevel < srcNumLevels; weight *= 2L) {
            int fromIndex = srcLevels[srcLevel] - offset;
            int toIndex = srcLevels[srcLevel + 1] - offset;
            if (fromIndex < toIndex) {
               Arrays.fill(this.cumWeights, fromIndex, toIndex, weight);
               myLevels[dstLevel] = fromIndex;
               myLevels[dstLevel + 1] = toIndex;
               ++dstLevel;
            }

            ++srcLevel;
         }

         KllDoublesSketch.blockyTandemMergeSort(this.quantiles, this.cumWeights, myLevels, dstLevel);
         KllHelper.convertToCumulative(this.cumWeights);
      }
   }
}
