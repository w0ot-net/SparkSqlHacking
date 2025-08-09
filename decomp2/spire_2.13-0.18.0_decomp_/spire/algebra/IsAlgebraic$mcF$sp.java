package spire.algebra;

import spire.math.Real;

public interface IsAlgebraic$mcF$sp extends IsAlgebraic, IsReal$mcF$sp {
   // $FF: synthetic method
   static Real toReal$(final IsAlgebraic$mcF$sp $this, final float a) {
      return $this.toReal(a);
   }

   default Real toReal(final float a) {
      return this.toReal$mcF$sp(a);
   }

   // $FF: synthetic method
   static Real toReal$mcF$sp$(final IsAlgebraic$mcF$sp $this, final float a) {
      return $this.toReal$mcF$sp(a);
   }

   default Real toReal$mcF$sp(final float a) {
      return this.toAlgebraic$mcF$sp(a).toReal();
   }
}
