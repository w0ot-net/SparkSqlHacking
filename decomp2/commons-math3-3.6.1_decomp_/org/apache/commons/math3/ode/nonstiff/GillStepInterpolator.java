package org.apache.commons.math3.ode.nonstiff;

import org.apache.commons.math3.ode.sampling.StepInterpolator;
import org.apache.commons.math3.util.FastMath;

class GillStepInterpolator extends RungeKuttaStepInterpolator {
   private static final double ONE_MINUS_INV_SQRT_2 = (double)1.0F - FastMath.sqrt((double)0.5F);
   private static final double ONE_PLUS_INV_SQRT_2 = (double)1.0F + FastMath.sqrt((double)0.5F);
   private static final long serialVersionUID = 20111120L;

   public GillStepInterpolator() {
   }

   GillStepInterpolator(GillStepInterpolator interpolator) {
      super(interpolator);
   }

   protected StepInterpolator doCopy() {
      return new GillStepInterpolator(this);
   }

   protected void computeInterpolatedStateAndDerivatives(double theta, double oneMinusThetaH) {
      double twoTheta = (double)2.0F * theta;
      double fourTheta2 = twoTheta * twoTheta;
      double coeffDot1 = theta * (twoTheta - (double)3.0F) + (double)1.0F;
      double cDot23 = twoTheta * ((double)1.0F - theta);
      double coeffDot2 = cDot23 * ONE_MINUS_INV_SQRT_2;
      double coeffDot3 = cDot23 * ONE_PLUS_INV_SQRT_2;
      double coeffDot4 = theta * (twoTheta - (double)1.0F);
      if (this.previousState != null && theta <= (double)0.5F) {
         double s = theta * this.h / (double)6.0F;
         double c23 = s * ((double)6.0F * theta - fourTheta2);
         double coeff1 = s * ((double)6.0F - (double)9.0F * theta + fourTheta2);
         double coeff2 = c23 * ONE_MINUS_INV_SQRT_2;
         double coeff3 = c23 * ONE_PLUS_INV_SQRT_2;
         double coeff4 = s * ((double)-3.0F * theta + fourTheta2);

         for(int i = 0; i < this.interpolatedState.length; ++i) {
            double yDot1 = this.yDotK[0][i];
            double yDot2 = this.yDotK[1][i];
            double yDot3 = this.yDotK[2][i];
            double yDot4 = this.yDotK[3][i];
            this.interpolatedState[i] = this.previousState[i] + coeff1 * yDot1 + coeff2 * yDot2 + coeff3 * yDot3 + coeff4 * yDot4;
            this.interpolatedDerivatives[i] = coeffDot1 * yDot1 + coeffDot2 * yDot2 + coeffDot3 * yDot3 + coeffDot4 * yDot4;
         }
      } else {
         double s = oneMinusThetaH / (double)6.0F;
         double c23 = s * ((double)2.0F + twoTheta - fourTheta2);
         double coeff1 = s * ((double)1.0F - (double)5.0F * theta + fourTheta2);
         double coeff2 = c23 * ONE_MINUS_INV_SQRT_2;
         double coeff3 = c23 * ONE_PLUS_INV_SQRT_2;
         double coeff4 = s * ((double)1.0F + theta + fourTheta2);

         for(int i = 0; i < this.interpolatedState.length; ++i) {
            double yDot1 = this.yDotK[0][i];
            double yDot2 = this.yDotK[1][i];
            double yDot3 = this.yDotK[2][i];
            double yDot4 = this.yDotK[3][i];
            this.interpolatedState[i] = this.currentState[i] - coeff1 * yDot1 - coeff2 * yDot2 - coeff3 * yDot3 - coeff4 * yDot4;
            this.interpolatedDerivatives[i] = coeffDot1 * yDot1 + coeffDot2 * yDot2 + coeffDot3 * yDot3 + coeffDot4 * yDot4;
         }
      }

   }
}
