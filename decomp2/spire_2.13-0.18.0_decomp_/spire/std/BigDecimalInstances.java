package spire.std;

import algebra.ring.Field;
import java.math.MathContext;
import scala.package.;
import scala.reflect.ScalaSignature;
import spire.math.NumberTag;

@ScalaSignature(
   bytes = "\u0006\u0005!4qAB\u0004\u0011\u0002\u0007\u0005A\u0002C\u0003\u0014\u0001\u0011\u0005A\u0003C\u0004\u0019\u0001\t\u0007IqA\r\t\u000b\u0015\u0003A1\u0001$\t\u000fU\u0003\u0011\u0013!C\u0001-\"9\u0011\r\u0001b\u0001\n\u000f\u0011'a\u0005\"jO\u0012+7-[7bY&s7\u000f^1oG\u0016\u001c(B\u0001\u0005\n\u0003\r\u0019H\u000f\u001a\u0006\u0002\u0015\u0005)1\u000f]5sK\u000e\u00011C\u0001\u0001\u000e!\tq\u0011#D\u0001\u0010\u0015\u0005\u0001\u0012!B:dC2\f\u0017B\u0001\n\u0010\u0005\u0019\te.\u001f*fM\u00061A%\u001b8ji\u0012\"\u0012!\u0006\t\u0003\u001dYI!aF\b\u0003\tUs\u0017\u000e^\u0001\u0012\u0005&<G)Z2j[\u0006d\u0017\t\\4fEJ\fW#\u0001\u000e\u0013\u000fmiR'\u000f\u001f@\u0005\u001a!A\u0004\u0001\u0001\u001b\u00051a$/\u001a4j]\u0016lWM\u001c;?!\rq\"&\f\b\u0003?\u001dr!\u0001I\u0013\u000f\u0005\u0005\"S\"\u0001\u0012\u000b\u0005\rZ\u0011A\u0002\u001fs_>$h(C\u0001\u000b\u0013\t1\u0013\"A\u0004bY\u001e,'M]1\n\u0005!J\u0013a\u00029bG.\fw-\u001a\u0006\u0003M%I!a\u000b\u0017\u0003\u000b\u0019KW\r\u001c3\u000b\u0005!J\u0003C\u0001\u00183\u001d\ty\u0013G\u0004\u0002\"a%\t\u0001#\u0003\u0002)\u001f%\u00111\u0007\u000e\u0002\u000b\u0005&<G)Z2j[\u0006d'B\u0001\u0015\u0010!\r1t'L\u0007\u0002S%\u0011\u0001(\u000b\u0002\u0006\u001dJ{w\u000e\u001e\t\u0004mij\u0013BA\u001e*\u0005)I5OU1uS>t\u0017\r\u001c\t\u0004=uj\u0013B\u0001 -\u0005Y!&/\u001e8dCR,G\rR5wSNLwN\\\"SS:<\u0007c\u0001\u0010A[%\u0011\u0011\t\f\u0002\u0007'&<g.\u001a3\u0011\u0007y\u0019U&\u0003\u0002EY\t)qJ\u001d3fe\u0006\u0001\")[4EK\u000eLW.\u00197JgR\u0013\u0018n\u001a\u000b\u0003\u000f.\u0003\"\u0001S%\u000e\u0003\u001dI!AS\u0004\u0003!\tKw\rR3dS6\fG.S:Ue&<\u0007b\u0002'\u0004!\u0003\u0005\u001d!T\u0001\u0003[\u000e\u0004\"AT*\u000e\u0003=S!\u0001U)\u0002\t5\fG\u000f\u001b\u0006\u0002%\u0006!!.\u0019<b\u0013\t!vJA\u0006NCRD7i\u001c8uKb$\u0018A\u0007\"jO\u0012+7-[7bY&\u001bHK]5hI\u0011,g-Y;mi\u0012\nT#A,+\u00055C6&A-\u0011\u0005i{V\"A.\u000b\u0005qk\u0016!C;oG\",7m[3e\u0015\tqv\"\u0001\u0006b]:|G/\u0019;j_:L!\u0001Y.\u0003#Ut7\r[3dW\u0016$g+\u0019:jC:\u001cW-A\u0007CS\u001e$UmY5nC2$\u0016mZ\u000b\u0002GB\u0019AMZ\u0017\u000e\u0003\u0015T!\u0001U\u0005\n\u0005\u001d,'!\u0003(v[\n,'\u000fV1h\u0001"
)
public interface BigDecimalInstances {
   void spire$std$BigDecimalInstances$_setter_$BigDecimalAlgebra_$eq(final Field x$1);

   void spire$std$BigDecimalInstances$_setter_$BigDecimalTag_$eq(final NumberTag x$1);

   Field BigDecimalAlgebra();

   // $FF: synthetic method
   static BigDecimalIsTrig BigDecimalIsTrig$(final BigDecimalInstances $this, final MathContext mc) {
      return $this.BigDecimalIsTrig(mc);
   }

   default BigDecimalIsTrig BigDecimalIsTrig(final MathContext mc) {
      return new BigDecimalIsTrig(mc);
   }

   // $FF: synthetic method
   static MathContext BigDecimalIsTrig$default$1$(final BigDecimalInstances $this) {
      return $this.BigDecimalIsTrig$default$1();
   }

   default MathContext BigDecimalIsTrig$default$1() {
      return .MODULE$.BigDecimal().defaultMathContext();
   }

   NumberTag BigDecimalTag();

   static void $init$(final BigDecimalInstances $this) {
      $this.spire$std$BigDecimalInstances$_setter_$BigDecimalAlgebra_$eq(new BigDecimalAlgebra());
      $this.spire$std$BigDecimalInstances$_setter_$BigDecimalTag_$eq(new NumberTag.LargeTag(NumberTag.Approximate$.MODULE$, .MODULE$.BigDecimal().apply(0)));
   }
}
