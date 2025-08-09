package shaded.parquet.it.unimi.dsi.fastutil.ints;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.IntFunction;
import shaded.parquet.it.unimi.dsi.fastutil.objects.ObjectIterable;
import shaded.parquet.it.unimi.dsi.fastutil.objects.ObjectIterator;
import shaded.parquet.it.unimi.dsi.fastutil.objects.ObjectSet;
import shaded.parquet.it.unimi.dsi.fastutil.objects.ObjectSets;
import shaded.parquet.it.unimi.dsi.fastutil.objects.ObjectSpliterator;

public final class Int2IntMaps {
   public static final EmptyMap EMPTY_MAP = new EmptyMap();

   private Int2IntMaps() {
   }

   public static ObjectIterator fastIterator(Int2IntMap map) {
      ObjectSet<Int2IntMap.Entry> entries = map.int2IntEntrySet();
      return entries instanceof Int2IntMap.FastEntrySet ? ((Int2IntMap.FastEntrySet)entries).fastIterator() : entries.iterator();
   }

   public static void fastForEach(Int2IntMap map, Consumer consumer) {
      ObjectSet<Int2IntMap.Entry> entries = map.int2IntEntrySet();
      if (entries instanceof Int2IntMap.FastEntrySet) {
         ((Int2IntMap.FastEntrySet)entries).fastForEach(consumer);
      } else {
         entries.forEach(consumer);
      }

   }

   public static ObjectIterable fastIterable(Int2IntMap map) {
      final ObjectSet<Int2IntMap.Entry> entries = map.int2IntEntrySet();
      return (ObjectIterable)(entries instanceof Int2IntMap.FastEntrySet ? new ObjectIterable() {
         public ObjectIterator iterator() {
            return ((Int2IntMap.FastEntrySet)entries).fastIterator();
         }

         public ObjectSpliterator spliterator() {
            return entries.spliterator();
         }

         public void forEach(Consumer consumer) {
            ((Int2IntMap.FastEntrySet)entries).fastForEach(consumer);
         }
      } : entries);
   }

   public static Int2IntMap singleton(int key, int value) {
      return new Singleton(key, value);
   }

   public static Int2IntMap singleton(Integer key, Integer value) {
      return new Singleton(key, value);
   }

   public static Int2IntMap synchronize(Int2IntMap m) {
      return new SynchronizedMap(m);
   }

   public static Int2IntMap synchronize(Int2IntMap m, Object sync) {
      return new SynchronizedMap(m, sync);
   }

   public static Int2IntMap unmodifiable(Int2IntMap m) {
      return new UnmodifiableMap(m);
   }

   public static class EmptyMap extends Int2IntFunctions.EmptyFunction implements Int2IntMap, Serializable, Cloneable {
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

