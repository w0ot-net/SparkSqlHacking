package org.apache.commons.math3.ode.nonstiff;

public class EulerIntegrator extends RungeKuttaIntegrator {
   private static final double[] STATIC_C = new double[0];
   private static final double[][] STATIC_A = new double[0][];
   private static final double[] STATIC_B = new double[]{(double)1.0F};

   public EulerIntegrator(double step) {
      super("Euler", STATIC_C, STATIC_A, STATIC_B, new EulerStepInterpolator(), step);
   }
}
