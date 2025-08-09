package org.apache.commons.math3.ode.nonstiff;

import org.apache.commons.math3.Field;
import org.apache.commons.math3.RealFieldElement;
import org.apache.commons.math3.exception.DimensionMismatchException;
import org.apache.commons.math3.exception.MaxCountExceededException;
import org.apache.commons.math3.exception.NoBracketingException;
import org.apache.commons.math3.exception.NumberIsTooSmallException;
import org.apache.commons.math3.linear.Array2DRowFieldMatrix;
import org.apache.commons.math3.linear.FieldMatrix;
import org.apache.commons.math3.ode.FieldExpandableODE;
import org.apache.commons.math3.ode.FieldODEState;
import org.apache.commons.math3.ode.FieldODEStateAndDerivative;
import org.apache.commons.math3.util.MathArrays;

public class AdamsBashforthFieldIntegrator extends AdamsFieldIntegrator {
   private static final String METHOD_NAME = "Adams-Bashforth";

   public AdamsBashforthFieldIntegrator(Field field, int nSteps, double minStep, double maxStep, double scalAbsoluteTolerance, double scalRelativeTolerance) throws NumberIsTooSmallException {
      super(field, "Adams-Bashforth", nSteps, nSteps, minStep, maxStep, scalAbsoluteTolerance, scalRelativeTolerance);
   }

   public AdamsBashforthFieldIntegrator(Field field, int nSteps, double minStep, double maxStep, double[] vecAbsoluteTolerance, double[] vecRelativeTolerance) throws IllegalArgumentException {
      super(field, "Adams-Bashforth", nSteps, nSteps, minStep, maxStep, vecAbsoluteTolerance, vecRelativeTolerance);
   }

   private RealFieldElement errorEstimation(RealFieldElement[] previousState, RealFieldElement[] predictedState, RealFieldElement[] predictedScaled, FieldMatrix predictedNordsieck) {
      T error = (T)((RealFieldElement)this.getField().getZero());

      for(int i = 0; i < this.mainSetDimension; ++i) {
         T yScale = (T)((RealFieldElement)predictedState[i].abs());
         T tol = (T)(this.vecAbsoluteTolerance == null ? (RealFieldElement)((RealFieldElement)yScale.multiply(this.scalRelativeTolerance)).add(this.scalAbsoluteTolerance) : (RealFieldElement)((RealFieldElement)yScale.multiply(this.vecRelativeTolerance[i])).add(this.vecAbsoluteTolerance[i]));
         T variation = (T)((RealFieldElement)this.getField().getZero());
         int sign = predictedNordsieck.getRowDimension() % 2 == 0 ? -1 : 1;

         for(int k = predictedNordsieck.getRowDimension() - 1; k >= 0; --k) {
            variation = (T)((RealFieldElement)variation.add(((RealFieldElement)predictedNordsieck.getEntry(k, i)).multiply(sign)));
            sign = -sign;
         }

         variation = (T)((RealFieldElement)variation.subtract(predictedScaled[i]));
         T ratio = (T)((RealFieldElement)((RealFieldElement)((RealFieldElement)predictedState[i].subtract(previousState[i])).add(variation)).divide(tol));
         error = (T)((RealFieldElement)error.add(ratio.multiply(ratio)));
      }

      return (RealFieldElement)((RealFieldElement)error.divide((double)this.mainSetDimension)).sqrt();
   }

