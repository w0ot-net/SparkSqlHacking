package org.apache.commons.math3.analysis.integration.gauss;

import org.apache.commons.math3.analysis.UnivariateFunction;
import org.apache.commons.math3.exception.DimensionMismatchException;
import org.apache.commons.math3.exception.NonMonotonicSequenceException;
import org.apache.commons.math3.util.Pair;

public class SymmetricGaussIntegrator extends GaussIntegrator {
   public SymmetricGaussIntegrator(double[] points, double[] weights) throws NonMonotonicSequenceException, DimensionMismatchException {
      super(points, weights);
   }

   public SymmetricGaussIntegrator(Pair pointsAndWeights) throws NonMonotonicSequenceException {
      this((double[])pointsAndWeights.getFirst(), (double[])pointsAndWeights.getSecond());
   }

   public double integrate(UnivariateFunction f) {
      int ruleLength = this.getNumberOfPoints();
      if (ruleLength == 1) {
         return this.getWeight(0) * f.value((double)0.0F);
      } else {
         int iMax = ruleLength / 2;
         double s = (double)0.0F;
         double c = (double)0.0F;

         for(int i = 0; i < iMax; ++i) {
            double p = this.getPoint(i);
            double w = this.getWeight(i);
            double f1 = f.value(p);
            double f2 = f.value(-p);
            double y = w * (f1 + f2) - c;
            double t = s + y;
            c = t - s - y;
            s = t;
         }

         if (ruleLength % 2 != 0) {
            double w = this.getWeight(iMax);
            double y = w * f.value((double)0.0F) - c;
            double t = s + y;
            s = t;
         }

         return s;
      }
   }
}
