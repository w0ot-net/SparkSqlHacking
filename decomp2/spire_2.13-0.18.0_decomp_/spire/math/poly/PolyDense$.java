package spire.math.poly;

import algebra.ring.Field;
import algebra.ring.Semiring;
import cats.kernel.Eq;
import java.lang.invoke.SerializedLambda;
import scala.Predef;
import scala.Tuple2;
import scala.collection.ArrayOps;
import scala.reflect.ClassTag;
import scala.runtime.BoxesRunTime;
import scala.runtime.ScalaRunTime.;
import scala.runtime.java8.JFunction1;
import spire.algebra.package$;
import spire.math.Polynomial;
import spire.math.Polynomial$;
import spire.std.package;

public final class PolyDense$ {
   public static final PolyDense$ MODULE$ = new PolyDense$();

   public final Polynomial spire$math$poly$PolyDense$$plusDense(final Polynomial lhs, final Polynomial rhs, final Semiring evidence$1, final Eq evidence$2, final ClassTag evidence$3) {
      while(true) {
         Object lcoeffs = lhs.coeffsArray(evidence$1);
         Object rcoeffs = rhs.coeffsArray(evidence$1);
         if (.MODULE$.array_length(lcoeffs) >= .MODULE$.array_length(rcoeffs)) {
            Object cs = evidence$3.newArray(.MODULE$.array_length(lcoeffs));

            for(int index$macro$1 = 0; index$macro$1 < .MODULE$.array_length(rcoeffs); ++index$macro$1) {
               .MODULE$.array_update(cs, index$macro$1, evidence$1.plus(.MODULE$.array_apply(lcoeffs, index$macro$1), .MODULE$.array_apply(rcoeffs, index$macro$1)));
            }

            for(int index$macro$2 = .MODULE$.array_length(rcoeffs); index$macro$2 < .MODULE$.array_length(lcoeffs); ++index$macro$2) {
               .MODULE$.array_update(cs, index$macro$2, .MODULE$.array_apply(lcoeffs, index$macro$2));
            }

            return Polynomial$.MODULE$.dense(cs, evidence$1, evidence$2, evidence$3);
         }

         Polynomial var10000 = rhs;
         evidence$3 = evidence$3;
         evidence$2 = evidence$2;
         evidence$1 = evidence$1;
         rhs = lhs;
         lhs = var10000;
      }
   }

   public final Tuple2 quotmodDense(final PolyDense lhs, final Polynomial rhs, final Field evidence$4, final Eq evidence$5, final ClassTag evidence$6) {
      Object cs = rhs.coeffsArray(evidence$4);
      if (.MODULE$.array_length(cs) == 0) {
         throw new ArithmeticException("/ by zero polynomial");
      } else {
         Tuple2 var10000;
         if (.MODULE$.array_length(cs) == 1) {
            Object c = .MODULE$.array_apply(cs, 0);
            PolyDense q = Polynomial$.MODULE$.dense(scala.collection.ArrayOps..MODULE$.map$extension(scala.Predef..MODULE$.genericArrayOps(lhs.coeffs()), (x$29) -> evidence$4.div(x$29, c), evidence$6), evidence$4, evidence$5, evidence$6);
            PolyDense r = Polynomial$.MODULE$.dense(evidence$6.newArray(0), evidence$4, evidence$5, evidence$6);
            var10000 = new Tuple2(q, r);
         } else {
            var10000 = this.eval$1(evidence$6.newArray(0), scala.collection.ArrayOps..MODULE$.reverse$extension(scala.Predef..MODULE$.genericArrayOps(lhs.coeffs())), lhs.degree() - rhs.degree(), rhs, evidence$4, evidence$6, evidence$5);
         }

         return var10000;
      }
   }

