package shaded.parquet.it.unimi.dsi.fastutil.ints;

import java.io.Serializable;
import java.util.NoSuchElementException;

public final class IntSortedSets {
   public static final EmptySet EMPTY_SET = new EmptySet();

   private IntSortedSets() {
   }

   public static IntSortedSet singleton(int element) {
      return new Singleton(element);
   }

   public static IntSortedSet singleton(int element, IntComparator comparator) {
      return new Singleton(element, comparator);
   }

   public static IntSortedSet singleton(Object element) {
      return new Singleton((Integer)element);
   }

   public static IntSortedSet singleton(Object element, IntComparator comparator) {
      return new Singleton((Integer)element, comparator);
   }

   public static IntSortedSet synchronize(IntSortedSet s) {
      return new SynchronizedSortedSet(s);
   }

   public static IntSortedSet synchronize(IntSortedSet s, Object sync) {
      return new SynchronizedSortedSet(s, sync);
   }

   public static IntSortedSet unmodifiable(IntSortedSet s) {
      return new UnmodifiableSortedSet(s);
   }

   public static class EmptySet extends IntSets.EmptySet implements IntSortedSet, Serializable, Cloneable {
      private static final long serialVersionUID = -7046029254386353129L;

      protected EmptySet() {
      }

      public IntBidirectionalIterator iterator(int from) {
         return IntIterators.EMPTY_ITERATOR;
      }

      public IntSortedSet subSet(int from, int to) {
         return IntSortedSets.EMPTY_SET;
      }

      public IntSortedSet headSet(int from) {
         return IntSortedSets.EMPTY_SET;
      }

      public IntSortedSet tailSet(int to) {
         return IntSortedSets.EMPTY_SET;
      }

      public int firstInt() {
         throw new NoSuchElementException();
      }

      public int lastInt() {
         throw new NoSuchElementException();
      }

      public IntComparator comparator() {
         return null;
      }

      /** @deprecated */
      @Deprecated
      public IntSortedSet subSet(Integer from, Integer to) {
         return IntSortedSets.EMPTY_SET;
      }

      /** @deprecated */
      @Deprecated
      public IntSortedSet headSet(Integer from) {
         return IntSortedSets.EMPTY_SET;
      }

      /** @deprecated */
      @Deprecated
      public IntSortedSet tailSet(Integer to) {
         return IntSortedSets.EMPTY_SET;
      }

      /** @deprecated */
      @Deprecated
      public Integer first() {
         throw new NoSuchElementException();
      }

      /** @deprecated */
      @Deprecated
      public Integer last() {
         throw new NoSuchElementException();
      }

      public Object clone() {
         return IntSortedSets.EMPTY_SET;
      }

      private Object readResolve() {
         return IntSortedSets.EMPTY_SET;
      }
   }

   public static class Singleton extends IntSets.Singleton implements IntSortedSet, Serializable, Cloneable {
      private static final long serialVersionUID = -7046029254386353129L;
      final IntComparator comparator;

      protected Singleton(int element, IntComparator comparator) {
         super(element);
         this.comparator = comparator;
      }

      Singleton(int element) {
         this(element, (IntComparator)null);
      }

      final int compare(int k1, int k2) {
         return this.comparator == null ? Integer.compare(k1, k2) : this.comparator.compare(k1, k2);
      }

      public IntBidirectionalIterator iterator(int from) {
         IntBidirectionalIterator i = this.iterator();
         if (this.compare(this.element, from) <= 0) {
            i.nextInt();
         }

         return i;
      }

      public IntComparator comparator() {
         return this.comparator;
      }

      public IntSpliterator spliterator() {
         return IntSpliterators.singleton(this.element, this.comparator);
      }

      public IntSortedSet subSet(int from, int to) {
         return (IntSortedSet)(this.compare(from, this.element) <= 0 && this.compare(this.element, to) < 0 ? this : IntSortedSets.EMPTY_SET);
      }

      public IntSortedSet headSet(int to) {
         return (IntSortedSet)(this.compare(this.element, to) < 0 ? this : IntSortedSets.EMPTY_SET);
      }

      public IntSortedSet tailSet(int from) {
         return (IntSortedSet)(this.compare(from, this.element) <= 0 ? this : IntSortedSets.EMPTY_SET);
      }

      public int firstInt() {
         return this.element;
      }

      public int lastInt() {
         return this.element;
      }

      /** @deprecated */
      @Deprecated
      public IntSortedSet subSet(Integer from, Integer to) {
         return this.subSet(from, to);
      }

      /** @deprecated */
      @Deprecated
      public IntSortedSet headSet(Integer to) {
         return this.headSet(to);
      }

