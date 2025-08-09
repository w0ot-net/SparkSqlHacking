package org.apache.commons.math3.ode.sampling;

import org.apache.commons.math3.RealFieldElement;
import org.apache.commons.math3.exception.MaxCountExceededException;
import org.apache.commons.math3.ode.FieldODEStateAndDerivative;

public interface FieldStepHandler {
   void init(FieldODEStateAndDerivative var1, RealFieldElement var2);

   void handleStep(FieldStepInterpolator var1, boolean var2) throws MaxCountExceededException;
}
