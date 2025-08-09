package org.apache.curator.framework.recipes.atomic;

public class CachedAtomicLong {
   private final DistributedAtomicLong number;
   private final long cacheFactor;
   private AtomicValue currentValue = null;
   private int currentIndex = 0;

   public CachedAtomicLong(DistributedAtomicLong number, int cacheFactor) {
      this.number = number;
      this.cacheFactor = (long)cacheFactor;
   }

   public AtomicValue next() throws Exception {
      MutableAtomicValue<Long> result = new MutableAtomicValue(0L, 0L);
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
      result.preValue = (Long)this.currentValue.preValue() + (long)this.currentIndex;
      result.postValue = (Long)result.preValue + 1L;
      if ((long)(++this.currentIndex) >= this.cacheFactor) {
         this.currentValue = null;
      }

      return result;
   }
}
