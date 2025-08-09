package org.apache.datasketches.cpc;

import org.apache.datasketches.common.Util;
import org.apache.datasketches.memory.Memory;
import org.apache.datasketches.memory.WritableMemory;
import org.apache.datasketches.thetacommon.ThetaUtil;

final class CompressedState {
   private boolean csvIsValid = false;
   private boolean windowIsValid = false;
   final int lgK;
   final short seedHash;
   int fiCol = 0;
   boolean mergeFlag = false;
   long numCoupons = 0L;
   double kxp;
   double hipEstAccum = (double)0.0F;
   int numCsv = 0;
   int[] csvStream = null;
   int csvLengthInts = 0;
   int[] cwStream = null;
   int cwLengthInts = 0;

   private CompressedState(int lgK, short seedHash) {
      this.lgK = lgK;
      this.seedHash = seedHash;
      this.kxp = (double)(1 << lgK);
   }

   static CompressedState compress(CpcSketch source) {
      short seedHash = ThetaUtil.computeSeedHash(source.seed);
      CompressedState target = new CompressedState(source.lgK, seedHash);
      target.fiCol = source.fiCol;
      target.mergeFlag = source.mergeFlag;
      target.numCoupons = source.numCoupons;
      target.kxp = source.kxp;
      target.hipEstAccum = source.hipEstAccum;
      target.csvIsValid = source.pairTable != null;
      target.windowIsValid = source.slidingWindow != null;
      CpcCompression.compress(source, target);
      return target;
   }

   Flavor getFlavor() {
      return CpcUtil.determineFlavor(this.lgK, this.numCoupons);
   }

   Format getFormat() {
      int ordinal = (this.cwLengthInts > 0 ? 4 : 0) | (this.numCsv > 0 ? 2 : 0) | (this.mergeFlag ? 0 : 1);
      return Format.ordinalToFormat(ordinal);
   }

   int getWindowOffset() {
      return CpcUtil.determineCorrectOffset(this.lgK, this.numCoupons);
   }

   long getRequiredSerializedBytes() {
      Format format = this.getFormat();
      int preInts = PreambleUtil.getDefinedPreInts(format);
      return 4L * (long)(preInts + this.csvLengthInts + this.cwLengthInts);
   }

   static CompressedState importFromMemory(Memory mem) {
      PreambleUtil.checkLoPreamble(mem);
      RuntimeAsserts.rtAssert(PreambleUtil.isCompressed(mem));
      int lgK = PreambleUtil.getLgK(mem);
      short seedHash = PreambleUtil.getSeedHash(mem);
      CompressedState state = new CompressedState(lgK, seedHash);
      int fmtOrd = PreambleUtil.getFormatOrdinal(mem);
      Format format = Format.ordinalToFormat(fmtOrd);
      state.mergeFlag = (fmtOrd & 1) <= 0;
      state.csvIsValid = (fmtOrd & 2) > 0;
      state.windowIsValid = (fmtOrd & 4) > 0;
      switch (format) {
         case EMPTY_MERGED:
         case EMPTY_HIP:
            PreambleUtil.checkCapacity(mem.getCapacity(), 8L);
            break;
         case SPARSE_HYBRID_MERGED:
            state.numCoupons = (long)PreambleUtil.getNumCoupons(mem);
            state.numCsv = (int)state.numCoupons;
            state.csvLengthInts = PreambleUtil.getSvLengthInts(mem);
            PreambleUtil.checkCapacity(mem.getCapacity(), state.getRequiredSerializedBytes());
            state.csvStream = PreambleUtil.getSvStream(mem);
            break;
         case SPARSE_HYBRID_HIP:
            state.numCoupons = (long)PreambleUtil.getNumCoupons(mem);
            state.numCsv = (int)state.numCoupons;
            state.csvLengthInts = PreambleUtil.getSvLengthInts(mem);
            state.kxp = PreambleUtil.getKxP(mem);
            state.hipEstAccum = PreambleUtil.getHipAccum(mem);
            PreambleUtil.checkCapacity(mem.getCapacity(), state.getRequiredSerializedBytes());
            state.csvStream = PreambleUtil.getSvStream(mem);
            break;
         case PINNED_SLIDING_MERGED_NOSV:
            state.fiCol = PreambleUtil.getFiCol(mem);
            state.numCoupons = (long)PreambleUtil.getNumCoupons(mem);
            state.cwLengthInts = PreambleUtil.getWLengthInts(mem);
            PreambleUtil.checkCapacity(mem.getCapacity(), state.getRequiredSerializedBytes());
            state.cwStream = PreambleUtil.getWStream(mem);
            break;
         case PINNED_SLIDING_HIP_NOSV:
            state.fiCol = PreambleUtil.getFiCol(mem);
            state.numCoupons = (long)PreambleUtil.getNumCoupons(mem);
            state.cwLengthInts = PreambleUtil.getWLengthInts(mem);
            state.kxp = PreambleUtil.getKxP(mem);
            state.hipEstAccum = PreambleUtil.getHipAccum(mem);
            PreambleUtil.checkCapacity(mem.getCapacity(), state.getRequiredSerializedBytes());
            state.cwStream = PreambleUtil.getWStream(mem);
            break;
         case PINNED_SLIDING_MERGED:
            state.fiCol = PreambleUtil.getFiCol(mem);
            state.numCoupons = (long)PreambleUtil.getNumCoupons(mem);
            state.numCsv = PreambleUtil.getNumSv(mem);
            state.csvLengthInts = PreambleUtil.getSvLengthInts(mem);
            state.cwLengthInts = PreambleUtil.getWLengthInts(mem);
            PreambleUtil.checkCapacity(mem.getCapacity(), state.getRequiredSerializedBytes());
            state.cwStream = PreambleUtil.getWStream(mem);
            state.csvStream = PreambleUtil.getSvStream(mem);
            break;
         case PINNED_SLIDING_HIP:
            state.fiCol = PreambleUtil.getFiCol(mem);
            state.numCoupons = (long)PreambleUtil.getNumCoupons(mem);
            state.numCsv = PreambleUtil.getNumSv(mem);
            state.csvLengthInts = PreambleUtil.getSvLengthInts(mem);
            state.cwLengthInts = PreambleUtil.getWLengthInts(mem);
            state.kxp = PreambleUtil.getKxP(mem);
            state.hipEstAccum = PreambleUtil.getHipAccum(mem);
            PreambleUtil.checkCapacity(mem.getCapacity(), state.getRequiredSerializedBytes());
            state.cwStream = PreambleUtil.getWStream(mem);
            state.csvStream = PreambleUtil.getSvStream(mem);
      }

      PreambleUtil.checkCapacity(mem.getCapacity(), 4L * (long)(PreambleUtil.getPreInts(mem) + state.csvLengthInts + state.cwLengthInts));
      return state;
   }

