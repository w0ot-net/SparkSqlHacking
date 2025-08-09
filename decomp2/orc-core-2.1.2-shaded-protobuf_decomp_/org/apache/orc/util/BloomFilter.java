package org.apache.orc.util;

import java.nio.charset.Charset;
import java.util.Arrays;

public class BloomFilter {
   public static final double DEFAULT_FPP = 0.05;
   private final BitSet bitSet;
   private final int numBits;
   private final int numHashFunctions;

   static void checkArgument(boolean expression, String message) {
      if (!expression) {
         throw new IllegalArgumentException(message);
      }
   }

   public BloomFilter(long expectedEntries) {
      this(expectedEntries, 0.05);
   }

   public BloomFilter(long expectedEntries, double fpp) {
      expectedEntries = Math.max(expectedEntries, 1L);
      checkArgument(fpp > (double)0.0F && fpp < (double)1.0F, "False positive probability should be > 0.0 & < 1.0");
      int nb = optimalNumOfBits(expectedEntries, fpp);
      this.numBits = nb + (64 - nb % 64);
      this.numHashFunctions = optimalNumOfHashFunctions(expectedEntries, (long)this.numBits);
      this.bitSet = new BitSet((long)this.numBits);
   }

   public BloomFilter(long[] bits, int numFuncs) {
      this.bitSet = new BitSet(bits);
      this.numBits = (int)this.bitSet.bitSize();
      this.numHashFunctions = numFuncs;
   }

   static int optimalNumOfHashFunctions(long n, long m) {
      return Math.max(1, (int)Math.round((double)m / (double)n * Math.log((double)2.0F)));
   }

   static int optimalNumOfBits(long n, double p) {
      return (int)((double)(-n) * Math.log(p) / (Math.log((double)2.0F) * Math.log((double)2.0F)));
   }

   public boolean equals(Object other) {
      return other != null && other.getClass() == this.getClass() && this.numBits == ((BloomFilter)other).numBits && this.numHashFunctions == ((BloomFilter)other).numHashFunctions && this.bitSet.equals(((BloomFilter)other).bitSet);
   }

   public int hashCode() {
      return this.bitSet.hashCode() + this.numHashFunctions * 5;
   }

   public void add(byte[] val) {
      this.addBytes(val, 0, val == null ? 0 : val.length);
   }

   public void addBytes(byte[] val, int offset, int length) {
      long hash64 = val == null ? 2862933555777941757L : Murmur3.hash64(val, offset, length);
      this.addHash(hash64);
   }

   private void addHash(long hash64) {
      int hash1 = (int)hash64;
      int hash2 = (int)(hash64 >>> 32);

      for(int i = 1; i <= this.numHashFunctions; ++i) {
         int combinedHash = hash1 + i * hash2;
         if (combinedHash < 0) {
            combinedHash = ~combinedHash;
         }

         int pos = combinedHash % this.numBits;
         this.bitSet.set(pos);
      }

   }

   public void addString(String val) {
      if (val == null) {
         this.add((byte[])null);
      } else {
         this.add(val.getBytes(Charset.defaultCharset()));
      }

   }

   public void addLong(long val) {
      this.addHash(getLongHash(val));
   }

   public void addDouble(double val) {
      this.addLong(Double.doubleToLongBits(val));
   }

   public boolean test(byte[] val) {
      return this.testBytes(val, 0, val == null ? 0 : val.length);
   }

   public boolean testBytes(byte[] val, int offset, int length) {
      long hash64 = val == null ? 2862933555777941757L : Murmur3.hash64(val, offset, length);
      return this.testHash(hash64);
   }

   private boolean testHash(long hash64) {
      int hash1 = (int)hash64;
      int hash2 = (int)(hash64 >>> 32);

      for(int i = 1; i <= this.numHashFunctions; ++i) {
         int combinedHash = hash1 + i * hash2;
         if (combinedHash < 0) {
            combinedHash = ~combinedHash;
         }

         int pos = combinedHash % this.numBits;
         if (!this.bitSet.get(pos)) {
            return false;
         }
      }

      return true;
   }

   public boolean testString(String val) {
      return val == null ? this.test((byte[])null) : this.test(val.getBytes(Charset.defaultCharset()));
   }

   public boolean testLong(long val) {
      return this.testHash(getLongHash(val));
   }

   static long getLongHash(long key) {
      key = ~key + (key << 21);
      key ^= key >> 24;
      key = key + (key << 3) + (key << 8);
      key ^= key >> 14;
      key = key + (key << 2) + (key << 4);
      key ^= key >> 28;
      key += key << 31;
      return key;
   }

   public boolean testDouble(double val) {
      return this.testLong(Double.doubleToLongBits(val));
   }

   public long sizeInBytes() {
      return (long)(this.getBitSize() / 8);
   }

   public int getBitSize() {
      return this.bitSet.getData().length * 64;
   }

   public int getNumHashFunctions() {
      return this.numHashFunctions;
   }

   public long[] getBitSet() {
      return this.bitSet.getData();
   }

   public String toString() {
      return "m: " + this.numBits + " k: " + this.numHashFunctions;
   }

   public void merge(BloomFilter that) {
      if (this != that && this.numBits == that.numBits && this.numHashFunctions == that.numHashFunctions) {
         this.bitSet.putAll(that.bitSet);
      } else {
         String var10002 = String.valueOf(this);
         throw new IllegalArgumentException("BloomFilters are not compatible for merging. this - " + var10002 + " that - " + String.valueOf(that));
      }
   }

   public void reset() {
      this.bitSet.clear();
   }

   boolean testBitSetPos(int pos) {
      return this.bitSet.get(pos);
   }

   public static class BitSet {
      private final long[] data;

      public BitSet(long bits) {
         this(new long[(int)Math.ceil((double)bits / (double)64.0F)]);
      }

      public BitSet(long[] data) {
         assert data.length > 0 : "data length is zero!";

         this.data = data;
      }

      public void set(int index) {
         long[] var10000 = this.data;
         var10000[index >>> 6] |= 1L << index;
      }

      public boolean get(int index) {
         return (this.data[index >>> 6] & 1L << index) != 0L;
      }

      public long bitSize() {
         return (long)this.data.length * 64L;
      }

      public long[] getData() {
         return this.data;
      }

      public void putAll(BitSet array) {
         if (!$assertionsDisabled && this.data.length != array.data.length) {
            int var10002 = this.data.length;
            throw new AssertionError("BitArrays must be of equal length (" + var10002 + "!= " + array.data.length + ")");
         } else {
            for(int i = 0; i < this.data.length; ++i) {
               long[] var10000 = this.data;
               var10000[i] |= array.data[i];
            }

         }
      }

      public void clear() {
         Arrays.fill(this.data, 0L);
      }

      public boolean equals(Object other) {
         return other != null && other.getClass() == this.getClass() && Arrays.equals(this.data, ((BitSet)other).data);
      }

      public int hashCode() {
         return Arrays.hashCode(this.data);
      }
   }
}
