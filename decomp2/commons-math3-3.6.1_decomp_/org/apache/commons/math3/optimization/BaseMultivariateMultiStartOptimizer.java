package org.apache.commons.math3.optimization;

import java.util.Arrays;
import java.util.Comparator;
import org.apache.commons.math3.analysis.MultivariateFunction;
import org.apache.commons.math3.exception.MathIllegalStateException;
import org.apache.commons.math3.exception.NotStrictlyPositiveException;
import org.apache.commons.math3.exception.NullArgumentException;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.random.RandomVectorGenerator;

/** @deprecated */
@Deprecated
public class BaseMultivariateMultiStartOptimizer implements BaseMultivariateOptimizer {
   private final BaseMultivariateOptimizer optimizer;
   private int maxEvaluations;
   private int totalEvaluations;
   private int starts;
   private RandomVectorGenerator generator;
   private PointValuePair[] optima;

   protected BaseMultivariateMultiStartOptimizer(BaseMultivariateOptimizer optimizer, int starts, RandomVectorGenerator generator) {
      if (optimizer != null && generator != null) {
         if (starts < 1) {
            throw new NotStrictlyPositiveException(starts);
         } else {
            this.optimizer = optimizer;
            this.starts = starts;
            this.generator = generator;
         }
      } else {
         throw new NullArgumentException();
      }
   }

   public PointValuePair[] getOptima() {
      if (this.optima == null) {
         throw new MathIllegalStateException(LocalizedFormats.NO_OPTIMUM_COMPUTED_YET, new Object[0]);
      } else {
         return (PointValuePair[])this.optima.clone();
      }
   }

   public int getMaxEvaluations() {
      return this.maxEvaluations;
   }

   public int getEvaluations() {
      return this.totalEvaluations;
   }

   public ConvergenceChecker getConvergenceChecker() {
      return this.optimizer.getConvergenceChecker();
   }

   public PointValuePair optimize(int maxEval, MultivariateFunction f, GoalType goal, double[] startPoint) {
      this.maxEvaluations = maxEval;
      RuntimeException lastException = null;
      this.optima = new PointValuePair[this.starts];
      this.totalEvaluations = 0;

      for(int i = 0; i < this.starts; ++i) {
         try {
            this.optima[i] = this.optimizer.optimize(maxEval - this.totalEvaluations, f, goal, i == 0 ? startPoint : this.generator.nextVector());
         } catch (RuntimeException mue) {
            lastException = mue;
            this.optima[i] = null;
         }

         this.totalEvaluations += this.optimizer.getEvaluations();
      }

      this.sortPairs(goal);
      if (this.optima[0] == null) {
         throw lastException;
      } else {
         return this.optima[0];
      }
   }

   private void sortPairs(GoalType goal) {
      // $FF: Couldn't be decompiled
   }
}
