package org.apache.commons.math3.analysis.integration;

import org.apache.commons.math3.exception.MaxCountExceededException;
import org.apache.commons.math3.exception.NotStrictlyPositiveException;
import org.apache.commons.math3.exception.NumberIsTooLargeException;
import org.apache.commons.math3.exception.NumberIsTooSmallException;
import org.apache.commons.math3.exception.TooManyEvaluationsException;
import org.apache.commons.math3.util.FastMath;

public class RombergIntegrator extends BaseAbstractUnivariateIntegrator {
   public static final int ROMBERG_MAX_ITERATIONS_COUNT = 32;

   public RombergIntegrator(double relativeAccuracy, double absoluteAccuracy, int minimalIterationCount, int maximalIterationCount) throws NotStrictlyPositiveException, NumberIsTooSmallException, NumberIsTooLargeException {
      super(relativeAccuracy, absoluteAccuracy, minimalIterationCount, maximalIterationCount);
      if (maximalIterationCount > 32) {
         throw new NumberIsTooLargeException(maximalIterationCount, 32, false);
      }
   }

   public RombergIntegrator(int minimalIterationCount, int maximalIterationCount) throws NotStrictlyPositiveException, NumberIsTooSmallException, NumberIsTooLargeException {
      super(minimalIterationCount, maximalIterationCount);
      if (maximalIterationCount > 32) {
         throw new NumberIsTooLargeException(maximalIterationCount, 32, false);
      }
   }

   public RombergIntegrator() {
      super(3, 32);
   }

   protected double doIntegrate() throws TooManyEvaluationsException, MaxCountExceededException {
      int m = this.getMaximalIterationCount() + 1;
      double[] previousRow = new double[m];
      double[] currentRow = new double[m];
      TrapezoidIntegrator qtrap = new TrapezoidIntegrator();
      currentRow[0] = qtrap.stage(this, 0);
      this.incrementCount();
      double olds = currentRow[0];

      while(true) {
         int i = this.getIterations();
         double[] tmpRow = previousRow;
         previousRow = currentRow;
         currentRow = tmpRow;
         tmpRow[0] = qtrap.stage(this, i);
         this.incrementCount();

         for(int j = 1; j <= i; ++j) {
            double r = (double)((1L << 2 * j) - 1L);
            double tIJm1 = currentRow[j - 1];
            currentRow[j] = tIJm1 + (tIJm1 - previousRow[j - 1]) / r;
         }

         double s = currentRow[i];
         if (i >= this.getMinimalIterationCount()) {
            double delta = FastMath.abs(s - olds);
            double rLimit = this.getRelativeAccuracy() * (FastMath.abs(olds) + FastMath.abs(s)) * (double)0.5F;
            if (delta <= rLimit || delta <= this.getAbsoluteAccuracy()) {
               return s;
            }
         }

         olds = s;
      }
   }
}
