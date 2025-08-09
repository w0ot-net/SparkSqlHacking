package org.apache.datasketches.quantiles;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Objects;
import java.util.Random;
import org.apache.datasketches.common.ArrayOfItemsSerDe;
import org.apache.datasketches.common.SketchesArgumentException;
import org.apache.datasketches.common.SketchesStateException;
import org.apache.datasketches.memory.Memory;
import org.apache.datasketches.memory.WritableMemory;
import org.apache.datasketches.quantilescommon.GenericPartitionBoundaries;
import org.apache.datasketches.quantilescommon.ItemsSketchSortedView;
import org.apache.datasketches.quantilescommon.QuantileSearchCriteria;
import org.apache.datasketches.quantilescommon.QuantilesGenericAPI;
import org.apache.datasketches.quantilescommon.QuantilesGenericSketchIterator;

public final class ItemsSketch implements QuantilesGenericAPI {
   final Class clazz;
   private final Comparator comparator_;
   final int k_;
   long n_;
   Object maxItem_;
   Object minItem_;
   int combinedBufferItemCapacity_;
   int baseBufferCount_;
   long bitPattern_;
   Object[] combinedBuffer_;
   ItemsSketchSortedView classicQisSV = null;
   public static final Random rand = new Random();

   private ItemsSketch(int k, Class clazz, Comparator comparator) {
      Objects.requireNonNull(clazz, "Class<T> must not be null.");
      Objects.requireNonNull(comparator, "Comparator must not be null.");
      ClassicUtil.checkK(k);
      this.k_ = k;
      this.clazz = clazz;
      this.comparator_ = comparator;
   }

   public static ItemsSketch getInstance(Class clazz, Comparator comparator) {
      return getInstance(clazz, 128, comparator);
   }

   public static ItemsSketch getInstance(Class clazz, int k, Comparator comparator) {
      ItemsSketch<T> qs = new ItemsSketch(k, clazz, comparator);
      int bufAlloc = 2 * Math.min(2, k);
      qs.n_ = 0L;
      qs.combinedBufferItemCapacity_ = bufAlloc;
      qs.combinedBuffer_ = new Object[bufAlloc];
      qs.baseBufferCount_ = 0;
      qs.bitPattern_ = 0L;
      qs.minItem_ = null;
      qs.maxItem_ = null;
      return qs;
   }

   public static ItemsSketch getInstance(Class clazz, Memory srcMem, Comparator comparator, ArrayOfItemsSerDe serDe) {
      long memCapBytes = srcMem.getCapacity();
      if (memCapBytes < 8L) {
         throw new SketchesArgumentException("Memory too small: " + memCapBytes);
      } else {
         int preambleLongs = PreambleUtil.extractPreLongs(srcMem);
         int serVer = PreambleUtil.extractSerVer(srcMem);
         int familyID = PreambleUtil.extractFamilyID(srcMem);
         int flags = PreambleUtil.extractFlags(srcMem);
         int k = PreambleUtil.extractK(srcMem);
         ItemsUtil.checkItemsSerVer(serVer);
         if (serVer == 3 && (flags & 8) == 0) {
            throw new SketchesArgumentException("Non-compact Memory images are not supported.");
         } else {
            boolean empty = ClassicUtil.checkPreLongsFlagsCap(preambleLongs, flags, memCapBytes);
            ClassicUtil.checkFamilyID(familyID);
            ItemsSketch<T> sk = getInstance(clazz, k, comparator);
            if (empty) {
               return sk;
            } else {
               long n = PreambleUtil.extractN(srcMem);
               int extra = 2;
               int numMemItems = ClassicUtil.computeRetainedItems(k, n) + 2;
               sk.n_ = n;
               sk.combinedBufferItemCapacity_ = ClassicUtil.computeCombinedBufferItemCapacity(k, n);
               sk.baseBufferCount_ = ClassicUtil.computeBaseBufferItems(k, n);
               sk.bitPattern_ = ClassicUtil.computeBitPattern(k, n);
               sk.combinedBuffer_ = new Object[sk.combinedBufferItemCapacity_];
               int srcMemItemsOffsetBytes = preambleLongs * 8;
               Memory mReg = srcMem.region((long)srcMemItemsOffsetBytes, srcMem.getCapacity() - (long)srcMemItemsOffsetBytes);
               T[] itemsArray = (T[])serDe.deserializeFromMemory(mReg, 0L, numMemItems);
               sk.itemsArrayToCombinedBuffer(itemsArray);
               return sk;
            }
         }
      }
   }

