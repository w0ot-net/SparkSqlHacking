package spire.math;

import scala.math.BigDecimal;
import scala.math.BigInt;
import scala.package.;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\u00055a\u0001\u0003\b\u0010!\u0003\r\taD\n\t\u000b-\u0002A\u0011\u0001\u0017\t\u000bA\u0002A\u0011A\u0019\t\u000b]\u0002A\u0011\u0001\u001d\t\u000bu\u0002A\u0011\u0001 \t\u000b\r\u0003A\u0011\u0001#\t\u000b%\u0003A\u0011\u0001&\t\u000b=\u0003A\u0011\u0001)\t\u000bU\u0003A\u0011\u0001,\t\u000ba\u0003A\u0011A-\t\u000by\u0003A\u0011A0\t\u000b\u0011\u0004A\u0011A3\t\u000b)\u0004A\u0011A6\t\u000bA\u0004A\u0011A9\u0003'\r{gN^3si\u0006\u0014G.\u001a+p\u0005&<\u0017J\u001c;\u000b\u0005A\t\u0012\u0001B7bi\"T\u0011AE\u0001\u0006gBL'/Z\n\u0004\u0001QQ\u0002CA\u000b\u0019\u001b\u00051\"\"A\f\u0002\u000bM\u001c\u0017\r\\1\n\u0005e1\"AB!osJ+g\rE\u0002\u001c9yi\u0011aD\u0005\u0003;=\u0011QbQ8om\u0016\u0014H/\u00192mKR{\u0007CA\u0010)\u001d\t\u0001cE\u0004\u0002\"K5\t!E\u0003\u0002$I\u00051AH]8piz\u001a\u0001!C\u0001\u0018\u0013\t9c#A\u0004qC\u000e\\\u0017mZ3\n\u0005%R#A\u0002\"jO&sGO\u0003\u0002(-\u00051A%\u001b8ji\u0012\"\u0012!\f\t\u0003+9J!a\f\f\u0003\tUs\u0017\u000e^\u0001\tMJ|WNQ=uKR\u0011aD\r\u0005\u0006g\t\u0001\r\u0001N\u0001\u0002CB\u0011Q#N\u0005\u0003mY\u0011AAQ=uK\u0006IaM]8n'\"|'\u000f\u001e\u000b\u0003=eBQaM\u0002A\u0002i\u0002\"!F\u001e\n\u0005q2\"!B*i_J$\u0018a\u00024s_6Le\u000e\u001e\u000b\u0003=}BQa\r\u0003A\u0002\u0001\u0003\"!F!\n\u0005\t3\"aA%oi\u0006AaM]8n\u0019>tw\r\u0006\u0002\u001f\u000b\")1'\u0002a\u0001\rB\u0011QcR\u0005\u0003\u0011Z\u0011A\u0001T8oO\u0006IaM]8n\r2|\u0017\r\u001e\u000b\u0003=-CQa\r\u0004A\u00021\u0003\"!F'\n\u000593\"!\u0002$m_\u0006$\u0018A\u00034s_6$u.\u001e2mKR\u0011a$\u0015\u0005\u0006g\u001d\u0001\rA\u0015\t\u0003+MK!\u0001\u0016\f\u0003\r\u0011{WO\u00197f\u0003)1'o\\7CS\u001eLe\u000e\u001e\u000b\u0003=]CQa\r\u0005A\u0002y\taB\u001a:p[\nKw\rR3dS6\fG\u000e\u0006\u0002\u001f5\")1'\u0003a\u00017B\u0011q\u0004X\u0005\u0003;*\u0012!BQ5h\t\u0016\u001c\u0017.\\1m\u000311'o\\7SCRLwN\\1m)\tq\u0002\rC\u00034\u0015\u0001\u0007\u0011\r\u0005\u0002\u001cE&\u00111m\u0004\u0002\t%\u0006$\u0018n\u001c8bY\u0006iaM]8n\u00032<WM\u0019:bS\u000e$\"A\b4\t\u000bMZ\u0001\u0019A4\u0011\u0005mA\u0017BA5\u0010\u0005%\tEnZ3ce\u0006L7-\u0001\u0005ge>l'+Z1m)\tqB\u000eC\u00034\u0019\u0001\u0007Q\u000e\u0005\u0002\u001c]&\u0011qn\u0004\u0002\u0005%\u0016\fG.\u0001\u0005ge>lG+\u001f9f+\t\u00118\u0010F\u0002t\u0003\u0013!\"A\b;\t\u000fUl\u0011\u0011!a\u0002m\u0006QQM^5eK:\u001cW\r\n\u001d\u0011\u0007m9\u00180\u0003\u0002y\u001f\ty1i\u001c8wKJ$\u0018M\u00197f\rJ|W\u000e\u0005\u0002{w2\u0001A!\u0002?\u000e\u0005\u0004i(!\u0001\"\u0012\u0007y\f\u0019\u0001\u0005\u0002\u0016\u007f&\u0019\u0011\u0011\u0001\f\u0003\u000f9{G\u000f[5oOB\u0019Q#!\u0002\n\u0007\u0005\u001daCA\u0002B]fDa!a\u0003\u000e\u0001\u0004I\u0018!\u00012"
)
public interface ConvertableToBigInt extends ConvertableTo {
   // $FF: synthetic method
   static BigInt fromByte$(final ConvertableToBigInt $this, final byte a) {
      return $this.fromByte(a);
   }

