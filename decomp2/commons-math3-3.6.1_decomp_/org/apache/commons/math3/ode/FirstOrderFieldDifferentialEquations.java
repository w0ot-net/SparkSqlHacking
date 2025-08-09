package org.apache.commons.math3.ode;

import org.apache.commons.math3.RealFieldElement;

public interface FirstOrderFieldDifferentialEquations {
   int getDimension();

   void init(RealFieldElement var1, RealFieldElement[] var2, RealFieldElement var3);

   RealFieldElement[] computeDerivatives(RealFieldElement var1, RealFieldElement[] var2);
}
