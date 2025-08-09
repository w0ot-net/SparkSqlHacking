package org.apache.commons.math3.util;

import org.apache.commons.math3.exception.ConvergenceException;
import org.apache.commons.math3.exception.MaxCountExceededException;
import org.apache.commons.math3.exception.util.LocalizedFormats;

public abstract class ContinuedFraction {
   private static final double DEFAULT_EPSILON = 1.0E-8;

   protected ContinuedFraction() {
   }

   protected abstract double getA(int var1, double var2);

   protected abstract double getB(int var1, double var2);

   public double evaluate(double x) throws ConvergenceException {
      return this.evaluate(x, 1.0E-8, Integer.MAX_VALUE);
   }

   public double evaluate(double x, double epsilon) throws ConvergenceException {
      return this.evaluate(x, epsilon, Integer.MAX_VALUE);
   }

   public double evaluate(double x, int maxIterations) throws ConvergenceException, MaxCountExceededException {
      return this.evaluate(x, 1.0E-8, maxIterations);
   }

   public double evaluate(double x, double epsilon, int maxIterations) throws ConvergenceException, MaxCountExceededException {
      double small = 1.0E-50;
      double hPrev = this.getA(0, x);
      if (Precision.equals(hPrev, (double)0.0F, 1.0E-50)) {
         hPrev = 1.0E-50;
      }

      int n = 1;
      double dPrev = (double)0.0F;
      double cPrev = hPrev;
      double hN = hPrev;

      while(true) {
         if (n < maxIterations) {
            double a = this.getA(n, x);
            double b = this.getB(n, x);
            double dN = a + b * dPrev;
            if (Precision.equals(dN, (double)0.0F, 1.0E-50)) {
               dN = 1.0E-50;
            }

            double cN = a + b / cPrev;
            if (Precision.equals(cN, (double)0.0F, 1.0E-50)) {
               cN = 1.0E-50;
            }

            dN = (double)1.0F / dN;
            double deltaN = cN * dN;
            hN = hPrev * deltaN;
            if (Double.isInfinite(hN)) {
               throw new ConvergenceException(LocalizedFormats.CONTINUED_FRACTION_INFINITY_DIVERGENCE, new Object[]{x});
            }

            if (Double.isNaN(hN)) {
               throw new ConvergenceException(LocalizedFormats.CONTINUED_FRACTION_NAN_DIVERGENCE, new Object[]{x});
            }

            if (!(FastMath.abs(deltaN - (double)1.0F) < epsilon)) {
               dPrev = dN;
               cPrev = cN;
               hPrev = hN;
               ++n;
               continue;
            }
         }

         if (n >= maxIterations) {
            throw new MaxCountExceededException(LocalizedFormats.NON_CONVERGENT_CONTINUED_FRACTION, maxIterations, new Object[]{x});
         }

         return hN;
      }
   }
}
