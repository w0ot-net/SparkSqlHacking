package org.apache.datasketches.cpc;

import java.nio.ByteOrder;
import java.util.Objects;
import org.apache.datasketches.common.Family;
import org.apache.datasketches.common.SketchesArgumentException;
import org.apache.datasketches.common.SketchesStateException;
import org.apache.datasketches.common.Util;
import org.apache.datasketches.memory.Memory;
import org.apache.datasketches.memory.WritableMemory;

final class PreambleUtil {
   private static final String fmt = "%10d%10x";
   static final byte SER_VER = 1;
   static final int BIG_ENDIAN_FLAG_MASK = 1;
   static final int COMPRESSED_FLAG_MASK = 2;
   static final int HIP_FLAG_MASK = 4;
   static final int SUP_VAL_FLAG_MASK = 8;
   static final int WINDOW_FLAG_MASK = 16;
   private static final byte[] preIntDefs;
   private static final byte[][] hiFieldOffset;

   private PreambleUtil() {
   }

   static byte getDefinedPreInts(Format format) {
      return preIntDefs[format.ordinal()];
   }

   static int getLoFieldOffset(LoField loField) {
      return loField.ordinal();
   }

   static int getPreInts(Memory mem) {
      return mem.getByte((long)getLoFieldOffset(PreambleUtil.LoField.PRE_INTS)) & 255;
   }

   static int getSerVer(Memory mem) {
      return mem.getByte((long)getLoFieldOffset(PreambleUtil.LoField.SER_VERSION)) & 255;
   }

   static Family getFamily(Memory mem) {
      int fam = mem.getByte((long)getLoFieldOffset(PreambleUtil.LoField.FAMILY)) & 255;
      return Family.idToFamily(fam);
   }

   static int getLgK(Memory mem) {
      return mem.getByte((long)getLoFieldOffset(PreambleUtil.LoField.LG_K)) & 255;
   }

   static int getFiCol(Memory mem) {
      return mem.getByte((long)getLoFieldOffset(PreambleUtil.LoField.FI_COL)) & 255;
   }

   static int getFlags(Memory mem) {
      return mem.getByte((long)getLoFieldOffset(PreambleUtil.LoField.FLAGS)) & 255;
   }

   static short getSeedHash(Memory mem) {
      return mem.getShort((long)getLoFieldOffset(PreambleUtil.LoField.SEED_HASH));
   }

   static int getFormatOrdinal(Memory mem) {
      int flags = getFlags(mem);
      return flags >>> 2 & 7;
   }

   static Format getFormat(Memory mem) {
      int ordinal = getFormatOrdinal(mem);
      return Format.ordinalToFormat(ordinal);
   }

   static boolean hasHip(Memory mem) {
      return (getFlags(mem) & 4) > 0;
   }

   static final boolean hasSv(Memory mem) {
      return (getFlags(mem) & 8) > 0;
   }

   static final boolean hasWindow(Memory mem) {
      return (getFlags(mem) & 16) > 0;
   }

   static final boolean isCompressed(Memory mem) {
      return (getFlags(mem) & 2) > 0;
   }

   static long getHiFieldOffset(Format format, HiField hiField) {
      int formatIdx = format.ordinal();
      int hiFieldIdx = hiField.ordinal();
      long fieldOffset = (long)(hiFieldOffset[formatIdx][hiFieldIdx] & 255);
      if (fieldOffset == 0L) {
         throw new SketchesStateException("Undefined preamble field given the Format: Format: " + format.toString() + ", HiField: " + hiField.toString());
      } else {
         return fieldOffset;
      }
   }

   static int getNumCoupons(Memory mem) {
      Format format = getFormat(mem);
      HiField hiField = PreambleUtil.HiField.NUM_COUPONS;
      long offset = getHiFieldOffset(format, hiField);
      return mem.getInt(offset);
   }

   static int getNumSv(Memory mem) {
      Format format = getFormat(mem);
      HiField hiField = PreambleUtil.HiField.NUM_SV;
      long offset = getHiFieldOffset(format, hiField);
      return mem.getInt(offset);
   }

