package spire.std;

import algebra.ring.AdditiveMonoid;
import cats.kernel.Eq;

public class ArrayVectorEq$mcD$sp extends ArrayVectorEq {
   private static final long serialVersionUID = 0L;
   public final Eq evidence$36$mcD$sp;
   public final AdditiveMonoid evidence$37$mcD$sp;

   public boolean eqv(final double[] x, final double[] y) {
      return this.eqv$mcD$sp(x, y);
   }

   public boolean eqv$mcD$sp(final double[] x, final double[] y) {
      return ArraySupport$.MODULE$.vectorEqv$mDc$sp(x, y, this.evidence$36$mcD$sp, this.evidence$37$mcD$sp);
   }

   public ArrayVectorEq$mcD$sp(final Eq evidence$36$mcD$sp, final AdditiveMonoid evidence$37$mcD$sp) {
      super(evidence$36$mcD$sp, evidence$37$mcD$sp);
      this.evidence$36$mcD$sp = evidence$36$mcD$sp;
      this.evidence$37$mcD$sp = evidence$37$mcD$sp;
   }
}
