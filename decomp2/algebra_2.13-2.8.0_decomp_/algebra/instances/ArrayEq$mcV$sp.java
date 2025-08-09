package algebra.instances;

import cats.kernel.Eq;
import scala.runtime.BoxedUnit;

public final class ArrayEq$mcV$sp extends ArrayEq {
   public final Eq evidence$4$mcV$sp;

   public boolean eqv(final BoxedUnit[] x, final BoxedUnit[] y) {
      return this.eqv$mcV$sp(x, y);
   }

   public boolean eqv$mcV$sp(final BoxedUnit[] x, final BoxedUnit[] y) {
      return ArraySupport$.MODULE$.eqv$mVc$sp(x, y, this.evidence$4$mcV$sp);
   }

   public ArrayEq$mcV$sp(final Eq evidence$4$mcV$sp) {
      super(evidence$4$mcV$sp);
      this.evidence$4$mcV$sp = evidence$4$mcV$sp;
   }
}
