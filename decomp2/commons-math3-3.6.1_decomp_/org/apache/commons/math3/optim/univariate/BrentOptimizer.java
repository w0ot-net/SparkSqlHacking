package org.apache.commons.math3.optim.univariate;

import org.apache.commons.math3.exception.NotStrictlyPositiveException;
import org.apache.commons.math3.exception.NumberIsTooSmallException;
import org.apache.commons.math3.optim.ConvergenceChecker;
import org.apache.commons.math3.optim.nonlinear.scalar.GoalType;
import org.apache.commons.math3.util.FastMath;
import org.apache.commons.math3.util.Precision;

public class BrentOptimizer extends UnivariateOptimizer {
   private static final double GOLDEN_SECTION = (double)0.5F * ((double)3.0F - FastMath.sqrt((double)5.0F));
   private static final double MIN_RELATIVE_TOLERANCE = (double)2.0F * FastMath.ulp((double)1.0F);
   private final double relativeThreshold;
   private final double absoluteThreshold;

   public BrentOptimizer(double rel, double abs, ConvergenceChecker checker) {
      super(checker);
      if (rel < MIN_RELATIVE_TOLERANCE) {
         throw new NumberIsTooSmallException(rel, MIN_RELATIVE_TOLERANCE, true);
      } else if (abs <= (double)0.0F) {
         throw new NotStrictlyPositiveException(abs);
      } else {
         this.relativeThreshold = rel;
         this.absoluteThreshold = abs;
      }
   }

   public BrentOptimizer(double rel, double abs) {
      this(rel, abs, (ConvergenceChecker)null);
   }

   protected UnivariatePointValuePair doOptimize() {
      boolean isMinim = this.getGoalType() == GoalType.MINIMIZE;
      double lo = this.getMin();
      double mid = this.getStartValue();
      double hi = this.getMax();
      ConvergenceChecker<UnivariatePointValuePair> checker = this.getConvergenceChecker();
      double a;
      double b;
      if (lo < hi) {
         a = lo;
         b = hi;
      } else {
         a = hi;
         b = lo;
      }

      double x = mid;
      double v = mid;
      double w = mid;
      double d = (double)0.0F;
      double e = (double)0.0F;
      double fx = this.computeObjectiveValue(mid);
      if (!isMinim) {
         fx = -fx;
      }

      double fv = fx;
      double fw = fx;
      UnivariatePointValuePair previous = null;
      UnivariatePointValuePair current = new UnivariatePointValuePair(mid, isMinim ? fx : -fx);
      UnivariatePointValuePair best = current;

      while(true) {
         double m = (double)0.5F * (a + b);
         double tol1 = this.relativeThreshold * FastMath.abs(x) + this.absoluteThreshold;
         double tol2 = (double)2.0F * tol1;
         boolean stop = FastMath.abs(x - m) <= tol2 - (double)0.5F * (b - a);
         if (stop) {
            return this.best(best, this.best(previous, current, isMinim), isMinim);
         }

         double p = (double)0.0F;
         double q = (double)0.0F;
         double r = (double)0.0F;
         double u = (double)0.0F;
         if (FastMath.abs(e) > tol1) {
            r = (x - w) * (fx - fv);
            q = (x - v) * (fx - fw);
            p = (x - v) * q - (x - w) * r;
            q = (double)2.0F * (q - r);
            if (q > (double)0.0F) {
               p = -p;
            } else {
               q = -q;
            }

            r = e;
            e = d;
            if (p > q * (a - x) && p < q * (b - x) && FastMath.abs(p) < FastMath.abs((double)0.5F * q * r)) {
               d = p / q;
               u = x + d;
               if (u - a < tol2 || b - u < tol2) {
                  if (x <= m) {
                     d = tol1;
                  } else {
                     d = -tol1;
                  }
               }
            } else {
               if (x < m) {
                  e = b - x;
               } else {
                  e = a - x;
               }

               d = GOLDEN_SECTION * e;
            }
         } else {
            if (x < m) {
               e = b - x;
            } else {
               e = a - x;
            }

            d = GOLDEN_SECTION * e;
         }

         if (FastMath.abs(d) < tol1) {
            if (d >= (double)0.0F) {
               u = x + tol1;
            } else {
               u = x - tol1;
            }
         } else {
            u = x + d;
         }

         double fu = this.computeObjectiveValue(u);
         if (!isMinim) {
            fu = -fu;
         }

         previous = current;
         current = new UnivariatePointValuePair(u, isMinim ? fu : -fu);
         best = this.best(best, this.best(previous, current, isMinim), isMinim);
         if (checker != null && checker.converged(this.getIterations(), previous, current)) {
            return best;
         }

         if (fu <= fx) {
            if (u < x) {
               b = x;
            } else {
               a = x;
            }

            v = w;
            fv = fw;
            w = x;
            fw = fx;
            x = u;
            fx = fu;
         } else {
            if (u < x) {
               a = u;
            } else {
               b = u;
            }

            if (!(fu <= fw) && !Precision.equals(w, x)) {
               if (fu <= fv || Precision.equals(v, x) || Precision.equals(v, w)) {
                  v = u;
                  fv = fu;
               }
            } else {
               v = w;
               fv = fw;
               w = u;
               fw = fu;
            }
         }

         this.incrementIterationCount();
      }
   }

   private UnivariatePointValuePair best(UnivariatePointValuePair a, UnivariatePointValuePair b, boolean isMinim) {
      if (a == null) {
         return b;
      } else if (b == null) {
         return a;
      } else if (isMinim) {
         return a.getValue() <= b.getValue() ? a : b;
      } else {
         return a.getValue() >= b.getValue() ? a : b;
      }
   }
}
