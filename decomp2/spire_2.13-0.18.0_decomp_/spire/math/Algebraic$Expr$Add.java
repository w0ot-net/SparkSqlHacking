package spire.math;

import scala.Product;
import scala.collection.Iterator;
import scala.runtime.Statics;
import scala.runtime.ScalaRunTime.;

public class Algebraic$Expr$Add extends Algebraic$Expr$AddOrSubExpr implements Product {
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

   public Algebraic$Expr$Add copy(final Algebraic.Expr lhs, final Algebraic.Expr rhs) {
      return new Algebraic$Expr$Add(lhs, rhs);
   }

   public Algebraic.Expr copy$default$1() {
      return this.lhs();
   }

   public Algebraic.Expr copy$default$2() {
      return this.rhs();
   }

   public String productPrefix() {
      return "Add";
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
      return x$1 instanceof Algebraic$Expr$Add;
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
            if (x$1 instanceof Algebraic$Expr$Add) {
               var2 = true;
            } else {
               var2 = false;
            }

            if (var2) {
               label45: {
                  label54: {
                     Algebraic$Expr$Add var4 = (Algebraic$Expr$Add)x$1;
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

   public Algebraic$Expr$Add(final Algebraic.Expr lhs, final Algebraic.Expr rhs) {
      this.lhs = lhs;
      this.rhs = rhs;
      Product.$init$(this);
   }
}
