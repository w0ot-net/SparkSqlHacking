package algebra.instances;

import cats.kernel.Order;

public final class ArrayOrder$mcC$sp extends ArrayOrder {
   public final Order evidence$5$mcC$sp;

   public boolean eqv(final char[] x, final char[] y) {
      return this.eqv$mcC$sp(x, y);
   }

   public boolean eqv$mcC$sp(final char[] x, final char[] y) {
      return ArraySupport$.MODULE$.eqv$mCc$sp(x, y, this.evidence$5$mcC$sp);
   }

   public int compare(final char[] x, final char[] y) {
      return this.compare$mcC$sp(x, y);
   }

   public int compare$mcC$sp(final char[] x, final char[] y) {
      return ArraySupport$.MODULE$.compare$mCc$sp(x, y, this.evidence$5$mcC$sp);
   }

   public ArrayOrder$mcC$sp(final Order evidence$5$mcC$sp) {
      super(evidence$5$mcC$sp);
      this.evidence$5$mcC$sp = evidence$5$mcC$sp;
   }
}
