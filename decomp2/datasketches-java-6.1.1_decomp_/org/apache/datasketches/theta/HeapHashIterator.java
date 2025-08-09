package org.apache.datasketches.theta;

class HeapHashIterator implements HashIterator {
   private long[] cache;
   private long thetaLong;
   private int index;
   private long hash;

   HeapHashIterator(long[] cache, long thetaLong) {
      this.cache = cache;
      this.thetaLong = thetaLong;
      this.index = -1;
      this.hash = 0L;
   }

   public long get() {
      return this.hash;
   }

   public boolean next() {
      while(true) {
         if (++this.index < this.cache.length) {
            this.hash = this.cache[this.index];
            if (this.hash == 0L || this.hash >= this.thetaLong) {
               continue;
            }

            return true;
         }

         return false;
      }
   }
}
