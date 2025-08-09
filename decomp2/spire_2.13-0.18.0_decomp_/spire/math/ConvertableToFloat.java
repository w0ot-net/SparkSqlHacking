package spire.math;

import java.math.MathContext;
import scala.math.BigDecimal;
import scala.math.BigInt;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\u00055a\u0001\u0003\b\u0010!\u0003\r\taD\n\t\u000b\u0005\u0002A\u0011A\u0012\t\u000b\u001d\u0002A\u0011\u0001\u0015\t\u000b9\u0002A\u0011A\u0018\t\u000bQ\u0002A\u0011A\u001b\t\u000bi\u0002A\u0011A\u001e\t\u000b\u0001\u0003A\u0011A!\t\u000b\r\u0003A\u0011\u0001#\t\u000b%\u0003A\u0011\u0001&\t\u000ba\u0003A\u0011A-\t\u000by\u0003A\u0011A0\t\u000b\u0011\u0004A\u0011A3\t\u000b)\u0004A\u0011A6\t\u000bA\u0004A\u0011A9\u0003%\r{gN^3si\u0006\u0014G.\u001a+p\r2|\u0017\r\u001e\u0006\u0003!E\tA!\\1uQ*\t!#A\u0003ta&\u0014XmE\u0002\u0001)i\u0001\"!\u0006\r\u000e\u0003YQ\u0011aF\u0001\u0006g\u000e\fG.Y\u0005\u00033Y\u0011a!\u00118z%\u00164\u0007cA\u000e\u001d=5\tq\"\u0003\u0002\u001e\u001f\ti1i\u001c8wKJ$\u0018M\u00197f)>\u0004\"!F\u0010\n\u0005\u00012\"!\u0002$m_\u0006$\u0018A\u0002\u0013j]&$He\u0001\u0001\u0015\u0003\u0011\u0002\"!F\u0013\n\u0005\u00192\"\u0001B+oSR\f\u0001B\u001a:p[\nKH/\u001a\u000b\u0003=%BQA\u000b\u0002A\u0002-\n\u0011!\u0019\t\u0003+1J!!\f\f\u0003\t\tKH/Z\u0001\nMJ|Wn\u00155peR$\"A\b\u0019\t\u000b)\u001a\u0001\u0019A\u0019\u0011\u0005U\u0011\u0014BA\u001a\u0017\u0005\u0015\u0019\u0006n\u001c:u\u0003\u001d1'o\\7J]R$\"A\b\u001c\t\u000b)\"\u0001\u0019A\u001c\u0011\u0005UA\u0014BA\u001d\u0017\u0005\rIe\u000e^\u0001\tMJ|W\u000eT8oOR\u0011a\u0004\u0010\u0005\u0006U\u0015\u0001\r!\u0010\t\u0003+yJ!a\u0010\f\u0003\t1{gnZ\u0001\nMJ|WN\u00127pCR$\"A\b\"\t\u000b)2\u0001\u0019\u0001\u0010\u0002\u0015\u0019\u0014x.\u001c#pk\ndW\r\u0006\u0002\u001f\u000b\")!f\u0002a\u0001\rB\u0011QcR\u0005\u0003\u0011Z\u0011a\u0001R8vE2,\u0017A\u00034s_6\u0014\u0015nZ%oiR\u0011ad\u0013\u0005\u0006U!\u0001\r\u0001\u0014\t\u0003\u001bVs!AT*\u000f\u0005=\u0013V\"\u0001)\u000b\u0005E\u0013\u0013A\u0002\u001fs_>$h(C\u0001\u0018\u0013\t!f#A\u0004qC\u000e\\\u0017mZ3\n\u0005Y;&A\u0002\"jO&sGO\u0003\u0002U-\u0005qaM]8n\u0005&<G)Z2j[\u0006dGC\u0001\u0010[\u0011\u0015Q\u0013\u00021\u0001\\!\tiE,\u0003\u0002^/\nQ!)[4EK\u000eLW.\u00197\u0002\u0019\u0019\u0014x.\u001c*bi&|g.\u00197\u0015\u0005y\u0001\u0007\"\u0002\u0016\u000b\u0001\u0004\t\u0007CA\u000ec\u0013\t\u0019wB\u0001\u0005SCRLwN\\1m\u000351'o\\7BY\u001e,'M]1jGR\u0011aD\u001a\u0005\u0006U-\u0001\ra\u001a\t\u00037!L!![\b\u0003\u0013\u0005cw-\u001a2sC&\u001c\u0017\u0001\u00034s_6\u0014V-\u00197\u0015\u0005ya\u0007\"\u0002\u0016\r\u0001\u0004i\u0007CA\u000eo\u0013\tywB\u0001\u0003SK\u0006d\u0017\u0001\u00034s_6$\u0016\u0010]3\u0016\u0005I\\HcA:\u0002\nQ\u0011a\u0004\u001e\u0005\bk6\t\t\u0011q\u0001w\u0003))g/\u001b3f]\u000e,GE\u000e\t\u00047]L\u0018B\u0001=\u0010\u0005=\u0019uN\u001c<feR\f'\r\\3Ge>l\u0007C\u0001>|\u0019\u0001!Q\u0001`\u0007C\u0002u\u0014\u0011AQ\t\u0004}\u0006\r\u0001CA\u000b\u0000\u0013\r\t\tA\u0006\u0002\b\u001d>$\b.\u001b8h!\r)\u0012QA\u0005\u0004\u0003\u000f1\"aA!os\"1\u00111B\u0007A\u0002e\f\u0011A\u0019"
)
public interface ConvertableToFloat extends ConvertableTo$mcF$sp {
   // $FF: synthetic method
   static float fromByte$(final ConvertableToFloat $this, final byte a) {
      return $this.fromByte(a);
   }

