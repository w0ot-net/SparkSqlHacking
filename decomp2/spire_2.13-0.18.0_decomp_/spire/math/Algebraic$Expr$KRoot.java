package spire.math;

import java.math.BigDecimal;
import java.math.RoundingMode;
import scala.Product;
import scala.collection.Iterator;
import scala.runtime.BoxesRunTime;
import scala.runtime.Statics;
import scala.runtime.ScalaRunTime.;
import spire.macros.ArithmeticOverflowException;

public class Algebraic$Expr$KRoot extends Algebraic$Expr$UnaryExpr implements Product {
   private static final long serialVersionUID = 0L;
   private int hashCode;
   private final Algebraic.Expr sub;
   private final int k;
   private final int flagBits;
   private volatile boolean bitmap$0;

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
      return this.flagBits;
   }

   public long upperBound() {
      return Algebraic.BitBound$.MODULE$.$div$extension(Algebraic.BitBound$.MODULE$.$plus$extension(this.sub().upperBound(), 1), 2);
   }

   public int signum() {
      int s = this.sub().signum();
      if (s >= 0) {
         return s;
      } else {
         throw new ArithmeticException((new StringBuilder(24)).append(this.k()).append("-root of negative number").toString());
      }
   }

   public BigDecimal toBigDecimal(final int digits) {
      long digits0 = package$.MODULE$.max((long)checked$attempt$macro$1$4(digits), this.checked$attempt$macro$4$1());
      if (digits0 >= 2147483647L) {
         throw new IllegalArgumentException("required precision is too high");
      } else {
         BigDecimal value = this.sub().toBigDecimal((int)digits0);
         return Algebraic$.MODULE$.nroot(value, this.k(), digits, RoundingMode.DOWN);
      }
   }

   private int hashCode$lzycompute() {
      synchronized(this){}

      try {
         if (!this.bitmap$0) {
            this.hashCode = this.sub().hashCode() * 23 + this.k() * 29 + 13;
            this.bitmap$0 = true;
         }
      } catch (Throwable var3) {
         throw var3;
      }

      return this.hashCode;
   }

   public int hashCode() {
      return !this.bitmap$0 ? this.hashCode$lzycompute() : this.hashCode;
   }

   public Algebraic$Expr$KRoot copy(final Algebraic.Expr sub, final int k) {
      return new Algebraic$Expr$KRoot(sub, k);
   }

   public Algebraic.Expr copy$default$1() {
      return this.sub();
   }

   public int copy$default$2() {
      return this.k();
   }

   public String productPrefix() {
      return "KRoot";
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
      return x$1 instanceof Algebraic$Expr$KRoot;
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

   public String toString() {
      return .MODULE$._toString(this);
   }

   public boolean equals(final Object x$1) {
      boolean var7;
      if (this != x$1) {
         label55: {
            boolean var2;
            if (x$1 instanceof Algebraic$Expr$KRoot) {
               var2 = true;
            } else {
               var2 = false;
            }

            if (var2) {
               label38: {
                  Algebraic$Expr$KRoot var4 = (Algebraic$Expr$KRoot)x$1;
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

   private static final int checked$fallback$macro$2$4() {
      throw new ArithmeticOverflowException();
   }

   private static final int checked$attempt$macro$1$4(final int digits$3) {
      int z$macro$3 = digits$3 + 1;
      return (~(digits$3 ^ 1) & (digits$3 ^ z$macro$3)) < 0 ? checked$fallback$macro$2$4() : z$macro$3;
   }

   private static final long checked$fallback$macro$5$1() {
      throw new ArithmeticOverflowException();
   }

   private final long checked$attempt$macro$4$1() {
      long x$macro$11 = Algebraic.BitBound$.MODULE$.decimalDigits$extension(this.sub().lowerBound());
      long z$macro$10 = x$macro$11 + 1L;
      if ((~(x$macro$11 ^ 1L) & (x$macro$11 ^ z$macro$10)) < 0L) {
         return checked$fallback$macro$5$1();
      } else {
         long z$macro$8 = z$macro$10 / 2L;
         if (false && z$macro$10 == Long.MIN_VALUE) {
            return checked$fallback$macro$5$1();
         } else {
            long z$macro$6 = 1L - z$macro$8;
            return ((1L ^ z$macro$8) & (1L ^ z$macro$6)) < 0L ? checked$fallback$macro$5$1() : z$macro$6;
         }
      }
   }

   public Algebraic$Expr$KRoot(final Algebraic.Expr sub, final int k) {
      this.sub = sub;
      this.k = k;
      Product.$init$(this);
      this.flagBits = Algebraic$Expr$Flags$.MODULE$.$bar$extension(sub.flags(), Algebraic$Expr$Flags$.MODULE$.IsRadical());
   }
}
