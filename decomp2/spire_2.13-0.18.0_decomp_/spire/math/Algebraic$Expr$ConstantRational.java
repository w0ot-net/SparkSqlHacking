package spire.math;

import java.math.BigDecimal;
import java.math.RoundingMode;
import scala.Product;
import scala.collection.Iterator;
import scala.runtime.BoxesRunTime;
import scala.runtime.Statics;
import scala.runtime.ScalaRunTime.;

public class Algebraic$Expr$ConstantRational extends Algebraic$Expr$Constant implements Product {
   private static final long serialVersionUID = 0L;
   private final Rational value;

   public Iterator productElementNames() {
      return Product.productElementNames$(this);
   }

   public Rational value() {
      return this.value;
   }

   public int flagBits() {
      return Algebraic$Expr$Flags$.MODULE$.RationalLeaf();
   }

   public long upperBound() {
      return (long)(this.value().numerator().abs().bitLength() - this.value().denominator().bitLength() + 1);
   }

   public int signum() {
      return this.value().signum();
   }

   public BigDecimal toBigDecimal(final int digits) {
      BigDecimal num = new BigDecimal(this.value().numerator().toBigInteger());
      BigDecimal den = new BigDecimal(this.value().denominator().toBigInteger());
      return num.divide(den, digits, RoundingMode.DOWN);
   }

   public Algebraic$Expr$ConstantRational copy(final Rational value) {
      return new Algebraic$Expr$ConstantRational(value);
   }

   public Rational copy$default$1() {
      return this.value();
   }

   public String productPrefix() {
      return "ConstantRational";
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
      return x$1 instanceof Algebraic$Expr$ConstantRational;
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
      boolean var10000;
      if (this != x$1) {
         label49: {
            boolean var2;
            if (x$1 instanceof Algebraic$Expr$ConstantRational) {
               var2 = true;
            } else {
               var2 = false;
            }

            if (var2) {
               Algebraic$Expr$ConstantRational var4 = (Algebraic$Expr$ConstantRational)x$1;
               if (BoxesRunTime.equalsNumNum(this.value(), var4.value()) && var4.canEqual(this)) {
                  break label49;
               }
            }

            var10000 = false;
            return var10000;
         }
      }

      var10000 = true;
      return var10000;
   }

   public Algebraic$Expr$ConstantRational(final Rational value) {
      this.value = value;
      Product.$init$(this);
   }
}
