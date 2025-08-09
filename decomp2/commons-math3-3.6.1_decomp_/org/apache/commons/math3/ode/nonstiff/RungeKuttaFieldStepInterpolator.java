package org.apache.commons.math3.ode.nonstiff;

import org.apache.commons.math3.Field;
import org.apache.commons.math3.RealFieldElement;
import org.apache.commons.math3.ode.FieldEquationsMapper;
import org.apache.commons.math3.ode.FieldODEStateAndDerivative;
import org.apache.commons.math3.ode.sampling.AbstractFieldStepInterpolator;
import org.apache.commons.math3.util.MathArrays;

abstract class RungeKuttaFieldStepInterpolator extends AbstractFieldStepInterpolator {
   private final Field field;
   private final RealFieldElement[][] yDotK;

   protected RungeKuttaFieldStepInterpolator(Field field, boolean forward, RealFieldElement[][] yDotK, FieldODEStateAndDerivative globalPreviousState, FieldODEStateAndDerivative globalCurrentState, FieldODEStateAndDerivative softPreviousState, FieldODEStateAndDerivative softCurrentState, FieldEquationsMapper mapper) {
      super(forward, globalPreviousState, globalCurrentState, softPreviousState, softCurrentState, mapper);
      this.field = field;
      this.yDotK = (RealFieldElement[][])MathArrays.buildArray(field, yDotK.length, -1);

      for(int i = 0; i < yDotK.length; ++i) {
         this.yDotK[i] = (RealFieldElement[])yDotK[i].clone();
      }

   }

   protected RungeKuttaFieldStepInterpolator create(boolean newForward, FieldODEStateAndDerivative newGlobalPreviousState, FieldODEStateAndDerivative newGlobalCurrentState, FieldODEStateAndDerivative newSoftPreviousState, FieldODEStateAndDerivative newSoftCurrentState, FieldEquationsMapper newMapper) {
      return this.create(this.field, newForward, this.yDotK, newGlobalPreviousState, newGlobalCurrentState, newSoftPreviousState, newSoftCurrentState, newMapper);
   }

   protected abstract RungeKuttaFieldStepInterpolator create(Field var1, boolean var2, RealFieldElement[][] var3, FieldODEStateAndDerivative var4, FieldODEStateAndDerivative var5, FieldODEStateAndDerivative var6, FieldODEStateAndDerivative var7, FieldEquationsMapper var8);

   protected final RealFieldElement[] previousStateLinearCombination(RealFieldElement... coefficients) {
      return this.combine(this.getPreviousState().getState(), coefficients);
   }

   protected RealFieldElement[] currentStateLinearCombination(RealFieldElement... coefficients) {
      return this.combine(this.getCurrentState().getState(), coefficients);
   }

   protected RealFieldElement[] derivativeLinearCombination(RealFieldElement... coefficients) {
      return this.combine((RealFieldElement[])MathArrays.buildArray(this.field, this.yDotK[0].length), coefficients);
   }

   private RealFieldElement[] combine(RealFieldElement[] a, RealFieldElement... coefficients) {
      for(int i = 0; i < a.length; ++i) {
         for(int k = 0; k < coefficients.length; ++k) {
            a[i] = (RealFieldElement)a[i].add(coefficients[k].multiply(this.yDotK[k][i]));
         }
      }

      return a;
   }
}
