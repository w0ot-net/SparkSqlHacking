package org.apache.datasketches.quantiles;

import org.apache.datasketches.common.Family;
import org.apache.datasketches.common.SketchesArgumentException;
import org.apache.datasketches.common.Util;
import org.apache.datasketches.memory.Memory;

public final class ClassicUtil {
   static final int DOUBLES_SER_VER = 3;
   static final int MAX_PRELONGS;
   static final int MIN_K = 2;
   static final int MAX_K = 32768;
   static final char TAB = '\t';

   private ClassicUtil() {
   }

   public static double getNormalizedRankError(int k, boolean pmf) {
      return pmf ? 1.854 / Math.pow((double)k, 0.9657) : 1.576 / Math.pow((double)k, 0.9726);
   }

   public static int getKFromEpsilon(double epsilon, boolean pmf) {
      double eps = Math.max(epsilon, 6.395E-5);
      double kdbl = pmf ? Math.exp(Math.log(1.854 / eps) / 0.9657) : Math.exp(Math.log(1.576 / eps) / 0.9726);
      double krnd = (double)Math.round(kdbl);
      double del = Math.abs(krnd - kdbl);
      int k = (int)(del < 1.0E-6 ? krnd : Math.ceil(kdbl));
      return Math.max(2, Math.min(32768, k));
   }

   static void checkK(int k) {
      if (k < 2 || k > 32768 || !Util.isPowerOf2((long)k)) {
         throw new SketchesArgumentException("K must be >= 2 and <= 32768 and a power of 2: " + k);
      }
   }

   static void checkFamilyID(int familyID) {
      Family family = Family.idToFamily(familyID);
      if (!family.equals(Family.QUANTILES)) {
         throw new SketchesArgumentException("Possible corruption: Invalid Family: " + family.toString());
      }
   }

   static boolean checkPreLongsFlagsCap(int preambleLongs, int flags, long memCapBytes) {
      boolean empty = (flags & 4) > 0;
      int minPre = Family.QUANTILES.getMinPreLongs();
      int maxPre = Family.QUANTILES.getMaxPreLongs();
      boolean valid = preambleLongs == minPre && empty || preambleLongs == maxPre && !empty;
      if (!valid) {
         throw new SketchesArgumentException("Possible corruption: PreambleLongs inconsistent with empty state: " + preambleLongs);
      } else {
         checkHeapFlags(flags);
         if (memCapBytes < (long)(preambleLongs << 3)) {
            throw new SketchesArgumentException("Possible corruption: Insufficient capacity for preamble: " + memCapBytes);
         } else {
            return empty;
         }
      }
   }

   static void checkHeapFlags(int flags) {
      int allowedFlags = 30;
      int flagsMask = -31;
      if ((flags & -31) > 0) {
         throw new SketchesArgumentException("Possible corruption: Invalid flags field: " + Integer.toBinaryString(flags));
      }
   }

   static boolean checkIsCompactMemory(Memory srcMem) {
      int flags = PreambleUtil.extractFlags(srcMem);
      int compactFlags = 10;
      return (flags & 10) > 0;
   }

   static int computeRetainedItems(int k, long n) {
      int bbCnt = computeBaseBufferItems(k, n);
      long bitPattern = computeBitPattern(k, n);
      int validLevels = computeValidLevels(bitPattern);
      return bbCnt + validLevels * k;
   }

   static int computeCombinedBufferItemCapacity(int k, long n) {
      int totLevels = computeNumLevelsNeeded(k, n);
      if (totLevels == 0) {
         int bbItems = computeBaseBufferItems(k, n);
         return Math.max(4, Util.ceilingPowerOf2(bbItems));
      } else {
         return (2 + totLevels) * k;
      }
   }

   static int computeValidLevels(long bitPattern) {
      return Long.bitCount(bitPattern);
   }

   static int computeTotalLevels(long bitPattern) {
      return hiBitPos(bitPattern) + 1;
   }

   static int computeNumLevelsNeeded(int k, long n) {
      return 1 + hiBitPos(n / (2L * (long)k));
   }

   static int computeBaseBufferItems(int k, long n) {
      return (int)(n % (2L * (long)k));
   }

   static long computeBitPattern(int k, long n) {
      return n / (2L * (long)k);
   }

   static int hiBitPos(long num) {
      return 63 - Long.numberOfLeadingZeros(num);
   }

   static int lowestZeroBitStartingAt(long bits, int startingBit) {
      int pos = startingBit & 63;

      for(long myBits = bits >>> pos; (myBits & 1L) != 0L; ++pos) {
         myBits >>>= 1;
      }

      return pos;
   }

   static {
      MAX_PRELONGS = Family.QUANTILES.getMaxPreLongs();
   }
}
