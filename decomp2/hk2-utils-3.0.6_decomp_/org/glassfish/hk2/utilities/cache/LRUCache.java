package org.glassfish.hk2.utilities.cache;

import org.glassfish.hk2.utilities.cache.internal.LRUCacheCheapRead;

public abstract class LRUCache {
   public static LRUCache createCache(int maxCacheSize) {
      return new LRUCacheCheapRead(maxCacheSize);
   }

   public abstract Object get(Object var1);

   public abstract CacheEntry put(Object var1, Object var2);

   public abstract void releaseCache();

   public abstract int getMaxCacheSize();

   public abstract void releaseMatching(CacheKeyFilter var1);
}
