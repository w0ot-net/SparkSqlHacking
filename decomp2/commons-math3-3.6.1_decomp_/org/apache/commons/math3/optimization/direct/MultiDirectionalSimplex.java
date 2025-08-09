package org.apache.commons.math3.optimization.direct;

import java.util.Comparator;
import org.apache.commons.math3.analysis.MultivariateFunction;
import org.apache.commons.math3.optimization.PointValuePair;

/** @deprecated */
@Deprecated
public class MultiDirectionalSimplex extends AbstractSimplex {
   private static final double DEFAULT_KHI = (double)2.0F;
   private static final double DEFAULT_GAMMA = (double)0.5F;
   private final double khi;
   private final double gamma;

   public MultiDirectionalSimplex(int n) {
      this(n, (double)1.0F);
   }

   public MultiDirectionalSimplex(int n, double sideLength) {
      this(n, sideLength, (double)2.0F, (double)0.5F);
   }

   public MultiDirectionalSimplex(int n, double khi, double gamma) {
      this(n, (double)1.0F, khi, gamma);
   }

   public MultiDirectionalSimplex(int n, double sideLength, double khi, double gamma) {
      super(n, sideLength);
      this.khi = khi;
      this.gamma = gamma;
   }

   public MultiDirectionalSimplex(double[] steps) {
      this(steps, (double)2.0F, (double)0.5F);
   }

   public MultiDirectionalSimplex(double[] steps, double khi, double gamma) {
      super(steps);
      this.khi = khi;
      this.gamma = gamma;
   }

   public MultiDirectionalSimplex(double[][] referenceSimplex) {
      this(referenceSimplex, (double)2.0F, (double)0.5F);
   }

   public MultiDirectionalSimplex(double[][] referenceSimplex, double khi, double gamma) {
      super(referenceSimplex);
      this.khi = khi;
      this.gamma = gamma;
   }

   public void iterate(MultivariateFunction evaluationFunction, Comparator comparator) {
      PointValuePair[] original = this.getPoints();
      PointValuePair best = original[0];
      PointValuePair reflected = this.evaluateNewSimplex(evaluationFunction, original, (double)1.0F, comparator);
      if (comparator.compare(reflected, best) < 0) {
         PointValuePair[] reflectedSimplex = this.getPoints();
         PointValuePair expanded = this.evaluateNewSimplex(evaluationFunction, original, this.khi, comparator);
         if (comparator.compare(reflected, expanded) <= 0) {
            this.setPoints(reflectedSimplex);
         }

      } else {
         this.evaluateNewSimplex(evaluationFunction, original, this.gamma, comparator);
      }
   }

   private PointValuePair evaluateNewSimplex(MultivariateFunction evaluationFunction, PointValuePair[] original, double coeff, Comparator comparator) {
      double[] xSmallest = original[0].getPointRef();
      this.setPoint(0, original[0]);
      int dim = this.getDimension();

      for(int i = 1; i < this.getSize(); ++i) {
         double[] xOriginal = original[i].getPointRef();
         double[] xTransformed = new double[dim];

         for(int j = 0; j < dim; ++j) {
            xTransformed[j] = xSmallest[j] + coeff * (xSmallest[j] - xOriginal[j]);
         }

         this.setPoint(i, new PointValuePair(xTransformed, Double.NaN, false));
      }

      this.evaluate(evaluationFunction, comparator);
      return this.getPoint(0);
   }
}
