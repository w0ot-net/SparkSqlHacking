package org.apache.commons.math3.ode.nonstiff;

import org.apache.commons.math3.Field;
import org.apache.commons.math3.RealFieldElement;
import org.apache.commons.math3.exception.DimensionMismatchException;
import org.apache.commons.math3.exception.MaxCountExceededException;
import org.apache.commons.math3.exception.NumberIsTooSmallException;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.ode.AbstractFieldIntegrator;
import org.apache.commons.math3.ode.FieldEquationsMapper;
import org.apache.commons.math3.ode.FieldODEState;
import org.apache.commons.math3.ode.FieldODEStateAndDerivative;
import org.apache.commons.math3.util.FastMath;
import org.apache.commons.math3.util.MathArrays;
import org.apache.commons.math3.util.MathUtils;

public abstract class AdaptiveStepsizeFieldIntegrator extends AbstractFieldIntegrator {
   protected double scalAbsoluteTolerance;
   protected double scalRelativeTolerance;
   protected double[] vecAbsoluteTolerance;
   protected double[] vecRelativeTolerance;
   protected int mainSetDimension;
   private RealFieldElement initialStep;
   private RealFieldElement minStep;
   private RealFieldElement maxStep;

   public AdaptiveStepsizeFieldIntegrator(Field field, String name, double minStep, double maxStep, double scalAbsoluteTolerance, double scalRelativeTolerance) {
      super(field, name);
      this.setStepSizeControl(minStep, maxStep, scalAbsoluteTolerance, scalRelativeTolerance);
      this.resetInternalState();
   }

   public AdaptiveStepsizeFieldIntegrator(Field field, String name, double minStep, double maxStep, double[] vecAbsoluteTolerance, double[] vecRelativeTolerance) {
      super(field, name);
      this.setStepSizeControl(minStep, maxStep, vecAbsoluteTolerance, vecRelativeTolerance);
      this.resetInternalState();
   }

   public void setStepSizeControl(double minimalStep, double maximalStep, double absoluteTolerance, double relativeTolerance) {
      this.minStep = (RealFieldElement)((RealFieldElement)this.getField().getZero()).add(FastMath.abs(minimalStep));
      this.maxStep = (RealFieldElement)((RealFieldElement)this.getField().getZero()).add(FastMath.abs(maximalStep));
      this.initialStep = (RealFieldElement)((RealFieldElement)this.getField().getOne()).negate();
      this.scalAbsoluteTolerance = absoluteTolerance;
      this.scalRelativeTolerance = relativeTolerance;
      this.vecAbsoluteTolerance = null;
      this.vecRelativeTolerance = null;
   }

