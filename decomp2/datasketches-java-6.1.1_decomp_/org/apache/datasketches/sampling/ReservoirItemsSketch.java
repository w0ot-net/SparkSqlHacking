package org.apache.datasketches.sampling;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.function.Predicate;
import org.apache.datasketches.common.ArrayOfItemsSerDe;
import org.apache.datasketches.common.Family;
import org.apache.datasketches.common.ResizeFactor;
import org.apache.datasketches.common.SketchesArgumentException;
import org.apache.datasketches.common.SketchesStateException;
import org.apache.datasketches.common.Util;
import org.apache.datasketches.memory.Memory;
import org.apache.datasketches.memory.WritableMemory;

public final class ReservoirItemsSketch {
   private static final int MIN_LG_ARR_ITEMS = 4;
   private static final long MAX_ITEMS_SEEN = 281474976710655L;
   private static final ResizeFactor DEFAULT_RESIZE_FACTOR;
   private final int reservoirSize_;
   private int currItemsAlloc_;
   private long itemsSeen_;
   private final ResizeFactor rf_;
   private ArrayList data_;

   private ReservoirItemsSketch(int k, ResizeFactor rf) {
      if (k < 2) {
         throw new SketchesArgumentException("k must be at least 2");
      } else {
         this.reservoirSize_ = k;
         this.rf_ = rf;
         this.itemsSeen_ = 0L;
         int ceilingLgK = Util.exactLog2OfInt(Util.ceilingPowerOf2(this.reservoirSize_), "reservoirSize_");
         int initialLgSize = SamplingUtil.startingSubMultiple(ceilingLgK, this.rf_.lg(), 4);
         this.currItemsAlloc_ = SamplingUtil.getAdjustedSize(this.reservoirSize_, 1 << initialLgSize);
         this.data_ = new ArrayList(this.currItemsAlloc_);
      }
   }

   private ReservoirItemsSketch(ArrayList data, long itemsSeen, ResizeFactor rf, int k) {
      if (data == null) {
         throw new SketchesArgumentException("Instantiating sketch with null reservoir");
      } else if (k < 2) {
         throw new SketchesArgumentException("Cannot instantiate sketch with reservoir size less than 2");
      } else if (k < data.size()) {
         throw new SketchesArgumentException("Instantiating sketch with max size less than array length: " + k + " max size, array of length " + data.size());
      } else if ((itemsSeen < (long)k || data.size() >= k) && (itemsSeen >= (long)k || (long)data.size() >= itemsSeen)) {
         this.reservoirSize_ = k;
         this.currItemsAlloc_ = data.size();
         this.itemsSeen_ = itemsSeen;
         this.rf_ = rf;
         this.data_ = data;
      } else {
         throw new SketchesArgumentException("Instantiating sketch with too few samples. Items seen: " + itemsSeen + ", max reservoir size: " + k + ", items array length: " + data.size());
      }
   }

   private ReservoirItemsSketch(int k, int currItemsAlloc, long itemsSeen, ResizeFactor rf, ArrayList data) {
      this.reservoirSize_ = k;
      this.currItemsAlloc_ = currItemsAlloc;
      this.itemsSeen_ = itemsSeen;
      this.rf_ = rf;
      this.data_ = data;
   }

   public static ReservoirItemsSketch newInstance(int k) {
      return new ReservoirItemsSketch(k, DEFAULT_RESIZE_FACTOR);
   }

   public static ReservoirItemsSketch newInstance(int k, ResizeFactor rf) {
      return new ReservoirItemsSketch(k, rf);
   }

   static ReservoirItemsSketch newInstance(ArrayList data, long itemsSeen, ResizeFactor rf, int k) {
      return new ReservoirItemsSketch(data, itemsSeen, rf, k);
   }

   public static ReservoirItemsSketch heapify(Memory srcMem, ArrayOfItemsSerDe serDe) {
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
         throw new SketchesArgumentException("Possible corruption: Non-empty sketch with only " + Family.RESERVOIR.getMinPreLongs() + " preLong(s)");
      } else {
         if (serVer != 2) {
            if (serVer != 1) {
               throw new SketchesArgumentException("Possible Corruption: Ser Ver must be 2: " + serVer);
            }

            short encK = PreambleUtil.extractEncodedReservoirSize(srcMem);
            k = ReservoirSize.decodeValue(encK);
         }

         if (isEmpty) {
            return new ReservoirItemsSketch(k, rf);
         } else {
            int preLongBytes = numPreLongs << 3;
            int allocatedItems = k;
            if (itemsSeen < (long)k) {
               int ceilingLgK = Util.exactLog2OfInt(Util.ceilingPowerOf2(k), "heapify");
               int minLgSize = Util.exactLog2OfInt(Util.ceilingPowerOf2((int)itemsSeen), "heapify");
               int initialLgSize = SamplingUtil.startingSubMultiple(ceilingLgK, rf.lg(), Math.max(minLgSize, 4));
               allocatedItems = SamplingUtil.getAdjustedSize(k, 1 << initialLgSize);
            }

            int itemsToRead = (int)Math.min((long)k, itemsSeen);
            T[] data = (T[])serDe.deserializeFromMemory(srcMem, (long)preLongBytes, itemsToRead);
            ArrayList<T> dataList = new ArrayList(Arrays.asList(data));
            ReservoirItemsSketch<T> ris = new ReservoirItemsSketch(dataList, itemsSeen, rf, k);
            ris.data_.ensureCapacity(allocatedItems);
            ris.currItemsAlloc_ = allocatedItems;
            return ris;
         }
      }
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

