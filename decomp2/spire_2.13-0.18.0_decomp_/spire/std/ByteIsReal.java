package spire.std;

import scala.math.BigInt;
import scala.package.;
import scala.reflect.ScalaSignature;
import spire.algebra.IsIntegral;

@ScalaSignature(
   bytes = "\u0006\u0005q2q\u0001B\u0003\u0011\u0002\u0007\u0005!\u0002C\u0003\"\u0001\u0011\u0005!\u0005C\u0003'\u0001\u0011\u0005q\u0005C\u0003.\u0001\u0011\u0005aF\u0001\u0006CsR,\u0017j\u001d*fC2T!AB\u0004\u0002\u0007M$HMC\u0001\t\u0003\u0015\u0019\b/\u001b:f\u0007\u0001\u0019R\u0001A\u0006\u00125y\u0001\"\u0001D\b\u000e\u00035Q\u0011AD\u0001\u0006g\u000e\fG.Y\u0005\u0003!5\u0011a!\u00118z%\u00164\u0007c\u0001\n\u0016/5\t1C\u0003\u0002\u0015\u000f\u00059\u0011\r\\4fEJ\f\u0017B\u0001\f\u0014\u0005)I5/\u00138uK\u001e\u0014\u0018\r\u001c\t\u0003\u0019aI!!G\u0007\u0003\t\tKH/\u001a\t\u00037qi\u0011!B\u0005\u0003;\u0015\u0011QCQ=uKR\u0013XO\\2bi\u0016$G)\u001b<jg&|g\u000e\u0005\u0002\u001c?%\u0011\u0001%\u0002\u0002\u000b\u0005f$XmU5h]\u0016$\u0017A\u0002\u0013j]&$H\u0005F\u0001$!\taA%\u0003\u0002&\u001b\t!QK\\5u\u0003!!x\u000eR8vE2,GC\u0001\u0015,!\ta\u0011&\u0003\u0002+\u001b\t1Ai\\;cY\u0016DQ\u0001\f\u0002A\u0002]\t\u0011A\\\u0001\ti>\u0014\u0015nZ%oiR\u0011qf\u000f\t\u0003aar!!\r\u001c\u000f\u0005I*T\"A\u001a\u000b\u0005QJ\u0011A\u0002\u001fs_>$h(C\u0001\u000f\u0013\t9T\"A\u0004qC\u000e\\\u0017mZ3\n\u0005eR$A\u0002\"jO&sGO\u0003\u00028\u001b!)Af\u0001a\u0001/\u0001"
)
public interface ByteIsReal extends IsIntegral, ByteTruncatedDivision {
   // $FF: synthetic method
   static double toDouble$(final ByteIsReal $this, final byte n) {
      return $this.toDouble(n);
   }

   default double toDouble(final byte n) {
      return this.toDouble$mcB$sp(n);
   }

   // $FF: synthetic method
   static BigInt toBigInt$(final ByteIsReal $this, final byte n) {
      return $this.toBigInt(n);
   }

   default BigInt toBigInt(final byte n) {
      return .MODULE$.BigInt().apply(n);
   }

   // $FF: synthetic method
   static double toDouble$mcB$sp$(final ByteIsReal $this, final byte n) {
      return $this.toDouble$mcB$sp(n);
   }

   default double toDouble$mcB$sp(final byte n) {
      return (double)n;
   }

   static void $init$(final ByteIsReal $this) {
   }
}
