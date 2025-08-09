package org.glassfish.hk2.utilities.cache;

import org.glassfish.hk2.utilities.cache.internal.WeakCARCacheImpl;

public class CacheUtilities {
   public static WeakCARCache createWeakCARCache(Computable computable, int maxSize, boolean isWeak) {
      return new WeakCARCacheImpl(computable, maxSize, isWeak);
   }
}
