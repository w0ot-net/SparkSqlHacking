package org.apache.commons.math3.ml.neuralnet;

import org.apache.commons.math3.analysis.UnivariateFunction;
import org.apache.commons.math3.analysis.function.Constant;
import org.apache.commons.math3.distribution.RealDistribution;
import org.apache.commons.math3.distribution.UniformRealDistribution;
import org.apache.commons.math3.random.RandomGenerator;

public class FeatureInitializerFactory {
   private FeatureInitializerFactory() {
   }

   public static FeatureInitializer uniform(RandomGenerator rng, double min, double max) {
      return randomize(new UniformRealDistribution(rng, min, max), function(new Constant((double)0.0F), (double)0.0F, (double)0.0F));
   }

   public static FeatureInitializer uniform(double min, double max) {
      return randomize(new UniformRealDistribution(min, max), function(new Constant((double)0.0F), (double)0.0F, (double)0.0F));
   }

   public static FeatureInitializer function(final UnivariateFunction f, final double init, final double inc) {
      return new FeatureInitializer() {
         private double arg = init;

         public double value() {
            double result = f.value(this.arg);
            this.arg += inc;
            return result;
         }
      };
   }

   public static FeatureInitializer randomize(final RealDistribution random, final FeatureInitializer orig) {
      return new FeatureInitializer() {
         public double value() {
            return orig.value() + random.sample();
         }
      };
   }
}
