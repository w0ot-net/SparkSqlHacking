package org.apache.commons.math3.optimization;

import org.apache.commons.math3.random.RandomVectorGenerator;

/** @deprecated */
@Deprecated
public class MultivariateDifferentiableMultiStartOptimizer extends BaseMultivariateMultiStartOptimizer implements MultivariateDifferentiableOptimizer {
   public MultivariateDifferentiableMultiStartOptimizer(MultivariateDifferentiableOptimizer optimizer, int starts, RandomVectorGenerator generator) {
      super(optimizer, starts, generator);
   }
}