   default float fromByte(final byte a) {
      return this.fromByte$mcF$sp(a);
   }

   // $FF: synthetic method
   static float fromShort$(final ConvertableToFloat $this, final short a) {
      return $this.fromShort(a);
   }

   default float fromShort(final short a) {
      return this.fromShort$mcF$sp(a);
   }

   // $FF: synthetic method
   static float fromInt$(final ConvertableToFloat $this, final int a) {
      return $this.fromInt(a);
   }

   default float fromInt(final int a) {
      return this.fromInt$mcF$sp(a);
   }

   // $FF: synthetic method
   static float fromLong$(final ConvertableToFloat $this, final long a) {
      return $this.fromLong(a);
   }

   default float fromLong(final long a) {
      return this.fromLong$mcF$sp(a);
   }

   // $FF: synthetic method
   static float fromFloat$(final ConvertableToFloat $this, final float a) {
      return $this.fromFloat(a);
   }

   default float fromFloat(final float a) {
      return this.fromFloat$mcF$sp(a);
   }

   // $FF: synthetic method
   static float fromDouble$(final ConvertableToFloat $this, final double a) {
      return $this.fromDouble(a);
   }

   default float fromDouble(final double a) {
      return this.fromDouble$mcF$sp(a);
   }

   // $FF: synthetic method
   static float fromBigInt$(final ConvertableToFloat $this, final BigInt a) {
      return $this.fromBigInt(a);
   }

   default float fromBigInt(final BigInt a) {
      return this.fromBigInt$mcF$sp(a);
   }

   // $FF: synthetic method
   static float fromBigDecimal$(final ConvertableToFloat $this, final BigDecimal a) {
      return $this.fromBigDecimal(a);
   }

   default float fromBigDecimal(final BigDecimal a) {
      return this.fromBigDecimal$mcF$sp(a);
   }

   // $FF: synthetic method
   static float fromRational$(final ConvertableToFloat $this, final Rational a) {
      return $this.fromRational(a);
   }

   default float fromRational(final Rational a) {
      return this.fromRational$mcF$sp(a);
   }

   // $FF: synthetic method
   static float fromAlgebraic$(final ConvertableToFloat $this, final Algebraic a) {
      return $this.fromAlgebraic(a);
   }

   default float fromAlgebraic(final Algebraic a) {
      return this.fromAlgebraic$mcF$sp(a);
   }

