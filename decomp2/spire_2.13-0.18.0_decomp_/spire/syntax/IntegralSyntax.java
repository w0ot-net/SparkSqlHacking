package spire.syntax;

import scala.reflect.ScalaSignature;
import spire.math.Integral;
import spire.math.IntegralOps;

@ScalaSignature(
   bytes = "\u0006\u0005y2qa\u0001\u0003\u0011\u0002\u0007\u0005\u0011\u0002C\u0003\u001e\u0001\u0011\u0005a\u0004C\u0003#\u0001\u0011\r1E\u0001\bJ]R,wM]1m'ftG/\u0019=\u000b\u0005\u00151\u0011AB:z]R\f\u0007PC\u0001\b\u0003\u0015\u0019\b/\u001b:f\u0007\u0001\u0019b\u0001\u0001\u0006\u0011)]Q\u0002CA\u0006\u000f\u001b\u0005a!\"A\u0007\u0002\u000bM\u001c\u0017\r\\1\n\u0005=a!AB!osJ+g\r\u0005\u0002\u0012%5\tA!\u0003\u0002\u0014\t\t\u0019R)^2mS\u0012,\u0017M\u001c*j]\u001e\u001c\u0016P\u001c;bqB\u0011\u0011#F\u0005\u0003-\u0011\u0011QcQ8om\u0016\u0014H/\u00192mK\u001a\u0013x.\\*z]R\f\u0007\u0010\u0005\u0002\u00121%\u0011\u0011\u0004\u0002\u0002\f\u001fJ$WM]*z]R\f\u0007\u0010\u0005\u0002\u00127%\u0011A\u0004\u0002\u0002\r'&<g.\u001a3Ts:$\u0018\r_\u0001\u0007I%t\u0017\u000e\u001e\u0013\u0015\u0003}\u0001\"a\u0003\u0011\n\u0005\u0005b!\u0001B+oSR\f1\"\u001b8uK\u001e\u0014\u0018\r\\(qgV\u0011AE\f\u000b\u0003Kq\"\"AJ\u001c\u0011\u0007\u001dRC&D\u0001)\u0015\tIc!\u0001\u0003nCRD\u0017BA\u0016)\u0005-Ie\u000e^3he\u0006dw\n]:\u0011\u00055rC\u0002\u0001\u0003\u0006_\t\u0011\r\u0001\r\u0002\u0002\u0003F\u0011\u0011\u0007\u000e\t\u0003\u0017IJ!a\r\u0007\u0003\u000f9{G\u000f[5oOB\u00111\"N\u0005\u0003m1\u00111!\u00118z\u0011\u001dA$!!AA\u0004e\n1\"\u001a<jI\u0016t7-\u001a\u00133qA\u0019qE\u000f\u0017\n\u0005mB#\u0001C%oi\u0016<'/\u00197\t\u000bu\u0012\u0001\u0019\u0001\u0017\u0002\u0003\u0005\u0004"
)
public interface IntegralSyntax extends EuclideanRingSyntax, ConvertableFromSyntax, SignedSyntax {
   // $FF: synthetic method
   static IntegralOps integralOps$(final IntegralSyntax $this, final Object a, final Integral evidence$28) {
      return $this.integralOps(a, evidence$28);
   }

   default IntegralOps integralOps(final Object a, final Integral evidence$28) {
      return new IntegralOps(a, evidence$28);
   }

   static void $init$(final IntegralSyntax $this) {
   }
}
