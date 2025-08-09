package org.apache.commons.math3.analysis.integration.gauss;

import org.apache.commons.math3.exception.DimensionMismatchException;
import org.apache.commons.math3.util.FastMath;
import org.apache.commons.math3.util.Pair;

public class HermiteRuleFactory extends BaseRuleFactory {
   private static final double SQRT_PI = 1.772453850905516;
   private static final double H0 = 0.7511255444649425;
   private static final double H1 = 1.0622519320271968;

   protected Pair computeRule(int numberOfPoints) throws DimensionMismatchException {
      if (numberOfPoints == 1) {
         return new Pair(new Double[]{(double)0.0F}, new Double[]{1.772453850905516});
      } else {
         int lastNumPoints = numberOfPoints - 1;
         Double[] previousPoints = (Double[])this.getRuleInternal(lastNumPoints).getFirst();
         Double[] points = new Double[numberOfPoints];
         Double[] weights = new Double[numberOfPoints];
         double sqrtTwoTimesLastNumPoints = FastMath.sqrt((double)(2 * lastNumPoints));
         double sqrtTwoTimesNumPoints = FastMath.sqrt((double)(2 * numberOfPoints));
         int iMax = numberOfPoints / 2;

         for(int i = 0; i < iMax; ++i) {
            double a = i == 0 ? -sqrtTwoTimesLastNumPoints : previousPoints[i - 1];
            double b = iMax == 1 ? (double)-0.5F : previousPoints[i];
            double hma = 0.7511255444649425;
            double ha = 1.0622519320271968 * a;
            double hmb = 0.7511255444649425;
            double hb = 1.0622519320271968 * b;

            for(int j = 1; j < numberOfPoints; ++j) {
               double jp1 = (double)(j + 1);
               double s = FastMath.sqrt((double)2.0F / jp1);
               double sm = FastMath.sqrt((double)j / jp1);
               double hpa = s * a * ha - sm * hma;
               double hpb = s * b * hb - sm * hmb;
               hma = ha;
               ha = hpa;
               hmb = hb;
               hb = hpb;
            }

            double c = (double)0.5F * (a + b);
            double hmc = 0.7511255444649425;
            double hc = 1.0622519320271968 * c;
            boolean done = false;

            while(!done) {
               done = b - a <= Math.ulp(c);
               hmc = 0.7511255444649425;
               hc = 1.0622519320271968 * c;

               for(int j = 1; j < numberOfPoints; ++j) {
                  double jp1 = (double)(j + 1);
                  double s = FastMath.sqrt((double)2.0F / jp1);
                  double sm = FastMath.sqrt((double)j / jp1);
                  double hpc = s * c * hc - sm * hmc;
                  hmc = hc;
                  hc = hpc;
               }

               if (!done) {
                  if (ha * hc < (double)0.0F) {
                     b = c;
                  } else {
                     a = c;
                     ha = hc;
                  }

                  c = (double)0.5F * (a + b);
               }
            }

            double d = sqrtTwoTimesNumPoints * hmc;
            double w = (double)2.0F / (d * d);
            points[i] = c;
            weights[i] = w;
            int idx = lastNumPoints - i;
            points[idx] = -c;
            weights[idx] = w;
         }

         if (numberOfPoints % 2 != 0) {
            double hm = 0.7511255444649425;

            for(int j = 1; j < numberOfPoints; j += 2) {
               double jp1 = (double)(j + 1);
               hm = -FastMath.sqrt((double)j / jp1) * hm;
            }

            double d = sqrtTwoTimesNumPoints * hm;
            double w = (double)2.0F / (d * d);
            points[iMax] = (double)0.0F;
            weights[iMax] = w;
         }

         return new Pair(points, weights);
      }
   }
}
