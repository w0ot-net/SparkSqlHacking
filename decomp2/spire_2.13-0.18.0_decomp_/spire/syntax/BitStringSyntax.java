package spire.syntax;

import scala.reflect.ScalaSignature;
import spire.math.BitString;

@ScalaSignature(
   bytes = "\u0006\u0005I2qa\u0001\u0003\u0011\u0002\u0007\u0005\u0011\u0002C\u0003\u0011\u0001\u0011\u0005\u0011\u0003C\u0003\u0016\u0001\u0011\raCA\bCSR\u001cFO]5oONKh\u000e^1y\u0015\t)a!\u0001\u0004ts:$\u0018\r\u001f\u0006\u0002\u000f\u0005)1\u000f]5sK\u000e\u00011C\u0001\u0001\u000b!\tYa\"D\u0001\r\u0015\u0005i\u0011!B:dC2\f\u0017BA\b\r\u0005\u0019\te.\u001f*fM\u00061A%\u001b8ji\u0012\"\u0012A\u0005\t\u0003\u0017MI!\u0001\u0006\u0007\u0003\tUs\u0017\u000e^\u0001\rE&$8\u000b\u001e:j]\u001e|\u0005o]\u000b\u0003/}!\"\u0001\u0007\u0019\u0015\u0005eA\u0003c\u0001\u000e\u001c;5\tA!\u0003\u0002\u001d\t\ta!)\u001b;TiJLgnZ(qgB\u0011ad\b\u0007\u0001\t\u0015\u0001#A1\u0001\"\u0005\u0005\t\u0015C\u0001\u0012&!\tY1%\u0003\u0002%\u0019\t9aj\u001c;iS:<\u0007CA\u0006'\u0013\t9CBA\u0002B]fDq!\u000b\u0002\u0002\u0002\u0003\u000f!&A\u0006fm&$WM\\2fII*\u0004cA\u0016/;5\tAF\u0003\u0002.\r\u0005!Q.\u0019;i\u0013\tyCFA\u0005CSR\u001cFO]5oO\")\u0011G\u0001a\u0001;\u0005\t\u0011\r"
)
public interface BitStringSyntax {
   // $FF: synthetic method
   static BitStringOps bitStringOps$(final BitStringSyntax $this, final Object a, final BitString evidence$25) {
      return $this.bitStringOps(a, evidence$25);
   }

   default BitStringOps bitStringOps(final Object a, final BitString evidence$25) {
      return new BitStringOps(a, evidence$25);
   }

   static void $init$(final BitStringSyntax $this) {
   }
}
