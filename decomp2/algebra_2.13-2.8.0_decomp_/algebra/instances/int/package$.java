package algebra.instances.int;

import algebra.instances.IntAlgebra;
import algebra.instances.IntInstances;
import algebra.lattice.BoundedDistributiveLattice;
import cats.kernel.CommutativeGroup;
import cats.kernel.Order;

public final class package$ implements IntInstances {
   public static final package$ MODULE$ = new package$();
   private static IntAlgebra intAlgebra;
   private static BoundedDistributiveLattice IntMinMaxLattice;
   private static Order catsKernelStdOrderForInt;
   private static CommutativeGroup catsKernelStdGroupForInt;

   static {
      cats.kernel.instances.IntInstances.$init$(MODULE$);
      IntInstances.$init$(MODULE$);
   }

   public IntAlgebra intAlgebra() {
      return intAlgebra;
   }

   public BoundedDistributiveLattice IntMinMaxLattice() {
      return IntMinMaxLattice;
   }

   public void algebra$instances$IntInstances$_setter_$intAlgebra_$eq(final IntAlgebra x$1) {
      intAlgebra = x$1;
   }

   public void algebra$instances$IntInstances$_setter_$IntMinMaxLattice_$eq(final BoundedDistributiveLattice x$1) {
      IntMinMaxLattice = x$1;
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
