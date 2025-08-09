package org.apache.commons.math3.ode.nonstiff;

public class ClassicalRungeKuttaIntegrator extends RungeKuttaIntegrator {
   private static final double[] STATIC_C = new double[]{(double)0.5F, (double)0.5F, (double)1.0F};
   private static final double[][] STATIC_A = new double[][]{{(double)0.5F}, {(double)0.0F, (double)0.5F}, {(double)0.0F, (double)0.0F, (double)1.0F}};
   private static final double[] STATIC_B = new double[]{0.16666666666666666, 0.3333333333333333, 0.3333333333333333, 0.16666666666666666};

   public ClassicalRungeKuttaIntegrator(double step) {
      super("classical Runge-Kutta", STATIC_C, STATIC_A, STATIC_B, new ClassicalRungeKuttaStepInterpolator(), step);
   }
}
