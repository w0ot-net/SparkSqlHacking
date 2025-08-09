package org.apache.datasketches.tuple.arrayofdoubles;

import java.nio.ByteOrder;
import java.util.Arrays;
import org.apache.datasketches.common.Family;
import org.apache.datasketches.common.ResizeFactor;
import org.apache.datasketches.common.SketchesArgumentException;
import org.apache.datasketches.common.Util;
import org.apache.datasketches.memory.Memory;
import org.apache.datasketches.memory.WritableMemory;
import org.apache.datasketches.thetacommon.HashOperations;
import org.apache.datasketches.tuple.SerializerDeserializer;

final class HeapArrayOfDoublesQuickSelectSketch extends ArrayOfDoublesQuickSelectSketch {
   private final int lgNomEntries_;
   private final int lgResizeFactor_;
   private final float samplingProbability_;
   private int count_;
   private long[] keys_;
   private double[] values_;

   HeapArrayOfDoublesQuickSelectSketch(int nomEntries, int lgResizeFactor, float samplingProbability, int numValues, long seed) {
      super(numValues, seed);
      this.lgNomEntries_ = Util.exactLog2OfLong((long)Util.ceilingPowerOf2(nomEntries));
      this.lgResizeFactor_ = lgResizeFactor;
      this.samplingProbability_ = samplingProbability;
      this.thetaLong_ = (long)((double)Long.MAX_VALUE * (double)samplingProbability);
      int startingCapacity = org.apache.datasketches.tuple.Util.getStartingCapacity(nomEntries, lgResizeFactor);
      this.keys_ = new long[startingCapacity];
      this.values_ = new double[startingCapacity * numValues];
      this.lgCurrentCapacity_ = Integer.numberOfTrailingZeros(startingCapacity);
      this.setRebuildThreshold();
   }

   HeapArrayOfDoublesQuickSelectSketch(Memory mem, long seed) {
      super(mem.getByte(5L), seed);
      SerializerDeserializer.validateFamily(mem.getByte(2L), mem.getByte(0L));
      SerializerDeserializer.validateType(mem.getByte(3L), SerializerDeserializer.SketchType.ArrayOfDoublesQuickSelectSketch);
      byte version = mem.getByte(1L);
      if (version != 1) {
         throw new SketchesArgumentException("Serial version mismatch. Expected: 1, actual: " + version);
      } else {
         byte flags = mem.getByte(4L);
         boolean isBigEndian = (flags & 1 << ArrayOfDoublesSketch.Flags.IS_BIG_ENDIAN.ordinal()) > 0;
         if (isBigEndian ^ ByteOrder.nativeOrder().equals(ByteOrder.BIG_ENDIAN)) {
            throw new SketchesArgumentException("Byte order mismatch");
         } else {
            org.apache.datasketches.tuple.Util.checkSeedHashes(mem.getShort(6L), org.apache.datasketches.tuple.Util.computeSeedHash(seed));
            this.isEmpty_ = (flags & 1 << ArrayOfDoublesSketch.Flags.IS_EMPTY.ordinal()) > 0;
            this.lgNomEntries_ = mem.getByte(16L);
            this.thetaLong_ = mem.getLong(8L);
            int currentCapacity = 1 << mem.getByte(17L);
            this.lgResizeFactor_ = mem.getByte(18L);
            this.samplingProbability_ = mem.getFloat(20L);
            this.keys_ = new long[currentCapacity];
            this.values_ = new double[currentCapacity * this.numValues_];
            boolean hasEntries = (flags & 1 << ArrayOfDoublesSketch.Flags.HAS_ENTRIES.ordinal()) > 0;
            this.count_ = hasEntries ? mem.getInt(24L) : 0;
            if (this.count_ > 0) {
               mem.getLongArray(32L, this.keys_, 0, currentCapacity);
               mem.getDoubleArray(32L + 8L * (long)currentCapacity, this.values_, 0, currentCapacity * this.numValues_);
            }

            this.setRebuildThreshold();
            this.lgCurrentCapacity_ = Integer.numberOfTrailingZeros(currentCapacity);
         }
      }
   }

   public double[][] getValues() {
      int numVal = this.numValues_;
      int count = this.getRetainedEntries();
      double[][] values = new double[count][];
      if (count > 0) {
         int cnt = 0;

         for(int j = 0; j < this.keys_.length; ++j) {
            if (this.keys_[j] != 0L) {
               values[cnt++] = Arrays.copyOfRange(this.values_, j * numVal, (j + 1) * numVal);
            }
         }

         assert cnt == count;
      }

      return values;
   }

   double[] getValuesAsOneDimension() {
      int numVal = this.numValues_;
      int count = this.getRetainedEntries();
      double[] values = new double[count * numVal];
      if (count > 0) {
         int cnt = 0;

         for(int j = 0; j < this.keys_.length; ++j) {
            if (this.keys_[j] != 0L) {
               System.arraycopy(this.values_, j * numVal, values, cnt++ * numVal, numVal);
            }
         }

         assert cnt == count;
      }

      return values;
   }

   long[] getKeys() {
      int count = this.getRetainedEntries();
      long[] keysArr = new long[count];
      if (count > 0) {
         int cnt = 0;

         for(int j = 0; j < this.keys_.length; ++j) {
            if (this.keys_[j] != 0L) {
               keysArr[cnt++] = this.keys_[j];
            }
         }

         assert cnt == count;
      }

      return keysArr;
   }

   public int getRetainedEntries() {
      return this.count_;
   }

   public int getNominalEntries() {
      return 1 << this.lgNomEntries_;
   }

   public float getSamplingProbability() {
      return this.samplingProbability_;
   }

