package com.clearspring.analytics.stream.membership;

import java.io.IOException;
import java.util.BitSet;

public class BloomFilter extends Filter {
   static ICompactSerializer serializer_ = new BloomFilterSerializer();
   private BitSet filter_;

   public static ICompactSerializer serializer() {
      return serializer_;
   }

   public BloomFilter(int numElements, int bucketsPerElement) {
      this(BloomCalculations.computeBestK(bucketsPerElement), new BitSet(numElements * bucketsPerElement + 20));
   }

   public BloomFilter(int numElements, double maxFalsePosProbability) {
      BloomCalculations.BloomSpecification spec = BloomCalculations.computeBucketsAndK(maxFalsePosProbability);
      this.filter_ = new BitSet(numElements * spec.bucketsPerElement + 20);
      this.hashCount = spec.K;
   }

   BloomFilter(int hashes, BitSet filter) {
      this.hashCount = hashes;
      this.filter_ = filter;
   }

   public void clear() {
      this.filter_.clear();
   }

   public int buckets() {
      return this.filter_.size();
   }

   BitSet filter() {
      return this.filter_;
   }

   public boolean isPresent(String key) {
      for(int bucketIndex : this.getHashBuckets(key)) {
         if (!this.filter_.get(bucketIndex)) {
            return false;
         }
      }

      return true;
   }

   public boolean isPresent(byte[] key) {
      for(int bucketIndex : this.getHashBuckets(key)) {
         if (!this.filter_.get(bucketIndex)) {
            return false;
         }
      }

      return true;
   }

   public void add(String key) {
      for(int bucketIndex : this.getHashBuckets(key)) {
         this.filter_.set(bucketIndex);
      }

   }

   public void add(byte[] key) {
      for(int bucketIndex : this.getHashBuckets(key)) {
         this.filter_.set(bucketIndex);
      }

   }

   public String toString() {
      return this.filter_.toString();
   }

   ICompactSerializer tserializer() {
      return serializer_;
   }

   int emptyBuckets() {
      int n = 0;

      for(int i = 0; i < this.buckets(); ++i) {
         if (!this.filter_.get(i)) {
            ++n;
         }
      }

      return n;
   }

   public void addAll(BloomFilter other) {
      if (this.getHashCount() != other.getHashCount()) {
         throw new IllegalArgumentException("Cannot merge filters of different sizes");
      } else {
         this.filter().or(other.filter());
      }
   }

   public Filter merge(Filter... filters) {
      BloomFilter merged = new BloomFilter(this.getHashCount(), (BitSet)this.filter().clone());
      if (filters == null) {
         return merged;
      } else {
         for(Filter filter : filters) {
            if (!(filter instanceof BloomFilter)) {
               throw new IllegalArgumentException("Cannot merge filters of different class");
            }

            BloomFilter bf = (BloomFilter)filter;
            merged.addAll(bf);
         }

         return merged;
      }
   }

   public static BloomFilter alwaysMatchingBloomFilter() {
      BitSet set = new BitSet(64);
      set.set(0, 64);
      return new BloomFilter(1, set);
   }

   public static byte[] serialize(BloomFilter filter) {
      DataOutputBuffer out = new DataOutputBuffer();

      try {
         serializer().serialize(filter, out);
         out.close();
      } catch (IOException e) {
         e.printStackTrace();
      }

      return out.getData();
   }

   public static BloomFilter deserialize(byte[] bytes) {
      BloomFilter filter = null;
      DataInputBuffer in = new DataInputBuffer();
      in.reset(bytes, bytes.length);

      try {
         filter = (BloomFilter)serializer().deserialize(in);
         in.close();
      } catch (IOException e) {
         e.printStackTrace();
      }

      return filter;
   }
}
