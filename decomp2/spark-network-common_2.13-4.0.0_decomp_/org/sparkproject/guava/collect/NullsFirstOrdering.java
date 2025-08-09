package org.sparkproject.guava.collect;

import java.io.Serializable;
import javax.annotation.CheckForNull;
import org.sparkproject.guava.annotations.GwtCompatible;

@ElementTypesAreNonnullByDefault
@GwtCompatible(
   serializable = true
)
final class NullsFirstOrdering extends Ordering implements Serializable {
   final Ordering ordering;
   private static final long serialVersionUID = 0L;

   NullsFirstOrdering(Ordering ordering) {
      this.ordering = ordering;
   }

   public int compare(@CheckForNull Object left, @CheckForNull Object right) {
      if (left == right) {
         return 0;
      } else if (left == null) {
         return -1;
      } else {
         return right == null ? 1 : this.ordering.compare(left, right);
      }
   }

   public Ordering reverse() {
      return this.ordering.reverse().nullsLast();
   }

   public Ordering nullsFirst() {
      return this;
   }

   public Ordering nullsLast() {
      return this.ordering.nullsLast();
   }

   public boolean equals(@CheckForNull Object object) {
      if (object == this) {
         return true;
      } else if (object instanceof NullsFirstOrdering) {
         NullsFirstOrdering<?> that = (NullsFirstOrdering)object;
         return this.ordering.equals(that.ordering);
      } else {
         return false;
      }
   }

   public int hashCode() {
      return this.ordering.hashCode() ^ 957692532;
   }

   public String toString() {
      return this.ordering + ".nullsFirst()";
   }
}
