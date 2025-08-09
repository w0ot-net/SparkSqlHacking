package org.apache.hive.common.util;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

public class BloomFilter {
   public static final double DEFAULT_FPP = 0.05;
   protected BitSet bitSet;
   protected int numBits;
   protected int numHashFunctions;
   public static final int START_OF_SERIALIZED_LONGS = 5;

   public BloomFilter() {
   }

   public BloomFilter(long expectedEntries) {
      this(expectedEntries, 0.05);
   }

   static void checkArgument(boolean expression, String message) {
      if (!expression) {
         throw new IllegalArgumentException(message);
      }
   }

   public BloomFilter(long expectedEntries, double fpp) {
      checkArgument(expectedEntries > 0L, "expectedEntries should be > 0");
      checkArgument(fpp > (double)0.0F && fpp < (double)1.0F, "False positive probability should be > 0.0 & < 1.0");
      int nb = optimalNumOfBits(expectedEntries, fpp);
      this.numBits = nb + (64 - nb % 64);
      this.numHashFunctions = optimalNumOfHashFunctions(expectedEntries, (long)this.numBits);
      this.bitSet = new BitSet((long)this.numBits);
   }

   public BloomFilter(long[] bits, int numFuncs) {
      this.bitSet = new BitSet(bits);
      this.numBits = bits.length * 64;
      this.numHashFunctions = numFuncs;
   }

   static int optimalNumOfHashFunctions(long n, long m) {
      return Math.max(1, (int)Math.round((double)m / (double)n * Math.log((double)2.0F)));
   }

   static int optimalNumOfBits(long n, double p) {
      return (int)((double)(-n) * Math.log(p) / (Math.log((double)2.0F) * Math.log((double)2.0F)));
   }

   public void add(byte[] val) {
      if (val == null) {
         this.addBytes((byte[])null, -1, -1);
      } else {
         this.addBytes(val, 0, val.length);
      }

   }

   public void addBytes(byte[] val, int offset, int length) {
      long hash64 = val == null ? 2862933555777941757L : Murmur3.hash64(val, offset, length);
      this.addHash(hash64);
   }

   private void addHash(long hash64) {
      int hash1 = (int)hash64;
      int hash2 = (int)(hash64 >>> 32);

      for(int i = 1; i <= this.numHashFunctions; ++i) {
         int combinedHash = hash1 + (i + 1) * hash2;
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
         this.add(val.getBytes(StandardCharsets.UTF_8));
      }

   }

   public void addLong(long val) {
      this.addHash(this.getLongHash(val));
   }

   public void addDouble(double val) {
      this.addLong(Double.doubleToLongBits(val));
   }

   public boolean test(byte[] val) {
      return val == null ? this.testBytes((byte[])null, -1, -1) : this.testBytes(val, 0, val.length);
   }

   public boolean testBytes(byte[] val, int offset, int length) {
      long hash64 = val == null ? 2862933555777941757L : Murmur3.hash64(val, offset, length);
      return this.testHash(hash64);
   }

   private boolean testHash(long hash64) {
      int hash1 = (int)hash64;
      int hash2 = (int)(hash64 >>> 32);

      for(int i = 1; i <= this.numHashFunctions; ++i) {
         int combinedHash = hash1 + (i + 1) * hash2;
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
      return val == null ? this.test((byte[])null) : this.test(val.getBytes(StandardCharsets.UTF_8));
   }

   public boolean testLong(long val) {
      return this.testHash(this.getLongHash(val));
   }

   private long getLongHash(long key) {
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
         throw new IllegalArgumentException("BloomFilters are not compatible for merging. this - " + this.toString() + " that - " + that.toString());
      }
   }

   public void reset() {
      this.bitSet.clear();
   }

   public static void serialize(OutputStream out, BloomFilter bloomFilter) throws IOException {
      DataOutputStream dataOutputStream = new DataOutputStream(out);
      dataOutputStream.writeByte(bloomFilter.numHashFunctions);
      dataOutputStream.writeInt(bloomFilter.getBitSet().length);

      for(long value : bloomFilter.getBitSet()) {
         dataOutputStream.writeLong(value);
      }

   }

   public static BloomFilter deserialize(InputStream in) throws IOException {
      if (in == null) {
         throw new IOException("Input stream is null");
      } else {
         try {
            DataInputStream dataInputStream = new DataInputStream(in);
            int numHashFunc = dataInputStream.readByte();
            int numLongs = dataInputStream.readInt();
            long[] data = new long[numLongs];

            for(int i = 0; i < numLongs; ++i) {
               data[i] = dataInputStream.readLong();
            }

            return new BloomFilter(data, numHashFunc);
         } catch (RuntimeException e) {
            IOException io = new IOException("Unable to deserialize BloomFilter");
            io.initCause(e);
            throw io;
         }
      }
   }

   public static void mergeBloomFilterBytes(byte[] bf1Bytes, int bf1Start, int bf1Length, byte[] bf2Bytes, int bf2Start, int bf2Length) {
      if (bf1Length != bf2Length) {
         throw new IllegalArgumentException("bf1Length " + bf1Length + " does not match bf2Length " + bf2Length);
      } else {
         for(int idx = 0; idx < 5; ++idx) {
            if (bf1Bytes[bf1Start + idx] != bf2Bytes[bf2Start + idx]) {
               throw new IllegalArgumentException("bf1 NumHashFunctions/NumBits does not match bf2");
            }
         }

         for(int idx = 5; idx < bf1Length; ++idx) {
            bf1Bytes[bf1Start + idx] |= bf2Bytes[bf2Start + idx];
         }

      }
   }

   static class BitSet {
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
         assert this.data.length == array.data.length : "BitArrays must be of equal length (" + this.data.length + "!= " + array.data.length + ")";

         for(int i = 0; i < this.data.length; ++i) {
            long[] var10000 = this.data;
            var10000[i] |= array.data[i];
         }

      }

      public void clear() {
         Arrays.fill(this.data, 0L);
      }
   }
}
