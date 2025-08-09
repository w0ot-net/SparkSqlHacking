package org.apache.datasketches.theta;

import java.nio.ByteOrder;
import org.apache.datasketches.common.Family;
import org.apache.datasketches.common.ResizeFactor;
import org.apache.datasketches.common.SketchesArgumentException;
import org.apache.datasketches.common.Util;
import org.apache.datasketches.memory.Memory;
import org.apache.datasketches.memory.WritableMemory;
import org.apache.datasketches.thetacommon.ThetaUtil;

final class PreambleUtil {
   static final int PREAMBLE_LONGS_BYTE = 0;
   static final int LG_RESIZE_FACTOR_BIT = 6;
   static final int SER_VER_BYTE = 1;
   static final int FAMILY_BYTE = 2;
   static final int LG_NOM_LONGS_BYTE = 3;
   static final int LG_ARR_LONGS_BYTE = 4;
   static final int FLAGS_BYTE = 5;
   static final int SEED_HASH_SHORT = 6;
   static final int RETAINED_ENTRIES_INT = 8;
   static final int P_FLOAT = 12;
   static final int THETA_LONG = 16;
   static final int UNION_THETA_LONG = 24;
   static final int BIG_ENDIAN_FLAG_MASK = 1;
   static final int READ_ONLY_FLAG_MASK = 2;
   static final int EMPTY_FLAG_MASK = 4;
   static final int COMPACT_FLAG_MASK = 8;
   static final int ORDERED_FLAG_MASK = 16;
   static final int SINGLEITEM_FLAG_MASK = 32;
   static final int LG_RESIZE_RATIO_BYTE_V1 = 5;
   static final int FLAGS_BYTE_V1 = 6;
   static final int SER_VER = 3;
   static final int ENTRY_BITS_BYTE_V4 = 3;
   static final int NUM_ENTRIES_BYTES_BYTE_V4 = 4;
   static final int THETA_LONG_V4 = 8;
   static final boolean NATIVE_ORDER_IS_BIG_ENDIAN;

   private PreambleUtil() {
   }

   static final int getMemBytes(int lgArrLongs, int preambleLongs) {
      return (8 << lgArrLongs) + (preambleLongs << 3);
   }

   static String preambleToString(byte[] byteArr) {
      Memory mem = Memory.wrap(byteArr);
      return preambleToString(mem);
   }

