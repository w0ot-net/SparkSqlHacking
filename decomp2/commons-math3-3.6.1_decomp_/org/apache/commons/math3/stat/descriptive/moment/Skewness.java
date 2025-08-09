package org.apache.commons.math3.stat.descriptive.moment;

import java.io.Serializable;
import org.apache.commons.math3.exception.MathIllegalArgumentException;
import org.apache.commons.math3.exception.NullArgumentException;
import org.apache.commons.math3.stat.descriptive.AbstractStorelessUnivariateStatistic;
import org.apache.commons.math3.util.FastMath;
import org.apache.commons.math3.util.MathUtils;

public class Skewness extends AbstractStorelessUnivariateStatistic implements Serializable {
   private static final long serialVersionUID = 7101857578996691352L;
   protected ThirdMoment moment = null;
   protected boolean incMoment;

   public Skewness() {
      this.incMoment = true;
      this.moment = new ThirdMoment();
   }

   public Skewness(ThirdMoment m3) {
      this.incMoment = false;
      this.moment = m3;
   }

   public Skewness(Skewness original) throws NullArgumentException {
      copy(original, this);
   }

   public void increment(double d) {
      if (this.incMoment) {
         this.moment.increment(d);
      }

   }

   public double getResult() {
      if (this.moment.n < 3L) {
         return Double.NaN;
      } else {
         double variance = this.moment.m2 / (double)(this.moment.n - 1L);
         if (variance < 1.0E-19) {
            return (double)0.0F;
         } else {
            double n0 = (double)this.moment.getN();
            return n0 * this.moment.m3 / ((n0 - (double)1.0F) * (n0 - (double)2.0F) * FastMath.sqrt(variance) * variance);
         }
      }
   }

   public long getN() {
      return this.moment.getN();
   }

   public void clear() {
      if (this.incMoment) {
         this.moment.clear();
      }

   }

   public double evaluate(double[] values, int begin, int length) throws MathIllegalArgumentException {
      double skew = Double.NaN;
      if (this.test(values, begin, length) && length > 2) {
         Mean mean = new Mean();
         double m = mean.evaluate(values, begin, length);
         double accum = (double)0.0F;
         double accum2 = (double)0.0F;

         for(int i = begin; i < begin + length; ++i) {
            double d = values[i] - m;
            accum += d * d;
            accum2 += d;
         }

         double variance = (accum - accum2 * accum2 / (double)length) / (double)(length - 1);
         double accum3 = (double)0.0F;

         for(int i = begin; i < begin + length; ++i) {
            double d = values[i] - m;
            accum3 += d * d * d;
         }

         accum3 /= variance * FastMath.sqrt(variance);
         double n0 = (double)length;
         skew = n0 / ((n0 - (double)1.0F) * (n0 - (double)2.0F)) * accum3;
      }

      return skew;
   }

   public Skewness copy() {
      Skewness result = new Skewness();
      copy(this, result);
      return result;
   }

   public static void copy(Skewness source, Skewness dest) throws NullArgumentException {
      MathUtils.checkNotNull(source);
      MathUtils.checkNotNull(dest);
      dest.setData(source.getDataRef());
      dest.moment = new ThirdMoment(source.moment.copy());
      dest.incMoment = source.incMoment;
   }
}
