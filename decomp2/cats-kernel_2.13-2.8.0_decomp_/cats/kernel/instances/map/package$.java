package cats.kernel.instances.map;

import cats.kernel.CommutativeMonoid;
import cats.kernel.CommutativeSemigroup;
import cats.kernel.Eq;
import cats.kernel.Hash;
import cats.kernel.Monoid;
import cats.kernel.Semigroup;
import cats.kernel.instances.MapInstances;
import cats.kernel.instances.MapInstances1;

public final class package$ implements MapInstances {
   public static final package$ MODULE$ = new package$();

   static {
      MapInstances1.$init$(MODULE$);
      MapInstances.$init$(MODULE$);
   }

   public Hash catsKernelStdHashForMap(final Hash evidence$1, final Hash evidence$2) {
      return MapInstances.catsKernelStdHashForMap$(this, evidence$1, evidence$2);
   }

   public CommutativeMonoid catsKernelStdCommutativeMonoidForMap(final CommutativeSemigroup evidence$3) {
      return MapInstances.catsKernelStdCommutativeMonoidForMap$(this, evidence$3);
   }

   public Eq catsKernelStdEqForMap(final Eq evidence$4) {
      return MapInstances1.catsKernelStdEqForMap$(this, evidence$4);
   }

   public Monoid catsKernelStdMonoidForMap(final Semigroup evidence$5) {
      return MapInstances1.catsKernelStdMonoidForMap$(this, evidence$5);
   }

   private package$() {
   }
}
