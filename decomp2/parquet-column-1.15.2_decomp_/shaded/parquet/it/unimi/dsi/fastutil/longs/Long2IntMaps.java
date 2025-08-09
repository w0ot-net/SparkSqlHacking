package shaded.parquet.it.unimi.dsi.fastutil.longs;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.LongFunction;
import java.util.function.LongToIntFunction;
import shaded.parquet.it.unimi.dsi.fastutil.HashCommon;
import shaded.parquet.it.unimi.dsi.fastutil.ints.IntCollection;
import shaded.parquet.it.unimi.dsi.fastutil.ints.IntCollections;
import shaded.parquet.it.unimi.dsi.fastutil.ints.IntSets;
import shaded.parquet.it.unimi.dsi.fastutil.objects.ObjectIterable;
import shaded.parquet.it.unimi.dsi.fastutil.objects.ObjectIterator;
import shaded.parquet.it.unimi.dsi.fastutil.objects.ObjectSet;
import shaded.parquet.it.unimi.dsi.fastutil.objects.ObjectSets;
import shaded.parquet.it.unimi.dsi.fastutil.objects.ObjectSpliterator;

public final class Long2IntMaps {
   public static final EmptyMap EMPTY_MAP = new EmptyMap();

   private Long2IntMaps() {
   }

   public static ObjectIterator fastIterator(Long2IntMap map) {
      ObjectSet<Long2IntMap.Entry> entries = map.long2IntEntrySet();
      return entries instanceof Long2IntMap.FastEntrySet ? ((Long2IntMap.FastEntrySet)entries).fastIterator() : entries.iterator();
   }

   public static void fastForEach(Long2IntMap map, Consumer consumer) {
      ObjectSet<Long2IntMap.Entry> entries = map.long2IntEntrySet();
      if (entries instanceof Long2IntMap.FastEntrySet) {
         ((Long2IntMap.FastEntrySet)entries).fastForEach(consumer);
      } else {
         entries.forEach(consumer);
      }

   }

   public static ObjectIterable fastIterable(Long2IntMap map) {
      final ObjectSet<Long2IntMap.Entry> entries = map.long2IntEntrySet();
      return (ObjectIterable)(entries instanceof Long2IntMap.FastEntrySet ? new ObjectIterable() {
         public ObjectIterator iterator() {
            return ((Long2IntMap.FastEntrySet)entries).fastIterator();
         }

         public ObjectSpliterator spliterator() {
            return entries.spliterator();
         }

         public void forEach(Consumer consumer) {
            ((Long2IntMap.FastEntrySet)entries).fastForEach(consumer);
         }
      } : entries);
   }

   public static Long2IntMap singleton(long key, int value) {
      return new Singleton(key, value);
   }

   public static Long2IntMap singleton(Long key, Integer value) {
      return new Singleton(key, value);
   }

   public static Long2IntMap synchronize(Long2IntMap m) {
      return new SynchronizedMap(m);
   }

   public static Long2IntMap synchronize(Long2IntMap m, Object sync) {
      return new SynchronizedMap(m, sync);
   }

   public static Long2IntMap unmodifiable(Long2IntMap m) {
      return new UnmodifiableMap(m);
   }

   public static class EmptyMap extends Long2IntFunctions.EmptyFunction implements Long2IntMap, Serializable, Cloneable {
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

