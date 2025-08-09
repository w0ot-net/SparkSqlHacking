package org.apache.commons.math3.ode.sampling;

import org.apache.commons.math3.RealFieldElement;
import org.apache.commons.math3.exception.MaxCountExceededException;
import org.apache.commons.math3.ode.FieldEquationsMapper;
import org.apache.commons.math3.ode.FieldODEStateAndDerivative;

public abstract class AbstractFieldStepInterpolator implements FieldStepInterpolator {
   private final FieldODEStateAndDerivative globalPreviousState;
   private final FieldODEStateAndDerivative globalCurrentState;
   private final FieldODEStateAndDerivative softPreviousState;
   private final FieldODEStateAndDerivative softCurrentState;
   private final boolean forward;
   private FieldEquationsMapper mapper;

   protected AbstractFieldStepInterpolator(boolean isForward, FieldODEStateAndDerivative globalPreviousState, FieldODEStateAndDerivative globalCurrentState, FieldODEStateAndDerivative softPreviousState, FieldODEStateAndDerivative softCurrentState, FieldEquationsMapper equationsMapper) {
      this.forward = isForward;
      this.globalPreviousState = globalPreviousState;
      this.globalCurrentState = globalCurrentState;
      this.softPreviousState = softPreviousState;
      this.softCurrentState = softCurrentState;
      this.mapper = equationsMapper;
   }

   public AbstractFieldStepInterpolator restrictStep(FieldODEStateAndDerivative previousState, FieldODEStateAndDerivative currentState) {
      return this.create(this.forward, this.globalPreviousState, this.globalCurrentState, previousState, currentState, this.mapper);
   }

   protected abstract AbstractFieldStepInterpolator create(boolean var1, FieldODEStateAndDerivative var2, FieldODEStateAndDerivative var3, FieldODEStateAndDerivative var4, FieldODEStateAndDerivative var5, FieldEquationsMapper var6);

   public FieldODEStateAndDerivative getGlobalPreviousState() {
      return this.globalPreviousState;
   }

   public FieldODEStateAndDerivative getGlobalCurrentState() {
      return this.globalCurrentState;
   }

   public FieldODEStateAndDerivative getPreviousState() {
      return this.softPreviousState;
   }

   public FieldODEStateAndDerivative getCurrentState() {
      return this.softCurrentState;
   }

   public FieldODEStateAndDerivative getInterpolatedState(RealFieldElement time) {
      T thetaH = (T)((RealFieldElement)time.subtract(this.globalPreviousState.getTime()));
      T oneMinusThetaH = (T)((RealFieldElement)this.globalCurrentState.getTime().subtract(time));
      T theta = (T)((RealFieldElement)thetaH.divide(this.globalCurrentState.getTime().subtract(this.globalPreviousState.getTime())));
      return this.computeInterpolatedStateAndDerivatives(this.mapper, time, theta, thetaH, oneMinusThetaH);
   }

   public boolean isForward() {
      return this.forward;
   }

   protected abstract FieldODEStateAndDerivative computeInterpolatedStateAndDerivatives(FieldEquationsMapper var1, RealFieldElement var2, RealFieldElement var3, RealFieldElement var4, RealFieldElement var5) throws MaxCountExceededException;
}
