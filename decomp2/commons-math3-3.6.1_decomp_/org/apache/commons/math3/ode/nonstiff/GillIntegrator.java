package org.apache.commons.math3.ode.nonstiff;

import org.apache.commons.math3.util.FastMath;

public class GillIntegrator extends RungeKuttaIntegrator {
   private static final double[] STATIC_C = new double[]{(double)0.5F, (double)0.5F, (double)1.0F};
   private static final double[][] STATIC_A = new double[][]{{(double)0.5F}, {(FastMath.sqrt((double)2.0F) - (double)1.0F) / (double)2.0F, ((double)2.0F - FastMath.sqrt((double)2.0F)) / (double)2.0F}, {(double)0.0F, -FastMath.sqrt((double)2.0F) / (double)2.0F, ((double)2.0F + FastMath.sqrt((double)2.0F)) / (double)2.0F}};
   private static final double[] STATIC_B = new double[]{0.16666666666666666, ((double)2.0F - FastMath.sqrt((double)2.0F)) / (double)6.0F, ((double)2.0F + FastMath.sqrt((double)2.0F)) / (double)6.0F, 0.16666666666666666};

   public GillIntegrator(double step) {
      super("Gill", STATIC_C, STATIC_A, STATIC_B, new GillStepInterpolator(), step);
   }
}
