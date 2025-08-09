package org.apache.datasketches.sampling;

import java.nio.ByteOrder;
import java.util.Locale;
import org.apache.datasketches.common.Family;
import org.apache.datasketches.common.ResizeFactor;
import org.apache.datasketches.common.SketchesArgumentException;
import org.apache.datasketches.common.Util;
import org.apache.datasketches.memory.Memory;
import org.apache.datasketches.memory.WritableMemory;

final class PreambleUtil {
   static final int PREAMBLE_LONGS_BYTE = 0;
   static final int LG_RESIZE_FACTOR_BIT = 6;
   static final int SER_VER_BYTE = 1;
   static final int FAMILY_BYTE = 2;
   static final int FLAGS_BYTE = 3;
   static final int RESERVOIR_SIZE_SHORT = 4;
   static final int RESERVOIR_SIZE_INT = 4;
   static final int SERDE_ID_SHORT = 6;
   static final int ITEMS_SEEN_LONG = 8;
   static final int MAX_K_SIZE_INT = 4;
   static final int OUTER_TAU_NUM_DOUBLE = 16;
   static final int OUTER_TAU_DENOM_LONG = 24;
   static final int ITEM_COUNT_H_INT = 16;
   static final int ITEM_COUNT_R_INT = 20;
   static final int TOTAL_WEIGHT_R_DOUBLE = 24;
   static final int VO_PRELONGS_EMPTY;
   static final int VO_PRELONGS_WARMUP = 3;
   static final int VO_PRELONGS_FULL;
   static final int EBPPS_CUM_WT_DOUBLE = 16;
   static final int EBPPS_MAX_WT_DOUBLE = 24;
   static final int EBPPS_RHO_DOUBLE = 32;
   static final int EMPTY_FLAG_MASK = 4;
   static final int HAS_PARTIAL_ITEM_MASK = 8;
   static final int GADGET_FLAG_MASK = 128;
   static final int RESERVOIR_SER_VER = 2;
   static final int VAROPT_SER_VER = 2;
   static final int EBPPS_SER_VER = 1;
   static final boolean NATIVE_ORDER_IS_BIG_ENDIAN;

   private PreambleUtil() {
   }

   static String preambleToString(byte[] byteArr) {
      Memory mem = Memory.wrap(byteArr);
      return preambleToString(mem);
   }

   static String preambleToString(Memory mem) {
      int preLongs = getAndCheckPreLongs(mem);
      Family family = Family.idToFamily(mem.getByte(2L));
      switch (family) {
         case RESERVOIR:
         case VAROPT:
            return sketchPreambleToString(mem, family, preLongs);
         case RESERVOIR_UNION:
         case VAROPT_UNION:
            return unionPreambleToString(mem, family, preLongs);
         default:
            throw new SketchesArgumentException("Inspecting preamble with Sampling family's PreambleUtil with object of family " + family.getFamilyName());
      }
   }

