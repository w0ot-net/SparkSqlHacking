package breeze.math;

import breeze.linalg.norm$;

public interface Ring$mcD$sp extends Ring, Semiring$mcD$sp {
   // $FF: synthetic method
   static double negate$(final Ring$mcD$sp $this, final double s) {
      return $this.negate(s);
   }

   default double negate(final double s) {
      return this.negate$mcD$sp(s);
   }

   // $FF: synthetic method
   static double negate$mcD$sp$(final Ring$mcD$sp $this, final double s) {
      return $this.negate$mcD$sp(s);
   }

   default double negate$mcD$sp(final double s) {
      return this.$minus$mcD$sp(this.zero$mcD$sp(), s);
   }

   // $FF: synthetic method
   static double sNorm$(final Ring$mcD$sp $this, final double a) {
      return $this.sNorm(a);
   }

   default double sNorm(final double a) {
      return this.sNorm$mcD$sp(a);
   }

   // $FF: synthetic method
   static double sNorm$mcD$sp$(final Ring$mcD$sp $this, final double a) {
      return $this.sNorm$mcD$sp(a);
   }

   default double sNorm$mcD$sp(final double a) {
      return norm$.MODULE$.apply$mDDc$sp(a, this.normImpl$mcD$sp());
   }
}
