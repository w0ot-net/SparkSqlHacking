package org.apache.datasketches.hll;

class HllEstimators {
   static final double hllLowerBound(AbstractHllArray absHllArr, int numStdDev) {
      int lgConfigK = absHllArr.lgConfigK;
      int configK = 1 << lgConfigK;
      double numNonZeros = absHllArr.getCurMin() == 0 ? (double)(configK - absHllArr.getNumAtCurMin()) : (double)configK;
      double estimate = absHllArr.getEstimate();
      boolean oooFlag = absHllArr.isOutOfOrder();
      double relErr = BaseHllSketch.getRelErr(false, oooFlag, lgConfigK, numStdDev);
      return Math.max(estimate / ((double)1.0F + relErr), numNonZeros);
   }

   static final double hllUpperBound(AbstractHllArray absHllArr, int numStdDev) {
      int lgConfigK = absHllArr.lgConfigK;
      double estimate = absHllArr.getEstimate();
      boolean oooFlag = absHllArr.isOutOfOrder();
      double relErr = BaseHllSketch.getRelErr(true, oooFlag, lgConfigK, numStdDev);
      return estimate / ((double)1.0F - relErr);
   }

   static final double hllCompositeEstimate(AbstractHllArray absHllArr) {
      int lgConfigK = absHllArr.getLgConfigK();
      double rawEst = getHllRawEstimate(lgConfigK, absHllArr.getKxQ0() + absHllArr.getKxQ1());
      double[] xArr = CompositeInterpolationXTable.xArrs[lgConfigK - 4];
      double yStride = (double)CompositeInterpolationXTable.yStrides[lgConfigK - 4];
      int xArrLen = xArr.length;
      if (rawEst < xArr[0]) {
         return (double)0.0F;
      } else {
         int xArrLenM1 = xArrLen - 1;
         if (rawEst > xArr[xArrLenM1]) {
            double finalY = yStride * (double)xArrLenM1;
            double factor = finalY / xArr[xArrLenM1];
            return rawEst * factor;
         } else {
            double adjEst = CubicInterpolation.usingXArrAndYStride(xArr, yStride, rawEst);
            if (adjEst > (double)(3 << lgConfigK)) {
               return adjEst;
            } else {
               double linEst = getHllBitMapEstimate(lgConfigK, absHllArr.getCurMin(), absHllArr.getNumAtCurMin());
               double avgEst = (adjEst + linEst) / (double)2.0F;
               double crossOver = 0.64;
               if (lgConfigK == 4) {
                  crossOver = 0.718;
               } else if (lgConfigK == 5) {
                  crossOver = 0.672;
               }

               return avgEst > crossOver * (double)(1 << lgConfigK) ? adjEst : linEst;
            }
         }
      }
   }

   private static final double getHllBitMapEstimate(int lgConfigK, int curMin, int numAtCurMin) {
      int configK = 1 << lgConfigK;
      int numUnhitBuckets = curMin == 0 ? numAtCurMin : 0;
      if (numUnhitBuckets == 0) {
         return (double)configK * Math.log((double)configK / (double)0.5F);
      } else {
         int numHitBuckets = configK - numUnhitBuckets;
         return HarmonicNumbers.getBitMapEstimate(configK, numHitBuckets);
      }
   }

   private static final double getHllRawEstimate(int lgConfigK, double kxqSum) {
      int configK = 1 << lgConfigK;
      double correctionFactor;
      if (lgConfigK == 4) {
         correctionFactor = 0.673;
      } else if (lgConfigK == 5) {
         correctionFactor = 0.697;
      } else if (lgConfigK == 6) {
         correctionFactor = 0.709;
      } else {
         correctionFactor = 0.7213 / ((double)1.0F + 1.079 / (double)configK);
      }

      double hyperEst = correctionFactor * (double)configK * (double)configK / kxqSum;
      return hyperEst;
   }
}
