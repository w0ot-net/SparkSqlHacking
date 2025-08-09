package spire.std;

import scala.reflect.ScalaSignature;
import spire.algebra.IsRational;
import spire.math.Rational;
import spire.math.Rational$;

@ScalaSignature(
   bytes = "\u0006\u0005\u00113q\u0001C\u0005\u0011\u0002\u0007\u0005a\u0002C\u0003#\u0001\u0011\u00051\u0005C\u0003(\u0001\u0011\u0005\u0001\u0006C\u0003,\u0001\u0011\u0005A\u0006C\u00030\u0001\u0011\u0005\u0001\u0007C\u00033\u0001\u0011\u00051\u0007C\u00036\u0001\u0011\u0005a\u0007C\u0003<\u0001\u0011\u0005AH\u0001\u0007E_V\u0014G.Z%t%\u0016\fGN\u0003\u0002\u000b\u0017\u0005\u00191\u000f\u001e3\u000b\u00031\tQa\u001d9je\u0016\u001c\u0001a\u0005\u0003\u0001\u001fUq\u0002C\u0001\t\u0014\u001b\u0005\t\"\"\u0001\n\u0002\u000bM\u001c\u0017\r\\1\n\u0005Q\t\"AB!osJ+g\rE\u0002\u00173mi\u0011a\u0006\u0006\u00031-\tq!\u00197hK\n\u0014\u0018-\u0003\u0002\u001b/\tQ\u0011j\u001d*bi&|g.\u00197\u0011\u0005Aa\u0012BA\u000f\u0012\u0005\u0019!u.\u001e2mKB\u0011q\u0004I\u0007\u0002\u0013%\u0011\u0011%\u0003\u0002\u0018\t>,(\r\\3UeVt7-\u0019;fI\u0012Kg/[:j_:\fa\u0001J5oSR$C#\u0001\u0013\u0011\u0005A)\u0013B\u0001\u0014\u0012\u0005\u0011)f.\u001b;\u0002\u0011Q|Gi\\;cY\u0016$\"aG\u0015\t\u000b)\u0012\u0001\u0019A\u000e\u0002\u0003a\fAaY3jYR\u00111$\f\u0005\u0006]\r\u0001\raG\u0001\u0002C\u0006)a\r\\8peR\u00111$\r\u0005\u0006]\u0011\u0001\raG\u0001\u0006e>,h\u000e\u001a\u000b\u00037QBQAL\u0003A\u0002m\tq![:XQ>dW\r\u0006\u00028uA\u0011\u0001\u0003O\u0005\u0003sE\u0011qAQ8pY\u0016\fg\u000eC\u0003/\r\u0001\u00071$\u0001\u0006u_J\u000bG/[8oC2$\"!P\"\u0011\u0005y\nU\"A \u000b\u0005\u0001[\u0011\u0001B7bi\"L!AQ \u0003\u0011I\u000bG/[8oC2DQAL\u0004A\u0002m\u0001"
)
public interface DoubleIsReal extends IsRational, DoubleTruncatedDivision {
   // $FF: synthetic method
   static double toDouble$(final DoubleIsReal $this, final double x) {
      return $this.toDouble(x);
   }

   default double toDouble(final double x) {
      return this.toDouble$mcD$sp(x);
   }

   // $FF: synthetic method
   static double ceil$(final DoubleIsReal $this, final double a) {
      return $this.ceil(a);
   }

   default double ceil(final double a) {
      return this.ceil$mcD$sp(a);
   }

   // $FF: synthetic method
   static double floor$(final DoubleIsReal $this, final double a) {
      return $this.floor(a);
   }

   default double floor(final double a) {
      return this.floor$mcD$sp(a);
   }

   // $FF: synthetic method
   static double round$(final DoubleIsReal $this, final double a) {
      return $this.round(a);
   }

   default double round(final double a) {
      return this.round$mcD$sp(a);
   }

   // $FF: synthetic method
   static boolean isWhole$(final DoubleIsReal $this, final double a) {
      return $this.isWhole(a);
   }

   default boolean isWhole(final double a) {
      return this.isWhole$mcD$sp(a);
   }

   // $FF: synthetic method
   static Rational toRational$(final DoubleIsReal $this, final double a) {
      return $this.toRational(a);
   }

   default Rational toRational(final double a) {
      return Rational$.MODULE$.apply(a);
   }

   // $FF: synthetic method
   static double toDouble$mcD$sp$(final DoubleIsReal $this, final double x) {
      return $this.toDouble$mcD$sp(x);
   }

   default double toDouble$mcD$sp(final double x) {
      return x;
   }

   // $FF: synthetic method
   static double ceil$mcD$sp$(final DoubleIsReal $this, final double a) {
      return $this.ceil$mcD$sp(a);
   }

   default double ceil$mcD$sp(final double a) {
      return Math.ceil(a);
   }

   // $FF: synthetic method
   static double floor$mcD$sp$(final DoubleIsReal $this, final double a) {
      return $this.floor$mcD$sp(a);
   }

   default double floor$mcD$sp(final double a) {
      return Math.floor(a);
   }

   // $FF: synthetic method
   static double round$mcD$sp$(final DoubleIsReal $this, final double a) {
      return $this.round$mcD$sp(a);
   }

   default double round$mcD$sp(final double a) {
      return spire.math.package$.MODULE$.round(a);
   }

   // $FF: synthetic method
   static boolean isWhole$mcD$sp$(final DoubleIsReal $this, final double a) {
      return $this.isWhole$mcD$sp(a);
   }

   default boolean isWhole$mcD$sp(final double a) {
      return a % (double)1.0F == (double)0.0F;
   }

   static void $init$(final DoubleIsReal $this) {
   }
}
