package org.apache.commons.math3.stat.descriptive.moment;

import java.io.Serializable;
import org.apache.commons.math3.exception.MathIllegalArgumentException;
import org.apache.commons.math3.exception.NullArgumentException;
import org.apache.commons.math3.stat.descriptive.AbstractStorelessUnivariateStatistic;
import org.apache.commons.math3.util.FastMath;
import org.apache.commons.math3.util.MathUtils;

public class Kurtosis extends AbstractStorelessUnivariateStatistic implements Serializable {
   private static final long serialVersionUID = 2784465764798260919L;
   protected FourthMoment moment;
   protected boolean incMoment;

   public Kurtosis() {
      this.incMoment = true;
      this.moment = new FourthMoment();
   }

   public Kurtosis(FourthMoment m4) {
      this.incMoment = false;
      this.moment = m4;
   }

   public Kurtosis(Kurtosis original) throws NullArgumentException {
      copy(original, this);
   }

   public void increment(double d) {
      if (this.incMoment) {
         this.moment.increment(d);
      }

   }

   public double getResult() {
      double kurtosis = Double.NaN;
      if (this.moment.getN() > 3L) {
         double variance = this.moment.m2 / (double)(this.moment.n - 1L);
         if (this.moment.n > 3L && !(variance < 1.0E-19)) {
            double n = (double)this.moment.n;
            kurtosis = (n * (n + (double)1.0F) * this.moment.getResult() - (double)3.0F * this.moment.m2 * this.moment.m2 * (n - (double)1.0F)) / ((n - (double)1.0F) * (n - (double)2.0F) * (n - (double)3.0F) * variance * variance);
         } else {
            kurtosis = (double)0.0F;
         }
      }

      return kurtosis;
   }

   public void clear() {
      if (this.incMoment) {
         this.moment.clear();
      }

   }

   public long getN() {
      return this.moment.getN();
   }

   public double evaluate(double[] values, int begin, int length) throws MathIllegalArgumentException {
      double kurt = Double.NaN;
      if (this.test(values, begin, length) && length > 3) {
         Variance variance = new Variance();
         variance.incrementAll(values, begin, length);
         double mean = variance.moment.m1;
         double stdDev = FastMath.sqrt(variance.getResult());
         double accum3 = (double)0.0F;

         for(int i = begin; i < begin + length; ++i) {
            accum3 += FastMath.pow(values[i] - mean, (double)4.0F);
         }

         accum3 /= FastMath.pow(stdDev, (double)4.0F);
         double n0 = (double)length;
         double coefficientOne = n0 * (n0 + (double)1.0F) / ((n0 - (double)1.0F) * (n0 - (double)2.0F) * (n0 - (double)3.0F));
         double termTwo = (double)3.0F * FastMath.pow(n0 - (double)1.0F, (double)2.0F) / ((n0 - (double)2.0F) * (n0 - (double)3.0F));
         kurt = coefficientOne * accum3 - termTwo;
      }

      return kurt;
   }

   public Kurtosis copy() {
      Kurtosis result = new Kurtosis();
      copy(this, result);
      return result;
   }

   public static void copy(Kurtosis source, Kurtosis dest) throws NullArgumentException {
      MathUtils.checkNotNull(source);
      MathUtils.checkNotNull(dest);
      dest.setData(source.getDataRef());
      dest.moment = source.moment.copy();
      dest.incMoment = source.incMoment;
   }
}
