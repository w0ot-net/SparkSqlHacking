package com.google.common.collect;

import com.google.common.annotations.GwtIncompatible;
import com.google.common.annotations.J2ktIncompatible;
import com.google.common.base.Function;
import com.google.common.base.Preconditions;
import com.google.common.primitives.Ints;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import com.google.errorprone.annotations.DoNotCall;
import com.google.errorprone.annotations.concurrent.LazyInit;
import java.io.InvalidObjectException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.stream.Collector;
import javax.annotation.CheckForNull;

@ElementTypesAreNonnullByDefault
@GwtIncompatible
public final class ImmutableRangeSet extends AbstractRangeSet implements Serializable {
   private static final ImmutableRangeSet EMPTY = new ImmutableRangeSet(ImmutableList.of());
   private static final ImmutableRangeSet ALL = new ImmutableRangeSet(ImmutableList.of(Range.all()));
   private final transient ImmutableList ranges;
   @LazyInit
   @CheckForNull
   private transient ImmutableRangeSet complement;

   public static Collector toImmutableRangeSet() {
      return CollectCollectors.toImmutableRangeSet();
   }

   public static ImmutableRangeSet of() {
      return EMPTY;
   }

   public static ImmutableRangeSet of(Range range) {
      Preconditions.checkNotNull(range);
      if (range.isEmpty()) {
         return of();
      } else {
         return range.equals(Range.all()) ? all() : new ImmutableRangeSet(ImmutableList.of(range));
      }
   }

   static ImmutableRangeSet all() {
      return ALL;
   }

   public static ImmutableRangeSet copyOf(RangeSet rangeSet) {
      Preconditions.checkNotNull(rangeSet);
      if (rangeSet.isEmpty()) {
         return of();
      } else if (rangeSet.encloses(Range.all())) {
         return all();
      } else {
         if (rangeSet instanceof ImmutableRangeSet) {
            ImmutableRangeSet<C> immutableRangeSet = (ImmutableRangeSet)rangeSet;
            if (!immutableRangeSet.isPartialView()) {
               return immutableRangeSet;
            }
         }

         return new ImmutableRangeSet(ImmutableList.copyOf((Collection)rangeSet.asRanges()));
      }
   }

   public static ImmutableRangeSet copyOf(Iterable ranges) {
      return (new Builder()).addAll(ranges).build();
   }

   public static ImmutableRangeSet unionOf(Iterable ranges) {
      return copyOf((RangeSet)TreeRangeSet.create(ranges));
   }

   ImmutableRangeSet(ImmutableList ranges) {
      this.ranges = ranges;
   }

   private ImmutableRangeSet(ImmutableList ranges, ImmutableRangeSet complement) {
      this.ranges = ranges;
      this.complement = complement;
   }

   public boolean intersects(Range otherRange) {
      int ceilingIndex = SortedLists.binarySearch(this.ranges, Range::lowerBound, otherRange.lowerBound, Ordering.natural(), SortedLists.KeyPresentBehavior.ANY_PRESENT, SortedLists.KeyAbsentBehavior.NEXT_HIGHER);
      if (ceilingIndex < this.ranges.size() && ((Range)this.ranges.get(ceilingIndex)).isConnected(otherRange) && !((Range)this.ranges.get(ceilingIndex)).intersection(otherRange).isEmpty()) {
         return true;
      } else {
         return ceilingIndex > 0 && ((Range)this.ranges.get(ceilingIndex - 1)).isConnected(otherRange) && !((Range)this.ranges.get(ceilingIndex - 1)).intersection(otherRange).isEmpty();
      }
   }

   public boolean encloses(Range otherRange) {
      int index = SortedLists.binarySearch(this.ranges, Range::lowerBound, otherRange.lowerBound, Ordering.natural(), SortedLists.KeyPresentBehavior.ANY_PRESENT, SortedLists.KeyAbsentBehavior.NEXT_LOWER);
      return index != -1 && ((Range)this.ranges.get(index)).encloses(otherRange);
   }

