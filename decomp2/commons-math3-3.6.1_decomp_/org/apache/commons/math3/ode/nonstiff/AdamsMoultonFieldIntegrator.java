package org.apache.commons.math3.ode.nonstiff;

import [Lorg.apache.commons.math3.RealFieldElement;;
import java.util.Arrays;
import org.apache.commons.math3.Field;
import org.apache.commons.math3.RealFieldElement;
import org.apache.commons.math3.exception.DimensionMismatchException;
import org.apache.commons.math3.exception.MaxCountExceededException;
import org.apache.commons.math3.exception.NoBracketingException;
import org.apache.commons.math3.exception.NumberIsTooSmallException;
import org.apache.commons.math3.linear.Array2DRowFieldMatrix;
import org.apache.commons.math3.linear.FieldMatrixPreservingVisitor;
import org.apache.commons.math3.ode.FieldExpandableODE;
import org.apache.commons.math3.ode.FieldODEState;
import org.apache.commons.math3.ode.FieldODEStateAndDerivative;
import org.apache.commons.math3.util.MathArrays;
import org.apache.commons.math3.util.MathUtils;

public class AdamsMoultonFieldIntegrator extends AdamsFieldIntegrator {
   private static final String METHOD_NAME = "Adams-Moulton";

   public AdamsMoultonFieldIntegrator(Field field, int nSteps, double minStep, double maxStep, double scalAbsoluteTolerance, double scalRelativeTolerance) throws NumberIsTooSmallException {
      super(field, "Adams-Moulton", nSteps, nSteps + 1, minStep, maxStep, scalAbsoluteTolerance, scalRelativeTolerance);
   }

   public AdamsMoultonFieldIntegrator(Field field, int nSteps, double minStep, double maxStep, double[] vecAbsoluteTolerance, double[] vecRelativeTolerance) throws IllegalArgumentException {
      super(field, "Adams-Moulton", nSteps, nSteps + 1, minStep, maxStep, vecAbsoluteTolerance, vecRelativeTolerance);
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
            error = (T)((RealFieldElement)predictedNordsieck.walkInOptimizedOrder(new Corrector(y, predictedScaled, predictedY)));
            if (((RealFieldElement)error.subtract((double)1.0F)).getReal() >= (double)0.0F) {
               T factor = (T)this.computeStepGrowShrinkFactor(error);
               this.rescale(this.filterStep((RealFieldElement)this.getStepSize().multiply(factor), forward, false));
               stepEnd = AdamsFieldStepInterpolator.taylor(this.getStepStart(), (RealFieldElement)this.getStepStart().getTime().add(this.getStepSize()), this.getStepSize(), this.scaled, this.nordsieck);
            }
         }

         T[] correctedYDot = (T[])this.computeDerivatives(stepEnd.getTime(), predictedY);
         T[] correctedScaled = (T[])((RealFieldElement[])MathArrays.buildArray(this.getField(), y.length));

         for(int j = 0; j < correctedScaled.length; ++j) {
            correctedScaled[j] = (RealFieldElement)this.getStepSize().multiply(correctedYDot[j]);
         }

         this.updateHighOrderDerivativesPhase2(predictedScaled, correctedScaled, predictedNordsieck);
         stepEnd = new FieldODEStateAndDerivative(stepEnd.getTime(), predictedY, correctedYDot);
         this.setStepStart(this.acceptStep(new AdamsFieldStepInterpolator(this.getStepSize(), stepEnd, correctedScaled, predictedNordsieck, forward, this.getStepStart(), stepEnd, equations.getMapper()), finalTime));
         this.scaled = correctedScaled;
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

   private class Corrector implements FieldMatrixPreservingVisitor {
      private final RealFieldElement[] previous;
      private final RealFieldElement[] scaled;
      private final RealFieldElement[] before;
      private final RealFieldElement[] after;

      Corrector(RealFieldElement[] previous, RealFieldElement[] scaled, RealFieldElement[] state) {
         this.previous = previous;
         this.scaled = scaled;
         this.after = state;
         this.before = (RealFieldElement[])((RealFieldElement;)state).clone();
      }

      public void start(int rows, int columns, int startRow, int endRow, int startColumn, int endColumn) {
         Arrays.fill(this.after, AdamsMoultonFieldIntegrator.this.getField().getZero());
      }

      public void visit(int row, int column, RealFieldElement value) {
         if ((row & 1) == 0) {
            this.after[column] = (RealFieldElement)this.after[column].subtract(value);
         } else {
            this.after[column] = (RealFieldElement)this.after[column].add(value);
         }

      }

      public RealFieldElement end() {
         T error = (T)((RealFieldElement)AdamsMoultonFieldIntegrator.this.getField().getZero());

         for(int i = 0; i < this.after.length; ++i) {
            this.after[i] = (RealFieldElement)this.after[i].add(this.previous[i].add(this.scaled[i]));
            if (i < AdamsMoultonFieldIntegrator.this.mainSetDimension) {
               T yScale = (T)MathUtils.max((RealFieldElement)this.previous[i].abs(), (RealFieldElement)this.after[i].abs());
               T tol = (T)(AdamsMoultonFieldIntegrator.this.vecAbsoluteTolerance == null ? (RealFieldElement)((RealFieldElement)yScale.multiply(AdamsMoultonFieldIntegrator.this.scalRelativeTolerance)).add(AdamsMoultonFieldIntegrator.this.scalAbsoluteTolerance) : (RealFieldElement)((RealFieldElement)yScale.multiply(AdamsMoultonFieldIntegrator.this.vecRelativeTolerance[i])).add(AdamsMoultonFieldIntegrator.this.vecAbsoluteTolerance[i]));
               T ratio = (T)((RealFieldElement)((RealFieldElement)this.after[i].subtract(this.before[i])).divide(tol));
               error = (T)((RealFieldElement)error.add(ratio.multiply(ratio)));
            }
         }

         return (RealFieldElement)((RealFieldElement)error.divide((double)AdamsMoultonFieldIntegrator.this.mainSetDimension)).sqrt();
      }
   }
}
