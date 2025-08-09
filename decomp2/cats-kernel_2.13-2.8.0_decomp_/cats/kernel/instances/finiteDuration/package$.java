package cats.kernel.instances.finiteDuration;

import cats.kernel.CommutativeGroup;
import cats.kernel.Order;
import cats.kernel.instances.FiniteDurationInstances;

public final class package$ implements FiniteDurationInstances {
   public static final package$ MODULE$ = new package$();
   private static Order catsKernelStdOrderForFiniteDuration;
   private static CommutativeGroup catsKernelStdGroupForFiniteDuration;

   static {
      FiniteDurationInstances.$init$(MODULE$);
   }

   public Order catsKernelStdOrderForFiniteDuration() {
      return catsKernelStdOrderForFiniteDuration;
   }

   public CommutativeGroup catsKernelStdGroupForFiniteDuration() {
      return catsKernelStdGroupForFiniteDuration;
   }

   public void cats$kernel$instances$FiniteDurationInstances$_setter_$catsKernelStdOrderForFiniteDuration_$eq(final Order x$1) {
      catsKernelStdOrderForFiniteDuration = x$1;
   }

   public void cats$kernel$instances$FiniteDurationInstances$_setter_$catsKernelStdGroupForFiniteDuration_$eq(final CommutativeGroup x$1) {
      catsKernelStdGroupForFiniteDuration = x$1;
   }

   private package$() {
   }
}
