package spire.algebra;

import spire.math.Real;

public interface IsAlgebraic$mcJ$sp extends IsAlgebraic, IsReal$mcJ$sp {
   // $FF: synthetic method
   static Real toReal$(final IsAlgebraic$mcJ$sp $this, final long a) {
      return $this.toReal(a);
   }

   default Real toReal(final long a) {
      return this.toReal$mcJ$sp(a);
   }

   // $FF: synthetic method
   static Real toReal$mcJ$sp$(final IsAlgebraic$mcJ$sp $this, final long a) {
      return $this.toReal$mcJ$sp(a);
   }

   default Real toReal$mcJ$sp(final long a) {
      return this.toAlgebraic$mcJ$sp(a).toReal();
   }
}
