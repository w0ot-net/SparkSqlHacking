package org.apache.datasketches.tuple.arrayofdoubles;

import java.nio.ByteOrder;
import org.apache.datasketches.common.Family;
import org.apache.datasketches.common.SketchesArgumentException;
import org.apache.datasketches.memory.Memory;
import org.apache.datasketches.memory.WritableMemory;
import org.apache.datasketches.tuple.SerializerDeserializer;
import org.apache.datasketches.tuple.Util;

final class DirectArrayOfDoublesCompactSketch extends ArrayOfDoublesCompactSketch {
   private Memory mem_;

   DirectArrayOfDoublesCompactSketch(ArrayOfDoublesUpdatableSketch sketch, WritableMemory dstMem) {
      this(sketch, sketch.getThetaLong(), dstMem);
   }

   DirectArrayOfDoublesCompactSketch(ArrayOfDoublesUpdatableSketch sketch, long thetaLong, WritableMemory dstMem) {
      super(sketch.getNumValues());
      checkIfEnoughMemory(dstMem, sketch.getRetainedEntries(), sketch.getNumValues());
      this.mem_ = dstMem;
      dstMem.putByte(0L, (byte)1);
      dstMem.putByte(1L, (byte)1);
      dstMem.putByte(2L, (byte)Family.TUPLE.getID());
      dstMem.putByte(3L, (byte)SerializerDeserializer.SketchType.ArrayOfDoublesCompactSketch.ordinal());
      boolean isBigEndian = ByteOrder.nativeOrder().equals(ByteOrder.BIG_ENDIAN);
      this.isEmpty_ = sketch.isEmpty();
      int count = sketch.getRetainedEntries();
      dstMem.putByte(4L, (byte)((isBigEndian ? 1 << ArrayOfDoublesSketch.Flags.IS_BIG_ENDIAN.ordinal() : 0) | (this.isEmpty_ ? 1 << ArrayOfDoublesSketch.Flags.IS_EMPTY.ordinal() : 0) | (count > 0 ? 1 << ArrayOfDoublesSketch.Flags.HAS_ENTRIES.ordinal() : 0)));
      dstMem.putByte(5L, (byte)this.numValues_);
      dstMem.putShort(6L, Util.computeSeedHash(sketch.getSeed()));
      this.thetaLong_ = Math.min(sketch.getThetaLong(), thetaLong);
      dstMem.putLong(8L, this.thetaLong_);
      if (count > 0) {
         int keyOffset = 24;
         int valuesOffset = keyOffset + 8 * sketch.getRetainedEntries();
         ArrayOfDoublesSketchIterator it = sketch.iterator();
         int actualCount = 0;

         while(it.next()) {
            if (it.getKey() < this.thetaLong_) {
               dstMem.putLong((long)keyOffset, it.getKey());
               dstMem.putDoubleArray((long)valuesOffset, it.getValues(), 0, this.numValues_);
               keyOffset += 8;
               valuesOffset += 8 * this.numValues_;
               ++actualCount;
            }
         }

         dstMem.putInt(16L, actualCount);
      }

   }

   DirectArrayOfDoublesCompactSketch(long[] keys, double[] values, long thetaLong, boolean isEmpty, int numValues, short seedHash, WritableMemory dstMem) {
      super(numValues);
      checkIfEnoughMemory(dstMem, values.length, numValues);
      this.mem_ = dstMem;
      dstMem.putByte(0L, (byte)1);
      dstMem.putByte(1L, (byte)1);
      dstMem.putByte(2L, (byte)Family.TUPLE.getID());
      dstMem.putByte(3L, (byte)SerializerDeserializer.SketchType.ArrayOfDoublesCompactSketch.ordinal());
      boolean isBigEndian = ByteOrder.nativeOrder().equals(ByteOrder.BIG_ENDIAN);
      this.isEmpty_ = isEmpty;
      int count = keys.length;
      dstMem.putByte(4L, (byte)((isBigEndian ? 1 << ArrayOfDoublesSketch.Flags.IS_BIG_ENDIAN.ordinal() : 0) | (this.isEmpty_ ? 1 << ArrayOfDoublesSketch.Flags.IS_EMPTY.ordinal() : 0) | (count > 0 ? 1 << ArrayOfDoublesSketch.Flags.HAS_ENTRIES.ordinal() : 0)));
      dstMem.putByte(5L, (byte)this.numValues_);
      dstMem.putShort(6L, seedHash);
      this.thetaLong_ = thetaLong;
      dstMem.putLong(8L, this.thetaLong_);
      if (count > 0) {
         dstMem.putInt(16L, count);
         dstMem.putLongArray(24L, keys, 0, count);
         dstMem.putDoubleArray(24L + 8L * (long)count, values, 0, values.length);
      }

   }

