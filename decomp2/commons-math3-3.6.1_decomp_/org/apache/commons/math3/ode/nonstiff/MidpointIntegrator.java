package org.apache.commons.math3.ode.nonstiff;

public class MidpointIntegrator extends RungeKuttaIntegrator {
   private static final double[] STATIC_C = new double[]{(double)0.5F};
   private static final double[][] STATIC_A = new double[][]{{(double)0.5F}};
   private static final double[] STATIC_B = new double[]{(double)0.0F, (double)1.0F};

   public MidpointIntegrator(double step) {
      super("midpoint", STATIC_C, STATIC_A, STATIC_B, new MidpointStepInterpolator(), step);
   }
}
