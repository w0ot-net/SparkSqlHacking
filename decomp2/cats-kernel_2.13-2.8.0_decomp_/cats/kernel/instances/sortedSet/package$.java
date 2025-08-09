package cats.kernel.instances.sortedSet;

import cats.kernel.BoundedSemilattice;
import cats.kernel.Hash;
import cats.kernel.Order;
import cats.kernel.instances.SortedSetInstances;
import cats.kernel.instances.SortedSetInstances1;

public final class package$ implements SortedSetInstances {
   public static final package$ MODULE$ = new package$();

   static {
      SortedSetInstances1.$init$(MODULE$);
      SortedSetInstances.$init$(MODULE$);
   }

   /** @deprecated */
   public Hash catsKernelStdHashForSortedSet(final Order evidence$1, final Hash evidence$2) {
      return SortedSetInstances.catsKernelStdHashForSortedSet$(this, evidence$1, evidence$2);
   }

   public Hash catsKernelStdHashForSortedSet(final Hash evidence$3) {
      return SortedSetInstances.catsKernelStdHashForSortedSet$(this, evidence$3);
   }

   public Order catsKernelStdOrderForSortedSet(final Order evidence$4) {
      return SortedSetInstances1.catsKernelStdOrderForSortedSet$(this, evidence$4);
   }

   public BoundedSemilattice catsKernelStdBoundedSemilatticeForSortedSet(final Order evidence$5) {
      return SortedSetInstances1.catsKernelStdBoundedSemilatticeForSortedSet$(this, evidence$5);
   }

   private package$() {
   }
}