      public int getOrDefault(long key, int defaultValue) {
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

      public ObjectSet long2IntEntrySet() {
         return ObjectSets.EMPTY_SET;
      }

      public LongSet keySet() {
         return LongSets.EMPTY_SET;
      }

      public IntCollection values() {
         return IntSets.EMPTY_SET;
      }

      public void forEach(BiConsumer consumer) {
      }

      public Object clone() {
         return Long2IntMaps.EMPTY_MAP;
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

   public static class Singleton extends Long2IntFunctions.Singleton implements Long2IntMap, Serializable, Cloneable {
      private static final long serialVersionUID = -7046029254386353129L;
      protected transient ObjectSet entries;
      protected transient LongSet keys;
      protected transient IntCollection values;

      protected Singleton(long key, int value) {
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

      public ObjectSet long2IntEntrySet() {
         if (this.entries == null) {
            this.entries = ObjectSets.singleton(new AbstractLong2IntMap.BasicEntry(this.key, this.value));
         }

         return this.entries;
      }

      /** @deprecated */
      @Deprecated
      public ObjectSet entrySet() {
         return this.long2IntEntrySet();
      }

      public LongSet keySet() {
         if (this.keys == null) {
            this.keys = LongSets.singleton(this.key);
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
         return HashCommon.long2int(this.key) ^ this.value;
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

   public static class SynchronizedMap extends Long2IntFunctions.SynchronizedFunction implements Long2IntMap, Serializable {
      private static final long serialVersionUID = -7046029254386353129L;
      protected final Long2IntMap map;
      protected transient ObjectSet entries;
      protected transient LongSet keys;
      protected transient IntCollection values;

      protected SynchronizedMap(Long2IntMap m, Object sync) {
         super(m, sync);
         this.map = m;
      }

      protected SynchronizedMap(Long2IntMap m) {
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

      public ObjectSet long2IntEntrySet() {
         synchronized(this.sync) {
            if (this.entries == null) {
               this.entries = ObjectSets.synchronize(this.map.long2IntEntrySet(), this.sync);
            }

            return this.entries;
         }
      }

      /** @deprecated */
      @Deprecated
      public ObjectSet entrySet() {
         return this.long2IntEntrySet();
      }

      public LongSet keySet() {
         synchronized(this.sync) {
            if (this.keys == null) {
               this.keys = LongSets.synchronize(this.map.keySet(), this.sync);
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

      public int getOrDefault(long key, int defaultValue) {
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

      public int putIfAbsent(long key, int value) {
         synchronized(this.sync) {
            return this.map.putIfAbsent(key, value);
         }
      }

      public boolean remove(long key, int value) {
         synchronized(this.sync) {
            return this.map.remove(key, value);
         }
      }

      public int replace(long key, int value) {
         synchronized(this.sync) {
            return this.map.replace(key, value);
         }
      }

      public boolean replace(long key, int oldValue, int newValue) {
         synchronized(this.sync) {
            return this.map.replace(key, oldValue, newValue);
         }
      }

      public int computeIfAbsent(long key, LongToIntFunction mappingFunction) {
         synchronized(this.sync) {
            return this.map.computeIfAbsent(key, mappingFunction);
         }
      }

      public int computeIfAbsentNullable(long key, LongFunction mappingFunction) {
         synchronized(this.sync) {
            return this.map.computeIfAbsentNullable(key, mappingFunction);
         }
      }

      public int computeIfAbsent(long key, Long2IntFunction mappingFunction) {
         synchronized(this.sync) {
            return this.map.computeIfAbsent(key, mappingFunction);
         }
      }

      public int computeIfPresent(long key, BiFunction remappingFunction) {
         synchronized(this.sync) {
            return this.map.computeIfPresent(key, remappingFunction);
         }
      }

      public int compute(long key, BiFunction remappingFunction) {
         synchronized(this.sync) {
            return this.map.compute(key, remappingFunction);
         }
      }

      public int merge(long key, int value, BiFunction remappingFunction) {
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
      public Integer replace(Long key, Integer value) {
         synchronized(this.sync) {
            return this.map.replace(key, value);
         }
      }

      /** @deprecated */
      @Deprecated
      public boolean replace(Long key, Integer oldValue, Integer newValue) {
         synchronized(this.sync) {
            return this.map.replace(key, oldValue, newValue);
         }
      }

      /** @deprecated */
      @Deprecated
      public Integer putIfAbsent(Long key, Integer value) {
         synchronized(this.sync) {
            return this.map.putIfAbsent(key, value);
         }
      }

      /** @deprecated */
      @Deprecated
      public Integer computeIfAbsent(Long key, Function mappingFunction) {
         synchronized(this.sync) {
            return this.map.computeIfAbsent(key, mappingFunction);
         }
      }

      /** @deprecated */
      @Deprecated
      public Integer computeIfPresent(Long key, BiFunction remappingFunction) {
         synchronized(this.sync) {
            return this.map.computeIfPresent(key, remappingFunction);
         }
      }

      /** @deprecated */
      @Deprecated
      public Integer compute(Long key, BiFunction remappingFunction) {
         synchronized(this.sync) {
            return this.map.compute(key, remappingFunction);
         }
      }

      /** @deprecated */
      @Deprecated
      public Integer merge(Long key, Integer value, BiFunction remappingFunction) {
         synchronized(this.sync) {
            return this.map.merge(key, value, remappingFunction);
         }
      }
   }

   public static class UnmodifiableMap extends Long2IntFunctions.UnmodifiableFunction implements Long2IntMap, Serializable {
      private static final long serialVersionUID = -7046029254386353129L;
      protected final Long2IntMap map;
      protected transient ObjectSet entries;
      protected transient LongSet keys;
      protected transient IntCollection values;

      protected UnmodifiableMap(Long2IntMap m) {
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

      public ObjectSet long2IntEntrySet() {
         if (this.entries == null) {
            this.entries = ObjectSets.unmodifiable(this.map.long2IntEntrySet());
         }

         return this.entries;
      }

      /** @deprecated */
      @Deprecated
      public ObjectSet entrySet() {
         return this.long2IntEntrySet();
      }

      public LongSet keySet() {
         if (this.keys == null) {
            this.keys = LongSets.unmodifiable(this.map.keySet());
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

      public int getOrDefault(long key, int defaultValue) {
         return this.map.getOrDefault(key, defaultValue);
      }

      public void forEach(BiConsumer action) {
         this.map.forEach(action);
      }

      public void replaceAll(BiFunction function) {
         throw new UnsupportedOperationException();
      }

      public int putIfAbsent(long key, int value) {
         throw new UnsupportedOperationException();
      }

      public boolean remove(long key, int value) {
         throw new UnsupportedOperationException();
      }

      public int replace(long key, int value) {
         throw new UnsupportedOperationException();
      }

      public boolean replace(long key, int oldValue, int newValue) {
         throw new UnsupportedOperationException();
      }

      public int computeIfAbsent(long key, LongToIntFunction mappingFunction) {
         throw new UnsupportedOperationException();
      }

      public int computeIfAbsentNullable(long key, LongFunction mappingFunction) {
         throw new UnsupportedOperationException();
      }

      public int computeIfAbsent(long key, Long2IntFunction mappingFunction) {
         throw new UnsupportedOperationException();
      }

      public int computeIfPresent(long key, BiFunction remappingFunction) {
         throw new UnsupportedOperationException();
      }

      public int compute(long key, BiFunction remappingFunction) {
         throw new UnsupportedOperationException();
      }

      public int merge(long key, int value, BiFunction remappingFunction) {
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
      public Integer replace(Long key, Integer value) {
         throw new UnsupportedOperationException();
      }

      /** @deprecated */
      @Deprecated
      public boolean replace(Long key, Integer oldValue, Integer newValue) {
         throw new UnsupportedOperationException();
      }

      /** @deprecated */
      @Deprecated
      public Integer putIfAbsent(Long key, Integer value) {
         throw new UnsupportedOperationException();
      }

      /** @deprecated */
      @Deprecated
      public Integer computeIfAbsent(Long key, Function mappingFunction) {
         throw new UnsupportedOperationException();
      }

      /** @deprecated */
      @Deprecated
      public Integer computeIfPresent(Long key, BiFunction remappingFunction) {
         throw new UnsupportedOperationException();
      }

      /** @deprecated */
      @Deprecated
      public Integer compute(Long key, BiFunction remappingFunction) {
         throw new UnsupportedOperationException();
      }

      /** @deprecated */
      @Deprecated
      public Integer merge(Long key, Integer value, BiFunction remappingFunction) {
         throw new UnsupportedOperationException();
      }
   }
}
