package org.apache.commons.math3.optimization.direct;

import org.apache.commons.math3.analysis.MultivariateFunction;
import org.apache.commons.math3.optimization.BaseMultivariateOptimizer;
import org.apache.commons.math3.optimization.BaseMultivariateSimpleBoundsOptimizer;
import org.apache.commons.math3.optimization.ConvergenceChecker;
import org.apache.commons.math3.optimization.GoalType;
import org.apache.commons.math3.optimization.InitialGuess;
import org.apache.commons.math3.optimization.PointValuePair;
import org.apache.commons.math3.optimization.SimpleBounds;

/** @deprecated */
@Deprecated
public abstract class BaseAbstractMultivariateSimpleBoundsOptimizer extends BaseAbstractMultivariateOptimizer implements BaseMultivariateOptimizer, BaseMultivariateSimpleBoundsOptimizer {
   /** @deprecated */
   @Deprecated
   protected BaseAbstractMultivariateSimpleBoundsOptimizer() {
   }

   protected BaseAbstractMultivariateSimpleBoundsOptimizer(ConvergenceChecker checker) {
      super(checker);
   }

   public PointValuePair optimize(int maxEval, MultivariateFunction f, GoalType goalType, double[] startPoint) {
      return super.optimizeInternal(maxEval, f, goalType, new InitialGuess(startPoint));
   }

   public PointValuePair optimize(int maxEval, MultivariateFunction f, GoalType goalType, double[] startPoint, double[] lower, double[] upper) {
      return super.optimizeInternal(maxEval, f, goalType, new InitialGuess(startPoint), new SimpleBounds(lower, upper));
   }
}
