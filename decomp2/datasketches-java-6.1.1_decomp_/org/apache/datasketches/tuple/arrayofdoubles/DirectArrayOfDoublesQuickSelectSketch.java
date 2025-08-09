package org.apache.datasketches.tuple.arrayofdoubles;

import java.nio.ByteOrder;
import java.util.Arrays;
import org.apache.datasketches.common.Family;
import org.apache.datasketches.common.ResizeFactor;
import org.apache.datasketches.common.SketchesArgumentException;
import org.apache.datasketches.memory.Memory;
import org.apache.datasketches.memory.WritableMemory;
import org.apache.datasketches.thetacommon.HashOperations;
import org.apache.datasketches.tuple.SerializerDeserializer;
import org.apache.datasketches.tuple.Util;

class DirectArrayOfDoublesQuickSelectSketch extends ArrayOfDoublesQuickSelectSketch {
   private WritableMemory mem_;
   private int keysOffset_;
   private int valuesOffset_;

   DirectArrayOfDoublesQuickSelectSketch(int nomEntries, int lgResizeFactor, float samplingProbability, int numValues, long seed, WritableMemory dstMem) {
      this(checkMemory(nomEntries, lgResizeFactor, numValues, dstMem), nomEntries, lgResizeFactor, samplingProbability, numValues, seed, dstMem);
   }

   private DirectArrayOfDoublesQuickSelectSketch(boolean secure, int nomEntries, int lgResizeFactor, float samplingProbability, int numValues, long seed, WritableMemory dstMem) {
      super(numValues, seed);
      this.mem_ = dstMem;
      int startingCapacity = Util.getStartingCapacity(nomEntries, lgResizeFactor);
      this.mem_.putByte(0L, (byte)1);
      this.mem_.putByte(1L, (byte)1);
      this.mem_.putByte(2L, (byte)Family.TUPLE.getID());
      this.mem_.putByte(3L, (byte)SerializerDeserializer.SketchType.ArrayOfDoublesQuickSelectSketch.ordinal());
      boolean isBigEndian = ByteOrder.nativeOrder().equals(ByteOrder.BIG_ENDIAN);
      this.mem_.putByte(4L, (byte)((isBigEndian ? 1 << ArrayOfDoublesSketch.Flags.IS_BIG_ENDIAN.ordinal() : 0) | (samplingProbability < 1.0F ? 1 << ArrayOfDoublesSketch.Flags.IS_IN_SAMPLING_MODE.ordinal() : 0) | 1 << ArrayOfDoublesSketch.Flags.IS_EMPTY.ordinal()));
      this.mem_.putByte(5L, (byte)numValues);
      this.mem_.putShort(6L, Util.computeSeedHash(seed));
      this.thetaLong_ = (long)((double)Long.MAX_VALUE * (double)samplingProbability);
      this.mem_.putLong(8L, this.thetaLong_);
      this.mem_.putByte(16L, (byte)Integer.numberOfTrailingZeros(nomEntries));
      this.mem_.putByte(17L, (byte)Integer.numberOfTrailingZeros(startingCapacity));
      this.mem_.putByte(18L, (byte)lgResizeFactor);
      this.mem_.putFloat(20L, samplingProbability);
      this.mem_.putInt(24L, 0);
      this.keysOffset_ = 32;
      this.valuesOffset_ = this.keysOffset_ + 8 * startingCapacity;
      this.mem_.clear((long)this.keysOffset_, 8L * (long)startingCapacity);
      this.lgCurrentCapacity_ = Integer.numberOfTrailingZeros(startingCapacity);
      this.setRebuildThreshold();
   }

   private static final boolean checkMemory(int nomEntries, int lgResizeFactor, int numValues, WritableMemory dstMem) {
      int startingCapacity = Util.getStartingCapacity(nomEntries, lgResizeFactor);
      checkIfEnoughMemory(dstMem, startingCapacity, numValues);
      return true;
   }

   DirectArrayOfDoublesQuickSelectSketch(WritableMemory mem, long seed) {
      this(checkSerVer_Endianness(mem), mem, seed);
   }

   private DirectArrayOfDoublesQuickSelectSketch(boolean secure, WritableMemory mem, long seed) {
      super(mem.getByte(5L), seed);
      this.mem_ = mem;
      SerializerDeserializer.validateFamily(mem.getByte(2L), mem.getByte(0L));
      SerializerDeserializer.validateType(this.mem_.getByte(3L), SerializerDeserializer.SketchType.ArrayOfDoublesQuickSelectSketch);
      Util.checkSeedHashes(mem.getShort(6L), Util.computeSeedHash(seed));
      this.keysOffset_ = 32;
      this.valuesOffset_ = this.keysOffset_ + 8 * this.getCurrentCapacity();
      this.lgCurrentCapacity_ = Integer.numberOfTrailingZeros(this.getCurrentCapacity());
      this.thetaLong_ = this.mem_.getLong(8L);
      this.isEmpty_ = (this.mem_.getByte(4L) & 1 << ArrayOfDoublesSketch.Flags.IS_EMPTY.ordinal()) != 0;
      this.setRebuildThreshold();
   }

