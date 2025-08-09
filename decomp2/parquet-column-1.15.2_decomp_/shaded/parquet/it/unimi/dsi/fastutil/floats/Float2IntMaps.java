package shaded.parquet.it.unimi.dsi.fastutil.floats;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.DoubleFunction;
import java.util.function.DoubleToIntFunction;
import java.util.function.Function;
import shaded.parquet.it.unimi.dsi.fastutil.HashCommon;
import shaded.parquet.it.unimi.dsi.fastutil.ints.IntCollection;
import shaded.parquet.it.unimi.dsi.fastutil.ints.IntCollections;
import shaded.parquet.it.unimi.dsi.fastutil.ints.IntSets;
import shaded.parquet.it.unimi.dsi.fastutil.objects.ObjectIterable;
import shaded.parquet.it.unimi.dsi.fastutil.objects.ObjectIterator;
import shaded.parquet.it.unimi.dsi.fastutil.objects.ObjectSet;
import shaded.parquet.it.unimi.dsi.fastutil.objects.ObjectSets;
import shaded.parquet.it.unimi.dsi.fastutil.objects.ObjectSpliterator;

public final class Float2IntMaps {
   public static final EmptyMap EMPTY_MAP = new EmptyMap();

   private Float2IntMaps() {
   }

   public static ObjectIterator fastIterator(Float2IntMap map) {
      ObjectSet<Float2IntMap.Entry> entries = map.float2IntEntrySet();
      return entries instanceof Float2IntMap.FastEntrySet ? ((Float2IntMap.FastEntrySet)entries).fastIterator() : entries.iterator();
   }

   public static void fastForEach(Float2IntMap map, Consumer consumer) {
      ObjectSet<Float2IntMap.Entry> entries = map.float2IntEntrySet();
      if (entries instanceof Float2IntMap.FastEntrySet) {
         ((Float2IntMap.FastEntrySet)entries).fastForEach(consumer);
      } else {
         entries.forEach(consumer);
      }

   }

   public static ObjectIterable fastIterable(Float2IntMap map) {
      final ObjectSet<Float2IntMap.Entry> entries = map.float2IntEntrySet();
      return (ObjectIterable)(entries instanceof Float2IntMap.FastEntrySet ? new ObjectIterable() {
         public ObjectIterator iterator() {
            return ((Float2IntMap.FastEntrySet)entries).fastIterator();
         }

         public ObjectSpliterator spliterator() {
            return entries.spliterator();
         }

         public void forEach(Consumer consumer) {
            ((Float2IntMap.FastEntrySet)entries).fastForEach(consumer);
         }
      } : entries);
   }

   public static Float2IntMap singleton(float key, int value) {
      return new Singleton(key, value);
   }

   public static Float2IntMap singleton(Float key, Integer value) {
      return new Singleton(key, value);
   }

   public static Float2IntMap synchronize(Float2IntMap m) {
      return new SynchronizedMap(m);
   }

   public static Float2IntMap synchronize(Float2IntMap m, Object sync) {
      return new SynchronizedMap(m, sync);
   }

   public static Float2IntMap unmodifiable(Float2IntMap m) {
      return new UnmodifiableMap(m);
   }

   public static class EmptyMap extends Float2IntFunctions.EmptyFunction implements Float2IntMap, Serializable, Cloneable {
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

