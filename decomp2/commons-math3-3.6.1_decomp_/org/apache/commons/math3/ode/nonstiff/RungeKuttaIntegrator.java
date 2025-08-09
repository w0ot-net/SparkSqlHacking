package org.apache.commons.math3.ode.nonstiff;

import org.apache.commons.math3.exception.DimensionMismatchException;
import org.apache.commons.math3.exception.MaxCountExceededException;
import org.apache.commons.math3.exception.NoBracketingException;
import org.apache.commons.math3.exception.NumberIsTooSmallException;
import org.apache.commons.math3.ode.AbstractIntegrator;
import org.apache.commons.math3.ode.ExpandableStatefulODE;
import org.apache.commons.math3.ode.FirstOrderDifferentialEquations;
import org.apache.commons.math3.util.FastMath;

public abstract class RungeKuttaIntegrator extends AbstractIntegrator {
   private final double[] c;
   private final double[][] a;
   private final double[] b;
   private final RungeKuttaStepInterpolator prototype;
   private final double step;

   protected RungeKuttaIntegrator(String name, double[] c, double[][] a, double[] b, RungeKuttaStepInterpolator prototype, double step) {
      super(name);
      this.c = c;
      this.a = a;
      this.b = b;
      this.prototype = prototype;
      this.step = FastMath.abs(step);
   }

   public void integrate(ExpandableStatefulODE equations, double t) throws NumberIsTooSmallException, DimensionMismatchException, MaxCountExceededException, NoBracketingException {
      this.sanityChecks(equations, t);
      this.setEquations(equations);
      boolean forward = t > equations.getTime();
      double[] y0 = equations.getCompleteState();
      double[] y = (double[])(([D)y0).clone();
      int stages = this.c.length + 1;
      double[][] yDotK = new double[stages][];

      for(int i = 0; i < stages; ++i) {
         yDotK[i] = new double[y0.length];
      }

      double[] yTmp = (double[])(([D)y0).clone();
      double[] yDotTmp = new double[y0.length];
      RungeKuttaStepInterpolator interpolator = (RungeKuttaStepInterpolator)this.prototype.copy();
      interpolator.reinitialize(this, yTmp, yDotK, forward, equations.getPrimaryMapper(), equations.getSecondaryMappers());
      interpolator.storeTime(equations.getTime());
      this.stepStart = equations.getTime();
      if (forward) {
         if (this.stepStart + this.step >= t) {
            this.stepSize = t - this.stepStart;
         } else {
            this.stepSize = this.step;
         }
      } else if (this.stepStart - this.step <= t) {
         this.stepSize = t - this.stepStart;
      } else {
         this.stepSize = -this.step;
      }

      this.initIntegration(equations.getTime(), y0, t);
      this.isLastStep = false;

      do {
         interpolator.shift();
         this.computeDerivatives(this.stepStart, y, yDotK[0]);

         for(int k = 1; k < stages; ++k) {
            for(int j = 0; j < y0.length; ++j) {
               double sum = this.a[k - 1][0] * yDotK[0][j];

               for(int l = 1; l < k; ++l) {
                  sum += this.a[k - 1][l] * yDotK[l][j];
               }

               yTmp[j] = y[j] + this.stepSize * sum;
            }

            this.computeDerivatives(this.stepStart + this.c[k - 1] * this.stepSize, yTmp, yDotK[k]);
         }

         for(int j = 0; j < y0.length; ++j) {
            double sum = this.b[0] * yDotK[0][j];

            for(int l = 1; l < stages; ++l) {
               sum += this.b[l] * yDotK[l][j];
            }

            yTmp[j] = y[j] + this.stepSize * sum;
         }

         interpolator.storeTime(this.stepStart + this.stepSize);
         System.arraycopy(yTmp, 0, y, 0, y0.length);
         System.arraycopy(yDotK[stages - 1], 0, yDotTmp, 0, y0.length);
         this.stepStart = this.acceptStep(interpolator, y, yDotTmp, t);
         if (!this.isLastStep) {
            interpolator.storeTime(this.stepStart);
            double nextT = this.stepStart + this.stepSize;
            boolean nextIsLast = forward ? nextT >= t : nextT <= t;
            if (nextIsLast) {
               this.stepSize = t - this.stepStart;
            }
         }
      } while(!this.isLastStep);

      equations.setTime(this.stepStart);
      equations.setCompleteState(y);
      this.stepStart = Double.NaN;
      this.stepSize = Double.NaN;
   }

   public double[] singleStep(FirstOrderDifferentialEquations equations, double t0, double[] y0, double t) {
      double[] y = (double[])(([D)y0).clone();
      int stages = this.c.length + 1;
      double[][] yDotK = new double[stages][];

      for(int i = 0; i < stages; ++i) {
         yDotK[i] = new double[y0.length];
      }

      double[] yTmp = (double[])(([D)y0).clone();
      double h = t - t0;
      equations.computeDerivatives(t0, y, yDotK[0]);

      for(int k = 1; k < stages; ++k) {
         for(int j = 0; j < y0.length; ++j) {
            double sum = this.a[k - 1][0] * yDotK[0][j];

            for(int l = 1; l < k; ++l) {
               sum += this.a[k - 1][l] * yDotK[l][j];
            }

            yTmp[j] = y[j] + h * sum;
         }

         equations.computeDerivatives(t0 + this.c[k - 1] * h, yTmp, yDotK[k]);
      }

      for(int j = 0; j < y0.length; ++j) {
         double sum = this.b[0] * yDotK[0][j];

         for(int l = 1; l < stages; ++l) {
            sum += this.b[l] * yDotK[l][j];
         }

         y[j] += h * sum;
      }

      return y;
   }
}
