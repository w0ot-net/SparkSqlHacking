package org.apache.datasketches.hll;

import java.nio.ByteOrder;
import org.apache.datasketches.common.Family;
import org.apache.datasketches.common.Util;
import org.apache.datasketches.memory.Memory;
import org.apache.datasketches.memory.WritableMemory;

final class PreambleUtil {
   static int PREAMBLE_INTS_BYTE = 0;
   static int SER_VER_BYTE = 1;
   static int FAMILY_BYTE = 2;
   static int LG_K_BYTE = 3;
   static int LG_ARR_BYTE = 4;
   static int FLAGS_BYTE = 5;
   static int LIST_COUNT_BYTE = 6;
   static int HLL_CUR_MIN_BYTE = 6;
   static int MODE_BYTE = 7;
   static int LIST_INT_ARR_START = 8;
   static int HASH_SET_COUNT_INT = 8;
   static int HASH_SET_INT_ARR_START = 12;
   static int HIP_ACCUM_DOUBLE = 8;
   static int KXQ0_DOUBLE = 16;
   static int KXQ1_DOUBLE = 24;
   static int CUR_MIN_COUNT_INT = 32;
   static int AUX_COUNT_INT = 36;
   static int HLL_BYTE_ARR_START = 40;
   static final int BIG_ENDIAN_FLAG_MASK = 1;
   static final int READ_ONLY_FLAG_MASK = 2;
   static final int EMPTY_FLAG_MASK = 4;
   static final int COMPACT_FLAG_MASK = 8;
   static final int OUT_OF_ORDER_FLAG_MASK = 16;
   static final int REBUILD_CURMIN_NUM_KXQ_MASK = 32;
   static final int CUR_MODE_MASK = 3;
   static final int TGT_HLL_TYPE_MASK = 12;
   static final int SER_VER = 1;
   static final int FAMILY_ID = 7;
   static final int LIST_PREINTS = 2;
   static final int HASH_SET_PREINTS = 3;
   static final int HLL_PREINTS = 10;
   static final boolean NATIVE_ORDER_IS_BIG_ENDIAN;

   private PreambleUtil() {
   }

   static String toString(byte[] byteArr) {
      Memory mem = Memory.wrap(byteArr);
      return toString(mem);
   }

