package org.apache.commons.math3.optimization;

import org.apache.commons.math3.random.RandomVectorGenerator;

/** @deprecated */
@Deprecated
public class MultivariateMultiStartOptimizer extends BaseMultivariateMultiStartOptimizer implements MultivariateOptimizer {
   public MultivariateMultiStartOptimizer(MultivariateOptimizer optimizer, int starts, RandomVectorGenerator generator) {
      super(optimizer, starts, generator);
   }
}
