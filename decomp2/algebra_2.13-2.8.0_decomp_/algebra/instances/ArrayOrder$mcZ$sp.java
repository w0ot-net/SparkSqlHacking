package algebra.instances;

import cats.kernel.Order;

public final class ArrayOrder$mcZ$sp extends ArrayOrder {
   public final Order evidence$5$mcZ$sp;

   public boolean eqv(final boolean[] x, final boolean[] y) {
      return this.eqv$mcZ$sp(x, y);
   }

   public boolean eqv$mcZ$sp(final boolean[] x, final boolean[] y) {
      return ArraySupport$.MODULE$.eqv$mZc$sp(x, y, this.evidence$5$mcZ$sp);
   }

   public int compare(final boolean[] x, final boolean[] y) {
      return this.compare$mcZ$sp(x, y);
   }

   public int compare$mcZ$sp(final boolean[] x, final boolean[] y) {
      return ArraySupport$.MODULE$.compare$mZc$sp(x, y, this.evidence$5$mcZ$sp);
   }

   public ArrayOrder$mcZ$sp(final Order evidence$5$mcZ$sp) {
      super(evidence$5$mcZ$sp);
      this.evidence$5$mcZ$sp = evidence$5$mcZ$sp;
   }
}