   static String preambleToString(Memory mem) {
      int preLongs = getAndCheckPreLongs(mem);
      int rfId = extractLgResizeFactor(mem);
      ResizeFactor rf = ResizeFactor.getRF(rfId);
      int serVer = extractSerVer(mem);
      int familyId = extractFamilyID(mem);
      Family family = Family.idToFamily(familyId);
      int lgNomLongs = extractLgNomLongs(mem);
      int lgArrLongs = extractLgArrLongs(mem);
      int flags = extractFlags(mem);
      String flagsStr = flags + ", 0x" + Integer.toHexString(flags) + ", " + Util.zeroPad(Integer.toBinaryString(flags), 8);
      String nativeOrder = ByteOrder.nativeOrder().toString();
      boolean bigEndian = (flags & 1) > 0;
      boolean readOnly = (flags & 2) > 0;
      boolean empty = (flags & 4) > 0;
      boolean compact = (flags & 8) > 0;
      boolean ordered = (flags & 16) > 0;
      boolean singleItem = (flags & 32) > 0;
      int seedHash = extractSeedHash(mem);
      int curCount = singleItem ? 1 : 0;
      float p = 1.0F;
      long thetaLong = Long.MAX_VALUE;
      long thetaULong = thetaLong;
      if (preLongs == 2) {
         curCount = extractCurCount(mem);
         p = extractP(mem);
      } else if (preLongs == 3) {
         curCount = extractCurCount(mem);
         p = extractP(mem);
         thetaLong = extractThetaLong(mem);
         thetaULong = thetaLong;
      } else if (preLongs == 4) {
         curCount = extractCurCount(mem);
         p = extractP(mem);
         thetaLong = extractThetaLong(mem);
         thetaULong = extractUnionThetaLong(mem);
      }

      double thetaDbl = (double)thetaLong / (double)Long.MAX_VALUE;
      String thetaHex = Util.zeroPad(Long.toHexString(thetaLong), 16);
      double thetaUDbl = (double)thetaULong / (double)Long.MAX_VALUE;
      String thetaUHex = Util.zeroPad(Long.toHexString(thetaULong), 16);
      StringBuilder sb = new StringBuilder();
      sb.append(Util.LS);
      sb.append("### SKETCH PREAMBLE SUMMARY:").append(Util.LS);
      sb.append("Native Byte Order             : ").append(nativeOrder).append(Util.LS);
      sb.append("Byte  0: Preamble Longs       : ").append(preLongs).append(Util.LS);
      sb.append("Byte  0: ResizeFactor         : ").append(rfId + ", " + rf.toString()).append(Util.LS);
      sb.append("Byte  1: Serialization Version: ").append(serVer).append(Util.LS);
      sb.append("Byte  2: Family               : ").append(familyId + ", " + family.toString()).append(Util.LS);
      sb.append("Byte  3: LgNomLongs           : ").append(lgNomLongs).append(Util.LS);
      sb.append("Byte  4: LgArrLongs           : ").append(lgArrLongs).append(Util.LS);
      sb.append("Byte  5: Flags Field          : ").append(flagsStr).append(Util.LS);
      sb.append("  Bit Flag Name               : State:").append(Util.LS);
      sb.append("    0 BIG_ENDIAN_STORAGE      : ").append(bigEndian).append(Util.LS);
      sb.append("    1 READ_ONLY               : ").append(readOnly).append(Util.LS);
      sb.append("    2 EMPTY                   : ").append(empty).append(Util.LS);
      sb.append("    3 COMPACT                 : ").append(compact).append(Util.LS);
      sb.append("    4 ORDERED                 : ").append(ordered).append(Util.LS);
      sb.append("    5 SINGLE_ITEM             : ").append(singleItem).append(Util.LS);
      sb.append("Bytes 6-7  : Seed Hash Hex    : ").append(Integer.toHexString(seedHash)).append(Util.LS);
      if (preLongs == 1) {
         sb.append(" --ABSENT FIELDS, ASSUMED:").append(Util.LS);
         sb.append("Bytes 8-11 : CurrentCount     : ").append(curCount).append(Util.LS);
         sb.append("Bytes 12-15: P                : ").append(p).append(Util.LS);
         sb.append("Bytes 16-23: Theta (double)   : ").append(thetaDbl).append(Util.LS);
         sb.append("             Theta (long)     : ").append(thetaLong).append(Util.LS);
         sb.append("             Theta (long,hex) : ").append(thetaHex).append(Util.LS);
      } else if (preLongs == 2) {
         sb.append("Bytes 8-11 : CurrentCount     : ").append(curCount).append(Util.LS);
         sb.append("Bytes 12-15: P                : ").append(p).append(Util.LS);
         sb.append(" --ABSENT, ASSUMED:").append(Util.LS);
         sb.append("Bytes 16-23: Theta (double)   : ").append(thetaDbl).append(Util.LS);
         sb.append("             Theta (long)     : ").append(thetaLong).append(Util.LS);
         sb.append("             Theta (long,hex) : ").append(thetaHex).append(Util.LS);
      } else if (preLongs == 3) {
         sb.append("Bytes 8-11 : CurrentCount     : ").append(curCount).append(Util.LS);
         sb.append("Bytes 12-15: P                : ").append(p).append(Util.LS);
         sb.append("Bytes 16-23: Theta (double)   : ").append(thetaDbl).append(Util.LS);
         sb.append("             Theta (long)     : ").append(thetaLong).append(Util.LS);
         sb.append("             Theta (long,hex) : ").append(thetaHex).append(Util.LS);
      } else {
         sb.append("Bytes 8-11 : CurrentCount     : ").append(curCount).append(Util.LS);
         sb.append("Bytes 12-15: P                : ").append(p).append(Util.LS);
         sb.append("Bytes 16-23: Theta (double)   : ").append(thetaDbl).append(Util.LS);
         sb.append("             Theta (long)     : ").append(thetaLong).append(Util.LS);
         sb.append("             Theta (long,hex) : ").append(thetaHex).append(Util.LS);
         sb.append("Bytes 25-31: ThetaU (double)  : ").append(thetaUDbl).append(Util.LS);
         sb.append("             ThetaU (long)    : ").append(thetaULong).append(Util.LS);
         sb.append("             ThetaU (long,hex): ").append(thetaUHex).append(Util.LS);
      }

      sb.append("Preamble Bytes                : ").append(preLongs * 8).append(Util.LS);
      sb.append("Data Bytes                    : ").append(curCount * 8).append(Util.LS);
      sb.append("TOTAL Sketch Bytes            : ").append((preLongs + curCount) * 8).append(Util.LS);
      sb.append("TOTAL Capacity Bytes          : ").append(mem.getCapacity()).append(Util.LS);
      sb.append("### END SKETCH PREAMBLE SUMMARY").append(Util.LS);
      return sb.toString();
   }

