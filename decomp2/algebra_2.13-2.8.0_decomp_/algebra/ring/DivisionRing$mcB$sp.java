package algebra.ring;

import scala.runtime.BoxesRunTime;

public interface DivisionRing$mcB$sp extends DivisionRing {
   // $FF: synthetic method
   static byte fromDouble$(final DivisionRing$mcB$sp $this, final double a) {
      return $this.fromDouble(a);
   }

   default byte fromDouble(final double a) {
      return this.fromDouble$mcB$sp(a);
   }

   // $FF: synthetic method
   static byte fromDouble$mcB$sp$(final DivisionRing$mcB$sp $this, final double a) {
      return $this.fromDouble$mcB$sp(a);
   }

   default byte fromDouble$mcB$sp(final double a) {
      return BoxesRunTime.unboxToByte(DivisionRing$.MODULE$.defaultFromDouble(a, this, this));
   }
}
