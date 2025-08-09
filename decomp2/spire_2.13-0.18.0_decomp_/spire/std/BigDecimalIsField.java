package spire.std;

import algebra.ring.Field;
import java.math.MathContext;
import scala.math.BigDecimal;
import scala.package.;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005}3q\u0001D\u0007\u0011\u0002\u0007\u0005!\u0003C\u00032\u0001\u0011\u0005!\u0007C\u00037\u0001\u0011\u0005s\u0007C\u0003=\u0001\u0011\u0005Q\bC\u0004@\u0001\t\u0007I\u0011\u0001!\t\u000b\u0005\u0003A\u0011\u0001\"\t\u000b\u0015\u0003A\u0011\t$\t\u000b1\u0003A\u0011I'\t\u000fA\u0003!\u0019!C\u0001\u0001\")\u0011\u000b\u0001C!%\")Q\u000b\u0001C!-\")1\f\u0001C\u00019\n\t\")[4EK\u000eLW.\u00197Jg\u001aKW\r\u001c3\u000b\u00059y\u0011aA:uI*\t\u0001#A\u0003ta&\u0014Xm\u0001\u0001\u0014\u0007\u0001\u0019\u0012\u0004\u0005\u0002\u0015/5\tQCC\u0001\u0017\u0003\u0015\u00198-\u00197b\u0013\tARC\u0001\u0004B]f\u0014VM\u001a\t\u00045\u0019JcBA\u000e$\u001d\ta\u0012E\u0004\u0002\u001eA5\taD\u0003\u0002 #\u00051AH]8pizJ\u0011\u0001E\u0005\u0003E=\tq!\u00197hK\n\u0014\u0018-\u0003\u0002%K\u00059\u0001/Y2lC\u001e,'B\u0001\u0012\u0010\u0013\t9\u0003FA\u0003GS\u0016dGM\u0003\u0002%KA\u0011!F\f\b\u0003W5r!!\b\u0017\n\u0003YI!\u0001J\u000b\n\u0005=\u0002$A\u0003\"jO\u0012+7-[7bY*\u0011A%F\u0001\u0007I%t\u0017\u000e\u001e\u0013\u0015\u0003M\u0002\"\u0001\u0006\u001b\n\u0005U*\"\u0001B+oSR\fQ!\\5okN$2!\u000b\u001d;\u0011\u0015I$\u00011\u0001*\u0003\u0005\t\u0007\"B\u001e\u0003\u0001\u0004I\u0013!\u00012\u0002\r9,w-\u0019;f)\tIc\bC\u0003:\u0007\u0001\u0007\u0011&A\u0002p]\u0016,\u0012!K\u0001\u0005a2,8\u000fF\u0002*\u0007\u0012CQ!O\u0003A\u0002%BQaO\u0003A\u0002%\n1\u0001]8x)\rIs\t\u0013\u0005\u0006s\u0019\u0001\r!\u000b\u0005\u0006w\u0019\u0001\r!\u0013\t\u0003))K!aS\u000b\u0003\u0007%sG/A\u0003uS6,7\u000fF\u0002*\u001d>CQ!O\u0004A\u0002%BQaO\u0004A\u0002%\nAA_3s_\u00069aM]8n\u0013:$HCA\u0015T\u0011\u0015!\u0016\u00021\u0001J\u0003\u0005q\u0017A\u00034s_6$u.\u001e2mKR\u0011\u0011f\u0016\u0005\u0006)*\u0001\r\u0001\u0017\t\u0003)eK!AW\u000b\u0003\r\u0011{WO\u00197f\u0003\r!\u0017N\u001e\u000b\u0004Sus\u0006\"B\u001d\f\u0001\u0004I\u0003\"B\u001e\f\u0001\u0004I\u0003"
)
public interface BigDecimalIsField extends Field {
   void spire$std$BigDecimalIsField$_setter_$one_$eq(final BigDecimal x$1);

   void spire$std$BigDecimalIsField$_setter_$zero_$eq(final BigDecimal x$1);

   // $FF: synthetic method
   static BigDecimal minus$(final BigDecimalIsField $this, final BigDecimal a, final BigDecimal b) {
      return $this.minus(a, b);
   }

   default BigDecimal minus(final BigDecimal a, final BigDecimal b) {
      return a.$minus(b);
   }

   // $FF: synthetic method
   static BigDecimal negate$(final BigDecimalIsField $this, final BigDecimal a) {
      return $this.negate(a);
   }

   default BigDecimal negate(final BigDecimal a) {
      return a.unary_$minus();
   }

   BigDecimal one();

   // $FF: synthetic method
   static BigDecimal plus$(final BigDecimalIsField $this, final BigDecimal a, final BigDecimal b) {
      return $this.plus(a, b);
   }

   default BigDecimal plus(final BigDecimal a, final BigDecimal b) {
      return a.$plus(b);
   }

   // $FF: synthetic method
   static BigDecimal pow$(final BigDecimalIsField $this, final BigDecimal a, final int b) {
      return $this.pow(a, b);
   }

   default BigDecimal pow(final BigDecimal a, final int b) {
      return a.pow(b);
   }

   // $FF: synthetic method
   static BigDecimal times$(final BigDecimalIsField $this, final BigDecimal a, final BigDecimal b) {
      return $this.times(a, b);
   }

   default BigDecimal times(final BigDecimal a, final BigDecimal b) {
      return a.$times(b);
   }

   BigDecimal zero();

   // $FF: synthetic method
   static BigDecimal fromInt$(final BigDecimalIsField $this, final int n) {
      return $this.fromInt(n);
   }

   default BigDecimal fromInt(final int n) {
      return .MODULE$.BigDecimal().apply(n);
   }

   // $FF: synthetic method
   static BigDecimal fromDouble$(final BigDecimalIsField $this, final double n) {
      return $this.fromDouble(n);
   }

   default BigDecimal fromDouble(final double n) {
      return .MODULE$.BigDecimal().apply(n, MathContext.UNLIMITED);
   }

   // $FF: synthetic method
   static BigDecimal div$(final BigDecimalIsField $this, final BigDecimal a, final BigDecimal b) {
      return $this.div(a, b);
   }

   default BigDecimal div(final BigDecimal a, final BigDecimal b) {
      return a.$div(b);
   }

   static void $init$(final BigDecimalIsField $this) {
      $this.spire$std$BigDecimalIsField$_setter_$one_$eq(.MODULE$.BigDecimal().apply((double)1.0F));
      $this.spire$std$BigDecimalIsField$_setter_$zero_$eq(.MODULE$.BigDecimal().apply((double)0.0F));
   }
}
