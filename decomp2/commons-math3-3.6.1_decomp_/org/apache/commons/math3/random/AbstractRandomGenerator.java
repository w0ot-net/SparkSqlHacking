package org.apache.commons.math3.random;

import org.apache.commons.math3.exception.NotStrictlyPositiveException;
import org.apache.commons.math3.util.FastMath;

public abstract class AbstractRandomGenerator implements RandomGenerator {
   private double cachedNormalDeviate = Double.NaN;

   public void clear() {
      this.cachedNormalDeviate = Double.NaN;
   }

   public void setSeed(int seed) {
      this.setSeed((long)seed);
   }

   public void setSeed(int[] seed) {
      long prime = 4294967291L;
      long combined = 0L;

      for(int s : seed) {
         combined = combined * 4294967291L + (long)s;
      }

      this.setSeed(combined);
   }

   public abstract void setSeed(long var1);

   public void nextBytes(byte[] bytes) {
      int bytesOut = 0;

      while(bytesOut < bytes.length) {
         int randInt = this.nextInt();

         for(int i = 0; i < 3; ++i) {
            if (i > 0) {
               randInt >>= 8;
            }

            bytes[bytesOut++] = (byte)randInt;
            if (bytesOut == bytes.length) {
               return;
            }
         }
      }

   }

   public int nextInt() {
      return (int)(((double)2.0F * this.nextDouble() - (double)1.0F) * (double)Integer.MAX_VALUE);
   }

   public int nextInt(int n) {
      if (n <= 0) {
         throw new NotStrictlyPositiveException(n);
      } else {
         int result = (int)(this.nextDouble() * (double)n);
         return result < n ? result : n - 1;
      }
   }

   public long nextLong() {
      return (long)(((double)2.0F * this.nextDouble() - (double)1.0F) * (double)Long.MAX_VALUE);
   }

   public boolean nextBoolean() {
      return this.nextDouble() <= (double)0.5F;
   }

   public float nextFloat() {
      return (float)this.nextDouble();
   }

   public abstract double nextDouble();

   public double nextGaussian() {
      if (!Double.isNaN(this.cachedNormalDeviate)) {
         double dev = this.cachedNormalDeviate;
         this.cachedNormalDeviate = Double.NaN;
         return dev;
      } else {
         double v1 = (double)0.0F;
         double v2 = (double)0.0F;

         double s;
         for(s = (double)1.0F; s >= (double)1.0F; s = v1 * v1 + v2 * v2) {
            v1 = (double)2.0F * this.nextDouble() - (double)1.0F;
            v2 = (double)2.0F * this.nextDouble() - (double)1.0F;
         }

         if (s != (double)0.0F) {
            s = FastMath.sqrt((double)-2.0F * FastMath.log(s) / s);
         }

         this.cachedNormalDeviate = v2 * s;
         return v1 * s;
      }
   }
}
