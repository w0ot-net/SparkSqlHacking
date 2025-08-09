package org.apache.spark.ml.classification;

import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import org.apache.spark.ml.linalg.DenseVector;
import scala.collection.ArrayOps.;
import scala.runtime.BoxesRunTime;
import scala.runtime.ModuleSerializationProxy;
import scala.runtime.java8.JFunction1;

public final class ProbabilisticClassificationModel$ implements Serializable {
   public static final ProbabilisticClassificationModel$ MODULE$ = new ProbabilisticClassificationModel$();

   public void normalizeToProbabilitiesInPlace(final DenseVector v) {
      .MODULE$.foreach$extension(scala.Predef..MODULE$.doubleArrayOps(v.values()), (JFunction1.mcVD.sp)(value) -> scala.Predef..MODULE$.require(value >= (double)0, () -> "The input raw predictions should be nonnegative."));
      double sum = BoxesRunTime.unboxToDouble(scala.Predef..MODULE$.wrapDoubleArray(v.values()).sum(scala.math.Numeric.DoubleIsFractional..MODULE$));
      scala.Predef..MODULE$.require(sum > (double)0, () -> "Can't normalize the 0-vector.");
      int i = 0;

      for(int size = v.size(); i < size; ++i) {
         v.values()[i] /= sum;
      }

   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(ProbabilisticClassificationModel$.class);
   }

   private ProbabilisticClassificationModel$() {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
