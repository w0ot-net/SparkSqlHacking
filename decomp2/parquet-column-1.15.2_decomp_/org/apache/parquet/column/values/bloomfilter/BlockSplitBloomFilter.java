package org.apache.parquet.column.values.bloomfilter;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.IntBuffer;
import java.util.Arrays;
import org.apache.parquet.Preconditions;
import org.apache.parquet.io.api.Binary;

public class BlockSplitBloomFilter implements BloomFilter {
   private static final int BYTES_PER_BLOCK = 32;
   private static final int BITS_PER_BLOCK = 256;
   public static final int LOWER_BOUND_BYTES = 32;
   public static final int UPPER_BOUND_BYTES = 134217728;
   private static final int BITS_SET_PER_BLOCK = 8;
   public static final int HEADER_SIZE = 16;
   public static final double DEFAULT_FPP = 0.01;
   private final BloomFilter.HashStrategy hashStrategy;
   private byte[] bitset;
   private IntBuffer intBuffer;
   private HashFunction hashFunction;
   private int maximumBytes;
   private int minimumBytes;
   private ByteBuffer cacheBuffer;
   private int[] mask;
   private static final int[] SALT = new int[]{1203114875, 1150766481, -2010862245, -1565054819, 1884591559, 770785867, -1627633337, 1550580529};

   public BlockSplitBloomFilter(int numBytes) {
      this(numBytes, 32, 134217728, BloomFilter.HashStrategy.XXH64);
   }

   public BlockSplitBloomFilter(int numBytes, int maximumBytes) {
      this(numBytes, 32, maximumBytes, BloomFilter.HashStrategy.XXH64);
   }

   private BlockSplitBloomFilter(int numBytes, BloomFilter.HashStrategy hashStrategy) {
      this(numBytes, 32, 134217728, hashStrategy);
   }

   public BlockSplitBloomFilter(int numBytes, int minimumBytes, int maximumBytes, BloomFilter.HashStrategy hashStrategy) {
      this.maximumBytes = 134217728;
      this.minimumBytes = 32;
      this.cacheBuffer = ByteBuffer.allocate(8);
      this.mask = new int[8];
      if (minimumBytes > maximumBytes) {
         throw new IllegalArgumentException("the minimum bytes should be less or equal than maximum bytes");
      } else {
         if (minimumBytes > 32 && minimumBytes < 134217728) {
            this.minimumBytes = minimumBytes;
         }

         if (maximumBytes > 32 && maximumBytes < 134217728) {
            this.maximumBytes = maximumBytes;
         }

         this.initBitset(numBytes);
         this.cacheBuffer.order(ByteOrder.LITTLE_ENDIAN);
         switch (hashStrategy) {
            case XXH64:
               this.hashStrategy = hashStrategy;
               this.hashFunction = new XxHash();
               return;
            default:
               throw new RuntimeException("Unsupported hash strategy");
         }
      }
   }

   public BlockSplitBloomFilter(byte[] bitset) {
      this(bitset, BloomFilter.HashStrategy.XXH64);
   }

   private BlockSplitBloomFilter(byte[] bitset, BloomFilter.HashStrategy hashStrategy) {
      this.maximumBytes = 134217728;
      this.minimumBytes = 32;
      this.cacheBuffer = ByteBuffer.allocate(8);
      this.mask = new int[8];
      if (bitset == null) {
         throw new RuntimeException("Given bitset is null");
      } else {
         this.cacheBuffer.order(ByteOrder.LITTLE_ENDIAN);
         this.bitset = bitset;
         this.intBuffer = ByteBuffer.wrap(bitset).order(ByteOrder.LITTLE_ENDIAN).asIntBuffer();
         switch (hashStrategy) {
            case XXH64:
               this.hashStrategy = hashStrategy;
               this.hashFunction = new XxHash();
               return;
            default:
               throw new RuntimeException("Unsupported hash strategy");
         }
      }
   }

   private void initBitset(int numBytes) {
      if (numBytes < this.minimumBytes) {
         numBytes = this.minimumBytes;
      }

      if ((numBytes & numBytes - 1) != 0) {
         numBytes = Integer.highestOneBit(numBytes) << 1;
      }

      if (numBytes > this.maximumBytes || numBytes < 0) {
         numBytes = this.maximumBytes;
      }

      this.bitset = new byte[numBytes];
      this.intBuffer = ByteBuffer.wrap(this.bitset).order(ByteOrder.LITTLE_ENDIAN).asIntBuffer();
   }

   public void writeTo(OutputStream out) throws IOException {
      out.write(this.bitset);
   }

   private int[] setMask(int key) {
      for(int i = 0; i < 8; ++i) {
         this.mask[i] = key * SALT[i];
      }

      for(int i = 0; i < 8; ++i) {
         this.mask[i] >>>= 27;
      }

      for(int i = 0; i < 8; ++i) {
         this.mask[i] = 1 << this.mask[i];
      }

      return this.mask;
   }