   static String toString(Memory mem) {
      int preInts = mem.getByte((long)PREAMBLE_INTS_BYTE);
      int serVer = mem.getByte((long)SER_VER_BYTE);
      Family family = Family.idToFamily(mem.getByte((long)FAMILY_BYTE));
      int lgK = mem.getByte((long)LG_K_BYTE);
      int lgArr = mem.getByte((long)LG_ARR_BYTE);
      int flags = mem.getByte((long)FLAGS_BYTE);
      String flagsStr = Util.zeroPad(Integer.toBinaryString(flags), 8) + ", " + flags;
      boolean bigEndian = (flags & 1) > 0;
      String nativeOrder = ByteOrder.nativeOrder().toString();
      boolean compact = (flags & 8) > 0;
      boolean oooFlag = (flags & 16) > 0;
      boolean readOnly = (flags & 2) > 0;
      boolean empty = (flags & 4) > 0;
      boolean rebuildKxQ = (flags & 32) > 0;
      int hllCurMin = mem.getByte((long)HLL_CUR_MIN_BYTE);
      int modeByte = mem.getByte((long)MODE_BYTE);
      CurMode curMode = CurMode.fromOrdinal(modeByte & 3);
      TgtHllType tgtHllType = TgtHllType.fromOrdinal(modeByte >>> 2 & 3);
      double hipAccum = (double)0.0F;
      double kxq0 = (double)0.0F;
      double kxq1 = (double)0.0F;
      int hashSetCount = 0;
      int curMinCount = 0;
      int exceptionCount = 0;
      if (curMode == CurMode.SET) {
         hashSetCount = mem.getInt((long)HASH_SET_COUNT_INT);
      } else if (curMode == CurMode.HLL) {
         hipAccum = mem.getDouble((long)HIP_ACCUM_DOUBLE);
         kxq0 = mem.getDouble((long)KXQ0_DOUBLE);
         kxq1 = mem.getDouble((long)KXQ1_DOUBLE);
         curMinCount = mem.getInt((long)CUR_MIN_COUNT_INT);
         exceptionCount = mem.getInt((long)AUX_COUNT_INT);
      }

      StringBuilder sb = new StringBuilder();
      sb.append(Util.LS);
      sb.append("### HLL SKETCH PREAMBLE:").append(Util.LS);
      sb.append("Byte 0: Preamble Ints         : ").append(preInts).append(Util.LS);
      sb.append("Byte 1: SerVer                : ").append(serVer).append(Util.LS);
      sb.append("Byte 2: Family                : ").append(family).append(Util.LS);
      sb.append("Byte 3: lgK                   : ").append(lgK).append(Util.LS);
      if (curMode == CurMode.LIST) {
         sb.append("Byte 4: LgArr: List Arr       : ").append(lgArr).append(Util.LS);
      }

      if (curMode == CurMode.SET) {
         sb.append("Byte 4: LgArr: Hash Set Arr   : ").append(lgArr).append(Util.LS);
      }

      if (curMode == CurMode.HLL) {
         sb.append("Byte 4: LgArr or Aux LgArr    : ").append(lgArr).append(Util.LS);
      }

      sb.append("Byte 5: Flags:                : ").append(flagsStr).append(Util.LS);
      sb.append("  BIG_ENDIAN_STORAGE          : ").append(bigEndian).append(Util.LS);
      sb.append("  (Native Byte Order)         : ").append(nativeOrder).append(Util.LS);
      sb.append("  READ_ONLY                   : ").append(readOnly).append(Util.LS);
      sb.append("  EMPTY                       : ").append(empty).append(Util.LS);
      sb.append("  COMPACT                     : ").append(compact).append(Util.LS);
      sb.append("  OUT_OF_ORDER                : ").append(oooFlag).append(Util.LS);
      sb.append("  REBUILD_KXQ                 : ").append(rebuildKxQ).append(Util.LS);
      if (curMode == CurMode.LIST) {
         sb.append("Byte 6: List Count/CurMin     : ").append(hllCurMin).append(Util.LS);
      }

      if (curMode == CurMode.SET) {
         sb.append("Byte 6: (not used)            : ").append(Util.LS);
      }

      if (curMode == CurMode.HLL) {
         sb.append("Byte 6: Cur Min               : ").append(hllCurMin).append(Util.LS);
      }

      String modes = curMode.toString() + ", " + tgtHllType.toString();
      sb.append("Byte 7: Mode                  : ").append(modes).append(Util.LS);
      if (curMode == CurMode.SET) {
         sb.append("Hash Set Count                : ").append(hashSetCount).append(Util.LS);
      }

      if (curMode == CurMode.HLL) {
         sb.append("HIP Accum                     : ").append(hipAccum).append(Util.LS);
         sb.append("KxQ0                          : ").append(kxq0).append(Util.LS);
         sb.append("KxQ1                          : ").append(kxq1).append(Util.LS);
         sb.append("Num At Cur Min                : ").append(curMinCount).append(Util.LS);
         sb.append("Aux Count                     : ").append(exceptionCount).append(Util.LS);
      }

      sb.append("### END HLL SKETCH PREAMBLE").append(Util.LS);
      return sb.toString();
   }

   static int extractPreInts(Memory mem) {
      return mem.getByte((long)PREAMBLE_INTS_BYTE) & 63;
   }

   static void insertPreInts(WritableMemory wmem, int preInts) {
      wmem.putByte((long)PREAMBLE_INTS_BYTE, (byte)(preInts & 63));
   }

   static int extractSerVer(Memory mem) {
      return mem.getByte((long)SER_VER_BYTE) & 255;
   }

