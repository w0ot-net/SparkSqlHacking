package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import java.util.Comparator;
import java.util.Iterator;
import java.util.NavigableSet;
import javax.annotation.CheckForNull;

@ElementTypesAreNonnullByDefault
@GwtCompatible(
   emulated = true
)
public abstract class ForwardingSortedMultiset extends ForwardingMultiset implements SortedMultiset {
   protected ForwardingSortedMultiset() {
   }

   protected abstract SortedMultiset delegate();

   public NavigableSet elementSet() {
      return this.delegate().elementSet();
   }

   public Comparator comparator() {
      return this.delegate().comparator();
   }

   public SortedMultiset descendingMultiset() {
      return this.delegate().descendingMultiset();
   }

   @CheckForNull
   public Multiset.Entry firstEntry() {
      return this.delegate().firstEntry();
   }

   @CheckForNull
   protected Multiset.Entry standardFirstEntry() {
      Iterator<Multiset.Entry<E>> entryIterator = this.entrySet().iterator();
      if (!entryIterator.hasNext()) {
         return null;
      } else {
         Multiset.Entry<E> entry = (Multiset.Entry)entryIterator.next();
         return Multisets.immutableEntry(entry.getElement(), entry.getCount());
      }
   }

   @CheckForNull
   public Multiset.Entry lastEntry() {
      return this.delegate().lastEntry();
   }

   @CheckForNull
   protected Multiset.Entry standardLastEntry() {
      Iterator<Multiset.Entry<E>> entryIterator = this.descendingMultiset().entrySet().iterator();
      if (!entryIterator.hasNext()) {
         return null;
      } else {
         Multiset.Entry<E> entry = (Multiset.Entry)entryIterator.next();
         return Multisets.immutableEntry(entry.getElement(), entry.getCount());
      }
   }

   @CheckForNull
   public Multiset.Entry pollFirstEntry() {
      return this.delegate().pollFirstEntry();
   }

   @CheckForNull
   protected Multiset.Entry standardPollFirstEntry() {
      Iterator<Multiset.Entry<E>> entryIterator = this.entrySet().iterator();
      if (!entryIterator.hasNext()) {
         return null;
      } else {
         Multiset.Entry<E> entry = (Multiset.Entry)entryIterator.next();
         entry = Multisets.immutableEntry(entry.getElement(), entry.getCount());
         entryIterator.remove();
         return entry;
      }
   }

   @CheckForNull
   public Multiset.Entry pollLastEntry() {
      return this.delegate().pollLastEntry();
   }

   @CheckForNull
   protected Multiset.Entry standardPollLastEntry() {
      Iterator<Multiset.Entry<E>> entryIterator = this.descendingMultiset().entrySet().iterator();
      if (!entryIterator.hasNext()) {
         return null;
      } else {
         Multiset.Entry<E> entry = (Multiset.Entry)entryIterator.next();
         entry = Multisets.immutableEntry(entry.getElement(), entry.getCount());
         entryIterator.remove();
         return entry;
      }
   }

   public SortedMultiset headMultiset(@ParametricNullness Object upperBound, BoundType boundType) {
      return this.delegate().headMultiset(upperBound, boundType);
   }

   public SortedMultiset subMultiset(@ParametricNullness Object lowerBound, BoundType lowerBoundType, @ParametricNullness Object upperBound, BoundType upperBoundType) {
      return this.delegate().subMultiset(lowerBound, lowerBoundType, upperBound, upperBoundType);
   }

   protected SortedMultiset standardSubMultiset(@ParametricNullness Object lowerBound, BoundType lowerBoundType, @ParametricNullness Object upperBound, BoundType upperBoundType) {
      return this.tailMultiset(lowerBound, lowerBoundType).headMultiset(upperBound, upperBoundType);
   }

   public SortedMultiset tailMultiset(@ParametricNullness Object lowerBound, BoundType boundType) {
      return this.delegate().tailMultiset(lowerBound, boundType);
   }

   protected class StandardElementSet extends SortedMultisets.NavigableElementSet {
      public StandardElementSet() {
         super(ForwardingSortedMultiset.this);
      }
   }

   protected abstract class StandardDescendingMultiset extends DescendingMultiset {
      public StandardDescendingMultiset() {
      }

      SortedMultiset forwardMultiset() {
         return ForwardingSortedMultiset.this;
      }
   }
}
