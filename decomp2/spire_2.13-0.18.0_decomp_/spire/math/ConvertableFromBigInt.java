package spire.math;

import scala.math.BigDecimal;
import scala.math.BigInt;
import scala.package.;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005Eb\u0001\u0003\t\u0012!\u0003\r\t!E\u000b\t\u000b5\u0002A\u0011\u0001\u0018\t\u000bI\u0002A\u0011A\u001a\t\u000be\u0002A\u0011\u0001\u001e\t\u000b}\u0002A\u0011\u0001!\t\u000b\u0015\u0003A\u0011\u0001$\t\u000b-\u0003A\u0011\u0001'\t\u000bE\u0003A\u0011\u0001*\t\u000b]\u0003A\u0011\u0001-\t\u000bi\u0003A\u0011A.\t\u000b\u0001\u0004A\u0011A1\t\u000b\u0019\u0004A\u0011A4\t\u000b1\u0004A\u0011A7\t\u000bI\u0004A\u0011A:\t\u000ba\u0004A\u0011A=\t\u000f\u0005m\u0001\u0001\"\u0001\u0002\u001e\t)2i\u001c8wKJ$\u0018M\u00197f\rJ|WNQ5h\u0013:$(B\u0001\n\u0014\u0003\u0011i\u0017\r\u001e5\u000b\u0003Q\tQa\u001d9je\u0016\u001c2\u0001\u0001\f\u001d!\t9\"$D\u0001\u0019\u0015\u0005I\u0012!B:dC2\f\u0017BA\u000e\u0019\u0005\u0019\te.\u001f*fMB\u0019QD\b\u0011\u000e\u0003EI!aH\t\u0003\u001f\r{gN^3si\u0006\u0014G.\u001a$s_6\u0004\"!\t\u0016\u000f\u0005\tBcBA\u0012(\u001b\u0005!#BA\u0013'\u0003\u0019a$o\\8u}\r\u0001\u0011\"A\r\n\u0005%B\u0012a\u00029bG.\fw-Z\u0005\u0003W1\u0012aAQ5h\u0013:$(BA\u0015\u0019\u0003\u0019!\u0013N\\5uIQ\tq\u0006\u0005\u0002\u0018a%\u0011\u0011\u0007\u0007\u0002\u0005+:LG/\u0001\u0004u_\nKH/\u001a\u000b\u0003i]\u0002\"aF\u001b\n\u0005YB\"\u0001\u0002\"zi\u0016DQ\u0001\u000f\u0002A\u0002\u0001\n\u0011!Y\u0001\bi>\u001c\u0006n\u001c:u)\tYd\b\u0005\u0002\u0018y%\u0011Q\b\u0007\u0002\u0006'\"|'\u000f\u001e\u0005\u0006q\r\u0001\r\u0001I\u0001\u0006i>Le\u000e\u001e\u000b\u0003\u0003\u0012\u0003\"a\u0006\"\n\u0005\rC\"aA%oi\")\u0001\b\u0002a\u0001A\u00051Ao\u001c'p]\u001e$\"a\u0012&\u0011\u0005]A\u0015BA%\u0019\u0005\u0011auN\\4\t\u000ba*\u0001\u0019\u0001\u0011\u0002\u000fQ|g\t\\8biR\u0011Q\n\u0015\t\u0003/9K!a\u0014\r\u0003\u000b\u0019cw.\u0019;\t\u000ba2\u0001\u0019\u0001\u0011\u0002\u0011Q|Gi\\;cY\u0016$\"a\u0015,\u0011\u0005]!\u0016BA+\u0019\u0005\u0019!u.\u001e2mK\")\u0001h\u0002a\u0001A\u0005AAo\u001c\"jO&sG\u000f\u0006\u0002!3\")\u0001\b\u0003a\u0001A\u0005aAo\u001c\"jO\u0012+7-[7bYR\u0011Al\u0018\t\u0003CuK!A\u0018\u0017\u0003\u0015\tKw\rR3dS6\fG\u000eC\u00039\u0013\u0001\u0007\u0001%\u0001\u0006u_J\u000bG/[8oC2$\"AY3\u0011\u0005u\u0019\u0017B\u00013\u0012\u0005!\u0011\u0016\r^5p]\u0006d\u0007\"\u0002\u001d\u000b\u0001\u0004\u0001\u0013a\u0003;p\u00032<WM\u0019:bS\u000e$\"\u0001[6\u0011\u0005uI\u0017B\u00016\u0012\u0005%\tEnZ3ce\u0006L7\rC\u00039\u0017\u0001\u0007\u0001%\u0001\u0004u_J+\u0017\r\u001c\u000b\u0003]F\u0004\"!H8\n\u0005A\f\"\u0001\u0002*fC2DQ\u0001\u000f\u0007A\u0002\u0001\n\u0001\u0002^8Ok6\u0014WM\u001d\u000b\u0003i^\u0004\"!H;\n\u0005Y\f\"A\u0002(v[\n,'\u000fC\u00039\u001b\u0001\u0007\u0001%\u0001\u0004u_RK\b/Z\u000b\u0003uz$2a_A\r)\ra\u0018q\u0002\t\u0003{zd\u0001\u0001\u0002\u0004\u0000\u001d\t\u0007\u0011\u0011\u0001\u0002\u0002\u0005F!\u00111AA\u0005!\r9\u0012QA\u0005\u0004\u0003\u000fA\"a\u0002(pi\"Lgn\u001a\t\u0004/\u0005-\u0011bAA\u00071\t\u0019\u0011I\\=\t\u0013\u0005Ea\"!AA\u0004\u0005M\u0011aC3wS\u0012,gnY3%eQ\u0002B!HA\u000by&\u0019\u0011qC\t\u0003\u001b\r{gN^3si\u0006\u0014G.\u001a+p\u0011\u0015Ad\u00021\u0001!\u0003!!xn\u0015;sS:<G\u0003BA\u0010\u0003_\u0001B!!\t\u0002*9!\u00111EA\u0013!\t\u0019\u0003$C\u0002\u0002(a\ta\u0001\u0015:fI\u00164\u0017\u0002BA\u0016\u0003[\u0011aa\u0015;sS:<'bAA\u00141!)\u0001h\u0004a\u0001A\u0001"
)
public interface ConvertableFromBigInt extends ConvertableFrom {
   // $FF: synthetic method
   static byte toByte$(final ConvertableFromBigInt $this, final BigInt a) {
      return $this.toByte(a);
   }

