package spire.math;

import java.math.MathContext;
import scala.math.BigDecimal;
import scala.math.BigInt;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\u00055a\u0001\u0003\b\u0010!\u0003\r\taD\n\t\u000b\u0005\u0002A\u0011A\u0012\t\u000b\u001d\u0002A\u0011\u0001\u0015\t\u000b9\u0002A\u0011A\u0018\t\u000bQ\u0002A\u0011A\u001b\t\u000bi\u0002A\u0011A\u001e\t\u000b\u0001\u0003A\u0011A!\t\u000b\u0019\u0003A\u0011A$\t\u000b%\u0003A\u0011\u0001&\t\u000ba\u0003A\u0011A-\t\u000by\u0003A\u0011A0\t\u000b\u0011\u0004A\u0011A3\t\u000b)\u0004A\u0011A6\t\u000bA\u0004A\u0011A9\u0003'\r{gN^3si\u0006\u0014G.\u001a+p\t>,(\r\\3\u000b\u0005A\t\u0012\u0001B7bi\"T\u0011AE\u0001\u0006gBL'/Z\n\u0004\u0001QQ\u0002CA\u000b\u0019\u001b\u00051\"\"A\f\u0002\u000bM\u001c\u0017\r\\1\n\u0005e1\"AB!osJ+g\rE\u0002\u001c9yi\u0011aD\u0005\u0003;=\u0011QbQ8om\u0016\u0014H/\u00192mKR{\u0007CA\u000b \u0013\t\u0001cC\u0001\u0004E_V\u0014G.Z\u0001\u0007I%t\u0017\u000e\u001e\u0013\u0004\u0001Q\tA\u0005\u0005\u0002\u0016K%\u0011aE\u0006\u0002\u0005+:LG/\u0001\u0005ge>l')\u001f;f)\tq\u0012\u0006C\u0003+\u0005\u0001\u00071&A\u0001b!\t)B&\u0003\u0002.-\t!!)\u001f;f\u0003%1'o\\7TQ>\u0014H\u000f\u0006\u0002\u001fa!)!f\u0001a\u0001cA\u0011QCM\u0005\u0003gY\u0011Qa\u00155peR\fqA\u001a:p[&sG\u000f\u0006\u0002\u001fm!)!\u0006\u0002a\u0001oA\u0011Q\u0003O\u0005\u0003sY\u00111!\u00138u\u0003!1'o\\7M_:<GC\u0001\u0010=\u0011\u0015QS\u00011\u0001>!\t)b(\u0003\u0002@-\t!Aj\u001c8h\u0003%1'o\\7GY>\fG\u000f\u0006\u0002\u001f\u0005\")!F\u0002a\u0001\u0007B\u0011Q\u0003R\u0005\u0003\u000bZ\u0011QA\u00127pCR\f!B\u001a:p[\u0012{WO\u00197f)\tq\u0002\nC\u0003+\u000f\u0001\u0007a$\u0001\u0006ge>l')[4J]R$\"AH&\t\u000b)B\u0001\u0019\u0001'\u0011\u00055+fB\u0001(T\u001d\ty%+D\u0001Q\u0015\t\t&%\u0001\u0004=e>|GOP\u0005\u0002/%\u0011AKF\u0001\ba\u0006\u001c7.Y4f\u0013\t1vK\u0001\u0004CS\u001eLe\u000e\u001e\u0006\u0003)Z\taB\u001a:p[\nKw\rR3dS6\fG\u000e\u0006\u0002\u001f5\")!&\u0003a\u00017B\u0011Q\nX\u0005\u0003;^\u0013!BQ5h\t\u0016\u001c\u0017.\\1m\u000311'o\\7SCRLwN\\1m)\tq\u0002\rC\u0003+\u0015\u0001\u0007\u0011\r\u0005\u0002\u001cE&\u00111m\u0004\u0002\t%\u0006$\u0018n\u001c8bY\u0006iaM]8n\u00032<WM\u0019:bS\u000e$\"A\b4\t\u000b)Z\u0001\u0019A4\u0011\u0005mA\u0017BA5\u0010\u0005%\tEnZ3ce\u0006L7-\u0001\u0005ge>l'+Z1m)\tqB\u000eC\u0003+\u0019\u0001\u0007Q\u000e\u0005\u0002\u001c]&\u0011qn\u0004\u0002\u0005%\u0016\fG.\u0001\u0005ge>lG+\u001f9f+\t\u00118\u0010F\u0002t\u0003\u0013!\"A\b;\t\u000fUl\u0011\u0011!a\u0002m\u0006QQM^5eK:\u001cW\rJ\u001c\u0011\u0007m9\u00180\u0003\u0002y\u001f\ty1i\u001c8wKJ$\u0018M\u00197f\rJ|W\u000e\u0005\u0002{w2\u0001A!\u0002?\u000e\u0005\u0004i(!\u0001\"\u0012\u0007y\f\u0019\u0001\u0005\u0002\u0016\u007f&\u0019\u0011\u0011\u0001\f\u0003\u000f9{G\u000f[5oOB\u0019Q#!\u0002\n\u0007\u0005\u001daCA\u0002B]fDa!a\u0003\u000e\u0001\u0004I\u0018!\u00012"
)
public interface ConvertableToDouble extends ConvertableTo$mcD$sp {
   // $FF: synthetic method
   static double fromByte$(final ConvertableToDouble $this, final byte a) {
      return $this.fromByte(a);
   }

