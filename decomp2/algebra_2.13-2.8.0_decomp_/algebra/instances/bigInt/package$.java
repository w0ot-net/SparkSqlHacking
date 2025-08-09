package algebra.instances.bigInt;

import algebra.instances.BigIntAlgebra;
import algebra.instances.BigIntInstances;
import algebra.ring.TruncatedDivision;
import cats.kernel.CommutativeGroup;
import cats.kernel.Order;

public final class package$ implements BigIntInstances {
   public static final package$ MODULE$ = new package$();
   private static BigIntAlgebra bigIntAlgebra;
   private static Order catsKernelStdOrderForBigInt;
   private static CommutativeGroup catsKernelStdGroupForBigInt;

   static {
      cats.kernel.instances.BigIntInstances.$init$(MODULE$);
      BigIntInstances.$init$(MODULE$);
   }

   public TruncatedDivision bigIntTruncatedDivision() {
      return BigIntInstances.bigIntTruncatedDivision$(this);
   }

   public BigIntAlgebra bigIntAlgebra() {
      return bigIntAlgebra;
   }

   public void algebra$instances$BigIntInstances$_setter_$bigIntAlgebra_$eq(final BigIntAlgebra x$1) {
      bigIntAlgebra = x$1;
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