   static int getSvLengthInts(Memory mem) {
      Format format = getFormat(mem);
      HiField hiField = PreambleUtil.HiField.SV_LENGTH_INTS;
      long offset = getHiFieldOffset(format, hiField);
      return mem.getInt(offset);
   }

   static int getWLengthInts(Memory mem) {
      Format format = getFormat(mem);
      HiField hiField = PreambleUtil.HiField.W_LENGTH_INTS;
      long offset = getHiFieldOffset(format, hiField);
      return mem.getInt(offset);
   }

   static double getKxP(Memory mem) {
      Format format = getFormat(mem);
      HiField hiField = PreambleUtil.HiField.KXP;
      long offset = getHiFieldOffset(format, hiField);
      return mem.getDouble(offset);
   }

   static double getHipAccum(Memory mem) {
      Format format = getFormat(mem);
      HiField hiField = PreambleUtil.HiField.HIP_ACCUM;
      long offset = getHiFieldOffset(format, hiField);
      return mem.getDouble(offset);
   }

   static long getSvStreamOffset(Memory mem) {
      Format format = getFormat(mem);
      HiField svLenField = PreambleUtil.HiField.SV_LENGTH_INTS;
      if (!hasSv(mem)) {
         fieldError(format, svLenField);
      } else {
         long svLengthInts = (long)mem.getInt(getHiFieldOffset(format, PreambleUtil.HiField.SV_LENGTH_INTS)) & 4294967295L;
         if (svLengthInts == 0L) {
            throw new SketchesStateException("svLengthInts cannot be zero");
         }
      }

      long wLengthInts = 0L;
      if (hasWindow(mem)) {
         wLengthInts = (long)mem.getInt(getHiFieldOffset(format, PreambleUtil.HiField.W_LENGTH_INTS)) & 4294967295L;
         if (wLengthInts == 0L) {
            throw new SketchesStateException("wLengthInts cannot be zero");
         }
      }

      return (long)getPreInts(mem) + wLengthInts << 2;
   }

   static long getWStreamOffset(Memory mem) {
      Format format = getFormat(mem);
      HiField wLenField = PreambleUtil.HiField.W_LENGTH_INTS;
      if (!hasWindow(mem)) {
         fieldError(format, wLenField);
      }

      long wLengthInts = (long)mem.getInt(getHiFieldOffset(format, PreambleUtil.HiField.W_LENGTH_INTS)) & 4294967295L;
      if (wLengthInts == 0L) {
         throw new SketchesStateException("wLengthInts cannot be zero");
      } else {
         return (long)(getPreInts(mem) << 2);
      }
   }

   static int[] getSvStream(Memory mem) {
      long offset = getSvStreamOffset(mem);
      int svLengthInts = getSvLengthInts(mem);
      int[] svStream = new int[svLengthInts];
      mem.getIntArray(offset, svStream, 0, svLengthInts);
      return svStream;
   }

   static int[] getWStream(Memory mem) {
      long offset = getWStreamOffset(mem);
      int wLength = getWLengthInts(mem);
      int[] wStream = new int[wLength];
      mem.getIntArray(offset, wStream, 0, wLength);
      return wStream;
   }

   static void putEmptyMerged(WritableMemory wmem, int lgK, short seedHash) {
      Format format = Format.EMPTY_MERGED;
      byte preInts = getDefinedPreInts(format);
      byte fiCol = 0;
      byte flags = (byte)(format.ordinal() << 2 | 2);
      checkCapacity(wmem.getCapacity(), 8L);
      putFirst8(wmem, preInts, (byte)lgK, (byte)0, flags, seedHash);
   }

   static void putEmptyHip(WritableMemory wmem, int lgK, short seedHash) {
      Format format = Format.EMPTY_HIP;
      byte preInts = getDefinedPreInts(format);
      byte fiCol = 0;
      byte flags = (byte)(format.ordinal() << 2 | 2);
      checkCapacity(wmem.getCapacity(), 8L);
      putFirst8(wmem, preInts, (byte)lgK, (byte)0, flags, seedHash);
   }

