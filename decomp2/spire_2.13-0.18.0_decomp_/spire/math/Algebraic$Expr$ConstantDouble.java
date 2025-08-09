package spire.math;

import java.math.BigDecimal;
import java.math.RoundingMode;
import scala.Product;
import scala.collection.Iterator;
import scala.runtime.BoxesRunTime;
import scala.runtime.Statics;
import scala.runtime.ScalaRunTime.;

public class Algebraic$Expr$ConstantDouble extends Algebraic$Expr$Constant implements Product {
   private static final long serialVersionUID = 0L;
   private final double value;

   public Iterator productElementNames() {
      return Product.productElementNames$(this);
   }

   public double value() {
      return this.value;
   }

   public int flagBits() {
      return Algebraic$Expr$Flags$.MODULE$.DoubleLeaf();
   }

   public long upperBound() {
      return this.value() == (double)0.0F ? 0L : (long)package$.MODULE$.ceil(package$.MODULE$.log(package$.MODULE$.abs(this.value())));
   }

   public int signum() {
      return this.value() < (double)0.0F ? -1 : (this.value() > (double)0.0F ? 1 : 0);
   }

   public BigDecimal toBigDecimal(final int digits) {
      return (new BigDecimal(this.value())).setScale(digits, RoundingMode.HALF_UP);
   }

   public Algebraic$Expr$ConstantDouble copy(final double value) {
      return new Algebraic$Expr$ConstantDouble(value);
   }

   public double copy$default$1() {
      return this.value();
   }

   public String productPrefix() {
      return "ConstantDouble";
   }

   public int productArity() {
      return 1;
   }

   public Object productElement(final int x$1) {
      Object var10000;
      switch (x$1) {
         case 0:
            var10000 = BoxesRunTime.boxToDouble(this.value());
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
      return x$1 instanceof Algebraic$Expr$ConstantDouble;
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
      int var1 = -889275714;
      var1 = Statics.mix(var1, this.productPrefix().hashCode());
      var1 = Statics.mix(var1, Statics.doubleHash(this.value()));
      return Statics.finalizeHash(var1, 1);
   }

   public String toString() {
      return .MODULE$._toString(this);
   }

   public boolean equals(final Object x$1) {
      boolean var10000;
      if (this != x$1) {
         label49: {
            boolean var2;
            if (x$1 instanceof Algebraic$Expr$ConstantDouble) {
               var2 = true;
            } else {
               var2 = false;
            }

            if (var2) {
               Algebraic$Expr$ConstantDouble var4 = (Algebraic$Expr$ConstantDouble)x$1;
               if (this.value() == var4.value() && var4.canEqual(this)) {
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

   public Algebraic$Expr$ConstantDouble(final double value) {
      this.value = value;
      Product.$init$(this);
   }
}
