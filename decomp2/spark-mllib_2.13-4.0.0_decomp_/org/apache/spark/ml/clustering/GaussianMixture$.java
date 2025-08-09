package org.apache.spark.ml.clustering;

import java.io.Serializable;
import org.apache.spark.ml.linalg.DenseMatrix;
import org.apache.spark.ml.linalg.DenseVector;
import org.apache.spark.ml.linalg.Vector;
import org.apache.spark.ml.util.DefaultParamsReadable;
import org.apache.spark.ml.util.MLReadable;
import org.apache.spark.ml.util.MLReader;
import scala.Tuple2;
import scala.Tuple4;
import scala.math.package.;
import scala.runtime.BoxesRunTime;
import scala.runtime.ModuleSerializationProxy;

public final class GaussianMixture$ implements DefaultParamsReadable, Serializable {
   public static final GaussianMixture$ MODULE$ = new GaussianMixture$();
   private static final int MAX_NUM_FEATURES;

   static {
      MLReadable.$init$(MODULE$);
      DefaultParamsReadable.$init$(MODULE$);
      MAX_NUM_FEATURES = (int).MODULE$.sqrt((double)Integer.MAX_VALUE);
   }

   public MLReader read() {
      return DefaultParamsReadable.read$(this);
   }

   public int MAX_NUM_FEATURES() {
      return MAX_NUM_FEATURES;
   }

   public GaussianMixture load(final String path) {
      return (GaussianMixture)MLReadable.load$(this, path);
   }

   public DenseMatrix unpackUpperTriangularMatrix(final int n, final double[] triangularValues) {
      double[] symmetricValues = org.apache.spark.ml.impl.Utils..MODULE$.unpackUpperTriangular(n, triangularValues);
      return new DenseMatrix(n, n, symmetricValues);
   }

   public Tuple4 org$apache$spark$ml$clustering$GaussianMixture$$mergeWeightsMeans(final Tuple4 a, final Tuple4 b) {
      org.apache.spark.ml.linalg.BLAS..MODULE$.axpy((double)1.0F, (Vector)b._1(), (Vector)a._1());
      org.apache.spark.ml.linalg.BLAS..MODULE$.axpy((double)1.0F, (Vector)b._2(), (Vector)a._2());
      return new Tuple4(a._1(), a._2(), BoxesRunTime.boxToDouble(BoxesRunTime.unboxToDouble(a._3()) + BoxesRunTime.unboxToDouble(b._3())), BoxesRunTime.boxToDouble(BoxesRunTime.unboxToDouble(a._4()) + BoxesRunTime.unboxToDouble(b._4())));
   }

   public Tuple2 updateWeightsAndGaussians(final DenseVector mean, final DenseVector cov, final double weight, final double sumWeights) {
      org.apache.spark.ml.linalg.BLAS..MODULE$.scal((double)1.0F / weight, mean);
      org.apache.spark.ml.linalg.BLAS..MODULE$.spr(-weight, mean, cov);
      org.apache.spark.ml.linalg.BLAS..MODULE$.scal((double)1.0F / weight, cov);
      double newWeight = weight / sumWeights;
      Tuple2 newGaussian = new Tuple2(mean, cov);
      return new Tuple2(BoxesRunTime.boxToDouble(newWeight), newGaussian);
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(GaussianMixture$.class);
   }

   private GaussianMixture$() {
   }
}
