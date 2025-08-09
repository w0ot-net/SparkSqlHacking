package cats.kernel.instances.duration;

import cats.kernel.CommutativeGroup;
import cats.kernel.Order;
import cats.kernel.instances.DurationInstances;

public final class package$ implements DurationInstances {
   public static final package$ MODULE$ = new package$();
   private static Order catsKernelStdOrderForDuration;
   private static CommutativeGroup catsKernelStdGroupForDuration;

   static {
      DurationInstances.$init$(MODULE$);
   }

   public Order catsKernelStdOrderForDuration() {
      return catsKernelStdOrderForDuration;
   }

   public CommutativeGroup catsKernelStdGroupForDuration() {
      return catsKernelStdGroupForDuration;
   }

   public void cats$kernel$instances$DurationInstances$_setter_$catsKernelStdOrderForDuration_$eq(final Order x$1) {
      catsKernelStdOrderForDuration = x$1;
   }

   public void cats$kernel$instances$DurationInstances$_setter_$catsKernelStdGroupForDuration_$eq(final CommutativeGroup x$1) {
      catsKernelStdGroupForDuration = x$1;
   }

   private package$() {
   }
}
