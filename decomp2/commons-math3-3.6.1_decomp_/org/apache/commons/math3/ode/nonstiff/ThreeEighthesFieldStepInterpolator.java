package org.apache.commons.math3.ode.nonstiff;

import org.apache.commons.math3.Field;
import org.apache.commons.math3.RealFieldElement;
import org.apache.commons.math3.ode.FieldEquationsMapper;
import org.apache.commons.math3.ode.FieldODEStateAndDerivative;

class ThreeEighthesFieldStepInterpolator extends RungeKuttaFieldStepInterpolator {
   ThreeEighthesFieldStepInterpolator(Field field, boolean forward, RealFieldElement[][] yDotK, FieldODEStateAndDerivative globalPreviousState, FieldODEStateAndDerivative globalCurrentState, FieldODEStateAndDerivative softPreviousState, FieldODEStateAndDerivative softCurrentState, FieldEquationsMapper mapper) {
      super(field, forward, yDotK, globalPreviousState, globalCurrentState, softPreviousState, softCurrentState, mapper);
   }

   protected ThreeEighthesFieldStepInterpolator create(Field newField, boolean newForward, RealFieldElement[][] newYDotK, FieldODEStateAndDerivative newGlobalPreviousState, FieldODEStateAndDerivative newGlobalCurrentState, FieldODEStateAndDerivative newSoftPreviousState, FieldODEStateAndDerivative newSoftCurrentState, FieldEquationsMapper newMapper) {
      return new ThreeEighthesFieldStepInterpolator(newField, newForward, newYDotK, newGlobalPreviousState, newGlobalCurrentState, newSoftPreviousState, newSoftCurrentState, newMapper);
   }

   protected FieldODEStateAndDerivative computeInterpolatedStateAndDerivatives(FieldEquationsMapper mapper, RealFieldElement time, RealFieldElement theta, RealFieldElement thetaH, RealFieldElement oneMinusThetaH) {
      T coeffDot3 = (T)((RealFieldElement)theta.multiply((double)0.75F));
      T coeffDot1 = (T)((RealFieldElement)((RealFieldElement)coeffDot3.multiply(((RealFieldElement)theta.multiply(4)).subtract((double)5.0F))).add((double)1.0F));
      T coeffDot2 = (T)((RealFieldElement)coeffDot3.multiply(((RealFieldElement)theta.multiply(-6)).add((double)5.0F)));
      T coeffDot4 = (T)((RealFieldElement)coeffDot3.multiply(((RealFieldElement)theta.multiply(2)).subtract((double)1.0F)));
      T[] interpolatedState;
      T[] interpolatedDerivatives;
      if (this.getGlobalPreviousState() != null && theta.getReal() <= (double)0.5F) {
         T s = (T)((RealFieldElement)thetaH.divide((double)8.0F));
         T fourTheta2 = (T)((RealFieldElement)((RealFieldElement)theta.multiply(theta)).multiply(4));
         T coeff1 = (T)((RealFieldElement)s.multiply(((RealFieldElement)((RealFieldElement)fourTheta2.multiply(2)).subtract(theta.multiply(15))).add((double)8.0F)));
         T coeff2 = (T)((RealFieldElement)((RealFieldElement)s.multiply(((RealFieldElement)theta.multiply(5)).subtract(fourTheta2))).multiply(3));
         T coeff3 = (T)((RealFieldElement)((RealFieldElement)s.multiply(theta)).multiply(3));
         T coeff4 = (T)((RealFieldElement)s.multiply(fourTheta2.subtract(theta.multiply(3))));
         interpolatedState = (T[])this.previousStateLinearCombination(new RealFieldElement[]{coeff1, coeff2, coeff3, coeff4});
         interpolatedDerivatives = (T[])this.derivativeLinearCombination(new RealFieldElement[]{coeffDot1, coeffDot2, coeffDot3, coeffDot4});
      } else {
         T s = (T)((RealFieldElement)oneMinusThetaH.divide((double)-8.0F));
         T fourTheta2 = (T)((RealFieldElement)((RealFieldElement)theta.multiply(theta)).multiply(4));
         T thetaPlus1 = (T)((RealFieldElement)theta.add((double)1.0F));
         T coeff1 = (T)((RealFieldElement)s.multiply(((RealFieldElement)((RealFieldElement)fourTheta2.multiply(2)).subtract(theta.multiply(7))).add((double)1.0F)));
         T coeff2 = (T)((RealFieldElement)((RealFieldElement)s.multiply(thetaPlus1.subtract(fourTheta2))).multiply(3));
         T coeff3 = (T)((RealFieldElement)((RealFieldElement)s.multiply(thetaPlus1)).multiply(3));
         T coeff4 = (T)((RealFieldElement)s.multiply(thetaPlus1.add(fourTheta2)));
         interpolatedState = (T[])this.currentStateLinearCombination(new RealFieldElement[]{coeff1, coeff2, coeff3, coeff4});
         interpolatedDerivatives = (T[])this.derivativeLinearCombination(new RealFieldElement[]{coeffDot1, coeffDot2, coeffDot3, coeffDot4});
      }

      return new FieldODEStateAndDerivative(time, interpolatedState, interpolatedDerivatives);
   }
}
