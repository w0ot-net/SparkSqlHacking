package org.apache.commons.math3.ode;

import java.util.Collection;
import org.apache.commons.math3.RealFieldElement;
import org.apache.commons.math3.analysis.solvers.BracketedRealFieldUnivariateSolver;
import org.apache.commons.math3.exception.MaxCountExceededException;
import org.apache.commons.math3.exception.NoBracketingException;
import org.apache.commons.math3.exception.NumberIsTooSmallException;
import org.apache.commons.math3.ode.events.FieldEventHandler;
import org.apache.commons.math3.ode.sampling.FieldStepHandler;

public interface FirstOrderFieldIntegrator {
   String getName();

   void addStepHandler(FieldStepHandler var1);

   Collection getStepHandlers();

   void clearStepHandlers();

   void addEventHandler(FieldEventHandler var1, double var2, double var4, int var6);

   void addEventHandler(FieldEventHandler var1, double var2, double var4, int var6, BracketedRealFieldUnivariateSolver var7);

   Collection getEventHandlers();

   void clearEventHandlers();

   FieldODEStateAndDerivative getCurrentStepStart();

   RealFieldElement getCurrentSignedStepsize();

   void setMaxEvaluations(int var1);

   int getMaxEvaluations();

   int getEvaluations();

   FieldODEStateAndDerivative integrate(FieldExpandableODE var1, FieldODEState var2, RealFieldElement var3) throws NumberIsTooSmallException, MaxCountExceededException, NoBracketingException;
}
