package org.apache.datasketches.theta;

import org.apache.datasketches.common.ResizeFactor;
import org.apache.datasketches.memory.WritableMemory;
import org.apache.datasketches.thetacommon.ThetaUtil;

abstract class HeapUpdateSketch extends UpdateSketch {
   final int lgNomLongs_;
   private final long seed_;
   private final float p_;
   private final ResizeFactor rf_;

   HeapUpdateSketch(int lgNomLongs, long seed, float p, ResizeFactor rf) {
      this.lgNomLongs_ = Math.max(lgNomLongs, 4);
      this.seed_ = seed;
      this.p_ = p;
      this.rf_ = rf;
   }

   public int getCurrentBytes() {
      int preLongs = this.getCurrentPreambleLongs();
      int dataLongs = this.getCurrentDataLongs();
      return preLongs + dataLongs << 3;
   }

   public final int getLgNomLongs() {
      return this.lgNomLongs_;
   }

   float getP() {
      return this.p_;
   }

   public ResizeFactor getResizeFactor() {
      return this.rf_;
   }

   long getSeed() {
      return this.seed_;
   }

   short getSeedHash() {
      return ThetaUtil.computeSeedHash(this.getSeed());
   }

   byte[] toByteArray(int preLongs, byte familyID) {
      if (this.isDirty()) {
         this.rebuild();
      }

      CompactOperations.checkIllegalCurCountAndEmpty(this.isEmpty(), this.getRetainedEntries(true));
      int preBytes = preLongs << 3 & 63;
      int dataBytes = this.getCurrentDataLongs() << 3;
      byte[] byteArrOut = new byte[preBytes + dataBytes];
      WritableMemory memOut = WritableMemory.writableWrap(byteArrOut);
      int lgRf = this.getResizeFactor().lg() & 3;
      PreambleUtil.insertPreLongs(memOut, preLongs);
      PreambleUtil.insertLgResizeFactor(memOut, lgRf);
      PreambleUtil.insertSerVer(memOut, 3);
      PreambleUtil.insertFamilyID(memOut, familyID);
      PreambleUtil.insertLgNomLongs(memOut, this.getLgNomLongs());
      PreambleUtil.insertLgArrLongs(memOut, this.getLgArrLongs());
      PreambleUtil.insertSeedHash(memOut, this.getSeedHash());
      PreambleUtil.insertCurCount(memOut, this.getRetainedEntries(true));
      PreambleUtil.insertP(memOut, this.getP());
      long thetaLong = CompactOperations.correctThetaOnCompact(this.isEmpty(), this.getRetainedEntries(true), this.getThetaLong());
      PreambleUtil.insertThetaLong(memOut, thetaLong);
      byte flags = (byte)(this.isEmpty() ? 4 : 0);
      PreambleUtil.insertFlags(memOut, flags);
      int arrLongs = 1 << this.getLgArrLongs();
      long[] cache = this.getCache();
      memOut.putLongArray((long)preBytes, cache, 0, arrLongs);
      return byteArrOut;
   }
}
