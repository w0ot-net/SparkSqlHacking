package cats.kernel.instances;

import cats.kernel.Order;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005%2qa\u0001\u0003\u0011\u0002\u0007\u00051\u0002C\u0003\u0013\u0001\u0011\u00051\u0003C\u0004\u0018\u0001\t\u0007I1\u0001\r\u0003\u001fMKXNY8m\u0013:\u001cH/\u00198dKNT!!\u0002\u0004\u0002\u0013%t7\u000f^1oG\u0016\u001c(BA\u0004\t\u0003\u0019YWM\u001d8fY*\t\u0011\"\u0001\u0003dCR\u001c8\u0001A\n\u0003\u00011\u0001\"!\u0004\t\u000e\u00039Q\u0011aD\u0001\u0006g\u000e\fG.Y\u0005\u0003#9\u0011a!\u00118z%\u00164\u0017A\u0002\u0013j]&$H\u0005F\u0001\u0015!\tiQ#\u0003\u0002\u0017\u001d\t!QK\\5u\u0003m\u0019\u0017\r^:LKJtW\r\\*uI>\u0013H-\u001a:G_J\u001c\u00160\u001c2pYV\t\u0011D\u0005\u0003\u001b9\r2c\u0001B\u000e\u0001\u0001e\u0011A\u0002\u0010:fM&tW-\\3oiz\u00022!\b\u0010!\u001b\u00051\u0011BA\u0010\u0007\u0005\u0015y%\u000fZ3s!\ti\u0011%\u0003\u0002#\u001d\t11+_7c_2\u00042!\b\u0013!\u0013\t)cA\u0001\u0003ICND\u0007cA\u000f(A%\u0011\u0001F\u0002\u0002\r\u0019><XM\u001d\"pk:$W\r\u001a"
)
public interface SymbolInstances {
   void cats$kernel$instances$SymbolInstances$_setter_$catsKernelStdOrderForSymbol_$eq(final Order x$1);

   Order catsKernelStdOrderForSymbol();

   static void $init$(final SymbolInstances $this) {
      $this.cats$kernel$instances$SymbolInstances$_setter_$catsKernelStdOrderForSymbol_$eq(new SymbolOrder());
   }
}
