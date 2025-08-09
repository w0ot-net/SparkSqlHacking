package org.apache.datasketches.theta;

class HeapCompactHashIterator implements HashIterator {
   private long[] cache;
   private int index;

   HeapCompactHashIterator(long[] cache) {
      this.cache = cache;
      this.index = -1;
   }

   public long get() {
      return this.cache[this.index];
   }

   public boolean next() {
      return ++this.index < this.cache.length;
   }
}
