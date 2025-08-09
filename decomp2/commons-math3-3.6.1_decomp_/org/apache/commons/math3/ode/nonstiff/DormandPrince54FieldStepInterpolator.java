package org.apache.commons.math3.ode.nonstiff;

import org.apache.commons.math3.Field;
import org.apache.commons.math3.RealFieldElement;
import org.apache.commons.math3.ode.FieldEquationsMapper;
import org.apache.commons.math3.ode.FieldODEStateAndDerivative;

class DormandPrince54FieldStepInterpolator extends RungeKuttaFieldStepInterpolator {
   private final RealFieldElement a70;
   private final RealFieldElement a72;
   private final RealFieldElement a73;
   private final RealFieldElement a74;
   private final RealFieldElement a75;
   private final RealFieldElement d0;
   private final RealFieldElement d2;
   private final RealFieldElement d3;
   private final RealFieldElement d4;
   private final RealFieldElement d5;
   private final RealFieldElement d6;

   DormandPrince54FieldStepInterpolator(Field field, boolean forward, RealFieldElement[][] yDotK, FieldODEStateAndDerivative globalPreviousState, FieldODEStateAndDerivative globalCurrentState, FieldODEStateAndDerivative softPreviousState, FieldODEStateAndDerivative softCurrentState, FieldEquationsMapper mapper) {
      super(field, forward, yDotK, globalPreviousState, globalCurrentState, softPreviousState, softCurrentState, mapper);
      T one = (T)((RealFieldElement)field.getOne());
      this.a70 = (RealFieldElement)((RealFieldElement)one.multiply((double)35.0F)).divide((double)384.0F);
      this.a72 = (RealFieldElement)((RealFieldElement)one.multiply((double)500.0F)).divide((double)1113.0F);
      this.a73 = (RealFieldElement)((RealFieldElement)one.multiply((double)125.0F)).divide((double)192.0F);
      this.a74 = (RealFieldElement)((RealFieldElement)one.multiply((double)-2187.0F)).divide((double)6784.0F);
      this.a75 = (RealFieldElement)((RealFieldElement)one.multiply((double)11.0F)).divide((double)84.0F);
      this.d0 = (RealFieldElement)((RealFieldElement)one.multiply(-1.2715105075E10)).divide(1.1282082432E10);
      this.d2 = (RealFieldElement)((RealFieldElement)one.multiply(8.74874797E10)).divide(3.2700410799E10);
      this.d3 = (RealFieldElement)((RealFieldElement)one.multiply(-1.0690763975E10)).divide(1.880347072E9);
      this.d4 = (RealFieldElement)((RealFieldElement)one.multiply(7.01980252875E11)).divide(1.99316789632E11);
      this.d5 = (RealFieldElement)((RealFieldElement)one.multiply(-1.453857185E9)).divide(8.22651844E8);
      this.d6 = (RealFieldElement)((RealFieldElement)one.multiply(6.9997945E7)).divide(2.9380423E7);
   }

   protected DormandPrince54FieldStepInterpolator create(Field newField, boolean newForward, RealFieldElement[][] newYDotK, FieldODEStateAndDerivative newGlobalPreviousState, FieldODEStateAndDerivative newGlobalCurrentState, FieldODEStateAndDerivative newSoftPreviousState, FieldODEStateAndDerivative newSoftCurrentState, FieldEquationsMapper newMapper) {
      return new DormandPrince54FieldStepInterpolator(newField, newForward, newYDotK, newGlobalPreviousState, newGlobalCurrentState, newSoftPreviousState, newSoftCurrentState, newMapper);
   }

