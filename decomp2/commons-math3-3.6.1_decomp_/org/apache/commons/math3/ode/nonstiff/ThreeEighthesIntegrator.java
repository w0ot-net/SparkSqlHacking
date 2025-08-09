package org.apache.commons.math3.ode.nonstiff;

public class ThreeEighthesIntegrator extends RungeKuttaIntegrator {
   private static final double[] STATIC_C = new double[]{0.3333333333333333, 0.6666666666666666, (double)1.0F};
   private static final double[][] STATIC_A = new double[][]{{0.3333333333333333}, {-0.3333333333333333, (double)1.0F}, {(double)1.0F, (double)-1.0F, (double)1.0F}};
   private static final double[] STATIC_B = new double[]{(double)0.125F, (double)0.375F, (double)0.375F, (double)0.125F};

   public ThreeEighthesIntegrator(double step) {
      super("3/8", STATIC_C, STATIC_A, STATIC_B, new ThreeEighthesStepInterpolator(), step);
   }
}