   public void setStepSizeControl(double minimalStep, double maximalStep, double[] absoluteTolerance, double[] relativeTolerance) {
      this.minStep = (RealFieldElement)((RealFieldElement)this.getField().getZero()).add(FastMath.abs(minimalStep));
      this.maxStep = (RealFieldElement)((RealFieldElement)this.getField().getZero()).add(FastMath.abs(maximalStep));
      this.initialStep = (RealFieldElement)((RealFieldElement)this.getField().getOne()).negate();
      this.scalAbsoluteTolerance = (double)0.0F;
      this.scalRelativeTolerance = (double)0.0F;
      this.vecAbsoluteTolerance = (double[])(([D)absoluteTolerance).clone();
      this.vecRelativeTolerance = (double[])(([D)relativeTolerance).clone();
   }

   public void setInitialStepSize(RealFieldElement initialStepSize) {
      if (!(((RealFieldElement)initialStepSize.subtract(this.minStep)).getReal() < (double)0.0F) && !(((RealFieldElement)initialStepSize.subtract(this.maxStep)).getReal() > (double)0.0F)) {
         this.initialStep = initialStepSize;
      } else {
         this.initialStep = (RealFieldElement)((RealFieldElement)this.getField().getOne()).negate();
      }

   }

   protected void sanityChecks(FieldODEState eqn, RealFieldElement t) throws DimensionMismatchException, NumberIsTooSmallException {
      super.sanityChecks(eqn, t);
      this.mainSetDimension = eqn.getStateDimension();
      if (this.vecAbsoluteTolerance != null && this.vecAbsoluteTolerance.length != this.mainSetDimension) {
         throw new DimensionMismatchException(this.mainSetDimension, this.vecAbsoluteTolerance.length);
      } else if (this.vecRelativeTolerance != null && this.vecRelativeTolerance.length != this.mainSetDimension) {
         throw new DimensionMismatchException(this.mainSetDimension, this.vecRelativeTolerance.length);
      }
   }

   public RealFieldElement initializeStep(boolean forward, int order, RealFieldElement[] scale, FieldODEStateAndDerivative state0, FieldEquationsMapper mapper) throws MaxCountExceededException, DimensionMismatchException {
      if (this.initialStep.getReal() > (double)0.0F) {
         return forward ? this.initialStep : (RealFieldElement)this.initialStep.negate();
      } else {
         T[] y0 = (T[])mapper.mapState(state0);
         T[] yDot0 = (T[])mapper.mapDerivative(state0);
         T yOnScale2 = (T)((RealFieldElement)this.getField().getZero());
         T yDotOnScale2 = (T)((RealFieldElement)this.getField().getZero());

         for(int j = 0; j < scale.length; ++j) {
            T ratio = (T)((RealFieldElement)y0[j].divide(scale[j]));
            yOnScale2 = (T)((RealFieldElement)yOnScale2.add(ratio.multiply(ratio)));
            T ratioDot = (T)((RealFieldElement)yDot0[j].divide(scale[j]));
            yDotOnScale2 = (T)((RealFieldElement)yDotOnScale2.add(ratioDot.multiply(ratioDot)));
         }

         T h = (T)(!(yOnScale2.getReal() < 1.0E-10) && !(yDotOnScale2.getReal() < 1.0E-10) ? (RealFieldElement)((RealFieldElement)((RealFieldElement)yOnScale2.divide(yDotOnScale2)).sqrt()).multiply(0.01) : (RealFieldElement)((RealFieldElement)this.getField().getZero()).add(1.0E-6));
         if (!forward) {
            h = (T)((RealFieldElement)h.negate());
         }

         T[] y1 = (T[])((RealFieldElement[])MathArrays.buildArray(this.getField(), y0.length));

         for(int j = 0; j < y0.length; ++j) {
            y1[j] = (RealFieldElement)y0[j].add(yDot0[j].multiply(h));
         }

         T[] yDot1 = (T[])this.computeDerivatives((RealFieldElement)state0.getTime().add(h), y1);
         T yDDotOnScale = (T)((RealFieldElement)this.getField().getZero());

         for(int j = 0; j < scale.length; ++j) {
            T ratioDotDot = (T)((RealFieldElement)((RealFieldElement)yDot1[j].subtract(yDot0[j])).divide(scale[j]));
            yDDotOnScale = (T)((RealFieldElement)yDDotOnScale.add(ratioDotDot.multiply(ratioDotDot)));
         }

         yDDotOnScale = (T)((RealFieldElement)((RealFieldElement)yDDotOnScale.sqrt()).divide(h));
         T maxInv2 = (T)MathUtils.max((RealFieldElement)yDotOnScale2.sqrt(), yDDotOnScale);
         T h1 = (T)(maxInv2.getReal() < 1.0E-15 ? MathUtils.max((RealFieldElement)((RealFieldElement)this.getField().getZero()).add(1.0E-6), (RealFieldElement)((RealFieldElement)h.abs()).multiply(0.001)) : (RealFieldElement)((RealFieldElement)((RealFieldElement)maxInv2.multiply(100)).reciprocal()).pow((double)1.0F / (double)order));
         h = (T)MathUtils.min((RealFieldElement)((RealFieldElement)h.abs()).multiply(100), h1);
         h = (T)MathUtils.max(h, (RealFieldElement)((RealFieldElement)state0.getTime().abs()).multiply(1.0E-12));
         h = (T)MathUtils.max(this.minStep, MathUtils.min(this.maxStep, h));
         if (!forward) {
            h = (T)((RealFieldElement)h.negate());
         }

         return h;
      }
   }

   protected RealFieldElement filterStep(RealFieldElement h, boolean forward, boolean acceptSmall) throws NumberIsTooSmallException {
      T filteredH = h;
      if (((RealFieldElement)((RealFieldElement)h.abs()).subtract(this.minStep)).getReal() < (double)0.0F) {
         if (!acceptSmall) {
            throw new NumberIsTooSmallException(LocalizedFormats.MINIMAL_STEPSIZE_REACHED_DURING_INTEGRATION, ((RealFieldElement)h.abs()).getReal(), this.minStep.getReal(), true);
         }

         filteredH = (T)(forward ? this.minStep : (RealFieldElement)this.minStep.negate());
      }

      if (((RealFieldElement)filteredH.subtract(this.maxStep)).getReal() > (double)0.0F) {
         filteredH = (T)this.maxStep;
      } else if (((RealFieldElement)filteredH.add(this.maxStep)).getReal() < (double)0.0F) {
         filteredH = (T)((RealFieldElement)this.maxStep.negate());
      }

      return filteredH;
   }

   protected void resetInternalState() {
      this.setStepStart((FieldODEStateAndDerivative)null);
      this.setStepSize((RealFieldElement)((RealFieldElement)this.minStep.multiply(this.maxStep)).sqrt());
   }

   public RealFieldElement getMinStep() {
      return this.minStep;
   }

   public RealFieldElement getMaxStep() {
      return this.maxStep;
   }
}
