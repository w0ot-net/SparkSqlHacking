package spire.algebra;

import spire.math.Real;

public interface IsAlgebraic$mcC$sp extends IsAlgebraic, IsReal$mcC$sp {
   // $FF: synthetic method
   static Real toReal$(final IsAlgebraic$mcC$sp $this, final char a) {
      return $this.toReal(a);
   }

   default Real toReal(final char a) {
      return this.toReal$mcC$sp(a);
   }

   // $FF: synthetic method
   static Real toReal$mcC$sp$(final IsAlgebraic$mcC$sp $this, final char a) {
      return $this.toReal$mcC$sp(a);
   }

   default Real toReal$mcC$sp(final char a) {
      return this.toAlgebraic$mcC$sp(a).toReal();
   }
}
