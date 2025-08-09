package spire.math;

import java.math.MathContext;
import java.math.RoundingMode;
import scala.Product;
import scala.collection.Iterator;
import scala.math.BigDecimal;
import scala.runtime.Statics;
import scala.runtime.ScalaRunTime.;

public class Algebraic$Expr$ConstantBigDecimal extends Algebraic$Expr$Constant implements Product {
   private static final long serialVersionUID = 0L;
   private final BigDecimal value;

   public Iterator productElementNames() {
      return Product.productElementNames$(this);
   }

   public BigDecimal value() {
      return this.value;
   }

   public int flagBits() {
      return Algebraic$Expr$Flags$.MODULE$.BigDecimalLeaf();
   }

   public long upperBound() {
      long var10000;
      if (this.value().signum() == 0) {
         var10000 = 0L;
      } else {
         MathContext mc = new MathContext(4, RoundingMode.UP);
         var10000 = package$.MODULE$.ceil(package$.MODULE$.log(this.value().abs().apply(mc))).toLong();
      }

      return var10000;
   }

   public int signum() {
      return this.value().signum();
   }

   public java.math.BigDecimal toBigDecimal(final int digits) {
      return this.value().bigDecimal().setScale(digits, RoundingMode.HALF_UP);
   }

   public Algebraic$Expr$ConstantBigDecimal copy(final BigDecimal value) {
      return new Algebraic$Expr$ConstantBigDecimal(value);
   }

   public BigDecimal copy$default$1() {
      return this.value();
   }

   public String productPrefix() {
      return "ConstantBigDecimal";
   }

   public int productArity() {
      return 1;
   }

   public Object productElement(final int x$1) {
      Object var10000;
      switch (x$1) {
         case 0:
            var10000 = this.value();
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
      return x$1 instanceof Algebraic$Expr$ConstantBigDecimal;
   }

   public String productElementName(final int x$1) {
      String var10000;
      switch (x$1) {
         case 0:
            var10000 = "value";
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
            if (x$1 instanceof Algebraic$Expr$ConstantBigDecimal) {
               var2 = true;
            } else {
               var2 = false;
            }

            if (var2) {
               label36: {
                  label35: {
                     Algebraic$Expr$ConstantBigDecimal var4 = (Algebraic$Expr$ConstantBigDecimal)x$1;
                     BigDecimal var10000 = this.value();
                     BigDecimal var5 = var4.value();
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

   public Algebraic$Expr$ConstantBigDecimal(final BigDecimal value) {
      this.value = value;
      Product.$init$(this);
   }
}
