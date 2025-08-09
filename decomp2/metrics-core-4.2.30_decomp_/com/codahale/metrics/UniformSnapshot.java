package com.codahale.metrics;

import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Collection;

public class UniformSnapshot extends Snapshot {
   private final long[] values;

   public UniformSnapshot(Collection values) {
      Object[] copy = values.toArray();
      this.values = new long[copy.length];

      for(int i = 0; i < copy.length; ++i) {
         this.values[i] = (Long)copy[i];
      }

      Arrays.sort(this.values);
   }

   public UniformSnapshot(long[] values) {
      this.values = Arrays.copyOf(values, values.length);
      Arrays.sort(this.values);
   }

   public double getValue(double quantile) {
      if (!(quantile < (double)0.0F) && !(quantile > (double)1.0F) && !Double.isNaN(quantile)) {
         if (this.values.length == 0) {
            return (double)0.0F;
         } else {
            double pos = quantile * (double)(this.values.length + 1);
            int index = (int)pos;
            if (index < 1) {
               return (double)this.values[0];
            } else if (index >= this.values.length) {
               return (double)this.values[this.values.length - 1];
            } else {
               double lower = (double)this.values[index - 1];
               double upper = (double)this.values[index];
               return lower + (pos - Math.floor(pos)) * (upper - lower);
            }
         }
      } else {
         throw new IllegalArgumentException(quantile + " is not in [0..1]");
      }
   }

   public int size() {
      return this.values.length;
   }

   public long[] getValues() {
      return Arrays.copyOf(this.values, this.values.length);
   }

   public long getMax() {
      return this.values.length == 0 ? 0L : this.values[this.values.length - 1];
   }

   public long getMin() {
      return this.values.length == 0 ? 0L : this.values[0];
   }

   public double getMean() {
      if (this.values.length == 0) {
         return (double)0.0F;
      } else {
         double sum = (double)0.0F;

         for(long value : this.values) {
            sum += (double)value;
         }

         return sum / (double)this.values.length;
      }
   }

   public double getStdDev() {
      if (this.values.length <= 1) {
         return (double)0.0F;
      } else {
         double mean = this.getMean();
         double sum = (double)0.0F;

         for(long value : this.values) {
            double diff = (double)value - mean;
            sum += diff * diff;
         }

         double variance = sum / (double)(this.values.length - 1);
         return Math.sqrt(variance);
      }
   }

   public void dump(OutputStream output) {
      PrintWriter out = new PrintWriter(new OutputStreamWriter(output, StandardCharsets.UTF_8));

      try {
         for(long value : this.values) {
            out.printf("%d%n", value);
         }
      } catch (Throwable var9) {
         try {
            out.close();
         } catch (Throwable var8) {
            var9.addSuppressed(var8);
         }

         throw var9;
      }

      out.close();
   }
}
