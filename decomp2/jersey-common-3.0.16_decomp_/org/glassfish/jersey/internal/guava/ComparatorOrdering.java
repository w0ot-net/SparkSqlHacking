package org.glassfish.jersey.internal.guava;

import java.io.Serializable;
import java.util.Comparator;

final class ComparatorOrdering extends Ordering implements Serializable {
   private static final long serialVersionUID = 0L;
   private final Comparator comparator;

   ComparatorOrdering(Comparator comparator) {
      this.comparator = (Comparator)Preconditions.checkNotNull(comparator);
   }

   public int compare(Object a, Object b) {
      return this.comparator.compare(a, b);
   }

   public boolean equals(Object object) {
      if (object == this) {
         return true;
      } else if (object instanceof ComparatorOrdering) {
         ComparatorOrdering<?> that = (ComparatorOrdering)object;
         return this.comparator.equals(that.comparator);
      } else {
         return false;
      }
   }

   public int hashCode() {
      return this.comparator.hashCode();
   }

   public String toString() {
      return this.comparator.toString();
   }
}