   private static final boolean checkSerVer_Endianness(Memory mem) {
      byte version = mem.getByte(1L);
      if (version != 1) {
         throw new SketchesArgumentException("Serial version mismatch. Expected: 1, actual: " + version);
      } else {
         boolean isBigEndian = (mem.getByte(4L) & 1 << ArrayOfDoublesSketch.Flags.IS_BIG_ENDIAN.ordinal()) != 0;
         if (isBigEndian ^ ByteOrder.nativeOrder().equals(ByteOrder.BIG_ENDIAN)) {
            throw new SketchesArgumentException("Byte order mismatch");
         } else {
            return true;
         }
      }
   }

   public double[][] getValues() {
      int count = this.getRetainedEntries();
      double[][] values = new double[count][];
      if (count > 0) {
         long keyOffset = (long)this.keysOffset_;
         long valuesOffset = (long)this.valuesOffset_;
         int cnt = 0;

         for(int j = 0; j < this.getCurrentCapacity(); ++j) {
            if (this.mem_.getLong(keyOffset) != 0L) {
               double[] array = new double[this.numValues_];
               this.mem_.getDoubleArray(valuesOffset, array, 0, this.numValues_);
               values[cnt++] = array;
            }

            keyOffset += 8L;
            valuesOffset += 8L * (long)this.numValues_;
         }
      }

      return values;
   }

   double[] getValuesAsOneDimension() {
      int count = this.getRetainedEntries();
      double[] values = new double[count * this.numValues_];
      int cap = this.getCurrentCapacity();
      if (count > 0) {
         long keyOffsetBytes = (long)this.keysOffset_;
         long valuesOffsetBytes = (long)this.valuesOffset_;
         int cnt = 0;

         for(int j = 0; j < cap; ++j) {
            if (this.mem_.getLong(keyOffsetBytes) != 0L) {
               this.mem_.getDoubleArray(valuesOffsetBytes, values, cnt++ * this.numValues_, this.numValues_);
            }

            keyOffsetBytes += 8L;
            valuesOffsetBytes += 8L * (long)this.numValues_;
         }

         assert cnt == count;
      }

      return values;
   }

   long[] getKeys() {
      int count = this.getRetainedEntries();
      long[] keys = new long[count];
      int cap = this.getCurrentCapacity();
      if (count > 0) {
         long keyOffsetBytes = (long)this.keysOffset_;
         int cnt = 0;

         for(int j = 0; j < cap; ++j) {
            long key;
            if ((key = this.mem_.getLong(keyOffsetBytes)) != 0L) {
               keys[cnt++] = key;
            }

            keyOffsetBytes += 8L;
         }

         assert cnt == count;
      }

      return keys;
   }

   public int getRetainedEntries() {
      return this.mem_.getInt(24L);
   }

   public int getNominalEntries() {
      return 1 << this.mem_.getByte(16L);
   }

   public ResizeFactor getResizeFactor() {
      return ResizeFactor.getRF(this.mem_.getByte(18L));
   }

   public float getSamplingProbability() {
      return this.mem_.getFloat(20L);
   }

   public byte[] toByteArray() {
      int sizeBytes = this.getSerializedSizeBytes();
      byte[] byteArray = new byte[sizeBytes];
      WritableMemory mem = WritableMemory.writableWrap(byteArray);
      this.serializeInto(mem);
      return byteArray;
   }

   public ArrayOfDoublesSketchIterator iterator() {
      return new DirectArrayOfDoublesSketchIterator(this.mem_, this.keysOffset_, this.getCurrentCapacity(), this.numValues_);
   }

   public boolean hasMemory() {
      return true;
   }

   WritableMemory getMemory() {
      return this.mem_;
   }

   int getSerializedSizeBytes() {
      return this.valuesOffset_ + 8 * this.numValues_ * this.getCurrentCapacity();
   }

   void serializeInto(WritableMemory mem) {
      this.mem_.copyTo(0L, mem, 0L, mem.getCapacity());
   }

