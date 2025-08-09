package algebra.instances;

import cats.kernel.Eq;

public final class ArrayEq$mcB$sp extends ArrayEq {
   public final Eq evidence$4$mcB$sp;

   public boolean eqv(final byte[] x, final byte[] y) {
      return this.eqv$mcB$sp(x, y);
   }

   public boolean eqv$mcB$sp(final byte[] x, final byte[] y) {
      return ArraySupport$.MODULE$.eqv$mBc$sp(x, y, this.evidence$4$mcB$sp);
   }

   public ArrayEq$mcB$sp(final Eq evidence$4$mcB$sp) {
      super(evidence$4$mcB$sp);
      this.evidence$4$mcB$sp = evidence$4$mcB$sp;
   }
}
