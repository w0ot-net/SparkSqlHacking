package org.apache.commons.math3.ode.nonstiff;

import org.apache.commons.math3.ode.sampling.StepInterpolator;

class ClassicalRungeKuttaStepInterpolator extends RungeKuttaStepInterpolator {
   private static final long serialVersionUID = 20111120L;

   public ClassicalRungeKuttaStepInterpolator() {
   }

   ClassicalRungeKuttaStepInterpolator(ClassicalRungeKuttaStepInterpolator interpolator) {
      super(interpolator);
   }

   protected StepInterpolator doCopy() {
      return new ClassicalRungeKuttaStepInterpolator(this);
   }

   protected void computeInterpolatedStateAndDerivatives(double theta, double oneMinusThetaH) {
      double oneMinusTheta = (double)1.0F - theta;
      double oneMinus2Theta = (double)1.0F - (double)2.0F * theta;
      double coeffDot1 = oneMinusTheta * oneMinus2Theta;
      double coeffDot23 = (double)2.0F * theta * oneMinusTheta;
      double coeffDot4 = -theta * oneMinus2Theta;
      if (this.previousState != null && theta <= (double)0.5F) {
         double fourTheta2 = (double)4.0F * theta * theta;
         double s = theta * this.h / (double)6.0F;
         double coeff1 = s * ((double)6.0F - (double)9.0F * theta + fourTheta2);
         double coeff23 = s * ((double)6.0F * theta - fourTheta2);
         double coeff4 = s * ((double)-3.0F * theta + fourTheta2);

         for(int i = 0; i < this.interpolatedState.length; ++i) {
            double yDot1 = this.yDotK[0][i];
            double yDot23 = this.yDotK[1][i] + this.yDotK[2][i];
            double yDot4 = this.yDotK[3][i];
            this.interpolatedState[i] = this.previousState[i] + coeff1 * yDot1 + coeff23 * yDot23 + coeff4 * yDot4;
            this.interpolatedDerivatives[i] = coeffDot1 * yDot1 + coeffDot23 * yDot23 + coeffDot4 * yDot4;
         }
      } else {
         double fourTheta = (double)4.0F * theta;
         double s = oneMinusThetaH / (double)6.0F;
         double coeff1 = s * ((-fourTheta + (double)5.0F) * theta - (double)1.0F);
         double coeff23 = s * ((fourTheta - (double)2.0F) * theta - (double)2.0F);
         double coeff4 = s * ((-fourTheta - (double)1.0F) * theta - (double)1.0F);

         for(int i = 0; i < this.interpolatedState.length; ++i) {
            double yDot1 = this.yDotK[0][i];
            double yDot23 = this.yDotK[1][i] + this.yDotK[2][i];
            double yDot4 = this.yDotK[3][i];
            this.interpolatedState[i] = this.currentState[i] + coeff1 * yDot1 + coeff23 * yDot23 + coeff4 * yDot4;
            this.interpolatedDerivatives[i] = coeffDot1 * yDot1 + coeffDot23 * yDot23 + coeffDot4 * yDot4;
         }
      }

   }
}
