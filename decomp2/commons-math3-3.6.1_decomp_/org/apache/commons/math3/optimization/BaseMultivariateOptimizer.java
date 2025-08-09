package org.apache.commons.math3.optimization;

import org.apache.commons.math3.analysis.MultivariateFunction;

/** @deprecated */
@Deprecated
public interface BaseMultivariateOptimizer extends BaseOptimizer {
   /** @deprecated */
   @Deprecated
   PointValuePair optimize(int var1, MultivariateFunction var2, GoalType var3, double[] var4);
}
