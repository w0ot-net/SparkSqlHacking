package spire.math;

import java.math.BigDecimal;
import java.math.RoundingMode;
import scala.Product;
import scala.collection.Iterator;
import scala.runtime.Statics;
import scala.runtime.ScalaRunTime.;
import spire.macros.ArithmeticOverflowException;

public class Algebraic$Expr$Div extends Algebraic$Expr$BinaryExpr implements Product {
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
      return Algebraic.BitBound$.MODULE$.$minus$extension(this.lhs().upperBound(), this.rhs().lowerBound());
   }

   public int signum() {
      if (this.rhs().signum() == 0) {
         throw new ArithmeticException("divide by 0");
      } else {
         return this.lhs().signum() * this.rhs().signum();
      }
   }

   public BigDecimal toBigDecimal(final int digits) {
      return this.checked$attempt$macro$1$3(digits);
   }

   public Algebraic$Expr$Div copy(final Algebraic.Expr lhs, final Algebraic.Expr rhs) {
      return new Algebraic$Expr$Div(lhs, rhs);
   }

   public Algebraic.Expr copy$default$1() {
      return this.lhs();
   }

   public Algebraic.Expr copy$default$2() {
      return this.rhs();
   }

   public String productPrefix() {
      return "Div";
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
      return x$1 instanceof Algebraic$Expr$Div;
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
            if (x$1 instanceof Algebraic$Expr$Div) {
               var2 = true;
            } else {
               var2 = false;
            }

            if (var2) {
               label45: {
                  label54: {
                     Algebraic$Expr$Div var4 = (Algebraic$Expr$Div)x$1;
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

   private static final BigDecimal checked$fallback$macro$2$3() {
      throw new ArithmeticOverflowException();
   }

   private final BigDecimal checked$attempt$macro$1$3(final int digits$2) {
      int z$macro$6 = digits$2 + 2;
      if ((~(digits$2 ^ 2) & (digits$2 ^ z$macro$6)) < 0) {
         return checked$fallback$macro$2$3();
      } else {
         long y$macro$5 = Algebraic.BitBound$.MODULE$.decimalDigits$extension(this.rhs().lowerBound());
         long z$macro$3 = (long)z$macro$6 - y$macro$5;
         if ((((long)z$macro$6 ^ y$macro$5) & ((long)z$macro$6 ^ z$macro$3)) < 0L) {
            return checked$fallback$macro$2$3();
         } else {
            package$ var10000 = package$.MODULE$;
            long y$macro$8 = Algebraic.BitBound$.MODULE$.decimalDigits$extension(this.rhs().lowerBound());
            long z$macro$7 = 1L - y$macro$8;
            if (((1L ^ y$macro$8) & (1L ^ z$macro$7)) < 0L) {
               return checked$fallback$macro$2$3();
            } else {
               int z$macro$15 = digits$2 + 4;
               if ((~(digits$2 ^ 4) & (digits$2 ^ z$macro$15)) < 0) {
                  return checked$fallback$macro$2$3();
               } else {
                  long y$macro$17 = Algebraic.BitBound$.MODULE$.decimalDigits$extension(this.rhs().lowerBound());
                  long z$macro$16 = 2L * y$macro$17;
                  if (false || y$macro$17 == z$macro$16 / 2L && (true || y$macro$17 != Long.MIN_VALUE)) {
                     long z$macro$12 = (long)z$macro$15 - z$macro$16;
                     if ((((long)z$macro$15 ^ z$macro$16) & ((long)z$macro$15 ^ z$macro$12)) < 0L) {
                        return checked$fallback$macro$2$3();
                     } else {
                        long y$macro$11 = Algebraic.BitBound$.MODULE$.decimalDigits$extension(this.lhs().upperBound());
                        long z$macro$9 = z$macro$12 + y$macro$11;
                        if ((~(z$macro$12 ^ y$macro$11) & (z$macro$12 ^ z$macro$9)) < 0L) {
                           return checked$fallback$macro$2$3();
                        } else {
                           long rDigits = var10000.max(z$macro$7, z$macro$9);
                           if (z$macro$3 < 2147483647L && rDigits < 2147483647L) {
                              BigDecimal lValue = this.lhs().toBigDecimal((int)z$macro$3);
                              BigDecimal rValue = this.rhs().toBigDecimal((int)rDigits);
                              int z$macro$18 = digits$2 + 1;
                              if ((~(digits$2 ^ 1) & (digits$2 ^ z$macro$18)) < 0) {
                                 return checked$fallback$macro$2$3();
                              } else {
                                 BigDecimal quotient = lValue.divide(rValue, z$macro$18, RoundingMode.DOWN);
                                 return quotient.setScale(digits$2, RoundingMode.DOWN);
                              }
                           } else {
                              throw new IllegalArgumentException("required precision is too high");
                           }
                        }
                     }
                  } else {
                     return checked$fallback$macro$2$3();
                  }
               }
            }
         }
      }
   }

   public Algebraic$Expr$Div(final Algebraic.Expr lhs, final Algebraic.Expr rhs) {
      this.lhs = lhs;
      this.rhs = rhs;
      Product.$init$(this);
   }
}
