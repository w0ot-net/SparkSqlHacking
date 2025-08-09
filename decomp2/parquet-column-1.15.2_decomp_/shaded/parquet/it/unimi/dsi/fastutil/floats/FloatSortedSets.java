package shaded.parquet.it.unimi.dsi.fastutil.floats;

import java.io.Serializable;
import java.util.NoSuchElementException;
import shaded.parquet.it.unimi.dsi.fastutil.SafeMath;
import shaded.parquet.it.unimi.dsi.fastutil.doubles.DoubleSpliterator;
import shaded.parquet.it.unimi.dsi.fastutil.doubles.DoubleSpliterators;

public final class FloatSortedSets {
   public static final EmptySet EMPTY_SET = new EmptySet();

   private FloatSortedSets() {
   }

   public static FloatSortedSet singleton(float element) {
      return new Singleton(element);
   }

   public static FloatSortedSet singleton(float element, FloatComparator comparator) {
      return new Singleton(element, comparator);
   }

   public static FloatSortedSet singleton(Object element) {
      return new Singleton((Float)element);
   }

   public static FloatSortedSet singleton(Object element, FloatComparator comparator) {
      return new Singleton((Float)element, comparator);
   }

   public static FloatSortedSet synchronize(FloatSortedSet s) {
      return new SynchronizedSortedSet(s);
   }

   public static FloatSortedSet synchronize(FloatSortedSet s, Object sync) {
      return new SynchronizedSortedSet(s, sync);
   }

   public static FloatSortedSet unmodifiable(FloatSortedSet s) {
      return new UnmodifiableSortedSet(s);
   }

   public static class EmptySet extends FloatSets.EmptySet implements FloatSortedSet, Serializable, Cloneable {
      private static final long serialVersionUID = -7046029254386353129L;

      protected EmptySet() {
      }

      public FloatBidirectionalIterator iterator(float from) {
         return FloatIterators.EMPTY_ITERATOR;
      }

      public FloatSortedSet subSet(float from, float to) {
         return FloatSortedSets.EMPTY_SET;
      }

      public FloatSortedSet headSet(float from) {
         return FloatSortedSets.EMPTY_SET;
      }

      public FloatSortedSet tailSet(float to) {
         return FloatSortedSets.EMPTY_SET;
      }

      public float firstFloat() {
         throw new NoSuchElementException();
      }

      public float lastFloat() {
         throw new NoSuchElementException();
      }

      public FloatComparator comparator() {
         return null;
      }

      /** @deprecated */
      @Deprecated
      public FloatSortedSet subSet(Float from, Float to) {
         return FloatSortedSets.EMPTY_SET;
      }

      /** @deprecated */
      @Deprecated
      public FloatSortedSet headSet(Float from) {
         return FloatSortedSets.EMPTY_SET;
      }

      /** @deprecated */
      @Deprecated
      public FloatSortedSet tailSet(Float to) {
         return FloatSortedSets.EMPTY_SET;
      }

      /** @deprecated */
      @Deprecated
      public Float first() {
         throw new NoSuchElementException();
      }

      /** @deprecated */
      @Deprecated
      public Float last() {
         throw new NoSuchElementException();
      }

      public Object clone() {
         return FloatSortedSets.EMPTY_SET;
      }

      private Object readResolve() {
         return FloatSortedSets.EMPTY_SET;
      }
   }

   public static class Singleton extends FloatSets.Singleton implements FloatSortedSet, Serializable, Cloneable {
      private static final long serialVersionUID = -7046029254386353129L;
      final FloatComparator comparator;

      protected Singleton(float element, FloatComparator comparator) {
         super(element);
         this.comparator = comparator;
      }

      Singleton(float element) {
         this(element, (FloatComparator)null);
      }

      final int compare(float k1, float k2) {
         return this.comparator == null ? Float.compare(k1, k2) : this.comparator.compare(k1, k2);
      }

      public FloatBidirectionalIterator iterator(float from) {
         FloatBidirectionalIterator i = this.iterator();
         if (this.compare(this.element, from) <= 0) {
            i.nextFloat();
         }

         return i;
      }

      public FloatComparator comparator() {
         return this.comparator;
      }

      public FloatSpliterator spliterator() {
         return FloatSpliterators.singleton(this.element, this.comparator);
      }

      public FloatSortedSet subSet(float from, float to) {
         return (FloatSortedSet)(this.compare(from, this.element) <= 0 && this.compare(this.element, to) < 0 ? this : FloatSortedSets.EMPTY_SET);
      }

      public FloatSortedSet headSet(float to) {
         return (FloatSortedSet)(this.compare(this.element, to) < 0 ? this : FloatSortedSets.EMPTY_SET);
      }

      public FloatSortedSet tailSet(float from) {
         return (FloatSortedSet)(this.compare(from, this.element) <= 0 ? this : FloatSortedSets.EMPTY_SET);
      }

      public float firstFloat() {
         return this.element;
      }

      public float lastFloat() {
         return this.element;
      }

      public DoubleSpliterator doubleSpliterator() {
         return DoubleSpliterators.singleton((double)this.element, (left, right) -> this.comparator().compare(SafeMath.safeDoubleToFloat(left), SafeMath.safeDoubleToFloat(right)));
      }

