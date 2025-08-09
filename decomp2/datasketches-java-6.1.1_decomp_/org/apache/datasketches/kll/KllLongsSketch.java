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
import org.apache.datasketches.quantilescommon.LongsSketchSortedView;
import org.apache.datasketches.quantilescommon.QuantileSearchCriteria;
import org.apache.datasketches.quantilescommon.QuantilesLongsAPI;
import org.apache.datasketches.quantilescommon.QuantilesLongsSketchIterator;

public abstract class KllLongsSketch extends KllSketch implements QuantilesLongsAPI {
   private LongsSketchSortedView longsSV = null;
   static final int ITEM_BYTES = 8;

   KllLongsSketch(KllSketch.SketchStructure sketchStructure) {
      super(KllSketch.SketchType.LONGS_SKETCH, sketchStructure);
   }

   public static KllLongsSketch newHeapInstance() {
      return newHeapInstance(200);
   }

   public static KllLongsSketch newHeapInstance(int k) {
      return new KllHeapLongsSketch(k, 8);
   }

   public static KllLongsSketch newDirectInstance(WritableMemory dstMem, MemoryRequestServer memReqSvr) {
      return newDirectInstance(200, dstMem, memReqSvr);
   }

   public static KllLongsSketch newDirectInstance(int k, WritableMemory dstMem, MemoryRequestServer memReqSvr) {
      Objects.requireNonNull(dstMem, "Parameter 'dstMem' must not be null");
      Objects.requireNonNull(memReqSvr, "Parameter 'memReqSvr' must not be null");
      return KllDirectLongsSketch.newDirectUpdatableInstance(k, 8, dstMem, memReqSvr);
   }

   public static KllLongsSketch heapify(Memory srcMem) {
      Objects.requireNonNull(srcMem, "Parameter 'srcMem' must not be null");
      return KllHeapLongsSketch.heapifyImpl(srcMem);
   }

   public static KllLongsSketch wrap(Memory srcMem) {
      Objects.requireNonNull(srcMem, "Parameter 'srcMem' must not be null");
      KllMemoryValidate memVal = new KllMemoryValidate(srcMem, KllSketch.SketchType.LONGS_SKETCH, (ArrayOfItemsSerDe)null);
      if (memVal.sketchStructure == KllSketch.SketchStructure.UPDATABLE) {
         MemoryRequestServer memReqSvr = new DefaultMemoryRequestServer();
         return new KllDirectLongsSketch(memVal.sketchStructure, (WritableMemory)srcMem, memReqSvr, memVal);
      } else {
         return new KllDirectLongsSketch.KllDirectCompactLongsSketch(memVal.sketchStructure, srcMem, memVal);
      }
   }

   public static KllLongsSketch writableWrap(WritableMemory srcMem, MemoryRequestServer memReqSvr) {
      Objects.requireNonNull(srcMem, "Parameter 'srcMem' must not be null");
      Objects.requireNonNull(memReqSvr, "Parameter 'memReqSvr' must not be null");
      KllMemoryValidate memVal = new KllMemoryValidate(srcMem, KllSketch.SketchType.LONGS_SKETCH);
      return (KllLongsSketch)(memVal.sketchStructure == KllSketch.SketchStructure.UPDATABLE ? new KllDirectLongsSketch(KllSketch.SketchStructure.UPDATABLE, srcMem, memReqSvr, memVal) : new KllDirectLongsSketch.KllDirectCompactLongsSketch(memVal.sketchStructure, srcMem, memVal));
   }

   public double[] getCDF(long[] splitPoints, QuantileSearchCriteria searchCrit) {
      if (this.isEmpty()) {
         throw new SketchesArgumentException("The sketch must not be empty for this operation. ");
      } else {
         this.refreshSortedView();
         return this.longsSV.getCDF(splitPoints, searchCrit);
      }
   }

   public double[] getPMF(long[] splitPoints, QuantileSearchCriteria searchCrit) {
      if (this.isEmpty()) {
         throw new SketchesArgumentException("The sketch must not be empty for this operation. ");
      } else {
         this.refreshSortedView();
         return this.longsSV.getPMF(splitPoints, searchCrit);
      }
   }

   public long getQuantile(double rank, QuantileSearchCriteria searchCrit) {
      if (this.isEmpty()) {
         throw new SketchesArgumentException("The sketch must not be empty for this operation. ");
      } else {
         this.refreshSortedView();
         return this.longsSV.getQuantile(rank, searchCrit);
      }
   }

   public long[] getQuantiles(double[] ranks, QuantileSearchCriteria searchCrit) {
      if (this.isEmpty()) {
         throw new SketchesArgumentException("The sketch must not be empty for this operation. ");
      } else {
         this.refreshSortedView();
         int len = ranks.length;
         long[] quantiles = new long[len];

         for(int i = 0; i < len; ++i) {
            quantiles[i] = this.longsSV.getQuantile(ranks[i], searchCrit);
         }

         return quantiles;
      }
   }