   private static String sketchPreambleToString(Memory mem, Family family, int preLongs) {
      ResizeFactor rf = ResizeFactor.getRF(extractResizeFactor(mem));
      int serVer = extractSerVer(mem);
      int flags = extractFlags(mem);
      String flagsStr = Util.zeroPad(Integer.toBinaryString(flags), 8) + ", " + flags;
      boolean isEmpty = (flags & 4) > 0;
      boolean isGadget = (flags & 128) > 0;
      int k;
      if (serVer == 1) {
         short encK = extractEncodedReservoirSize(mem);
         k = ReservoirSize.decodeValue(encK);
      } else {
         k = extractK(mem);
      }

      long n = 0L;
      if (!isEmpty) {
         n = extractN(mem);
      }

      long dataBytes = mem.getCapacity() - (long)(preLongs << 3);
      StringBuilder sb = new StringBuilder();
      sb.append(Util.LS).append("### END ").append(family.getFamilyName().toUpperCase(Locale.US)).append(" PREAMBLE SUMMARY").append(Util.LS).append("Byte  0: Preamble Longs       : ").append(preLongs).append(Util.LS).append("Byte  0: ResizeFactor         : ").append(rf.toString()).append(Util.LS).append("Byte  1: Serialization Version: ").append(serVer).append(Util.LS).append("Byte  2: Family               : ").append(family.toString()).append(Util.LS).append("Byte  3: Flags Field          : ").append(flagsStr).append(Util.LS).append("  EMPTY                       : ").append(isEmpty).append(Util.LS);
      if (family == Family.VAROPT) {
         sb.append("  GADGET                      : ").append(isGadget).append(Util.LS);
      }

      sb.append("Bytes  4-7: Sketch Size (k)   : ").append(k).append(Util.LS);
      if (!isEmpty) {
         sb.append("Bytes 8-15: Items Seen (n)    : ").append(n).append(Util.LS);
      }

      if (family == Family.VAROPT && !isEmpty) {
         int hCount = extractHRegionItemCount(mem);
         int rCount = extractRRegionItemCount(mem);
         double totalRWeight = extractTotalRWeight(mem);
         sb.append("Bytes 16-19: H region count   : ").append(hCount).append(Util.LS).append("Bytes 20-23: R region count   : ").append(rCount).append(Util.LS);
         if (rCount > 0) {
            sb.append("Bytes 24-31: R region weight  : ").append(totalRWeight).append(Util.LS);
         }
      }

      sb.append("TOTAL Sketch Bytes            : ").append(mem.getCapacity()).append(Util.LS).append("  Preamble Bytes              : ").append(preLongs << 3).append(Util.LS).append("  Data Bytes                  : ").append(dataBytes).append(Util.LS).append("### END ").append(family.getFamilyName().toUpperCase(Locale.US)).append(" PREAMBLE SUMMARY").append(Util.LS);
      return sb.toString();
   }

   private static String unionPreambleToString(Memory mem, Family family, int preLongs) {
      ResizeFactor rf = ResizeFactor.getRF(extractResizeFactor(mem));
      int serVer = extractSerVer(mem);
      int flags = extractFlags(mem);
      String flagsStr = Util.zeroPad(Integer.toBinaryString(flags), 8) + ", " + flags;
      boolean isEmpty = (flags & 4) > 0;
      int k;
      if (serVer == 1) {
         short encK = extractEncodedReservoirSize(mem);
         k = ReservoirSize.decodeValue(encK);
      } else {
         k = extractK(mem);
      }

      long dataBytes = mem.getCapacity() - (long)(preLongs << 3);
      return Util.LS + "### END " + family.getFamilyName().toUpperCase(Locale.US) + " PREAMBLE SUMMARY" + Util.LS + "Byte  0: Preamble Longs           : " + preLongs + Util.LS + "Byte  0: ResizeFactor             : " + rf.toString() + Util.LS + "Byte  1: Serialization Version    : " + serVer + Util.LS + "Byte  2: Family                   : " + family.toString() + Util.LS + "Byte  3: Flags Field              : " + flagsStr + Util.LS + "  EMPTY                           : " + isEmpty + Util.LS + "Bytes  4-7: Max Sketch Size (maxK): " + k + Util.LS + "TOTAL Sketch Bytes                : " + mem.getCapacity() + Util.LS + "  Preamble Bytes                  : " + (preLongs << 3) + Util.LS + "  Sketch Bytes                    : " + dataBytes + Util.LS + "### END " + family.getFamilyName().toUpperCase(Locale.US) + " PREAMBLE SUMMARY" + Util.LS;
   }

   static int extractPreLongs(Memory mem) {
      return mem.getByte(0L) & 63;
   }