   public ResizeFactor getResizeFactor() {
      return ResizeFactor.getRF(this.lgResizeFactor_);
   }

   public byte[] toByteArray() {
      byte[] byteArray = new byte[this.getSerializedSizeBytes()];
      WritableMemory mem = WritableMemory.writableWrap(byteArray);
      this.serializeInto(mem);
      return byteArray;
   }

   public ArrayOfDoublesSketchIterator iterator() {
      return new HeapArrayOfDoublesSketchIterator(this.keys_, this.values_, this.numValues_);
   }

   int getSerializedSizeBytes() {
      return 32 + (8 + 8 * this.numValues_) * this.getCurrentCapacity();
   }

   void serializeInto(WritableMemory mem) {
      mem.putByte(0L, (byte)1);
      mem.putByte(1L, (byte)1);
      mem.putByte(2L, (byte)Family.TUPLE.getID());
      mem.putByte(3L, (byte)SerializerDeserializer.SketchType.ArrayOfDoublesQuickSelectSketch.ordinal());
      boolean isBigEndian = ByteOrder.nativeOrder().equals(ByteOrder.BIG_ENDIAN);
      mem.putByte(4L, (byte)((isBigEndian ? 1 << ArrayOfDoublesSketch.Flags.IS_BIG_ENDIAN.ordinal() : 0) | (this.isInSamplingMode() ? 1 << ArrayOfDoublesSketch.Flags.IS_IN_SAMPLING_MODE.ordinal() : 0) | (this.isEmpty_ ? 1 << ArrayOfDoublesSketch.Flags.IS_EMPTY.ordinal() : 0) | (this.count_ > 0 ? 1 << ArrayOfDoublesSketch.Flags.HAS_ENTRIES.ordinal() : 0)));
      mem.putByte(5L, (byte)this.numValues_);
      mem.putShort(6L, org.apache.datasketches.tuple.Util.computeSeedHash(this.seed_));
      mem.putLong(8L, this.thetaLong_);
      mem.putByte(16L, (byte)this.lgNomEntries_);
      mem.putByte(17L, (byte)Integer.numberOfTrailingZeros(this.keys_.length));
      mem.putByte(18L, (byte)this.lgResizeFactor_);
      mem.putFloat(20L, this.samplingProbability_);
      mem.putInt(24L, this.count_);
      if (this.count_ > 0) {
         mem.putLongArray(32L, this.keys_, 0, this.keys_.length);
         mem.putDoubleArray(32L + 8L * (long)this.keys_.length, this.values_, 0, this.values_.length);
      }

   }

   public boolean hasMemory() {
      return false;
   }

   Memory getMemory() {
      return null;
   }

   public void reset() {
      this.isEmpty_ = true;
      this.count_ = 0;
      this.thetaLong_ = (long)((double)Long.MAX_VALUE * (double)this.samplingProbability_);
      int startingCapacity = org.apache.datasketches.tuple.Util.getStartingCapacity(1 << this.lgNomEntries_, this.lgResizeFactor_);
      this.keys_ = new long[startingCapacity];
      this.values_ = new double[startingCapacity * this.numValues_];
      this.lgCurrentCapacity_ = Integer.numberOfTrailingZeros(startingCapacity);
      this.setRebuildThreshold();
   }

   protected long getKey(int index) {
      return this.keys_[index];
   }

   protected void incrementCount() {
      ++this.count_;
   }

   protected void setValues(int index, double[] values) {
      if (this.numValues_ == 1) {
         this.values_[index] = values[0];
      } else {
         System.arraycopy(values, 0, this.values_, index * this.numValues_, this.numValues_);
      }

   }

   protected void updateValues(int index, double[] values) {
      if (this.numValues_ == 1) {
         double[] var10000 = this.values_;
         var10000[index] += values[0];
      } else {
         int offset = index * this.numValues_;

         for(int i = 0; i < this.numValues_; ++i) {
            double[] var5 = this.values_;
            var5[offset + i] += values[i];
         }
      }

   }

   protected void setNotEmpty() {
      this.isEmpty_ = false;
   }

   protected boolean isInSamplingMode() {
      return this.samplingProbability_ < 1.0F;
   }

   protected void setThetaLong(long thetaLong) {
      this.thetaLong_ = thetaLong;
   }

   protected int getCurrentCapacity() {
      return this.keys_.length;
   }

   protected void rebuild(int newCapacity) {
      long[] oldKeys = this.keys_;
      double[] oldValues = this.values_;
      this.keys_ = new long[newCapacity];
      this.values_ = new double[newCapacity * this.numValues_];
      this.count_ = 0;
      this.lgCurrentCapacity_ = Integer.numberOfTrailingZeros(newCapacity);

      for(int i = 0; i < oldKeys.length; ++i) {
         if (oldKeys[i] != 0L && oldKeys[i] < this.thetaLong_) {
            this.insert(oldKeys[i], Arrays.copyOfRange(oldValues, i * this.numValues_, (i + 1) * this.numValues_));
         }
      }

      this.setRebuildThreshold();
   }

   protected int insertKey(long key) {
      return HashOperations.hashInsertOnly(this.keys_, this.lgCurrentCapacity_, key);
   }

   protected int findOrInsertKey(long key) {
      return HashOperations.hashSearchOrInsert(this.keys_, this.lgCurrentCapacity_, key);
   }

   protected double[] find(long key) {
      int index = HashOperations.hashSearch(this.keys_, this.lgCurrentCapacity_, key);
      return index == -1 ? null : Arrays.copyOfRange(this.values_, index * this.numValues_, (index + 1) * this.numValues_);
   }
}
