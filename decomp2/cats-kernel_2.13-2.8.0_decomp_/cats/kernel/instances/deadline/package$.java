package cats.kernel.instances.deadline;

import cats.kernel.Order;
import cats.kernel.instances.DeadlineInstances;

public final class package$ implements DeadlineInstances {
   public static final package$ MODULE$ = new package$();
   private static Order catsKernelStdOrderForDeadline;

   static {
      DeadlineInstances.$init$(MODULE$);
   }

   public Order catsKernelStdOrderForDeadline() {
      return catsKernelStdOrderForDeadline;
   }

   public void cats$kernel$instances$DeadlineInstances$_setter_$catsKernelStdOrderForDeadline_$eq(final Order x$1) {
      catsKernelStdOrderForDeadline = x$1;
   }

   private package$() {
   }
}
