package org.apache.datasketches.tuple.arrayofdoubles;

import java.nio.ByteOrder;
import java.util.Arrays;
import org.apache.datasketches.common.Family;
import org.apache.datasketches.common.SketchesArgumentException;
import org.apache.datasketches.memory.Memory;
import org.apache.datasketches.memory.WritableMemory;
import org.apache.datasketches.tuple.SerializerDeserializer;
import org.apache.datasketches.tuple.Util;

final class HeapArrayOfDoublesCompactSketch extends ArrayOfDoublesCompactSketch {
   private final short seedHash_;
   private long[] keys_;
   private double[] values_;

   HeapArrayOfDoublesCompactSketch(ArrayOfDoublesUpdatableSketch sketch) {
      this(sketch, sketch.getThetaLong());
   }

   HeapArrayOfDoublesCompactSketch(ArrayOfDoublesUpdatableSketch sketch, long thetaLong) {
      super(sketch.getNumValues());
      this.isEmpty_ = sketch.isEmpty();
      this.thetaLong_ = Math.min(sketch.getThetaLong(), thetaLong);
      this.seedHash_ = Util.computeSeedHash(sketch.getSeed());
      int count = sketch.getRetainedEntries();
      if (count > 0) {
         this.keys_ = new long[count];
         this.values_ = new double[count * this.numValues_];
         ArrayOfDoublesSketchIterator it = sketch.iterator();
         int i = 0;

         while(it.next()) {
            long key = it.getKey();
            if (key < this.thetaLong_) {
               this.keys_[i] = key;
               System.arraycopy(it.getValues(), 0, this.values_, i * this.numValues_, this.numValues_);
               ++i;
            }
         }

         if (i < count) {
            if (i == 0) {
               this.keys_ = null;
               this.values_ = null;
            } else {
               this.keys_ = Arrays.copyOf(this.keys_, i);
               this.values_ = Arrays.copyOf(this.values_, i * this.numValues_);
            }
         }
      }

   }

   HeapArrayOfDoublesCompactSketch(long[] keys, double[] values, long thetaLong, boolean isEmpty, int numValues, short seedHash) {
      super(numValues);
      this.keys_ = keys;
      this.values_ = values;
      this.thetaLong_ = thetaLong;
      this.isEmpty_ = isEmpty;
      this.seedHash_ = seedHash;
   }

   HeapArrayOfDoublesCompactSketch(Memory mem) {
      this(mem, 9001L);
   }

   HeapArrayOfDoublesCompactSketch(Memory mem, long seed) {
      super(mem.getByte(5L));
      this.seedHash_ = mem.getShort(6L);
      SerializerDeserializer.validateFamily(mem.getByte(2L), mem.getByte(0L));
      SerializerDeserializer.validateType(mem.getByte(3L), SerializerDeserializer.SketchType.ArrayOfDoublesCompactSketch);
      byte version = mem.getByte(1L);
      if (version != 1) {
         throw new SketchesArgumentException("Serial version mismatch. Expected: 1, actual: " + version);
      } else {
         boolean isBigEndian = (mem.getByte(4L) & 1 << ArrayOfDoublesSketch.Flags.IS_BIG_ENDIAN.ordinal()) != 0;
         if (isBigEndian ^ ByteOrder.nativeOrder().equals(ByteOrder.BIG_ENDIAN)) {
            throw new SketchesArgumentException("Byte order mismatch");
         } else {
            Util.checkSeedHashes(this.seedHash_, Util.computeSeedHash(seed));
            this.isEmpty_ = (mem.getByte(4L) & 1 << ArrayOfDoublesSketch.Flags.IS_EMPTY.ordinal()) != 0;
            this.thetaLong_ = mem.getLong(8L);
            boolean hasEntries = (mem.getByte(4L) & 1 << ArrayOfDoublesSketch.Flags.HAS_ENTRIES.ordinal()) != 0;
            if (hasEntries) {
               int count = mem.getInt(16L);
               this.keys_ = new long[count];
               this.values_ = new double[count * this.numValues_];
               mem.getLongArray(24L, this.keys_, 0, count);
               mem.getDoubleArray(24L + 8L * (long)count, this.values_, 0, this.values_.length);
            }

         }
      }
   }

   public ArrayOfDoublesCompactSketch compact(WritableMemory dstMem) {
      if (dstMem == null) {
         return new HeapArrayOfDoublesCompactSketch((long[])this.keys_.clone(), (double[])this.values_.clone(), this.thetaLong_, this.isEmpty_, this.numValues_, this.seedHash_);
      } else {
         byte[] byteArr = this.toByteArray();
         dstMem.putByteArray(0L, byteArr, 0, byteArr.length);
         return new DirectArrayOfDoublesCompactSketch(dstMem);
      }
   }

   public int getRetainedEntries() {
      return this.keys_ == null ? 0 : this.keys_.length;
   }

   public byte[] toByteArray() {
      int count = this.getRetainedEntries();
      int sizeBytes = this.getCurrentBytes();
      byte[] bytes = new byte[sizeBytes];
      WritableMemory mem = WritableMemory.writableWrap(bytes);
      mem.putByte(0L, (byte)1);
      mem.putByte(1L, (byte)1);
      mem.putByte(2L, (byte)Family.TUPLE.getID());
      mem.putByte(3L, (byte)SerializerDeserializer.SketchType.ArrayOfDoublesCompactSketch.ordinal());
      boolean isBigEndian = ByteOrder.nativeOrder().equals(ByteOrder.BIG_ENDIAN);
      mem.putByte(4L, (byte)((isBigEndian ? 1 : 0) << ArrayOfDoublesSketch.Flags.IS_BIG_ENDIAN.ordinal() | (this.isEmpty() ? 1 : 0) << ArrayOfDoublesSketch.Flags.IS_EMPTY.ordinal() | (count > 0 ? 1 : 0) << ArrayOfDoublesSketch.Flags.HAS_ENTRIES.ordinal()));
      mem.putByte(5L, (byte)this.numValues_);
      mem.putShort(6L, this.seedHash_);
      mem.putLong(8L, this.thetaLong_);
      if (count > 0) {
         mem.putInt(16L, count);
         mem.putLongArray(24L, this.keys_, 0, count);
         mem.putDoubleArray(24L + 8L * (long)count, this.values_, 0, this.values_.length);
      }

      return bytes;
   }

   public double[][] getValues() {
      int count = this.getRetainedEntries();
      double[][] values = new double[count][];
      if (count > 0) {
         int i = 0;

         for(int j = 0; j < count; ++j) {
            values[i++] = Arrays.copyOfRange(this.values_, j * this.numValues_, (j + 1) * this.numValues_);
         }
      }

      return values;
   }

   double[] getValuesAsOneDimension() {
      return (double[])this.values_.clone();
   }

   long[] getKeys() {
      return (long[])this.keys_.clone();
   }

   public ArrayOfDoublesSketchIterator iterator() {
      return new HeapArrayOfDoublesSketchIterator(this.keys_, this.values_, this.numValues_);
   }

   short getSeedHash() {
      return this.seedHash_;
   }

   public boolean hasMemory() {
      return false;
   }

   Memory getMemory() {
      return null;
   }
}