   default double fromByte(final byte a) {
      return this.fromByte$mcD$sp(a);
   }

   // $FF: synthetic method
   static double fromShort$(final ConvertableToDouble $this, final short a) {
      return $this.fromShort(a);
   }

   default double fromShort(final short a) {
      return this.fromShort$mcD$sp(a);
   }

   // $FF: synthetic method
   static double fromInt$(final ConvertableToDouble $this, final int a) {
      return $this.fromInt(a);
   }

   default double fromInt(final int a) {
      return this.fromInt$mcD$sp(a);
   }

   // $FF: synthetic method
   static double fromLong$(final ConvertableToDouble $this, final long a) {
      return $this.fromLong(a);
   }

   default double fromLong(final long a) {
      return this.fromLong$mcD$sp(a);
   }

   // $FF: synthetic method
   static double fromFloat$(final ConvertableToDouble $this, final float a) {
      return $this.fromFloat(a);
   }

   default double fromFloat(final float a) {
      return this.fromFloat$mcD$sp(a);
   }

   // $FF: synthetic method
   static double fromDouble$(final ConvertableToDouble $this, final double a) {
      return $this.fromDouble(a);
   }

   default double fromDouble(final double a) {
      return this.fromDouble$mcD$sp(a);
   }

   // $FF: synthetic method
   static double fromBigInt$(final ConvertableToDouble $this, final BigInt a) {
      return $this.fromBigInt(a);
   }

   default double fromBigInt(final BigInt a) {
      return this.fromBigInt$mcD$sp(a);
   }

   // $FF: synthetic method
   static double fromBigDecimal$(final ConvertableToDouble $this, final BigDecimal a) {
      return $this.fromBigDecimal(a);
   }

   default double fromBigDecimal(final BigDecimal a) {
      return this.fromBigDecimal$mcD$sp(a);
   }

   // $FF: synthetic method
   static double fromRational$(final ConvertableToDouble $this, final Rational a) {
      return $this.fromRational(a);
   }

   default double fromRational(final Rational a) {
      return this.fromRational$mcD$sp(a);
   }

   // $FF: synthetic method
   static double fromAlgebraic$(final ConvertableToDouble $this, final Algebraic a) {
      return $this.fromAlgebraic(a);
   }

   default double fromAlgebraic(final Algebraic a) {
      return this.fromAlgebraic$mcD$sp(a);
   }

   // $FF: synthetic method
   static double fromReal$(final ConvertableToDouble $this, final Real a) {
      return $this.fromReal(a);
   }

