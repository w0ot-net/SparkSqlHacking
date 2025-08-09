package shaded.parquet.it.unimi.dsi.fastutil.longs;

import java.io.Serializable;
import java.util.NoSuchElementException;

public final class LongSortedSets {
   public static final EmptySet EMPTY_SET = new EmptySet();

   private LongSortedSets() {
   }

   public static LongSortedSet singleton(long element) {
      return new Singleton(element);
   }

   public static LongSortedSet singleton(long element, LongComparator comparator) {
      return new Singleton(element, comparator);
   }

   public static LongSortedSet singleton(Object element) {
      return new Singleton((Long)element);
   }

   public static LongSortedSet singleton(Object element, LongComparator comparator) {
      return new Singleton((Long)element, comparator);
   }

   public static LongSortedSet synchronize(LongSortedSet s) {
      return new SynchronizedSortedSet(s);
   }

   public static LongSortedSet synchronize(LongSortedSet s, Object sync) {
      return new SynchronizedSortedSet(s, sync);
   }

   public static LongSortedSet unmodifiable(LongSortedSet s) {
      return new UnmodifiableSortedSet(s);
   }

   public static class EmptySet extends LongSets.EmptySet implements LongSortedSet, Serializable, Cloneable {
      private static final long serialVersionUID = -7046029254386353129L;

      protected EmptySet() {
      }

      public LongBidirectionalIterator iterator(long from) {
         return LongIterators.EMPTY_ITERATOR;
      }

      public LongSortedSet subSet(long from, long to) {
         return LongSortedSets.EMPTY_SET;
      }

      public LongSortedSet headSet(long from) {
         return LongSortedSets.EMPTY_SET;
      }

      public LongSortedSet tailSet(long to) {
         return LongSortedSets.EMPTY_SET;
      }

      public long firstLong() {
         throw new NoSuchElementException();
      }

      public long lastLong() {
         throw new NoSuchElementException();
      }

      public LongComparator comparator() {
         return null;
      }

      /** @deprecated */
      @Deprecated
      public LongSortedSet subSet(Long from, Long to) {
         return LongSortedSets.EMPTY_SET;
      }

      /** @deprecated */
      @Deprecated
      public LongSortedSet headSet(Long from) {
         return LongSortedSets.EMPTY_SET;
      }

      /** @deprecated */
      @Deprecated
      public LongSortedSet tailSet(Long to) {
         return LongSortedSets.EMPTY_SET;
      }

      /** @deprecated */
      @Deprecated
      public Long first() {
         throw new NoSuchElementException();
      }

      /** @deprecated */
      @Deprecated
      public Long last() {
         throw new NoSuchElementException();
      }

      public Object clone() {
         return LongSortedSets.EMPTY_SET;
      }

      private Object readResolve() {
         return LongSortedSets.EMPTY_SET;
      }
   }

   public static class Singleton extends LongSets.Singleton implements LongSortedSet, Serializable, Cloneable {
      private static final long serialVersionUID = -7046029254386353129L;
      final LongComparator comparator;

      protected Singleton(long element, LongComparator comparator) {
         super(element);
         this.comparator = comparator;
      }

      Singleton(long element) {
         this(element, (LongComparator)null);
      }

      final int compare(long k1, long k2) {
         return this.comparator == null ? Long.compare(k1, k2) : this.comparator.compare(k1, k2);
      }

      public LongBidirectionalIterator iterator(long from) {
         LongBidirectionalIterator i = this.iterator();
         if (this.compare(this.element, from) <= 0) {
            i.nextLong();
         }

         return i;
      }

      public LongComparator comparator() {
         return this.comparator;
      }

      public LongSpliterator spliterator() {
         return LongSpliterators.singleton(this.element, this.comparator);
      }

      public LongSortedSet subSet(long from, long to) {
         return (LongSortedSet)(this.compare(from, this.element) <= 0 && this.compare(this.element, to) < 0 ? this : LongSortedSets.EMPTY_SET);
      }

      public LongSortedSet headSet(long to) {
         return (LongSortedSet)(this.compare(this.element, to) < 0 ? this : LongSortedSets.EMPTY_SET);
      }

      public LongSortedSet tailSet(long from) {
         return (LongSortedSet)(this.compare(from, this.element) <= 0 ? this : LongSortedSets.EMPTY_SET);
      }

      public long firstLong() {
         return this.element;
      }

      public long lastLong() {
         return this.element;
      }

      /** @deprecated */
      @Deprecated
      public LongSortedSet subSet(Long from, Long to) {
         return this.subSet(from, to);
      }

      /** @deprecated */
      @Deprecated
      public LongSortedSet headSet(Long to) {
         return this.headSet(to);
      }

