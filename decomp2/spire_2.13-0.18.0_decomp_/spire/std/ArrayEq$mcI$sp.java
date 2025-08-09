package spire.std;

import cats.kernel.Eq;

public final class ArrayEq$mcI$sp extends ArrayEq {
   private static final long serialVersionUID = 0L;
   public final Eq evidence$30$mcI$sp;

   public boolean eqv(final int[] x, final int[] y) {
      return this.eqv$mcI$sp(x, y);
   }

   public boolean eqv$mcI$sp(final int[] x, final int[] y) {
      return ArraySupport$.MODULE$.eqv$mIc$sp(x, y, this.evidence$30$mcI$sp);
   }

   public ArrayEq$mcI$sp(final Eq evidence$30$mcI$sp) {
      super(evidence$30$mcI$sp);
      this.evidence$30$mcI$sp = evidence$30$mcI$sp;
   }
}
