package algebra.instances;

import cats.kernel.PartialOrder;

public final class ArrayPartialOrder$mcS$sp extends ArrayPartialOrder {
   public final PartialOrder evidence$6$mcS$sp;

   public boolean eqv(final short[] x, final short[] y) {
      return this.eqv$mcS$sp(x, y);
   }

   public boolean eqv$mcS$sp(final short[] x, final short[] y) {
      return ArraySupport$.MODULE$.eqv$mSc$sp(x, y, this.evidence$6$mcS$sp);
   }

   public double partialCompare(final short[] x, final short[] y) {
      return this.partialCompare$mcS$sp(x, y);
   }

   public double partialCompare$mcS$sp(final short[] x, final short[] y) {
      return ArraySupport$.MODULE$.partialCompare$mSc$sp(x, y, this.evidence$6$mcS$sp);
   }

   public ArrayPartialOrder$mcS$sp(final PartialOrder evidence$6$mcS$sp) {
      super(evidence$6$mcS$sp);
      this.evidence$6$mcS$sp = evidence$6$mcS$sp;
   }
}
