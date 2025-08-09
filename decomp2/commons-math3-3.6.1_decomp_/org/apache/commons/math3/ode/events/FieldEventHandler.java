package org.apache.commons.math3.ode.events;

import org.apache.commons.math3.RealFieldElement;
import org.apache.commons.math3.ode.FieldODEState;
import org.apache.commons.math3.ode.FieldODEStateAndDerivative;

public interface FieldEventHandler {
   void init(FieldODEStateAndDerivative var1, RealFieldElement var2);

   RealFieldElement g(FieldODEStateAndDerivative var1);

   Action eventOccurred(FieldODEStateAndDerivative var1, boolean var2);

   FieldODEState resetState(FieldODEStateAndDerivative var1);
}
