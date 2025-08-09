package spire.syntax;

import algebra.ring.MultiplicativeSemigroup;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005m3qAB\u0004\u0011\u0002\u0007\u0005A\u0002C\u0003\u0014\u0001\u0011\u0005A\u0003C\u0003\u0019\u0001\u0011\r\u0011\u0004C\u0003@\u0001\u0011\r\u0001\tC\u0003J\u0001\u0011\r!\nC\u0003S\u0001\u0011\r1KA\u000fNk2$\u0018\u000e\u001d7jG\u0006$\u0018N^3TK6LwM]8vaNKh\u000e^1y\u0015\tA\u0011\"\u0001\u0004ts:$\u0018\r\u001f\u0006\u0002\u0015\u0005)1\u000f]5sK\u000e\u00011C\u0001\u0001\u000e!\tq\u0011#D\u0001\u0010\u0015\u0005\u0001\u0012!B:dC2\f\u0017B\u0001\n\u0010\u0005\u0019\te.\u001f*fM\u00061A%\u001b8ji\u0012\"\u0012!\u0006\t\u0003\u001dYI!aF\b\u0003\tUs\u0017\u000e^\u0001\u001b[VdG/\u001b9mS\u000e\fG/\u001b<f'\u0016l\u0017n\u001a:pkB|\u0005o]\u000b\u00035\t\"\"aG\u001f\u0015\u0005qY\u0003cA\u000f\u001fA5\tq!\u0003\u0002 \u000f\tQR*\u001e7uSBd\u0017nY1uSZ,7+Z7jOJ|W\u000f](qgB\u0011\u0011E\t\u0007\u0001\t\u0015\u0019#A1\u0001%\u0005\u0005\t\u0015CA\u0013)!\tqa%\u0003\u0002(\u001f\t9aj\u001c;iS:<\u0007C\u0001\b*\u0013\tQsBA\u0002B]fDq\u0001\f\u0002\u0002\u0002\u0003\u000fQ&A\u0006fm&$WM\\2fIE\u001a\u0004c\u0001\u0018;A9\u0011qf\u000e\b\u0003aUr!!\r\u001b\u000e\u0003IR!aM\u0006\u0002\rq\u0012xn\u001c;?\u0013\u0005Q\u0011B\u0001\u001c\n\u0003\u001d\tGnZ3ce\u0006L!\u0001O\u001d\u0002\u000fA\f7m[1hK*\u0011a'C\u0005\u0003wq\u0012q#T;mi&\u0004H.[2bi&4XmU3nS\u001e\u0014x.\u001e9\u000b\u0005aJ\u0004\"\u0002 \u0003\u0001\u0004\u0001\u0013!A1\u0002I1LG/\u001a:bY&sG/T;mi&\u0004H.[2bi&4XmU3nS\u001e\u0014x.\u001e9PaN$\"!\u0011#\u0011\u0005u\u0011\u0015BA\"\b\u0005\u0011b\u0015\u000e^3sC2Le\u000e^'vYRL\u0007\u000f\\5dCRLg/Z*f[&<'o\\;q\u001fB\u001c\b\"B#\u0004\u0001\u00041\u0015a\u00017igB\u0011abR\u0005\u0003\u0011>\u00111!\u00138u\u0003\u0015b\u0017\u000e^3sC2duN\\4Nk2$\u0018\u000e\u001d7jG\u0006$\u0018N^3TK6LwM]8va>\u00038\u000f\u0006\u0002L\u001dB\u0011Q\u0004T\u0005\u0003\u001b\u001e\u0011Q\u0005T5uKJ\fG\u000eT8oO6+H\u000e^5qY&\u001c\u0017\r^5wKN+W.[4s_V\u0004x\n]:\t\u000b\u0015#\u0001\u0019A(\u0011\u00059\u0001\u0016BA)\u0010\u0005\u0011auN\\4\u0002O1LG/\u001a:bY\u0012{WO\u00197f\u001bVdG/\u001b9mS\u000e\fG/\u001b<f'\u0016l\u0017n\u001a:pkB|\u0005o\u001d\u000b\u0003)^\u0003\"!H+\n\u0005Y;!a\n'ji\u0016\u0014\u0018\r\u001c#pk\ndW-T;mi&\u0004H.[2bi&4XmU3nS\u001e\u0014x.\u001e9PaNDQ!R\u0003A\u0002a\u0003\"AD-\n\u0005i{!A\u0002#pk\ndW\r"
)
public interface MultiplicativeSemigroupSyntax {
   // $FF: synthetic method
   static MultiplicativeSemigroupOps multiplicativeSemigroupOps$(final MultiplicativeSemigroupSyntax $this, final Object a, final MultiplicativeSemigroup evidence$13) {
      return $this.multiplicativeSemigroupOps(a, evidence$13);
   }

   default MultiplicativeSemigroupOps multiplicativeSemigroupOps(final Object a, final MultiplicativeSemigroup evidence$13) {
      return new MultiplicativeSemigroupOps(a, evidence$13);
   }

   // $FF: synthetic method
   static int literalIntMultiplicativeSemigroupOps$(final MultiplicativeSemigroupSyntax $this, final int lhs) {
      return $this.literalIntMultiplicativeSemigroupOps(lhs);
   }

   default int literalIntMultiplicativeSemigroupOps(final int lhs) {
      return lhs;
   }

   // $FF: synthetic method
   static long literalLongMultiplicativeSemigroupOps$(final MultiplicativeSemigroupSyntax $this, final long lhs) {
      return $this.literalLongMultiplicativeSemigroupOps(lhs);
   }

   default long literalLongMultiplicativeSemigroupOps(final long lhs) {
      return lhs;
   }

   // $FF: synthetic method
   static double literalDoubleMultiplicativeSemigroupOps$(final MultiplicativeSemigroupSyntax $this, final double lhs) {
      return $this.literalDoubleMultiplicativeSemigroupOps(lhs);
   }

   default double literalDoubleMultiplicativeSemigroupOps(final double lhs) {
      return lhs;
   }

   static void $init$(final MultiplicativeSemigroupSyntax $this) {
   }
}
