package shaded.parquet.it.unimi.dsi.fastutil.ints;

import java.io.Serializable;
import java.util.Comparator;
import java.util.NoSuchElementException;
import java.util.Objects;
import shaded.parquet.it.unimi.dsi.fastutil.objects.ObjectBidirectionalIterable;
import shaded.parquet.it.unimi.dsi.fastutil.objects.ObjectBidirectionalIterator;
import shaded.parquet.it.unimi.dsi.fastutil.objects.ObjectSortedSet;
import shaded.parquet.it.unimi.dsi.fastutil.objects.ObjectSortedSets;

public final class Int2IntSortedMaps {
   public static final EmptySortedMap EMPTY_MAP = new EmptySortedMap();

   private Int2IntSortedMaps() {
   }

   public static Comparator entryComparator(IntComparator comparator) {
      return (x, y) -> comparator.compare((Integer)x.getKey(), (Integer)y.getKey());
   }

   public static ObjectBidirectionalIterator fastIterator(Int2IntSortedMap map) {
      ObjectSortedSet<Int2IntMap.Entry> entries = map.int2IntEntrySet();
      return entries instanceof Int2IntSortedMap.FastSortedEntrySet ? ((Int2IntSortedMap.FastSortedEntrySet)entries).fastIterator() : entries.iterator();
   }

   public static ObjectBidirectionalIterable fastIterable(Int2IntSortedMap map) {
      ObjectSortedSet<Int2IntMap.Entry> entries = map.int2IntEntrySet();
      Object var2;
      if (entries instanceof Int2IntSortedMap.FastSortedEntrySet) {
         var2 = (Int2IntSortedMap.FastSortedEntrySet)entries;
         Objects.requireNonNull(entries);
         var2 = var2::fastIterator;
      } else {
         var2 = entries;
      }

      return var2;
   }

   public static Int2IntSortedMap singleton(Integer key, Integer value) {
      return new Singleton(key, value);
   }

   public static Int2IntSortedMap singleton(Integer key, Integer value, IntComparator comparator) {
      return new Singleton(key, value, comparator);
   }

   public static Int2IntSortedMap singleton(int key, int value) {
      return new Singleton(key, value);
   }

   public static Int2IntSortedMap singleton(int key, int value, IntComparator comparator) {
      return new Singleton(key, value, comparator);
   }

   public static Int2IntSortedMap synchronize(Int2IntSortedMap m) {
      return new SynchronizedSortedMap(m);
   }

   public static Int2IntSortedMap synchronize(Int2IntSortedMap m, Object sync) {
      return new SynchronizedSortedMap(m, sync);
   }

   public static Int2IntSortedMap unmodifiable(Int2IntSortedMap m) {
      return new UnmodifiableSortedMap(m);
   }

   public static class EmptySortedMap extends Int2IntMaps.EmptyMap implements Int2IntSortedMap, Serializable, Cloneable {
      private static final long serialVersionUID = -7046029254386353129L;

      protected EmptySortedMap() {
      }

      public IntComparator comparator() {
         return null;
      }

      public ObjectSortedSet int2IntEntrySet() {
         return ObjectSortedSets.EMPTY_SET;
      }

      /** @deprecated */
      @Deprecated
      public ObjectSortedSet entrySet() {
         return ObjectSortedSets.EMPTY_SET;
      }

      public IntSortedSet keySet() {
         return IntSortedSets.EMPTY_SET;
      }

      public Int2IntSortedMap subMap(int from, int to) {
         return Int2IntSortedMaps.EMPTY_MAP;
      }

      public Int2IntSortedMap headMap(int to) {
         return Int2IntSortedMaps.EMPTY_MAP;
      }

      public Int2IntSortedMap tailMap(int from) {
         return Int2IntSortedMaps.EMPTY_MAP;
      }

      public int firstIntKey() {
         throw new NoSuchElementException();
      }

      public int lastIntKey() {
         throw new NoSuchElementException();
      }

      /** @deprecated */
      @Deprecated
      public Int2IntSortedMap headMap(Integer oto) {
         return this.headMap(oto);
      }

      /** @deprecated */
      @Deprecated
      public Int2IntSortedMap tailMap(Integer ofrom) {
         return this.tailMap(ofrom);
      }

