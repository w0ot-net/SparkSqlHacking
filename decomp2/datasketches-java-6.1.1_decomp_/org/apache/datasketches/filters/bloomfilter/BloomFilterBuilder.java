package org.apache.datasketches.filters.bloomfilter;

import java.util.concurrent.ThreadLocalRandom;
import org.apache.datasketches.common.SketchesArgumentException;
import org.apache.datasketches.memory.WritableMemory;

public final class BloomFilterBuilder {
   public static short suggestNumHashes(long maxDistinctItems, long numFilterBits) {
      if (maxDistinctItems >= 1L && numFilterBits >= 1L) {
         return (short)Math.max(1, (int)Math.ceil((double)numFilterBits / (double)maxDistinctItems * Math.log((double)2.0F)));
      } else {
         throw new SketchesArgumentException("maxDistinctItems and numFilterBits must be strictly positive");
      }
   }

   public static short suggestNumHashes(double targetFalsePositiveProb) {
      if (!(targetFalsePositiveProb <= (double)0.0F) && !(targetFalsePositiveProb > (double)1.0F)) {
         return (short)((int)Math.ceil(-Math.log(targetFalsePositiveProb) / Math.log((double)2.0F)));
      } else {
         throw new SketchesArgumentException("targetFalsePositiveProb must be a valid probability and strictly greater than 0");
      }
   }

   public static long suggestNumFilterBits(long maxDistinctItems, double targetFalsePositiveProb) {
      validateAccuracyInputs(maxDistinctItems, targetFalsePositiveProb);
      return (long)Math.ceil((double)(-maxDistinctItems) * Math.log(targetFalsePositiveProb) / (Math.log((double)2.0F) * Math.log((double)2.0F)));
   }

   public static long getSerializedFilterSizeByAccuracy(long maxDistinctItems, double targetFalsePositiveProb) {
      validateAccuracyInputs(maxDistinctItems, targetFalsePositiveProb);
      return BloomFilter.getSerializedSize(suggestNumFilterBits(maxDistinctItems, targetFalsePositiveProb));
   }

   public static long getSerializedFilterSize(long numBits) {
      validateSizeInputs(numBits, 1);
      return BloomFilter.getSerializedSize(numBits);
   }

   public static BloomFilter createByAccuracy(long maxDistinctItems, double targetFalsePositiveProb) {
      validateAccuracyInputs(maxDistinctItems, targetFalsePositiveProb);
      return createByAccuracy(maxDistinctItems, targetFalsePositiveProb, ThreadLocalRandom.current().nextLong());
   }

   public static BloomFilter createByAccuracy(long maxDistinctItems, double targetFalsePositiveProb, long seed) {
      validateAccuracyInputs(maxDistinctItems, targetFalsePositiveProb);
      long numBits = suggestNumFilterBits(maxDistinctItems, targetFalsePositiveProb);
      short numHashes = suggestNumHashes(maxDistinctItems, numBits);
      return new BloomFilter(numBits, numHashes, seed);
   }

   public static BloomFilter createBySize(long numBits, int numHashes) {
      return createBySize(numBits, numHashes, ThreadLocalRandom.current().nextLong());
   }

   public static BloomFilter createBySize(long numBits, int numHashes, long seed) {
      validateSizeInputs(numBits, numHashes);
      return new BloomFilter(numBits, numHashes, seed);
   }

   public static BloomFilter initializeByAccuracy(long maxDistinctItems, double targetFalsePositiveProb, WritableMemory dstMem) {
      return initializeByAccuracy(maxDistinctItems, targetFalsePositiveProb, ThreadLocalRandom.current().nextLong(), dstMem);
   }

   public static BloomFilter initializeByAccuracy(long maxDistinctItems, double targetFalsePositiveProb, long seed, WritableMemory dstMem) {
      validateAccuracyInputs(maxDistinctItems, targetFalsePositiveProb);
      long numBits = suggestNumFilterBits(maxDistinctItems, targetFalsePositiveProb);
      short numHashes = suggestNumHashes(maxDistinctItems, numBits);
      if (dstMem.getCapacity() < BloomFilter.getSerializedSize(numBits)) {
         throw new SketchesArgumentException("Provided WritableMemory is insufficient to hold requested filter");
      } else {
         return new BloomFilter(numBits, numHashes, seed, dstMem);
      }
   }

   public static BloomFilter initializeBySize(long numBits, int numHashes, WritableMemory dstMem) {
      return initializeBySize(numBits, numHashes, ThreadLocalRandom.current().nextLong(), dstMem);
   }

   public static BloomFilter initializeBySize(long numBits, int numHashes, long seed, WritableMemory dstMem) {
      validateSizeInputs(numBits, numHashes);
      if (dstMem.getCapacity() < BloomFilter.getSerializedSize(numBits)) {
         throw new SketchesArgumentException("Provided WritableMemory is insufficient to hold requested filter");
      } else {
         return new BloomFilter(numBits, numHashes, seed, dstMem);
      }
   }

   private static void validateAccuracyInputs(long maxDistinctItems, double targetFalsePositiveProb) {
      if (maxDistinctItems <= 0L) {
         throw new SketchesArgumentException("maxDistinctItems must be strictly positive");
      } else if (targetFalsePositiveProb <= (double)0.0F || targetFalsePositiveProb > (double)1.0F) {
         throw new SketchesArgumentException("targetFalsePositiveProb must be a valid probability and strictly greater than 0");
      }
   }

   private static void validateSizeInputs(long numBits, int numHashes) {
      if (numBits < 0L) {
         throw new SketchesArgumentException("Size of BloomFilter must be strictly positive. Requested: " + numBits);
      } else if (numBits > BloomFilter.MAX_SIZE_BITS) {
         throw new SketchesArgumentException("Size of BloomFilter must be <= " + BloomFilter.MAX_SIZE_BITS + ". Requested: " + numBits);
      } else if (numHashes < 1) {
         throw new SketchesArgumentException("Must specify a strictly positive number of hash functions. Requested: " + numHashes);
      } else if (numHashes > 32767) {
         throw new SketchesArgumentException("Number of hashes cannot exceed 32767. Requested: " + numHashes);
      }
   }
}