   public FieldODEStateAndDerivative integrate(FieldExpandableODE equations, FieldODEState initialState, RealFieldElement finalTime) throws NumberIsTooSmallException, DimensionMismatchException, MaxCountExceededException, NoBracketingException {
      this.sanityChecks(initialState, finalTime);
      T t0 = (T)initialState.getTime();
      T[] y = (T[])equations.getMapper().mapState(initialState);
      this.setStepStart(this.initIntegration(equations, t0, y, finalTime));
      boolean forward = ((RealFieldElement)finalTime.subtract(initialState.getTime())).getReal() > (double)0.0F;
      this.start(equations, this.getStepStart(), finalTime);
      FieldODEStateAndDerivative<T> stepStart = this.getStepStart();
      FieldODEStateAndDerivative<T> stepEnd = AdamsFieldStepInterpolator.taylor(stepStart, (RealFieldElement)stepStart.getTime().add(this.getStepSize()), this.getStepSize(), this.scaled, this.nordsieck);
      this.setIsLastStep(false);

      do {
         T[] predictedY = null;
         T[] predictedScaled = (T[])((RealFieldElement[])MathArrays.buildArray(this.getField(), y.length));
         Array2DRowFieldMatrix<T> predictedNordsieck = null;
         T error = (T)((RealFieldElement)((RealFieldElement)this.getField().getZero()).add((double)10.0F));

         while(((RealFieldElement)error.subtract((double)1.0F)).getReal() >= (double)0.0F) {
            predictedY = (T[])stepEnd.getState();
            T[] yDot = (T[])this.computeDerivatives(stepEnd.getTime(), predictedY);

            for(int j = 0; j < predictedScaled.length; ++j) {
               predictedScaled[j] = (RealFieldElement)this.getStepSize().multiply(yDot[j]);
            }

            predictedNordsieck = this.updateHighOrderDerivativesPhase1(this.nordsieck);
            this.updateHighOrderDerivativesPhase2(this.scaled, predictedScaled, predictedNordsieck);
            error = (T)this.errorEstimation(y, predictedY, predictedScaled, predictedNordsieck);
            if (((RealFieldElement)error.subtract((double)1.0F)).getReal() >= (double)0.0F) {
               T factor = (T)this.computeStepGrowShrinkFactor(error);
               this.rescale(this.filterStep((RealFieldElement)this.getStepSize().multiply(factor), forward, false));
               stepEnd = AdamsFieldStepInterpolator.taylor(this.getStepStart(), (RealFieldElement)this.getStepStart().getTime().add(this.getStepSize()), this.getStepSize(), this.scaled, this.nordsieck);
            }
         }

         this.setStepStart(this.acceptStep(new AdamsFieldStepInterpolator(this.getStepSize(), stepEnd, predictedScaled, predictedNordsieck, forward, this.getStepStart(), stepEnd, equations.getMapper()), finalTime));
         this.scaled = predictedScaled;
         this.nordsieck = predictedNordsieck;
         if (!this.isLastStep()) {
            System.arraycopy(predictedY, 0, y, 0, y.length);
            if (this.resetOccurred()) {
               this.start(equations, this.getStepStart(), finalTime);
            }

            T factor = (T)this.computeStepGrowShrinkFactor(error);
            T scaledH = (T)((RealFieldElement)this.getStepSize().multiply(factor));
            T nextT = (T)((RealFieldElement)this.getStepStart().getTime().add(scaledH));
            boolean nextIsLast = forward ? ((RealFieldElement)nextT.subtract(finalTime)).getReal() >= (double)0.0F : ((RealFieldElement)nextT.subtract(finalTime)).getReal() <= (double)0.0F;
            T hNew = (T)this.filterStep(scaledH, forward, nextIsLast);
            T filteredNextT = (T)((RealFieldElement)this.getStepStart().getTime().add(hNew));
            boolean filteredNextIsLast = forward ? ((RealFieldElement)filteredNextT.subtract(finalTime)).getReal() >= (double)0.0F : ((RealFieldElement)filteredNextT.subtract(finalTime)).getReal() <= (double)0.0F;
            if (filteredNextIsLast) {
               hNew = (T)((RealFieldElement)finalTime.subtract(this.getStepStart().getTime()));
            }

            this.rescale(hNew);
            stepEnd = AdamsFieldStepInterpolator.taylor(this.getStepStart(), (RealFieldElement)this.getStepStart().getTime().add(this.getStepSize()), this.getStepSize(), this.scaled, this.nordsieck);
         }
      } while(!this.isLastStep());

      FieldODEStateAndDerivative<T> finalState = this.getStepStart();
      this.setStepStart((FieldODEStateAndDerivative)null);
      this.setStepSize((RealFieldElement)null);
      return finalState;
   }
}
