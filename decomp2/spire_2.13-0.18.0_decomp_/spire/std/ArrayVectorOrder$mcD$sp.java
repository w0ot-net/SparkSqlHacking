package spire.std;

import algebra.ring.AdditiveMonoid;
import cats.kernel.Order;

public class ArrayVectorOrder$mcD$sp extends ArrayVectorOrder {
   private static final long serialVersionUID = 0L;
   public final Order evidence$38$mcD$sp;
   public final AdditiveMonoid evidence$39$mcD$sp;

   public boolean eqv(final double[] x, final double[] y) {
      return this.eqv$mcD$sp(x, y);
   }

   public boolean eqv$mcD$sp(final double[] x, final double[] y) {
      return ArraySupport$.MODULE$.vectorEqv$mDc$sp(x, y, this.evidence$38$mcD$sp, this.evidence$39$mcD$sp);
   }

   public int compare(final double[] x, final double[] y) {
      return this.compare$mcD$sp(x, y);
   }

   public int compare$mcD$sp(final double[] x, final double[] y) {
      return ArraySupport$.MODULE$.vectorCompare$mDc$sp(x, y, this.evidence$38$mcD$sp, this.evidence$39$mcD$sp);
   }

   public ArrayVectorOrder$mcD$sp(final Order evidence$38$mcD$sp, final AdditiveMonoid evidence$39$mcD$sp) {
      super(evidence$38$mcD$sp, evidence$39$mcD$sp);
      this.evidence$38$mcD$sp = evidence$38$mcD$sp;
      this.evidence$39$mcD$sp = evidence$39$mcD$sp;
   }
}
