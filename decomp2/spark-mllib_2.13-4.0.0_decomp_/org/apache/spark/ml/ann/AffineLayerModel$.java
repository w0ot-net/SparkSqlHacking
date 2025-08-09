package org.apache.spark.ml.ann;

import breeze.linalg.DenseVector;
import java.io.Serializable;
import java.util.Random;
import scala.math.package.;
import scala.runtime.ModuleSerializationProxy;

public final class AffineLayerModel$ implements Serializable {
   public static final AffineLayerModel$ MODULE$ = new AffineLayerModel$();

   public AffineLayerModel apply(final AffineLayer layer, final DenseVector weights, final Random random) {
      this.randomWeights(layer.numIn(), layer.numOut(), weights, random);
      return new AffineLayerModel(weights, layer);
   }

   public void randomWeights(final int numIn, final int numOut, final DenseVector weights, final Random random) {
      int i = 0;

      for(double sqrtIn = .MODULE$.sqrt((double)numIn); i < weights.length(); ++i) {
         weights.update$mcD$sp(i, (random.nextDouble() * 4.8 - 2.4) / sqrtIn);
      }

   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(AffineLayerModel$.class);
   }

   private AffineLayerModel$() {
   }
}
