package spire.syntax;

import algebra.ring.AdditiveGroup;
import cats.kernel.Order;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\u00053qa\u0001\u0003\u0011\u0002\u0007\u0005\u0011\u0002C\u0003\u0011\u0001\u0011\u0005\u0011\u0003C\u0003\u0016\u0001\u0011\raC\u0001\bJ]R,'O^1m'ftG/\u0019=\u000b\u0005\u00151\u0011AB:z]R\f\u0007PC\u0001\b\u0003\u0015\u0019\b/\u001b:f\u0007\u0001\u0019\"\u0001\u0001\u0006\u0011\u0005-qQ\"\u0001\u0007\u000b\u00035\tQa]2bY\u0006L!a\u0004\u0007\u0003\r\u0005s\u0017PU3g\u0003\u0019!\u0013N\\5uIQ\t!\u0003\u0005\u0002\f'%\u0011A\u0003\u0004\u0002\u0005+:LG/A\u0006j]R,'O^1m\u001fB\u001cXCA\f )\tAr\bF\u0002\u001aQi\u00022AG\u000e\u001e\u001b\u0005!\u0011B\u0001\u000f\u0005\u0005AIe\u000e^3sm\u0006d\u0007k\\5oi>\u00038\u000f\u0005\u0002\u001f?1\u0001A!\u0002\u0011\u0003\u0005\u0004\t#!A!\u0012\u0005\t*\u0003CA\u0006$\u0013\t!CBA\u0004O_RD\u0017N\\4\u0011\u0005-1\u0013BA\u0014\r\u0005\r\te.\u001f\u0005\bS\t\t\t\u0011q\u0001+\u0003-)g/\u001b3f]\u000e,GE\r\u001c\u0011\u0007-:TD\u0004\u0002-i9\u0011QF\r\b\u0003]Ej\u0011a\f\u0006\u0003a!\ta\u0001\u0010:p_Rt\u0014\"A\u0004\n\u0005M2\u0011aB1mO\u0016\u0014'/Y\u0005\u0003kY\nq\u0001]1dW\u0006<WM\u0003\u00024\r%\u0011\u0001(\u000f\u0002\u0006\u001fJ$WM\u001d\u0006\u0003kYBqa\u000f\u0002\u0002\u0002\u0003\u000fA(A\u0006fm&$WM\\2fII:\u0004cA\u0016>;%\u0011a(\u000f\u0002\u000e\u0003\u0012$\u0017\u000e^5wK\u001e\u0013x.\u001e9\t\u000b\u0001\u0013\u0001\u0019A\u000f\u0002\u0003\u0005\u0004"
)
public interface IntervalSyntax {
   // $FF: synthetic method
   static IntervalPointOps intervalOps$(final IntervalSyntax $this, final Object a, final Order evidence$26, final AdditiveGroup evidence$27) {
      return $this.intervalOps(a, evidence$26, evidence$27);
   }

   default IntervalPointOps intervalOps(final Object a, final Order evidence$26, final AdditiveGroup evidence$27) {
      return new IntervalPointOps(a, evidence$26, evidence$27);
   }

   static void $init$(final IntervalSyntax $this) {
   }
}
