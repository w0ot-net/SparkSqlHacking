package org.sparkproject.guava.math;

import java.io.Serializable;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import javax.annotation.CheckForNull;
import org.sparkproject.guava.annotations.GwtIncompatible;
import org.sparkproject.guava.annotations.J2ktIncompatible;
import org.sparkproject.guava.base.MoreObjects;
import org.sparkproject.guava.base.Objects;
import org.sparkproject.guava.base.Preconditions;

@ElementTypesAreNonnullByDefault
@J2ktIncompatible
@GwtIncompatible
public final class PairedStats implements Serializable {
   private final Stats xStats;
   private final Stats yStats;
   private final double sumOfProductsOfDeltas;
   private static final int BYTES = 88;
   private static final long serialVersionUID = 0L;

   PairedStats(Stats xStats, Stats yStats, double sumOfProductsOfDeltas) {
      this.xStats = xStats;
      this.yStats = yStats;
      this.sumOfProductsOfDeltas = sumOfProductsOfDeltas;
   }

   public long count() {
      return this.xStats.count();
   }

   public Stats xStats() {
      return this.xStats;
   }

   public Stats yStats() {
      return this.yStats;
   }

   public double populationCovariance() {
      Preconditions.checkState(this.count() != 0L);
      return this.sumOfProductsOfDeltas / (double)this.count();
   }

   public double sampleCovariance() {
      Preconditions.checkState(this.count() > 1L);
      return this.sumOfProductsOfDeltas / (double)(this.count() - 1L);
   }

   public double pearsonsCorrelationCoefficient() {
      Preconditions.checkState(this.count() > 1L);
      if (Double.isNaN(this.sumOfProductsOfDeltas)) {
         return Double.NaN;
      } else {
         double xSumOfSquaresOfDeltas = this.xStats().sumOfSquaresOfDeltas();
         double ySumOfSquaresOfDeltas = this.yStats().sumOfSquaresOfDeltas();
         Preconditions.checkState(xSumOfSquaresOfDeltas > (double)0.0F);
         Preconditions.checkState(ySumOfSquaresOfDeltas > (double)0.0F);
         double productOfSumsOfSquaresOfDeltas = ensurePositive(xSumOfSquaresOfDeltas * ySumOfSquaresOfDeltas);
         return ensureInUnitRange(this.sumOfProductsOfDeltas / Math.sqrt(productOfSumsOfSquaresOfDeltas));
      }
   }

   public LinearTransformation leastSquaresFit() {
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

   public boolean equals(@CheckForNull Object obj) {
      if (obj == null) {
         return false;
      } else if (this.getClass() != obj.getClass()) {
         return false;
      } else {
         PairedStats other = (PairedStats)obj;
         return this.xStats.equals(other.xStats) && this.yStats.equals(other.yStats) && Double.doubleToLongBits(this.sumOfProductsOfDeltas) == Double.doubleToLongBits(other.sumOfProductsOfDeltas);
      }
   }

   public int hashCode() {
      return Objects.hashCode(this.xStats, this.yStats, this.sumOfProductsOfDeltas);
   }

   public String toString() {
      return this.count() > 0L ? MoreObjects.toStringHelper((Object)this).add("xStats", this.xStats).add("yStats", this.yStats).add("populationCovariance", this.populationCovariance()).toString() : MoreObjects.toStringHelper((Object)this).add("xStats", this.xStats).add("yStats", this.yStats).toString();
   }

   double sumOfProductsOfDeltas() {
      return this.sumOfProductsOfDeltas;
   }

   private static double ensurePositive(double value) {
      return value > (double)0.0F ? value : Double.MIN_VALUE;
   }

   private static double ensureInUnitRange(double value) {
      if (value >= (double)1.0F) {
         return (double)1.0F;
      } else {
         return value <= (double)-1.0F ? (double)-1.0F : value;
      }
   }

   public byte[] toByteArray() {
      ByteBuffer buffer = ByteBuffer.allocate(88).order(ByteOrder.LITTLE_ENDIAN);
      this.xStats.writeTo(buffer);
      this.yStats.writeTo(buffer);
      buffer.putDouble(this.sumOfProductsOfDeltas);
      return buffer.array();
   }

   public static PairedStats fromByteArray(byte[] byteArray) {
      Preconditions.checkNotNull(byteArray);
      Preconditions.checkArgument(byteArray.length == 88, "Expected PairedStats.BYTES = %s, got %s", (int)88, (int)byteArray.length);
      ByteBuffer buffer = ByteBuffer.wrap(byteArray).order(ByteOrder.LITTLE_ENDIAN);
      Stats xStats = Stats.readFrom(buffer);
      Stats yStats = Stats.readFrom(buffer);
      double sumOfProductsOfDeltas = buffer.getDouble();
      return new PairedStats(xStats, yStats, sumOfProductsOfDeltas);
   }
}