   @CheckForNull
   public Range rangeContaining(Comparable value) {
      int index = SortedLists.binarySearch(this.ranges, Range::lowerBound, Cut.belowValue(value), Ordering.natural(), SortedLists.KeyPresentBehavior.ANY_PRESENT, SortedLists.KeyAbsentBehavior.NEXT_LOWER);
      if (index != -1) {
         Range<C> range = (Range)this.ranges.get(index);
         return range.contains(value) ? range : null;
      } else {
         return null;
      }
   }

   public Range span() {
      if (this.ranges.isEmpty()) {
         throw new NoSuchElementException();
      } else {
         return Range.create(((Range)this.ranges.get(0)).lowerBound, ((Range)this.ranges.get(this.ranges.size() - 1)).upperBound);
      }
   }

   public boolean isEmpty() {
      return this.ranges.isEmpty();
   }

   /** @deprecated */
   @Deprecated
   @DoNotCall("Always throws UnsupportedOperationException")
   public void add(Range range) {
      throw new UnsupportedOperationException();
   }

   /** @deprecated */
   @Deprecated
   @DoNotCall("Always throws UnsupportedOperationException")
   public void addAll(RangeSet other) {
      throw new UnsupportedOperationException();
   }

   /** @deprecated */
   @Deprecated
   @DoNotCall("Always throws UnsupportedOperationException")
   public void addAll(Iterable other) {
      throw new UnsupportedOperationException();
   }

   /** @deprecated */
   @Deprecated
   @DoNotCall("Always throws UnsupportedOperationException")
   public void remove(Range range) {
      throw new UnsupportedOperationException();
   }

   /** @deprecated */
   @Deprecated
   @DoNotCall("Always throws UnsupportedOperationException")
   public void removeAll(RangeSet other) {
      throw new UnsupportedOperationException();
   }

   /** @deprecated */
   @Deprecated
   @DoNotCall("Always throws UnsupportedOperationException")
   public void removeAll(Iterable other) {
      throw new UnsupportedOperationException();
   }

   public ImmutableSet asRanges() {
      return (ImmutableSet)(this.ranges.isEmpty() ? ImmutableSet.of() : new RegularImmutableSortedSet(this.ranges, Range.rangeLexOrdering()));
   }

   public ImmutableSet asDescendingSetOfRanges() {
      return (ImmutableSet)(this.ranges.isEmpty() ? ImmutableSet.of() : new RegularImmutableSortedSet(this.ranges.reverse(), Range.rangeLexOrdering().reverse()));
   }

   public ImmutableRangeSet complement() {
      ImmutableRangeSet<C> result = this.complement;
      if (result != null) {
         return result;
      } else if (this.ranges.isEmpty()) {
         return this.complement = all();
      } else if (this.ranges.size() == 1 && ((Range)this.ranges.get(0)).equals(Range.all())) {
         return this.complement = of();
      } else {
         ImmutableList<Range<C>> complementRanges = new ComplementRanges();
         result = this.complement = new ImmutableRangeSet(complementRanges, this);
         return result;
      }
   }

   public ImmutableRangeSet union(RangeSet other) {
      return unionOf(Iterables.concat(this.asRanges(), other.asRanges()));
   }

   public ImmutableRangeSet intersection(RangeSet other) {
      RangeSet<C> copy = TreeRangeSet.create((RangeSet)this);
      copy.removeAll(other.complement());
      return copyOf(copy);
   }

   public ImmutableRangeSet difference(RangeSet other) {
      RangeSet<C> copy = TreeRangeSet.create((RangeSet)this);
      copy.removeAll(other);
      return copyOf(copy);
   }

