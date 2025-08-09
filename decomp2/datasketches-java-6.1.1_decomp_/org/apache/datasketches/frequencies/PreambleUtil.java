package org.apache.datasketches.frequencies;

import org.apache.datasketches.common.Family;
import org.apache.datasketches.common.SketchesArgumentException;
import org.apache.datasketches.memory.Memory;

final class PreambleUtil {
   static final int PREAMBLE_LONGS_BYTE = 0;
   static final int SER_VER_BYTE = 1;
   static final int FAMILY_BYTE = 2;
   static final int LG_MAX_MAP_SIZE_BYTE = 3;
   static final int LG_CUR_MAP_SIZE_BYTE = 4;
   static final int FLAGS_BYTE = 5;
   static final int SER_DE_ID_SHORT = 6;
   static final int ACTIVE_ITEMS_INT = 8;
   static final int STREAMLENGTH_LONG = 16;
   static final int OFFSET_LONG = 24;
   static final int EMPTY_FLAG_MASK = 5;
   static final int SER_VER = 1;

   private PreambleUtil() {
   }

   public static String preambleToString(Memory srcMem) {
      long pre0 = checkPreambleSize(srcMem);
      int preLongs = extractPreLongs(pre0);
      int serVer = extractSerVer(pre0);
      Family family = Family.idToFamily(extractFamilyID(pre0));
      int lgMaxMapSize = extractLgMaxMapSize(pre0);
      int lgCurMapSize = extractLgCurMapSize(pre0);
      int flags = extractFlags(pre0);
      String flagsStr = org.apache.datasketches.common.Util.zeroPad(Integer.toBinaryString(flags), 8) + ", " + flags;
      boolean empty = (flags & 5) > 0;
      int maxMapSize = 1 << lgMaxMapSize;
      int curMapSize = 1 << lgCurMapSize;
      int maxPreLongs = Family.FREQUENCY.getMaxPreLongs();
      int activeItems = 0;
      long streamLength = 0L;
      long offset = 0L;
      if (preLongs == maxPreLongs) {
         long[] preArr = new long[preLongs];
         srcMem.getLongArray(0L, preArr, 0, preLongs);
         activeItems = extractActiveItems(preArr[1]);
         streamLength = preArr[2];
         offset = preArr[3];
      }

      StringBuilder sb = new StringBuilder();
      sb.append(org.apache.datasketches.common.Util.LS).append("### FREQUENCY SKETCH PREAMBLE SUMMARY:").append(org.apache.datasketches.common.Util.LS).append("Byte  0: Preamble Longs       : ").append(preLongs).append(org.apache.datasketches.common.Util.LS).append("Byte  1: Serialization Version: ").append(serVer).append(org.apache.datasketches.common.Util.LS).append("Byte  2: Family               : ").append(family.toString()).append(org.apache.datasketches.common.Util.LS).append("Byte  3: MaxMapSize           : ").append(maxMapSize).append(org.apache.datasketches.common.Util.LS).append("Byte  4: CurMapSize           : ").append(curMapSize).append(org.apache.datasketches.common.Util.LS).append("Byte  5: Flags Field          : ").append(flagsStr).append(org.apache.datasketches.common.Util.LS).append("  EMPTY                       : ").append(empty).append(org.apache.datasketches.common.Util.LS);
      if (preLongs == 1) {
         sb.append(" --ABSENT, ASSUMED:").append(org.apache.datasketches.common.Util.LS);
      } else {
         sb.append("Bytes 8-11 : ActiveItems      : ").append(activeItems).append(org.apache.datasketches.common.Util.LS);
         sb.append("Bytes 16-23: StreamLength     : ").append(streamLength).append(org.apache.datasketches.common.Util.LS).append("Bytes 24-31: Offset           : ").append(offset).append(org.apache.datasketches.common.Util.LS);
      }

      sb.append("Preamble Bytes                : ").append(preLongs * 8).append(org.apache.datasketches.common.Util.LS);
      sb.append("TOTAL Sketch Bytes            : ").append(preLongs + activeItems * 2 << 3).append(org.apache.datasketches.common.Util.LS).append("### END FREQUENCY SKETCH PREAMBLE SUMMARY").append(org.apache.datasketches.common.Util.LS);
      return sb.toString();
   }

   static int extractPreLongs(long pre0) {
      long mask = 63L;
      return (int)(pre0 & 63L);
   }

   static int extractSerVer(long pre0) {
      int shift = 8;
      long mask = 255L;
      return (int)(pre0 >>> 8 & 255L);
   }

   static int extractFamilyID(long pre0) {
      int shift = 16;
      long mask = 255L;
      return (int)(pre0 >>> 16 & 255L);
   }

   static int extractLgMaxMapSize(long pre0) {
      int shift = 24;
      long mask = 255L;
      return (int)(pre0 >>> 24 & 255L);
   }

   static int extractLgCurMapSize(long pre0) {
      int shift = 32;
      long mask = 255L;
      return (int)(pre0 >>> 32 & 255L);
   }

   static int extractFlags(long pre0) {
      int shift = 40;
      long mask = 255L;
      return (int)(pre0 >>> 40 & 255L);
   }

   static int extractActiveItems(long pre1) {
      long mask = 4294967295L;
      return (int)(pre1 & 4294967295L);
   }

   static long insertPreLongs(int preLongs, long pre0) {
      long mask = 63L;
      return (long)preLongs & 63L | -64L & pre0;
   }

   static long insertSerVer(int serVer, long pre0) {
      int shift = 8;
      long mask = 255L;
      return ((long)serVer & 255L) << 8 | -65281L & pre0;
   }

   static long insertFamilyID(int familyID, long pre0) {
      int shift = 16;
      long mask = 255L;
      return ((long)familyID & 255L) << 16 | -16711681L & pre0;
   }

   static long insertLgMaxMapSize(int lgMaxMapSize, long pre0) {
      int shift = 24;
      long mask = 255L;
      return ((long)lgMaxMapSize & 255L) << 24 | -4278190081L & pre0;
   }

   static long insertLgCurMapSize(int lgCurMapSize, long pre0) {
      int shift = 32;
      long mask = 255L;
      return ((long)lgCurMapSize & 255L) << 32 | -1095216660481L & pre0;
   }

   static long insertFlags(int flags, long pre0) {
      int shift = 40;
      long mask = 255L;
      return ((long)flags & 255L) << 40 | -280375465082881L & pre0;
   }

   static long insertActiveItems(int activeItems, long pre1) {
      long mask = 4294967295L;
      return (long)activeItems & 4294967295L | -4294967296L & pre1;
   }

   static long checkPreambleSize(Memory mem) {
      long cap = mem.getCapacity();
      if (cap < 8L) {
         throwNotBigEnough(cap, 8);
      }

      long pre0 = mem.getLong(0L);
      int preLongs = (int)(pre0 & 63L);
      int required = Math.max(preLongs << 3, 8);
      if (cap < (long)required) {
         throwNotBigEnough(cap, required);
      }

      return pre0;
   }

   private static void throwNotBigEnough(long cap, int required) {
      throw new SketchesArgumentException("Possible Corruption: Size of byte array or Memory not large enough for Preamble: Size: " + cap + ", Required: " + required);
   }
}
