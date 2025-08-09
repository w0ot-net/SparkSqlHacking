package cats.kernel.instances.short;

import cats.kernel.CommutativeGroup;
import cats.kernel.Order;
import cats.kernel.instances.ShortInstances;

public final class package$ implements ShortInstances {
   public static final package$ MODULE$ = new package$();
   private static Order catsKernelStdOrderForShort;
   private static CommutativeGroup catsKernelStdGroupForShort;

   static {
      ShortInstances.$init$(MODULE$);
   }

   public Order catsKernelStdOrderForShort() {
      return catsKernelStdOrderForShort;
   }

   public CommutativeGroup catsKernelStdGroupForShort() {
      return catsKernelStdGroupForShort;
   }

   public void cats$kernel$instances$ShortInstances$_setter_$catsKernelStdOrderForShort_$eq(final Order x$1) {
      catsKernelStdOrderForShort = x$1;
   }

   public void cats$kernel$instances$ShortInstances$_setter_$catsKernelStdGroupForShort_$eq(final CommutativeGroup x$1) {
      catsKernelStdGroupForShort = x$1;
   }

   private package$() {
   }
}
