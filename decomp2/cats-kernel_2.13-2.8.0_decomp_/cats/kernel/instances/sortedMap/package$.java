package cats.kernel.instances.sortedMap;

import cats.kernel.CommutativeMonoid;
import cats.kernel.CommutativeSemigroup;
import cats.kernel.Eq;
import cats.kernel.Hash;
import cats.kernel.Monoid;
import cats.kernel.Order;
import cats.kernel.PartialOrder;
import cats.kernel.Semigroup;
import cats.kernel.instances.SortedMapInstances;
import cats.kernel.instances.SortedMapInstances1;
import cats.kernel.instances.SortedMapInstances2;
import cats.kernel.instances.SortedMapInstances3;

public final class package$ implements SortedMapInstances {
   public static final package$ MODULE$ = new package$();

   static {
      SortedMapInstances1.$init$(MODULE$);
      SortedMapInstances2.$init$(MODULE$);
      SortedMapInstances3.$init$(MODULE$);
      SortedMapInstances.$init$(MODULE$);
   }

   public Hash catsKernelStdHashForSortedMap(final Hash evidence$1, final Hash evidence$2) {
      return SortedMapInstances.catsKernelStdHashForSortedMap$(this, evidence$1, evidence$2);
   }

   /** @deprecated */
   public Hash catsKernelStdHashForSortedMap(final Hash hashK, final Order orderK, final Hash hashV) {
      return SortedMapInstances.catsKernelStdHashForSortedMap$(this, hashK, orderK, hashV);
   }

   public CommutativeSemigroup catsKernelStdCommutativeSemigroupForSortedMap(final CommutativeSemigroup evidence$3) {
      return SortedMapInstances.catsKernelStdCommutativeSemigroupForSortedMap$(this, evidence$3);
   }

   public CommutativeMonoid catsKernelStdCommutativeMonoidForSortedMap(final Order evidence$4, final CommutativeSemigroup evidence$5) {
      return SortedMapInstances.catsKernelStdCommutativeMonoidForSortedMap$(this, evidence$4, evidence$5);
   }

   public Order catsKernelStdOrderForSortedMap(final Order evidence$11) {
      return SortedMapInstances3.catsKernelStdOrderForSortedMap$(this, evidence$11);
   }

   public Semigroup catsKernelStdSemigroupForSortedMap(final Semigroup evidence$7) {
      return SortedMapInstances2.catsKernelStdSemigroupForSortedMap$(this, evidence$7);
   }

   public Monoid catsKernelStdMonoidForSortedMap(final Order evidence$8, final Semigroup evidence$9) {
      return SortedMapInstances2.catsKernelStdMonoidForSortedMap$(this, evidence$8, evidence$9);
   }

   public PartialOrder catsKernelStdPartialOrderForSortedMap(final PartialOrder evidence$10) {
      return SortedMapInstances2.catsKernelStdPartialOrderForSortedMap$(this, evidence$10);
   }

   public Eq catsKernelStdEqForSortedMap(final Eq evidence$6) {
      return SortedMapInstances1.catsKernelStdEqForSortedMap$(this, evidence$6);
   }

   /** @deprecated */
   public Eq catsKernelStdEqForSortedMap(final Order orderK, final Eq eqV) {
      return SortedMapInstances1.catsKernelStdEqForSortedMap$(this, orderK, eqV);
   }

   private package$() {
   }
}