   public void insertHash(long hash) {
      long numBlocks = (long)(this.bitset.length / 32);
      long lowHash = hash >>> 32;
      int blockIndex = (int)(lowHash * numBlocks >> 32);
      int key = (int)hash;
      int[] mask = this.setMask(key);

      for(int i = 0; i < 8; ++i) {
         int value = this.intBuffer.get(blockIndex * 8 + i);
         value |= mask[i];
         this.intBuffer.put(blockIndex * 8 + i, value);
      }

   }

   public boolean findHash(long hash) {
      long numBlocks = (long)(this.bitset.length / 32);
      long lowHash = hash >>> 32;
      int blockIndex = (int)(lowHash * numBlocks >> 32);
      int key = (int)hash;
      int[] mask = this.setMask(key);

      for(int i = 0; i < 8; ++i) {
         if (0 == (this.intBuffer.get(blockIndex * 8 + i) & mask[i])) {
            return false;
         }
      }

      return true;
   }

   public static int optimalNumOfBits(long n, double p) {
      Preconditions.checkArgument(p > (double)0.0F && p < (double)1.0F, "FPP should be less than 1.0 and great than 0.0");
      double m = (double)(-8L * n) / Math.log((double)1.0F - Math.pow(p, (double)0.125F));
      int numBits = (int)m;
      if (numBits > 1073741824 || m < (double)0.0F) {
         numBits = 1073741824;
      }

      numBits = numBits + 256 - 1 & -257;
      if (numBits < 256) {
         numBits = 256;
      }

      return numBits;
   }

   public int getBitsetSize() {
      return this.bitset.length;
   }

   public long hash(Object value) {
      if (value instanceof Binary) {
         return this.hashFunction.hashBytes(((Binary)value).getBytes());
      } else {
         if (value instanceof Integer) {
            this.cacheBuffer.putInt((Integer)value);
         } else if (value instanceof Long) {
            this.cacheBuffer.putLong((Long)value);
         } else if (value instanceof Float) {
            this.cacheBuffer.putFloat((Float)value);
         } else {
            if (!(value instanceof Double)) {
               throw new RuntimeException("Parquet Bloom filter: Not supported type");
            }

            this.cacheBuffer.putDouble((Double)value);
         }

         return this.doHash();
      }
   }

   public boolean equals(Object object) {
      if (object == this) {
         return true;
      } else if (!(object instanceof BlockSplitBloomFilter)) {
         return false;
      } else {
         BlockSplitBloomFilter that = (BlockSplitBloomFilter)object;
         return Arrays.equals(this.bitset, that.bitset) && this.getAlgorithm() == that.getAlgorithm() && this.hashStrategy == that.hashStrategy;
      }
   }

   public BloomFilter.HashStrategy getHashStrategy() {
      return BloomFilter.HashStrategy.XXH64;
   }

   public BloomFilter.Algorithm getAlgorithm() {
      return BloomFilter.Algorithm.BLOCK;
   }

   public BloomFilter.Compression getCompression() {
      return BloomFilter.Compression.UNCOMPRESSED;
   }

   private long doHash() {
      this.cacheBuffer.flip();
      long hashResult = this.hashFunction.hashByteBuffer(this.cacheBuffer);
      this.cacheBuffer.clear();
      return hashResult;
   }

   public long hash(int value) {
      this.cacheBuffer.putInt(value);
      return this.doHash();
   }

   public long hash(long value) {
      this.cacheBuffer.putLong(value);
      return this.doHash();
   }

   public long hash(double value) {
      this.cacheBuffer.putDouble(value);
      return this.doHash();
   }

   public long hash(float value) {
      this.cacheBuffer.putFloat(value);
      return this.doHash();
   }

   public long hash(Binary value) {
      return this.hashFunction.hashBytes(value.getBytes());
   }

   public boolean canMergeFrom(BloomFilter otherBloomFilter) {
      return otherBloomFilter != null && this.getBitsetSize() == otherBloomFilter.getBitsetSize() && this.getAlgorithm() == otherBloomFilter.getAlgorithm() && this.getHashStrategy() == otherBloomFilter.getHashStrategy();
   }

   public void merge(BloomFilter otherBloomFilter) throws IOException {
      Preconditions.checkArgument(otherBloomFilter != null, "The BloomFilter to merge shouldn't be null");
      Preconditions.checkArgument(this.canMergeFrom(otherBloomFilter), "BloomFilters must have the same size of bitset, hashStrategy and algorithm.This BloomFilter's size of bitset is %s , hashStrategy is %s, algorithm is %s ,but the other BloomFilter's size of bitset is %s , hashStrategy is %s, algorithm is %s.", new Object[]{this.getBitsetSize(), this.getHashStrategy(), this.getAlgorithm(), otherBloomFilter.getBitsetSize(), otherBloomFilter.getHashStrategy(), otherBloomFilter.getAlgorithm()});
      ByteArrayOutputStream otherOutputStream = new ByteArrayOutputStream();
      otherBloomFilter.writeTo(otherOutputStream);
      byte[] otherBits = otherOutputStream.toByteArray();

      for(int i = 0; i < otherBits.length; ++i) {
         byte[] var10000 = this.bitset;
         var10000[i] |= otherBits[i];
      }

   }
}
