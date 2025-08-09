package com.google.common.math;

import com.google.common.annotations.GwtIncompatible;
import com.google.common.annotations.J2ktIncompatible;
import com.google.common.base.Preconditions;
import com.google.common.primitives.Doubles;

@ElementTypesAreNonnullByDefault
@J2ktIncompatible
@GwtIncompatible
public final class PairedStatsAccumulator {
   private final StatsAccumulator xStats = new StatsAccumulator();
   private final StatsAccumulator yStats = new StatsAccumulator();
   private double sumOfProductsOfDeltas = (double)0.0F;

   public void add(double x, double y) {
      this.xStats.add(x);
      if (Doubles.isFinite(x) && Doubles.isFinite(y)) {
         if (this.xStats.count() > 1L) {
            this.sumOfProductsOfDeltas += (x - this.xStats.mean()) * (y - this.yStats.mean());
         }
      } else {
         this.sumOfProductsOfDeltas = Double.NaN;
      }

      this.yStats.add(y);
   }

   public void addAll(PairedStats values) {
      if (values.count() != 0L) {
         this.xStats.addAll(values.xStats());
         if (this.yStats.count() == 0L) {
            this.sumOfProductsOfDeltas = values.sumOfProductsOfDeltas();
         } else {
            this.sumOfProductsOfDeltas += values.sumOfProductsOfDeltas() + (values.xStats().mean() - this.xStats.mean()) * (values.yStats().mean() - this.yStats.mean()) * (double)values.count();
         }

         this.yStats.addAll(values.yStats());
      }
   }

   public PairedStats snapshot() {
      return new PairedStats(this.xStats.snapshot(), this.yStats.snapshot(), this.sumOfProductsOfDeltas);
   }

   public long count() {
      return this.xStats.count();
   }

   public Stats xStats() {
      return this.xStats.snapshot();
   }

   public Stats yStats() {
      return this.yStats.snapshot();
   }

   public double populationCovariance() {
      Preconditions.checkState(this.count() != 0L);
      return this.sumOfProductsOfDeltas / (double)this.count();
   }

   public final double sampleCovariance() {
      Preconditions.checkState(this.count() > 1L);
      return this.sumOfProductsOfDeltas / (double)(this.count() - 1L);
   }

   public final double pearsonsCorrelationCoefficient() {
      Preconditions.checkState(this.count() > 1L);
      if (Double.isNaN(this.sumOfProductsOfDeltas)) {
         return Double.NaN;
      } else {
         double xSumOfSquaresOfDeltas = this.xStats.sumOfSquaresOfDeltas();
         double ySumOfSquaresOfDeltas = this.yStats.sumOfSquaresOfDeltas();
         Preconditions.checkState(xSumOfSquaresOfDeltas > (double)0.0F);
         Preconditions.checkState(ySumOfSquaresOfDeltas > (double)0.0F);
         double productOfSumsOfSquaresOfDeltas = this.ensurePositive(xSumOfSquaresOfDeltas * ySumOfSquaresOfDeltas);
         return ensureInUnitRange(this.sumOfProductsOfDeltas / Math.sqrt(productOfSumsOfSquaresOfDeltas));
      }
   }

   public final LinearTransformation leastSquaresFit() {
      Preconditions.checkState(this.count() > 1L);
      if (Double.isNaN(this.sumOfProductsOfDeltas)) {
         return LinearTransformation.forNaN();
      } else {
         double xSumOfSquaresOfDeltas = this.xStats.sumOfSquaresOfDeltas();
         if (xSumOfSquaresOfDeltas > (double)0.0F) {
            return this.yStats.sumOfSquaresOfDeltas() > (double)0.0F ? LinearTransformation.mapping(this.xStats.mean(), this.yStats.mean()).withSlope(this.sumOfProductsOfDeltas / xSumOfSquaresOfDeltas) : LinearTransformation.horizontal(this.yStats.mean());
         } else {
            Preconditions.checkState(this.yStats.sumOfSquaresOfDeltas() > (double)0.0F);
            return LinearTransformation.vertical(this.xStats.mean());
         }
      }
   }

   private double ensurePositive(double value) {
      return value > (double)0.0F ? value : Double.MIN_VALUE;
   }

   private static double ensureInUnitRange(double value) {
      return Doubles.constrainToRange(value, (double)-1.0F, (double)1.0F);
   }
}
