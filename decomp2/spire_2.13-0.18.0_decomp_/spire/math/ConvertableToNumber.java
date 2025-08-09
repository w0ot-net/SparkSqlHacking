package spire.math;

import java.lang.invoke.SerializedLambda;
import java.math.MathContext;
import scala.math.BigDecimal;
import scala.math.BigInt;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005Ma\u0001\u0003\b\u0010!\u0003\r\taD\n\t\u000b\u0005\u0002A\u0011A\u0012\t\u000b\u001d\u0002A\u0011\u0001\u0015\t\u000b9\u0002A\u0011A\u0018\t\u000bQ\u0002A\u0011A\u001b\t\u000bi\u0002A\u0011A\u001e\t\u000b\u0001\u0003A\u0011A!\t\u000b\u0019\u0003A\u0011A$\t\u000b1\u0003A\u0011A'\t\u000bm\u0003A\u0011\u0001/\t\u000b\u0005\u0004A\u0011\u00012\t\u000b\u001d\u0004A\u0011\u00015\t\u000b5\u0004A\u0011\u00018\t\u000bM\u0004A\u0011\u0001;\u0003'\r{gN^3si\u0006\u0014G.\u001a+p\u001dVl'-\u001a:\u000b\u0005A\t\u0012\u0001B7bi\"T\u0011AE\u0001\u0006gBL'/Z\n\u0004\u0001QQ\u0002CA\u000b\u0019\u001b\u00051\"\"A\f\u0002\u000bM\u001c\u0017\r\\1\n\u0005e1\"AB!osJ+g\rE\u0002\u001c9yi\u0011aD\u0005\u0003;=\u0011QbQ8om\u0016\u0014H/\u00192mKR{\u0007CA\u000e \u0013\t\u0001sB\u0001\u0004Ok6\u0014WM]\u0001\u0007I%t\u0017\u000e\u001e\u0013\u0004\u0001Q\tA\u0005\u0005\u0002\u0016K%\u0011aE\u0006\u0002\u0005+:LG/\u0001\u0005ge>l')\u001f;f)\tq\u0012\u0006C\u0003+\u0005\u0001\u00071&A\u0001b!\t)B&\u0003\u0002.-\t!!)\u001f;f\u0003%1'o\\7TQ>\u0014H\u000f\u0006\u0002\u001fa!)!f\u0001a\u0001cA\u0011QCM\u0005\u0003gY\u0011Qa\u00155peR\fqA\u001a:p[&sG\u000f\u0006\u0002\u001fm!)!\u0006\u0002a\u0001oA\u0011Q\u0003O\u0005\u0003sY\u00111!\u00138u\u0003!1'o\\7M_:<GC\u0001\u0010=\u0011\u0015QS\u00011\u0001>!\t)b(\u0003\u0002@-\t!Aj\u001c8h\u0003%1'o\\7GY>\fG\u000f\u0006\u0002\u001f\u0005\")!F\u0002a\u0001\u0007B\u0011Q\u0003R\u0005\u0003\u000bZ\u0011QA\u00127pCR\f!B\u001a:p[\u0012{WO\u00197f)\tq\u0002\nC\u0003+\u000f\u0001\u0007\u0011\n\u0005\u0002\u0016\u0015&\u00111J\u0006\u0002\u0007\t>,(\r\\3\u0002\u0015\u0019\u0014x.\u001c\"jO&sG\u000f\u0006\u0002\u001f\u001d\")!\u0006\u0003a\u0001\u001fB\u0011\u0001\u000b\u0017\b\u0003#Zs!AU+\u000e\u0003MS!\u0001\u0016\u0012\u0002\rq\u0012xn\u001c;?\u0013\u00059\u0012BA,\u0017\u0003\u001d\u0001\u0018mY6bO\u0016L!!\u0017.\u0003\r\tKw-\u00138u\u0015\t9f#\u0001\bge>l')[4EK\u000eLW.\u00197\u0015\u0005yi\u0006\"\u0002\u0016\n\u0001\u0004q\u0006C\u0001)`\u0013\t\u0001'L\u0001\u0006CS\u001e$UmY5nC2\fAB\u001a:p[J\u000bG/[8oC2$\"AH2\t\u000b)R\u0001\u0019\u00013\u0011\u0005m)\u0017B\u00014\u0010\u0005!\u0011\u0016\r^5p]\u0006d\u0017!\u00044s_6\fEnZ3ce\u0006L7\r\u0006\u0002\u001fS\")!f\u0003a\u0001UB\u00111d[\u0005\u0003Y>\u0011\u0011\"\u00117hK\n\u0014\u0018-[2\u0002\u0011\u0019\u0014x.\u001c*fC2$\"AH8\t\u000b)b\u0001\u0019\u00019\u0011\u0005m\t\u0018B\u0001:\u0010\u0005\u0011\u0011V-\u00197\u0002\u0011\u0019\u0014x.\u001c+za\u0016,\"!\u001e@\u0015\u0007Y\fy\u0001\u0006\u0002\u001fo\"9\u00010DA\u0001\u0002\bI\u0018aC3wS\u0012,gnY3%cQ\u00022a\u0007>}\u0013\tYxBA\bD_:4XM\u001d;bE2,gI]8n!\tih\u0010\u0004\u0001\u0005\r}l!\u0019AA\u0001\u0005\u0005\u0011\u0015\u0003BA\u0002\u0003\u0013\u00012!FA\u0003\u0013\r\t9A\u0006\u0002\b\u001d>$\b.\u001b8h!\r)\u00121B\u0005\u0004\u0003\u001b1\"aA!os\"1\u0011\u0011C\u0007A\u0002q\f\u0011A\u0019"
)
public interface ConvertableToNumber extends ConvertableTo {
   // $FF: synthetic method
   static Number fromByte$(final ConvertableToNumber $this, final byte a) {
      return $this.fromByte(a);
   }

