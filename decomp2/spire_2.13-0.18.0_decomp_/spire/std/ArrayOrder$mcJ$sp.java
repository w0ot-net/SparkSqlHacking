package spire.std;

import cats.kernel.Order;

public final class ArrayOrder$mcJ$sp extends ArrayOrder {
   private static final long serialVersionUID = 0L;
   public final Order evidence$33$mcJ$sp;

   public boolean eqv(final long[] x, final long[] y) {
      return this.eqv$mcJ$sp(x, y);
   }

   public boolean eqv$mcJ$sp(final long[] x, final long[] y) {
      return ArraySupport$.MODULE$.eqv$mJc$sp(x, y, this.evidence$33$mcJ$sp);
   }

   public int compare(final long[] x, final long[] y) {
      return this.compare$mcJ$sp(x, y);
   }

   public int compare$mcJ$sp(final long[] x, final long[] y) {
      return ArraySupport$.MODULE$.compare$mJc$sp(x, y, this.evidence$33$mcJ$sp);
   }

   public ArrayOrder$mcJ$sp(final Order evidence$33$mcJ$sp) {
      super(evidence$33$mcJ$sp);
      this.evidence$33$mcJ$sp = evidence$33$mcJ$sp;
   }
}
