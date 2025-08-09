package org.apache.commons.math3.ode.nonstiff;

import org.apache.commons.math3.util.FastMath;

public class DormandPrince853Integrator extends EmbeddedRungeKuttaIntegrator {
   private static final String METHOD_NAME = "Dormand-Prince 8 (5, 3)";
   private static final double[] STATIC_C = new double[]{((double)12.0F - (double)2.0F * FastMath.sqrt((double)6.0F)) / (double)135.0F, ((double)6.0F - FastMath.sqrt((double)6.0F)) / (double)45.0F, ((double)6.0F - FastMath.sqrt((double)6.0F)) / (double)30.0F, ((double)6.0F + FastMath.sqrt((double)6.0F)) / (double)30.0F, 0.3333333333333333, (double)0.25F, 0.3076923076923077, 0.6512820512820513, 0.6, 0.8571428571428571, (double)1.0F, (double)1.0F};
   private static final double[][] STATIC_A = new double[][]{{((double)12.0F - (double)2.0F * FastMath.sqrt((double)6.0F)) / (double)135.0F}, {((double)6.0F - FastMath.sqrt((double)6.0F)) / (double)180.0F, ((double)6.0F - FastMath.sqrt((double)6.0F)) / (double)60.0F}, {((double)6.0F - FastMath.sqrt((double)6.0F)) / (double)120.0F, (double)0.0F, ((double)6.0F - FastMath.sqrt((double)6.0F)) / (double)40.0F}, {((double)462.0F + (double)107.0F * FastMath.sqrt((double)6.0F)) / (double)3000.0F, (double)0.0F, ((double)-402.0F - (double)197.0F * FastMath.sqrt((double)6.0F)) / (double)1000.0F, ((double)168.0F + (double)73.0F * FastMath.sqrt((double)6.0F)) / (double)375.0F}, {0.037037037037037035, (double)0.0F, (double)0.0F, ((double)16.0F + FastMath.sqrt((double)6.0F)) / (double)108.0F, ((double)16.0F - FastMath.sqrt((double)6.0F)) / (double)108.0F}, {(double)0.037109375F, (double)0.0F, (double)0.0F, ((double)118.0F + (double)23.0F * FastMath.sqrt((double)6.0F)) / (double)1024.0F, ((double)118.0F - (double)23.0F * FastMath.sqrt((double)6.0F)) / (double)1024.0F, (double)-0.017578125F}, {0.03709200011850479, (double)0.0F, (double)0.0F, ((double)51544.0F + (double)4784.0F * FastMath.sqrt((double)6.0F)) / (double)371293.0F, ((double)51544.0F - (double)4784.0F * FastMath.sqrt((double)6.0F)) / (double)371293.0F, -0.015319437748624402, 0.008273789163814023}, {0.6241109587160757, (double)0.0F, (double)0.0F, (-1.324889724104E12 - 3.18801444819E11 * FastMath.sqrt((double)6.0F)) / 6.265569375E11, (-1.324889724104E12 + 3.18801444819E11 * FastMath.sqrt((double)6.0F)) / 6.265569375E11, 27.59209969944671, 20.154067550477894, -43.48988418106996}, {0.47766253643826434, (double)0.0F, (double)0.0F, ((double)-4521408.0F - (double)1137963.0F * FastMath.sqrt((double)6.0F)) / (double)2937500.0F, ((double)-4521408.0F + (double)1137963.0F * FastMath.sqrt((double)6.0F)) / (double)2937500.0F, 21.230051448181193, 15.279233632882423, -33.28821096898486, -0.020331201708508627}, {-0.9371424300859873, (double)0.0F, (double)0.0F, ((double)354216.0F + (double)94326.0F * FastMath.sqrt((double)6.0F)) / (double)112847.0F, ((double)354216.0F - (double)94326.0F * FastMath.sqrt((double)6.0F)) / (double)112847.0F, -8.149787010746927, -18.52006565999696, 22.739487099350505, 2.4936055526796523, -3.0467644718982196}, {2.273310147516538, (double)0.0F, (double)0.0F, ((double)-3457480.0F - (double)960905.0F * FastMath.sqrt((double)6.0F)) / (double)551636.0F, ((double)-3457480.0F + (double)960905.0F * FastMath.sqrt((double)6.0F)) / (double)551636.0F, -17.9589318631188, 27.94888452941996, -2.8589982771350235, -8.87285693353063, 12.360567175794303, 0.6433927460157636}, {0.054293734116568765, (double)0.0F, (double)0.0F, (double)0.0F, (double)0.0F, 4.450312892752409, 1.8915178993145003, -5.801203960010585, 0.3111643669578199, -0.1521609496625161, 0.20136540080403034, 0.04471061572777259}};
   private static final double[] STATIC_B = new double[]{0.054293734116568765, (double)0.0F, (double)0.0F, (double)0.0F, (double)0.0F, 4.450312892752409, 1.8915178993145003, -5.801203960010585, 0.3111643669578199, -0.1521609496625161, 0.20136540080403034, 0.04471061572777259, (double)0.0F};
   private static final double E1_01 = 0.01312004499419488;
   private static final double E1_06 = -1.2251564463762044;
   private static final double E1_07 = -0.4957589496572502;
   private static final double E1_08 = 1.6643771824549864;
   private static final double E1_09 = -0.35032884874997366;
   private static final double E1_10 = 0.3341791187130175;
   private static final double E1_11 = 0.08192320648511571;
   private static final double E1_12 = -0.022355307863886294;
   private static final double E2_01 = -0.18980075407240762;
   private static final double E2_06 = 4.450312892752409;
   private static final double E2_07 = 1.8915178993145003;
   private static final double E2_08 = -5.801203960010585;
   private static final double E2_09 = -0.42268232132379197;
   private static final double E2_10 = -0.1521609496625161;
   private static final double E2_11 = 0.20136540080403034;
   private static final double E2_12 = 0.022651792198360825;

