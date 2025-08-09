package spire.optional;

import cats.kernel.PartialOrder;

public final class powerSetPartialOrder$ {
   public static final powerSetPartialOrder$ MODULE$ = new powerSetPartialOrder$();

   public PartialOrder powerSetPartialOrder() {
      return new powerSetPartialOrder.PowerSetPartialOrder();
   }

   private powerSetPartialOrder$() {
   }
}
