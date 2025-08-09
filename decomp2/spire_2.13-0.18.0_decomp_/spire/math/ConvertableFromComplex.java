package spire.math;

import scala.math.BigDecimal;
import scala.math.BigInt;
import scala.reflect.ScalaSignature;
import scala.sys.package.;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005-c\u0001C\t\u0013!\u0003\r\tA\u0005\f\t\u000bE\u0002A\u0011\u0001\u001a\t\u000bY\u0002a\u0011A\u001c\t\u000bm\u0002A\u0011\u0001\u001f\t\u000b\t\u0003A\u0011A\"\t\u000b!\u0003A\u0011A%\t\u000b9\u0003A\u0011A(\t\u000bQ\u0003A\u0011A+\t\u000bi\u0003A\u0011A.\t\u000b\u0001\u0004A\u0011A1\t\u000b=\u0004A\u0011\u00019\t\u000bU\u0004A\u0011\u0001<\t\u000bm\u0004A\u0011\u0001?\t\u000f\u0005\r\u0001\u0001\"\u0001\u0002\u0006!9\u0011q\u0002\u0001\u0005\u0002\u0005E\u0001bBA\u000e\u0001\u0011\u0005\u0011Q\u0004\u0005\b\u0003k\u0001A\u0011AA\u001c\u0005Y\u0019uN\u001c<feR\f'\r\\3Ge>l7i\\7qY\u0016D(BA\n\u0015\u0003\u0011i\u0017\r\u001e5\u000b\u0003U\tQa\u001d9je\u0016,\"aF\u0014\u0014\u0007\u0001Ab\u0004\u0005\u0002\u001a95\t!DC\u0001\u001c\u0003\u0015\u00198-\u00197b\u0013\ti\"D\u0001\u0004B]f\u0014VM\u001a\t\u0004?\u0001\u0012S\"\u0001\n\n\u0005\u0005\u0012\"aD\"p]Z,'\u000f^1cY\u00164%o\\7\u0011\u0007}\u0019S%\u0003\u0002%%\t91i\\7qY\u0016D\bC\u0001\u0014(\u0019\u0001!Q\u0001\u000b\u0001C\u0002)\u0012\u0011!Q\u0002\u0001#\tYc\u0006\u0005\u0002\u001aY%\u0011QF\u0007\u0002\b\u001d>$\b.\u001b8h!\tIr&\u0003\u000215\t\u0019\u0011I\\=\u0002\r\u0011Jg.\u001b;%)\u0005\u0019\u0004CA\r5\u0013\t)$D\u0001\u0003V]&$\u0018aB1mO\u0016\u0014'/Y\u000b\u0002qA\u0019q$O\u0013\n\u0005i\u0012\"\u0001C%oi\u0016<'/\u00197\u0002\rQ|')\u001f;f)\ti\u0004\t\u0005\u0002\u001a}%\u0011qH\u0007\u0002\u0005\u0005f$X\rC\u0003B\u0007\u0001\u0007!%A\u0001b\u0003\u001d!xn\u00155peR$\"\u0001R$\u0011\u0005e)\u0015B\u0001$\u001b\u0005\u0015\u0019\u0006n\u001c:u\u0011\u0015\tE\u00011\u0001#\u0003\u0015!x.\u00138u)\tQU\n\u0005\u0002\u001a\u0017&\u0011AJ\u0007\u0002\u0004\u0013:$\b\"B!\u0006\u0001\u0004\u0011\u0013A\u0002;p\u0019>tw\r\u0006\u0002Q'B\u0011\u0011$U\u0005\u0003%j\u0011A\u0001T8oO\")\u0011I\u0002a\u0001E\u00059Ao\u001c$m_\u0006$HC\u0001,Z!\tIr+\u0003\u0002Y5\t)a\t\\8bi\")\u0011i\u0002a\u0001E\u0005AAo\u001c#pk\ndW\r\u0006\u0002]?B\u0011\u0011$X\u0005\u0003=j\u0011a\u0001R8vE2,\u0007\"B!\t\u0001\u0004\u0011\u0013\u0001\u0003;p\u0005&<\u0017J\u001c;\u0015\u0005\tt\u0007CA2l\u001d\t!\u0017N\u0004\u0002fQ6\taM\u0003\u0002hS\u00051AH]8pizJ\u0011aG\u0005\u0003Uj\tq\u0001]1dW\u0006<W-\u0003\u0002m[\n1!)[4J]RT!A\u001b\u000e\t\u000b\u0005K\u0001\u0019\u0001\u0012\u0002\u0019Q|')[4EK\u000eLW.\u00197\u0015\u0005E$\bCA2s\u0013\t\u0019XN\u0001\u0006CS\u001e$UmY5nC2DQ!\u0011\u0006A\u0002\t\n!\u0002^8SCRLwN\\1m)\t9(\u0010\u0005\u0002 q&\u0011\u0011P\u0005\u0002\t%\u0006$\u0018n\u001c8bY\")\u0011i\u0003a\u0001E\u0005YAo\\!mO\u0016\u0014'/Y5d)\ri\u0018\u0011\u0001\t\u0003?yL!a \n\u0003\u0013\u0005cw-\u001a2sC&\u001c\u0007\"B!\r\u0001\u0004\u0011\u0013A\u0002;p%\u0016\fG\u000e\u0006\u0003\u0002\b\u00055\u0001cA\u0010\u0002\n%\u0019\u00111\u0002\n\u0003\tI+\u0017\r\u001c\u0005\u0006\u00036\u0001\rAI\u0001\ti>tU/\u001c2feR!\u00111CA\r!\ry\u0012QC\u0005\u0004\u0003/\u0011\"A\u0002(v[\n,'\u000fC\u0003B\u001d\u0001\u0007!%\u0001\u0004u_RK\b/Z\u000b\u0005\u0003?\t)\u0003\u0006\u0003\u0002\"\u0005MB\u0003BA\u0012\u0003S\u00012AJA\u0013\t\u0019\t9c\u0004b\u0001U\t\t!\tC\u0005\u0002,=\t\t\u0011q\u0001\u0002.\u0005YQM^5eK:\u001cW\r\n\u001a9!\u0015y\u0012qFA\u0012\u0013\r\t\tD\u0005\u0002\u000e\u0007>tg/\u001a:uC\ndW\rV8\t\u000b\u0005{\u0001\u0019\u0001\u0012\u0002\u0011Q|7\u000b\u001e:j]\u001e$B!!\u000f\u0002JA!\u00111HA\"\u001d\u0011\ti$a\u0010\u0011\u0005\u0015T\u0012bAA!5\u00051\u0001K]3eK\u001aLA!!\u0012\u0002H\t11\u000b\u001e:j]\u001eT1!!\u0011\u001b\u0011\u0015\t\u0005\u00031\u0001#\u0001"
)
public interface ConvertableFromComplex extends ConvertableFrom {
   Integral algebra();

