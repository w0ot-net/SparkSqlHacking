package spire.std;

import scala.reflect.ScalaSignature;
import spire.algebra.MetricSpace;

@ScalaSignature(
   bytes = "\u0006\u0005-2qa\u0001\u0003\u0011\u0002\u0007\u0005\u0011\u0002C\u0003\u0011\u0001\u0011\u0005\u0011\u0003C\u0003\u0016\u0001\u0011\raC\u0001\tTiJLgnZ%ogR\fgnY3ta)\u0011QAB\u0001\u0004gR$'\"A\u0004\u0002\u000bM\u0004\u0018N]3\u0004\u0001M\u0011\u0001A\u0003\t\u0003\u00179i\u0011\u0001\u0004\u0006\u0002\u001b\u0005)1oY1mC&\u0011q\u0002\u0004\u0002\u0007\u0003:L(+\u001a4\u0002\r\u0011Jg.\u001b;%)\u0005\u0011\u0002CA\u0006\u0014\u0013\t!BB\u0001\u0003V]&$\u0018a\u00057fm\u0016t7\u000f\u001b;fS:$\u0015n\u001d;b]\u000e,W#A\f\u0011\taYR\u0004K\u0007\u00023)\u0011!DB\u0001\bC2<WM\u0019:b\u0013\ta\u0012DA\u0006NKR\u0014\u0018nY*qC\u000e,\u0007C\u0001\u0010&\u001d\ty2\u0005\u0005\u0002!\u00195\t\u0011E\u0003\u0002#\u0011\u00051AH]8pizJ!\u0001\n\u0007\u0002\rA\u0013X\rZ3g\u0013\t1sE\u0001\u0004TiJLgn\u001a\u0006\u0003I1\u0001\"aC\u0015\n\u0005)b!aA%oi\u0002"
)
public interface StringInstances0 {
   // $FF: synthetic method
   static MetricSpace levenshteinDistance$(final StringInstances0 $this) {
      return $this.levenshteinDistance();
   }

   default MetricSpace levenshteinDistance() {
      return LevenshteinDistance$.MODULE$;
   }

   static void $init$(final StringInstances0 $this) {
   }
}
