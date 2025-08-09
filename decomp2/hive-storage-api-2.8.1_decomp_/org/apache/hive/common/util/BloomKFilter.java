package org.apache.hive.common.util;

import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import org.apache.hadoop.io.IOUtils;

public class BloomKFilter {
   public static final float DEFAULT_FPP = 0.05F;
   private static final int DEFAULT_BLOCK_SIZE = 8;
   private static final int DEFAULT_BLOCK_SIZE_BITS = (int)(Math.log((double)8.0F) / Math.log((double)2.0F));
   private static final int DEFAULT_BLOCK_OFFSET_MASK = 7;
   private static final int DEFAULT_BIT_OFFSET_MASK = 63;
   private final BitSet bitSet;
   private final long m;
   private final int k;
   private final int totalBlockCount;
   public static final int START_OF_SERIALIZED_LONGS = 5;

   private static void checkArgument(boolean expression, String message) {
      if (!expression) {
         throw new IllegalArgumentException(message);
      }
   }

   public BloomKFilter(long maxNumEntries) {
      checkArgument(maxNumEntries > 0L, "expectedEntries should be > 0");
      long numBits = optimalNumOfBits(maxNumEntries, (double)0.05F);
      this.k = optimalNumOfHashFunctions(maxNumEntries, numBits);
      long nLongs = (long)Math.ceil((double)numBits / (double)64.0F);
      long padLongs = 8L - nLongs % 8L;
      this.m = (nLongs + padLongs) * 64L;
      this.bitSet = new BitSet(this.m);
      checkArgument(this.bitSet.data.length % 8 == 0, "bitSet has to be block aligned");
      this.totalBlockCount = this.bitSet.data.length / 8;
   }

   public BloomKFilter(long[] bits, int numFuncs) {
      this.bitSet = new BitSet(bits);
      this.m = (long)(bits.length * 64);
      this.k = numFuncs;
      checkArgument(this.bitSet.data.length % 8 == 0, "bitSet has to be block aligned");
      this.totalBlockCount = this.bitSet.data.length / 8;
   }

   static int optimalNumOfHashFunctions(long n, long m) {
      return Math.max(1, (int)Math.round((double)m / (double)n * Math.log((double)2.0F)));
   }

   static long optimalNumOfBits(long n, double p) {
      return (long)((double)(-n) * Math.log(p) / (Math.log((double)2.0F) * Math.log((double)2.0F)));
   }

   public void add(byte[] val) {
      this.addBytes(val);
   }

   public void addBytes(byte[] val, int offset, int length) {
      long hash64 = val == null ? 2862933555777941757L : Murmur3.hash64(val, offset, length);
      this.addHash(hash64);
   }

   public void addBytes(byte[] val) {
      this.addBytes(val, 0, val.length);
   }

   private void addHash(long hash64) {
      int hash1 = (int)hash64;
      int hash2 = (int)(hash64 >>> 32);
      int firstHash = hash1 + hash2;
      if (firstHash < 0) {
         firstHash = ~firstHash;
      }

      int blockIdx = firstHash % this.totalBlockCount;
      int blockBaseOffset = blockIdx << DEFAULT_BLOCK_SIZE_BITS;

      for(int i = 1; i <= this.k; ++i) {
         int combinedHash = hash1 + (i + 1) * hash2;
         if (combinedHash < 0) {
            combinedHash = ~combinedHash;
         }

         int absOffset = blockBaseOffset + (combinedHash & 7);
         int bitPos = combinedHash >>> DEFAULT_BLOCK_SIZE_BITS & 63;
         long[] var10000 = this.bitSet.data;
         var10000[absOffset] |= 1L << bitPos;
      }

   }

   public void addString(String val) {
      this.addBytes(val.getBytes(StandardCharsets.UTF_8));
   }

   public void addByte(byte val) {
      this.addBytes(new byte[]{val});
   }

   public void addInt(int val) {
      this.addHash(Murmur3.hash64(val));
   }

   public void addLong(long val) {
      this.addHash(Murmur3.hash64(val));
   }

   public void addFloat(float val) {
      this.addInt(Float.floatToIntBits(val));
   }

   public void addDouble(double val) {
      this.addLong(Double.doubleToLongBits(val));
   }

   public boolean test(byte[] val) {
      return this.testBytes(val);
   }

   public boolean testBytes(byte[] val) {
      return this.testBytes(val, 0, val.length);
   }

   public boolean testBytes(byte[] val, int offset, int length) {
      long hash64 = val == null ? 2862933555777941757L : Murmur3.hash64(val, offset, length);
      return this.testHash(hash64);
   }

