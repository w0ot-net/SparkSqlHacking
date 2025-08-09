package org.apache.curator.shaded.com.google.common.collect;

import javax.annotation.CheckForNull;
import org.apache.curator.shaded.com.google.common.annotations.GwtIncompatible;

@ElementTypesAreNonnullByDefault
@GwtIncompatible
final class DescendingImmutableSortedMultiset extends ImmutableSortedMultiset {
   private final transient ImmutableSortedMultiset forward;

   DescendingImmutableSortedMultiset(ImmutableSortedMultiset forward) {
      this.forward = forward;
   }

   public int count(@CheckForNull Object element) {
      return this.forward.count(element);
   }

   @CheckForNull
   public Multiset.Entry firstEntry() {
      return this.forward.lastEntry();
   }

   @CheckForNull
   public Multiset.Entry lastEntry() {
      return this.forward.firstEntry();
   }

   public int size() {
      return this.forward.size();
   }

   public ImmutableSortedSet elementSet() {
      return this.forward.elementSet().descendingSet();
   }

   Multiset.Entry getEntry(int index) {
      return (Multiset.Entry)this.forward.entrySet().asList().reverse().get(index);
   }

   public ImmutableSortedMultiset descendingMultiset() {
      return this.forward;
   }

   public ImmutableSortedMultiset headMultiset(Object upperBound, BoundType boundType) {
      return this.forward.tailMultiset(upperBound, boundType).descendingMultiset();
   }

   public ImmutableSortedMultiset tailMultiset(Object lowerBound, BoundType boundType) {
      return this.forward.headMultiset(lowerBound, boundType).descendingMultiset();
   }

   boolean isPartialView() {
      return this.forward.isPartialView();
   }
}
