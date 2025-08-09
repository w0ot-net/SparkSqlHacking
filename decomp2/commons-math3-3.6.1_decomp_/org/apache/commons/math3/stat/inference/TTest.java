package org.apache.commons.math3.stat.inference;

import org.apache.commons.math3.distribution.TDistribution;
import org.apache.commons.math3.exception.DimensionMismatchException;
import org.apache.commons.math3.exception.MathIllegalArgumentException;
import org.apache.commons.math3.exception.MaxCountExceededException;
import org.apache.commons.math3.exception.NoDataException;
import org.apache.commons.math3.exception.NotStrictlyPositiveException;
import org.apache.commons.math3.exception.NullArgumentException;
import org.apache.commons.math3.exception.NumberIsTooSmallException;
import org.apache.commons.math3.exception.OutOfRangeException;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.random.RandomGenerator;
import org.apache.commons.math3.stat.StatUtils;
import org.apache.commons.math3.stat.descriptive.StatisticalSummary;
import org.apache.commons.math3.util.FastMath;

public class TTest {
   public double pairedT(double[] sample1, double[] sample2) throws NullArgumentException, NoDataException, DimensionMismatchException, NumberIsTooSmallException {
      this.checkSampleData(sample1);
      this.checkSampleData(sample2);
      double meanDifference = StatUtils.meanDifference(sample1, sample2);
      return this.t(meanDifference, (double)0.0F, StatUtils.varianceDifference(sample1, sample2, meanDifference), (double)sample1.length);
   }

   public double pairedTTest(double[] sample1, double[] sample2) throws NullArgumentException, NoDataException, DimensionMismatchException, NumberIsTooSmallException, MaxCountExceededException {
      double meanDifference = StatUtils.meanDifference(sample1, sample2);
      return this.tTest(meanDifference, (double)0.0F, StatUtils.varianceDifference(sample1, sample2, meanDifference), (double)sample1.length);
   }

   public boolean pairedTTest(double[] sample1, double[] sample2, double alpha) throws NullArgumentException, NoDataException, DimensionMismatchException, NumberIsTooSmallException, OutOfRangeException, MaxCountExceededException {
      this.checkSignificanceLevel(alpha);
      return this.pairedTTest(sample1, sample2) < alpha;
   }

   public double t(double mu, double[] observed) throws NullArgumentException, NumberIsTooSmallException {
      this.checkSampleData(observed);
      return this.t(StatUtils.mean(observed), mu, StatUtils.variance(observed), (double)observed.length);
   }

   public double t(double mu, StatisticalSummary sampleStats) throws NullArgumentException, NumberIsTooSmallException {
      this.checkSampleData(sampleStats);
      return this.t(sampleStats.getMean(), mu, sampleStats.getVariance(), (double)sampleStats.getN());
   }

   public double homoscedasticT(double[] sample1, double[] sample2) throws NullArgumentException, NumberIsTooSmallException {
      this.checkSampleData(sample1);
      this.checkSampleData(sample2);
      return this.homoscedasticT(StatUtils.mean(sample1), StatUtils.mean(sample2), StatUtils.variance(sample1), StatUtils.variance(sample2), (double)sample1.length, (double)sample2.length);
   }

   public double t(double[] sample1, double[] sample2) throws NullArgumentException, NumberIsTooSmallException {
      this.checkSampleData(sample1);
      this.checkSampleData(sample2);
      return this.t(StatUtils.mean(sample1), StatUtils.mean(sample2), StatUtils.variance(sample1), StatUtils.variance(sample2), (double)sample1.length, (double)sample2.length);
   }

   public double t(StatisticalSummary sampleStats1, StatisticalSummary sampleStats2) throws NullArgumentException, NumberIsTooSmallException {
      this.checkSampleData(sampleStats1);
      this.checkSampleData(sampleStats2);
      return this.t(sampleStats1.getMean(), sampleStats2.getMean(), sampleStats1.getVariance(), sampleStats2.getVariance(), (double)sampleStats1.getN(), (double)sampleStats2.getN());
   }

