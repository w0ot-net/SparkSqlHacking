package spire.math;

import java.math.BigDecimal;
import scala.Product;
import scala.collection.Iterator;
import scala.runtime.Statics;
import scala.runtime.ScalaRunTime.;

public class Algebraic$Expr$Neg extends Algebraic$Expr$UnaryExpr implements Product {
   private static final long serialVersionUID = 0L;
   private final Algebraic.Expr sub;

   public Iterator productElementNames() {
      return Product.productElementNames$(this);
   }

   public Algebraic.Expr sub() {
      return this.sub;
   }

   public int flagBits() {
      return this.sub().flags();
   }

   public long upperBound() {
      return this.sub().upperBound();
   }

   public int signum() {
      return -this.sub().signum();
   }

   public BigDecimal toBigDecimal(final int digits) {
      return this.sub().toBigDecimal(digits).negate();
   }

   public Algebraic$Expr$Neg copy(final Algebraic.Expr sub) {
      return new Algebraic$Expr$Neg(sub);
   }

   public Algebraic.Expr copy$default$1() {
      return this.sub();
   }

   public String productPrefix() {
      return "Neg";
   }

   public int productArity() {
      return 1;
   }

   public Object productElement(final int x$1) {
      Object var10000;
      switch (x$1) {
         case 0:
            var10000 = this.sub();
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
      return x$1 instanceof Algebraic$Expr$Neg;
   }

   public String productElementName(final int x$1) {
      String var10000;
      switch (x$1) {
         case 0:
            var10000 = "sub";
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
      boolean var7;
      if (this != x$1) {
         label53: {
            boolean var2;
            if (x$1 instanceof Algebraic$Expr$Neg) {
               var2 = true;
            } else {
               var2 = false;
            }

            if (var2) {
               label36: {
                  label35: {
                     Algebraic$Expr$Neg var4 = (Algebraic$Expr$Neg)x$1;
                     Algebraic.Expr var10000 = this.sub();
                     Algebraic.Expr var5 = var4.sub();
                     if (var10000 == null) {
                        if (var5 != null) {
                           break label35;
                        }
                     } else if (!var10000.equals(var5)) {
                        break label35;
                     }

                     if (var4.canEqual(this)) {
                        var7 = true;
                        break label36;
                     }
                  }

                  var7 = false;
               }

               if (var7) {
                  break label53;
               }
            }

            var7 = false;
            return var7;
         }
      }

      var7 = true;
      return var7;
   }

   public Algebraic$Expr$Neg(final Algebraic.Expr sub) {
      this.sub = sub;
      Product.$init$(this);
   }
}
