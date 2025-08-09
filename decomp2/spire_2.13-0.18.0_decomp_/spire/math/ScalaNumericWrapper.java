package spire.math;

import algebra.ring.Ring;
import algebra.ring.Signed;
import cats.kernel.Order;
import scala.Option;
import scala.None.;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005-c\u0001\u0003\u000b\u0016!\u0003\r\taF\r\t\u000bm\u0002A\u0011\u0001\u001f\t\u000b\u0001\u0003a\u0011A!\t\u000bI\u0003a\u0011A*\t\u000b]\u0003a\u0011\u0001-\t\u000bq\u0003a\u0011A/\t\u000b\u0005\u0004A\u0011\u00012\t\u000b!\u0004A\u0011A5\t\u000b-\u0004A\u0011\u00017\t\u000bA\u0004A\u0011A9\t\u000bQ\u0004A\u0011A;\t\u000ba\u0004A\u0011I=\t\u000bi\u0004A\u0011I=\t\u000bm\u0004A\u0011\u0001?\t\u000f\u0005\r\u0001\u0001\"\u0001\u0002\u0006!9\u0011q\u0002\u0001\u0005\u0002\u0005E\u0001bBA\u000b\u0001\u0011\u0005\u0011q\u0003\u0005\b\u0003C\u0001A\u0011IA\u0012\u0011\u001d\t9\u0003\u0001C!\u0003SAq!!\f\u0001\t\u0003\tyCA\nTG\u0006d\u0017MT;nKJL7m\u0016:baB,'O\u0003\u0002\u0017/\u0005!Q.\u0019;i\u0015\u0005A\u0012!B:qSJ,WC\u0001\u000e-'\u0011\u00011dI\u001c\u0011\u0005q\tS\"A\u000f\u000b\u0005yy\u0012\u0001\u00027b]\u001eT\u0011\u0001I\u0001\u0005U\u00064\u0018-\u0003\u0002#;\t1qJ\u00196fGR\u00042\u0001\n\u0015+\u001b\u0005)#B\u0001\f'\u0015\u00059\u0013!B:dC2\f\u0017BA\u0015&\u0005\u001dqU/\\3sS\u000e\u0004\"a\u000b\u0017\r\u0001\u0011)Q\u0006\u0001b\u0001_\t\t\u0011i\u0001\u0001\u0012\u0005A\"\u0004CA\u00193\u001b\u00051\u0013BA\u001a'\u0005\u001dqu\u000e\u001e5j]\u001e\u0004\"!M\u001b\n\u0005Y2#aA!osB\u0019\u0001(\u000f\u0016\u000e\u0003UI!AO\u000b\u0003)M\u001b\u0017\r\\1Pe\u0012,'/\u001b8h/J\f\u0007\u000f]3s\u0003\u0019!\u0013N\\5uIQ\tQ\b\u0005\u00022}%\u0011qH\n\u0002\u0005+:LG/A\u0005tiJ,8\r^;sKV\t!\tE\u0002D\u001f*r!\u0001\u0012'\u000f\u0005\u0015SeB\u0001$J\u001b\u00059%B\u0001%/\u0003\u0019a$o\\8u}%\t\u0001$\u0003\u0002L/\u00059\u0011\r\\4fEJ\f\u0017BA'O\u0003\u001d\u0001\u0018mY6bO\u0016T!aS\f\n\u0005A\u000b&\u0001\u0002*j]\u001eT!!\u0014(\u0002\u0017\r|gN^3sg&|gn]\u000b\u0002)B\u0019\u0001(\u0016\u0016\n\u0005Y+\"aD\"p]Z,'\u000f^1cY\u00164%o\\7\u0002\rMLwM\\3e+\u0005I\u0006cA\"[U%\u00111,\u0015\u0002\u0007'&<g.\u001a3\u0002\u000b=\u0014H-\u001a:\u0016\u0003y\u00032aQ0+\u0013\t\u0001\u0017KA\u0003Pe\u0012,'/A\u0004ge>l\u0017J\u001c;\u0015\u0005)\u001a\u0007\"\u00023\u0007\u0001\u0004)\u0017!\u0001=\u0011\u0005E2\u0017BA4'\u0005\rIe\u000e^\u0001\u0007]\u0016<\u0017\r^3\u0015\u0005)R\u0007\"\u00023\b\u0001\u0004Q\u0013!B7j]V\u001cHc\u0001\u0016n]\")A\r\u0003a\u0001U!)q\u000e\u0003a\u0001U\u0005\t\u00110\u0001\u0003qYV\u001cHc\u0001\u0016sg\")A-\u0003a\u0001U!)q.\u0003a\u0001U\u0005)A/[7fgR\u0019!F^<\t\u000b\u0011T\u0001\u0019\u0001\u0016\t\u000b=T\u0001\u0019\u0001\u0016\u0002\ti,'o\\\u000b\u0002U\u0005\u0019qN\\3\u0002\u0011Q|Gi\\;cY\u0016$2!`A\u0001!\t\td0\u0003\u0002\u0000M\t1Ai\\;cY\u0016DQ\u0001Z\u0007A\u0002)\nq\u0001^8GY>\fG\u000f\u0006\u0003\u0002\b\u00055\u0001cA\u0019\u0002\n%\u0019\u00111\u0002\u0014\u0003\u000b\u0019cw.\u0019;\t\u000b\u0011t\u0001\u0019\u0001\u0016\u0002\u000bQ|\u0017J\u001c;\u0015\u0007\u0015\f\u0019\u0002C\u0003e\u001f\u0001\u0007!&\u0001\u0004u_2{gn\u001a\u000b\u0005\u00033\ty\u0002E\u00022\u00037I1!!\b'\u0005\u0011auN\\4\t\u000b\u0011\u0004\u0002\u0019\u0001\u0016\u0002\rMLwM\\;n)\r)\u0017Q\u0005\u0005\u0006IF\u0001\rAK\u0001\u0004C\n\u001cHc\u0001\u0016\u0002,!)AM\u0005a\u0001U\u0005Y\u0001/\u0019:tKN#(/\u001b8h)\u0011\t\t$a\u000e\u0011\tE\n\u0019DK\u0005\u0004\u0003k1#AB(qi&|g\u000eC\u0004\u0002:M\u0001\r!a\u000f\u0002\u0007M$(\u000f\u0005\u0003\u0002>\u0005\u0015c\u0002BA \u0003\u0003\u0002\"A\u0012\u0014\n\u0007\u0005\rc%\u0001\u0004Qe\u0016$WMZ\u0005\u0005\u0003\u000f\nIE\u0001\u0004TiJLgn\u001a\u0006\u0004\u0003\u00072\u0003"
)
public interface ScalaNumericWrapper extends scala.math.Numeric, ScalaOrderingWrapper {
   Ring structure();

