package org.apache.commons.math3.ode.nonstiff;

import org.apache.commons.math3.ode.sampling.StepInterpolator;

class ThreeEighthesStepInterpolator extends RungeKuttaStepInterpolator {
   private static final long serialVersionUID = 20111120L;

   public ThreeEighthesStepInterpolator() {
   }

   ThreeEighthesStepInterpolator(ThreeEighthesStepInterpolator interpolator) {
      super(interpolator);
   }

   protected StepInterpolator doCopy() {
      return new ThreeEighthesStepInterpolator(this);
   }

   protected void computeInterpolatedStateAndDerivatives(double theta, double oneMinusThetaH) {
      double coeffDot3 = (double)0.75F * theta;
      double coeffDot1 = coeffDot3 * ((double)4.0F * theta - (double)5.0F) + (double)1.0F;
      double coeffDot2 = coeffDot3 * ((double)5.0F - (double)6.0F * theta);
      double coeffDot4 = coeffDot3 * ((double)2.0F * theta - (double)1.0F);
      if (this.previousState != null && theta <= (double)0.5F) {
         double s = theta * this.h / (double)8.0F;
         double fourTheta2 = (double)4.0F * theta * theta;
         double coeff1 = s * ((double)8.0F - (double)15.0F * theta + (double)2.0F * fourTheta2);
         double coeff2 = (double)3.0F * s * ((double)5.0F * theta - fourTheta2);
         double coeff3 = (double)3.0F * s * theta;
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
         double s = oneMinusThetaH / (double)8.0F;
         double fourTheta2 = (double)4.0F * theta * theta;
         double coeff1 = s * ((double)1.0F - (double)7.0F * theta + (double)2.0F * fourTheta2);
         double coeff2 = (double)3.0F * s * ((double)1.0F + theta - fourTheta2);
         double coeff3 = (double)3.0F * s * ((double)1.0F + theta);
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
