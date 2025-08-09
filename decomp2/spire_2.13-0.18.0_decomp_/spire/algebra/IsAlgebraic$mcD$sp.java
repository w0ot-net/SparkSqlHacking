package spire.algebra;

import spire.math.Real;

public interface IsAlgebraic$mcD$sp extends IsAlgebraic, IsReal$mcD$sp {
   // $FF: synthetic method
   static Real toReal$(final IsAlgebraic$mcD$sp $this, final double a) {
      return $this.toReal(a);
   }

   default Real toReal(final double a) {
      return this.toReal$mcD$sp(a);
   }

   // $FF: synthetic method
   static Real toReal$mcD$sp$(final IsAlgebraic$mcD$sp $this, final double a) {
      return $this.toReal$mcD$sp(a);
   }

   default Real toReal$mcD$sp(final double a) {
      return this.toAlgebraic$mcD$sp(a).toReal();
   }
}
