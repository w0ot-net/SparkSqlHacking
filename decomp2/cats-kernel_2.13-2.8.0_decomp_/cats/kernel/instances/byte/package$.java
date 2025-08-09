package cats.kernel.instances.byte;

import cats.kernel.CommutativeGroup;
import cats.kernel.Order;
import cats.kernel.instances.ByteInstances;

public final class package$ implements ByteInstances {
   public static final package$ MODULE$ = new package$();
   private static Order catsKernelStdOrderForByte;
   private static CommutativeGroup catsKernelStdGroupForByte;

   static {
      ByteInstances.$init$(MODULE$);
   }

   public Order catsKernelStdOrderForByte() {
      return catsKernelStdOrderForByte;
   }

   public CommutativeGroup catsKernelStdGroupForByte() {
      return catsKernelStdGroupForByte;
   }

   public void cats$kernel$instances$ByteInstances$_setter_$catsKernelStdOrderForByte_$eq(final Order x$1) {
      catsKernelStdOrderForByte = x$1;
   }

   public void cats$kernel$instances$ByteInstances$_setter_$catsKernelStdGroupForByte_$eq(final CommutativeGroup x$1) {
      catsKernelStdGroupForByte = x$1;
   }

   private package$() {
   }
}
