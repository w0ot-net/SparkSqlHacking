package com.codahale.metrics;

import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;

public class WeightedSnapshot extends Snapshot {
   private final long[] values;
   private final double[] normWeights;
   private final double[] quantiles;

   public WeightedSnapshot(Collection values) {
      WeightedSample[] copy = (WeightedSample[])values.toArray(new WeightedSample[0]);
      Arrays.sort(copy, Comparator.comparingLong((w) -> w.value));
      this.values = new long[copy.length];
      this.normWeights = new double[copy.length];
      this.quantiles = new double[copy.length];
      double sumWeight = (double)0.0F;

      for(WeightedSample sample : copy) {
         sumWeight += sample.weight;
      }

      for(int i = 0; i < copy.length; ++i) {
         this.values[i] = copy[i].value;
         this.normWeights[i] = sumWeight != (double)0.0F ? copy[i].weight / sumWeight : (double)0.0F;
      }

      for(int i = 1; i < copy.length; ++i) {
         this.quantiles[i] = this.quantiles[i - 1] + this.normWeights[i - 1];
      }

   }

   public double getValue(double quantile) {
      if (!(quantile < (double)0.0F) && !(quantile > (double)1.0F) && !Double.isNaN(quantile)) {
         if (this.values.length == 0) {
            return (double)0.0F;
         } else {
            int posx = Arrays.binarySearch(this.quantiles, quantile);
            if (posx < 0) {
               posx = -posx - 1 - 1;
            }

            if (posx < 1) {
               return (double)this.values[0];
            } else {
               return posx >= this.values.length ? (double)this.values[this.values.length - 1] : (double)this.values[posx];
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

         for(int i = 0; i < this.values.length; ++i) {
            sum += (double)this.values[i] * this.normWeights[i];
         }

         return sum;
      }
   }

   public double getStdDev() {
      if (this.values.length <= 1) {
         return (double)0.0F;
      } else {
         double mean = this.getMean();
         double variance = (double)0.0F;

         for(int i = 0; i < this.values.length; ++i) {
            double diff = (double)this.values[i] - mean;
            variance += this.normWeights[i] * diff * diff;
         }

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

   public static class WeightedSample {
      public final long value;
      public final double weight;

      public WeightedSample(long value, double weight) {
         this.value = value;
         this.weight = weight;
      }
   }
}
