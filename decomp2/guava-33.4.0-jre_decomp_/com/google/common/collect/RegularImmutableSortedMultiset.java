package com.google.common.collect;

import com.google.common.annotations.GwtIncompatible;
import com.google.common.annotations.J2ktIncompatible;
import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.Preconditions;
import com.google.common.primitives.Ints;
import java.util.Comparator;
import java.util.function.ObjIntConsumer;
import javax.annotation.CheckForNull;

@ElementTypesAreNonnullByDefault
@GwtIncompatible
final class RegularImmutableSortedMultiset extends ImmutableSortedMultiset {
   private static final long[] ZERO_CUMULATIVE_COUNTS = new long[]{0L};
   static final ImmutableSortedMultiset NATURAL_EMPTY_MULTISET = new RegularImmutableSortedMultiset(Ordering.natural());
   @VisibleForTesting
   final transient RegularImmutableSortedSet elementSet;
   private final transient long[] cumulativeCounts;
   private final transient int offset;
   private final transient int length;

   RegularImmutableSortedMultiset(Comparator comparator) {
      this.elementSet = ImmutableSortedSet.emptySet(comparator);
      this.cumulativeCounts = ZERO_CUMULATIVE_COUNTS;
      this.offset = 0;
      this.length = 0;
   }

   RegularImmutableSortedMultiset(RegularImmutableSortedSet elementSet, long[] cumulativeCounts, int offset, int length) {
      this.elementSet = elementSet;
      this.cumulativeCounts = cumulativeCounts;
      this.offset = offset;
      this.length = length;
   }

   private int getCount(int index) {
      return (int)(this.cumulativeCounts[this.offset + index + 1] - this.cumulativeCounts[this.offset + index]);
   }

   Multiset.Entry getEntry(int index) {
      return Multisets.immutableEntry(this.elementSet.asList().get(index), this.getCount(index));
   }

   public void forEachEntry(ObjIntConsumer action) {
      Preconditions.checkNotNull(action);

      for(int i = 0; i < this.length; ++i) {
         action.accept(this.elementSet.asList().get(i), this.getCount(i));
      }

   }

   @CheckForNull
   public Multiset.Entry firstEntry() {
      return this.isEmpty() ? null : this.getEntry(0);
   }

   @CheckForNull
   public Multiset.Entry lastEntry() {
      return this.isEmpty() ? null : this.getEntry(this.length - 1);
   }

   public int count(@CheckForNull Object element) {
      int index = this.elementSet.indexOf(element);
      return index >= 0 ? this.getCount(index) : 0;
   }

   public int size() {
      long size = this.cumulativeCounts[this.offset + this.length] - this.cumulativeCounts[this.offset];
      return Ints.saturatedCast(size);
   }

   public ImmutableSortedSet elementSet() {
      return this.elementSet;
   }

   public ImmutableSortedMultiset headMultiset(Object upperBound, BoundType boundType) {
      return this.getSubMultiset(0, this.elementSet.headIndex(upperBound, Preconditions.checkNotNull(boundType) == BoundType.CLOSED));
   }

   public ImmutableSortedMultiset tailMultiset(Object lowerBound, BoundType boundType) {
      return this.getSubMultiset(this.elementSet.tailIndex(lowerBound, Preconditions.checkNotNull(boundType) == BoundType.CLOSED), this.length);
   }

   ImmutableSortedMultiset getSubMultiset(int from, int to) {
      Preconditions.checkPositionIndexes(from, to, this.length);
      if (from == to) {
         return emptyMultiset(this.comparator());
      } else if (from == 0 && to == this.length) {
         return this;
      } else {
         RegularImmutableSortedSet<E> subElementSet = this.elementSet.getSubSet(from, to);
         return new RegularImmutableSortedMultiset(subElementSet, this.cumulativeCounts, this.offset + from, to - from);
      }
   }

   boolean isPartialView() {
      return this.offset > 0 || this.length < this.cumulativeCounts.length - 1;
   }

   @J2ktIncompatible
   Object writeReplace() {
      return super.writeReplace();
   }
}
