package cats.kernel.instances.stream;

import cats.kernel.Eq;
import cats.kernel.Hash;
import cats.kernel.Monoid;
import cats.kernel.Order;
import cats.kernel.PartialOrder;
import cats.kernel.instances.StreamInstances;
import cats.kernel.instances.StreamInstances1;
import cats.kernel.instances.StreamInstances2;

public final class package$ implements StreamInstances {
   public static final package$ MODULE$ = new package$();

   static {
      StreamInstances2.$init$(MODULE$);
      StreamInstances1.$init$(MODULE$);
      StreamInstances.$init$(MODULE$);
   }

   /** @deprecated */
   public Order catsKernelStdOrderForStream(final Order evidence$1) {
      return StreamInstances.catsKernelStdOrderForStream$(this, evidence$1);
   }

   /** @deprecated */
   public Monoid catsKernelStdMonoidForStream() {
      return StreamInstances.catsKernelStdMonoidForStream$(this);
   }

   /** @deprecated */
   public PartialOrder catsKernelStdPartialOrderForStream(final PartialOrder evidence$2) {
      return StreamInstances1.catsKernelStdPartialOrderForStream$(this, evidence$2);
   }

   /** @deprecated */
   public Hash catsKernelStdHashForStream(final Hash evidence$3) {
      return StreamInstances1.catsKernelStdHashForStream$(this, evidence$3);
   }

   /** @deprecated */
   public Eq catsKernelStdEqForStream(final Eq evidence$4) {
      return StreamInstances2.catsKernelStdEqForStream$(this, evidence$4);
   }

   private package$() {
   }
}
