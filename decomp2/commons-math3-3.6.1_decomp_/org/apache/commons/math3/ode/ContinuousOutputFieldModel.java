package org.apache.commons.math3.ode;

import java.util.ArrayList;
import java.util.List;
import org.apache.commons.math3.RealFieldElement;
import org.apache.commons.math3.exception.DimensionMismatchException;
import org.apache.commons.math3.exception.MathIllegalArgumentException;
import org.apache.commons.math3.exception.MaxCountExceededException;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.ode.sampling.FieldStepHandler;
import org.apache.commons.math3.ode.sampling.FieldStepInterpolator;
import org.apache.commons.math3.util.FastMath;

public class ContinuousOutputFieldModel implements FieldStepHandler {
   private RealFieldElement initialTime = null;
   private RealFieldElement finalTime = null;
   private boolean forward = true;
   private int index = 0;
   private List steps = new ArrayList();

   public void append(ContinuousOutputFieldModel model) throws MathIllegalArgumentException, MaxCountExceededException {
      if (model.steps.size() != 0) {
         if (this.steps.size() == 0) {
            this.initialTime = model.initialTime;
            this.forward = model.forward;
         } else {
            FieldODEStateAndDerivative<T> s1 = ((FieldStepInterpolator)this.steps.get(0)).getPreviousState();
            FieldODEStateAndDerivative<T> s2 = ((FieldStepInterpolator)model.steps.get(0)).getPreviousState();
            this.checkDimensionsEquality(s1.getStateDimension(), s2.getStateDimension());
            this.checkDimensionsEquality(s1.getNumberOfSecondaryStates(), s2.getNumberOfSecondaryStates());

            for(int i = 0; i < s1.getNumberOfSecondaryStates(); ++i) {
               this.checkDimensionsEquality(s1.getSecondaryStateDimension(i), s2.getSecondaryStateDimension(i));
            }

            if (this.forward ^ model.forward) {
               throw new MathIllegalArgumentException(LocalizedFormats.PROPAGATION_DIRECTION_MISMATCH, new Object[0]);
            }

            FieldStepInterpolator<T> lastInterpolator = (FieldStepInterpolator)this.steps.get(this.index);
            T current = (T)lastInterpolator.getCurrentState().getTime();
            T previous = (T)lastInterpolator.getPreviousState().getTime();
            T step = (T)((RealFieldElement)current.subtract(previous));
            T gap = (T)((RealFieldElement)model.getInitialTime().subtract(current));
            if (((RealFieldElement)((RealFieldElement)gap.abs()).subtract(((RealFieldElement)step.abs()).multiply(0.001))).getReal() > (double)0.0F) {
               throw new MathIllegalArgumentException(LocalizedFormats.HOLE_BETWEEN_MODELS_TIME_RANGES, new Object[]{((RealFieldElement)gap.abs()).getReal()});
            }
         }

         for(FieldStepInterpolator interpolator : model.steps) {
            this.steps.add(interpolator);
         }

         this.index = this.steps.size() - 1;
         this.finalTime = ((FieldStepInterpolator)this.steps.get(this.index)).getCurrentState().getTime();
      }
   }

   private void checkDimensionsEquality(int d1, int d2) throws DimensionMismatchException {
      if (d1 != d2) {
         throw new DimensionMismatchException(d2, d1);
      }
   }

   public void init(FieldODEStateAndDerivative initialState, RealFieldElement t) {
      this.initialTime = initialState.getTime();
      this.finalTime = t;
      this.forward = true;
      this.index = 0;
      this.steps.clear();
   }

   public void handleStep(FieldStepInterpolator interpolator, boolean isLast) throws MaxCountExceededException {
      if (this.steps.size() == 0) {
         this.initialTime = interpolator.getPreviousState().getTime();
         this.forward = interpolator.isForward();
      }

      this.steps.add(interpolator);
      if (isLast) {
         this.finalTime = interpolator.getCurrentState().getTime();
         this.index = this.steps.size() - 1;
      }

   }

   public RealFieldElement getInitialTime() {
      return this.initialTime;
   }

   public RealFieldElement getFinalTime() {
      return this.finalTime;
   }

