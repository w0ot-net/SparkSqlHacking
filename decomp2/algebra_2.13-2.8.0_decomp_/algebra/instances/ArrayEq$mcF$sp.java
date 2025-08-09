package algebra.instances;

import cats.kernel.Eq;

public final class ArrayEq$mcF$sp extends ArrayEq {
   public final Eq evidence$4$mcF$sp;

   public boolean eqv(final float[] x, final float[] y) {
      return this.eqv$mcF$sp(x, y);
   }

   public boolean eqv$mcF$sp(final float[] x, final float[] y) {
      return ArraySupport$.MODULE$.eqv$mFc$sp(x, y, this.evidence$4$mcF$sp);
   }

   public ArrayEq$mcF$sp(final Eq evidence$4$mcF$sp) {
      super(evidence$4$mcF$sp);
      this.evidence$4$mcF$sp = evidence$4$mcF$sp;
   }
}
