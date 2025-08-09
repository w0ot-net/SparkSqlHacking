package org.apache.curator.framework.recipes.atomic;

public class CachedAtomicInteger {
   private final DistributedAtomicInteger number;
   private final int cacheFactor;
   private AtomicValue currentValue = null;
   private int currentIndex = 0;

   public CachedAtomicInteger(DistributedAtomicInteger number, int cacheFactor) {
      this.number = number;
      this.cacheFactor = cacheFactor;
   }

   public AtomicValue next() throws Exception {
      MutableAtomicValue<Integer> result = new MutableAtomicValue(0, 0);
      if (this.currentValue == null) {
         this.currentValue = this.number.add(this.cacheFactor);
         if (!this.currentValue.succeeded()) {
            this.currentValue = null;
            result.succeeded = false;
            return result;
         }

         this.currentIndex = 0;
      }

      result.succeeded = true;
      result.preValue = (Integer)this.currentValue.preValue() + this.currentIndex;
      result.postValue = (Integer)result.preValue + 1;
      if (++this.currentIndex >= this.cacheFactor) {
         this.currentValue = null;
      }

      return result;
   }
}
