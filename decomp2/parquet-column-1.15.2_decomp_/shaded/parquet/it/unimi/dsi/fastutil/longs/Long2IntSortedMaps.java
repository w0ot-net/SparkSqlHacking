package shaded.parquet.it.unimi.dsi.fastutil.longs;

import java.io.Serializable;
import java.util.Comparator;
import java.util.NoSuchElementException;
import java.util.Objects;
import shaded.parquet.it.unimi.dsi.fastutil.objects.ObjectBidirectionalIterable;
import shaded.parquet.it.unimi.dsi.fastutil.objects.ObjectBidirectionalIterator;
import shaded.parquet.it.unimi.dsi.fastutil.objects.ObjectSortedSet;
import shaded.parquet.it.unimi.dsi.fastutil.objects.ObjectSortedSets;

public final class Long2IntSortedMaps {
   public static final EmptySortedMap EMPTY_MAP = new EmptySortedMap();

   private Long2IntSortedMaps() {
   }

   public static Comparator entryComparator(LongComparator comparator) {
      return (x, y) -> comparator.compare((Long)x.getKey(), (Long)y.getKey());
   }

   public static ObjectBidirectionalIterator fastIterator(Long2IntSortedMap map) {
      ObjectSortedSet<Long2IntMap.Entry> entries = map.long2IntEntrySet();
      return entries instanceof Long2IntSortedMap.FastSortedEntrySet ? ((Long2IntSortedMap.FastSortedEntrySet)entries).fastIterator() : entries.iterator();
   }

   public static ObjectBidirectionalIterable fastIterable(Long2IntSortedMap map) {
      ObjectSortedSet<Long2IntMap.Entry> entries = map.long2IntEntrySet();
      Object var2;
      if (entries instanceof Long2IntSortedMap.FastSortedEntrySet) {
         var2 = (Long2IntSortedMap.FastSortedEntrySet)entries;
         Objects.requireNonNull(entries);
         var2 = var2::fastIterator;
      } else {
         var2 = entries;
      }

      return var2;
   }

   public static Long2IntSortedMap singleton(Long key, Integer value) {
      return new Singleton(key, value);
   }

   public static Long2IntSortedMap singleton(Long key, Integer value, LongComparator comparator) {
      return new Singleton(key, value, comparator);
   }

   public static Long2IntSortedMap singleton(long key, int value) {
      return new Singleton(key, value);
   }

   public static Long2IntSortedMap singleton(long key, int value, LongComparator comparator) {
      return new Singleton(key, value, comparator);
   }

   public static Long2IntSortedMap synchronize(Long2IntSortedMap m) {
      return new SynchronizedSortedMap(m);
   }

   public static Long2IntSortedMap synchronize(Long2IntSortedMap m, Object sync) {
      return new SynchronizedSortedMap(m, sync);
   }

   public static Long2IntSortedMap unmodifiable(Long2IntSortedMap m) {
      return new UnmodifiableSortedMap(m);
   }

   public static class EmptySortedMap extends Long2IntMaps.EmptyMap implements Long2IntSortedMap, Serializable, Cloneable {
      private static final long serialVersionUID = -7046029254386353129L;

      protected EmptySortedMap() {
      }

      public LongComparator comparator() {
         return null;
      }

      public ObjectSortedSet long2IntEntrySet() {
         return ObjectSortedSets.EMPTY_SET;
      }

      /** @deprecated */
      @Deprecated
      public ObjectSortedSet entrySet() {
         return ObjectSortedSets.EMPTY_SET;
      }

      public LongSortedSet keySet() {
         return LongSortedSets.EMPTY_SET;
      }

      public Long2IntSortedMap subMap(long from, long to) {
         return Long2IntSortedMaps.EMPTY_MAP;
      }

      public Long2IntSortedMap headMap(long to) {
         return Long2IntSortedMaps.EMPTY_MAP;
      }

      public Long2IntSortedMap tailMap(long from) {
         return Long2IntSortedMaps.EMPTY_MAP;
      }

      public long firstLongKey() {
         throw new NoSuchElementException();
      }

      public long lastLongKey() {
         throw new NoSuchElementException();
      }

      /** @deprecated */
      @Deprecated
      public Long2IntSortedMap headMap(Long oto) {
         return this.headMap(oto);
      }