   static int extractResizeFactor(Memory mem) {
      return mem.getByte(0L) >>> 6 & 3;
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

   static short extractEncodedReservoirSize(Memory mem) {
      return mem.getShort(4L);
   }

   static int extractK(Memory mem) {
      return mem.getInt(4L);
   }

   static int extractMaxK(Memory mem) {
      return extractK(mem);
   }

   static long extractN(Memory mem) {
      return mem.getLong(8L);
   }

   static int extractHRegionItemCount(Memory mem) {
      return mem.getInt(16L);
   }

   static int extractRRegionItemCount(Memory mem) {
      return mem.getInt(20L);
   }

   static double extractTotalRWeight(Memory mem) {
      return mem.getDouble(24L);
   }

   static double extractOuterTauNumerator(Memory mem) {
      return mem.getDouble(16L);
   }

   static long extractOuterTauDenominator(Memory mem) {
      return mem.getLong(24L);
   }

   static double extractEbppsCumulativeWeight(Memory mem) {
      return mem.getDouble(16L);
   }

   static double extractEbppsMaxWeight(Memory mem) {
      return mem.getDouble(24L);
   }

   static double extractEbppsRho(Memory mem) {
      return mem.getDouble(32L);
   }

   static void insertPreLongs(WritableMemory wmem, int preLongs) {
      int curByte = wmem.getByte(0L);
      int mask = 63;
      byte newByte = (byte)(preLongs & 63 | -64 & curByte);
      wmem.putByte(0L, newByte);
   }

   static void insertLgResizeFactor(WritableMemory wmem, int rf) {
      int curByte = wmem.getByte(0L);
      int shift = 6;
      int mask = 3;
      byte newByte = (byte)((rf & 3) << 6 | -193 & curByte);
      wmem.putByte(0L, newByte);
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
      wmem.putInt(4L, k);
   }

   static void insertMaxK(WritableMemory wmem, int maxK) {
      insertK(wmem, maxK);
   }

   static void insertN(WritableMemory wmem, long totalSeen) {
      wmem.putLong(8L, totalSeen);
   }

   static void insertHRegionItemCount(WritableMemory wmem, int hCount) {
      wmem.putInt(16L, hCount);
   }

   static void insertRRegionItemCount(WritableMemory wmem, int rCount) {
      wmem.putInt(20L, rCount);
   }

   static void insertTotalRWeight(WritableMemory wmem, double weight) {
      wmem.putDouble(24L, weight);
   }

   static void insertOuterTauNumerator(WritableMemory wmem, double numer) {
      wmem.putDouble(16L, numer);
   }

   static void insertOuterTauDenominator(WritableMemory wmem, long denom) {
      wmem.putLong(24L, denom);
   }

   static void insertEbppsCumulativeWeight(WritableMemory wmem, double cumWt) {
      wmem.putDouble(16L, cumWt);
   }

   static void insertEbppsMaxWeight(WritableMemory wmem, double maxWt) {
      wmem.putDouble(24L, maxWt);
   }

   static void insertEbppsRho(WritableMemory wmem, double rho) {
      wmem.putDouble(32L, rho);
   }

   static int getAndCheckPreLongs(Memory mem) {
      long cap = mem.getCapacity();
      if (cap < 8L) {
         throwNotBigEnough(cap, 8);
      }

      int preLongs = mem.getByte(0L) & 63;
      int required = Math.max(preLongs << 3, 8);
      if (cap < (long)required) {
         throwNotBigEnough(cap, required);
      }

      return preLongs;
   }

   private static void throwNotBigEnough(long cap, int required) {
      throw new SketchesArgumentException("Possible Corruption: Size of byte array or Memory not large enough: Size: " + cap + ", Required: " + required);
   }

   static {
      VO_PRELONGS_EMPTY = Family.VAROPT.getMinPreLongs();
      VO_PRELONGS_FULL = Family.VAROPT.getMaxPreLongs();
      NATIVE_ORDER_IS_BIG_ENDIAN = ByteOrder.nativeOrder() == ByteOrder.BIG_ENDIAN;
   }
}
