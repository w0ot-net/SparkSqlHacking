package shaded.parquet.it.unimi.dsi.fastutil.floats;

import java.io.Serializable;
import java.util.Comparator;
import java.util.NoSuchElementException;
import java.util.Objects;
import shaded.parquet.it.unimi.dsi.fastutil.objects.ObjectBidirectionalIterable;
import shaded.parquet.it.unimi.dsi.fastutil.objects.ObjectBidirectionalIterator;
import shaded.parquet.it.unimi.dsi.fastutil.objects.ObjectSortedSet;
import shaded.parquet.it.unimi.dsi.fastutil.objects.ObjectSortedSets;

public final class Float2IntSortedMaps {
   public static final EmptySortedMap EMPTY_MAP = new EmptySortedMap();

   private Float2IntSortedMaps() {
   }

   public static Comparator entryComparator(FloatComparator comparator) {
      return (x, y) -> comparator.compare((Float)x.getKey(), (Float)y.getKey());
   }

   public static ObjectBidirectionalIterator fastIterator(Float2IntSortedMap map) {
      ObjectSortedSet<Float2IntMap.Entry> entries = map.float2IntEntrySet();
      return entries instanceof Float2IntSortedMap.FastSortedEntrySet ? ((Float2IntSortedMap.FastSortedEntrySet)entries).fastIterator() : entries.iterator();
   }

   public static ObjectBidirectionalIterable fastIterable(Float2IntSortedMap map) {
      ObjectSortedSet<Float2IntMap.Entry> entries = map.float2IntEntrySet();
      Object var2;
      if (entries instanceof Float2IntSortedMap.FastSortedEntrySet) {
         var2 = (Float2IntSortedMap.FastSortedEntrySet)entries;
         Objects.requireNonNull(entries);
         var2 = var2::fastIterator;
      } else {
         var2 = entries;
      }

      return var2;
   }

   public static Float2IntSortedMap singleton(Float key, Integer value) {
      return new Singleton(key, value);
   }

   public static Float2IntSortedMap singleton(Float key, Integer value, FloatComparator comparator) {
      return new Singleton(key, value, comparator);
   }

   public static Float2IntSortedMap singleton(float key, int value) {
      return new Singleton(key, value);
   }

   public static Float2IntSortedMap singleton(float key, int value, FloatComparator comparator) {
      return new Singleton(key, value, comparator);
   }

   public static Float2IntSortedMap synchronize(Float2IntSortedMap m) {
      return new SynchronizedSortedMap(m);
   }

   public static Float2IntSortedMap synchronize(Float2IntSortedMap m, Object sync) {
      return new SynchronizedSortedMap(m, sync);
   }

   public static Float2IntSortedMap unmodifiable(Float2IntSortedMap m) {
      return new UnmodifiableSortedMap(m);
   }

   public static class EmptySortedMap extends Float2IntMaps.EmptyMap implements Float2IntSortedMap, Serializable, Cloneable {
      private static final long serialVersionUID = -7046029254386353129L;

      protected EmptySortedMap() {
      }

      public FloatComparator comparator() {
         return null;
      }

      public ObjectSortedSet float2IntEntrySet() {
         return ObjectSortedSets.EMPTY_SET;
      }

      /** @deprecated */
      @Deprecated
      public ObjectSortedSet entrySet() {
         return ObjectSortedSets.EMPTY_SET;
      }

      public FloatSortedSet keySet() {
         return FloatSortedSets.EMPTY_SET;
      }

      public Float2IntSortedMap subMap(float from, float to) {
         return Float2IntSortedMaps.EMPTY_MAP;
      }

      public Float2IntSortedMap headMap(float to) {
         return Float2IntSortedMaps.EMPTY_MAP;
      }

      public Float2IntSortedMap tailMap(float from) {
         return Float2IntSortedMaps.EMPTY_MAP;
      }

      public float firstFloatKey() {
         throw new NoSuchElementException();
      }

      public float lastFloatKey() {
         throw new NoSuchElementException();
      }

      /** @deprecated */
      @Deprecated
      public Float2IntSortedMap headMap(Float oto) {
         return this.headMap(oto);
      }

      /** @deprecated */
      @Deprecated
      public Float2IntSortedMap tailMap(Float ofrom) {
         return this.tailMap(ofrom);
      }