      /** @deprecated */
      @Deprecated
      public Long2IntSortedMap tailMap(Long ofrom) {
         return this.tailMap(ofrom);
      }

      /** @deprecated */
      @Deprecated
      public Long2IntSortedMap subMap(Long ofrom, Long oto) {
         return this.subMap(ofrom, oto);
      }

      /** @deprecated */
      @Deprecated
      public Long firstKey() {
         return this.firstLongKey();
      }

      /** @deprecated */
      @Deprecated
      public Long lastKey() {
         return this.lastLongKey();
      }
   }

   public static class Singleton extends Long2IntMaps.Singleton implements Long2IntSortedMap, Serializable, Cloneable {
      private static final long serialVersionUID = -7046029254386353129L;
      protected final LongComparator comparator;

      protected Singleton(long key, int value, LongComparator comparator) {
         super(key, value);
         this.comparator = comparator;
      }

      protected Singleton(long key, int value) {
         this(key, value, (LongComparator)null);
      }

      final int compare(long k1, long k2) {
         return this.comparator == null ? Long.compare(k1, k2) : this.comparator.compare(k1, k2);
      }

      public LongComparator comparator() {
         return this.comparator;
      }

      public ObjectSortedSet long2IntEntrySet() {
         if (this.entries == null) {
            this.entries = ObjectSortedSets.singleton(new AbstractLong2IntMap.BasicEntry(this.key, this.value), Long2IntSortedMaps.entryComparator(this.comparator));
         }

         return (ObjectSortedSet)this.entries;
      }

      /** @deprecated */
      @Deprecated
      public ObjectSortedSet entrySet() {
         return this.long2IntEntrySet();
      }

      public LongSortedSet keySet() {
         if (this.keys == null) {
            this.keys = LongSortedSets.singleton(this.key, this.comparator);
         }

         return (LongSortedSet)this.keys;
      }

      public Long2IntSortedMap subMap(long from, long to) {
         return (Long2IntSortedMap)(this.compare(from, this.key) <= 0 && this.compare(this.key, to) < 0 ? this : Long2IntSortedMaps.EMPTY_MAP);
      }

      public Long2IntSortedMap headMap(long to) {
         return (Long2IntSortedMap)(this.compare(this.key, to) < 0 ? this : Long2IntSortedMaps.EMPTY_MAP);
      }

      public Long2IntSortedMap tailMap(long from) {
         return (Long2IntSortedMap)(this.compare(from, this.key) <= 0 ? this : Long2IntSortedMaps.EMPTY_MAP);
      }

      public long firstLongKey() {
         return this.key;
      }

      public long lastLongKey() {
         return this.key;
      }

      /** @deprecated */
      @Deprecated
      public Long2IntSortedMap headMap(Long oto) {
         return this.headMap(oto);
      }

      /** @deprecated */
      @Deprecated
      public Long2IntSortedMap tailMap(Long ofrom) {
         return this.tailMap(ofrom);
      }

      /** @deprecated */
      @Deprecated
      public Long2IntSortedMap subMap(Long ofrom, Long oto) {
         return this.subMap(ofrom, oto);
      }

      /** @deprecated */
      @Deprecated
      public Long firstKey() {
         return this.firstLongKey();
      }

      /** @deprecated */
      @Deprecated
      public Long lastKey() {
         return this.lastLongKey();
      }
   }

   public static class SynchronizedSortedMap extends Long2IntMaps.SynchronizedMap implements Long2IntSortedMap, Serializable {
      private static final long serialVersionUID = -7046029254386353129L;
      protected final Long2IntSortedMap sortedMap;

      protected SynchronizedSortedMap(Long2IntSortedMap m, Object sync) {
         super(m, sync);
         this.sortedMap = m;
      }

      protected SynchronizedSortedMap(Long2IntSortedMap m) {
         super(m);
         this.sortedMap = m;
      }

      public LongComparator comparator() {
         synchronized(this.sync) {
            return this.sortedMap.comparator();
         }
      }

      public ObjectSortedSet long2IntEntrySet() {
         if (this.entries == null) {
            this.entries = ObjectSortedSets.synchronize(this.sortedMap.long2IntEntrySet(), this.sync);
         }

         return (ObjectSortedSet)this.entries;
      }

      /** @deprecated */
      @Deprecated
      public ObjectSortedSet entrySet() {
         return this.long2IntEntrySet();
      }