      public int getOrDefault(float key, int defaultValue) {
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

      public ObjectSet float2IntEntrySet() {
         return ObjectSets.EMPTY_SET;
      }

      public FloatSet keySet() {
         return FloatSets.EMPTY_SET;
      }

      public IntCollection values() {
         return IntSets.EMPTY_SET;
      }

      public void forEach(BiConsumer consumer) {
      }

      public Object clone() {
         return Float2IntMaps.EMPTY_MAP;
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

   public static class Singleton extends Float2IntFunctions.Singleton implements Float2IntMap, Serializable, Cloneable {
      private static final long serialVersionUID = -7046029254386353129L;
      protected transient ObjectSet entries;
      protected transient FloatSet keys;
      protected transient IntCollection values;

      protected Singleton(float key, int value) {
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

      public ObjectSet float2IntEntrySet() {
         if (this.entries == null) {
            this.entries = ObjectSets.singleton(new AbstractFloat2IntMap.BasicEntry(this.key, this.value));
         }

         return this.entries;
      }

      /** @deprecated */
      @Deprecated
      public ObjectSet entrySet() {
         return this.float2IntEntrySet();
      }

      public FloatSet keySet() {
         if (this.keys == null) {
            this.keys = FloatSets.singleton(this.key);
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
         return HashCommon.float2int(this.key) ^ this.value;
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

   public static class SynchronizedMap extends Float2IntFunctions.SynchronizedFunction implements Float2IntMap, Serializable {
      private static final long serialVersionUID = -7046029254386353129L;
      protected final Float2IntMap map;
      protected transient ObjectSet entries;
      protected transient FloatSet keys;
      protected transient IntCollection values;

      protected SynchronizedMap(Float2IntMap m, Object sync) {
         super(m, sync);
         this.map = m;
      }

      protected SynchronizedMap(Float2IntMap m) {
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

      public ObjectSet float2IntEntrySet() {
         synchronized(this.sync) {
            if (this.entries == null) {
               this.entries = ObjectSets.synchronize(this.map.float2IntEntrySet(), this.sync);
            }

            return this.entries;
         }
      }

      /** @deprecated */
      @Deprecated
      public ObjectSet entrySet() {
         return this.float2IntEntrySet();
      }

      public FloatSet keySet() {
         synchronized(this.sync) {
            if (this.keys == null) {
               this.keys = FloatSets.synchronize(this.map.keySet(), this.sync);
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

      public int getOrDefault(float key, int defaultValue) {
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

      public int putIfAbsent(float key, int value) {
         synchronized(this.sync) {
            return this.map.putIfAbsent(key, value);
         }
      }

      public boolean remove(float key, int value) {
         synchronized(this.sync) {
            return this.map.remove(key, value);
         }
      }

      public int replace(float key, int value) {
         synchronized(this.sync) {
            return this.map.replace(key, value);
         }
      }

      public boolean replace(float key, int oldValue, int newValue) {
         synchronized(this.sync) {
            return this.map.replace(key, oldValue, newValue);
         }
      }

      public int computeIfAbsent(float key, DoubleToIntFunction mappingFunction) {
         synchronized(this.sync) {
            return this.map.computeIfAbsent(key, mappingFunction);
         }
      }

      public int computeIfAbsentNullable(float key, DoubleFunction mappingFunction) {
         synchronized(this.sync) {
            return this.map.computeIfAbsentNullable(key, mappingFunction);
         }
      }

      public int computeIfAbsent(float key, Float2IntFunction mappingFunction) {
         synchronized(this.sync) {
            return this.map.computeIfAbsent(key, mappingFunction);
         }
      }

      public int computeIfPresent(float key, BiFunction remappingFunction) {
         synchronized(this.sync) {
            return this.map.computeIfPresent(key, remappingFunction);
         }
      }

      public int compute(float key, BiFunction remappingFunction) {
         synchronized(this.sync) {
            return this.map.compute(key, remappingFunction);
         }
      }

      public int merge(float key, int value, BiFunction remappingFunction) {
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
      public Integer replace(Float key, Integer value) {
         synchronized(this.sync) {
            return this.map.replace(key, value);
         }
      }

      /** @deprecated */
      @Deprecated
      public boolean replace(Float key, Integer oldValue, Integer newValue) {
         synchronized(this.sync) {
            return this.map.replace(key, oldValue, newValue);
         }
      }

      /** @deprecated */
      @Deprecated
      public Integer putIfAbsent(Float key, Integer value) {
         synchronized(this.sync) {
            return this.map.putIfAbsent(key, value);
         }
      }

      /** @deprecated */
      @Deprecated
      public Integer computeIfAbsent(Float key, Function mappingFunction) {
         synchronized(this.sync) {
            return this.map.computeIfAbsent(key, mappingFunction);
         }
      }

      /** @deprecated */
      @Deprecated
      public Integer computeIfPresent(Float key, BiFunction remappingFunction) {
         synchronized(this.sync) {
            return this.map.computeIfPresent(key, remappingFunction);
         }
      }

      /** @deprecated */
      @Deprecated
      public Integer compute(Float key, BiFunction remappingFunction) {
         synchronized(this.sync) {
            return this.map.compute(key, remappingFunction);
         }
      }

      /** @deprecated */
      @Deprecated
      public Integer merge(Float key, Integer value, BiFunction remappingFunction) {
         synchronized(this.sync) {
            return this.map.merge(key, value, remappingFunction);
         }
      }
   }

   public static class UnmodifiableMap extends Float2IntFunctions.UnmodifiableFunction implements Float2IntMap, Serializable {
      private static final long serialVersionUID = -7046029254386353129L;
      protected final Float2IntMap map;
      protected transient ObjectSet entries;
      protected transient FloatSet keys;
      protected transient IntCollection values;

      protected UnmodifiableMap(Float2IntMap m) {
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

      public ObjectSet float2IntEntrySet() {
         if (this.entries == null) {
            this.entries = ObjectSets.unmodifiable(this.map.float2IntEntrySet());
         }

         return this.entries;
      }

      /** @deprecated */
      @Deprecated
      public ObjectSet entrySet() {
         return this.float2IntEntrySet();
      }

      public FloatSet keySet() {
         if (this.keys == null) {
            this.keys = FloatSets.unmodifiable(this.map.keySet());
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

      public int getOrDefault(float key, int defaultValue) {
         return this.map.getOrDefault(key, defaultValue);
      }

      public void forEach(BiConsumer action) {
         this.map.forEach(action);
      }

      public void replaceAll(BiFunction function) {
         throw new UnsupportedOperationException();
      }

      public int putIfAbsent(float key, int value) {
         throw new UnsupportedOperationException();
      }

      public boolean remove(float key, int value) {
         throw new UnsupportedOperationException();
      }

      public int replace(float key, int value) {
         throw new UnsupportedOperationException();
      }

      public boolean replace(float key, int oldValue, int newValue) {
         throw new UnsupportedOperationException();
      }

      public int computeIfAbsent(float key, DoubleToIntFunction mappingFunction) {
         throw new UnsupportedOperationException();
      }

      public int computeIfAbsentNullable(float key, DoubleFunction mappingFunction) {
         throw new UnsupportedOperationException();
      }

      public int computeIfAbsent(float key, Float2IntFunction mappingFunction) {
         throw new UnsupportedOperationException();
      }

      public int computeIfPresent(float key, BiFunction remappingFunction) {
         throw new UnsupportedOperationException();
      }

      public int compute(float key, BiFunction remappingFunction) {
         throw new UnsupportedOperationException();
      }

      public int merge(float key, int value, BiFunction remappingFunction) {
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
      public Integer replace(Float key, Integer value) {
         throw new UnsupportedOperationException();
      }

      /** @deprecated */
      @Deprecated
      public boolean replace(Float key, Integer oldValue, Integer newValue) {
         throw new UnsupportedOperationException();
      }

      /** @deprecated */
      @Deprecated
      public Integer putIfAbsent(Float key, Integer value) {
         throw new UnsupportedOperationException();
      }

      /** @deprecated */
      @Deprecated
      public Integer computeIfAbsent(Float key, Function mappingFunction) {
         throw new UnsupportedOperationException();
      }

      /** @deprecated */
      @Deprecated
      public Integer computeIfPresent(Float key, BiFunction remappingFunction) {
         throw new UnsupportedOperationException();
      }

      /** @deprecated */
      @Deprecated
      public Integer compute(Float key, BiFunction remappingFunction) {
         throw new UnsupportedOperationException();
      }

      /** @deprecated */
      @Deprecated
      public Integer merge(Float key, Integer value, BiFunction remappingFunction) {
         throw new UnsupportedOperationException();
      }
   }
}
