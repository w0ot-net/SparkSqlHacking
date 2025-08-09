package algebra.instances.set;

import algebra.instances.SetInstances;
import algebra.lattice.GenBool;
import algebra.ring.BoolRng;
import algebra.ring.Semiring;
import cats.kernel.BoundedSemilattice;
import cats.kernel.Hash;
import cats.kernel.PartialOrder;
import cats.kernel.instances.SetInstances1;

public final class package$ implements SetInstances {
   public static final package$ MODULE$ = new package$();

   static {
      SetInstances1.$init$(MODULE$);
      cats.kernel.instances.SetInstances.$init$(MODULE$);
      SetInstances.$init$(MODULE$);
   }

   public GenBool setLattice() {
      return SetInstances.setLattice$(this);
   }

   public Semiring setSemiring() {
      return SetInstances.setSemiring$(this);
   }

   public BoolRng setBoolRng() {
      return SetInstances.setBoolRng$(this);
   }

   public Hash catsKernelStdHashForSet() {
      return cats.kernel.instances.SetInstances.catsKernelStdHashForSet$(this);
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