   default BigInt fromByte(final byte a) {
      return .MODULE$.BigInt().apply(a);
   }

   // $FF: synthetic method
   static BigInt fromShort$(final ConvertableToBigInt $this, final short a) {
      return $this.fromShort(a);
   }

   default BigInt fromShort(final short a) {
      return .MODULE$.BigInt().apply(a);
   }

   // $FF: synthetic method
   static BigInt fromInt$(final ConvertableToBigInt $this, final int a) {
      return $this.fromInt(a);
   }

   default BigInt fromInt(final int a) {
      return .MODULE$.BigInt().apply(a);
   }

   // $FF: synthetic method
   static BigInt fromLong$(final ConvertableToBigInt $this, final long a) {
      return $this.fromLong(a);
   }

   default BigInt fromLong(final long a) {
      return .MODULE$.BigInt().apply(a);
   }

   // $FF: synthetic method
   static BigInt fromFloat$(final ConvertableToBigInt $this, final float a) {
      return $this.fromFloat(a);
   }

   default BigInt fromFloat(final float a) {
      return .MODULE$.BigInt().apply((long)a);
   }

   // $FF: synthetic method
   static BigInt fromDouble$(final ConvertableToBigInt $this, final double a) {
      return $this.fromDouble(a);
   }

   default BigInt fromDouble(final double a) {
      return .MODULE$.BigInt().apply((long)a);
   }

   // $FF: synthetic method
   static BigInt fromBigInt$(final ConvertableToBigInt $this, final BigInt a) {
      return $this.fromBigInt(a);
   }

   default BigInt fromBigInt(final BigInt a) {
      return a;
   }

   // $FF: synthetic method
   static BigInt fromBigDecimal$(final ConvertableToBigInt $this, final BigDecimal a) {
      return $this.fromBigDecimal(a);
   }

   default BigInt fromBigDecimal(final BigDecimal a) {
      return a.toBigInt();
   }

   // $FF: synthetic method
   static BigInt fromRational$(final ConvertableToBigInt $this, final Rational a) {
      return $this.fromRational(a);
   }

   default BigInt fromRational(final Rational a) {
      return a.toBigInt();
   }

   // $FF: synthetic method
   static BigInt fromAlgebraic$(final ConvertableToBigInt $this, final Algebraic a) {
      return $this.fromAlgebraic(a);
   }

   default BigInt fromAlgebraic(final Algebraic a) {
      return a.toBigInt();
   }

   // $FF: synthetic method
   static BigInt fromReal$(final ConvertableToBigInt $this, final Real a) {
      return $this.fromReal(a);
   }

   default BigInt fromReal(final Real a) {
      return this.fromRational(a.toRational());
   }

   // $FF: synthetic method
   static BigInt fromType$(final ConvertableToBigInt $this, final Object b, final ConvertableFrom evidence$8) {
      return $this.fromType(b, evidence$8);
   }

   default BigInt fromType(final Object b, final ConvertableFrom evidence$8) {
      return ConvertableFrom$.MODULE$.apply(evidence$8).toBigInt(b);
   }

   static void $init$(final ConvertableToBigInt $this) {
   }
}
