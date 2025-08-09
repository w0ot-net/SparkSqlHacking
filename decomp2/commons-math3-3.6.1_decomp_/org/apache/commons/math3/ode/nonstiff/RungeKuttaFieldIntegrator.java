package org.apache.commons.math3.ode.nonstiff;

import [Lorg.apache.commons.math3.RealFieldElement;;
import org.apache.commons.math3.Field;
import org.apache.commons.math3.RealFieldElement;
import org.apache.commons.math3.exception.DimensionMismatchException;
import org.apache.commons.math3.exception.MaxCountExceededException;
import org.apache.commons.math3.exception.NoBracketingException;
import org.apache.commons.math3.exception.NumberIsTooSmallException;
import org.apache.commons.math3.ode.AbstractFieldIntegrator;
import org.apache.commons.math3.ode.FieldEquationsMapper;
import org.apache.commons.math3.ode.FieldExpandableODE;
import org.apache.commons.math3.ode.FieldODEState;
import org.apache.commons.math3.ode.FieldODEStateAndDerivative;
import org.apache.commons.math3.ode.FirstOrderFieldDifferentialEquations;
import org.apache.commons.math3.util.MathArrays;

public abstract class RungeKuttaFieldIntegrator extends AbstractFieldIntegrator implements FieldButcherArrayProvider {
   private final RealFieldElement[] c = this.getC();
   private final RealFieldElement[][] a = this.getA();
   private final RealFieldElement[] b = this.getB();
   private final RealFieldElement step;

   protected RungeKuttaFieldIntegrator(Field field, String name, RealFieldElement step) {
      super(field, name);
      this.step = (RealFieldElement)step.abs();
   }

   protected RealFieldElement fraction(int p, int q) {
      return (RealFieldElement)((RealFieldElement)((RealFieldElement)this.getField().getZero()).add((double)p)).divide((double)q);
   }

   protected abstract RungeKuttaFieldStepInterpolator createInterpolator(boolean var1, RealFieldElement[][] var2, FieldODEStateAndDerivative var3, FieldODEStateAndDerivative var4, FieldEquationsMapper var5);

   public FieldODEStateAndDerivative integrate(FieldExpandableODE equations, FieldODEState initialState, RealFieldElement finalTime) throws NumberIsTooSmallException, DimensionMismatchException, MaxCountExceededException, NoBracketingException {
      this.sanityChecks(initialState, finalTime);
      T t0 = (T)initialState.getTime();
      T[] y0 = (T[])equations.getMapper().mapState(initialState);
      this.setStepStart(this.initIntegration(equations, t0, y0, finalTime));
      boolean forward = ((RealFieldElement)finalTime.subtract(initialState.getTime())).getReal() > (double)0.0F;
      int stages = this.c.length + 1;
      T[][] yDotK = (T[][])((RealFieldElement[][])MathArrays.buildArray(this.getField(), stages, -1));
      T[] yTmp = (T[])((RealFieldElement[])MathArrays.buildArray(this.getField(), y0.length));
      if (forward) {
         if (((RealFieldElement)((RealFieldElement)this.getStepStart().getTime().add(this.step)).subtract(finalTime)).getReal() >= (double)0.0F) {
            this.setStepSize((RealFieldElement)finalTime.subtract(this.getStepStart().getTime()));
         } else {
            this.setStepSize(this.step);
         }
      } else if (((RealFieldElement)((RealFieldElement)this.getStepStart().getTime().subtract(this.step)).subtract(finalTime)).getReal() <= (double)0.0F) {
         this.setStepSize((RealFieldElement)finalTime.subtract(this.getStepStart().getTime()));
      } else {
         this.setStepSize((RealFieldElement)this.step.negate());
      }

      this.setIsLastStep(false);

      do {
         RealFieldElement[] y = equations.getMapper().mapState(this.getStepStart());
         yDotK[0] = equations.getMapper().mapDerivative(this.getStepStart());

         for(int k = 1; k < stages; ++k) {
            for(int j = 0; j < y0.length; ++j) {
               T sum = (T)((RealFieldElement)yDotK[0][j].multiply(this.a[k - 1][0]));

               for(int l = 1; l < k; ++l) {
                  sum = (T)((RealFieldElement)sum.add(yDotK[l][j].multiply(this.a[k - 1][l])));
               }

               yTmp[j] = (RealFieldElement)y[j].add(this.getStepSize().multiply(sum));
            }

            yDotK[k] = this.computeDerivatives((RealFieldElement)this.getStepStart().getTime().add(this.getStepSize().multiply(this.c[k - 1])), yTmp);
         }

         for(int j = 0; j < y0.length; ++j) {
            T sum = (T)((RealFieldElement)yDotK[0][j].multiply(this.b[0]));

            for(int l = 1; l < stages; ++l) {
               sum = (T)((RealFieldElement)sum.add(yDotK[l][j].multiply(this.b[l])));
            }

            yTmp[j] = (RealFieldElement)y[j].add(this.getStepSize().multiply(sum));
         }

         T stepEnd = (T)((RealFieldElement)this.getStepStart().getTime().add(this.getStepSize()));
         T[] yDotTmp = (T[])this.computeDerivatives(stepEnd, yTmp);
         FieldODEStateAndDerivative<T> stateTmp = new FieldODEStateAndDerivative(stepEnd, yTmp, yDotTmp);
         System.arraycopy(yTmp, 0, y, 0, y0.length);
         this.setStepStart(this.acceptStep(this.createInterpolator(forward, yDotK, this.getStepStart(), stateTmp, equations.getMapper()), finalTime));
         if (!this.isLastStep()) {
            T nextT = (T)((RealFieldElement)this.getStepStart().getTime().add(this.getStepSize()));
            boolean nextIsLast = forward ? ((RealFieldElement)nextT.subtract(finalTime)).getReal() >= (double)0.0F : ((RealFieldElement)nextT.subtract(finalTime)).getReal() <= (double)0.0F;
            if (nextIsLast) {
               this.setStepSize((RealFieldElement)finalTime.subtract(this.getStepStart().getTime()));
            }
         }
      } while(!this.isLastStep());

      FieldODEStateAndDerivative<T> finalState = this.getStepStart();
      this.setStepStart((FieldODEStateAndDerivative)null);
      this.setStepSize((RealFieldElement)null);
      return finalState;
   }

   public RealFieldElement[] singleStep(FirstOrderFieldDifferentialEquations equations, RealFieldElement t0, RealFieldElement[] y0, RealFieldElement t) {
      T[] y = (T[])((RealFieldElement[])((RealFieldElement;)y0).clone());
      int stages = this.c.length + 1;
      T[][] yDotK = (T[][])((RealFieldElement[][])MathArrays.buildArray(this.getField(), stages, -1));
      T[] yTmp = (T[])((RealFieldElement[])((RealFieldElement;)y0).clone());
      T h = (T)((RealFieldElement)t.subtract(t0));
      yDotK[0] = equations.computeDerivatives(t0, y);

      for(int k = 1; k < stages; ++k) {
         for(int j = 0; j < y0.length; ++j) {
            T sum = (T)((RealFieldElement)yDotK[0][j].multiply(this.a[k - 1][0]));

            for(int l = 1; l < k; ++l) {
               sum = (T)((RealFieldElement)sum.add(yDotK[l][j].multiply(this.a[k - 1][l])));
            }

            yTmp[j] = (RealFieldElement)y[j].add(h.multiply(sum));
         }

         yDotK[k] = equations.computeDerivatives((RealFieldElement)t0.add(h.multiply(this.c[k - 1])), yTmp);
      }

      for(int j = 0; j < y0.length; ++j) {
         T sum = (T)((RealFieldElement)yDotK[0][j].multiply(this.b[0]));

         for(int l = 1; l < stages; ++l) {
            sum = (T)((RealFieldElement)sum.add(yDotK[l][j].multiply(this.b[l])));
         }

         y[j] = (RealFieldElement)y[j].add(h.multiply(sum));
      }

      return y;
   }
}
