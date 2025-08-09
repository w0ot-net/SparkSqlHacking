package org.apache.curator.shaded.com.google.common.collect;

import java.io.Serializable;
import java.util.Comparator;
import java.util.Iterator;
import java.util.SortedSet;
import javax.annotation.CheckForNull;
import org.apache.curator.shaded.com.google.common.annotations.GwtCompatible;
import org.apache.curator.shaded.com.google.common.base.Function;
import org.apache.curator.shaded.com.google.common.base.Preconditions;
import org.apache.curator.shaded.com.google.common.base.Predicate;
import org.apache.curator.shaded.com.google.errorprone.annotations.Immutable;

@Immutable(
   containerOf = {"C"}
)
@ElementTypesAreNonnullByDefault
@GwtCompatible
public final class Range extends RangeGwtSerializationDependencies implements Predicate, Serializable {
   private static final Range ALL = new Range(Cut.belowAll(), Cut.aboveAll());
   final Cut lowerBound;
   final Cut upperBound;
   private static final long serialVersionUID = 0L;

   static Function lowerBoundFn() {
      return Range.LowerBoundFn.INSTANCE;
   }

   static Function upperBoundFn() {
      return Range.UpperBoundFn.INSTANCE;
   }

   static Ordering rangeLexOrdering() {
      return Range.RangeLexOrdering.INSTANCE;
   }

   static Range create(Cut lowerBound, Cut upperBound) {
      return new Range(lowerBound, upperBound);
   }

   public static Range open(Comparable lower, Comparable upper) {
      return create(Cut.aboveValue(lower), Cut.belowValue(upper));
   }

   public static Range closed(Comparable lower, Comparable upper) {
      return create(Cut.belowValue(lower), Cut.aboveValue(upper));
   }

   public static Range closedOpen(Comparable lower, Comparable upper) {
      return create(Cut.belowValue(lower), Cut.belowValue(upper));
   }

   public static Range openClosed(Comparable lower, Comparable upper) {
      return create(Cut.aboveValue(lower), Cut.aboveValue(upper));
   }

   public static Range range(Comparable lower, BoundType lowerType, Comparable upper, BoundType upperType) {
      Preconditions.checkNotNull(lowerType);
      Preconditions.checkNotNull(upperType);
      Cut<C> lowerBound = lowerType == BoundType.OPEN ? Cut.aboveValue(lower) : Cut.belowValue(lower);
      Cut<C> upperBound = upperType == BoundType.OPEN ? Cut.belowValue(upper) : Cut.aboveValue(upper);
      return create(lowerBound, upperBound);
   }

   public static Range lessThan(Comparable endpoint) {
      return create(Cut.belowAll(), Cut.belowValue(endpoint));
   }

   public static Range atMost(Comparable endpoint) {
      return create(Cut.belowAll(), Cut.aboveValue(endpoint));
   }

   public static Range upTo(Comparable endpoint, BoundType boundType) {
      switch (boundType) {
         case OPEN:
            return lessThan(endpoint);
         case CLOSED:
            return atMost(endpoint);
         default:
            throw new AssertionError();
      }
   }

   public static Range greaterThan(Comparable endpoint) {
      return create(Cut.aboveValue(endpoint), Cut.aboveAll());
   }

   public static Range atLeast(Comparable endpoint) {
      return create(Cut.belowValue(endpoint), Cut.aboveAll());
   }

   public static Range downTo(Comparable endpoint, BoundType boundType) {
      switch (boundType) {
         case OPEN:
            return greaterThan(endpoint);
         case CLOSED:
            return atLeast(endpoint);
         default:
            throw new AssertionError();
      }
   }

   public static Range all() {
      return ALL;
   }

   public static Range singleton(Comparable value) {
      return closed(value, value);
   }

   public static Range encloseAll(Iterable values) {
      Preconditions.checkNotNull(values);
      if (values instanceof SortedSet) {
         SortedSet<C> set = (SortedSet)values;
         Comparator<?> comparator = set.comparator();
         if (Ordering.natural().equals(comparator) || comparator == null) {
            return closed((Comparable)set.first(), (Comparable)set.last());
         }
      }

      Iterator<C> valueIterator = values.iterator();
      C min = (C)((Comparable)Preconditions.checkNotNull((Comparable)valueIterator.next()));

      C max;
      C value;
      for(max = min; valueIterator.hasNext(); max = (C)((Comparable)Ordering.natural().max(max, value))) {
         value = (C)((Comparable)Preconditions.checkNotNull((Comparable)valueIterator.next()));
         min = (C)((Comparable)Ordering.natural().min(min, value));
      }

      return closed(min, max);
   }

   private Range(Cut lowerBound, Cut upperBound) {
      this.lowerBound = (Cut)Preconditions.checkNotNull(lowerBound);
      this.upperBound = (Cut)Preconditions.checkNotNull(upperBound);
      if (lowerBound.compareTo(upperBound) > 0 || lowerBound == Cut.aboveAll() || upperBound == Cut.belowAll()) {
         throw new IllegalArgumentException("Invalid range: " + toString(lowerBound, upperBound));
      }
   }

   public boolean hasLowerBound() {
      return this.lowerBound != Cut.belowAll();
   }

   public Comparable lowerEndpoint() {
      return this.lowerBound.endpoint();
   }

   public BoundType lowerBoundType() {
      return this.lowerBound.typeAsLowerBound();
   }

   public boolean hasUpperBound() {
      return this.upperBound != Cut.aboveAll();
   }

   public Comparable upperEndpoint() {
      return this.upperBound.endpoint();
   }

