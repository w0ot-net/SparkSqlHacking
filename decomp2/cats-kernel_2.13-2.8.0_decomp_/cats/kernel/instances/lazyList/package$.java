package cats.kernel.instances.lazyList;

import cats.kernel.Eq;
import cats.kernel.Hash;
import cats.kernel.Monoid;
import cats.kernel.Order;
import cats.kernel.PartialOrder;
import cats.kernel.instances.LazyListInstances;
import cats.kernel.instances.LazyListInstances1;
import cats.kernel.instances.LazyListInstances2;

public final class package$ implements LazyListInstances {
   public static final package$ MODULE$ = new package$();

   static {
      LazyListInstances2.$init$(MODULE$);
      LazyListInstances1.$init$(MODULE$);
      LazyListInstances.$init$(MODULE$);
   }

   public Order catsKernelStdOrderForLazyList(final Order evidence$1) {
      return LazyListInstances.catsKernelStdOrderForLazyList$(this, evidence$1);
   }

   public Monoid catsKernelStdMonoidForLazyList() {
      return LazyListInstances.catsKernelStdMonoidForLazyList$(this);
   }

   public PartialOrder catsKernelStdPartialOrderForLazyList(final PartialOrder evidence$2) {
      return LazyListInstances1.catsKernelStdPartialOrderForLazyList$(this, evidence$2);
   }

   public Hash catsKernelStdHashForLazyList(final Hash evidence$3) {
      return LazyListInstances1.catsKernelStdHashForLazyList$(this, evidence$3);
   }

   public Eq catsKernelStdEqForLazyList(final Eq evidence$4) {
      return LazyListInstances2.catsKernelStdEqForLazyList$(this, evidence$4);
   }

   private package$() {
   }
}
