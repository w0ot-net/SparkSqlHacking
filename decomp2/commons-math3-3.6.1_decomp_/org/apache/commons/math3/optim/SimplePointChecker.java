package org.apache.commons.math3.optim;

import org.apache.commons.math3.exception.NotStrictlyPositiveException;
import org.apache.commons.math3.util.FastMath;
import org.apache.commons.math3.util.Pair;

public class SimplePointChecker extends AbstractConvergenceChecker {
   private static final int ITERATION_CHECK_DISABLED = -1;
   private final int maxIterationCount;

   public SimplePointChecker(double relativeThreshold, double absoluteThreshold) {
      super(relativeThreshold, absoluteThreshold);
      this.maxIterationCount = -1;
   }

   public SimplePointChecker(double relativeThreshold, double absoluteThreshold, int maxIter) {
      super(relativeThreshold, absoluteThreshold);
      if (maxIter <= 0) {
         throw new NotStrictlyPositiveException(maxIter);
      } else {
         this.maxIterationCount = maxIter;
      }
   }

   public boolean converged(int iteration, Pair previous, Pair current) {
      if (this.maxIterationCount != -1 && iteration >= this.maxIterationCount) {
         return true;
      } else {
         double[] p = (double[])previous.getKey();
         double[] c = (double[])current.getKey();

         for(int i = 0; i < p.length; ++i) {
            double pi = p[i];
            double ci = c[i];
            double difference = FastMath.abs(pi - ci);
            double size = FastMath.max(FastMath.abs(pi), FastMath.abs(ci));
            if (difference > size * this.getRelativeThreshold() && difference > this.getAbsoluteThreshold()) {
               return false;
            }
         }

         return true;
      }
   }
}