   public final Tuple2 quotmodDense$mDc$sp(final PolyDense lhs, final Polynomial rhs, final Field evidence$4, final Eq evidence$5, final ClassTag evidence$6) {
      double[] cs = rhs.coeffsArray$mcD$sp(evidence$4);
      if (cs.length == 0) {
         throw new ArithmeticException("/ by zero polynomial");
      } else {
         Tuple2 var10000;
         if (cs.length == 1) {
            double c = cs[0];
            PolyDense q = Polynomial$.MODULE$.dense$mDc$sp((double[])scala.collection.ArrayOps..MODULE$.map$extension(scala.Predef..MODULE$.genericArrayOps(lhs.coeffs$mcD$sp()), (JFunction1.mcDD.sp)(x$29) -> evidence$4.div$mcD$sp(x$29, c), evidence$6), evidence$4, evidence$5, evidence$6);
            PolyDense r = Polynomial$.MODULE$.dense$mDc$sp((double[])evidence$6.newArray(0), evidence$4, evidence$5, evidence$6);
            var10000 = new Tuple2(q, r);
         } else {
            var10000 = this.eval$2((double[])evidence$6.newArray(0), (double[])scala.collection.ArrayOps..MODULE$.reverse$extension(scala.Predef..MODULE$.genericArrayOps(lhs.coeffs$mcD$sp())), lhs.degree() - rhs.degree(), rhs, evidence$4, evidence$6, evidence$5);
         }

         return var10000;
      }
   }

   private static final Object zipSum$1(final Object lcs, final Object rcs, final Field evidence$4$1, final ClassTag evidence$6$1) {
      return scala.collection.ArrayOps..MODULE$.tail$extension(scala.Predef..MODULE$.genericArrayOps(package.array$.MODULE$.ArrayInnerProductSpace(evidence$4$1, evidence$6$1).plus(lcs, rcs)));
   }

   private static final Polynomial polyFromCoeffsLE$1(final Object cs, final Field evidence$4$1, final Eq evidence$5$1, final ClassTag evidence$6$1) {
      return Polynomial$.MODULE$.dense(cs, evidence$4$1, evidence$5$1, evidence$6$1);
   }

   // $FF: synthetic method
   public static final boolean $anonfun$quotmodDense$1(final Eq evidence$5$1, final Field evidence$4$1, final Object x$27) {
      return evidence$5$1.eqv(x$27, package$.MODULE$.Field().apply(evidence$4$1).zero());
   }

   private static final Polynomial polyFromCoeffsBE$1(final Object cs, final Eq evidence$5$1, final Field evidence$4$1, final ClassTag evidence$6$1) {
      Object ncs = scala.collection.ArrayOps..MODULE$.dropWhile$extension(scala.Predef..MODULE$.genericArrayOps(cs), (x$27) -> BoxesRunTime.boxToBoolean($anonfun$quotmodDense$1(evidence$5$1, evidence$4$1, x$27)));
      return Polynomial$.MODULE$.dense(scala.collection.ArrayOps..MODULE$.reverse$extension(scala.Predef..MODULE$.genericArrayOps(ncs)), evidence$4$1, evidence$5$1, evidence$6$1);
   }

   private final Tuple2 eval$1(final Object q, final Object u, final int n, final Polynomial rhs$1, final Field evidence$4$1, final ClassTag evidence$6$1, final Eq evidence$5$1) {
      while(!scala.collection.ArrayOps..MODULE$.isEmpty$extension(scala.Predef..MODULE$.genericArrayOps(u)) && n >= 0) {
         Object v0 = rhs$1.isZero() ? package$.MODULE$.Field().apply(evidence$4$1).zero() : rhs$1.maxOrderTermCoeff(evidence$4$1);
         Object q0 = evidence$4$1.div(.MODULE$.array_apply(u, 0), v0);
         Object uprime = zipSum$1(u, scala.collection.ArrayOps..MODULE$.map$extension(scala.Predef..MODULE$.genericArrayOps(scala.collection.ArrayOps..MODULE$.reverse$extension(scala.Predef..MODULE$.genericArrayOps(rhs$1.coeffsArray(evidence$4$1)))), (x$28) -> evidence$4$1.times(x$28, evidence$4$1.negate(q0)), evidence$6$1), evidence$4$1, evidence$6$1);
         ArrayOps var10000 = scala.collection.ArrayOps..MODULE$;
         Predef var10001 = scala.Predef..MODULE$;
         Object var12 = evidence$6$1.newArray(1);
         .MODULE$.array_update(var12, 0, q0);
         Object var13 = var10000.$plus$plus$extension(var10001.genericArrayOps(var12), q, evidence$6$1);
         --n;
         u = uprime;
         q = var13;
      }

      return new Tuple2(polyFromCoeffsLE$1(q, evidence$4$1, evidence$5$1, evidence$6$1), polyFromCoeffsBE$1(u, evidence$5$1, evidence$4$1, evidence$6$1));
   }

