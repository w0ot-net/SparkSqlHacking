package org.glassfish.jersey.internal.util.collection;

import java.util.concurrent.TimeUnit;
import org.glassfish.jersey.internal.guava.CacheBuilder;

public abstract class LRU {
   public abstract Object getIfPresent(Object var1);

   public abstract void put(Object var1, Object var2);

   public static LRU create() {
      return LRU.LRUFactory.createLRU();
   }

   private static class LRUFactory {
      public static final int LRU_CACHE_SIZE = 128;
      public static final long TIMEOUT = 5000L;

      private static LRU createLRU() {
         final org.glassfish.jersey.internal.guava.Cache<K, V> CACHE = CacheBuilder.newBuilder().maximumSize(128L).expireAfterAccess(5000L, TimeUnit.MILLISECONDS).build();
         return new LRU() {
            public Object getIfPresent(Object key) {
               return CACHE.getIfPresent(key);
            }

            public void put(Object key, Object value) {
               CACHE.put(key, value);
            }
         };
      }
   }
}
