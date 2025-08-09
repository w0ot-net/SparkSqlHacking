package org.apache.datasketches.quantiles;

import java.util.Arrays;
import org.apache.datasketches.common.SketchesArgumentException;
import org.apache.datasketches.common.Util;

final class ItemsUtil {
   static final int ITEMS_SER_VER = 3;
   static final int PRIOR_ITEMS_SER_VER = 2;

   private ItemsUtil() {
   }

   static void checkItemsSerVer(int serVer) {
      if (serVer != 3 && serVer != 2) {
         throw new SketchesArgumentException("Possible corruption: Invalid Serialization Version: " + serVer);
      }
   }

   static void processFullBaseBuffer(ItemsSketch sketch) {
      int bbCount = sketch.getBaseBufferCount();
      long n = sketch.getN();

      assert bbCount == 2 * sketch.getK();

      ItemsUpdateImpl.maybeGrowLevels(sketch, n);
      Object[] baseBuffer = sketch.getCombinedBuffer();
      Arrays.sort(baseBuffer, 0, bbCount, sketch.getComparator());
      ItemsUpdateImpl.inPlacePropagateCarry(0, (Object[])null, 0, baseBuffer, 0, true, sketch);
      sketch.baseBufferCount_ = 0;
      Arrays.fill(baseBuffer, 0, 2 * sketch.getK(), (Object)null);

      assert n / (2L * (long)sketch.getK()) == sketch.getBitPattern();

   }

   static String toString(boolean withLevels, boolean withLevelsAndItems, ItemsSketch sk) {
      StringBuilder sb = new StringBuilder();
      String thisSimpleName = sk.getClass().getSimpleName();
      int bbCount = sk.getBaseBufferCount();
      int combBufCap = sk.getCombinedBufferAllocatedCount();
      int k = sk.getK();
      long bitPattern = sk.getBitPattern();
      long n = sk.getN();
      String nStr = String.format("%,d", n);
      String bbCntStr = String.format("%,d", bbCount);
      String combBufCapStr = String.format("%,d", combBufCap);
      int neededLevels = ClassicUtil.computeNumLevelsNeeded(k, n);
      int totalLevels = ClassicUtil.computeTotalLevels(bitPattern);
      int validLevels = ClassicUtil.computeValidLevels(bitPattern);
      int numRetained = sk.getNumRetained();
      String numRetainedStr = String.format("%,d", numRetained);
      int preBytes = sk.isEmpty() ? 8 : 16;
      double epsPmf = ClassicUtil.getNormalizedRankError(k, true);
      String epsPmfPctStr = String.format("%.3f%%", epsPmf * (double)100.0F);
      double eps = ClassicUtil.getNormalizedRankError(k, false);
      String epsPctStr = String.format("%.3f%%", eps * (double)100.0F);
      T minItem = (T)(sk.isEmpty() ? null : sk.getMinItem());
      T maxItem = (T)(sk.isEmpty() ? null : sk.getMaxItem());
      sb.append(Util.LS).append("### Classic Quantiles ").append(thisSimpleName).append(" SUMMARY: ").append(Util.LS);
      sb.append("    Empty                        : ").append(sk.isEmpty()).append(Util.LS);
      sb.append("    Estimation Mode              : ").append(sk.isEstimationMode()).append(Util.LS);
      sb.append("    K                            : ").append(k).append(Util.LS);
      sb.append("    N                            : ").append(nStr).append(Util.LS);
      sb.append("    Levels (Needed, Total, Valid): ").append(neededLevels + ", " + totalLevels + ", " + validLevels).append(Util.LS);
      sb.append("    Level Bit Pattern            : ").append(Long.toBinaryString(bitPattern)).append(Util.LS);
      sb.append("    Base Buffer Count            : ").append(bbCntStr).append(Util.LS);
      sb.append("    Combined Buffer Capacity     : ").append(combBufCapStr).append(Util.LS);
      sb.append("    Retained Items               : ").append(numRetainedStr).append(Util.LS);
      sb.append("    Preamble Bytes               : ").append(preBytes).append(Util.LS);
      sb.append("    Normalized Rank Error        : ").append(epsPctStr).append(Util.LS);
      sb.append("    Normalized Rank Error (PMF)  : ").append(epsPmfPctStr).append(Util.LS);
      sb.append("    Min Item                     : ").append(minItem).append(Util.LS);
      sb.append("    Max Item                     : ").append(maxItem).append(Util.LS);
      sb.append("### END SKETCH SUMMARY").append(Util.LS);
      if (withLevels) {
         sb.append(outputLevels(sk));
      }

      if (withLevelsAndItems) {
         sb.append(outputDataDetail(sk));
      }

      return sb.toString();
   }

   private static String outputLevels(ItemsSketch sk) {
      String name = sk.getClass().getSimpleName();
      int k = sk.getK();
      long n = sk.getN();
      int totNumLevels = ClassicUtil.computeNumLevelsNeeded(k, n);
      long bitPattern = sk.getBitPattern();
      StringBuilder sb = new StringBuilder();
      sb.append(Util.LS).append("### ").append(name).append(" LEVELS ABOVE BASE BUF:").append(Util.LS);
      if (totNumLevels == 0) {
         sb.append("    <NONE>").append(Util.LS);
      } else {
         sb.append("    Level |  Valid |  Weight").append(Util.LS);

         for(int i = 0; i < totNumLevels; ++i) {
            String wt = "" + (1L << i + 1);
            String valid = getValidFromLevel(i, bitPattern) ? "T" : "F";
            String row = String.format("  %7s %8s %9s", i, valid, wt);
            sb.append(row).append(Util.LS);
         }
      }

      sb.append("### END LEVELS ABOVE BASE BUF").append(Util.LS);
      return sb.toString();
   }

   private static String outputDataDetail(ItemsSketch sk) {
      String name = sk.getClass().getSimpleName();
      int k = sk.getK();
      long n = sk.getN();
      long bitPattern = sk.getBitPattern();
      int bbCount = sk.getBaseBufferCount();
      int combBufCap = sk.getCombinedBufferAllocatedCount();
      StringBuilder sb = new StringBuilder();
      sb.append(Util.LS).append("### ").append(name).append(" DATA DETAIL: ").append(Util.LS);
      Object[] items = sk.getCombinedBuffer();
      if (n == 0L) {
         sb.append("    <NO DATA>").append(Util.LS);
      } else {
         sb.append("  Index | Level | Valid | Item").append(Util.LS);

         for(int i = 0; i < combBufCap; ++i) {
            int levelNum = getLevelNum(k, i);
            String lvlStr = levelNum == -1 ? "BB" : "" + levelNum;
            String validLvl = getValidFromIndex(levelNum, bitPattern, i, bbCount) ? "T" : "F";
            String row = String.format("%7s %7s %7s   %s", i, lvlStr, validLvl, items[i]);
            sb.append(row).append(Util.LS);
         }
      }

      sb.append("### END DATA DETAIL").append(Util.LS);
      return sb.toString();
   }

   private static boolean getValidFromIndex(int levelNum, long bitPattern, int index, int bbCount) {
      return levelNum == -1 && index < bbCount || getValidFromLevel(levelNum, bitPattern);
   }

   private static boolean getValidFromLevel(int levelNum, long bitPattern) {
      return (1L << levelNum & bitPattern) > 0L;
   }

   private static int getLevelNum(int k, int index) {
      int twoK = 2 * k;
      return index < twoK ? -1 : (index - twoK) / k;
   }
}
