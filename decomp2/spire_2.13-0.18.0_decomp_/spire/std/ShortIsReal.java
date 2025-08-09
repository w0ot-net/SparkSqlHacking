package spire.std;

import scala.math.BigInt;
import scala.package.;
import scala.reflect.ScalaSignature;
import spire.algebra.IsIntegral;

@ScalaSignature(
   bytes = "\u0006\u0005e2q\u0001B\u0003\u0011\u0002\u0007\u0005!\u0002C\u0003\u001f\u0001\u0011\u0005q\u0004C\u0003$\u0001\u0011\u0005A\u0005C\u0003+\u0001\u0011\u00051FA\u0006TQ>\u0014H/S:SK\u0006d'B\u0001\u0004\b\u0003\r\u0019H\u000f\u001a\u0006\u0002\u0011\u0005)1\u000f]5sK\u000e\u00011\u0003\u0002\u0001\f#i\u0001\"\u0001D\b\u000e\u00035Q\u0011AD\u0001\u0006g\u000e\fG.Y\u0005\u0003!5\u0011a!\u00118z%\u00164\u0007c\u0001\n\u0016/5\t1C\u0003\u0002\u0015\u000f\u00059\u0011\r\\4fEJ\f\u0017B\u0001\f\u0014\u0005)I5/\u00138uK\u001e\u0014\u0018\r\u001c\t\u0003\u0019aI!!G\u0007\u0003\u000bMCwN\u001d;\u0011\u0005maR\"A\u0003\n\u0005u)!AF*i_J$HK];oG\u0006$X\r\u001a#jm&\u001c\u0018n\u001c8\u0002\r\u0011Jg.\u001b;%)\u0005\u0001\u0003C\u0001\u0007\"\u0013\t\u0011SB\u0001\u0003V]&$\u0018\u0001\u0003;p\t>,(\r\\3\u0015\u0005\u0015B\u0003C\u0001\u0007'\u0013\t9SB\u0001\u0004E_V\u0014G.\u001a\u0005\u0006S\t\u0001\raF\u0001\u0002]\u0006AAo\u001c\"jO&sG\u000f\u0006\u0002-qA\u0011Q&\u000e\b\u0003]Mr!a\f\u001a\u000e\u0003AR!!M\u0005\u0002\rq\u0012xn\u001c;?\u0013\u0005q\u0011B\u0001\u001b\u000e\u0003\u001d\u0001\u0018mY6bO\u0016L!AN\u001c\u0003\r\tKw-\u00138u\u0015\t!T\u0002C\u0003*\u0007\u0001\u0007q\u0003"
)
public interface ShortIsReal extends IsIntegral, ShortTruncatedDivision {
   // $FF: synthetic method
   static double toDouble$(final ShortIsReal $this, final short n) {
      return $this.toDouble(n);
   }

   default double toDouble(final short n) {
      return this.toDouble$mcS$sp(n);
   }

   // $FF: synthetic method
   static BigInt toBigInt$(final ShortIsReal $this, final short n) {
      return $this.toBigInt(n);
   }

   default BigInt toBigInt(final short n) {
      return .MODULE$.BigInt().apply(n);
   }

   // $FF: synthetic method
   static double toDouble$mcS$sp$(final ShortIsReal $this, final short n) {
      return $this.toDouble$mcS$sp(n);
   }

   default double toDouble$mcS$sp(final short n) {
      return (double)n;
   }

   static void $init$(final ShortIsReal $this) {
   }
}
