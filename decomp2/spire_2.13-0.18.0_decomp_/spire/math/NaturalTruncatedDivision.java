package spire.math;

import algebra.ring.TruncatedDivision;
import scala.math.BigInt;
import scala.reflect.ScalaSignature;
import spire.util.Opt.;

@ScalaSignature(
   bytes = "\u0006\u0005M3\u0001b\u0002\u0005\u0011\u0002\u0007\u0005\u0001\u0002\u0004\u0005\u0006W\u0001!\t\u0001\f\u0005\u0006a\u0001!\t!\r\u0005\u0006\u0005\u0002!\ta\u0011\u0005\u0006\u000f\u0002!\t\u0001\u0013\u0005\u0006\u0017\u0002!\t\u0001\u0014\u0005\u0006\u001f\u0002!\t\u0001\u0015\u0002\u0019\u001d\u0006$XO]1m)J,hnY1uK\u0012$\u0015N^5tS>t'BA\u0005\u000b\u0003\u0011i\u0017\r\u001e5\u000b\u0003-\tQa\u001d9je\u0016\u001cB\u0001A\u0007\u0014/A\u0011a\"E\u0007\u0002\u001f)\t\u0001#A\u0003tG\u0006d\u0017-\u0003\u0002\u0013\u001f\t1\u0011I\\=SK\u001a\u0004\"\u0001F\u000b\u000e\u0003!I!A\u0006\u0005\u0003\u001b9\u000bG/\u001e:bYNKwM\\3e!\rAR\u0005\u000b\b\u00033\tr!A\u0007\u0011\u000f\u0005myR\"\u0001\u000f\u000b\u0005uq\u0012A\u0002\u001fs_>$hh\u0001\u0001\n\u0003-I!!\t\u0006\u0002\u000f\u0005dw-\u001a2sC&\u00111\u0005J\u0001\ba\u0006\u001c7.Y4f\u0015\t\t#\"\u0003\u0002'O\t\tBK];oG\u0006$X\r\u001a#jm&\u001c\u0018n\u001c8\u000b\u0005\r\"\u0003C\u0001\u000b*\u0013\tQ\u0003BA\u0004OCR,(/\u00197\u0002\r\u0011Jg.\u001b;%)\u0005i\u0003C\u0001\b/\u0013\tysB\u0001\u0003V]&$\u0018a\u0003;p\u0005&<\u0017J\u001c;PaR$\"A\r!\u0011\u0007M2\u0004(D\u00015\u0015\t)$\"\u0001\u0003vi&d\u0017BA\u001c5\u0005\ry\u0005\u000f\u001e\t\u0003sur!A\u000f\u001f\u000f\u0005mY\u0014\"\u0001\t\n\u0005\rz\u0011B\u0001 @\u0005\u0019\u0011\u0015nZ%oi*\u00111e\u0004\u0005\u0006\u0003\n\u0001\r\u0001K\u0001\u0002q\u0006)A/];piR\u0019\u0001\u0006R#\t\u000b\u0005\u001b\u0001\u0019\u0001\u0015\t\u000b\u0019\u001b\u0001\u0019\u0001\u0015\u0002\u0003e\fA\u0001^7pIR\u0019\u0001&\u0013&\t\u000b\u0005#\u0001\u0019\u0001\u0015\t\u000b\u0019#\u0001\u0019\u0001\u0015\u0002\u000b\u0019\fXo\u001c;\u0015\u0007!je\nC\u0003B\u000b\u0001\u0007\u0001\u0006C\u0003G\u000b\u0001\u0007\u0001&\u0001\u0003g[>$Gc\u0001\u0015R%\")\u0011I\u0002a\u0001Q!)aI\u0002a\u0001Q\u0001"
)
public interface NaturalTruncatedDivision extends NaturalSigned, TruncatedDivision {
   // $FF: synthetic method
   static BigInt toBigIntOpt$(final NaturalTruncatedDivision $this, final Natural x) {
      return $this.toBigIntOpt(x);
   }

   default BigInt toBigIntOpt(final Natural x) {
      return (BigInt).MODULE$.apply(x.toBigInt());
   }

   // $FF: synthetic method
   static Natural tquot$(final NaturalTruncatedDivision $this, final Natural x, final Natural y) {
      return $this.tquot(x, y);
   }

   default Natural tquot(final Natural x, final Natural y) {
      return x.$div(y);
   }

   // $FF: synthetic method
   static Natural tmod$(final NaturalTruncatedDivision $this, final Natural x, final Natural y) {
      return $this.tmod(x, y);
   }

   default Natural tmod(final Natural x, final Natural y) {
      return x.$percent(y);
   }

   // $FF: synthetic method
   static Natural fquot$(final NaturalTruncatedDivision $this, final Natural x, final Natural y) {
      return $this.fquot(x, y);
   }

   default Natural fquot(final Natural x, final Natural y) {
      return x.$div(y);
   }

   // $FF: synthetic method
   static Natural fmod$(final NaturalTruncatedDivision $this, final Natural x, final Natural y) {
      return $this.fmod(x, y);
   }

   default Natural fmod(final Natural x, final Natural y) {
      return x.$percent(y);
   }

   static void $init$(final NaturalTruncatedDivision $this) {
   }
}