   static void putSparseHybridMerged(WritableMemory wmem, int lgK, int numCoupons, int svLengthInts, short seedHash, int[] svStream) {
      Format format = Format.SPARSE_HYBRID_MERGED;
      byte preInts = getDefinedPreInts(format);
      byte fiCol = 0;
      byte flags = (byte)(format.ordinal() << 2 | 2);
      checkCapacity(wmem.getCapacity(), 4L * (long)(preInts + svLengthInts));
      putFirst8(wmem, preInts, (byte)lgK, (byte)0, flags, seedHash);
      wmem.putInt(getHiFieldOffset(format, PreambleUtil.HiField.NUM_COUPONS), numCoupons);
      wmem.putInt(getHiFieldOffset(format, PreambleUtil.HiField.SV_LENGTH_INTS), svLengthInts);
      wmem.putIntArray(getSvStreamOffset(wmem), svStream, 0, svLengthInts);
   }

   static void putSparseHybridHip(WritableMemory wmem, int lgK, int numCoupons, int svLengthInts, double kxp, double hipAccum, short seedHash, int[] svStream) {
      Format format = Format.SPARSE_HYBRID_HIP;
      byte preInts = getDefinedPreInts(format);
      byte fiCol = 0;
      byte flags = (byte)(format.ordinal() << 2 | 2);
      checkCapacity(wmem.getCapacity(), 4L * (long)(preInts + svLengthInts));
      putFirst8(wmem, preInts, (byte)lgK, (byte)0, flags, seedHash);
      wmem.putInt(getHiFieldOffset(format, PreambleUtil.HiField.NUM_COUPONS), numCoupons);
      wmem.putInt(getHiFieldOffset(format, PreambleUtil.HiField.SV_LENGTH_INTS), svLengthInts);
      wmem.putDouble(getHiFieldOffset(format, PreambleUtil.HiField.KXP), kxp);
      wmem.putDouble(getHiFieldOffset(format, PreambleUtil.HiField.HIP_ACCUM), hipAccum);
      wmem.putIntArray(getSvStreamOffset(wmem), svStream, 0, svLengthInts);
   }

   static void putPinnedSlidingMergedNoSv(WritableMemory wmem, int lgK, int fiCol, int numCoupons, int wLengthInts, short seedHash, int[] wStream) {
      Format format = Format.PINNED_SLIDING_MERGED_NOSV;
      byte preInts = getDefinedPreInts(format);
      byte flags = (byte)(format.ordinal() << 2 | 2);
      checkCapacity(wmem.getCapacity(), 4L * (long)(preInts + wLengthInts));
      putFirst8(wmem, preInts, (byte)lgK, (byte)fiCol, flags, seedHash);
      wmem.putInt(getHiFieldOffset(format, PreambleUtil.HiField.NUM_COUPONS), numCoupons);
      wmem.putInt(getHiFieldOffset(format, PreambleUtil.HiField.W_LENGTH_INTS), wLengthInts);
      wmem.putIntArray(getWStreamOffset(wmem), wStream, 0, wLengthInts);
   }

   static void putPinnedSlidingHipNoSv(WritableMemory wmem, int lgK, int fiCol, int numCoupons, int wLengthInts, double kxp, double hipAccum, short seedHash, int[] wStream) {
      Format format = Format.PINNED_SLIDING_HIP_NOSV;
      byte preInts = getDefinedPreInts(format);
      byte flags = (byte)(format.ordinal() << 2 | 2);
      checkCapacity(wmem.getCapacity(), 4L * (long)(preInts + wLengthInts));
      putFirst8(wmem, preInts, (byte)lgK, (byte)fiCol, flags, seedHash);
      wmem.putInt(getHiFieldOffset(format, PreambleUtil.HiField.NUM_COUPONS), numCoupons);
      wmem.putInt(getHiFieldOffset(format, PreambleUtil.HiField.W_LENGTH_INTS), wLengthInts);
      wmem.putDouble(getHiFieldOffset(format, PreambleUtil.HiField.KXP), kxp);
      wmem.putDouble(getHiFieldOffset(format, PreambleUtil.HiField.HIP_ACCUM), hipAccum);
      wmem.putIntArray(getWStreamOffset(wmem), wStream, 0, wLengthInts);
   }