      /** @deprecated */
      @Deprecated
      public Float2IntSortedMap subMap(Float ofrom, Float oto) {
         return this.subMap(ofrom, oto);
      }

      /** @deprecated */
      @Deprecated
      public Float firstKey() {
         return this.firstFloatKey();
      }

      /** @deprecated */
      @Deprecated
      public Float lastKey() {
         return this.lastFloatKey();
      }
   }

   public static class Singleton extends Float2IntMaps.Singleton implements Float2IntSortedMap, Serializable, Cloneable {
      private static final long serialVersionUID = -7046029254386353129L;
      protected final FloatComparator comparator;

      protected Singleton(float key, int value, FloatComparator comparator) {
         super(key, value);
         this.comparator = comparator;
      }

      protected Singleton(float key, int value) {
         this(key, value, (FloatComparator)null);
      }

      final int compare(float k1, float k2) {
         return this.comparator == null ? Float.compare(k1, k2) : this.comparator.compare(k1, k2);
      }

      public FloatComparator comparator() {
         return this.comparator;
      }

      public ObjectSortedSet float2IntEntrySet() {
         if (this.entries == null) {
            this.entries = ObjectSortedSets.singleton(new AbstractFloat2IntMap.BasicEntry(this.key, this.value), Float2IntSortedMaps.entryComparator(this.comparator));
         }

         return (ObjectSortedSet)this.entries;
      }

      /** @deprecated */
      @Deprecated
      public ObjectSortedSet entrySet() {
         return this.float2IntEntrySet();
      }

      public FloatSortedSet keySet() {
         if (this.keys == null) {
            this.keys = FloatSortedSets.singleton(this.key, this.comparator);
         }

         return (FloatSortedSet)this.keys;
      }

      public Float2IntSortedMap subMap(float from, float to) {
         return (Float2IntSortedMap)(this.compare(from, this.key) <= 0 && this.compare(this.key, to) < 0 ? this : Float2IntSortedMaps.EMPTY_MAP);
      }

      public Float2IntSortedMap headMap(float to) {
         return (Float2IntSortedMap)(this.compare(this.key, to) < 0 ? this : Float2IntSortedMaps.EMPTY_MAP);
      }

      public Float2IntSortedMap tailMap(float from) {
         return (Float2IntSortedMap)(this.compare(from, this.key) <= 0 ? this : Float2IntSortedMaps.EMPTY_MAP);
      }

      public float firstFloatKey() {
         return this.key;
      }

      public float lastFloatKey() {
         return this.key;
      }

      /** @deprecated */
      @Deprecated
      public Float2IntSortedMap headMap(Float oto) {
         return this.headMap(oto);
      }

      /** @deprecated */
      @Deprecated
      public Float2IntSortedMap tailMap(Float ofrom) {
         return this.tailMap(ofrom);
      }

      /** @deprecated */
      @Deprecated
      public Float2IntSortedMap subMap(Float ofrom, Float oto) {
         return this.subMap(ofrom, oto);
      }

      /** @deprecated */
      @Deprecated
      public Float firstKey() {
         return this.firstFloatKey();
      }

      /** @deprecated */
      @Deprecated
      public Float lastKey() {
         return this.lastFloatKey();
      }
   }

   public static class SynchronizedSortedMap extends Float2IntMaps.SynchronizedMap implements Float2IntSortedMap, Serializable {
      private static final long serialVersionUID = -7046029254386353129L;
      protected final Float2IntSortedMap sortedMap;

      protected SynchronizedSortedMap(Float2IntSortedMap m, Object sync) {
         super(m, sync);
         this.sortedMap = m;
      }

      protected SynchronizedSortedMap(Float2IntSortedMap m) {
         super(m);
         this.sortedMap = m;
      }

      public FloatComparator comparator() {
         synchronized(this.sync) {
            return this.sortedMap.comparator();
         }
      }

      public ObjectSortedSet float2IntEntrySet() {
         if (this.entries == null) {
            this.entries = ObjectSortedSets.synchronize(this.sortedMap.float2IntEntrySet(), this.sync);
         }

         return (ObjectSortedSet)this.entries;
      }

      /** @deprecated */
      @Deprecated
      public ObjectSortedSet entrySet() {
         return this.float2IntEntrySet();
      }

