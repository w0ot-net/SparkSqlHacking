package algebra.instances;

import cats.kernel.Order;

public final class ArrayOrder$mcF$sp extends ArrayOrder {
   public final Order evidence$5$mcF$sp;

   public boolean eqv(final float[] x, final float[] y) {
      return this.eqv$mcF$sp(x, y);
   }

   public boolean eqv$mcF$sp(final float[] x, final float[] y) {
      return ArraySupport$.MODULE$.eqv$mFc$sp(x, y, this.evidence$5$mcF$sp);
   }

   public int compare(final float[] x, final float[] y) {
      return this.compare$mcF$sp(x, y);
   }

   public int compare$mcF$sp(final float[] x, final float[] y) {
      return ArraySupport$.MODULE$.compare$mFc$sp(x, y, this.evidence$5$mcF$sp);
   }

   public ArrayOrder$mcF$sp(final Order evidence$5$mcF$sp) {
      super(evidence$5$mcF$sp);
      this.evidence$5$mcF$sp = evidence$5$mcF$sp;
   }
}
