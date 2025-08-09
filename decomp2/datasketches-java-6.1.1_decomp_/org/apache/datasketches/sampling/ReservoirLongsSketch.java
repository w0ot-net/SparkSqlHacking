package org.apache.datasketches.sampling;

import java.util.Arrays;
import java.util.function.Predicate;
import org.apache.datasketches.common.Family;
import org.apache.datasketches.common.ResizeFactor;
import org.apache.datasketches.common.SketchesArgumentException;
import org.apache.datasketches.common.SketchesStateException;
import org.apache.datasketches.common.Util;
import org.apache.datasketches.memory.Memory;
import org.apache.datasketches.memory.WritableMemory;

public final class ReservoirLongsSketch {
   private static final int MIN_LG_ARR_LONGS = 4;
   private static final long MAX_ITEMS_SEEN = 281474976710655L;
   private static final ResizeFactor DEFAULT_RESIZE_FACTOR;
   private final int reservoirSize_;
   private int currItemsAlloc_;
   private long itemsSeen_;
   private final ResizeFactor rf_;
   private long[] data_;

   private ReservoirLongsSketch(int k, ResizeFactor rf) {
      if (k < 2) {
         throw new SketchesArgumentException("k must be at least 2");
      } else {
         this.reservoirSize_ = k;
         this.rf_ = rf;
         this.itemsSeen_ = 0L;
         int ceilingLgK = Util.exactLog2OfInt(Util.ceilingPowerOf2(this.reservoirSize_), "ReservoirLongsSketch");
         int initialLgSize = SamplingUtil.startingSubMultiple(ceilingLgK, this.rf_.lg(), 4);
         this.currItemsAlloc_ = SamplingUtil.getAdjustedSize(this.reservoirSize_, 1 << initialLgSize);
         this.data_ = new long[this.currItemsAlloc_];
         Arrays.fill(this.data_, 0L);
      }
   }

   private ReservoirLongsSketch(long[] data, long itemsSeen, ResizeFactor rf, int k) {
      if (data == null) {
         throw new SketchesArgumentException("Instantiating sketch with null reservoir");
      } else if (k < 2) {
         throw new SketchesArgumentException("Cannot instantiate sketch with reservoir size less than 2");
      } else if (k < data.length) {
         throw new SketchesArgumentException("Instantiating sketch with max size less than array length: " + k + " max size, array of length " + data.length);
      } else if ((itemsSeen < (long)k || data.length >= k) && (itemsSeen >= (long)k || (long)data.length >= itemsSeen)) {
         this.reservoirSize_ = k;
         this.currItemsAlloc_ = data.length;
         this.itemsSeen_ = itemsSeen;
         this.rf_ = rf;
         this.data_ = data;
      } else {
         throw new SketchesArgumentException("Instantiating sketch with too few samples. Items seen: " + itemsSeen + ", max reservoir size: " + k + ", items array length: " + data.length);
      }
   }

   private ReservoirLongsSketch(int k, int currItemsAlloc, long itemsSeen, ResizeFactor rf, long[] data) {
      this.reservoirSize_ = k;
      this.currItemsAlloc_ = currItemsAlloc;
      this.itemsSeen_ = itemsSeen;
      this.rf_ = rf;
      this.data_ = data;
   }

   public static ReservoirLongsSketch newInstance(int k) {
      return new ReservoirLongsSketch(k, DEFAULT_RESIZE_FACTOR);
   }

   public static ReservoirLongsSketch newInstance(int k, ResizeFactor rf) {
      return new ReservoirLongsSketch(k, rf);
   }