   static void putPinnedSlidingMerged(WritableMemory wmem, int lgK, int fiCol, int numCoupons, int numSv, int svLengthInts, int wLengthInts, short seedHash, int[] svStream, int[] wStream) {
      Format format = Format.PINNED_SLIDING_MERGED;
      byte preInts = getDefinedPreInts(format);
      byte flags = (byte)(format.ordinal() << 2 | 2);
      checkCapacity(wmem.getCapacity(), 4L * (long)(preInts + svLengthInts + wLengthInts));
      putFirst8(wmem, preInts, (byte)lgK, (byte)fiCol, flags, seedHash);
      wmem.putInt(getHiFieldOffset(format, PreambleUtil.HiField.NUM_COUPONS), numCoupons);
      wmem.putInt(getHiFieldOffset(format, PreambleUtil.HiField.NUM_SV), numSv);
      wmem.putInt(getHiFieldOffset(format, PreambleUtil.HiField.SV_LENGTH_INTS), svLengthInts);
      wmem.putInt(getHiFieldOffset(format, PreambleUtil.HiField.W_LENGTH_INTS), wLengthInts);
      wmem.putIntArray(getSvStreamOffset(wmem), svStream, 0, svLengthInts);
      wmem.putIntArray(getWStreamOffset(wmem), wStream, 0, wLengthInts);
   }

   static void putPinnedSlidingHip(WritableMemory wmem, int lgK, int fiCol, int numCoupons, int numSv, double kxp, double hipAccum, int svLengthInts, int wLengthInts, short seedHash, int[] svStream, int[] wStream) {
      Format format = Format.PINNED_SLIDING_HIP;
      byte preInts = getDefinedPreInts(format);
      byte flags = (byte)(format.ordinal() << 2 | 2);
      checkCapacity(wmem.getCapacity(), 4L * (long)(preInts + svLengthInts + wLengthInts));
      putFirst8(wmem, preInts, (byte)lgK, (byte)fiCol, flags, seedHash);
      wmem.putInt(getHiFieldOffset(format, PreambleUtil.HiField.NUM_COUPONS), numCoupons);
      wmem.putInt(getHiFieldOffset(format, PreambleUtil.HiField.NUM_SV), numSv);
      wmem.putDouble(getHiFieldOffset(format, PreambleUtil.HiField.KXP), kxp);
      wmem.putDouble(getHiFieldOffset(format, PreambleUtil.HiField.HIP_ACCUM), hipAccum);
      wmem.putInt(getHiFieldOffset(format, PreambleUtil.HiField.SV_LENGTH_INTS), svLengthInts);
      wmem.putInt(getHiFieldOffset(format, PreambleUtil.HiField.W_LENGTH_INTS), wLengthInts);
      wmem.putIntArray(getSvStreamOffset(wmem), svStream, 0, svLengthInts);
      wmem.putIntArray(getWStreamOffset(wmem), wStream, 0, wLengthInts);
   }

   private static void putFirst8(WritableMemory wmem, byte preInts, byte lgK, byte fiCol, byte flags, short seedHash) {
      wmem.clear(0L, 4L * (long)preInts);
      wmem.putByte((long)getLoFieldOffset(PreambleUtil.LoField.PRE_INTS), preInts);
      wmem.putByte((long)getLoFieldOffset(PreambleUtil.LoField.SER_VERSION), (byte)1);
      wmem.putByte((long)getLoFieldOffset(PreambleUtil.LoField.FAMILY), (byte)Family.CPC.getID());
      wmem.putByte((long)getLoFieldOffset(PreambleUtil.LoField.LG_K), lgK);
      wmem.putByte((long)getLoFieldOffset(PreambleUtil.LoField.FI_COL), fiCol);
      wmem.putByte((long)getLoFieldOffset(PreambleUtil.LoField.FLAGS), flags);
      wmem.putShort((long)getLoFieldOffset(PreambleUtil.LoField.SEED_HASH), seedHash);
   }

