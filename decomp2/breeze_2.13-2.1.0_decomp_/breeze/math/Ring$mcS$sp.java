package breeze.math;

import breeze.linalg.norm$;
import scala.runtime.BoxesRunTime;

public interface Ring$mcS$sp extends Ring, Semiring$mcS$sp {
   // $FF: synthetic method
   static short negate$(final Ring$mcS$sp $this, final short s) {
      return $this.negate(s);
   }

   default short negate(final short s) {
      return this.negate$mcS$sp(s);
   }

   // $FF: synthetic method
   static short negate$mcS$sp$(final Ring$mcS$sp $this, final short s) {
      return $this.negate$mcS$sp(s);
   }

   default short negate$mcS$sp(final short s) {
      return this.$minus$mcS$sp(this.zero$mcS$sp(), s);
   }

   // $FF: synthetic method
   static double sNorm$(final Ring$mcS$sp $this, final short a) {
      return $this.sNorm(a);
   }

   default double sNorm(final short a) {
      return this.sNorm$mcS$sp(a);
   }

   // $FF: synthetic method
   static double sNorm$mcS$sp$(final Ring$mcS$sp $this, final short a) {
      return $this.sNorm$mcS$sp(a);
   }

   default double sNorm$mcS$sp(final short a) {
      return BoxesRunTime.unboxToDouble(norm$.MODULE$.apply(BoxesRunTime.boxToShort(a), this.normImpl$mcS$sp()));
   }
}
