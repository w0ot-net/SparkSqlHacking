package spire.random;

import algebra.ring.Field;
import cats.kernel.Eq;
import java.lang.invoke.SerializedLambda;
import scala.reflect.ScalaSignature;
import spire.algebra.VectorSpace;

@ScalaSignature(
   bytes = "\u0006\u0005M3qAB\u0004\u0011\u0002\u0007\u0005A\u0002C\u00031\u0001\u0011\u0005\u0011\u0007C\u00036\u0001\u0019\ra\u0007C\u00039\u0001\u0019\r\u0011\bC\u0003I\u0001\u0011\u0005\u0013\nC\u0003N\u0001\u0011\u0005cJA\bESN$h+Z2u_J\u001c\u0006/Y2f\u0015\tA\u0011\"\u0001\u0004sC:$w.\u001c\u0006\u0002\u0015\u0005)1\u000f]5sK\u000e\u0001QcA\u0007\u001bIM!\u0001A\u0004\u000b'!\ty!#D\u0001\u0011\u0015\u0005\t\u0012!B:dC2\f\u0017BA\n\u0011\u0005\u0019\te.\u001f*fMB!QC\u0006\r$\u001b\u00059\u0011BA\f\b\u0005-!\u0015n\u001d;D\u001b>$W\u000f\\3\u0011\u0005eQB\u0002\u0001\u0003\u00067\u0001\u0011\r\u0001\b\u0002\u0002-F\u0011Q\u0004\t\t\u0003\u001fyI!a\b\t\u0003\u000f9{G\u000f[5oOB\u0011q\"I\u0005\u0003EA\u00111!\u00118z!\tIB\u0005B\u0003&\u0001\t\u0007ADA\u0001L!\u00119#\u0006L\u0018\u000e\u0003!R!!K\u0005\u0002\u000f\u0005dw-\u001a2sC&\u00111\u0006\u000b\u0002\f-\u0016\u001cGo\u001c:Ta\u0006\u001cW\rE\u0002\u0016[aI!AL\u0004\u0003\t\u0011K7\u000f\u001e\t\u0004+5\u001a\u0013A\u0002\u0013j]&$H\u0005F\u00013!\ty1'\u0003\u00025!\t!QK\\5u\u0003\r\tGnZ\u000b\u0002oA!qE\u000b\r$\u0003\r)\u0017oS\u000b\u0002uA\u00191(R\u0012\u000f\u0005q\u001aeBA\u001fC\u001d\tq\u0014)D\u0001@\u0015\t\u00015\"\u0001\u0004=e>|GOP\u0005\u0002\u0015%\u0011\u0011&C\u0005\u0003\t\"\nq\u0001]1dW\u0006<W-\u0003\u0002G\u000f\n\u0011Q)\u001d\u0006\u0003\t\"\naa]2bY\u0006\u0014X#\u0001&\u0011\u0007mZu&\u0003\u0002M\u000f\n)a)[3mI\u0006!A-\u001b<s)\ras*\u0015\u0005\u0006!\u0016\u0001\r\u0001L\u0001\u0002m\")!+\u0002a\u0001_\u0005\t1\u000e"
)
public interface DistVectorSpace extends DistCModule, VectorSpace {
   VectorSpace alg();

   Eq eqK();

   // $FF: synthetic method
   static Field scalar$(final DistVectorSpace $this) {
      return $this.scalar();
   }

   default Field scalar() {
      return Dist$.MODULE$.field(this.eqK(), this.alg().scalar());
   }

   // $FF: synthetic method
   static Dist divr$(final DistVectorSpace $this, final Dist v, final Dist k) {
      return $this.divr(v, k);
   }

   default Dist divr(final Dist v, final Dist k) {
      return new DistFromGen((g) -> this.alg().divr(v.apply(g), k.apply(g)));
   }

   static void $init$(final DistVectorSpace $this) {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return var0.lambdaDeserialize<invokedynamic>(var0);
   }
}
