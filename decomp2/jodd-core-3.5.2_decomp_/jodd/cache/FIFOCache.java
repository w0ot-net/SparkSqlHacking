package jodd.cache;

import java.util.Iterator;
import java.util.LinkedHashMap;

public class FIFOCache extends AbstractCacheMap {
   public FIFOCache(int cacheSize) {
      this(cacheSize, 0L);
   }

   public FIFOCache(int cacheSize, long timeout) {
      this.cacheSize = cacheSize;
      this.timeout = timeout;
      this.cacheMap = new LinkedHashMap(cacheSize + 1, 1.0F, false);
   }

   protected int pruneCache() {
      int count = 0;
      AbstractCacheMap<K, V>.CacheObject<K, V> first = null;
      Iterator<AbstractCacheMap<K, V>.CacheObject<K, V>> values = this.cacheMap.values().iterator();

      while(values.hasNext()) {
         AbstractCacheMap<K, V>.CacheObject<K, V> co = (AbstractCacheMap.CacheObject)values.next();
         if (co.isExpired()) {
            values.remove();
            ++count;
         }

         if (first == null) {
            first = co;
         }
      }

      if (this.isFull() && first != null) {
         this.cacheMap.remove(first.key);
         ++count;
      }

      return count;
   }
}
