package org.apache.datasketches.quantiles;

import java.util.Arrays;
import java.util.Random;
import org.apache.datasketches.common.SketchesArgumentException;
import org.apache.datasketches.common.SketchesStateException;
import org.apache.datasketches.common.Util;
import org.apache.datasketches.memory.Memory;
import org.apache.datasketches.memory.WritableMemory;
import org.apache.datasketches.quantilescommon.DoublesSketchSortedView;
import org.apache.datasketches.quantilescommon.QuantileSearchCriteria;
import org.apache.datasketches.quantilescommon.QuantilesDoublesAPI;
import org.apache.datasketches.quantilescommon.QuantilesDoublesSketchIterator;

public abstract class DoublesSketch implements QuantilesDoublesAPI {
   static Random rand = new Random();
   final int k_;
   DoublesSketchSortedView doublesSV = null;

   DoublesSketch(int k) {
      ClassicUtil.checkK(k);
      this.k_ = k;
   }

   static synchronized void setRandom(long seed) {
      rand = new Random(seed);
   }

   public static final DoublesSketchBuilder builder() {
      return new DoublesSketchBuilder();
   }

   public static DoublesSketch heapify(Memory srcMem) {
      return (DoublesSketch)(ClassicUtil.checkIsCompactMemory(srcMem) ? CompactDoublesSketch.heapify(srcMem) : UpdateDoublesSketch.heapify(srcMem));
   }

   public static DoublesSketch wrap(Memory srcMem) {
      return (DoublesSketch)(ClassicUtil.checkIsCompactMemory(srcMem) ? DirectCompactDoublesSketch.wrapInstance(srcMem) : DirectUpdateDoublesSketchR.wrapInstance(srcMem));
   }

   public double[] getCDF(double[] splitPoints, QuantileSearchCriteria searchCrit) {
      if (this.isEmpty()) {
         throw new IllegalArgumentException("The sketch must not be empty for this operation. ");
      } else {
         this.refreshSortedView();
         return this.doublesSV.getCDF(splitPoints, searchCrit);
      }
   }

   public abstract double getMaxItem();

   public abstract double getMinItem();

   public double[] getPMF(double[] splitPoints, QuantileSearchCriteria searchCrit) {
      if (this.isEmpty()) {
         throw new IllegalArgumentException("The sketch must not be empty for this operation. ");
      } else {
         this.refreshSortedView();
         return this.doublesSV.getPMF(splitPoints, searchCrit);
      }
   }

   public double getQuantile(double rank, QuantileSearchCriteria searchCrit) {
      if (this.isEmpty()) {
         throw new IllegalArgumentException("The sketch must not be empty for this operation. ");
      } else {
         this.refreshSortedView();
         return this.doublesSV.getQuantile(rank, searchCrit);
      }
   }

