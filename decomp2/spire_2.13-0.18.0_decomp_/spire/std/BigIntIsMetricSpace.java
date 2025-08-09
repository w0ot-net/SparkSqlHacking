package spire.std;

import scala.math.BigInt;
import scala.reflect.ScalaSignature;
import spire.algebra.MetricSpace;

@ScalaSignature(
   bytes = "\u0006\u000552qa\u0001\u0003\u0011\u0002\u0007\u0005\u0011\u0002C\u0003#\u0001\u0011\u00051\u0005C\u0003(\u0001\u0011\u0005\u0001FA\nCS\u001eLe\u000e^%t\u001b\u0016$(/[2Ta\u0006\u001cWM\u0003\u0002\u0006\r\u0005\u00191\u000f\u001e3\u000b\u0003\u001d\tQa\u001d9je\u0016\u001c\u0001aE\u0002\u0001\u0015A\u0001\"a\u0003\b\u000e\u00031Q\u0011!D\u0001\u0006g\u000e\fG.Y\u0005\u0003\u001f1\u0011a!\u00118z%\u00164\u0007\u0003B\t\u0015-Yi\u0011A\u0005\u0006\u0003'\u0019\tq!\u00197hK\n\u0014\u0018-\u0003\u0002\u0016%\tYQ*\u001a;sS\u000e\u001c\u0006/Y2f!\t9rD\u0004\u0002\u0019;9\u0011\u0011\u0004H\u0007\u00025)\u00111\u0004C\u0001\u0007yI|w\u000e\u001e \n\u00035I!A\b\u0007\u0002\u000fA\f7m[1hK&\u0011\u0001%\t\u0002\u0007\u0005&<\u0017J\u001c;\u000b\u0005ya\u0011A\u0002\u0013j]&$H\u0005F\u0001%!\tYQ%\u0003\u0002'\u0019\t!QK\\5u\u0003!!\u0017n\u001d;b]\u000e,Gc\u0001\f*W!)!F\u0001a\u0001-\u0005\ta\u000fC\u0003-\u0005\u0001\u0007a#A\u0001x\u0001"
)
public interface BigIntIsMetricSpace extends MetricSpace {
   // $FF: synthetic method
   static BigInt distance$(final BigIntIsMetricSpace $this, final BigInt v, final BigInt w) {
      return $this.distance(v, w);
   }

   default BigInt distance(final BigInt v, final BigInt w) {
      return w.$minus(v).abs();
   }

   static void $init$(final BigIntIsMetricSpace $this) {
   }
}
