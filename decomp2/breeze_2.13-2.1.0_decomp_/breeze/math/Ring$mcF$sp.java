package breeze.math;

import breeze.linalg.norm$;

public interface Ring$mcF$sp extends Ring, Semiring$mcF$sp {
   // $FF: synthetic method
   static float negate$(final Ring$mcF$sp $this, final float s) {
      return $this.negate(s);
   }

   default float negate(final float s) {
      return this.negate$mcF$sp(s);
   }

   // $FF: synthetic method
   static float negate$mcF$sp$(final Ring$mcF$sp $this, final float s) {
      return $this.negate$mcF$sp(s);
   }

   default float negate$mcF$sp(final float s) {
      return this.$minus$mcF$sp(this.zero$mcF$sp(), s);
   }

   // $FF: synthetic method
   static double sNorm$(final Ring$mcF$sp $this, final float a) {
      return $this.sNorm(a);
   }

   default double sNorm(final float a) {
      return this.sNorm$mcF$sp(a);
   }

   // $FF: synthetic method
   static double sNorm$mcF$sp$(final Ring$mcF$sp $this, final float a) {
      return $this.sNorm$mcF$sp(a);
   }

   default double sNorm$mcF$sp(final float a) {
      return norm$.MODULE$.apply$mFDc$sp(a, this.normImpl$mcF$sp());
   }
}
