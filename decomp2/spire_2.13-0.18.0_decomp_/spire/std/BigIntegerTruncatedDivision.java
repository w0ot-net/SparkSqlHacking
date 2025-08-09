package spire.std;

import algebra.ring.TruncatedDivision;
import java.math.BigInteger;
import scala.Tuple2;
import scala.math.BigInt;
import scala.reflect.ScalaSignature;
import spire.util.Opt.;

@ScalaSignature(
   bytes = "\u0006\u0005]3qAB\u0004\u0011\u0002\u0007\u0005A\u0002C\u00030\u0001\u0011\u0005\u0001\u0007C\u00035\u0001\u0011\u0005Q\u0007C\u0003G\u0001\u0011\u0005q\tC\u0003M\u0001\u0011\u0005Q\nC\u0003Q\u0001\u0011\u0005\u0013KA\u000eCS\u001eLe\u000e^3hKJ$&/\u001e8dCR,G\rR5wSNLwN\u001c\u0006\u0003\u0011%\t1a\u001d;e\u0015\u0005Q\u0011!B:qSJ,7\u0001A\n\u0005\u00015\u00192\u0006\u0005\u0002\u000f#5\tqBC\u0001\u0011\u0003\u0015\u00198-\u00197b\u0013\t\u0011rB\u0001\u0004B]f\u0014VM\u001a\t\u0004)\u0001\u001acBA\u000b\u001e\u001d\t12D\u0004\u0002\u001855\t\u0001D\u0003\u0002\u001a\u0017\u00051AH]8pizJ\u0011AC\u0005\u00039%\tq!\u00197hK\n\u0014\u0018-\u0003\u0002\u001f?\u00059\u0001/Y2lC\u001e,'B\u0001\u000f\n\u0013\t\t#E\u0001\fUeVt7-\u0019;fI\u0012Kg/[:j_:\u001c%+\u001b8h\u0015\tqr\u0004\u0005\u0002%S5\tQE\u0003\u0002'O\u0005!Q.\u0019;i\u0015\u0005A\u0013\u0001\u00026bm\u0006L!AK\u0013\u0003\u0015\tKw-\u00138uK\u001e,'\u000f\u0005\u0002-[5\tq!\u0003\u0002/\u000f\t\u0001\")[4J]R,w-\u001a:TS\u001etW\rZ\u0001\u0007I%t\u0017\u000e\u001e\u0013\u0015\u0003E\u0002\"A\u0004\u001a\n\u0005Mz!\u0001B+oSR\f1\u0002^8CS\u001eLe\u000e^(qiR\u0011a\u0007\u0012\t\u0004oibT\"\u0001\u001d\u000b\u0005eJ\u0011\u0001B;uS2L!a\u000f\u001d\u0003\u0007=\u0003H\u000f\u0005\u0002>\u0003:\u0011a\b\u0011\b\u0003/}J\u0011\u0001E\u0005\u0003==I!AQ\"\u0003\r\tKw-\u00138u\u0015\tqr\u0002C\u0003F\u0005\u0001\u00071%A\u0001o\u0003\u0015!\u0018/^8u)\r\u0019\u0003J\u0013\u0005\u0006\u0013\u000e\u0001\raI\u0001\u0002C\")1j\u0001a\u0001G\u0005\t!-\u0001\u0003u[>$GcA\u0012O\u001f\")\u0011\n\u0002a\u0001G!)1\n\u0002a\u0001G\u0005AA/];pi6|G\rF\u0002S+Z\u0003BAD*$G%\u0011Ak\u0004\u0002\u0007)V\u0004H.\u001a\u001a\t\u000b%+\u0001\u0019A\u0012\t\u000b-+\u0001\u0019A\u0012"
)
public interface BigIntegerTruncatedDivision extends TruncatedDivision.forCommutativeRing, BigIntegerSigned {
   // $FF: synthetic method
   static BigInt toBigIntOpt$(final BigIntegerTruncatedDivision $this, final BigInteger n) {
      return $this.toBigIntOpt(n);
   }

   default BigInt toBigIntOpt(final BigInteger n) {
      return (BigInt).MODULE$.apply(scala.package..MODULE$.BigInt().apply(n));
   }

   // $FF: synthetic method
   static BigInteger tquot$(final BigIntegerTruncatedDivision $this, final BigInteger a, final BigInteger b) {
      return $this.tquot(a, b);
   }

   default BigInteger tquot(final BigInteger a, final BigInteger b) {
      return a.divide(b);
   }

   // $FF: synthetic method
   static BigInteger tmod$(final BigIntegerTruncatedDivision $this, final BigInteger a, final BigInteger b) {
      return $this.tmod(a, b);
   }

   default BigInteger tmod(final BigInteger a, final BigInteger b) {
      return a.remainder(b);
   }

   // $FF: synthetic method
   static Tuple2 tquotmod$(final BigIntegerTruncatedDivision $this, final BigInteger a, final BigInteger b) {
      return $this.tquotmod(a, b);
   }

   default Tuple2 tquotmod(final BigInteger a, final BigInteger b) {
      BigInteger[] arr = a.divideAndRemainder(b);
      BigInteger d = arr[0];
      BigInteger r = arr[1];
      return new Tuple2(d, r);
   }

   static void $init$(final BigIntegerTruncatedDivision $this) {
   }
}
