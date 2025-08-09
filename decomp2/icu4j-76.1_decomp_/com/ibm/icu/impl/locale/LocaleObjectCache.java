package com.ibm.icu.impl.locale;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;
import java.util.concurrent.ConcurrentHashMap;

public abstract class LocaleObjectCache {
   private ConcurrentHashMap _map;
   private ReferenceQueue _queue;

   public LocaleObjectCache() {
      this(16, 0.75F, 16);
   }

   public LocaleObjectCache(int initialCapacity, float loadFactor, int concurrencyLevel) {
      this._queue = new ReferenceQueue();
      this._map = new ConcurrentHashMap(initialCapacity, loadFactor, concurrencyLevel);
   }

   public Object get(Object key) {
      V value = (V)null;
      this.cleanStaleEntries();
      CacheEntry<K, V> entry = (CacheEntry)this._map.get(key);
      if (entry != null) {
         value = (V)entry.get();
      }

      if (value == null) {
         key = (K)this.normalizeKey(key);
         if (key == null) {
            return null;
         } else {
            entry = (CacheEntry)this._map.get(key);
            if (entry != null) {
               value = (V)entry.get();
            }

            if (value != null) {
               return value;
            } else {
               V newVal = (V)this.createObject(key);
               if (newVal == null) {
                  return null;
               } else {
                  CacheEntry<K, V> newEntry = new CacheEntry(key, newVal, this._queue);
                  this._map.put(key, newEntry);
                  this.cleanStaleEntries();
                  return newVal;
               }
            }
         }
      } else {
         return value;
      }
   }

   private void cleanStaleEntries() {
      CacheEntry<K, V> entry;
      while((entry = (CacheEntry)this._queue.poll()) != null) {
         this._map.remove(entry.getKey());
      }

   }

   protected abstract Object createObject(Object var1);

   protected Object normalizeKey(Object key) {
      return key;
   }

   private static class CacheEntry extends SoftReference {
      private Object _key;

      CacheEntry(Object key, Object value, ReferenceQueue queue) {
         super(value, queue);
         this._key = key;
      }

      Object getKey() {
         return this._key;
      }
   }
}
