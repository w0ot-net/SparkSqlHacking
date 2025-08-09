package algebra.instances;

import cats.kernel.Eq;

public final class ArrayEq$mcC$sp extends ArrayEq {
   public final Eq evidence$4$mcC$sp;

   public boolean eqv(final char[] x, final char[] y) {
      return this.eqv$mcC$sp(x, y);
   }

   public boolean eqv$mcC$sp(final char[] x, final char[] y) {
      return ArraySupport$.MODULE$.eqv$mCc$sp(x, y, this.evidence$4$mcC$sp);
   }

   public ArrayEq$mcC$sp(final Eq evidence$4$mcC$sp) {
      super(evidence$4$mcC$sp);
      this.evidence$4$mcC$sp = evidence$4$mcC$sp;
   }
}