   static int extractPreLongs(Memory mem) {
      return mem.getByte(0L) & 63;
   }

   static int extractLgResizeFactor(Memory mem) {
      return mem.getByte(0L) >>> 6 & 3;
   }

   static int extractLgResizeRatioV1(Memory mem) {
      return mem.getByte(5L) & 3;
   }

   static int extractSerVer(Memory mem) {
      return mem.getByte(1L) & 255;
   }

   static int extractFamilyID(Memory mem) {
      return mem.getByte(2L) & 255;
   }

   static int extractLgNomLongs(Memory mem) {
      return mem.getByte(3L) & 255;
   }

   static int extractLgArrLongs(Memory mem) {
      return mem.getByte(4L) & 255;
   }

   static int extractFlags(Memory mem) {
      return mem.getByte(5L) & 255;
   }

   static int extractFlagsV1(Memory mem) {
      return mem.getByte(6L) & 255;
   }

   static int extractSeedHash(Memory mem) {
      return mem.getShort(6L) & '\uffff';
   }

   static int extractCurCount(Memory mem) {
      return mem.getInt(8L);
   }

   static float extractP(Memory mem) {
      return mem.getFloat(12L);
   }

   static long extractThetaLong(Memory mem) {
      return mem.getLong(16L);
   }

   static long extractUnionThetaLong(Memory mem) {
      return mem.getLong(24L);
   }

   static int extractEntryBitsV4(Memory mem) {
      return mem.getByte(3L) & 255;
   }

   static int extractNumEntriesBytesV4(Memory mem) {
      return mem.getByte(4L) & 255;
   }

   static long extractThetaLongV4(Memory mem) {
      return mem.getLong(8L);
   }

   static void insertPreLongs(WritableMemory wmem, int preLongs) {
      wmem.putByte(0L, (byte)(preLongs & 63));
   }

   static void insertLgResizeFactor(WritableMemory wmem, int rf) {
      int curByte = wmem.getByte(0L) & 255;
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

   static void insertLgNomLongs(WritableMemory wmem, int lgNomLongs) {
      wmem.putByte(3L, (byte)lgNomLongs);
   }

   static void insertLgArrLongs(WritableMemory wmem, int lgArrLongs) {
      wmem.putByte(4L, (byte)lgArrLongs);
   }

   static void insertFlags(WritableMemory wmem, int flags) {
      wmem.putByte(5L, (byte)flags);
   }

   static void insertSeedHash(WritableMemory wmem, int seedHash) {
      wmem.putShort(6L, (short)seedHash);
   }

   static void insertCurCount(WritableMemory wmem, int curCount) {
      wmem.putInt(8L, curCount);
   }

   static void insertP(WritableMemory wmem, float p) {
      wmem.putFloat(12L, p);
   }

   static void insertThetaLong(WritableMemory wmem, long thetaLong) {
      wmem.putLong(16L, thetaLong);
   }

   static void insertUnionThetaLong(WritableMemory wmem, long unionThetaLong) {
      wmem.putLong(24L, unionThetaLong);
   }

   static void setEmpty(WritableMemory wmem) {
      int flags = wmem.getByte(5L) & 255;
      flags |= 4;
      wmem.putByte(5L, (byte)flags);
   }

   static void clearEmpty(WritableMemory wmem) {
      int flags = wmem.getByte(5L) & 255;
      flags &= -5;
      wmem.putByte(5L, (byte)flags);
   }

   static boolean isEmptyFlag(Memory mem) {
      return (extractFlags(mem) & 4) > 0;
   }

   static int getAndCheckPreLongs(Memory mem) {
      long cap = mem.getCapacity();
      if (cap < 8L) {
         throwNotBigEnough(cap, 8);
      }

      int preLongs = extractPreLongs(mem);
      int required = Math.max(preLongs << 3, 8);
      if (cap < (long)required) {
         throwNotBigEnough(cap, required);
      }

      return preLongs;
   }

   static final short checkMemorySeedHash(Memory mem, long seed) {
      short seedHashMem = (short)extractSeedHash(mem);
      ThetaUtil.checkSeedHashes(seedHashMem, ThetaUtil.computeSeedHash(seed));
      return seedHashMem;
   }

   private static void throwNotBigEnough(long cap, int required) {
      throw new SketchesArgumentException("Possible Corruption: Size of byte array or Memory not large enough: Size: " + cap + ", Required: " + required);
   }

   static {
      NATIVE_ORDER_IS_BIG_ENDIAN = ByteOrder.nativeOrder() == ByteOrder.BIG_ENDIAN;
   }
}
