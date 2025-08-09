package algebra.instances;

import cats.kernel.PartialOrder;

public final class ArrayPartialOrder$mcI$sp extends ArrayPartialOrder {
   public final PartialOrder evidence$6$mcI$sp;

   public boolean eqv(final int[] x, final int[] y) {
      return this.eqv$mcI$sp(x, y);
   }

   public boolean eqv$mcI$sp(final int[] x, final int[] y) {
      return ArraySupport$.MODULE$.eqv$mIc$sp(x, y, this.evidence$6$mcI$sp);
   }

   public double partialCompare(final int[] x, final int[] y) {
      return this.partialCompare$mcI$sp(x, y);
   }

   public double partialCompare$mcI$sp(final int[] x, final int[] y) {
      return ArraySupport$.MODULE$.partialCompare$mIc$sp(x, y, this.evidence$6$mcI$sp);
   }

   public ArrayPartialOrder$mcI$sp(final PartialOrder evidence$6$mcI$sp) {
      super(evidence$6$mcI$sp);
      this.evidence$6$mcI$sp = evidence$6$mcI$sp;
   }
}
