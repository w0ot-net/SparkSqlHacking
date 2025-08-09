package algebra.instances;

import cats.kernel.Eq;

public final class ArrayEq$mcS$sp extends ArrayEq {
   public final Eq evidence$4$mcS$sp;

   public boolean eqv(final short[] x, final short[] y) {
      return this.eqv$mcS$sp(x, y);
   }

   public boolean eqv$mcS$sp(final short[] x, final short[] y) {
      return ArraySupport$.MODULE$.eqv$mSc$sp(x, y, this.evidence$4$mcS$sp);
   }

   public ArrayEq$mcS$sp(final Eq evidence$4$mcS$sp) {
      super(evidence$4$mcS$sp);
      this.evidence$4$mcS$sp = evidence$4$mcS$sp;
   }
}
