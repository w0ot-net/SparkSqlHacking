package org.apache.commons.math3.ode.nonstiff;

import org.apache.commons.math3.util.FastMath;

public class HighamHall54Integrator extends EmbeddedRungeKuttaIntegrator {
   private static final String METHOD_NAME = "Higham-Hall 5(4)";
   private static final double[] STATIC_C = new double[]{0.2222222222222222, 0.3333333333333333, (double)0.5F, 0.6, (double)1.0F, (double)1.0F};
   private static final double[][] STATIC_A = new double[][]{{0.2222222222222222}, {0.08333333333333333, (double)0.25F}, {(double)0.125F, (double)0.0F, (double)0.375F}, {0.182, -0.27, 0.624, 0.064}, {-0.55, 1.35, 2.4, -7.2, (double)5.0F}, {0.08333333333333333, (double)0.0F, (double)0.84375F, -1.3333333333333333, 1.3020833333333333, 0.10416666666666667}};
   private static final double[] STATIC_B = new double[]{0.08333333333333333, (double)0.0F, (double)0.84375F, -1.3333333333333333, 1.3020833333333333, 0.10416666666666667, (double)0.0F};
   private static final double[] STATIC_E = new double[]{-0.05, (double)0.0F, 0.50625, -1.2, (double)0.78125F, (double)0.0625F, -0.1};

   public HighamHall54Integrator(double minStep, double maxStep, double scalAbsoluteTolerance, double scalRelativeTolerance) {
      super("Higham-Hall 5(4)", false, STATIC_C, STATIC_A, STATIC_B, new HighamHall54StepInterpolator(), minStep, maxStep, scalAbsoluteTolerance, scalRelativeTolerance);
   }

   public HighamHall54Integrator(double minStep, double maxStep, double[] vecAbsoluteTolerance, double[] vecRelativeTolerance) {
      super("Higham-Hall 5(4)", false, STATIC_C, STATIC_A, STATIC_B, new HighamHall54StepInterpolator(), minStep, maxStep, vecAbsoluteTolerance, vecRelativeTolerance);
   }

   public int getOrder() {
      return 5;
   }

   protected double estimateError(double[][] yDotK, double[] y0, double[] y1, double h) {
      double error = (double)0.0F;

      for(int j = 0; j < this.mainSetDimension; ++j) {
         double errSum = STATIC_E[0] * yDotK[0][j];

         for(int l = 1; l < STATIC_E.length; ++l) {
            errSum += STATIC_E[l] * yDotK[l][j];
         }

         double yScale = FastMath.max(FastMath.abs(y0[j]), FastMath.abs(y1[j]));
         double tol = this.vecAbsoluteTolerance == null ? this.scalAbsoluteTolerance + this.scalRelativeTolerance * yScale : this.vecAbsoluteTolerance[j] + this.vecRelativeTolerance[j] * yScale;
         double ratio = h * errSum / tol;
         error += ratio * ratio;
      }

      return FastMath.sqrt(error / (double)this.mainSetDimension);
   }
}
