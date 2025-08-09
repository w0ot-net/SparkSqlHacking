package cats.kernel.instances.double;

import cats.kernel.CommutativeGroup;
import cats.kernel.Order;
import cats.kernel.instances.DoubleInstances;

public final class package$ implements DoubleInstances {
   public static final package$ MODULE$ = new package$();
   private static Order catsKernelStdOrderForDouble;
   private static CommutativeGroup catsKernelStdGroupForDouble;

   static {
      DoubleInstances.$init$(MODULE$);
   }

   public Order catsKernelStdOrderForDouble() {
      return catsKernelStdOrderForDouble;
   }

   public CommutativeGroup catsKernelStdGroupForDouble() {
      return catsKernelStdGroupForDouble;
   }

   public void cats$kernel$instances$DoubleInstances$_setter_$catsKernelStdOrderForDouble_$eq(final Order x$1) {
      catsKernelStdOrderForDouble = x$1;
   }

   public void cats$kernel$instances$DoubleInstances$_setter_$catsKernelStdGroupForDouble_$eq(final CommutativeGroup x$1) {
      catsKernelStdGroupForDouble = x$1;
   }

   private package$() {
   }
}
