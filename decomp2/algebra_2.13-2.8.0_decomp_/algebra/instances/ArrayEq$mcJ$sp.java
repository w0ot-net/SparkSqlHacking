package algebra.instances;

import cats.kernel.Eq;

public final class ArrayEq$mcJ$sp extends ArrayEq {
   public final Eq evidence$4$mcJ$sp;

   public boolean eqv(final long[] x, final long[] y) {
      return this.eqv$mcJ$sp(x, y);
   }

   public boolean eqv$mcJ$sp(final long[] x, final long[] y) {
      return ArraySupport$.MODULE$.eqv$mJc$sp(x, y, this.evidence$4$mcJ$sp);
   }

   public ArrayEq$mcJ$sp(final Eq evidence$4$mcJ$sp) {
      super(evidence$4$mcJ$sp);
      this.evidence$4$mcJ$sp = evidence$4$mcJ$sp;
   }
}