   private ImmutableList intersectRanges(final Range range) {
      if (!this.ranges.isEmpty() && !range.isEmpty()) {
         if (range.encloses(this.span())) {
            return this.ranges;
         } else {
            final int fromIndex;
            if (range.hasLowerBound()) {
               fromIndex = SortedLists.binarySearch(this.ranges, (Function)(Range::upperBound), (Comparable)range.lowerBound, SortedLists.KeyPresentBehavior.FIRST_AFTER, SortedLists.KeyAbsentBehavior.NEXT_HIGHER);
            } else {
               fromIndex = 0;
            }

            int toIndex;
            if (range.hasUpperBound()) {
               toIndex = SortedLists.binarySearch(this.ranges, (Function)(Range::lowerBound), (Comparable)range.upperBound, SortedLists.KeyPresentBehavior.FIRST_PRESENT, SortedLists.KeyAbsentBehavior.NEXT_HIGHER);
            } else {
               toIndex = this.ranges.size();
            }

            final int length = toIndex - fromIndex;
            return length == 0 ? ImmutableList.of() : new ImmutableList() {
               public int size() {
                  return length;
               }

               public Range get(int index) {
                  Preconditions.checkElementIndex(index, length);
                  return index != 0 && index != length - 1 ? (Range)ImmutableRangeSet.this.ranges.get(index + fromIndex) : ((Range)ImmutableRangeSet.this.ranges.get(index + fromIndex)).intersection(range);
               }

               boolean isPartialView() {
                  return true;
               }

               @J2ktIncompatible
               @GwtIncompatible
               Object writeReplace() {
                  return super.writeReplace();
               }
            };
         }
      } else {
         return ImmutableList.of();
      }
   }

   public ImmutableRangeSet subRangeSet(Range range) {
      if (!this.isEmpty()) {
         Range<C> span = this.span();
         if (range.encloses(span)) {
            return this;
         }

         if (range.isConnected(span)) {
            return new ImmutableRangeSet(this.intersectRanges(range));
         }
      }

      return of();
   }

   public ImmutableSortedSet asSet(DiscreteDomain domain) {
      Preconditions.checkNotNull(domain);
      if (this.isEmpty()) {
         return ImmutableSortedSet.of();
      } else {
         Range<C> span = this.span().canonical(domain);
         if (!span.hasLowerBound()) {
            throw new IllegalArgumentException("Neither the DiscreteDomain nor this range set are bounded below");
         } else {
            if (!span.hasUpperBound()) {
               try {
                  domain.maxValue();
               } catch (NoSuchElementException var4) {
                  throw new IllegalArgumentException("Neither the DiscreteDomain nor this range set are bounded above");
               }
            }

            return new AsSet(domain);
         }
      }
   }

   boolean isPartialView() {
      return this.ranges.isPartialView();
   }

   public static Builder builder() {
      return new Builder();
   }

   @J2ktIncompatible
   Object writeReplace() {
      return new SerializedForm(this.ranges);
   }

   @J2ktIncompatible
   private void readObject(ObjectInputStream stream) throws InvalidObjectException {
      throw new InvalidObjectException("Use SerializedForm");
   }

   private final class ComplementRanges extends ImmutableList {
      private final boolean positiveBoundedBelow;
      private final boolean positiveBoundedAbove;
      private final int size;

      ComplementRanges() {
         this.positiveBoundedBelow = ((Range)ImmutableRangeSet.this.ranges.get(0)).hasLowerBound();
         this.positiveBoundedAbove = ((Range)Iterables.getLast(ImmutableRangeSet.this.ranges)).hasUpperBound();
         int size = ImmutableRangeSet.this.ranges.size() - 1;
         if (this.positiveBoundedBelow) {
            ++size;
         }

         if (this.positiveBoundedAbove) {
            ++size;
         }

         this.size = size;
      }

      public int size() {
         return this.size;
      }

      public Range get(int index) {
         Preconditions.checkElementIndex(index, this.size);
         Cut<C> lowerBound;
         if (this.positiveBoundedBelow) {
            lowerBound = index == 0 ? Cut.belowAll() : ((Range)ImmutableRangeSet.this.ranges.get(index - 1)).upperBound;
         } else {
            lowerBound = ((Range)ImmutableRangeSet.this.ranges.get(index)).upperBound;
         }

         Cut<C> upperBound;
         if (this.positiveBoundedAbove && index == this.size - 1) {
            upperBound = Cut.aboveAll();
         } else {
            upperBound = ((Range)ImmutableRangeSet.this.ranges.get(index + (this.positiveBoundedBelow ? 0 : 1))).lowerBound;
         }

         return Range.create(lowerBound, upperBound);
      }

