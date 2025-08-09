package algebra.instances.unit;

import algebra.instances.UnitInstances;
import algebra.ring.CommutativeRing;
import cats.kernel.BoundedSemilattice;
import cats.kernel.Order;

public final class package$ implements UnitInstances {
   public static final package$ MODULE$ = new package$();
   private static CommutativeRing unitRing;
   private static Order catsKernelStdOrderForUnit;
   private static BoundedSemilattice catsKernelStdAlgebraForUnit;

   static {
      cats.kernel.instances.UnitInstances.$init$(MODULE$);
      UnitInstances.$init$(MODULE$);
   }

   public CommutativeRing unitRing() {
      return unitRing;
   }

   public void algebra$instances$UnitInstances$_setter_$unitRing_$eq(final CommutativeRing x$1) {
      unitRing = x$1;
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
