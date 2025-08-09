package cats.kernel.instances;

import cats.kernel.Order;
import cats.kernel.OrderToOrderingConversion;
import scala.math.Ordering;

public final class order$ implements OrderInstances {
   public static final order$ MODULE$ = new order$();

   static {
      OrderToOrderingConversion.$init$(MODULE$);
   }

   public Ordering catsKernelOrderingForOrder(final Order ev) {
      return OrderToOrderingConversion.catsKernelOrderingForOrder$(this, ev);
   }

   private order$() {
   }
}
