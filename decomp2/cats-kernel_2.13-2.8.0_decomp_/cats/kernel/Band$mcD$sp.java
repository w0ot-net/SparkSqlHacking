package cats.kernel;

public interface Band$mcD$sp extends Band, Semigroup$mcD$sp {
   // $FF: synthetic method
   static double repeatedCombineN$(final Band$mcD$sp $this, final double a, final int n) {
      return $this.repeatedCombineN(a, n);
   }

   default double repeatedCombineN(final double a, final int n) {
      return this.repeatedCombineN$mcD$sp(a, n);
   }

   // $FF: synthetic method
   static double repeatedCombineN$mcD$sp$(final Band$mcD$sp $this, final double a, final int n) {
      return $this.repeatedCombineN$mcD$sp(a, n);
   }

   default double repeatedCombineN$mcD$sp(final double a, final int n) {
      return a;
   }
}
