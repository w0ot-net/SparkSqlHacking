package org.apache.commons.math3.ode.nonstiff;

import org.apache.commons.math3.Field;
import org.apache.commons.math3.RealFieldElement;
import org.apache.commons.math3.ode.FieldEquationsMapper;
import org.apache.commons.math3.ode.FieldODEStateAndDerivative;
import org.apache.commons.math3.util.MathArrays;

public class EulerFieldIntegrator extends RungeKuttaFieldIntegrator {
   public EulerFieldIntegrator(Field field, RealFieldElement step) {
      super(field, "Euler", step);
   }

   public RealFieldElement[] getC() {
      return (RealFieldElement[])MathArrays.buildArray(this.getField(), 0);
   }

   public RealFieldElement[][] getA() {
      return (RealFieldElement[][])MathArrays.buildArray(this.getField(), 0, 0);
   }

   public RealFieldElement[] getB() {
      T[] b = (T[])((RealFieldElement[])MathArrays.buildArray(this.getField(), 1));
      b[0] = (RealFieldElement)this.getField().getOne();
      return b;
   }

   protected EulerFieldStepInterpolator createInterpolator(boolean forward, RealFieldElement[][] yDotK, FieldODEStateAndDerivative globalPreviousState, FieldODEStateAndDerivative globalCurrentState, FieldEquationsMapper mapper) {
      return new EulerFieldStepInterpolator(this.getField(), forward, yDotK, globalPreviousState, globalCurrentState, globalPreviousState, globalCurrentState, mapper);
   }
}
