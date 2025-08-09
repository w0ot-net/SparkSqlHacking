package org.apache.commons.math3.optimization;

import org.apache.commons.math3.analysis.MultivariateVectorFunction;

/** @deprecated */
@Deprecated
public interface BaseMultivariateVectorOptimizer extends BaseOptimizer {
   /** @deprecated */
   @Deprecated
   PointVectorValuePair optimize(int var1, MultivariateVectorFunction var2, double[] var3, double[] var4, double[] var5);
}
