package algebra.ring;

import scala.runtime.BoxesRunTime;

public interface DivisionRing$mcJ$sp extends DivisionRing, Semifield$mcJ$sp, Ring$mcJ$sp {
   // $FF: synthetic method
   static long fromDouble$(final DivisionRing$mcJ$sp $this, final double a) {
      return $this.fromDouble(a);
   }

   default long fromDouble(final double a) {
      return this.fromDouble$mcJ$sp(a);
   }

   // $FF: synthetic method
   static long fromDouble$mcJ$sp$(final DivisionRing$mcJ$sp $this, final double a) {
      return $this.fromDouble$mcJ$sp(a);
   }

   default long fromDouble$mcJ$sp(final double a) {
      return BoxesRunTime.unboxToLong(DivisionRing$.MODULE$.defaultFromDouble(a, this, this));
   }
}