      /** @deprecated */
      @Deprecated
      public FloatSortedSet subSet(Float from, Float to) {
         return this.subSet(from, to);
      }

      /** @deprecated */
      @Deprecated
      public FloatSortedSet headSet(Float to) {
         return this.headSet(to);
      }

      /** @deprecated */
      @Deprecated
      public FloatSortedSet tailSet(Float from) {
         return this.tailSet(from);
      }

      /** @deprecated */
      @Deprecated
      public Float first() {
         return this.element;
      }

      /** @deprecated */
      @Deprecated
      public Float last() {
         return this.element;
      }
   }

   public static class SynchronizedSortedSet extends FloatSets.SynchronizedSet implements FloatSortedSet, Serializable {
      private static final long serialVersionUID = -7046029254386353129L;
      protected final FloatSortedSet sortedSet;

      protected SynchronizedSortedSet(FloatSortedSet s, Object sync) {
         super(s, sync);
         this.sortedSet = s;
      }

      protected SynchronizedSortedSet(FloatSortedSet s) {
         super(s);
         this.sortedSet = s;
      }

      public FloatComparator comparator() {
         synchronized(this.sync) {
            return this.sortedSet.comparator();
         }
      }

      public FloatSortedSet subSet(float from, float to) {
         return new SynchronizedSortedSet(this.sortedSet.subSet(from, to), this.sync);
      }

      public FloatSortedSet headSet(float to) {
         return new SynchronizedSortedSet(this.sortedSet.headSet(to), this.sync);
      }

      public FloatSortedSet tailSet(float from) {
         return new SynchronizedSortedSet(this.sortedSet.tailSet(from), this.sync);
      }

      public FloatBidirectionalIterator iterator() {
         return this.sortedSet.iterator();
      }

      public FloatBidirectionalIterator iterator(float from) {
         return this.sortedSet.iterator(from);
      }

      public float firstFloat() {
         synchronized(this.sync) {
            return this.sortedSet.firstFloat();
         }
      }

      public float lastFloat() {
         synchronized(this.sync) {
            return this.sortedSet.lastFloat();
         }
      }

      /** @deprecated */
      @Deprecated
      public Float first() {
         synchronized(this.sync) {
            return this.sortedSet.first();
         }
      }

      /** @deprecated */
      @Deprecated
      public Float last() {
         synchronized(this.sync) {
            return this.sortedSet.last();
         }
      }

      /** @deprecated */
      @Deprecated
      public FloatSortedSet subSet(Float from, Float to) {
         return new SynchronizedSortedSet(this.sortedSet.subSet(from, to), this.sync);
      }

      /** @deprecated */
      @Deprecated
      public FloatSortedSet headSet(Float to) {
         return new SynchronizedSortedSet(this.sortedSet.headSet(to), this.sync);
      }

      /** @deprecated */
      @Deprecated
      public FloatSortedSet tailSet(Float from) {
         return new SynchronizedSortedSet(this.sortedSet.tailSet(from), this.sync);
      }
   }

   public static class UnmodifiableSortedSet extends FloatSets.UnmodifiableSet implements FloatSortedSet, Serializable {
      private static final long serialVersionUID = -7046029254386353129L;
      protected final FloatSortedSet sortedSet;

      protected UnmodifiableSortedSet(FloatSortedSet s) {
         super(s);
         this.sortedSet = s;
      }

      public FloatComparator comparator() {
         return this.sortedSet.comparator();
      }

      public FloatSortedSet subSet(float from, float to) {
         return new UnmodifiableSortedSet(this.sortedSet.subSet(from, to));
      }

      public FloatSortedSet headSet(float to) {
         return new UnmodifiableSortedSet(this.sortedSet.headSet(to));
      }

      public FloatSortedSet tailSet(float from) {
         return new UnmodifiableSortedSet(this.sortedSet.tailSet(from));
      }

      public FloatBidirectionalIterator iterator() {
         return FloatIterators.unmodifiable(this.sortedSet.iterator());
      }

      public FloatBidirectionalIterator iterator(float from) {
         return FloatIterators.unmodifiable(this.sortedSet.iterator(from));
      }

      public float firstFloat() {
         return this.sortedSet.firstFloat();
      }

      public float lastFloat() {
         return this.sortedSet.lastFloat();
      }

      /** @deprecated */
      @Deprecated
      public Float first() {
         return this.sortedSet.first();
      }

      /** @deprecated */
      @Deprecated
      public Float last() {
         return this.sortedSet.last();
      }

      /** @deprecated */
      @Deprecated
      public FloatSortedSet subSet(Float from, Float to) {
         return new UnmodifiableSortedSet(this.sortedSet.subSet(from, to));
      }

      /** @deprecated */
      @Deprecated
      public FloatSortedSet headSet(Float to) {
         return new UnmodifiableSortedSet(this.sortedSet.headSet(to));
      }

      /** @deprecated */
      @Deprecated
      public FloatSortedSet tailSet(Float from) {
         return new UnmodifiableSortedSet(this.sortedSet.tailSet(from));
      }
   }
}
