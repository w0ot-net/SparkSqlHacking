package org.apache.commons.math3.analysis.integration.gauss;

import org.apache.commons.math3.exception.DimensionMismatchException;
import org.apache.commons.math3.util.Pair;

public class LegendreRuleFactory extends BaseRuleFactory {
   protected Pair computeRule(int numberOfPoints) throws DimensionMismatchException {
      if (numberOfPoints == 1) {
         return new Pair(new Double[]{(double)0.0F}, new Double[]{(double)2.0F});
      } else {
         Double[] previousPoints = (Double[])this.getRuleInternal(numberOfPoints - 1).getFirst();
         Double[] points = new Double[numberOfPoints];
         Double[] weights = new Double[numberOfPoints];
         int iMax = numberOfPoints / 2;

         for(int i = 0; i < iMax; ++i) {
            double a = i == 0 ? (double)-1.0F : previousPoints[i - 1];
            double b = iMax == 1 ? (double)1.0F : previousPoints[i];
            double pma = (double)1.0F;
            double pa = a;
            double pmb = (double)1.0F;
            double pb = b;

            for(int j = 1; j < numberOfPoints; ++j) {
               int two_j_p_1 = 2 * j + 1;
               int j_p_1 = j + 1;
               double ppa = ((double)two_j_p_1 * a * pa - (double)j * pma) / (double)j_p_1;
               double ppb = ((double)two_j_p_1 * b * pb - (double)j * pmb) / (double)j_p_1;
               pma = pa;
               pa = ppa;
               pmb = pb;
               pb = ppb;
            }

            double c = (double)0.5F * (a + b);
            double pmc = (double)1.0F;
            double pc = c;
            boolean done = false;

            while(!done) {
               done = b - a <= Math.ulp(c);
               pmc = (double)1.0F;
               pc = c;

               for(int j = 1; j < numberOfPoints; ++j) {
                  double ppc = ((double)(2 * j + 1) * c * pc - (double)j * pmc) / (double)(j + 1);
                  pmc = pc;
                  pc = ppc;
               }

               if (!done) {
                  if (pa * pc <= (double)0.0F) {
                     b = c;
                  } else {
                     a = c;
                     pa = pc;
                  }

                  c = (double)0.5F * (a + b);
               }
            }

            double d = (double)numberOfPoints * (pmc - c * pc);
            double w = (double)2.0F * ((double)1.0F - c * c) / (d * d);
            points[i] = c;
            weights[i] = w;
            int idx = numberOfPoints - i - 1;
            points[idx] = -c;
            weights[idx] = w;
         }

         if (numberOfPoints % 2 != 0) {
            double pmc = (double)1.0F;

            for(int j = 1; j < numberOfPoints; j += 2) {
               pmc = (double)(-j) * pmc / (double)(j + 1);
            }

            double d = (double)numberOfPoints * pmc;
            double w = (double)2.0F / (d * d);
            points[iMax] = (double)0.0F;
            weights[iMax] = w;
         }

         return new Pair(points, weights);
      }
   }
}
