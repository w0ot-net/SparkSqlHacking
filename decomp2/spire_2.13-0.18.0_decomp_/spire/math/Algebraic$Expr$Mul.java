package spire.math;

import java.math.BigDecimal;
import java.math.RoundingMode;
import scala.Product;
import scala.collection.Iterator;
import scala.runtime.Statics;
import scala.runtime.ScalaRunTime.;
import spire.macros.ArithmeticOverflowException;

public class Algebraic$Expr$Mul extends Algebraic$Expr$BinaryExpr implements Product {
   private static final long serialVersionUID = 0L;
   private final Algebraic.Expr lhs;
   private final Algebraic.Expr rhs;

   public Iterator productElementNames() {
      return Product.productElementNames$(this);
   }

   public Algebraic.Expr lhs() {
      return this.lhs;
   }

   public Algebraic.Expr rhs() {
      return this.rhs;
   }

   public long upperBound() {
      return Algebraic.BitBound$.MODULE$.$plus$extension(this.lhs().upperBound(), this.rhs().upperBound());
   }

   public int signum() {
      return this.lhs().signum() * this.rhs().signum();
   }

   public BigDecimal toBigDecimal(final int digits) {
      long lDigits = this.checked$attempt$macro$1$2(digits);
      long rDigits = this.checked$attempt$macro$7$1(digits);
      if (lDigits < 2147483647L && rDigits < 2147483647L) {
         BigDecimal lValue = this.lhs().toBigDecimal((int)lDigits);
         BigDecimal rValue = this.rhs().toBigDecimal((int)rDigits);
         return lValue.multiply(rValue).setScale(digits, RoundingMode.DOWN);
      } else {
         throw new IllegalArgumentException("required precision is too high");
      }
   }

   public Algebraic$Expr$Mul copy(final Algebraic.Expr lhs, final Algebraic.Expr rhs) {
      return new Algebraic$Expr$Mul(lhs, rhs);
   }

   public Algebraic.Expr copy$default$1() {
      return this.lhs();
   }

   public Algebraic.Expr copy$default$2() {
      return this.rhs();
   }

   public String productPrefix() {
      return "Mul";
   }

   public int productArity() {
      return 2;
   }

   public Object productElement(final int x$1) {
      Object var10000;
      switch (x$1) {
         case 0:
            var10000 = this.lhs();
            break;
         case 1:
            var10000 = this.rhs();
            break;
         default:
            var10000 = Statics.ioobe(x$1);
      }

      return var10000;
   }

   public Iterator productIterator() {
      return .MODULE$.typedProductIterator(this);
   }

   public boolean canEqual(final Object x$1) {
      return x$1 instanceof Algebraic$Expr$Mul;
   }

   public String productElementName(final int x$1) {
      String var10000;
      switch (x$1) {
         case 0:
            var10000 = "lhs";
            break;
         case 1:
            var10000 = "rhs";
            break;
         default:
            var10000 = (String)Statics.ioobe(x$1);
      }

      return var10000;
   }

   public int hashCode() {
      return .MODULE$._hashCode(this);
   }

   public String toString() {
      return .MODULE$._toString(this);
   }

   public boolean equals(final Object x$1) {
      boolean var9;
      if (this != x$1) {
         label63: {
            boolean var2;
            if (x$1 instanceof Algebraic$Expr$Mul) {
               var2 = true;
            } else {
               var2 = false;
            }

            if (var2) {
               label45: {
                  label54: {
                     Algebraic$Expr$Mul var4 = (Algebraic$Expr$Mul)x$1;
                     Algebraic.Expr var10000 = this.lhs();
                     Algebraic.Expr var5 = var4.lhs();
                     if (var10000 == null) {
                        if (var5 != null) {
                           break label54;
                        }
                     } else if (!var10000.equals(var5)) {
                        break label54;
                     }

                     var10000 = this.rhs();
                     Algebraic.Expr var6 = var4.rhs();
                     if (var10000 == null) {
                        if (var6 != null) {
                           break label54;
                        }
                     } else if (!var10000.equals(var6)) {
                        break label54;
                     }

                     if (var4.canEqual(this)) {
                        var9 = true;
                        break label45;
                     }
                  }

                  var9 = false;
               }

               if (var9) {
                  break label63;
               }
            }

            var9 = false;
            return var9;
         }
      }

      var9 = true;
      return var9;
   }

   private static final long checked$fallback$macro$2$2() {
      throw new ArithmeticOverflowException();
   }

   private final long checked$attempt$macro$1$2(final int digits$1) {
      long x$macro$6 = Algebraic.BitBound$.MODULE$.decimalDigits$extension(this.rhs().upperBound());
      long z$macro$5 = x$macro$6 + (long)digits$1;
      if ((~(x$macro$6 ^ (long)digits$1) & (x$macro$6 ^ z$macro$5)) < 0L) {
         return checked$fallback$macro$2$2();
      } else {
         long z$macro$3 = z$macro$5 + 1L;
         return (~(z$macro$5 ^ 1L) & (z$macro$5 ^ z$macro$3)) < 0L ? checked$fallback$macro$2$2() : z$macro$3;
      }
   }

   private static final long checked$fallback$macro$8$1() {
      throw new ArithmeticOverflowException();
   }

   private final long checked$attempt$macro$7$1(final int digits$1) {
      long x$macro$12 = Algebraic.BitBound$.MODULE$.decimalDigits$extension(this.lhs().upperBound());
      long z$macro$11 = x$macro$12 + (long)digits$1;
      if ((~(x$macro$12 ^ (long)digits$1) & (x$macro$12 ^ z$macro$11)) < 0L) {
         return checked$fallback$macro$8$1();
      } else {
         long z$macro$9 = z$macro$11 + 1L;
         return (~(z$macro$11 ^ 1L) & (z$macro$11 ^ z$macro$9)) < 0L ? checked$fallback$macro$8$1() : z$macro$9;
      }
   }

   public Algebraic$Expr$Mul(final Algebraic.Expr lhs, final Algebraic.Expr rhs) {
      this.lhs = lhs;
      this.rhs = rhs;
      Product.$init$(this);
   }
}
