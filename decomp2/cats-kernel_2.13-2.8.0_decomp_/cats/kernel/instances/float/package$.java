package cats.kernel.instances.float;

import cats.kernel.CommutativeGroup;
import cats.kernel.Order;
import cats.kernel.instances.FloatInstances;

public final class package$ implements FloatInstances {
   public static final package$ MODULE$ = new package$();
   private static Order catsKernelStdOrderForFloat;
   private static CommutativeGroup catsKernelStdGroupForFloat;

   static {
      FloatInstances.$init$(MODULE$);
   }

   public Order catsKernelStdOrderForFloat() {
      return catsKernelStdOrderForFloat;
   }

   public CommutativeGroup catsKernelStdGroupForFloat() {
      return catsKernelStdGroupForFloat;
   }

   public void cats$kernel$instances$FloatInstances$_setter_$catsKernelStdOrderForFloat_$eq(final Order x$1) {
      catsKernelStdOrderForFloat = x$1;
   }

   public void cats$kernel$instances$FloatInstances$_setter_$catsKernelStdGroupForFloat_$eq(final CommutativeGroup x$1) {
      catsKernelStdGroupForFloat = x$1;
   }

   private package$() {
   }
}
