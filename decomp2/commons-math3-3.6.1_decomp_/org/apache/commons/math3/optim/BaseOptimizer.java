package org.apache.commons.math3.optim;

import org.apache.commons.math3.exception.TooManyEvaluationsException;
import org.apache.commons.math3.exception.TooManyIterationsException;
import org.apache.commons.math3.util.Incrementor;

public abstract class BaseOptimizer {
   protected final Incrementor evaluations;
   protected final Incrementor iterations;
   private final ConvergenceChecker checker;

   protected BaseOptimizer(ConvergenceChecker checker) {
      this(checker, 0, Integer.MAX_VALUE);
   }

   protected BaseOptimizer(ConvergenceChecker checker, int maxEval, int maxIter) {
      this.checker = checker;
      this.evaluations = new Incrementor(maxEval, new MaxEvalCallback());
      this.iterations = new Incrementor(maxIter, new MaxIterCallback());
   }

   public int getMaxEvaluations() {
      return this.evaluations.getMaximalCount();
   }

   public int getEvaluations() {
      return this.evaluations.getCount();
   }

   public int getMaxIterations() {
      return this.iterations.getMaximalCount();
   }

   public int getIterations() {
      return this.iterations.getCount();
   }

   public ConvergenceChecker getConvergenceChecker() {
      return this.checker;
   }

   public Object optimize(OptimizationData... optData) throws TooManyEvaluationsException, TooManyIterationsException {
      this.parseOptimizationData(optData);
      this.evaluations.resetCount();
      this.iterations.resetCount();
      return this.doOptimize();
   }

   public Object optimize() throws TooManyEvaluationsException, TooManyIterationsException {
      this.evaluations.resetCount();
      this.iterations.resetCount();
      return this.doOptimize();
   }

   protected abstract Object doOptimize();

   protected void incrementEvaluationCount() throws TooManyEvaluationsException {
      this.evaluations.incrementCount();
   }

   protected void incrementIterationCount() throws TooManyIterationsException {
      this.iterations.incrementCount();
   }

   protected void parseOptimizationData(OptimizationData... optData) {
      for(OptimizationData data : optData) {
         if (data instanceof MaxEval) {
            this.evaluations.setMaximalCount(((MaxEval)data).getMaxEval());
         } else if (data instanceof MaxIter) {
            this.iterations.setMaximalCount(((MaxIter)data).getMaxIter());
         }
      }

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