   private static final double[] zipSum$2(final double[] lcs, final double[] rcs, final Field evidence$4$2, final ClassTag evidence$6$2) {
      return (double[])scala.collection.ArrayOps..MODULE$.tail$extension(scala.Predef..MODULE$.genericArrayOps((double[])package.array$.MODULE$.ArrayInnerProductSpace$mDc$sp(evidence$4$2, evidence$6$2).plus(lcs, rcs)));
   }

   private static final Polynomial polyFromCoeffsLE$2(final double[] cs, final Field evidence$4$2, final Eq evidence$5$2, final ClassTag evidence$6$2) {
      return Polynomial$.MODULE$.dense$mDc$sp(cs, evidence$4$2, evidence$5$2, evidence$6$2);
   }

   private static final Polynomial polyFromCoeffsBE$2(final double[] cs, final Eq evidence$5$2, final Field evidence$4$2, final ClassTag evidence$6$2) {
      double[] ncs = (double[])scala.collection.ArrayOps..MODULE$.dropWhile$extension(scala.Predef..MODULE$.genericArrayOps(cs), (JFunction1.mcZD.sp)(x$27) -> evidence$5$2.eqv$mcD$sp(x$27, package$.MODULE$.Field().apply(evidence$4$2).zero$mcD$sp()));
      return Polynomial$.MODULE$.dense$mDc$sp((double[])scala.collection.ArrayOps..MODULE$.reverse$extension(scala.Predef..MODULE$.genericArrayOps(ncs)), evidence$4$2, evidence$5$2, evidence$6$2);
   }

   private final Tuple2 eval$2(final double[] q, final double[] u, final int n, final Polynomial rhs$2, final Field evidence$4$2, final ClassTag evidence$6$2, final Eq evidence$5$2) {
      while(!scala.collection.ArrayOps..MODULE$.isEmpty$extension(scala.Predef..MODULE$.genericArrayOps(u)) && n >= 0) {
         double v0 = rhs$2.isZero() ? package$.MODULE$.Field().apply(evidence$4$2).zero$mcD$sp() : rhs$2.maxOrderTermCoeff$mcD$sp(evidence$4$2);
         double q0 = evidence$4$2.div$mcD$sp(u[0], v0);
         double[] uprime = zipSum$2(u, (double[])scala.collection.ArrayOps..MODULE$.map$extension(scala.Predef..MODULE$.genericArrayOps(scala.collection.ArrayOps..MODULE$.reverse$extension(scala.Predef..MODULE$.genericArrayOps(rhs$2.coeffsArray$mcD$sp(evidence$4$2)))), (JFunction1.mcDD.sp)(x$28) -> evidence$4$2.times$mcD$sp(x$28, evidence$4$2.negate$mcD$sp(q0)), evidence$6$2), evidence$4$2, evidence$6$2);
         double[] var10000 = (double[])scala.collection.ArrayOps..MODULE$.$plus$plus$extension(scala.Predef..MODULE$.genericArrayOps(scala.Array..MODULE$.apply(.MODULE$.genericWrapArray(new double[]{q0}), evidence$6$2)), q, evidence$6$2);
         --n;
         u = uprime;
         q = var10000;
      }

      return new Tuple2(polyFromCoeffsLE$2(q, evidence$4$2, evidence$5$2, evidence$6$2), polyFromCoeffsBE$2(u, evidence$5$2, evidence$4$2, evidence$6$2));
   }

   private PolyDense$() {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