   static void insertSerVer(WritableMemory wmem) {
      wmem.putByte((long)SER_VER_BYTE, (byte)1);
   }

   static int extractFamilyId(Memory mem) {
      return mem.getByte((long)FAMILY_BYTE) & 255;
   }

   static void insertFamilyId(WritableMemory wmem) {
      wmem.putByte((long)FAMILY_BYTE, (byte)7);
   }

   static int extractLgK(Memory mem) {
      return mem.getByte((long)LG_K_BYTE) & 255;
   }

   static void insertLgK(WritableMemory wmem, int lgK) {
      wmem.putByte((long)LG_K_BYTE, (byte)lgK);
   }

   static int extractLgArr(Memory mem) {
      int lgArr = mem.getByte((long)LG_ARR_BYTE) & 255;
      return lgArr;
   }

   static void insertLgArr(WritableMemory wmem, int lgArr) {
      wmem.putByte((long)LG_ARR_BYTE, (byte)lgArr);
   }

   static int extractListCount(Memory mem) {
      return mem.getByte((long)LIST_COUNT_BYTE) & 255;
   }

   static void insertListCount(WritableMemory wmem, int listCnt) {
      wmem.putByte((long)LIST_COUNT_BYTE, (byte)listCnt);
   }

   static int extractCurMin(Memory mem) {
      return mem.getByte((long)HLL_CUR_MIN_BYTE) & 255;
   }

   static void insertCurMin(WritableMemory wmem, int curMin) {
      wmem.putByte((long)HLL_CUR_MIN_BYTE, (byte)curMin);
   }

   static double extractHipAccum(Memory mem) {
      return mem.getDouble((long)HIP_ACCUM_DOUBLE);
   }

   static void insertHipAccum(WritableMemory wmem, double hipAccum) {
      wmem.putDouble((long)HIP_ACCUM_DOUBLE, hipAccum);
   }

   static double extractKxQ0(Memory mem) {
      return mem.getDouble((long)KXQ0_DOUBLE);
   }

   static void insertKxQ0(WritableMemory wmem, double kxq0) {
      wmem.putDouble((long)KXQ0_DOUBLE, kxq0);
   }

   static double extractKxQ1(Memory mem) {
      return mem.getDouble((long)KXQ1_DOUBLE);
   }

   static void insertKxQ1(WritableMemory wmem, double kxq1) {
      wmem.putDouble((long)KXQ1_DOUBLE, kxq1);
   }

   static int extractHashSetCount(Memory mem) {
      return mem.getInt((long)HASH_SET_COUNT_INT);
   }

   static void insertHashSetCount(WritableMemory wmem, int hashSetCnt) {
      wmem.putInt((long)HASH_SET_COUNT_INT, hashSetCnt);
   }

   static int extractNumAtCurMin(Memory mem) {
      return mem.getInt((long)CUR_MIN_COUNT_INT);
   }

   static void insertNumAtCurMin(WritableMemory wmem, int numAtCurMin) {
      wmem.putInt((long)CUR_MIN_COUNT_INT, numAtCurMin);
   }

   static int extractAuxCount(Memory mem) {
      return mem.getInt((long)AUX_COUNT_INT);
   }

   static void insertAuxCount(WritableMemory wmem, int auxCount) {
      wmem.putInt((long)AUX_COUNT_INT, auxCount);
   }

   static void insertCurMode(WritableMemory wmem, CurMode curMode) {
      int curModeId = curMode.ordinal();
      int mode = wmem.getByte((long)MODE_BYTE) & -4;
      mode |= curModeId & 3;
      wmem.putByte((long)MODE_BYTE, (byte)mode);
   }

   static CurMode extractCurMode(Memory mem) {
      int curModeId = mem.getByte((long)MODE_BYTE) & 3;
      return CurMode.fromOrdinal(curModeId);
   }

   static void insertTgtHllType(WritableMemory wmem, TgtHllType tgtHllType) {
      int typeId = tgtHllType.ordinal();
      int mode = wmem.getByte((long)MODE_BYTE) & -13;
      mode |= typeId << 2 & 12;
      wmem.putByte((long)MODE_BYTE, (byte)mode);
   }