   public double[] getQuantiles(double[] ranks, QuantileSearchCriteria searchCrit) {
      if (this.isEmpty()) {
         throw new IllegalArgumentException("The sketch must not be empty for this operation. ");
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
      return this.getQuantile(Math.max((double)0.0F, rank - getNormalizedRankError(this.k_, false)));
   }

   public double getQuantileUpperBound(double rank) {
      return this.getQuantile(Math.min((double)1.0F, rank + getNormalizedRankError(this.k_, false)));
   }

   public double getRank(double quantile, QuantileSearchCriteria searchCrit) {
      if (this.isEmpty()) {
         throw new IllegalArgumentException("The sketch must not be empty for this operation. ");
      } else {
         this.refreshSortedView();
         return this.doublesSV.getRank(quantile, searchCrit);
      }
   }

   public double getRankLowerBound(double rank) {
      return Math.max((double)0.0F, rank - getNormalizedRankError(this.k_, false));
   }

   public double getRankUpperBound(double rank) {
      return Math.min((double)1.0F, rank + getNormalizedRankError(this.k_, false));
   }

   public double[] getRanks(double[] quantiles, QuantileSearchCriteria searchCrit) {
      if (this.isEmpty()) {
         throw new IllegalArgumentException("The sketch must not be empty for this operation. ");
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

   public int getK() {
      return this.k_;
   }

   public abstract long getN();

   public double getNormalizedRankError(boolean pmf) {
      return getNormalizedRankError(this.k_, pmf);
   }

   public static double getNormalizedRankError(int k, boolean pmf) {
      return ClassicUtil.getNormalizedRankError(k, pmf);
   }

   public static int getKFromEpsilon(double epsilon, boolean pmf) {
      return ClassicUtil.getKFromEpsilon(epsilon, pmf);
   }

   public abstract boolean hasMemory();

   public abstract boolean isDirect();

   public boolean isEmpty() {
      return this.getN() == 0L;
   }

   public boolean isEstimationMode() {
      return this.getN() >= 2L * (long)this.k_;
   }

   public abstract boolean isReadOnly();

   public boolean isSameResource(Memory that) {
      return false;
   }

   public byte[] toByteArray() {
      return this.isCompact() ? this.toByteArray(true) : this.toByteArray(false);
   }

   public byte[] toByteArray(boolean compact) {
      return DoublesByteArrayImpl.toByteArray(this, compact, compact);
   }

   public String toString() {
      return this.toString(true, false);
   }

   public String toString(boolean withLevels, boolean withLevelsAndItems) {
      return DoublesUtil.toString(withLevels, withLevelsAndItems, this);
   }

   public static String toString(byte[] byteArr) {
      return PreambleUtil.toString(byteArr, true);
   }

   public static String toString(Memory mem) {
      return PreambleUtil.toString(mem, true);
   }

   public DoublesSketch downSample(DoublesSketch srcSketch, int smallerK, WritableMemory dstMem) {
      return this.downSampleInternal(srcSketch, smallerK, dstMem);
   }

   public int getNumRetained() {
      return ClassicUtil.computeRetainedItems(this.getK(), this.getN());
   }

   public int getCurrentCompactSerializedSizeBytes() {
      return getCompactSerialiedSizeBytes(this.getK(), this.getN());
   }

   public static int getCompactSerialiedSizeBytes(int k, long n) {
      if (n == 0L) {
         return 8;
      } else {
         int metaPreLongs = ClassicUtil.MAX_PRELONGS + 2;
         return metaPreLongs + ClassicUtil.computeRetainedItems(k, n) << 3;
      }
   }

   public int getSerializedSizeBytes() {
      return this.isCompact() ? this.getCurrentCompactSerializedSizeBytes() : this.getCurrentUpdatableSerializedSizeBytes();
   }

   public int getCurrentUpdatableSerializedSizeBytes() {
      return getUpdatableStorageBytes(this.getK(), this.getN());
   }

   public static int getUpdatableStorageBytes(int k, long n) {
      if (n == 0L) {
         return 8;
      } else {
         int metaPre = ClassicUtil.MAX_PRELONGS + 2;
         int totLevels = ClassicUtil.computeNumLevelsNeeded(k, n);
         if (n <= (long)k) {
            int ceil = Math.max(Util.ceilingPowerOf2((int)n), 4);
            return metaPre + ceil << 3;
         } else {
            return metaPre + (2 + totLevels) * k << 3;
         }
      }
   }

   public void putMemory(WritableMemory dstMem) {
      this.putMemory(dstMem, true);
   }

   public void putMemory(WritableMemory dstMem, boolean compact) {
      if (this.hasMemory() && this.isCompact() == compact) {
         Memory srcMem = this.getMemory();
         srcMem.copyTo(0L, dstMem, 0L, (long)this.getSerializedSizeBytes());
      } else {
         byte[] byteArr = this.toByteArray(compact);
         int arrLen = byteArr.length;
         long memCap = dstMem.getCapacity();
         if (memCap < (long)arrLen) {
            throw new SketchesArgumentException("Destination Memory not large enough: " + memCap + " < " + arrLen);
         }

         dstMem.putByteArray(0L, byteArr, 0, arrLen);
      }

   }

   public QuantilesDoublesSketchIterator iterator() {
      return new DoublesSketchIterator(this, this.getBitPattern());
   }

   public abstract void reset();

   UpdateDoublesSketch downSampleInternal(DoublesSketch srcSketch, int smallerK, WritableMemory dstMem) {
      UpdateDoublesSketch newSketch = (UpdateDoublesSketch)(dstMem == null ? HeapUpdateDoublesSketch.newInstance(smallerK) : DirectUpdateDoublesSketch.newInstance(smallerK, dstMem));
      if (srcSketch.isEmpty()) {
         return newSketch;
      } else {
         DoublesMergeImpl.downSamplingMergeInto(srcSketch, newSketch);
         return newSketch;
      }
   }

   abstract boolean isCompact();

   abstract int getBaseBufferCount();

   abstract long getBitPattern();

   abstract int getCombinedBufferItemCapacity();

   abstract double[] getCombinedBuffer();

   abstract WritableMemory getMemory();

   public final DoublesSketchSortedView getSortedView() {
      return this.refreshSortedView();
   }

   private final DoublesSketchSortedView refreshSortedView() {
      return this.doublesSV == null ? (this.doublesSV = this.getSV()) : this.doublesSV;
   }

   private DoublesSketchSortedView getSV() {
      long totalN = this.getN();
      if (!this.isEmpty() && totalN != 0L) {
         int numQuantiles = this.getNumRetained();
         double[] svQuantiles = new double[numQuantiles];
         long[] svCumWeights = new long[numQuantiles];
         DoublesSketchAccessor sketchAccessor = DoublesSketchAccessor.wrap(this);
         populateFromDoublesSketch(this.getK(), totalN, this.getBitPattern(), sketchAccessor, svQuantiles, svCumWeights);
         blockyTandemMergeSort(svQuantiles, svCumWeights, numQuantiles, this.getK());
         if (convertToCumulative(svCumWeights) != totalN) {
            throw new SketchesStateException("Sorted View is misconfigured. TotalN does not match cumWeights.");
         } else {
            return new DoublesSketchSortedView(svQuantiles, svCumWeights, this);
         }
      } else {
         throw new SketchesArgumentException("The sketch must not be empty for this operation. ");
      }
   }

   private static final void populateFromDoublesSketch(int k, long totalN, long bitPattern, DoublesSketchAccessor sketchAccessor, double[] svQuantiles, long[] svCumWeights) {
      // $FF: Couldn't be decompiled
   }

   static void blockyTandemMergeSort(double[] svQuantiles, long[] svCumWts, int arrLen, int blkSize) {
      assert blkSize >= 1;

      if (arrLen > blkSize) {
         int numblks = arrLen / blkSize;
         if (numblks * blkSize < arrLen) {
            ++numblks;
         }

         assert numblks * blkSize >= arrLen;

         double[] qSrc = Arrays.copyOf(svQuantiles, arrLen);
         long[] cwSrc = Arrays.copyOf(svCumWts, arrLen);
         blockyTandemMergeSortRecursion(qSrc, cwSrc, svQuantiles, svCumWts, 0, numblks, blkSize, arrLen);
      }
   }

   private static void blockyTandemMergeSortRecursion(double[] qSrc, long[] cwSrc, double[] qDst, long[] cwDst, int grpStart, int grpLen, int blkSize, int arrLim) {
      assert grpLen > 0;

      if (grpLen != 1) {
         int grpLen1 = grpLen / 2;
         int grpLen2 = grpLen - grpLen1;

         assert grpLen1 >= 1;

         assert grpLen2 >= grpLen1;

         int grpStart2 = grpStart + grpLen1;
         blockyTandemMergeSortRecursion(qDst, cwDst, qSrc, cwSrc, grpStart, grpLen1, blkSize, arrLim);
         blockyTandemMergeSortRecursion(qDst, cwDst, qSrc, cwSrc, grpStart2, grpLen2, blkSize, arrLim);
         int arrStart1 = grpStart * blkSize;
         int arrStart2 = grpStart2 * blkSize;
         int arrLen1 = grpLen1 * blkSize;
         int arrLen2 = grpLen2 * blkSize;
         if (arrStart2 + arrLen2 > arrLim) {
            arrLen2 = arrLim - arrStart2;
         }

         tandemMerge(qSrc, cwSrc, arrStart1, arrLen1, arrStart2, arrLen2, qDst, cwDst, arrStart1);
      }
   }

   private static void tandemMerge(double[] qSrc, long[] cwSrc, int arrStart1, int arrLen1, int arrStart2, int arrLen2, double[] qDst, long[] cwDst, int arrStart3) {
      int arrStop1 = arrStart1 + arrLen1;
      int arrStop2 = arrStart2 + arrLen2;
      int i1 = arrStart1;
      int i2 = arrStart2;

      int i3;
      for(i3 = arrStart3; i1 < arrStop1 && i2 < arrStop2; ++i3) {
         if (qSrc[i2] < qSrc[i1]) {
            qDst[i3] = qSrc[i2];
            cwDst[i3] = cwSrc[i2];
            ++i2;
         } else {
            qDst[i3] = qSrc[i1];
            cwDst[i3] = cwSrc[i1];
            ++i1;
         }
      }

      if (i1 < arrStop1) {
         System.arraycopy(qSrc, i1, qDst, i3, arrStop1 - i1);
         System.arraycopy(cwSrc, i1, cwDst, i3, arrStop1 - i1);
      } else {
         assert i2 < arrStop2;

         System.arraycopy(qSrc, i2, qDst, i3, arrStop2 - i2);
         System.arraycopy(cwSrc, i2, cwDst, i3, arrStop2 - i2);
      }

   }

   private static long convertToCumulative(long[] array) {
      long subtotal = 0L;

      for(int i = 0; i < array.length; ++i) {
         long newSubtotal = subtotal + array[i];
         subtotal = array[i] = newSubtotal;
      }

      return subtotal;
   }
}
