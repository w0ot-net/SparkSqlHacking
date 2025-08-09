package algebra.ring;

import scala.runtime.BoxesRunTime;

public interface DivisionRing$mcD$sp extends DivisionRing, Semifield$mcD$sp, Ring$mcD$sp {
   // $FF: synthetic method
   static double fromDouble$(final DivisionRing$mcD$sp $this, final double a) {
      return $this.fromDouble(a);
   }

   default double fromDouble(final double a) {
      return this.fromDouble$mcD$sp(a);
   }

   // $FF: synthetic method
   static double fromDouble$mcD$sp$(final DivisionRing$mcD$sp $this, final double a) {
      return $this.fromDouble$mcD$sp(a);
   }

   default double fromDouble$mcD$sp(final double a) {
      return BoxesRunTime.unboxToDouble(DivisionRing$.MODULE$.defaultFromDouble(a, this, this));
   }
}
