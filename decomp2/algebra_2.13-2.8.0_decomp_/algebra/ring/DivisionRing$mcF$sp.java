package algebra.ring;

import scala.runtime.BoxesRunTime;

public interface DivisionRing$mcF$sp extends DivisionRing, Semifield$mcF$sp, Ring$mcF$sp {
   // $FF: synthetic method
   static float fromDouble$(final DivisionRing$mcF$sp $this, final double a) {
      return $this.fromDouble(a);
   }

   default float fromDouble(final double a) {
      return this.fromDouble$mcF$sp(a);
   }

   // $FF: synthetic method
   static float fromDouble$mcF$sp$(final DivisionRing$mcF$sp $this, final double a) {
      return $this.fromDouble$mcF$sp(a);
   }

   default float fromDouble$mcF$sp(final double a) {
      return BoxesRunTime.unboxToFloat(DivisionRing$.MODULE$.defaultFromDouble(a, this, this));
   }
}
