package cats.kernel.instances.uuid;

import cats.kernel.Order;
import cats.kernel.instances.UUIDInstances;

public final class package$ implements UUIDInstances {
   public static final package$ MODULE$ = new package$();
   private static Order catsKernelStdOrderForUUID;

   static {
      UUIDInstances.$init$(MODULE$);
   }

   public Order catsKernelStdOrderForUUID() {
      return catsKernelStdOrderForUUID;
   }

   public void cats$kernel$instances$UUIDInstances$_setter_$catsKernelStdOrderForUUID_$eq(final Order x$1) {
      catsKernelStdOrderForUUID = x$1;
   }

   private package$() {
   }
}
