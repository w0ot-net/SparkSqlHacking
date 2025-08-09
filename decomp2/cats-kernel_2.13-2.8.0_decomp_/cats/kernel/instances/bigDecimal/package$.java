package cats.kernel.instances.bigDecimal;

import cats.kernel.CommutativeGroup;
import cats.kernel.Order;
import cats.kernel.instances.BigDecimalInstances;

public final class package$ implements BigDecimalInstances {
   public static final package$ MODULE$ = new package$();
   private static Order catsKernelStdOrderForBigDecimal;
   private static CommutativeGroup catsKernelStdGroupForBigDecimal;

   static {
      BigDecimalInstances.$init$(MODULE$);
   }

   public Order catsKernelStdOrderForBigDecimal() {
      return catsKernelStdOrderForBigDecimal;
   }

   public CommutativeGroup catsKernelStdGroupForBigDecimal() {
      return catsKernelStdGroupForBigDecimal;
   }

   public void cats$kernel$instances$BigDecimalInstances$_setter_$catsKernelStdOrderForBigDecimal_$eq(final Order x$1) {
      catsKernelStdOrderForBigDecimal = x$1;
   }

   public void cats$kernel$instances$BigDecimalInstances$_setter_$catsKernelStdGroupForBigDecimal_$eq(final CommutativeGroup x$1) {
      catsKernelStdGroupForBigDecimal = x$1;
   }

   private package$() {
   }
}