   static ItemsSketch copy(ItemsSketch sketch) {
      ItemsSketch<T> qsCopy = getInstance(sketch.clazz, sketch.k_, sketch.comparator_);
      qsCopy.n_ = sketch.n_;
      qsCopy.minItem_ = sketch.isEmpty() ? null : sketch.getMinItem();
      qsCopy.maxItem_ = sketch.isEmpty() ? null : sketch.getMaxItem();
      qsCopy.combinedBufferItemCapacity_ = sketch.getCombinedBufferAllocatedCount();
      qsCopy.baseBufferCount_ = sketch.getBaseBufferCount();
      qsCopy.bitPattern_ = sketch.getBitPattern();
      Object[] combBuf = sketch.getCombinedBuffer();
      qsCopy.combinedBuffer_ = Arrays.copyOf(combBuf, combBuf.length);
      return qsCopy;
   }

   public double[] getCDF(Object[] splitPoints, QuantileSearchCriteria searchCrit) {
      if (this.isEmpty()) {
         throw new IllegalArgumentException("The sketch must not be empty for this operation. ");
      } else {
         this.refreshSortedView();
         return this.classicQisSV.getCDF(splitPoints, searchCrit);
      }
   }

   public Class getClassOfT() {
      return this.clazz;
   }

   public Comparator getComparator() {
      return this.comparator_;
   }

   public Object getMaxItem() {
      if (this.isEmpty()) {
         throw new IllegalArgumentException("The sketch must not be empty for this operation. ");
      } else {
         return this.maxItem_;
      }
   }

   public Object getMinItem() {
      if (this.isEmpty()) {
         throw new IllegalArgumentException("The sketch must not be empty for this operation. ");
      } else {
         return this.minItem_;
      }
   }

   public GenericPartitionBoundaries getPartitionBoundariesFromNumParts(int numEquallySizedParts, QuantileSearchCriteria searchCrit) {
      if (this.isEmpty()) {
         throw new IllegalArgumentException("The sketch must not be empty for this operation. ");
      } else {
         this.refreshSortedView();
         return this.classicQisSV.getPartitionBoundariesFromNumParts(numEquallySizedParts, searchCrit);
      }
   }

   public GenericPartitionBoundaries getPartitionBoundariesFromPartSize(long nominalPartSizeItems, QuantileSearchCriteria searchCrit) {
      if (this.isEmpty()) {
         throw new IllegalArgumentException("The sketch must not be empty for this operation. ");
      } else {
         this.refreshSortedView();
         return this.classicQisSV.getPartitionBoundariesFromPartSize(nominalPartSizeItems, searchCrit);
      }
   }

   public double[] getPMF(Object[] splitPoints, QuantileSearchCriteria searchCrit) {
      if (this.isEmpty()) {
         throw new IllegalArgumentException("The sketch must not be empty for this operation. ");
      } else {
         this.refreshSortedView();
         return this.classicQisSV.getPMF(splitPoints, searchCrit);
      }
   }

   public Object getQuantile(double rank, QuantileSearchCriteria searchCrit) {
      if (this.isEmpty()) {
         throw new IllegalArgumentException("The sketch must not be empty for this operation. ");
      } else {
         this.refreshSortedView();
         return this.classicQisSV.getQuantile(rank, searchCrit);
      }
   }

   public Object getQuantileLowerBound(double rank) {
      if (this.isEmpty()) {
         throw new IllegalArgumentException("The sketch must not be empty for this operation. ");
      } else {
         return this.getQuantile(Math.max((double)0.0F, rank - getNormalizedRankError(this.k_, false)));
      }
   }

   public Object getQuantileUpperBound(double rank) {
      if (this.isEmpty()) {
         throw new IllegalArgumentException("The sketch must not be empty for this operation. ");
      } else {
         return this.getQuantile(Math.min((double)1.0F, rank + getNormalizedRankError(this.k_, false)));
      }
   }

