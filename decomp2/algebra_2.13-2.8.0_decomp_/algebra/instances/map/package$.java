package algebra.instances.map;

import algebra.instances.MapAdditiveMonoid;
import algebra.instances.MapInstances;
import algebra.instances.MapInstances0;
import algebra.instances.MapInstances2;
import algebra.instances.MapSemiring;
import algebra.ring.AdditiveSemigroup;
import algebra.ring.Semiring;
import cats.kernel.CommutativeMonoid;
import cats.kernel.CommutativeSemigroup;
import cats.kernel.Eq;
import cats.kernel.Hash;
import cats.kernel.Monoid;
import cats.kernel.Semigroup;
import cats.kernel.instances.MapInstances1;

public final class package$ implements MapInstances {
   public static final package$ MODULE$ = new package$();

   static {
      MapInstances1.$init$(MODULE$);
      cats.kernel.instances.MapInstances.$init$(MODULE$);
      MapInstances0.$init$(MODULE$);
      MapInstances2.$init$(MODULE$);
   }

   public MapSemiring mapSemiring(final Semiring evidence$1) {
      return MapInstances2.mapSemiring$(this, evidence$1);
   }

   public MapAdditiveMonoid mapAdditiveMonoid(final AdditiveSemigroup evidence$2) {
      return MapInstances0.mapAdditiveMonoid$(this, evidence$2);
   }

   public Hash catsKernelStdHashForMap(final Hash evidence$1, final Hash evidence$2) {
      return cats.kernel.instances.MapInstances.catsKernelStdHashForMap$(this, evidence$1, evidence$2);
   }

   public CommutativeMonoid catsKernelStdCommutativeMonoidForMap(final CommutativeSemigroup evidence$3) {
      return cats.kernel.instances.MapInstances.catsKernelStdCommutativeMonoidForMap$(this, evidence$3);
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
