package spire.math;

import java.math.MathContext;
import scala.math.BigDecimal;
import scala.math.BigInt;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005Eb\u0001\u0003\t\u0012!\u0003\r\t!E\u000b\t\u000b\r\u0002A\u0011A\u0013\t\u000b%\u0002A\u0011\u0001\u0016\t\u000bA\u0002A\u0011A\u0019\t\u000bY\u0002A\u0011A\u001c\t\u000bq\u0002A\u0011A\u001f\t\u000b\t\u0003A\u0011A\"\t\u000b!\u0003A\u0011A%\t\u000b9\u0003A\u0011A(\t\u000bu\u0003A\u0011\u00010\t\u000b\r\u0004A\u0011\u00013\t\u000b\u0019\u0004A\u0011A4\t\u000b1\u0004A\u0011A7\t\u000bI\u0004A\u0011A:\t\u000ba\u0004A\u0011A=\t\u000f\u0005m\u0001\u0001\"\u0001\u0002\u001e\t92i\u001c8wKJ$\u0018M\u00197f\rJ|WNU1uS>t\u0017\r\u001c\u0006\u0003%M\tA!\\1uQ*\tA#A\u0003ta&\u0014XmE\u0002\u0001-q\u0001\"a\u0006\u000e\u000e\u0003aQ\u0011!G\u0001\u0006g\u000e\fG.Y\u0005\u00037a\u0011a!\u00118z%\u00164\u0007cA\u000f\u001fA5\t\u0011#\u0003\u0002 #\ty1i\u001c8wKJ$\u0018M\u00197f\rJ|W\u000e\u0005\u0002\u001eC%\u0011!%\u0005\u0002\t%\u0006$\u0018n\u001c8bY\u00061A%\u001b8ji\u0012\u001a\u0001\u0001F\u0001'!\t9r%\u0003\u0002)1\t!QK\\5u\u0003\u0019!xNQ=uKR\u00111F\f\t\u0003/1J!!\f\r\u0003\t\tKH/\u001a\u0005\u0006_\t\u0001\r\u0001I\u0001\u0002C\u00069Ao\\*i_J$HC\u0001\u001a6!\t92'\u0003\u000251\t)1\u000b[8si\")qf\u0001a\u0001A\u0005)Ao\\%oiR\u0011\u0001h\u000f\t\u0003/eJ!A\u000f\r\u0003\u0007%sG\u000fC\u00030\t\u0001\u0007\u0001%\u0001\u0004u_2{gn\u001a\u000b\u0003}\u0005\u0003\"aF \n\u0005\u0001C\"\u0001\u0002'p]\u001eDQaL\u0003A\u0002\u0001\nq\u0001^8GY>\fG\u000f\u0006\u0002E\u000fB\u0011q#R\u0005\u0003\rb\u0011QA\u00127pCRDQa\f\u0004A\u0002\u0001\n\u0001\u0002^8E_V\u0014G.\u001a\u000b\u0003\u00156\u0003\"aF&\n\u00051C\"A\u0002#pk\ndW\rC\u00030\u000f\u0001\u0007\u0001%\u0001\u0005u_\nKw-\u00138u)\t\u0001F\f\u0005\u0002R3:\u0011!k\u0016\b\u0003'Zk\u0011\u0001\u0016\u0006\u0003+\u0012\na\u0001\u0010:p_Rt\u0014\"A\r\n\u0005aC\u0012a\u00029bG.\fw-Z\u0005\u00035n\u0013aAQ5h\u0013:$(B\u0001-\u0019\u0011\u0015y\u0003\u00021\u0001!\u00031!xNQ5h\t\u0016\u001c\u0017.\\1m)\ty&\r\u0005\u0002RA&\u0011\u0011m\u0017\u0002\u000b\u0005&<G)Z2j[\u0006d\u0007\"B\u0018\n\u0001\u0004\u0001\u0013A\u0003;p%\u0006$\u0018n\u001c8bYR\u0011\u0001%\u001a\u0005\u0006_)\u0001\r\u0001I\u0001\fi>\fEnZ3ce\u0006L7\r\u0006\u0002iWB\u0011Q$[\u0005\u0003UF\u0011\u0011\"\u00117hK\n\u0014\u0018-[2\t\u000b=Z\u0001\u0019\u0001\u0011\u0002\rQ|'+Z1m)\tq\u0017\u000f\u0005\u0002\u001e_&\u0011\u0001/\u0005\u0002\u0005%\u0016\fG\u000eC\u00030\u0019\u0001\u0007\u0001%\u0001\u0005u_:+XNY3s)\t!x\u000f\u0005\u0002\u001ek&\u0011a/\u0005\u0002\u0007\u001dVl'-\u001a:\t\u000b=j\u0001\u0019\u0001\u0011\u0002\rQ|G+\u001f9f+\tQh\u0010F\u0002|\u00033!2\u0001`A\b!\tih\u0010\u0004\u0001\u0005\r}t!\u0019AA\u0001\u0005\u0005\u0011\u0015\u0003BA\u0002\u0003\u0013\u00012aFA\u0003\u0013\r\t9\u0001\u0007\u0002\b\u001d>$\b.\u001b8h!\r9\u00121B\u0005\u0004\u0003\u001bA\"aA!os\"I\u0011\u0011\u0003\b\u0002\u0002\u0003\u000f\u00111C\u0001\fKZLG-\u001a8dK\u0012\u0012d\u0007\u0005\u0003\u001e\u0003+a\u0018bAA\f#\ti1i\u001c8wKJ$\u0018M\u00197f)>DQa\f\bA\u0002\u0001\n\u0001\u0002^8TiJLgn\u001a\u000b\u0005\u0003?\ty\u0003\u0005\u0003\u0002\"\u0005%b\u0002BA\u0012\u0003K\u0001\"a\u0015\r\n\u0007\u0005\u001d\u0002$\u0001\u0004Qe\u0016$WMZ\u0005\u0005\u0003W\tiC\u0001\u0004TiJLgn\u001a\u0006\u0004\u0003OA\u0002\"B\u0018\u0010\u0001\u0004\u0001\u0003"
)
public interface ConvertableFromRational extends ConvertableFrom {
   // $FF: synthetic method
   static byte toByte$(final ConvertableFromRational $this, final Rational a) {
      return $this.toByte(a);
   }

