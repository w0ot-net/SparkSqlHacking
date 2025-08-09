package spire.math;

import java.io.Serializable;
import scala.Product;
import scala.collection.Iterator;
import scala.runtime.BoxesRunTime;
import scala.runtime.Statics;
import scala.runtime.ScalaRunTime.;
import spire.macros.ArithmeticOverflowException;

public final class Algebraic$BFMSS$Bound implements Product, Serializable {
   private final long l;
   private final long u;

   public Iterator productElementNames() {
      return Product.productElementNames$(this);
   }

   public long l() {
      return this.l;
   }

   public long u() {
      return this.u;
   }

   public long getBitBound(final long degreeBound) {
      return this.checked$attempt$macro$1$8(degreeBound);
   }

   public Algebraic$BFMSS$Bound copy(final long l, final long u) {
      return new Algebraic$BFMSS$Bound(l, u);
   }

   public long copy$default$1() {
      return this.l();
   }

   public long copy$default$2() {
      return this.u();
   }

   public String productPrefix() {
      return "Bound";
   }

   public int productArity() {
      return 2;
   }

   public Object productElement(final int x$1) {
      Object var10000;
      switch (x$1) {
         case 0:
            var10000 = BoxesRunTime.boxToLong(this.l());
            break;
         case 1:
            var10000 = BoxesRunTime.boxToLong(this.u());
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
      return x$1 instanceof Algebraic$BFMSS$Bound;
   }

   public String productElementName(final int x$1) {
      String var10000;
      switch (x$1) {
         case 0:
            var10000 = "l";
            break;
         case 1:
            var10000 = "u";
            break;
         default:
            var10000 = (String)Statics.ioobe(x$1);
      }

      return var10000;
   }

   public int hashCode() {
      int var1 = -889275714;
      var1 = Statics.mix(var1, this.productPrefix().hashCode());
      var1 = Statics.mix(var1, Statics.longHash(this.l()));
      var1 = Statics.mix(var1, Statics.longHash(this.u()));
      return Statics.finalizeHash(var1, 2);
   }

   public String toString() {
      return .MODULE$._toString(this);
   }

   public boolean equals(final Object x$1) {
      boolean var10000;
      if (this != x$1) {
         label49: {
            boolean var2;
            if (x$1 instanceof Algebraic$BFMSS$Bound) {
               var2 = true;
            } else {
               var2 = false;
            }

            if (var2) {
               Algebraic$BFMSS$Bound var4 = (Algebraic$BFMSS$Bound)x$1;
               if (this.l() == var4.l() && this.u() == var4.u()) {
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

   private static final long checked$fallback$macro$2$8() {
      throw new ArithmeticOverflowException();
   }

   private final long checked$attempt$macro$1$8(final long degreeBound$2) {
      long x$macro$4 = this.l();
      long x$macro$7 = this.u();
      long z$macro$9 = degreeBound$2 - 1L;
      if (((degreeBound$2 ^ 1L) & (degreeBound$2 ^ z$macro$9)) < 0L) {
         return checked$fallback$macro$2$8();
      } else {
         long z$macro$6 = x$macro$7 * z$macro$9;
         if (x$macro$7 == 0L || z$macro$9 == z$macro$6 / x$macro$7 && (x$macro$7 != -1L || z$macro$9 != Long.MIN_VALUE)) {
            long z$macro$3 = x$macro$4 + z$macro$6;
            return (~(x$macro$4 ^ z$macro$6) & (x$macro$4 ^ z$macro$3)) < 0L ? checked$fallback$macro$2$8() : z$macro$3;
         } else {
            return checked$fallback$macro$2$8();
         }
      }
   }

   public Algebraic$BFMSS$Bound(final long l, final long u) {
      this.l = l;
      this.u = u;
      Product.$init$(this);
   }
}
