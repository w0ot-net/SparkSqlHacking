package org.apache.commons.math3.analysis.solvers;

import org.apache.commons.math3.Field;
import org.apache.commons.math3.RealFieldElement;
import org.apache.commons.math3.analysis.RealFieldUnivariateFunction;
import org.apache.commons.math3.exception.MathInternalError;
import org.apache.commons.math3.exception.NoBracketingException;
import org.apache.commons.math3.exception.NullArgumentException;
import org.apache.commons.math3.exception.NumberIsTooSmallException;
import org.apache.commons.math3.util.IntegerSequence;
import org.apache.commons.math3.util.MathArrays;
import org.apache.commons.math3.util.MathUtils;
import org.apache.commons.math3.util.Precision;

public class FieldBracketingNthOrderBrentSolver implements BracketedRealFieldUnivariateSolver {
   private static final int MAXIMAL_AGING = 2;
   private final Field field;
   private final int maximalOrder;
   private final RealFieldElement functionValueAccuracy;
   private final RealFieldElement absoluteAccuracy;
   private final RealFieldElement relativeAccuracy;
   private IntegerSequence.Incrementor evaluations;

   public FieldBracketingNthOrderBrentSolver(RealFieldElement relativeAccuracy, RealFieldElement absoluteAccuracy, RealFieldElement functionValueAccuracy, int maximalOrder) throws NumberIsTooSmallException {
      if (maximalOrder < 2) {
         throw new NumberIsTooSmallException(maximalOrder, 2, true);
      } else {
         this.field = relativeAccuracy.getField();
         this.maximalOrder = maximalOrder;
         this.absoluteAccuracy = absoluteAccuracy;
         this.relativeAccuracy = relativeAccuracy;
         this.functionValueAccuracy = functionValueAccuracy;
         this.evaluations = IntegerSequence.Incrementor.create();
      }
   }

   public int getMaximalOrder() {
      return this.maximalOrder;
   }

   public int getMaxEvaluations() {
      return this.evaluations.getMaximalCount();
   }

   public int getEvaluations() {
      return this.evaluations.getCount();
   }

   public RealFieldElement getAbsoluteAccuracy() {
      return this.absoluteAccuracy;
   }

   public RealFieldElement getRelativeAccuracy() {
      return this.relativeAccuracy;
   }

   public RealFieldElement getFunctionValueAccuracy() {
      return this.functionValueAccuracy;
   }

   public RealFieldElement solve(int maxEval, RealFieldUnivariateFunction f, RealFieldElement min, RealFieldElement max, AllowedSolution allowedSolution) throws NullArgumentException, NoBracketingException {
      return this.solve(maxEval, f, min, max, (RealFieldElement)((RealFieldElement)min.add(max)).divide((double)2.0F), allowedSolution);
   }

