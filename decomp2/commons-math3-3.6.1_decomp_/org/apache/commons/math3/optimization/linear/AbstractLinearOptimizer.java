package org.apache.commons.math3.optimization.linear;

import java.util.Collection;
import java.util.Collections;
import org.apache.commons.math3.exception.MathIllegalStateException;
import org.apache.commons.math3.exception.MaxCountExceededException;
import org.apache.commons.math3.optimization.GoalType;
import org.apache.commons.math3.optimization.PointValuePair;

/** @deprecated */
@Deprecated
public abstract class AbstractLinearOptimizer implements LinearOptimizer {
   public static final int DEFAULT_MAX_ITERATIONS = 100;
   private LinearObjectiveFunction function;
   private Collection linearConstraints;
   private GoalType goal;
   private boolean nonNegative;
   private int maxIterations;
   private int iterations;

   protected AbstractLinearOptimizer() {
      this.setMaxIterations(100);
   }

   protected boolean restrictToNonNegative() {
      return this.nonNegative;
   }

   protected GoalType getGoalType() {
      return this.goal;
   }

   protected LinearObjectiveFunction getFunction() {
      return this.function;
   }

   protected Collection getConstraints() {
      return Collections.unmodifiableCollection(this.linearConstraints);
   }

   public void setMaxIterations(int maxIterations) {
      this.maxIterations = maxIterations;
   }

   public int getMaxIterations() {
      return this.maxIterations;
   }

   public int getIterations() {
      return this.iterations;
   }

   protected void incrementIterationsCounter() throws MaxCountExceededException {
      if (++this.iterations > this.maxIterations) {
         throw new MaxCountExceededException(this.maxIterations);
      }
   }

   public PointValuePair optimize(LinearObjectiveFunction f, Collection constraints, GoalType goalType, boolean restrictToNonNegative) throws MathIllegalStateException {
      this.function = f;
      this.linearConstraints = constraints;
      this.goal = goalType;
      this.nonNegative = restrictToNonNegative;
      this.iterations = 0;
      return this.doOptimize();
   }

   protected abstract PointValuePair doOptimize() throws MathIllegalStateException;
}
