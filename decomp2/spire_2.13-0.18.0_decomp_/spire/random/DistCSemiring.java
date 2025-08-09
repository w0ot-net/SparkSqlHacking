package spire.random;

import algebra.ring.CommutativeSemiring;
import java.lang.invoke.SerializedLambda;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\u001d3qAB\u0004\u0011\u0002\u0007\u0005A\u0002C\u00034\u0001\u0011\u0005A\u0007C\u00039\u0001\u0019\r\u0011\bC\u0003<\u0001\u0011\u0005A\bC\u0003>\u0001\u0011\u0005a\bC\u0003D\u0001\u0011\u0005AIA\u0007ESN$8iU3nSJLgn\u001a\u0006\u0003\u0011%\taA]1oI>l'\"\u0001\u0006\u0002\u000bM\u0004\u0018N]3\u0004\u0001U\u0011QBK\n\u0004\u00019!\u0002CA\b\u0013\u001b\u0005\u0001\"\"A\t\u0002\u000bM\u001c\u0017\r\\1\n\u0005M\u0001\"AB!osJ+g\rE\u0002\u0016C\u0011r!A\u0006\u0010\u000f\u0005]abB\u0001\r\u001c\u001b\u0005I\"B\u0001\u000e\f\u0003\u0019a$o\\8u}%\t!\"\u0003\u0002\u001e\u0013\u00059\u0011\r\\4fEJ\f\u0017BA\u0010!\u0003\u001d\u0001\u0018mY6bO\u0016T!!H\u0005\n\u0005\t\u001a#!C\"TK6L'/\u001b8h\u0015\ty\u0002\u0005E\u0002&M!j\u0011aB\u0005\u0003O\u001d\u0011A\u0001R5tiB\u0011\u0011F\u000b\u0007\u0001\t\u0015Y\u0003A1\u0001-\u0005\u0005\t\u0015CA\u00171!\tya&\u0003\u00020!\t9aj\u001c;iS:<\u0007CA\b2\u0013\t\u0011\u0004CA\u0002B]f\fa\u0001J5oSR$C#A\u001b\u0011\u0005=1\u0014BA\u001c\u0011\u0005\u0011)f.\u001b;\u0002\u0007\u0005dw-F\u0001;!\r)\u0012\u0005K\u0001\u0005u\u0016\u0014x.F\u0001%\u0003\u0011\u0001H.^:\u0015\u0007\u0011z\u0014\tC\u0003A\t\u0001\u0007A%A\u0001y\u0011\u0015\u0011E\u00011\u0001%\u0003\u0005I\u0018!\u0002;j[\u0016\u001cHc\u0001\u0013F\r\")\u0001)\u0002a\u0001I!)!)\u0002a\u0001I\u0001"
)
public interface DistCSemiring extends CommutativeSemiring {
   CommutativeSemiring alg();

   // $FF: synthetic method
   static Dist zero$(final DistCSemiring $this) {
      return $this.zero();
   }

   default Dist zero() {
      return Dist$.MODULE$.constant(this.alg().zero());
   }

   // $FF: synthetic method
   static Dist plus$(final DistCSemiring $this, final Dist x, final Dist y) {
      return $this.plus(x, y);
   }

   default Dist plus(final Dist x, final Dist y) {
      return new DistFromGen((g) -> this.alg().plus(x.apply(g), y.apply(g)));
   }

   // $FF: synthetic method
   static Dist times$(final DistCSemiring $this, final Dist x, final Dist y) {
      return $this.times(x, y);
   }

   default Dist times(final Dist x, final Dist y) {
      return new DistFromGen((g) -> this.alg().times(x.apply(g), y.apply(g)));
   }

   static void $init$(final DistCSemiring $this) {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
