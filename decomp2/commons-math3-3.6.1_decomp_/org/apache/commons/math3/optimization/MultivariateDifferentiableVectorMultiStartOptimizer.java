package org.apache.commons.math3.optimization;

import org.apache.commons.math3.random.RandomVectorGenerator;

/** @deprecated */
@Deprecated
public class MultivariateDifferentiableVectorMultiStartOptimizer extends BaseMultivariateVectorMultiStartOptimizer implements MultivariateDifferentiableVectorOptimizer {
   public MultivariateDifferentiableVectorMultiStartOptimizer(MultivariateDifferentiableVectorOptimizer optimizer, int starts, RandomVectorGenerator generator) {
      super(optimizer, starts, generator);
   }
}