   public FieldODEStateAndDerivative getInterpolatedState(RealFieldElement time) {
      int iMin = 0;
      FieldStepInterpolator<T> sMin = (FieldStepInterpolator)this.steps.get(iMin);
      T tMin = (T)((RealFieldElement)((RealFieldElement)sMin.getPreviousState().getTime().add(sMin.getCurrentState().getTime())).multiply((double)0.5F));
      int iMax = this.steps.size() - 1;
      FieldStepInterpolator<T> sMax = (FieldStepInterpolator)this.steps.get(iMax);
      T tMax = (T)((RealFieldElement)((RealFieldElement)sMax.getPreviousState().getTime().add(sMax.getCurrentState().getTime())).multiply((double)0.5F));
      if (this.locatePoint(time, sMin) <= 0) {
         this.index = iMin;
         return sMin.getInterpolatedState(time);
      } else if (this.locatePoint(time, sMax) >= 0) {
         this.index = iMax;
         return sMax.getInterpolatedState(time);
      } else {
         while(iMax - iMin > 5) {
            FieldStepInterpolator<T> si = (FieldStepInterpolator)this.steps.get(this.index);
            int location = this.locatePoint(time, si);
            if (location < 0) {
               iMax = this.index;
               tMax = (T)((RealFieldElement)((RealFieldElement)si.getPreviousState().getTime().add(si.getCurrentState().getTime())).multiply((double)0.5F));
            } else {
               if (location <= 0) {
                  return si.getInterpolatedState(time);
               }

               iMin = this.index;
               tMin = (T)((RealFieldElement)((RealFieldElement)si.getPreviousState().getTime().add(si.getCurrentState().getTime())).multiply((double)0.5F));
            }

            int iMed = (iMin + iMax) / 2;
            FieldStepInterpolator<T> sMed = (FieldStepInterpolator)this.steps.get(iMed);
            T tMed = (T)((RealFieldElement)((RealFieldElement)sMed.getPreviousState().getTime().add(sMed.getCurrentState().getTime())).multiply((double)0.5F));
            if (!(((RealFieldElement)((RealFieldElement)((RealFieldElement)tMed.subtract(tMin)).abs()).subtract(1.0E-6)).getReal() < (double)0.0F) && !(((RealFieldElement)((RealFieldElement)((RealFieldElement)tMax.subtract(tMed)).abs()).subtract(1.0E-6)).getReal() < (double)0.0F)) {
               T d12 = (T)((RealFieldElement)tMax.subtract(tMed));
               T d23 = (T)((RealFieldElement)tMed.subtract(tMin));
               T d13 = (T)((RealFieldElement)tMax.subtract(tMin));
               T dt1 = (T)((RealFieldElement)time.subtract(tMax));
               T dt2 = (T)((RealFieldElement)time.subtract(tMed));
               T dt3 = (T)((RealFieldElement)time.subtract(tMin));
               T iLagrange = (T)((RealFieldElement)((RealFieldElement)((RealFieldElement)((RealFieldElement)((RealFieldElement)((RealFieldElement)dt2.multiply(dt3)).multiply(d23)).multiply(iMax)).subtract(((RealFieldElement)((RealFieldElement)dt1.multiply(dt3)).multiply(d13)).multiply(iMed))).add(((RealFieldElement)((RealFieldElement)dt1.multiply(dt2)).multiply(d12)).multiply(iMin))).divide(((RealFieldElement)d12.multiply(d23)).multiply(d13)));
               this.index = (int)FastMath.rint(iLagrange.getReal());
            } else {
               this.index = iMed;
            }

            int low = FastMath.max(iMin + 1, (9 * iMin + iMax) / 10);
            int high = FastMath.min(iMax - 1, (iMin + 9 * iMax) / 10);
            if (this.index < low) {
               this.index = low;
            } else if (this.index > high) {
               this.index = high;
            }
         }

         for(this.index = iMin; this.index <= iMax && this.locatePoint(time, (FieldStepInterpolator)this.steps.get(this.index)) > 0; ++this.index) {
         }

         return ((FieldStepInterpolator)this.steps.get(this.index)).getInterpolatedState(time);
      }
   }

   private int locatePoint(RealFieldElement time, FieldStepInterpolator interval) {
      if (this.forward) {
         if (((RealFieldElement)time.subtract(interval.getPreviousState().getTime())).getReal() < (double)0.0F) {
            return -1;
         } else {
            return ((RealFieldElement)time.subtract(interval.getCurrentState().getTime())).getReal() > (double)0.0F ? 1 : 0;
         }
      } else if (((RealFieldElement)time.subtract(interval.getPreviousState().getTime())).getReal() > (double)0.0F) {
         return -1;
      } else {
         return ((RealFieldElement)time.subtract(interval.getCurrentState().getTime())).getReal() < (double)0.0F ? 1 : 0;
      }
   }
}
