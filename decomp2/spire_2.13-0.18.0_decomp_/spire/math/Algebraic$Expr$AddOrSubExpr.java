package spire.math;

import java.math.BigDecimal;
import java.math.RoundingMode;
import scala.MatchError;

public abstract class Algebraic$Expr$AddOrSubExpr extends Algebraic$Expr$BinaryExpr {
   private static final long serialVersionUID = 0L;
   private int signum;
   private volatile boolean bitmap$0;

   public long upperBound() {
      return package$.MODULE$.max(this.lhs().upperBound(), this.rhs().upperBound()) + 1L;
   }

   private int signum$lzycompute() {
      synchronized(this){}

      try {
         if (!this.bitmap$0) {
            long maxDigits = Algebraic.BitBound$.MODULE$.decimalDigits$extension(this.separationBound()) + 1L;
            this.signum = this.loop$1(4L, maxDigits);
            this.bitmap$0 = true;
         }
      } catch (Throwable var5) {
         throw var5;
      }

      return this.signum;
   }

   public int signum() {
      return !this.bitmap$0 ? this.signum$lzycompute() : this.signum;
   }

   public BigDecimal toBigDecimal(final int digits) {
      BigDecimal lValue = this.lhs().toBigDecimal(digits + 1);
      BigDecimal rValue = this.rhs().toBigDecimal(digits + 1);
      BigDecimal sum;
      if (this instanceof Algebraic$Expr$Add) {
         sum = lValue.add(rValue);
      } else {
         if (!(this instanceof Algebraic$Expr$Sub)) {
            throw new MatchError(this);
         }

         sum = lValue.subtract(rValue);
      }

      BigDecimal result = sum.setScale(digits, RoundingMode.DOWN);
      return result;
   }

   private final int loop$1(final long digits0, final long maxDigits$1) {
      while(true) {
         int digits = (int)package$.MODULE$.min(digits0, package$.MODULE$.min(maxDigits$1, 2147483647L));
         BigDecimal approx = this.toBigDecimal(digits + 1).setScale(digits, RoundingMode.DOWN);
         if (approx.signum() != 0 || (long)digits >= maxDigits$1) {
            return approx.signum();
         }

         if (digits == Integer.MAX_VALUE) {
            throw new ArithmeticException("required precision to calculate sign is too high");
         }

         digits0 = 2L * digits0;
      }
   }
}
