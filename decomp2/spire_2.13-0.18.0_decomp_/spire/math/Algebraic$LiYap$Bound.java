package spire.math;

import java.io.Serializable;
import scala.Product;
import scala.collection.Iterator;
import scala.runtime.BoxesRunTime;
import scala.runtime.Statics;
import scala.runtime.ScalaRunTime.;
import spire.macros.ArithmeticOverflowException;

public final class Algebraic$LiYap$Bound implements Product, Serializable {
   private final long lc;
   private final long tc;
   private final long measure;
   private final long lb;
   private final long ub;

   public Iterator productElementNames() {
      return Product.productElementNames$(this);
   }

   public long lc() {
      return this.lc;
   }

   public long tc() {
      return this.tc;
   }

   public long measure() {
      return this.measure;
   }

   public long lb() {
      return this.lb;
   }

   public long ub() {
      return this.ub;
   }

   public long getBitBound(final long degreeBound) {
      return this.checked$attempt$macro$1$6(degreeBound);
   }

   public Algebraic$LiYap$Bound copy(final long lc, final long tc, final long measure, final long lb, final long ub) {
      return new Algebraic$LiYap$Bound(lc, tc, measure, lb, ub);
   }

   public long copy$default$1() {
      return this.lc();
   }

   public long copy$default$2() {
      return this.tc();
   }

   public long copy$default$3() {
      return this.measure();
   }

   public long copy$default$4() {
      return this.lb();
   }

   public long copy$default$5() {
      return this.ub();
   }

   public String productPrefix() {
      return "Bound";
   }

   public int productArity() {
      return 5;
   }

   public Object productElement(final int x$1) {
      Object var10000;
      switch (x$1) {
         case 0:
            var10000 = BoxesRunTime.boxToLong(this.lc());
            break;
         case 1:
            var10000 = BoxesRunTime.boxToLong(this.tc());
            break;
         case 2:
            var10000 = BoxesRunTime.boxToLong(this.measure());
            break;
         case 3:
            var10000 = BoxesRunTime.boxToLong(this.lb());
            break;
         case 4:
            var10000 = BoxesRunTime.boxToLong(this.ub());
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
      return x$1 instanceof Algebraic$LiYap$Bound;
   }

   public String productElementName(final int x$1) {
      String var10000;
      switch (x$1) {
         case 0:
            var10000 = "lc";
            break;
         case 1:
            var10000 = "tc";
            break;
         case 2:
            var10000 = "measure";
            break;
         case 3:
            var10000 = "lb";
            break;
         case 4:
            var10000 = "ub";
            break;
         default:
            var10000 = (String)Statics.ioobe(x$1);
      }

      return var10000;
   }

   public int hashCode() {
      int var1 = -889275714;
      var1 = Statics.mix(var1, this.productPrefix().hashCode());
      var1 = Statics.mix(var1, Statics.longHash(this.lc()));
      var1 = Statics.mix(var1, Statics.longHash(this.tc()));
      var1 = Statics.mix(var1, Statics.longHash(this.measure()));
      var1 = Statics.mix(var1, Statics.longHash(this.lb()));
      var1 = Statics.mix(var1, Statics.longHash(this.ub()));
      return Statics.finalizeHash(var1, 5);
   }

   public String toString() {
      return .MODULE$._toString(this);
   }

   public boolean equals(final Object x$1) {
      boolean var10000;
      if (this != x$1) {
         label55: {
            boolean var2;
            if (x$1 instanceof Algebraic$LiYap$Bound) {
               var2 = true;
            } else {
               var2 = false;
            }

            if (var2) {
               Algebraic$LiYap$Bound var4 = (Algebraic$LiYap$Bound)x$1;
               if (this.lc() == var4.lc() && this.tc() == var4.tc() && this.measure() == var4.measure() && this.lb() == var4.lb() && this.ub() == var4.ub()) {
                  break label55;
               }
            }

            var10000 = false;
            return var10000;
         }
      }

      var10000 = true;
      return var10000;
   }

   private static final long checked$fallback$macro$2$6() {
      throw new ArithmeticOverflowException();
   }

   private final long checked$attempt$macro$1$6(final long degreeBound$1) {
      long x$macro$7 = this.ub();
      long z$macro$9 = degreeBound$1 - 1L;
      if (((degreeBound$1 ^ 1L) & (degreeBound$1 ^ z$macro$9)) < 0L) {
         return checked$fallback$macro$2$6();
      } else {
         long z$macro$6 = x$macro$7 * z$macro$9;
         if (x$macro$7 == 0L || z$macro$9 == z$macro$6 / x$macro$7 && (x$macro$7 != -1L || z$macro$9 != Long.MIN_VALUE)) {
            long y$macro$5 = this.lc();
            long z$macro$3 = z$macro$6 + y$macro$5;
            return (~(z$macro$6 ^ y$macro$5) & (z$macro$6 ^ z$macro$3)) < 0L ? checked$fallback$macro$2$6() : z$macro$3;
         } else {
            return checked$fallback$macro$2$6();
         }
      }
   }

   public Algebraic$LiYap$Bound(final long lc, final long tc, final long measure, final long lb, final long ub) {
      this.lc = lc;
      this.tc = tc;
      this.measure = measure;
      this.lb = lb;
      this.ub = ub;
      Product.$init$(this);
   }
}
