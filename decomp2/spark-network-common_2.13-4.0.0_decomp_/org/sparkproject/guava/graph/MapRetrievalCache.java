package org.sparkproject.guava.graph;

import java.util.Map;
import javax.annotation.CheckForNull;
import org.sparkproject.guava.base.Preconditions;

@ElementTypesAreNonnullByDefault
final class MapRetrievalCache extends MapIteratorCache {
   @CheckForNull
   private transient volatile CacheEntry cacheEntry1;
   @CheckForNull
   private transient volatile CacheEntry cacheEntry2;

   MapRetrievalCache(Map backingMap) {
      super(backingMap);
   }

   @CheckForNull
   Object get(Object key) {
      Preconditions.checkNotNull(key);
      V value = (V)this.getIfCached(key);
      if (value != null) {
         return value;
      } else {
         value = (V)this.getWithoutCaching(key);
         if (value != null) {
            this.addToCache(key, value);
         }

         return value;
      }
   }

   @CheckForNull
   Object getIfCached(@CheckForNull Object key) {
      V value = (V)super.getIfCached(key);
      if (value != null) {
         return value;
      } else {
         CacheEntry<K, V> entry = this.cacheEntry1;
         if (entry != null && entry.key == key) {
            return entry.value;
         } else {
            entry = this.cacheEntry2;
            if (entry != null && entry.key == key) {
               this.addToCache(entry);
               return entry.value;
            } else {
               return null;
            }
         }
      }
   }

   void clearCache() {
      super.clearCache();
      this.cacheEntry1 = null;
      this.cacheEntry2 = null;
   }

   private void addToCache(Object key, Object value) {
      this.addToCache(new CacheEntry(key, value));
   }

   private void addToCache(CacheEntry entry) {
      this.cacheEntry2 = this.cacheEntry1;
      this.cacheEntry1 = entry;
   }

   private static final class CacheEntry {
      final Object key;
      final Object value;

      CacheEntry(Object key, Object value) {
         this.key = key;
         this.value = value;
      }
   }
}
