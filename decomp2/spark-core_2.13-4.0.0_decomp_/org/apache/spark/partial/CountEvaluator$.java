package org.apache.spark.partial;

import org.apache.commons.math3.distribution.PoissonDistribution;

public final class CountEvaluator$ {
   public static final CountEvaluator$ MODULE$ = new CountEvaluator$();

   public BoundedDouble bound(final double confidence, final long sum, final double p) {
      PoissonDistribution dist = new PoissonDistribution((double)sum * ((double)1 - p) / p);
      int low = dist.inverseCumulativeProbability(((double)1 - confidence) / (double)2);
      int high = dist.inverseCumulativeProbability(((double)1 + confidence) / (double)2);
      return new BoundedDouble((double)sum + dist.getNumericalMean(), confidence, (double)(sum + (long)low), (double)(sum + (long)high));
   }

   private CountEvaluator$() {
   }
}
