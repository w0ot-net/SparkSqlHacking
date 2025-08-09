package org.apache.commons.math3.optimization;

/** @deprecated */
@Deprecated
public interface BaseOptimizer {
   int getMaxEvaluations();

   int getEvaluations();

   ConvergenceChecker getConvergenceChecker();
}
