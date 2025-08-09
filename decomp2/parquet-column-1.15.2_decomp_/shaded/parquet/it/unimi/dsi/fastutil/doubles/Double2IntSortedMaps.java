package shaded.parquet.it.unimi.dsi.fastutil.doubles;

import java.io.Serializable;
import java.util.Comparator;
import java.util.NoSuchElementException;
import java.util.Objects;
import shaded.parquet.it.unimi.dsi.fastutil.objects.ObjectBidirectionalIterable;
import shaded.parquet.it.unimi.dsi.fastutil.objects.ObjectBidirectionalIterator;
import shaded.parquet.it.unimi.dsi.fastutil.objects.ObjectSortedSet;
import shaded.parquet.it.unimi.dsi.fastutil.objects.ObjectSortedSets;

public final class Double2IntSortedMaps {
   public static final EmptySortedMap EMPTY_MAP = new EmptySortedMap();

   private Double2IntSortedMaps() {
   }

   public static Comparator entryComparator(DoubleComparator comparator) {
      return (x, y) -> comparator.compare((Double)x.getKey(), (Double)y.getKey());
   }

   public static ObjectBidirectionalIterator fastIterator(Double2IntSortedMap map) {
      ObjectSortedSet<Double2IntMap.Entry> entries = map.double2IntEntrySet();
      return entries instanceof Double2IntSortedMap.FastSortedEntrySet ? ((Double2IntSortedMap.FastSortedEntrySet)entries).fastIterator() : entries.iterator();
   }

   public static ObjectBidirectionalIterable fastIterable(Double2IntSortedMap map) {
      ObjectSortedSet<Double2IntMap.Entry> entries = map.double2IntEntrySet();
      Object var2;
      if (entries instanceof Double2IntSortedMap.FastSortedEntrySet) {
         var2 = (Double2IntSortedMap.FastSortedEntrySet)entries;
         Objects.requireNonNull(entries);
         var2 = var2::fastIterator;
      } else {
         var2 = entries;
      }

      return var2;
   }

   public static Double2IntSortedMap singleton(Double key, Integer value) {
      return new Singleton(key, value);
   }

   public static Double2IntSortedMap singleton(Double key, Integer value, DoubleComparator comparator) {
      return new Singleton(key, value, comparator);
   }

   public static Double2IntSortedMap singleton(double key, int value) {
      return new Singleton(key, value);
   }

   public static Double2IntSortedMap singleton(double key, int value, DoubleComparator comparator) {
      return new Singleton(key, value, comparator);
   }

   public static Double2IntSortedMap synchronize(Double2IntSortedMap m) {
      return new SynchronizedSortedMap(m);
   }

   public static Double2IntSortedMap synchronize(Double2IntSortedMap m, Object sync) {
      return new SynchronizedSortedMap(m, sync);
   }

   public static Double2IntSortedMap unmodifiable(Double2IntSortedMap m) {
      return new UnmodifiableSortedMap(m);
   }

   public static class EmptySortedMap extends Double2IntMaps.EmptyMap implements Double2IntSortedMap, Serializable, Cloneable {
      private static final long serialVersionUID = -7046029254386353129L;

      protected EmptySortedMap() {
      }

      public DoubleComparator comparator() {
         return null;
      }

      public ObjectSortedSet double2IntEntrySet() {
         return ObjectSortedSets.EMPTY_SET;
      }

      /** @deprecated */
      @Deprecated
      public ObjectSortedSet entrySet() {
         return ObjectSortedSets.EMPTY_SET;
      }

      public DoubleSortedSet keySet() {
         return DoubleSortedSets.EMPTY_SET;
      }

      public Double2IntSortedMap subMap(double from, double to) {
         return Double2IntSortedMaps.EMPTY_MAP;
      }

      public Double2IntSortedMap headMap(double to) {
         return Double2IntSortedMaps.EMPTY_MAP;
      }

      public Double2IntSortedMap tailMap(double from) {
         return Double2IntSortedMaps.EMPTY_MAP;
      }

      public double firstDoubleKey() {
         throw new NoSuchElementException();
      }

      public double lastDoubleKey() {
         throw new NoSuchElementException();
      }

      /** @deprecated */
      @Deprecated
      public Double2IntSortedMap headMap(Double oto) {
         return this.headMap(oto);
      }

      /** @deprecated */
      @Deprecated
      public Double2IntSortedMap tailMap(Double ofrom) {
         return this.tailMap(ofrom);
      }