   public static ReservoirLongsSketch heapify(Memory srcMem) {
      Family.RESERVOIR.checkFamilyID(srcMem.getByte(2L));
      int numPreLongs = PreambleUtil.extractPreLongs(srcMem);
      ResizeFactor rf = ResizeFactor.getRF(PreambleUtil.extractResizeFactor(srcMem));
      int serVer = PreambleUtil.extractSerVer(srcMem);
      boolean isEmpty = (PreambleUtil.extractFlags(srcMem) & 4) != 0;
      long itemsSeen = isEmpty ? 0L : PreambleUtil.extractN(srcMem);
      int k = PreambleUtil.extractK(srcMem);
      boolean preLongsEqMin = numPreLongs == Family.RESERVOIR.getMinPreLongs();
      boolean preLongsEqMax = numPreLongs == Family.RESERVOIR.getMaxPreLongs();
      if (!preLongsEqMin && !preLongsEqMax) {
         throw new SketchesArgumentException("Possible corruption: Non-empty sketch with only " + Family.RESERVOIR.getMinPreLongs() + "preLongs");
      } else {
         if (serVer != 2) {
            if (serVer != 1) {
               throw new SketchesArgumentException("Possible Corruption: Ser Ver must be 2: " + serVer);
            }

            short encK = PreambleUtil.extractEncodedReservoirSize(srcMem);
            k = ReservoirSize.decodeValue(encK);
         }

         if (isEmpty) {
            return new ReservoirLongsSketch(k, rf);
         } else {
            int preLongBytes = numPreLongs << 3;
            int numSketchLongs = (int)Math.min(itemsSeen, (long)k);
            int allocatedSize = k;
            if (itemsSeen < (long)k) {
               int ceilingLgK = Util.exactLog2OfInt(Util.ceilingPowerOf2(k), "k");
               int minLgSize = Util.exactLog2OfInt(Util.ceilingPowerOf2((int)itemsSeen), "heapify");
               int initialLgSize = SamplingUtil.startingSubMultiple(ceilingLgK, rf.lg(), Math.max(minLgSize, 4));
               allocatedSize = SamplingUtil.getAdjustedSize(k, 1 << initialLgSize);
            }

            long[] data = new long[allocatedSize];
            srcMem.getLongArray((long)preLongBytes, data, 0, numSketchLongs);
            return new ReservoirLongsSketch(data, itemsSeen, rf, k);
         }
      }
   }

   static ReservoirLongsSketch getInstance(long[] data, long itemsSeen, ResizeFactor rf, int k) {
      return new ReservoirLongsSketch(data, itemsSeen, rf, k);
   }

   public int getK() {
      return this.reservoirSize_;
   }

   public long getN() {
      return this.itemsSeen_;
   }

   public int getNumSamples() {
      return (int)Math.min((long)this.reservoirSize_, this.itemsSeen_);
   }

   public long[] getSamples() {
      if (this.itemsSeen_ == 0L) {
         return null;
      } else {
         int numSamples = (int)Math.min((long)this.reservoirSize_, this.itemsSeen_);
         return Arrays.copyOf(this.data_, numSamples);
      }
   }

   public void update(long item) {
      if (this.itemsSeen_ == 281474976710655L) {
         throw new SketchesStateException("Sketch has exceeded capacity for total items seen: 281474976710655");
      } else {
         if (this.itemsSeen_ < (long)this.reservoirSize_) {
            if (this.itemsSeen_ >= (long)this.currItemsAlloc_) {
               this.growReservoir();
            }

            assert this.itemsSeen_ < (long)this.currItemsAlloc_;

            this.data_[(int)this.itemsSeen_] = item;
            ++this.itemsSeen_;
         } else {
            ++this.itemsSeen_;
            if (SamplingUtil.rand().nextDouble() * (double)this.itemsSeen_ < (double)this.reservoirSize_) {
               int newSlot = SamplingUtil.rand().nextInt(this.reservoirSize_);
               this.data_[newSlot] = item;
            }
         }

      }
   }

   public void reset() {
      int ceilingLgK = Util.exactLog2OfInt(Util.ceilingPowerOf2(this.reservoirSize_), "ReservoirLongsSketch");
      int initialLgSize = SamplingUtil.startingSubMultiple(ceilingLgK, this.rf_.lg(), 4);
      this.currItemsAlloc_ = SamplingUtil.getAdjustedSize(this.reservoirSize_, 1 << initialLgSize);
      this.data_ = new long[this.currItemsAlloc_];
      this.itemsSeen_ = 0L;
   }

   public String toString() {
      StringBuilder sb = new StringBuilder();
      String thisSimpleName = this.getClass().getSimpleName();
      sb.append(Util.LS);
      sb.append("### ").append(thisSimpleName).append(" SUMMARY: ").append(Util.LS);
      sb.append("   k            : ").append(this.reservoirSize_).append(Util.LS);
      sb.append("   n            : ").append(this.itemsSeen_).append(Util.LS);
      sb.append("   Current size : ").append(this.currItemsAlloc_).append(Util.LS);
      sb.append("   Resize factor: ").append(this.rf_).append(Util.LS);
      sb.append("### END SKETCH SUMMARY").append(Util.LS);
      return sb.toString();
   }

   public static String toString(byte[] byteArr) {
      return PreambleUtil.preambleToString(byteArr);
   }

   public static String toString(Memory mem) {
      return PreambleUtil.preambleToString(mem);
   }

