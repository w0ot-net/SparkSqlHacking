package org.apache.zookeeper.server;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import org.apache.zookeeper.data.Stat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ResponseCache {
   private static final Logger LOG = LoggerFactory.getLogger(ResponseCache.class);
   public static final int DEFAULT_RESPONSE_CACHE_SIZE = 400;
   private final int cacheSize;
   private final Map cache;

   public ResponseCache(int cacheSize, String requestType) {
      this.cacheSize = cacheSize;
      this.cache = Collections.synchronizedMap(new LRUCache(cacheSize));
      LOG.info("{} response cache size is initialized with value {}.", requestType, cacheSize);
   }

   public int getCacheSize() {
      return this.cacheSize;
   }

   public void put(String path, byte[] data, Stat stat) {
      Entry entry = new Entry();
      entry.data = data;
      entry.stat = stat;
      this.cache.put(path, entry);
   }

   public byte[] get(String key, Stat stat) {
      Entry entry = (Entry)this.cache.get(key);
      if (entry == null) {
         return null;
      } else if (!stat.equals(entry.stat)) {
         this.cache.remove(key);
         return null;
      } else {
         return entry.data;
      }
   }

   public boolean isEnabled() {
      return this.cacheSize > 0;
   }

   private static class Entry {
      public Stat stat;
      public byte[] data;

      private Entry() {
      }
   }

   private static class LRUCache extends LinkedHashMap {
      private int cacheSize;

      LRUCache(int cacheSize) {
         super(cacheSize / 4);
         this.cacheSize = cacheSize;
      }

      protected boolean removeEldestEntry(Map.Entry eldest) {
         return this.size() >= this.cacheSize;
      }
   }
}
