package spire.algebra;

import spire.math.Real;

public interface IsAlgebraic$mcB$sp extends IsAlgebraic, IsReal$mcB$sp {
   // $FF: synthetic method
   static Real toReal$(final IsAlgebraic$mcB$sp $this, final byte a) {
      return $this.toReal(a);
   }

   default Real toReal(final byte a) {
      return this.toReal$mcB$sp(a);
   }

   // $FF: synthetic method
   static Real toReal$mcB$sp$(final IsAlgebraic$mcB$sp $this, final byte a) {
      return $this.toReal$mcB$sp(a);
   }

   default Real toReal$mcB$sp(final byte a) {
      return this.toAlgebraic$mcB$sp(a).toReal();
   }
}