   void exportToMemory(WritableMemory wmem) {
      Format format = this.getFormat();
      switch (format) {
         case EMPTY_MERGED:
            PreambleUtil.putEmptyMerged(wmem, this.lgK, this.seedHash);
            break;
         case EMPTY_HIP:
            PreambleUtil.putEmptyHip(wmem, this.lgK, this.seedHash);
            break;
         case SPARSE_HYBRID_MERGED:
            PreambleUtil.putSparseHybridMerged(wmem, this.lgK, (int)this.numCoupons, this.csvLengthInts, this.seedHash, this.csvStream);
            break;
         case SPARSE_HYBRID_HIP:
            PreambleUtil.putSparseHybridHip(wmem, this.lgK, (int)this.numCoupons, this.csvLengthInts, this.kxp, this.hipEstAccum, this.seedHash, this.csvStream);
            break;
         case PINNED_SLIDING_MERGED_NOSV:
            PreambleUtil.putPinnedSlidingMergedNoSv(wmem, this.lgK, this.fiCol, (int)this.numCoupons, this.cwLengthInts, this.seedHash, this.cwStream);
            break;
         case PINNED_SLIDING_HIP_NOSV:
            PreambleUtil.putPinnedSlidingHipNoSv(wmem, this.lgK, this.fiCol, (int)this.numCoupons, this.cwLengthInts, this.kxp, this.hipEstAccum, this.seedHash, this.cwStream);
            break;
         case PINNED_SLIDING_MERGED:
            PreambleUtil.putPinnedSlidingMerged(wmem, this.lgK, this.fiCol, (int)this.numCoupons, this.numCsv, this.csvLengthInts, this.cwLengthInts, this.seedHash, this.csvStream, this.cwStream);
            break;
         case PINNED_SLIDING_HIP:
            PreambleUtil.putPinnedSlidingHip(wmem, this.lgK, this.fiCol, (int)this.numCoupons, this.numCsv, this.kxp, this.hipEstAccum, this.csvLengthInts, this.cwLengthInts, this.seedHash, this.csvStream, this.cwStream);
      }

   }

   public String toString() {
      return toString(this, false);
   }

   public static String toString(CompressedState state, boolean detail) {
      StringBuilder sb = new StringBuilder();
      sb.append("CompressedState").append(Util.LS);
      sb.append("  Flavor     : ").append(state.getFlavor()).append(Util.LS);
      sb.append("  Format     : ").append(state.getFormat()).append(Util.LS);
      sb.append("  lgK        : ").append(state.lgK).append(Util.LS);
      sb.append("  seedHash   : ").append(state.seedHash).append(Util.LS);
      sb.append("  fiCol      : ").append(state.fiCol).append(Util.LS);
      sb.append("  mergeFlag  : ").append(state.mergeFlag).append(Util.LS);
      sb.append("  csvStream  : ").append(state.csvIsValid).append(Util.LS);
      sb.append("  cwStream   : ").append(state.windowIsValid).append(Util.LS);
      sb.append("  numCoupons : ").append(state.numCoupons).append(Util.LS);
      sb.append("  kxp        : ").append(state.kxp).append(Util.LS);
      sb.append("  hipAccum   : ").append(state.hipEstAccum).append(Util.LS);
      sb.append("  numCsv     : ").append(state.numCsv).append(Util.LS);
      sb.append("  csvLengthInts  : ").append(state.csvLengthInts).append(Util.LS);
      sb.append("  csLength   : ").append(state.cwLengthInts).append(Util.LS);
      if (detail) {
         if (state.csvStream != null) {
            sb.append("  CsvStream  : ").append(Util.LS);

            for(int i = 0; i < state.csvLengthInts; ++i) {
               sb.append(String.format("%8d %12d" + Util.LS, i, state.csvStream[i]));
            }
         }

         if (state.cwStream != null) {
            sb.append("  CwStream  : ").append(Util.LS);

            for(int i = 0; i < state.cwLengthInts; ++i) {
               sb.append(String.format("%8d %12d" + Util.LS, i, state.cwStream[i]));
            }
         }
      }

      return sb.toString();
   }
}
