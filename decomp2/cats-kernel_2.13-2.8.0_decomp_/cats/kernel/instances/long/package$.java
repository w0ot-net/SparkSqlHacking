package cats.kernel.instances.long;

import cats.kernel.CommutativeGroup;
import cats.kernel.Order;
import cats.kernel.instances.LongInstances;

public final class package$ implements LongInstances {
   public static final package$ MODULE$ = new package$();
   private static Order catsKernelStdOrderForLong;
   private static CommutativeGroup catsKernelStdGroupForLong;

   static {
      LongInstances.$init$(MODULE$);
   }

   public Order catsKernelStdOrderForLong() {
      return catsKernelStdOrderForLong;
   }

   public CommutativeGroup catsKernelStdGroupForLong() {
      return catsKernelStdGroupForLong;
   }

   public void cats$kernel$instances$LongInstances$_setter_$catsKernelStdOrderForLong_$eq(final Order x$1) {
      catsKernelStdOrderForLong = x$1;
   }

   public void cats$kernel$instances$LongInstances$_setter_$catsKernelStdGroupForLong_$eq(final CommutativeGroup x$1) {
      catsKernelStdGroupForLong = x$1;
   }

   private package$() {
   }
}
