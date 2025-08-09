package spire.std;

import algebra.ring.EuclideanRing;
import java.math.BigInteger;
import scala.reflect.ScalaSignature;
import spire.math.NumberTag;

@ScalaSignature(
   bytes = "\u0006\u000553q\u0001B\u0003\u0011\u0002\u0007\u0005!\u0002C\u0003\u0012\u0001\u0011\u0005!\u0003C\u0004\u0017\u0001\t\u0007IqA\f\t\u000f\u0019\u0003!\u0019!C\u0004\u000f\n\u0019\")[4J]R,w-\u001a:J]N$\u0018M\\2fg*\u0011aaB\u0001\u0004gR$'\"\u0001\u0005\u0002\u000bM\u0004\u0018N]3\u0004\u0001M\u0011\u0001a\u0003\t\u0003\u0019=i\u0011!\u0004\u0006\u0002\u001d\u0005)1oY1mC&\u0011\u0001#\u0004\u0002\u0007\u0003:L(+\u001a4\u0002\r\u0011Jg.\u001b;%)\u0005\u0019\u0002C\u0001\u0007\u0015\u0013\t)RB\u0001\u0003V]&$\u0018!\u0005\"jO&sG/Z4fe\u0006cw-\u001a2sCV\t\u0001D\u0005\u0005\u001a7M:$(\u0010!D\r\u0011Q\u0002\u0001\u0001\r\u0003\u0019q\u0012XMZ5oK6,g\u000e\u001e \u0011\u0007qA3F\u0004\u0002\u001eK9\u0011ad\t\b\u0003?\tj\u0011\u0001\t\u0006\u0003C%\ta\u0001\u0010:p_Rt\u0014\"\u0001\u0005\n\u0005\u0011:\u0011aB1mO\u0016\u0014'/Y\u0005\u0003M\u001d\nq\u0001]1dW\u0006<WM\u0003\u0002%\u000f%\u0011\u0011F\u000b\u0002\u000e\u000bV\u001cG.\u001b3fC:\u0014\u0016N\\4\u000b\u0005\u0019:\u0003C\u0001\u00172\u001b\u0005i#B\u0001\u00180\u0003\u0011i\u0017\r\u001e5\u000b\u0003A\nAA[1wC&\u0011!'\f\u0002\u000b\u0005&<\u0017J\u001c;fO\u0016\u0014\bc\u0001\u001b6W5\tq%\u0003\u00027O\t)aJU8piB!A\u0007O\u0016,\u0013\tItEA\u0006NKR\u0014\u0018nY*qC\u000e,\u0007c\u0001\u001b<W%\u0011Ah\n\u0002\u000b\u0013NLe\u000e^3he\u0006d\u0007c\u0001\u000f?W%\u0011qH\u000b\u0002\u0017)J,hnY1uK\u0012$\u0015N^5tS>t7IU5oOB\u0019A$Q\u0016\n\u0005\tS#AB*jO:,G\rE\u0002\u001d\t.J!!\u0012\u0016\u0003\u000b=\u0013H-\u001a:\u0002\u001b\tKw-\u00138uK\u001e,'\u000fV1h+\u0005A\u0005cA%LW5\t!J\u0003\u0002/\u000f%\u0011AJ\u0013\u0002\n\u001dVl'-\u001a:UC\u001e\u0004"
)
public interface BigIntegerInstances {
   void spire$std$BigIntegerInstances$_setter_$BigIntegerAlgebra_$eq(final EuclideanRing x$1);

   void spire$std$BigIntegerInstances$_setter_$BigIntegerTag_$eq(final NumberTag x$1);

   EuclideanRing BigIntegerAlgebra();

   NumberTag BigIntegerTag();

   static void $init$(final BigIntegerInstances $this) {
      $this.spire$std$BigIntegerInstances$_setter_$BigIntegerAlgebra_$eq(new BigIntegerAlgebra());
      $this.spire$std$BigIntegerInstances$_setter_$BigIntegerTag_$eq(new NumberTag.LargeTag(NumberTag.Integral$.MODULE$, BigInteger.ZERO));
   }
}