      /** @deprecated */
      @Deprecated
      public Int2IntSortedMap subMap(Integer ofrom, Integer oto) {
         return this.subMap(ofrom, oto);
      }

      /** @deprecated */
      @Deprecated
      public Integer firstKey() {
         return this.firstIntKey();
      }

      /** @deprecated */
      @Deprecated
      public Integer lastKey() {
         return this.lastIntKey();
      }
   }

   public static class Singleton extends Int2IntMaps.Singleton implements Int2IntSortedMap, Serializable, Cloneable {
      private static final long serialVersionUID = -7046029254386353129L;
      protected final IntComparator comparator;

      protected Singleton(int key, int value, IntComparator comparator) {
         super(key, value);
         this.comparator = comparator;
      }

      protected Singleton(int key, int value) {
         this(key, value, (IntComparator)null);
      }

      final int compare(int k1, int k2) {
         return this.comparator == null ? Integer.compare(k1, k2) : this.comparator.compare(k1, k2);
      }

      public IntComparator comparator() {
         return this.comparator;
      }

      public ObjectSortedSet int2IntEntrySet() {
         if (this.entries == null) {
            this.entries = ObjectSortedSets.singleton(new AbstractInt2IntMap.BasicEntry(this.key, this.value), Int2IntSortedMaps.entryComparator(this.comparator));
         }

         return (ObjectSortedSet)this.entries;
      }

      /** @deprecated */
      @Deprecated
      public ObjectSortedSet entrySet() {
         return this.int2IntEntrySet();
      }

      public IntSortedSet keySet() {
         if (this.keys == null) {
            this.keys = IntSortedSets.singleton(this.key, this.comparator);
         }

         return (IntSortedSet)this.keys;
      }

      public Int2IntSortedMap subMap(int from, int to) {
         return (Int2IntSortedMap)(this.compare(from, this.key) <= 0 && this.compare(this.key, to) < 0 ? this : Int2IntSortedMaps.EMPTY_MAP);
      }

      public Int2IntSortedMap headMap(int to) {
         return (Int2IntSortedMap)(this.compare(this.key, to) < 0 ? this : Int2IntSortedMaps.EMPTY_MAP);
      }

      public Int2IntSortedMap tailMap(int from) {
         return (Int2IntSortedMap)(this.compare(from, this.key) <= 0 ? this : Int2IntSortedMaps.EMPTY_MAP);
      }

      public int firstIntKey() {
         return this.key;
      }

      public int lastIntKey() {
         return this.key;
      }

      /** @deprecated */
      @Deprecated
      public Int2IntSortedMap headMap(Integer oto) {
         return this.headMap(oto);
      }

      /** @deprecated */
      @Deprecated
      public Int2IntSortedMap tailMap(Integer ofrom) {
         return this.tailMap(ofrom);
      }

      /** @deprecated */
      @Deprecated
      public Int2IntSortedMap subMap(Integer ofrom, Integer oto) {
         return this.subMap(ofrom, oto);
      }

      /** @deprecated */
      @Deprecated
      public Integer firstKey() {
         return this.firstIntKey();
      }

      /** @deprecated */
      @Deprecated
      public Integer lastKey() {
         return this.lastIntKey();
      }
   }

   public static class SynchronizedSortedMap extends Int2IntMaps.SynchronizedMap implements Int2IntSortedMap, Serializable {
      private static final long serialVersionUID = -7046029254386353129L;
      protected final Int2IntSortedMap sortedMap;

      protected SynchronizedSortedMap(Int2IntSortedMap m, Object sync) {
         super(m, sync);
         this.sortedMap = m;
      }

      protected SynchronizedSortedMap(Int2IntSortedMap m) {
         super(m);
         this.sortedMap = m;
      }

      public IntComparator comparator() {
         synchronized(this.sync) {
            return this.sortedMap.comparator();
         }
      }

      public ObjectSortedSet int2IntEntrySet() {
         if (this.entries == null) {
            this.entries = ObjectSortedSets.synchronize(this.sortedMap.int2IntEntrySet(), this.sync);
         }

         return (ObjectSortedSet)this.entries;
      }

      /** @deprecated */
      @Deprecated
      public ObjectSortedSet entrySet() {
         return this.int2IntEntrySet();
      }

