package org.apache.commons.math3.optim;

public abstract class AbstractConvergenceChecker implements ConvergenceChecker {
   private final double relativeThreshold;
   private final double absoluteThreshold;

   public AbstractConvergenceChecker(double relativeThreshold, double absoluteThreshold) {
      this.relativeThreshold = relativeThreshold;
      this.absoluteThreshold = absoluteThreshold;
   }

   public double getRelativeThreshold() {
      return this.relativeThreshold;
   }

   public double getAbsoluteThreshold() {
      return this.absoluteThreshold;
   }

   public abstract boolean converged(int var1, Object var2, Object var3);
}
