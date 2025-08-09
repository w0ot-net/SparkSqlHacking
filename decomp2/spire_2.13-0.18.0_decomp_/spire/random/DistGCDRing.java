package spire.random;

import algebra.ring.GCDRing;
import cats.kernel.Eq;
import java.lang.invoke.SerializedLambda;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005M3qAB\u0004\u0011\u0002\u0007\u0005A\u0002C\u00037\u0001\u0011\u0005q\u0007C\u0003<\u0001\u0019\rA\bC\u0003A\u0001\u0019\u0005\u0011\tC\u0003D\u0001\u0011\u0005A\tC\u0003N\u0001\u0011\u0005aJA\u0006ESN$xi\u0011#SS:<'B\u0001\u0005\n\u0003\u0019\u0011\u0018M\u001c3p[*\t!\"A\u0003ta&\u0014Xm\u0001\u0001\u0016\u00055Q2\u0003\u0002\u0001\u000f)\r\u0002\"a\u0004\n\u000e\u0003AQ\u0011!E\u0001\u0006g\u000e\fG.Y\u0005\u0003'A\u0011a!\u00118z%\u00164\u0007cA\u000b\u001715\tq!\u0003\u0002\u0018\u000f\tIA)[:u\u0007JKgn\u001a\t\u00033ia\u0001\u0001B\u0003\u001c\u0001\t\u0007ADA\u0001B#\ti\u0002\u0005\u0005\u0002\u0010=%\u0011q\u0004\u0005\u0002\b\u001d>$\b.\u001b8h!\ty\u0011%\u0003\u0002#!\t\u0019\u0011I\\=\u0011\u0007\u0011\u00024G\u0004\u0002&[9\u0011ae\u000b\b\u0003O)j\u0011\u0001\u000b\u0006\u0003S-\ta\u0001\u0010:p_Rt\u0014\"\u0001\u0006\n\u00051J\u0011aB1mO\u0016\u0014'/Y\u0005\u0003]=\nq\u0001]1dW\u0006<WM\u0003\u0002-\u0013%\u0011\u0011G\r\u0002\b\u000f\u000e#%+\u001b8h\u0015\tqs\u0006E\u0002\u0016iaI!!N\u0004\u0003\t\u0011K7\u000f^\u0001\u0007I%t\u0017\u000e\u001e\u0013\u0015\u0003a\u0002\"aD\u001d\n\u0005i\u0002\"\u0001B+oSR\f1!Z9B+\u0005i\u0004c\u0001\u0013?1%\u0011qH\r\u0002\u0003\u000bF\f1!\u00197h+\u0005\u0011\u0005c\u0001\u001311\u0005\u0019qm\u00193\u0015\u0007\u0015K5\n\u0006\u00024\r\")q\t\u0002a\u0002\u0011\u0006\u0011QM\u001e\t\u0004Iy\u001a\u0004\"\u0002&\u0005\u0001\u0004\u0019\u0014!\u0001=\t\u000b1#\u0001\u0019A\u001a\u0002\u0003e\f1\u0001\\2n)\ry\u0015K\u0015\u000b\u0003gACQaR\u0003A\u0004!CQAS\u0003A\u0002MBQ\u0001T\u0003A\u0002M\u0002"
)
public interface DistGCDRing extends DistCRing, GCDRing {
   Eq eqA();

   GCDRing alg();

   // $FF: synthetic method
   static Dist gcd$(final DistGCDRing $this, final Dist x, final Dist y, final Eq ev) {
      return $this.gcd(x, y, ev);
   }

   default Dist gcd(final Dist x, final Dist y, final Eq ev) {
      return new DistFromGen((g) -> this.alg().gcd(x.apply(g), y.apply(g), this.eqA()));
   }

   // $FF: synthetic method
   static Dist lcm$(final DistGCDRing $this, final Dist x, final Dist y, final Eq ev) {
      return $this.lcm(x, y, ev);
   }

   default Dist lcm(final Dist x, final Dist y, final Eq ev) {
      return new DistFromGen((g) -> this.alg().lcm(x.apply(g), y.apply(g), this.eqA()));
   }

   static void $init$(final DistGCDRing $this) {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
