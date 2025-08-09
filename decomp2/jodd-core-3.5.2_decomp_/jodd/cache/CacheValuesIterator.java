package jodd.cache;

import java.util.Iterator;

public class CacheValuesIterator implements Iterator {
   private Iterator iterator;
   private AbstractCacheMap.CacheObject nextValue;

   CacheValuesIterator(AbstractCacheMap abstractCacheMap) {
      this.iterator = abstractCacheMap.cacheMap.values().iterator();
      this.nextValue();
   }

   private void nextValue() {
      while(true) {
         if (this.iterator.hasNext()) {
            this.nextValue = (AbstractCacheMap.CacheObject)this.iterator.next();
            if (this.nextValue.isExpired()) {
               continue;
            }

            return;
         }

         this.nextValue = null;
         return;
      }
   }

   public boolean hasNext() {
      return this.nextValue != null;
   }

   public Object next() {
      V cachedObject = (V)this.nextValue.cachedObject;
      this.nextValue();
      return cachedObject;
   }

   public void remove() {
      this.iterator.remove();
   }
}
