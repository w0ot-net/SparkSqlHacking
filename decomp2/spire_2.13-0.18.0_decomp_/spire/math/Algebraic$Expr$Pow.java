package spire.math;

import java.math.BigDecimal;
import scala.Product;
import scala.collection.Iterator;
import scala.runtime.BoxesRunTime;
import scala.runtime.Statics;
import scala.runtime.ScalaRunTime.;
import spire.macros.ArithmeticOverflowException;

public class Algebraic$Expr$Pow extends Algebraic$Expr$UnaryExpr implements Product {
   private static final long serialVersionUID = 0L;
   private final Algebraic.Expr sub;
   private final int k;

   public Iterator productElementNames() {
      return Product.productElementNames$(this);
   }

   public Algebraic.Expr sub() {
      return this.sub;
   }

   public int k() {
      return this.k;
   }

   public int flagBits() {
      return this.sub().flags();
   }

   public long upperBound() {
      return Algebraic.BitBound$.MODULE$.$times$extension(this.sub().upperBound(), this.k());
   }

   public int signum() {
      int s = this.sub().signum();
      int var10000;
      if (s == 0) {
         if (this.k() < 0) {
            throw new ArithmeticException("divide by 0");
         }

         if (this.k() == 0) {
            throw new ArithmeticException("indeterminate");
         }

         var10000 = 0;
      } else {
         var10000 = this.k() % 2 == 0 ? (s < 0 ? 1 : s) : s;
      }

      return var10000;
   }

   public BigDecimal toBigDecimal(final int digits) {
      int height = 32 - Integer.numberOfLeadingZeros(this.k() - 1);
      long maxDigits = this.checked$attempt$macro$1$5(height, digits);
      if (maxDigits >= 2147483647L) {
         throw new IllegalArgumentException("required precision is too high");
      } else {
         BigDecimal leafValue = this.sub().toBigDecimal((int)maxDigits);
         return leafValue.pow(this.k());
      }
   }

   public Algebraic$Expr$Pow copy(final Algebraic.Expr sub, final int k) {
      return new Algebraic$Expr$Pow(sub, k);
   }

   public Algebraic.Expr copy$default$1() {
      return this.sub();
   }

   public int copy$default$2() {
      return this.k();
   }

   public String productPrefix() {
      return "Pow";
   }

   public int productArity() {
      return 2;
   }

   public Object productElement(final int x$1) {
      Object var10000;
      switch (x$1) {
         case 0:
            var10000 = this.sub();
            break;
         case 1:
            var10000 = BoxesRunTime.boxToInteger(this.k());
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
      return x$1 instanceof Algebraic$Expr$Pow;
   }

   public String productElementName(final int x$1) {
      String var10000;
      switch (x$1) {
         case 0:
            var10000 = "sub";
            break;
         case 1:
            var10000 = "k";
            break;
         default:
            var10000 = (String)Statics.ioobe(x$1);
      }

      return var10000;
   }

   public int hashCode() {
      int var1 = -889275714;
      var1 = Statics.mix(var1, this.productPrefix().hashCode());
      var1 = Statics.mix(var1, Statics.anyHash(this.sub()));
      var1 = Statics.mix(var1, this.k());
      return Statics.finalizeHash(var1, 2);
   }

   public String toString() {
      return .MODULE$._toString(this);
   }

   public boolean equals(final Object x$1) {
      boolean var7;
      if (this != x$1) {
         label55: {
            boolean var2;
            if (x$1 instanceof Algebraic$Expr$Pow) {
               var2 = true;
            } else {
               var2 = false;
            }

            if (var2) {
               label38: {
                  Algebraic$Expr$Pow var4 = (Algebraic$Expr$Pow)x$1;
                  if (this.k() == var4.k()) {
                     label36: {
                        Algebraic.Expr var10000 = this.sub();
                        Algebraic.Expr var5 = var4.sub();
                        if (var10000 == null) {
                           if (var5 != null) {
                              break label36;
                           }
                        } else if (!var10000.equals(var5)) {
                           break label36;
                        }

                        if (var4.canEqual(this)) {
                           var7 = true;
                           break label38;
                        }
                     }
                  }

                  var7 = false;
               }

               if (var7) {
                  break label55;
               }
            }

            var7 = false;
            return var7;
         }
      }

      var7 = true;
      return var7;
   }

   private static final long checked$fallback$macro$2$5() {
      throw new ArithmeticOverflowException();
   }

   private final long checked$attempt$macro$1$5(final int height$1, final int digits$4) {
      long y$macro$8 = Algebraic.BitBound$.MODULE$.decimalDigits$extension(this.sub().upperBound());
      long z$macro$7 = 1L + y$macro$8;
      if ((~(1L ^ y$macro$8) & (1L ^ z$macro$7)) < 0L) {
         return checked$fallback$macro$2$5();
      } else {
         long z$macro$5 = (long)height$1 * z$macro$7;
         if (height$1 == 0 || z$macro$7 == z$macro$5 / (long)height$1 && (height$1 != -1 || z$macro$7 != Long.MIN_VALUE)) {
            long z$macro$3 = (long)digits$4 + z$macro$5;
            return (~((long)digits$4 ^ z$macro$5) & ((long)digits$4 ^ z$macro$3)) < 0L ? checked$fallback$macro$2$5() : z$macro$3;
         } else {
            return checked$fallback$macro$2$5();
         }
      }
   }

   public Algebraic$Expr$Pow(final Algebraic.Expr sub, final int k) {
      this.sub = sub;
      this.k = k;
      Product.$init$(this);
      scala.Predef..MODULE$.require(k > 1);
   }
}
