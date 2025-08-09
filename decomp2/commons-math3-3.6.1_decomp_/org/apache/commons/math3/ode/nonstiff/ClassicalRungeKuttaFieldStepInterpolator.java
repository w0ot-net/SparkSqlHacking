package org.apache.commons.math3.ode.nonstiff;

import org.apache.commons.math3.Field;
import org.apache.commons.math3.RealFieldElement;
import org.apache.commons.math3.ode.FieldEquationsMapper;
import org.apache.commons.math3.ode.FieldODEStateAndDerivative;

class ClassicalRungeKuttaFieldStepInterpolator extends RungeKuttaFieldStepInterpolator {
   ClassicalRungeKuttaFieldStepInterpolator(Field field, boolean forward, RealFieldElement[][] yDotK, FieldODEStateAndDerivative globalPreviousState, FieldODEStateAndDerivative globalCurrentState, FieldODEStateAndDerivative softPreviousState, FieldODEStateAndDerivative softCurrentState, FieldEquationsMapper mapper) {
      super(field, forward, yDotK, globalPreviousState, globalCurrentState, softPreviousState, softCurrentState, mapper);
   }

   protected ClassicalRungeKuttaFieldStepInterpolator create(Field newField, boolean newForward, RealFieldElement[][] newYDotK, FieldODEStateAndDerivative newGlobalPreviousState, FieldODEStateAndDerivative newGlobalCurrentState, FieldODEStateAndDerivative newSoftPreviousState, FieldODEStateAndDerivative newSoftCurrentState, FieldEquationsMapper newMapper) {
      return new ClassicalRungeKuttaFieldStepInterpolator(newField, newForward, newYDotK, newGlobalPreviousState, newGlobalCurrentState, newSoftPreviousState, newSoftCurrentState, newMapper);
   }

   protected FieldODEStateAndDerivative computeInterpolatedStateAndDerivatives(FieldEquationsMapper mapper, RealFieldElement time, RealFieldElement theta, RealFieldElement thetaH, RealFieldElement oneMinusThetaH) {
      T one = (T)((RealFieldElement)time.getField().getOne());
      T oneMinusTheta = (T)((RealFieldElement)one.subtract(theta));
      T oneMinus2Theta = (T)((RealFieldElement)one.subtract(theta.multiply(2)));
      T coeffDot1 = (T)((RealFieldElement)oneMinusTheta.multiply(oneMinus2Theta));
      T coeffDot23 = (T)((RealFieldElement)((RealFieldElement)theta.multiply(oneMinusTheta)).multiply(2));
      T coeffDot4 = (T)((RealFieldElement)((RealFieldElement)theta.multiply(oneMinus2Theta)).negate());
      T[] interpolatedState;
      T[] interpolatedDerivatives;
      if (this.getGlobalPreviousState() != null && theta.getReal() <= (double)0.5F) {
         T fourTheta2 = (T)((RealFieldElement)((RealFieldElement)theta.multiply(theta)).multiply(4));
         T s = (T)((RealFieldElement)thetaH.divide((double)6.0F));
         T coeff1 = (T)((RealFieldElement)s.multiply(((RealFieldElement)fourTheta2.subtract(theta.multiply(9))).add((double)6.0F)));
         T coeff23 = (T)((RealFieldElement)s.multiply(((RealFieldElement)theta.multiply(6)).subtract(fourTheta2)));
         T coeff4 = (T)((RealFieldElement)s.multiply(fourTheta2.subtract(theta.multiply(3))));
         interpolatedState = (T[])this.previousStateLinearCombination(new RealFieldElement[]{coeff1, coeff23, coeff23, coeff4});
         interpolatedDerivatives = (T[])this.derivativeLinearCombination(new RealFieldElement[]{coeffDot1, coeffDot23, coeffDot23, coeffDot4});
      } else {
         T fourTheta = (T)((RealFieldElement)theta.multiply(4));
         T s = (T)((RealFieldElement)oneMinusThetaH.divide((double)6.0F));
         T coeff1 = (T)((RealFieldElement)s.multiply(((RealFieldElement)theta.multiply(((RealFieldElement)fourTheta.negate()).add((double)5.0F))).subtract((double)1.0F)));
         T coeff23 = (T)((RealFieldElement)s.multiply(((RealFieldElement)theta.multiply(fourTheta.subtract((double)2.0F))).subtract((double)2.0F)));
         T coeff4 = (T)((RealFieldElement)s.multiply(((RealFieldElement)theta.multiply(((RealFieldElement)fourTheta.negate()).subtract((double)1.0F))).subtract((double)1.0F)));
         interpolatedState = (T[])this.currentStateLinearCombination(new RealFieldElement[]{coeff1, coeff23, coeff23, coeff4});
         interpolatedDerivatives = (T[])this.derivativeLinearCombination(new RealFieldElement[]{coeffDot1, coeffDot23, coeffDot23, coeffDot4});
      }

      return new FieldODEStateAndDerivative(time, interpolatedState, interpolatedDerivatives);
   }
}