   public DormandPrince853Integrator(double minStep, double maxStep, double scalAbsoluteTolerance, double scalRelativeTolerance) {
      super("Dormand-Prince 8 (5, 3)", true, STATIC_C, STATIC_A, STATIC_B, new DormandPrince853StepInterpolator(), minStep, maxStep, scalAbsoluteTolerance, scalRelativeTolerance);
   }

   public DormandPrince853Integrator(double minStep, double maxStep, double[] vecAbsoluteTolerance, double[] vecRelativeTolerance) {
      super("Dormand-Prince 8 (5, 3)", true, STATIC_C, STATIC_A, STATIC_B, new DormandPrince853StepInterpolator(), minStep, maxStep, vecAbsoluteTolerance, vecRelativeTolerance);
   }

   public int getOrder() {
      return 8;
   }

   protected double estimateError(double[][] yDotK, double[] y0, double[] y1, double h) {
      double error1 = (double)0.0F;
      double error2 = (double)0.0F;

      for(int j = 0; j < this.mainSetDimension; ++j) {
         double errSum1 = 0.01312004499419488 * yDotK[0][j] + -1.2251564463762044 * yDotK[5][j] + -0.4957589496572502 * yDotK[6][j] + 1.6643771824549864 * yDotK[7][j] + -0.35032884874997366 * yDotK[8][j] + 0.3341791187130175 * yDotK[9][j] + 0.08192320648511571 * yDotK[10][j] + -0.022355307863886294 * yDotK[11][j];
         double errSum2 = -0.18980075407240762 * yDotK[0][j] + 4.450312892752409 * yDotK[5][j] + 1.8915178993145003 * yDotK[6][j] + -5.801203960010585 * yDotK[7][j] + -0.42268232132379197 * yDotK[8][j] + -0.1521609496625161 * yDotK[9][j] + 0.20136540080403034 * yDotK[10][j] + 0.022651792198360825 * yDotK[11][j];
         double yScale = FastMath.max(FastMath.abs(y0[j]), FastMath.abs(y1[j]));
         double tol = this.vecAbsoluteTolerance == null ? this.scalAbsoluteTolerance + this.scalRelativeTolerance * yScale : this.vecAbsoluteTolerance[j] + this.vecRelativeTolerance[j] * yScale;
         double ratio1 = errSum1 / tol;
         error1 += ratio1 * ratio1;
         double ratio2 = errSum2 / tol;
         error2 += ratio2 * ratio2;
      }

      double den = error1 + 0.01 * error2;
      if (den <= (double)0.0F) {
         den = (double)1.0F;
      }

      return FastMath.abs(h) * error1 / FastMath.sqrt((double)this.mainSetDimension * den);
   }
}
