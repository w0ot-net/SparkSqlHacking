package cats.kernel.instances.vector;

import cats.kernel.Eq;
import cats.kernel.Hash;
import cats.kernel.Monoid;
import cats.kernel.Order;
import cats.kernel.PartialOrder;
import cats.kernel.instances.VectorInstances;
import cats.kernel.instances.VectorInstances1;
import cats.kernel.instances.VectorInstances2;

public final class package$ implements VectorInstances {
   public static final package$ MODULE$ = new package$();

   static {
      VectorInstances2.$init$(MODULE$);
      VectorInstances1.$init$(MODULE$);
      VectorInstances.$init$(MODULE$);
   }

   public Order catsKernelStdOrderForVector(final Order evidence$1) {
      return VectorInstances.catsKernelStdOrderForVector$(this, evidence$1);
   }

   public Monoid catsKernelStdMonoidForVector() {
      return VectorInstances.catsKernelStdMonoidForVector$(this);
   }

   public PartialOrder catsKernelStdPartialOrderForVector(final PartialOrder evidence$2) {
      return VectorInstances1.catsKernelStdPartialOrderForVector$(this, evidence$2);
   }

   public Hash catsKernelStdHashForVector(final Hash evidence$3) {
      return VectorInstances1.catsKernelStdHashForVector$(this, evidence$3);
   }

   public Eq catsKernelStdEqForVector(final Eq evidence$4) {
      return VectorInstances2.catsKernelStdEqForVector$(this, evidence$4);
   }

   private package$() {
   }
}
