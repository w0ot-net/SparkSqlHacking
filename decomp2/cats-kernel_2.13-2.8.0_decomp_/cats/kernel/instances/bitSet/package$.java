package cats.kernel.instances.bitSet;

import cats.kernel.BoundedSemilattice;
import cats.kernel.PartialOrder;
import cats.kernel.instances.BitSetInstances;

public final class package$ implements BitSetInstances {
   public static final package$ MODULE$ = new package$();
   private static PartialOrder catsKernelStdOrderForBitSet;
   private static BoundedSemilattice catsKernelStdSemilatticeForBitSet;

   static {
      BitSetInstances.$init$(MODULE$);
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
