package org.apache.commons.math3.ode.sampling;

import org.apache.commons.math3.RealFieldElement;
import org.apache.commons.math3.exception.MaxCountExceededException;
import org.apache.commons.math3.ode.FieldODEStateAndDerivative;
import org.apache.commons.math3.util.FastMath;
import org.apache.commons.math3.util.Precision;

public class FieldStepNormalizer implements FieldStepHandler {
   private double h;
   private final FieldFixedStepHandler handler;
   private FieldODEStateAndDerivative first;
   private FieldODEStateAndDerivative last;
   private boolean forward;
   private final StepNormalizerBounds bounds;
   private final StepNormalizerMode mode;

   public FieldStepNormalizer(double h, FieldFixedStepHandler handler) {
      this(h, handler, StepNormalizerMode.INCREMENT, StepNormalizerBounds.FIRST);
   }

   public FieldStepNormalizer(double h, FieldFixedStepHandler handler, StepNormalizerMode mode) {
      this(h, handler, mode, StepNormalizerBounds.FIRST);
   }

   public FieldStepNormalizer(double h, FieldFixedStepHandler handler, StepNormalizerBounds bounds) {
      this(h, handler, StepNormalizerMode.INCREMENT, bounds);
   }

   public FieldStepNormalizer(double h, FieldFixedStepHandler handler, StepNormalizerMode mode, StepNormalizerBounds bounds) {
      this.h = FastMath.abs(h);
      this.handler = handler;
      this.mode = mode;
      this.bounds = bounds;
      this.first = null;
      this.last = null;
      this.forward = true;
   }

   public void init(FieldODEStateAndDerivative initialState, RealFieldElement finalTime) {
      this.first = null;
      this.last = null;
      this.forward = true;
      this.handler.init(initialState, finalTime);
   }

   public void handleStep(FieldStepInterpolator interpolator, boolean isLast) throws MaxCountExceededException {
      if (this.last == null) {
         this.first = interpolator.getPreviousState();
         this.last = this.first;
         this.forward = interpolator.isForward();
         if (!this.forward) {
            this.h = -this.h;
         }
      }

      T nextTime = (T)(this.mode == StepNormalizerMode.INCREMENT ? (RealFieldElement)this.last.getTime().add(this.h) : (RealFieldElement)((RealFieldElement)this.last.getTime().getField().getZero()).add((FastMath.floor(this.last.getTime().getReal() / this.h) + (double)1.0F) * this.h));
      if (this.mode == StepNormalizerMode.MULTIPLES && Precision.equals(nextTime.getReal(), this.last.getTime().getReal(), 1)) {
         nextTime = (T)((RealFieldElement)nextTime.add(this.h));
      }

      for(boolean nextInStep = this.isNextInStep(nextTime, interpolator); nextInStep; nextInStep = this.isNextInStep(nextTime, interpolator)) {
         this.doNormalizedStep(false);
         this.last = interpolator.getInterpolatedState(nextTime);
         nextTime = (T)((RealFieldElement)nextTime.add(this.h));
      }

      if (isLast) {
         boolean addLast = this.bounds.lastIncluded() && this.last.getTime().getReal() != interpolator.getCurrentState().getTime().getReal();
         this.doNormalizedStep(!addLast);
         if (addLast) {
            this.last = interpolator.getCurrentState();
            this.doNormalizedStep(true);
         }
      }

   }

   private boolean isNextInStep(RealFieldElement nextTime, FieldStepInterpolator interpolator) {
      return this.forward ? nextTime.getReal() <= interpolator.getCurrentState().getTime().getReal() : nextTime.getReal() >= interpolator.getCurrentState().getTime().getReal();
   }

   private void doNormalizedStep(boolean isLast) {
      if (this.bounds.firstIncluded() || this.first.getTime().getReal() != this.last.getTime().getReal()) {
         this.handler.handleStep(this.last, isLast);
      }
   }
}
