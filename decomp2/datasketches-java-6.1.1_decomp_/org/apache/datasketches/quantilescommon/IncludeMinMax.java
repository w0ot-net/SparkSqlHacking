package org.apache.datasketches.quantilescommon;

import java.lang.reflect.Array;
import java.util.Comparator;

public class IncludeMinMax {
   public static DoublesPair includeDoublesMinMax(double[] quantilesIn, long[] cumWeightsIn, double maxItem, double minItem) {
      int lenIn = cumWeightsIn.length;
      boolean adjLow = quantilesIn[0] != minItem;
      boolean adjHigh = quantilesIn[lenIn - 1] != maxItem;
      int adjLen = lenIn + (adjLow ? 1 : 0);
      adjLen += adjHigh ? 1 : 0;
      double[] adjQuantiles;
      long[] adjCumWeights;
      if (adjLen > lenIn) {
         adjQuantiles = new double[adjLen];
         adjCumWeights = new long[adjLen];
         int offset = adjLow ? 1 : 0;
         System.arraycopy(quantilesIn, 0, adjQuantiles, offset, lenIn);
         System.arraycopy(cumWeightsIn, 0, adjCumWeights, offset, lenIn);
         if (adjLow) {
            adjQuantiles[0] = minItem;
            adjCumWeights[0] = 1L;
         }

         if (adjHigh) {
            adjQuantiles[adjLen - 1] = maxItem;
            adjCumWeights[adjLen - 1] = cumWeightsIn[lenIn - 1];
            adjCumWeights[adjLen - 2] = cumWeightsIn[lenIn - 1] - 1L;
         }
      } else {
         adjQuantiles = quantilesIn;
         adjCumWeights = cumWeightsIn;
      }

      return new DoublesPair(adjQuantiles, adjCumWeights);
   }

   public static LongsPair includeLongsMinMax(long[] quantilesIn, long[] cumWeightsIn, long maxItem, long minItem) {
      int lenIn = cumWeightsIn.length;
      boolean adjLow = quantilesIn[0] != minItem;
      boolean adjHigh = quantilesIn[lenIn - 1] != maxItem;
      int adjLen = lenIn + (adjLow ? 1 : 0);
      adjLen += adjHigh ? 1 : 0;
      long[] adjQuantiles;
      long[] adjCumWeights;
      if (adjLen > lenIn) {
         adjQuantiles = new long[adjLen];
         adjCumWeights = new long[adjLen];
         int offset = adjLow ? 1 : 0;
         System.arraycopy(quantilesIn, 0, adjQuantiles, offset, lenIn);
         System.arraycopy(cumWeightsIn, 0, adjCumWeights, offset, lenIn);
         if (adjLow) {
            adjQuantiles[0] = minItem;
            adjCumWeights[0] = 1L;
         }

         if (adjHigh) {
            adjQuantiles[adjLen - 1] = maxItem;
            adjCumWeights[adjLen - 1] = cumWeightsIn[lenIn - 1];
            adjCumWeights[adjLen - 2] = cumWeightsIn[lenIn - 1] - 1L;
         }
      } else {
         adjQuantiles = quantilesIn;
         adjCumWeights = cumWeightsIn;
      }

      return new LongsPair(adjQuantiles, adjCumWeights);
   }

   public static FloatsPair includeFloatsMinMax(float[] quantilesIn, long[] cumWeightsIn, float maxItem, float minItem) {
      int lenIn = cumWeightsIn.length;
      boolean adjLow = quantilesIn[0] != minItem;
      boolean adjHigh = quantilesIn[lenIn - 1] != maxItem;
      int adjLen = lenIn + (adjLow ? 1 : 0);
      adjLen += adjHigh ? 1 : 0;
      float[] adjQuantiles;
      long[] adjCumWeights;
      if (adjLen > lenIn) {
         adjQuantiles = new float[adjLen];
         adjCumWeights = new long[adjLen];
         int offset = adjLow ? 1 : 0;
         System.arraycopy(quantilesIn, 0, adjQuantiles, offset, lenIn);
         System.arraycopy(cumWeightsIn, 0, adjCumWeights, offset, lenIn);
         if (adjLow) {
            adjQuantiles[0] = minItem;
            adjCumWeights[0] = 1L;
         }

         if (adjHigh) {
            adjQuantiles[adjLen - 1] = maxItem;
            adjCumWeights[adjLen - 1] = cumWeightsIn[lenIn - 1];
            adjCumWeights[adjLen - 2] = cumWeightsIn[lenIn - 1] - 1L;
         }
      } else {
         adjQuantiles = quantilesIn;
         adjCumWeights = cumWeightsIn;
      }

      return new FloatsPair(adjQuantiles, adjCumWeights);
   }

   public static ItemsPair includeItemsMinMax(Object[] quantilesIn, long[] cumWeightsIn, Object maxItem, Object minItem, Comparator comparator) {
      int lenIn = cumWeightsIn.length;
      boolean adjLow = comparator.compare(quantilesIn[0], minItem) != 0;
      boolean adjHigh = comparator.compare(quantilesIn[lenIn - 1], maxItem) != 0;
      int adjLen = lenIn + (adjLow ? 1 : 0);
      adjLen += adjHigh ? 1 : 0;
      T[] adjQuantiles;
      long[] adjCumWeights;
      if (adjLen > lenIn) {
         adjQuantiles = (T[])((Object[])((Object[])Array.newInstance(minItem.getClass(), adjLen)));
         adjCumWeights = new long[adjLen];
         int offset = adjLow ? 1 : 0;
         System.arraycopy(quantilesIn, 0, adjQuantiles, offset, lenIn);
         System.arraycopy(cumWeightsIn, 0, adjCumWeights, offset, lenIn);
         if (adjLow) {
            adjQuantiles[0] = minItem;
            adjCumWeights[0] = 1L;
         }

         if (adjHigh) {
            adjQuantiles[adjLen - 1] = maxItem;
            adjCumWeights[adjLen - 1] = cumWeightsIn[lenIn - 1];
            adjCumWeights[adjLen - 2] = cumWeightsIn[lenIn - 1] - 1L;
         }
      } else {
         adjQuantiles = quantilesIn;
         adjCumWeights = cumWeightsIn;
      }

      return new ItemsPair(adjQuantiles, adjCumWeights);
   }

   public static class DoublesPair {
      public double[] quantiles;
      public long[] cumWeights;

      public DoublesPair(double[] quantiles, long[] cumWeights) {
         this.quantiles = quantiles;
         this.cumWeights = cumWeights;
      }
   }

   public static class FloatsPair {
      public float[] quantiles;
      public long[] cumWeights;

      public FloatsPair(float[] quantiles, long[] cumWeights) {
         this.quantiles = quantiles;
         this.cumWeights = cumWeights;
      }
   }

   public static class LongsPair {
      public long[] quantiles;
      public long[] cumWeights;

      public LongsPair(long[] quantiles, long[] cumWeights) {
         this.quantiles = quantiles;
         this.cumWeights = cumWeights;
      }
   }

   public static class ItemsPair {
      public Object[] quantiles;
      public long[] cumWeights;

      public ItemsPair(Object[] quantiles, long[] cumWeights) {
         this.quantiles = quantiles;
         this.cumWeights = cumWeights;
      }
   }
}