   public long getQuantileLowerBound(double rank) {
      return this.getQuantile(Math.max((double)0.0F, rank - KllHelper.getNormalizedRankError(this.getMinK(), false)));
   }

   public long getQuantileUpperBound(double rank) {
      return this.getQuantile(Math.min((double)1.0F, rank + KllHelper.getNormalizedRankError(this.getMinK(), false)));
   }

   public double getRank(long quantile, QuantileSearchCriteria searchCrit) {
      if (this.isEmpty()) {
         throw new SketchesArgumentException("The sketch must not be empty for this operation. ");
      } else {
         this.refreshSortedView();
         return this.longsSV.getRank(quantile, searchCrit);
      }
   }

   public double getRankLowerBound(double rank) {
      return Math.max((double)0.0F, rank - KllHelper.getNormalizedRankError(this.getMinK(), false));
   }

   public double getRankUpperBound(double rank) {
      return Math.min((double)1.0F, rank + KllHelper.getNormalizedRankError(this.getMinK(), false));
   }

   public double[] getRanks(long[] quantiles, QuantileSearchCriteria searchCrit) {
      if (this.isEmpty()) {
         throw new SketchesArgumentException("The sketch must not be empty for this operation. ");
      } else {
         this.refreshSortedView();
         int len = quantiles.length;
         double[] ranks = new double[len];

         for(int i = 0; i < len; ++i) {
            ranks[i] = this.longsSV.getRank(quantiles[i], searchCrit);
         }

         return ranks;
      }
   }

   public QuantilesLongsSketchIterator iterator() {
      return new KllLongsSketchIterator(this.getLongItemsArray(), this.getLevelsArray(KllSketch.SketchStructure.UPDATABLE), this.getNumLevels());
   }

