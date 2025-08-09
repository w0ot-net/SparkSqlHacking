package algebra.instances.string;

import algebra.instances.StringInstances;
import cats.kernel.Monoid;
import cats.kernel.Order;

public final class package$ implements StringInstances {
   public static final package$ MODULE$ = new package$();
   private static Order catsKernelStdOrderForString;
   private static Monoid catsKernelStdMonoidForString;

   static {
      cats.kernel.instances.StringInstances.$init$(MODULE$);
   }

   public Order catsKernelStdOrderForString() {
      return catsKernelStdOrderForString;
   }

   public Monoid catsKernelStdMonoidForString() {
      return catsKernelStdMonoidForString;
   }

   public void cats$kernel$instances$StringInstances$_setter_$catsKernelStdOrderForString_$eq(final Order x$1) {
      catsKernelStdOrderForString = x$1;
   }

   public void cats$kernel$instances$StringInstances$_setter_$catsKernelStdMonoidForString_$eq(final Monoid x$1) {
      catsKernelStdMonoidForString = x$1;
   }

   private package$() {
   }
}
