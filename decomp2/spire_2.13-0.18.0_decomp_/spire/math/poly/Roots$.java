package spire.math.poly;

import cats.kernel.Eq;
import java.lang.invoke.SerializedLambda;
import scala.MatchError;
import scala.collection.IterableOnce;
import scala.collection.ArrayOps.;
import scala.collection.immutable.List;
import scala.collection.immutable.Vector;
import scala.math.BigDecimal;
import scala.math.BigInt;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;
import scala.runtime.DoubleRef;
import spire.math.Polynomial;
import spire.math.Polynomial$;
import spire.math.Rational;
import spire.math.Rational$;
import spire.math.SafeLong$;
import spire.math.package$;
import spire.std.package;

public final class Roots$ {
   public static final Roots$ MODULE$ = new Roots$();

   public final Vector isolateRoots(final Polynomial poly, final RootIsolator isolator) {
      return isolator.isolateRoots(poly);
   }

   public final Polynomial removeFractions(final Polynomial poly) {
      Rational[] coeffs = (Rational[])poly.coeffsArray(Rational$.MODULE$.RationalAlgebra());
      BigInt factors = (BigInt).MODULE$.foldLeft$extension(scala.Predef..MODULE$.refArrayOps((Object[])coeffs), scala.package..MODULE$.BigInt().apply(1), (acc, coeff) -> {
         BigInt d = coeff.denominator().toBigInt();
         return acc.$times(d.$div(acc.gcd(d)));
      });
      BigInt[] zCoeffs = (BigInt[]).MODULE$.map$extension(scala.Predef..MODULE$.refArrayOps((Object[])coeffs), (n) -> n.numerator().$times(SafeLong$.MODULE$.apply(factors).$div(n.denominator())).toBigInt(), scala.reflect.ClassTag..MODULE$.apply(BigInt.class));
      return Polynomial$.MODULE$.dense(zCoeffs, package.bigInt$.MODULE$.BigIntAlgebra(), (Eq)package.bigInt$.MODULE$.BigIntAlgebra(), scala.reflect.ClassTag..MODULE$.apply(BigInt.class));
   }

   public final Polynomial removeDecimal(final Polynomial poly) {
      Object var10000;
      label23: {
         Polynomial var2 = Polynomial$.MODULE$.zero((Eq)package.bigDecimal$.MODULE$.BigDecimalAlgebra(), package.bigDecimal$.MODULE$.BigDecimalAlgebra(), scala.reflect.ClassTag..MODULE$.apply(BigDecimal.class));
         if (poly == null) {
            if (var2 == null) {
               break label23;
            }
         } else if (poly.equals(var2)) {
            break label23;
         }

         List terms = poly.terms(package.bigDecimal$.MODULE$.BigDecimalAlgebra(), (Eq)package.bigDecimal$.MODULE$.BigDecimalAlgebra()).map((x0$1) -> {
            if (x0$1 != null) {
               BigDecimal c = (BigDecimal)x0$1.coeff();
               int e = x0$1.exp();
               Term var1 = new Term(c.bigDecimal().stripTrailingZeros(), e);
               return var1;
            } else {
               throw new MatchError(x0$1);
            }
         });
         int maxScale = BoxesRunTime.unboxToInt(terms.map((x$1) -> BoxesRunTime.boxToInteger($anonfun$removeDecimal$2(x$1))).max(scala.math.Ordering.Int..MODULE$));
         var10000 = Polynomial$.MODULE$.apply((IterableOnce)terms.map((x0$2) -> {
            if (x0$2 != null) {
               java.math.BigDecimal c = (java.math.BigDecimal)x0$2.coeff();
               int e = x0$2.exp();
               BigInt c0 = scala.package..MODULE$.BigInt().apply(c.movePointRight(maxScale).unscaledValue());
               Term var2 = new Term(c0, e);
               return var2;
            } else {
               throw new MatchError(x0$2);
            }
         }), package.bigInt$.MODULE$.BigIntAlgebra(), (Eq)package.bigInt$.MODULE$.BigIntAlgebra(), scala.reflect.ClassTag..MODULE$.apply(BigInt.class));
         return (Polynomial)var10000;
      }

      var10000 = Polynomial$.MODULE$.zero((Eq)package.bigInt$.MODULE$.BigIntAlgebra(), package.bigInt$.MODULE$.BigIntAlgebra(), scala.reflect.ClassTag..MODULE$.apply(BigInt.class));
      return (Polynomial)var10000;
   }

   public final int upperBound(final Polynomial p) {
      int lgLastCoeff = ((BigInt)p.maxOrderTermCoeff(package.bigInt$.MODULE$.BigIntAlgebra())).abs().bitLength();
      int n = p.degree();
      DoubleRef maxBound = DoubleRef.create(Double.NEGATIVE_INFINITY);
      p.foreachNonZero((k, coeff) -> {
         $anonfun$upperBound$1(n, lgLastCoeff, maxBound, BoxesRunTime.unboxToInt(k), coeff);
         return BoxedUnit.UNIT;
      }, package.bigInt$.MODULE$.BigIntAlgebra(), (Eq)package.bigInt$.MODULE$.BigIntAlgebra());
      if (scala.runtime.RichDouble..MODULE$.isValidInt$extension(scala.Predef..MODULE$.doubleWrapper(maxBound.elem))) {
         return (int)maxBound.elem;
      } else {
         throw new ArithmeticException("bound too large");
      }
   }

   public int lowerBound(final Polynomial p) {
      return -this.upperBound(p.reciprocal(package.bigInt$.MODULE$.BigIntAlgebra(), (Eq)package.bigInt$.MODULE$.BigIntAlgebra()));
   }

   // $FF: synthetic method
   public static final int $anonfun$removeDecimal$2(final Term x$1) {
      return ((java.math.BigDecimal)x$1.coeff()).scale();
   }

   // $FF: synthetic method
   public static final void $anonfun$upperBound$1(final int n$1, final int lgLastCoeff$1, final DoubleRef maxBound$1, final int k, final BigInt coeff) {
      if (k != n$1) {
         int i = n$1 - k;
         int bound = (coeff.abs().bitLength() - lgLastCoeff$1 + 1) / i + 2;
         maxBound$1.elem = package$.MODULE$.max(maxBound$1.elem, (double)bound);
      }

   }

   private Roots$() {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