   public double homoscedasticT(StatisticalSummary sampleStats1, StatisticalSummary sampleStats2) throws NullArgumentException, NumberIsTooSmallException {
      this.checkSampleData(sampleStats1);
      this.checkSampleData(sampleStats2);
      return this.homoscedasticT(sampleStats1.getMean(), sampleStats2.getMean(), sampleStats1.getVariance(), sampleStats2.getVariance(), (double)sampleStats1.getN(), (double)sampleStats2.getN());
   }

   public double tTest(double mu, double[] sample) throws NullArgumentException, NumberIsTooSmallException, MaxCountExceededException {
      this.checkSampleData(sample);
      return this.tTest(StatUtils.mean(sample), mu, StatUtils.variance(sample), (double)sample.length);
   }

   public boolean tTest(double mu, double[] sample, double alpha) throws NullArgumentException, NumberIsTooSmallException, OutOfRangeException, MaxCountExceededException {
      this.checkSignificanceLevel(alpha);
      return this.tTest(mu, sample) < alpha;
   }

   public double tTest(double mu, StatisticalSummary sampleStats) throws NullArgumentException, NumberIsTooSmallException, MaxCountExceededException {
      this.checkSampleData(sampleStats);
      return this.tTest(sampleStats.getMean(), mu, sampleStats.getVariance(), (double)sampleStats.getN());
   }

   public boolean tTest(double mu, StatisticalSummary sampleStats, double alpha) throws NullArgumentException, NumberIsTooSmallException, OutOfRangeException, MaxCountExceededException {
      this.checkSignificanceLevel(alpha);
      return this.tTest(mu, sampleStats) < alpha;
   }

   public double tTest(double[] sample1, double[] sample2) throws NullArgumentException, NumberIsTooSmallException, MaxCountExceededException {
      this.checkSampleData(sample1);
      this.checkSampleData(sample2);
      return this.tTest(StatUtils.mean(sample1), StatUtils.mean(sample2), StatUtils.variance(sample1), StatUtils.variance(sample2), (double)sample1.length, (double)sample2.length);
   }

   public double homoscedasticTTest(double[] sample1, double[] sample2) throws NullArgumentException, NumberIsTooSmallException, MaxCountExceededException {
      this.checkSampleData(sample1);
      this.checkSampleData(sample2);
      return this.homoscedasticTTest(StatUtils.mean(sample1), StatUtils.mean(sample2), StatUtils.variance(sample1), StatUtils.variance(sample2), (double)sample1.length, (double)sample2.length);
   }

   public boolean tTest(double[] sample1, double[] sample2, double alpha) throws NullArgumentException, NumberIsTooSmallException, OutOfRangeException, MaxCountExceededException {
      this.checkSignificanceLevel(alpha);
      return this.tTest(sample1, sample2) < alpha;
   }

   public boolean homoscedasticTTest(double[] sample1, double[] sample2, double alpha) throws NullArgumentException, NumberIsTooSmallException, OutOfRangeException, MaxCountExceededException {
      this.checkSignificanceLevel(alpha);
      return this.homoscedasticTTest(sample1, sample2) < alpha;
   }

   public double tTest(StatisticalSummary sampleStats1, StatisticalSummary sampleStats2) throws NullArgumentException, NumberIsTooSmallException, MaxCountExceededException {
      this.checkSampleData(sampleStats1);
      this.checkSampleData(sampleStats2);
      return this.tTest(sampleStats1.getMean(), sampleStats2.getMean(), sampleStats1.getVariance(), sampleStats2.getVariance(), (double)sampleStats1.getN(), (double)sampleStats2.getN());
   }

   public double homoscedasticTTest(StatisticalSummary sampleStats1, StatisticalSummary sampleStats2) throws NullArgumentException, NumberIsTooSmallException, MaxCountExceededException {
      this.checkSampleData(sampleStats1);
      this.checkSampleData(sampleStats2);
      return this.homoscedasticTTest(sampleStats1.getMean(), sampleStats2.getMean(), sampleStats1.getVariance(), sampleStats2.getVariance(), (double)sampleStats1.getN(), (double)sampleStats2.getN());
   }

