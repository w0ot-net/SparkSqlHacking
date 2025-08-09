package spire.std;

import scala.math.BigDecimal;
import scala.package.;
import scala.reflect.ScalaSignature;
import spire.algebra.NRoot;

@ScalaSignature(
   bytes = "\u0006\u0005\r3qAB\u0004\u0011\u0002\u0007\u0005A\u0002C\u0003&\u0001\u0011\u0005a\u0005C\u0003+\u0001\u0011\u00051\u0006C\u00044\u0001\t\u0007K\u0011\u0002\u001b\t\u000bi\u0002A\u0011I\u001e\t\u000by\u0002A\u0011A \u0003#\tKw\rR3dS6\fG.S:O%>|GO\u0003\u0002\t\u0013\u0005\u00191\u000f\u001e3\u000b\u0003)\tQa\u001d9je\u0016\u001c\u0001aE\u0002\u0001\u001bM\u0001\"AD\t\u000e\u0003=Q\u0011\u0001E\u0001\u0006g\u000e\fG.Y\u0005\u0003%=\u0011a!\u00118z%\u00164\u0007c\u0001\u000b\u001835\tQC\u0003\u0002\u0017\u0013\u00059\u0011\r\\4fEJ\f\u0017B\u0001\r\u0016\u0005\u0015q%k\\8u!\tQ\"E\u0004\u0002\u001cA9\u0011AdH\u0007\u0002;)\u0011adC\u0001\u0007yI|w\u000e\u001e \n\u0003AI!!I\b\u0002\u000fA\f7m[1hK&\u00111\u0005\n\u0002\u000b\u0005&<G)Z2j[\u0006d'BA\u0011\u0010\u0003\u0019!\u0013N\\5uIQ\tq\u0005\u0005\u0002\u000fQ%\u0011\u0011f\u0004\u0002\u0005+:LG/A\u0003oe>|G\u000fF\u0002\u001aY9BQ!\f\u0002A\u0002e\t\u0011!\u0019\u0005\u0006_\t\u0001\r\u0001M\u0001\u0002WB\u0011a\"M\u0005\u0003e=\u00111!\u00138u\u0003\r!xo\\\u000b\u0002kA\u0011a'O\u0007\u0002o)\u0011\u0001hD\u0001\u0005[\u0006$\b.\u0003\u0002$o\u0005!1/\u001d:u)\tIB\bC\u0003>\t\u0001\u0007\u0011$A\u0001o\u0003\u00111\u0007o\\<\u0015\u0007e\u0001\u0015\tC\u0003.\u000b\u0001\u0007\u0011\u0004C\u0003C\u000b\u0001\u0007\u0011$A\u0001c\u0001"
)
public interface BigDecimalIsNRoot extends NRoot {
   void spire$std$BigDecimalIsNRoot$_setter_$spire$std$BigDecimalIsNRoot$$two_$eq(final BigDecimal x$1);

   // $FF: synthetic method
   static BigDecimal nroot$(final BigDecimalIsNRoot $this, final BigDecimal a, final int k) {
      return $this.nroot(a, k);
   }

   default BigDecimal nroot(final BigDecimal a, final int k) {
      if (a.mc().getPrecision() <= 0) {
         throw new ArithmeticException("Cannot find the nroot of a BigDecimal with unlimited precision.");
      } else {
         return spire.math.package$.MODULE$.nroot(a, k, a.mc());
      }
   }

   BigDecimal spire$std$BigDecimalIsNRoot$$two();

   // $FF: synthetic method
   static BigDecimal sqrt$(final BigDecimalIsNRoot $this, final BigDecimal n) {
      return $this.sqrt(n);
   }

   default BigDecimal sqrt(final BigDecimal n) {
      if (n.mc().getPrecision() <= 0) {
         throw new ArithmeticException("Cannot find the sqrt of a BigDecimal with unlimited precision.");
      } else {
         return this.loop$1(.MODULE$.BigDecimal().apply(0, n.mc()), approxSqrt$1(n), n);
      }
   }

   // $FF: synthetic method
   static BigDecimal fpow$(final BigDecimalIsNRoot $this, final BigDecimal a, final BigDecimal b) {
      return $this.fpow(a, b);
   }

   default BigDecimal fpow(final BigDecimal a, final BigDecimal b) {
      return spire.math.package$.MODULE$.pow(a, b);
   }

   private static BigDecimal approxSqrt$1(final BigDecimal x) {
      return x.$less(scala.math.BigDecimal..MODULE$.double2bigDecimal(Double.MAX_VALUE)) ? .MODULE$.BigDecimal().apply(Math.sqrt(x.toDouble()), x.mc()) : approxSqrt$1(x.$div(scala.math.BigDecimal..MODULE$.double2bigDecimal(Double.MAX_VALUE))).$times(.MODULE$.BigDecimal().apply(Math.sqrt(Double.MAX_VALUE), x.mc()));
   }

   private BigDecimal loop$1(final BigDecimal x, final BigDecimal y, final BigDecimal n$1) {
      BigDecimal var7;
      while(true) {
         if (x == null) {
            if (y == null) {
               break;
            }
         } else if (x.equals(y)) {
            break;
         }

         BigDecimal y2 = n$1.$div(y).$plus(y).$div(this.spire$std$BigDecimalIsNRoot$$two());
         if (y2.$less$eq(y)) {
            var7 = y;
            return var7;
         }

         var7 = y;
         y = y2;
         x = var7;
      }

      var7 = y;
      return var7;
   }

   static void $init$(final BigDecimalIsNRoot $this) {
      $this.spire$std$BigDecimalIsNRoot$_setter_$spire$std$BigDecimalIsNRoot$$two_$eq(.MODULE$.BigDecimal().apply(2));
   }
}