   public Object[] getQuantiles(double[] ranks, QuantileSearchCriteria searchCrit) {
      if (this.isEmpty()) {
         throw new IllegalArgumentException("The sketch must not be empty for this operation. ");
      } else {
         this.refreshSortedView();
         return this.classicQisSV.getQuantiles(ranks, searchCrit);
      }
   }

   public double getRank(Object quantile, QuantileSearchCriteria searchCrit) {
      if (this.isEmpty()) {
         throw new IllegalArgumentException("The sketch must not be empty for this operation. ");
      } else {
         this.refreshSortedView();
         return this.classicQisSV.getRank(quantile, searchCrit);
      }
   }

   public double getRankLowerBound(double rank) {
      return Math.max((double)0.0F, rank - getNormalizedRankError(this.k_, false));
   }

   public double getRankUpperBound(double rank) {
      return Math.min((double)1.0F, rank + getNormalizedRankError(this.k_, false));
   }

   public double[] getRanks(Object[] quantiles, QuantileSearchCriteria searchCrit) {
      if (this.isEmpty()) {
         throw new IllegalArgumentException("The sketch must not be empty for this operation. ");
      } else {
         this.refreshSortedView();
         int len = quantiles.length;
         double[] ranks = new double[len];

         for(int i = 0; i < len; ++i) {
            ranks[i] = this.classicQisSV.getRank(quantiles[i], searchCrit);
         }

         return ranks;
      }
   }

   public QuantilesGenericSketchIterator iterator() {
      return new ItemsSketchIterator(this, this.bitPattern_);
   }

   public int getK() {
      return this.k_;
   }

   public long getN() {
      return this.n_;
   }

   public double getNormalizedRankError(boolean pmf) {
      return getNormalizedRankError(this.k_, pmf);
   }

   public static double getNormalizedRankError(int k, boolean pmf) {
      return ClassicUtil.getNormalizedRankError(k, pmf);
   }

   public static int getKFromEpsilon(double epsilon, boolean pmf) {
      return ClassicUtil.getKFromEpsilon(epsilon, pmf);
   }

   public boolean hasMemory() {
      return false;
   }

   public boolean isEmpty() {
      return this.getN() == 0L;
   }

   public boolean isDirect() {
      return false;
   }

   public boolean isEstimationMode() {
      return this.getN() >= 2L * (long)this.k_;
   }

   public boolean isReadOnly() {
      return false;
   }

   public void reset() {
      this.n_ = 0L;
      this.combinedBufferItemCapacity_ = 2 * Math.min(2, this.k_);
      this.combinedBuffer_ = new Object[this.combinedBufferItemCapacity_];
      this.baseBufferCount_ = 0;
      this.bitPattern_ = 0L;
      this.minItem_ = null;
      this.maxItem_ = null;
      this.classicQisSV = null;
   }

   public byte[] toByteArray(ArrayOfItemsSerDe serDe) {
      return this.toByteArray(false, serDe);
   }

   public byte[] toByteArray(boolean ordered, ArrayOfItemsSerDe serDe) {
      return ItemsByteArrayImpl.toByteArray(this, ordered, serDe);
   }

   public String toString() {
      return this.toString(false, false);
   }

   public String toString(boolean withLevels, boolean withLevelsAndItems) {
      return ItemsUtil.toString(withLevels, withLevelsAndItems, this);
   }

   public static String toString(byte[] byteArr) {
      return PreambleUtil.toString(byteArr, false);
   }

   public static String toString(Memory mem) {
      return PreambleUtil.toString(mem, false);
   }

   public ItemsSketch downSample(int newK) {
      ItemsSketch<T> newSketch = getInstance(this.clazz, newK, this.comparator_);
      ItemsMergeImpl.downSamplingMergeInto(this, newSketch);
      return newSketch;
   }

   public int getNumRetained() {
      return ClassicUtil.computeRetainedItems(this.getK(), this.getN());
   }

   public void putMemory(WritableMemory dstMem, ArrayOfItemsSerDe serDe) {
      byte[] byteArr = this.toByteArray(serDe);
      long memCap = dstMem.getCapacity();
      if (memCap < (long)byteArr.length) {
         throw new SketchesArgumentException("Destination Memory not large enough: " + memCap + " < " + byteArr.length);
      } else {
         dstMem.putByteArray(0L, byteArr, 0, byteArr.length);
      }
   }

