package shaded.parquet.it.unimi.dsi.fastutil.objects;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.ToIntFunction;
import shaded.parquet.it.unimi.dsi.fastutil.ints.IntCollection;
import shaded.parquet.it.unimi.dsi.fastutil.ints.IntCollections;
import shaded.parquet.it.unimi.dsi.fastutil.ints.IntSets;

public final class Object2IntMaps {
   public static final EmptyMap EMPTY_MAP = new EmptyMap();

   private Object2IntMaps() {
   }

   public static ObjectIterator fastIterator(Object2IntMap map) {
      ObjectSet<Object2IntMap.Entry<K>> entries = map.object2IntEntrySet();
      return entries instanceof Object2IntMap.FastEntrySet ? ((Object2IntMap.FastEntrySet)entries).fastIterator() : entries.iterator();
   }

   public static void fastForEach(Object2IntMap map, Consumer consumer) {
      ObjectSet<Object2IntMap.Entry<K>> entries = map.object2IntEntrySet();
      if (entries instanceof Object2IntMap.FastEntrySet) {
         ((Object2IntMap.FastEntrySet)entries).fastForEach(consumer);
      } else {
         entries.forEach(consumer);
      }

   }

   public static ObjectIterable fastIterable(Object2IntMap map) {
      final ObjectSet<Object2IntMap.Entry<K>> entries = map.object2IntEntrySet();
      return (ObjectIterable)(entries instanceof Object2IntMap.FastEntrySet ? new ObjectIterable() {
         public ObjectIterator iterator() {
            return ((Object2IntMap.FastEntrySet)entries).fastIterator();
         }

         public ObjectSpliterator spliterator() {
            return entries.spliterator();
         }

         public void forEach(Consumer consumer) {
            ((Object2IntMap.FastEntrySet)entries).fastForEach(consumer);
         }
      } : entries);
   }

   public static Object2IntMap emptyMap() {
      return EMPTY_MAP;
   }

   public static Object2IntMap singleton(Object key, int value) {
      return new Singleton(key, value);
   }

   public static Object2IntMap singleton(Object key, Integer value) {
      return new Singleton(key, value);
   }

   public static Object2IntMap synchronize(Object2IntMap m) {
      return new SynchronizedMap(m);
   }

   public static Object2IntMap synchronize(Object2IntMap m, Object sync) {
      return new SynchronizedMap(m, sync);
   }

   public static Object2IntMap unmodifiable(Object2IntMap m) {
      return new UnmodifiableMap(m);
   }

   public static class EmptyMap extends Object2IntFunctions.EmptyFunction implements Object2IntMap, Serializable, Cloneable {
      private static final long serialVersionUID = -7046029254386353129L;

      protected EmptyMap() {
      }

      public boolean containsValue(int v) {
         return false;
      }

      /** @deprecated */
      @Deprecated
      public Integer getOrDefault(Object key, Integer defaultValue) {
         return defaultValue;
      }

      public int getOrDefault(Object key, int defaultValue) {
         return defaultValue;
      }

      /** @deprecated */
      @Deprecated
      public boolean containsValue(Object ov) {
         return false;
      }

      public void putAll(Map m) {
         throw new UnsupportedOperationException();
      }

      public ObjectSet object2IntEntrySet() {
         return ObjectSets.EMPTY_SET;
      }

      public ObjectSet keySet() {
         return ObjectSets.EMPTY_SET;
      }

      public IntCollection values() {
         return IntSets.EMPTY_SET;
      }

      public void forEach(BiConsumer consumer) {
      }

      public Object clone() {
         return Object2IntMaps.EMPTY_MAP;
      }

      public boolean isEmpty() {
         return true;
      }

      public int hashCode() {
         return 0;
      }

      public boolean equals(Object o) {
         return !(o instanceof Map) ? false : ((Map)o).isEmpty();
      }

      public String toString() {
         return "{}";
      }
   }

