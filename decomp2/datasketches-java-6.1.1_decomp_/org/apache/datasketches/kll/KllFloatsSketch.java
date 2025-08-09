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
import org.apache.datasketches.quantilescommon.FloatsSketchSortedView;
import org.apache.datasketches.quantilescommon.QuantileSearchCriteria;
import org.apache.datasketches.quantilescommon.QuantilesFloatsAPI;
import org.apache.datasketches.quantilescommon.QuantilesFloatsSketchIterator;

public abstract class KllFloatsSketch extends KllSketch implements QuantilesFloatsAPI {
   private FloatsSketchSortedView floatsSV = null;
   static final int ITEM_BYTES = 4;

   KllFloatsSketch(KllSketch.SketchStructure sketchStructure) {
      super(KllSketch.SketchType.FLOATS_SKETCH, sketchStructure);
   }

   public static KllFloatsSketch newHeapInstance() {
      return newHeapInstance(200);
   }

   public static KllFloatsSketch newHeapInstance(int k) {
      return new KllHeapFloatsSketch(k, 8);
   }

   public static KllFloatsSketch newDirectInstance(WritableMemory dstMem, MemoryRequestServer memReqSvr) {
      return newDirectInstance(200, dstMem, memReqSvr);
   }

   public static KllFloatsSketch newDirectInstance(int k, WritableMemory dstMem, MemoryRequestServer memReqSvr) {
      Objects.requireNonNull(dstMem, "Parameter 'dstMem' must not be null");
      Objects.requireNonNull(memReqSvr, "Parameter 'memReqSvr' must not be null");
      return KllDirectFloatsSketch.newDirectUpdatableInstance(k, 8, dstMem, memReqSvr);
   }

   public static KllFloatsSketch heapify(Memory srcMem) {
      Objects.requireNonNull(srcMem, "Parameter 'srcMem' must not be null");
      return KllHeapFloatsSketch.heapifyImpl(srcMem);
   }

   public static KllFloatsSketch wrap(Memory srcMem) {
      Objects.requireNonNull(srcMem, "Parameter 'srcMem' must not be null");
      KllMemoryValidate memVal = new KllMemoryValidate(srcMem, KllSketch.SketchType.FLOATS_SKETCH, (ArrayOfItemsSerDe)null);
      if (memVal.sketchStructure == KllSketch.SketchStructure.UPDATABLE) {
         MemoryRequestServer memReqSvr = new DefaultMemoryRequestServer();
         return new KllDirectFloatsSketch(memVal.sketchStructure, (WritableMemory)srcMem, memReqSvr, memVal);
      } else {
         return new KllDirectFloatsSketch.KllDirectCompactFloatsSketch(memVal.sketchStructure, srcMem, memVal);
      }
   }

   public static KllFloatsSketch writableWrap(WritableMemory srcMem, MemoryRequestServer memReqSvr) {
      Objects.requireNonNull(srcMem, "Parameter 'srcMem' must not be null");
      Objects.requireNonNull(memReqSvr, "Parameter 'memReqSvr' must not be null");
      KllMemoryValidate memVal = new KllMemoryValidate(srcMem, KllSketch.SketchType.FLOATS_SKETCH, (ArrayOfItemsSerDe)null);
      return (KllFloatsSketch)(memVal.sketchStructure == KllSketch.SketchStructure.UPDATABLE ? new KllDirectFloatsSketch(KllSketch.SketchStructure.UPDATABLE, srcMem, memReqSvr, memVal) : new KllDirectFloatsSketch.KllDirectCompactFloatsSketch(memVal.sketchStructure, srcMem, memVal));
   }

   public double[] getCDF(float[] splitPoints, QuantileSearchCriteria searchCrit) {
      if (this.isEmpty()) {
         throw new SketchesArgumentException("The sketch must not be empty for this operation. ");
      } else {
         this.refreshSortedView();
         return this.floatsSV.getCDF(splitPoints, searchCrit);
      }
   }

