package spire.math;

import scala.math.BigDecimal;
import scala.math.BigInt;
import scala.package.;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005Eb\u0001\u0003\t\u0012!\u0003\r\t!E\u000b\t\u000b\r\u0002A\u0011A\u0013\t\u000b%\u0002A\u0011\u0001\u0016\t\u000bA\u0002A\u0011A\u0019\t\u000bY\u0002A\u0011A\u001c\t\u000bq\u0002A\u0011A\u001f\t\u000b\t\u0003A\u0011A\"\t\u000b!\u0003A\u0011A%\t\u000b9\u0003A\u0011A(\t\u000bu\u0003A\u0011\u00010\t\u000b\r\u0004A\u0011\u00013\t\u000b%\u0004A\u0011\u00016\t\u000b=\u0004A\u0011\u00019\t\u000bU\u0004A\u0011\u0001<\t\u000ba\u0004A\u0011A=\t\u000f\u0005m\u0001\u0001\"\u0001\u0002\u001e\t)2i\u001c8wKJ$\u0018M\u00197f\rJ|WNT;nE\u0016\u0014(B\u0001\n\u0014\u0003\u0011i\u0017\r\u001e5\u000b\u0003Q\tQa\u001d9je\u0016\u001c2\u0001\u0001\f\u001d!\t9\"$D\u0001\u0019\u0015\u0005I\u0012!B:dC2\f\u0017BA\u000e\u0019\u0005\u0019\te.\u001f*fMB\u0019QD\b\u0011\u000e\u0003EI!aH\t\u0003\u001f\r{gN^3si\u0006\u0014G.\u001a$s_6\u0004\"!H\u0011\n\u0005\t\n\"A\u0002(v[\n,'/\u0001\u0004%S:LG\u000fJ\u0002\u0001)\u00051\u0003CA\f(\u0013\tA\u0003D\u0001\u0003V]&$\u0018A\u0002;p\u0005f$X\r\u0006\u0002,]A\u0011q\u0003L\u0005\u0003[a\u0011AAQ=uK\")qF\u0001a\u0001A\u0005\t\u0011-A\u0004u_NCwN\u001d;\u0015\u0005I*\u0004CA\f4\u0013\t!\u0004DA\u0003TQ>\u0014H\u000fC\u00030\u0007\u0001\u0007\u0001%A\u0003u_&sG\u000f\u0006\u00029wA\u0011q#O\u0005\u0003ua\u00111!\u00138u\u0011\u0015yC\u00011\u0001!\u0003\u0019!x\u000eT8oOR\u0011a(\u0011\t\u0003/}J!\u0001\u0011\r\u0003\t1{gn\u001a\u0005\u0006_\u0015\u0001\r\u0001I\u0001\bi>4En\\1u)\t!u\t\u0005\u0002\u0018\u000b&\u0011a\t\u0007\u0002\u0006\r2|\u0017\r\u001e\u0005\u0006_\u0019\u0001\r\u0001I\u0001\ti>$u.\u001e2mKR\u0011!*\u0014\t\u0003/-K!\u0001\u0014\r\u0003\r\u0011{WO\u00197f\u0011\u0015ys\u00011\u0001!\u0003!!xNQ5h\u0013:$HC\u0001)]!\t\t\u0016L\u0004\u0002S/:\u00111KV\u0007\u0002)*\u0011Q\u000bJ\u0001\u0007yI|w\u000e\u001e \n\u0003eI!\u0001\u0017\r\u0002\u000fA\f7m[1hK&\u0011!l\u0017\u0002\u0007\u0005&<\u0017J\u001c;\u000b\u0005aC\u0002\"B\u0018\t\u0001\u0004\u0001\u0013\u0001\u0004;p\u0005&<G)Z2j[\u0006dGCA0c!\t\t\u0006-\u0003\u0002b7\nQ!)[4EK\u000eLW.\u00197\t\u000b=J\u0001\u0019\u0001\u0011\u0002\u0015Q|'+\u0019;j_:\fG\u000e\u0006\u0002fQB\u0011QDZ\u0005\u0003OF\u0011\u0001BU1uS>t\u0017\r\u001c\u0005\u0006_)\u0001\r\u0001I\u0001\fi>\fEnZ3ce\u0006L7\r\u0006\u0002l]B\u0011Q\u0004\\\u0005\u0003[F\u0011\u0011\"\u00117hK\n\u0014\u0018-[2\t\u000b=Z\u0001\u0019\u0001\u0011\u0002\rQ|'+Z1m)\t\tH\u000f\u0005\u0002\u001ee&\u00111/\u0005\u0002\u0005%\u0016\fG\u000eC\u00030\u0019\u0001\u0007\u0001%\u0001\u0005u_:+XNY3s)\t\u0001s\u000fC\u00030\u001b\u0001\u0007\u0001%\u0001\u0004u_RK\b/Z\u000b\u0003uz$2a_A\r)\ra\u0018q\u0002\t\u0003{zd\u0001\u0001\u0002\u0004\u0000\u001d\t\u0007\u0011\u0011\u0001\u0002\u0002\u0005F!\u00111AA\u0005!\r9\u0012QA\u0005\u0004\u0003\u000fA\"a\u0002(pi\"Lgn\u001a\t\u0004/\u0005-\u0011bAA\u00071\t\u0019\u0011I\\=\t\u0013\u0005Ea\"!AA\u0004\u0005M\u0011aC3wS\u0012,gnY3%gA\u0002B!HA\u000by&\u0019\u0011qC\t\u0003\u001b\r{gN^3si\u0006\u0014G.\u001a+p\u0011\u0015yc\u00021\u0001!\u0003!!xn\u0015;sS:<G\u0003BA\u0010\u0003_\u0001B!!\t\u0002*9!\u00111EA\u0013!\t\u0019\u0006$C\u0002\u0002(a\ta\u0001\u0015:fI\u00164\u0017\u0002BA\u0016\u0003[\u0011aa\u0015;sS:<'bAA\u00141!)qf\u0004a\u0001A\u0001"
)
public interface ConvertableFromNumber extends ConvertableFrom {
   // $FF: synthetic method
   static byte toByte$(final ConvertableFromNumber $this, final Number a) {
      return $this.toByte(a);
   }

