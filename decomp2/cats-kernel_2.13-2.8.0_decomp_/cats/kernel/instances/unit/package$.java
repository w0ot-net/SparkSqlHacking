package cats.kernel.instances.unit;

import cats.kernel.BoundedSemilattice;
import cats.kernel.Order;
import cats.kernel.instances.UnitInstances;

public final class package$ implements UnitInstances {
   public static final package$ MODULE$ = new package$();
   private static Order catsKernelStdOrderForUnit;
   private static BoundedSemilattice catsKernelStdAlgebraForUnit;

   static {
      UnitInstances.$init$(MODULE$);
   }

   public Order catsKernelStdOrderForUnit() {
      return catsKernelStdOrderForUnit;
   }

   public BoundedSemilattice catsKernelStdAlgebraForUnit() {
      return catsKernelStdAlgebraForUnit;
   }

   public void cats$kernel$instances$UnitInstances$_setter_$catsKernelStdOrderForUnit_$eq(final Order x$1) {
      catsKernelStdOrderForUnit = x$1;
   }

   public void cats$kernel$instances$UnitInstances$_setter_$catsKernelStdAlgebraForUnit_$eq(final BoundedSemilattice x$1) {
      catsKernelStdAlgebraForUnit = x$1;
   }

   private package$() {
   }
}