      public int getOrDefault(int key, int defaultValue) {
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

      public ObjectSet int2IntEntrySet() {
         return ObjectSets.EMPTY_SET;
      }

      public IntSet keySet() {
         return IntSets.EMPTY_SET;
      }

      public IntCollection values() {
         return IntSets.EMPTY_SET;
      }

      public void forEach(BiConsumer consumer) {
      }

      public Object clone() {
         return Int2IntMaps.EMPTY_MAP;
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

   public static class Singleton extends Int2IntFunctions.Singleton implements Int2IntMap, Serializable, Cloneable {
      private static final long serialVersionUID = -7046029254386353129L;
      protected transient ObjectSet entries;
      protected transient IntSet keys;
      protected transient IntCollection values;

      protected Singleton(int key, int value) {
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

      public ObjectSet int2IntEntrySet() {
         if (this.entries == null) {
            this.entries = ObjectSets.singleton(new AbstractInt2IntMap.BasicEntry(this.key, this.value));
         }

         return this.entries;
      }

      /** @deprecated */
      @Deprecated
      public ObjectSet entrySet() {
         return this.int2IntEntrySet();
      }

      public IntSet keySet() {
         if (this.keys == null) {
            this.keys = IntSets.singleton(this.key);
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
         return this.key ^ this.value;
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

   public static class SynchronizedMap extends Int2IntFunctions.SynchronizedFunction implements Int2IntMap, Serializable {
      private static final long serialVersionUID = -7046029254386353129L;
      protected final Int2IntMap map;
      protected transient ObjectSet entries;
      protected transient IntSet keys;
      protected transient IntCollection values;

      protected SynchronizedMap(Int2IntMap m, Object sync) {
         super(m, sync);
         this.map = m;
      }

      protected SynchronizedMap(Int2IntMap m) {
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

      public ObjectSet int2IntEntrySet() {
         synchronized(this.sync) {
            if (this.entries == null) {
               this.entries = ObjectSets.synchronize(this.map.int2IntEntrySet(), this.sync);
            }

            return this.entries;
         }
      }

      /** @deprecated */
      @Deprecated
      public ObjectSet entrySet() {
         return this.int2IntEntrySet();
      }

      public IntSet keySet() {
         synchronized(this.sync) {
            if (this.keys == null) {
               this.keys = IntSets.synchronize(this.map.keySet(), this.sync);
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

      public int getOrDefault(int key, int defaultValue) {
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

      public int putIfAbsent(int key, int value) {
         synchronized(this.sync) {
            return this.map.putIfAbsent(key, value);
         }
      }

      public boolean remove(int key, int value) {
         synchronized(this.sync) {
            return this.map.remove(key, value);
         }
      }

      public int replace(int key, int value) {
         synchronized(this.sync) {
            return this.map.replace(key, value);
         }
      }

      public boolean replace(int key, int oldValue, int newValue) {
         synchronized(this.sync) {
            return this.map.replace(key, oldValue, newValue);
         }
      }

      public int computeIfAbsent(int key, java.util.function.IntUnaryOperator mappingFunction) {
         synchronized(this.sync) {
            return this.map.computeIfAbsent(key, mappingFunction);
         }
      }

      public int computeIfAbsentNullable(int key, IntFunction mappingFunction) {
         synchronized(this.sync) {
            return this.map.computeIfAbsentNullable(key, mappingFunction);
         }
      }

      public int computeIfAbsent(int key, Int2IntFunction mappingFunction) {
         synchronized(this.sync) {
            return this.map.computeIfAbsent(key, mappingFunction);
         }
      }

      public int computeIfPresent(int key, BiFunction remappingFunction) {
         synchronized(this.sync) {
            return this.map.computeIfPresent(key, remappingFunction);
         }
      }

      public int compute(int key, BiFunction remappingFunction) {
         synchronized(this.sync) {
            return this.map.compute(key, remappingFunction);
         }
      }

      public int merge(int key, int value, BiFunction remappingFunction) {
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
      public Integer replace(Integer key, Integer value) {
         synchronized(this.sync) {
            return this.map.replace(key, value);
         }
      }

      /** @deprecated */
      @Deprecated
      public boolean replace(Integer key, Integer oldValue, Integer newValue) {
         synchronized(this.sync) {
            return this.map.replace(key, oldValue, newValue);
         }
      }

      /** @deprecated */
      @Deprecated
      public Integer putIfAbsent(Integer key, Integer value) {
         synchronized(this.sync) {
            return this.map.putIfAbsent(key, value);
         }
      }

      /** @deprecated */
      @Deprecated
      public Integer computeIfAbsent(Integer key, Function mappingFunction) {
         synchronized(this.sync) {
            return this.map.computeIfAbsent(key, mappingFunction);
         }
      }

      /** @deprecated */
      @Deprecated
      public Integer computeIfPresent(Integer key, BiFunction remappingFunction) {
         synchronized(this.sync) {
            return this.map.computeIfPresent(key, remappingFunction);
         }
      }

      /** @deprecated */
      @Deprecated
      public Integer compute(Integer key, BiFunction remappingFunction) {
         synchronized(this.sync) {
            return this.map.compute(key, remappingFunction);
         }
      }

      /** @deprecated */
      @Deprecated
      public Integer merge(Integer key, Integer value, BiFunction remappingFunction) {
         synchronized(this.sync) {
            return this.map.merge(key, value, remappingFunction);
         }
      }
   }

   public static class UnmodifiableMap extends Int2IntFunctions.UnmodifiableFunction implements Int2IntMap, Serializable {
      private static final long serialVersionUID = -7046029254386353129L;
      protected final Int2IntMap map;
      protected transient ObjectSet entries;
      protected transient IntSet keys;
      protected transient IntCollection values;

      protected UnmodifiableMap(Int2IntMap m) {
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

      public ObjectSet int2IntEntrySet() {
         if (this.entries == null) {
            this.entries = ObjectSets.unmodifiable(this.map.int2IntEntrySet());
         }

         return this.entries;
      }

      /** @deprecated */
      @Deprecated
      public ObjectSet entrySet() {
         return this.int2IntEntrySet();
      }

      public IntSet keySet() {
         if (this.keys == null) {
            this.keys = IntSets.unmodifiable(this.map.keySet());
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

      public int getOrDefault(int key, int defaultValue) {
         return this.map.getOrDefault(key, defaultValue);
      }

      public void forEach(BiConsumer action) {
         this.map.forEach(action);
      }

      public void replaceAll(BiFunction function) {
         throw new UnsupportedOperationException();
      }

      public int putIfAbsent(int key, int value) {
         throw new UnsupportedOperationException();
      }

      public boolean remove(int key, int value) {
         throw new UnsupportedOperationException();
      }

      public int replace(int key, int value) {
         throw new UnsupportedOperationException();
      }

      public boolean replace(int key, int oldValue, int newValue) {
         throw new UnsupportedOperationException();
      }

      public int computeIfAbsent(int key, java.util.function.IntUnaryOperator mappingFunction) {
         throw new UnsupportedOperationException();
      }

      public int computeIfAbsentNullable(int key, IntFunction mappingFunction) {
         throw new UnsupportedOperationException();
      }

      public int computeIfAbsent(int key, Int2IntFunction mappingFunction) {
         throw new UnsupportedOperationException();
      }

      public int computeIfPresent(int key, BiFunction remappingFunction) {
         throw new UnsupportedOperationException();
      }

      public int compute(int key, BiFunction remappingFunction) {
         throw new UnsupportedOperationException();
      }

      public int merge(int key, int value, BiFunction remappingFunction) {
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
      public Integer replace(Integer key, Integer value) {
         throw new UnsupportedOperationException();
      }

      /** @deprecated */
      @Deprecated
      public boolean replace(Integer key, Integer oldValue, Integer newValue) {
         throw new UnsupportedOperationException();
      }

      /** @deprecated */
      @Deprecated
      public Integer putIfAbsent(Integer key, Integer value) {
         throw new UnsupportedOperationException();
      }

      /** @deprecated */
      @Deprecated
      public Integer computeIfAbsent(Integer key, Function mappingFunction) {
         throw new UnsupportedOperationException();
      }

      /** @deprecated */
      @Deprecated
      public Integer computeIfPresent(Integer key, BiFunction remappingFunction) {
         throw new UnsupportedOperationException();
      }

      /** @deprecated */
      @Deprecated
      public Integer compute(Integer key, BiFunction remappingFunction) {
         throw new UnsupportedOperationException();
      }

      /** @deprecated */
      @Deprecated
      public Integer merge(Integer key, Integer value, BiFunction remappingFunction) {
         throw new UnsupportedOperationException();
      }
   }
}
