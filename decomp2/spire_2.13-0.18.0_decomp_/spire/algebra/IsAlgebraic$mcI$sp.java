package spire.algebra;

import spire.math.Real;

public interface IsAlgebraic$mcI$sp extends IsAlgebraic, IsReal$mcI$sp {
   // $FF: synthetic method
   static Real toReal$(final IsAlgebraic$mcI$sp $this, final int a) {
      return $this.toReal(a);
   }

   default Real toReal(final int a) {
      return this.toReal$mcI$sp(a);
   }

   // $FF: synthetic method
   static Real toReal$mcI$sp$(final IsAlgebraic$mcI$sp $this, final int a) {
      return $this.toReal$mcI$sp(a);
   }

   default Real toReal$mcI$sp(final int a) {
      return this.toAlgebraic$mcI$sp(a).toReal();
   }
}