   // $FF: synthetic method
   static float fromReal$(final ConvertableToFloat $this, final Real a) {
      return $this.fromReal(a);
   }

   default float fromReal(final Real a) {
      return this.fromReal$mcF$sp(a);
   }

   // $FF: synthetic method
   static float fromType$(final ConvertableToFloat $this, final Object b, final ConvertableFrom evidence$6) {
      return $this.fromType(b, evidence$6);
   }

   default float fromType(final Object b, final ConvertableFrom evidence$6) {
      return this.fromType$mcF$sp(b, evidence$6);
   }

   // $FF: synthetic method
   static float fromByte$mcF$sp$(final ConvertableToFloat $this, final byte a) {
      return $this.fromByte$mcF$sp(a);
   }

   default float fromByte$mcF$sp(final byte a) {
      return (float)a;
   }

   // $FF: synthetic method
   static float fromShort$mcF$sp$(final ConvertableToFloat $this, final short a) {
      return $this.fromShort$mcF$sp(a);
   }

   default float fromShort$mcF$sp(final short a) {
      return (float)a;
   }

   // $FF: synthetic method
   static float fromInt$mcF$sp$(final ConvertableToFloat $this, final int a) {
      return $this.fromInt$mcF$sp(a);
   }

   default float fromInt$mcF$sp(final int a) {
      return (float)a;
   }

   // $FF: synthetic method
   static float fromLong$mcF$sp$(final ConvertableToFloat $this, final long a) {
      return $this.fromLong$mcF$sp(a);
   }

   default float fromLong$mcF$sp(final long a) {
      return (float)a;
   }

   // $FF: synthetic method
   static float fromFloat$mcF$sp$(final ConvertableToFloat $this, final float a) {
      return $this.fromFloat$mcF$sp(a);
   }

   default float fromFloat$mcF$sp(final float a) {
      return a;
   }

   // $FF: synthetic method
   static float fromDouble$mcF$sp$(final ConvertableToFloat $this, final double a) {
      return $this.fromDouble$mcF$sp(a);
   }

   default float fromDouble$mcF$sp(final double a) {
      return (float)a;
   }

   // $FF: synthetic method
   static float fromBigInt$mcF$sp$(final ConvertableToFloat $this, final BigInt a) {
      return $this.fromBigInt$mcF$sp(a);
   }

   default float fromBigInt$mcF$sp(final BigInt a) {
      return a.toFloat();
   }

   // $FF: synthetic method
   static float fromBigDecimal$mcF$sp$(final ConvertableToFloat $this, final BigDecimal a) {
      return $this.fromBigDecimal$mcF$sp(a);
   }

   default float fromBigDecimal$mcF$sp(final BigDecimal a) {
      return a.toFloat();
   }

   // $FF: synthetic method
   static float fromRational$mcF$sp$(final ConvertableToFloat $this, final Rational a) {
      return $this.fromRational$mcF$sp(a);
   }

   default float fromRational$mcF$sp(final Rational a) {
      return a.toBigDecimal(MathContext.DECIMAL64).toFloat();
   }

   // $FF: synthetic method
   static float fromAlgebraic$mcF$sp$(final ConvertableToFloat $this, final Algebraic a) {
      return $this.fromAlgebraic$mcF$sp(a);
   }

   default float fromAlgebraic$mcF$sp(final Algebraic a) {
      return a.toFloat();
   }

   // $FF: synthetic method
   static float fromReal$mcF$sp$(final ConvertableToFloat $this, final Real a) {
      return $this.fromReal$mcF$sp(a);
   }

   default float fromReal$mcF$sp(final Real a) {
      return a.toFloat();
   }

   // $FF: synthetic method
   static float fromType$mcF$sp$(final ConvertableToFloat $this, final Object b, final ConvertableFrom evidence$6) {
      return $this.fromType$mcF$sp(b, evidence$6);
   }

   default float fromType$mcF$sp(final Object b, final ConvertableFrom evidence$6) {
      return ConvertableFrom$.MODULE$.apply(evidence$6).toFloat(b);
   }

   static void $init$(final ConvertableToFloat $this) {
   }
}
