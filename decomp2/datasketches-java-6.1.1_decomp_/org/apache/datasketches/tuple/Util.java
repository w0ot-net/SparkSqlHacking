package org.apache.datasketches.tuple;

import java.lang.reflect.Array;
import java.nio.charset.StandardCharsets;
import org.apache.datasketches.common.SketchesArgumentException;
import org.apache.datasketches.hash.MurmurHash3;
import org.apache.datasketches.memory.XxHash;
import org.apache.datasketches.thetacommon.ThetaUtil;

public final class Util {
   private static final int PRIME = 2050804337;

   public static final long[] doubleToLongArray(double value) {
      double d = value == (double)0.0F ? (double)0.0F : value;
      long[] array = new long[]{Double.doubleToLongBits(d)};
      return array;
   }

   public static final byte[] stringToByteArray(String value) {
      return value != null && !value.isEmpty() ? value.getBytes(StandardCharsets.UTF_8) : null;
   }

   public static short computeSeedHash(long seed) {
      long[] seedArr = new long[]{seed};
      short seedHash = (short)((int)(MurmurHash3.hash(seedArr, 0L)[0] & 65535L));
      if (seedHash == 0) {
         throw new SketchesArgumentException("The given seed: " + seed + " produced a seedHash of zero. You must choose a different seed.");
      } else {
         return seedHash;
      }
   }

   public static final void checkSeedHashes(short seedHashA, short seedHashB) {
      if (seedHashA != seedHashB) {
         throw new SketchesArgumentException("Incompatible Seed Hashes. " + seedHashA + ", " + seedHashB);
      }
   }

   public static int getStartingCapacity(int nomEntries, int lgResizeFactor) {
      return 1 << ThetaUtil.startingSubMultiple(Integer.numberOfTrailingZeros(org.apache.datasketches.common.Util.ceilingPowerOf2(nomEntries) * 2), lgResizeFactor, 5);
   }

   public static String stringConcat(String[] strArr) {
      int len = strArr.length;
      StringBuilder sb = new StringBuilder();

      for(int i = 0; i < len; ++i) {
         sb.append(strArr[i]);
         if (i + 1 < len) {
            sb.append(',');
         }
      }

      return sb.toString();
   }

   public static long stringHash(String s) {
      return XxHash.hashString(s, 0L, (long)s.length(), 2050804337L);
   }

   public static long stringArrHash(String[] strArray) {
      String s = stringConcat(strArray);
      return XxHash.hashCharArr(s.toCharArray(), 0L, (long)s.length(), 2050804337L);
   }

   public static Summary[] copySummaryArray(Summary[] summaryArr) {
      int len = summaryArr.length;
      S[] tmpSummaryArr = (S[])newSummaryArray(summaryArr, len);

      for(int i = 0; i < len; ++i) {
         S summary = (S)summaryArr[i];
         if (summary != null) {
            tmpSummaryArr[i] = summary.copy();
         }
      }

      return tmpSummaryArr;
   }

   public static Summary[] newSummaryArray(Summary[] summaryArr, int length) {
      Class<S> summaryType = summaryArr.getClass().getComponentType();
      S[] tmpSummaryArr = (S[])((Summary[])((Summary[])Array.newInstance(summaryType, length)));
      return tmpSummaryArr;
   }
}
