package spire.algebra;

public interface NRoot$mcD$sp extends NRoot {
   // $FF: synthetic method
   static double sqrt$(final NRoot$mcD$sp $this, final double a) {
      return $this.sqrt(a);
   }

   default double sqrt(final double a) {
      return this.sqrt$mcD$sp(a);
   }

   // $FF: synthetic method
   static double sqrt$mcD$sp$(final NRoot$mcD$sp $this, final double a) {
      return $this.sqrt$mcD$sp(a);
   }

   default double sqrt$mcD$sp(final double a) {
      return this.nroot$mcD$sp(a, 2);
   }
}
