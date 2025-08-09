package org.apache.commons.math3.ode.nonstiff;

import org.apache.commons.math3.Field;
import org.apache.commons.math3.RealFieldElement;
import org.apache.commons.math3.exception.DimensionMismatchException;
import org.apache.commons.math3.exception.MaxCountExceededException;
import org.apache.commons.math3.exception.NoBracketingException;
import org.apache.commons.math3.exception.NumberIsTooSmallException;
import org.apache.commons.math3.linear.Array2DRowFieldMatrix;
import org.apache.commons.math3.ode.FieldExpandableODE;
import org.apache.commons.math3.ode.FieldODEState;
import org.apache.commons.math3.ode.FieldODEStateAndDerivative;
import org.apache.commons.math3.ode.MultistepFieldIntegrator;

public abstract class AdamsFieldIntegrator extends MultistepFieldIntegrator {
   private final AdamsNordsieckFieldTransformer transformer;

   public AdamsFieldIntegrator(Field field, String name, int nSteps, int order, double minStep, double maxStep, double scalAbsoluteTolerance, double scalRelativeTolerance) throws NumberIsTooSmallException {
      super(field, name, nSteps, order, minStep, maxStep, scalAbsoluteTolerance, scalRelativeTolerance);
      this.transformer = AdamsNordsieckFieldTransformer.getInstance(field, nSteps);
   }

   public AdamsFieldIntegrator(Field field, String name, int nSteps, int order, double minStep, double maxStep, double[] vecAbsoluteTolerance, double[] vecRelativeTolerance) throws IllegalArgumentException {
      super(field, name, nSteps, order, minStep, maxStep, vecAbsoluteTolerance, vecRelativeTolerance);
      this.transformer = AdamsNordsieckFieldTransformer.getInstance(field, nSteps);
   }

   public abstract FieldODEStateAndDerivative integrate(FieldExpandableODE var1, FieldODEState var2, RealFieldElement var3) throws NumberIsTooSmallException, DimensionMismatchException, MaxCountExceededException, NoBracketingException;

   protected Array2DRowFieldMatrix initializeHighOrderDerivatives(RealFieldElement h, RealFieldElement[] t, RealFieldElement[][] y, RealFieldElement[][] yDot) {
      return this.transformer.initializeHighOrderDerivatives(h, t, y, yDot);
   }

   public Array2DRowFieldMatrix updateHighOrderDerivativesPhase1(Array2DRowFieldMatrix highOrder) {
      return this.transformer.updateHighOrderDerivativesPhase1(highOrder);
   }

   public void updateHighOrderDerivativesPhase2(RealFieldElement[] start, RealFieldElement[] end, Array2DRowFieldMatrix highOrder) {
      this.transformer.updateHighOrderDerivativesPhase2(start, end, highOrder);
   }
}
