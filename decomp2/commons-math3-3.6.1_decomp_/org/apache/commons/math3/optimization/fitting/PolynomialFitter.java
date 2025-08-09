package org.apache.commons.math3.optimization.fitting;

import org.apache.commons.math3.analysis.polynomials.PolynomialFunction;
import org.apache.commons.math3.optimization.DifferentiableMultivariateVectorOptimizer;

/** @deprecated */
@Deprecated
public class PolynomialFitter extends CurveFitter {
   /** @deprecated */
   @Deprecated
   private final int degree;

   /** @deprecated */
   @Deprecated
   public PolynomialFitter(int degree, DifferentiableMultivariateVectorOptimizer optimizer) {
      super(optimizer);
      this.degree = degree;
   }

   public PolynomialFitter(DifferentiableMultivariateVectorOptimizer optimizer) {
      super(optimizer);
      this.degree = -1;
   }

   /** @deprecated */
   @Deprecated
   public double[] fit() {
      return this.fit(new PolynomialFunction.Parametric(), new double[this.degree + 1]);
   }

   public double[] fit(int maxEval, double[] guess) {
      return this.fit(maxEval, new PolynomialFunction.Parametric(), guess);
   }

   public double[] fit(double[] guess) {
      return this.fit(new PolynomialFunction.Parametric(), guess);
   }
}
