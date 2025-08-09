package spire.std;

import algebra.ring.AdditiveMonoid;
import cats.kernel.Eq;

public class ArrayVectorEq$mcF$sp extends ArrayVectorEq {
   private static final long serialVersionUID = 0L;
   public final Eq evidence$36$mcF$sp;
   public final AdditiveMonoid evidence$37$mcF$sp;

   public boolean eqv(final float[] x, final float[] y) {
      return this.eqv$mcF$sp(x, y);
   }

   public boolean eqv$mcF$sp(final float[] x, final float[] y) {
      return ArraySupport$.MODULE$.vectorEqv$mFc$sp(x, y, this.evidence$36$mcF$sp, this.evidence$37$mcF$sp);
   }

   public ArrayVectorEq$mcF$sp(final Eq evidence$36$mcF$sp, final AdditiveMonoid evidence$37$mcF$sp) {
      super(evidence$36$mcF$sp, evidence$37$mcF$sp);
      this.evidence$36$mcF$sp = evidence$36$mcF$sp;
      this.evidence$37$mcF$sp = evidence$37$mcF$sp;
   }
}
