package org.sparkproject.guava.graph;

import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.util.AbstractSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import javax.annotation.CheckForNull;
import org.sparkproject.guava.base.Preconditions;
import org.sparkproject.guava.collect.UnmodifiableIterator;

@ElementTypesAreNonnullByDefault
class MapIteratorCache {
   private final Map backingMap;
   @CheckForNull
   private transient volatile Map.Entry cacheEntry;

   MapIteratorCache(Map backingMap) {
      this.backingMap = (Map)Preconditions.checkNotNull(backingMap);
   }

   @CheckForNull
   @CanIgnoreReturnValue
   final Object put(Object key, Object value) {
      Preconditions.checkNotNull(key);
      Preconditions.checkNotNull(value);
      this.clearCache();
      return this.backingMap.put(key, value);
   }

   @CheckForNull
   @CanIgnoreReturnValue
   final Object remove(Object key) {
      Preconditions.checkNotNull(key);
      this.clearCache();
      return this.backingMap.remove(key);
   }

   final void clear() {
      this.clearCache();
      this.backingMap.clear();
   }

   @CheckForNull
   Object get(Object key) {
      Preconditions.checkNotNull(key);
      V value = (V)this.getIfCached(key);
      return value == null ? this.getWithoutCaching(key) : value;
   }

   @CheckForNull
   final Object getWithoutCaching(Object key) {
      Preconditions.checkNotNull(key);
      return this.backingMap.get(key);
   }

   final boolean containsKey(@CheckForNull Object key) {
      return this.getIfCached(key) != null || this.backingMap.containsKey(key);
   }

   final Set unmodifiableKeySet() {
      return new AbstractSet() {
         public UnmodifiableIterator iterator() {
            final Iterator<Map.Entry<K, V>> entryIterator = MapIteratorCache.this.backingMap.entrySet().iterator();
            return new UnmodifiableIterator() {
               public boolean hasNext() {
                  return entryIterator.hasNext();
               }

               public Object next() {
                  Map.Entry<K, V> entry = (Map.Entry)entryIterator.next();
                  MapIteratorCache.this.cacheEntry = entry;
                  return entry.getKey();
               }
            };
         }

         public int size() {
            return MapIteratorCache.this.backingMap.size();
         }

         public boolean contains(@CheckForNull Object key) {
            return MapIteratorCache.this.containsKey(key);
         }
      };
   }

   @CheckForNull
   Object getIfCached(@CheckForNull Object key) {
      Map.Entry<K, V> entry = this.cacheEntry;
      return entry != null && entry.getKey() == key ? entry.getValue() : null;
   }

   void clearCache() {
      this.cacheEntry = null;
   }
}
