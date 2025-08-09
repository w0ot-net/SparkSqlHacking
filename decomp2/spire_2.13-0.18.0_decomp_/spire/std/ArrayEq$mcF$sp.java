package spire.std;

import cats.kernel.Eq;

public final class ArrayEq$mcF$sp extends ArrayEq {
   private static final long serialVersionUID = 0L;
   public final Eq evidence$30$mcF$sp;

   public boolean eqv(final float[] x, final float[] y) {
      return this.eqv$mcF$sp(x, y);
   }

   public boolean eqv$mcF$sp(final float[] x, final float[] y) {
      return ArraySupport$.MODULE$.eqv$mFc$sp(x, y, this.evidence$30$mcF$sp);
   }

   public ArrayEq$mcF$sp(final Eq evidence$30$mcF$sp) {
      super(evidence$30$mcF$sp);
      this.evidence$30$mcF$sp = evidence$30$mcF$sp;
   }
}
