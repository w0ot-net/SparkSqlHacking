package org.apache.commons.math3.analysis.integration;

import org.apache.commons.math3.exception.MathIllegalArgumentException;
import org.apache.commons.math3.exception.MaxCountExceededException;
import org.apache.commons.math3.exception.NotStrictlyPositiveException;
import org.apache.commons.math3.exception.NumberIsTooSmallException;
import org.apache.commons.math3.exception.TooManyEvaluationsException;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.util.FastMath;

/** @deprecated */
@Deprecated
public class LegendreGaussIntegrator extends BaseAbstractUnivariateIntegrator {
   private static final double[] ABSCISSAS_2 = new double[]{(double)-1.0F / FastMath.sqrt((double)3.0F), (double)1.0F / FastMath.sqrt((double)3.0F)};
   private static final double[] WEIGHTS_2 = new double[]{(double)1.0F, (double)1.0F};
   private static final double[] ABSCISSAS_3 = new double[]{-FastMath.sqrt(0.6), (double)0.0F, FastMath.sqrt(0.6)};
   private static final double[] WEIGHTS_3 = new double[]{0.5555555555555556, 0.8888888888888888, 0.5555555555555556};
   private static final double[] ABSCISSAS_4 = new double[]{-FastMath.sqrt(((double)15.0F + (double)2.0F * FastMath.sqrt((double)30.0F)) / (double)35.0F), -FastMath.sqrt(((double)15.0F - (double)2.0F * FastMath.sqrt((double)30.0F)) / (double)35.0F), FastMath.sqrt(((double)15.0F - (double)2.0F * FastMath.sqrt((double)30.0F)) / (double)35.0F), FastMath.sqrt(((double)15.0F + (double)2.0F * FastMath.sqrt((double)30.0F)) / (double)35.0F)};
   private static final double[] WEIGHTS_4 = new double[]{((double)90.0F - (double)5.0F * FastMath.sqrt((double)30.0F)) / (double)180.0F, ((double)90.0F + (double)5.0F * FastMath.sqrt((double)30.0F)) / (double)180.0F, ((double)90.0F + (double)5.0F * FastMath.sqrt((double)30.0F)) / (double)180.0F, ((double)90.0F - (double)5.0F * FastMath.sqrt((double)30.0F)) / (double)180.0F};
   private static final double[] ABSCISSAS_5 = new double[]{-FastMath.sqrt(((double)35.0F + (double)2.0F * FastMath.sqrt((double)70.0F)) / (double)63.0F), -FastMath.sqrt(((double)35.0F - (double)2.0F * FastMath.sqrt((double)70.0F)) / (double)63.0F), (double)0.0F, FastMath.sqrt(((double)35.0F - (double)2.0F * FastMath.sqrt((double)70.0F)) / (double)63.0F), FastMath.sqrt(((double)35.0F + (double)2.0F * FastMath.sqrt((double)70.0F)) / (double)63.0F)};
   private static final double[] WEIGHTS_5 = new double[]{((double)322.0F - (double)13.0F * FastMath.sqrt((double)70.0F)) / (double)900.0F, ((double)322.0F + (double)13.0F * FastMath.sqrt((double)70.0F)) / (double)900.0F, 0.5688888888888889, ((double)322.0F + (double)13.0F * FastMath.sqrt((double)70.0F)) / (double)900.0F, ((double)322.0F - (double)13.0F * FastMath.sqrt((double)70.0F)) / (double)900.0F};
   private final double[] abscissas;
   private final double[] weights;

   public LegendreGaussIntegrator(int n, double relativeAccuracy, double absoluteAccuracy, int minimalIterationCount, int maximalIterationCount) throws MathIllegalArgumentException, NotStrictlyPositiveException, NumberIsTooSmallException {
      super(relativeAccuracy, absoluteAccuracy, minimalIterationCount, maximalIterationCount);
      switch (n) {
         case 2:
            this.abscissas = ABSCISSAS_2;
            this.weights = WEIGHTS_2;
            break;
         case 3:
            this.abscissas = ABSCISSAS_3;
            this.weights = WEIGHTS_3;
            break;
         case 4:
            this.abscissas = ABSCISSAS_4;
            this.weights = WEIGHTS_4;
            break;
         case 5:
            this.abscissas = ABSCISSAS_5;
            this.weights = WEIGHTS_5;
            break;
         default:
            throw new MathIllegalArgumentException(LocalizedFormats.N_POINTS_GAUSS_LEGENDRE_INTEGRATOR_NOT_SUPPORTED, new Object[]{n, 2, 5});
      }

   }

   public LegendreGaussIntegrator(int n, double relativeAccuracy, double absoluteAccuracy) throws MathIllegalArgumentException {
      this(n, relativeAccuracy, absoluteAccuracy, 3, Integer.MAX_VALUE);
   }

   public LegendreGaussIntegrator(int n, int minimalIterationCount, int maximalIterationCount) throws MathIllegalArgumentException {
      this(n, 1.0E-6, 1.0E-15, minimalIterationCount, maximalIterationCount);
   }

   protected double doIntegrate() throws MathIllegalArgumentException, TooManyEvaluationsException, MaxCountExceededException {
      double oldt = this.stage(1);
      int n = 2;

      while(true) {
         double t = this.stage(n);
         double delta = FastMath.abs(t - oldt);
         double limit = FastMath.max(this.getAbsoluteAccuracy(), this.getRelativeAccuracy() * (FastMath.abs(oldt) + FastMath.abs(t)) * (double)0.5F);
         if (this.getIterations() + 1 >= this.getMinimalIterationCount() && delta <= limit) {
            return t;
         }

         double ratio = FastMath.min((double)4.0F, FastMath.pow(delta / limit, (double)0.5F / (double)this.abscissas.length));
         n = FastMath.max((int)(ratio * (double)n), n + 1);
         oldt = t;
         this.incrementCount();
      }
   }

   private double stage(int n) throws TooManyEvaluationsException {
      double step = (this.getMax() - this.getMin()) / (double)n;
      double halfStep = step / (double)2.0F;
      double midPoint = this.getMin() + halfStep;
      double sum = (double)0.0F;

      for(int i = 0; i < n; ++i) {
         for(int j = 0; j < this.abscissas.length; ++j) {
            sum += this.weights[j] * this.computeObjectiveValue(midPoint + halfStep * this.abscissas[j]);
         }

         midPoint += step;
      }

      return halfStep * sum;
   }
}
