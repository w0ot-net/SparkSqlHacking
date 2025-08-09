package algebra.instances.short;

import algebra.instances.ShortAlgebra;
import algebra.instances.ShortInstances;
import algebra.lattice.BoundedDistributiveLattice;
import cats.kernel.CommutativeGroup;
import cats.kernel.Order;

public final class package$ implements ShortInstances {
   public static final package$ MODULE$ = new package$();
   private static ShortAlgebra shortAlgebra;
   private static BoundedDistributiveLattice ShortMinMaxLattice;
   private static Order catsKernelStdOrderForShort;
   private static CommutativeGroup catsKernelStdGroupForShort;

   static {
      cats.kernel.instances.ShortInstances.$init$(MODULE$);
      ShortInstances.$init$(MODULE$);
   }

   public ShortAlgebra shortAlgebra() {
      return shortAlgebra;
   }

   public BoundedDistributiveLattice ShortMinMaxLattice() {
      return ShortMinMaxLattice;
   }

   public void algebra$instances$ShortInstances$_setter_$shortAlgebra_$eq(final ShortAlgebra x$1) {
      shortAlgebra = x$1;
   }

   public void algebra$instances$ShortInstances$_setter_$ShortMinMaxLattice_$eq(final BoundedDistributiveLattice x$1) {
      ShortMinMaxLattice = x$1;
   }

   public Order catsKernelStdOrderForShort() {
      return catsKernelStdOrderForShort;
   }

   public CommutativeGroup catsKernelStdGroupForShort() {
      return catsKernelStdGroupForShort;
   }

   public void cats$kernel$instances$ShortInstances$_setter_$catsKernelStdOrderForShort_$eq(final Order x$1) {
      catsKernelStdOrderForShort = x$1;
   }

   public void cats$kernel$instances$ShortInstances$_setter_$catsKernelStdGroupForShort_$eq(final CommutativeGroup x$1) {
      catsKernelStdGroupForShort = x$1;
   }

   private package$() {
   }
}
