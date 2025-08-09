package org.apache.commons.lang3;

import java.io.Serializable;
import java.util.Comparator;
import java.util.Objects;

public class Range implements Serializable {
   private static final long serialVersionUID = 1L;
   private final Comparator comparator;
   private transient int hashCode;
   private final Object maximum;
   private final Object minimum;
   private transient String toString;

   /** @deprecated */
   @Deprecated
   public static Range between(Comparable fromInclusive, Comparable toInclusive) {
      return of(fromInclusive, toInclusive, (Comparator)null);
   }

   /** @deprecated */
   @Deprecated
   public static Range between(Object fromInclusive, Object toInclusive, Comparator comparator) {
      return new Range(fromInclusive, toInclusive, comparator);
   }

   public static Range is(Comparable element) {
      return of(element, element, (Comparator)null);
   }

   public static Range is(Object element, Comparator comparator) {
      return of(element, element, comparator);
   }

   public static Range of(Comparable fromInclusive, Comparable toInclusive) {
      return of(fromInclusive, toInclusive, (Comparator)null);
   }

   public static Range of(Object fromInclusive, Object toInclusive, Comparator comparator) {
      return new Range(fromInclusive, toInclusive, comparator);
   }

   Range(Object element1, Object element2, Comparator comp) {
      Objects.requireNonNull(element1, "element1");
      Objects.requireNonNull(element2, "element2");
      if (comp == null) {
         this.comparator = Range.ComparableComparator.INSTANCE;
      } else {
         this.comparator = comp;
      }

      if (this.comparator.compare(element1, element2) < 1) {
         this.minimum = element1;
         this.maximum = element2;
      } else {
         this.minimum = element2;
         this.maximum = element1;
      }

   }

   public boolean contains(Object element) {
      if (element == null) {
         return false;
      } else {
         return this.comparator.compare(element, this.minimum) > -1 && this.comparator.compare(element, this.maximum) < 1;
      }
   }

   public boolean containsRange(Range otherRange) {
      if (otherRange == null) {
         return false;
      } else {
         return this.contains(otherRange.minimum) && this.contains(otherRange.maximum);
      }
   }

   public int elementCompareTo(Object element) {
      Objects.requireNonNull(element, "element");
      if (this.isAfter(element)) {
         return -1;
      } else {
         return this.isBefore(element) ? 1 : 0;
      }
   }

   public boolean equals(Object obj) {
      if (obj == this) {
         return true;
      } else if (obj != null && obj.getClass() == this.getClass()) {
         Range<T> range = (Range)obj;
         return this.minimum.equals(range.minimum) && this.maximum.equals(range.maximum);
      } else {
         return false;
      }
   }

   public Object fit(Object element) {
      Objects.requireNonNull(element, "element");
      if (this.isAfter(element)) {
         return this.minimum;
      } else {
         return this.isBefore(element) ? this.maximum : element;
      }
   }

   public Comparator getComparator() {
      return this.comparator;
   }

   public Object getMaximum() {
      return this.maximum;
   }

   public Object getMinimum() {
      return this.minimum;
   }

   public int hashCode() {
      int result = this.hashCode;
      if (this.hashCode == 0) {
         int var2 = 17;
         int var3 = 37 * var2 + this.getClass().hashCode();
         int var4 = 37 * var3 + this.minimum.hashCode();
         result = 37 * var4 + this.maximum.hashCode();
         this.hashCode = result;
      }

      return result;
   }

   public Range intersectionWith(Range other) {
      if (!this.isOverlappedBy(other)) {
         throw new IllegalArgumentException(String.format("Cannot calculate intersection with non-overlapping range %s", other));
      } else if (this.equals(other)) {
         return this;
      } else {
         T min = (T)(this.getComparator().compare(this.minimum, other.minimum) < 0 ? other.minimum : this.minimum);
         T max = (T)(this.getComparator().compare(this.maximum, other.maximum) < 0 ? this.maximum : other.maximum);
         return of(min, max, this.getComparator());
      }
   }

   public boolean isAfter(Object element) {
      if (element == null) {
         return false;
      } else {
         return this.comparator.compare(element, this.minimum) < 0;
      }
   }

   public boolean isAfterRange(Range otherRange) {
      return otherRange == null ? false : this.isAfter(otherRange.maximum);
   }

   public boolean isBefore(Object element) {
      if (element == null) {
         return false;
      } else {
         return this.comparator.compare(element, this.maximum) > 0;
      }
   }

   public boolean isBeforeRange(Range otherRange) {
      return otherRange == null ? false : this.isBefore(otherRange.minimum);
   }

   public boolean isEndedBy(Object element) {
      if (element == null) {
         return false;
      } else {
         return this.comparator.compare(element, this.maximum) == 0;
      }
   }

   public boolean isNaturalOrdering() {
      return this.comparator == Range.ComparableComparator.INSTANCE;
   }

   public boolean isOverlappedBy(Range otherRange) {
      if (otherRange == null) {
         return false;
      } else {
         return otherRange.contains(this.minimum) || otherRange.contains(this.maximum) || this.contains(otherRange.minimum);
      }
   }

   public boolean isStartedBy(Object element) {
      if (element == null) {
         return false;
      } else {
         return this.comparator.compare(element, this.minimum) == 0;
      }
   }

   public String toString() {
      if (this.toString == null) {
         this.toString = "[" + this.minimum + ".." + this.maximum + "]";
      }

      return this.toString;
   }

   public String toString(String format) {
      return String.format(format, this.minimum, this.maximum, this.comparator);
   }

   private static enum ComparableComparator implements Comparator {
      INSTANCE;

      public int compare(Object obj1, Object obj2) {
         return ((Comparable)obj1).compareTo(obj2);
      }

      // $FF: synthetic method
      private static ComparableComparator[] $values() {
         return new ComparableComparator[]{INSTANCE};
      }
   }
}
