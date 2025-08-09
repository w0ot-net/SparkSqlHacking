package org.apache.commons.math3.ode.nonstiff;

import org.apache.commons.math3.Field;
import org.apache.commons.math3.RealFieldElement;
import org.apache.commons.math3.exception.DimensionMismatchException;
import org.apache.commons.math3.exception.MaxCountExceededException;
import org.apache.commons.math3.exception.NoBracketingException;
import org.apache.commons.math3.exception.NumberIsTooSmallException;
import org.apache.commons.math3.ode.FieldEquationsMapper;
import org.apache.commons.math3.ode.FieldExpandableODE;
import org.apache.commons.math3.ode.FieldODEState;
import org.apache.commons.math3.ode.FieldODEStateAndDerivative;
import org.apache.commons.math3.util.MathArrays;
import org.apache.commons.math3.util.MathUtils;

public abstract class EmbeddedRungeKuttaFieldIntegrator extends AdaptiveStepsizeFieldIntegrator implements FieldButcherArrayProvider {
   private final int fsal;
   private final RealFieldElement[] c;
   private final RealFieldElement[][] a;
   private final RealFieldElement[] b;
   private final RealFieldElement exp;
   private RealFieldElement safety;
   private RealFieldElement minReduction;
   private RealFieldElement maxGrowth;

   protected EmbeddedRungeKuttaFieldIntegrator(Field field, String name, int fsal, double minStep, double maxStep, double scalAbsoluteTolerance, double scalRelativeTolerance) {
      super(field, name, minStep, maxStep, scalAbsoluteTolerance, scalRelativeTolerance);
      this.fsal = fsal;
      this.c = this.getC();
      this.a = this.getA();
      this.b = this.getB();
      this.exp = (RealFieldElement)((RealFieldElement)field.getOne()).divide((double)(-this.getOrder()));
      this.setSafety((RealFieldElement)((RealFieldElement)field.getZero()).add(0.9));
      this.setMinReduction((RealFieldElement)((RealFieldElement)field.getZero()).add(0.2));
      this.setMaxGrowth((RealFieldElement)((RealFieldElement)field.getZero()).add((double)10.0F));
   }

   protected EmbeddedRungeKuttaFieldIntegrator(Field field, String name, int fsal, double minStep, double maxStep, double[] vecAbsoluteTolerance, double[] vecRelativeTolerance) {
      super(field, name, minStep, maxStep, vecAbsoluteTolerance, vecRelativeTolerance);
      this.fsal = fsal;
      this.c = this.getC();
      this.a = this.getA();
      this.b = this.getB();
      this.exp = (RealFieldElement)((RealFieldElement)field.getOne()).divide((double)(-this.getOrder()));
      this.setSafety((RealFieldElement)((RealFieldElement)field.getZero()).add(0.9));
      this.setMinReduction((RealFieldElement)((RealFieldElement)field.getZero()).add(0.2));
      this.setMaxGrowth((RealFieldElement)((RealFieldElement)field.getZero()).add((double)10.0F));
   }

   protected RealFieldElement fraction(int p, int q) {
      return (RealFieldElement)((RealFieldElement)((RealFieldElement)this.getField().getOne()).multiply(p)).divide((double)q);
   }

   protected RealFieldElement fraction(double p, double q) {
      return (RealFieldElement)((RealFieldElement)((RealFieldElement)this.getField().getOne()).multiply(p)).divide(q);
   }

   protected abstract RungeKuttaFieldStepInterpolator createInterpolator(boolean var1, RealFieldElement[][] var2, FieldODEStateAndDerivative var3, FieldODEStateAndDerivative var4, FieldEquationsMapper var5);

   public abstract int getOrder();

   public RealFieldElement getSafety() {
      return this.safety;
   }

   public void setSafety(RealFieldElement safety) {
      this.safety = safety;
   }

