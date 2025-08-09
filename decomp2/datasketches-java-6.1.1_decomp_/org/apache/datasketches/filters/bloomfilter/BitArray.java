package org.apache.datasketches.filters.bloomfilter;

import org.apache.datasketches.common.SketchesArgumentException;
import org.apache.datasketches.common.Util;
import org.apache.datasketches.memory.Buffer;
import org.apache.datasketches.memory.Memory;
import org.apache.datasketches.memory.WritableMemory;

abstract class BitArray {
   protected static final long MAX_BITS = 137438953408L;

   protected BitArray() {
   }

   static BitArray heapify(Buffer mem, boolean isEmpty) {
      return HeapBitArray.heapify(mem, isEmpty);
   }

   static BitArray wrap(Memory mem, boolean isEmpty) {
      return DirectBitArrayR.wrap(mem, isEmpty);
   }

   static BitArray writableWrap(WritableMemory wmem, boolean isEmpty) {
      return DirectBitArray.writableWrap(wmem, isEmpty);
   }

   boolean isEmpty() {
      return !this.isDirty() && this.getNumBitsSet() == 0L;
   }

   abstract boolean hasMemory();

   abstract boolean isDirect();

   abstract boolean isReadOnly();

   abstract boolean getBit(long var1);

   abstract boolean getAndSetBit(long var1);

   abstract void setBit(long var1);

   abstract long getNumBitsSet();

   abstract void reset();

   abstract long getCapacity();

   abstract int getArrayLength();

   abstract void union(BitArray var1);

   abstract void intersect(BitArray var1);

   abstract void invert();

   public String toString() {
      StringBuilder sb = new StringBuilder();

      for(int i = 0; i < this.getArrayLength(); ++i) {
         sb.append(i + ": ").append(printLong(this.getLong(i))).append(Util.LS);
      }

      return sb.toString();
   }

   long getSerializedSizeBytes() {
      return 8L * (this.isEmpty() ? 1L : 2L + (long)this.getArrayLength());
   }

   static long getSerializedSizeBytes(long numBits) {
      if (numBits <= 0L) {
         throw new SketchesArgumentException("Requested number of bits must be strictly positive");
      } else if (numBits > 137438953408L) {
         throw new SketchesArgumentException("Requested number of bits exceeds maximum allowed. Requested: " + numBits + ", maximum: " + 137438953408L);
      } else {
         int numLongs = (int)Math.ceil((double)numBits / (double)64.0F);
         return 8L * ((long)numLongs + 2L);
      }
   }

   protected abstract boolean isDirty();

   protected abstract long getLong(int var1);

   protected abstract void setLong(int var1, long var2);

   protected static String printLong(long val) {
      StringBuilder sb = new StringBuilder();

      for(int j = 0; j < 64; ++j) {
         sb.append((val & 1L << j) != 0L ? "1" : "0");
         if (j % 8 == 7) {
            sb.append(" ");
         }
      }

      return sb.toString();
   }
}