   default double fromReal(final Real a) {
      return this.fromReal$mcD$sp(a);
   }

   // $FF: synthetic method
   static double fromType$(final ConvertableToDouble $this, final Object b, final ConvertableFrom evidence$7) {
      return $this.fromType(b, evidence$7);
   }

   default double fromType(final Object b, final ConvertableFrom evidence$7) {
      return this.fromType$mcD$sp(b, evidence$7);
   }

   // $FF: synthetic method
   static double fromByte$mcD$sp$(final ConvertableToDouble $this, final byte a) {
      return $this.fromByte$mcD$sp(a);
   }

   default double fromByte$mcD$sp(final byte a) {
      return (double)a;
   }

   // $FF: synthetic method
   static double fromShort$mcD$sp$(final ConvertableToDouble $this, final short a) {
      return $this.fromShort$mcD$sp(a);
   }

   default double fromShort$mcD$sp(final short a) {
      return (double)a;
   }

   // $FF: synthetic method
   static double fromInt$mcD$sp$(final ConvertableToDouble $this, final int a) {
      return $this.fromInt$mcD$sp(a);
   }

   default double fromInt$mcD$sp(final int a) {
      return (double)a;
   }

   // $FF: synthetic method
   static double fromLong$mcD$sp$(final ConvertableToDouble $this, final long a) {
      return $this.fromLong$mcD$sp(a);
   }

   default double fromLong$mcD$sp(final long a) {
      return (double)a;
   }

   // $FF: synthetic method
   static double fromFloat$mcD$sp$(final ConvertableToDouble $this, final float a) {
      return $this.fromFloat$mcD$sp(a);
   }

   default double fromFloat$mcD$sp(final float a) {
      return (double)a;
   }

   // $FF: synthetic method
   static double fromDouble$mcD$sp$(final ConvertableToDouble $this, final double a) {
      return $this.fromDouble$mcD$sp(a);
   }

   default double fromDouble$mcD$sp(final double a) {
      return a;
   }

   // $FF: synthetic method
   static double fromBigInt$mcD$sp$(final ConvertableToDouble $this, final BigInt a) {
      return $this.fromBigInt$mcD$sp(a);
   }

   default double fromBigInt$mcD$sp(final BigInt a) {
      return a.toDouble();
   }

   // $FF: synthetic method
   static double fromBigDecimal$mcD$sp$(final ConvertableToDouble $this, final BigDecimal a) {
      return $this.fromBigDecimal$mcD$sp(a);
   }

   default double fromBigDecimal$mcD$sp(final BigDecimal a) {
      return a.toDouble();
   }

   // $FF: synthetic method
   static double fromRational$mcD$sp$(final ConvertableToDouble $this, final Rational a) {
      return $this.fromRational$mcD$sp(a);
   }

   default double fromRational$mcD$sp(final Rational a) {
      return a.toBigDecimal(MathContext.DECIMAL64).toDouble();
   }

   // $FF: synthetic method
   static double fromAlgebraic$mcD$sp$(final ConvertableToDouble $this, final Algebraic a) {
      return $this.fromAlgebraic$mcD$sp(a);
   }

   default double fromAlgebraic$mcD$sp(final Algebraic a) {
      return a.toDouble();
   }

   // $FF: synthetic method
   static double fromReal$mcD$sp$(final ConvertableToDouble $this, final Real a) {
      return $this.fromReal$mcD$sp(a);
   }

   default double fromReal$mcD$sp(final Real a) {
      return a.toDouble();
   }

   // $FF: synthetic method
   static double fromType$mcD$sp$(final ConvertableToDouble $this, final Object b, final ConvertableFrom evidence$7) {
      return $this.fromType$mcD$sp(b, evidence$7);
   }

   default double fromType$mcD$sp(final Object b, final ConvertableFrom evidence$7) {
      return ConvertableFrom$.MODULE$.apply(evidence$7).toDouble(b);
   }

   static void $init$(final ConvertableToDouble $this) {
   }
}
