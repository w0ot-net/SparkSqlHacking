package cats.kernel;

public interface BoundedSemilattice$mcD$sp extends BoundedSemilattice, CommutativeMonoid$mcD$sp, Semilattice$mcD$sp {
   // $FF: synthetic method
   static double combineN$(final BoundedSemilattice$mcD$sp $this, final double a, final int n) {
      return $this.combineN(a, n);
   }

   default double combineN(final double a, final int n) {
      return this.combineN$mcD$sp(a, n);
   }

   // $FF: synthetic method
   static double combineN$mcD$sp$(final BoundedSemilattice$mcD$sp $this, final double a, final int n) {
      return $this.combineN$mcD$sp(a, n);
   }

   default double combineN$mcD$sp(final double a, final int n) {
      if (n < 0) {
         throw new IllegalArgumentException("Repeated combining for monoids must have n >= 0");
      } else {
         return n == 0 ? this.empty$mcD$sp() : a;
      }
   }
}
