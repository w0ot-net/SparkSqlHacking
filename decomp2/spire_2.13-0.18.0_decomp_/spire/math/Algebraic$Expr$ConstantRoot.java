package spire.math;

import cats.kernel.Eq;
import java.lang.invoke.SerializedLambda;
import java.math.BigDecimal;
import java.math.MathContext;
import java.util.concurrent.atomic.AtomicReference;
import scala.Product;
import scala.collection.Iterator;
import scala.math.BigInt;
import scala.runtime.BoxesRunTime;
import scala.runtime.Statics;
import scala.runtime.ScalaRunTime.;
import spire.math.poly.BigDecimalRootRefinement;
import spire.math.poly.BigDecimalRootRefinement$;

public class Algebraic$Expr$ConstantRoot extends Algebraic$Expr$Constant implements Product {
   private static final long serialVersionUID = 0L;
   private final Polynomial poly;
   private final int i;
   private final Rational lb;
   private final Rational ub;
   private final AtomicReference refinement;

   public Iterator productElementNames() {
      return Product.productElementNames$(this);
   }

   public Polynomial poly() {
      return this.poly;
   }

   public int i() {
      return this.i;
   }

   public Rational lb() {
      return this.lb;
   }

   public Rational ub() {
      return this.ub;
   }

   public Polynomial value() {
      return this.poly();
   }

   public int flagBits() {
      return Algebraic$Expr$Flags$.MODULE$.IsRadical();
   }

   public long upperBound() {
      return this.ub().signum() > 0 ? (long)(this.ub().numerator().bitLength() - this.ub().denominator().bitLength() + 1) : (long)(this.lb().numerator().abs().bitLength() - this.lb().denominator().bitLength() + 1);
   }

   public int signum() {
      return this.lb().signum() != 0 ? this.lb().signum() : this.ub().signum();
   }

   private AtomicReference refinement() {
      return this.refinement;
   }

   public BigDecimal toBigDecimal(final int digits) {
      BigDecimalRootRefinement oldRefinement = (BigDecimalRootRefinement)this.refinement().get();
      BigDecimalRootRefinement newRefinement = oldRefinement.refine(digits);
      this.refinement().set(newRefinement);
      return newRefinement.approximateValue();
   }

   public BigInt lead() {
      return (BigInt)this.poly().maxTerm(spire.std.package.bigInt$.MODULE$.BigIntAlgebra()).coeff();
   }

   public BigInt tail() {
      return (BigInt)this.poly().minTerm(spire.std.package.bigInt$.MODULE$.BigIntAlgebra(), (Eq)spire.std.package.bigInt$.MODULE$.BigIntAlgebra()).coeff();
   }

   public Algebraic$Expr$ConstantRoot copy(final Polynomial poly, final int i, final Rational lb, final Rational ub) {
      return new Algebraic$Expr$ConstantRoot(poly, i, lb, ub);
   }

   public Polynomial copy$default$1() {
      return this.poly();
   }

   public int copy$default$2() {
      return this.i();
   }

   public Rational copy$default$3() {
      return this.lb();
   }

   public Rational copy$default$4() {
      return this.ub();
   }

   public String productPrefix() {
      return "ConstantRoot";
   }

   public int productArity() {
      return 4;
   }

   public Object productElement(final int x$1) {
      Object var10000;
      switch (x$1) {
         case 0:
            var10000 = this.poly();
            break;
         case 1:
            var10000 = BoxesRunTime.boxToInteger(this.i());
            break;
         case 2:
            var10000 = this.lb();
            break;
         case 3:
            var10000 = this.ub();
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
      return x$1 instanceof Algebraic$Expr$ConstantRoot;
   }

   public String productElementName(final int x$1) {
      String var10000;
      switch (x$1) {
         case 0:
            var10000 = "poly";
            break;
         case 1:
            var10000 = "i";
            break;
         case 2:
            var10000 = "lb";
            break;
         case 3:
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
      var1 = Statics.mix(var1, Statics.anyHash(this.poly()));
      var1 = Statics.mix(var1, this.i());
      var1 = Statics.mix(var1, Statics.anyHash(this.lb()));
      var1 = Statics.mix(var1, Statics.anyHash(this.ub()));
      return Statics.finalizeHash(var1, 4);
   }

   public String toString() {
      return .MODULE$._toString(this);
   }

   public boolean equals(final Object x$1) {
      boolean var7;
      if (this != x$1) {
         label59: {
            boolean var2;
            if (x$1 instanceof Algebraic$Expr$ConstantRoot) {
               var2 = true;
            } else {
               var2 = false;
            }

            if (var2) {
               label42: {
                  Algebraic$Expr$ConstantRoot var4 = (Algebraic$Expr$ConstantRoot)x$1;
                  if (this.i() == var4.i()) {
                     label40: {
                        Polynomial var10000 = this.poly();
                        Polynomial var5 = var4.poly();
                        if (var10000 == null) {
                           if (var5 != null) {
                              break label40;
                           }
                        } else if (!var10000.equals(var5)) {
                           break label40;
                        }

                        if (BoxesRunTime.equalsNumNum(this.lb(), var4.lb()) && BoxesRunTime.equalsNumNum(this.ub(), var4.ub()) && var4.canEqual(this)) {
                           var7 = true;
                           break label42;
                        }
                     }
                  }

                  var7 = false;
               }

               if (var7) {
                  break label59;
               }
            }

            var7 = false;
            return var7;
         }
      }

      var7 = true;
      return var7;
   }

   public Algebraic$Expr$ConstantRoot(final Polynomial poly, final int i, final Rational lb, final Rational ub) {
      this.poly = poly;
      this.i = i;
      this.lb = lb;
      this.ub = ub;
      Product.$init$(this);
      Polynomial poly0 = poly.map((n) -> new scala.math.BigDecimal(new BigDecimal(n.bigInteger()), MathContext.UNLIMITED), spire.std.package.bigDecimal$.MODULE$.BigDecimalAlgebra(), (Eq)spire.std.package.bigDecimal$.MODULE$.BigDecimalAlgebra(), scala.reflect.ClassTag..MODULE$.apply(scala.math.BigDecimal.class));
      this.refinement = new AtomicReference(BigDecimalRootRefinement$.MODULE$.apply(poly0, lb, ub));
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return var0.lambdaDeserialize<invokedynamic>(var0);
   }
}
