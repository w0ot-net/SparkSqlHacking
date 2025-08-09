package shaded.parquet.it.unimi.dsi.fastutil.doubles;

import java.io.Serializable;
import java.util.NoSuchElementException;

public final class DoubleSortedSets {
   public static final EmptySet EMPTY_SET = new EmptySet();

   private DoubleSortedSets() {
   }

   public static DoubleSortedSet singleton(double element) {
      return new Singleton(element);
   }

   public static DoubleSortedSet singleton(double element, DoubleComparator comparator) {
      return new Singleton(element, comparator);
   }

   public static DoubleSortedSet singleton(Object element) {
      return new Singleton((Double)element);
   }

   public static DoubleSortedSet singleton(Object element, DoubleComparator comparator) {
      return new Singleton((Double)element, comparator);
   }

   public static DoubleSortedSet synchronize(DoubleSortedSet s) {
      return new SynchronizedSortedSet(s);
   }

   public static DoubleSortedSet synchronize(DoubleSortedSet s, Object sync) {
      return new SynchronizedSortedSet(s, sync);
   }

   public static DoubleSortedSet unmodifiable(DoubleSortedSet s) {
      return new UnmodifiableSortedSet(s);
   }

   public static class EmptySet extends DoubleSets.EmptySet implements DoubleSortedSet, Serializable, Cloneable {
      private static final long serialVersionUID = -7046029254386353129L;

      protected EmptySet() {
      }

      public DoubleBidirectionalIterator iterator(double from) {
         return DoubleIterators.EMPTY_ITERATOR;
      }

      public DoubleSortedSet subSet(double from, double to) {
         return DoubleSortedSets.EMPTY_SET;
      }

      public DoubleSortedSet headSet(double from) {
         return DoubleSortedSets.EMPTY_SET;
      }

      public DoubleSortedSet tailSet(double to) {
         return DoubleSortedSets.EMPTY_SET;
      }

      public double firstDouble() {
         throw new NoSuchElementException();
      }

      public double lastDouble() {
         throw new NoSuchElementException();
      }

      public DoubleComparator comparator() {
         return null;
      }

      /** @deprecated */
      @Deprecated
      public DoubleSortedSet subSet(Double from, Double to) {
         return DoubleSortedSets.EMPTY_SET;
      }

      /** @deprecated */
      @Deprecated
      public DoubleSortedSet headSet(Double from) {
         return DoubleSortedSets.EMPTY_SET;
      }

      /** @deprecated */
      @Deprecated
      public DoubleSortedSet tailSet(Double to) {
         return DoubleSortedSets.EMPTY_SET;
      }

      /** @deprecated */
      @Deprecated
      public Double first() {
         throw new NoSuchElementException();
      }

      /** @deprecated */
      @Deprecated
      public Double last() {
         throw new NoSuchElementException();
      }

      public Object clone() {
         return DoubleSortedSets.EMPTY_SET;
      }

      private Object readResolve() {
         return DoubleSortedSets.EMPTY_SET;
      }
   }

   public static class Singleton extends DoubleSets.Singleton implements DoubleSortedSet, Serializable, Cloneable {
      private static final long serialVersionUID = -7046029254386353129L;
      final DoubleComparator comparator;

      protected Singleton(double element, DoubleComparator comparator) {
         super(element);
         this.comparator = comparator;
      }

      Singleton(double element) {
         this(element, (DoubleComparator)null);
      }

      final int compare(double k1, double k2) {
         return this.comparator == null ? Double.compare(k1, k2) : this.comparator.compare(k1, k2);
      }

      public DoubleBidirectionalIterator iterator(double from) {
         DoubleBidirectionalIterator i = this.iterator();
         if (this.compare(this.element, from) <= 0) {
            i.nextDouble();
         }

         return i;
      }

      public DoubleComparator comparator() {
         return this.comparator;
      }

      public DoubleSpliterator spliterator() {
         return DoubleSpliterators.singleton(this.element, this.comparator);
      }

      public DoubleSortedSet subSet(double from, double to) {
         return (DoubleSortedSet)(this.compare(from, this.element) <= 0 && this.compare(this.element, to) < 0 ? this : DoubleSortedSets.EMPTY_SET);
      }

      public DoubleSortedSet headSet(double to) {
         return (DoubleSortedSet)(this.compare(this.element, to) < 0 ? this : DoubleSortedSets.EMPTY_SET);
      }

      public DoubleSortedSet tailSet(double from) {
         return (DoubleSortedSet)(this.compare(from, this.element) <= 0 ? this : DoubleSortedSets.EMPTY_SET);
      }

      public double firstDouble() {
         return this.element;
      }

      public double lastDouble() {
         return this.element;
      }

      /** @deprecated */
      @Deprecated
      public DoubleSortedSet subSet(Double from, Double to) {
         return this.subSet(from, to);
      }

      /** @deprecated */
      @Deprecated
      public DoubleSortedSet headSet(Double to) {
         return this.headSet(to);
      }

