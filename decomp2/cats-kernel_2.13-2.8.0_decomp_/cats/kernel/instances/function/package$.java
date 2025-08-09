package cats.kernel.instances.function;

import cats.kernel.Band;
import cats.kernel.BoundedSemilattice;
import cats.kernel.CommutativeGroup;
import cats.kernel.CommutativeMonoid;
import cats.kernel.CommutativeSemigroup;
import cats.kernel.Eq;
import cats.kernel.Group;
import cats.kernel.Hash;
import cats.kernel.Monoid;
import cats.kernel.Order;
import cats.kernel.PartialOrder;
import cats.kernel.Semigroup;
import cats.kernel.Semilattice;
import cats.kernel.instances.FunctionInstances;
import cats.kernel.instances.FunctionInstances0;
import cats.kernel.instances.FunctionInstances1;
import cats.kernel.instances.FunctionInstances2;
import cats.kernel.instances.FunctionInstances3;
import cats.kernel.instances.FunctionInstances4;

public final class package$ implements FunctionInstances {
   public static final package$ MODULE$ = new package$();

   static {
      FunctionInstances4.$init$(MODULE$);
      FunctionInstances3.$init$(MODULE$);
      FunctionInstances2.$init$(MODULE$);
      FunctionInstances1.$init$(MODULE$);
      FunctionInstances0.$init$(MODULE$);
      FunctionInstances.$init$(MODULE$);
   }

   public Order catsKernelOrderForFunction0(final Order ev) {
      return FunctionInstances.catsKernelOrderForFunction0$(this, ev);
   }

   public CommutativeGroup catsKernelCommutativeGroupForFunction0(final CommutativeGroup G) {
      return FunctionInstances.catsKernelCommutativeGroupForFunction0$(this, G);
   }

   public CommutativeGroup catsKernelCommutativeGroupForFunction1(final CommutativeGroup G) {
      return FunctionInstances.catsKernelCommutativeGroupForFunction1$(this, G);
   }

   public Hash catsKernelHashForFunction0(final Hash ev) {
      return FunctionInstances0.catsKernelHashForFunction0$(this, ev);
   }

   public PartialOrder catsKernelPartialOrderForFunction0(final PartialOrder ev) {
      return FunctionInstances0.catsKernelPartialOrderForFunction0$(this, ev);
   }

   public Group catsKernelGroupForFunction0(final Group G) {
      return FunctionInstances0.catsKernelGroupForFunction0$(this, G);
   }

   public Group catsKernelGroupForFunction1(final Group G) {
      return FunctionInstances0.catsKernelGroupForFunction1$(this, G);
   }

   public BoundedSemilattice catsKernelBoundedSemilatticeForFunction0(final BoundedSemilattice G) {
      return FunctionInstances0.catsKernelBoundedSemilatticeForFunction0$(this, G);
   }

   public BoundedSemilattice catsKernelBoundedSemilatticeForFunction1(final BoundedSemilattice G) {
      return FunctionInstances0.catsKernelBoundedSemilatticeForFunction1$(this, G);
   }

   public Eq catsKernelEqForFunction0(final Eq ev) {
      return FunctionInstances1.catsKernelEqForFunction0$(this, ev);
   }

   public CommutativeMonoid catsKernelCommutativeMonoidForFunction0(final CommutativeMonoid M) {
      return FunctionInstances1.catsKernelCommutativeMonoidForFunction0$(this, M);
   }

   public CommutativeMonoid catsKernelCommutativeMonoidForFunction1(final CommutativeMonoid M) {
      return FunctionInstances1.catsKernelCommutativeMonoidForFunction1$(this, M);
   }

   public Semilattice catsKernelSemilatticeForFunction0(final Semilattice M) {
      return FunctionInstances1.catsKernelSemilatticeForFunction0$(this, M);
   }

   public Semilattice catsKernelSemilatticeForFunction1(final Semilattice M) {
      return FunctionInstances1.catsKernelSemilatticeForFunction1$(this, M);
   }

   public Monoid catsKernelMonoidForFunction0(final Monoid M) {
      return FunctionInstances2.catsKernelMonoidForFunction0$(this, M);
   }

   public Monoid catsKernelMonoidForFunction1(final Monoid M) {
      return FunctionInstances2.catsKernelMonoidForFunction1$(this, M);
   }

   public Band catsKernelBandForFunction0(final Band S) {
      return FunctionInstances2.catsKernelBandForFunction0$(this, S);
   }

   public Band catsKernelBandForFunction1(final Band S) {
      return FunctionInstances2.catsKernelBandForFunction1$(this, S);
   }

   public CommutativeSemigroup catsKernelCommutativeSemigroupForFunction0(final CommutativeSemigroup S) {
      return FunctionInstances3.catsKernelCommutativeSemigroupForFunction0$(this, S);
   }

   public CommutativeSemigroup catsKernelCommutativeSemigroupForFunction1(final CommutativeSemigroup S) {
      return FunctionInstances3.catsKernelCommutativeSemigroupForFunction1$(this, S);
   }

   public Semigroup catsKernelSemigroupForFunction0(final Semigroup S) {
      return FunctionInstances4.catsKernelSemigroupForFunction0$(this, S);
   }

   public Semigroup catsKernelSemigroupForFunction1(final Semigroup S) {
      return FunctionInstances4.catsKernelSemigroupForFunction1$(this, S);
   }

   private package$() {
   }
}
