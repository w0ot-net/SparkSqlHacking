package org.apache.commons.math3.optimization;

import org.apache.commons.math3.random.RandomVectorGenerator;

/** @deprecated */
@Deprecated
public class DifferentiableMultivariateVectorMultiStartOptimizer extends BaseMultivariateVectorMultiStartOptimizer implements DifferentiableMultivariateVectorOptimizer {
   public DifferentiableMultivariateVectorMultiStartOptimizer(DifferentiableMultivariateVectorOptimizer optimizer, int starts, RandomVectorGenerator generator) {
      super(optimizer, starts, generator);
   }
}
