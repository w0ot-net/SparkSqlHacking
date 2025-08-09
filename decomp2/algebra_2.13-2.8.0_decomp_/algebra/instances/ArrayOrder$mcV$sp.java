package algebra.instances;

import cats.kernel.Order;
import scala.runtime.BoxedUnit;

public final class ArrayOrder$mcV$sp extends ArrayOrder {
   public final Order evidence$5$mcV$sp;

   public boolean eqv(final BoxedUnit[] x, final BoxedUnit[] y) {
      return this.eqv$mcV$sp(x, y);
   }

   public boolean eqv$mcV$sp(final BoxedUnit[] x, final BoxedUnit[] y) {
      return ArraySupport$.MODULE$.eqv$mVc$sp(x, y, this.evidence$5$mcV$sp);
   }

   public int compare(final BoxedUnit[] x, final BoxedUnit[] y) {
      return this.compare$mcV$sp(x, y);
   }

   public int compare$mcV$sp(final BoxedUnit[] x, final BoxedUnit[] y) {
      return ArraySupport$.MODULE$.compare$mVc$sp(x, y, this.evidence$5$mcV$sp);
   }

   public ArrayOrder$mcV$sp(final Order evidence$5$mcV$sp) {
      super(evidence$5$mcV$sp);
      this.evidence$5$mcV$sp = evidence$5$mcV$sp;
   }
}
