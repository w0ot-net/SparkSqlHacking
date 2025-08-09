package spire.std;

import algebra.ring.AdditiveMonoid;
import cats.kernel.Eq;

public class ArrayVectorEq$mcI$sp extends ArrayVectorEq {
   private static final long serialVersionUID = 0L;
   public final Eq evidence$36$mcI$sp;
   public final AdditiveMonoid evidence$37$mcI$sp;

   public boolean eqv(final int[] x, final int[] y) {
      return this.eqv$mcI$sp(x, y);
   }

   public boolean eqv$mcI$sp(final int[] x, final int[] y) {
      return ArraySupport$.MODULE$.vectorEqv$mIc$sp(x, y, this.evidence$36$mcI$sp, this.evidence$37$mcI$sp);
   }

   public ArrayVectorEq$mcI$sp(final Eq evidence$36$mcI$sp, final AdditiveMonoid evidence$37$mcI$sp) {
      super(evidence$36$mcI$sp, evidence$37$mcI$sp);
      this.evidence$36$mcI$sp = evidence$36$mcI$sp;
      this.evidence$37$mcI$sp = evidence$37$mcI$sp;
   }
}