   public final void merge(KllSketch other) {
      if (!this.readOnly && this.sketchStructure == KllSketch.SketchStructure.UPDATABLE) {
         if (this == other) {
            throw new SketchesArgumentException("A sketch cannot merge with itself. ");
         } else {
            KllLongsSketch otherLngSk = (KllLongsSketch)other;
            if (!otherLngSk.isEmpty()) {
               KllLongsHelper.mergeLongsImpl(this, otherLngSk);
               this.longsSV = null;
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
         this.setMinItem(Long.MAX_VALUE);
         this.setMaxItem(Long.MIN_VALUE);
         this.setLongItemsArray(new long[k]);
         this.longsSV = null;
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

   public void update(long item) {
      if (this.readOnly) {
         throw new SketchesArgumentException("Target sketch is Read Only, cannot write. ");
      } else {
         updateLong(this, item);
         this.longsSV = null;
      }
   }

   static void updateLong(KllLongsSketch lngSk, long item) {
      lngSk.updateMinMax(item);
      int freeSpace = lngSk.levelsArr[0];

      assert freeSpace >= 0;

      if (freeSpace == 0) {
         KllLongsHelper.compressWhileUpdatingSketch(lngSk);
         freeSpace = lngSk.levelsArr[0];

         assert freeSpace > 0;
      }

      lngSk.incN(1);
      lngSk.setLevelZeroSorted(false);
      int nextPos = freeSpace - 1;
      lngSk.setLevelsArrayAt(0, nextPos);
      lngSk.setLongItemsArrayAt(nextPos, item);
   }

   final void updateMinMax(long item) {
      if (this.isEmpty()) {
         this.setMinItem(item);
         this.setMaxItem(item);
      } else {
         this.setMinItem(Math.min(this.getMinItemInternal(), item));
         this.setMaxItem(Math.max(this.getMaxItemInternal(), item));
      }

   }

   public void update(long item, long weight) {
      if (this.readOnly) {
         throw new SketchesArgumentException("Target sketch is Read Only, cannot write. ");
      } else if (weight < 1L) {
         throw new SketchesArgumentException("Weight is less than one.");
      } else {
         if (weight == 1L) {
            updateLong(this, item);
         } else if (weight < (long)this.levelsArr[0]) {
            for(int i = 0; i < (int)weight; ++i) {
               updateLong(this, item);
            }
         } else {
            KllHeapLongsSketch tmpSk = new KllHeapLongsSketch(this.getK(), 8, item, weight);
            this.merge(tmpSk);
         }

         this.longsSV = null;
      }
   }

   public void update(long[] items, int offset, int length) {
      if (this.readOnly) {
         throw new SketchesArgumentException("Target sketch is Read Only, cannot write. ");
      } else if (length != 0) {
         this.updateLong(items, offset, length);
         this.longsSV = null;
      }
   }

   private void updateLong(long[] srcItems, int srcOffset, int length) {
      if (this.isEmpty()) {
         this.setMinItem(srcItems[srcOffset]);
         this.setMaxItem(srcItems[srcOffset]);
      }

      int count = 0;

      while(count < length) {
         if (this.levelsArr[0] == 0) {
            KllLongsHelper.compressWhileUpdatingSketch(this);
         }

         int spaceNeeded = length - count;
         int freeSpace = this.levelsArr[0];

         assert freeSpace > 0;

         int numItemsToCopy = Math.min(spaceNeeded, freeSpace);
         int dstOffset = freeSpace - numItemsToCopy;
         int localSrcOffset = srcOffset + count;
         this.setLongItemsArrayAt(dstOffset, srcItems, localSrcOffset, numItemsToCopy);
         this.updateMinMax(srcItems, localSrcOffset, numItemsToCopy);
         count += numItemsToCopy;
         this.incN(numItemsToCopy);
         this.setLevelsArrayAt(0, dstOffset);
      }

      this.setLevelZeroSorted(false);
   }

   private void updateMinMax(long[] srcItems, int srcOffset, int length) {
      int end = srcOffset + length;

      for(int i = srcOffset; i < end; ++i) {
         this.setMinItem(Math.min(this.getMinItemInternal(), srcItems[i]));
         this.setMaxItem(Math.max(this.getMaxItemInternal(), srcItems[i]));
      }

   }

   abstract long[] getLongItemsArray();

   abstract long[] getLongRetainedItemsArray();

   abstract long getLongSingleItem();

   abstract long getMaxItemInternal();

   abstract void setMaxItem(long var1);

   abstract long getMinItemInternal();

   abstract void setMinItem(long var1);

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
      ByteArrayUtil.putLongLE(bytes, 0, this.getLongSingleItem());
      return bytes;
   }

   int getSingleItemSizeBytes() {
      return 8;
   }

   abstract byte[] getTotalItemsByteArr();

   int getTotalItemsNumBytes() {
      return this.levelsArr[this.getNumLevels()] * 8;
   }

   abstract void setLongItemsArray(long[] var1);

   abstract void setLongItemsArrayAt(int var1, long var2);

   abstract void setLongItemsArrayAt(int var1, long[] var2, int var3, int var4);

   @SuppressFBWarnings(
      value = {"EI_EXPOSE_REP"},
      justification = "OK in this case."
   )
   public LongsSketchSortedView getSortedView() {
      this.refreshSortedView();
      return this.longsSV;
   }

   private final LongsSketchSortedView refreshSortedView() {
      if (this.longsSV == null) {
         CreateSortedView csv = new CreateSortedView();
         this.longsSV = csv.getSV();
      }

      return this.longsSV;
   }

   private static void blockyTandemMergeSort(long[] quantiles, long[] weights, int[] levels, int numLevels) {
      if (numLevels != 1) {
         long[] quantilesTmp = Arrays.copyOf(quantiles, quantiles.length);
         long[] weightsTmp = Arrays.copyOf(weights, quantiles.length);
         blockyTandemMergeSortRecursion(quantilesTmp, weightsTmp, quantiles, weights, levels, 0, numLevels);
      }
   }

   private static void blockyTandemMergeSortRecursion(long[] quantilesSrc, long[] weightsSrc, long[] quantilesDst, long[] weightsDst, int[] levels, int startingLevel, int numLevels) {
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

   private static void tandemMerge(long[] quantilesSrc, long[] weightsSrc, long[] quantilesDst, long[] weightsDst, int[] levelStarts, int startingLevel1, int numLevels1, int startingLevel2, int numLevels2) {
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
      long[] quantiles;
      long[] cumWeights;

      private CreateSortedView() {
      }

      LongsSketchSortedView getSV() {
         if (KllLongsSketch.this.isEmpty()) {
            throw new SketchesArgumentException("The sketch must not be empty for this operation. ");
         } else {
            long[] srcQuantiles = KllLongsSketch.this.getLongItemsArray();
            int[] srcLevels = KllLongsSketch.this.levelsArr;
            int srcNumLevels = KllLongsSketch.this.getNumLevels();
            if (!KllLongsSketch.this.isLevelZeroSorted()) {
               Arrays.sort(srcQuantiles, srcLevels[0], srcLevels[1]);
               if (!KllLongsSketch.this.hasMemory()) {
                  KllLongsSketch.this.setLevelZeroSorted(true);
               }
            }

            int numQuantiles = KllLongsSketch.this.getNumRetained();
            this.quantiles = new long[numQuantiles];
            this.cumWeights = new long[numQuantiles];
            this.populateFromSketch(srcQuantiles, srcLevels, srcNumLevels, numQuantiles);
            return new LongsSketchSortedView(this.quantiles, this.cumWeights, KllLongsSketch.this);
         }
      }

      private void populateFromSketch(long[] srcQuantiles, int[] srcLevels, int srcNumLevels, int numItems) {
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

         KllLongsSketch.blockyTandemMergeSort(this.quantiles, this.cumWeights, myLevels, dstLevel);
         KllHelper.convertToCumulative(this.cumWeights);
      }
   }
}
