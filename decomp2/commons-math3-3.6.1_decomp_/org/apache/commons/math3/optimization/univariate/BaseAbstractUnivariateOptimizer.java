package org.apache.commons.math3.optimization.univariate;

import org.apache.commons.math3.analysis.UnivariateFunction;
import org.apache.commons.math3.exception.MaxCountExceededException;
import org.apache.commons.math3.exception.NullArgumentException;
import org.apache.commons.math3.exception.TooManyEvaluationsException;
import org.apache.commons.math3.optimization.ConvergenceChecker;
import org.apache.commons.math3.optimization.GoalType;
import org.apache.commons.math3.util.Incrementor;

/** @deprecated */
@Deprecated
public abstract class BaseAbstractUnivariateOptimizer implements UnivariateOptimizer {
   private final ConvergenceChecker checker;
   private final Incrementor evaluations = new Incrementor();
   private GoalType goal;
   private double searchMin;
   private double searchMax;
   private double searchStart;
   private UnivariateFunction function;

   protected BaseAbstractUnivariateOptimizer(ConvergenceChecker checker) {
      this.checker = checker;
   }

   public int getMaxEvaluations() {
      return this.evaluations.getMaximalCount();
   }

   public int getEvaluations() {
      return this.evaluations.getCount();
   }

   public GoalType getGoalType() {
      return this.goal;
   }

   public double getMin() {
      return this.searchMin;
   }

   public double getMax() {
      return this.searchMax;
   }

   public double getStartValue() {
      return this.searchStart;
   }

   protected double computeObjectiveValue(double point) {
      try {
         this.evaluations.incrementCount();
      } catch (MaxCountExceededException e) {
         throw new TooManyEvaluationsException(e.getMax());
      }

      return this.function.value(point);
   }

   public UnivariatePointValuePair optimize(int maxEval, UnivariateFunction f, GoalType goalType, double min, double max, double startValue) {
      if (f == null) {
         throw new NullArgumentException();
      } else if (goalType == null) {
         throw new NullArgumentException();
      } else {
         this.searchMin = min;
         this.searchMax = max;
         this.searchStart = startValue;
         this.goal = goalType;
         this.function = f;
         this.evaluations.setMaximalCount(maxEval);
         this.evaluations.resetCount();
         return this.doOptimize();
      }
   }

   public UnivariatePointValuePair optimize(int maxEval, UnivariateFunction f, GoalType goalType, double min, double max) {
      return this.optimize(maxEval, f, goalType, min, max, min + (double)0.5F * (max - min));
   }

   public ConvergenceChecker getConvergenceChecker() {
      return this.checker;
   }

   protected abstract UnivariatePointValuePair doOptimize();
}
