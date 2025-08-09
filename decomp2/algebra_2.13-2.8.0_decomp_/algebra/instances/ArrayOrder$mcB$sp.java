package algebra.instances;

import cats.kernel.Order;

public final class ArrayOrder$mcB$sp extends ArrayOrder {
   public final Order evidence$5$mcB$sp;

   public boolean eqv(final byte[] x, final byte[] y) {
      return this.eqv$mcB$sp(x, y);
   }

   public boolean eqv$mcB$sp(final byte[] x, final byte[] y) {
      return ArraySupport$.MODULE$.eqv$mBc$sp(x, y, this.evidence$5$mcB$sp);
   }

   public int compare(final byte[] x, final byte[] y) {
      return this.compare$mcB$sp(x, y);
   }

   public int compare$mcB$sp(final byte[] x, final byte[] y) {
      return ArraySupport$.MODULE$.compare$mBc$sp(x, y, this.evidence$5$mcB$sp);
   }

   public ArrayOrder$mcB$sp(final Order evidence$5$mcB$sp) {
      super(evidence$5$mcB$sp);
      this.evidence$5$mcB$sp = evidence$5$mcB$sp;
   }
}
