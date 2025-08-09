package algebra.instances.bigDecimal;

import algebra.instances.BigDecimalAlgebra;
import algebra.instances.BigDecimalInstances;
import cats.kernel.CommutativeGroup;
import cats.kernel.Order;

public final class package$ implements BigDecimalInstances {
   public static final package$ MODULE$ = new package$();
   private static BigDecimalAlgebra bigDecimalAlgebra;
   private static Order catsKernelStdOrderForBigDecimal;
   private static CommutativeGroup catsKernelStdGroupForBigDecimal;

   static {
      cats.kernel.instances.BigDecimalInstances.$init$(MODULE$);
      BigDecimalInstances.$init$(MODULE$);
   }

   public BigDecimalAlgebra bigDecimalAlgebra() {
      return bigDecimalAlgebra;
   }

   public void algebra$instances$BigDecimalInstances$_setter_$bigDecimalAlgebra_$eq(final BigDecimalAlgebra x$1) {
      bigDecimalAlgebra = x$1;
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
