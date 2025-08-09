package org.apache.commons.lang3.tuple;

import java.io.Serializable;
import java.util.Objects;
import org.apache.commons.lang3.builder.CompareToBuilder;

public abstract class Triple implements Comparable, Serializable {
   private static final long serialVersionUID = 1L;
   public static final Triple[] EMPTY_ARRAY = new Triple[0];

   public static Triple[] emptyArray() {
      return EMPTY_ARRAY;
   }

   public static Triple of(Object left, Object middle, Object right) {
      return ImmutableTriple.of(left, middle, right);
   }

   public static Triple ofNonNull(Object left, Object middle, Object right) {
      return ImmutableTriple.ofNonNull(left, middle, right);
   }

   public int compareTo(Triple other) {
      return (new CompareToBuilder()).append(this.getLeft(), other.getLeft()).append(this.getMiddle(), other.getMiddle()).append(this.getRight(), other.getRight()).toComparison();
   }

   public boolean equals(Object obj) {
      if (obj == this) {
         return true;
      } else if (!(obj instanceof Triple)) {
         return false;
      } else {
         Triple<?, ?, ?> other = (Triple)obj;
         return Objects.equals(this.getLeft(), other.getLeft()) && Objects.equals(this.getMiddle(), other.getMiddle()) && Objects.equals(this.getRight(), other.getRight());
      }
   }

   public abstract Object getLeft();

   public abstract Object getMiddle();

   public abstract Object getRight();

   public int hashCode() {
      return Objects.hashCode(this.getLeft()) ^ Objects.hashCode(this.getMiddle()) ^ Objects.hashCode(this.getRight());
   }

   public String toString() {
      return "(" + this.getLeft() + "," + this.getMiddle() + "," + this.getRight() + ")";
   }

   public String toString(String format) {
      return String.format(format, this.getLeft(), this.getMiddle(), this.getRight());
   }
}
