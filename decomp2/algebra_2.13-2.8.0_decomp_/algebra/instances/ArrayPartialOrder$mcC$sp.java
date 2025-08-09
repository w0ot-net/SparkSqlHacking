package algebra.instances;

import cats.kernel.PartialOrder;

public final class ArrayPartialOrder$mcC$sp extends ArrayPartialOrder {
   public final PartialOrder evidence$6$mcC$sp;

   public boolean eqv(final char[] x, final char[] y) {
      return this.eqv$mcC$sp(x, y);
   }

   public boolean eqv$mcC$sp(final char[] x, final char[] y) {
      return ArraySupport$.MODULE$.eqv$mCc$sp(x, y, this.evidence$6$mcC$sp);
   }

   public double partialCompare(final char[] x, final char[] y) {
      return this.partialCompare$mcC$sp(x, y);
   }

   public double partialCompare$mcC$sp(final char[] x, final char[] y) {
      return ArraySupport$.MODULE$.partialCompare$mCc$sp(x, y, this.evidence$6$mcC$sp);
   }

   public ArrayPartialOrder$mcC$sp(final PartialOrder evidence$6$mcC$sp) {
      super(evidence$6$mcC$sp);
      this.evidence$6$mcC$sp = evidence$6$mcC$sp;
   }
}
