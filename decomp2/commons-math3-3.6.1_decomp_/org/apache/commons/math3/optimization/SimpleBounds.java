package org.apache.commons.math3.optimization;

/** @deprecated */
@Deprecated
public class SimpleBounds implements OptimizationData {
   private final double[] lower;
   private final double[] upper;

   public SimpleBounds(double[] lB, double[] uB) {
      this.lower = (double[])(([D)lB).clone();
      this.upper = (double[])(([D)uB).clone();
   }

   public double[] getLower() {
      return (double[])this.lower.clone();
   }

   public double[] getUpper() {
      return (double[])this.upper.clone();
   }
}
