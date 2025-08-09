package org.apache.commons.math3.optim;

import org.apache.commons.math3.exception.MathIllegalStateException;
import org.apache.commons.math3.exception.NotStrictlyPositiveException;
import org.apache.commons.math3.exception.TooManyEvaluationsException;
import org.apache.commons.math3.random.RandomVectorGenerator;

public abstract class BaseMultiStartMultivariateOptimizer extends BaseMultivariateOptimizer {
   private final BaseMultivariateOptimizer optimizer;
   private int totalEvaluations;
   private int starts;
   private RandomVectorGenerator generator;
   private OptimizationData[] optimData;
   private int maxEvalIndex = -1;
   private int initialGuessIndex = -1;

   public BaseMultiStartMultivariateOptimizer(BaseMultivariateOptimizer optimizer, int starts, RandomVectorGenerator generator) {
      super(optimizer.getConvergenceChecker());
      if (starts < 1) {
         throw new NotStrictlyPositiveException(starts);
      } else {
         this.optimizer = optimizer;
         this.starts = starts;
         this.generator = generator;
      }
   }

   public int getEvaluations() {
      return this.totalEvaluations;
   }

   public abstract Object[] getOptima();

   public Object optimize(OptimizationData... optData) {
      this.optimData = optData;
      return super.optimize(optData);
   }

   protected Object doOptimize() {
      for(int i = 0; i < this.optimData.length; ++i) {
         if (this.optimData[i] instanceof MaxEval) {
            this.optimData[i] = null;
            this.maxEvalIndex = i;
         }

         if (this.optimData[i] instanceof InitialGuess) {
            this.optimData[i] = null;
            this.initialGuessIndex = i;
         }
      }

      if (this.maxEvalIndex == -1) {
         throw new MathIllegalStateException();
      } else if (this.initialGuessIndex == -1) {
         throw new MathIllegalStateException();
      } else {
         RuntimeException lastException = null;
         this.totalEvaluations = 0;
         this.clear();
         int maxEval = this.getMaxEvaluations();
         double[] min = this.getLowerBound();
         double[] max = this.getUpperBound();
         double[] startPoint = this.getStartPoint();

         for(int i = 0; i < this.starts; ++i) {
            try {
               this.optimData[this.maxEvalIndex] = new MaxEval(maxEval - this.totalEvaluations);
               double[] s = null;
               if (i == 0) {
                  s = startPoint;
               } else {
                  int attempts = 0;

                  while(s == null) {
                     if (attempts++ >= this.getMaxEvaluations()) {
                        throw new TooManyEvaluationsException(this.getMaxEvaluations());
                     }

                     s = this.generator.nextVector();

                     for(int k = 0; s != null && k < s.length; ++k) {
                        if (min != null && s[k] < min[k] || max != null && s[k] > max[k]) {
                           s = null;
                        }
                     }
                  }
               }

               this.optimData[this.initialGuessIndex] = new InitialGuess(s);
               PAIR result = (PAIR)this.optimizer.optimize(this.optimData);
               this.store(result);
            } catch (RuntimeException mue) {
               lastException = mue;
            }

            this.totalEvaluations += this.optimizer.getEvaluations();
         }

         PAIR[] optima = (PAIR[])this.getOptima();
         if (optima.length == 0) {
            throw lastException;
         } else {
            return optima[0];
         }
      }
   }

   protected abstract void store(Object var1);

   protected abstract void clear();
}
