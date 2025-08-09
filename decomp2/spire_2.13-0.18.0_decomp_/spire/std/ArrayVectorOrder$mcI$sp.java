package spire.std;

import algebra.ring.AdditiveMonoid;
import cats.kernel.Order;

public class ArrayVectorOrder$mcI$sp extends ArrayVectorOrder {
   private static final long serialVersionUID = 0L;
   public final Order evidence$38$mcI$sp;
   public final AdditiveMonoid evidence$39$mcI$sp;

   public boolean eqv(final int[] x, final int[] y) {
      return this.eqv$mcI$sp(x, y);
   }

   public boolean eqv$mcI$sp(final int[] x, final int[] y) {
      return ArraySupport$.MODULE$.vectorEqv$mIc$sp(x, y, this.evidence$38$mcI$sp, this.evidence$39$mcI$sp);
   }

   public int compare(final int[] x, final int[] y) {
      return this.compare$mcI$sp(x, y);
   }

   public int compare$mcI$sp(final int[] x, final int[] y) {
      return ArraySupport$.MODULE$.vectorCompare$mIc$sp(x, y, this.evidence$38$mcI$sp, this.evidence$39$mcI$sp);
   }

   public ArrayVectorOrder$mcI$sp(final Order evidence$38$mcI$sp, final AdditiveMonoid evidence$39$mcI$sp) {
      super(evidence$38$mcI$sp, evidence$39$mcI$sp);
      this.evidence$38$mcI$sp = evidence$38$mcI$sp;
      this.evidence$39$mcI$sp = evidence$39$mcI$sp;
   }
}
