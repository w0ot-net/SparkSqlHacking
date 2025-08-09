package org.apache.hadoop.hive.metastore.hbase;

import java.util.HashMap;
import java.util.Map;

class ObjectCache {
   private Map cache;
   private final int maxSize;
   private Counter hits;
   private Counter misses;
   private Counter overflows;

   ObjectCache(int max, Counter hits, Counter misses, Counter overflows) {
      this.maxSize = max;
      this.cache = new HashMap();
      this.hits = hits;
      this.misses = misses;
      this.overflows = overflows;
   }

   void put(Object key, Object value) {
      if (this.cache.size() < this.maxSize) {
         this.cache.put(key, value);
      } else {
         this.overflows.incr();
      }

   }

   Object get(Object key) {
      V val = (V)this.cache.get(key);
      if (val == null) {
         this.misses.incr();
      } else {
         this.hits.incr();
      }

      return val;
   }

   void remove(Object key) {
      this.cache.remove(key);
   }

   void flush() {
      this.cache.clear();
   }
}
