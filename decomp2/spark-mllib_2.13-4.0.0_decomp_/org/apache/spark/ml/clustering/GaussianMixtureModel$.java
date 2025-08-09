package org.apache.spark.ml.clustering;

import java.io.Serializable;
import org.apache.spark.ml.linalg.Vector;
import org.apache.spark.ml.stat.distribution.MultivariateGaussian;
import org.apache.spark.ml.util.MLReadable;
import org.apache.spark.ml.util.MLReader;
import scala.Array.;
import scala.runtime.ModuleSerializationProxy;

public final class GaussianMixtureModel$ implements MLReadable, Serializable {
   public static final GaussianMixtureModel$ MODULE$ = new GaussianMixtureModel$();

   static {
      MLReadable.$init$(MODULE$);
   }

   public MLReader read() {
      return new GaussianMixtureModel.GaussianMixtureModelReader();
   }

   public GaussianMixtureModel load(final String path) {
      return (GaussianMixtureModel)MLReadable.load$(this, path);
   }

   public double[] computeProbabilities(final Vector features, final MultivariateGaussian[] dists, final double[] weights) {
      double[] probArray = (double[]).MODULE$.ofDim(weights.length, scala.reflect.ClassTag..MODULE$.Double());
      double probSum = (double)0.0F;

      for(int i = 0; i < weights.length; ++i) {
         double p = org.apache.spark.ml.impl.Utils..MODULE$.EPSILON() + weights[i] * dists[i].pdf(features);
         probArray[i] = p;
         probSum += p;
      }

      for(int var11 = 0; var11 < weights.length; ++var11) {
         probArray[var11] /= probSum;
      }

      return probArray;
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(GaussianMixtureModel$.class);
   }

   private GaussianMixtureModel$() {
   }
}
