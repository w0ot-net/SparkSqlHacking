package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Comparator;
import javax.annotation.CheckForNull;

@ElementTypesAreNonnullByDefault
@GwtCompatible(
   serializable = true
)
final class CompoundOrdering extends Ordering implements Serializable {
   final Comparator[] comparators;
   private static final long serialVersionUID = 0L;

   CompoundOrdering(Comparator primary, Comparator secondary) {
      this.comparators = new Comparator[]{primary, secondary};
   }

   CompoundOrdering(Iterable comparators) {
      this.comparators = (Comparator[])Iterables.toArray(comparators, (Object[])(new Comparator[0]));
   }

   public int compare(@ParametricNullness Object left, @ParametricNullness Object right) {
      for(int i = 0; i < this.comparators.length; ++i) {
         int result = this.comparators[i].compare(left, right);
         if (result != 0) {
            return result;
         }
      }

      return 0;
   }

   public boolean equals(@CheckForNull Object object) {
      if (object == this) {
         return true;
      } else if (object instanceof CompoundOrdering) {
         CompoundOrdering<?> that = (CompoundOrdering)object;
         return Arrays.equals(this.comparators, that.comparators);
      } else {
         return false;
      }
   }

   public int hashCode() {
      return Arrays.hashCode(this.comparators);
   }

   public String toString() {
      return "Ordering.compound(" + Arrays.toString(this.comparators) + ")";
   }
}