      public LongSortedSet keySet() {
         if (this.keys == null) {
            this.keys = LongSortedSets.synchronize(this.sortedMap.keySet(), this.sync);
         }

         return (LongSortedSet)this.keys;
      }

      public Long2IntSortedMap subMap(long from, long to) {
         return new SynchronizedSortedMap(this.sortedMap.subMap(from, to), this.sync);
      }

      public Long2IntSortedMap headMap(long to) {
         return new SynchronizedSortedMap(this.sortedMap.headMap(to), this.sync);
      }

      public Long2IntSortedMap tailMap(long from) {
         return new SynchronizedSortedMap(this.sortedMap.tailMap(from), this.sync);
      }

      public long firstLongKey() {
         synchronized(this.sync) {
            return this.sortedMap.firstLongKey();
         }
      }

      public long lastLongKey() {
         synchronized(this.sync) {
            return this.sortedMap.lastLongKey();
         }
      }

      /** @deprecated */
      @Deprecated
      public Long firstKey() {
         synchronized(this.sync) {
            return this.sortedMap.firstKey();
         }
      }

      /** @deprecated */
      @Deprecated
      public Long lastKey() {
         synchronized(this.sync) {
            return this.sortedMap.lastKey();
         }
      }

      /** @deprecated */
      @Deprecated
      public Long2IntSortedMap subMap(Long from, Long to) {
         return new SynchronizedSortedMap(this.sortedMap.subMap(from, to), this.sync);
      }

      /** @deprecated */
      @Deprecated
      public Long2IntSortedMap headMap(Long to) {
         return new SynchronizedSortedMap(this.sortedMap.headMap(to), this.sync);
      }

      /** @deprecated */
      @Deprecated
      public Long2IntSortedMap tailMap(Long from) {
         return new SynchronizedSortedMap(this.sortedMap.tailMap(from), this.sync);
      }
   }

   public static class UnmodifiableSortedMap extends Long2IntMaps.UnmodifiableMap implements Long2IntSortedMap, Serializable {
      private static final long serialVersionUID = -7046029254386353129L;
      protected final Long2IntSortedMap sortedMap;

      protected UnmodifiableSortedMap(Long2IntSortedMap m) {
         super(m);
         this.sortedMap = m;
      }

      public LongComparator comparator() {
         return this.sortedMap.comparator();
      }

      public ObjectSortedSet long2IntEntrySet() {
         if (this.entries == null) {
            this.entries = ObjectSortedSets.unmodifiable(this.sortedMap.long2IntEntrySet());
         }

         return (ObjectSortedSet)this.entries;
      }

      /** @deprecated */
      @Deprecated
      public ObjectSortedSet entrySet() {
         return this.long2IntEntrySet();
      }

      public LongSortedSet keySet() {
         if (this.keys == null) {
            this.keys = LongSortedSets.unmodifiable(this.sortedMap.keySet());
         }

         return (LongSortedSet)this.keys;
      }

      public Long2IntSortedMap subMap(long from, long to) {
         return new UnmodifiableSortedMap(this.sortedMap.subMap(from, to));
      }

      public Long2IntSortedMap headMap(long to) {
         return new UnmodifiableSortedMap(this.sortedMap.headMap(to));
      }

      public Long2IntSortedMap tailMap(long from) {
         return new UnmodifiableSortedMap(this.sortedMap.tailMap(from));
      }

      public long firstLongKey() {
         return this.sortedMap.firstLongKey();
      }

      public long lastLongKey() {
         return this.sortedMap.lastLongKey();
      }

      /** @deprecated */
      @Deprecated
      public Long firstKey() {
         return this.sortedMap.firstKey();
      }

      /** @deprecated */
      @Deprecated
      public Long lastKey() {
         return this.sortedMap.lastKey();
      }

      /** @deprecated */
      @Deprecated
      public Long2IntSortedMap subMap(Long from, Long to) {
         return new UnmodifiableSortedMap(this.sortedMap.subMap(from, to));
      }

      /** @deprecated */
      @Deprecated
      public Long2IntSortedMap headMap(Long to) {
         return new UnmodifiableSortedMap(this.sortedMap.headMap(to));
      }

      /** @deprecated */
      @Deprecated
      public Long2IntSortedMap tailMap(Long from) {
         return new UnmodifiableSortedMap(this.sortedMap.tailMap(from));
      }
   }
}
