package org.apache.datasketches.tuple.arrayofdoubles;

import java.nio.ByteBuffer;
import org.apache.datasketches.common.ResizeFactor;
import org.apache.datasketches.hash.MurmurHash3;
import org.apache.datasketches.memory.Memory;
import org.apache.datasketches.memory.WritableMemory;
import org.apache.datasketches.tuple.Util;

public abstract class ArrayOfDoublesUpdatableSketch extends ArrayOfDoublesSketch {
   final long seed_;

   ArrayOfDoublesUpdatableSketch(int numValues, long seed) {
      super(numValues);
      this.seed_ = seed;
   }

   public static ArrayOfDoublesUpdatableSketch heapify(Memory mem) {
      return heapify(mem, 9001L);
   }

   public static ArrayOfDoublesUpdatableSketch heapify(Memory mem, long seed) {
      return new HeapArrayOfDoublesQuickSelectSketch(mem, seed);
   }

   public static ArrayOfDoublesUpdatableSketch wrap(WritableMemory mem) {
      return wrap(mem, 9001L);
   }

   public static ArrayOfDoublesUpdatableSketch wrap(WritableMemory mem, long seed) {
      return new DirectArrayOfDoublesQuickSelectSketch(mem, seed);
   }

   public void update(long key, double[] values) {
      this.update(new long[]{key}, values);
   }

   public void update(double key, double[] values) {
      this.update(Util.doubleToLongArray(key), values);
   }

   public void update(String key, double[] values) {
      this.update(Util.stringToByteArray(key), values);
   }

   public void update(byte[] key, double[] values) {
      if (key != null && key.length != 0) {
         this.insertOrIgnore(MurmurHash3.hash(key, this.seed_)[0] >>> 1, values);
      }
   }

   public void update(ByteBuffer key, double[] values) {
      if (key != null && key.hasRemaining()) {
         this.insertOrIgnore(MurmurHash3.hash(key, this.seed_)[0] >>> 1, values);
      }
   }

   public void update(int[] key, double[] values) {
      if (key != null && key.length != 0) {
         this.insertOrIgnore(MurmurHash3.hash(key, this.seed_)[0] >>> 1, values);
      }
   }

   public void update(long[] key, double[] values) {
      if (key != null && key.length != 0) {
         this.insertOrIgnore(MurmurHash3.hash(key, this.seed_)[0] >>> 1, values);
      }
   }

   public abstract int getNominalEntries();

   public abstract ResizeFactor getResizeFactor();

   public abstract float getSamplingProbability();

   public abstract void trim();

   public abstract void reset();

   public ArrayOfDoublesCompactSketch compact() {
      return this.compact((WritableMemory)null);
   }

   public ArrayOfDoublesCompactSketch compact(WritableMemory dstMem) {
      return (ArrayOfDoublesCompactSketch)(dstMem == null ? new HeapArrayOfDoublesCompactSketch(this) : new DirectArrayOfDoublesCompactSketch(this, dstMem));
   }

   abstract int getCurrentCapacity();

   long getSeed() {
      return this.seed_;
   }

   short getSeedHash() {
      return Util.computeSeedHash(this.seed_);
   }

   abstract void insertOrIgnore(long var1, double[] var3);
}
