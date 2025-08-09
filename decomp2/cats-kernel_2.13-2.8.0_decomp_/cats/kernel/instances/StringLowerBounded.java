package cats.kernel.instances;

import cats.kernel.LowerBounded;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005!2qa\u0001\u0003\u0011\u0002\u0007\u00051\u0002C\u0003\"\u0001\u0011\u0005!\u0005C\u0003'\u0001\u0011\u0005sE\u0001\nTiJLgn\u001a'po\u0016\u0014(i\\;oI\u0016$'BA\u0003\u0007\u0003%Ign\u001d;b]\u000e,7O\u0003\u0002\b\u0011\u000511.\u001a:oK2T\u0011!C\u0001\u0005G\u0006$8o\u0001\u0001\u0014\u0007\u0001a!\u0003\u0005\u0002\u000e!5\taBC\u0001\u0010\u0003\u0015\u00198-\u00197b\u0013\t\tbB\u0001\u0004B]f\u0014VM\u001a\t\u0004'Q1R\"\u0001\u0004\n\u0005U1!\u0001\u0004'po\u0016\u0014(i\\;oI\u0016$\u0007CA\f\u001f\u001d\tAB\u0004\u0005\u0002\u001a\u001d5\t!D\u0003\u0002\u001c\u0015\u00051AH]8pizJ!!\b\b\u0002\rA\u0013X\rZ3g\u0013\ty\u0002E\u0001\u0004TiJLgn\u001a\u0006\u0003;9\ta\u0001J5oSR$C#A\u0012\u0011\u00055!\u0013BA\u0013\u000f\u0005\u0011)f.\u001b;\u0002\u00115LgNQ8v]\u0012,\u0012A\u0006"
)
public interface StringLowerBounded extends LowerBounded {
   // $FF: synthetic method
   static String minBound$(final StringLowerBounded $this) {
      return $this.minBound();
   }

   default String minBound() {
      return "";
   }

   static void $init$(final StringLowerBounded $this) {
   }
}
