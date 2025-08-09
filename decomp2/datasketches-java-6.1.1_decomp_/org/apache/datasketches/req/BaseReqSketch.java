package org.apache.datasketches.req;

import org.apache.datasketches.quantilescommon.FloatsSortedView;
import org.apache.datasketches.quantilescommon.QuantileSearchCriteria;
import org.apache.datasketches.quantilescommon.QuantilesFloatsAPI;
import org.apache.datasketches.quantilescommon.QuantilesFloatsSketchIterator;

abstract class BaseReqSketch implements QuantilesFloatsAPI {
   static final byte INIT_NUMBER_OF_SECTIONS = 3;
   private static final double relRseFactor = Math.sqrt(0.017066666666666667);
   private static final double fixRseFactor = 0.084;

   public abstract double[] getCDF(float[] var1, QuantileSearchCriteria var2);

   public abstract boolean getHighRankAccuracyMode();

   public abstract int getK();

   public abstract float getMaxItem();

   public abstract float getMinItem();

   public static double getRSE(int k, double rank, boolean hra, long totalN) {
      return getRankUB(k, 2, rank, 1, hra, totalN);
   }

   public abstract long getN();

   public abstract double[] getPMF(float[] var1, QuantileSearchCriteria var2);

   public abstract float getQuantile(double var1, QuantileSearchCriteria var3);

   public abstract float[] getQuantiles(double[] var1, QuantileSearchCriteria var2);

   public abstract float getQuantileLowerBound(double var1);

   public abstract float getQuantileLowerBound(double var1, int var3);

   public abstract float getQuantileUpperBound(double var1);

   public abstract float getQuantileUpperBound(double var1, int var3);

   public abstract double getRank(float var1, QuantileSearchCriteria var2);

   public abstract double getRankLowerBound(double var1, int var3);

   public abstract double[] getRanks(float[] var1, QuantileSearchCriteria var2);

   public abstract double getRankUpperBound(double var1, int var3);

   public abstract int getNumRetained();

   public abstract int getSerializedSizeBytes();

   public abstract FloatsSortedView getSortedView();

   public boolean hasMemory() {
      return false;
   }

   public boolean isDirect() {
      return false;
   }

   public abstract boolean isEmpty();

   public abstract boolean isEstimationMode();

   public boolean isReadOnly() {
      return false;
   }

   public abstract QuantilesFloatsSketchIterator iterator();

   public abstract ReqSketch merge(ReqSketch var1);

   public abstract void reset();

   public abstract byte[] toByteArray();

   public abstract String toString();

   public abstract void update(float var1);

   public abstract String viewCompactorDetail(String var1, boolean var2);

   static boolean exactRank(int k, int levels, double rank, boolean hra, long totalN) {
      int baseCap = k * 3;
      if (levels != 1 && totalN > (long)baseCap) {
         double exactRankThresh = (double)baseCap / (double)totalN;
         return hra && rank >= (double)1.0F - exactRankThresh || !hra && rank <= exactRankThresh;
      } else {
         return true;
      }
   }

   static double getRankLB(int k, int levels, double rank, int numStdDev, boolean hra, long totalN) {
      if (exactRank(k, levels, rank, hra, totalN)) {
         return rank;
      } else {
         double relative = relRseFactor / (double)k * (hra ? (double)1.0F - rank : rank);
         double fixed = 0.084 / (double)k;
         double lbRel = rank - (double)numStdDev * relative;
         double lbFix = rank - (double)numStdDev * fixed;
         return Math.max(lbRel, lbFix);
      }
   }

   static double getRankUB(int k, int levels, double rank, int numStdDev, boolean hra, long totalN) {
      if (exactRank(k, levels, rank, hra, totalN)) {
         return rank;
      } else {
         double relative = relRseFactor / (double)k * (hra ? (double)1.0F - rank : rank);
         double fixed = 0.084 / (double)k;
         double ubRel = rank + (double)numStdDev * relative;
         double ubFix = rank + (double)numStdDev * fixed;
         return Math.min(ubRel, ubFix);
      }
   }
}
