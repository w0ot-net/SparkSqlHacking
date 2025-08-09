package org.apache.spark.mllib.classification;

import java.io.Serializable;
import org.apache.spark.mllib.linalg.Vector;
import org.apache.spark.rdd.RDD;
import scala.runtime.ModuleSerializationProxy;

public final class SVMWithSGD$ implements Serializable {
   public static final SVMWithSGD$ MODULE$ = new SVMWithSGD$();

   public SVMModel train(final RDD input, final int numIterations, final double stepSize, final double regParam, final double miniBatchFraction, final Vector initialWeights) {
      return (SVMModel)(new SVMWithSGD(stepSize, numIterations, regParam, miniBatchFraction)).run(input, initialWeights);
   }

   public SVMModel train(final RDD input, final int numIterations, final double stepSize, final double regParam, final double miniBatchFraction) {
      return (SVMModel)(new SVMWithSGD(stepSize, numIterations, regParam, miniBatchFraction)).run(input);
   }

   public SVMModel train(final RDD input, final int numIterations, final double stepSize, final double regParam) {
      return this.train(input, numIterations, stepSize, regParam, (double)1.0F);
   }

   public SVMModel train(final RDD input, final int numIterations) {
      return this.train(input, numIterations, (double)1.0F, 0.01, (double)1.0F);
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(SVMWithSGD$.class);
   }

   private SVMWithSGD$() {
   }
}