   public FieldODEStateAndDerivative integrate(FieldExpandableODE equations, FieldODEState initialState, RealFieldElement finalTime) throws NumberIsTooSmallException, DimensionMismatchException, MaxCountExceededException, NoBracketingException {
      this.sanityChecks(initialState, finalTime);
      T t0 = (T)initialState.getTime();
      T[] y0 = (T[])equations.getMapper().mapState(initialState);
      this.setStepStart(this.initIntegration(equations, t0, y0, finalTime));
      boolean forward = ((RealFieldElement)finalTime.subtract(initialState.getTime())).getReal() > (double)0.0F;
      int stages = this.c.length + 1;
      T[] y = y0;
      T[][] yDotK = (T[][])((RealFieldElement[][])MathArrays.buildArray(this.getField(), stages, -1));
      T[] yTmp = (T[])((RealFieldElement[])MathArrays.buildArray(this.getField(), y0.length));
      T hNew = (T)((RealFieldElement)this.getField().getZero());
      boolean firstTime = true;
      this.setIsLastStep(false);

      do {
         T error = (T)((RealFieldElement)((RealFieldElement)this.getField().getZero()).add((double)10.0F));

         while(((RealFieldElement)error.subtract((double)1.0F)).getReal() >= (double)0.0F) {
            y = (T[])equations.getMapper().mapState(this.getStepStart());
            yDotK[0] = equations.getMapper().mapDerivative(this.getStepStart());
            if (firstTime) {
               T[] scale = (T[])((RealFieldElement[])MathArrays.buildArray(this.getField(), this.mainSetDimension));
               if (this.vecAbsoluteTolerance == null) {
                  for(int i = 0; i < scale.length; ++i) {
                     scale[i] = (RealFieldElement)((RealFieldElement)((RealFieldElement)y[i].abs()).multiply(this.scalRelativeTolerance)).add(this.scalAbsoluteTolerance);
                  }
               } else {
                  for(int i = 0; i < scale.length; ++i) {
                     scale[i] = (RealFieldElement)((RealFieldElement)((RealFieldElement)y[i].abs()).multiply(this.vecRelativeTolerance[i])).add(this.vecAbsoluteTolerance[i]);
                  }
               }

               hNew = (T)this.initializeStep(forward, this.getOrder(), scale, this.getStepStart(), equations.getMapper());
               firstTime = false;
            }

            this.setStepSize(hNew);
            if (forward) {
               if (((RealFieldElement)((RealFieldElement)this.getStepStart().getTime().add(this.getStepSize())).subtract(finalTime)).getReal() >= (double)0.0F) {
                  this.setStepSize((RealFieldElement)finalTime.subtract(this.getStepStart().getTime()));
               }
            } else if (((RealFieldElement)((RealFieldElement)this.getStepStart().getTime().add(this.getStepSize())).subtract(finalTime)).getReal() <= (double)0.0F) {
               this.setStepSize((RealFieldElement)finalTime.subtract(this.getStepStart().getTime()));
            }

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

            error = (T)this.estimateError(yDotK, y, yTmp, this.getStepSize());
            if (((RealFieldElement)error.subtract((double)1.0F)).getReal() >= (double)0.0F) {
               T factor = (T)MathUtils.min(this.maxGrowth, MathUtils.max(this.minReduction, (RealFieldElement)this.safety.multiply(error.pow(this.exp))));
               hNew = (T)this.filterStep((RealFieldElement)this.getStepSize().multiply(factor), forward, false);
            }
         }

         T stepEnd = (T)((RealFieldElement)this.getStepStart().getTime().add(this.getStepSize()));
         T[] yDotTmp = (T[])(this.fsal >= 0 ? yDotK[this.fsal] : this.computeDerivatives(stepEnd, yTmp));
         FieldODEStateAndDerivative<T> stateTmp = new FieldODEStateAndDerivative(stepEnd, yTmp, yDotTmp);
         System.arraycopy(yTmp, 0, y, 0, y0.length);
         this.setStepStart(this.acceptStep(this.createInterpolator(forward, yDotK, this.getStepStart(), stateTmp, equations.getMapper()), finalTime));
         if (!this.isLastStep()) {
            T factor = (T)MathUtils.min(this.maxGrowth, MathUtils.max(this.minReduction, (RealFieldElement)this.safety.multiply(error.pow(this.exp))));
            T scaledH = (T)((RealFieldElement)this.getStepSize().multiply(factor));
            T nextT = (T)((RealFieldElement)this.getStepStart().getTime().add(scaledH));
            boolean nextIsLast = forward ? ((RealFieldElement)nextT.subtract(finalTime)).getReal() >= (double)0.0F : ((RealFieldElement)nextT.subtract(finalTime)).getReal() <= (double)0.0F;
            hNew = (T)this.filterStep(scaledH, forward, nextIsLast);
            T filteredNextT = (T)((RealFieldElement)this.getStepStart().getTime().add(hNew));
            boolean filteredNextIsLast = forward ? ((RealFieldElement)filteredNextT.subtract(finalTime)).getReal() >= (double)0.0F : ((RealFieldElement)filteredNextT.subtract(finalTime)).getReal() <= (double)0.0F;
            if (filteredNextIsLast) {
               hNew = (T)((RealFieldElement)finalTime.subtract(this.getStepStart().getTime()));
            }
         }
      } while(!this.isLastStep());

      FieldODEStateAndDerivative<T> finalState = this.getStepStart();
      this.resetInternalState();
      return finalState;
   }

   public RealFieldElement getMinReduction() {
      return this.minReduction;
   }

   public void setMinReduction(RealFieldElement minReduction) {
      this.minReduction = minReduction;
   }

   public RealFieldElement getMaxGrowth() {
      return this.maxGrowth;
   }

   public void setMaxGrowth(RealFieldElement maxGrowth) {
      this.maxGrowth = maxGrowth;
   }

   protected abstract RealFieldElement estimateError(RealFieldElement[][] var1, RealFieldElement[] var2, RealFieldElement[] var3, RealFieldElement var4);
}
