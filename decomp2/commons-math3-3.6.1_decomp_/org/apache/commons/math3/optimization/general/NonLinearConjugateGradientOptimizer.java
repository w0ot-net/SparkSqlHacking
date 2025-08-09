package org.apache.commons.math3.optimization.general;

import org.apache.commons.math3.analysis.UnivariateFunction;
import org.apache.commons.math3.analysis.solvers.BrentSolver;
import org.apache.commons.math3.analysis.solvers.UnivariateSolver;
import org.apache.commons.math3.exception.MathIllegalStateException;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.optimization.ConvergenceChecker;
import org.apache.commons.math3.optimization.GoalType;
import org.apache.commons.math3.optimization.PointValuePair;
import org.apache.commons.math3.optimization.SimpleValueChecker;
import org.apache.commons.math3.util.FastMath;

/** @deprecated */
@Deprecated
public class NonLinearConjugateGradientOptimizer extends AbstractScalarDifferentiableOptimizer {
   private final ConjugateGradientFormula updateFormula;
   private final Preconditioner preconditioner;
   private final UnivariateSolver solver;
   private double initialStep;
   private double[] point;

   /** @deprecated */
   @Deprecated
   public NonLinearConjugateGradientOptimizer(ConjugateGradientFormula updateFormula) {
      this(updateFormula, new SimpleValueChecker());
   }

   public NonLinearConjugateGradientOptimizer(ConjugateGradientFormula updateFormula, ConvergenceChecker checker) {
      this(updateFormula, checker, new BrentSolver(), new IdentityPreconditioner());
   }

   public NonLinearConjugateGradientOptimizer(ConjugateGradientFormula updateFormula, ConvergenceChecker checker, UnivariateSolver lineSearchSolver) {
      this(updateFormula, checker, lineSearchSolver, new IdentityPreconditioner());
   }

   public NonLinearConjugateGradientOptimizer(ConjugateGradientFormula updateFormula, ConvergenceChecker checker, UnivariateSolver lineSearchSolver, Preconditioner preconditioner) {
      super(checker);
      this.updateFormula = updateFormula;
      this.solver = lineSearchSolver;
      this.preconditioner = preconditioner;
      this.initialStep = (double)1.0F;
   }

   public void setInitialStep(double initialStep) {
      if (initialStep <= (double)0.0F) {
         this.initialStep = (double)1.0F;
      } else {
         this.initialStep = initialStep;
      }

   }

   protected PointValuePair doOptimize() {
      ConvergenceChecker<PointValuePair> checker = this.getConvergenceChecker();
      this.point = this.getStartPoint();
      GoalType goal = this.getGoalType();
      int n = this.point.length;
      double[] r = this.computeObjectiveGradient(this.point);
      if (goal == GoalType.MINIMIZE) {
         for(int i = 0; i < n; ++i) {
            r[i] = -r[i];
         }
      }

      double[] steepestDescent = this.preconditioner.precondition(this.point, r);
      double[] searchDirection = (double[])(([D)steepestDescent).clone();
      double delta = (double)0.0F;

      for(int i = 0; i < n; ++i) {
         delta += r[i] * searchDirection[i];
      }

      PointValuePair current = null;
      int iter = 0;
      int maxEval = this.getMaxEvaluations();

      while(true) {
         ++iter;
         double objective = this.computeObjectiveValue(this.point);
         PointValuePair previous = current;
         current = new PointValuePair(this.point, objective);
         if (previous != null && checker.converged(iter, previous, current)) {
            return current;
         }

         UnivariateFunction lsf = new LineSearchFunction(searchDirection);
         double uB = this.findUpperBound(lsf, (double)0.0F, this.initialStep);
         double step = this.solver.solve(maxEval, lsf, (double)0.0F, uB, 1.0E-15);
         maxEval -= this.solver.getEvaluations();

         for(int i = 0; i < this.point.length; ++i) {
            double[] var10000 = this.point;
            var10000[i] += step * searchDirection[i];
         }

         r = this.computeObjectiveGradient(this.point);
         if (goal == GoalType.MINIMIZE) {
            for(int i = 0; i < n; ++i) {
               r[i] = -r[i];
            }
         }

         double deltaOld = delta;
         double[] newSteepestDescent = this.preconditioner.precondition(this.point, r);
         delta = (double)0.0F;

         for(int i = 0; i < n; ++i) {
            delta += r[i] * newSteepestDescent[i];
         }

         double beta;
         if (this.updateFormula == ConjugateGradientFormula.FLETCHER_REEVES) {
            beta = delta / deltaOld;
         } else {
            double deltaMid = (double)0.0F;

            for(int i = 0; i < r.length; ++i) {
               deltaMid += r[i] * steepestDescent[i];
            }

            beta = (delta - deltaMid) / deltaOld;
         }

         steepestDescent = newSteepestDescent;
         if (iter % n != 0 && !(beta < (double)0.0F)) {
            for(int i = 0; i < n; ++i) {
               searchDirection[i] = steepestDescent[i] + beta * searchDirection[i];
            }
         } else {
            searchDirection = (double[])(([D)newSteepestDescent).clone();
         }
      }
   }

   private double findUpperBound(UnivariateFunction f, double a, double h) {
      double yA = f.value(a);

      double yB;
      for(double step = h; step < Double.MAX_VALUE; step *= FastMath.max((double)2.0F, yA / yB)) {
         double b = a + step;
         yB = f.value(b);
         if (yA * yB <= (double)0.0F) {
            return b;
         }
      }

      throw new MathIllegalStateException(LocalizedFormats.UNABLE_TO_BRACKET_OPTIMUM_IN_LINE_SEARCH, new Object[0]);
   }

   public static class IdentityPreconditioner implements Preconditioner {
      public double[] precondition(double[] variables, double[] r) {
         return (double[])(([D)r).clone();
      }
   }

   private class LineSearchFunction implements UnivariateFunction {
      private final double[] searchDirection;

      LineSearchFunction(double[] searchDirection) {
         this.searchDirection = searchDirection;
      }

      public double value(double x) {
         double[] shiftedPoint = (double[])NonLinearConjugateGradientOptimizer.this.point.clone();

         for(int i = 0; i < shiftedPoint.length; ++i) {
            shiftedPoint[i] += x * this.searchDirection[i];
         }

         double[] gradient = NonLinearConjugateGradientOptimizer.this.computeObjectiveGradient(shiftedPoint);
         double dotProduct = (double)0.0F;

         for(int i = 0; i < gradient.length; ++i) {
            dotProduct += gradient[i] * this.searchDirection[i];
         }

         return dotProduct;
      }
   }
}
