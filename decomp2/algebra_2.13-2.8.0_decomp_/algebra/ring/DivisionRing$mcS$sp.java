package algebra.ring;

import scala.runtime.BoxesRunTime;

public interface DivisionRing$mcS$sp extends DivisionRing {
   // $FF: synthetic method
   static short fromDouble$(final DivisionRing$mcS$sp $this, final double a) {
      return $this.fromDouble(a);
   }

   default short fromDouble(final double a) {
      return this.fromDouble$mcS$sp(a);
   }

   // $FF: synthetic method
   static short fromDouble$mcS$sp$(final DivisionRing$mcS$sp $this, final double a) {
      return $this.fromDouble$mcS$sp(a);
   }

   default short fromDouble$mcS$sp(final double a) {
      return BoxesRunTime.unboxToShort(DivisionRing$.MODULE$.defaultFromDouble(a, this, this));
   }
}
