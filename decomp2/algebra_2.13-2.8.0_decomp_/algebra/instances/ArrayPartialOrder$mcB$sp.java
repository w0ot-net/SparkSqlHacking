package algebra.instances;

import cats.kernel.PartialOrder;

public final class ArrayPartialOrder$mcB$sp extends ArrayPartialOrder {
   public final PartialOrder evidence$6$mcB$sp;

   public boolean eqv(final byte[] x, final byte[] y) {
      return this.eqv$mcB$sp(x, y);
   }

   public boolean eqv$mcB$sp(final byte[] x, final byte[] y) {
      return ArraySupport$.MODULE$.eqv$mBc$sp(x, y, this.evidence$6$mcB$sp);
   }

   public double partialCompare(final byte[] x, final byte[] y) {
      return this.partialCompare$mcB$sp(x, y);
   }

   public double partialCompare$mcB$sp(final byte[] x, final byte[] y) {
      return ArraySupport$.MODULE$.partialCompare$mBc$sp(x, y, this.evidence$6$mcB$sp);
   }

   public ArrayPartialOrder$mcB$sp(final PartialOrder evidence$6$mcB$sp) {
      super(evidence$6$mcB$sp);
      this.evidence$6$mcB$sp = evidence$6$mcB$sp;
   }
}