   ConvertableFrom conversions();

   Signed signed();

   Order order();

   // $FF: synthetic method
   static Object fromInt$(final ScalaNumericWrapper $this, final int x) {
      return $this.fromInt(x);
   }

   default Object fromInt(final int x) {
      return this.structure().fromInt(x);
   }

   // $FF: synthetic method
   static Object negate$(final ScalaNumericWrapper $this, final Object x) {
      return $this.negate(x);
   }

   default Object negate(final Object x) {
      return this.structure().negate(x);
   }

   // $FF: synthetic method
   static Object minus$(final ScalaNumericWrapper $this, final Object x, final Object y) {
      return $this.minus(x, y);
   }

   default Object minus(final Object x, final Object y) {
      return this.structure().minus(x, y);
   }

   // $FF: synthetic method
   static Object plus$(final ScalaNumericWrapper $this, final Object x, final Object y) {
      return $this.plus(x, y);
   }

   default Object plus(final Object x, final Object y) {
      return this.structure().plus(x, y);
   }

   // $FF: synthetic method
   static Object times$(final ScalaNumericWrapper $this, final Object x, final Object y) {
      return $this.times(x, y);
   }

   default Object times(final Object x, final Object y) {
      return this.structure().times(x, y);
   }

   // $FF: synthetic method
   static Object zero$(final ScalaNumericWrapper $this) {
      return $this.zero();
   }

   default Object zero() {
      return this.structure().zero();
   }

   // $FF: synthetic method
   static Object one$(final ScalaNumericWrapper $this) {
      return $this.one();
   }

   default Object one() {
      return this.structure().one();
   }

   // $FF: synthetic method
   static double toDouble$(final ScalaNumericWrapper $this, final Object x) {
      return $this.toDouble(x);
   }

   default double toDouble(final Object x) {
      return this.conversions().toDouble(x);
   }

   // $FF: synthetic method
   static float toFloat$(final ScalaNumericWrapper $this, final Object x) {
      return $this.toFloat(x);
   }

   default float toFloat(final Object x) {
      return this.conversions().toFloat(x);
   }

   // $FF: synthetic method
   static int toInt$(final ScalaNumericWrapper $this, final Object x) {
      return $this.toInt(x);
   }

   default int toInt(final Object x) {
      return this.conversions().toInt(x);
   }

   // $FF: synthetic method
   static long toLong$(final ScalaNumericWrapper $this, final Object x) {
      return $this.toLong(x);
   }

   default long toLong(final Object x) {
      return this.conversions().toLong(x);
   }

   // $FF: synthetic method
   static int signum$(final ScalaNumericWrapper $this, final Object x) {
      return $this.signum(x);
   }

   default int signum(final Object x) {
      return this.signed().signum(x);
   }

   // $FF: synthetic method
   static Object abs$(final ScalaNumericWrapper $this, final Object x) {
      return $this.abs(x);
   }

   default Object abs(final Object x) {
      return this.signed().abs(x);
   }

   // $FF: synthetic method
   static Option parseString$(final ScalaNumericWrapper $this, final String str) {
      return $this.parseString(str);
   }

   default Option parseString(final String str) {
      return .MODULE$;
   }

   static void $init$(final ScalaNumericWrapper $this) {
   }
}
