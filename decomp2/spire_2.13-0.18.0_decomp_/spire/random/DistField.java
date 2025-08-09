package spire.random;

import algebra.ring.EuclideanRing;
import algebra.ring.Field;
import java.lang.invoke.SerializedLambda;
import scala.Tuple2;
import scala.math.BigInt;
import scala.reflect.ScalaSignature;
import scala.sys.package.;

@ScalaSignature(
   bytes = "\u0006\u0005\u00114q!\u0003\u0006\u0011\u0002\u0007\u0005q\u0002C\u0003:\u0001\u0011\u0005!\bC\u0003?\u0001\u0019\u0005q\bC\u0003B\u0001\u0011\u0005!\tC\u0003H\u0001\u0011\u0005\u0003\nC\u0003L\u0001\u0011\u0005C\nC\u0003P\u0001\u0011\u0005\u0003\u000bC\u0003W\u0001\u0011\u0005s\u000bC\u0003Z\u0001\u0011\u0005#LA\u0005ESN$h)[3mI*\u00111\u0002D\u0001\u0007e\u0006tGm\\7\u000b\u00035\tQa\u001d9je\u0016\u001c\u0001!\u0006\u0002\u0011;M!\u0001!E\f'!\t\u0011R#D\u0001\u0014\u0015\u0005!\u0012!B:dC2\f\u0017B\u0001\f\u0014\u0005\u0019\te.\u001f*fMB\u0019\u0001$G\u000e\u000e\u0003)I!A\u0007\u0006\u0003#\u0011K7\u000f^#vG2LG-Z1o%&tw\r\u0005\u0002\u001d;1\u0001A!\u0002\u0010\u0001\u0005\u0004y\"!A!\u0012\u0005\u0001\u001a\u0003C\u0001\n\"\u0013\t\u00113CA\u0004O_RD\u0017N\\4\u0011\u0005I!\u0013BA\u0013\u0014\u0005\r\te.\u001f\t\u0004OM2dB\u0001\u00151\u001d\tIcF\u0004\u0002+[5\t1F\u0003\u0002-\u001d\u00051AH]8pizJ\u0011!D\u0005\u0003_1\tq!\u00197hK\n\u0014\u0018-\u0003\u00022e\u00059\u0001/Y2lC\u001e,'BA\u0018\r\u0013\t!TGA\u0003GS\u0016dGM\u0003\u00022eA\u0019\u0001dN\u000e\n\u0005aR!\u0001\u0002#jgR\fa\u0001J5oSR$C#A\u001e\u0011\u0005Ia\u0014BA\u001f\u0014\u0005\u0011)f.\u001b;\u0002\u0007\u0005dw-F\u0001A!\r93gG\u0001\u0004I&4Hc\u0001\u001cD\u000b\")Ai\u0001a\u0001m\u0005\t\u0001\u0010C\u0003G\u0007\u0001\u0007a'A\u0001z\u0003\u0015)\u0017/^8u)\r1\u0014J\u0013\u0005\u0006\t\u0012\u0001\rA\u000e\u0005\u0006\r\u0012\u0001\rAN\u0001\u0005K6|G\rF\u00027\u001b:CQ\u0001R\u0003A\u0002YBQAR\u0003A\u0002Y\n\u0001\"Z9v_Rlw\u000e\u001a\u000b\u0004#R+\u0006\u0003\u0002\nSmYJ!aU\n\u0003\rQ+\b\u000f\\33\u0011\u0015!e\u00011\u00017\u0011\u00151e\u00011\u00017\u0003)\u0011XmY5qe>\u001c\u0017\r\u001c\u000b\u0003maCQ\u0001R\u0004A\u0002Y\n\u0011#Z;dY&$W-\u00198Gk:\u001cG/[8o)\tY6\r\u0005\u0002]A:\u0011Ql\u0018\b\u0003UyK\u0011\u0001F\u0005\u0003cMI!!\u00192\u0003\r\tKw-\u00138u\u0015\t\t4\u0003C\u0003E\u0011\u0001\u0007a\u0007"
)
public interface DistField extends DistEuclideanRing, Field {
   Field alg();

   // $FF: synthetic method
   static Dist div$(final DistField $this, final Dist x, final Dist y) {
      return $this.div(x, y);
   }

   default Dist div(final Dist x, final Dist y) {
      return new DistFromGen((g) -> this.alg().div(x.apply(g), y.apply(g)));
   }

   // $FF: synthetic method
   static Dist equot$(final DistField $this, final Dist x, final Dist y) {
      return $this.equot(x, y);
   }

   default Dist equot(final Dist x, final Dist y) {
      return DistEuclideanRing.equot$(this, x, y);
   }

   // $FF: synthetic method
   static Dist emod$(final DistField $this, final Dist x, final Dist y) {
      return $this.emod(x, y);
   }

   default Dist emod(final Dist x, final Dist y) {
      return DistEuclideanRing.emod$(this, x, y);
   }

   // $FF: synthetic method
   static Tuple2 equotmod$(final DistField $this, final Dist x, final Dist y) {
      return $this.equotmod(x, y);
   }

   default Tuple2 equotmod(final Dist x, final Dist y) {
      return EuclideanRing.equotmod$(this, x, y);
   }

   // $FF: synthetic method
   static Dist reciprocal$(final DistField $this, final Dist x) {
      return $this.reciprocal(x);
   }

   default Dist reciprocal(final Dist x) {
      return new DistFromGen((g) -> this.alg().reciprocal(x.apply(g)));
   }

   // $FF: synthetic method
   static BigInt euclideanFunction$(final DistField $this, final Dist x) {
      return $this.euclideanFunction(x);
   }

   default BigInt euclideanFunction(final Dist x) {
      throw .MODULE$.error("euclideanFunction is not defined, as Dist is a monad, and euclideanFunction should return Dist[BigInt]");
   }

   static void $init$(final DistField $this) {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