   public double[] getPMF(float[] splitPoints, QuantileSearchCriteria searchCrit) {
      if (this.isEmpty()) {
         throw new SketchesArgumentException("The sketch must not be empty for this operation. ");
      } else {
         this.refreshSortedView();
         return this.floatsSV.getPMF(splitPoints, searchCrit);
      }
   }

   public float getQuantile(double rank, QuantileSearchCriteria searchCrit) {
      if (this.isEmpty()) {
         throw new SketchesArgumentException("The sketch must not be empty for this operation. ");
      } else {
         this.refreshSortedView();
         return this.floatsSV.getQuantile(rank, searchCrit);
      }
   }

   public float[] getQuantiles(double[] ranks, QuantileSearchCriteria searchCrit) {
      if (this.isEmpty()) {
         throw new SketchesArgumentException("The sketch must not be empty for this operation. ");
      } else {
         this.refreshSortedView();
         int len = ranks.length;
         float[] quantiles = new float[len];

         for(int i = 0; i < len; ++i) {
            quantiles[i] = this.floatsSV.getQuantile(ranks[i], searchCrit);
         }

         return quantiles;
      }
   }

   public float getQuantileLowerBound(double rank) {
      return this.getQuantile(Math.max((double)0.0F, rank - KllHelper.getNormalizedRankError(this.getMinK(), false)));
   }

   public float getQuantileUpperBound(double rank) {
      return this.getQuantile(Math.min((double)1.0F, rank + KllHelper.getNormalizedRankError(this.getMinK(), false)));
   }

   public double getRank(float quantile, QuantileSearchCriteria searchCrit) {
      if (this.isEmpty()) {
         throw new SketchesArgumentException("The sketch must not be empty for this operation. ");
      } else {
         this.refreshSortedView();
         return this.floatsSV.getRank(quantile, searchCrit);
      }
   }

   public double getRankLowerBound(double rank) {
      return Math.max((double)0.0F, rank - KllHelper.getNormalizedRankError(this.getMinK(), false));
   }

   public double getRankUpperBound(double rank) {
      return Math.min((double)1.0F, rank + KllHelper.getNormalizedRankError(this.getMinK(), false));
   }

   public double[] getRanks(float[] quantiles, QuantileSearchCriteria searchCrit) {
      if (this.isEmpty()) {
         throw new SketchesArgumentException("The sketch must not be empty for this operation. ");
      } else {
         this.refreshSortedView();
         int len = quantiles.length;
         double[] ranks = new double[len];

         for(int i = 0; i < len; ++i) {
            ranks[i] = this.floatsSV.getRank(quantiles[i], searchCrit);
         }

         return ranks;
      }
   }

   public QuantilesFloatsSketchIterator iterator() {
      return new KllFloatsSketchIterator(this.getFloatItemsArray(), this.getLevelsArray(KllSketch.SketchStructure.UPDATABLE), this.getNumLevels());
   }

