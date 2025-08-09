package algebra.instances;

import cats.kernel.Eq;

public final class ArrayEq$mcZ$sp extends ArrayEq {
   public final Eq evidence$4$mcZ$sp;

   public boolean eqv(final boolean[] x, final boolean[] y) {
      return this.eqv$mcZ$sp(x, y);
   }

   public boolean eqv$mcZ$sp(final boolean[] x, final boolean[] y) {
      return ArraySupport$.MODULE$.eqv$mZc$sp(x, y, this.evidence$4$mcZ$sp);
   }

   public ArrayEq$mcZ$sp(final Eq evidence$4$mcZ$sp) {
      super(evidence$4$mcZ$sp);
      this.evidence$4$mcZ$sp = evidence$4$mcZ$sp;
   }
}
