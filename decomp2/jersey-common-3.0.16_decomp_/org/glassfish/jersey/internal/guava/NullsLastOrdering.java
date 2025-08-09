package org.glassfish.jersey.internal.guava;

import java.io.Serializable;

final class NullsLastOrdering extends Ordering implements Serializable {
   private static final long serialVersionUID = 0L;
   private final Ordering ordering;

   NullsLastOrdering(Ordering ordering) {
      this.ordering = ordering;
   }

   public int compare(Object left, Object right) {
      if (left == right) {
         return 0;
      } else if (left == null) {
         return 1;
      } else {
         return right == null ? -1 : this.ordering.compare(left, right);
      }
   }

   public Ordering reverse() {
      return this.ordering.reverse().nullsFirst();
   }

   public Ordering nullsFirst() {
      return this.ordering.nullsFirst();
   }

   public Ordering nullsLast() {
      return this;
   }

   public boolean equals(Object object) {
      if (object == this) {
         return true;
      } else if (object instanceof NullsLastOrdering) {
         NullsLastOrdering<?> that = (NullsLastOrdering)object;
         return this.ordering.equals(that.ordering);
      } else {
         return false;
      }
   }

   public int hashCode() {
      return this.ordering.hashCode() ^ -921210296;
   }

   public String toString() {
      return this.ordering + ".nullsLast()";
   }
}
