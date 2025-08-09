package org.apache.commons.math3.optimization.univariate;

import java.util.Arrays;
import java.util.Comparator;
import org.apache.commons.math3.analysis.UnivariateFunction;
import org.apache.commons.math3.exception.MathIllegalStateException;
import org.apache.commons.math3.exception.NotStrictlyPositiveException;
import org.apache.commons.math3.exception.NullArgumentException;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.optimization.ConvergenceChecker;
import org.apache.commons.math3.optimization.GoalType;
import org.apache.commons.math3.random.RandomGenerator;

/** @deprecated */
@Deprecated
public class UnivariateMultiStartOptimizer implements BaseUnivariateOptimizer {
   private final BaseUnivariateOptimizer optimizer;
   private int maxEvaluations;
   private int totalEvaluations;
   private int starts;
   private RandomGenerator generator;
   private UnivariatePointValuePair[] optima;

   public UnivariateMultiStartOptimizer(BaseUnivariateOptimizer optimizer, int starts, RandomGenerator generator) {
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

   public ConvergenceChecker getConvergenceChecker() {
      return this.optimizer.getConvergenceChecker();
   }

   public int getMaxEvaluations() {
      return this.maxEvaluations;
   }

   public int getEvaluations() {
      return this.totalEvaluations;
   }

   public UnivariatePointValuePair[] getOptima() {
      if (this.optima == null) {
         throw new MathIllegalStateException(LocalizedFormats.NO_OPTIMUM_COMPUTED_YET, new Object[0]);
      } else {
         return (UnivariatePointValuePair[])this.optima.clone();
      }
   }

   public UnivariatePointValuePair optimize(int maxEval, UnivariateFunction f, GoalType goal, double min, double max) {
      return this.optimize(maxEval, f, goal, min, max, min + (double)0.5F * (max - min));
   }

   public UnivariatePointValuePair optimize(int maxEval, UnivariateFunction f, GoalType goal, double min, double max, double startValue) {
      RuntimeException lastException = null;
      this.optima = new UnivariatePointValuePair[this.starts];
      this.totalEvaluations = 0;

      for(int i = 0; i < this.starts; ++i) {
         try {
            double s = i == 0 ? startValue : min + this.generator.nextDouble() * (max - min);
            this.optima[i] = this.optimizer.optimize(maxEval - this.totalEvaluations, f, goal, min, max, s);
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
