package org.apache.commons.math3.analysis.differentiation;

import java.io.Serializable;
import org.apache.commons.math3.analysis.UnivariateFunction;
import org.apache.commons.math3.analysis.UnivariateMatrixFunction;
import org.apache.commons.math3.analysis.UnivariateVectorFunction;
import org.apache.commons.math3.exception.MathIllegalArgumentException;
import org.apache.commons.math3.exception.NotPositiveException;
import org.apache.commons.math3.exception.NumberIsTooLargeException;
import org.apache.commons.math3.exception.NumberIsTooSmallException;
import org.apache.commons.math3.util.FastMath;

public class FiniteDifferencesDifferentiator implements UnivariateFunctionDifferentiator, UnivariateVectorFunctionDifferentiator, UnivariateMatrixFunctionDifferentiator, Serializable {
   private static final long serialVersionUID = 20120917L;
   private final int nbPoints;
   private final double stepSize;
   private final double halfSampleSpan;
   private final double tMin;
   private final double tMax;

   public FiniteDifferencesDifferentiator(int nbPoints, double stepSize) throws NotPositiveException, NumberIsTooSmallException {
      this(nbPoints, stepSize, Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY);
   }

   public FiniteDifferencesDifferentiator(int nbPoints, double stepSize, double tLower, double tUpper) throws NotPositiveException, NumberIsTooSmallException, NumberIsTooLargeException {
      if (nbPoints <= 1) {
         throw new NumberIsTooSmallException(stepSize, 1, false);
      } else {
         this.nbPoints = nbPoints;
         if (stepSize <= (double)0.0F) {
            throw new NotPositiveException(stepSize);
         } else {
            this.stepSize = stepSize;
            this.halfSampleSpan = (double)0.5F * stepSize * (double)(nbPoints - 1);
            if ((double)2.0F * this.halfSampleSpan >= tUpper - tLower) {
               throw new NumberIsTooLargeException((double)2.0F * this.halfSampleSpan, tUpper - tLower, false);
            } else {
               double safety = FastMath.ulp(this.halfSampleSpan);
               this.tMin = tLower + this.halfSampleSpan + safety;
               this.tMax = tUpper - this.halfSampleSpan - safety;
            }
         }
      }
   }

   public int getNbPoints() {
      return this.nbPoints;
   }

   public double getStepSize() {
      return this.stepSize;
   }

   private DerivativeStructure evaluate(DerivativeStructure t, double t0, double[] y) throws NumberIsTooLargeException {
      double[] top = new double[this.nbPoints];
      double[] bottom = new double[this.nbPoints];

      for(int i = 0; i < this.nbPoints; ++i) {
         bottom[i] = y[i];

         for(int j = 1; j <= i; ++j) {
            bottom[i - j] = (bottom[i - j + 1] - bottom[i - j]) / ((double)j * this.stepSize);
         }

         top[i] = bottom[0];
      }

      int order = t.getOrder();
      int parameters = t.getFreeParameters();
      double[] derivatives = t.getAllDerivatives();
      double dt0 = t.getValue() - t0;
      DerivativeStructure interpolation = new DerivativeStructure(parameters, order, (double)0.0F);
      DerivativeStructure monomial = null;

      for(int i = 0; i < this.nbPoints; ++i) {
         if (i == 0) {
            monomial = new DerivativeStructure(parameters, order, (double)1.0F);
         } else {
            derivatives[0] = dt0 - (double)(i - 1) * this.stepSize;
            DerivativeStructure deltaX = new DerivativeStructure(parameters, order, derivatives);
            monomial = monomial.multiply(deltaX);
         }

         interpolation = interpolation.add(monomial.multiply(top[i]));
      }

      return interpolation;
   }

