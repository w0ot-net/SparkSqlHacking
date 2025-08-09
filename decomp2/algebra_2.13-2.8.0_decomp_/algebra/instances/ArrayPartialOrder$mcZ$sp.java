package algebra.instances;

import cats.kernel.PartialOrder;

public final class ArrayPartialOrder$mcZ$sp extends ArrayPartialOrder {
   public final PartialOrder evidence$6$mcZ$sp;

   public boolean eqv(final boolean[] x, final boolean[] y) {
      return this.eqv$mcZ$sp(x, y);
   }

   public boolean eqv$mcZ$sp(final boolean[] x, final boolean[] y) {
      return ArraySupport$.MODULE$.eqv$mZc$sp(x, y, this.evidence$6$mcZ$sp);
   }

   public double partialCompare(final boolean[] x, final boolean[] y) {
      return this.partialCompare$mcZ$sp(x, y);
   }

   public double partialCompare$mcZ$sp(final boolean[] x, final boolean[] y) {
      return ArraySupport$.MODULE$.partialCompare$mZc$sp(x, y, this.evidence$6$mcZ$sp);
   }

   public ArrayPartialOrder$mcZ$sp(final PartialOrder evidence$6$mcZ$sp) {
      super(evidence$6$mcZ$sp);
      this.evidence$6$mcZ$sp = evidence$6$mcZ$sp;
   }
}
