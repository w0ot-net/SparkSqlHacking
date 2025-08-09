package algebra.instances;

import cats.kernel.PartialOrder;

public final class ArrayPartialOrder$mcF$sp extends ArrayPartialOrder {
   public final PartialOrder evidence$6$mcF$sp;

   public boolean eqv(final float[] x, final float[] y) {
      return this.eqv$mcF$sp(x, y);
   }

   public boolean eqv$mcF$sp(final float[] x, final float[] y) {
      return ArraySupport$.MODULE$.eqv$mFc$sp(x, y, this.evidence$6$mcF$sp);
   }

   public double partialCompare(final float[] x, final float[] y) {
      return this.partialCompare$mcF$sp(x, y);
   }

   public double partialCompare$mcF$sp(final float[] x, final float[] y) {
      return ArraySupport$.MODULE$.partialCompare$mFc$sp(x, y, this.evidence$6$mcF$sp);
   }

   public ArrayPartialOrder$mcF$sp(final PartialOrder evidence$6$mcF$sp) {
      super(evidence$6$mcF$sp);
      this.evidence$6$mcF$sp = evidence$6$mcF$sp;
   }
}
