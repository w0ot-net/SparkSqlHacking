package algebra.instances;

import cats.kernel.Order;

public final class ArrayOrder$mcI$sp extends ArrayOrder {
   public final Order evidence$5$mcI$sp;

   public boolean eqv(final int[] x, final int[] y) {
      return this.eqv$mcI$sp(x, y);
   }

   public boolean eqv$mcI$sp(final int[] x, final int[] y) {
      return ArraySupport$.MODULE$.eqv$mIc$sp(x, y, this.evidence$5$mcI$sp);
   }

   public int compare(final int[] x, final int[] y) {
      return this.compare$mcI$sp(x, y);
   }

   public int compare$mcI$sp(final int[] x, final int[] y) {
      return ArraySupport$.MODULE$.compare$mIc$sp(x, y, this.evidence$5$mcI$sp);
   }

   public ArrayOrder$mcI$sp(final Order evidence$5$mcI$sp) {
      super(evidence$5$mcI$sp);
      this.evidence$5$mcI$sp = evidence$5$mcI$sp;
   }
}
