package org.apache.commons.math3.ode.nonstiff;

import org.apache.commons.math3.exception.DimensionMismatchException;
import org.apache.commons.math3.exception.MaxCountExceededException;
import org.apache.commons.math3.exception.NoBracketingException;
import org.apache.commons.math3.exception.NumberIsTooSmallException;
import org.apache.commons.math3.linear.Array2DRowRealMatrix;
import org.apache.commons.math3.linear.RealMatrix;
import org.apache.commons.math3.ode.EquationsMapper;
import org.apache.commons.math3.ode.ExpandableStatefulODE;
import org.apache.commons.math3.ode.sampling.NordsieckStepInterpolator;
import org.apache.commons.math3.util.FastMath;

public class AdamsBashforthIntegrator extends AdamsIntegrator {
   private static final String METHOD_NAME = "Adams-Bashforth";

   public AdamsBashforthIntegrator(int nSteps, double minStep, double maxStep, double scalAbsoluteTolerance, double scalRelativeTolerance) throws NumberIsTooSmallException {
      super("Adams-Bashforth", nSteps, nSteps, minStep, maxStep, scalAbsoluteTolerance, scalRelativeTolerance);
   }

   public AdamsBashforthIntegrator(int nSteps, double minStep, double maxStep, double[] vecAbsoluteTolerance, double[] vecRelativeTolerance) throws IllegalArgumentException {
      super("Adams-Bashforth", nSteps, nSteps, minStep, maxStep, vecAbsoluteTolerance, vecRelativeTolerance);
   }

   private double errorEstimation(double[] previousState, double[] predictedState, double[] predictedScaled, RealMatrix predictedNordsieck) {
      double error = (double)0.0F;

      for(int i = 0; i < this.mainSetDimension; ++i) {
         double yScale = FastMath.abs(predictedState[i]);
         double tol = this.vecAbsoluteTolerance == null ? this.scalAbsoluteTolerance + this.scalRelativeTolerance * yScale : this.vecAbsoluteTolerance[i] + this.vecRelativeTolerance[i] * yScale;
         double variation = (double)0.0F;
         int sign = predictedNordsieck.getRowDimension() % 2 == 0 ? -1 : 1;

         for(int k = predictedNordsieck.getRowDimension() - 1; k >= 0; --k) {
            variation += (double)sign * predictedNordsieck.getEntry(k, i);
            sign = -sign;
         }

         variation -= predictedScaled[i];
         double ratio = (predictedState[i] - previousState[i] + variation) / tol;
         error += ratio * ratio;
      }

      return FastMath.sqrt(error / (double)this.mainSetDimension);
   }

   public void integrate(ExpandableStatefulODE equations, double t) throws NumberIsTooSmallException, DimensionMismatchException, MaxCountExceededException, NoBracketingException {
      this.sanityChecks(equations, t);
      this.setEquations(equations);
      boolean forward = t > equations.getTime();
      double[] y = equations.getCompleteState();
      double[] yDot = new double[y.length];
      NordsieckStepInterpolator interpolator = new NordsieckStepInterpolator();
      interpolator.reinitialize(y, forward, equations.getPrimaryMapper(), equations.getSecondaryMappers());
      this.initIntegration(equations.getTime(), y, t);
      this.start(equations.getTime(), y, t);
      interpolator.reinitialize(this.stepStart, this.stepSize, this.scaled, this.nordsieck);
      interpolator.storeTime(this.stepStart);
      double hNew = this.stepSize;
      interpolator.rescale(hNew);
      this.isLastStep = false;

      do {
         interpolator.shift();
         double[] predictedY = new double[y.length];
         double[] predictedScaled = new double[y.length];
         Array2DRowRealMatrix predictedNordsieck = null;
         double error = (double)10.0F;

         while(error >= (double)1.0F) {
            double stepEnd = this.stepStart + hNew;
            interpolator.storeTime(stepEnd);
            ExpandableStatefulODE expandable = this.getExpandable();
            EquationsMapper primary = expandable.getPrimaryMapper();
            primary.insertEquationData(interpolator.getInterpolatedState(), predictedY);
            int index = 0;

            for(EquationsMapper secondary : expandable.getSecondaryMappers()) {
               secondary.insertEquationData(interpolator.getInterpolatedSecondaryState(index), predictedY);
               ++index;
            }

            this.computeDerivatives(stepEnd, predictedY, yDot);

            for(int j = 0; j < predictedScaled.length; ++j) {
               predictedScaled[j] = hNew * yDot[j];
            }

            predictedNordsieck = this.updateHighOrderDerivativesPhase1(this.nordsieck);
            this.updateHighOrderDerivativesPhase2(this.scaled, predictedScaled, predictedNordsieck);
            error = this.errorEstimation(y, predictedY, predictedScaled, predictedNordsieck);
            if (error >= (double)1.0F) {
               double factor = this.computeStepGrowShrinkFactor(error);
               hNew = this.filterStep(hNew * factor, forward, false);
               interpolator.rescale(hNew);
            }
         }

         this.stepSize = hNew;
         double stepEnd = this.stepStart + this.stepSize;
         interpolator.reinitialize(stepEnd, this.stepSize, predictedScaled, predictedNordsieck);
         interpolator.storeTime(stepEnd);
         System.arraycopy(predictedY, 0, y, 0, y.length);
         this.stepStart = this.acceptStep(interpolator, y, yDot, t);
         this.scaled = predictedScaled;
         this.nordsieck = predictedNordsieck;
         interpolator.reinitialize(stepEnd, this.stepSize, this.scaled, this.nordsieck);
         if (!this.isLastStep) {
            interpolator.storeTime(this.stepStart);
            if (this.resetOccurred) {
               this.start(this.stepStart, y, t);
               interpolator.reinitialize(this.stepStart, this.stepSize, this.scaled, this.nordsieck);
            }

            double factor = this.computeStepGrowShrinkFactor(error);
            double scaledH = this.stepSize * factor;
            double nextT = this.stepStart + scaledH;
            boolean nextIsLast = forward ? nextT >= t : nextT <= t;
            hNew = this.filterStep(scaledH, forward, nextIsLast);
            double filteredNextT = this.stepStart + hNew;
            boolean filteredNextIsLast = forward ? filteredNextT >= t : filteredNextT <= t;
            if (filteredNextIsLast) {
               hNew = t - this.stepStart;
            }

            interpolator.rescale(hNew);
         }
      } while(!this.isLastStep);

      equations.setTime(this.stepStart);
      equations.setCompleteState(y);
      this.resetInternalState();
   }
}
