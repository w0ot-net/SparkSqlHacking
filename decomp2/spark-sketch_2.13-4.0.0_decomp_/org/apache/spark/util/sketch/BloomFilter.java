package org.apache.spark.util.sketch;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public abstract class BloomFilter {
   static final double DEFAULT_FPP = 0.03;

   public abstract double expectedFpp();

   public abstract long bitSize();

   public abstract boolean put(Object var1);

   public abstract boolean putString(String var1);

   public abstract boolean putLong(long var1);

   public abstract boolean putBinary(byte[] var1);

   public abstract boolean isCompatible(BloomFilter var1);

   public abstract BloomFilter mergeInPlace(BloomFilter var1) throws IncompatibleMergeException;

   public abstract BloomFilter intersectInPlace(BloomFilter var1) throws IncompatibleMergeException;

   public abstract boolean mightContain(Object var1);

   public abstract boolean mightContainString(String var1);

   public abstract boolean mightContainLong(long var1);

   public abstract boolean mightContainBinary(byte[] var1);

   public abstract void writeTo(OutputStream var1) throws IOException;

   public long cardinality() {
      throw new UnsupportedOperationException("Not implemented");
   }

   public static BloomFilter readFrom(InputStream in) throws IOException {
      return BloomFilterImpl.readFrom(in);
   }

   public static BloomFilter readFrom(byte[] bytes) throws IOException {
      return BloomFilterImpl.readFrom(bytes);
   }

   private static int optimalNumOfHashFunctions(long n, long m) {
      return Math.max(1, (int)Math.round((double)m / (double)n * Math.log((double)2.0F)));
   }

   public static long optimalNumOfBits(long n, double p) {
      return (long)((double)(-n) * Math.log(p) / (Math.log((double)2.0F) * Math.log((double)2.0F)));
   }

   public static long optimalNumOfBits(long expectedNumItems, long maxNumItems, long maxNumOfBits) {
      double fpp = Math.min((double)expectedNumItems / ((double)maxNumItems / 0.03), 0.03);
      return Math.min(optimalNumOfBits(expectedNumItems, fpp), maxNumOfBits);
   }

   public static BloomFilter create(long expectedNumItems) {
      return create(expectedNumItems, 0.03);
   }

   public static BloomFilter create(long expectedNumItems, double fpp) {
      if (!(fpp <= (double)0.0F) && !(fpp >= (double)1.0F)) {
         return create(expectedNumItems, optimalNumOfBits(expectedNumItems, fpp));
      } else {
         throw new IllegalArgumentException("False positive probability must be within range (0.0, 1.0)");
      }
   }

   public static BloomFilter create(long expectedNumItems, long numBits) {
      if (expectedNumItems <= 0L) {
         throw new IllegalArgumentException("Expected insertions must be positive");
      } else if (numBits <= 0L) {
         throw new IllegalArgumentException("Number of bits must be positive");
      } else {
         return new BloomFilterImpl(optimalNumOfHashFunctions(expectedNumItems, numBits), numBits);
      }
   }

   public static enum Version {
      V1(1);

      private final int versionNumber;

      private Version(int versionNumber) {
         this.versionNumber = versionNumber;
      }

      int getVersionNumber() {
         return this.versionNumber;
      }

      // $FF: synthetic method
      private static Version[] $values() {
         return new Version[]{V1};
      }
   }
}
