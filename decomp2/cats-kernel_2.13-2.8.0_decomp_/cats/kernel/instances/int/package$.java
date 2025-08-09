package cats.kernel.instances.int;

import cats.kernel.CommutativeGroup;
import cats.kernel.Order;
import cats.kernel.instances.IntInstances;

public final class package$ implements IntInstances {
   public static final package$ MODULE$ = new package$();
   private static Order catsKernelStdOrderForInt;
   private static CommutativeGroup catsKernelStdGroupForInt;

   static {
      IntInstances.$init$(MODULE$);
   }

   public Order catsKernelStdOrderForInt() {
      return catsKernelStdOrderForInt;
   }

   public CommutativeGroup catsKernelStdGroupForInt() {
      return catsKernelStdGroupForInt;
   }

   public void cats$kernel$instances$IntInstances$_setter_$catsKernelStdOrderForInt_$eq(final Order x$1) {
      catsKernelStdOrderForInt = x$1;
   }

   public void cats$kernel$instances$IntInstances$_setter_$catsKernelStdGroupForInt_$eq(final CommutativeGroup x$1) {
      catsKernelStdGroupForInt = x$1;
   }

   private package$() {
   }
}