      /** @deprecated */
      @Deprecated
      public Double2IntSortedMap subMap(Double ofrom, Double oto) {
         return this.subMap(ofrom, oto);
      }

      /** @deprecated */
      @Deprecated
      public Double firstKey() {
         return this.firstDoubleKey();
      }

      /** @deprecated */
      @Deprecated
      public Double lastKey() {
         return this.lastDoubleKey();
      }
   }

   public static class Singleton extends Double2IntMaps.Singleton implements Double2IntSortedMap, Serializable, Cloneable {
      private static final long serialVersionUID = -7046029254386353129L;
      protected final DoubleComparator comparator;

      protected Singleton(double key, int value, DoubleComparator comparator) {
         super(key, value);
         this.comparator = comparator;
      }

      protected Singleton(double key, int value) {
         this(key, value, (DoubleComparator)null);
      }

      final int compare(double k1, double k2) {
         return this.comparator == null ? Double.compare(k1, k2) : this.comparator.compare(k1, k2);
      }

      public DoubleComparator comparator() {
         return this.comparator;
      }

      public ObjectSortedSet double2IntEntrySet() {
         if (this.entries == null) {
            this.entries = ObjectSortedSets.singleton(new AbstractDouble2IntMap.BasicEntry(this.key, this.value), Double2IntSortedMaps.entryComparator(this.comparator));
         }

         return (ObjectSortedSet)this.entries;
      }

      /** @deprecated */
      @Deprecated
      public ObjectSortedSet entrySet() {
         return this.double2IntEntrySet();
      }

      public DoubleSortedSet keySet() {
         if (this.keys == null) {
            this.keys = DoubleSortedSets.singleton(this.key, this.comparator);
         }

         return (DoubleSortedSet)this.keys;
      }

      public Double2IntSortedMap subMap(double from, double to) {
         return (Double2IntSortedMap)(this.compare(from, this.key) <= 0 && this.compare(this.key, to) < 0 ? this : Double2IntSortedMaps.EMPTY_MAP);
      }

      public Double2IntSortedMap headMap(double to) {
         return (Double2IntSortedMap)(this.compare(this.key, to) < 0 ? this : Double2IntSortedMaps.EMPTY_MAP);
      }

      public Double2IntSortedMap tailMap(double from) {
         return (Double2IntSortedMap)(this.compare(from, this.key) <= 0 ? this : Double2IntSortedMaps.EMPTY_MAP);
      }

      public double firstDoubleKey() {
         return this.key;
      }

      public double lastDoubleKey() {
         return this.key;
      }

      /** @deprecated */
      @Deprecated
      public Double2IntSortedMap headMap(Double oto) {
         return this.headMap(oto);
      }

      /** @deprecated */
      @Deprecated
      public Double2IntSortedMap tailMap(Double ofrom) {
         return this.tailMap(ofrom);
      }

      /** @deprecated */
      @Deprecated
      public Double2IntSortedMap subMap(Double ofrom, Double oto) {
         return this.subMap(ofrom, oto);
      }

      /** @deprecated */
      @Deprecated
      public Double firstKey() {
         return this.firstDoubleKey();
      }

      /** @deprecated */
      @Deprecated
      public Double lastKey() {
         return this.lastDoubleKey();
      }
   }

   public static class SynchronizedSortedMap extends Double2IntMaps.SynchronizedMap implements Double2IntSortedMap, Serializable {
      private static final long serialVersionUID = -7046029254386353129L;
      protected final Double2IntSortedMap sortedMap;

      protected SynchronizedSortedMap(Double2IntSortedMap m, Object sync) {
         super(m, sync);
         this.sortedMap = m;
      }

      protected SynchronizedSortedMap(Double2IntSortedMap m) {
         super(m);
         this.sortedMap = m;
      }

      public DoubleComparator comparator() {
         synchronized(this.sync) {
            return this.sortedMap.comparator();
         }
      }

      public ObjectSortedSet double2IntEntrySet() {
         if (this.entries == null) {
            this.entries = ObjectSortedSets.synchronize(this.sortedMap.double2IntEntrySet(), this.sync);
         }

         return (ObjectSortedSet)this.entries;
      }

      /** @deprecated */
      @Deprecated
      public ObjectSortedSet entrySet() {
         return this.double2IntEntrySet();
      }