   default byte toByte(final Number a) {
      return a.toBigInt().toByte();
   }

   // $FF: synthetic method
   static short toShort$(final ConvertableFromNumber $this, final Number a) {
      return $this.toShort(a);
   }

   default short toShort(final Number a) {
      return a.toBigInt().toShort();
   }

   // $FF: synthetic method
   static int toInt$(final ConvertableFromNumber $this, final Number a) {
      return $this.toInt(a);
   }

   default int toInt(final Number a) {
      return a.toBigInt().toInt();
   }

   // $FF: synthetic method
   static long toLong$(final ConvertableFromNumber $this, final Number a) {
      return $this.toLong(a);
   }

   default long toLong(final Number a) {
      return a.toBigInt().toLong();
   }

   // $FF: synthetic method
   static float toFloat$(final ConvertableFromNumber $this, final Number a) {
      return $this.toFloat(a);
   }

   default float toFloat(final Number a) {
      return a.toBigInt().toFloat();
   }

   // $FF: synthetic method
   static double toDouble$(final ConvertableFromNumber $this, final Number a) {
      return $this.toDouble(a);
   }

   default double toDouble(final Number a) {
      return a.toBigInt().toDouble();
   }

   // $FF: synthetic method
   static BigInt toBigInt$(final ConvertableFromNumber $this, final Number a) {
      return $this.toBigInt(a);
   }

   default BigInt toBigInt(final Number a) {
      return a.toBigInt();
   }

   // $FF: synthetic method
   static BigDecimal toBigDecimal$(final ConvertableFromNumber $this, final Number a) {
      return $this.toBigDecimal(a);
   }

   default BigDecimal toBigDecimal(final Number a) {
      return .MODULE$.BigDecimal().apply(a.toBigInt());
   }

   // $FF: synthetic method
   static Rational toRational$(final ConvertableFromNumber $this, final Number a) {
      return $this.toRational(a);
   }

   default Rational toRational(final Number a) {
      return Rational$.MODULE$.apply(a.toBigInt());
   }

   // $FF: synthetic method
   static Algebraic toAlgebraic$(final ConvertableFromNumber $this, final Number a) {
      return $this.toAlgebraic(a);
   }

   default Algebraic toAlgebraic(final Number a) {
      return Algebraic$.MODULE$.apply(a.toRational());
   }

   // $FF: synthetic method
   static Real toReal$(final ConvertableFromNumber $this, final Number a) {
      return $this.toReal(a);
   }

   default Real toReal(final Number a) {
      return Real$.MODULE$.apply(a.toRational());
   }

   // $FF: synthetic method
   static Number toNumber$(final ConvertableFromNumber $this, final Number a) {
      return $this.toNumber(a);
   }

   default Number toNumber(final Number a) {
      return a;
   }

   // $FF: synthetic method
   static Object toType$(final ConvertableFromNumber $this, final Number a, final ConvertableTo evidence$30) {
      return $this.toType(a, evidence$30);
   }

   default Object toType(final Number a, final ConvertableTo evidence$30) {
      return ConvertableTo$.MODULE$.apply(evidence$30).fromBigInt(a.toBigInt());
   }

   // $FF: synthetic method
   static String toString$(final ConvertableFromNumber $this, final Number a) {
      return $this.toString(a);
   }

   default String toString(final Number a) {
      return a.toString();
   }

   static void $init$(final ConvertableFromNumber $this) {
   }
}
