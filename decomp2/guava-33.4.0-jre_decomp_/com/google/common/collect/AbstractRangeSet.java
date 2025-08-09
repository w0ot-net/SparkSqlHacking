package com.google.common.collect;

import com.google.common.annotations.GwtIncompatible;
import javax.annotation.CheckForNull;

@ElementTypesAreNonnullByDefault
@GwtIncompatible
abstract class AbstractRangeSet implements RangeSet {
   public boolean contains(Comparable value) {
      return this.rangeContaining(value) != null;
   }

   @CheckForNull
   public abstract Range rangeContaining(Comparable value);

   public boolean isEmpty() {
      return this.asRanges().isEmpty();
   }

   public void add(Range range) {
      throw new UnsupportedOperationException();
   }

   public void remove(Range range) {
      throw new UnsupportedOperationException();
   }

   public void clear() {
      this.remove(Range.all());
   }

   public boolean enclosesAll(RangeSet other) {
      return this.enclosesAll(other.asRanges());
   }

   public void addAll(RangeSet other) {
      this.addAll(other.asRanges());
   }

   public void removeAll(RangeSet other) {
      this.removeAll(other.asRanges());
   }

   public boolean intersects(Range otherRange) {
      return !this.subRangeSet(otherRange).isEmpty();
   }

   public abstract boolean encloses(Range otherRange);

   public boolean equals(@CheckForNull Object obj) {
      if (obj == this) {
         return true;
      } else if (obj instanceof RangeSet) {
         RangeSet<?> other = (RangeSet)obj;
         return this.asRanges().equals(other.asRanges());
      } else {
         return false;
      }
   }

   public final int hashCode() {
      return this.asRanges().hashCode();
   }

   public final String toString() {
      return this.asRanges().toString();
   }
}
