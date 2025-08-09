package cats.kernel;

public interface BoundedSemilattice$mcJ$sp extends BoundedSemilattice, CommutativeMonoid$mcJ$sp, Semilattice$mcJ$sp {
   // $FF: synthetic method
   static long combineN$(final BoundedSemilattice$mcJ$sp $this, final long a, final int n) {
      return $this.combineN(a, n);
   }

   default long combineN(final long a, final int n) {
      return this.combineN$mcJ$sp(a, n);
   }

   // $FF: synthetic method
   static long combineN$mcJ$sp$(final BoundedSemilattice$mcJ$sp $this, final long a, final int n) {
      return $this.combineN$mcJ$sp(a, n);
   }

   default long combineN$mcJ$sp(final long a, final int n) {
      if (n < 0) {
         throw new IllegalArgumentException("Repeated combining for monoids must have n >= 0");
      } else {
         return n == 0 ? this.empty$mcJ$sp() : a;
      }
   }
}
