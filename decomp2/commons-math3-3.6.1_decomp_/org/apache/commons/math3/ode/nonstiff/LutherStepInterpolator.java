package org.apache.commons.math3.ode.nonstiff;

import org.apache.commons.math3.ode.sampling.StepInterpolator;
import org.apache.commons.math3.util.FastMath;

class LutherStepInterpolator extends RungeKuttaStepInterpolator {
   private static final long serialVersionUID = 20140416L;
   private static final double Q = FastMath.sqrt((double)21.0F);

   public LutherStepInterpolator() {
   }

   LutherStepInterpolator(LutherStepInterpolator interpolator) {
      super(interpolator);
   }

   protected StepInterpolator doCopy() {
      return new LutherStepInterpolator(this);
   }

   protected void computeInterpolatedStateAndDerivatives(double theta, double oneMinusThetaH) {
      double coeffDot1 = (double)1.0F + theta * (-10.8 + theta * ((double)36.0F + theta * ((double)-47.0F + theta * (double)21.0F)));
      double coeffDot2 = (double)0.0F;
      double coeffDot3 = theta * (-13.866666666666667 + theta * (106.66666666666667 + theta * (-202.66666666666666 + theta * (double)112.0F)));
      double coeffDot4 = theta * (12.96 + theta * (-97.2 + theta * (194.4 + theta * (double)-567.0F / (double)5.0F)));
      double coeffDot5 = theta * (((double)833.0F + (double)343.0F * Q) / (double)150.0F + theta * (((double)-637.0F - (double)357.0F * Q) / (double)30.0F + theta * (((double)392.0F + (double)287.0F * Q) / (double)15.0F + theta * ((double)-49.0F - (double)49.0F * Q) / (double)5.0F)));
      double coeffDot6 = theta * (((double)833.0F - (double)343.0F * Q) / (double)150.0F + theta * (((double)-637.0F + (double)357.0F * Q) / (double)30.0F + theta * (((double)392.0F - (double)287.0F * Q) / (double)15.0F + theta * ((double)-49.0F + (double)49.0F * Q) / (double)5.0F)));
      double coeffDot7 = theta * (0.6 + theta * ((double)-3.0F + theta * (double)3.0F));
      if (this.previousState != null && theta <= (double)0.5F) {
         double coeff1 = (double)1.0F + theta * (-5.4 + theta * ((double)12.0F + theta * ((double)-11.75F + theta * (double)21.0F / (double)5.0F)));
         double coeff2 = (double)0.0F;
         double coeff3 = theta * (-6.933333333333334 + theta * (35.55555555555556 + theta * (-50.666666666666664 + theta * (double)112.0F / (double)5.0F)));
         double coeff4 = theta * (6.48 + theta * (-32.4 + theta * (48.6 + theta * (double)-567.0F / (double)25.0F)));
         double coeff5 = theta * (((double)833.0F + (double)343.0F * Q) / (double)300.0F + theta * (((double)-637.0F - (double)357.0F * Q) / (double)90.0F + theta * (((double)392.0F + (double)287.0F * Q) / (double)60.0F + theta * ((double)-49.0F - (double)49.0F * Q) / (double)25.0F)));
         double coeff6 = theta * (((double)833.0F - (double)343.0F * Q) / (double)300.0F + theta * (((double)-637.0F + (double)357.0F * Q) / (double)90.0F + theta * (((double)392.0F - (double)287.0F * Q) / (double)60.0F + theta * ((double)-49.0F + (double)49.0F * Q) / (double)25.0F)));
         double coeff7 = theta * (0.3 + theta * ((double)-1.0F + theta * (double)0.75F));

         for(int i = 0; i < this.interpolatedState.length; ++i) {
            double yDot1 = this.yDotK[0][i];
            double yDot2 = this.yDotK[1][i];
            double yDot3 = this.yDotK[2][i];
            double yDot4 = this.yDotK[3][i];
            double yDot5 = this.yDotK[4][i];
            double yDot6 = this.yDotK[5][i];
            double yDot7 = this.yDotK[6][i];
            this.interpolatedState[i] = this.previousState[i] + theta * this.h * (coeff1 * yDot1 + (double)0.0F * yDot2 + coeff3 * yDot3 + coeff4 * yDot4 + coeff5 * yDot5 + coeff6 * yDot6 + coeff7 * yDot7);
            this.interpolatedDerivatives[i] = coeffDot1 * yDot1 + (double)0.0F * yDot2 + coeffDot3 * yDot3 + coeffDot4 * yDot4 + coeffDot5 * yDot5 + coeffDot6 * yDot6 + coeffDot7 * yDot7;
         }
      } else {
         double coeff1 = -0.05 + theta * (0.95 + theta * (-4.45 + theta * (7.55 + theta * (double)-21.0F / (double)5.0F)));
         double coeff2 = (double)0.0F;
         double coeff3 = -0.35555555555555557 + theta * (-0.35555555555555557 + theta * (-7.288888888888889 + theta * (28.266666666666666 + theta * (double)-112.0F / (double)5.0F)));
         double coeff4 = theta * theta * (6.48 + theta * (-25.92 + theta * (double)567.0F / (double)25.0F));
         double coeff5 = -0.2722222222222222 + theta * (-0.2722222222222222 + theta * (((double)2254.0F + (double)1029.0F * Q) / (double)900.0F + theta * (((double)-1372.0F - (double)847.0F * Q) / (double)300.0F + theta * ((double)49.0F + (double)49.0F * Q) / (double)25.0F)));
         double coeff6 = -0.2722222222222222 + theta * (-0.2722222222222222 + theta * (((double)2254.0F - (double)1029.0F * Q) / (double)900.0F + theta * (((double)-1372.0F + (double)847.0F * Q) / (double)300.0F + theta * ((double)49.0F - (double)49.0F * Q) / (double)25.0F)));
         double coeff7 = -0.05 + theta * (-0.05 + theta * ((double)0.25F + theta * (double)-0.75F));

         for(int i = 0; i < this.interpolatedState.length; ++i) {
            double yDot1 = this.yDotK[0][i];
            double yDot2 = this.yDotK[1][i];
            double yDot3 = this.yDotK[2][i];
            double yDot4 = this.yDotK[3][i];
            double yDot5 = this.yDotK[4][i];
            double yDot6 = this.yDotK[5][i];
            double yDot7 = this.yDotK[6][i];
            this.interpolatedState[i] = this.currentState[i] + oneMinusThetaH * (coeff1 * yDot1 + (double)0.0F * yDot2 + coeff3 * yDot3 + coeff4 * yDot4 + coeff5 * yDot5 + coeff6 * yDot6 + coeff7 * yDot7);
            this.interpolatedDerivatives[i] = coeffDot1 * yDot1 + (double)0.0F * yDot2 + coeffDot3 * yDot3 + coeffDot4 * yDot4 + coeffDot5 * yDot5 + coeffDot6 * yDot6 + coeffDot7 * yDot7;
         }
      }

   }
}
