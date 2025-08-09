package org.apache.commons.math3.optimization.univariate;

import org.apache.commons.math3.analysis.UnivariateFunction;
import org.apache.commons.math3.exception.MaxCountExceededException;
import org.apache.commons.math3.exception.NotStrictlyPositiveException;
import org.apache.commons.math3.exception.TooManyEvaluationsException;
import org.apache.commons.math3.optimization.GoalType;
import org.apache.commons.math3.util.FastMath;
import org.apache.commons.math3.util.Incrementor;

/** @deprecated */
@Deprecated
public class BracketFinder {
   private static final double EPS_MIN = 1.0E-21;
   private static final double GOLD = 1.618034;
   private final double growLimit;
   private final Incrementor evaluations;
   private double lo;
   private double hi;
   private double mid;
   private double fLo;
   private double fHi;
   private double fMid;

   public BracketFinder() {
      this((double)100.0F, 50);
   }

   public BracketFinder(double growLimit, int maxEvaluations) {
      this.evaluations = new Incrementor();
      if (growLimit <= (double)0.0F) {
         throw new NotStrictlyPositiveException(growLimit);
      } else if (maxEvaluations <= 0) {
         throw new NotStrictlyPositiveException(maxEvaluations);
      } else {
         this.growLimit = growLimit;
         this.evaluations.setMaximalCount(maxEvaluations);
      }
   }

   public void search(UnivariateFunction func, GoalType goal, double xA, double xB) {
      boolean isMinim;
      double fA;
      double fB;
      label84: {
         this.evaluations.resetCount();
         isMinim = goal == GoalType.MINIMIZE;
         fA = this.eval(func, xA);
         fB = this.eval(func, xB);
         if (isMinim) {
            if (!(fA < fB)) {
               break label84;
            }
         } else if (!(fA > fB)) {
            break label84;
         }

         double tmp = xA;
         xA = xB;
         xB = tmp;
         tmp = fA;
         fA = fB;
         fB = tmp;
      }

      double xC = xB + 1.618034 * (xB - xA);
      double fC = this.eval(func, xC);

      while(true) {
         if (isMinim) {
            if (!(fC < fB)) {
               break;
            }
         } else if (!(fC > fB)) {
            break;
         }

         double w;
         double fW;
         label74: {
            double tmp1 = (xB - xA) * (fB - fC);
            double tmp2 = (xB - xC) * (fB - fA);
            double val = tmp2 - tmp1;
            double denom = FastMath.abs(val) < 1.0E-21 ? 2.0E-21 : (double)2.0F * val;
            w = xB - ((xB - xC) * tmp2 - (xB - xA) * tmp1) / denom;
            double wLim = xB + this.growLimit * (xC - xB);
            if ((w - xC) * (xB - w) > (double)0.0F) {
               fW = this.eval(func, w);
               if (isMinim) {
                  if (fW < fC) {
                     break label74;
                  }
               } else if (fW > fC) {
                  break label74;
               }

               label70: {
                  if (isMinim) {
                     if (!(fW > fB)) {
                        break label70;
                     }
                  } else if (!(fW < fB)) {
                     break label70;
                  }

                  xC = w;
                  fC = fW;
                  break;
               }

               w = xC + 1.618034 * (xC - xB);
               fW = this.eval(func, w);
            } else if ((w - wLim) * (wLim - xC) >= (double)0.0F) {
               w = wLim;
               fW = this.eval(func, wLim);
            } else if ((w - wLim) * (xC - w) > (double)0.0F) {
               label58: {
                  fW = this.eval(func, w);
                  if (isMinim) {
                     if (!(fW < fC)) {
                        break label58;
                     }
                  } else if (!(fW > fC)) {
                     break label58;
                  }

                  xB = xC;
                  xC = w;
                  w += 1.618034 * (w - xB);
                  fB = fC;
                  fC = fW;
                  fW = this.eval(func, w);
               }
            } else {
               w = xC + 1.618034 * (xC - xB);
               fW = this.eval(func, w);
            }

            xA = xB;
            fA = fB;
            xB = xC;
            fB = fC;
            xC = w;
            fC = fW;
            continue;
         }

         xA = xB;
         xB = w;
         fA = fB;
         fB = fW;
         break;
      }

      this.lo = xA;
      this.fLo = fA;
      this.mid = xB;
      this.fMid = fB;
      this.hi = xC;
      this.fHi = fC;
      if (this.lo > this.hi) {
         double tmp = this.lo;
         this.lo = this.hi;
         this.hi = tmp;
         tmp = this.fLo;
         this.fLo = this.fHi;
         this.fHi = tmp;
      }

   }

   public int getMaxEvaluations() {
      return this.evaluations.getMaximalCount();
   }

   public int getEvaluations() {
      return this.evaluations.getCount();
   }

   public double getLo() {
      return this.lo;
   }

   public double getFLo() {
      return this.fLo;
   }

   public double getHi() {
      return this.hi;
   }

   public double getFHi() {
      return this.fHi;
   }

   public double getMid() {
      return this.mid;
   }

   public double getFMid() {
      return this.fMid;
   }

   private double eval(UnivariateFunction f, double x) {
      try {
         this.evaluations.incrementCount();
      } catch (MaxCountExceededException e) {
         throw new TooManyEvaluationsException(e.getMax());
      }

      return f.value(x);
   }
}
