package org.apache.derby.impl.services.cache;

import org.apache.derby.iapi.services.cache.CacheFactory;
import org.apache.derby.iapi.services.cache.CacheManager;
import org.apache.derby.iapi.services.cache.CacheableFactory;

public class ConcurrentCacheFactory implements CacheFactory {
   public CacheManager newCacheManager(CacheableFactory var1, String var2, int var3, int var4) {
      return new ConcurrentCache(var1, var2, var3, var4);
   }
}
