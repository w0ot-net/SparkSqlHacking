package spire.algebra;

import spire.math.Real;

public interface IsAlgebraic$mcZ$sp extends IsAlgebraic, IsReal$mcZ$sp {
   // $FF: synthetic method
   static Real toReal$(final IsAlgebraic$mcZ$sp $this, final boolean a) {
      return $this.toReal(a);
   }

   default Real toReal(final boolean a) {
      return this.toReal$mcZ$sp(a);
   }

   // $FF: synthetic method
   static Real toReal$mcZ$sp$(final IsAlgebraic$mcZ$sp $this, final boolean a) {
      return $this.toReal$mcZ$sp(a);
   }

   default Real toReal$mcZ$sp(final boolean a) {
      return this.toAlgebraic$mcZ$sp(a).toReal();
   }
}
