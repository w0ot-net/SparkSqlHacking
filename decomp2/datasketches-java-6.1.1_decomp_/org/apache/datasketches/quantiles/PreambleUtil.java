package org.apache.datasketches.quantiles;

import java.nio.ByteOrder;
import org.apache.datasketches.common.Family;
import org.apache.datasketches.common.Util;
import org.apache.datasketches.memory.Memory;
import org.apache.datasketches.memory.WritableMemory;

final class PreambleUtil {
   static final int PREAMBLE_LONGS_BYTE = 0;
   static final int SER_VER_BYTE = 1;
   static final int FAMILY_BYTE = 2;
   static final int FLAGS_BYTE = 3;
   static final int K_SHORT = 4;
   static final int N_LONG = 8;
   static final int MIN_DOUBLE = 16;
   static final int MAX_DOUBLE = 24;
   static final int COMBINED_BUFFER = 32;
   static final int BIG_ENDIAN_FLAG_MASK = 1;
   static final int READ_ONLY_FLAG_MASK = 2;
   static final int EMPTY_FLAG_MASK = 4;
   static final int COMPACT_FLAG_MASK = 8;
   static final int ORDERED_FLAG_MASK = 16;
   static final boolean NATIVE_ORDER_IS_BIG_ENDIAN;
   static final int DEFAULT_K = 128;

   private PreambleUtil() {
   }

   static String toString(byte[] byteArr, boolean isDoublesSketch) {
      Memory mem = Memory.wrap(byteArr);
      return toString(mem, isDoublesSketch);
   }

   static String toString(Memory mem, boolean isDoublesSketch) {
      return memoryToString(mem, isDoublesSketch);
   }

   private static String memoryToString(Memory srcMem, boolean isDoublesSketch) {
      int preLongs = extractPreLongs(srcMem);
      int serVer = extractSerVer(srcMem);
      int familyID = extractFamilyID(srcMem);
      String famName = Family.idToFamily(familyID).toString();
      int flags = extractFlags(srcMem);
      boolean bigEndian = (flags & 1) > 0;
      String nativeOrder = ByteOrder.nativeOrder().toString();
      boolean readOnly = (flags & 2) > 0;
      boolean empty = (flags & 4) > 0;
      boolean compact = (flags & 8) > 0;
      boolean ordered = (flags & 16) > 0;
      int k = extractK(srcMem);
      long n = preLongs == 1 ? 0L : extractN(srcMem);
      double minDouble = Double.NaN;
      double maxDouble = Double.NaN;
      if (preLongs > 1 && isDoublesSketch) {
         minDouble = extractMinDouble(srcMem);
         maxDouble = extractMaxDouble(srcMem);
      }

      StringBuilder sb = new StringBuilder();
      sb.append(Util.LS);
      sb.append("### QUANTILES SKETCH PREAMBLE SUMMARY:").append(Util.LS);
      sb.append("Byte  0: Preamble Longs       : ").append(preLongs).append(Util.LS);
      sb.append("Byte  1: Serialization Version: ").append(serVer).append(Util.LS);
      sb.append("Byte  2: Family               : ").append(famName).append(Util.LS);
      sb.append("Byte  3: Flags Field          : ").append(String.format("%02o", flags)).append(Util.LS);
      sb.append("  BIG ENDIAN                  : ").append(bigEndian).append(Util.LS);
      sb.append("  (Native Byte Order)         : ").append(nativeOrder).append(Util.LS);
      sb.append("  READ ONLY                   : ").append(readOnly).append(Util.LS);
      sb.append("  EMPTY                       : ").append(empty).append(Util.LS);
      sb.append("  COMPACT                     : ").append(compact).append(Util.LS);
      sb.append("  ORDERED                     : ").append(ordered).append(Util.LS);
      sb.append("Bytes  4-5  : K               : ").append(k).append(Util.LS);
      if (preLongs == 1) {
         sb.append(" --ABSENT, ASSUMED:").append(Util.LS);
      }

      sb.append("Bytes  8-15 : N                : ").append(n).append(Util.LS);
      if (isDoublesSketch) {
         sb.append("MinDouble                      : ").append(minDouble).append(Util.LS);
         sb.append("MaxDouble                      : ").append(maxDouble).append(Util.LS);
      }

      sb.append("Retained Items                 : ").append(ClassicUtil.computeRetainedItems(k, n)).append(Util.LS);
      sb.append("Total Bytes                    : ").append(srcMem.getCapacity()).append(Util.LS);
      sb.append("### END SKETCH PREAMBLE SUMMARY").append(Util.LS);
      return sb.toString();
   }

   static int extractPreLongs(Memory mem) {
      return mem.getByte(0L) & 255;
   }

   static int extractSerVer(Memory mem) {
      return mem.getByte(1L) & 255;
   }

   static int extractFamilyID(Memory mem) {
      return mem.getByte(2L) & 255;
   }

   static int extractFlags(Memory mem) {
      return mem.getByte(3L) & 255;
   }

   static int extractK(Memory mem) {
      return mem.getShort(4L) & '\uffff';
   }

   static long extractN(Memory mem) {
      return mem.getLong(8L);
   }

   static double extractMinDouble(Memory mem) {
      return mem.getDouble(16L);
   }

   static double extractMaxDouble(Memory mem) {
      return mem.getDouble(24L);
   }

   static void insertPreLongs(WritableMemory wmem, int numPreLongs) {
      wmem.putByte(0L, (byte)numPreLongs);
   }

   static void insertSerVer(WritableMemory wmem, int serVer) {
      wmem.putByte(1L, (byte)serVer);
   }

   static void insertFamilyID(WritableMemory wmem, int famId) {
      wmem.putByte(2L, (byte)famId);
   }

   static void insertFlags(WritableMemory wmem, int flags) {
      wmem.putByte(3L, (byte)flags);
   }

   static void insertK(WritableMemory wmem, int k) {
      wmem.putShort(4L, (short)k);
   }

   static void insertN(WritableMemory wmem, long n) {
      wmem.putLong(8L, n);
   }

   static void insertMinDouble(WritableMemory wmem, double minDouble) {
      wmem.putDouble(16L, minDouble);
   }

   static void insertMaxDouble(WritableMemory wmem, double maxDouble) {
      wmem.putDouble(24L, maxDouble);
   }

   static {
      NATIVE_ORDER_IS_BIG_ENDIAN = ByteOrder.nativeOrder() == ByteOrder.BIG_ENDIAN;
   }
}
