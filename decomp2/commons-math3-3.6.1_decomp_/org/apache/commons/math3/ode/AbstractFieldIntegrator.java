package org.apache.commons.math3.ode;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;
import org.apache.commons.math3.Field;
import org.apache.commons.math3.RealFieldElement;
import org.apache.commons.math3.analysis.solvers.BracketedRealFieldUnivariateSolver;
import org.apache.commons.math3.analysis.solvers.FieldBracketingNthOrderBrentSolver;
import org.apache.commons.math3.exception.DimensionMismatchException;
import org.apache.commons.math3.exception.MaxCountExceededException;
import org.apache.commons.math3.exception.NoBracketingException;
import org.apache.commons.math3.exception.NumberIsTooSmallException;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.ode.events.FieldEventHandler;
import org.apache.commons.math3.ode.events.FieldEventState;
import org.apache.commons.math3.ode.sampling.AbstractFieldStepInterpolator;
import org.apache.commons.math3.ode.sampling.FieldStepHandler;
import org.apache.commons.math3.util.FastMath;
import org.apache.commons.math3.util.IntegerSequence;

public abstract class AbstractFieldIntegrator implements FirstOrderFieldIntegrator {
   private static final double DEFAULT_RELATIVE_ACCURACY = 1.0E-14;
   private static final double DEFAULT_FUNCTION_VALUE_ACCURACY = 1.0E-15;
   private Collection stepHandlers;
   private FieldODEStateAndDerivative stepStart;
   private RealFieldElement stepSize;
   private boolean isLastStep;
   private boolean resetOccurred;
   private final Field field;
   private Collection eventsStates;
   private boolean statesInitialized;
   private final String name;
   private IntegerSequence.Incrementor evaluations;
   private transient FieldExpandableODE equations;

   protected AbstractFieldIntegrator(Field field, String name) {
      this.field = field;
      this.name = name;
      this.stepHandlers = new ArrayList();
      this.stepStart = null;
      this.stepSize = null;
      this.eventsStates = new ArrayList();
      this.statesInitialized = false;
      this.evaluations = IntegerSequence.Incrementor.create().withMaximalCount(Integer.MAX_VALUE);
   }

   public Field getField() {
      return this.field;
   }

   public String getName() {
      return this.name;
   }

   public void addStepHandler(FieldStepHandler handler) {
      this.stepHandlers.add(handler);
   }

   public Collection getStepHandlers() {
      return Collections.unmodifiableCollection(this.stepHandlers);
   }

   public void clearStepHandlers() {
      this.stepHandlers.clear();
   }

   public void addEventHandler(FieldEventHandler handler, double maxCheckInterval, double convergence, int maxIterationCount) {
      this.addEventHandler(handler, maxCheckInterval, convergence, maxIterationCount, new FieldBracketingNthOrderBrentSolver((RealFieldElement)((RealFieldElement)this.field.getZero()).add(1.0E-14), (RealFieldElement)((RealFieldElement)this.field.getZero()).add(convergence), (RealFieldElement)((RealFieldElement)this.field.getZero()).add(1.0E-15), 5));
   }

   public void addEventHandler(FieldEventHandler handler, double maxCheckInterval, double convergence, int maxIterationCount, BracketedRealFieldUnivariateSolver solver) {
      this.eventsStates.add(new FieldEventState(handler, maxCheckInterval, (RealFieldElement)((RealFieldElement)this.field.getZero()).add(convergence), maxIterationCount, solver));
   }

   public Collection getEventHandlers() {
      List<FieldEventHandler<T>> list = new ArrayList(this.eventsStates.size());

      for(FieldEventState state : this.eventsStates) {
         list.add(state.getEventHandler());
      }

      return Collections.unmodifiableCollection(list);
   }

   public void clearEventHandlers() {
      this.eventsStates.clear();
   }

   public FieldODEStateAndDerivative getCurrentStepStart() {
      return this.stepStart;
   }

   public RealFieldElement getCurrentSignedStepsize() {
      return this.stepSize;
   }

   public void setMaxEvaluations(int maxEvaluations) {
      this.evaluations = this.evaluations.withMaximalCount(maxEvaluations < 0 ? Integer.MAX_VALUE : maxEvaluations);
   }

   public int getMaxEvaluations() {
      return this.evaluations.getMaximalCount();
   }

   public int getEvaluations() {
      return this.evaluations.getCount();
   }

   protected FieldODEStateAndDerivative initIntegration(FieldExpandableODE eqn, RealFieldElement t0, RealFieldElement[] y0, RealFieldElement t) {
      this.equations = eqn;
      this.evaluations = this.evaluations.withStart(0);
      eqn.init(t0, y0, t);
      T[] y0Dot = (T[])this.computeDerivatives(t0, y0);
      FieldODEStateAndDerivative<T> state0 = new FieldODEStateAndDerivative(t0, y0, y0Dot);

      for(FieldEventState state : this.eventsStates) {
         state.getEventHandler().init(state0, t);
      }

      for(FieldStepHandler handler : this.stepHandlers) {
         handler.init(state0, t);
      }

      this.setStateInitialized(false);
      return state0;
   }

   protected FieldExpandableODE getEquations() {
      return this.equations;
   }

   protected IntegerSequence.Incrementor getEvaluationsCounter() {
      return this.evaluations;
   }

   public RealFieldElement[] computeDerivatives(RealFieldElement t, RealFieldElement[] y) throws DimensionMismatchException, MaxCountExceededException, NullPointerException {
      this.evaluations.increment();
      return this.equations.computeDerivatives(t, y);
   }

   protected void setStateInitialized(boolean stateInitialized) {
      this.statesInitialized = stateInitialized;
   }

   protected FieldODEStateAndDerivative acceptStep(AbstractFieldStepInterpolator interpolator, RealFieldElement tEnd) throws MaxCountExceededException, DimensionMismatchException, NoBracketingException {
      // $FF: Couldn't be decompiled
   }

   protected void sanityChecks(FieldODEState eqn, RealFieldElement t) throws NumberIsTooSmallException, DimensionMismatchException {
      double threshold = (double)1000.0F * FastMath.ulp(FastMath.max(FastMath.abs(eqn.getTime().getReal()), FastMath.abs(t.getReal())));
      double dt = ((RealFieldElement)((RealFieldElement)eqn.getTime().subtract(t)).abs()).getReal();
      if (dt <= threshold) {
         throw new NumberIsTooSmallException(LocalizedFormats.TOO_SMALL_INTEGRATION_INTERVAL, dt, threshold, false);
      }
   }

   protected boolean resetOccurred() {
      return this.resetOccurred;
   }

   protected void setStepSize(RealFieldElement stepSize) {
      this.stepSize = stepSize;
   }

   protected RealFieldElement getStepSize() {
      return this.stepSize;
   }

   protected void setStepStart(FieldODEStateAndDerivative stepStart) {
      this.stepStart = stepStart;
   }

   protected FieldODEStateAndDerivative getStepStart() {
      return this.stepStart;
   }

   protected void setIsLastStep(boolean isLastStep) {
      this.isLastStep = isLastStep;
   }

   protected boolean isLastStep() {
      return this.isLastStep;
   }
}