   public void update(Object item) {
      if (item != null) {
         if (this.maxItem_ == null || this.comparator_.compare(item, this.maxItem_) > 0) {
            this.maxItem_ = item;
         }

         if (this.minItem_ == null || this.comparator_.compare(item, this.minItem_) < 0) {
            this.minItem_ = item;
         }

         if (this.baseBufferCount_ + 1 > this.combinedBufferItemCapacity_) {
            growBaseBuffer(this);
         }

         this.combinedBuffer_[this.baseBufferCount_++] = item;
         ++this.n_;
         if (this.baseBufferCount_ == 2 * this.k_) {
            ItemsUtil.processFullBaseBuffer(this);
         }

         this.classicQisSV = null;
      }
   }

   int getBaseBufferCount() {
      return this.baseBufferCount_;
   }

   int getCombinedBufferAllocatedCount() {
      return this.combinedBufferItemCapacity_;
   }

   long getBitPattern() {
      return this.bitPattern_;
   }

   Object[] getCombinedBuffer() {
      return this.combinedBuffer_;
   }

   private void itemsArrayToCombinedBuffer(Object[] itemsArray) {
      int extra = 2;
      this.minItem_ = itemsArray[0];
      this.maxItem_ = itemsArray[1];
      System.arraycopy(itemsArray, 2, this.combinedBuffer_, 0, this.baseBufferCount_);
      long bits = this.bitPattern_;
      if (bits > 0L) {
         int index = 2 + this.baseBufferCount_;

         for(int level = 0; bits != 0L; bits >>>= 1) {
            if ((bits & 1L) > 0L) {
               System.arraycopy(itemsArray, index, this.combinedBuffer_, (2 + level) * this.k_, this.k_);
               index += this.k_;
            }

            ++level;
         }
      }

   }

   private static void growBaseBuffer(ItemsSketch sketch) {
      Object[] baseBuffer = sketch.getCombinedBuffer();
      int oldSize = sketch.getCombinedBufferAllocatedCount();
      int k = sketch.getK();

      assert oldSize < 2 * k;

      int newSize = Math.max(Math.min(2 * k, 2 * oldSize), 1);
      sketch.combinedBufferItemCapacity_ = newSize;
      sketch.combinedBuffer_ = Arrays.copyOf(baseBuffer, newSize);
   }

   public ItemsSketchSortedView getSortedView() {
      return this.refreshSortedView();
   }

   private final ItemsSketchSortedView refreshSortedView() {
      return this.classicQisSV == null ? (this.classicQisSV = getSV(this)) : this.classicQisSV;
   }

   private static ItemsSketchSortedView getSV(ItemsSketch sk) {
      long totalN = sk.getN();
      if (!sk.isEmpty() && totalN != 0L) {
         int k = sk.getK();
         int numQuantiles = sk.getNumRetained();
         T[] svQuantiles = (T[])((Object[])((Object[])Array.newInstance(sk.clazz, numQuantiles)));
         long[] svCumWeights = new long[numQuantiles];
         Comparator<? super T> comparator = sk.comparator_;
         T[] combinedBuffer = (T[])((Object[])sk.getCombinedBuffer());
         int baseBufferCount = sk.getBaseBufferCount();
         populateFromItemsSketch(k, totalN, sk.getBitPattern(), combinedBuffer, baseBufferCount, numQuantiles, svQuantiles, svCumWeights, sk.getComparator());
         ItemsMergeImpl.blockyTandemMergeSort(svQuantiles, svCumWeights, numQuantiles, k, comparator);
         if (convertToCumulative(svCumWeights) != totalN) {
            throw new SketchesStateException("Sorted View is misconfigured. TotalN does not match cumWeights.");
         } else {
            return new ItemsSketchSortedView(svQuantiles, svCumWeights, sk);
         }
      } else {
         throw new SketchesArgumentException("The sketch must not be empty for this operation. ");
      }
   }

   private static final void populateFromItemsSketch(int k, long totalN, long bitPattern, Object[] combinedBuffer, int baseBufferCount, int numQuantiles, Object[] svQuantiles, long[] svCumWeights, Comparator comparator) {
      // $FF: Couldn't be decompiled
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
