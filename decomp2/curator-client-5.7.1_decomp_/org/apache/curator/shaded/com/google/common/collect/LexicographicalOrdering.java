package org.apache.curator.shaded.com.google.common.collect;

import java.io.Serializable;
import java.util.Comparator;
import java.util.Iterator;
import javax.annotation.CheckForNull;
import org.apache.curator.shaded.com.google.common.annotations.GwtCompatible;

@ElementTypesAreNonnullByDefault
@GwtCompatible(
   serializable = true
)
final class LexicographicalOrdering extends Ordering implements Serializable {
   final Comparator elementOrder;
   private static final long serialVersionUID = 0L;

   LexicographicalOrdering(Comparator elementOrder) {
      this.elementOrder = elementOrder;
   }

   public int compare(Iterable leftIterable, Iterable rightIterable) {
      Iterator<T> left = leftIterable.iterator();
      Iterator<T> right = rightIterable.iterator();

      while(left.hasNext()) {
         if (!right.hasNext()) {
            return 1;
         }

         int result = this.elementOrder.compare(left.next(), right.next());
         if (result != 0) {
            return result;
         }
      }

      if (right.hasNext()) {
         return -1;
      } else {
         return 0;
      }
   }

   public boolean equals(@CheckForNull Object object) {
      if (object == this) {
         return true;
      } else if (object instanceof LexicographicalOrdering) {
         LexicographicalOrdering<?> that = (LexicographicalOrdering)object;
         return this.elementOrder.equals(that.elementOrder);
      } else {
         return false;
      }
   }

   public int hashCode() {
      return this.elementOrder.hashCode() ^ 2075626741;
   }

   public String toString() {
      return this.elementOrder + ".lexicographical()";
   }
}
