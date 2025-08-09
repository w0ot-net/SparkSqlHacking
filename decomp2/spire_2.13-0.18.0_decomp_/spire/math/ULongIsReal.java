package spire.math;

import scala.math.BigInt;
import scala.reflect.ScalaSignature;
import spire.algebra.IsIntegral;

@ScalaSignature(
   bytes = "\u0006\u0005e2\u0001\u0002B\u0003\u0011\u0002\u0007\u0005Q!\u0003\u0005\u0006;\u0001!\ta\b\u0005\u0006G\u0001!\t\u0001\n\u0005\u0006U\u0001!\ta\u000b\u0002\f+2{gnZ%t%\u0016\fGN\u0003\u0002\u0007\u000f\u0005!Q.\u0019;i\u0015\u0005A\u0011!B:qSJ,7\u0003\u0002\u0001\u000b!i\u0001\"a\u0003\b\u000e\u00031Q\u0011!D\u0001\u0006g\u000e\fG.Y\u0005\u0003\u001f1\u0011a!\u00118z%\u00164\u0007cA\t\u0015-5\t!C\u0003\u0002\u0014\u000f\u00059\u0011\r\\4fEJ\f\u0017BA\u000b\u0013\u0005)I5/\u00138uK\u001e\u0014\u0018\r\u001c\t\u0003/ai\u0011!B\u0005\u00033\u0015\u0011Q!\u0016'p]\u001e\u0004\"aF\u000e\n\u0005q)!AF+M_:<GK];oG\u0006$X\r\u001a#jm&\u001c\u0018n\u001c8\u0002\r\u0011Jg.\u001b;%\u0007\u0001!\u0012\u0001\t\t\u0003\u0017\u0005J!A\t\u0007\u0003\tUs\u0017\u000e^\u0001\ti>$u.\u001e2mKR\u0011Q\u0005\u000b\t\u0003\u0017\u0019J!a\n\u0007\u0003\r\u0011{WO\u00197f\u0011\u0015I#\u00011\u0001\u0017\u0003\u0005q\u0017\u0001\u0003;p\u0005&<\u0017J\u001c;\u0015\u00051B\u0004CA\u00176\u001d\tq3G\u0004\u00020e5\t\u0001G\u0003\u00022=\u00051AH]8pizJ\u0011!D\u0005\u0003i1\tq\u0001]1dW\u0006<W-\u0003\u00027o\t1!)[4J]RT!\u0001\u000e\u0007\t\u000b%\u001a\u0001\u0019\u0001\f"
)
public interface ULongIsReal extends IsIntegral, ULongTruncatedDivision {
   // $FF: synthetic method
   static double toDouble$(final ULongIsReal $this, final long n) {
      return $this.toDouble(n);
   }

   default double toDouble(final long n) {
      return ULong$.MODULE$.toDouble$extension(n);
   }

   // $FF: synthetic method
   static BigInt toBigInt$(final ULongIsReal $this, final long n) {
      return $this.toBigInt(n);
   }

   default BigInt toBigInt(final long n) {
      return ULong$.MODULE$.toBigInt$extension(n);
   }

   static void $init$(final ULongIsReal $this) {
   }
}