   public static class Singleton extends Object2IntFunctions.Singleton implements Object2IntMap, Serializable, Cloneable {
      private static final long serialVersionUID = -7046029254386353129L;
      protected transient ObjectSet entries;
      protected transient ObjectSet keys;
      protected transient IntCollection values;

      protected Singleton(Object key, int value) {
         super(key, value);
      }

      public boolean containsValue(int v) {
         return this.value == v;
      }

      /** @deprecated */
      @Deprecated
      public boolean containsValue(Object ov) {
         return (Integer)ov == this.value;
      }

      public void putAll(Map m) {
         throw new UnsupportedOperationException();
      }

      public ObjectSet object2IntEntrySet() {
         if (this.entries == null) {
            this.entries = ObjectSets.singleton(new AbstractObject2IntMap.BasicEntry(this.key, this.value));
         }

         return this.entries;
      }

      /** @deprecated */
      @Deprecated
      public ObjectSet entrySet() {
         return this.object2IntEntrySet();
      }

      public ObjectSet keySet() {
         if (this.keys == null) {
            this.keys = ObjectSets.singleton(this.key);
         }

         return this.keys;
      }

      public IntCollection values() {
         if (this.values == null) {
            this.values = IntSets.singleton(this.value);
         }

         return this.values;
      }

      public boolean isEmpty() {
         return false;
      }

      public int hashCode() {
         return (this.key == null ? 0 : this.key.hashCode()) ^ this.value;
      }

      public boolean equals(Object o) {
         if (o == this) {
            return true;
         } else if (!(o instanceof Map)) {
            return false;
         } else {
            Map<?, ?> m = (Map)o;
            return m.size() != 1 ? false : ((Map.Entry)m.entrySet().iterator().next()).equals(this.entrySet().iterator().next());
         }
      }

      public String toString() {
         return "{" + this.key + "=>" + this.value + "}";
      }
   }

   public static class SynchronizedMap extends Object2IntFunctions.SynchronizedFunction implements Object2IntMap, Serializable {
      private static final long serialVersionUID = -7046029254386353129L;
      protected final Object2IntMap map;
      protected transient ObjectSet entries;
      protected transient ObjectSet keys;
      protected transient IntCollection values;

      protected SynchronizedMap(Object2IntMap m, Object sync) {
         super(m, sync);
         this.map = m;
      }

      protected SynchronizedMap(Object2IntMap m) {
         super(m);
         this.map = m;
      }

      public boolean containsValue(int v) {
         synchronized(this.sync) {
            return this.map.containsValue(v);
         }
      }

      /** @deprecated */
      @Deprecated
      public boolean containsValue(Object ov) {
         synchronized(this.sync) {
            return this.map.containsValue(ov);
         }
      }

      public void putAll(Map m) {
         synchronized(this.sync) {
            this.map.putAll(m);
         }
      }

      public ObjectSet object2IntEntrySet() {
         synchronized(this.sync) {
            if (this.entries == null) {
               this.entries = ObjectSets.synchronize(this.map.object2IntEntrySet(), this.sync);
            }

            return this.entries;
         }
      }

      /** @deprecated */
      @Deprecated
      public ObjectSet entrySet() {
         return this.object2IntEntrySet();
      }

      public ObjectSet keySet() {
         synchronized(this.sync) {
            if (this.keys == null) {
               this.keys = ObjectSets.synchronize(this.map.keySet(), this.sync);
            }

            return this.keys;
         }
      }

      public IntCollection values() {
         synchronized(this.sync) {
            if (this.values == null) {
               this.values = IntCollections.synchronize(this.map.values(), this.sync);
            }

            return this.values;
         }
      }

      public boolean isEmpty() {
         synchronized(this.sync) {
            return this.map.isEmpty();
         }
      }

      public int hashCode() {
         synchronized(this.sync) {
            return this.map.hashCode();
         }
      }

      public boolean equals(Object o) {
         if (o == this) {
            return true;
         } else {
            synchronized(this.sync) {
               return this.map.equals(o);
            }
         }
      }

