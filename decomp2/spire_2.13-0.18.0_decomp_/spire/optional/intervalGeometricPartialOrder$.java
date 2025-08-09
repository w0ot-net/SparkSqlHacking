package spire.optional;

import cats.kernel.Order;
import cats.kernel.PartialOrder;

public final class intervalGeometricPartialOrder$ {
   public static final intervalGeometricPartialOrder$ MODULE$ = new intervalGeometricPartialOrder$();

   public PartialOrder intervalGeometricPartialOrder(final Order evidence$2) {
      return new intervalGeometricPartialOrder.IntervalGeometricPartialOrder(evidence$2);
   }

   private intervalGeometricPartialOrder$() {
   }
}
