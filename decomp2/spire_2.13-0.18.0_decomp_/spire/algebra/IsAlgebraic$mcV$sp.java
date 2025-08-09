package spire.algebra;

import scala.runtime.BoxedUnit;
import spire.math.Real;

public interface IsAlgebraic$mcV$sp extends IsAlgebraic, IsReal$mcV$sp {
   // $FF: synthetic method
   static Real toReal$(final IsAlgebraic$mcV$sp $this, final BoxedUnit a) {
      return $this.toReal(a);
   }

   default Real toReal(final BoxedUnit a) {
      return this.toReal$mcV$sp(a);
   }

   // $FF: synthetic method
   static Real toReal$mcV$sp$(final IsAlgebraic$mcV$sp $this, final BoxedUnit a) {
      return $this.toReal$mcV$sp(a);
   }

   default Real toReal$mcV$sp(final BoxedUnit a) {
      return this.toAlgebraic$mcV$sp(a).toReal();
   }
}
