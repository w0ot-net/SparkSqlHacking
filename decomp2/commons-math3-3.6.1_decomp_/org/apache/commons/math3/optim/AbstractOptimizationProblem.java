package org.apache.commons.math3.optim;

import org.apache.commons.math3.exception.TooManyEvaluationsException;
import org.apache.commons.math3.exception.TooManyIterationsException;
import org.apache.commons.math3.util.Incrementor;

public abstract class AbstractOptimizationProblem implements OptimizationProblem {
   private static final MaxEvalCallback MAX_EVAL_CALLBACK = new MaxEvalCallback();
   private static final MaxIterCallback MAX_ITER_CALLBACK = new MaxIterCallback();
   private final int maxEvaluations;
   private final int maxIterations;
   private final ConvergenceChecker checker;

   protected AbstractOptimizationProblem(int maxEvaluations, int maxIterations, ConvergenceChecker checker) {
      this.maxEvaluations = maxEvaluations;
      this.maxIterations = maxIterations;
      this.checker = checker;
   }

   public Incrementor getEvaluationCounter() {
      return new Incrementor(this.maxEvaluations, MAX_EVAL_CALLBACK);
   }

   public Incrementor getIterationCounter() {
      return new Incrementor(this.maxIterations, MAX_ITER_CALLBACK);
   }

   public ConvergenceChecker getConvergenceChecker() {
      return this.checker;
   }

   private static class MaxEvalCallback implements Incrementor.MaxCountExceededCallback {
      private MaxEvalCallback() {
      }

      public void trigger(int max) {
         throw new TooManyEvaluationsException(max);
      }
   }

   private static class MaxIterCallback implements Incrementor.MaxCountExceededCallback {
      private MaxIterCallback() {
      }

      public void trigger(int max) {
         throw new TooManyIterationsException(max);
      }
   }
}