   public RealFieldElement solve(int maxEval, RealFieldUnivariateFunction f, RealFieldElement min, RealFieldElement max, RealFieldElement startValue, AllowedSolution allowedSolution) throws NullArgumentException, NoBracketingException {
      MathUtils.checkNotNull(f);
      this.evaluations = this.evaluations.withMaximalCount(maxEval).withStart(0);
      T zero = (T)((RealFieldElement)this.field.getZero());
      T nan = (T)((RealFieldElement)zero.add(Double.NaN));
      T[] x = (T[])((RealFieldElement[])MathArrays.buildArray(this.field, this.maximalOrder + 1));
      T[] y = (T[])((RealFieldElement[])MathArrays.buildArray(this.field, this.maximalOrder + 1));
      x[0] = min;
      x[1] = startValue;
      x[2] = max;
      this.evaluations.increment();
      y[1] = f.value(x[1]);
      if (Precision.equals(y[1].getReal(), (double)0.0F, 1)) {
         return x[1];
      } else {
         this.evaluations.increment();
         y[0] = f.value(x[0]);
         if (Precision.equals(y[0].getReal(), (double)0.0F, 1)) {
            return x[0];
         } else {
            int nbPoints;
            int signChangeIndex;
            if (((RealFieldElement)y[0].multiply(y[1])).getReal() < (double)0.0F) {
               nbPoints = 2;
               signChangeIndex = 1;
            } else {
               this.evaluations.increment();
               y[2] = f.value(x[2]);
               if (Precision.equals(y[2].getReal(), (double)0.0F, 1)) {
                  return x[2];
               }

               if (!(((RealFieldElement)y[1].multiply(y[2])).getReal() < (double)0.0F)) {
                  throw new NoBracketingException(x[0].getReal(), x[2].getReal(), y[0].getReal(), y[2].getReal());
               }

               nbPoints = 3;
               signChangeIndex = 2;
            }

            T[] tmpX = (T[])((RealFieldElement[])MathArrays.buildArray(this.field, x.length));
            T xA = (T)x[signChangeIndex - 1];
            T yA = (T)y[signChangeIndex - 1];
            T absXA = (T)((RealFieldElement)xA.abs());
            T absYA = (T)((RealFieldElement)yA.abs());
            int agingA = 0;
            T xB = (T)x[signChangeIndex];
            T yB = (T)y[signChangeIndex];
            T absXB = (T)((RealFieldElement)xB.abs());
            T absYB = (T)((RealFieldElement)yB.abs());
            int agingB = 0;

            while(true) {
               T maxX = (T)(((RealFieldElement)absXA.subtract(absXB)).getReal() < (double)0.0F ? absXB : absXA);
               T maxY = (T)(((RealFieldElement)absYA.subtract(absYB)).getReal() < (double)0.0F ? absYB : absYA);
               T xTol = (T)((RealFieldElement)this.absoluteAccuracy.add(this.relativeAccuracy.multiply(maxX)));
               if (((RealFieldElement)((RealFieldElement)xB.subtract(xA)).subtract(xTol)).getReal() <= (double)0.0F || ((RealFieldElement)maxY.subtract(this.functionValueAccuracy)).getReal() < (double)0.0F) {
                  switch (allowedSolution) {
                     case ANY_SIDE:
                        return ((RealFieldElement)absYA.subtract(absYB)).getReal() < (double)0.0F ? xA : xB;
                     case LEFT_SIDE:
                        return xA;
                     case RIGHT_SIDE:
                        return xB;
                     case BELOW_SIDE:
                        return yA.getReal() <= (double)0.0F ? xA : xB;
                     case ABOVE_SIDE:
                        return yA.getReal() < (double)0.0F ? xB : xA;
                     default:
                        throw new MathInternalError((Throwable)null);
                  }
               }

               T targetY;
               if (agingA >= 2) {
                  targetY = (T)((RealFieldElement)((RealFieldElement)yB.divide((double)16.0F)).negate());
               } else if (agingB >= 2) {
                  targetY = (T)((RealFieldElement)((RealFieldElement)yA.divide((double)16.0F)).negate());
               } else {
                  targetY = zero;
               }

               int start = 0;
               int end = nbPoints;

               T nextX;
               do {
                  System.arraycopy(x, start, tmpX, start, end - start);
                  nextX = (T)this.guessX(targetY, tmpX, y, start, end);
                  if (!(((RealFieldElement)nextX.subtract(xA)).getReal() > (double)0.0F) || !(((RealFieldElement)nextX.subtract(xB)).getReal() < (double)0.0F)) {
                     if (signChangeIndex - start >= end - signChangeIndex) {
                        ++start;
                     } else {
                        --end;
                     }

                     nextX = nan;
                  }
               } while(Double.isNaN(nextX.getReal()) && end - start > 1);

               if (Double.isNaN(nextX.getReal())) {
                  nextX = (T)((RealFieldElement)xA.add(((RealFieldElement)xB.subtract(xA)).divide((double)2.0F)));
                  start = signChangeIndex - 1;
                  end = signChangeIndex;
               }

               this.evaluations.increment();
               T nextY = (T)f.value(nextX);
               if (Precision.equals(nextY.getReal(), (double)0.0F, 1)) {
                  return nextX;
               }

               if (nbPoints > 2 && end - start != nbPoints) {
                  nbPoints = end - start;
                  System.arraycopy(x, start, x, 0, nbPoints);
                  System.arraycopy(y, start, y, 0, nbPoints);
                  signChangeIndex -= start;
               } else if (nbPoints == x.length) {
                  --nbPoints;
                  if (signChangeIndex >= (x.length + 1) / 2) {
                     System.arraycopy(x, 1, x, 0, nbPoints);
                     System.arraycopy(y, 1, y, 0, nbPoints);
                     --signChangeIndex;
                  }
               }

               System.arraycopy(x, signChangeIndex, x, signChangeIndex + 1, nbPoints - signChangeIndex);
               x[signChangeIndex] = nextX;
               System.arraycopy(y, signChangeIndex, y, signChangeIndex + 1, nbPoints - signChangeIndex);
               y[signChangeIndex] = nextY;
               ++nbPoints;
               if (((RealFieldElement)nextY.multiply(yA)).getReal() <= (double)0.0F) {
                  xB = nextX;
                  yB = nextY;
                  absYB = (T)((RealFieldElement)nextY.abs());
                  ++agingA;
                  agingB = 0;
               } else {
                  xA = nextX;
                  yA = nextY;
                  absYA = (T)((RealFieldElement)nextY.abs());
                  agingA = 0;
                  ++agingB;
                  ++signChangeIndex;
               }
            }
         }
      }
   }

   private RealFieldElement guessX(RealFieldElement targetY, RealFieldElement[] x, RealFieldElement[] y, int start, int end) {
      for(int i = start; i < end - 1; ++i) {
         int delta = i + 1 - start;

         for(int j = end - 1; j > i; --j) {
            x[j] = (RealFieldElement)((RealFieldElement)x[j].subtract(x[j - 1])).divide(y[j].subtract(y[j - delta]));
         }
      }

      T x0 = (T)((RealFieldElement)this.field.getZero());

      for(int j = end - 1; j >= start; --j) {
         x0 = (T)((RealFieldElement)x[j].add(x0.multiply(targetY.subtract(y[j]))));
      }

      return x0;
   }
}
