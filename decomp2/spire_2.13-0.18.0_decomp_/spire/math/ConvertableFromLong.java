package spire.math;

import scala.math.BigDecimal;
import scala.math.BigInt;
import scala.package.;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005Eb\u0001\u0003\t\u0012!\u0003\r\t!E\u000b\t\u000b\r\u0002A\u0011A\u0013\t\u000b%\u0002A\u0011\u0001\u0016\t\u000bA\u0002A\u0011A\u0019\t\u000bY\u0002A\u0011A\u001c\t\u000bq\u0002A\u0011A\u001f\t\u000b}\u0002A\u0011\u0001!\t\u000b\u0015\u0003A\u0011\u0001$\t\u000b-\u0003A\u0011\u0001'\t\u000bi\u0003A\u0011A.\t\u000b\u0001\u0004A\u0011A1\t\u000b\u0019\u0004A\u0011A4\t\u000b1\u0004A\u0011A7\t\u000bI\u0004A\u0011A:\t\u000ba\u0004A\u0011A=\t\u000f\u0005m\u0001\u0001\"\u0001\u0002\u001e\t\u00192i\u001c8wKJ$\u0018M\u00197f\rJ|W\u000eT8oO*\u0011!cE\u0001\u0005[\u0006$\bNC\u0001\u0015\u0003\u0015\u0019\b/\u001b:f'\r\u0001a\u0003\b\t\u0003/ii\u0011\u0001\u0007\u0006\u00023\u0005)1oY1mC&\u00111\u0004\u0007\u0002\u0007\u0003:L(+\u001a4\u0011\u0007uq\u0002%D\u0001\u0012\u0013\ty\u0012CA\bD_:4XM\u001d;bE2,gI]8n!\t9\u0012%\u0003\u0002#1\t!Aj\u001c8h\u0003\u0019!\u0013N\\5uI\r\u0001A#\u0001\u0014\u0011\u0005]9\u0013B\u0001\u0015\u0019\u0005\u0011)f.\u001b;\u0002\rQ|')\u001f;f)\tYc\u0006\u0005\u0002\u0018Y%\u0011Q\u0006\u0007\u0002\u0005\u0005f$X\rC\u00030\u0005\u0001\u0007\u0001%A\u0001b\u0003\u001d!xn\u00155peR$\"AM\u001b\u0011\u0005]\u0019\u0014B\u0001\u001b\u0019\u0005\u0015\u0019\u0006n\u001c:u\u0011\u0015y3\u00011\u0001!\u0003\u0015!x.\u00138u)\tA4\b\u0005\u0002\u0018s%\u0011!\b\u0007\u0002\u0004\u0013:$\b\"B\u0018\u0005\u0001\u0004\u0001\u0013A\u0002;p\u0019>tw\r\u0006\u0002!}!)q&\u0002a\u0001A\u00059Ao\u001c$m_\u0006$HCA!E!\t9\")\u0003\u0002D1\t)a\t\\8bi\")qF\u0002a\u0001A\u0005AAo\u001c#pk\ndW\r\u0006\u0002H\u0015B\u0011q\u0003S\u0005\u0003\u0013b\u0011a\u0001R8vE2,\u0007\"B\u0018\b\u0001\u0004\u0001\u0013\u0001\u0003;p\u0005&<\u0017J\u001c;\u0015\u00055K\u0006C\u0001(W\u001d\tyEK\u0004\u0002Q'6\t\u0011K\u0003\u0002SI\u00051AH]8pizJ\u0011!G\u0005\u0003+b\tq\u0001]1dW\u0006<W-\u0003\u0002X1\n1!)[4J]RT!!\u0016\r\t\u000b=B\u0001\u0019\u0001\u0011\u0002\u0019Q|')[4EK\u000eLW.\u00197\u0015\u0005q{\u0006C\u0001(^\u0013\tq\u0006L\u0001\u0006CS\u001e$UmY5nC2DQaL\u0005A\u0002\u0001\n!\u0002^8SCRLwN\\1m)\t\u0011W\r\u0005\u0002\u001eG&\u0011A-\u0005\u0002\t%\u0006$\u0018n\u001c8bY\")qF\u0003a\u0001A\u0005YAo\\!mO\u0016\u0014'/Y5d)\tA7\u000e\u0005\u0002\u001eS&\u0011!.\u0005\u0002\n\u00032<WM\u0019:bS\u000eDQaL\u0006A\u0002\u0001\na\u0001^8SK\u0006dGC\u00018r!\tir.\u0003\u0002q#\t!!+Z1m\u0011\u0015yC\u00021\u0001!\u0003!!xNT;nE\u0016\u0014HC\u0001;x!\tiR/\u0003\u0002w#\t1a*^7cKJDQaL\u0007A\u0002\u0001\na\u0001^8UsB,WC\u0001>\u007f)\rY\u0018\u0011\u0004\u000b\u0004y\u0006=\u0001CA?\u007f\u0019\u0001!aa \bC\u0002\u0005\u0005!!\u0001\"\u0012\t\u0005\r\u0011\u0011\u0002\t\u0004/\u0005\u0015\u0011bAA\u00041\t9aj\u001c;iS:<\u0007cA\f\u0002\f%\u0019\u0011Q\u0002\r\u0003\u0007\u0005s\u0017\u0010C\u0005\u0002\u00129\t\t\u0011q\u0001\u0002\u0014\u0005YQM^5eK:\u001cW\r\n\u001a2!\u0011i\u0012Q\u0003?\n\u0007\u0005]\u0011CA\u0007D_:4XM\u001d;bE2,Gk\u001c\u0005\u0006_9\u0001\r\u0001I\u0001\ti>\u001cFO]5oOR!\u0011qDA\u0018!\u0011\t\t#!\u000b\u000f\t\u0005\r\u0012Q\u0005\t\u0003!bI1!a\n\u0019\u0003\u0019\u0001&/\u001a3fM&!\u00111FA\u0017\u0005\u0019\u0019FO]5oO*\u0019\u0011q\u0005\r\t\u000b=z\u0001\u0019\u0001\u0011"
)
public interface ConvertableFromLong extends ConvertableFrom$mcJ$sp {
   // $FF: synthetic method
   static byte toByte$(final ConvertableFromLong $this, final long a) {
      return $this.toByte(a);
   }