      public IntSortedSet keySet() {
         if (this.keys == null) {
            this.keys = IntSortedSets.synchronize(this.sortedMap.keySet(), this.sync);
         }

         return (IntSortedSet)this.keys;
      }

      public Int2IntSortedMap subMap(int from, int to) {
         return new SynchronizedSortedMap(this.sortedMap.subMap(from, to), this.sync);
      }

      public Int2IntSortedMap headMap(int to) {
         return new SynchronizedSortedMap(this.sortedMap.headMap(to), this.sync);
      }

      public Int2IntSortedMap tailMap(int from) {
         return new SynchronizedSortedMap(this.sortedMap.tailMap(from), this.sync);
      }

      public int firstIntKey() {
         synchronized(this.sync) {
            return this.sortedMap.firstIntKey();
         }
      }

      public int lastIntKey() {
         synchronized(this.sync) {
            return this.sortedMap.lastIntKey();
         }
      }

      /** @deprecated */
      @Deprecated
      public Integer firstKey() {
         synchronized(this.sync) {
            return this.sortedMap.firstKey();
         }
      }

      /** @deprecated */
      @Deprecated
      public Integer lastKey() {
         synchronized(this.sync) {
            return this.sortedMap.lastKey();
         }
      }

      /** @deprecated */
      @Deprecated
      public Int2IntSortedMap subMap(Integer from, Integer to) {
         return new SynchronizedSortedMap(this.sortedMap.subMap(from, to), this.sync);
      }

      /** @deprecated */
      @Deprecated
      public Int2IntSortedMap headMap(Integer to) {
         return new SynchronizedSortedMap(this.sortedMap.headMap(to), this.sync);
      }

      /** @deprecated */
      @Deprecated
      public Int2IntSortedMap tailMap(Integer from) {
         return new SynchronizedSortedMap(this.sortedMap.tailMap(from), this.sync);
      }
   }

   public static class UnmodifiableSortedMap extends Int2IntMaps.UnmodifiableMap implements Int2IntSortedMap, Serializable {
      private static final long serialVersionUID = -7046029254386353129L;
      protected final Int2IntSortedMap sortedMap;

      protected UnmodifiableSortedMap(Int2IntSortedMap m) {
         super(m);
         this.sortedMap = m;
      }

      public IntComparator comparator() {
         return this.sortedMap.comparator();
      }

      public ObjectSortedSet int2IntEntrySet() {
         if (this.entries == null) {
            this.entries = ObjectSortedSets.unmodifiable(this.sortedMap.int2IntEntrySet());
         }

         return (ObjectSortedSet)this.entries;
      }

      /** @deprecated */
      @Deprecated
      public ObjectSortedSet entrySet() {
         return this.int2IntEntrySet();
      }

      public IntSortedSet keySet() {
         if (this.keys == null) {
            this.keys = IntSortedSets.unmodifiable(this.sortedMap.keySet());
         }

         return (IntSortedSet)this.keys;
      }

      public Int2IntSortedMap subMap(int from, int to) {
         return new UnmodifiableSortedMap(this.sortedMap.subMap(from, to));
      }

      public Int2IntSortedMap headMap(int to) {
         return new UnmodifiableSortedMap(this.sortedMap.headMap(to));
      }

      public Int2IntSortedMap tailMap(int from) {
         return new UnmodifiableSortedMap(this.sortedMap.tailMap(from));
      }

      public int firstIntKey() {
         return this.sortedMap.firstIntKey();
      }

      public int lastIntKey() {
         return this.sortedMap.lastIntKey();
      }

      /** @deprecated */
      @Deprecated
      public Integer firstKey() {
         return this.sortedMap.firstKey();
      }

      /** @deprecated */
      @Deprecated
      public Integer lastKey() {
         return this.sortedMap.lastKey();
      }

      /** @deprecated */
      @Deprecated
      public Int2IntSortedMap subMap(Integer from, Integer to) {
         return new UnmodifiableSortedMap(this.sortedMap.subMap(from, to));
      }

      /** @deprecated */
      @Deprecated
      public Int2IntSortedMap headMap(Integer to) {
         return new UnmodifiableSortedMap(this.sortedMap.headMap(to));
      }

      /** @deprecated */
      @Deprecated
      public Int2IntSortedMap tailMap(Integer from) {
         return new UnmodifiableSortedMap(this.sortedMap.tailMap(from));
      }
   }
}
