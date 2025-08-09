package algebra.instances.long;

import algebra.instances.LongAlgebra;
import algebra.instances.LongInstances;
import algebra.lattice.BoundedDistributiveLattice;
import cats.kernel.CommutativeGroup;
import cats.kernel.Order;

public final class package$ implements LongInstances {
   public static final package$ MODULE$ = new package$();
   private static LongAlgebra longAlgebra;
   private static BoundedDistributiveLattice LongMinMaxLattice;
   private static Order catsKernelStdOrderForLong;
   private static CommutativeGroup catsKernelStdGroupForLong;

   static {
      cats.kernel.instances.LongInstances.$init$(MODULE$);
      LongInstances.$init$(MODULE$);
   }

   public LongAlgebra longAlgebra() {
      return longAlgebra;
   }

   public BoundedDistributiveLattice LongMinMaxLattice() {
      return LongMinMaxLattice;
   }

   public void algebra$instances$LongInstances$_setter_$longAlgebra_$eq(final LongAlgebra x$1) {
      longAlgebra = x$1;
   }

   public void algebra$instances$LongInstances$_setter_$LongMinMaxLattice_$eq(final BoundedDistributiveLattice x$1) {
      LongMinMaxLattice = x$1;
   }

   public Order catsKernelStdOrderForLong() {
      return catsKernelStdOrderForLong;
   }

   public CommutativeGroup catsKernelStdGroupForLong() {
      return catsKernelStdGroupForLong;
   }

   public void cats$kernel$instances$LongInstances$_setter_$catsKernelStdOrderForLong_$eq(final Order x$1) {
      catsKernelStdOrderForLong = x$1;
   }

   public void cats$kernel$instances$LongInstances$_setter_$catsKernelStdGroupForLong_$eq(final CommutativeGroup x$1) {
      catsKernelStdGroupForLong = x$1;
   }

   private package$() {
   }
}
