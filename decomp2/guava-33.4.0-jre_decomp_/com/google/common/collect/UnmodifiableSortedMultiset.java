package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.google.errorprone.annotations.concurrent.LazyInit;
import java.util.Comparator;
import java.util.NavigableSet;
import javax.annotation.CheckForNull;

@ElementTypesAreNonnullByDefault
@GwtCompatible(
   emulated = true
)
final class UnmodifiableSortedMultiset extends Multisets.UnmodifiableMultiset implements SortedMultiset {
   @LazyInit
   @CheckForNull
   private transient UnmodifiableSortedMultiset descendingMultiset;
   private static final long serialVersionUID = 0L;

   UnmodifiableSortedMultiset(SortedMultiset delegate) {
      super(delegate);
   }

   protected SortedMultiset delegate() {
      return (SortedMultiset)super.delegate();
   }

   public Comparator comparator() {
      return this.delegate().comparator();
   }

   NavigableSet createElementSet() {
      return Sets.unmodifiableNavigableSet(this.delegate().elementSet());
   }

   public NavigableSet elementSet() {
      return (NavigableSet)super.elementSet();
   }

   public SortedMultiset descendingMultiset() {
      UnmodifiableSortedMultiset<E> result = this.descendingMultiset;
      if (result == null) {
         result = new UnmodifiableSortedMultiset(this.delegate().descendingMultiset());
         result.descendingMultiset = this;
         return this.descendingMultiset = result;
      } else {
         return result;
      }
   }

   @CheckForNull
   public Multiset.Entry firstEntry() {
      return this.delegate().firstEntry();
   }

   @CheckForNull
   public Multiset.Entry lastEntry() {
      return this.delegate().lastEntry();
   }

   @CheckForNull
   public Multiset.Entry pollFirstEntry() {
      throw new UnsupportedOperationException();
   }

   @CheckForNull
   public Multiset.Entry pollLastEntry() {
      throw new UnsupportedOperationException();
   }

   public SortedMultiset headMultiset(@ParametricNullness Object upperBound, BoundType boundType) {
      return Multisets.unmodifiableSortedMultiset(this.delegate().headMultiset(upperBound, boundType));
   }

   public SortedMultiset subMultiset(@ParametricNullness Object lowerBound, BoundType lowerBoundType, @ParametricNullness Object upperBound, BoundType upperBoundType) {
      return Multisets.unmodifiableSortedMultiset(this.delegate().subMultiset(lowerBound, lowerBoundType, upperBound, upperBoundType));
   }

   public SortedMultiset tailMultiset(@ParametricNullness Object lowerBound, BoundType boundType) {
      return Multisets.unmodifiableSortedMultiset(this.delegate().tailMultiset(lowerBound, boundType));
   }
}
