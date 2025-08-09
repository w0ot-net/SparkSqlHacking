package org.apache.commons.math3.ode.nonstiff;

import org.apache.commons.math3.Field;
import org.apache.commons.math3.RealFieldElement;
import org.apache.commons.math3.ode.FieldEquationsMapper;
import org.apache.commons.math3.ode.FieldODEStateAndDerivative;

class MidpointFieldStepInterpolator extends RungeKuttaFieldStepInterpolator {
   MidpointFieldStepInterpolator(Field field, boolean forward, RealFieldElement[][] yDotK, FieldODEStateAndDerivative globalPreviousState, FieldODEStateAndDerivative globalCurrentState, FieldODEStateAndDerivative softPreviousState, FieldODEStateAndDerivative softCurrentState, FieldEquationsMapper mapper) {
      super(field, forward, yDotK, globalPreviousState, globalCurrentState, softPreviousState, softCurrentState, mapper);
   }

   protected MidpointFieldStepInterpolator create(Field newField, boolean newForward, RealFieldElement[][] newYDotK, FieldODEStateAndDerivative newGlobalPreviousState, FieldODEStateAndDerivative newGlobalCurrentState, FieldODEStateAndDerivative newSoftPreviousState, FieldODEStateAndDerivative newSoftCurrentState, FieldEquationsMapper newMapper) {
      return new MidpointFieldStepInterpolator(newField, newForward, newYDotK, newGlobalPreviousState, newGlobalCurrentState, newSoftPreviousState, newSoftCurrentState, newMapper);
   }

   protected FieldODEStateAndDerivative computeInterpolatedStateAndDerivatives(FieldEquationsMapper mapper, RealFieldElement time, RealFieldElement theta, RealFieldElement thetaH, RealFieldElement oneMinusThetaH) {
      T coeffDot2 = (T)((RealFieldElement)theta.multiply(2));
      T coeffDot1 = (T)((RealFieldElement)((RealFieldElement)time.getField().getOne()).subtract(coeffDot2));
      T[] interpolatedState;
      T[] interpolatedDerivatives;
      if (this.getGlobalPreviousState() != null && theta.getReal() <= (double)0.5F) {
         T coeff1 = (T)((RealFieldElement)theta.multiply(oneMinusThetaH));
         T coeff2 = (T)((RealFieldElement)theta.multiply(thetaH));
         interpolatedState = (T[])this.previousStateLinearCombination(new RealFieldElement[]{coeff1, coeff2});
         interpolatedDerivatives = (T[])this.derivativeLinearCombination(new RealFieldElement[]{coeffDot1, coeffDot2});
      } else {
         T coeff1 = (T)((RealFieldElement)oneMinusThetaH.multiply(theta));
         T coeff2 = (T)((RealFieldElement)((RealFieldElement)oneMinusThetaH.multiply(theta.add((double)1.0F))).negate());
         interpolatedState = (T[])this.currentStateLinearCombination(new RealFieldElement[]{coeff1, coeff2});
         interpolatedDerivatives = (T[])this.derivativeLinearCombination(new RealFieldElement[]{coeffDot1, coeffDot2});
      }

      return new FieldODEStateAndDerivative(time, interpolatedState, interpolatedDerivatives);
   }
}
