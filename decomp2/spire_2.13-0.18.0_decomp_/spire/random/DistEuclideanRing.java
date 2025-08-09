package spire.random;

import algebra.ring.EuclideanRing;
import cats.kernel.Eq;
import java.lang.invoke.SerializedLambda;
import scala.math.BigInt;
import scala.reflect.ScalaSignature;
import scala.sys.package.;

@ScalaSignature(
   bytes = "\u0006\u0005\u00154q\u0001C\u0005\u0011\u0002\u0007\u0005a\u0002C\u00039\u0001\u0011\u0005\u0011\bC\u0003>\u0001\u0019\u0005a\bC\u0003A\u0001\u0011\u0005\u0013\tC\u0003M\u0001\u0011\u0005Q\nC\u0003R\u0001\u0011\u0005!\u000bC\u0003V\u0001\u0011\u0005c\u000bC\u0003`\u0001\u0011\u0005\u0003MA\tESN$X)^2mS\u0012,\u0017M\u001c*j]\u001eT!AC\u0006\u0002\rI\fg\u000eZ8n\u0015\u0005a\u0011!B:qSJ,7\u0001A\u000b\u0003\u001fq\u0019B\u0001\u0001\t\u0017KA\u0011\u0011\u0003F\u0007\u0002%)\t1#A\u0003tG\u0006d\u0017-\u0003\u0002\u0016%\t1\u0011I\\=SK\u001a\u00042a\u0006\r\u001b\u001b\u0005I\u0011BA\r\n\u0005-!\u0015n\u001d;H\u0007\u0012\u0013\u0016N\\4\u0011\u0005maB\u0002\u0001\u0003\u0006;\u0001\u0011\rA\b\u0002\u0002\u0003F\u0011qD\t\t\u0003#\u0001J!!\t\n\u0003\u000f9{G\u000f[5oOB\u0011\u0011cI\u0005\u0003II\u00111!\u00118z!\r1#'\u000e\b\u0003O=r!\u0001K\u0017\u000f\u0005%bS\"\u0001\u0016\u000b\u0005-j\u0011A\u0002\u001fs_>$h(C\u0001\r\u0013\tq3\"A\u0004bY\u001e,'M]1\n\u0005A\n\u0014a\u00029bG.\fw-\u001a\u0006\u0003]-I!a\r\u001b\u0003\u001b\u0015+8\r\\5eK\u0006t'+\u001b8h\u0015\t\u0001\u0014\u0007E\u0002\u0018miI!aN\u0005\u0003\t\u0011K7\u000f^\u0001\u0007I%t\u0017\u000e\u001e\u0013\u0015\u0003i\u0002\"!E\u001e\n\u0005q\u0012\"\u0001B+oSR\f1!\u00197h+\u0005y\u0004c\u0001\u001435\u0005\tR-^2mS\u0012,\u0017M\u001c$v]\u000e$\u0018n\u001c8\u0015\u0005\tS\u0005CA\"H\u001d\t!eI\u0004\u0002*\u000b&\t1#\u0003\u00021%%\u0011\u0001*\u0013\u0002\u0007\u0005&<\u0017J\u001c;\u000b\u0005A\u0012\u0002\"B&\u0004\u0001\u0004)\u0014!\u0001=\u0002\u000b\u0015\fXo\u001c;\u0015\u0007Uru\nC\u0003L\t\u0001\u0007Q\u0007C\u0003Q\t\u0001\u0007Q'A\u0001z\u0003\u0011)Wn\u001c3\u0015\u0007U\u001aF\u000bC\u0003L\u000b\u0001\u0007Q\u0007C\u0003Q\u000b\u0001\u0007Q'A\u0002hG\u0012$2aV/_)\t)\u0004\fC\u0003Z\r\u0001\u000f!,\u0001\u0002fmB\u0019aeW\u001b\n\u0005q#$AA#r\u0011\u0015Ye\u00011\u00016\u0011\u0015\u0001f\u00011\u00016\u0003\ra7-\u001c\u000b\u0004C\u000e$GCA\u001bc\u0011\u0015Iv\u0001q\u0001[\u0011\u0015Yu\u00011\u00016\u0011\u0015\u0001v\u00011\u00016\u0001"
)
public interface DistEuclideanRing extends DistGCDRing, EuclideanRing {
   EuclideanRing alg();

   // $FF: synthetic method
   static BigInt euclideanFunction$(final DistEuclideanRing $this, final Dist x) {
      return $this.euclideanFunction(x);
   }

   default BigInt euclideanFunction(final Dist x) {
      throw .MODULE$.error("euclideanFunction is not defined, as Dist is a monad, and euclideanFunction should return Dist[BigInt]");
   }

   // $FF: synthetic method
   static Dist equot$(final DistEuclideanRing $this, final Dist x, final Dist y) {
      return $this.equot(x, y);
   }

   default Dist equot(final Dist x, final Dist y) {
      return new DistFromGen((g) -> this.alg().equot(x.apply(g), y.apply(g)));
   }

   // $FF: synthetic method
   static Dist emod$(final DistEuclideanRing $this, final Dist x, final Dist y) {
      return $this.emod(x, y);
   }

   default Dist emod(final Dist x, final Dist y) {
      return new DistFromGen((g) -> this.alg().emod(x.apply(g), y.apply(g)));
   }

   // $FF: synthetic method
   static Dist gcd$(final DistEuclideanRing $this, final Dist x, final Dist y, final Eq ev) {
      return $this.gcd(x, y, ev);
   }

   default Dist gcd(final Dist x, final Dist y, final Eq ev) {
      return DistGCDRing.gcd$(this, x, y, ev);
   }

   // $FF: synthetic method
   static Dist lcm$(final DistEuclideanRing $this, final Dist x, final Dist y, final Eq ev) {
      return $this.lcm(x, y, ev);
   }

   default Dist lcm(final Dist x, final Dist y, final Eq ev) {
      return DistGCDRing.lcm$(this, x, y, ev);
   }

   static void $init$(final DistEuclideanRing $this) {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