   public UnivariateDifferentiableFunction differentiate(final UnivariateFunction function) {
      return new UnivariateDifferentiableFunction() {
         public double value(double x) throws MathIllegalArgumentException {
            return function.value(x);
         }

         public DerivativeStructure value(DerivativeStructure t) throws MathIllegalArgumentException {
            if (t.getOrder() >= FiniteDifferencesDifferentiator.this.nbPoints) {
               throw new NumberIsTooLargeException(t.getOrder(), FiniteDifferencesDifferentiator.this.nbPoints, false);
            } else {
               double t0 = FastMath.max(FastMath.min(t.getValue(), FiniteDifferencesDifferentiator.this.tMax), FiniteDifferencesDifferentiator.this.tMin) - FiniteDifferencesDifferentiator.this.halfSampleSpan;
               double[] y = new double[FiniteDifferencesDifferentiator.this.nbPoints];

               for(int i = 0; i < FiniteDifferencesDifferentiator.this.nbPoints; ++i) {
                  y[i] = function.value(t0 + (double)i * FiniteDifferencesDifferentiator.this.stepSize);
               }

               return FiniteDifferencesDifferentiator.this.evaluate(t, t0, y);
            }
         }
      };
   }

   public UnivariateDifferentiableVectorFunction differentiate(final UnivariateVectorFunction function) {
      return new UnivariateDifferentiableVectorFunction() {
         public double[] value(double x) throws MathIllegalArgumentException {
            return function.value(x);
         }

         public DerivativeStructure[] value(DerivativeStructure t) throws MathIllegalArgumentException {
            if (t.getOrder() >= FiniteDifferencesDifferentiator.this.nbPoints) {
               throw new NumberIsTooLargeException(t.getOrder(), FiniteDifferencesDifferentiator.this.nbPoints, false);
            } else {
               double t0 = FastMath.max(FastMath.min(t.getValue(), FiniteDifferencesDifferentiator.this.tMax), FiniteDifferencesDifferentiator.this.tMin) - FiniteDifferencesDifferentiator.this.halfSampleSpan;
               double[][] y = (double[][])null;

               for(int i = 0; i < FiniteDifferencesDifferentiator.this.nbPoints; ++i) {
                  double[] v = function.value(t0 + (double)i * FiniteDifferencesDifferentiator.this.stepSize);
                  if (i == 0) {
                     y = new double[v.length][FiniteDifferencesDifferentiator.this.nbPoints];
                  }

                  for(int j = 0; j < v.length; ++j) {
                     y[j][i] = v[j];
                  }
               }

               DerivativeStructure[] value = new DerivativeStructure[y.length];

               for(int j = 0; j < value.length; ++j) {
                  value[j] = FiniteDifferencesDifferentiator.this.evaluate(t, t0, y[j]);
               }

               return value;
            }
         }
      };
   }

   public UnivariateDifferentiableMatrixFunction differentiate(final UnivariateMatrixFunction function) {
      return new UnivariateDifferentiableMatrixFunction() {
         public double[][] value(double x) throws MathIllegalArgumentException {
            return function.value(x);
         }

         public DerivativeStructure[][] value(DerivativeStructure t) throws MathIllegalArgumentException {
            if (t.getOrder() >= FiniteDifferencesDifferentiator.this.nbPoints) {
               throw new NumberIsTooLargeException(t.getOrder(), FiniteDifferencesDifferentiator.this.nbPoints, false);
            } else {
               double t0 = FastMath.max(FastMath.min(t.getValue(), FiniteDifferencesDifferentiator.this.tMax), FiniteDifferencesDifferentiator.this.tMin) - FiniteDifferencesDifferentiator.this.halfSampleSpan;
               double[][][] y = (double[][][])null;

               for(int i = 0; i < FiniteDifferencesDifferentiator.this.nbPoints; ++i) {
                  double[][] v = function.value(t0 + (double)i * FiniteDifferencesDifferentiator.this.stepSize);
                  if (i == 0) {
                     y = new double[v.length][v[0].length][FiniteDifferencesDifferentiator.this.nbPoints];
                  }

                  for(int j = 0; j < v.length; ++j) {
                     for(int k = 0; k < v[j].length; ++k) {
                        y[j][k][i] = v[j][k];
                     }
                  }
               }

               DerivativeStructure[][] value = new DerivativeStructure[y.length][y[0].length];

               for(int j = 0; j < value.length; ++j) {
                  for(int k = 0; k < y[j].length; ++k) {
                     value[j][k] = FiniteDifferencesDifferentiator.this.evaluate(t, t0, y[j][k]);
                  }
               }

               return value;
            }
         }
      };
   }
}