      boolean isPartialView() {
         return true;
      }

      @J2ktIncompatible
      Object writeReplace() {
         return super.writeReplace();
      }
   }

   private final class AsSet extends ImmutableSortedSet {
      private final DiscreteDomain domain;
      @LazyInit
      @CheckForNull
      private transient Integer size;

      AsSet(DiscreteDomain domain) {
         super(Ordering.natural());
         this.domain = domain;
      }

      public int size() {
         Integer result = this.size;
         if (result == null) {
            long total = 0L;

            for(Range range : ImmutableRangeSet.this.ranges) {
               total += (long)ContiguousSet.create(range, this.domain).size();
               if (total >= 2147483647L) {
                  break;
               }
            }

            result = this.size = Ints.saturatedCast(total);
         }

         return result;
      }

      public UnmodifiableIterator iterator() {
         return new AbstractIterator() {
            final Iterator rangeItr;
            Iterator elemItr;

            {
               this.rangeItr = ImmutableRangeSet.this.ranges.iterator();
               this.elemItr = Iterators.emptyIterator();
            }

            @CheckForNull
            protected Comparable computeNext() {
               while(true) {
                  if (!this.elemItr.hasNext()) {
                     if (this.rangeItr.hasNext()) {
                        this.elemItr = ContiguousSet.create((Range)this.rangeItr.next(), AsSet.this.domain).iterator();
                        continue;
                     }

                     return (Comparable)this.endOfData();
                  }

                  return (Comparable)this.elemItr.next();
               }
            }
         };
      }

      @GwtIncompatible("NavigableSet")
      public UnmodifiableIterator descendingIterator() {
         return new AbstractIterator() {
            final Iterator rangeItr;
            Iterator elemItr;

            {
               this.rangeItr = ImmutableRangeSet.this.ranges.reverse().iterator();
               this.elemItr = Iterators.emptyIterator();
            }

            @CheckForNull
            protected Comparable computeNext() {
               while(true) {
                  if (!this.elemItr.hasNext()) {
                     if (this.rangeItr.hasNext()) {
                        this.elemItr = ContiguousSet.create((Range)this.rangeItr.next(), AsSet.this.domain).descendingIterator();
                        continue;
                     }

                     return (Comparable)this.endOfData();
                  }

                  return (Comparable)this.elemItr.next();
               }
            }
         };
      }

      ImmutableSortedSet subSet(Range range) {
         return ImmutableRangeSet.this.subRangeSet(range).asSet(this.domain);
      }

      ImmutableSortedSet headSetImpl(Comparable toElement, boolean inclusive) {
         return this.subSet(Range.upTo(toElement, BoundType.forBoolean(inclusive)));
      }

      ImmutableSortedSet subSetImpl(Comparable fromElement, boolean fromInclusive, Comparable toElement, boolean toInclusive) {
         return !fromInclusive && !toInclusive && Range.compareOrThrow(fromElement, toElement) == 0 ? ImmutableSortedSet.of() : this.subSet(Range.range(fromElement, BoundType.forBoolean(fromInclusive), toElement, BoundType.forBoolean(toInclusive)));
      }

      ImmutableSortedSet tailSetImpl(Comparable fromElement, boolean inclusive) {
         return this.subSet(Range.downTo(fromElement, BoundType.forBoolean(inclusive)));
      }

      public boolean contains(@CheckForNull Object o) {
         if (o == null) {
            return false;
         } else {
            try {
               C c = (C)((Comparable)o);
               return ImmutableRangeSet.this.contains(c);
            } catch (ClassCastException var3) {
               return false;
            }
         }
      }

