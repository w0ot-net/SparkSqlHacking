package org.apache.commons.math3.ode.nonstiff;

import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import org.apache.commons.math3.ode.EquationsMapper;
import org.apache.commons.math3.ode.sampling.AbstractStepInterpolator;
import org.apache.commons.math3.ode.sampling.StepInterpolator;
import org.apache.commons.math3.util.FastMath;

class GraggBulirschStoerStepInterpolator extends AbstractStepInterpolator {
   private static final long serialVersionUID = 20110928L;
   private double[] y0Dot;
   private double[] y1;
   private double[] y1Dot;
   private double[][] yMidDots;
   private double[][] polynomials;
   private double[] errfac;
   private int currentDegree;

   public GraggBulirschStoerStepInterpolator() {
      this.y0Dot = null;
      this.y1 = null;
      this.y1Dot = null;
      this.yMidDots = (double[][])null;
      this.resetTables(-1);
   }

   GraggBulirschStoerStepInterpolator(double[] y, double[] y0Dot, double[] y1, double[] y1Dot, double[][] yMidDots, boolean forward, EquationsMapper primaryMapper, EquationsMapper[] secondaryMappers) {
      super(y, forward, primaryMapper, secondaryMappers);
      this.y0Dot = y0Dot;
      this.y1 = y1;
      this.y1Dot = y1Dot;
      this.yMidDots = yMidDots;
      this.resetTables(yMidDots.length + 4);
   }

   GraggBulirschStoerStepInterpolator(GraggBulirschStoerStepInterpolator interpolator) {
      super(interpolator);
      int dimension = this.currentState.length;
      this.y0Dot = null;
      this.y1 = null;
      this.y1Dot = null;
      this.yMidDots = (double[][])null;
      if (interpolator.polynomials == null) {
         this.polynomials = (double[][])null;
         this.currentDegree = -1;
      } else {
         this.resetTables(interpolator.currentDegree);

         for(int i = 0; i < this.polynomials.length; ++i) {
            this.polynomials[i] = new double[dimension];
            System.arraycopy(interpolator.polynomials[i], 0, this.polynomials[i], 0, dimension);
         }

         this.currentDegree = interpolator.currentDegree;
      }

   }

   private void resetTables(int maxDegree) {
      if (maxDegree < 0) {
         this.polynomials = (double[][])null;
         this.errfac = null;
         this.currentDegree = -1;
      } else {
         double[][] newPols = new double[maxDegree + 1][];
         if (this.polynomials != null) {
            System.arraycopy(this.polynomials, 0, newPols, 0, this.polynomials.length);

            for(int i = this.polynomials.length; i < newPols.length; ++i) {
               newPols[i] = new double[this.currentState.length];
            }
         } else {
            for(int i = 0; i < newPols.length; ++i) {
               newPols[i] = new double[this.currentState.length];
            }
         }

         this.polynomials = newPols;
         if (maxDegree <= 4) {
            this.errfac = null;
         } else {
            this.errfac = new double[maxDegree - 4];

            for(int i = 0; i < this.errfac.length; ++i) {
               int ip5 = i + 5;
               this.errfac[i] = (double)1.0F / (double)(ip5 * ip5);
               double e = (double)0.5F * FastMath.sqrt((double)(i + 1) / (double)ip5);

               for(int j = 0; j <= i; ++j) {
                  double[] var10000 = this.errfac;
                  var10000[i] *= e / (double)(j + 1);
               }
            }
         }

         this.currentDegree = 0;
      }

   }

   protected StepInterpolator doCopy() {
      return new GraggBulirschStoerStepInterpolator(this);
   }

