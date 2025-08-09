package spire.math;

import algebra.ring.Field;
import scala.reflect.ScalaSignature;
import spire.algebra.NRoot;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005%ca\u0002\n\u0014!\u0003\r\n\u0001G\u0004\u0006'NA\t\u0001\u0016\u0004\u0006%MA\t!\u0016\u0005\u0006C\n!\tA\u0019\u0005\bG\n\u0011\r\u0011b\u0002e\u0011\u0019I'\u0001)A\u0007K\"9!N\u0001b\u0001\n\u000fY\u0007B\u00029\u0003A\u00035A\u000eC\u0004r\u0005\t\u0007Iq\u0001:\t\ri\u0014\u0001\u0015!\u0004t\u0011\u001dY(A1A\u0005\bqDq!a\u0001\u0003A\u00035Q\u0010C\u0005\u0002\u0006\t\u0011\r\u0011b\u0002\u0002\b!A\u0011\u0011\u0003\u0002!\u0002\u001b\tI\u0001C\u0005\u0002\u0014\t\u0011\r\u0011b\u0002\u0002\u0016!A\u0011q\u0004\u0002!\u0002\u001b\t9\u0002C\u0004\u0002\"\t!)!a\t\t\u0013\u0005e\"!!A\u0005\n\u0005m\"A\u0003$sC\u000e$\u0018n\u001c8bY*\u0011A#F\u0001\u0005[\u0006$\bNC\u0001\u0017\u0003\u0015\u0019\b/\u001b:f\u0007\u0001)\"!\u0007\u001a\u0014\r\u0001Q\u0002\u0005\u0013'Q!\tYb$D\u0001\u001d\u0015\u0005i\u0012!B:dC2\f\u0017BA\u0010\u001d\u0005\r\te.\u001f\t\u0004C5\u0002dB\u0001\u0012+\u001d\t\u0019\u0003F\u0004\u0002%O5\tQE\u0003\u0002'/\u00051AH]8pizJ\u0011AF\u0005\u0003SU\tq!\u00197hK\n\u0014\u0018-\u0003\u0002,Y\u00059\u0001/Y2lC\u001e,'BA\u0015\u0016\u0013\tqsFA\u0003GS\u0016dGM\u0003\u0002,YA\u0011\u0011G\r\u0007\u0001\t%\u0019\u0004\u0001)A\u0001\u0002\u000b\u0007AGA\u0001B#\t)$\u0004\u0005\u0002\u001cm%\u0011q\u0007\b\u0002\b\u001d>$\b.\u001b8hQ\u0011\u0011\u0014\bP\"\u0011\u0005mQ\u0014BA\u001e\u001d\u0005-\u0019\b/Z2jC2L'0\u001a32\u000b\rjd\bQ \u000f\u0005mq\u0014BA \u001d\u0003\u00151En\\1uc\u0011!\u0013IQ\u000f\u000f\u0005\u0011\u0012\u0015\"A\u000f2\u000b\r\"Ui\u0012$\u000f\u0005m)\u0015B\u0001$\u001d\u0003\u0019!u.\u001e2mKF\"A%\u0011\"\u001e!\rI%\nM\u0007\u0002Y%\u00111\n\f\u0002\u0006\u001dJ{w\u000e\u001e\t\u0004\u001b:\u0003T\"A\n\n\u0005=\u001b\"\u0001C%oi\u0016<'/\u00197\u0011\u0007\u0005\n\u0006'\u0003\u0002S_\t)qJ\u001d3fe\u0006QaI]1di&|g.\u00197\u0011\u00055\u00131c\u0001\u0002W3B\u00111dV\u0005\u00031r\u0011a!\u00118z%\u00164\u0007C\u0001.`\u001b\u0005Y&B\u0001/^\u0003\tIwNC\u0001_\u0003\u0011Q\u0017M^1\n\u0005\u0001\\&\u0001D*fe&\fG.\u001b>bE2,\u0017A\u0002\u001fj]&$h\bF\u0001U\u0003E1En\\1u\u0013N4%/Y2uS>t\u0017\r\\\u000b\u0002KB\u0019Q\n\u00014\u0011\u0005m9\u0017B\u00015\u001d\u0005\u00151En\\1u\u0003I1En\\1u\u0013N4%/Y2uS>t\u0017\r\u001c\u0011\u0002%\u0011{WO\u00197f\u0013N4%/Y2uS>t\u0017\r\\\u000b\u0002YB\u0019Q\nA7\u0011\u0005mq\u0017BA8\u001d\u0005\u0019!u.\u001e2mK\u0006\u0019Bi\\;cY\u0016L5O\u0012:bGRLwN\\1mA\u00051\")[4EK\u000eLW.\u00197Jg\u001a\u0013\u0018m\u0019;j_:\fG.F\u0001t!\ri\u0005\u0001\u001e\t\u0003k^t!!\u0011<\n\u0005-b\u0012B\u0001=z\u0005)\u0011\u0015n\u001a#fG&l\u0017\r\u001c\u0006\u0003Wq\tqCQ5h\t\u0016\u001c\u0017.\\1m\u0013N4%/Y2uS>t\u0017\r\u001c\u0011\u0002+\u0005cw-\u001a2sC&\u001c\u0017j\u001d$sC\u000e$\u0018n\u001c8bYV\tQ\u0010E\u0002N\u0001y\u0004\"!T@\n\u0007\u0005\u00051CA\u0005BY\u001e,'M]1jG\u00061\u0012\t\\4fEJ\f\u0017nY%t\rJ\f7\r^5p]\u0006d\u0007%\u0001\nOk6\u0014WM]%t\rJ\f7\r^5p]\u0006dWCAA\u0005!\u0011i\u0005!a\u0003\u0011\u00075\u000bi!C\u0002\u0002\u0010M\u0011aAT;nE\u0016\u0014\u0018a\u0005(v[\n,'/S:Ge\u0006\u001cG/[8oC2\u0004\u0013\u0001\u0006*bi&|g.\u00197Jg\u001a\u0013\u0018m\u0019;j_:\fG.\u0006\u0002\u0002\u0018A!Q\nAA\r!\ri\u00151D\u0005\u0004\u0003;\u0019\"\u0001\u0003*bi&|g.\u00197\u0002+I\u000bG/[8oC2L5O\u0012:bGRLwN\\1mA\u0005)\u0011\r\u001d9msV!\u0011QEA\u0016)\u0011\t9#!\f\u0011\t5\u0003\u0011\u0011\u0006\t\u0004c\u0005-B!B\u001a\u0011\u0005\u0004!\u0004bBA\u0018!\u0001\u000f\u0011qE\u0001\u0003KZD3\u0001EA\u001a!\rY\u0012QG\u0005\u0004\u0003oa\"AB5oY&tW-\u0001\u0007xe&$XMU3qY\u0006\u001cW\r\u0006\u0002\u0002>A!\u0011qHA#\u001b\t\t\tEC\u0002\u0002Du\u000bA\u0001\\1oO&!\u0011qIA!\u0005\u0019y%M[3di\u0002"
)
public interface Fractional extends Field, NRoot, Integral {
   static Fractional apply(final Fractional ev) {
      return Fractional$.MODULE$.apply(ev);
   }

   static Fractional RationalIsFractional() {
      return Fractional$.MODULE$.RationalIsFractional();
   }

   static Fractional NumberIsFractional() {
      return Fractional$.MODULE$.NumberIsFractional();
   }

   static Fractional AlgebraicIsFractional() {
      return Fractional$.MODULE$.AlgebraicIsFractional();
   }

   static Fractional BigDecimalIsFractional() {
      return Fractional$.MODULE$.BigDecimalIsFractional();
   }

   static Fractional DoubleIsFractional() {
      return Fractional$.MODULE$.DoubleIsFractional();
   }

   static Fractional FloatIsFractional() {
      return Fractional$.MODULE$.FloatIsFractional();
   }
}
