package org.apache.commons.math3.ode.nonstiff;

import org.apache.commons.math3.Field;
import org.apache.commons.math3.RealFieldElement;
import org.apache.commons.math3.ode.FieldEquationsMapper;
import org.apache.commons.math3.ode.FieldODEStateAndDerivative;

class LutherFieldStepInterpolator extends RungeKuttaFieldStepInterpolator {
   private final RealFieldElement c5a;
   private final RealFieldElement c5b;
   private final RealFieldElement c5c;
   private final RealFieldElement c5d;
   private final RealFieldElement c6a;
   private final RealFieldElement c6b;
   private final RealFieldElement c6c;
   private final RealFieldElement c6d;
   private final RealFieldElement d5a;
   private final RealFieldElement d5b;
   private final RealFieldElement d5c;
   private final RealFieldElement d6a;
   private final RealFieldElement d6b;
   private final RealFieldElement d6c;

   LutherFieldStepInterpolator(Field field, boolean forward, RealFieldElement[][] yDotK, FieldODEStateAndDerivative globalPreviousState, FieldODEStateAndDerivative globalCurrentState, FieldODEStateAndDerivative softPreviousState, FieldODEStateAndDerivative softCurrentState, FieldEquationsMapper mapper) {
      super(field, forward, yDotK, globalPreviousState, globalCurrentState, softPreviousState, softCurrentState, mapper);
      T q = (T)((RealFieldElement)((RealFieldElement)((RealFieldElement)field.getZero()).add((double)21.0F)).sqrt());
      this.c5a = (RealFieldElement)((RealFieldElement)q.multiply(-49)).add((double)-49.0F);
      this.c5b = (RealFieldElement)((RealFieldElement)q.multiply(287)).add((double)392.0F);
      this.c5c = (RealFieldElement)((RealFieldElement)q.multiply(-357)).add((double)-637.0F);
      this.c5d = (RealFieldElement)((RealFieldElement)q.multiply(343)).add((double)833.0F);
      this.c6a = (RealFieldElement)((RealFieldElement)q.multiply(49)).add((double)-49.0F);
      this.c6b = (RealFieldElement)((RealFieldElement)q.multiply(-287)).add((double)392.0F);
      this.c6c = (RealFieldElement)((RealFieldElement)q.multiply(357)).add((double)-637.0F);
      this.c6d = (RealFieldElement)((RealFieldElement)q.multiply(-343)).add((double)833.0F);
      this.d5a = (RealFieldElement)((RealFieldElement)q.multiply(49)).add((double)49.0F);
      this.d5b = (RealFieldElement)((RealFieldElement)q.multiply(-847)).add((double)-1372.0F);
      this.d5c = (RealFieldElement)((RealFieldElement)q.multiply(1029)).add((double)2254.0F);
      this.d6a = (RealFieldElement)((RealFieldElement)q.multiply(-49)).add((double)49.0F);
      this.d6b = (RealFieldElement)((RealFieldElement)q.multiply(847)).add((double)-1372.0F);
      this.d6c = (RealFieldElement)((RealFieldElement)q.multiply(-1029)).add((double)2254.0F);
   }

   protected LutherFieldStepInterpolator create(Field newField, boolean newForward, RealFieldElement[][] newYDotK, FieldODEStateAndDerivative newGlobalPreviousState, FieldODEStateAndDerivative newGlobalCurrentState, FieldODEStateAndDerivative newSoftPreviousState, FieldODEStateAndDerivative newSoftCurrentState, FieldEquationsMapper newMapper) {
      return new LutherFieldStepInterpolator(newField, newForward, newYDotK, newGlobalPreviousState, newGlobalCurrentState, newSoftPreviousState, newSoftCurrentState, newMapper);
   }

