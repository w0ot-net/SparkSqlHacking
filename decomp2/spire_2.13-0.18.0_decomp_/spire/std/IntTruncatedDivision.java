package spire.std;

import algebra.ring.TruncatedDivision;
import scala.math.BigInt;
import scala.reflect.ScalaSignature;
import spire.util.Opt.;

@ScalaSignature(
   bytes = "\u0006\u0005%3q!\u0002\u0004\u0011\u0002\u0007\u00051\u0002C\u0003*\u0001\u0011\u0005!\u0006C\u0003/\u0001\u0011\u0005q\u0006C\u0003A\u0001\u0011\u0005\u0011\tC\u0003F\u0001\u0011\u0005aI\u0001\u000bJ]R$&/\u001e8dCR,G\rR5wSNLwN\u001c\u0006\u0003\u000f!\t1a\u001d;e\u0015\u0005I\u0011!B:qSJ,7\u0001A\n\u0005\u00011\u0011R\u0005\u0005\u0002\u000e!5\taBC\u0001\u0010\u0003\u0015\u00198-\u00197b\u0013\t\tbB\u0001\u0004B]f\u0014VM\u001a\t\u0004'}\u0011cB\u0001\u000b\u001d\u001d\t)\"D\u0004\u0002\u001735\tqC\u0003\u0002\u0019\u0015\u00051AH]8pizJ\u0011!C\u0005\u00037!\tq!\u00197hK\n\u0014\u0018-\u0003\u0002\u001e=\u00059\u0001/Y2lC\u001e,'BA\u000e\t\u0013\t\u0001\u0013E\u0001\fUeVt7-\u0019;fI\u0012Kg/[:j_:\u001c%+\u001b8h\u0015\tib\u0004\u0005\u0002\u000eG%\u0011AE\u0004\u0002\u0004\u0013:$\bC\u0001\u0014(\u001b\u00051\u0011B\u0001\u0015\u0007\u0005%Ie\u000e^*jO:,G-\u0001\u0004%S:LG\u000f\n\u000b\u0002WA\u0011Q\u0002L\u0005\u0003[9\u0011A!\u00168ji\u0006YAo\u001c\"jO&sGo\u00149u)\t\u0001d\bE\u00022iYj\u0011A\r\u0006\u0003g!\tA!\u001e;jY&\u0011QG\r\u0002\u0004\u001fB$\bCA\u001c<\u001d\tA$H\u0004\u0002\u0017s%\tq\"\u0003\u0002\u001e\u001d%\u0011A(\u0010\u0002\u0007\u0005&<\u0017J\u001c;\u000b\u0005uq\u0001\"B \u0003\u0001\u0004\u0011\u0013!\u0001=\u0002\u000bQ\fXo\u001c;\u0015\u0007\t\u00125\tC\u0003@\u0007\u0001\u0007!\u0005C\u0003E\u0007\u0001\u0007!%A\u0001z\u0003\u0011!Xn\u001c3\u0015\u0007\t:\u0005\nC\u0003@\t\u0001\u0007!\u0005C\u0003E\t\u0001\u0007!\u0005"
)
public interface IntTruncatedDivision extends TruncatedDivision.forCommutativeRing.mcI.sp, IntSigned {
   // $FF: synthetic method
   static BigInt toBigIntOpt$(final IntTruncatedDivision $this, final int x) {
      return $this.toBigIntOpt(x);
   }

   default BigInt toBigIntOpt(final int x) {
      return (BigInt).MODULE$.apply(scala.package..MODULE$.BigInt().apply(x));
   }

   // $FF: synthetic method
   static int tquot$(final IntTruncatedDivision $this, final int x, final int y) {
      return $this.tquot(x, y);
   }

   default int tquot(final int x, final int y) {
      return this.tquot$mcI$sp(x, y);
   }

   // $FF: synthetic method
   static int tmod$(final IntTruncatedDivision $this, final int x, final int y) {
      return $this.tmod(x, y);
   }

   default int tmod(final int x, final int y) {
      return this.tmod$mcI$sp(x, y);
   }

   // $FF: synthetic method
   static int tquot$mcI$sp$(final IntTruncatedDivision $this, final int x, final int y) {
      return $this.tquot$mcI$sp(x, y);
   }

   default int tquot$mcI$sp(final int x, final int y) {
      return x / y;
   }

   // $FF: synthetic method
   static int tmod$mcI$sp$(final IntTruncatedDivision $this, final int x, final int y) {
      return $this.tmod$mcI$sp(x, y);
   }

   default int tmod$mcI$sp(final int x, final int y) {
      return x % y;
   }

   static void $init$(final IntTruncatedDivision $this) {
   }
}
