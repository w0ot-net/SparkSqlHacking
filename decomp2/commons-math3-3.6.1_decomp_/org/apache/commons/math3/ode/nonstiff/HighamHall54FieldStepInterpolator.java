package org.apache.commons.math3.ode.nonstiff;

import org.apache.commons.math3.Field;
import org.apache.commons.math3.RealFieldElement;
import org.apache.commons.math3.ode.FieldEquationsMapper;
import org.apache.commons.math3.ode.FieldODEStateAndDerivative;

class HighamHall54FieldStepInterpolator extends RungeKuttaFieldStepInterpolator {
   HighamHall54FieldStepInterpolator(Field field, boolean forward, RealFieldElement[][] yDotK, FieldODEStateAndDerivative globalPreviousState, FieldODEStateAndDerivative globalCurrentState, FieldODEStateAndDerivative softPreviousState, FieldODEStateAndDerivative softCurrentState, FieldEquationsMapper mapper) {
      super(field, forward, yDotK, globalPreviousState, globalCurrentState, softPreviousState, softCurrentState, mapper);
   }

   protected HighamHall54FieldStepInterpolator create(Field newField, boolean newForward, RealFieldElement[][] newYDotK, FieldODEStateAndDerivative newGlobalPreviousState, FieldODEStateAndDerivative newGlobalCurrentState, FieldODEStateAndDerivative newSoftPreviousState, FieldODEStateAndDerivative newSoftCurrentState, FieldEquationsMapper newMapper) {
      return new HighamHall54FieldStepInterpolator(newField, newForward, newYDotK, newGlobalPreviousState, newGlobalCurrentState, newSoftPreviousState, newSoftCurrentState, newMapper);
   }

   protected FieldODEStateAndDerivative computeInterpolatedStateAndDerivatives(FieldEquationsMapper mapper, RealFieldElement time, RealFieldElement theta, RealFieldElement thetaH, RealFieldElement oneMinusThetaH) {
      T bDot0 = (T)((RealFieldElement)((RealFieldElement)theta.multiply(((RealFieldElement)theta.multiply(((RealFieldElement)theta.multiply((double)-10.0F)).add((double)16.0F))).add((double)-7.5F))).add((double)1.0F));
      T bDot1 = (T)((RealFieldElement)time.getField().getZero());
      T bDot2 = (T)((RealFieldElement)theta.multiply(((RealFieldElement)theta.multiply(((RealFieldElement)theta.multiply((double)67.5F)).add((double)-91.125F))).add((double)28.6875F)));
      T bDot3 = (T)((RealFieldElement)theta.multiply(((RealFieldElement)theta.multiply(((RealFieldElement)theta.multiply((double)-120.0F)).add((double)152.0F))).add((double)-44.0F)));
      T bDot4 = (T)((RealFieldElement)theta.multiply(((RealFieldElement)theta.multiply(((RealFieldElement)theta.multiply((double)62.5F)).add((double)-78.125F))).add((double)23.4375F)));
      T bDot5 = (T)((RealFieldElement)((RealFieldElement)theta.multiply((double)0.625F)).multiply(((RealFieldElement)theta.multiply(2)).subtract((double)1.0F)));
      T[] interpolatedState;
      T[] interpolatedDerivatives;
      if (this.getGlobalPreviousState() != null && theta.getReal() <= (double)0.5F) {
         T b0 = (T)((RealFieldElement)thetaH.multiply(((RealFieldElement)theta.multiply(((RealFieldElement)theta.multiply(((RealFieldElement)theta.multiply((double)-2.5F)).add(5.333333333333333))).add((double)-3.75F))).add((double)1.0F)));
         T b1 = (T)((RealFieldElement)time.getField().getZero());
         T b2 = (T)((RealFieldElement)thetaH.multiply(theta.multiply(((RealFieldElement)theta.multiply(((RealFieldElement)theta.multiply((double)16.875F)).add((double)-30.375F))).add((double)14.34375F))));
         T b3 = (T)((RealFieldElement)thetaH.multiply(theta.multiply(((RealFieldElement)theta.multiply(((RealFieldElement)theta.multiply((double)-30.0F)).add(50.666666666666664))).add((double)-22.0F))));
         T b4 = (T)((RealFieldElement)thetaH.multiply(theta.multiply(((RealFieldElement)theta.multiply(((RealFieldElement)theta.multiply((double)15.625F)).add(-26.041666666666668))).add((double)11.71875F))));
         T b5 = (T)((RealFieldElement)thetaH.multiply(theta.multiply(((RealFieldElement)theta.multiply(0.4166666666666667)).add((double)-0.3125F))));
         interpolatedState = (T[])this.previousStateLinearCombination(new RealFieldElement[]{b0, b1, b2, b3, b4, b5});
         interpolatedDerivatives = (T[])this.derivativeLinearCombination(new RealFieldElement[]{bDot0, bDot1, bDot2, bDot3, bDot4, bDot5});
      } else {
         T theta2 = (T)((RealFieldElement)theta.multiply(theta));
         T h = (T)((RealFieldElement)thetaH.divide(theta));
         T b0 = (T)((RealFieldElement)h.multiply(((RealFieldElement)theta.multiply(((RealFieldElement)theta.multiply(((RealFieldElement)theta.multiply(((RealFieldElement)theta.multiply((double)-2.5F)).add(5.333333333333333))).add((double)-3.75F))).add((double)1.0F))).add(-0.08333333333333333)));
         T b1 = (T)((RealFieldElement)time.getField().getZero());
         T b2 = (T)((RealFieldElement)h.multiply(((RealFieldElement)theta2.multiply(((RealFieldElement)theta.multiply(((RealFieldElement)theta.multiply((double)16.875F)).add((double)-30.375F))).add((double)14.34375F))).add((double)-0.84375F)));
         T b3 = (T)((RealFieldElement)h.multiply(((RealFieldElement)theta2.multiply(((RealFieldElement)theta.multiply(((RealFieldElement)theta.multiply((double)-30.0F)).add(50.666666666666664))).add((double)-22.0F))).add(1.3333333333333333)));
         T b4 = (T)((RealFieldElement)h.multiply(((RealFieldElement)theta2.multiply(((RealFieldElement)theta.multiply(((RealFieldElement)theta.multiply((double)15.625F)).add(-26.041666666666668))).add((double)11.71875F))).add(-1.3020833333333333)));
         T b5 = (T)((RealFieldElement)h.multiply(((RealFieldElement)theta2.multiply(((RealFieldElement)theta.multiply(0.4166666666666667)).add((double)-0.3125F))).add(-0.10416666666666667)));
         interpolatedState = (T[])this.currentStateLinearCombination(new RealFieldElement[]{b0, b1, b2, b3, b4, b5});
         interpolatedDerivatives = (T[])this.derivativeLinearCombination(new RealFieldElement[]{bDot0, bDot1, bDot2, bDot3, bDot4, bDot5});
      }

      return new FieldODEStateAndDerivative(time, interpolatedState, interpolatedDerivatives);
   }
}
