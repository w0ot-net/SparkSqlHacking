package org.apache.commons.math3.optimization.direct;

import java.util.Comparator;
import org.apache.commons.math3.analysis.MultivariateFunction;
import org.apache.commons.math3.optimization.PointValuePair;

/** @deprecated */
@Deprecated
public class NelderMeadSimplex extends AbstractSimplex {
   private static final double DEFAULT_RHO = (double)1.0F;
   private static final double DEFAULT_KHI = (double)2.0F;
   private static final double DEFAULT_GAMMA = (double)0.5F;
   private static final double DEFAULT_SIGMA = (double)0.5F;
   private final double rho;
   private final double khi;
   private final double gamma;
   private final double sigma;

   public NelderMeadSimplex(int n) {
      this(n, (double)1.0F);
   }

   public NelderMeadSimplex(int n, double sideLength) {
      this(n, sideLength, (double)1.0F, (double)2.0F, (double)0.5F, (double)0.5F);
   }

   public NelderMeadSimplex(int n, double sideLength, double rho, double khi, double gamma, double sigma) {
      super(n, sideLength);
      this.rho = rho;
      this.khi = khi;
      this.gamma = gamma;
      this.sigma = sigma;
   }

   public NelderMeadSimplex(int n, double rho, double khi, double gamma, double sigma) {
      this(n, (double)1.0F, rho, khi, gamma, sigma);
   }

   public NelderMeadSimplex(double[] steps) {
      this(steps, (double)1.0F, (double)2.0F, (double)0.5F, (double)0.5F);
   }

   public NelderMeadSimplex(double[] steps, double rho, double khi, double gamma, double sigma) {
      super(steps);
      this.rho = rho;
      this.khi = khi;
      this.gamma = gamma;
      this.sigma = sigma;
   }

   public NelderMeadSimplex(double[][] referenceSimplex) {
      this(referenceSimplex, (double)1.0F, (double)2.0F, (double)0.5F, (double)0.5F);
   }

   public NelderMeadSimplex(double[][] referenceSimplex, double rho, double khi, double gamma, double sigma) {
      super(referenceSimplex);
      this.rho = rho;
      this.khi = khi;
      this.gamma = gamma;
      this.sigma = sigma;
   }

   public void iterate(MultivariateFunction evaluationFunction, Comparator comparator) {
      int n = this.getDimension();
      PointValuePair best = this.getPoint(0);
      PointValuePair secondBest = this.getPoint(n - 1);
      PointValuePair worst = this.getPoint(n);
      double[] xWorst = worst.getPointRef();
      double[] centroid = new double[n];

      for(int i = 0; i < n; ++i) {
         double[] x = this.getPoint(i).getPointRef();

         for(int j = 0; j < n; ++j) {
            centroid[j] += x[j];
         }
      }

      double scaling = (double)1.0F / (double)n;

      for(int j = 0; j < n; ++j) {
         centroid[j] *= scaling;
      }

      double[] xR = new double[n];

      for(int j = 0; j < n; ++j) {
         xR[j] = centroid[j] + this.rho * (centroid[j] - xWorst[j]);
      }

      PointValuePair reflected = new PointValuePair(xR, evaluationFunction.value(xR), false);
      if (comparator.compare(best, reflected) <= 0 && comparator.compare(reflected, secondBest) < 0) {
         this.replaceWorstPoint(reflected, comparator);
      } else if (comparator.compare(reflected, best) < 0) {
         double[] xE = new double[n];

         for(int j = 0; j < n; ++j) {
            xE[j] = centroid[j] + this.khi * (xR[j] - centroid[j]);
         }

         PointValuePair expanded = new PointValuePair(xE, evaluationFunction.value(xE), false);
         if (comparator.compare(expanded, reflected) < 0) {
            this.replaceWorstPoint(expanded, comparator);
         } else {
            this.replaceWorstPoint(reflected, comparator);
         }
      } else {
         if (comparator.compare(reflected, worst) < 0) {
            double[] xC = new double[n];

            for(int j = 0; j < n; ++j) {
               xC[j] = centroid[j] + this.gamma * (xR[j] - centroid[j]);
            }

            PointValuePair outContracted = new PointValuePair(xC, evaluationFunction.value(xC), false);
            if (comparator.compare(outContracted, reflected) <= 0) {
               this.replaceWorstPoint(outContracted, comparator);
               return;
            }
         } else {
            double[] xC = new double[n];

            for(int j = 0; j < n; ++j) {
               xC[j] = centroid[j] - this.gamma * (centroid[j] - xWorst[j]);
            }

            PointValuePair inContracted = new PointValuePair(xC, evaluationFunction.value(xC), false);
            if (comparator.compare(inContracted, worst) < 0) {
               this.replaceWorstPoint(inContracted, comparator);
               return;
            }
         }

         double[] xSmallest = this.getPoint(0).getPointRef();

         for(int i = 1; i <= n; ++i) {
            double[] x = this.getPoint(i).getPoint();

            for(int j = 0; j < n; ++j) {
               x[j] = xSmallest[j] + this.sigma * (x[j] - xSmallest[j]);
            }

            this.setPoint(i, new PointValuePair(x, Double.NaN, false));
         }

         this.evaluate(evaluationFunction, comparator);
      }

   }
}
