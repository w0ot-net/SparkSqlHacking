package spire.optional;

import cats.kernel.Order;
import cats.kernel.PartialOrder;

public final class intervalValuePartialOrder$ {
   public static final intervalValuePartialOrder$ MODULE$ = new intervalValuePartialOrder$();

   public PartialOrder intervalValuePartialOrder(final Order evidence$2) {
      return new intervalValuePartialOrder.IntervalValuePartialOrder(evidence$2);
   }

   private intervalValuePartialOrder$() {
   }
}
