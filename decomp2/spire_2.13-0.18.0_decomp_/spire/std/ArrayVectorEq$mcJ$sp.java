package spire.std;

import algebra.ring.AdditiveMonoid;
import cats.kernel.Eq;

public class ArrayVectorEq$mcJ$sp extends ArrayVectorEq {
   private static final long serialVersionUID = 0L;
   public final Eq evidence$36$mcJ$sp;
   public final AdditiveMonoid evidence$37$mcJ$sp;

   public boolean eqv(final long[] x, final long[] y) {
      return this.eqv$mcJ$sp(x, y);
   }

   public boolean eqv$mcJ$sp(final long[] x, final long[] y) {
      return ArraySupport$.MODULE$.vectorEqv$mJc$sp(x, y, this.evidence$36$mcJ$sp, this.evidence$37$mcJ$sp);
   }

   public ArrayVectorEq$mcJ$sp(final Eq evidence$36$mcJ$sp, final AdditiveMonoid evidence$37$mcJ$sp) {
      super(evidence$36$mcJ$sp, evidence$37$mcJ$sp);
      this.evidence$36$mcJ$sp = evidence$36$mcJ$sp;
      this.evidence$37$mcJ$sp = evidence$37$mcJ$sp;
   }
}
