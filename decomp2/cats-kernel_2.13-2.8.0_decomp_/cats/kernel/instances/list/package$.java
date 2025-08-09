package cats.kernel.instances.list;

import cats.kernel.Eq;
import cats.kernel.Hash;
import cats.kernel.Monoid;
import cats.kernel.Order;
import cats.kernel.PartialOrder;
import cats.kernel.instances.ListInstances;
import cats.kernel.instances.ListInstances1;
import cats.kernel.instances.ListInstances2;

public final class package$ implements ListInstances {
   public static final package$ MODULE$ = new package$();

   static {
      ListInstances2.$init$(MODULE$);
      ListInstances1.$init$(MODULE$);
      ListInstances.$init$(MODULE$);
   }

   public Order catsKernelStdOrderForList(final Order evidence$1) {
      return ListInstances.catsKernelStdOrderForList$(this, evidence$1);
   }

   public Monoid catsKernelStdMonoidForList() {
      return ListInstances.catsKernelStdMonoidForList$(this);
   }

   public PartialOrder catsKernelStdPartialOrderForList(final PartialOrder evidence$2) {
      return ListInstances1.catsKernelStdPartialOrderForList$(this, evidence$2);
   }

   public Hash catsKernelStdHashForList(final Hash evidence$3) {
      return ListInstances1.catsKernelStdHashForList$(this, evidence$3);
   }

   public Eq catsKernelStdEqForList(final Eq evidence$4) {
      return ListInstances2.catsKernelStdEqForList$(this, evidence$4);
   }

   private package$() {
   }
}