   public void reset() {
      if (!this.isEmpty_) {
         this.isEmpty_ = true;
         this.mem_.setBits(4L, (byte)(1 << ArrayOfDoublesSketch.Flags.IS_EMPTY.ordinal()));
      }

      int lgResizeFactor = this.mem_.getByte(18L);
      float samplingProbability = this.mem_.getFloat(20L);
      int startingCapacity = Util.getStartingCapacity(this.getNominalEntries(), lgResizeFactor);
      this.thetaLong_ = (long)((double)Long.MAX_VALUE * (double)samplingProbability);
      this.mem_.putLong(8L, this.thetaLong_);
      this.mem_.putByte(17L, (byte)Integer.numberOfTrailingZeros(startingCapacity));
      this.mem_.putInt(24L, 0);
      this.keysOffset_ = 32;
      this.valuesOffset_ = this.keysOffset_ + 8 * startingCapacity;
      this.mem_.clear((long)this.keysOffset_, 8L * (long)startingCapacity);
      this.lgCurrentCapacity_ = Integer.numberOfTrailingZeros(startingCapacity);
      this.setRebuildThreshold();
   }

   protected long getKey(int index) {
      return this.mem_.getLong((long)this.keysOffset_ + 8L * (long)index);
   }

   protected void incrementCount() {
      int count = this.mem_.getInt(24L);
      if (count == 0) {
         this.mem_.setBits(4L, (byte)(1 << ArrayOfDoublesSketch.Flags.HAS_ENTRIES.ordinal()));
      }

      this.mem_.putInt(24L, count + 1);
   }

   protected final int getCurrentCapacity() {
      return 1 << this.mem_.getByte(17L);
   }

   protected void setThetaLong(long thetaLong) {
      this.thetaLong_ = thetaLong;
      this.mem_.putLong(8L, this.thetaLong_);
   }

   protected void setValues(int index, double[] values) {
      long offset = (long)this.valuesOffset_ + 8L * (long)this.numValues_ * (long)index;

      for(int i = 0; i < this.numValues_; ++i) {
         this.mem_.putDouble(offset, values[i]);
         offset += 8L;
      }

   }

   protected void updateValues(int index, double[] values) {
      long offset = (long)this.valuesOffset_ + 8L * (long)this.numValues_ * (long)index;

      for(int i = 0; i < this.numValues_; ++i) {
         this.mem_.putDouble(offset, this.mem_.getDouble(offset) + values[i]);
         offset += 8L;
      }

   }

   protected void setNotEmpty() {
      if (this.isEmpty_) {
         this.isEmpty_ = false;
         this.mem_.clearBits(4L, (byte)(1 << ArrayOfDoublesSketch.Flags.IS_EMPTY.ordinal()));
      }

   }

   protected boolean isInSamplingMode() {
      return (this.mem_.getByte(4L) & 1 << ArrayOfDoublesSketch.Flags.IS_IN_SAMPLING_MODE.ordinal()) != 0;
   }

   protected void rebuild(int newCapacity) {
      int numValues = this.getNumValues();
      checkIfEnoughMemory(this.mem_, newCapacity, numValues);
      int currCapacity = this.getCurrentCapacity();
      long[] keys = new long[currCapacity];
      double[] values = new double[currCapacity * numValues];
      this.mem_.getLongArray((long)this.keysOffset_, keys, 0, currCapacity);
      this.mem_.getDoubleArray((long)this.valuesOffset_, values, 0, currCapacity * numValues);
      this.mem_.clear((long)this.keysOffset_, 8L * (long)newCapacity + 8L * (long)newCapacity * (long)numValues);
      this.mem_.putInt(24L, 0);
      this.mem_.putByte(17L, (byte)Integer.numberOfTrailingZeros(newCapacity));
      this.valuesOffset_ = this.keysOffset_ + 8 * newCapacity;
      this.lgCurrentCapacity_ = Integer.numberOfTrailingZeros(newCapacity);

      for(int i = 0; i < keys.length; ++i) {
         if (keys[i] != 0L && keys[i] < this.thetaLong_) {
            this.insert(keys[i], Arrays.copyOfRange(values, i * numValues, (i + 1) * numValues));
         }
      }

      this.setRebuildThreshold();
   }

   protected int insertKey(long key) {
      return HashOperations.hashInsertOnlyMemory(this.mem_, this.lgCurrentCapacity_, key, 32);
   }

   protected int findOrInsertKey(long key) {
      return HashOperations.hashSearchOrInsertMemory(this.mem_, this.lgCurrentCapacity_, key, 32);
   }

   protected double[] find(long key) {
      int index = HashOperations.hashSearchMemory(this.mem_, this.lgCurrentCapacity_, key, 32);
      if (index == -1) {
         return null;
      } else {
         double[] array = new double[this.numValues_];
         this.mem_.getDoubleArray((long)this.valuesOffset_ + 8L * (long)this.numValues_ * (long)index, array, 0, this.numValues_);
         return array;
      }
   }

   private static void checkIfEnoughMemory(Memory mem, int numEntries, int numValues) {
      int sizeNeeded = 32 + (8 + 8 * numValues) * numEntries;
      if ((long)sizeNeeded > mem.getCapacity()) {
         throw new SketchesArgumentException("Not enough memory: need " + sizeNeeded + " bytes, got " + mem.getCapacity() + " bytes");
      }
   }
}