      /** @deprecated */
      @Deprecated
      public DoubleSortedSet tailSet(Double from) {
         return this.tailSet(from);
      }

      /** @deprecated */
      @Deprecated
      public Double first() {
         return this.element;
      }

      /** @deprecated */
      @Deprecated
      public Double last() {
         return this.element;
      }
   }

   public static class SynchronizedSortedSet extends DoubleSets.SynchronizedSet implements DoubleSortedSet, Serializable {
      private static final long serialVersionUID = -7046029254386353129L;
      protected final DoubleSortedSet sortedSet;

      protected SynchronizedSortedSet(DoubleSortedSet s, Object sync) {
         super(s, sync);
         this.sortedSet = s;
      }

      protected SynchronizedSortedSet(DoubleSortedSet s) {
         super(s);
         this.sortedSet = s;
      }

      public DoubleComparator comparator() {
         synchronized(this.sync) {
            return this.sortedSet.comparator();
         }
      }

      public DoubleSortedSet subSet(double from, double to) {
         return new SynchronizedSortedSet(this.sortedSet.subSet(from, to), this.sync);
      }

      public DoubleSortedSet headSet(double to) {
         return new SynchronizedSortedSet(this.sortedSet.headSet(to), this.sync);
      }

      public DoubleSortedSet tailSet(double from) {
         return new SynchronizedSortedSet(this.sortedSet.tailSet(from), this.sync);
      }

      public DoubleBidirectionalIterator iterator() {
         return this.sortedSet.iterator();
      }

      public DoubleBidirectionalIterator iterator(double from) {
         return this.sortedSet.iterator(from);
      }

      public double firstDouble() {
         synchronized(this.sync) {
            return this.sortedSet.firstDouble();
         }
      }

      public double lastDouble() {
         synchronized(this.sync) {
            return this.sortedSet.lastDouble();
         }
      }

      /** @deprecated */
      @Deprecated
      public Double first() {
         synchronized(this.sync) {
            return this.sortedSet.first();
         }
      }

      /** @deprecated */
      @Deprecated
      public Double last() {
         synchronized(this.sync) {
            return this.sortedSet.last();
         }
      }

      /** @deprecated */
      @Deprecated
      public DoubleSortedSet subSet(Double from, Double to) {
         return new SynchronizedSortedSet(this.sortedSet.subSet(from, to), this.sync);
      }

      /** @deprecated */
      @Deprecated
      public DoubleSortedSet headSet(Double to) {
         return new SynchronizedSortedSet(this.sortedSet.headSet(to), this.sync);
      }

      /** @deprecated */
      @Deprecated
      public DoubleSortedSet tailSet(Double from) {
         return new SynchronizedSortedSet(this.sortedSet.tailSet(from), this.sync);
      }
   }

   public static class UnmodifiableSortedSet extends DoubleSets.UnmodifiableSet implements DoubleSortedSet, Serializable {
      private static final long serialVersionUID = -7046029254386353129L;
      protected final DoubleSortedSet sortedSet;

      protected UnmodifiableSortedSet(DoubleSortedSet s) {
         super(s);
         this.sortedSet = s;
      }

      public DoubleComparator comparator() {
         return this.sortedSet.comparator();
      }

      public DoubleSortedSet subSet(double from, double to) {
         return new UnmodifiableSortedSet(this.sortedSet.subSet(from, to));
      }

      public DoubleSortedSet headSet(double to) {
         return new UnmodifiableSortedSet(this.sortedSet.headSet(to));
      }

      public DoubleSortedSet tailSet(double from) {
         return new UnmodifiableSortedSet(this.sortedSet.tailSet(from));
      }

      public DoubleBidirectionalIterator iterator() {
         return DoubleIterators.unmodifiable(this.sortedSet.iterator());
      }

      public DoubleBidirectionalIterator iterator(double from) {
         return DoubleIterators.unmodifiable(this.sortedSet.iterator(from));
      }

      public double firstDouble() {
         return this.sortedSet.firstDouble();
      }

      public double lastDouble() {
         return this.sortedSet.lastDouble();
      }

      /** @deprecated */
      @Deprecated
      public Double first() {
         return this.sortedSet.first();
      }

      /** @deprecated */
      @Deprecated
      public Double last() {
         return this.sortedSet.last();
      }

      /** @deprecated */
      @Deprecated
      public DoubleSortedSet subSet(Double from, Double to) {
         return new UnmodifiableSortedSet(this.sortedSet.subSet(from, to));
      }

      /** @deprecated */
      @Deprecated
      public DoubleSortedSet headSet(Double to) {
         return new UnmodifiableSortedSet(this.sortedSet.headSet(to));
      }

      /** @deprecated */
      @Deprecated
      public DoubleSortedSet tailSet(Double from) {
         return new UnmodifiableSortedSet(this.sortedSet.tailSet(from));
      }
   }
}
