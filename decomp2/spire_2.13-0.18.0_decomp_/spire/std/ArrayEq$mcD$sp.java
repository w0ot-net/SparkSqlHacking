package spire.std;

import cats.kernel.Eq;

public final class ArrayEq$mcD$sp extends ArrayEq {
   private static final long serialVersionUID = 0L;
   public final Eq evidence$30$mcD$sp;

   public boolean eqv(final double[] x, final double[] y) {
      return this.eqv$mcD$sp(x, y);
   }

   public boolean eqv$mcD$sp(final double[] x, final double[] y) {
      return ArraySupport$.MODULE$.eqv$mDc$sp(x, y, this.evidence$30$mcD$sp);
   }

   public ArrayEq$mcD$sp(final Eq evidence$30$mcD$sp) {
      super(evidence$30$mcD$sp);
      this.evidence$30$mcD$sp = evidence$30$mcD$sp;
   }
}