   public BoundType upperBoundType() {
      return this.upperBound.typeAsUpperBound();
   }

   public boolean isEmpty() {
      return this.lowerBound.equals(this.upperBound);
   }

   public boolean contains(Comparable value) {
      Preconditions.checkNotNull(value);
      return this.lowerBound.isLessThan(value) && !this.upperBound.isLessThan(value);
   }

   /** @deprecated */
   @Deprecated
   public boolean apply(Comparable input) {
      return this.contains(input);
   }

   public boolean containsAll(Iterable values) {
      if (Iterables.isEmpty(values)) {
         return true;
      } else {
         if (values instanceof SortedSet) {
            SortedSet<? extends C> set = (SortedSet)values;
            Comparator<?> comparator = set.comparator();
            if (Ordering.natural().equals(comparator) || comparator == null) {
               return this.contains((Comparable)set.first()) && this.contains((Comparable)set.last());
            }
         }

         for(Comparable value : values) {
            if (!this.contains(value)) {
               return false;
            }
         }

         return true;
      }
   }

   public boolean encloses(Range other) {
      return this.lowerBound.compareTo(other.lowerBound) <= 0 && this.upperBound.compareTo(other.upperBound) >= 0;
   }

   public boolean isConnected(Range other) {
      return this.lowerBound.compareTo(other.upperBound) <= 0 && other.lowerBound.compareTo(this.upperBound) <= 0;
   }

   public Range intersection(Range connectedRange) {
      int lowerCmp = this.lowerBound.compareTo(connectedRange.lowerBound);
      int upperCmp = this.upperBound.compareTo(connectedRange.upperBound);
      if (lowerCmp >= 0 && upperCmp <= 0) {
         return this;
      } else if (lowerCmp <= 0 && upperCmp >= 0) {
         return connectedRange;
      } else {
         Cut<C> newLower = lowerCmp >= 0 ? this.lowerBound : connectedRange.lowerBound;
         Cut<C> newUpper = upperCmp <= 0 ? this.upperBound : connectedRange.upperBound;
         Preconditions.checkArgument(newLower.compareTo(newUpper) <= 0, "intersection is undefined for disconnected ranges %s and %s", this, connectedRange);
         return create(newLower, newUpper);
      }
   }

   public Range gap(Range otherRange) {
      if (this.lowerBound.compareTo(otherRange.upperBound) < 0 && otherRange.lowerBound.compareTo(this.upperBound) < 0) {
         throw new IllegalArgumentException("Ranges have a nonempty intersection: " + this + ", " + otherRange);
      } else {
         boolean isThisFirst = this.lowerBound.compareTo(otherRange.lowerBound) < 0;
         Range<C> firstRange = isThisFirst ? this : otherRange;
         Range<C> secondRange = isThisFirst ? otherRange : this;
         return create(firstRange.upperBound, secondRange.lowerBound);
      }
   }

   public Range span(Range other) {
      int lowerCmp = this.lowerBound.compareTo(other.lowerBound);
      int upperCmp = this.upperBound.compareTo(other.upperBound);
      if (lowerCmp <= 0 && upperCmp >= 0) {
         return this;
      } else if (lowerCmp >= 0 && upperCmp <= 0) {
         return other;
      } else {
         Cut<C> newLower = lowerCmp <= 0 ? this.lowerBound : other.lowerBound;
         Cut<C> newUpper = upperCmp >= 0 ? this.upperBound : other.upperBound;
         return create(newLower, newUpper);
      }
   }

   public Range canonical(DiscreteDomain domain) {
      Preconditions.checkNotNull(domain);
      Cut<C> lower = this.lowerBound.canonical(domain);
      Cut<C> upper = this.upperBound.canonical(domain);
      return lower == this.lowerBound && upper == this.upperBound ? this : create(lower, upper);
   }

   public boolean equals(@CheckForNull Object object) {
      if (!(object instanceof Range)) {
         return false;
      } else {
         Range<?> other = (Range)object;
         return this.lowerBound.equals(other.lowerBound) && this.upperBound.equals(other.upperBound);
      }
   }

   public int hashCode() {
      return this.lowerBound.hashCode() * 31 + this.upperBound.hashCode();
   }

   public String toString() {
      return toString(this.lowerBound, this.upperBound);
   }

   private static String toString(Cut lowerBound, Cut upperBound) {
      StringBuilder sb = new StringBuilder(16);
      lowerBound.describeAsLowerBound(sb);
      sb.append("..");
      upperBound.describeAsUpperBound(sb);
      return sb.toString();
   }

   Object readResolve() {
      return this.equals(ALL) ? all() : this;
   }

   static int compareOrThrow(Comparable left, Comparable right) {
      return left.compareTo(right);
   }

   static class LowerBoundFn implements Function {
      static final LowerBoundFn INSTANCE = new LowerBoundFn();

      public Cut apply(Range range) {
         return range.lowerBound;
      }
   }

   static class UpperBoundFn implements Function {
      static final UpperBoundFn INSTANCE = new UpperBoundFn();

      public Cut apply(Range range) {
         return range.upperBound;
      }
   }

   private static class RangeLexOrdering extends Ordering implements Serializable {
      static final Ordering INSTANCE = new RangeLexOrdering();
      private static final long serialVersionUID = 0L;

      public int compare(Range left, Range right) {
         return ComparisonChain.start().compare((Comparable)left.lowerBound, (Comparable)right.lowerBound).compare((Comparable)left.upperBound, (Comparable)right.upperBound).result();
      }
   }
}
