package algebra.instances.byte;

import algebra.instances.ByteAlgebra;
import algebra.instances.ByteInstances;
import algebra.lattice.BoundedDistributiveLattice;
import cats.kernel.CommutativeGroup;
import cats.kernel.Order;

public final class package$ implements ByteInstances {
   public static final package$ MODULE$ = new package$();
   private static ByteAlgebra byteAlgebra;
   private static BoundedDistributiveLattice ByteMinMaxLattice;
   private static Order catsKernelStdOrderForByte;
   private static CommutativeGroup catsKernelStdGroupForByte;

   static {
      cats.kernel.instances.ByteInstances.$init$(MODULE$);
      ByteInstances.$init$(MODULE$);
   }

   public ByteAlgebra byteAlgebra() {
      return byteAlgebra;
   }

   public BoundedDistributiveLattice ByteMinMaxLattice() {
      return ByteMinMaxLattice;
   }

   public void algebra$instances$ByteInstances$_setter_$byteAlgebra_$eq(final ByteAlgebra x$1) {
      byteAlgebra = x$1;
   }

   public void algebra$instances$ByteInstances$_setter_$ByteMinMaxLattice_$eq(final BoundedDistributiveLattice x$1) {
      ByteMinMaxLattice = x$1;
   }

   public Order catsKernelStdOrderForByte() {
      return catsKernelStdOrderForByte;
   }

   public CommutativeGroup catsKernelStdGroupForByte() {
      return catsKernelStdGroupForByte;
   }

   public void cats$kernel$instances$ByteInstances$_setter_$catsKernelStdOrderForByte_$eq(final Order x$1) {
      catsKernelStdOrderForByte = x$1;
   }

   public void cats$kernel$instances$ByteInstances$_setter_$catsKernelStdGroupForByte_$eq(final CommutativeGroup x$1) {
      catsKernelStdGroupForByte = x$1;
   }

   private package$() {
   }
}