      private void writeObject(ObjectOutputStream s) throws IOException {
         synchronized(this.sync) {
            s.defaultWriteObject();
         }
      }

      public int getOrDefault(Object key, int defaultValue) {
         synchronized(this.sync) {
            return this.map.getOrDefault(key, defaultValue);
         }
      }

      public void forEach(BiConsumer action) {
         synchronized(this.sync) {
            this.map.forEach(action);
         }
      }

      public void replaceAll(BiFunction function) {
         synchronized(this.sync) {
            this.map.replaceAll(function);
         }
      }

      public int putIfAbsent(Object key, int value) {
         synchronized(this.sync) {
            return this.map.putIfAbsent(key, value);
         }
      }

      public boolean remove(Object key, int value) {
         synchronized(this.sync) {
            return this.map.remove(key, value);
         }
      }

      public int replace(Object key, int value) {
         synchronized(this.sync) {
            return this.map.replace(key, value);
         }
      }

      public boolean replace(Object key, int oldValue, int newValue) {
         synchronized(this.sync) {
            return this.map.replace(key, oldValue, newValue);
         }
      }

      public int computeIfAbsent(Object key, ToIntFunction mappingFunction) {
         synchronized(this.sync) {
            return this.map.computeIfAbsent(key, mappingFunction);
         }
      }

      public int computeIfAbsent(Object key, Object2IntFunction mappingFunction) {
         synchronized(this.sync) {
            return this.map.computeIfAbsent(key, mappingFunction);
         }
      }

      public int computeIntIfPresent(Object key, BiFunction remappingFunction) {
         synchronized(this.sync) {
            return this.map.computeIntIfPresent(key, remappingFunction);
         }
      }

      public int computeInt(Object key, BiFunction remappingFunction) {
         synchronized(this.sync) {
            return this.map.computeInt(key, remappingFunction);
         }
      }

      public int merge(Object key, int value, BiFunction remappingFunction) {
         synchronized(this.sync) {
            return this.map.merge(key, value, remappingFunction);
         }
      }

      /** @deprecated */
      @Deprecated
      public Integer getOrDefault(Object key, Integer defaultValue) {
         synchronized(this.sync) {
            return this.map.getOrDefault(key, defaultValue);
         }
      }

      /** @deprecated */
      @Deprecated
      public boolean remove(Object key, Object value) {
         synchronized(this.sync) {
            return this.map.remove(key, value);
         }
      }

      /** @deprecated */
      @Deprecated
      public Integer replace(Object key, Integer value) {
         synchronized(this.sync) {
            return this.map.replace(key, value);
         }
      }

      /** @deprecated */
      @Deprecated
      public boolean replace(Object key, Integer oldValue, Integer newValue) {
         synchronized(this.sync) {
            return this.map.replace(key, oldValue, newValue);
         }
      }

      /** @deprecated */
      @Deprecated
      public Integer putIfAbsent(Object key, Integer value) {
         synchronized(this.sync) {
            return this.map.putIfAbsent(key, value);
         }
      }

      public Integer computeIfAbsent(Object key, Function mappingFunction) {
         synchronized(this.sync) {
            return (Integer)this.map.computeIfAbsent(key, (Function)mappingFunction);
         }
      }

      public Integer computeIfPresent(Object key, BiFunction remappingFunction) {
         synchronized(this.sync) {
            return (Integer)this.map.computeIfPresent(key, remappingFunction);
         }
      }

      public Integer compute(Object key, BiFunction remappingFunction) {
         synchronized(this.sync) {
            return (Integer)this.map.compute(key, remappingFunction);
         }
      }

      /** @deprecated */
      @Deprecated
      public Integer merge(Object key, Integer value, BiFunction remappingFunction) {
         synchronized(this.sync) {
            return this.map.merge(key, value, remappingFunction);
         }
      }
   }

   public static class UnmodifiableMap extends Object2IntFunctions.UnmodifiableFunction implements Object2IntMap, Serializable {
      private static final long serialVersionUID = -7046029254386353129L;
      protected final Object2IntMap map;
      protected transient ObjectSet entries;
      protected transient ObjectSet keys;
      protected transient IntCollection values;

