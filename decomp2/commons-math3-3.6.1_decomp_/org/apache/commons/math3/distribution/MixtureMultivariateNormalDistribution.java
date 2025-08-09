package org.apache.commons.math3.distribution;

import java.util.ArrayList;
import java.util.List;
import org.apache.commons.math3.exception.DimensionMismatchException;
import org.apache.commons.math3.exception.NotPositiveException;
import org.apache.commons.math3.random.RandomGenerator;
import org.apache.commons.math3.util.Pair;

public class MixtureMultivariateNormalDistribution extends MixtureMultivariateRealDistribution {
   public MixtureMultivariateNormalDistribution(double[] weights, double[][] means, double[][][] covariances) {
      super(createComponents(weights, means, covariances));
   }

   public MixtureMultivariateNormalDistribution(List components) {
      super(components);
   }

   public MixtureMultivariateNormalDistribution(RandomGenerator rng, List components) throws NotPositiveException, DimensionMismatchException {
      super(rng, components);
   }

   private static List createComponents(double[] weights, double[][] means, double[][][] covariances) {
      List<Pair<Double, MultivariateNormalDistribution>> mvns = new ArrayList(weights.length);

      for(int i = 0; i < weights.length; ++i) {
         MultivariateNormalDistribution dist = new MultivariateNormalDistribution(means[i], covariances[i]);
         mvns.add(new Pair(weights[i], dist));
      }

      return mvns;
   }
}
