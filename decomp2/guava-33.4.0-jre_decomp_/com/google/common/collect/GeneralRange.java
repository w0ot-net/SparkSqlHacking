package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.google.common.base.Objects;
import com.google.common.base.Preconditions;
import com.google.errorprone.annotations.concurrent.LazyInit;
import java.io.Serializable;
import java.util.Comparator;
import javax.annotation.CheckForNull;

@ElementTypesAreNonnullByDefault
@GwtCompatible(
   serializable = true
)
final class GeneralRange implements Serializable {
   private final Comparator comparator;
   private final boolean hasLowerBound;
   @CheckForNull
   private final Object lowerEndpoint;
   private final BoundType lowerBoundType;
   private final boolean hasUpperBound;
   @CheckForNull
   private final Object upperEndpoint;
   private final BoundType upperBoundType;
   @LazyInit
   @CheckForNull
   private transient GeneralRange reverse;

   static GeneralRange from(Range range) {
      T lowerEndpoint = (T)(range.hasLowerBound() ? range.lowerEndpoint() : null);
      BoundType lowerBoundType = range.hasLowerBound() ? range.lowerBoundType() : BoundType.OPEN;
      T upperEndpoint = (T)(range.hasUpperBound() ? range.upperEndpoint() : null);
      BoundType upperBoundType = range.hasUpperBound() ? range.upperBoundType() : BoundType.OPEN;
      return new GeneralRange(Ordering.natural(), range.hasLowerBound(), lowerEndpoint, lowerBoundType, range.hasUpperBound(), upperEndpoint, upperBoundType);
   }

   static GeneralRange all(Comparator comparator) {
      return new GeneralRange(comparator, false, (Object)null, BoundType.OPEN, false, (Object)null, BoundType.OPEN);
   }

   static GeneralRange downTo(Comparator comparator, @ParametricNullness Object endpoint, BoundType boundType) {
      return new GeneralRange(comparator, true, endpoint, boundType, false, (Object)null, BoundType.OPEN);
   }

   static GeneralRange upTo(Comparator comparator, @ParametricNullness Object endpoint, BoundType boundType) {
      return new GeneralRange(comparator, false, (Object)null, BoundType.OPEN, true, endpoint, boundType);
   }

   static GeneralRange range(Comparator comparator, @ParametricNullness Object lower, BoundType lowerType, @ParametricNullness Object upper, BoundType upperType) {
      return new GeneralRange(comparator, true, lower, lowerType, true, upper, upperType);
   }

   private GeneralRange(Comparator comparator, boolean hasLowerBound, @CheckForNull Object lowerEndpoint, BoundType lowerBoundType, boolean hasUpperBound, @CheckForNull Object upperEndpoint, BoundType upperBoundType) {
      this.comparator = (Comparator)Preconditions.checkNotNull(comparator);
      this.hasLowerBound = hasLowerBound;
      this.hasUpperBound = hasUpperBound;
      this.lowerEndpoint = lowerEndpoint;
      this.lowerBoundType = (BoundType)Preconditions.checkNotNull(lowerBoundType);
      this.upperEndpoint = upperEndpoint;
      this.upperBoundType = (BoundType)Preconditions.checkNotNull(upperBoundType);
      if (hasLowerBound) {
         comparator.compare(NullnessCasts.uncheckedCastNullableTToT(lowerEndpoint), NullnessCasts.uncheckedCastNullableTToT(lowerEndpoint));
      }

      if (hasUpperBound) {
         comparator.compare(NullnessCasts.uncheckedCastNullableTToT(upperEndpoint), NullnessCasts.uncheckedCastNullableTToT(upperEndpoint));
      }

      if (hasLowerBound && hasUpperBound) {
         int cmp = comparator.compare(NullnessCasts.uncheckedCastNullableTToT(lowerEndpoint), NullnessCasts.uncheckedCastNullableTToT(upperEndpoint));
         Preconditions.checkArgument(cmp <= 0, "lowerEndpoint (%s) > upperEndpoint (%s)", lowerEndpoint, upperEndpoint);
         if (cmp == 0) {
            Preconditions.checkArgument(lowerBoundType != BoundType.OPEN || upperBoundType != BoundType.OPEN);
         }
      }

   }

   Comparator comparator() {
      return this.comparator;
   }

   boolean hasLowerBound() {
      return this.hasLowerBound;
   }

   boolean hasUpperBound() {
      return this.hasUpperBound;
   }

   boolean isEmpty() {
      return this.hasUpperBound() && this.tooLow(NullnessCasts.uncheckedCastNullableTToT(this.getUpperEndpoint())) || this.hasLowerBound() && this.tooHigh(NullnessCasts.uncheckedCastNullableTToT(this.getLowerEndpoint()));
   }

