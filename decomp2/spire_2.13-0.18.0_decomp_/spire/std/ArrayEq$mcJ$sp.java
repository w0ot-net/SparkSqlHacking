package spire.std;

import cats.kernel.Eq;

public final class ArrayEq$mcJ$sp extends ArrayEq {
   private static final long serialVersionUID = 0L;
   public final Eq evidence$30$mcJ$sp;

   public boolean eqv(final long[] x, final long[] y) {
      return this.eqv$mcJ$sp(x, y);
   }

   public boolean eqv$mcJ$sp(final long[] x, final long[] y) {
      return ArraySupport$.MODULE$.eqv$mJc$sp(x, y, this.evidence$30$mcJ$sp);
   }

   public ArrayEq$mcJ$sp(final Eq evidence$30$mcJ$sp) {
      super(evidence$30$mcJ$sp);
      this.evidence$30$mcJ$sp = evidence$30$mcJ$sp;
   }
}
