package org.apache.parquet.crypto.keytools;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

class TwoLevelCacheWithExpiration {
   private final ConcurrentMap cache = new ConcurrentHashMap();
   private volatile long lastCacheCleanupTimestamp = System.currentTimeMillis();

   ConcurrentMap getOrCreateInternalCache(String accessToken, long cacheEntryLifetime) {
      ExpiringCacheEntry<ConcurrentMap<String, V>> externalCacheEntry = (ExpiringCacheEntry)this.cache.compute(accessToken, (token, cacheEntry) -> null != cacheEntry && !cacheEntry.isExpired() ? cacheEntry : new ExpiringCacheEntry(new ConcurrentHashMap(), cacheEntryLifetime));
      return (ConcurrentMap)externalCacheEntry.getCachedItem();
   }

   void removeCacheEntriesForToken(String accessToken) {
      this.cache.remove(accessToken);
   }

   void removeCacheEntriesForAllTokens() {
      this.cache.clear();
   }

   public void checkCacheForExpiredTokens(long cacheCleanupPeriod) {
      long now = System.currentTimeMillis();
      if (now > this.lastCacheCleanupTimestamp + cacheCleanupPeriod) {
         synchronized(this.cache) {
            if (now > this.lastCacheCleanupTimestamp + cacheCleanupPeriod) {
               this.removeExpiredEntriesFromCache();
               this.lastCacheCleanupTimestamp = now + cacheCleanupPeriod;
            }
         }
      }

   }

   public void removeExpiredEntriesFromCache() {
      this.cache.values().removeIf((cacheEntry) -> cacheEntry.isExpired());
   }

   public void remove(String accessToken) {
      this.cache.remove(accessToken);
   }

   public void clear() {
      this.cache.clear();
   }

   static class ExpiringCacheEntry {
      private final long expirationTimestamp;
      private final Object cachedItem;

      private ExpiringCacheEntry(Object cachedItem, long expirationIntervalMillis) {
         this.expirationTimestamp = System.currentTimeMillis() + expirationIntervalMillis;
         this.cachedItem = cachedItem;
      }

      private boolean isExpired() {
         long now = System.currentTimeMillis();
         return now > this.expirationTimestamp;
      }

      private Object getCachedItem() {
         return this.cachedItem;
      }
   }
}