   public boolean tTest(StatisticalSummary sampleStats1, StatisticalSummary sampleStats2, double alpha) throws NullArgumentException, NumberIsTooSmallException, OutOfRangeException, MaxCountExceededException {
      this.checkSignificanceLevel(alpha);
      return this.tTest(sampleStats1, sampleStats2) < alpha;
   }

   protected double df(double v1, double v2, double n1, double n2) {
      return (v1 / n1 + v2 / n2) * (v1 / n1 + v2 / n2) / (v1 * v1 / (n1 * n1 * (n1 - (double)1.0F)) + v2 * v2 / (n2 * n2 * (n2 - (double)1.0F)));
   }

   protected double t(double m, double mu, double v, double n) {
      return (m - mu) / FastMath.sqrt(v / n);
   }

   protected double t(double m1, double m2, double v1, double v2, double n1, double n2) {
      return (m1 - m2) / FastMath.sqrt(v1 / n1 + v2 / n2);
   }

   protected double homoscedasticT(double m1, double m2, double v1, double v2, double n1, double n2) {
      double pooledVariance = ((n1 - (double)1.0F) * v1 + (n2 - (double)1.0F) * v2) / (n1 + n2 - (double)2.0F);
      return (m1 - m2) / FastMath.sqrt(pooledVariance * ((double)1.0F / n1 + (double)1.0F / n2));
   }

   protected double tTest(double m, double mu, double v, double n) throws MaxCountExceededException, MathIllegalArgumentException {
      double t = FastMath.abs(this.t(m, mu, v, n));
      TDistribution distribution = new TDistribution((RandomGenerator)null, n - (double)1.0F);
      return (double)2.0F * distribution.cumulativeProbability(-t);
   }

   protected double tTest(double m1, double m2, double v1, double v2, double n1, double n2) throws MaxCountExceededException, NotStrictlyPositiveException {
      double t = FastMath.abs(this.t(m1, m2, v1, v2, n1, n2));
      double degreesOfFreedom = this.df(v1, v2, n1, n2);
      TDistribution distribution = new TDistribution((RandomGenerator)null, degreesOfFreedom);
      return (double)2.0F * distribution.cumulativeProbability(-t);
   }

   protected double homoscedasticTTest(double m1, double m2, double v1, double v2, double n1, double n2) throws MaxCountExceededException, NotStrictlyPositiveException {
      double t = FastMath.abs(this.homoscedasticT(m1, m2, v1, v2, n1, n2));
      double degreesOfFreedom = n1 + n2 - (double)2.0F;
      TDistribution distribution = new TDistribution((RandomGenerator)null, degreesOfFreedom);
      return (double)2.0F * distribution.cumulativeProbability(-t);
   }

   private void checkSignificanceLevel(double alpha) throws OutOfRangeException {
      if (alpha <= (double)0.0F || alpha > (double)0.5F) {
         throw new OutOfRangeException(LocalizedFormats.SIGNIFICANCE_LEVEL, alpha, (double)0.0F, (double)0.5F);
      }
   }

   private void checkSampleData(double[] data) throws NullArgumentException, NumberIsTooSmallException {
      if (data == null) {
         throw new NullArgumentException();
      } else if (data.length < 2) {
         throw new NumberIsTooSmallException(LocalizedFormats.INSUFFICIENT_DATA_FOR_T_STATISTIC, data.length, 2, true);
      }
   }

   private void checkSampleData(StatisticalSummary stat) throws NullArgumentException, NumberIsTooSmallException {
      if (stat == null) {
         throw new NullArgumentException();
      } else if (stat.getN() < 2L) {
         throw new NumberIsTooSmallException(LocalizedFormats.INSUFFICIENT_DATA_FOR_T_STATISTIC, stat.getN(), 2, true);
      }
   }
}
