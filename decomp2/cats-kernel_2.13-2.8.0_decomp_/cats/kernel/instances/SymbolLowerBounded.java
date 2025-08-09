package cats.kernel.instances;

import cats.kernel.LowerBounded;
import scala.Symbol;
import scala.Symbol.;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\u00012qa\u0001\u0003\u0011\u0002\u0007\u00051\u0002C\u0003\u001a\u0001\u0011\u0005!\u0004C\u0003\u001f\u0001\u0011\u0005sD\u0001\nTs6\u0014w\u000e\u001c'po\u0016\u0014(i\\;oI\u0016$'BA\u0003\u0007\u0003%Ign\u001d;b]\u000e,7O\u0003\u0002\b\u0011\u000511.\u001a:oK2T\u0011!C\u0001\u0005G\u0006$8o\u0001\u0001\u0014\u0007\u0001a!\u0003\u0005\u0002\u000e!5\taBC\u0001\u0010\u0003\u0015\u00198-\u00197b\u0013\t\tbB\u0001\u0004B]f\u0014VM\u001a\t\u0004'Q1R\"\u0001\u0004\n\u0005U1!\u0001\u0004'po\u0016\u0014(i\\;oI\u0016$\u0007CA\u0007\u0018\u0013\tAbB\u0001\u0004Ts6\u0014w\u000e\\\u0001\u0007I%t\u0017\u000e\u001e\u0013\u0015\u0003m\u0001\"!\u0004\u000f\n\u0005uq!\u0001B+oSR\f\u0001\"\\5o\u0005>,h\u000eZ\u000b\u0002-\u0001"
)
public interface SymbolLowerBounded extends LowerBounded {
   // $FF: synthetic method
   static Symbol minBound$(final SymbolLowerBounded $this) {
      return $this.minBound();
   }

   default Symbol minBound() {
      return .MODULE$.apply("");
   }

   static void $init$(final SymbolLowerBounded $this) {
   }
}
