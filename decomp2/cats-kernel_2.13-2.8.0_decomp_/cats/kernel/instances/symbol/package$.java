package cats.kernel.instances.symbol;

import cats.kernel.Order;
import cats.kernel.instances.SymbolInstances;

public final class package$ implements SymbolInstances {
   public static final package$ MODULE$ = new package$();
   private static Order catsKernelStdOrderForSymbol;

   static {
      SymbolInstances.$init$(MODULE$);
   }

   public Order catsKernelStdOrderForSymbol() {
      return catsKernelStdOrderForSymbol;
   }

   public void cats$kernel$instances$SymbolInstances$_setter_$catsKernelStdOrderForSymbol_$eq(final Order x$1) {
      catsKernelStdOrderForSymbol = x$1;
   }

   private package$() {
   }
}
