package org.apache.curator.shaded.com.google.common.collect;

import java.io.InvalidObjectException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.Collection;
import java.util.Objects;
import javax.annotation.CheckForNull;
import org.apache.curator.shaded.com.google.common.annotations.GwtCompatible;
import org.apache.curator.shaded.com.google.common.annotations.GwtIncompatible;
import org.apache.curator.shaded.com.google.common.annotations.J2ktIncompatible;
import org.apache.curator.shaded.com.google.common.base.Preconditions;

@ElementTypesAreNonnullByDefault
@GwtCompatible(
   emulated = true
)
final class RegularContiguousSet extends ContiguousSet {
   private final Range range;
   private static final long serialVersionUID = 0L;

   RegularContiguousSet(Range range, DiscreteDomain domain) {
      super(domain);
      this.range = range;
   }

   private ContiguousSet intersectionInCurrentDomain(Range other) {
      return (ContiguousSet)(this.range.isConnected(other) ? ContiguousSet.create(this.range.intersection(other), this.domain) : new EmptyContiguousSet(this.domain));
   }

   ContiguousSet headSetImpl(Comparable toElement, boolean inclusive) {
      return this.intersectionInCurrentDomain(Range.upTo(toElement, BoundType.forBoolean(inclusive)));
   }

   ContiguousSet subSetImpl(Comparable fromElement, boolean fromInclusive, Comparable toElement, boolean toInclusive) {
      return (ContiguousSet)(fromElement.compareTo(toElement) == 0 && !fromInclusive && !toInclusive ? new EmptyContiguousSet(this.domain) : this.intersectionInCurrentDomain(Range.range(fromElement, BoundType.forBoolean(fromInclusive), toElement, BoundType.forBoolean(toInclusive))));
   }

   ContiguousSet tailSetImpl(Comparable fromElement, boolean inclusive) {
      return this.intersectionInCurrentDomain(Range.downTo(fromElement, BoundType.forBoolean(inclusive)));
   }

   @GwtIncompatible
   int indexOf(@CheckForNull Object target) {
      return this.contains(target) ? (int)this.domain.distance(this.first(), (Comparable)Objects.requireNonNull(target)) : -1;
   }

   public UnmodifiableIterator iterator() {
      return new AbstractSequentialIterator(this.first()) {
         final Comparable last = RegularContiguousSet.this.last();

         @CheckForNull
         protected Comparable computeNext(Comparable previous) {
            return RegularContiguousSet.equalsOrThrow(previous, this.last) ? null : RegularContiguousSet.this.domain.next(previous);
         }
      };
   }

   @GwtIncompatible
   public UnmodifiableIterator descendingIterator() {
      return new AbstractSequentialIterator(this.last()) {
         final Comparable first = RegularContiguousSet.this.first();

         @CheckForNull
         protected Comparable computeNext(Comparable previous) {
            return RegularContiguousSet.equalsOrThrow(previous, this.first) ? null : RegularContiguousSet.this.domain.previous(previous);
         }
      };
   }

   private static boolean equalsOrThrow(Comparable left, @CheckForNull Comparable right) {
      return right != null && Range.compareOrThrow(left, right) == 0;
   }

   boolean isPartialView() {
      return false;
   }

   public Comparable first() {
      return (Comparable)Objects.requireNonNull(this.range.lowerBound.leastValueAbove(this.domain));
   }

   public Comparable last() {
      return (Comparable)Objects.requireNonNull(this.range.upperBound.greatestValueBelow(this.domain));
   }

   ImmutableList createAsList() {
      return (ImmutableList)(this.domain.supportsFastOffset ? new ImmutableAsList() {
         ImmutableSortedSet delegateCollection() {
            return RegularContiguousSet.this;
         }

         public Comparable get(int i) {
            Preconditions.checkElementIndex(i, this.size());
            return RegularContiguousSet.this.domain.offset(RegularContiguousSet.this.first(), (long)i);
         }
      } : super.createAsList());
   }

   public int size() {
      long distance = this.domain.distance(this.first(), this.last());
      return distance >= 2147483647L ? Integer.MAX_VALUE : (int)distance + 1;
   }

   public boolean contains(@CheckForNull Object object) {
      if (object == null) {
         return false;
      } else {
         try {
            return this.range.contains((Comparable)object);
         } catch (ClassCastException var3) {
            return false;
         }
      }
   }

   public boolean containsAll(Collection targets) {
      return Collections2.containsAllImpl(this, targets);
   }

   public boolean isEmpty() {
      return false;
   }

   public ContiguousSet intersection(ContiguousSet other) {
      Preconditions.checkNotNull(other);
      Preconditions.checkArgument(this.domain.equals(other.domain));
      if (other.isEmpty()) {
         return other;
      } else {
         C lowerEndpoint = (C)((Comparable)Ordering.natural().max(this.first(), (Comparable)other.first()));
         C upperEndpoint = (C)((Comparable)Ordering.natural().min(this.last(), (Comparable)other.last()));
         return (ContiguousSet)(lowerEndpoint.compareTo(upperEndpoint) <= 0 ? ContiguousSet.create(Range.closed(lowerEndpoint, upperEndpoint), this.domain) : new EmptyContiguousSet(this.domain));
      }
   }

   public Range range() {
      return this.range(BoundType.CLOSED, BoundType.CLOSED);
   }

   public Range range(BoundType lowerBoundType, BoundType upperBoundType) {
      return Range.create(this.range.lowerBound.withLowerBoundType(lowerBoundType, this.domain), this.range.upperBound.withUpperBoundType(upperBoundType, this.domain));
   }

   public boolean equals(@CheckForNull Object object) {
      if (object == this) {
         return true;
      } else {
         if (object instanceof RegularContiguousSet) {
            RegularContiguousSet<?> that = (RegularContiguousSet)object;
            if (this.domain.equals(that.domain)) {
               return this.first().equals(that.first()) && this.last().equals(that.last());
            }
         }

         return super.equals(object);
      }
   }

   public int hashCode() {
      return Sets.hashCodeImpl(this);
   }

   @GwtIncompatible
   @J2ktIncompatible
   Object writeReplace() {
      return new SerializedForm(this.range, this.domain);
   }

   @GwtIncompatible
   @J2ktIncompatible
   private void readObject(ObjectInputStream stream) throws InvalidObjectException {
      throw new InvalidObjectException("Use SerializedForm");
   }

   @GwtIncompatible
   @J2ktIncompatible
   private static final class SerializedForm implements Serializable {
      final Range range;
      final DiscreteDomain domain;

      private SerializedForm(Range range, DiscreteDomain domain) {
         this.range = range;
         this.domain = domain;
      }

      private Object readResolve() {
         return new RegularContiguousSet(this.range, this.domain);
      }
   }
}
