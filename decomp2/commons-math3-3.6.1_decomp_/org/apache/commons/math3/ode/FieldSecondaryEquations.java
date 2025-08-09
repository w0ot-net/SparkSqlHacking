package org.apache.commons.math3.ode;

import org.apache.commons.math3.RealFieldElement;
import org.apache.commons.math3.exception.DimensionMismatchException;
import org.apache.commons.math3.exception.MaxCountExceededException;

public interface FieldSecondaryEquations {
   int getDimension();

   void init(RealFieldElement var1, RealFieldElement[] var2, RealFieldElement[] var3, RealFieldElement var4);

   RealFieldElement[] computeDerivatives(RealFieldElement var1, RealFieldElement[] var2, RealFieldElement[] var3, RealFieldElement[] var4) throws MaxCountExceededException, DimensionMismatchException;
}
