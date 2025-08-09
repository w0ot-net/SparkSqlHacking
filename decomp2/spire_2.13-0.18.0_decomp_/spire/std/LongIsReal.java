package spire.std;

import scala.math.BigInt;
import scala.package.;
import scala.reflect.ScalaSignature;
import spire.algebra.IsIntegral;

@ScalaSignature(
   bytes = "\u0006\u0005e2q\u0001B\u0003\u0011\u0002\u0007\u0005!\u0002C\u0003\u001f\u0001\u0011\u0005q\u0004C\u0003$\u0001\u0011\u0005A\u0005C\u0003+\u0001\u0011\u00051F\u0001\u0006M_:<\u0017j\u001d*fC2T!AB\u0004\u0002\u0007M$HMC\u0001\t\u0003\u0015\u0019\b/\u001b:f\u0007\u0001\u0019B\u0001A\u0006\u00125A\u0011AbD\u0007\u0002\u001b)\ta\"A\u0003tG\u0006d\u0017-\u0003\u0002\u0011\u001b\t1\u0011I\\=SK\u001a\u00042AE\u000b\u0018\u001b\u0005\u0019\"B\u0001\u000b\b\u0003\u001d\tGnZ3ce\u0006L!AF\n\u0003\u0015%\u001b\u0018J\u001c;fOJ\fG\u000e\u0005\u0002\r1%\u0011\u0011$\u0004\u0002\u0005\u0019>tw\r\u0005\u0002\u001c95\tQ!\u0003\u0002\u001e\u000b\t)Bj\u001c8h)J,hnY1uK\u0012$\u0015N^5tS>t\u0017A\u0002\u0013j]&$H\u0005F\u0001!!\ta\u0011%\u0003\u0002#\u001b\t!QK\\5u\u0003!!x\u000eR8vE2,GCA\u0013)!\taa%\u0003\u0002(\u001b\t1Ai\\;cY\u0016DQ!\u000b\u0002A\u0002]\t\u0011A\\\u0001\ti>\u0014\u0015nZ%oiR\u0011A\u0006\u000f\t\u0003[Ur!AL\u001a\u000f\u0005=\u0012T\"\u0001\u0019\u000b\u0005EJ\u0011A\u0002\u001fs_>$h(C\u0001\u000f\u0013\t!T\"A\u0004qC\u000e\\\u0017mZ3\n\u0005Y:$A\u0002\"jO&sGO\u0003\u00025\u001b!)\u0011f\u0001a\u0001/\u0001"
)
public interface LongIsReal extends IsIntegral, LongTruncatedDivision {
   // $FF: synthetic method
   static double toDouble$(final LongIsReal $this, final long n) {
      return $this.toDouble(n);
   }

   default double toDouble(final long n) {
      return this.toDouble$mcJ$sp(n);
   }

   // $FF: synthetic method
   static BigInt toBigInt$(final LongIsReal $this, final long n) {
      return $this.toBigInt(n);
   }

   default BigInt toBigInt(final long n) {
      return .MODULE$.BigInt().apply(n);
   }

   // $FF: synthetic method
   static double toDouble$mcJ$sp$(final LongIsReal $this, final long n) {
      return $this.toDouble$mcJ$sp(n);
   }

   default double toDouble$mcJ$sp(final long n) {
      return (double)n;
   }

   static void $init$(final LongIsReal $this) {
   }
}
