package org.apache.commons.math3.optimization;

import org.apache.commons.math3.util.Precision;

/** @deprecated */
@Deprecated
public abstract class AbstractConvergenceChecker implements ConvergenceChecker {
   /** @deprecated */
   @Deprecated
   private static final double DEFAULT_RELATIVE_THRESHOLD;
   /** @deprecated */
   @Deprecated
   private static final double DEFAULT_ABSOLUTE_THRESHOLD;
   private final double relativeThreshold;
   private final double absoluteThreshold;

   /** @deprecated */
   @Deprecated
   public AbstractConvergenceChecker() {
      this.relativeThreshold = DEFAULT_RELATIVE_THRESHOLD;
      this.absoluteThreshold = DEFAULT_ABSOLUTE_THRESHOLD;
   }

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

   static {
      DEFAULT_RELATIVE_THRESHOLD = (double)100.0F * Precision.EPSILON;
      DEFAULT_ABSOLUTE_THRESHOLD = (double)100.0F * Precision.SAFE_MIN;
   }
}
