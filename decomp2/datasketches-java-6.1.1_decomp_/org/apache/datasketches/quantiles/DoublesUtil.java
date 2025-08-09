package org.apache.datasketches.quantiles;

import java.util.Arrays;
import org.apache.datasketches.common.SketchesArgumentException;
import org.apache.datasketches.common.Util;

final class DoublesUtil {
   private DoublesUtil() {
   }

   static HeapUpdateDoublesSketch copyToHeap(DoublesSketch sketch) {
      HeapUpdateDoublesSketch qsCopy = HeapUpdateDoublesSketch.newInstance(sketch.getK());
      qsCopy.putN(sketch.getN());
      qsCopy.putMinItem(sketch.isEmpty() ? Double.NaN : sketch.getMinItem());
      qsCopy.putMaxItem(sketch.isEmpty() ? Double.NaN : sketch.getMaxItem());
      qsCopy.putBaseBufferCount(sketch.getBaseBufferCount());
      qsCopy.putBitPattern(sketch.getBitPattern());
      if (sketch.isCompact()) {
         int combBufItems = ClassicUtil.computeCombinedBufferItemCapacity(sketch.getK(), sketch.getN());
         double[] combBuf = new double[combBufItems];
         qsCopy.putCombinedBuffer(combBuf);
         DoublesSketchAccessor sketchAccessor = DoublesSketchAccessor.wrap(sketch);
         DoublesSketchAccessor copyAccessor = DoublesSketchAccessor.wrap(qsCopy);
         copyAccessor.putArray(sketchAccessor.getArray(0, sketchAccessor.numItems()), 0, 0, sketchAccessor.numItems());
         long bitPattern = sketch.getBitPattern();

         for(int lvl = 0; bitPattern != 0L; bitPattern >>>= 1) {
            if ((bitPattern & 1L) > 0L) {
               sketchAccessor.setLevel(lvl);
               copyAccessor.setLevel(lvl);
               copyAccessor.putArray(sketchAccessor.getArray(0, sketchAccessor.numItems()), 0, 0, sketchAccessor.numItems());
            }

            ++lvl;
         }
      } else {
         double[] combBuf = sketch.getCombinedBuffer();
         qsCopy.putCombinedBuffer(Arrays.copyOf(combBuf, combBuf.length));
      }

      return qsCopy;
   }

   static void checkDoublesSerVer(int serVer, int minSupportedSerVer) {
      int max = 3;
      if (serVer > 3 || serVer < minSupportedSerVer) {
         throw new SketchesArgumentException("Possible corruption: Unsupported Serialization Version: " + serVer);
      }
   }

   static String toString(boolean withLevels, boolean withLevelsAndItems, DoublesSketch sk) {
      StringBuilder sb = new StringBuilder();
      sb.append(getSummary(sk));
      if (withLevels) {
         sb.append(outputLevels(sk));
      }

      if (withLevelsAndItems) {
         sb.append(outputDataDetail(sk));
      }

      return sb.toString();
   }

   private static String getSummary(DoublesSketch sk) {
      StringBuilder sb = new StringBuilder();
      String thisSimpleName = sk.getClass().getSimpleName();
      int k = sk.getK();
      String kStr = String.format("%,d", k);
      long n = sk.getN();
      String nStr = String.format("%,d", n);
      String bbCntStr = String.format("%,d", sk.getBaseBufferCount());
      String combBufCapStr = String.format("%,d", sk.getCombinedBufferItemCapacity());
      long bitPattern = sk.getBitPattern();
      int neededLevels = ClassicUtil.computeNumLevelsNeeded(k, n);
      int totalLevels = ClassicUtil.computeTotalLevels(bitPattern);
      int validLevels = ClassicUtil.computeValidLevels(bitPattern);
      String retItemsStr = String.format("%,d", sk.getNumRetained());
      int preBytes = sk.isEmpty() ? 8 : 16;
      String cmptBytesStr = String.format("%,d", sk.getCurrentCompactSerializedSizeBytes());
      String updtBytesStr = String.format("%,d", sk.getCurrentUpdatableSerializedSizeBytes());
      double epsPmf = ClassicUtil.getNormalizedRankError(k, true);
      String epsPmfPctStr = String.format("%.3f%%", epsPmf * (double)100.0F);
      double eps = ClassicUtil.getNormalizedRankError(k, false);
      String epsPctStr = String.format("%.3f%%", eps * (double)100.0F);
      String memCap = sk.hasMemory() ? Long.toString(sk.getMemory().getCapacity()) : "";
      double minItem = sk.isEmpty() ? Double.NaN : sk.getMinItem();
      double maxItem = sk.isEmpty() ? Double.NaN : sk.getMaxItem();
      sb.append(Util.LS).append("### Classic Quantiles ").append(thisSimpleName).append(" SUMMARY: ").append(Util.LS);
      sb.append("    Empty                        : ").append(sk.isEmpty()).append(Util.LS);
      sb.append("    Memory, Capacity bytes       : ").append(sk.hasMemory()).append(", ").append(memCap).append(Util.LS);
      sb.append("    Estimation Mode              : ").append(sk.isEstimationMode()).append(Util.LS);
      sb.append("    K                            : ").append(kStr).append(Util.LS);
      sb.append("    N                            : ").append(nStr).append(Util.LS);
      sb.append("    Levels (Needed, Total, Valid): ").append(neededLevels + ", " + totalLevels + ", " + validLevels).append(Util.LS);
      sb.append("    Level Bit Pattern            : ").append(Long.toBinaryString(bitPattern)).append(Util.LS);
      sb.append("    Base Buffer Count            : ").append(bbCntStr).append(Util.LS);
      sb.append("    Combined Buffer Capacity     : ").append(combBufCapStr).append(Util.LS);
      sb.append("    Retained Items               : ").append(retItemsStr).append(Util.LS);
      sb.append("    Preamble Bytes               : ").append(preBytes).append(Util.LS);
      sb.append("    Compact Storage Bytes        : ").append(cmptBytesStr).append(Util.LS);
      sb.append("    Updatable Storage Bytes      : ").append(updtBytesStr).append(Util.LS);
      sb.append("    Normalized Rank Error        : ").append(epsPctStr).append(Util.LS);
      sb.append("    Normalized Rank Error (PMF)  : ").append(epsPmfPctStr).append(Util.LS);
      sb.append("    Min Item                     : ").append(String.format("%12.6e", minItem)).append(Util.LS);
      sb.append("    Max Item                     : ").append(String.format("%12.6e", maxItem)).append(Util.LS);
      sb.append("### END SKETCH SUMMARY").append(Util.LS);
      return sb.toString();
   }

   private static String outputLevels(DoublesSketch sk) {
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

   private static String outputDataDetail(DoublesSketch sk) {
      String name = sk.getClass().getSimpleName();
      int k = sk.getK();
      long n = sk.getN();
      long bitPattern = sk.getBitPattern();
      int bbCount = sk.getBaseBufferCount();
      int combBufCap = sk.getCombinedBufferItemCapacity();
      StringBuilder sb = new StringBuilder();
      sb.append(Util.LS).append("### ").append(name).append(" DATA DETAIL: ").append(Util.LS);
      double[] items = sk.getCombinedBuffer();
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