   private boolean testHash(long hash64) {
      int hash1 = (int)hash64;
      int hash2 = (int)(hash64 >>> 32);
      long[] bits = this.bitSet.data;
      int firstHash = hash1 + hash2;
      if (firstHash < 0) {
         firstHash = ~firstHash;
      }

      int blockIdx = firstHash % this.totalBlockCount;
      int blockBaseOffset = blockIdx << DEFAULT_BLOCK_SIZE_BITS;

      for(int i = 1; i <= this.k; ++i) {
         int combinedHash = hash1 + (i + 1) * hash2;
         if (combinedHash < 0) {
            combinedHash = ~combinedHash;
         }

         int wordOffset = combinedHash & 7;
         int absOffset = blockBaseOffset + wordOffset;
         int bitPos = combinedHash >>> DEFAULT_BLOCK_SIZE_BITS & 63;
         long bloomWord = bits[absOffset];
         if (0L == (bloomWord & 1L << bitPos)) {
            return false;
         }
      }

      return true;
   }

   public boolean testString(String val) {
      return this.testBytes(val.getBytes(StandardCharsets.UTF_8));
   }

   public boolean testByte(byte val) {
      return this.testBytes(new byte[]{val});
   }

   public boolean testInt(int val) {
      return this.testHash(Murmur3.hash64(val));
   }

   public boolean testLong(long val) {
      return this.testHash(Murmur3.hash64(val));
   }

   public boolean testFloat(float val) {
      return this.testInt(Float.floatToIntBits(val));
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
      return this.k;
   }

   public long getNumBits() {
      return this.m;
   }

   public long[] getBitSet() {
      return this.bitSet.getData();
   }

   public String toString() {
      return "m: " + this.m + " k: " + this.k;
   }

   public void merge(BloomKFilter that) {
      if (this != that && this.m == that.m && this.k == that.k) {
         this.bitSet.putAll(that.bitSet);
      } else {
         throw new IllegalArgumentException("BloomKFilters are not compatible for merging. this - " + this.toString() + " that - " + that.toString());
      }
   }

   public void reset() {
      this.bitSet.clear();
   }

   public static void serialize(OutputStream out, BloomKFilter bloomFilter) throws IOException {
      DataOutputStream dataOutputStream = new DataOutputStream(out);
      dataOutputStream.writeByte(bloomFilter.k);
      dataOutputStream.writeInt(bloomFilter.getBitSet().length);

      for(long value : bloomFilter.getBitSet()) {
         dataOutputStream.writeLong(value);
      }

   }

   public static BloomKFilter deserialize(InputStream in) throws IOException {
      if (in == null) {
         throw new IOException("Input stream is null");
      } else {
         try {
            DataInputStream dataInputStream = new DataInputStream(in);
            int numHashFunc = dataInputStream.readByte();
            int bitsetArrayLen = dataInputStream.readInt();
            long[] data = new long[bitsetArrayLen];

            for(int i = 0; i < bitsetArrayLen; ++i) {
               data[i] = dataInputStream.readLong();
            }

            return new BloomKFilter(data, numHashFunc);
         } catch (RuntimeException e) {
            throw new IOException("Unable to deserialize BloomKFilter", e);
         }
      }
   }

   public static void mergeBloomFilterBytes(byte[] bf1Bytes, int bf1Start, int bf1Length, byte[] bf2Bytes, int bf2Start, int bf2Length) {
      mergeBloomFilterBytes(bf1Bytes, bf1Start, bf1Length, bf2Bytes, bf2Start, bf2Length, 5, bf1Length);
   }

   public static void mergeBloomFilterBytes(byte[] bf1Bytes, int bf1Start, int bf1Length, byte[] bf2Bytes, int bf2Start, int bf2Length, int mergeStart, int mergeEnd) {
      if (bf1Length != bf2Length) {
         throw new IllegalArgumentException("bf1Length " + bf1Length + " does not match bf2Length " + bf2Length);
      } else {
         for(int idx = 0; idx < 5; ++idx) {
            if (bf1Bytes[bf1Start + idx] != bf2Bytes[bf2Start + idx]) {
               throw new IllegalArgumentException("bf1 NumHashFunctions/NumBits does not match bf2");
            }
         }

         for(int idx = mergeStart; idx < mergeEnd; ++idx) {
            bf1Bytes[bf1Start + idx] |= bf2Bytes[bf2Start + idx];
         }

      }
   }

   public static byte[] getInitialBytes(long expectedEntries) {
      ByteArrayOutputStream bytesOut = null;

      byte[] var4;
      try {
         bytesOut = new ByteArrayOutputStream();
         BloomKFilter bf = new BloomKFilter(expectedEntries);
         serialize(bytesOut, bf);
         var4 = bytesOut.toByteArray();
      } catch (Exception err) {
         throw new IllegalArgumentException("Error creating aggregation buffer", err);
      } finally {
         IOUtils.closeStream(bytesOut);
      }

      return var4;
   }

   public static class BitSet {
      private final long[] data;

      public BitSet(long bits) {
         this(new long[(int)Math.ceil((double)bits / (double)64.0F)]);
      }

      @SuppressFBWarnings(
         value = {"EI_EXPOSE_REP2"},
         justification = "Ref external obj for efficiency"
      )
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

      public int bitSize() {
         return this.data.length * 64;
      }

      @SuppressFBWarnings(
         value = {"EI_EXPOSE_REP"},
         justification = "Expose internal rep for efficiency"
      )
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