      protected UnmodifiableMap(Object2IntMap m) {
         super(m);
         this.map = m;
      }

      public boolean containsValue(int v) {
         return this.map.containsValue(v);
      }

      /** @deprecated */
      @Deprecated
      public boolean containsValue(Object ov) {
         return this.map.containsValue(ov);
      }

      public void putAll(Map m) {
         throw new UnsupportedOperationException();
      }

      public ObjectSet object2IntEntrySet() {
         if (this.entries == null) {
            this.entries = ObjectSets.unmodifiable(this.map.object2IntEntrySet());
         }

         return this.entries;
      }

      /** @deprecated */
      @Deprecated
      public ObjectSet entrySet() {
         return this.object2IntEntrySet();
      }

      public ObjectSet keySet() {
         if (this.keys == null) {
            this.keys = ObjectSets.unmodifiable(this.map.keySet());
         }

         return this.keys;
      }

      public IntCollection values() {
         if (this.values == null) {
            this.values = IntCollections.unmodifiable(this.map.values());
         }

         return this.values;
      }

      public boolean isEmpty() {
         return this.map.isEmpty();
      }

      public int hashCode() {
         return this.map.hashCode();
      }

      public boolean equals(Object o) {
         return o == this ? true : this.map.equals(o);
      }

      public int getOrDefault(Object key, int defaultValue) {
         return this.map.getOrDefault(key, defaultValue);
      }

      public void forEach(BiConsumer action) {
         this.map.forEach(action);
      }

      public void replaceAll(BiFunction function) {
         throw new UnsupportedOperationException();
      }

      public int putIfAbsent(Object key, int value) {
         throw new UnsupportedOperationException();
      }

      public boolean remove(Object key, int value) {
         throw new UnsupportedOperationException();
      }

      public int replace(Object key, int value) {
         throw new UnsupportedOperationException();
      }

      public boolean replace(Object key, int oldValue, int newValue) {
         throw new UnsupportedOperationException();
      }

      public int computeIfAbsent(Object key, ToIntFunction mappingFunction) {
         throw new UnsupportedOperationException();
      }

      public int computeIfAbsent(Object key, Object2IntFunction mappingFunction) {
         throw new UnsupportedOperationException();
      }

      public int computeIntIfPresent(Object key, BiFunction remappingFunction) {
         throw new UnsupportedOperationException();
      }

      public int computeInt(Object key, BiFunction remappingFunction) {
         throw new UnsupportedOperationException();
      }

      public int merge(Object key, int value, BiFunction remappingFunction) {
         throw new UnsupportedOperationException();
      }

      /** @deprecated */
      @Deprecated
      public Integer getOrDefault(Object key, Integer defaultValue) {
         return this.map.getOrDefault(key, defaultValue);
      }

      /** @deprecated */
      @Deprecated
      public boolean remove(Object key, Object value) {
         throw new UnsupportedOperationException();
      }

      /** @deprecated */
      @Deprecated
      public Integer replace(Object key, Integer value) {
         throw new UnsupportedOperationException();
      }

      /** @deprecated */
      @Deprecated
      public boolean replace(Object key, Integer oldValue, Integer newValue) {
         throw new UnsupportedOperationException();
      }

      /** @deprecated */
      @Deprecated
      public Integer putIfAbsent(Object key, Integer value) {
         throw new UnsupportedOperationException();
      }

      public Integer computeIfAbsent(Object key, Function mappingFunction) {
         throw new UnsupportedOperationException();
      }

      public Integer computeIfPresent(Object key, BiFunction remappingFunction) {
         throw new UnsupportedOperationException();
      }

      public Integer compute(Object key, BiFunction remappingFunction) {
         throw new UnsupportedOperationException();
      }

      /** @deprecated */
      @Deprecated
      public Integer merge(Object key, Integer value, BiFunction remappingFunction) {
         throw new UnsupportedOperationException();
      }
   }
}
