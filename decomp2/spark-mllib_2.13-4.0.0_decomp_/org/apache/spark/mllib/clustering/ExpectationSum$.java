package org.apache.spark.mllib.clustering;

import breeze.linalg.DenseMatrix;
import breeze.linalg.DenseVector;
import breeze.linalg.Vector;
import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import org.apache.spark.mllib.linalg.BLAS$;
import org.apache.spark.mllib.linalg.Matrices$;
import org.apache.spark.mllib.linalg.Vectors$;
import org.apache.spark.mllib.stat.distribution.MultivariateGaussian;
import org.apache.spark.mllib.util.MLUtils$;
import scala.MatchError;
import scala.Tuple2;
import scala.Array.;
import scala.runtime.BoxesRunTime;
import scala.runtime.ModuleSerializationProxy;
import scala.runtime.java8.JFunction0;

public final class ExpectationSum$ implements Serializable {
   public static final ExpectationSum$ MODULE$ = new ExpectationSum$();

   public ExpectationSum zero(final int k, final int d) {
      return new ExpectationSum((double)0.0F, (double[]).MODULE$.fill(k, (JFunction0.mcD.sp)() -> (double)0.0F, scala.reflect.ClassTag..MODULE$.Double()), (DenseVector[]).MODULE$.fill(k, () -> breeze.linalg.DenseVector..MODULE$.zeros$mDc$sp(d, scala.reflect.ClassTag..MODULE$.Double(), breeze.storage.Zero..MODULE$.DoubleZero()), scala.reflect.ClassTag..MODULE$.apply(DenseVector.class)), (DenseMatrix[]).MODULE$.fill(k, () -> breeze.linalg.DenseMatrix..MODULE$.zeros$mDc$sp(d, d, scala.reflect.ClassTag..MODULE$.Double(), breeze.storage.Zero..MODULE$.DoubleZero()), scala.reflect.ClassTag..MODULE$.apply(DenseMatrix.class)));
   }

   public ExpectationSum add(final double[] weights, final MultivariateGaussian[] dists, final ExpectationSum sums, final Vector x) {
      double[] p = (double[])scala.collection.ArrayOps..MODULE$.map$extension(scala.Predef..MODULE$.refArrayOps((Object[])scala.collection.ArrayOps..MODULE$.zip$extension(scala.Predef..MODULE$.doubleArrayOps(weights), scala.Predef..MODULE$.wrapRefArray(dists))), (x0$1) -> BoxesRunTime.boxToDouble($anonfun$add$1(x, x0$1)), scala.reflect.ClassTag..MODULE$.Double());
      double pSum = BoxesRunTime.unboxToDouble(scala.Predef..MODULE$.wrapDoubleArray(p).sum(scala.math.Numeric.DoubleIsFractional..MODULE$));
      sums.logLikelihood_$eq(sums.logLikelihood() + scala.math.package..MODULE$.log(pSum));

      for(int i = 0; i < sums.k(); ++i) {
         p[i] /= pSum;
         sums.weights()[i] += p[i];
         sums.means()[i].$plus$eq(x.$times(BoxesRunTime.boxToDouble(p[i]), breeze.linalg.operators.HasOps..MODULE$.impl_Op_V_S_eq_V_Double_OpMulMatrix()), breeze.linalg.operators.HasOps..MODULE$.impl_Op_InPlace_DV_V_zero_idempotent_Double_OpAdd());
         BLAS$.MODULE$.syr(p[i], Vectors$.MODULE$.fromBreeze(x), (org.apache.spark.mllib.linalg.DenseMatrix)Matrices$.MODULE$.fromBreeze(sums.sigmas()[i]));
      }

      return sums;
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(ExpectationSum$.class);
   }

   // $FF: synthetic method
   public static final double $anonfun$add$1(final Vector x$7, final Tuple2 x0$1) {
      if (x0$1 != null) {
         double weight = x0$1._1$mcD$sp();
         MultivariateGaussian dist = (MultivariateGaussian)x0$1._2();
         return MLUtils$.MODULE$.EPSILON() + weight * dist.pdf(x$7);
      } else {
         throw new MatchError(x0$1);
      }
   }

   private ExpectationSum$() {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