   default byte toByte(final long a) {
      return this.toByte$mcJ$sp(a);
   }

   // $FF: synthetic method
   static short toShort$(final ConvertableFromLong $this, final long a) {
      return $this.toShort(a);
   }

   default short toShort(final long a) {
      return this.toShort$mcJ$sp(a);
   }

   // $FF: synthetic method
   static int toInt$(final ConvertableFromLong $this, final long a) {
      return $this.toInt(a);
   }

   default int toInt(final long a) {
      return this.toInt$mcJ$sp(a);
   }

   // $FF: synthetic method
   static long toLong$(final ConvertableFromLong $this, final long a) {
      return $this.toLong(a);
   }

   default long toLong(final long a) {
      return this.toLong$mcJ$sp(a);
   }

   // $FF: synthetic method
   static float toFloat$(final ConvertableFromLong $this, final long a) {
      return $this.toFloat(a);
   }

   default float toFloat(final long a) {
      return this.toFloat$mcJ$sp(a);
   }

   // $FF: synthetic method
   static double toDouble$(final ConvertableFromLong $this, final long a) {
      return $this.toDouble(a);
   }

   default double toDouble(final long a) {
      return this.toDouble$mcJ$sp(a);
   }

   // $FF: synthetic method
   static BigInt toBigInt$(final ConvertableFromLong $this, final long a) {
      return $this.toBigInt(a);
   }

   default BigInt toBigInt(final long a) {
      return this.toBigInt$mcJ$sp(a);
   }

   // $FF: synthetic method
   static BigDecimal toBigDecimal$(final ConvertableFromLong $this, final long a) {
      return $this.toBigDecimal(a);
   }

   default BigDecimal toBigDecimal(final long a) {
      return this.toBigDecimal$mcJ$sp(a);
   }

   // $FF: synthetic method
   static Rational toRational$(final ConvertableFromLong $this, final long a) {
      return $this.toRational(a);
   }

   default Rational toRational(final long a) {
      return this.toRational$mcJ$sp(a);
   }

   // $FF: synthetic method
   static Algebraic toAlgebraic$(final ConvertableFromLong $this, final long a) {
      return $this.toAlgebraic(a);
   }

   default Algebraic toAlgebraic(final long a) {
      return this.toAlgebraic$mcJ$sp(a);
   }

   // $FF: synthetic method
   static Real toReal$(final ConvertableFromLong $this, final long a) {
      return $this.toReal(a);
   }

   default Real toReal(final long a) {
      return this.toReal$mcJ$sp(a);
   }

   // $FF: synthetic method
   static Number toNumber$(final ConvertableFromLong $this, final long a) {
      return $this.toNumber(a);
   }

   default Number toNumber(final long a) {
      return this.toNumber$mcJ$sp(a);
   }

   // $FF: synthetic method
   static Object toType$(final ConvertableFromLong $this, final long a, final ConvertableTo evidence$21) {
      return $this.toType(a, evidence$21);
   }

