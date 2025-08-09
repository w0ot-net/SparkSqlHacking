package org.apache.datasketches.thetacommon;

import org.apache.datasketches.common.SketchesArgumentException;
import org.apache.datasketches.common.Util;
import org.apache.datasketches.hash.MurmurHash3;

public final class ThetaUtil {
   public static final int MIN_LG_NOM_LONGS = 4;
   public static final int MAX_LG_NOM_LONGS = 26;
   public static final double REBUILD_THRESHOLD = (double)0.9375F;
   public static final double RESIZE_THRESHOLD = (double)0.5F;
   public static final int DEFAULT_NOMINAL_ENTRIES = 4096;
   public static final long DEFAULT_UPDATE_SEED = 9001L;
   public static final int MIN_LG_ARR_LONGS = 5;

   private ThetaUtil() {
   }

   public static short checkSeedHashes(short seedHashA, short seedHashB) {
      if (seedHashA != seedHashB) {
         throw new SketchesArgumentException("Incompatible Seed Hashes. " + Integer.toHexString(seedHashA & '\uffff') + ", " + Integer.toHexString(seedHashB & '\uffff'));
      } else {
         return seedHashA;
      }
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

   public static int startingSubMultiple(int lgTarget, int lgRF, int lgMin) {
      return lgTarget <= lgMin ? lgMin : (lgRF == 0 ? lgTarget : (lgTarget - lgMin) % lgRF + lgMin);
   }

   public static int checkNomLongs(int nomLongs) {
      int lgNomLongs = Integer.numberOfTrailingZeros(Util.ceilingPowerOf2(nomLongs));
      if (lgNomLongs <= 26 && lgNomLongs >= 4) {
         return lgNomLongs;
      } else {
         throw new SketchesArgumentException("Nominal Entries must be >= 16 and <= 67108864: " + nomLongs);
      }
   }
}
