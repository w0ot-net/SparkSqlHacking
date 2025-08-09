package org.apache.commons.math3.ode.nonstiff;

import org.apache.commons.math3.Field;
import org.apache.commons.math3.RealFieldElement;
import org.apache.commons.math3.ode.FieldEquationsMapper;
import org.apache.commons.math3.ode.FieldODEStateAndDerivative;

class GillFieldStepInterpolator extends RungeKuttaFieldStepInterpolator {
   private final RealFieldElement one_minus_inv_sqrt_2;
   private final RealFieldElement one_plus_inv_sqrt_2;

   GillFieldStepInterpolator(Field field, boolean forward, RealFieldElement[][] yDotK, FieldODEStateAndDerivative globalPreviousState, FieldODEStateAndDerivative globalCurrentState, FieldODEStateAndDerivative softPreviousState, FieldODEStateAndDerivative softCurrentState, FieldEquationsMapper mapper) {
      super(field, forward, yDotK, globalPreviousState, globalCurrentState, softPreviousState, softCurrentState, mapper);
      T sqrt = (T)((RealFieldElement)((RealFieldElement)((RealFieldElement)field.getZero()).add((double)0.5F)).sqrt());
      this.one_minus_inv_sqrt_2 = (RealFieldElement)((RealFieldElement)field.getOne()).subtract(sqrt);
      this.one_plus_inv_sqrt_2 = (RealFieldElement)((RealFieldElement)field.getOne()).add(sqrt);
   }

   protected GillFieldStepInterpolator create(Field newField, boolean newForward, RealFieldElement[][] newYDotK, FieldODEStateAndDerivative newGlobalPreviousState, FieldODEStateAndDerivative newGlobalCurrentState, FieldODEStateAndDerivative newSoftPreviousState, FieldODEStateAndDerivative newSoftCurrentState, FieldEquationsMapper newMapper) {
      return new GillFieldStepInterpolator(newField, newForward, newYDotK, newGlobalPreviousState, newGlobalCurrentState, newSoftPreviousState, newSoftCurrentState, newMapper);
   }

   protected FieldODEStateAndDerivative computeInterpolatedStateAndDerivatives(FieldEquationsMapper mapper, RealFieldElement time, RealFieldElement theta, RealFieldElement thetaH, RealFieldElement oneMinusThetaH) {
      T one = (T)((RealFieldElement)time.getField().getOne());
      T twoTheta = (T)((RealFieldElement)theta.multiply(2));
      T fourTheta2 = (T)((RealFieldElement)twoTheta.multiply(twoTheta));
      T coeffDot1 = (T)((RealFieldElement)((RealFieldElement)theta.multiply(twoTheta.subtract((double)3.0F))).add((double)1.0F));
      T cDot23 = (T)((RealFieldElement)twoTheta.multiply(one.subtract(theta)));
      T coeffDot2 = (T)((RealFieldElement)cDot23.multiply(this.one_minus_inv_sqrt_2));
      T coeffDot3 = (T)((RealFieldElement)cDot23.multiply(this.one_plus_inv_sqrt_2));
      T coeffDot4 = (T)((RealFieldElement)theta.multiply(twoTheta.subtract((double)1.0F)));
      T[] interpolatedState;
      T[] interpolatedDerivatives;
      if (this.getGlobalPreviousState() != null && theta.getReal() <= (double)0.5F) {
         T s = (T)((RealFieldElement)thetaH.divide((double)6.0F));
         T c23 = (T)((RealFieldElement)s.multiply(((RealFieldElement)theta.multiply(6)).subtract(fourTheta2)));
         T coeff1 = (T)((RealFieldElement)s.multiply(((RealFieldElement)fourTheta2.subtract(theta.multiply(9))).add((double)6.0F)));
         T coeff2 = (T)((RealFieldElement)c23.multiply(this.one_minus_inv_sqrt_2));
         T coeff3 = (T)((RealFieldElement)c23.multiply(this.one_plus_inv_sqrt_2));
         T coeff4 = (T)((RealFieldElement)s.multiply(fourTheta2.subtract(theta.multiply(3))));
         interpolatedState = (T[])this.previousStateLinearCombination(new RealFieldElement[]{coeff1, coeff2, coeff3, coeff4});
         interpolatedDerivatives = (T[])this.derivativeLinearCombination(new RealFieldElement[]{coeffDot1, coeffDot2, coeffDot3, coeffDot4});
      } else {
         T s = (T)((RealFieldElement)oneMinusThetaH.divide((double)-6.0F));
         T c23 = (T)((RealFieldElement)s.multiply(((RealFieldElement)twoTheta.add((double)2.0F)).subtract(fourTheta2)));
         T coeff1 = (T)((RealFieldElement)s.multiply(((RealFieldElement)fourTheta2.subtract(theta.multiply(5))).add((double)1.0F)));
         T coeff2 = (T)((RealFieldElement)c23.multiply(this.one_minus_inv_sqrt_2));
         T coeff3 = (T)((RealFieldElement)c23.multiply(this.one_plus_inv_sqrt_2));
         T coeff4 = (T)((RealFieldElement)s.multiply(((RealFieldElement)fourTheta2.add(theta)).add((double)1.0F)));
         interpolatedState = (T[])this.currentStateLinearCombination(new RealFieldElement[]{coeff1, coeff2, coeff3, coeff4});
         interpolatedDerivatives = (T[])this.derivativeLinearCombination(new RealFieldElement[]{coeffDot1, coeffDot2, coeffDot3, coeffDot4});
      }

      return new FieldODEStateAndDerivative(time, interpolatedState, interpolatedDerivatives);
   }
}
