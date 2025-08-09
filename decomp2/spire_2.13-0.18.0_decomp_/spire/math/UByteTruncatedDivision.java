package spire.math;

import algebra.ring.TruncatedDivision;
import scala.math.BigInt;
import scala.reflect.ScalaSignature;
import spire.util.Opt.;

@ScalaSignature(
   bytes = "\u0006\u0005M3\u0001b\u0002\u0005\u0011\u0002\u0007\u0005\u0001\u0002\u0004\u0005\u0006W\u0001!\t\u0001\f\u0005\u0006a\u0001!\t!\r\u0005\u0006\u0005\u0002!\ta\u0011\u0005\u0006\u000f\u0002!\t\u0001\u0013\u0005\u0006\u0017\u0002!\t\u0001\u0014\u0005\u0006\u001f\u0002!\t\u0001\u0015\u0002\u0017+\nKH/\u001a+sk:\u001c\u0017\r^3e\t&4\u0018n]5p]*\u0011\u0011BC\u0001\u0005[\u0006$\bNC\u0001\f\u0003\u0015\u0019\b/\u001b:f'\u0011\u0001Qb\u0005\u0015\u0011\u00059\tR\"A\b\u000b\u0003A\tQa]2bY\u0006L!AE\b\u0003\r\u0005s\u0017PU3g!\r!\u0012\u0005\n\b\u0003+yq!A\u0006\u000f\u000f\u0005]YR\"\u0001\r\u000b\u0005eQ\u0012A\u0002\u001fs_>$hh\u0001\u0001\n\u0003-I!!\b\u0006\u0002\u000f\u0005dw-\u001a2sC&\u0011q\u0004I\u0001\ba\u0006\u001c7.Y4f\u0015\ti\"\"\u0003\u0002#G\t\tBK];oG\u0006$X\r\u001a#jm&\u001c\u0018n\u001c8\u000b\u0005}\u0001\u0003CA\u0013'\u001b\u0005A\u0011BA\u0014\t\u0005\u0015)&)\u001f;f!\t)\u0013&\u0003\u0002+\u0011\tYQKQ=uKNKwM\\3e\u0003\u0019!\u0013N\\5uIQ\tQ\u0006\u0005\u0002\u000f]%\u0011qf\u0004\u0002\u0005+:LG/A\u0006u_\nKw-\u00138u\u001fB$HC\u0001\u001aA!\r\u0019d\u0007O\u0007\u0002i)\u0011QGC\u0001\u0005kRLG.\u0003\u00028i\t\u0019q\n\u001d;\u0011\u0005ejdB\u0001\u001e=\u001d\t92(C\u0001\u0011\u0013\tyr\"\u0003\u0002?\u007f\t1!)[4J]RT!aH\b\t\u000b\u0005\u0013\u0001\u0019\u0001\u0013\u0002\u0003a\fQ\u0001^9v_R$2\u0001\n#F\u0011\u0015\t5\u00011\u0001%\u0011\u001515\u00011\u0001%\u0003\u0005I\u0018\u0001\u0002;n_\u0012$2\u0001J%K\u0011\u0015\tE\u00011\u0001%\u0011\u00151E\u00011\u0001%\u0003\u00151\u0017/^8u)\r!SJ\u0014\u0005\u0006\u0003\u0016\u0001\r\u0001\n\u0005\u0006\r\u0016\u0001\r\u0001J\u0001\u0005M6|G\rF\u0002%#JCQ!\u0011\u0004A\u0002\u0011BQA\u0012\u0004A\u0002\u0011\u0002"
)
public interface UByteTruncatedDivision extends TruncatedDivision, UByteSigned {
   // $FF: synthetic method
   static BigInt toBigIntOpt$(final UByteTruncatedDivision $this, final byte x) {
      return $this.toBigIntOpt(x);
   }

   default BigInt toBigIntOpt(final byte x) {
      return (BigInt).MODULE$.apply(UByte$.MODULE$.toBigInt$extension(x));
   }

   // $FF: synthetic method
   static byte tquot$(final UByteTruncatedDivision $this, final byte x, final byte y) {
      return $this.tquot(x, y);
   }

   default byte tquot(final byte x, final byte y) {
      return UByte$.MODULE$.$div$extension(x, y);
   }

   // $FF: synthetic method
   static byte tmod$(final UByteTruncatedDivision $this, final byte x, final byte y) {
      return $this.tmod(x, y);
   }

   default byte tmod(final byte x, final byte y) {
      return UByte$.MODULE$.$percent$extension(x, y);
   }

   // $FF: synthetic method
   static byte fquot$(final UByteTruncatedDivision $this, final byte x, final byte y) {
      return $this.fquot(x, y);
   }

   default byte fquot(final byte x, final byte y) {
      return UByte$.MODULE$.$div$extension(x, y);
   }

   // $FF: synthetic method
   static byte fmod$(final UByteTruncatedDivision $this, final byte x, final byte y) {
      return $this.fmod(x, y);
   }

   default byte fmod(final byte x, final byte y) {
      return UByte$.MODULE$.$percent$extension(x, y);
   }

   static void $init$(final UByteTruncatedDivision $this) {
   }
}