      int indexOf(@CheckForNull Object target) {
         if (this.contains(target)) {
            C c = (C)((Comparable)Objects.requireNonNull(target));
            long total = 0L;

            for(Range range : ImmutableRangeSet.this.ranges) {
               if (range.contains(c)) {
                  return Ints.saturatedCast(total + (long)ContiguousSet.create(range, this.domain).indexOf(c));
               }

               total += (long)ContiguousSet.create(range, this.domain).size();
            }

            throw new AssertionError("impossible");
         } else {
            return -1;
         }
      }

      ImmutableSortedSet createDescendingSet() {
         return new DescendingImmutableSortedSet(this);
      }

      boolean isPartialView() {
         return ImmutableRangeSet.this.ranges.isPartialView();
      }

      public String toString() {
         return ImmutableRangeSet.this.ranges.toString();
      }

      @J2ktIncompatible
      Object writeReplace() {
         return new AsSetSerializedForm(ImmutableRangeSet.this.ranges, this.domain);
      }

      @J2ktIncompatible
      private void readObject(ObjectInputStream stream) throws InvalidObjectException {
         throw new InvalidObjectException("Use SerializedForm");
      }
   }

   private static class AsSetSerializedForm implements Serializable {
      private final ImmutableList ranges;
      private final DiscreteDomain domain;

      AsSetSerializedForm(ImmutableList ranges, DiscreteDomain domain) {
         this.ranges = ranges;
         this.domain = domain;
      }

      Object readResolve() {
         return (new ImmutableRangeSet(this.ranges)).asSet(this.domain);
      }
   }

   public static class Builder {
      private final List ranges = Lists.newArrayList();

      @CanIgnoreReturnValue
      public Builder add(Range range) {
         Preconditions.checkArgument(!range.isEmpty(), "range must not be empty, but was %s", (Object)range);
         this.ranges.add(range);
         return this;
      }

      @CanIgnoreReturnValue
      public Builder addAll(RangeSet ranges) {
         return this.addAll((Iterable)ranges.asRanges());
      }

      @CanIgnoreReturnValue
      public Builder addAll(Iterable ranges) {
         for(Range range : ranges) {
            this.add(range);
         }

         return this;
      }

      @CanIgnoreReturnValue
      Builder combine(Builder builder) {
         this.addAll((Iterable)builder.ranges);
         return this;
      }

      public ImmutableRangeSet build() {
         ImmutableList.Builder<Range<C>> mergedRangesBuilder = new ImmutableList.Builder(this.ranges.size());
         Collections.sort(this.ranges, Range.rangeLexOrdering());

         Range<C> range;
         for(PeekingIterator<Range<C>> peekingItr = Iterators.peekingIterator(this.ranges.iterator()); peekingItr.hasNext(); mergedRangesBuilder.add((Object)range)) {
            for(range = (Range)peekingItr.next(); peekingItr.hasNext(); range = range.span((Range)peekingItr.next())) {
               Range<C> nextRange = (Range)peekingItr.peek();
               if (!range.isConnected(nextRange)) {
                  break;
               }

               Preconditions.checkArgument(range.intersection(nextRange).isEmpty(), "Overlapping ranges not permitted but found %s overlapping %s", range, nextRange);
            }
         }

         ImmutableList<Range<C>> mergedRanges = mergedRangesBuilder.build();
         if (mergedRanges.isEmpty()) {
            return ImmutableRangeSet.of();
         } else if (mergedRanges.size() == 1 && ((Range)Iterables.getOnlyElement(mergedRanges)).equals(Range.all())) {
            return ImmutableRangeSet.all();
         } else {
            return new ImmutableRangeSet(mergedRanges);
         }
      }
   }

   private static final class SerializedForm implements Serializable {
      private final ImmutableList ranges;

      SerializedForm(ImmutableList ranges) {
         this.ranges = ranges;
      }

      Object readResolve() {
         if (this.ranges.isEmpty()) {
            return ImmutableRangeSet.of();
         } else {
            return this.ranges.equals(ImmutableList.of(Range.all())) ? ImmutableRangeSet.all() : new ImmutableRangeSet(this.ranges);
         }
      }
   }
}
