package algebra.ring;

import scala.runtime.BoxesRunTime;

public interface DivisionRing$mcI$sp extends DivisionRing, Semifield$mcI$sp, Ring$mcI$sp {
   // $FF: synthetic method
   static int fromDouble$(final DivisionRing$mcI$sp $this, final double a) {
      return $this.fromDouble(a);
   }

   default int fromDouble(final double a) {
      return this.fromDouble$mcI$sp(a);
   }

   // $FF: synthetic method
   static int fromDouble$mcI$sp$(final DivisionRing$mcI$sp $this, final double a) {
      return $this.fromDouble$mcI$sp(a);
   }

   default int fromDouble$mcI$sp(final double a) {
      return BoxesRunTime.unboxToInt(DivisionRing$.MODULE$.defaultFromDouble(a, this, this));
   }
}