   default byte toByte(final BigInt a) {
      return a.toByte();
   }

   // $FF: synthetic method
   static short toShort$(final ConvertableFromBigInt $this, final BigInt a) {
      return $this.toShort(a);
   }

   default short toShort(final BigInt a) {
      return a.toShort();
   }

   // $FF: synthetic method
   static int toInt$(final ConvertableFromBigInt $this, final BigInt a) {
      return $this.toInt(a);
   }

   default int toInt(final BigInt a) {
      return a.toInt();
   }

   // $FF: synthetic method
   static long toLong$(final ConvertableFromBigInt $this, final BigInt a) {
      return $this.toLong(a);
   }

   default long toLong(final BigInt a) {
      return a.toLong();
   }

   // $FF: synthetic method
   static float toFloat$(final ConvertableFromBigInt $this, final BigInt a) {
      return $this.toFloat(a);
   }

   default float toFloat(final BigInt a) {
      return a.toFloat();
   }

   // $FF: synthetic method
   static double toDouble$(final ConvertableFromBigInt $this, final BigInt a) {
      return $this.toDouble(a);
   }

   default double toDouble(final BigInt a) {
      return a.toDouble();
   }

   // $FF: synthetic method
   static BigInt toBigInt$(final ConvertableFromBigInt $this, final BigInt a) {
      return $this.toBigInt(a);
   }

   default BigInt toBigInt(final BigInt a) {
      return a;
   }

   // $FF: synthetic method
   static BigDecimal toBigDecimal$(final ConvertableFromBigInt $this, final BigInt a) {
      return $this.toBigDecimal(a);
   }

   default BigDecimal toBigDecimal(final BigInt a) {
      return .MODULE$.BigDecimal().apply(a);
   }

   // $FF: synthetic method
   static Rational toRational$(final ConvertableFromBigInt $this, final BigInt a) {
      return $this.toRational(a);
   }

   default Rational toRational(final BigInt a) {
      return Rational$.MODULE$.apply(a);
   }

   // $FF: synthetic method
   static Algebraic toAlgebraic$(final ConvertableFromBigInt $this, final BigInt a) {
      return $this.toAlgebraic(a);
   }

   default Algebraic toAlgebraic(final BigInt a) {
      return Algebraic$.MODULE$.apply(a);
   }

   // $FF: synthetic method
   static Real toReal$(final ConvertableFromBigInt $this, final BigInt a) {
      return $this.toReal(a);
   }

   default Real toReal(final BigInt a) {
      return Real$.MODULE$.apply(a);
   }

   // $FF: synthetic method
   static Number toNumber$(final ConvertableFromBigInt $this, final BigInt a) {
      return $this.toNumber(a);
   }

   default Number toNumber(final BigInt a) {
      return Number$.MODULE$.apply(a);
   }

   // $FF: synthetic method
   static Object toType$(final ConvertableFromBigInt $this, final BigInt a, final ConvertableTo evidence$24) {
      return $this.toType(a, evidence$24);
   }

   default Object toType(final BigInt a, final ConvertableTo evidence$24) {
      return ConvertableTo$.MODULE$.apply(evidence$24).fromBigInt(a);
   }

   // $FF: synthetic method
   static String toString$(final ConvertableFromBigInt $this, final BigInt a) {
      return $this.toString(a);
   }

   default String toString(final BigInt a) {
      return a.toString();
   }

   static void $init$(final ConvertableFromBigInt $this) {
   }
}