      /** @deprecated */
      @Deprecated
      public LongSortedSet tailSet(Long from) {
         return this.tailSet(from);
      }

      /** @deprecated */
      @Deprecated
      public Long first() {
         return this.element;
      }

      /** @deprecated */
      @Deprecated
      public Long last() {
         return this.element;
      }
   }

   public static class SynchronizedSortedSet extends LongSets.SynchronizedSet implements LongSortedSet, Serializable {
      private static final long serialVersionUID = -7046029254386353129L;
      protected final LongSortedSet sortedSet;

      protected SynchronizedSortedSet(LongSortedSet s, Object sync) {
         super(s, sync);
         this.sortedSet = s;
      }

      protected SynchronizedSortedSet(LongSortedSet s) {
         super(s);
         this.sortedSet = s;
      }

      public LongComparator comparator() {
         synchronized(this.sync) {
            return this.sortedSet.comparator();
         }
      }

      public LongSortedSet subSet(long from, long to) {
         return new SynchronizedSortedSet(this.sortedSet.subSet(from, to), this.sync);
      }

      public LongSortedSet headSet(long to) {
         return new SynchronizedSortedSet(this.sortedSet.headSet(to), this.sync);
      }

      public LongSortedSet tailSet(long from) {
         return new SynchronizedSortedSet(this.sortedSet.tailSet(from), this.sync);
      }

      public LongBidirectionalIterator iterator() {
         return this.sortedSet.iterator();
      }

      public LongBidirectionalIterator iterator(long from) {
         return this.sortedSet.iterator(from);
      }

      public long firstLong() {
         synchronized(this.sync) {
            return this.sortedSet.firstLong();
         }
      }

      public long lastLong() {
         synchronized(this.sync) {
            return this.sortedSet.lastLong();
         }
      }

      /** @deprecated */
      @Deprecated
      public Long first() {
         synchronized(this.sync) {
            return this.sortedSet.first();
         }
      }

      /** @deprecated */
      @Deprecated
      public Long last() {
         synchronized(this.sync) {
            return this.sortedSet.last();
         }
      }

      /** @deprecated */
      @Deprecated
      public LongSortedSet subSet(Long from, Long to) {
         return new SynchronizedSortedSet(this.sortedSet.subSet(from, to), this.sync);
      }

      /** @deprecated */
      @Deprecated
      public LongSortedSet headSet(Long to) {
         return new SynchronizedSortedSet(this.sortedSet.headSet(to), this.sync);
      }

      /** @deprecated */
      @Deprecated
      public LongSortedSet tailSet(Long from) {
         return new SynchronizedSortedSet(this.sortedSet.tailSet(from), this.sync);
      }
   }

   public static class UnmodifiableSortedSet extends LongSets.UnmodifiableSet implements LongSortedSet, Serializable {
      private static final long serialVersionUID = -7046029254386353129L;
      protected final LongSortedSet sortedSet;

      protected UnmodifiableSortedSet(LongSortedSet s) {
         super(s);
         this.sortedSet = s;
      }

      public LongComparator comparator() {
         return this.sortedSet.comparator();
      }

      public LongSortedSet subSet(long from, long to) {
         return new UnmodifiableSortedSet(this.sortedSet.subSet(from, to));
      }

      public LongSortedSet headSet(long to) {
         return new UnmodifiableSortedSet(this.sortedSet.headSet(to));
      }

      public LongSortedSet tailSet(long from) {
         return new UnmodifiableSortedSet(this.sortedSet.tailSet(from));
      }

      public LongBidirectionalIterator iterator() {
         return LongIterators.unmodifiable(this.sortedSet.iterator());
      }

      public LongBidirectionalIterator iterator(long from) {
         return LongIterators.unmodifiable(this.sortedSet.iterator(from));
      }

      public long firstLong() {
         return this.sortedSet.firstLong();
      }

      public long lastLong() {
         return this.sortedSet.lastLong();
      }

      /** @deprecated */
      @Deprecated
      public Long first() {
         return this.sortedSet.first();
      }

      /** @deprecated */
      @Deprecated
      public Long last() {
         return this.sortedSet.last();
      }

      /** @deprecated */
      @Deprecated
      public LongSortedSet subSet(Long from, Long to) {
         return new UnmodifiableSortedSet(this.sortedSet.subSet(from, to));
      }

      /** @deprecated */
      @Deprecated
      public LongSortedSet headSet(Long to) {
         return new UnmodifiableSortedSet(this.sortedSet.headSet(to));
      }

      /** @deprecated */
      @Deprecated
      public LongSortedSet tailSet(Long from) {
         return new UnmodifiableSortedSet(this.sortedSet.tailSet(from));
      }
   }
}
