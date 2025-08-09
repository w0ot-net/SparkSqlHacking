package spire.math;

import cats.kernel.Eq;
import java.lang.invoke.SerializedLambda;
import java.math.MathContext;
import scala.math.BigDecimal;
import scala.math.BigInt;
import scala.reflect.ScalaSignature;
import scala.reflect.ClassTag.;
import spire.algebra.NRoot;
import spire.math.poly.RootFinder$;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005Eb\u0001\u0003\t\u0012!\u0003\r\t!E\u000b\t\u000b\r\u0002A\u0011A\u0013\t\u000b%\u0002A\u0011\u0001\u0016\t\u000bA\u0002A\u0011A\u0019\t\u000bY\u0002A\u0011A\u001c\t\u000bq\u0002A\u0011A\u001f\t\u000b\t\u0003A\u0011A\"\t\u000b!\u0003A\u0011A%\t\u000b9\u0003A\u0011A(\t\u000bu\u0003A\u0011\u00010\t\u000b\r\u0004A\u0011\u00013\t\u000b%\u0004A\u0011\u00016\t\u000b1\u0004A\u0011A7\t\u000bI\u0004A\u0011A:\t\u000ba\u0004A\u0011A=\t\u000f\u0005m\u0001\u0001\"\u0001\u0002\u001e\tA2i\u001c8wKJ$\u0018M\u00197f\rJ|W.\u00117hK\n\u0014\u0018-[2\u000b\u0005I\u0019\u0012\u0001B7bi\"T\u0011\u0001F\u0001\u0006gBL'/Z\n\u0004\u0001Ya\u0002CA\f\u001b\u001b\u0005A\"\"A\r\u0002\u000bM\u001c\u0017\r\\1\n\u0005mA\"AB!osJ+g\rE\u0002\u001e=\u0001j\u0011!E\u0005\u0003?E\u0011qbQ8om\u0016\u0014H/\u00192mK\u001a\u0013x.\u001c\t\u0003;\u0005J!AI\t\u0003\u0013\u0005cw-\u001a2sC&\u001c\u0017A\u0002\u0013j]&$He\u0001\u0001\u0015\u0003\u0019\u0002\"aF\u0014\n\u0005!B\"\u0001B+oSR\fa\u0001^8CsR,GCA\u0016/!\t9B&\u0003\u0002.1\t!!)\u001f;f\u0011\u0015y#\u00011\u0001!\u0003\u0005\t\u0017a\u0002;p'\"|'\u000f\u001e\u000b\u0003eU\u0002\"aF\u001a\n\u0005QB\"!B*i_J$\b\"B\u0018\u0004\u0001\u0004\u0001\u0013!\u0002;p\u0013:$HC\u0001\u001d<!\t9\u0012(\u0003\u0002;1\t\u0019\u0011J\u001c;\t\u000b=\"\u0001\u0019\u0001\u0011\u0002\rQ|Gj\u001c8h)\tq\u0014\t\u0005\u0002\u0018\u007f%\u0011\u0001\t\u0007\u0002\u0005\u0019>tw\rC\u00030\u000b\u0001\u0007\u0001%A\u0004u_\u001acw.\u0019;\u0015\u0005\u0011;\u0005CA\fF\u0013\t1\u0005DA\u0003GY>\fG\u000fC\u00030\r\u0001\u0007\u0001%\u0001\u0005u_\u0012{WO\u00197f)\tQU\n\u0005\u0002\u0018\u0017&\u0011A\n\u0007\u0002\u0007\t>,(\r\\3\t\u000b=:\u0001\u0019\u0001\u0011\u0002\u0011Q|')[4J]R$\"\u0001\u0015/\u0011\u0005EKfB\u0001*X\u001d\t\u0019f+D\u0001U\u0015\t)F%\u0001\u0004=e>|GOP\u0005\u00023%\u0011\u0001\fG\u0001\ba\u0006\u001c7.Y4f\u0013\tQ6L\u0001\u0004CS\u001eLe\u000e\u001e\u0006\u00031bAQa\f\u0005A\u0002\u0001\nA\u0002^8CS\u001e$UmY5nC2$\"a\u00182\u0011\u0005E\u0003\u0017BA1\\\u0005)\u0011\u0015n\u001a#fG&l\u0017\r\u001c\u0005\u0006_%\u0001\r\u0001I\u0001\u000bi>\u0014\u0016\r^5p]\u0006dGCA3i!\tib-\u0003\u0002h#\tA!+\u0019;j_:\fG\u000eC\u00030\u0015\u0001\u0007\u0001%A\u0006u_\u0006cw-\u001a2sC&\u001cGC\u0001\u0011l\u0011\u0015y3\u00021\u0001!\u0003\u0019!xNU3bYR\u0011a.\u001d\t\u0003;=L!\u0001]\t\u0003\tI+\u0017\r\u001c\u0005\u0006_1\u0001\r\u0001I\u0001\ti>tU/\u001c2feR\u0011Ao\u001e\t\u0003;UL!A^\t\u0003\r9+XNY3s\u0011\u0015yS\u00021\u0001!\u0003\u0019!x\u000eV=qKV\u0011!P \u000b\u0004w\u0006eAc\u0001?\u0002\u0010A\u0011QP \u0007\u0001\t\u0019yhB1\u0001\u0002\u0002\t\t!)\u0005\u0003\u0002\u0004\u0005%\u0001cA\f\u0002\u0006%\u0019\u0011q\u0001\r\u0003\u000f9{G\u000f[5oOB\u0019q#a\u0003\n\u0007\u00055\u0001DA\u0002B]fD\u0011\"!\u0005\u000f\u0003\u0003\u0005\u001d!a\u0005\u0002\u0017\u00154\u0018\u000eZ3oG\u0016$#g\u000e\t\u0005;\u0005UA0C\u0002\u0002\u0018E\u0011QbQ8om\u0016\u0014H/\u00192mKR{\u0007\"B\u0018\u000f\u0001\u0004\u0001\u0013\u0001\u0003;p'R\u0014\u0018N\\4\u0015\t\u0005}\u0011q\u0006\t\u0005\u0003C\tIC\u0004\u0003\u0002$\u0005\u0015\u0002CA*\u0019\u0013\r\t9\u0003G\u0001\u0007!J,G-\u001a4\n\t\u0005-\u0012Q\u0006\u0002\u0007'R\u0014\u0018N\\4\u000b\u0007\u0005\u001d\u0002\u0004C\u00030\u001f\u0001\u0007\u0001\u0005"
)
public interface ConvertableFromAlgebraic extends ConvertableFrom {
   // $FF: synthetic method
   static byte toByte$(final ConvertableFromAlgebraic $this, final Algebraic a) {
      return $this.toByte(a);
   }

