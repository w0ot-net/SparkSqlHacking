package org.glassfish.hk2.utilities.cache.internal;

import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.glassfish.hk2.utilities.cache.CacheEntry;
import org.glassfish.hk2.utilities.cache.CacheKeyFilter;
import org.glassfish.hk2.utilities.cache.LRUCache;

public class LRUCacheCheapRead extends LRUCache {
   final Object prunningLock = new Object();
   final int maxCacheSize;
   Map cache = new ConcurrentHashMap();
   private static final CacheEntryImplComparator COMPARATOR = new CacheEntryImplComparator();

   public LRUCacheCheapRead(int maxCacheSize) {
      this.maxCacheSize = maxCacheSize;
   }

   public Object get(Object key) {
      CacheEntryImpl<K, V> entry = (CacheEntryImpl)this.cache.get(key);
      return entry != null ? entry.hit().value : null;
   }

   public CacheEntry put(Object key, Object value) {
      CacheEntryImpl<K, V> entry = new CacheEntryImpl(key, value, this);
      synchronized(this.prunningLock) {
         if (this.cache.size() + 1 > this.maxCacheSize) {
            this.removeLRUItem();
         }

         this.cache.put(key, entry);
         return entry;
      }
   }

   public void releaseCache() {
      this.cache.clear();
   }

   public int getMaxCacheSize() {
      return this.maxCacheSize;
   }

   public void releaseMatching(CacheKeyFilter filter) {
      if (filter != null) {
         for(Map.Entry entry : (new HashMap(this.cache)).entrySet()) {
            if (filter.matches(entry.getKey())) {
               ((CacheEntryImpl)entry.getValue()).removeFromCache();
            }
         }

      }
   }

   private void removeLRUItem() {
      Collection<CacheEntryImpl<K, V>> values = this.cache.values();
      ((CacheEntryImpl)Collections.min(values, COMPARATOR)).removeFromCache();
   }

   private static class CacheEntryImplComparator implements Comparator {
      public int compare(CacheEntryImpl first, CacheEntryImpl second) {
         long diff = first.lastHit - second.lastHit;
         return diff > 0L ? 1 : (diff == 0L ? 0 : -1);
      }
   }

   private static class CacheEntryImpl implements CacheEntry {
      final Object key;
      final Object value;
      final LRUCacheCheapRead parent;
      long lastHit;

      public CacheEntryImpl(Object k, Object v, LRUCacheCheapRead cache) {
         this.parent = cache;
         this.key = k;
         this.value = v;
         this.lastHit = System.nanoTime();
      }

      public void removeFromCache() {
         this.parent.cache.remove(this.key);
      }

      public CacheEntryImpl hit() {
         this.lastHit = System.nanoTime();
         return this;
      }
   }
}
