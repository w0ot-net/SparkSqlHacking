package com.clearspring.analytics.stream.frequency;

import com.clearspring.analytics.stream.membership.Filter;

public class ConservativeAddSketch extends CountMinSketch {
   ConservativeAddSketch() {
   }

   public ConservativeAddSketch(int depth, int width, int seed) {
      super(depth, width, seed);
   }

   public ConservativeAddSketch(double epsOfTotalCount, double confidence, int seed) {
      super(epsOfTotalCount, confidence, seed);
   }

   ConservativeAddSketch(int depth, int width, long size, long[] hashA, long[][] table) {
      super(depth, width, size, hashA, table);
   }

   public void add(long item, long count) {
      if (count < 0L) {
         throw new IllegalArgumentException("Negative increments not implemented");
      } else {
         int[] buckets = new int[this.depth];

         for(int i = 0; i < this.depth; ++i) {
            buckets[i] = this.hash(item, i);
         }

         long min = this.table[0][buckets[0]];

         for(int i = 1; i < this.depth; ++i) {
            min = Math.min(min, this.table[i][buckets[i]]);
         }

         for(int i = 0; i < this.depth; ++i) {
            long newVal = Math.max(this.table[i][buckets[i]], min + count);
            this.table[i][buckets[i]] = newVal;
         }

         this.size += count;
      }
   }

   public void add(String item, long count) {
      if (count < 0L) {
         throw new IllegalArgumentException("Negative increments not implemented");
      } else {
         int[] buckets = Filter.getHashBuckets(item, this.depth, this.width);
         long min = this.table[0][buckets[0]];

         for(int i = 1; i < this.depth; ++i) {
            min = Math.min(min, this.table[i][buckets[i]]);
         }

         for(int i = 0; i < this.depth; ++i) {
            long newVal = Math.max(this.table[i][buckets[i]], min + count);
            this.table[i][buckets[i]] = newVal;
         }

         this.size += count;
      }
   }
}
