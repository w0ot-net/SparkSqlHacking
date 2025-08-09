package org.apache.commons.math3.ode.sampling;

import org.apache.commons.math3.RealFieldElement;
import org.apache.commons.math3.ode.FieldODEStateAndDerivative;

public interface FieldStepInterpolator {
   FieldODEStateAndDerivative getPreviousState();

   FieldODEStateAndDerivative getCurrentState();

   FieldODEStateAndDerivative getInterpolatedState(RealFieldElement var1);

   boolean isForward();
}
