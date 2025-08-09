package spire.optional;

import cats.kernel.Order;
import cats.kernel.PartialOrder;

public final class intervalSubsetPartialOrder$ {
   public static final intervalSubsetPartialOrder$ MODULE$ = new intervalSubsetPartialOrder$();

   public PartialOrder intervalSubsetPartialOrder(final Order evidence$2) {
      return new intervalSubsetPartialOrder.IntervalSubsetPartialOrder(evidence$2);
   }

   private intervalSubsetPartialOrder$() {
   }
}
