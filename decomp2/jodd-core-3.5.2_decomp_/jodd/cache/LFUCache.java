package jodd.cache;

import java.util.HashMap;
import java.util.Iterator;

public class LFUCache extends AbstractCacheMap {
   public LFUCache(int maxSize) {
      this(maxSize, 0L);
   }

   public LFUCache(int maxSize, long timeout) {
      this.cacheSize = maxSize;
      this.timeout = timeout;
      this.cacheMap = new HashMap(maxSize + 1);
   }

   protected int pruneCache() {
      int count = 0;
      AbstractCacheMap<K, V>.CacheObject<K, V> comin = null;
      Iterator<AbstractCacheMap<K, V>.CacheObject<K, V>> values = this.cacheMap.values().iterator();

      while(values.hasNext()) {
         AbstractCacheMap<K, V>.CacheObject<K, V> co = (AbstractCacheMap.CacheObject)values.next();
         if (co.isExpired()) {
            values.remove();
            this.onRemove(co.key, co.cachedObject);
            ++count;
         } else if (comin == null) {
            comin = co;
         } else if (co.accessCount < comin.accessCount) {
            comin = co;
         }
      }

      if (!this.isFull()) {
         return count;
      } else {
         if (comin != null) {
            long minAccessCount = comin.accessCount;
            values = this.cacheMap.values().iterator();

            while(values.hasNext()) {
               AbstractCacheMap<K, V>.CacheObject<K, V> co = (AbstractCacheMap.CacheObject)values.next();
               co.accessCount -= minAccessCount;
               if (co.accessCount <= 0L) {
                  values.remove();
                  this.onRemove(co.key, co.cachedObject);
                  ++count;
               }
            }
         }

         return count;
      }
   }

   protected void onRemove(Object key, Object cachedObject) {
   }
}
