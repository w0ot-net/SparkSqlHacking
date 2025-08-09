package org.apache.commons.math3.stat.descriptive.moment;

import java.io.Serializable;
import org.apache.commons.math3.exception.NullArgumentException;
import org.apache.commons.math3.util.MathUtils;

class FourthMoment extends ThirdMoment implements Serializable {
   private static final long serialVersionUID = 4763990447117157611L;
   private double m4;

   FourthMoment() {
      this.m4 = Double.NaN;
   }

   FourthMoment(FourthMoment original) throws NullArgumentException {
      copy(original, this);
   }

   public void increment(double d) {
      if (this.n < 1L) {
         this.m4 = (double)0.0F;
         this.m3 = (double)0.0F;
         this.m2 = (double)0.0F;
         this.m1 = (double)0.0F;
      }

      double prevM3 = this.m3;
      double prevM2 = this.m2;
      super.increment(d);
      double n0 = (double)this.n;
      this.m4 = this.m4 - (double)4.0F * this.nDev * prevM3 + (double)6.0F * this.nDevSq * prevM2 + (n0 * n0 - (double)3.0F * (n0 - (double)1.0F)) * this.nDevSq * this.nDevSq * (n0 - (double)1.0F) * n0;
   }

   public double getResult() {
      return this.m4;
   }

   public void clear() {
      super.clear();
      this.m4 = Double.NaN;
   }

   public FourthMoment copy() {
      FourthMoment result = new FourthMoment();
      copy(this, result);
      return result;
   }

   public static void copy(FourthMoment source, FourthMoment dest) throws NullArgumentException {
      MathUtils.checkNotNull(source);
      MathUtils.checkNotNull(dest);
      ThirdMoment.copy(source, dest);
      dest.m4 = source.m4;
   }
}
