package org.apache.commons.math3.stat.descriptive.summary;

import java.io.Serializable;
import org.apache.commons.math3.exception.MathIllegalArgumentException;
import org.apache.commons.math3.exception.NullArgumentException;
import org.apache.commons.math3.stat.descriptive.AbstractStorelessUnivariateStatistic;
import org.apache.commons.math3.util.MathUtils;

public class SumOfSquares extends AbstractStorelessUnivariateStatistic implements Serializable {
   private static final long serialVersionUID = 1460986908574398008L;
   private long n;
   private double value;

   public SumOfSquares() {
      this.n = 0L;
      this.value = (double)0.0F;
   }

   public SumOfSquares(SumOfSquares original) throws NullArgumentException {
      copy(original, this);
   }

   public void increment(double d) {
      this.value += d * d;
      ++this.n;
   }

   public double getResult() {
      return this.value;
   }

   public long getN() {
      return this.n;
   }

   public void clear() {
      this.value = (double)0.0F;
      this.n = 0L;
   }

   public double evaluate(double[] values, int begin, int length) throws MathIllegalArgumentException {
      double sumSq = Double.NaN;
      if (this.test(values, begin, length, true)) {
         sumSq = (double)0.0F;

         for(int i = begin; i < begin + length; ++i) {
            sumSq += values[i] * values[i];
         }
      }

      return sumSq;
   }

   public SumOfSquares copy() {
      SumOfSquares result = new SumOfSquares();
      copy(this, result);
      return result;
   }

   public static void copy(SumOfSquares source, SumOfSquares dest) throws NullArgumentException {
      MathUtils.checkNotNull(source);
      MathUtils.checkNotNull(dest);
      dest.setData(source.getDataRef());
      dest.n = source.n;
      dest.value = source.value;
   }
}