      public FloatSortedSet keySet() {
         if (this.keys == null) {
            this.keys = FloatSortedSets.synchronize(this.sortedMap.keySet(), this.sync);
         }

         return (FloatSortedSet)this.keys;
      }

      public Float2IntSortedMap subMap(float from, float to) {
         return new SynchronizedSortedMap(this.sortedMap.subMap(from, to), this.sync);
      }

      public Float2IntSortedMap headMap(float to) {
         return new SynchronizedSortedMap(this.sortedMap.headMap(to), this.sync);
      }

      public Float2IntSortedMap tailMap(float from) {
         return new SynchronizedSortedMap(this.sortedMap.tailMap(from), this.sync);
      }

      public float firstFloatKey() {
         synchronized(this.sync) {
            return this.sortedMap.firstFloatKey();
         }
      }

      public float lastFloatKey() {
         synchronized(this.sync) {
            return this.sortedMap.lastFloatKey();
         }
      }

      /** @deprecated */
      @Deprecated
      public Float firstKey() {
         synchronized(this.sync) {
            return this.sortedMap.firstKey();
         }
      }

      /** @deprecated */
      @Deprecated
      public Float lastKey() {
         synchronized(this.sync) {
            return this.sortedMap.lastKey();
         }
      }

      /** @deprecated */
      @Deprecated
      public Float2IntSortedMap subMap(Float from, Float to) {
         return new SynchronizedSortedMap(this.sortedMap.subMap(from, to), this.sync);
      }

      /** @deprecated */
      @Deprecated
      public Float2IntSortedMap headMap(Float to) {
         return new SynchronizedSortedMap(this.sortedMap.headMap(to), this.sync);
      }

      /** @deprecated */
      @Deprecated
      public Float2IntSortedMap tailMap(Float from) {
         return new SynchronizedSortedMap(this.sortedMap.tailMap(from), this.sync);
      }
   }

   public static class UnmodifiableSortedMap extends Float2IntMaps.UnmodifiableMap implements Float2IntSortedMap, Serializable {
      private static final long serialVersionUID = -7046029254386353129L;
      protected final Float2IntSortedMap sortedMap;

      protected UnmodifiableSortedMap(Float2IntSortedMap m) {
         super(m);
         this.sortedMap = m;
      }

      public FloatComparator comparator() {
         return this.sortedMap.comparator();
      }

      public ObjectSortedSet float2IntEntrySet() {
         if (this.entries == null) {
            this.entries = ObjectSortedSets.unmodifiable(this.sortedMap.float2IntEntrySet());
         }

         return (ObjectSortedSet)this.entries;
      }

      /** @deprecated */
      @Deprecated
      public ObjectSortedSet entrySet() {
         return this.float2IntEntrySet();
      }

      public FloatSortedSet keySet() {
         if (this.keys == null) {
            this.keys = FloatSortedSets.unmodifiable(this.sortedMap.keySet());
         }

         return (FloatSortedSet)this.keys;
      }

      public Float2IntSortedMap subMap(float from, float to) {
         return new UnmodifiableSortedMap(this.sortedMap.subMap(from, to));
      }

      public Float2IntSortedMap headMap(float to) {
         return new UnmodifiableSortedMap(this.sortedMap.headMap(to));
      }

      public Float2IntSortedMap tailMap(float from) {
         return new UnmodifiableSortedMap(this.sortedMap.tailMap(from));
      }

      public float firstFloatKey() {
         return this.sortedMap.firstFloatKey();
      }

      public float lastFloatKey() {
         return this.sortedMap.lastFloatKey();
      }

      /** @deprecated */
      @Deprecated
      public Float firstKey() {
         return this.sortedMap.firstKey();
      }

      /** @deprecated */
      @Deprecated
      public Float lastKey() {
         return this.sortedMap.lastKey();
      }

      /** @deprecated */
      @Deprecated
      public Float2IntSortedMap subMap(Float from, Float to) {
         return new UnmodifiableSortedMap(this.sortedMap.subMap(from, to));
      }

      /** @deprecated */
      @Deprecated
      public Float2IntSortedMap headMap(Float to) {
         return new UnmodifiableSortedMap(this.sortedMap.headMap(to));
      }

      /** @deprecated */
      @Deprecated
      public Float2IntSortedMap tailMap(Float from) {
         return new UnmodifiableSortedMap(this.sortedMap.tailMap(from));
      }
   }
}
