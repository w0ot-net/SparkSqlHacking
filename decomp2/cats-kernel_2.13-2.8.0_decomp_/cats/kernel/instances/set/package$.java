package cats.kernel.instances.set;

import cats.kernel.BoundedSemilattice;
import cats.kernel.Hash;
import cats.kernel.PartialOrder;
import cats.kernel.instances.SetInstances;
import cats.kernel.instances.SetInstances1;

public final class package$ implements SetInstances {
   public static final package$ MODULE$ = new package$();

   static {
      SetInstances1.$init$(MODULE$);
      SetInstances.$init$(MODULE$);
   }

   public Hash catsKernelStdHashForSet() {
      return SetInstances.catsKernelStdHashForSet$(this);
   }

   public PartialOrder catsKernelStdPartialOrderForSet() {
      return SetInstances1.catsKernelStdPartialOrderForSet$(this);
   }

   public BoundedSemilattice catsKernelStdSemilatticeForSet() {
      return SetInstances1.catsKernelStdSemilatticeForSet$(this);
   }

   private package$() {
   }
}
