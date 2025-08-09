package org.apache.arrow.vector.util;

import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MultiMapWithOrdinal implements MapWithOrdinal {
   private final Map keyToOrdinal = new LinkedHashMap();
   private final IntObjectHashMap ordinalToValue = new IntObjectHashMap();

   public Object getByOrdinal(int id) {
      return this.ordinalToValue.get(id);
   }

   public int getOrdinal(Object key) {
      Set<Integer> pair = this.getOrdinals(key);
      return !pair.isEmpty() ? (Integer)pair.iterator().next() : -1;
   }

   private Set getOrdinals(Object key) {
      return (Set)this.keyToOrdinal.getOrDefault(key, new HashSet());
   }

   public int size() {
      return this.ordinalToValue.size();
   }

   public boolean isEmpty() {
      return this.ordinalToValue.isEmpty();
   }

   public Object get(Object key) {
      Set<Integer> ordinals = (Set)this.keyToOrdinal.get(key);
      if (ordinals == null) {
         return null;
      } else {
         Stream var10000 = ordinals.stream();
         IntObjectHashMap var10001 = this.ordinalToValue;
         Objects.requireNonNull(var10001);
         return ((List)var10000.map(var10001::get).collect(Collectors.toList())).get(0);
      }
   }

   public Collection getAll(Object key) {
      Set<Integer> ordinals = (Set)this.keyToOrdinal.get(key);
      if (ordinals == null) {
         return null;
      } else {
         Stream var10000 = ordinals.stream();
         IntObjectHashMap var10001 = this.ordinalToValue;
         Objects.requireNonNull(var10001);
         return (Collection)var10000.map(var10001::get).collect(Collectors.toList());
      }
   }

   public boolean put(Object key, Object value, boolean overwrite) {
      if (overwrite) {
         this.removeAll(key);
      }

      Set<Integer> ordinalSet = this.getOrdinals(key);
      int nextOrdinal = this.ordinalToValue.size();
      this.ordinalToValue.put(nextOrdinal, value);
      boolean changed = ordinalSet.add(nextOrdinal);
      this.keyToOrdinal.put(key, ordinalSet);
      return changed;
   }

   public Collection values() {
      return this.ordinalToValue.values();
   }

   public boolean containsKey(Object key) {
      return this.keyToOrdinal.containsKey(key);
   }

   public synchronized boolean remove(Object key, Object value) {
      Set<Integer> removalSet = this.getOrdinals(key);
      if (removalSet.isEmpty()) {
         return false;
      } else {
         Stream var10000 = removalSet.stream();
         IntObjectHashMap var10001 = this.ordinalToValue;
         Objects.requireNonNull(var10001);
         var10000 = var10000.map(var10001::get);
         Objects.requireNonNull(value);
         Optional<V> removeValue = var10000.filter(value::equals).findFirst();
         if (!removeValue.isPresent()) {
            return false;
         } else {
            int removalOrdinal = this.removeKv(removalSet, key, value);
            int lastOrdinal = this.ordinalToValue.size();
            if (lastOrdinal != removalOrdinal) {
               this.swapOrdinal(lastOrdinal, removalOrdinal);
            }

            return true;
         }
      }
   }

   private void swapOrdinal(int lastOrdinal, int removalOrdinal) {
      V swapOrdinalValue = (V)this.ordinalToValue.remove(lastOrdinal);
      this.ordinalToValue.put(removalOrdinal, swapOrdinalValue);
      K swapOrdinalKey = (K)this.keyToOrdinal.entrySet().stream().filter((kv) -> ((Set)kv.getValue()).stream().anyMatch((o) -> o == lastOrdinal)).map(Map.Entry::getKey).findFirst().orElseThrow(() -> new IllegalStateException("MultimapWithOrdinal in bad state"));
      this.ordinalToValue.put(removalOrdinal, swapOrdinalValue);
      Set<Integer> swapSet = this.getOrdinals(swapOrdinalKey);
      swapSet.remove(lastOrdinal);
      swapSet.add(removalOrdinal);
      this.keyToOrdinal.put(swapOrdinalKey, swapSet);
   }

   private int removeKv(Set removalSet, Object key, Object value) {
      Integer removalOrdinal = (Integer)removalSet.stream().filter((i) -> this.ordinalToValue.get(i).equals(value)).findFirst().orElseThrow(() -> new IllegalStateException("MultimapWithOrdinal in bad state"));
      this.ordinalToValue.remove(removalOrdinal);
      removalSet.remove(removalOrdinal);
      if (removalSet.isEmpty()) {
         this.keyToOrdinal.remove(key);
      } else {
         this.keyToOrdinal.put(key, removalSet);
      }

      return removalOrdinal;
   }

   public synchronized boolean removeAll(Object key) {
      Collection<V> values = this.getAll(key);
      if (values == null) {
         return false;
      } else {
         for(Object v : values) {
            this.remove(key, v);
         }

         return true;
      }
   }

   public void clear() {
      this.ordinalToValue.clear();
      this.keyToOrdinal.clear();
   }

   public Set keys() {
      return this.keyToOrdinal.keySet();
   }
}
