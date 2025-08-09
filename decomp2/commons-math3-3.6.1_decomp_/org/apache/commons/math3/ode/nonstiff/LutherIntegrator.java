package org.apache.commons.math3.ode.nonstiff;

import org.apache.commons.math3.util.FastMath;

public class LutherIntegrator extends RungeKuttaIntegrator {
   private static final double Q = FastMath.sqrt((double)21.0F);
   private static final double[] STATIC_C;
   private static final double[][] STATIC_A;
   private static final double[] STATIC_B;

   public LutherIntegrator(double step) {
      super("Luther", STATIC_C, STATIC_A, STATIC_B, new LutherStepInterpolator(), step);
   }

   static {
      STATIC_C = new double[]{(double)1.0F, (double)0.5F, 0.6666666666666666, ((double)7.0F - Q) / (double)14.0F, ((double)7.0F + Q) / (double)14.0F, (double)1.0F};
      STATIC_A = new double[][]{{(double)1.0F}, {(double)0.375F, (double)0.125F}, {0.2962962962962963, 0.07407407407407407, 0.2962962962962963}, {((double)-21.0F + (double)9.0F * Q) / (double)392.0F, ((double)-56.0F + (double)8.0F * Q) / (double)392.0F, ((double)336.0F - (double)48.0F * Q) / (double)392.0F, ((double)-63.0F + (double)3.0F * Q) / (double)392.0F}, {((double)-1155.0F - (double)255.0F * Q) / (double)1960.0F, ((double)-280.0F - (double)40.0F * Q) / (double)1960.0F, ((double)0.0F - (double)320.0F * Q) / (double)1960.0F, ((double)63.0F + (double)363.0F * Q) / (double)1960.0F, ((double)2352.0F + (double)392.0F * Q) / (double)1960.0F}, {((double)330.0F + (double)105.0F * Q) / (double)180.0F, ((double)120.0F + (double)0.0F * Q) / (double)180.0F, ((double)-200.0F + (double)280.0F * Q) / (double)180.0F, ((double)126.0F - (double)189.0F * Q) / (double)180.0F, ((double)-686.0F - (double)126.0F * Q) / (double)180.0F, ((double)490.0F - (double)70.0F * Q) / (double)180.0F}};
      STATIC_B = new double[]{0.05, (double)0.0F, 0.35555555555555557, (double)0.0F, 0.2722222222222222, 0.2722222222222222, 0.05};
   }
}
