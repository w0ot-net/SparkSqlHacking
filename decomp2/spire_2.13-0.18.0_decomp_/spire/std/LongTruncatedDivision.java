package spire.std;

import algebra.ring.TruncatedDivision;
import scala.math.BigInt;
import scala.reflect.ScalaSignature;
import spire.util.Opt.;

@ScalaSignature(
   bytes = "\u0006\u0005%3q!\u0002\u0004\u0011\u0002\u0007\u00051\u0002C\u0003*\u0001\u0011\u0005!\u0006C\u0003/\u0001\u0011\u0005q\u0006C\u0003A\u0001\u0011\u0005\u0011\tC\u0003F\u0001\u0011\u0005aIA\u000bM_:<GK];oG\u0006$X\r\u001a#jm&\u001c\u0018n\u001c8\u000b\u0005\u001dA\u0011aA:uI*\t\u0011\"A\u0003ta&\u0014Xm\u0001\u0001\u0014\t\u0001a!#\n\t\u0003\u001bAi\u0011A\u0004\u0006\u0002\u001f\u0005)1oY1mC&\u0011\u0011C\u0004\u0002\u0007\u0003:L(+\u001a4\u0011\u0007My\"E\u0004\u0002\u001599\u0011QC\u0007\b\u0003-ei\u0011a\u0006\u0006\u00031)\ta\u0001\u0010:p_Rt\u0014\"A\u0005\n\u0005mA\u0011aB1mO\u0016\u0014'/Y\u0005\u0003;y\tq\u0001]1dW\u0006<WM\u0003\u0002\u001c\u0011%\u0011\u0001%\t\u0002\u0017)J,hnY1uK\u0012$\u0015N^5tS>t7IU5oO*\u0011QD\b\t\u0003\u001b\rJ!\u0001\n\b\u0003\t1{gn\u001a\t\u0003M\u001dj\u0011AB\u0005\u0003Q\u0019\u0011!\u0002T8oONKwM\\3e\u0003\u0019!\u0013N\\5uIQ\t1\u0006\u0005\u0002\u000eY%\u0011QF\u0004\u0002\u0005+:LG/A\u0006u_\nKw-\u00138u\u001fB$HC\u0001\u0019?!\r\tDGN\u0007\u0002e)\u00111\u0007C\u0001\u0005kRLG.\u0003\u00026e\t\u0019q\n\u001d;\u0011\u0005]ZdB\u0001\u001d;\u001d\t1\u0012(C\u0001\u0010\u0013\tib\"\u0003\u0002={\t1!)[4J]RT!!\b\b\t\u000b}\u0012\u0001\u0019\u0001\u0012\u0002\u0003a\fQ\u0001^9v_R$2A\t\"D\u0011\u0015y4\u00011\u0001#\u0011\u0015!5\u00011\u0001#\u0003\u0005I\u0018\u0001\u0002;n_\u0012$2AI$I\u0011\u0015yD\u00011\u0001#\u0011\u0015!E\u00011\u0001#\u0001"
)
public interface LongTruncatedDivision extends TruncatedDivision.forCommutativeRing.mcJ.sp, LongSigned {
   // $FF: synthetic method
   static BigInt toBigIntOpt$(final LongTruncatedDivision $this, final long x) {
      return $this.toBigIntOpt(x);
   }

   default BigInt toBigIntOpt(final long x) {
      return (BigInt).MODULE$.apply(scala.package..MODULE$.BigInt().apply(x));
   }

   // $FF: synthetic method
   static long tquot$(final LongTruncatedDivision $this, final long x, final long y) {
      return $this.tquot(x, y);
   }

   default long tquot(final long x, final long y) {
      return this.tquot$mcJ$sp(x, y);
   }

   // $FF: synthetic method
   static long tmod$(final LongTruncatedDivision $this, final long x, final long y) {
      return $this.tmod(x, y);
   }

   default long tmod(final long x, final long y) {
      return this.tmod$mcJ$sp(x, y);
   }

   // $FF: synthetic method
   static long tquot$mcJ$sp$(final LongTruncatedDivision $this, final long x, final long y) {
      return $this.tquot$mcJ$sp(x, y);
   }

   default long tquot$mcJ$sp(final long x, final long y) {
      return x / y;
   }

   // $FF: synthetic method
   static long tmod$mcJ$sp$(final LongTruncatedDivision $this, final long x, final long y) {
      return $this.tmod$mcJ$sp(x, y);
   }

   default long tmod$mcJ$sp(final long x, final long y) {
      return x % y;
   }

   static void $init$(final LongTruncatedDivision $this) {
   }
}
