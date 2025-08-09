package spire.math;

import java.lang.invoke.SerializedLambda;
import scala.MatchError;
import scala.Tuple2;
import scala.math.BigInt;
import scala.math.BigInt.;
import spire.algebra.FieldAssociativeAlgebra$mcD$sp;
import spire.math.poly.PolyDense;
import spire.math.poly.PolyDense$;
import spire.math.poly.PolySparse;
import spire.math.poly.PolySparse$;

public interface PolynomialOverField$mcD$sp extends PolynomialOverField, FieldAssociativeAlgebra$mcD$sp, PolynomialOverRing$mcD$sp {
   // $FF: synthetic method
   static Polynomial divr$(final PolynomialOverField$mcD$sp $this, final Polynomial x, final double k) {
      return $this.divr(x, k);
   }

   default Polynomial divr(final Polynomial x, final double k) {
      return this.divr$mcD$sp(x, k);
   }

   // $FF: synthetic method
   static Polynomial divr$mcD$sp$(final PolynomialOverField$mcD$sp $this, final Polynomial x, final double k) {
      return $this.divr$mcD$sp(x, k);
   }

   default Polynomial divr$mcD$sp(final Polynomial x, final double k) {
      return x.$colon$div$mcD$sp(k, this.scalar$mcD$sp(), this.eq$mcD$sp());
   }

   // $FF: synthetic method
   static BigInt euclideanFunction$(final PolynomialOverField$mcD$sp $this, final Polynomial x) {
      return $this.euclideanFunction(x);
   }

   default BigInt euclideanFunction(final Polynomial x) {
      return this.euclideanFunction$mcD$sp(x);
   }

   // $FF: synthetic method
   static BigInt euclideanFunction$mcD$sp$(final PolynomialOverField$mcD$sp $this, final Polynomial x) {
      return $this.euclideanFunction$mcD$sp(x);
   }

   default BigInt euclideanFunction$mcD$sp(final Polynomial x) {
      return .MODULE$.int2bigInt(x.degree());
   }

   // $FF: synthetic method
   static Polynomial equot$(final PolynomialOverField$mcD$sp $this, final Polynomial x, final Polynomial y) {
      return $this.equot(x, y);
   }

   default Polynomial equot(final Polynomial x, final Polynomial y) {
      return this.equot$mcD$sp(x, y);
   }

   // $FF: synthetic method
   static Polynomial equot$mcD$sp$(final PolynomialOverField$mcD$sp $this, final Polynomial x, final Polynomial y) {
      return $this.equot$mcD$sp(x, y);
   }

   default Polynomial equot$mcD$sp(final Polynomial x, final Polynomial y) {
      return (Polynomial)this.equotmod$mcD$sp(x, y)._1();
   }

   // $FF: synthetic method
   static Polynomial emod$(final PolynomialOverField$mcD$sp $this, final Polynomial x, final Polynomial y) {
      return $this.emod(x, y);
   }

   default Polynomial emod(final Polynomial x, final Polynomial y) {
      return this.emod$mcD$sp(x, y);
   }

   // $FF: synthetic method
   static Polynomial emod$mcD$sp$(final PolynomialOverField$mcD$sp $this, final Polynomial x, final Polynomial y) {
      return $this.emod$mcD$sp(x, y);
   }

   default Polynomial emod$mcD$sp(final Polynomial x, final Polynomial y) {
      return (Polynomial)this.equotmod$mcD$sp(x, y)._2();
   }

   // $FF: synthetic method
   static Tuple2 equotmod$(final PolynomialOverField$mcD$sp $this, final Polynomial x, final Polynomial y) {
      return $this.equotmod(x, y);
   }

   default Tuple2 equotmod(final Polynomial x, final Polynomial y) {
      return this.equotmod$mcD$sp(x, y);
   }

   // $FF: synthetic method
   static Tuple2 equotmod$mcD$sp$(final PolynomialOverField$mcD$sp $this, final Polynomial x, final Polynomial y) {
      return $this.equotmod$mcD$sp(x, y);
   }

   default Tuple2 equotmod$mcD$sp(final Polynomial x, final Polynomial y) {
      scala.Predef..MODULE$.require(!y.isZero(), () -> "Can't divide by polynomial of zero!");
      Tuple2 var3;
      if (x instanceof PolyDense) {
         PolyDense var6 = (PolyDense)x;
         var3 = PolyDense$.MODULE$.quotmodDense$mDc$sp(var6, y, this.scalar$mcD$sp(), this.eq$mcD$sp(), this.ct());
      } else {
         if (!(x instanceof PolySparse)) {
            throw new MatchError(x);
         }

         PolySparse var7 = (PolySparse)x;
         PolySparse ys;
         if (y instanceof PolyDense) {
            PolyDense var10 = (PolyDense)y;
            ys = PolySparse$.MODULE$.dense2sparse$mDc$sp(var10, this.scalar$mcD$sp(), this.eq$mcD$sp(), this.ct());
         } else {
            if (!(y instanceof PolySparse)) {
               throw new MatchError(y);
            }

            PolySparse var11 = (PolySparse)y;
            ys = var11;
         }

         var3 = PolySparse$.MODULE$.quotmodSparse$mDc$sp(var7, ys, this.scalar$mcD$sp(), this.eq$mcD$sp(), this.ct());
      }

      return var3;
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return var0.lambdaDeserialize<invokedynamic>(var0);
   }
}
