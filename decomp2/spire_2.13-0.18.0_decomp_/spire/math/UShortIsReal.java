package spire.math;

import scala.math.BigInt;
import scala.reflect.ScalaSignature;
import spire.algebra.IsIntegral;

@ScalaSignature(
   bytes = "\u0006\u0005e2\u0001\u0002B\u0003\u0011\u0002\u0007\u0005Q!\u0003\u0005\u0006;\u0001!\ta\b\u0005\u0006G\u0001!\t\u0001\n\u0005\u0006U\u0001!\ta\u000b\u0002\r+NCwN\u001d;JgJ+\u0017\r\u001c\u0006\u0003\r\u001d\tA!\\1uQ*\t\u0001\"A\u0003ta&\u0014Xm\u0005\u0003\u0001\u0015AQ\u0002CA\u0006\u000f\u001b\u0005a!\"A\u0007\u0002\u000bM\u001c\u0017\r\\1\n\u0005=a!AB!osJ+g\rE\u0002\u0012)Yi\u0011A\u0005\u0006\u0003'\u001d\tq!\u00197hK\n\u0014\u0018-\u0003\u0002\u0016%\tQ\u0011j]%oi\u0016<'/\u00197\u0011\u0005]AR\"A\u0003\n\u0005e)!AB+TQ>\u0014H\u000f\u0005\u0002\u00187%\u0011A$\u0002\u0002\u0018+NCwN\u001d;UeVt7-\u0019;fI\u0012Kg/[:j_:\fa\u0001J5oSR$3\u0001\u0001\u000b\u0002AA\u00111\"I\u0005\u0003E1\u0011A!\u00168ji\u0006AAo\u001c#pk\ndW\r\u0006\u0002&QA\u00111BJ\u0005\u0003O1\u0011a\u0001R8vE2,\u0007\"B\u0015\u0003\u0001\u00041\u0012!\u00018\u0002\u0011Q|')[4J]R$\"\u0001\f\u001d\u0011\u00055*dB\u0001\u00184\u001d\ty#'D\u00011\u0015\t\td$\u0001\u0004=e>|GOP\u0005\u0002\u001b%\u0011A\u0007D\u0001\ba\u0006\u001c7.Y4f\u0013\t1tG\u0001\u0004CS\u001eLe\u000e\u001e\u0006\u0003i1AQ!K\u0002A\u0002Y\u0001"
)
public interface UShortIsReal extends IsIntegral, UShortTruncatedDivision {
   // $FF: synthetic method
   static double toDouble$(final UShortIsReal $this, final char n) {
      return $this.toDouble(n);
   }

   default double toDouble(final char n) {
      return UShort$.MODULE$.toDouble$extension(n);
   }

   // $FF: synthetic method
   static BigInt toBigInt$(final UShortIsReal $this, final char n) {
      return $this.toBigInt(n);
   }

   default BigInt toBigInt(final char n) {
      return UShort$.MODULE$.toBigInt$extension(n);
   }

   static void $init$(final UShortIsReal $this) {
   }
}