      /** @deprecated */
      @Deprecated
      public IntSortedSet tailSet(Integer from) {
         return this.tailSet(from);
      }

      /** @deprecated */
      @Deprecated
      public Integer first() {
         return this.element;
      }

      /** @deprecated */
      @Deprecated
      public Integer last() {
         return this.element;
      }
   }

   public static class SynchronizedSortedSet extends IntSets.SynchronizedSet implements IntSortedSet, Serializable {
      private static final long serialVersionUID = -7046029254386353129L;
      protected final IntSortedSet sortedSet;

      protected SynchronizedSortedSet(IntSortedSet s, Object sync) {
         super(s, sync);
         this.sortedSet = s;
      }

      protected SynchronizedSortedSet(IntSortedSet s) {
         super(s);
         this.sortedSet = s;
      }

      public IntComparator comparator() {
         synchronized(this.sync) {
            return this.sortedSet.comparator();
         }
      }

      public IntSortedSet subSet(int from, int to) {
         return new SynchronizedSortedSet(this.sortedSet.subSet(from, to), this.sync);
      }

      public IntSortedSet headSet(int to) {
         return new SynchronizedSortedSet(this.sortedSet.headSet(to), this.sync);
      }

      public IntSortedSet tailSet(int from) {
         return new SynchronizedSortedSet(this.sortedSet.tailSet(from), this.sync);
      }

      public IntBidirectionalIterator iterator() {
         return this.sortedSet.iterator();
      }

      public IntBidirectionalIterator iterator(int from) {
         return this.sortedSet.iterator(from);
      }

      public int firstInt() {
         synchronized(this.sync) {
            return this.sortedSet.firstInt();
         }
      }

      public int lastInt() {
         synchronized(this.sync) {
            return this.sortedSet.lastInt();
         }
      }

      /** @deprecated */
      @Deprecated
      public Integer first() {
         synchronized(this.sync) {
            return this.sortedSet.first();
         }
      }

      /** @deprecated */
      @Deprecated
      public Integer last() {
         synchronized(this.sync) {
            return this.sortedSet.last();
         }
      }

      /** @deprecated */
      @Deprecated
      public IntSortedSet subSet(Integer from, Integer to) {
         return new SynchronizedSortedSet(this.sortedSet.subSet(from, to), this.sync);
      }

      /** @deprecated */
      @Deprecated
      public IntSortedSet headSet(Integer to) {
         return new SynchronizedSortedSet(this.sortedSet.headSet(to), this.sync);
      }

      /** @deprecated */
      @Deprecated
      public IntSortedSet tailSet(Integer from) {
         return new SynchronizedSortedSet(this.sortedSet.tailSet(from), this.sync);
      }
   }

   public static class UnmodifiableSortedSet extends IntSets.UnmodifiableSet implements IntSortedSet, Serializable {
      private static final long serialVersionUID = -7046029254386353129L;
      protected final IntSortedSet sortedSet;

      protected UnmodifiableSortedSet(IntSortedSet s) {
         super(s);
         this.sortedSet = s;
      }

      public IntComparator comparator() {
         return this.sortedSet.comparator();
      }

      public IntSortedSet subSet(int from, int to) {
         return new UnmodifiableSortedSet(this.sortedSet.subSet(from, to));
      }

      public IntSortedSet headSet(int to) {
         return new UnmodifiableSortedSet(this.sortedSet.headSet(to));
      }

      public IntSortedSet tailSet(int from) {
         return new UnmodifiableSortedSet(this.sortedSet.tailSet(from));
      }

      public IntBidirectionalIterator iterator() {
         return IntIterators.unmodifiable(this.sortedSet.iterator());
      }

      public IntBidirectionalIterator iterator(int from) {
         return IntIterators.unmodifiable(this.sortedSet.iterator(from));
      }

      public int firstInt() {
         return this.sortedSet.firstInt();
      }

      public int lastInt() {
         return this.sortedSet.lastInt();
      }

      /** @deprecated */
      @Deprecated
      public Integer first() {
         return this.sortedSet.first();
      }

      /** @deprecated */
      @Deprecated
      public Integer last() {
         return this.sortedSet.last();
      }

      /** @deprecated */
      @Deprecated
      public IntSortedSet subSet(Integer from, Integer to) {
         return new UnmodifiableSortedSet(this.sortedSet.subSet(from, to));
      }

      /** @deprecated */
      @Deprecated
      public IntSortedSet headSet(Integer to) {
         return new UnmodifiableSortedSet(this.sortedSet.headSet(to));
      }

      /** @deprecated */
      @Deprecated
      public IntSortedSet tailSet(Integer from) {
         return new UnmodifiableSortedSet(this.sortedSet.tailSet(from));
      }
   }
}
