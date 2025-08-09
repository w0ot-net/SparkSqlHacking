package org.apache.commons.math3.ode.nonstiff;

import [Lorg.apache.commons.math3.RealFieldElement;;
import java.util.Arrays;
import org.apache.commons.math3.RealFieldElement;
import org.apache.commons.math3.linear.Array2DRowFieldMatrix;
import org.apache.commons.math3.ode.FieldEquationsMapper;
import org.apache.commons.math3.ode.FieldODEStateAndDerivative;
import org.apache.commons.math3.ode.sampling.AbstractFieldStepInterpolator;
import org.apache.commons.math3.util.MathArrays;

class AdamsFieldStepInterpolator extends AbstractFieldStepInterpolator {
   private RealFieldElement scalingH;
   private final FieldODEStateAndDerivative reference;
   private final RealFieldElement[] scaled;
   private final Array2DRowFieldMatrix nordsieck;

   AdamsFieldStepInterpolator(RealFieldElement stepSize, FieldODEStateAndDerivative reference, RealFieldElement[] scaled, Array2DRowFieldMatrix nordsieck, boolean isForward, FieldODEStateAndDerivative globalPreviousState, FieldODEStateAndDerivative globalCurrentState, FieldEquationsMapper equationsMapper) {
      this(stepSize, reference, scaled, nordsieck, isForward, globalPreviousState, globalCurrentState, globalPreviousState, globalCurrentState, equationsMapper);
   }

   private AdamsFieldStepInterpolator(RealFieldElement stepSize, FieldODEStateAndDerivative reference, RealFieldElement[] scaled, Array2DRowFieldMatrix nordsieck, boolean isForward, FieldODEStateAndDerivative globalPreviousState, FieldODEStateAndDerivative globalCurrentState, FieldODEStateAndDerivative softPreviousState, FieldODEStateAndDerivative softCurrentState, FieldEquationsMapper equationsMapper) {
      super(isForward, globalPreviousState, globalCurrentState, softPreviousState, softCurrentState, equationsMapper);
      this.scalingH = stepSize;
      this.reference = reference;
      this.scaled = (RealFieldElement[])((RealFieldElement;)scaled).clone();
      this.nordsieck = new Array2DRowFieldMatrix(nordsieck.getData(), false);
   }

   protected AdamsFieldStepInterpolator create(boolean newForward, FieldODEStateAndDerivative newGlobalPreviousState, FieldODEStateAndDerivative newGlobalCurrentState, FieldODEStateAndDerivative newSoftPreviousState, FieldODEStateAndDerivative newSoftCurrentState, FieldEquationsMapper newMapper) {
      return new AdamsFieldStepInterpolator(this.scalingH, this.reference, this.scaled, this.nordsieck, newForward, newGlobalPreviousState, newGlobalCurrentState, newSoftPreviousState, newSoftCurrentState, newMapper);
   }

   protected FieldODEStateAndDerivative computeInterpolatedStateAndDerivatives(FieldEquationsMapper equationsMapper, RealFieldElement time, RealFieldElement theta, RealFieldElement thetaH, RealFieldElement oneMinusThetaH) {
      return taylor(this.reference, time, this.scalingH, this.scaled, this.nordsieck);
   }

   public static FieldODEStateAndDerivative taylor(FieldODEStateAndDerivative reference, RealFieldElement time, RealFieldElement stepSize, RealFieldElement[] scaled, Array2DRowFieldMatrix nordsieck) {
      S x = (S)((RealFieldElement)time.subtract(reference.getTime()));
      S normalizedAbscissa = (S)((RealFieldElement)x.divide(stepSize));
      S[] stateVariation = (S[])((RealFieldElement[])MathArrays.buildArray(time.getField(), scaled.length));
      Arrays.fill(stateVariation, time.getField().getZero());
      S[] estimatedDerivatives = (S[])((RealFieldElement[])MathArrays.buildArray(time.getField(), scaled.length));
      Arrays.fill(estimatedDerivatives, time.getField().getZero());
      S[][] nData = (S[][])((RealFieldElement[][])nordsieck.getDataRef());

      for(int i = nData.length - 1; i >= 0; --i) {
         int order = i + 2;
         S[] nDataI = (S[])nData[i];
         S power = (S)((RealFieldElement)normalizedAbscissa.pow(order));

         for(int j = 0; j < nDataI.length; ++j) {
            S d = (S)((RealFieldElement)nDataI[j].multiply(power));
            stateVariation[j] = (RealFieldElement)stateVariation[j].add(d);
            estimatedDerivatives[j] = (RealFieldElement)estimatedDerivatives[j].add(d.multiply(order));
         }
      }

      S[] estimatedState = (S[])reference.getState();

      for(int j = 0; j < stateVariation.length; ++j) {
         stateVariation[j] = (RealFieldElement)stateVariation[j].add(scaled[j].multiply(normalizedAbscissa));
         estimatedState[j] = (RealFieldElement)estimatedState[j].add(stateVariation[j]);
         estimatedDerivatives[j] = (RealFieldElement)((RealFieldElement)estimatedDerivatives[j].add(scaled[j].multiply(normalizedAbscissa))).divide(x);
      }

      return new FieldODEStateAndDerivative(time, estimatedState, estimatedDerivatives);
   }
}
