package org.apache.commons.math3.optimization;

import org.apache.commons.math3.random.RandomVectorGenerator;

/** @deprecated */
@Deprecated
public class DifferentiableMultivariateMultiStartOptimizer extends BaseMultivariateMultiStartOptimizer implements DifferentiableMultivariateOptimizer {
   public DifferentiableMultivariateMultiStartOptimizer(DifferentiableMultivariateOptimizer optimizer, int starts, RandomVectorGenerator generator) {
      super(optimizer, starts, generator);
   }
}