      public DoubleSortedSet keySet() {
         if (this.keys == null) {
            this.keys = DoubleSortedSets.synchronize(this.sortedMap.keySet(), this.sync);
         }

         return (DoubleSortedSet)this.keys;
      }

      public Double2IntSortedMap subMap(double from, double to) {
         return new SynchronizedSortedMap(this.sortedMap.subMap(from, to), this.sync);
      }

      public Double2IntSortedMap headMap(double to) {
         return new SynchronizedSortedMap(this.sortedMap.headMap(to), this.sync);
      }

      public Double2IntSortedMap tailMap(double from) {
         return new SynchronizedSortedMap(this.sortedMap.tailMap(from), this.sync);
      }

      public double firstDoubleKey() {
         synchronized(this.sync) {
            return this.sortedMap.firstDoubleKey();
         }
      }

      public double lastDoubleKey() {
         synchronized(this.sync) {
            return this.sortedMap.lastDoubleKey();
         }
      }

      /** @deprecated */
      @Deprecated
      public Double firstKey() {
         synchronized(this.sync) {
            return this.sortedMap.firstKey();
         }
      }

      /** @deprecated */
      @Deprecated
      public Double lastKey() {
         synchronized(this.sync) {
            return this.sortedMap.lastKey();
         }
      }

      /** @deprecated */
      @Deprecated
      public Double2IntSortedMap subMap(Double from, Double to) {
         return new SynchronizedSortedMap(this.sortedMap.subMap(from, to), this.sync);
      }

      /** @deprecated */
      @Deprecated
      public Double2IntSortedMap headMap(Double to) {
         return new SynchronizedSortedMap(this.sortedMap.headMap(to), this.sync);
      }

      /** @deprecated */
      @Deprecated
      public Double2IntSortedMap tailMap(Double from) {
         return new SynchronizedSortedMap(this.sortedMap.tailMap(from), this.sync);
      }
   }

   public static class UnmodifiableSortedMap extends Double2IntMaps.UnmodifiableMap implements Double2IntSortedMap, Serializable {
      private static final long serialVersionUID = -7046029254386353129L;
      protected final Double2IntSortedMap sortedMap;

      protected UnmodifiableSortedMap(Double2IntSortedMap m) {
         super(m);
         this.sortedMap = m;
      }

      public DoubleComparator comparator() {
         return this.sortedMap.comparator();
      }

      public ObjectSortedSet double2IntEntrySet() {
         if (this.entries == null) {
            this.entries = ObjectSortedSets.unmodifiable(this.sortedMap.double2IntEntrySet());
         }

         return (ObjectSortedSet)this.entries;
      }

      /** @deprecated */
      @Deprecated
      public ObjectSortedSet entrySet() {
         return this.double2IntEntrySet();
      }

      public DoubleSortedSet keySet() {
         if (this.keys == null) {
            this.keys = DoubleSortedSets.unmodifiable(this.sortedMap.keySet());
         }

         return (DoubleSortedSet)this.keys;
      }

      public Double2IntSortedMap subMap(double from, double to) {
         return new UnmodifiableSortedMap(this.sortedMap.subMap(from, to));
      }

      public Double2IntSortedMap headMap(double to) {
         return new UnmodifiableSortedMap(this.sortedMap.headMap(to));
      }

      public Double2IntSortedMap tailMap(double from) {
         return new UnmodifiableSortedMap(this.sortedMap.tailMap(from));
      }

      public double firstDoubleKey() {
         return this.sortedMap.firstDoubleKey();
      }

      public double lastDoubleKey() {
         return this.sortedMap.lastDoubleKey();
      }

      /** @deprecated */
      @Deprecated
      public Double firstKey() {
         return this.sortedMap.firstKey();
      }

      /** @deprecated */
      @Deprecated
      public Double lastKey() {
         return this.sortedMap.lastKey();
      }

      /** @deprecated */
      @Deprecated
      public Double2IntSortedMap subMap(Double from, Double to) {
         return new UnmodifiableSortedMap(this.sortedMap.subMap(from, to));
      }

      /** @deprecated */
      @Deprecated
      public Double2IntSortedMap headMap(Double to) {
         return new UnmodifiableSortedMap(this.sortedMap.headMap(to));
      }

      /** @deprecated */
      @Deprecated
      public Double2IntSortedMap tailMap(Double from) {
         return new UnmodifiableSortedMap(this.sortedMap.tailMap(from));
      }
   }
}
