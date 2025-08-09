package spire.math;

import java.math.BigDecimal;
import java.math.RoundingMode;
import scala.Product;
import scala.Predef.;
import scala.collection.Iterator;
import scala.runtime.BoxesRunTime;
import scala.runtime.RichLong;
import scala.runtime.Statics;

public class Algebraic$Expr$ConstantLong extends Algebraic$Expr$Constant implements Product {
   private static final long serialVersionUID = 0L;
   private final long value;

   public Iterator productElementNames() {
      return Product.productElementNames$(this);
   }

   public long value() {
      return this.value;
   }

   public int flagBits() {
      return Algebraic$Expr$Flags$.MODULE$.IntegerLeaf();
   }

   public long upperBound() {
      return this.value() == 0L ? 0L : (this.value() == Long.MIN_VALUE ? 64L : (long)(64 - Long.numberOfLeadingZeros(package$.MODULE$.abs(this.value()) - 1L)));
   }

   public int signum() {
      return (int)BoxesRunTime.unboxToLong((new RichLong(.MODULE$.longWrapper(this.value()))).sign());
   }

   public BigDecimal toBigDecimal(final int digits) {
      return (new BigDecimal(this.value())).setScale(digits, RoundingMode.HALF_UP);
   }

   public Algebraic$Expr$ConstantLong copy(final long value) {
      return new Algebraic$Expr$ConstantLong(value);
   }

   public long copy$default$1() {
      return this.value();
   }

   public String productPrefix() {
      return "ConstantLong";
   }

   public int productArity() {
      return 1;
   }

   public Object productElement(final int x$1) {
      Object var10000;
      switch (x$1) {
         case 0:
            var10000 = BoxesRunTime.boxToLong(this.value());
            break;
         default:
            var10000 = Statics.ioobe(x$1);
      }

      return var10000;
   }

   public Iterator productIterator() {
      return scala.runtime.ScalaRunTime..MODULE$.typedProductIterator(this);
   }

   public boolean canEqual(final Object x$1) {
      return x$1 instanceof Algebraic$Expr$ConstantLong;
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
      var1 = Statics.mix(var1, Statics.longHash(this.value()));
      return Statics.finalizeHash(var1, 1);
   }

   public String toString() {
      return scala.runtime.ScalaRunTime..MODULE$._toString(this);
   }

   public boolean equals(final Object x$1) {
      boolean var10000;
      if (this != x$1) {
         label49: {
            boolean var2;
            if (x$1 instanceof Algebraic$Expr$ConstantLong) {
               var2 = true;
            } else {
               var2 = false;
            }

            if (var2) {
               Algebraic$Expr$ConstantLong var4 = (Algebraic$Expr$ConstantLong)x$1;
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

   public Algebraic$Expr$ConstantLong(final long value) {
      this.value = value;
      Product.$init$(this);
   }
}
