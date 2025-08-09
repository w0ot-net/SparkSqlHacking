package spire.std;

import java.math.BigInteger;
import scala.reflect.ScalaSignature;
import spire.algebra.MetricSpace;

@ScalaSignature(
   bytes = "\u0006\u0005%2qa\u0001\u0003\u0011\u0002\u0007\u0005\u0011\u0002C\u0003\u001f\u0001\u0011\u0005q\u0004C\u0003$\u0001\u0011\u0005AEA\fCS\u001eLe\u000e^3hKJL5/T3ue&\u001c7\u000b]1dK*\u0011QAB\u0001\u0004gR$'\"A\u0004\u0002\u000bM\u0004\u0018N]3\u0004\u0001M\u0019\u0001A\u0003\t\u0011\u0005-qQ\"\u0001\u0007\u000b\u00035\tQa]2bY\u0006L!a\u0004\u0007\u0003\r\u0005s\u0017PU3g!\u0011\tBC\u0006\f\u000e\u0003IQ!a\u0005\u0004\u0002\u000f\u0005dw-\u001a2sC&\u0011QC\u0005\u0002\f\u001b\u0016$(/[2Ta\u0006\u001cW\r\u0005\u0002\u001895\t\u0001D\u0003\u0002\u001a5\u0005!Q.\u0019;i\u0015\u0005Y\u0012\u0001\u00026bm\u0006L!!\b\r\u0003\u0015\tKw-\u00138uK\u001e,'/\u0001\u0004%S:LG\u000f\n\u000b\u0002AA\u00111\"I\u0005\u0003E1\u0011A!\u00168ji\u0006AA-[:uC:\u001cW\rF\u0002\u0017K\u001dBQA\n\u0002A\u0002Y\t\u0011A\u001e\u0005\u0006Q\t\u0001\rAF\u0001\u0002o\u0002"
)
public interface BigIntegerIsMetricSpace extends MetricSpace {
   // $FF: synthetic method
   static BigInteger distance$(final BigIntegerIsMetricSpace $this, final BigInteger v, final BigInteger w) {
      return $this.distance(v, w);
   }

   default BigInteger distance(final BigInteger v, final BigInteger w) {
      return w.subtract(v).abs();
   }

   static void $init$(final BigIntegerIsMetricSpace $this) {
   }
}
