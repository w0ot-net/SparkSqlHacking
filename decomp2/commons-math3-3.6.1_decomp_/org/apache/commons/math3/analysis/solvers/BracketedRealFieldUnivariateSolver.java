package org.apache.commons.math3.analysis.solvers;

import org.apache.commons.math3.RealFieldElement;
import org.apache.commons.math3.analysis.RealFieldUnivariateFunction;

public interface BracketedRealFieldUnivariateSolver {
   int getMaxEvaluations();

   int getEvaluations();

   RealFieldElement getAbsoluteAccuracy();

   RealFieldElement getRelativeAccuracy();

   RealFieldElement getFunctionValueAccuracy();

   RealFieldElement solve(int var1, RealFieldUnivariateFunction var2, RealFieldElement var3, RealFieldElement var4, AllowedSolution var5);

   RealFieldElement solve(int var1, RealFieldUnivariateFunction var2, RealFieldElement var3, RealFieldElement var4, RealFieldElement var5, AllowedSolution var6);
}
