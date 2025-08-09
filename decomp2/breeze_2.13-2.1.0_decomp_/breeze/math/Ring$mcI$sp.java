package breeze.math;

import breeze.linalg.norm$;

public interface Ring$mcI$sp extends Ring, Semiring$mcI$sp {
   // $FF: synthetic method
   static int negate$(final Ring$mcI$sp $this, final int s) {
      return $this.negate(s);
   }

   default int negate(final int s) {
      return this.negate$mcI$sp(s);
   }

   // $FF: synthetic method
   static int negate$mcI$sp$(final Ring$mcI$sp $this, final int s) {
      return $this.negate$mcI$sp(s);
   }

   default int negate$mcI$sp(final int s) {
      return this.$minus$mcI$sp(this.zero$mcI$sp(), s);
   }

   // $FF: synthetic method
   static double sNorm$(final Ring$mcI$sp $this, final int a) {
      return $this.sNorm(a);
   }

   default double sNorm(final int a) {
      return this.sNorm$mcI$sp(a);
   }

   // $FF: synthetic method
   static double sNorm$mcI$sp$(final Ring$mcI$sp $this, final int a) {
      return $this.sNorm$mcI$sp(a);
   }

   default double sNorm$mcI$sp(final int a) {
      return norm$.MODULE$.apply$mIDc$sp(a, this.normImpl$mcI$sp());
   }
}
