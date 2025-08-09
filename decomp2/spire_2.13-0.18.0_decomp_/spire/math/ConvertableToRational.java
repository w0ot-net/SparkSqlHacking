package spire.math;

import java.lang.invoke.SerializedLambda;
import java.math.MathContext;
import scala.math.BigDecimal;
import scala.math.BigInt;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\u00055a\u0001\u0003\b\u0010!\u0003\r\taD\n\t\u000b\u0005\u0002A\u0011A\u0012\t\u000b\u001d\u0002A\u0011\u0001\u0015\t\u000b9\u0002A\u0011A\u0018\t\u000bQ\u0002A\u0011A\u001b\t\u000bi\u0002A\u0011A\u001e\t\u000b\u0001\u0003A\u0011A!\t\u000b\u0019\u0003A\u0011A$\t\u000b1\u0003A\u0011A'\t\u000bm\u0003A\u0011\u0001/\t\u000b\u0005\u0004A\u0011\u00012\t\u000b\u0011\u0004A\u0011A3\t\u000b)\u0004A\u0011A6\t\u000bA\u0004A\u0011A9\u0003+\r{gN^3si\u0006\u0014G.\u001a+p%\u0006$\u0018n\u001c8bY*\u0011\u0001#E\u0001\u0005[\u0006$\bNC\u0001\u0013\u0003\u0015\u0019\b/\u001b:f'\r\u0001AC\u0007\t\u0003+ai\u0011A\u0006\u0006\u0002/\u0005)1oY1mC&\u0011\u0011D\u0006\u0002\u0007\u0003:L(+\u001a4\u0011\u0007mab$D\u0001\u0010\u0013\tirBA\u0007D_:4XM\u001d;bE2,Gk\u001c\t\u00037}I!\u0001I\b\u0003\u0011I\u000bG/[8oC2\fa\u0001J5oSR$3\u0001\u0001\u000b\u0002IA\u0011Q#J\u0005\u0003MY\u0011A!\u00168ji\u0006AaM]8n\u0005f$X\r\u0006\u0002\u001fS!)!F\u0001a\u0001W\u0005\t\u0011\r\u0005\u0002\u0016Y%\u0011QF\u0006\u0002\u0005\u0005f$X-A\u0005ge>l7\u000b[8siR\u0011a\u0004\r\u0005\u0006U\r\u0001\r!\r\t\u0003+IJ!a\r\f\u0003\u000bMCwN\u001d;\u0002\u000f\u0019\u0014x.\\%oiR\u0011aD\u000e\u0005\u0006U\u0011\u0001\ra\u000e\t\u0003+aJ!!\u000f\f\u0003\u0007%sG/\u0001\u0005ge>lGj\u001c8h)\tqB\bC\u0003+\u000b\u0001\u0007Q\b\u0005\u0002\u0016}%\u0011qH\u0006\u0002\u0005\u0019>tw-A\u0005ge>lg\t\\8biR\u0011aD\u0011\u0005\u0006U\u0019\u0001\ra\u0011\t\u0003+\u0011K!!\u0012\f\u0003\u000b\u0019cw.\u0019;\u0002\u0015\u0019\u0014x.\u001c#pk\ndW\r\u0006\u0002\u001f\u0011\")!f\u0002a\u0001\u0013B\u0011QCS\u0005\u0003\u0017Z\u0011a\u0001R8vE2,\u0017A\u00034s_6\u0014\u0015nZ%oiR\u0011aD\u0014\u0005\u0006U!\u0001\ra\u0014\t\u0003!bs!!\u0015,\u000f\u0005I+V\"A*\u000b\u0005Q\u0013\u0013A\u0002\u001fs_>$h(C\u0001\u0018\u0013\t9f#A\u0004qC\u000e\\\u0017mZ3\n\u0005eS&A\u0002\"jO&sGO\u0003\u0002X-\u0005qaM]8n\u0005&<G)Z2j[\u0006dGC\u0001\u0010^\u0011\u0015Q\u0013\u00021\u0001_!\t\u0001v,\u0003\u0002a5\nQ!)[4EK\u000eLW.\u00197\u0002\u0019\u0019\u0014x.\u001c*bi&|g.\u00197\u0015\u0005y\u0019\u0007\"\u0002\u0016\u000b\u0001\u0004q\u0012!\u00044s_6\fEnZ3ce\u0006L7\r\u0006\u0002\u001fM\")!f\u0003a\u0001OB\u00111\u0004[\u0005\u0003S>\u0011\u0011\"\u00117hK\n\u0014\u0018-[2\u0002\u0011\u0019\u0014x.\u001c*fC2$\"A\b7\t\u000b)b\u0001\u0019A7\u0011\u0005mq\u0017BA8\u0010\u0005\u0011\u0011V-\u00197\u0002\u0011\u0019\u0014x.\u001c+za\u0016,\"A]>\u0015\u0007M\fI\u0001\u0006\u0002\u001fi\"9Q/DA\u0001\u0002\b1\u0018aC3wS\u0012,gnY3%cA\u00022aG<z\u0013\tAxBA\bD_:4XM\u001d;bE2,gI]8n!\tQ8\u0010\u0004\u0001\u0005\u000bql!\u0019A?\u0003\u0003\t\u000b2A`A\u0002!\t)r0C\u0002\u0002\u0002Y\u0011qAT8uQ&tw\rE\u0002\u0016\u0003\u000bI1!a\u0002\u0017\u0005\r\te.\u001f\u0005\u0007\u0003\u0017i\u0001\u0019A=\u0002\u0003\t\u0004"
)
public interface ConvertableToRational extends ConvertableTo {
   // $FF: synthetic method
   static Rational fromByte$(final ConvertableToRational $this, final byte a) {
      return $this.fromByte(a);
   }

