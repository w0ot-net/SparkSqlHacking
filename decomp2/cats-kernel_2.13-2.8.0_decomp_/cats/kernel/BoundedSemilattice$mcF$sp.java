package cats.kernel;

public interface BoundedSemilattice$mcF$sp extends BoundedSemilattice, CommutativeMonoid$mcF$sp, Semilattice$mcF$sp {
   // $FF: synthetic method
   static float combineN$(final BoundedSemilattice$mcF$sp $this, final float a, final int n) {
      return $this.combineN(a, n);
   }

   default float combineN(final float a, final int n) {
      return this.combineN$mcF$sp(a, n);
   }

   // $FF: synthetic method
   static float combineN$mcF$sp$(final BoundedSemilattice$mcF$sp $this, final float a, final int n) {
      return $this.combineN$mcF$sp(a, n);
   }

   default float combineN$mcF$sp(final float a, final int n) {
      if (n < 0) {
         throw new IllegalArgumentException("Repeated combining for monoids must have n >= 0");
      } else {
         return n == 0 ? this.empty$mcF$sp() : a;
      }
   }
}
