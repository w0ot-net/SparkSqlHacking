package org.apache.commons.math3.ode.nonstiff;

import org.apache.commons.math3.Field;
import org.apache.commons.math3.RealFieldElement;
import org.apache.commons.math3.ode.FieldEquationsMapper;
import org.apache.commons.math3.ode.FieldODEStateAndDerivative;
import org.apache.commons.math3.util.MathArrays;

public class MidpointFieldIntegrator extends RungeKuttaFieldIntegrator {
   public MidpointFieldIntegrator(Field field, RealFieldElement step) {
      super(field, "midpoint", step);
   }

   public RealFieldElement[] getC() {
      T[] c = (T[])((RealFieldElement[])MathArrays.buildArray(this.getField(), 1));
      c[0] = (RealFieldElement)((RealFieldElement)this.getField().getOne()).multiply((double)0.5F);
      return c;
   }

   public RealFieldElement[][] getA() {
      T[][] a = (T[][])((RealFieldElement[][])MathArrays.buildArray(this.getField(), 1, 1));
      a[0][0] = this.fraction(1, 2);
      return a;
   }

   public RealFieldElement[] getB() {
      T[] b = (T[])((RealFieldElement[])MathArrays.buildArray(this.getField(), 2));
      b[0] = (RealFieldElement)this.getField().getZero();
      b[1] = (RealFieldElement)this.getField().getOne();
      return b;
   }

   protected MidpointFieldStepInterpolator createInterpolator(boolean forward, RealFieldElement[][] yDotK, FieldODEStateAndDerivative globalPreviousState, FieldODEStateAndDerivative globalCurrentState, FieldEquationsMapper mapper) {
      return new MidpointFieldStepInterpolator(this.getField(), forward, yDotK, globalPreviousState, globalCurrentState, globalPreviousState, globalCurrentState, mapper);
   }
}
