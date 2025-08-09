package org.apache.commons.math3.ode.nonstiff;

import org.apache.commons.math3.Field;
import org.apache.commons.math3.RealFieldElement;
import org.apache.commons.math3.ode.FieldEquationsMapper;
import org.apache.commons.math3.ode.FieldODEStateAndDerivative;
import org.apache.commons.math3.util.MathArrays;

public class LutherFieldIntegrator extends RungeKuttaFieldIntegrator {
   public LutherFieldIntegrator(Field field, RealFieldElement step) {
      super(field, "Luther", step);
   }

   public RealFieldElement[] getC() {
      T q = (T)((RealFieldElement)((RealFieldElement)((RealFieldElement)this.getField().getZero()).add((double)21.0F)).sqrt());
      T[] c = (T[])((RealFieldElement[])MathArrays.buildArray(this.getField(), 6));
      c[0] = (RealFieldElement)this.getField().getOne();
      c[1] = this.fraction(1, 2);
      c[2] = this.fraction(2, 3);
      c[3] = (RealFieldElement)((RealFieldElement)q.subtract((double)7.0F)).divide((double)-14.0F);
      c[4] = (RealFieldElement)((RealFieldElement)q.add((double)7.0F)).divide((double)14.0F);
      c[5] = (RealFieldElement)this.getField().getOne();
      return c;
   }

   public RealFieldElement[][] getA() {
      T q = (T)((RealFieldElement)((RealFieldElement)((RealFieldElement)this.getField().getZero()).add((double)21.0F)).sqrt());
      T[][] a = (T[][])((RealFieldElement[][])MathArrays.buildArray(this.getField(), 6, -1));

      for(int i = 0; i < a.length; ++i) {
         a[i] = (RealFieldElement[])MathArrays.buildArray(this.getField(), i + 1);
      }

      a[0][0] = (RealFieldElement)this.getField().getOne();
      a[1][0] = this.fraction(3, 8);
      a[1][1] = this.fraction(1, 8);
      a[2][0] = this.fraction(8, 27);
      a[2][1] = this.fraction(2, 27);
      a[2][2] = a[2][0];
      a[3][0] = (RealFieldElement)((RealFieldElement)((RealFieldElement)q.multiply(9)).add((double)-21.0F)).divide((double)392.0F);
      a[3][1] = (RealFieldElement)((RealFieldElement)((RealFieldElement)q.multiply(8)).add((double)-56.0F)).divide((double)392.0F);
      a[3][2] = (RealFieldElement)((RealFieldElement)((RealFieldElement)q.multiply(-48)).add((double)336.0F)).divide((double)392.0F);
      a[3][3] = (RealFieldElement)((RealFieldElement)((RealFieldElement)q.multiply(3)).add((double)-63.0F)).divide((double)392.0F);
      a[4][0] = (RealFieldElement)((RealFieldElement)((RealFieldElement)q.multiply(-255)).add((double)-1155.0F)).divide((double)1960.0F);
      a[4][1] = (RealFieldElement)((RealFieldElement)((RealFieldElement)q.multiply(-40)).add((double)-280.0F)).divide((double)1960.0F);
      a[4][2] = (RealFieldElement)((RealFieldElement)q.multiply(-320)).divide((double)1960.0F);
      a[4][3] = (RealFieldElement)((RealFieldElement)((RealFieldElement)q.multiply(363)).add((double)63.0F)).divide((double)1960.0F);
      a[4][4] = (RealFieldElement)((RealFieldElement)((RealFieldElement)q.multiply(392)).add((double)2352.0F)).divide((double)1960.0F);
      a[5][0] = (RealFieldElement)((RealFieldElement)((RealFieldElement)q.multiply(105)).add((double)330.0F)).divide((double)180.0F);
      a[5][1] = this.fraction(2, 3);
      a[5][2] = (RealFieldElement)((RealFieldElement)((RealFieldElement)q.multiply(280)).add((double)-200.0F)).divide((double)180.0F);
      a[5][3] = (RealFieldElement)((RealFieldElement)((RealFieldElement)q.multiply(-189)).add((double)126.0F)).divide((double)180.0F);
      a[5][4] = (RealFieldElement)((RealFieldElement)((RealFieldElement)q.multiply(-126)).add((double)-686.0F)).divide((double)180.0F);
      a[5][5] = (RealFieldElement)((RealFieldElement)((RealFieldElement)q.multiply(-70)).add((double)490.0F)).divide((double)180.0F);
      return a;
   }

   public RealFieldElement[] getB() {
      T[] b = (T[])((RealFieldElement[])MathArrays.buildArray(this.getField(), 7));
      b[0] = this.fraction(1, 20);
      b[1] = (RealFieldElement)this.getField().getZero();
      b[2] = this.fraction(16, 45);
      b[3] = (RealFieldElement)this.getField().getZero();
      b[4] = this.fraction(49, 180);
      b[5] = b[4];
      b[6] = b[0];
      return b;
   }

   protected LutherFieldStepInterpolator createInterpolator(boolean forward, RealFieldElement[][] yDotK, FieldODEStateAndDerivative globalPreviousState, FieldODEStateAndDerivative globalCurrentState, FieldEquationsMapper mapper) {
      return new LutherFieldStepInterpolator(this.getField(), forward, yDotK, globalPreviousState, globalCurrentState, globalPreviousState, globalCurrentState, mapper);
   }
}
