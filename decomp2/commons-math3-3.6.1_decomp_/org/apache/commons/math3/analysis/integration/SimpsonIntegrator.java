package org.apache.commons.math3.analysis.integration;

import org.apache.commons.math3.exception.MaxCountExceededException;
import org.apache.commons.math3.exception.NotStrictlyPositiveException;
import org.apache.commons.math3.exception.NumberIsTooLargeException;
import org.apache.commons.math3.exception.NumberIsTooSmallException;
import org.apache.commons.math3.exception.TooManyEvaluationsException;
import org.apache.commons.math3.util.FastMath;

public class SimpsonIntegrator extends BaseAbstractUnivariateIntegrator {
   public static final int SIMPSON_MAX_ITERATIONS_COUNT = 64;

   public SimpsonIntegrator(double relativeAccuracy, double absoluteAccuracy, int minimalIterationCount, int maximalIterationCount) throws NotStrictlyPositiveException, NumberIsTooSmallException, NumberIsTooLargeException {
      super(relativeAccuracy, absoluteAccuracy, minimalIterationCount, maximalIterationCount);
      if (maximalIterationCount > 64) {
         throw new NumberIsTooLargeException(maximalIterationCount, 64, false);
      }
   }

   public SimpsonIntegrator(int minimalIterationCount, int maximalIterationCount) throws NotStrictlyPositiveException, NumberIsTooSmallException, NumberIsTooLargeException {
      super(minimalIterationCount, maximalIterationCount);
      if (maximalIterationCount > 64) {
         throw new NumberIsTooLargeException(maximalIterationCount, 64, false);
      }
   }

   public SimpsonIntegrator() {
      super(3, 64);
   }

   protected double doIntegrate() throws TooManyEvaluationsException, MaxCountExceededException {
      TrapezoidIntegrator qtrap = new TrapezoidIntegrator();
      if (this.getMinimalIterationCount() == 1) {
         return ((double)4.0F * qtrap.stage(this, 1) - qtrap.stage(this, 0)) / (double)3.0F;
      } else {
         double olds = (double)0.0F;
         double oldt = qtrap.stage(this, 0);

         while(true) {
            double t = qtrap.stage(this, this.getIterations());
            this.incrementCount();
            double s = ((double)4.0F * t - oldt) / (double)3.0F;
            if (this.getIterations() >= this.getMinimalIterationCount()) {
               double delta = FastMath.abs(s - olds);
               double rLimit = this.getRelativeAccuracy() * (FastMath.abs(olds) + FastMath.abs(s)) * (double)0.5F;
               if (delta <= rLimit || delta <= this.getAbsoluteAccuracy()) {
                  return s;
               }
            }

            olds = s;
            oldt = t;
         }
      }
   }
}
