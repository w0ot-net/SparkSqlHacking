package spire.std;

import algebra.ring.AdditiveMonoid;
import cats.kernel.Order;

public class ArrayVectorOrder$mcF$sp extends ArrayVectorOrder {
   private static final long serialVersionUID = 0L;
   public final Order evidence$38$mcF$sp;
   public final AdditiveMonoid evidence$39$mcF$sp;

   public boolean eqv(final float[] x, final float[] y) {
      return this.eqv$mcF$sp(x, y);
   }

   public boolean eqv$mcF$sp(final float[] x, final float[] y) {
      return ArraySupport$.MODULE$.vectorEqv$mFc$sp(x, y, this.evidence$38$mcF$sp, this.evidence$39$mcF$sp);
   }

   public int compare(final float[] x, final float[] y) {
      return this.compare$mcF$sp(x, y);
   }

   public int compare$mcF$sp(final float[] x, final float[] y) {
      return ArraySupport$.MODULE$.vectorCompare$mFc$sp(x, y, this.evidence$38$mcF$sp, this.evidence$39$mcF$sp);
   }

   public ArrayVectorOrder$mcF$sp(final Order evidence$38$mcF$sp, final AdditiveMonoid evidence$39$mcF$sp) {
      super(evidence$38$mcF$sp, evidence$39$mcF$sp);
      this.evidence$38$mcF$sp = evidence$38$mcF$sp;
      this.evidence$39$mcF$sp = evidence$39$mcF$sp;
   }
}