   static String toString(byte[] byteArr, boolean detail) {
      Memory mem = Memory.wrap(byteArr);
      return toString(mem, detail);
   }

   static String toString(Memory mem, boolean detail) {
      long capBytes = mem.getCapacity();
      int preInts = mem.getByte((long)getLoFieldOffset(PreambleUtil.LoField.PRE_INTS)) & 255;
      int serVer = mem.getByte((long)getLoFieldOffset(PreambleUtil.LoField.SER_VERSION)) & 255;
      Family family = Family.idToFamily(mem.getByte((long)getLoFieldOffset(PreambleUtil.LoField.FAMILY)) & 255);
      int lgK = mem.getByte((long)getLoFieldOffset(PreambleUtil.LoField.LG_K)) & 255;
      int fiCol = mem.getByte((long)getLoFieldOffset(PreambleUtil.LoField.FI_COL)) & 255;
      int flags = mem.getByte((long)getLoFieldOffset(PreambleUtil.LoField.FLAGS)) & 255;
      int seedHash = mem.getShort((long)getLoFieldOffset(PreambleUtil.LoField.SEED_HASH)) & '\uffff';
      String seedHashStr = Integer.toHexString(seedHash);
      String flagsStr = Util.zeroPad(Integer.toBinaryString(flags), 8) + ", " + flags;
      boolean bigEndian = (flags & 1) > 0;
      boolean compressed = (flags & 2) > 0;
      boolean hasHip = (flags & 4) > 0;
      boolean hasSV = (flags & 8) > 0;
      boolean hasWindow = (flags & 16) > 0;
      int formatOrdinal = flags >>> 2 & 7;
      Format format = Format.ordinalToFormat(formatOrdinal);
      String nativeOrderStr = ByteOrder.nativeOrder().toString();
      long numCoupons = 0L;
      long numSv = 0L;
      long winOffset = 0L;
      long svLengthInts = 0L;
      long wLengthInts = 0L;
      double kxp = (double)0.0F;
      double hipAccum = (double)0.0F;
      long svStreamStart = 0L;
      long wStreamStart = 0L;
      long reqBytes = 0L;
      StringBuilder sb = new StringBuilder();
      sb.append(Util.LS);
      sb.append("### CPC SKETCH IMAGE - PREAMBLE:").append(Util.LS);
      sb.append("Format                          : ").append(format.name()).append(Util.LS);
      sb.append("Byte 0: Preamble Ints           : ").append(preInts).append(Util.LS);
      sb.append("Byte 1: SerVer                  : ").append(serVer).append(Util.LS);
      sb.append("Byte 2: Family                  : ").append(family).append(Util.LS);
      sb.append("Byte 3: lgK                     : ").append(lgK).append(Util.LS);
      sb.append("Byte 4: First Interesting Col   : ").append(fiCol).append(Util.LS);
      sb.append("Byte 5: Flags                   : ").append(flagsStr).append(Util.LS);
      sb.append("  BIG_ENDIAN_STORAGE            : ").append(bigEndian).append(Util.LS);
      sb.append("  (Native Byte Order)           : ").append(nativeOrderStr).append(Util.LS);
      sb.append("  Compressed                    : ").append(compressed).append(Util.LS);
      sb.append("  Has HIP                       : ").append(hasHip).append(Util.LS);
      sb.append("  Has Surprising Values         : ").append(hasSV).append(Util.LS);
      sb.append("  Has Window Values             : ").append(hasWindow).append(Util.LS);
      sb.append("Byte 6, 7: Seed Hash            : ").append(seedHashStr).append(Util.LS);
      switch (format) {
         case EMPTY_MERGED:
         case EMPTY_HIP:
            Flavor flavor = CpcUtil.determineFlavor(lgK, numCoupons);
            sb.append("Flavor                          : ").append(flavor).append(Util.LS);
            break;
         case SPARSE_HYBRID_MERGED:
            numCoupons = (long)mem.getInt(getHiFieldOffset(format, PreambleUtil.HiField.NUM_COUPONS)) & 4294967295L;
            svLengthInts = (long)mem.getInt(getHiFieldOffset(format, PreambleUtil.HiField.SV_LENGTH_INTS)) & 4294967295L;
            svStreamStart = getSvStreamOffset(mem);
            reqBytes = svStreamStart + (svLengthInts << 2);
            Flavor flavor = CpcUtil.determineFlavor(lgK, numCoupons);
            sb.append("Flavor                          : ").append(flavor).append(Util.LS);
            sb.append("Num Coupons                     : ").append(numCoupons).append(Util.LS);
            sb.append("Num SV                          : ").append(numCoupons).append(Util.LS);
            sb.append("SV Length Ints                  : ").append(svLengthInts).append(Util.LS);
            sb.append("SV Stream Start                 : ").append(svStreamStart).append(Util.LS);
            break;
         case SPARSE_HYBRID_HIP:
            numCoupons = (long)mem.getInt(getHiFieldOffset(format, PreambleUtil.HiField.NUM_COUPONS)) & 4294967295L;
            svLengthInts = (long)mem.getInt(getHiFieldOffset(format, PreambleUtil.HiField.SV_LENGTH_INTS)) & 4294967295L;
            svStreamStart = getSvStreamOffset(mem);
            kxp = mem.getDouble(getHiFieldOffset(format, PreambleUtil.HiField.KXP));
            hipAccum = mem.getDouble(getHiFieldOffset(format, PreambleUtil.HiField.HIP_ACCUM));
            reqBytes = svStreamStart + (svLengthInts << 2);
            Flavor flavor = CpcUtil.determineFlavor(lgK, numCoupons);
            sb.append("Flavor                          : ").append(flavor).append(Util.LS);
            sb.append("Num Coupons                     : ").append(numCoupons).append(Util.LS);
            sb.append("Num SV                          : ").append(numCoupons).append(Util.LS);
            sb.append("SV Length Ints                  : ").append(svLengthInts).append(Util.LS);
            sb.append("SV Stream Start                 : ").append(svStreamStart).append(Util.LS);
            sb.append("KxP                             : ").append(kxp).append(Util.LS);
            sb.append("HipAccum                        : ").append(hipAccum).append(Util.LS);
            break;
         case PINNED_SLIDING_MERGED_NOSV:
            numCoupons = (long)mem.getInt(getHiFieldOffset(format, PreambleUtil.HiField.NUM_COUPONS)) & 4294967295L;
            winOffset = (long)CpcUtil.determineCorrectOffset(lgK, numCoupons);
            wLengthInts = (long)mem.getInt(getHiFieldOffset(format, PreambleUtil.HiField.W_LENGTH_INTS)) & 4294967295L;
            wStreamStart = getWStreamOffset(mem);
            reqBytes = wStreamStart + (wLengthInts << 2);
            Flavor flavor = CpcUtil.determineFlavor(lgK, numCoupons);
            sb.append("Flavor                          : ").append(flavor).append(Util.LS);
            sb.append("Num Coupons                     : ").append(numCoupons).append(Util.LS);
            sb.append("Window Offset                   : ").append(winOffset).append(Util.LS);
            sb.append("Window Length Ints              : ").append(wLengthInts).append(Util.LS);
            sb.append("Window Stream Start             : ").append(wStreamStart).append(Util.LS);
            break;
         case PINNED_SLIDING_HIP_NOSV:
            numCoupons = (long)mem.getInt(getHiFieldOffset(format, PreambleUtil.HiField.NUM_COUPONS)) & 4294967295L;
            winOffset = (long)CpcUtil.determineCorrectOffset(lgK, numCoupons);
            wLengthInts = (long)mem.getInt(getHiFieldOffset(format, PreambleUtil.HiField.W_LENGTH_INTS)) & 4294967295L;
            wStreamStart = getWStreamOffset(mem);
            kxp = mem.getDouble(getHiFieldOffset(format, PreambleUtil.HiField.KXP));
            hipAccum = mem.getDouble(getHiFieldOffset(format, PreambleUtil.HiField.HIP_ACCUM));
            reqBytes = wStreamStart + (wLengthInts << 2);
            Flavor flavor = CpcUtil.determineFlavor(lgK, numCoupons);
            sb.append("Flavor                          : ").append(flavor).append(Util.LS);
            sb.append("Num Coupons                     : ").append(numCoupons).append(Util.LS);
            sb.append("Window Offset                   : ").append(winOffset).append(Util.LS);
            sb.append("Window Length Ints              : ").append(wLengthInts).append(Util.LS);
            sb.append("Window Stream Start             : ").append(wStreamStart).append(Util.LS);
            sb.append("KxP                             : ").append(kxp).append(Util.LS);
            sb.append("HipAccum                        : ").append(hipAccum).append(Util.LS);
            break;
         case PINNED_SLIDING_MERGED:
            numCoupons = (long)mem.getInt(getHiFieldOffset(format, PreambleUtil.HiField.NUM_COUPONS) & 4294967295L);
            winOffset = (long)CpcUtil.determineCorrectOffset(lgK, numCoupons);
            wLengthInts = (long)mem.getInt(getHiFieldOffset(format, PreambleUtil.HiField.W_LENGTH_INTS)) & 4294967295L;
            numSv = (long)mem.getInt(getHiFieldOffset(format, PreambleUtil.HiField.NUM_SV)) & 4294967295L;
            svLengthInts = (long)mem.getInt(getHiFieldOffset(format, PreambleUtil.HiField.SV_LENGTH_INTS)) & 4294967295L;
            wStreamStart = getWStreamOffset(mem);
            svStreamStart = getSvStreamOffset(mem);
            reqBytes = svStreamStart + (svLengthInts << 2);
            Flavor flavor = CpcUtil.determineFlavor(lgK, numCoupons);
            sb.append("Flavor                          : ").append(flavor).append(Util.LS);
            sb.append("Num Coupons                     : ").append(numCoupons).append(Util.LS);
            sb.append("Num SV                          : ").append(numSv).append(Util.LS);
            sb.append("SV Length Ints                  : ").append(svLengthInts).append(Util.LS);
            sb.append("SV Stream Start                 : ").append(svStreamStart).append(Util.LS);
            sb.append("Window Offset                   : ").append(winOffset).append(Util.LS);
            sb.append("Window Length Ints              : ").append(wLengthInts).append(Util.LS);
            sb.append("Window Stream Start             : ").append(wStreamStart).append(Util.LS);
            break;
         case PINNED_SLIDING_HIP:
            numCoupons = (long)mem.getInt(getHiFieldOffset(format, PreambleUtil.HiField.NUM_COUPONS) & 4294967295L);
            winOffset = (long)CpcUtil.determineCorrectOffset(lgK, numCoupons);
            wLengthInts = (long)mem.getInt(getHiFieldOffset(format, PreambleUtil.HiField.W_LENGTH_INTS)) & 4294967295L;
            numSv = (long)mem.getInt(getHiFieldOffset(format, PreambleUtil.HiField.NUM_SV)) & 4294967295L;
            svLengthInts = (long)mem.getInt(getHiFieldOffset(format, PreambleUtil.HiField.SV_LENGTH_INTS)) & 4294967295L;
            wStreamStart = getWStreamOffset(mem);
            svStreamStart = getSvStreamOffset(mem);
            kxp = mem.getDouble(getHiFieldOffset(format, PreambleUtil.HiField.KXP));
            hipAccum = mem.getDouble(getHiFieldOffset(format, PreambleUtil.HiField.HIP_ACCUM));
            reqBytes = svStreamStart + (svLengthInts << 2);
            Flavor flavor = CpcUtil.determineFlavor(lgK, numCoupons);
            sb.append("Flavor                          : ").append(flavor).append(Util.LS);
            sb.append("Num Coupons                     : ").append(numCoupons).append(Util.LS);
            sb.append("Num SV                          : ").append(numSv).append(Util.LS);
            sb.append("SV Length Ints                  : ").append(svLengthInts).append(Util.LS);
            sb.append("SV Stream Start                 : ").append(svStreamStart).append(Util.LS);
            sb.append("Window Offset                   : ").append(winOffset).append(Util.LS);
            sb.append("Window Length Ints              : ").append(wLengthInts).append(Util.LS);
            sb.append("Window Stream Start             : ").append(wStreamStart).append(Util.LS);
            sb.append("KxP                             : ").append(kxp).append(Util.LS);
            sb.append("HipAccum                        : ").append(hipAccum).append(Util.LS);
      }

      sb.append("Actual Bytes                    : ").append(capBytes).append(Util.LS);
      sb.append("Required Bytes                  : ").append(reqBytes).append(Util.LS);
      if (detail) {
         sb.append(Util.LS).append("### CPC SKETCH IMAGE - DATA").append(Util.LS);
         if (wLengthInts > 0L) {
            sb.append(Util.LS).append("Window Stream:").append(Util.LS);
            listData(mem, wStreamStart, wLengthInts, sb);
         }

         if (svLengthInts > 0L) {
            sb.append(Util.LS).append("SV Stream:").append(Util.LS);
            listData(mem, svStreamStart, svLengthInts, sb);
         }
      }

      sb.append("### END CPC SKETCH IMAGE").append(Util.LS);
      return sb.toString();
   }

