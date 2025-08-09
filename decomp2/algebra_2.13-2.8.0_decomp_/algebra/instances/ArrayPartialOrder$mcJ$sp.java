package algebra.instances;

import cats.kernel.PartialOrder;

public final class ArrayPartialOrder$mcJ$sp extends ArrayPartialOrder {
   public final PartialOrder evidence$6$mcJ$sp;

   public boolean eqv(final long[] x, final long[] y) {
      return this.eqv$mcJ$sp(x, y);
   }

   public boolean eqv$mcJ$sp(final long[] x, final long[] y) {
      return ArraySupport$.MODULE$.eqv$mJc$sp(x, y, this.evidence$6$mcJ$sp);
   }

   public double partialCompare(final long[] x, final long[] y) {
      return this.partialCompare$mcJ$sp(x, y);
   }

   public double partialCompare$mcJ$sp(final long[] x, final long[] y) {
      return ArraySupport$.MODULE$.partialCompare$mJc$sp(x, y, this.evidence$6$mcJ$sp);
   }

   public ArrayPartialOrder$mcJ$sp(final PartialOrder evidence$6$mcJ$sp) {
      super(evidence$6$mcJ$sp);
      this.evidence$6$mcJ$sp = evidence$6$mcJ$sp;
   }
}
