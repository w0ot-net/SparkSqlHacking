package org.apache.parquet.column.values.bloomfilter;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import org.apache.parquet.Preconditions;
import org.apache.parquet.column.ColumnDescriptor;
import org.apache.parquet.io.api.Binary;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AdaptiveBlockSplitBloomFilter implements BloomFilter {
   private static final Logger LOG = LoggerFactory.getLogger(AdaptiveBlockSplitBloomFilter.class);
   private final List candidates;
   private BloomFilterCandidate largestCandidate;
   private long numDistinctHashValues;
   private boolean finalized;
   private static final int NDV_STEP = 500;
   private int maximumBytes;
   private int minimumBytes;
   private int minimumCandidateNdv;
   private final BloomFilter.HashStrategy hashStrategy;
   private ColumnDescriptor column;

   public AdaptiveBlockSplitBloomFilter(int maximumBytes, int numCandidates, double fpp, ColumnDescriptor column) {
      this(maximumBytes, BloomFilter.HashStrategy.XXH64, fpp, numCandidates, column);
   }

   public AdaptiveBlockSplitBloomFilter(int maximumBytes, BloomFilter.HashStrategy hashStrategy, double fpp, int numCandidates, ColumnDescriptor column) {
      this.candidates = new ArrayList();
      this.numDistinctHashValues = 0L;
      this.finalized = false;
      this.maximumBytes = 134217728;
      this.minimumBytes = 32;
      this.minimumCandidateNdv = 16;
      this.column = column;
      switch (hashStrategy) {
         case XXH64:
            this.hashStrategy = hashStrategy;
            this.initCandidates(maximumBytes, numCandidates, fpp);
            return;
         default:
            throw new RuntimeException("Unsupported hash strategy");
      }
   }

   private void initCandidates(int maxBytes, int numCandidates, double fpp) {
      int candidateByteSize = this.calculateBoundedPowerOfTwo(maxBytes);

      for(int i = 0; i < numCandidates; ++i) {
         int candidateExpectedNDV = this.expectedNDV(candidateByteSize, fpp);
         if (candidateExpectedNDV <= 0) {
            break;
         }

         BloomFilterCandidate candidate = new BloomFilterCandidate(candidateExpectedNDV, candidateByteSize, this.minimumBytes, this.maximumBytes, this.hashStrategy);
         this.candidates.add(candidate);
         candidateByteSize = this.calculateBoundedPowerOfTwo(candidateByteSize / 2);
      }

      if (this.candidates.isEmpty()) {
         this.candidates.add(new BloomFilterCandidate(this.minimumCandidateNdv, this.minimumBytes, this.minimumBytes, this.maximumBytes, this.hashStrategy));
      }

      this.largestCandidate = (BloomFilterCandidate)this.candidates.stream().max(BloomFilterCandidate::compareTo).get();
   }

   private int expectedNDV(int numBytes, double fpp) {
      int expectedNDV = 0;

      for(int optimalBytes = 0; optimalBytes < numBytes; optimalBytes = BlockSplitBloomFilter.optimalNumOfBits((long)expectedNDV, fpp) / 8) {
         expectedNDV += 500;
      }

      expectedNDV -= 500;
      if (expectedNDV <= 0) {
         expectedNDV = 0;
      }

      return expectedNDV;
   }

   private int calculateBoundedPowerOfTwo(int numBytes) {
      if (numBytes < this.minimumBytes) {
         numBytes = this.minimumBytes;
      }

      if ((numBytes & numBytes - 1) != 0) {
         numBytes = Integer.highestOneBit(numBytes);
      }

      numBytes = Math.min(numBytes, this.maximumBytes);
      numBytes = Math.max(numBytes, this.minimumBytes);
      return numBytes;
   }

   protected BloomFilterCandidate optimalCandidate() {
      return (BloomFilterCandidate)this.candidates.stream().min(BloomFilterCandidate::compareTo).get();
   }

   protected List getCandidates() {
      return this.candidates;
   }

   public void writeTo(OutputStream out) throws IOException {
      this.finalized = true;
      BloomFilterCandidate optimalBloomFilter = this.optimalCandidate();
      optimalBloomFilter.bloomFilter.writeTo(out);
      String columnName = this.column != null && this.column.getPath() != null ? Arrays.toString(this.column.getPath()) : "unknown";
      LOG.info("The number of distinct values in {} is approximately {}, the optimal bloom filter can accept {} distinct values, byte size is {}.", new Object[]{columnName, this.numDistinctHashValues, optimalBloomFilter.getExpectedNDV(), optimalBloomFilter.bloomFilter.getBitsetSize()});
   }

   public void insertHash(long hash) {
      Preconditions.checkArgument(!this.finalized, "Insertion has been mark as finalized, no more data is allowed!");
      if (!this.largestCandidate.bloomFilter.findHash(hash)) {
         ++this.numDistinctHashValues;
      }

      this.candidates.removeIf((candidate) -> (long)candidate.getExpectedNDV() < this.numDistinctHashValues && candidate != this.largestCandidate);
      this.candidates.forEach((candidate) -> candidate.getBloomFilter().insertHash(hash));
   }

   public int getBitsetSize() {
      return this.optimalCandidate().getBloomFilter().getBitsetSize();
   }

   public boolean findHash(long hash) {
      return this.largestCandidate.bloomFilter.findHash(hash);
   }

   public long hash(Object value) {
      return this.largestCandidate.bloomFilter.hash(value);
   }

   public BloomFilter.HashStrategy getHashStrategy() {
      return this.largestCandidate.bloomFilter.getHashStrategy();
   }

   public BloomFilter.Algorithm getAlgorithm() {
      return this.largestCandidate.bloomFilter.getAlgorithm();
   }

   public BloomFilter.Compression getCompression() {
      return this.largestCandidate.bloomFilter.getCompression();
   }

   public long hash(int value) {
      return this.largestCandidate.bloomFilter.hash(value);
   }

   public long hash(long value) {
      return this.largestCandidate.bloomFilter.hash(value);
   }

   public long hash(double value) {
      return this.largestCandidate.bloomFilter.hash(value);
   }

   public long hash(float value) {
      return this.largestCandidate.bloomFilter.hash(value);
   }

   public long hash(Binary value) {
      return this.largestCandidate.bloomFilter.hash(value);
   }

   protected class BloomFilterCandidate implements Comparable {
      private final BlockSplitBloomFilter bloomFilter;
      private final int expectedNDV;

      public BloomFilterCandidate(int expectedNDV, int candidateBytes, int minimumBytes, int maximumBytes, BloomFilter.HashStrategy hashStrategy) {
         this.bloomFilter = new BlockSplitBloomFilter(candidateBytes, minimumBytes, maximumBytes, hashStrategy);
         this.expectedNDV = expectedNDV;
      }

      public BlockSplitBloomFilter getBloomFilter() {
         return this.bloomFilter;
      }

      public int getExpectedNDV() {
         return this.expectedNDV;
      }

      public int compareTo(BloomFilterCandidate o) {
         return this.bloomFilter.getBitsetSize() - o.bloomFilter.getBitsetSize();
      }

      public boolean equals(Object o) {
         if (this == o) {
            return true;
         } else if (o != null && this.getClass() == o.getClass()) {
            BloomFilterCandidate that = (BloomFilterCandidate)o;
            return this.expectedNDV == that.expectedNDV && Objects.equals(this.bloomFilter, that.bloomFilter);
         } else {
            return false;
         }
      }

      public int hashCode() {
         return Objects.hash(new Object[]{this.bloomFilter, this.expectedNDV});
      }
   }
}
