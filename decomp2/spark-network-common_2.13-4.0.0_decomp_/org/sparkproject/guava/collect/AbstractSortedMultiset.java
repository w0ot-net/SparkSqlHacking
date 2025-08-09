package org.sparkproject.guava.collect;

import com.google.errorprone.annotations.concurrent.LazyInit;
import java.util.Comparator;
import java.util.Iterator;
import java.util.NavigableSet;
import javax.annotation.CheckForNull;
import org.sparkproject.guava.annotations.GwtCompatible;
import org.sparkproject.guava.base.Preconditions;

@ElementTypesAreNonnullByDefault
@GwtCompatible(
   emulated = true
)
abstract class AbstractSortedMultiset extends AbstractMultiset implements SortedMultiset {
   @GwtTransient
   final Comparator comparator;
   @LazyInit
   @CheckForNull
   private transient SortedMultiset descendingMultiset;

   AbstractSortedMultiset() {
      this(Ordering.natural());
   }

   AbstractSortedMultiset(Comparator comparator) {
      this.comparator = (Comparator)Preconditions.checkNotNull(comparator);
   }

   public NavigableSet elementSet() {
      return (NavigableSet)super.elementSet();
   }

   NavigableSet createElementSet() {
      return new SortedMultisets.NavigableElementSet(this);
   }

   public Comparator comparator() {
      return this.comparator;
   }

   @CheckForNull
   public Multiset.Entry firstEntry() {
      Iterator<Multiset.Entry<E>> entryIterator = this.entryIterator();
      return entryIterator.hasNext() ? (Multiset.Entry)entryIterator.next() : null;
   }

   @CheckForNull
   public Multiset.Entry lastEntry() {
      Iterator<Multiset.Entry<E>> entryIterator = this.descendingEntryIterator();
      return entryIterator.hasNext() ? (Multiset.Entry)entryIterator.next() : null;
   }

   @CheckForNull
   public Multiset.Entry pollFirstEntry() {
      Iterator<Multiset.Entry<E>> entryIterator = this.entryIterator();
      if (entryIterator.hasNext()) {
         Multiset.Entry<E> result = (Multiset.Entry)entryIterator.next();
         result = Multisets.immutableEntry(result.getElement(), result.getCount());
         entryIterator.remove();
         return result;
      } else {
         return null;
      }
   }

   @CheckForNull
   public Multiset.Entry pollLastEntry() {
      Iterator<Multiset.Entry<E>> entryIterator = this.descendingEntryIterator();
      if (entryIterator.hasNext()) {
         Multiset.Entry<E> result = (Multiset.Entry)entryIterator.next();
         result = Multisets.immutableEntry(result.getElement(), result.getCount());
         entryIterator.remove();
         return result;
      } else {
         return null;
      }
   }

   public SortedMultiset subMultiset(@ParametricNullness Object fromElement, BoundType fromBoundType, @ParametricNullness Object toElement, BoundType toBoundType) {
      Preconditions.checkNotNull(fromBoundType);
      Preconditions.checkNotNull(toBoundType);
      return this.tailMultiset(fromElement, fromBoundType).headMultiset(toElement, toBoundType);
   }

   abstract Iterator descendingEntryIterator();

   Iterator descendingIterator() {
      return Multisets.iteratorImpl(this.descendingMultiset());
   }

   public SortedMultiset descendingMultiset() {
      SortedMultiset<E> result = this.descendingMultiset;
      return result == null ? (this.descendingMultiset = this.createDescendingMultiset()) : result;
   }

   SortedMultiset createDescendingMultiset() {
      class DescendingMultisetImpl extends DescendingMultiset {
         SortedMultiset forwardMultiset() {
            return AbstractSortedMultiset.this;
         }

         Iterator entryIterator() {
            return AbstractSortedMultiset.this.descendingEntryIterator();
         }

         public Iterator iterator() {
            return AbstractSortedMultiset.this.descendingIterator();
         }
      }

      return new DescendingMultisetImpl();
   }
}
