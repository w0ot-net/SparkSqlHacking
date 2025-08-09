package jodd.cache;

import java.util.Iterator;
import java.util.LinkedHashMap;

public class LRUCache extends AbstractCacheMap {
   public LRUCache(int cacheSize) {
      this(cacheSize, 0L);
   }

   public LRUCache(int cacheSize, long timeout) {
      // $FF: Couldn't be decompiled
   }

   protected boolean removeEldestEntry(int currentSize) {
      if (this.cacheSize == 0) {
         return false;
      } else {
         return currentSize > this.cacheSize;
      }
   }

   protected int pruneCache() {
      if (!this.isPruneExpiredActive()) {
         return 0;
      } else {
         int count = 0;
         Iterator<AbstractCacheMap<K, V>.CacheObject<K, V>> values = this.cacheMap.values().iterator();

         while(values.hasNext()) {
            AbstractCacheMap<K, V>.CacheObject<K, V> co = (AbstractCacheMap.CacheObject)values.next();
            if (co.isExpired()) {
               values.remove();
               ++count;
            }
         }

         return count;
      }
   }
}
