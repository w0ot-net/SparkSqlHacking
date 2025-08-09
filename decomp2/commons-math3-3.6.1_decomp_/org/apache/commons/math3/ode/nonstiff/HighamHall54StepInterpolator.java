package org.apache.commons.math3.ode.nonstiff;

import org.apache.commons.math3.ode.sampling.StepInterpolator;

class HighamHall54StepInterpolator extends RungeKuttaStepInterpolator {
   private static final long serialVersionUID = 20111120L;

   public HighamHall54StepInterpolator() {
   }

   HighamHall54StepInterpolator(HighamHall54StepInterpolator interpolator) {
      super(interpolator);
   }

   protected StepInterpolator doCopy() {
      return new HighamHall54StepInterpolator(this);
   }

   protected void computeInterpolatedStateAndDerivatives(double theta, double oneMinusThetaH) {
      double bDot0 = (double)1.0F + theta * ((double)-7.5F + theta * ((double)16.0F - (double)10.0F * theta));
      double bDot2 = theta * ((double)28.6875F + theta * ((double)-91.125F + (double)67.5F * theta));
      double bDot3 = theta * ((double)-44.0F + theta * ((double)152.0F - (double)120.0F * theta));
      double bDot4 = theta * ((double)23.4375F + theta * ((double)-78.125F + (double)62.5F * theta));
      double bDot5 = theta * (double)5.0F / (double)8.0F * ((double)2.0F * theta - (double)1.0F);
      if (this.previousState != null && theta <= (double)0.5F) {
         double hTheta = this.h * theta;
         double b0 = hTheta * ((double)1.0F + theta * ((double)-3.75F + theta * (5.333333333333333 - (double)2.5F * theta)));
         double b2 = hTheta * theta * ((double)14.34375F + theta * ((double)-30.375F + theta * (double)135.0F / (double)8.0F));
         double b3 = hTheta * theta * ((double)-22.0F + theta * (50.666666666666664 + theta * (double)-30.0F));
         double b4 = hTheta * theta * ((double)11.71875F + theta * (-26.041666666666668 + theta * (double)125.0F / (double)8.0F));
         double b5 = hTheta * theta * ((double)-0.3125F + theta * (double)5.0F / (double)12.0F);

         for(int i = 0; i < this.interpolatedState.length; ++i) {
            double yDot0 = this.yDotK[0][i];
            double yDot2 = this.yDotK[2][i];
            double yDot3 = this.yDotK[3][i];
            double yDot4 = this.yDotK[4][i];
            double yDot5 = this.yDotK[5][i];
            this.interpolatedState[i] = this.previousState[i] + b0 * yDot0 + b2 * yDot2 + b3 * yDot3 + b4 * yDot4 + b5 * yDot5;
            this.interpolatedDerivatives[i] = bDot0 * yDot0 + bDot2 * yDot2 + bDot3 * yDot3 + bDot4 * yDot4 + bDot5 * yDot5;
         }
      } else {
         double theta2 = theta * theta;
         double b0 = this.h * (-0.08333333333333333 + theta * ((double)1.0F + theta * ((double)-3.75F + theta * (5.333333333333333 + theta * (double)-5.0F / (double)2.0F))));
         double b2 = this.h * ((double)-0.84375F + theta2 * ((double)14.34375F + theta * ((double)-30.375F + theta * (double)135.0F / (double)8.0F)));
         double b3 = this.h * (1.3333333333333333 + theta2 * ((double)-22.0F + theta * (50.666666666666664 + theta * (double)-30.0F)));
         double b4 = this.h * (-1.3020833333333333 + theta2 * ((double)11.71875F + theta * (-26.041666666666668 + theta * (double)125.0F / (double)8.0F)));
         double b5 = this.h * (-0.10416666666666667 + theta2 * ((double)-0.3125F + theta * (double)5.0F / (double)12.0F));

         for(int i = 0; i < this.interpolatedState.length; ++i) {
            double yDot0 = this.yDotK[0][i];
            double yDot2 = this.yDotK[2][i];
            double yDot3 = this.yDotK[3][i];
            double yDot4 = this.yDotK[4][i];
            double yDot5 = this.yDotK[5][i];
            this.interpolatedState[i] = this.currentState[i] + b0 * yDot0 + b2 * yDot2 + b3 * yDot3 + b4 * yDot4 + b5 * yDot5;
            this.interpolatedDerivatives[i] = bDot0 * yDot0 + bDot2 * yDot2 + bDot3 * yDot3 + bDot4 * yDot4 + bDot5 * yDot5;
         }
      }

   }
}
