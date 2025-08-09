package org.apache.arrow.vector.util;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class MapWithOrdinalImpl implements MapWithOrdinal {
   private final Map primary = new LinkedHashMap();
   private final IntObjectHashMap secondary = new IntObjectHashMap();
   private final Map delegate = new Map() {
      public boolean isEmpty() {
         return this.size() == 0;
      }

      public int size() {
         return MapWithOrdinalImpl.this.primary.size();
      }

      public boolean containsKey(Object key) {
         return MapWithOrdinalImpl.this.primary.containsKey(key);
      }

      public boolean containsValue(Object value) {
         return MapWithOrdinalImpl.this.primary.containsValue(value);
      }

      public Object get(Object key) {
         Map.Entry<Integer, V> pair = (Map.Entry)MapWithOrdinalImpl.this.primary.get(key);
         return pair != null ? pair.getValue() : null;
      }

      public Object put(Object key, Object value) {
         Map.Entry<Integer, V> oldPair = (Map.Entry)MapWithOrdinalImpl.this.primary.get(key);
         int ordinal = oldPair == null ? MapWithOrdinalImpl.this.primary.size() : (Integer)oldPair.getKey();
         MapWithOrdinalImpl.this.primary.put(key, new AbstractMap.SimpleImmutableEntry(ordinal, value));
         MapWithOrdinalImpl.this.secondary.put(ordinal, value);
         return oldPair == null ? null : oldPair.getValue();
      }

      public Object remove(Object key) {
         Map.Entry<Integer, V> oldPair = (Map.Entry)MapWithOrdinalImpl.this.primary.remove(key);
         if (oldPair != null) {
            int lastOrdinal = MapWithOrdinalImpl.this.secondary.size();
            V last = (V)MapWithOrdinalImpl.this.secondary.get(lastOrdinal);
            MapWithOrdinalImpl.this.secondary.put((Integer)oldPair.getKey(), last);
            MapWithOrdinalImpl.this.primary.put(key, new AbstractMap.SimpleImmutableEntry((Integer)oldPair.getKey(), last));
         }

         return oldPair == null ? null : oldPair.getValue();
      }

      public void putAll(Map m) {
         throw new UnsupportedOperationException();
      }

      public void clear() {
         MapWithOrdinalImpl.this.primary.clear();
         MapWithOrdinalImpl.this.secondary.clear();
      }

      public Set keySet() {
         return MapWithOrdinalImpl.this.primary.keySet();
      }

      public Collection values() {
         return MapWithOrdinalImpl.this.secondary.values();
      }

      public Set entrySet() {
         return (Set)MapWithOrdinalImpl.this.primary.entrySet().stream().map((entry) -> new AbstractMap.SimpleImmutableEntry(entry.getKey(), ((Map.Entry)entry.getValue()).getValue())).collect(Collectors.toSet());
      }
   };

   public Object getByOrdinal(int id) {
      return this.secondary.get(id);
   }

   public int getOrdinal(Object key) {
      Map.Entry<Integer, V> pair = (Map.Entry)this.primary.get(key);
      return pair != null ? (Integer)pair.getKey() : -1;
   }

   public int size() {
      return this.delegate.size();
   }

   public boolean isEmpty() {
      return this.delegate.isEmpty();
   }

   public Collection getAll(Object key) {
      if (this.delegate.containsKey(key)) {
         List<V> list = new ArrayList(1);
         list.add(this.get(key));
         return list;
      } else {
         return null;
      }
   }

   public Object get(Object key) {
      return this.delegate.get(key);
   }

   public boolean put(Object key, Object value, boolean overwrite) {
      return this.delegate.put(key, value) != null;
   }

   public Collection values() {
      return this.delegate.values();
   }

   public boolean remove(Object key, Object value) {
      return false;
   }

   public boolean containsKey(Object key) {
      return this.delegate.containsKey(key);
   }

   public boolean removeAll(Object key) {
      return this.delegate.remove(key) != null;
   }

   public void clear() {
      this.delegate.clear();
   }

   public Set keys() {
      return this.delegate.keySet();
   }
}