   default byte toByte(final Rational a) {
      return a.toBigInt().toByte();
   }

   // $FF: synthetic method
   static short toShort$(final ConvertableFromRational $this, final Rational a) {
      return $this.toShort(a);
   }

   default short toShort(final Rational a) {
      return a.toBigInt().toShort();
   }

   // $FF: synthetic method
   static int toInt$(final ConvertableFromRational $this, final Rational a) {
      return $this.toInt(a);
   }

   default int toInt(final Rational a) {
      return a.toBigInt().toInt();
   }

   // $FF: synthetic method
   static long toLong$(final ConvertableFromRational $this, final Rational a) {
      return $this.toLong(a);
   }

   default long toLong(final Rational a) {
      return a.toBigInt().toLong();
   }

   // $FF: synthetic method
   static float toFloat$(final ConvertableFromRational $this, final Rational a) {
      return $this.toFloat(a);
   }

   default float toFloat(final Rational a) {
      return a.toBigDecimal(MathContext.DECIMAL64).toFloat();
   }

   // $FF: synthetic method
   static double toDouble$(final ConvertableFromRational $this, final Rational a) {
      return $this.toDouble(a);
   }

   default double toDouble(final Rational a) {
      return a.toBigDecimal(MathContext.DECIMAL64).toDouble();
   }

   // $FF: synthetic method
   static BigInt toBigInt$(final ConvertableFromRational $this, final Rational a) {
      return $this.toBigInt(a);
   }

   default BigInt toBigInt(final Rational a) {
      return a.toBigInt();
   }

   // $FF: synthetic method
   static BigDecimal toBigDecimal$(final ConvertableFromRational $this, final Rational a) {
      return $this.toBigDecimal(a);
   }

   default BigDecimal toBigDecimal(final Rational a) {
      return a.toBigDecimal(MathContext.DECIMAL64);
   }

   // $FF: synthetic method
   static Rational toRational$(final ConvertableFromRational $this, final Rational a) {
      return $this.toRational(a);
   }

   default Rational toRational(final Rational a) {
      return a;
   }

   // $FF: synthetic method
   static Algebraic toAlgebraic$(final ConvertableFromRational $this, final Rational a) {
      return $this.toAlgebraic(a);
   }

   default Algebraic toAlgebraic(final Rational a) {
      return Algebraic$.MODULE$.apply(a);
   }

   // $FF: synthetic method
   static Real toReal$(final ConvertableFromRational $this, final Rational a) {
      return $this.toReal(a);
   }

   default Real toReal(final Rational a) {
      return Real$.MODULE$.apply(a);
   }

   // $FF: synthetic method
   static Number toNumber$(final ConvertableFromRational $this, final Rational a) {
      return $this.toNumber(a);
   }

   default Number toNumber(final Rational a) {
      return Number$.MODULE$.apply(a);
   }

   // $FF: synthetic method
   static Object toType$(final ConvertableFromRational $this, final Rational a, final ConvertableTo evidence$26) {
      return $this.toType(a, evidence$26);
   }

   default Object toType(final Rational a, final ConvertableTo evidence$26) {
      return ConvertableTo$.MODULE$.apply(evidence$26).fromRational(a);
   }

   // $FF: synthetic method
   static String toString$(final ConvertableFromRational $this, final Rational a) {
      return $this.toString(a);
   }

   default String toString(final Rational a) {
      return a.toString();
   }

   static void $init$(final ConvertableFromRational $this) {
   }
}