   default Rational fromByte(final byte a) {
      return Rational$.MODULE$.apply(a);
   }

   // $FF: synthetic method
   static Rational fromShort$(final ConvertableToRational $this, final short a) {
      return $this.fromShort(a);
   }

   default Rational fromShort(final short a) {
      return Rational$.MODULE$.apply(a);
   }

   // $FF: synthetic method
   static Rational fromInt$(final ConvertableToRational $this, final int a) {
      return $this.fromInt(a);
   }

   default Rational fromInt(final int a) {
      return Rational$.MODULE$.apply(a);
   }

   // $FF: synthetic method
   static Rational fromLong$(final ConvertableToRational $this, final long a) {
      return $this.fromLong(a);
   }

   default Rational fromLong(final long a) {
      return Rational$.MODULE$.apply(a);
   }

   // $FF: synthetic method
   static Rational fromFloat$(final ConvertableToRational $this, final float a) {
      return $this.fromFloat(a);
   }

   default Rational fromFloat(final float a) {
      return Rational$.MODULE$.apply(a);
   }

   // $FF: synthetic method
   static Rational fromDouble$(final ConvertableToRational $this, final double a) {
      return $this.fromDouble(a);
   }

   default Rational fromDouble(final double a) {
      return Rational$.MODULE$.apply(a);
   }

   // $FF: synthetic method
   static Rational fromBigInt$(final ConvertableToRational $this, final BigInt a) {
      return $this.fromBigInt(a);
   }

   default Rational fromBigInt(final BigInt a) {
      return Rational$.MODULE$.apply(a);
   }

   // $FF: synthetic method
   static Rational fromBigDecimal$(final ConvertableToRational $this, final BigDecimal a) {
      return $this.fromBigDecimal(a);
   }

   default Rational fromBigDecimal(final BigDecimal a) {
      return Rational$.MODULE$.apply(a);
   }

   // $FF: synthetic method
   static Rational fromRational$(final ConvertableToRational $this, final Rational a) {
      return $this.fromRational(a);
   }

   default Rational fromRational(final Rational a) {
      return a;
   }

   // $FF: synthetic method
   static Rational fromAlgebraic$(final ConvertableToRational $this, final Algebraic a) {
      return $this.fromAlgebraic(a);
   }

   default Rational fromAlgebraic(final Algebraic a) {
      return (Rational)a.toRational().getOrElse(() -> Rational$.MODULE$.apply(a.toBigDecimal(MathContext.DECIMAL64)));
   }

   // $FF: synthetic method
   static Rational fromReal$(final ConvertableToRational $this, final Real a) {
      return $this.fromReal(a);
   }

   default Rational fromReal(final Real a) {
      return a.toRational();
   }

   // $FF: synthetic method
   static Rational fromType$(final ConvertableToRational $this, final Object b, final ConvertableFrom evidence$10) {
      return $this.fromType(b, evidence$10);
   }

   default Rational fromType(final Object b, final ConvertableFrom evidence$10) {
      return ConvertableFrom$.MODULE$.apply(evidence$10).toRational(b);
   }

   static void $init$(final ConvertableToRational $this) {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return var0.lambdaDeserialize<invokedynamic>(var0);
   }
}
