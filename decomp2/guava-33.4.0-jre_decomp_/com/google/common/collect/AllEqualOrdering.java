package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import java.io.Serializable;
import java.util.List;
import javax.annotation.CheckForNull;

@ElementTypesAreNonnullByDefault
@GwtCompatible(
   serializable = true
)
final class AllEqualOrdering extends Ordering implements Serializable {
   static final AllEqualOrdering INSTANCE = new AllEqualOrdering();
   private static final long serialVersionUID = 0L;

   public int compare(@CheckForNull Object left, @CheckForNull Object right) {
      return 0;
   }

   public List sortedCopy(Iterable iterable) {
      return Lists.newArrayList(iterable);
   }

   public ImmutableList immutableSortedCopy(Iterable iterable) {
      return ImmutableList.copyOf(iterable);
   }

   public Ordering reverse() {
      return this;
   }

   private Object readResolve() {
      return INSTANCE;
   }

   public String toString() {
      return "Ordering.allEqual()";
   }
}
