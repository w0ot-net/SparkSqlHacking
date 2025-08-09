package algebra.instances.bitSet;

import algebra.instances.BitSetAlgebra;
import algebra.instances.BitSetInstances;
import cats.kernel.BoundedSemilattice;
import cats.kernel.PartialOrder;

public final class package$ implements BitSetInstances {
   public static final package$ MODULE$ = new package$();
   private static BitSetAlgebra bitSetAlgebra;
   private static PartialOrder catsKernelStdOrderForBitSet;
   private static BoundedSemilattice catsKernelStdSemilatticeForBitSet;

   static {
      cats.kernel.instances.BitSetInstances.$init$(MODULE$);
      BitSetInstances.$init$(MODULE$);
   }

   public BitSetAlgebra bitSetAlgebra() {
      return bitSetAlgebra;
   }

   public void algebra$instances$BitSetInstances$_setter_$bitSetAlgebra_$eq(final BitSetAlgebra x$1) {
      bitSetAlgebra = x$1;
   }

   public PartialOrder catsKernelStdOrderForBitSet() {
      return catsKernelStdOrderForBitSet;
   }

   public BoundedSemilattice catsKernelStdSemilatticeForBitSet() {
      return catsKernelStdSemilatticeForBitSet;
   }

   public void cats$kernel$instances$BitSetInstances$_setter_$catsKernelStdOrderForBitSet_$eq(final PartialOrder x$1) {
      catsKernelStdOrderForBitSet = x$1;
   }

   public void cats$kernel$instances$BitSetInstances$_setter_$catsKernelStdSemilatticeForBitSet_$eq(final BoundedSemilattice x$1) {
      catsKernelStdSemilatticeForBitSet = x$1;
   }

   private package$() {
   }
}