   // $FF: synthetic method
   static byte toByte$(final ConvertableFromComplex $this, final Complex a) {
      return $this.toByte(a);
   }

   default byte toByte(final Complex a) {
      return this.algebra().toByte(a.real());
   }

   // $FF: synthetic method
   static short toShort$(final ConvertableFromComplex $this, final Complex a) {
      return $this.toShort(a);
   }

   default short toShort(final Complex a) {
      return this.algebra().toShort(a.real());
   }

   // $FF: synthetic method
   static int toInt$(final ConvertableFromComplex $this, final Complex a) {
      return $this.toInt(a);
   }

   default int toInt(final Complex a) {
      return this.algebra().toInt(a.real());
   }

   // $FF: synthetic method
   static long toLong$(final ConvertableFromComplex $this, final Complex a) {
      return $this.toLong(a);
   }

   default long toLong(final Complex a) {
      return this.algebra().toLong(a.real());
   }

   // $FF: synthetic method
   static float toFloat$(final ConvertableFromComplex $this, final Complex a) {
      return $this.toFloat(a);
   }

   default float toFloat(final Complex a) {
      return this.algebra().toFloat(a.real());
   }

   // $FF: synthetic method
   static double toDouble$(final ConvertableFromComplex $this, final Complex a) {
      return $this.toDouble(a);
   }

   default double toDouble(final Complex a) {
      return this.algebra().toDouble(a.real());
   }

   // $FF: synthetic method
   static BigInt toBigInt$(final ConvertableFromComplex $this, final Complex a) {
      return $this.toBigInt(a);
   }

   default BigInt toBigInt(final Complex a) {
      return this.algebra().toBigInt(a.real());
   }

   // $FF: synthetic method
   static BigDecimal toBigDecimal$(final ConvertableFromComplex $this, final Complex a) {
      return $this.toBigDecimal(a);
   }

   default BigDecimal toBigDecimal(final Complex a) {
      return this.algebra().toBigDecimal(a.real());
   }

   // $FF: synthetic method
   static Rational toRational$(final ConvertableFromComplex $this, final Complex a) {
      return $this.toRational(a);
   }

   default Rational toRational(final Complex a) {
      return this.algebra().toRational(a.real());
   }

   // $FF: synthetic method
   static Algebraic toAlgebraic$(final ConvertableFromComplex $this, final Complex a) {
      return $this.toAlgebraic(a);
   }

   default Algebraic toAlgebraic(final Complex a) {
      return this.algebra().toAlgebraic(a.real());
   }

   // $FF: synthetic method
   static Real toReal$(final ConvertableFromComplex $this, final Complex a) {
      return $this.toReal(a);
   }

   default Real toReal(final Complex a) {
      return this.algebra().toReal(a.real());
   }

   // $FF: synthetic method
   static Number toNumber$(final ConvertableFromComplex $this, final Complex a) {
      return $this.toNumber(a);
   }

   default Number toNumber(final Complex a) {
      return this.algebra().toNumber(a.real());
   }

   // $FF: synthetic method
   static Object toType$(final ConvertableFromComplex $this, final Complex a, final ConvertableTo evidence$28) {
      return $this.toType(a, evidence$28);
   }

   default Object toType(final Complex a, final ConvertableTo evidence$28) {
      throw .MODULE$.error("fixme");
   }

   // $FF: synthetic method
   static String toString$(final ConvertableFromComplex $this, final Complex a) {
      return $this.toString(a);
   }

   default String toString(final Complex a) {
      return a.toString();
   }

   static void $init$(final ConvertableFromComplex $this) {
   }
}
