package algebra.instances.option;

import algebra.instances.OptionInstances;
import cats.kernel.CommutativeMonoid;
import cats.kernel.CommutativeSemigroup;
import cats.kernel.Eq;
import cats.kernel.Hash;
import cats.kernel.Monoid;
import cats.kernel.Order;
import cats.kernel.PartialOrder;
import cats.kernel.Semigroup;
import cats.kernel.instances.OptionInstances0;
import cats.kernel.instances.OptionInstances1;
import cats.kernel.instances.OptionInstances2;

public final class package$ implements OptionInstances {
   public static final package$ MODULE$ = new package$();

   static {
      OptionInstances2.$init$(MODULE$);
      OptionInstances1.$init$(MODULE$);
      OptionInstances0.$init$(MODULE$);
      cats.kernel.instances.OptionInstances.$init$(MODULE$);
   }

   public Order catsKernelStdOrderForOption(final Order evidence$1) {
      return cats.kernel.instances.OptionInstances.catsKernelStdOrderForOption$(this, evidence$1);
   }

   public CommutativeMonoid catsKernelStdCommutativeMonoidForOption(final CommutativeSemigroup evidence$2) {
      return cats.kernel.instances.OptionInstances.catsKernelStdCommutativeMonoidForOption$(this, evidence$2);
   }

   public Monoid catsKernelStdMonoidForOption(final Semigroup evidence$3) {
      return cats.kernel.instances.OptionInstances.catsKernelStdMonoidForOption$(this, evidence$3);
   }

   public PartialOrder catsKernelStdPartialOrderForOption(final PartialOrder evidence$4) {
      return OptionInstances0.catsKernelStdPartialOrderForOption$(this, evidence$4);
   }

   public Hash catsKernelStdHashForOption(final Hash evidence$5) {
      return OptionInstances1.catsKernelStdHashForOption$(this, evidence$5);
   }

   public Eq catsKernelStdEqForOption(final Eq evidence$6) {
      return OptionInstances2.catsKernelStdEqForOption$(this, evidence$6);
   }

   private package$() {
   }
}