   default byte toByte(final Algebraic a) {
      return (byte)a.toInt();
   }

   // $FF: synthetic method
   static short toShort$(final ConvertableFromAlgebraic $this, final Algebraic a) {
      return $this.toShort(a);
   }

   default short toShort(final Algebraic a) {
      return (short)a.toInt();
   }

   // $FF: synthetic method
   static int toInt$(final ConvertableFromAlgebraic $this, final Algebraic a) {
      return $this.toInt(a);
   }

   default int toInt(final Algebraic a) {
      return a.toInt();
   }

   // $FF: synthetic method
   static long toLong$(final ConvertableFromAlgebraic $this, final Algebraic a) {
      return $this.toLong(a);
   }

   default long toLong(final Algebraic a) {
      return a.toLong();
   }

   // $FF: synthetic method
   static float toFloat$(final ConvertableFromAlgebraic $this, final Algebraic a) {
      return $this.toFloat(a);
   }

   default float toFloat(final Algebraic a) {
      return (float)a.toDouble();
   }

   // $FF: synthetic method
   static double toDouble$(final ConvertableFromAlgebraic $this, final Algebraic a) {
      return $this.toDouble(a);
   }

   default double toDouble(final Algebraic a) {
      return a.toDouble();
   }

   // $FF: synthetic method
   static BigInt toBigInt$(final ConvertableFromAlgebraic $this, final Algebraic a) {
      return $this.toBigInt(a);
   }

   default BigInt toBigInt(final Algebraic a) {
      return a.toBigInt();
   }

   // $FF: synthetic method
   static BigDecimal toBigDecimal$(final ConvertableFromAlgebraic $this, final Algebraic a) {
      return $this.toBigDecimal(a);
   }

   default BigDecimal toBigDecimal(final Algebraic a) {
      return a.toBigDecimal(MathContext.DECIMAL128);
   }

   // $FF: synthetic method
   static Rational toRational$(final ConvertableFromAlgebraic $this, final Algebraic a) {
      return $this.toRational(a);
   }

   default Rational toRational(final Algebraic a) {
      return (Rational)a.toRational().getOrElse(() -> Rational$.MODULE$.apply(a.toBigDecimal(MathContext.DECIMAL64)));
   }

   // $FF: synthetic method
   static Algebraic toAlgebraic$(final ConvertableFromAlgebraic $this, final Algebraic a) {
      return $this.toAlgebraic(a);
   }

   default Algebraic toAlgebraic(final Algebraic a) {
      return a;
   }

   // $FF: synthetic method
   static Real toReal$(final ConvertableFromAlgebraic $this, final Algebraic a) {
      return $this.toReal(a);
   }

   default Real toReal(final Algebraic a) {
      return (Real)a.evaluateWith(Real$.MODULE$.algebra(), Real$.MODULE$.algebra(), RootFinder$.MODULE$.RealRootFinder(), Real$.MODULE$.algebra(), .MODULE$.apply(Real.class), Real$.MODULE$.algebra());
   }

   // $FF: synthetic method
   static Number toNumber$(final ConvertableFromAlgebraic $this, final Algebraic a) {
      return $this.toNumber(a);
   }

   default Number toNumber(final Algebraic a) {
      return (Number)a.toRational().map((x$1) -> Number$.MODULE$.apply(x$1)).getOrElse(() -> (Number)a.evaluateWith(Number$.MODULE$.NumberAlgebra(), (NRoot)Number$.MODULE$.NumberAlgebra(), RootFinder$.MODULE$.NumberRootFinder(), (Eq)Number$.MODULE$.NumberAlgebra(), .MODULE$.apply(Number.class), ConvertableTo$.MODULE$.ConvertableToNumber()));
   }

   // $FF: synthetic method
   static Object toType$(final ConvertableFromAlgebraic $this, final Algebraic a, final ConvertableTo evidence$27) {
      return $this.toType(a, evidence$27);
   }

   default Object toType(final Algebraic a, final ConvertableTo evidence$27) {
      return ConvertableTo$.MODULE$.apply(evidence$27).fromAlgebraic(a);
   }

   // $FF: synthetic method
   static String toString$(final ConvertableFromAlgebraic $this, final Algebraic a) {
      return $this.toString(a);
   }

   default String toString(final Algebraic a) {
      return a.toString();
   }

   static void $init$(final ConvertableFromAlgebraic $this) {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