   public byte[] toByteArray() {
      boolean empty = this.itemsSeen_ == 0L;
      int numItems = (int)Math.min((long)this.reservoirSize_, this.itemsSeen_);
      int preLongs;
      int outBytes;
      if (empty) {
         preLongs = 1;
         outBytes = 8;
      } else {
         preLongs = Family.RESERVOIR.getMaxPreLongs();
         outBytes = preLongs + numItems << 3;
      }

      byte[] outArr = new byte[outBytes];
      WritableMemory mem = WritableMemory.writableWrap(outArr);
      PreambleUtil.insertPreLongs(mem, preLongs);
      PreambleUtil.insertLgResizeFactor(mem, this.rf_.lg());
      PreambleUtil.insertSerVer(mem, 2);
      PreambleUtil.insertFamilyID(mem, Family.RESERVOIR.getID());
      if (empty) {
         PreambleUtil.insertFlags(mem, 4);
      } else {
         PreambleUtil.insertFlags(mem, 0);
      }

      PreambleUtil.insertK(mem, this.reservoirSize_);
      if (!empty) {
         PreambleUtil.insertN(mem, this.itemsSeen_);
         int preBytes = preLongs << 3;
         mem.putLongArray((long)preBytes, this.data_, 0, numItems);
      }

      return outArr;
   }

   public SampleSubsetSummary estimateSubsetSum(Predicate predicate) {
      if (this.itemsSeen_ == 0L) {
         return new SampleSubsetSummary((double)0.0F, (double)0.0F, (double)0.0F, (double)0.0F);
      } else {
         int numSamples = this.getNumSamples();
         double samplingRate = (double)numSamples / (double)this.itemsSeen_;

         assert samplingRate >= (double)0.0F;

         assert samplingRate <= (double)1.0F;

         int predTrueCount = 0;

         for(int i = 0; i < numSamples; ++i) {
            if (predicate.test(this.data_[i])) {
               ++predTrueCount;
            }
         }

         if (this.itemsSeen_ <= (long)this.reservoirSize_) {
            return new SampleSubsetSummary((double)predTrueCount, (double)predTrueCount, (double)predTrueCount, (double)numSamples);
         } else {
            double lbTrueFraction = SamplingUtil.pseudoHypergeometricLBonP((long)numSamples, predTrueCount, samplingRate);
            double estimatedTrueFraction = (double)1.0F * (double)predTrueCount / (double)numSamples;
            double ubTrueFraction = SamplingUtil.pseudoHypergeometricUBonP((long)numSamples, predTrueCount, samplingRate);
            return new SampleSubsetSummary((double)this.itemsSeen_ * lbTrueFraction, (double)this.itemsSeen_ * estimatedTrueFraction, (double)this.itemsSeen_ * ubTrueFraction, (double)this.itemsSeen_);
         }
      }
   }

   double getImplicitSampleWeight() {
      return this.itemsSeen_ < (long)this.reservoirSize_ ? (double)1.0F : (double)1.0F * (double)this.itemsSeen_ / (double)this.reservoirSize_;
   }

   long getValueAtPosition(int pos) {
      if (this.itemsSeen_ == 0L) {
         throw new SketchesArgumentException("Requested element from empty reservoir.");
      } else if (pos >= 0 && pos < this.getNumSamples()) {
         return this.data_[pos];
      } else {
         throw new SketchesArgumentException("Requested position must be between 0 and " + (this.getNumSamples() - 1) + ", inclusive. Received: " + pos);
      }
   }

   void insertValueAtPosition(long value, int pos) {
      if (pos >= 0 && pos < this.getNumSamples()) {
         this.data_[pos] = value;
      } else {
         throw new SketchesArgumentException("Insert position must be between 0 and " + this.getNumSamples() + ", inclusive. Received: " + pos);
      }
   }

   void forceIncrementItemsSeen(long inc) {
      this.itemsSeen_ += inc;
      if (this.itemsSeen_ > 281474976710655L) {
         throw new SketchesStateException("Sketch has exceeded capacity for total items seen. Limit: 281474976710655, found: " + this.itemsSeen_);
      }
   }

   ReservoirLongsSketch copy() {
      long[] dataCopy = Arrays.copyOf(this.data_, this.currItemsAlloc_);
      return new ReservoirLongsSketch(this.reservoirSize_, this.currItemsAlloc_, this.itemsSeen_, this.rf_, dataCopy);
   }

   ReservoirLongsSketch downsampledCopy(int maxK) {
      ReservoirLongsSketch rls = new ReservoirLongsSketch(maxK, this.rf_);

      for(long l : this.getSamples()) {
         rls.update(l);
      }

      if (rls.getN() < this.itemsSeen_) {
         rls.forceIncrementItemsSeen(this.itemsSeen_ - rls.getN());
      }

      return rls;
   }

   private void growReservoir() {
      this.currItemsAlloc_ = SamplingUtil.getAdjustedSize(this.reservoirSize_, this.currItemsAlloc_ * this.rf_.getValue());
      this.data_ = Arrays.copyOf(this.data_, this.currItemsAlloc_);
   }

   static {
      DEFAULT_RESIZE_FACTOR = ResizeFactor.X8;
   }
}