   default Object toType(final long a, final ConvertableTo evidence$21) {
      return this.toType$mcJ$sp(a, evidence$21);
   }

   // $FF: synthetic method
   static String toString$(final ConvertableFromLong $this, final long a) {
      return $this.toString(a);
   }

   default String toString(final long a) {
      return this.toString$mcJ$sp(a);
   }

   // $FF: synthetic method
   static byte toByte$mcJ$sp$(final ConvertableFromLong $this, final long a) {
      return $this.toByte$mcJ$sp(a);
   }

   default byte toByte$mcJ$sp(final long a) {
      return (byte)((int)a);
   }

   // $FF: synthetic method
   static short toShort$mcJ$sp$(final ConvertableFromLong $this, final long a) {
      return $this.toShort$mcJ$sp(a);
   }

   default short toShort$mcJ$sp(final long a) {
      return (short)((int)a);
   }

   // $FF: synthetic method
   static int toInt$mcJ$sp$(final ConvertableFromLong $this, final long a) {
      return $this.toInt$mcJ$sp(a);
   }

   default int toInt$mcJ$sp(final long a) {
      return (int)a;
   }

   // $FF: synthetic method
   static long toLong$mcJ$sp$(final ConvertableFromLong $this, final long a) {
      return $this.toLong$mcJ$sp(a);
   }

   default long toLong$mcJ$sp(final long a) {
      return a;
   }

   // $FF: synthetic method
   static float toFloat$mcJ$sp$(final ConvertableFromLong $this, final long a) {
      return $this.toFloat$mcJ$sp(a);
   }

   default float toFloat$mcJ$sp(final long a) {
      return (float)a;
   }

   // $FF: synthetic method
   static double toDouble$mcJ$sp$(final ConvertableFromLong $this, final long a) {
      return $this.toDouble$mcJ$sp(a);
   }

   default double toDouble$mcJ$sp(final long a) {
      return (double)a;
   }

   // $FF: synthetic method
   static BigInt toBigInt$mcJ$sp$(final ConvertableFromLong $this, final long a) {
      return $this.toBigInt$mcJ$sp(a);
   }

   default BigInt toBigInt$mcJ$sp(final long a) {
      return .MODULE$.BigInt().apply(a);
   }

   // $FF: synthetic method
   static BigDecimal toBigDecimal$mcJ$sp$(final ConvertableFromLong $this, final long a) {
      return $this.toBigDecimal$mcJ$sp(a);
   }

   default BigDecimal toBigDecimal$mcJ$sp(final long a) {
      return .MODULE$.BigDecimal().apply(a);
   }

   // $FF: synthetic method
   static Rational toRational$mcJ$sp$(final ConvertableFromLong $this, final long a) {
      return $this.toRational$mcJ$sp(a);
   }

   default Rational toRational$mcJ$sp(final long a) {
      return Rational$.MODULE$.apply(a);
   }

   // $FF: synthetic method
   static Algebraic toAlgebraic$mcJ$sp$(final ConvertableFromLong $this, final long a) {
      return $this.toAlgebraic$mcJ$sp(a);
   }

   default Algebraic toAlgebraic$mcJ$sp(final long a) {
      return Algebraic$.MODULE$.apply(a);
   }

   // $FF: synthetic method
   static Real toReal$mcJ$sp$(final ConvertableFromLong $this, final long a) {
      return $this.toReal$mcJ$sp(a);
   }

   default Real toReal$mcJ$sp(final long a) {
      return Real$.MODULE$.apply(a);
   }

   // $FF: synthetic method
   static Number toNumber$mcJ$sp$(final ConvertableFromLong $this, final long a) {
      return $this.toNumber$mcJ$sp(a);
   }

   default Number toNumber$mcJ$sp(final long a) {
      return Number$.MODULE$.apply(a);
   }

   // $FF: synthetic method
   static Object toType$mcJ$sp$(final ConvertableFromLong $this, final long a, final ConvertableTo evidence$21) {
      return $this.toType$mcJ$sp(a, evidence$21);
   }

   default Object toType$mcJ$sp(final long a, final ConvertableTo evidence$21) {
      return ConvertableTo$.MODULE$.apply(evidence$21).fromLong(a);
   }

   // $FF: synthetic method
   static String toString$mcJ$sp$(final ConvertableFromLong $this, final long a) {
      return $this.toString$mcJ$sp(a);
   }

   default String toString$mcJ$sp(final long a) {
      return Long.toString(a);
   }

   static void $init$(final ConvertableFromLong $this) {
   }
}
