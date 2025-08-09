package cats.kernel.instances.boolean;

import cats.kernel.Order;
import cats.kernel.instances.BooleanInstances;

public final class package$ implements BooleanInstances {
   public static final package$ MODULE$ = new package$();
   private static Order catsKernelStdOrderForBoolean;

   static {
      BooleanInstances.$init$(MODULE$);
   }

   public Order catsKernelStdOrderForBoolean() {
      return catsKernelStdOrderForBoolean;
   }

   public void cats$kernel$instances$BooleanInstances$_setter_$catsKernelStdOrderForBoolean_$eq(final Order x$1) {
      catsKernelStdOrderForBoolean = x$1;
   }

   private package$() {
   }
}
