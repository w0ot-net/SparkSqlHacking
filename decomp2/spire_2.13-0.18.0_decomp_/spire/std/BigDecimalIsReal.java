package spire.std;

import scala.math.BigDecimal;
import scala.math.BigDecimal.RoundingMode.;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;
import spire.algebra.IsRational;
import spire.math.Rational;
import spire.math.Rational$;

@ScalaSignature(
   bytes = "\u0006\u0005A3q\u0001C\u0005\u0011\u0002\u0007\u0005a\u0002C\u0003,\u0001\u0011\u0005A\u0006C\u00031\u0001\u0011\u0005\u0011\u0007C\u00038\u0001\u0011\u0005\u0001\bC\u0003<\u0001\u0011\u0005A\bC\u0003?\u0001\u0011\u0005q\bC\u0003B\u0001\u0011\u0005!\tC\u0003H\u0001\u0011\u0005\u0001J\u0001\tCS\u001e$UmY5nC2L5OU3bY*\u0011!bC\u0001\u0004gR$'\"\u0001\u0007\u0002\u000bM\u0004\u0018N]3\u0004\u0001M!\u0001aD\u000b(!\t\u00012#D\u0001\u0012\u0015\u0005\u0011\u0012!B:dC2\f\u0017B\u0001\u000b\u0012\u0005\u0019\te.\u001f*fMB\u0019a#G\u000e\u000e\u0003]Q!\u0001G\u0006\u0002\u000f\u0005dw-\u001a2sC&\u0011!d\u0006\u0002\u000b\u0013N\u0014\u0016\r^5p]\u0006d\u0007C\u0001\u000f%\u001d\ti\"E\u0004\u0002\u001fC5\tqD\u0003\u0002!\u001b\u00051AH]8pizJ\u0011AE\u0005\u0003GE\tq\u0001]1dW\u0006<W-\u0003\u0002&M\tQ!)[4EK\u000eLW.\u00197\u000b\u0005\r\n\u0002C\u0001\u0015*\u001b\u0005I\u0011B\u0001\u0016\n\u0005m\u0011\u0015n\u001a#fG&l\u0017\r\u001c+sk:\u001c\u0017\r^3e\t&4\u0018n]5p]\u00061A%\u001b8ji\u0012\"\u0012!\f\t\u0003!9J!aL\t\u0003\tUs\u0017\u000e^\u0001\ti>$u.\u001e2mKR\u0011!'\u000e\t\u0003!MJ!\u0001N\t\u0003\r\u0011{WO\u00197f\u0011\u00151$\u00011\u0001\u001c\u0003\u0005A\u0018\u0001B2fS2$\"aG\u001d\t\u000bi\u001a\u0001\u0019A\u000e\u0002\u0003\u0005\fQA\u001a7p_J$\"aG\u001f\t\u000bi\"\u0001\u0019A\u000e\u0002\u000bI|WO\u001c3\u0015\u0005m\u0001\u0005\"\u0002\u001e\u0006\u0001\u0004Y\u0012aB5t/\"|G.\u001a\u000b\u0003\u0007\u001a\u0003\"\u0001\u0005#\n\u0005\u0015\u000b\"a\u0002\"p_2,\u0017M\u001c\u0005\u0006u\u0019\u0001\raG\u0001\u000bi>\u0014\u0016\r^5p]\u0006dGCA%P!\tQU*D\u0001L\u0015\ta5\"\u0001\u0003nCRD\u0017B\u0001(L\u0005!\u0011\u0016\r^5p]\u0006d\u0007\"\u0002\u001e\b\u0001\u0004Y\u0002"
)
public interface BigDecimalIsReal extends IsRational, BigDecimalTruncatedDivision {
   // $FF: synthetic method
   static double toDouble$(final BigDecimalIsReal $this, final BigDecimal x) {
      return $this.toDouble(x);
   }

   default double toDouble(final BigDecimal x) {
      return x.toDouble();
   }

   // $FF: synthetic method
   static BigDecimal ceil$(final BigDecimalIsReal $this, final BigDecimal a) {
      return $this.ceil(a);
   }

   default BigDecimal ceil(final BigDecimal a) {
      return a.setScale(0, .MODULE$.CEILING());
   }

   // $FF: synthetic method
   static BigDecimal floor$(final BigDecimalIsReal $this, final BigDecimal a) {
      return $this.floor(a);
   }

   default BigDecimal floor(final BigDecimal a) {
      return a.setScale(0, .MODULE$.FLOOR());
   }

   // $FF: synthetic method
   static BigDecimal round$(final BigDecimalIsReal $this, final BigDecimal a) {
      return $this.round(a);
   }

   default BigDecimal round(final BigDecimal a) {
      return a.setScale(0, .MODULE$.HALF_UP());
   }

   // $FF: synthetic method
   static boolean isWhole$(final BigDecimalIsReal $this, final BigDecimal a) {
      return $this.isWhole(a);
   }

   default boolean isWhole(final BigDecimal a) {
      return BoxesRunTime.equalsNumObject(a.$percent(scala.math.BigDecimal..MODULE$.double2bigDecimal((double)1.0F)), BoxesRunTime.boxToDouble((double)0.0F));
   }

   // $FF: synthetic method
   static Rational toRational$(final BigDecimalIsReal $this, final BigDecimal a) {
      return $this.toRational(a);
   }

   default Rational toRational(final BigDecimal a) {
      return Rational$.MODULE$.apply(a);
   }

   static void $init$(final BigDecimalIsReal $this) {
   }
}