   public void computeCoefficients(int mu, double h) {
      if (this.polynomials == null || this.polynomials.length <= mu + 4) {
         this.resetTables(mu + 4);
      }

      this.currentDegree = mu + 4;

      for(int i = 0; i < this.currentState.length; ++i) {
         double yp0 = h * this.y0Dot[i];
         double yp1 = h * this.y1Dot[i];
         double ydiff = this.y1[i] - this.currentState[i];
         double aspl = ydiff - yp1;
         double bspl = yp0 - ydiff;
         this.polynomials[0][i] = this.currentState[i];
         this.polynomials[1][i] = ydiff;
         this.polynomials[2][i] = aspl;
         this.polynomials[3][i] = bspl;
         if (mu < 0) {
            return;
         }

         double ph0 = (double)0.5F * (this.currentState[i] + this.y1[i]) + (double)0.125F * (aspl + bspl);
         this.polynomials[4][i] = (double)16.0F * (this.yMidDots[0][i] - ph0);
         if (mu > 0) {
            double ph1 = ydiff + (double)0.25F * (aspl - bspl);
            this.polynomials[5][i] = (double)16.0F * (this.yMidDots[1][i] - ph1);
            if (mu > 1) {
               double ph2 = yp1 - yp0;
               this.polynomials[6][i] = (double)16.0F * (this.yMidDots[2][i] - ph2 + this.polynomials[4][i]);
               if (mu > 2) {
                  double ph3 = (double)6.0F * (bspl - aspl);
                  this.polynomials[7][i] = (double)16.0F * (this.yMidDots[3][i] - ph3 + (double)3.0F * this.polynomials[5][i]);

                  for(int j = 4; j <= mu; ++j) {
                     double fac1 = (double)0.5F * (double)j * (double)(j - 1);
                     double fac2 = (double)2.0F * fac1 * (double)(j - 2) * (double)(j - 3);
                     this.polynomials[j + 4][i] = (double)16.0F * (this.yMidDots[j][i] + fac1 * this.polynomials[j + 2][i] - fac2 * this.polynomials[j][i]);
                  }
               }
            }
         }
      }

   }

   public double estimateError(double[] scale) {
      double error = (double)0.0F;
      if (this.currentDegree >= 5) {
         for(int i = 0; i < scale.length; ++i) {
            double e = this.polynomials[this.currentDegree][i] / scale[i];
            error += e * e;
         }

         error = FastMath.sqrt(error / (double)scale.length) * this.errfac[this.currentDegree - 5];
      }

      return error;
   }

   protected void computeInterpolatedStateAndDerivatives(double theta, double oneMinusThetaH) {
      int dimension = this.currentState.length;
      double oneMinusTheta = (double)1.0F - theta;
      double theta05 = theta - (double)0.5F;
      double tOmT = theta * oneMinusTheta;
      double t4 = tOmT * tOmT;
      double t4Dot = (double)2.0F * tOmT * ((double)1.0F - (double)2.0F * theta);
      double dot1 = (double)1.0F / this.h;
      double dot2 = theta * ((double)2.0F - (double)3.0F * theta) / this.h;
      double dot3 = (((double)3.0F * theta - (double)4.0F) * theta + (double)1.0F) / this.h;

      for(int i = 0; i < dimension; ++i) {
         double p0 = this.polynomials[0][i];
         double p1 = this.polynomials[1][i];
         double p2 = this.polynomials[2][i];
         double p3 = this.polynomials[3][i];
         this.interpolatedState[i] = p0 + theta * (p1 + oneMinusTheta * (p2 * theta + p3 * oneMinusTheta));
         this.interpolatedDerivatives[i] = dot1 * p1 + dot2 * p2 + dot3 * p3;
         if (this.currentDegree > 3) {
            double cDot = (double)0.0F;
            double c = this.polynomials[this.currentDegree][i];

            for(int j = this.currentDegree - 1; j > 3; --j) {
               double d = (double)1.0F / (double)(j - 3);
               cDot = d * (theta05 * cDot + c);
               c = this.polynomials[j][i] + c * d * theta05;
            }

            double[] var10000 = this.interpolatedState;
            var10000[i] += t4 * c;
            var10000 = this.interpolatedDerivatives;
            var10000[i] += (t4 * cDot + t4Dot * c) / this.h;
         }
      }

      if (this.h == (double)0.0F) {
         System.arraycopy(this.yMidDots[1], 0, this.interpolatedDerivatives, 0, dimension);
      }

   }

   public void writeExternal(ObjectOutput out) throws IOException {
      int dimension = this.currentState == null ? -1 : this.currentState.length;
      this.writeBaseExternal(out);
      out.writeInt(this.currentDegree);

      for(int k = 0; k <= this.currentDegree; ++k) {
         for(int l = 0; l < dimension; ++l) {
            out.writeDouble(this.polynomials[k][l]);
         }
      }

   }

   public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
      double t = this.readBaseExternal(in);
      int dimension = this.currentState == null ? -1 : this.currentState.length;
      int degree = in.readInt();
      this.resetTables(degree);
      this.currentDegree = degree;

      for(int k = 0; k <= this.currentDegree; ++k) {
         for(int l = 0; l < dimension; ++l) {
            this.polynomials[k][l] = in.readDouble();
         }
      }

      this.setInterpolatedTime(t);
   }
}
