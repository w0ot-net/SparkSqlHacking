package algebra.instances;

import cats.kernel.PartialOrder;

public final class ArrayPartialOrder$mcD$sp extends ArrayPartialOrder {
   public final PartialOrder evidence$6$mcD$sp;

   public boolean eqv(final double[] x, final double[] y) {
      return this.eqv$mcD$sp(x, y);
   }

   public boolean eqv$mcD$sp(final double[] x, final double[] y) {
      return ArraySupport$.MODULE$.eqv$mDc$sp(x, y, this.evidence$6$mcD$sp);
   }

   public double partialCompare(final double[] x, final double[] y) {
      return this.partialCompare$mcD$sp(x, y);
   }

   public double partialCompare$mcD$sp(final double[] x, final double[] y) {
      return ArraySupport$.MODULE$.partialCompare$mDc$sp(x, y, this.evidence$6$mcD$sp);
   }

   public ArrayPartialOrder$mcD$sp(final PartialOrder evidence$6$mcD$sp) {
      super(evidence$6$mcD$sp);
      this.evidence$6$mcD$sp = evidence$6$mcD$sp;
   }
}
