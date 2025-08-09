package org.apache.curator.shaded.com.google.common.collect;

import java.io.Serializable;
import javax.annotation.CheckForNull;
import org.apache.curator.shaded.com.google.common.annotations.GwtCompatible;

@ElementTypesAreNonnullByDefault
@GwtCompatible(
   serializable = true
)
final class NullsLastOrdering extends Ordering implements Serializable {
   final Ordering ordering;
   private static final long serialVersionUID = 0L;

   NullsLastOrdering(Ordering ordering) {
      this.ordering = ordering;
   }

   public int compare(@CheckForNull Object left, @CheckForNull Object right) {
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

   public boolean equals(@CheckForNull Object object) {
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
