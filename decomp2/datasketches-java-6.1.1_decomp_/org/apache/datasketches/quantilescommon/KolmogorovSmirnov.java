package org.apache.datasketches.quantilescommon;

import org.apache.datasketches.req.ReqSketch;

public final class KolmogorovSmirnov {
   public static double computeKSDelta(QuantilesDoublesAPI sketch1, QuantilesDoublesAPI sketch2) {
      DoublesSortedView p = sketch1.getSortedView();
      DoublesSortedView q = sketch2.getSortedView();
      double[] pSamplesArr = p.getQuantiles();
      double[] qSamplesArr = q.getQuantiles();
      long[] pCumWtsArr = p.getCumulativeWeights();
      long[] qCumWtsArr = q.getCumulativeWeights();
      int pSamplesArrLen = pSamplesArr.length;
      int qSamplesArrLen = qSamplesArr.length;
      double n1 = (double)sketch1.getN();
      double n2 = (double)sketch2.getN();
      double deltaHeight = (double)0.0F;
      int i = 0;
      int j = 0;

      while(i < pSamplesArrLen - 1 && j < qSamplesArrLen - 1) {
         deltaHeight = Math.max(deltaHeight, Math.abs((double)pCumWtsArr[i] / n1 - (double)qCumWtsArr[j] / n2));
         if (pSamplesArr[i] < qSamplesArr[j]) {
            ++i;
         } else if (qSamplesArr[j] < pSamplesArr[i]) {
            ++j;
         } else {
            ++i;
            ++j;
         }
      }

      deltaHeight = Math.max(deltaHeight, Math.abs((double)pCumWtsArr[i] / n1 - (double)qCumWtsArr[j] / n2));
      return deltaHeight;
   }

   public static double computeKSDelta(QuantilesFloatsAPI sketch1, QuantilesFloatsAPI sketch2) {
      FloatsSortedView p = sketch1.getSortedView();
      FloatsSortedView q = sketch2.getSortedView();
      float[] pSamplesArr = p.getQuantiles();
      float[] qSamplesArr = q.getQuantiles();
      long[] pCumWtsArr = p.getCumulativeWeights();
      long[] qCumWtsArr = q.getCumulativeWeights();
      int pSamplesArrLen = pSamplesArr.length;
      int qSamplesArrLen = qSamplesArr.length;
      double n1 = (double)sketch1.getN();
      double n2 = (double)sketch2.getN();
      double deltaHeight = (double)0.0F;
      int i = 0;
      int j = 0;

      while(i < pSamplesArrLen - 1 && j < qSamplesArrLen - 1) {
         deltaHeight = Math.max(deltaHeight, Math.abs((double)pCumWtsArr[i] / n1 - (double)qCumWtsArr[j] / n2));
         if (pSamplesArr[i] < qSamplesArr[j]) {
            ++i;
         } else if (qSamplesArr[j] < pSamplesArr[i]) {
            ++j;
         } else {
            ++i;
            ++j;
         }
      }

      deltaHeight = Math.max(deltaHeight, Math.abs((double)pCumWtsArr[i] / n1 - (double)qCumWtsArr[j] / n2));
      return deltaHeight;
   }

   public static double computeKSThreshold(QuantilesAPI sketch1, QuantilesAPI sketch2, double tgtPvalue) {
      double r1 = (double)sketch1.getNumRetained();
      double r2 = (double)sketch2.getNumRetained();
      double alphaFactor = Math.sqrt((double)-0.5F * Math.log((double)0.5F * tgtPvalue));
      double deltaAreaThreshold = alphaFactor * Math.sqrt((r1 + r2) / (r1 * r2));
      double eps1 = sketch1.getNormalizedRankError(false);
      double eps2 = sketch2.getNormalizedRankError(false);
      return deltaAreaThreshold + eps1 + eps2;
   }

   public static boolean kolmogorovSmirnovTest(QuantilesAPI sketch1, QuantilesAPI sketch2, double tgtPvalue) {
      double delta = isDoubleType(sketch1, sketch2) ? computeKSDelta((QuantilesDoublesAPI)sketch1, (QuantilesDoublesAPI)sketch2) : computeKSDelta((QuantilesFloatsAPI)sketch1, (QuantilesFloatsAPI)sketch2);
      double thresh = computeKSThreshold(sketch1, sketch2, tgtPvalue);
      return delta > thresh;
   }

   private static boolean isDoubleType(Object sk1, Object sk2) {
      if (!(sk1 instanceof ReqSketch) && !(sk2 instanceof ReqSketch)) {
         boolean isDbl = sk1 instanceof QuantilesDoublesAPI && sk2 instanceof QuantilesDoublesAPI;
         boolean isFlt = sk1 instanceof QuantilesFloatsAPI && sk2 instanceof QuantilesFloatsAPI;
         if (isDbl ^ isFlt) {
            return isDbl;
         } else {
            throw new UnsupportedOperationException("Unsupported operation for this Sketch Type. ");
         }
      } else {
         throw new UnsupportedOperationException("Unsupported operation for this Sketch Type. ");
      }
   }
}
