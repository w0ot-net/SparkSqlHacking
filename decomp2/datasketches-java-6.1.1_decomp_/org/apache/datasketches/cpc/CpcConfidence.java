package org.apache.datasketches.cpc;

final class CpcConfidence {
   private static final double iconErrorConstant = Math.log((double)2.0F);
   private static final double hipErrorConstant = Math.sqrt(Math.log((double)2.0F) / (double)2.0F);
   static short[] iconLowSideData = new short[]{6037, 5720, 5328, 6411, 6262, 5682, 6724, 6403, 6127, 6665, 6411, 6208, 6959, 6525, 6427, 6892, 6665, 6619, 6792, 6752, 6690, 6899, 6818, 6708, 6871, 6845, 6812, 6909, 6861, 6828, 6919, 6897, 6842};
   static short[] iconHighSideData = new short[]{8031, 8559, 9309, 7084, 7959, 8660, 7141, 7514, 7876, 7458, 7430, 7572, 6892, 7141, 7497, 6889, 7132, 7290, 7075, 7118, 7185, 7040, 7047, 7085, 6993, 7019, 7053, 6953, 7001, 6983, 6944, 6966, 7004};
   static short[] hipLowSideData = new short[]{5871, 5247, 4826, 5877, 5403, 5070, 5873, 5533, 5304, 5878, 5632, 5464, 5874, 5690, 5564, 5880, 5745, 5619, 5875, 5784, 5701, 5866, 5789, 5742, 5869, 5827, 5784, 5876, 5860, 5827, 5881, 5853, 5842};
   static short[] hipHighSideData = new short[]{5855, 6688, 7391, 5886, 6444, 6923, 5885, 6254, 6594, 5889, 6134, 6326, 5900, 6072, 6203, 5875, 6005, 6089, 5871, 5980, 6040, 5889, 5941, 6015, 5871, 5926, 5973, 5866, 5901, 5915, 5880, 5914, 5953};

   static double getIconConfidenceLB(int lgK, long numCoupons, int kappa) {
      if (numCoupons == 0L) {
         return (double)0.0F;
      } else {
         assert lgK >= 4;

         assert kappa >= 1 && kappa <= 3;

         double x = iconErrorConstant;
         if (lgK <= 14) {
            x = (double)iconHighSideData[3 * (lgK - 4) + (kappa - 1)] / (double)10000.0F;
         }

         double rel = x / Math.sqrt((double)(1 << lgK));
         double eps = (double)kappa * rel;
         double est = IconEstimator.getIconEstimate(lgK, numCoupons);
         double result = est / ((double)1.0F + eps);
         if (result < (double)numCoupons) {
            result = (double)numCoupons;
         }

         return result;
      }
   }

   static double getIconConfidenceUB(int lgK, long numCoupons, int kappa) {
      if (numCoupons == 0L) {
         return (double)0.0F;
      } else {
         assert lgK >= 4;

         assert kappa >= 1 && kappa <= 3;

         double x = iconErrorConstant;
         if (lgK <= 14) {
            x = (double)iconLowSideData[3 * (lgK - 4) + (kappa - 1)] / (double)10000.0F;
         }

         double rel = x / Math.sqrt((double)(1 << lgK));
         double eps = (double)kappa * rel;
         double est = IconEstimator.getIconEstimate(lgK, numCoupons);
         double result = est / ((double)1.0F - eps);
         return Math.ceil(result);
      }
   }

   static double getHipConfidenceLB(int lgK, long numCoupons, double hipEstAccum, int kappa) {
      if (numCoupons == 0L) {
         return (double)0.0F;
      } else {
         assert lgK >= 4;

         assert kappa >= 1 && kappa <= 3;

         double x = hipErrorConstant;
         if (lgK <= 14) {
            x = (double)hipHighSideData[3 * (lgK - 4) + (kappa - 1)] / (double)10000.0F;
         }

         double rel = x / Math.sqrt((double)(1 << lgK));
         double eps = (double)kappa * rel;
         double result = hipEstAccum / ((double)1.0F + eps);
         if (result < (double)numCoupons) {
            result = (double)numCoupons;
         }

         return result;
      }
   }

   static double getHipConfidenceUB(int lgK, long numCoupons, double hipEstAccum, int kappa) {
      if (numCoupons == 0L) {
         return (double)0.0F;
      } else {
         assert lgK >= 4;

         assert kappa >= 1 && kappa <= 3;

         double x = hipErrorConstant;
         if (lgK <= 14) {
            x = (double)hipLowSideData[3 * (lgK - 4) + (kappa - 1)] / (double)10000.0F;
         }

         double rel = x / Math.sqrt((double)(1 << lgK));
         double eps = (double)kappa * rel;
         double result = hipEstAccum / ((double)1.0F - eps);
         return Math.ceil(result);
      }
   }
}
