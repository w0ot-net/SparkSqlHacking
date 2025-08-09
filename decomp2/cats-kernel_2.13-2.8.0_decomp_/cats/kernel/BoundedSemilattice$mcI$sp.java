package cats.kernel;

public interface BoundedSemilattice$mcI$sp extends BoundedSemilattice, CommutativeMonoid$mcI$sp, Semilattice$mcI$sp {
   // $FF: synthetic method
   static int combineN$(final BoundedSemilattice$mcI$sp $this, final int a, final int n) {
      return $this.combineN(a, n);
   }

   default int combineN(final int a, final int n) {
      return this.combineN$mcI$sp(a, n);
   }

   // $FF: synthetic method
   static int combineN$mcI$sp$(final BoundedSemilattice$mcI$sp $this, final int a, final int n) {
      return $this.combineN$mcI$sp(a, n);
   }

   default int combineN$mcI$sp(final int a, final int n) {
      if (n < 0) {
         throw new IllegalArgumentException("Repeated combining for monoids must have n >= 0");
      } else {
         return n == 0 ? this.empty$mcI$sp() : a;
      }
   }
}
