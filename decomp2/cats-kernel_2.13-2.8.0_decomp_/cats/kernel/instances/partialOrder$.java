package cats.kernel.instances;

import cats.kernel.PartialOrder;
import cats.kernel.PartialOrderToPartialOrderingConversion;
import scala.math.PartialOrdering;

public final class partialOrder$ implements PartialOrderInstances {
   public static final partialOrder$ MODULE$ = new partialOrder$();

   static {
      PartialOrderToPartialOrderingConversion.$init$(MODULE$);
   }

   public PartialOrdering catsKernelPartialOrderingForPartialOrder(final PartialOrder ev) {
      return PartialOrderToPartialOrderingConversion.catsKernelPartialOrderingForPartialOrder$(this, ev);
   }

   private partialOrder$() {
   }
}