   DirectArrayOfDoublesCompactSketch(Memory mem) {
      super(mem.getByte(5L));
      this.mem_ = mem;
      SerializerDeserializer.validateFamily(mem.getByte(2L), mem.getByte(0L));
      SerializerDeserializer.validateType(this.mem_.getByte(3L), SerializerDeserializer.SketchType.ArrayOfDoublesCompactSketch);
      byte version = this.mem_.getByte(1L);
      if (version != 1) {
         throw new SketchesArgumentException("Serial version mismatch. Expected: 1, actual: " + version);
      } else {
         boolean isBigEndian = (mem.getByte(4L) & 1 << ArrayOfDoublesSketch.Flags.IS_BIG_ENDIAN.ordinal()) != 0;
         if (isBigEndian ^ ByteOrder.nativeOrder().equals(ByteOrder.BIG_ENDIAN)) {
            throw new SketchesArgumentException("Byte order mismatch");
         } else {
            this.isEmpty_ = (this.mem_.getByte(4L) & 1 << ArrayOfDoublesSketch.Flags.IS_EMPTY.ordinal()) != 0;
            this.thetaLong_ = this.mem_.getLong(8L);
         }
      }
   }

   DirectArrayOfDoublesCompactSketch(Memory mem, long seed) {
      super(mem.getByte(5L));
      this.mem_ = mem;
      SerializerDeserializer.validateFamily(mem.getByte(2L), mem.getByte(0L));
      SerializerDeserializer.validateType(this.mem_.getByte(3L), SerializerDeserializer.SketchType.ArrayOfDoublesCompactSketch);
      byte version = this.mem_.getByte(1L);
      if (version != 1) {
         throw new SketchesArgumentException("Serial version mismatch. Expected: 1, actual: " + version);
      } else {
         boolean isBigEndian = (mem.getByte(4L) & 1 << ArrayOfDoublesSketch.Flags.IS_BIG_ENDIAN.ordinal()) != 0;
         if (isBigEndian ^ ByteOrder.nativeOrder().equals(ByteOrder.BIG_ENDIAN)) {
            throw new SketchesArgumentException("Byte order mismatch");
         } else {
            Util.checkSeedHashes(mem.getShort(6L), Util.computeSeedHash(seed));
            this.isEmpty_ = (this.mem_.getByte(4L) & 1 << ArrayOfDoublesSketch.Flags.IS_EMPTY.ordinal()) != 0;
            this.thetaLong_ = this.mem_.getLong(8L);
         }
      }
   }

   public ArrayOfDoublesCompactSketch compact(WritableMemory dstMem) {
      if (dstMem == null) {
         return new HeapArrayOfDoublesCompactSketch(this.getKeys(), this.getValuesAsOneDimension(), this.thetaLong_, this.isEmpty_, this.numValues_, this.getSeedHash());
      } else {
         this.mem_.copyTo(0L, dstMem, 0L, this.mem_.getCapacity());
         return new DirectArrayOfDoublesCompactSketch(dstMem);
      }
   }

   public int getRetainedEntries() {
      boolean hasEntries = (this.mem_.getByte(4L) & 1 << ArrayOfDoublesSketch.Flags.HAS_ENTRIES.ordinal()) != 0;
      return hasEntries ? this.mem_.getInt(16L) : 0;
   }

   public double[][] getValues() {
      int count = this.getRetainedEntries();
      double[][] values = new double[count][];
      if (count > 0) {
         int valuesOffset = 24 + 8 * count;

         for(int i = 0; i < count; ++i) {
            double[] array = new double[this.numValues_];
            this.mem_.getDoubleArray((long)valuesOffset, array, 0, this.numValues_);
            values[i] = array;
            valuesOffset += 8 * this.numValues_;
         }
      }

      return values;
   }

   double[] getValuesAsOneDimension() {
      int count = this.getRetainedEntries();
      int numDoubles = count * this.numValues_;
      double[] values = new double[numDoubles];
      if (count > 0) {
         int valuesOffset = 24 + 8 * count;
         this.mem_.getDoubleArray((long)valuesOffset, values, 0, numDoubles);
      }

      return values;
   }

   long[] getKeys() {
      int count = this.getRetainedEntries();
      long[] keys = new long[count];
      if (count > 0) {
         for(int i = 0; i < count; ++i) {
            this.mem_.getLongArray(24L, keys, 0, count);
         }
      }

      return keys;
   }

   public byte[] toByteArray() {
      int sizeBytes = this.getCurrentBytes();
      byte[] byteArray = new byte[sizeBytes];
      WritableMemory mem = WritableMemory.writableWrap(byteArray);
      this.mem_.copyTo(0L, mem, 0L, (long)sizeBytes);
      return byteArray;
   }

   public ArrayOfDoublesSketchIterator iterator() {
      return new DirectArrayOfDoublesSketchIterator(this.mem_, 24, this.getRetainedEntries(), this.numValues_);
   }

   short getSeedHash() {
      return this.mem_.getShort(6L);
   }

   public boolean hasMemory() {
      return true;
   }

   Memory getMemory() {
      return this.mem_;
   }

   private static void checkIfEnoughMemory(Memory mem, int numEntries, int numValues) {
      int sizeNeeded = 24 + (8 + 8 * numValues) * numEntries;
      if ((long)sizeNeeded > mem.getCapacity()) {
         throw new SketchesArgumentException("Not enough memory: need " + sizeNeeded + " bytes, got " + mem.getCapacity() + " bytes");
      }
   }
}