   protected FieldODEStateAndDerivative computeInterpolatedStateAndDerivatives(FieldEquationsMapper mapper, RealFieldElement time, RealFieldElement theta, RealFieldElement thetaH, RealFieldElement oneMinusThetaH) {
      T one = (T)((RealFieldElement)time.getField().getOne());
      T eta = (T)((RealFieldElement)one.subtract(theta));
      T twoTheta = (T)((RealFieldElement)theta.multiply(2));
      T dot2 = (T)((RealFieldElement)one.subtract(twoTheta));
      T dot3 = (T)((RealFieldElement)theta.multiply(((RealFieldElement)theta.multiply(-3)).add((double)2.0F)));
      T dot4 = (T)((RealFieldElement)twoTheta.multiply(((RealFieldElement)theta.multiply(twoTheta.subtract((double)3.0F))).add((double)1.0F)));
      T[] interpolatedState;
      T[] interpolatedDerivatives;
      if (this.getGlobalPreviousState() != null && theta.getReal() <= (double)0.5F) {
         T f2 = (T)((RealFieldElement)thetaH.multiply(eta));
         T f3 = (T)((RealFieldElement)f2.multiply(theta));
         T f4 = (T)((RealFieldElement)f3.multiply(eta));
         T coeff0 = (T)((RealFieldElement)((RealFieldElement)((RealFieldElement)((RealFieldElement)thetaH.multiply(this.a70)).subtract(f2.multiply(this.a70.subtract((double)1.0F)))).add(f3.multiply(((RealFieldElement)this.a70.multiply(2)).subtract((double)1.0F)))).add(f4.multiply(this.d0)));
         T coeff1 = (T)((RealFieldElement)time.getField().getZero());
         T coeff2 = (T)((RealFieldElement)((RealFieldElement)((RealFieldElement)((RealFieldElement)thetaH.multiply(this.a72)).subtract(f2.multiply(this.a72))).add(f3.multiply(this.a72.multiply(2)))).add(f4.multiply(this.d2)));
         T coeff3 = (T)((RealFieldElement)((RealFieldElement)((RealFieldElement)((RealFieldElement)thetaH.multiply(this.a73)).subtract(f2.multiply(this.a73))).add(f3.multiply(this.a73.multiply(2)))).add(f4.multiply(this.d3)));
         T coeff4 = (T)((RealFieldElement)((RealFieldElement)((RealFieldElement)((RealFieldElement)thetaH.multiply(this.a74)).subtract(f2.multiply(this.a74))).add(f3.multiply(this.a74.multiply(2)))).add(f4.multiply(this.d4)));
         T coeff5 = (T)((RealFieldElement)((RealFieldElement)((RealFieldElement)((RealFieldElement)thetaH.multiply(this.a75)).subtract(f2.multiply(this.a75))).add(f3.multiply(this.a75.multiply(2)))).add(f4.multiply(this.d5)));
         T coeff6 = (T)((RealFieldElement)((RealFieldElement)f4.multiply(this.d6)).subtract(f3));
         T coeffDot0 = (T)((RealFieldElement)((RealFieldElement)((RealFieldElement)this.a70.subtract(dot2.multiply(this.a70.subtract((double)1.0F)))).add(dot3.multiply(((RealFieldElement)this.a70.multiply(2)).subtract((double)1.0F)))).add(dot4.multiply(this.d0)));
         T coeffDot1 = (T)((RealFieldElement)time.getField().getZero());
         T coeffDot2 = (T)((RealFieldElement)((RealFieldElement)((RealFieldElement)this.a72.subtract(dot2.multiply(this.a72))).add(dot3.multiply(this.a72.multiply(2)))).add(dot4.multiply(this.d2)));
         T coeffDot3 = (T)((RealFieldElement)((RealFieldElement)((RealFieldElement)this.a73.subtract(dot2.multiply(this.a73))).add(dot3.multiply(this.a73.multiply(2)))).add(dot4.multiply(this.d3)));
         T coeffDot4 = (T)((RealFieldElement)((RealFieldElement)((RealFieldElement)this.a74.subtract(dot2.multiply(this.a74))).add(dot3.multiply(this.a74.multiply(2)))).add(dot4.multiply(this.d4)));
         T coeffDot5 = (T)((RealFieldElement)((RealFieldElement)((RealFieldElement)this.a75.subtract(dot2.multiply(this.a75))).add(dot3.multiply(this.a75.multiply(2)))).add(dot4.multiply(this.d5)));
         T coeffDot6 = (T)((RealFieldElement)((RealFieldElement)dot4.multiply(this.d6)).subtract(dot3));
         interpolatedState = (T[])this.previousStateLinearCombination(new RealFieldElement[]{coeff0, coeff1, coeff2, coeff3, coeff4, coeff5, coeff6});
         interpolatedDerivatives = (T[])this.derivativeLinearCombination(new RealFieldElement[]{coeffDot0, coeffDot1, coeffDot2, coeffDot3, coeffDot4, coeffDot5, coeffDot6});
      } else {
         T f1 = (T)((RealFieldElement)oneMinusThetaH.negate());
         T f2 = (T)((RealFieldElement)oneMinusThetaH.multiply(theta));
         T f3 = (T)((RealFieldElement)f2.multiply(theta));
         T f4 = (T)((RealFieldElement)f3.multiply(eta));
         T coeff0 = (T)((RealFieldElement)((RealFieldElement)((RealFieldElement)((RealFieldElement)f1.multiply(this.a70)).subtract(f2.multiply(this.a70.subtract((double)1.0F)))).add(f3.multiply(((RealFieldElement)this.a70.multiply(2)).subtract((double)1.0F)))).add(f4.multiply(this.d0)));
         T coeff1 = (T)((RealFieldElement)time.getField().getZero());
         T coeff2 = (T)((RealFieldElement)((RealFieldElement)((RealFieldElement)((RealFieldElement)f1.multiply(this.a72)).subtract(f2.multiply(this.a72))).add(f3.multiply(this.a72.multiply(2)))).add(f4.multiply(this.d2)));
         T coeff3 = (T)((RealFieldElement)((RealFieldElement)((RealFieldElement)((RealFieldElement)f1.multiply(this.a73)).subtract(f2.multiply(this.a73))).add(f3.multiply(this.a73.multiply(2)))).add(f4.multiply(this.d3)));
         T coeff4 = (T)((RealFieldElement)((RealFieldElement)((RealFieldElement)((RealFieldElement)f1.multiply(this.a74)).subtract(f2.multiply(this.a74))).add(f3.multiply(this.a74.multiply(2)))).add(f4.multiply(this.d4)));
         T coeff5 = (T)((RealFieldElement)((RealFieldElement)((RealFieldElement)((RealFieldElement)f1.multiply(this.a75)).subtract(f2.multiply(this.a75))).add(f3.multiply(this.a75.multiply(2)))).add(f4.multiply(this.d5)));
         T coeff6 = (T)((RealFieldElement)((RealFieldElement)f4.multiply(this.d6)).subtract(f3));
         T coeffDot0 = (T)((RealFieldElement)((RealFieldElement)((RealFieldElement)this.a70.subtract(dot2.multiply(this.a70.subtract((double)1.0F)))).add(dot3.multiply(((RealFieldElement)this.a70.multiply(2)).subtract((double)1.0F)))).add(dot4.multiply(this.d0)));
         T coeffDot1 = (T)((RealFieldElement)time.getField().getZero());
         T coeffDot2 = (T)((RealFieldElement)((RealFieldElement)((RealFieldElement)this.a72.subtract(dot2.multiply(this.a72))).add(dot3.multiply(this.a72.multiply(2)))).add(dot4.multiply(this.d2)));
         T coeffDot3 = (T)((RealFieldElement)((RealFieldElement)((RealFieldElement)this.a73.subtract(dot2.multiply(this.a73))).add(dot3.multiply(this.a73.multiply(2)))).add(dot4.multiply(this.d3)));
         T coeffDot4 = (T)((RealFieldElement)((RealFieldElement)((RealFieldElement)this.a74.subtract(dot2.multiply(this.a74))).add(dot3.multiply(this.a74.multiply(2)))).add(dot4.multiply(this.d4)));
         T coeffDot5 = (T)((RealFieldElement)((RealFieldElement)((RealFieldElement)this.a75.subtract(dot2.multiply(this.a75))).add(dot3.multiply(this.a75.multiply(2)))).add(dot4.multiply(this.d5)));
         T coeffDot6 = (T)((RealFieldElement)((RealFieldElement)dot4.multiply(this.d6)).subtract(dot3));
         interpolatedState = (T[])this.currentStateLinearCombination(new RealFieldElement[]{coeff0, coeff1, coeff2, coeff3, coeff4, coeff5, coeff6});
         interpolatedDerivatives = (T[])this.derivativeLinearCombination(new RealFieldElement[]{coeffDot0, coeffDot1, coeffDot2, coeffDot3, coeffDot4, coeffDot5, coeffDot6});
      }

      return new FieldODEStateAndDerivative(time, interpolatedState, interpolatedDerivatives);
   }
}
