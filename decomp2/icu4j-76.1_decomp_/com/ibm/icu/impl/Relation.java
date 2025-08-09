package com.ibm.icu.impl;

import com.ibm.icu.util.Freezable;
import java.lang.reflect.Constructor;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

public class Relation implements Freezable {
   private Map data;
   Constructor setCreator;
   Object[] setComparatorParam;
   volatile boolean frozen;

   public static Relation of(Map map, Class setCreator) {
      return new Relation(map, setCreator);
   }

   public static Relation of(Map map, Class setCreator, Comparator setComparator) {
      return new Relation(map, setCreator, setComparator);
   }

   public Relation(Map map, Class setCreator) {
      this(map, setCreator, (Comparator)null);
   }

   public Relation(Map map, Class setCreator, Comparator setComparator) {
      this.frozen = false;

      try {
         this.setComparatorParam = setComparator == null ? null : new Object[]{setComparator};
         if (setComparator == null) {
            this.setCreator = setCreator.getConstructor();
            this.setCreator.newInstance(this.setComparatorParam);
         } else {
            this.setCreator = setCreator.getConstructor(Comparator.class);
            this.setCreator.newInstance(this.setComparatorParam);
         }

         this.data = (Map)(map == null ? new HashMap() : map);
      } catch (Exception e) {
         throw (RuntimeException)(new IllegalArgumentException("Can't create new set")).initCause(e);
      }
   }

   public void clear() {
      this.data.clear();
   }

   public boolean containsKey(Object key) {
      return this.data.containsKey(key);
   }

   public boolean containsValue(Object value) {
      for(Set values : this.data.values()) {
         if (values.contains(value)) {
            return true;
         }
      }

      return false;
   }

   public final Set entrySet() {
      return this.keyValueSet();
   }

   public Set keyValuesSet() {
      return this.data.entrySet();
   }

   public Set keyValueSet() {
      Set<Map.Entry<K, V>> result = new LinkedHashSet();

      for(Object key : this.data.keySet()) {
         for(Object value : (Set)this.data.get(key)) {
            result.add(new SimpleEntry(key, value));
         }
      }

      return result;
   }

   public boolean equals(Object o) {
      if (o == null) {
         return false;
      } else {
         return o.getClass() != this.getClass() ? false : this.data.equals(((Relation)o).data);
      }
   }

   public Set getAll(Object key) {
      return (Set)this.data.get(key);
   }

   public Set get(Object key) {
      return (Set)this.data.get(key);
   }

   public int hashCode() {
      return this.data.hashCode();
   }

   public boolean isEmpty() {
      return this.data.isEmpty();
   }

   public Set keySet() {
      return this.data.keySet();
   }

   public Object put(Object key, Object value) {
      Set<V> set = (Set)this.data.get(key);
      if (set == null) {
         this.data.put(key, set = this.newSet());
      }

      set.add(value);
      return value;
   }

   public Object putAll(Object key, Collection values) {
      Set<V> set = (Set)this.data.get(key);
      if (set == null) {
         this.data.put(key, set = this.newSet());
      }

      set.addAll(values);
      return values.size() == 0 ? null : values.iterator().next();
   }

   public Object putAll(Collection keys, Object value) {
      V result = (V)null;

      for(Object key : keys) {
         result = (V)this.put(key, value);
      }

      return result;
   }

   private Set newSet() {
      try {
         return (Set)this.setCreator.newInstance(this.setComparatorParam);
      } catch (Exception e) {
         throw (RuntimeException)(new IllegalArgumentException("Can't create new set")).initCause(e);
      }
   }

   public void putAll(Map t) {
      for(Map.Entry entry : t.entrySet()) {
         this.put(entry.getKey(), entry.getValue());
      }

   }

   public void putAll(Relation t) {
      for(Object key : t.keySet()) {
         for(Object value : t.getAll(key)) {
            this.put(key, value);
         }
      }

   }

   public Set removeAll(Object key) {
      try {
         return (Set)this.data.remove(key);
      } catch (NullPointerException var3) {
         return null;
      }
   }

   public boolean remove(Object key, Object value) {
      try {
         Set<V> set = (Set)this.data.get(key);
         if (set == null) {
            return false;
         } else {
            boolean result = set.remove(value);
            if (set.size() == 0) {
               this.data.remove(key);
            }

            return result;
         }
      } catch (NullPointerException var5) {
         return false;
      }
   }

   public int size() {
      return this.data.size();
   }

   public Set values() {
      return (Set)this.values(new LinkedHashSet());
   }

   public Collection values(Collection result) {
      for(Map.Entry keyValue : this.data.entrySet()) {
         result.addAll((Collection)keyValue.getValue());
      }

      return result;
   }

   public String toString() {
      return this.data.toString();
   }

   public Relation addAllInverted(Relation source) {
      for(Object value : source.data.keySet()) {
         for(Object key : (Set)source.data.get(value)) {
            this.put(key, value);
         }
      }

      return this;
   }

   public Relation addAllInverted(Map source) {
      for(Map.Entry entry : source.entrySet()) {
         this.put(entry.getValue(), entry.getKey());
      }

      return this;
   }

   public boolean isFrozen() {
      return this.frozen;
   }

   public Relation freeze() {
      if (!this.frozen) {
         for(Object key : this.data.keySet()) {
            this.data.put(key, Collections.unmodifiableSet((Set)this.data.get(key)));
         }

         this.data = Collections.unmodifiableMap(this.data);
         this.frozen = true;
      }

      return this;
   }

   public Relation cloneAsThawed() {
      throw new UnsupportedOperationException();
   }

   public boolean removeAll(Relation toBeRemoved) {
      boolean result = false;

      for(Object key : toBeRemoved.keySet()) {
         try {
            Set<V> values = toBeRemoved.getAll(key);
            if (values != null) {
               result |= this.removeAll(key, values);
            }
         } catch (NullPointerException var6) {
         }
      }

      return result;
   }

   @SafeVarargs
   public final Set removeAll(Object... keys) {
      return this.removeAll((Collection)Arrays.asList(keys));
   }

   public boolean removeAll(Object key, Iterable toBeRemoved) {
      boolean result = false;

      for(Object value : toBeRemoved) {
         result |= this.remove(key, value);
      }

      return result;
   }

   public Set removeAll(Collection toBeRemoved) {
      Set<V> result = new LinkedHashSet();

      for(Object key : toBeRemoved) {
         try {
            Set<V> removals = (Set)this.data.remove(key);
            if (removals != null) {
               result.addAll(removals);
            }
         } catch (NullPointerException var6) {
         }
      }

      return result;
   }

   static class SimpleEntry implements Map.Entry {
      Object key;
      Object value;

      public SimpleEntry(Object key, Object value) {
         this.key = key;
         this.value = value;
      }

      public SimpleEntry(Map.Entry e) {
         this.key = e.getKey();
         this.value = e.getValue();
      }

      public Object getKey() {
         return this.key;
      }

      public Object getValue() {
         return this.value;
      }

      public Object setValue(Object value) {
         V oldValue = (V)this.value;
         this.value = value;
         return oldValue;
      }
   }
}
