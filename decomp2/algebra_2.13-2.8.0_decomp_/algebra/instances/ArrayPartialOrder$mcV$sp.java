package algebra.instances;

import cats.kernel.PartialOrder;
import scala.runtime.BoxedUnit;

public final class ArrayPartialOrder$mcV$sp extends ArrayPartialOrder {
   public final PartialOrder evidence$6$mcV$sp;

   public boolean eqv(final BoxedUnit[] x, final BoxedUnit[] y) {
      return this.eqv$mcV$sp(x, y);
   }

   public boolean eqv$mcV$sp(final BoxedUnit[] x, final BoxedUnit[] y) {
      return ArraySupport$.MODULE$.eqv$mVc$sp(x, y, this.evidence$6$mcV$sp);
   }

   public double partialCompare(final BoxedUnit[] x, final BoxedUnit[] y) {
      return this.partialCompare$mcV$sp(x, y);
   }

   public double partialCompare$mcV$sp(final BoxedUnit[] x, final BoxedUnit[] y) {
      return ArraySupport$.MODULE$.partialCompare$mVc$sp(x, y, this.evidence$6$mcV$sp);
   }

   public ArrayPartialOrder$mcV$sp(final PartialOrder evidence$6$mcV$sp) {
      super(evidence$6$mcV$sp);
      this.evidence$6$mcV$sp = evidence$6$mcV$sp;
   }
}