   private static void listData(Memory mem, long offsetBytes, long lengthInts, StringBuilder sb) {
      long memCap = mem.getCapacity();
      long expectedCap = offsetBytes + 4L * lengthInts;
      checkCapacity(memCap, expectedCap);

      for(long i = 0L; i < lengthInts; ++i) {
         sb.append(String.format("%10d%10x", i, mem.getInt(offsetBytes + 4L * i))).append(Util.LS);
      }

   }

   static void fieldError(Format format, HiField hiField) {
      throw new SketchesArgumentException("Operation is illegal: Format = " + format.name() + ", HiField = " + hiField);
   }

   static void checkCapacity(long memCap, long expectedCap) {
      if (memCap < expectedCap) {
         throw new SketchesArgumentException("Insufficient Image Bytes = " + memCap + ", Expected = " + expectedCap);
      }
   }

   static void checkLoPreamble(Memory mem) {
      Objects.requireNonNull(mem, "Source Memory must not be null");
      Util.checkBounds(0L, 8L, mem.getCapacity());
      RuntimeAsserts.rtAssertEquals((long)getSerVer(mem), 1L);
      Format fmat = getFormat(mem);
      int preIntsDef = getDefinedPreInts(fmat) & 255;
      RuntimeAsserts.rtAssertEquals((long)getPreInts(mem), (long)preIntsDef);
      Family fam = getFamily(mem);
      RuntimeAsserts.rtAssert(fam == Family.CPC);
      int lgK = getLgK(mem);
      RuntimeAsserts.rtAssert(lgK >= 4 && lgK <= 26);
      int fiCol = getFiCol(mem);
      RuntimeAsserts.rtAssert(fiCol <= 63 && fiCol >= 0);
   }

   static {
      if (ByteOrder.nativeOrder() == ByteOrder.BIG_ENDIAN) {
         throw new SketchesStateException("This sketch will not work on Big Endian CPUs.");
      } else {
         preIntDefs = new byte[]{2, 2, 4, 8, 4, 8, 6, 10};
         hiFieldOffset = new byte[][]{{0, 0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 0}, {8, 0, 0, 0, 12, 0, 16, 0}, {8, 0, 16, 24, 12, 0, 32, 0}, {8, 0, 0, 0, 0, 12, 0, 16}, {8, 0, 16, 24, 0, 12, 0, 32}, {8, 12, 0, 0, 16, 20, 24, 24}, {8, 12, 16, 24, 32, 36, 40, 40}};
      }
   }

   static enum LoField {
      PRE_INTS,
      SER_VERSION,
      FAMILY,
      LG_K,
      FI_COL,
      FLAGS,
      SEED_HASH;
   }

   static enum HiField {
      NUM_COUPONS,
      NUM_SV,
      KXP,
      HIP_ACCUM,
      SV_LENGTH_INTS,
      W_LENGTH_INTS,
      SV_STREAM,
      W_STREAM;
   }
}
