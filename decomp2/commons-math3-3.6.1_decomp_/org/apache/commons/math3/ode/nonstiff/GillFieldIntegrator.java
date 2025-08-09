package org.apache.commons.math3.ode.nonstiff;

import org.apache.commons.math3.Field;
import org.apache.commons.math3.RealFieldElement;
import org.apache.commons.math3.ode.FieldEquationsMapper;
import org.apache.commons.math3.ode.FieldODEStateAndDerivative;
import org.apache.commons.math3.util.MathArrays;

public class GillFieldIntegrator extends RungeKuttaFieldIntegrator {
   public GillFieldIntegrator(Field field, RealFieldElement step) {
      super(field, "Gill", step);
   }

   public RealFieldElement[] getC() {
      T[] c = (T[])((RealFieldElement[])MathArrays.buildArray(this.getField(), 3));
      c[0] = this.fraction(1, 2);
      c[1] = c[0];
      c[2] = (RealFieldElement)this.getField().getOne();
      return c;
   }

   public RealFieldElement[][] getA() {
      T two = (T)((RealFieldElement)((RealFieldElement)this.getField().getZero()).add((double)2.0F));
      T sqrtTwo = (T)((RealFieldElement)two.sqrt());
      T[][] a = (T[][])((RealFieldElement[][])MathArrays.buildArray(this.getField(), 3, -1));

      for(int i = 0; i < a.length; ++i) {
         a[i] = (RealFieldElement[])MathArrays.buildArray(this.getField(), i + 1);
      }

      a[0][0] = this.fraction(1, 2);
      a[1][0] = (RealFieldElement)((RealFieldElement)sqrtTwo.subtract((double)1.0F)).multiply((double)0.5F);
      a[1][1] = (RealFieldElement)((RealFieldElement)sqrtTwo.subtract((double)2.0F)).multiply((double)-0.5F);
      a[2][0] = (RealFieldElement)this.getField().getZero();
      a[2][1] = (RealFieldElement)sqrtTwo.multiply((double)-0.5F);
      a[2][2] = (RealFieldElement)((RealFieldElement)sqrtTwo.add((double)2.0F)).multiply((double)0.5F);
      return a;
   }

   public RealFieldElement[] getB() {
      T two = (T)((RealFieldElement)((RealFieldElement)this.getField().getZero()).add((double)2.0F));
      T sqrtTwo = (T)((RealFieldElement)two.sqrt());
      T[] b = (T[])((RealFieldElement[])MathArrays.buildArray(this.getField(), 4));
      b[0] = this.fraction(1, 6);
      b[1] = (RealFieldElement)((RealFieldElement)sqrtTwo.subtract((double)2.0F)).divide((double)-6.0F);
      b[2] = (RealFieldElement)((RealFieldElement)sqrtTwo.add((double)2.0F)).divide((double)6.0F);
      b[3] = b[0];
      return b;
   }

   protected GillFieldStepInterpolator createInterpolator(boolean forward, RealFieldElement[][] yDotK, FieldODEStateAndDerivative globalPreviousState, FieldODEStateAndDerivative globalCurrentState, FieldEquationsMapper mapper) {
      return new GillFieldStepInterpolator(this.getField(), forward, yDotK, globalPreviousState, globalCurrentState, globalPreviousState, globalCurrentState, mapper);
   }
}