   protected FieldODEStateAndDerivative computeInterpolatedStateAndDerivatives(FieldEquationsMapper mapper, RealFieldElement time, RealFieldElement theta, RealFieldElement thetaH, RealFieldElement oneMinusThetaH) {
      T coeffDot1 = (T)((RealFieldElement)((RealFieldElement)theta.multiply(((RealFieldElement)theta.multiply(((RealFieldElement)theta.multiply(((RealFieldElement)theta.multiply(21)).add((double)-47.0F))).add((double)36.0F))).add(-10.8))).add((double)1.0F));
      T coeffDot2 = (T)((RealFieldElement)time.getField().getZero());
      T coeffDot3 = (T)((RealFieldElement)theta.multiply(((RealFieldElement)theta.multiply(((RealFieldElement)theta.multiply(((RealFieldElement)theta.multiply(112)).add(-202.66666666666666))).add(106.66666666666667))).add(-13.866666666666667)));
      T coeffDot4 = (T)((RealFieldElement)theta.multiply(((RealFieldElement)theta.multiply(((RealFieldElement)theta.multiply(((RealFieldElement)theta.multiply(-113.4)).add(194.4))).add(-97.2))).add(12.96)));
      T coeffDot5 = (T)((RealFieldElement)theta.multiply(((RealFieldElement)theta.multiply(((RealFieldElement)theta.multiply(((RealFieldElement)theta.multiply(this.c5a.divide((double)5.0F))).add(this.c5b.divide((double)15.0F)))).add(this.c5c.divide((double)30.0F)))).add(this.c5d.divide((double)150.0F))));
      T coeffDot6 = (T)((RealFieldElement)theta.multiply(((RealFieldElement)theta.multiply(((RealFieldElement)theta.multiply(((RealFieldElement)theta.multiply(this.c6a.divide((double)5.0F))).add(this.c6b.divide((double)15.0F)))).add(this.c6c.divide((double)30.0F)))).add(this.c6d.divide((double)150.0F))));
      T coeffDot7 = (T)((RealFieldElement)theta.multiply(((RealFieldElement)theta.multiply(((RealFieldElement)theta.multiply((double)3.0F)).add((double)-3.0F))).add(0.6)));
      T[] interpolatedState;
      T[] interpolatedDerivatives;
      if (this.getGlobalPreviousState() != null && theta.getReal() <= (double)0.5F) {
         T coeff1 = (T)((RealFieldElement)thetaH.multiply(((RealFieldElement)theta.multiply(((RealFieldElement)theta.multiply(((RealFieldElement)theta.multiply(((RealFieldElement)theta.multiply(4.2)).add((double)-11.75F))).add((double)12.0F))).add(-5.4))).add((double)1.0F)));
         T coeff2 = (T)((RealFieldElement)time.getField().getZero());
         T coeff3 = (T)((RealFieldElement)thetaH.multiply(theta.multiply(((RealFieldElement)theta.multiply(((RealFieldElement)theta.multiply(((RealFieldElement)theta.multiply(22.4)).add(-50.666666666666664))).add(35.55555555555556))).add(-6.933333333333334))));
         T coeff4 = (T)((RealFieldElement)thetaH.multiply(theta.multiply(((RealFieldElement)theta.multiply(((RealFieldElement)theta.multiply(((RealFieldElement)theta.multiply(-22.68)).add(48.6))).add(-32.4))).add(6.48))));
         T coeff5 = (T)((RealFieldElement)thetaH.multiply(theta.multiply(((RealFieldElement)theta.multiply(((RealFieldElement)theta.multiply(((RealFieldElement)theta.multiply(this.c5a.divide((double)25.0F))).add(this.c5b.divide((double)60.0F)))).add(this.c5c.divide((double)90.0F)))).add(this.c5d.divide((double)300.0F)))));
         T coeff6 = (T)((RealFieldElement)thetaH.multiply(theta.multiply(((RealFieldElement)theta.multiply(((RealFieldElement)theta.multiply(((RealFieldElement)theta.multiply(this.c6a.divide((double)25.0F))).add(this.c6b.divide((double)60.0F)))).add(this.c6c.divide((double)90.0F)))).add(this.c6d.divide((double)300.0F)))));
         T coeff7 = (T)((RealFieldElement)thetaH.multiply(theta.multiply(((RealFieldElement)theta.multiply(((RealFieldElement)theta.multiply((double)0.75F)).add((double)-1.0F))).add(0.3))));
         interpolatedState = (T[])this.previousStateLinearCombination(new RealFieldElement[]{coeff1, coeff2, coeff3, coeff4, coeff5, coeff6, coeff7});
         interpolatedDerivatives = (T[])this.derivativeLinearCombination(new RealFieldElement[]{coeffDot1, coeffDot2, coeffDot3, coeffDot4, coeffDot5, coeffDot6, coeffDot7});
      } else {
         T coeff1 = (T)((RealFieldElement)oneMinusThetaH.multiply(((RealFieldElement)theta.multiply(((RealFieldElement)theta.multiply(((RealFieldElement)theta.multiply(((RealFieldElement)theta.multiply(-4.2)).add(7.55))).add(-4.45))).add(0.95))).add(-0.05)));
         T coeff2 = (T)((RealFieldElement)time.getField().getZero());
         T coeff3 = (T)((RealFieldElement)oneMinusThetaH.multiply(((RealFieldElement)theta.multiply(((RealFieldElement)theta.multiply(((RealFieldElement)theta.multiply(((RealFieldElement)theta.multiply(-22.4)).add(28.266666666666666))).add(-7.288888888888889))).add(-0.35555555555555557))).add(-0.35555555555555557)));
         T coeff4 = (T)((RealFieldElement)oneMinusThetaH.multiply(theta.multiply(theta.multiply(((RealFieldElement)theta.multiply(((RealFieldElement)theta.multiply(22.68)).add(-25.92))).add(6.48)))));
         T coeff5 = (T)((RealFieldElement)oneMinusThetaH.multiply(((RealFieldElement)theta.multiply(((RealFieldElement)theta.multiply(((RealFieldElement)theta.multiply(((RealFieldElement)theta.multiply(this.d5a.divide((double)25.0F))).add(this.d5b.divide((double)300.0F)))).add(this.d5c.divide((double)900.0F)))).add(-0.2722222222222222))).add(-0.2722222222222222)));
         T coeff6 = (T)((RealFieldElement)oneMinusThetaH.multiply(((RealFieldElement)theta.multiply(((RealFieldElement)theta.multiply(((RealFieldElement)theta.multiply(((RealFieldElement)theta.multiply(this.d6a.divide((double)25.0F))).add(this.d6b.divide((double)300.0F)))).add(this.d6c.divide((double)900.0F)))).add(-0.2722222222222222))).add(-0.2722222222222222)));
         T coeff7 = (T)((RealFieldElement)oneMinusThetaH.multiply(((RealFieldElement)theta.multiply(((RealFieldElement)theta.multiply(((RealFieldElement)theta.multiply((double)-0.75F)).add((double)0.25F))).add(-0.05))).add(-0.05)));
         interpolatedState = (T[])this.currentStateLinearCombination(new RealFieldElement[]{coeff1, coeff2, coeff3, coeff4, coeff5, coeff6, coeff7});
         interpolatedDerivatives = (T[])this.derivativeLinearCombination(new RealFieldElement[]{coeffDot1, coeffDot2, coeffDot3, coeffDot4, coeffDot5, coeffDot6, coeffDot7});
      }

      return new FieldODEStateAndDerivative(time, interpolatedState, interpolatedDerivatives);
   }
}
