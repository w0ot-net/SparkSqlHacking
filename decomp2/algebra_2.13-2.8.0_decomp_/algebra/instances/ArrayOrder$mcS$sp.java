package algebra.instances;

import cats.kernel.Order;

public final class ArrayOrder$mcS$sp extends ArrayOrder {
   public final Order evidence$5$mcS$sp;

   public boolean eqv(final short[] x, final short[] y) {
      return this.eqv$mcS$sp(x, y);
   }

   public boolean eqv$mcS$sp(final short[] x, final short[] y) {
      return ArraySupport$.MODULE$.eqv$mSc$sp(x, y, this.evidence$5$mcS$sp);
   }

   public int compare(final short[] x, final short[] y) {
      return this.compare$mcS$sp(x, y);
   }

   public int compare$mcS$sp(final short[] x, final short[] y) {
      return ArraySupport$.MODULE$.compare$mSc$sp(x, y, this.evidence$5$mcS$sp);
   }

   public ArrayOrder$mcS$sp(final Order evidence$5$mcS$sp) {
      super(evidence$5$mcS$sp);
      this.evidence$5$mcS$sp = evidence$5$mcS$sp;
   }
}
