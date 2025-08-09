package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.annotations.J2ktIncompatible;
import com.google.common.base.Preconditions;
import com.google.errorprone.annotations.DoNotCall;
import java.util.NoSuchElementException;
import java.util.Objects;

@ElementTypesAreNonnullByDefault
@GwtCompatible(
   emulated = true
)
public abstract class ContiguousSet extends ImmutableSortedSet {
   final DiscreteDomain domain;

   public static ContiguousSet create(Range range, DiscreteDomain domain) {
      Preconditions.checkNotNull(range);
      Preconditions.checkNotNull(domain);
      Range<C> effectiveRange = range;

      try {
         if (!range.hasLowerBound()) {
            effectiveRange = effectiveRange.intersection(Range.atLeast(domain.minValue()));
         }

         if (!range.hasUpperBound()) {
            effectiveRange = effectiveRange.intersection(Range.atMost(domain.maxValue()));
         }
      } catch (NoSuchElementException e) {
         throw new IllegalArgumentException(e);
      }

      boolean empty;
      if (effectiveRange.isEmpty()) {
         empty = true;
      } else {
         C afterLower = (C)((Comparable)Objects.requireNonNull(range.lowerBound.leastValueAbove(domain)));
         C beforeUpper = (C)((Comparable)Objects.requireNonNull(range.upperBound.greatestValueBelow(domain)));
         empty = Range.compareOrThrow(afterLower, beforeUpper) > 0;
      }

      return (ContiguousSet)(empty ? new EmptyContiguousSet(domain) : new RegularContiguousSet(effectiveRange, domain));
   }

   public static ContiguousSet closed(int lower, int upper) {
      return create(Range.closed(lower, upper), DiscreteDomain.integers());
   }

   public static ContiguousSet closed(long lower, long upper) {
      return create(Range.closed(lower, upper), DiscreteDomain.longs());
   }

   public static ContiguousSet closedOpen(int lower, int upper) {
      return create(Range.closedOpen(lower, upper), DiscreteDomain.integers());
   }

   public static ContiguousSet closedOpen(long lower, long upper) {
      return create(Range.closedOpen(lower, upper), DiscreteDomain.longs());
   }

   ContiguousSet(DiscreteDomain domain) {
      super(Ordering.natural());
      this.domain = domain;
   }

   public ContiguousSet headSet(Comparable toElement) {
      return this.headSetImpl((Comparable)Preconditions.checkNotNull(toElement), false);
   }

   @GwtIncompatible
   public ContiguousSet headSet(Comparable toElement, boolean inclusive) {
      return this.headSetImpl((Comparable)Preconditions.checkNotNull(toElement), inclusive);
   }

   public ContiguousSet subSet(Comparable fromElement, Comparable toElement) {
      Preconditions.checkNotNull(fromElement);
      Preconditions.checkNotNull(toElement);
      Preconditions.checkArgument(this.comparator().compare(fromElement, toElement) <= 0);
      return this.subSetImpl(fromElement, true, toElement, false);
   }

   @GwtIncompatible
   public ContiguousSet subSet(Comparable fromElement, boolean fromInclusive, Comparable toElement, boolean toInclusive) {
      Preconditions.checkNotNull(fromElement);
      Preconditions.checkNotNull(toElement);
      Preconditions.checkArgument(this.comparator().compare(fromElement, toElement) <= 0);
      return this.subSetImpl(fromElement, fromInclusive, toElement, toInclusive);
   }

   public ContiguousSet tailSet(Comparable fromElement) {
      return this.tailSetImpl((Comparable)Preconditions.checkNotNull(fromElement), true);
   }

   @GwtIncompatible
   public ContiguousSet tailSet(Comparable fromElement, boolean inclusive) {
      return this.tailSetImpl((Comparable)Preconditions.checkNotNull(fromElement), inclusive);
   }

   abstract ContiguousSet headSetImpl(Comparable toElement, boolean inclusive);

   abstract ContiguousSet subSetImpl(Comparable fromElement, boolean fromInclusive, Comparable toElement, boolean toInclusive);

   abstract ContiguousSet tailSetImpl(Comparable fromElement, boolean inclusive);

   public abstract ContiguousSet intersection(ContiguousSet other);

   public abstract Range range();

   public abstract Range range(BoundType lowerBoundType, BoundType upperBoundType);

   @GwtIncompatible
   ImmutableSortedSet createDescendingSet() {
      return new DescendingImmutableSortedSet(this);
   }

   public String toString() {
      return this.range().toString();
   }

   /** @deprecated */
   @Deprecated
   @DoNotCall("Always throws UnsupportedOperationException")
   public static ImmutableSortedSet.Builder builder() {
      throw new UnsupportedOperationException();
   }

   @J2ktIncompatible
   @GwtIncompatible
   Object writeReplace() {
      return super.writeReplace();
   }
}
