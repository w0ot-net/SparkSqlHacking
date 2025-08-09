package org.apache.spark.util.sketch;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.Arrays;

final class BitArray {
   private final long[] data;
   private long bitCount;

   static int numWords(long numBits) {
      if (numBits <= 0L) {
         throw new IllegalArgumentException("numBits must be positive, but got " + numBits);
      } else {
         long numWords = (long)Math.ceil((double)numBits / (double)64.0F);
         if (numWords > 2147483647L) {
            throw new IllegalArgumentException("Can't allocate enough space for " + numBits + " bits");
         } else {
            return (int)numWords;
         }
      }
   }

   BitArray(long numBits) {
      this(new long[numWords(numBits)]);
   }

   private BitArray(long[] data) {
      this.data = data;
      long bitCount = 0L;

      for(long word : data) {
         bitCount += (long)Long.bitCount(word);
      }

      this.bitCount = bitCount;
   }

   boolean set(long index) {
      if (!this.get(index)) {
         long[] var10000 = this.data;
         var10000[(int)(index >>> 6)] |= 1L << (int)index;
         ++this.bitCount;
         return true;
      } else {
         return false;
      }
   }

   boolean get(long index) {
      return (this.data[(int)(index >>> 6)] & 1L << (int)index) != 0L;
   }

   long bitSize() {
      return (long)this.data.length * 64L;
   }

   long cardinality() {
      return this.bitCount;
   }

   void putAll(BitArray array) {
      assert this.data.length == array.data.length : "BitArrays must be of equal length when merging";

      long bitCount = 0L;

      for(int i = 0; i < this.data.length; ++i) {
         long[] var10000 = this.data;
         var10000[i] |= array.data[i];
         bitCount += (long)Long.bitCount(this.data[i]);
      }

      this.bitCount = bitCount;
   }

   void and(BitArray array) {
      assert this.data.length == array.data.length : "BitArrays must be of equal length when merging";

      long bitCount = 0L;

      for(int i = 0; i < this.data.length; ++i) {
         long[] var10000 = this.data;
         var10000[i] &= array.data[i];
         bitCount += (long)Long.bitCount(this.data[i]);
      }

      this.bitCount = bitCount;
   }

   void writeTo(DataOutputStream out) throws IOException {
      out.writeInt(this.data.length);

      for(long datum : this.data) {
         out.writeLong(datum);
      }

   }

   static BitArray readFrom(DataInputStream in) throws IOException {
      int numWords = in.readInt();
      long[] data = new long[numWords];

      for(int i = 0; i < numWords; ++i) {
         data[i] = in.readLong();
      }

      return new BitArray(data);
   }

   public boolean equals(Object other) {
      if (this == other) {
         return true;
      } else if (other instanceof BitArray) {
         BitArray that = (BitArray)other;
         return Arrays.equals(this.data, that.data);
      } else {
         return false;
      }
   }

   public int hashCode() {
      return Arrays.hashCode(this.data);
   }
}