   static TgtHllType extractTgtHllType(Memory mem) {
      int typeId = mem.getByte((long)MODE_BYTE) & 12;
      return TgtHllType.fromOrdinal(typeId >>> 2);
   }

   static void insertModes(WritableMemory wmem, TgtHllType tgtHllType, CurMode curMode) {
      int curModeId = curMode.ordinal() & 3;
      int typeId = (tgtHllType.ordinal() & 3) << 2;
      int mode = typeId | curModeId;
      wmem.putByte((long)MODE_BYTE, (byte)mode);
   }

   static void insertEmptyFlag(WritableMemory wmem, boolean empty) {
      int flags = wmem.getByte((long)FLAGS_BYTE);
      if (empty) {
         flags |= 4;
      } else {
         flags &= -5;
      }

      wmem.putByte((long)FLAGS_BYTE, (byte)flags);
   }

   static boolean extractEmptyFlag(Memory mem) {
      int flags = mem.getByte((long)FLAGS_BYTE);
      return (flags & 4) > 0;
   }

   static void insertCompactFlag(WritableMemory wmem, boolean compact) {
      int flags = wmem.getByte((long)FLAGS_BYTE);
      if (compact) {
         flags |= 8;
      } else {
         flags &= -9;
      }

      wmem.putByte((long)FLAGS_BYTE, (byte)flags);
   }

   static boolean extractCompactFlag(Memory mem) {
      int flags = mem.getByte((long)FLAGS_BYTE);
      return (flags & 8) > 0;
   }

   static void insertOooFlag(WritableMemory wmem, boolean oooFlag) {
      int flags = wmem.getByte((long)FLAGS_BYTE);
      if (oooFlag) {
         flags |= 16;
      } else {
         flags &= -17;
      }

      wmem.putByte((long)FLAGS_BYTE, (byte)flags);
   }

   static boolean extractOooFlag(Memory mem) {
      int flags = mem.getByte((long)FLAGS_BYTE);
      return (flags & 16) > 0;
   }

   static void insertRebuildCurMinNumKxQFlag(WritableMemory wmem, boolean rebuild) {
      int flags = wmem.getByte((long)FLAGS_BYTE);
      if (rebuild) {
         flags |= 32;
      } else {
         flags &= -33;
      }

      wmem.putByte((long)FLAGS_BYTE, (byte)flags);
   }

   static boolean extractRebuildCurMinNumKxQFlag(Memory mem) {
      int flags = mem.getByte((long)FLAGS_BYTE);
      return (flags & 32) > 0;
   }

   static void insertFlags(WritableMemory wmem, int flags) {
      wmem.putByte((long)FLAGS_BYTE, (byte)flags);
   }

   static int extractFlags(Memory mem) {
      return mem.getByte((long)FLAGS_BYTE) & 255;
   }

   static int extractInt(Memory mem, long byteOffset) {
      return mem.getInt(byteOffset);
   }

   static void insertInt(WritableMemory wmem, long byteOffset, int value) {
      wmem.putInt(byteOffset, value);
   }

   static int computeLgArr(Memory mem, int count, int lgConfigK) {
      CurMode curMode = extractCurMode(mem);
      if (curMode == CurMode.LIST) {
         return 3;
      } else {
         int ceilPwr2 = Util.ceilingPowerOf2(count);
         if (4 * count > 3 * ceilPwr2) {
            ceilPwr2 <<= 1;
         }

         return curMode == CurMode.SET ? Math.max(5, Util.exactLog2OfLong((long)ceilPwr2)) : Math.max(HllUtil.LG_AUX_ARR_INTS[lgConfigK], Util.exactLog2OfLong((long)ceilPwr2));
      }
   }

   static {
      NATIVE_ORDER_IS_BIG_ENDIAN = ByteOrder.nativeOrder() == ByteOrder.BIG_ENDIAN;
   }
}
