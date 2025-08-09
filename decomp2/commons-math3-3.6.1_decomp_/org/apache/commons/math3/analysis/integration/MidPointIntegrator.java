package org.apache.commons.math3.analysis.integration;

import org.apache.commons.math3.exception.MathIllegalArgumentException;
import org.apache.commons.math3.exception.MaxCountExceededException;
import org.apache.commons.math3.exception.NotStrictlyPositiveException;
import org.apache.commons.math3.exception.NumberIsTooLargeException;
import org.apache.commons.math3.exception.NumberIsTooSmallException;
import org.apache.commons.math3.exception.TooManyEvaluationsException;
import org.apache.commons.math3.util.FastMath;

public class MidPointIntegrator extends BaseAbstractUnivariateIntegrator {
   public static final int MIDPOINT_MAX_ITERATIONS_COUNT = 64;

   public MidPointIntegrator(double relativeAccuracy, double absoluteAccuracy, int minimalIterationCount, int maximalIterationCount) throws NotStrictlyPositiveException, NumberIsTooSmallException, NumberIsTooLargeException {
      super(relativeAccuracy, absoluteAccuracy, minimalIterationCount, maximalIterationCount);
      if (maximalIterationCount > 64) {
         throw new NumberIsTooLargeException(maximalIterationCount, 64, false);
      }
   }

   public MidPointIntegrator(int minimalIterationCount, int maximalIterationCount) throws NotStrictlyPositiveException, NumberIsTooSmallException, NumberIsTooLargeException {
      super(minimalIterationCount, maximalIterationCount);
      if (maximalIterationCount > 64) {
         throw new NumberIsTooLargeException(maximalIterationCount, 64, false);
      }
   }

   public MidPointIntegrator() {
      super(3, 64);
   }

   private double stage(int n, double previousStageResult, double min, double diffMaxMin) throws TooManyEvaluationsException {
      long np = 1L << n - 1;
      double sum = (double)0.0F;
      double spacing = diffMaxMin / (double)np;
      double x = min + (double)0.5F * spacing;

      for(long i = 0L; i < np; ++i) {
         sum += this.computeObjectiveValue(x);
         x += spacing;
      }

      return (double)0.5F * (previousStageResult + sum * spacing);
   }

   protected double doIntegrate() throws MathIllegalArgumentException, TooManyEvaluationsException, MaxCountExceededException {
      double min = this.getMin();
      double diff = this.getMax() - min;
      double midPoint = min + (double)0.5F * diff;
      double oldt = diff * this.computeObjectiveValue(midPoint);

      while(true) {
         this.incrementCount();
         int i = this.getIterations();
         double t = this.stage(i, oldt, min, diff);
         if (i >= this.getMinimalIterationCount()) {
            double delta = FastMath.abs(t - oldt);
            double rLimit = this.getRelativeAccuracy() * (FastMath.abs(oldt) + FastMath.abs(t)) * (double)0.5F;
            if (delta <= rLimit || delta <= this.getAbsoluteAccuracy()) {
               return t;
            }
         }

         oldt = t;
      }
   }
}
