package cats.kernel.instances.bigInt;

import cats.kernel.CommutativeGroup;
import cats.kernel.Order;
import cats.kernel.instances.BigIntInstances;

public final class package$ implements BigIntInstances {
   public static final package$ MODULE$ = new package$();
   private static Order catsKernelStdOrderForBigInt;
   private static CommutativeGroup catsKernelStdGroupForBigInt;

   static {
      BigIntInstances.$init$(MODULE$);
   }

   public Order catsKernelStdOrderForBigInt() {
      return catsKernelStdOrderForBigInt;
   }

   public CommutativeGroup catsKernelStdGroupForBigInt() {
      return catsKernelStdGroupForBigInt;
   }

   public void cats$kernel$instances$BigIntInstances$_setter_$catsKernelStdOrderForBigInt_$eq(final Order x$1) {
      catsKernelStdOrderForBigInt = x$1;
   }

   public void cats$kernel$instances$BigIntInstances$_setter_$catsKernelStdGroupForBigInt_$eq(final CommutativeGroup x$1) {
      catsKernelStdGroupForBigInt = x$1;
   }

   private package$() {
   }
}
