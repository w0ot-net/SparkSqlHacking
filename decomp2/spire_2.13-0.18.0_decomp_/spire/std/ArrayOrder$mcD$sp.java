package spire.std;

import cats.kernel.Order;

public final class ArrayOrder$mcD$sp extends ArrayOrder {
   private static final long serialVersionUID = 0L;
   public final Order evidence$33$mcD$sp;

   public boolean eqv(final double[] x, final double[] y) {
      return this.eqv$mcD$sp(x, y);
   }

   public boolean eqv$mcD$sp(final double[] x, final double[] y) {
      return ArraySupport$.MODULE$.eqv$mDc$sp(x, y, this.evidence$33$mcD$sp);
   }

   public int compare(final double[] x, final double[] y) {
      return this.compare$mcD$sp(x, y);
   }

   public int compare$mcD$sp(final double[] x, final double[] y) {
      return ArraySupport$.MODULE$.compare$mDc$sp(x, y, this.evidence$33$mcD$sp);
   }

   public ArrayOrder$mcD$sp(final Order evidence$33$mcD$sp) {
      super(evidence$33$mcD$sp);
      this.evidence$33$mcD$sp = evidence$33$mcD$sp;
   }
}