   public void update(Object item) {
      if (this.itemsSeen_ == 281474976710655L) {
         throw new SketchesStateException("Sketch has exceeded capacity for total items seen: 281474976710655");
      } else if (item != null) {
         if (this.itemsSeen_ < (long)this.reservoirSize_) {
            if (this.itemsSeen_ >= (long)this.currItemsAlloc_) {
               this.growReservoir();
            }

            assert this.itemsSeen_ < (long)this.currItemsAlloc_;

            this.data_.add(item);
            ++this.itemsSeen_;
         } else {
            ++this.itemsSeen_;
            if (SamplingUtil.rand().nextDouble() * (double)this.itemsSeen_ < (double)this.reservoirSize_) {
               int newSlot = SamplingUtil.rand().nextInt(this.reservoirSize_);
               this.data_.set(newSlot, item);
            }
         }

      }
   }

   public void reset() {
      int ceilingLgK = Util.exactLog2OfInt(Util.ceilingPowerOf2(this.reservoirSize_), "ReservoirItemsSketch");
      int initialLgSize = SamplingUtil.startingSubMultiple(ceilingLgK, this.rf_.lg(), 4);
      this.currItemsAlloc_ = SamplingUtil.getAdjustedSize(this.reservoirSize_, 1 << initialLgSize);
      this.data_ = new ArrayList(this.currItemsAlloc_);
      this.itemsSeen_ = 0L;
   }

   public Object[] getSamples() {
      if (this.itemsSeen_ == 0L) {
         return null;
      } else {
         Class<?> clazz = this.data_.get(0).getClass();
         return this.data_.toArray(Array.newInstance(clazz, 0));
      }
   }

   public Object[] getSamples(Class clazz) {
      return this.itemsSeen_ == 0L ? null : this.data_.toArray(Array.newInstance(clazz, 0));
   }

   ArrayList getRawSamplesAsList() {
      return this.data_;
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

   public byte[] toByteArray(ArrayOfItemsSerDe serDe) {
      return this.itemsSeen_ == 0L ? this.toByteArray(serDe, (Class)null) : this.toByteArray(serDe, this.data_.get(0).getClass());
   }

   public byte[] toByteArray(ArrayOfItemsSerDe serDe, Class clazz) {
      boolean empty = this.itemsSeen_ == 0L;
      byte[] bytes = null;
      int preLongs;
      int outBytes;
      if (empty) {
         preLongs = 1;
         outBytes = 8;
      } else {
         preLongs = Family.RESERVOIR.getMaxPreLongs();
         bytes = serDe.serializeToByteArray(this.getSamples(clazz));
         outBytes = (preLongs << 3) + bytes.length;
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
         mem.putByteArray((long)preBytes, bytes, 0, bytes.length);
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

         int trueCount = 0;

         for(int i = 0; i < numSamples; ++i) {
            if (predicate.test(this.data_.get(i))) {
               ++trueCount;
            }
         }

         if (this.itemsSeen_ <= (long)this.reservoirSize_) {
            return new SampleSubsetSummary((double)trueCount, (double)trueCount, (double)trueCount, (double)numSamples);
         } else {
            double lbTrueFraction = SamplingUtil.pseudoHypergeometricLBonP((long)numSamples, trueCount, samplingRate);
            double estimatedTrueFraction = (double)1.0F * (double)trueCount / (double)numSamples;
            double ubTrueFraction = SamplingUtil.pseudoHypergeometricUBonP((long)numSamples, trueCount, samplingRate);
            return new SampleSubsetSummary((double)this.itemsSeen_ * lbTrueFraction, (double)this.itemsSeen_ * estimatedTrueFraction, (double)this.itemsSeen_ * ubTrueFraction, (double)this.itemsSeen_);
         }
      }
   }

   double getImplicitSampleWeight() {
      return this.itemsSeen_ < (long)this.reservoirSize_ ? (double)1.0F : (double)1.0F * (double)this.itemsSeen_ / (double)this.reservoirSize_;
   }

   Object getValueAtPosition(int pos) {
      if (this.itemsSeen_ == 0L) {
         throw new SketchesArgumentException("Requested element from empty reservoir.");
      } else if (pos >= 0 && pos < this.getNumSamples()) {
         return this.data_.get(pos);
      } else {
         throw new SketchesArgumentException("Requested position must be between 0 and " + this.getNumSamples() + ", inclusive. Received: " + pos);
      }
   }

   void insertValueAtPosition(Object value, int pos) {
      if (pos >= 0 && pos < this.getNumSamples()) {
         this.data_.set(pos, value);
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

   ReservoirItemsSketch copy() {
      return new ReservoirItemsSketch(this.reservoirSize_, this.currItemsAlloc_, this.itemsSeen_, this.rf_, (ArrayList)this.data_.clone());
   }

   ReservoirItemsSketch downsampledCopy(int maxK) {
      ReservoirItemsSketch<T> ris = new ReservoirItemsSketch(maxK, this.rf_);

      for(Object item : this.getSamples()) {
         ris.update(item);
      }

      if (ris.getN() < this.itemsSeen_) {
         ris.forceIncrementItemsSeen(this.itemsSeen_ - ris.getN());
      }

      return ris;
   }

   private void growReservoir() {
      this.currItemsAlloc_ = SamplingUtil.getAdjustedSize(this.reservoirSize_, this.currItemsAlloc_ << this.rf_.lg());
      this.data_.ensureCapacity(this.currItemsAlloc_);
   }

   static {
      DEFAULT_RESIZE_FACTOR = ResizeFactor.X8;
   }
}