   public final void merge(KllSketch other) {
      if (!this.readOnly && this.sketchStructure == KllSketch.SketchStructure.UPDATABLE) {
         if (this == other) {
            throw new SketchesArgumentException("A sketch cannot merge with itself. ");
         } else {
            KllFloatsSketch othFltSk = (KllFloatsSketch)other;
            if (!othFltSk.isEmpty()) {
               KllFloatsHelper.mergeFloatImpl(this, othFltSk);
               this.floatsSV = null;
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
         this.setMinItem(Float.NaN);
         this.setMaxItem(Float.NaN);
         this.setFloatItemsArray(new float[k]);
         this.floatsSV = null;
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

   public void update(float item) {
      if (!Float.isNaN(item)) {
         if (this.readOnly) {
            throw new SketchesArgumentException("Target sketch is Read Only, cannot write. ");
         } else {
            updateFloat(this, item);
            this.floatsSV = null;
         }
      }
   }

   static void updateFloat(KllFloatsSketch fltSk, float item) {
      fltSk.updateMinMax(item);
      int freeSpace = fltSk.levelsArr[0];

      assert freeSpace >= 0;

      if (freeSpace == 0) {
         KllFloatsHelper.compressWhileUpdatingSketch(fltSk);
         freeSpace = fltSk.levelsArr[0];

         assert freeSpace > 0;
      }

      fltSk.incN(1);
      fltSk.setLevelZeroSorted(false);
      int nextPos = freeSpace - 1;
      fltSk.setLevelsArrayAt(0, nextPos);
      fltSk.setFloatItemsArrayAt(nextPos, item);
   }

   final void updateMinMax(float item) {
      if (!this.isEmpty() && !Float.isNaN(this.getMinItemInternal())) {
         this.setMinItem(Math.min(this.getMinItemInternal(), item));
         this.setMaxItem(Math.max(this.getMaxItemInternal(), item));
      } else {
         this.setMinItem(item);
         this.setMaxItem(item);
      }

   }

   public void update(float item, long weight) {
      if (!Float.isNaN(item)) {
         if (this.readOnly) {
            throw new SketchesArgumentException("Target sketch is Read Only, cannot write. ");
         } else if (weight < 1L) {
            throw new SketchesArgumentException("Weight is less than one.");
         } else {
            if (weight == 1L) {
               updateFloat(this, item);
            } else if (weight < (long)this.levelsArr[0]) {
               for(int i = 0; i < (int)weight; ++i) {
                  updateFloat(this, item);
               }
            } else {
               KllHeapFloatsSketch tmpSk = new KllHeapFloatsSketch(this.getK(), 8, item, weight);
               this.merge(tmpSk);
            }

            this.floatsSV = null;
         }
      }
   }

   public void update(float[] items, int offset, int length) {
      if (this.readOnly) {
         throw new SketchesArgumentException("Target sketch is Read Only, cannot write. ");
      } else if (length != 0) {
         if (!hasNaN(items, offset, length)) {
            this.updateFloat(items, offset, length);
            this.floatsSV = null;
         } else {
            int end = offset + length;

            for(int i = offset; i < end; ++i) {
               float v = items[i];
               if (!Float.isNaN(v)) {
                  updateFloat(this, v);
                  this.floatsSV = null;
               }
            }

         }
      }
   }

   private void updateFloat(float[] srcItems, int srcOffset, int length) {
      if (this.isEmpty() || Float.isNaN(this.getMinItemInternal())) {
         this.setMinItem(srcItems[srcOffset]);
         this.setMaxItem(srcItems[srcOffset]);
      }

      int count = 0;

      while(count < length) {
         if (this.levelsArr[0] == 0) {
            KllFloatsHelper.compressWhileUpdatingSketch(this);
         }

         int spaceNeeded = length - count;
         int freeSpace = this.levelsArr[0];

         assert freeSpace > 0;

         int numItemsToCopy = Math.min(spaceNeeded, freeSpace);
         int dstOffset = freeSpace - numItemsToCopy;
         int localSrcOffset = srcOffset + count;
         this.setFloatItemsArrayAt(dstOffset, srcItems, localSrcOffset, numItemsToCopy);
         this.updateMinMax(srcItems, localSrcOffset, numItemsToCopy);
         count += numItemsToCopy;
         this.incN(numItemsToCopy);
         this.setLevelsArrayAt(0, dstOffset);
      }

      this.setLevelZeroSorted(false);
   }

   private void updateMinMax(float[] srcItems, int srcOffset, int length) {
      int end = srcOffset + length;

      for(int i = srcOffset; i < end; ++i) {
         this.setMinItem(Math.min(this.getMinItemInternal(), srcItems[i]));
         this.setMaxItem(Math.max(this.getMaxItemInternal(), srcItems[i]));
      }

   }

   private static boolean hasNaN(float[] items, int offset, int length) {
      int end = offset + length;

      for(int i = offset; i < end; ++i) {
         if (Float.isNaN(items[i])) {
            return true;
         }
      }

      return false;
   }

   abstract float[] getFloatItemsArray();

   abstract float[] getFloatRetainedItemsArray();

   abstract float getFloatSingleItem();

   abstract float getMaxItemInternal();

   abstract void setMaxItem(float var1);

   abstract float getMinItemInternal();

   abstract void setMinItem(float var1);

   abstract byte[] getMinMaxByteArr();

   int getMinMaxSizeBytes() {
      return 8;
   }

   abstract byte[] getRetainedItemsByteArr();

   int getRetainedItemsSizeBytes() {
      return this.getNumRetained() * 4;
   }

   ArrayOfItemsSerDe getSerDe() {
      return null;
   }

   final byte[] getSingleItemByteArr() {
      byte[] bytes = new byte[4];
      ByteArrayUtil.putFloatLE(bytes, 0, this.getFloatSingleItem());
      return bytes;
   }

   int getSingleItemSizeBytes() {
      return 4;
   }

   abstract byte[] getTotalItemsByteArr();

   int getTotalItemsNumBytes() {
      return this.levelsArr[this.getNumLevels()] * 4;
   }

   abstract void setFloatItemsArray(float[] var1);

   abstract void setFloatItemsArrayAt(int var1, float var2);

   abstract void setFloatItemsArrayAt(int var1, float[] var2, int var3, int var4);

   @SuppressFBWarnings(
      value = {"EI_EXPOSE_REP"},
      justification = "OK in this case."
   )
   public FloatsSketchSortedView getSortedView() {
      this.refreshSortedView();
      return this.floatsSV;
   }

   private final FloatsSketchSortedView refreshSortedView() {
      if (this.floatsSV == null) {
         CreateSortedView csv = new CreateSortedView();
         this.floatsSV = csv.getSV();
      }

      return this.floatsSV;
   }

   private static void blockyTandemMergeSort(float[] quantiles, long[] weights, int[] levels, int numLevels) {
      if (numLevels != 1) {
         float[] quantilesTmp = Arrays.copyOf(quantiles, quantiles.length);
         long[] weightsTmp = Arrays.copyOf(weights, quantiles.length);
         blockyTandemMergeSortRecursion(quantilesTmp, weightsTmp, quantiles, weights, levels, 0, numLevels);
      }
   }

   private static void blockyTandemMergeSortRecursion(float[] quantilesSrc, long[] weightsSrc, float[] quantilesDst, long[] weightsDst, int[] levels, int startingLevel, int numLevels) {
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

   private static void tandemMerge(float[] quantilesSrc, long[] weightsSrc, float[] quantilesDst, long[] weightsDst, int[] levelStarts, int startingLevel1, int numLevels1, int startingLevel2, int numLevels2) {
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
      float[] quantiles;
      long[] cumWeights;

      private CreateSortedView() {
      }

      FloatsSketchSortedView getSV() {
         if (KllFloatsSketch.this.isEmpty()) {
            throw new SketchesArgumentException("The sketch must not be empty for this operation. ");
         } else {
            float[] srcQuantiles = KllFloatsSketch.this.getFloatItemsArray();
            int[] srcLevels = KllFloatsSketch.this.levelsArr;
            int srcNumLevels = KllFloatsSketch.this.getNumLevels();
            if (!KllFloatsSketch.this.isLevelZeroSorted()) {
               Arrays.sort(srcQuantiles, srcLevels[0], srcLevels[1]);
               if (!KllFloatsSketch.this.hasMemory()) {
                  KllFloatsSketch.this.setLevelZeroSorted(true);
               }
            }

            int numQuantiles = KllFloatsSketch.this.getNumRetained();
            this.quantiles = new float[numQuantiles];
            this.cumWeights = new long[numQuantiles];
            this.populateFromSketch(srcQuantiles, srcLevels, srcNumLevels, numQuantiles);
            return new FloatsSketchSortedView(this.quantiles, this.cumWeights, KllFloatsSketch.this);
         }
      }

      private void populateFromSketch(float[] srcQuantiles, int[] srcLevels, int srcNumLevels, int numItems) {
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

         KllFloatsSketch.blockyTandemMergeSort(this.quantiles, this.cumWeights, myLevels, dstLevel);
         KllHelper.convertToCumulative(this.cumWeights);
      }
   }
}
