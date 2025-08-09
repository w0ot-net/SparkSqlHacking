package spire.std;

import algebra.ring.AdditiveMonoid;
import cats.kernel.Order;

public class ArrayVectorOrder$mcJ$sp extends ArrayVectorOrder {
   private static final long serialVersionUID = 0L;
   public final Order evidence$38$mcJ$sp;
   public final AdditiveMonoid evidence$39$mcJ$sp;

   public boolean eqv(final long[] x, final long[] y) {
      return this.eqv$mcJ$sp(x, y);
   }

   public boolean eqv$mcJ$sp(final long[] x, final long[] y) {
      return ArraySupport$.MODULE$.vectorEqv$mJc$sp(x, y, this.evidence$38$mcJ$sp, this.evidence$39$mcJ$sp);
   }

   public int compare(final long[] x, final long[] y) {
      return this.compare$mcJ$sp(x, y);
   }

   public int compare$mcJ$sp(final long[] x, final long[] y) {
      return ArraySupport$.MODULE$.vectorCompare$mJc$sp(x, y, this.evidence$38$mcJ$sp, this.evidence$39$mcJ$sp);
   }

   public ArrayVectorOrder$mcJ$sp(final Order evidence$38$mcJ$sp, final AdditiveMonoid evidence$39$mcJ$sp) {
      super(evidence$38$mcJ$sp, evidence$39$mcJ$sp);
      this.evidence$38$mcJ$sp = evidence$38$mcJ$sp;
      this.evidence$39$mcJ$sp = evidence$39$mcJ$sp;
   }
}
