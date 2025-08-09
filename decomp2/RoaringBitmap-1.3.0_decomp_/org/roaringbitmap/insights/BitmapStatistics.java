package org.roaringbitmap.insights;

import java.util.Objects;

public class BitmapStatistics {
   private final long bitmapsCount;
   private final ArrayContainersStats arrayContainersStats;
   private final long bitmapContainerCount;
   private final long runContainerCount;
   public static final BitmapStatistics empty;

   BitmapStatistics(ArrayContainersStats arrayContainersStats, long bitmapContainerCount, long runContainerCount) {
      this(arrayContainersStats, bitmapContainerCount, runContainerCount, 1L);
   }

   BitmapStatistics(ArrayContainersStats arrayContainersStats, long bitmapContainerCount, long runContainerCount, long bitmapsCount) {
      this.arrayContainersStats = arrayContainersStats;
      this.bitmapContainerCount = bitmapContainerCount;
      this.runContainerCount = runContainerCount;
      this.bitmapsCount = bitmapsCount;
   }

   public double containerFraction(long containerTypeCount) {
      return this.containerCount() == 0L ? Double.NaN : (double)containerTypeCount / (double)this.containerCount();
   }

   public ArrayContainersStats getArrayContainersStats() {
      return this.arrayContainersStats;
   }

   public String toString() {
      return "BitmapStatistics{bitmapsCount=" + this.bitmapsCount + ", arrayContainersStats=" + this.arrayContainersStats + ", bitmapContainerCount=" + this.bitmapContainerCount + ", runContainerCount=" + this.runContainerCount + '}';
   }

   public long containerCount() {
      return this.arrayContainersStats.containersCount + this.bitmapContainerCount + this.runContainerCount;
   }

   BitmapStatistics merge(BitmapStatistics other) {
      return new BitmapStatistics(this.arrayContainersStats.merge(other.arrayContainersStats), this.bitmapContainerCount + other.bitmapContainerCount, this.runContainerCount + other.runContainerCount, this.bitmapsCount + other.bitmapsCount);
   }

   public boolean equals(Object o) {
      if (this == o) {
         return true;
      } else if (o != null && this.getClass() == o.getClass()) {
         BitmapStatistics that = (BitmapStatistics)o;
         return this.bitmapsCount == that.bitmapsCount && this.bitmapContainerCount == that.bitmapContainerCount && this.runContainerCount == that.runContainerCount && Objects.equals(this.arrayContainersStats, that.arrayContainersStats);
      } else {
         return false;
      }
   }

   public int hashCode() {
      return Objects.hash(new Object[]{this.bitmapsCount, this.bitmapContainerCount, this.runContainerCount});
   }

   public long getBitmapsCount() {
      return this.bitmapsCount;
   }

   public long getBitmapContainerCount() {
      return this.bitmapContainerCount;
   }

   public long getRunContainerCount() {
      return this.runContainerCount;
   }

   static {
      empty = new BitmapStatistics(BitmapStatistics.ArrayContainersStats.empty, 0L, 0L, 0L);
   }

   public static class ArrayContainersStats {
      private final long containersCount;
      private final long cardinalitySum;
      public static final ArrayContainersStats empty = new ArrayContainersStats(0L, 0L);

      public long getContainersCount() {
         return this.containersCount;
      }

      public long getCardinalitySum() {
         return this.cardinalitySum;
      }

      ArrayContainersStats(long containersCount, long cardinalitySum) {
         this.containersCount = containersCount;
         this.cardinalitySum = cardinalitySum;
      }

      ArrayContainersStats merge(ArrayContainersStats other) {
         return new ArrayContainersStats(this.containersCount + other.containersCount, this.cardinalitySum + other.cardinalitySum);
      }

      public long averageCardinality() {
         return this.containersCount == 0L ? Long.MAX_VALUE : this.cardinalitySum / this.containersCount;
      }

      public boolean equals(Object o) {
         if (this == o) {
            return true;
         } else if (o != null && this.getClass() == o.getClass()) {
            ArrayContainersStats that = (ArrayContainersStats)o;
            return this.containersCount == that.containersCount && this.cardinalitySum == that.cardinalitySum;
         } else {
            return false;
         }
      }

      public int hashCode() {
         return Objects.hash(new Object[]{this.containersCount, this.cardinalitySum});
      }

      public String toString() {
         return "ArrayContainersStats{containersCount=" + this.containersCount + ", cardinalitySum=" + this.cardinalitySum + '}';
      }
   }
}
