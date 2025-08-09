package spire.std;

import algebra.ring.TruncatedDivision;
import scala.Tuple2;
import scala.math.BigInt;
import scala.reflect.ScalaSignature;
import spire.util.Opt.;

@ScalaSignature(
   bytes = "\u0006\u0005=3qAB\u0004\u0011\u0002\u0007\u0005A\u0002C\u00030\u0001\u0011\u0005\u0001\u0007C\u00035\u0001\u0011\u0005Q\u0007C\u0003?\u0001\u0011\u0005q\bC\u0003E\u0001\u0011\u0005Q\tC\u0003I\u0001\u0011\u0005\u0013JA\fCS\u001eLe\u000e\u001e+sk:\u001c\u0017\r^3e\t&4\u0018n]5p]*\u0011\u0001\"C\u0001\u0004gR$'\"\u0001\u0006\u0002\u000bM\u0004\u0018N]3\u0004\u0001M!\u0001!D\n,!\tq\u0011#D\u0001\u0010\u0015\u0005\u0001\u0012!B:dC2\f\u0017B\u0001\n\u0010\u0005\u0019\te.\u001f*fMB\u0019A\u0003I\u0012\u000f\u0005UibB\u0001\f\u001c\u001d\t9\"$D\u0001\u0019\u0015\tI2\"\u0001\u0004=e>|GOP\u0005\u0002\u0015%\u0011A$C\u0001\bC2<WM\u0019:b\u0013\tqr$A\u0004qC\u000e\\\u0017mZ3\u000b\u0005qI\u0011BA\u0011#\u0005Y!&/\u001e8dCR,G\rR5wSNLwN\\\"SS:<'B\u0001\u0010 !\t!\u0003F\u0004\u0002&O9\u0011qCJ\u0005\u0002!%\u0011adD\u0005\u0003S)\u0012aAQ5h\u0013:$(B\u0001\u0010\u0010!\taS&D\u0001\b\u0013\tqsA\u0001\u0007CS\u001eLe\u000e^*jO:,G-\u0001\u0004%S:LG\u000f\n\u000b\u0002cA\u0011aBM\u0005\u0003g=\u0011A!\u00168ji\u0006YAo\u001c\"jO&sGo\u00149u)\t1D\bE\u00028u\rj\u0011\u0001\u000f\u0006\u0003s%\tA!\u001e;jY&\u00111\b\u000f\u0002\u0004\u001fB$\b\"B\u001f\u0003\u0001\u0004\u0019\u0013!\u0001=\u0002\u000bQ\fXo\u001c;\u0015\u0007\r\u0002%\tC\u0003B\u0007\u0001\u00071%A\u0001b\u0011\u0015\u00195\u00011\u0001$\u0003\u0005\u0011\u0017\u0001\u0002;n_\u0012$2a\t$H\u0011\u0015\tE\u00011\u0001$\u0011\u0015\u0019E\u00011\u0001$\u0003!!\u0018/^8u[>$Gc\u0001&N\u001dB!abS\u0012$\u0013\tauB\u0001\u0004UkBdWM\r\u0005\u0006\u0003\u0016\u0001\ra\t\u0005\u0006\u0007\u0016\u0001\ra\t"
)
public interface BigIntTruncatedDivision extends TruncatedDivision.forCommutativeRing, BigIntSigned {
   // $FF: synthetic method
   static BigInt toBigIntOpt$(final BigIntTruncatedDivision $this, final BigInt x) {
      return $this.toBigIntOpt(x);
   }

   default BigInt toBigIntOpt(final BigInt x) {
      return (BigInt).MODULE$.apply(x);
   }

   // $FF: synthetic method
   static BigInt tquot$(final BigIntTruncatedDivision $this, final BigInt a, final BigInt b) {
      return $this.tquot(a, b);
   }

   default BigInt tquot(final BigInt a, final BigInt b) {
      return a.$div(b);
   }

   // $FF: synthetic method
   static BigInt tmod$(final BigIntTruncatedDivision $this, final BigInt a, final BigInt b) {
      return $this.tmod(a, b);
   }

   default BigInt tmod(final BigInt a, final BigInt b) {
      return a.$percent(b);
   }

   // $FF: synthetic method
   static Tuple2 tquotmod$(final BigIntTruncatedDivision $this, final BigInt a, final BigInt b) {
      return $this.tquotmod(a, b);
   }

   default Tuple2 tquotmod(final BigInt a, final BigInt b) {
      return a.$div$percent(b);
   }

   static void $init$(final BigIntTruncatedDivision $this) {
   }
}
