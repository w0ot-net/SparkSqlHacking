package org.apache.commons.math3.ode.nonstiff;

import org.apache.commons.math3.Field;
import org.apache.commons.math3.RealFieldElement;
import org.apache.commons.math3.ode.FieldEquationsMapper;
import org.apache.commons.math3.ode.FieldODEStateAndDerivative;
import org.apache.commons.math3.util.MathArrays;

public class ClassicalRungeKuttaFieldIntegrator extends RungeKuttaFieldIntegrator {
   public ClassicalRungeKuttaFieldIntegrator(Field field, RealFieldElement step) {
      super(field, "classical Runge-Kutta", step);
   }

   public RealFieldElement[] getC() {
      T[] c = (T[])((RealFieldElement[])MathArrays.buildArray(this.getField(), 3));
      c[0] = (RealFieldElement)((RealFieldElement)this.getField().getOne()).multiply((double)0.5F);
      c[1] = c[0];
      c[2] = (RealFieldElement)this.getField().getOne();
      return c;
   }

   public RealFieldElement[][] getA() {
      T[][] a = (T[][])((RealFieldElement[][])MathArrays.buildArray(this.getField(), 3, -1));

      for(int i = 0; i < a.length; ++i) {
         a[i] = (RealFieldElement[])MathArrays.buildArray(this.getField(), i + 1);
      }

      a[0][0] = this.fraction(1, 2);
      a[1][0] = (RealFieldElement)this.getField().getZero();
      a[1][1] = a[0][0];
      a[2][0] = (RealFieldElement)this.getField().getZero();
      a[2][1] = (RealFieldElement)this.getField().getZero();
      a[2][2] = (RealFieldElement)this.getField().getOne();
      return a;
   }

   public RealFieldElement[] getB() {
      T[] b = (T[])((RealFieldElement[])MathArrays.buildArray(this.getField(), 4));
      b[0] = this.fraction(1, 6);
      b[1] = this.fraction(1, 3);
      b[2] = b[1];
      b[3] = b[0];
      return b;
   }

   protected ClassicalRungeKuttaFieldStepInterpolator createInterpolator(boolean forward, RealFieldElement[][] yDotK, FieldODEStateAndDerivative globalPreviousState, FieldODEStateAndDerivative globalCurrentState, FieldEquationsMapper mapper) {
      return new ClassicalRungeKuttaFieldStepInterpolator(this.getField(), forward, yDotK, globalPreviousState, globalCurrentState, globalPreviousState, globalCurrentState, mapper);
   }
}
