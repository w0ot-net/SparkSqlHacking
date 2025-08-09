package spire.math;

import algebra.ring.TruncatedDivision;
import scala.math.BigInt;
import scala.reflect.ScalaSignature;
import spire.util.Opt.;

@ScalaSignature(
   bytes = "\u0006\u0005M3\u0001b\u0002\u0005\u0011\u0002\u0007\u0005\u0001\u0002\u0004\u0005\u0006W\u0001!\t\u0001\f\u0005\u0006a\u0001!\t!\r\u0005\u0006\u0005\u0002!\ta\u0011\u0005\u0006\u000f\u0002!\t\u0001\u0013\u0005\u0006\u0017\u0002!\t\u0001\u0014\u0005\u0006\u001f\u0002!\t\u0001\u0015\u0002\u0016+&sG\u000f\u0016:v]\u000e\fG/\u001a3ESZL7/[8o\u0015\tI!\"\u0001\u0003nCRD'\"A\u0006\u0002\u000bM\u0004\u0018N]3\u0014\t\u0001i1\u0003\u000b\t\u0003\u001dEi\u0011a\u0004\u0006\u0002!\u0005)1oY1mC&\u0011!c\u0004\u0002\u0007\u0003:L(+\u001a4\u0011\u0007Q\tCE\u0004\u0002\u0016=9\u0011a\u0003\b\b\u0003/mi\u0011\u0001\u0007\u0006\u00033i\ta\u0001\u0010:p_Rt4\u0001A\u0005\u0002\u0017%\u0011QDC\u0001\bC2<WM\u0019:b\u0013\ty\u0002%A\u0004qC\u000e\\\u0017mZ3\u000b\u0005uQ\u0011B\u0001\u0012$\u0005E!&/\u001e8dCR,G\rR5wSNLwN\u001c\u0006\u0003?\u0001\u0002\"!\n\u0014\u000e\u0003!I!a\n\u0005\u0003\tUKe\u000e\u001e\t\u0003K%J!A\u000b\u0005\u0003\u0015UKe\u000e^*jO:,G-\u0001\u0004%S:LG\u000f\n\u000b\u0002[A\u0011aBL\u0005\u0003_=\u0011A!\u00168ji\u0006YAo\u001c\"jO&sGo\u00149u)\t\u0011\u0004\tE\u00024maj\u0011\u0001\u000e\u0006\u0003k)\tA!\u001e;jY&\u0011q\u0007\u000e\u0002\u0004\u001fB$\bCA\u001d>\u001d\tQDH\u0004\u0002\u0018w%\t\u0001#\u0003\u0002 \u001f%\u0011ah\u0010\u0002\u0007\u0005&<\u0017J\u001c;\u000b\u0005}y\u0001\"B!\u0003\u0001\u0004!\u0013!\u0001=\u0002\u000bQ\fXo\u001c;\u0015\u0007\u0011\"U\tC\u0003B\u0007\u0001\u0007A\u0005C\u0003G\u0007\u0001\u0007A%A\u0001z\u0003\u0011!Xn\u001c3\u0015\u0007\u0011J%\nC\u0003B\t\u0001\u0007A\u0005C\u0003G\t\u0001\u0007A%A\u0003gcV|G\u000fF\u0002%\u001b:CQ!Q\u0003A\u0002\u0011BQAR\u0003A\u0002\u0011\nAAZ7pIR\u0019A%\u0015*\t\u000b\u00053\u0001\u0019\u0001\u0013\t\u000b\u00193\u0001\u0019\u0001\u0013"
)
public interface UIntTruncatedDivision extends TruncatedDivision, UIntSigned {
   // $FF: synthetic method
   static BigInt toBigIntOpt$(final UIntTruncatedDivision $this, final int x) {
      return $this.toBigIntOpt(x);
   }

   default BigInt toBigIntOpt(final int x) {
      return (BigInt).MODULE$.apply(UInt$.MODULE$.toBigInt$extension(x));
   }

   // $FF: synthetic method
   static int tquot$(final UIntTruncatedDivision $this, final int x, final int y) {
      return $this.tquot(x, y);
   }

   default int tquot(final int x, final int y) {
      return UInt$.MODULE$.$div$extension(x, y);
   }

   // $FF: synthetic method
   static int tmod$(final UIntTruncatedDivision $this, final int x, final int y) {
      return $this.tmod(x, y);
   }

   default int tmod(final int x, final int y) {
      return UInt$.MODULE$.$percent$extension(x, y);
   }

   // $FF: synthetic method
   static int fquot$(final UIntTruncatedDivision $this, final int x, final int y) {
      return $this.fquot(x, y);
   }

   default int fquot(final int x, final int y) {
      return UInt$.MODULE$.$div$extension(x, y);
   }

   // $FF: synthetic method
   static int fmod$(final UIntTruncatedDivision $this, final int x, final int y) {
      return $this.fmod(x, y);
   }

   default int fmod(final int x, final int y) {
      return UInt$.MODULE$.$percent$extension(x, y);
   }

   static void $init$(final UIntTruncatedDivision $this) {
   }
}