   default Number fromByte(final byte a) {
      return Number$.MODULE$.apply(a);
   }

   // $FF: synthetic method
   static Number fromShort$(final ConvertableToNumber $this, final short a) {
      return $this.fromShort(a);
   }

   default Number fromShort(final short a) {
      return Number$.MODULE$.apply(a);
   }

   // $FF: synthetic method
   static Number fromInt$(final ConvertableToNumber $this, final int a) {
      return $this.fromInt(a);
   }

   default Number fromInt(final int a) {
      return Number$.MODULE$.apply(a);
   }

   // $FF: synthetic method
   static Number fromLong$(final ConvertableToNumber $this, final long a) {
      return $this.fromLong(a);
   }

   default Number fromLong(final long a) {
      return Number$.MODULE$.apply(a);
   }

   // $FF: synthetic method
   static Number fromFloat$(final ConvertableToNumber $this, final float a) {
      return $this.fromFloat(a);
   }

   default Number fromFloat(final float a) {
      return Number$.MODULE$.apply(a);
   }

   // $FF: synthetic method
   static Number fromDouble$(final ConvertableToNumber $this, final double a) {
      return $this.fromDouble(a);
   }

   default Number fromDouble(final double a) {
      return Number$.MODULE$.apply(a);
   }

   // $FF: synthetic method
   static Number fromBigInt$(final ConvertableToNumber $this, final BigInt a) {
      return $this.fromBigInt(a);
   }

   default Number fromBigInt(final BigInt a) {
      return Number$.MODULE$.apply(a);
   }

   // $FF: synthetic method
   static Number fromBigDecimal$(final ConvertableToNumber $this, final BigDecimal a) {
      return $this.fromBigDecimal(a);
   }

   default Number fromBigDecimal(final BigDecimal a) {
      return Number$.MODULE$.apply(a);
   }

   // $FF: synthetic method
   static Number fromRational$(final ConvertableToNumber $this, final Rational a) {
      return $this.fromRational(a);
   }

   default Number fromRational(final Rational a) {
      return Number$.MODULE$.apply(a);
   }

   // $FF: synthetic method
   static Number fromAlgebraic$(final ConvertableToNumber $this, final Algebraic a) {
      return $this.fromAlgebraic(a);
   }

   default Number fromAlgebraic(final Algebraic a) {
      return Number$.MODULE$.apply((Rational)a.toRational().getOrElse(() -> Rational$.MODULE$.apply(a.toBigDecimal(MathContext.DECIMAL64))));
   }

   // $FF: synthetic method
   static Number fromReal$(final ConvertableToNumber $this, final Real a) {
      return $this.fromReal(a);
   }

   default Number fromReal(final Real a) {
      return Number$.MODULE$.apply(a.toRational());
   }

   // $FF: synthetic method
   static Number fromType$(final ConvertableToNumber $this, final Object b, final ConvertableFrom evidence$14) {
      return $this.fromType(b, evidence$14);
   }

   default Number fromType(final Object b, final ConvertableFrom evidence$14) {
      return Number$.MODULE$.apply(ConvertableFrom$.MODULE$.apply(evidence$14).toDouble(b));
   }

   static void $init$(final ConvertableToNumber $this) {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return var0.lambdaDeserialize<invokedynamic>(var0);
   }
}
