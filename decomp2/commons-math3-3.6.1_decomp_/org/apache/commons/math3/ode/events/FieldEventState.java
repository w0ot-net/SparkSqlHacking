package org.apache.commons.math3.ode.events;

import org.apache.commons.math3.RealFieldElement;
import org.apache.commons.math3.analysis.RealFieldUnivariateFunction;
import org.apache.commons.math3.analysis.solvers.BracketedRealFieldUnivariateSolver;
import org.apache.commons.math3.exception.MaxCountExceededException;
import org.apache.commons.math3.exception.NoBracketingException;
import org.apache.commons.math3.ode.FieldODEState;
import org.apache.commons.math3.ode.FieldODEStateAndDerivative;
import org.apache.commons.math3.ode.sampling.FieldStepInterpolator;
import org.apache.commons.math3.util.FastMath;

public class FieldEventState {
   private final FieldEventHandler handler;
   private final double maxCheckInterval;
   private final RealFieldElement convergence;
   private final int maxIterationCount;
   private RealFieldElement t0;
   private RealFieldElement g0;
   private boolean g0Positive;
   private boolean pendingEvent;
   private RealFieldElement pendingEventTime;
   private RealFieldElement previousEventTime;
   private boolean forward;
   private boolean increasing;
   private Action nextAction;
   private final BracketedRealFieldUnivariateSolver solver;

   public FieldEventState(FieldEventHandler handler, double maxCheckInterval, RealFieldElement convergence, int maxIterationCount, BracketedRealFieldUnivariateSolver solver) {
      this.handler = handler;
      this.maxCheckInterval = maxCheckInterval;
      this.convergence = (RealFieldElement)convergence.abs();
      this.maxIterationCount = maxIterationCount;
      this.solver = solver;
      this.t0 = null;
      this.g0 = null;
      this.g0Positive = true;
      this.pendingEvent = false;
      this.pendingEventTime = null;
      this.previousEventTime = null;
      this.increasing = true;
      this.nextAction = Action.CONTINUE;
   }

   public FieldEventHandler getEventHandler() {
      return this.handler;
   }

   public double getMaxCheckInterval() {
      return this.maxCheckInterval;
   }

   public RealFieldElement getConvergence() {
      return this.convergence;
   }

   public int getMaxIterationCount() {
      return this.maxIterationCount;
   }

   public void reinitializeBegin(FieldStepInterpolator interpolator) throws MaxCountExceededException {
      FieldODEStateAndDerivative<T> s0 = interpolator.getPreviousState();
      this.t0 = s0.getTime();
      this.g0 = this.handler.g(s0);
      if (this.g0.getReal() == (double)0.0F) {
         double epsilon = FastMath.max(this.solver.getAbsoluteAccuracy().getReal(), FastMath.abs(((RealFieldElement)this.solver.getRelativeAccuracy().multiply(this.t0)).getReal()));
         T tStart = (T)((RealFieldElement)this.t0.add((double)0.5F * epsilon));
         this.g0 = this.handler.g(interpolator.getInterpolatedState(tStart));
      }

      this.g0Positive = this.g0.getReal() >= (double)0.0F;
   }

   public boolean evaluateStep(FieldStepInterpolator interpolator) throws MaxCountExceededException, NoBracketingException {
      // $FF: Couldn't be decompiled
   }

   public RealFieldElement getEventTime() {
      return this.pendingEvent ? this.pendingEventTime : (RealFieldElement)((RealFieldElement)this.t0.getField().getZero()).add(this.forward ? Double.POSITIVE_INFINITY : Double.NEGATIVE_INFINITY);
   }

   public void stepAccepted(FieldODEStateAndDerivative state) {
      this.t0 = state.getTime();
      this.g0 = this.handler.g(state);
      if (this.pendingEvent && ((RealFieldElement)((RealFieldElement)((RealFieldElement)this.pendingEventTime.subtract(state.getTime())).abs()).subtract(this.convergence)).getReal() <= (double)0.0F) {
         this.previousEventTime = state.getTime();
         this.g0Positive = this.increasing;
         this.nextAction = this.handler.eventOccurred(state, !(this.increasing ^ this.forward));
      } else {
         this.g0Positive = this.g0.getReal() >= (double)0.0F;
         this.nextAction = Action.CONTINUE;
      }

   }

   public boolean stop() {
      return this.nextAction == Action.STOP;
   }

   public FieldODEState reset(FieldODEStateAndDerivative state) {
      if (this.pendingEvent && ((RealFieldElement)((RealFieldElement)((RealFieldElement)this.pendingEventTime.subtract(state.getTime())).abs()).subtract(this.convergence)).getReal() <= (double)0.0F) {
         FieldODEState<T> newState;
         if (this.nextAction == Action.RESET_STATE) {
            newState = this.handler.resetState(state);
         } else if (this.nextAction == Action.RESET_DERIVATIVES) {
            newState = state;
         } else {
            newState = null;
         }

         this.pendingEvent = false;
         this.pendingEventTime = null;
         return newState;
      } else {
         return null;
      }
   }

   // $FF: synthetic method
   static FieldEventHandler access$000(FieldEventState x0) {
      return x0.handler;
   }
}