   boolean tooLow(@ParametricNullness Object t) {
      if (!this.hasLowerBound()) {
         return false;
      } else {
         T lbound = (T)NullnessCasts.uncheckedCastNullableTToT(this.getLowerEndpoint());
         int cmp = this.comparator.compare(t, lbound);
         return cmp < 0 | cmp == 0 & this.getLowerBoundType() == BoundType.OPEN;
      }
   }

   boolean tooHigh(@ParametricNullness Object t) {
      if (!this.hasUpperBound()) {
         return false;
      } else {
         T ubound = (T)NullnessCasts.uncheckedCastNullableTToT(this.getUpperEndpoint());
         int cmp = this.comparator.compare(t, ubound);
         return cmp > 0 | cmp == 0 & this.getUpperBoundType() == BoundType.OPEN;
      }
   }

   boolean contains(@ParametricNullness Object t) {
      return !this.tooLow(t) && !this.tooHigh(t);
   }

   GeneralRange intersect(GeneralRange other) {
      Preconditions.checkNotNull(other);
      Preconditions.checkArgument(this.comparator.equals(other.comparator));
      boolean hasLowBound = this.hasLowerBound;
      T lowEnd = (T)this.getLowerEndpoint();
      BoundType lowType = this.getLowerBoundType();
      if (!this.hasLowerBound()) {
         hasLowBound = other.hasLowerBound;
         lowEnd = (T)other.getLowerEndpoint();
         lowType = other.getLowerBoundType();
      } else if (other.hasLowerBound()) {
         int cmp = this.comparator.compare(this.getLowerEndpoint(), other.getLowerEndpoint());
         if (cmp < 0 || cmp == 0 && other.getLowerBoundType() == BoundType.OPEN) {
            lowEnd = (T)other.getLowerEndpoint();
            lowType = other.getLowerBoundType();
         }
      }

      boolean hasUpBound = this.hasUpperBound;
      T upEnd = (T)this.getUpperEndpoint();
      BoundType upType = this.getUpperBoundType();
      if (!this.hasUpperBound()) {
         hasUpBound = other.hasUpperBound;
         upEnd = (T)other.getUpperEndpoint();
         upType = other.getUpperBoundType();
      } else if (other.hasUpperBound()) {
         int cmp = this.comparator.compare(this.getUpperEndpoint(), other.getUpperEndpoint());
         if (cmp > 0 || cmp == 0 && other.getUpperBoundType() == BoundType.OPEN) {
            upEnd = (T)other.getUpperEndpoint();
            upType = other.getUpperBoundType();
         }
      }

      if (hasLowBound && hasUpBound) {
         int cmp = this.comparator.compare(lowEnd, upEnd);
         if (cmp > 0 || cmp == 0 && lowType == BoundType.OPEN && upType == BoundType.OPEN) {
            lowEnd = upEnd;
            lowType = BoundType.OPEN;
            upType = BoundType.CLOSED;
         }
      }

      return new GeneralRange(this.comparator, hasLowBound, lowEnd, lowType, hasUpBound, upEnd, upType);
   }

   public boolean equals(@CheckForNull Object obj) {
      if (!(obj instanceof GeneralRange)) {
         return false;
      } else {
         GeneralRange<?> r = (GeneralRange)obj;
         return this.comparator.equals(r.comparator) && this.hasLowerBound == r.hasLowerBound && this.hasUpperBound == r.hasUpperBound && this.getLowerBoundType().equals(r.getLowerBoundType()) && this.getUpperBoundType().equals(r.getUpperBoundType()) && Objects.equal(this.getLowerEndpoint(), r.getLowerEndpoint()) && Objects.equal(this.getUpperEndpoint(), r.getUpperEndpoint());
      }
   }

   public int hashCode() {
      return Objects.hashCode(this.comparator, this.getLowerEndpoint(), this.getLowerBoundType(), this.getUpperEndpoint(), this.getUpperBoundType());
   }

   GeneralRange reverse() {
      GeneralRange<T> result = this.reverse;
      if (result == null) {
         result = new GeneralRange(Ordering.from(this.comparator).reverse(), this.hasUpperBound, this.getUpperEndpoint(), this.getUpperBoundType(), this.hasLowerBound, this.getLowerEndpoint(), this.getLowerBoundType());
         result.reverse = this;
         return this.reverse = result;
      } else {
         return result;
      }
   }

   public String toString() {
      return this.comparator + ":" + (this.lowerBoundType == BoundType.CLOSED ? '[' : '(') + (this.hasLowerBound ? this.lowerEndpoint : "-∞") + ',' + (this.hasUpperBound ? this.upperEndpoint : "∞") + (this.upperBoundType == BoundType.CLOSED ? ']' : ')');
   }

   @CheckForNull
   Object getLowerEndpoint() {
      return this.lowerEndpoint;
   }

   BoundType getLowerBoundType() {
      return this.lowerBoundType;
   }

   @CheckForNull
   Object getUpperEndpoint() {
      return this.upperEndpoint;
   }

   BoundType getUpperBoundType() {
      return this.upperBoundType;
   }
}
