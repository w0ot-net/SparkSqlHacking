package spire.std;

import scala.math.BigInt;
import scala.package.;
import scala.reflect.ScalaSignature;
import spire.algebra.IsIntegral;

@ScalaSignature(
   bytes = "\u0006\u0005u2q!\u0002\u0004\u0011\u0002\u0007\u00051\u0002C\u0003 \u0001\u0011\u0005\u0001\u0005C\u0003%\u0001\u0011\u0005Q\u0005C\u0003(\u0001\u0011\u0005\u0001\u0006C\u0003/\u0001\u0011\u0005qFA\u0005J]RL5OU3bY*\u0011q\u0001C\u0001\u0004gR$'\"A\u0005\u0002\u000bM\u0004\u0018N]3\u0004\u0001M!\u0001\u0001\u0004\n\u001c!\ti\u0001#D\u0001\u000f\u0015\u0005y\u0011!B:dC2\f\u0017BA\t\u000f\u0005\u0019\te.\u001f*fMB\u00191C\u0006\r\u000e\u0003QQ!!\u0006\u0005\u0002\u000f\u0005dw-\u001a2sC&\u0011q\u0003\u0006\u0002\u000b\u0013NLe\u000e^3he\u0006d\u0007CA\u0007\u001a\u0013\tQbBA\u0002J]R\u0004\"\u0001H\u000f\u000e\u0003\u0019I!A\b\u0004\u0003)%sG\u000f\u0016:v]\u000e\fG/\u001a3ESZL7/[8o\u0003\u0019!\u0013N\\5uIQ\t\u0011\u0005\u0005\u0002\u000eE%\u00111E\u0004\u0002\u0005+:LG/A\u0003pe\u0012,'/F\u0001'!\ta\u0002!\u0001\u0005u_\u0012{WO\u00197f)\tIC\u0006\u0005\u0002\u000eU%\u00111F\u0004\u0002\u0007\t>,(\r\\3\t\u000b5\u001a\u0001\u0019\u0001\r\u0002\u00039\f\u0001\u0002^8CS\u001eLe\u000e\u001e\u000b\u0003aq\u0002\"!M\u001d\u000f\u0005I:dBA\u001a7\u001b\u0005!$BA\u001b\u000b\u0003\u0019a$o\\8u}%\tq\"\u0003\u00029\u001d\u00059\u0001/Y2lC\u001e,\u0017B\u0001\u001e<\u0005\u0019\u0011\u0015nZ%oi*\u0011\u0001H\u0004\u0005\u0006[\u0011\u0001\r\u0001\u0007"
)
public interface IntIsReal extends IsIntegral, IntTruncatedDivision {
   // $FF: synthetic method
   static IntIsReal order$(final IntIsReal $this) {
      return $this.order();
   }

   default IntIsReal order() {
      return this;
   }

   // $FF: synthetic method
   static double toDouble$(final IntIsReal $this, final int n) {
      return $this.toDouble(n);
   }

   default double toDouble(final int n) {
      return this.toDouble$mcI$sp(n);
   }

   // $FF: synthetic method
   static BigInt toBigInt$(final IntIsReal $this, final int n) {
      return $this.toBigInt(n);
   }

   default BigInt toBigInt(final int n) {
      return .MODULE$.BigInt().apply(n);
   }

   // $FF: synthetic method
   static double toDouble$mcI$sp$(final IntIsReal $this, final int n) {
      return $this.toDouble$mcI$sp(n);
   }

   default double toDouble$mcI$sp(final int n) {
      return (double)n;
   }

   static void $init$(final IntIsReal $this) {
   }
}
