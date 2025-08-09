package spire.algebra;

import spire.math.Real;

public interface IsAlgebraic$mcS$sp extends IsAlgebraic, IsReal$mcS$sp {
   // $FF: synthetic method
   static Real toReal$(final IsAlgebraic$mcS$sp $this, final short a) {
      return $this.toReal(a);
   }

   default Real toReal(final short a) {
      return this.toReal$mcS$sp(a);
   }

   // $FF: synthetic method
   static Real toReal$mcS$sp$(final IsAlgebraic$mcS$sp $this, final short a) {
      return $this.toReal$mcS$sp(a);
   }

   default Real toReal$mcS$sp(final short a) {
      return this.toAlgebraic$mcS$sp(a).toReal();
   }
}
