package spire.syntax;

import scala.reflect.ScalaSignature;
import spire.math.ConvertableFrom;

@ScalaSignature(
   bytes = "\u0006\u0005I2qa\u0001\u0003\u0011\u0002\u0007\u0005\u0011\u0002C\u0003\u0011\u0001\u0011\u0005\u0011\u0003C\u0003\u0016\u0001\u0011\raCA\u000bD_:4XM\u001d;bE2,gI]8n'ftG/\u0019=\u000b\u0005\u00151\u0011AB:z]R\f\u0007PC\u0001\b\u0003\u0015\u0019\b/\u001b:f\u0007\u0001\u0019\"\u0001\u0001\u0006\u0011\u0005-qQ\"\u0001\u0007\u000b\u00035\tQa]2bY\u0006L!a\u0004\u0007\u0003\r\u0005s\u0017PU3g\u0003\u0019!\u0013N\\5uIQ\t!\u0003\u0005\u0002\f'%\u0011A\u0003\u0004\u0002\u0005+:LG/\u0001\bd_:4XM\u001d;bE2,w\n]:\u0016\u0005]yBC\u0001\r1)\tI\u0002\u0006E\u0002\u001b7ui\u0011\u0001B\u0005\u00039\u0011\u0011!cQ8om\u0016\u0014H/\u00192mK\u001a\u0013x.\\(qgB\u0011ad\b\u0007\u0001\t\u0015\u0001#A1\u0001\"\u0005\u0005\t\u0015C\u0001\u0012&!\tY1%\u0003\u0002%\u0019\t9aj\u001c;iS:<\u0007CA\u0006'\u0013\t9CBA\u0002B]fDq!\u000b\u0002\u0002\u0002\u0003\u000f!&A\u0006fm&$WM\\2fIIJ\u0004cA\u0016/;5\tAF\u0003\u0002.\r\u0005!Q.\u0019;i\u0013\tyCFA\bD_:4XM\u001d;bE2,gI]8n\u0011\u0015\t$\u00011\u0001\u001e\u0003\u0005\t\u0007"
)
public interface ConvertableFromSyntax {
   // $FF: synthetic method
   static ConvertableFromOps convertableOps$(final ConvertableFromSyntax $this, final Object a, final ConvertableFrom evidence$29) {
      return $this.convertableOps(a, evidence$29);
   }

   default ConvertableFromOps convertableOps(final Object a, final ConvertableFrom evidence$29) {
      return new ConvertableFromOps(a, evidence$29);
   }

   static void $init$(final ConvertableFromSyntax $this) {
   }
}
