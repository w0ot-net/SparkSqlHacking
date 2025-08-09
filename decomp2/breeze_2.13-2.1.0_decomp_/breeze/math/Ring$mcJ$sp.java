package breeze.math;

import breeze.linalg.norm$;
import scala.runtime.BoxesRunTime;

public interface Ring$mcJ$sp extends Ring, Semiring$mcJ$sp {
   // $FF: synthetic method
   static long negate$(final Ring$mcJ$sp $this, final long s) {
      return $this.negate(s);
   }

   default long negate(final long s) {
      return this.negate$mcJ$sp(s);
   }

   // $FF: synthetic method
   static long negate$mcJ$sp$(final Ring$mcJ$sp $this, final long s) {
      return $this.negate$mcJ$sp(s);
   }

   default long negate$mcJ$sp(final long s) {
      return this.$minus$mcJ$sp(this.zero$mcJ$sp(), s);
   }

   // $FF: synthetic method
   static double sNorm$(final Ring$mcJ$sp $this, final long a) {
      return $this.sNorm(a);
   }

   default double sNorm(final long a) {
      return this.sNorm$mcJ$sp(a);
   }

   // $FF: synthetic method
   static double sNorm$mcJ$sp$(final Ring$mcJ$sp $this, final long a) {
      return $this.sNorm$mcJ$sp(a);
   }

   default double sNorm$mcJ$sp(final long a) {
      return BoxesRunTime.unboxToDouble(norm$.MODULE$.apply(BoxesRunTime.boxToLong(a), this.normImpl$mcJ$sp()));
   }
}
