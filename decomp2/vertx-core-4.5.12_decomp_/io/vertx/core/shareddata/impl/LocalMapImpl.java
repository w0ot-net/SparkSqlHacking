package io.vertx.core.shareddata.impl;

import io.vertx.core.shareddata.LocalMap;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Function;

class LocalMapImpl implements LocalMap {
   private final ConcurrentMap maps;
   private final String name;
   private final ConcurrentMap map = new ConcurrentHashMap();

   LocalMapImpl(String name, ConcurrentMap maps) {
      this.name = name;
      this.maps = maps;
   }

   public Object get(Object key) {
      return Checker.copyIfRequired(this.map.get(key));
   }

   public Object put(Object key, Object value) {
      Checker.checkType(key);
      Checker.checkType(value);
      return this.map.put(key, value);
   }

   public Object remove(Object key) {
      return Checker.copyIfRequired(this.map.remove(key));
   }

   public void clear() {
      this.map.clear();
   }

   public int size() {
      return this.map.size();
   }

   public boolean isEmpty() {
      return this.map.isEmpty();
   }

   public Object putIfAbsent(Object key, Object value) {
      Checker.checkType(key);
      Checker.checkType(value);
      return Checker.copyIfRequired(this.map.putIfAbsent(key, value));
   }

   public boolean remove(Object key, Object value) {
      return this.map.remove(key, value);
   }

   public boolean replace(Object key, Object oldValue, Object newValue) {
      return this.map.replace(key, oldValue, newValue);
   }

   public boolean removeIfPresent(Object key, Object value) {
      return this.map.remove(key, value);
   }

   public boolean replaceIfPresent(Object key, Object oldValue, Object newValue) {
      Checker.checkType(key);
      Checker.checkType(oldValue);
      Checker.checkType(newValue);
      return this.map.replace(key, oldValue, newValue);
   }

   public Object replace(Object key, Object value) {
      Checker.checkType(key);
      Checker.checkType(value);
      return Checker.copyIfRequired(this.map.replace(key, value));
   }

   public void replaceAll(BiFunction function) {
      this.map.replaceAll((k, v) -> {
         Checker.checkType(k);
         Checker.checkType(v);
         V output = (V)function.apply(k, v);
         if (output != null) {
            Checker.checkType(output);
         }

         return output;
      });
   }

   public void close() {
      this.maps.remove(this.name);
   }

   public Set keySet() {
      Set<K> keys = new HashSet(this.map.size());

      for(Object k : this.map.keySet()) {
         keys.add(Checker.copyIfRequired(k));
      }

      return keys;
   }

   public Collection values() {
      List<V> values = new ArrayList(this.map.size());

      for(Object v : this.map.values()) {
         values.add(Checker.copyIfRequired(v));
      }

      return values;
   }

   private BiFunction typeChecked(BiFunction function) {
      return (k, v) -> {
         Checker.checkType(k);
         V output = (V)function.apply(k, v);
         if (output != null) {
            Checker.checkType(output);
         }

         return output;
      };
   }

   private Function typeChecked(Function function) {
      return (k) -> {
         Checker.checkType(k);
         V output = (V)function.apply(k);
         if (output != null) {
            Checker.checkType(output);
         }

         return output;
      };
   }

   public Object compute(Object key, BiFunction remappingFunction) {
      return this.map.compute(key, this.typeChecked(remappingFunction));
   }

   public Object computeIfAbsent(Object key, Function mappingFunction) {
      return this.map.computeIfAbsent(key, this.typeChecked(mappingFunction));
   }

   public Object computeIfPresent(Object key, BiFunction remappingFunction) {
      return this.map.computeIfPresent(key, this.typeChecked(remappingFunction));
   }

   public boolean containsKey(Object key) {
      return this.map.containsKey(key);
   }

   public boolean containsValue(Object value) {
      return this.map.containsValue(value);
   }

   public Set entrySet() {
      Set<Map.Entry<K, V>> entries = new HashSet(this.map.size());

      for(final Map.Entry entry : this.map.entrySet()) {
         entries.add(new Map.Entry() {
            public Object getKey() {
               return Checker.copyIfRequired(entry.getKey());
            }

            public Object getValue() {
               return Checker.copyIfRequired(entry.getValue());
            }

            public Object setValue(Object value) {
               throw new UnsupportedOperationException();
            }
         });
      }

      return entries;
   }

   public void forEach(BiConsumer action) {
      for(Map.Entry entry : this.entrySet()) {
         action.accept(entry.getKey(), entry.getValue());
      }

   }

   public Object getOrDefault(Object key, Object defaultValue) {
      return Checker.copyIfRequired(this.map.getOrDefault(key, defaultValue));
   }

   public Object merge(Object key, Object value, BiFunction remappingFunction) {
      Checker.checkType(key);
      Checker.checkType(value);
      return this.map.merge(key, value, (k, v) -> {
         V output = (V)remappingFunction.apply(k, v);
         if (output != null) {
            Checker.checkType(output);
         }

         return output;
      });
   }

   public void putAll(Map m) {
      for(Map.Entry entry : m.entrySet()) {
         this.put(entry.getKey(), entry.getValue());
      }

   }

   public String toString() {
      return this.map.toString();
   }
}
