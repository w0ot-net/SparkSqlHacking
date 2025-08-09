package org.apache.datasketches.theta;

import org.apache.datasketches.common.Family;
import org.apache.datasketches.common.ResizeFactor;
import org.apache.datasketches.common.SketchesArgumentException;
import org.apache.datasketches.memory.Memory;
import org.apache.datasketches.memory.MemoryRequestServer;
import org.apache.datasketches.memory.WritableMemory;
import org.apache.datasketches.thetacommon.HashOperations;
import org.apache.datasketches.thetacommon.ThetaUtil;

class DirectQuickSelectSketch extends DirectQuickSelectSketchR {
   MemoryRequestServer memReqSvr_;

   private DirectQuickSelectSketch(long seed, WritableMemory wmem) {
      super(seed, wmem);
      this.memReqSvr_ = null;
   }

   DirectQuickSelectSketch(int lgNomLongs, long seed, float p, ResizeFactor rf, MemoryRequestServer memReqSvr, WritableMemory dstMem, boolean unionGadget) {
      this(checkMemSize(lgNomLongs, rf, dstMem, unionGadget), lgNomLongs, seed, p, rf, memReqSvr, dstMem, unionGadget);
   }

   private DirectQuickSelectSketch(boolean secure, int lgNomLongs, long seed, float p, ResizeFactor rf, MemoryRequestServer memReqSvr, WritableMemory dstMem, boolean unionGadget) {
      super(seed, dstMem);
      this.memReqSvr_ = null;
      Family family;
      int preambleLongs;
      if (unionGadget) {
         preambleLongs = Family.UNION.getMinPreLongs();
         family = Family.UNION;
      } else {
         preambleLongs = Family.QUICKSELECT.getMinPreLongs();
         family = Family.QUICKSELECT;
      }

      int lgRF = rf.lg();
      int lgArrLongs = lgRF == 0 ? lgNomLongs + 1 : 5;
      PreambleUtil.insertPreLongs(dstMem, preambleLongs);
      PreambleUtil.insertLgResizeFactor(dstMem, lgRF);
      PreambleUtil.insertSerVer(dstMem, 3);
      PreambleUtil.insertFamilyID(dstMem, family.getID());
      PreambleUtil.insertLgNomLongs(dstMem, lgNomLongs);
      PreambleUtil.insertLgArrLongs(dstMem, lgArrLongs);
      PreambleUtil.insertFlags(dstMem, 4);
      PreambleUtil.insertSeedHash(dstMem, ThetaUtil.computeSeedHash(seed));
      PreambleUtil.insertCurCount(dstMem, 0);
      PreambleUtil.insertP(dstMem, p);
      long thetaLong = (long)((double)p * (double)Long.MAX_VALUE);
      PreambleUtil.insertThetaLong(dstMem, thetaLong);
      if (unionGadget) {
         PreambleUtil.insertUnionThetaLong(dstMem, thetaLong);
      }

      dstMem.clear((long)(preambleLongs << 3), (long)(8 << lgArrLongs));
      this.hashTableThreshold_ = getOffHeapHashTableThreshold(lgNomLongs, lgArrLongs);
      this.memReqSvr_ = memReqSvr;
   }

   private static final boolean checkMemSize(int lgNomLongs, ResizeFactor rf, Memory dstMem, boolean unionGadget) {
      int preambleLongs = unionGadget ? Family.UNION.getMinPreLongs() : Family.QUICKSELECT.getMinPreLongs();
      int lgRF = rf.lg();
      int lgArrLongs = lgRF == 0 ? lgNomLongs + 1 : 5;
      int minReqBytes = PreambleUtil.getMemBytes(lgArrLongs, preambleLongs);
      long curMemCapBytes = dstMem.getCapacity();
      if (curMemCapBytes < (long)minReqBytes) {
         throw new SketchesArgumentException("Memory capacity is too small: " + curMemCapBytes + " < " + minReqBytes);
      } else {
         return true;
      }
   }

   static DirectQuickSelectSketch writableWrap(WritableMemory srcMem, long seed) {
      int preambleLongs = PreambleUtil.extractPreLongs(srcMem);
      int lgNomLongs = PreambleUtil.extractLgNomLongs(srcMem);
      int lgArrLongs = PreambleUtil.extractLgArrLongs(srcMem);
      UpdateSketch.checkUnionQuickSelectFamily(srcMem, preambleLongs, lgNomLongs);
      checkMemIntegrity(srcMem, seed, preambleLongs, lgNomLongs, lgArrLongs);
      if (isResizeFactorIncorrect(srcMem, lgNomLongs, lgArrLongs)) {
         PreambleUtil.insertLgResizeFactor(srcMem, ResizeFactor.X2.lg());
      }

      DirectQuickSelectSketch dqss = new DirectQuickSelectSketch(seed, srcMem);
      dqss.hashTableThreshold_ = getOffHeapHashTableThreshold(lgNomLongs, lgArrLongs);
      return dqss;
   }

   static DirectQuickSelectSketch fastWritableWrap(WritableMemory srcMem, long seed) {
      int lgNomLongs = PreambleUtil.extractLgNomLongs(srcMem);
      int lgArrLongs = PreambleUtil.extractLgArrLongs(srcMem);
      DirectQuickSelectSketch dqss = new DirectQuickSelectSketch(seed, srcMem);
      dqss.hashTableThreshold_ = getOffHeapHashTableThreshold(lgNomLongs, lgArrLongs);
      return dqss;
   }

   public UpdateSketch rebuild() {
      int lgNomLongs = this.getLgNomLongs();
      int preambleLongs = this.wmem_.getByte(0L) & 63;
      if (this.getRetainedEntries(true) > 1 << lgNomLongs) {
         Rebuilder.quickSelectAndRebuild(this.wmem_, preambleLongs, lgNomLongs);
      }

      return this;
   }

   public void reset() {
      int arrLongs = 1 << this.getLgArrLongs();
      int preambleLongs = this.wmem_.getByte(0L) & 63;
      int preBytes = preambleLongs << 3;
      this.wmem_.clear((long)preBytes, (long)arrLongs * 8L);
      this.wmem_.putByte(5L, (byte)4);
      this.wmem_.putInt(8L, 0);
      float p = this.wmem_.getFloat(12L);
      long thetaLong = (long)((double)p * (double)Long.MAX_VALUE);
      this.wmem_.putLong(16L, thetaLong);
   }

   UpdateReturnState hashUpdate(long hash) {
      HashOperations.checkHashCorruption(hash);
      this.wmem_.putByte(5L, (byte)(this.wmem_.getByte(5L) & -5));
      long thetaLong = this.getThetaLong();
      int lgNomLongs = this.getLgNomLongs();
      if (HashOperations.continueCondition(thetaLong, hash)) {
         return UpdateReturnState.RejectedOverTheta;
      } else {
         int lgArrLongs = this.getLgArrLongs();
         int preambleLongs = this.wmem_.getByte(0L) & 63;
         int index = HashOperations.hashSearchOrInsertMemory(this.wmem_, lgArrLongs, hash, preambleLongs << 3);
         if (index >= 0) {
            return UpdateReturnState.RejectedDuplicate;
         } else {
            int curCount = this.getRetainedEntries(true) + 1;
            this.wmem_.putInt(8L, curCount);
            if (this.isOutOfSpace(curCount)) {
               if (lgArrLongs > lgNomLongs) {
                  assert lgArrLongs == lgNomLongs + 1 : "lgArr: " + lgArrLongs + ", lgNom: " + lgNomLongs;

                  Rebuilder.quickSelectAndRebuild(this.wmem_, preambleLongs, lgNomLongs);
                  return UpdateReturnState.InsertedCountIncrementedRebuilt;
               } else {
                  int lgRF = this.getLgRF();
                  int actLgRF = Rebuilder.actLgResizeFactor(this.wmem_.getCapacity(), lgArrLongs, preambleLongs, lgRF);
                  int tgtLgArrLongs = Math.min(lgArrLongs + actLgRF, lgNomLongs + 1);
                  if (actLgRF > 0) {
                     Rebuilder.resize(this.wmem_, preambleLongs, lgArrLongs, tgtLgArrLongs);
                     this.hashTableThreshold_ = getOffHeapHashTableThreshold(lgNomLongs, tgtLgArrLongs);
                     return UpdateReturnState.InsertedCountIncrementedResized;
                  } else {
                     int preBytes = preambleLongs << 3;
                     tgtLgArrLongs = Math.min(lgArrLongs + lgRF, lgNomLongs + 1);
                     int tgtArrBytes = 8 << tgtLgArrLongs;
                     int reqBytes = tgtArrBytes + preBytes;
                     this.memReqSvr_ = this.memReqSvr_ == null ? this.wmem_.getMemoryRequestServer() : this.memReqSvr_;
                     if (this.memReqSvr_ == null) {
                        throw new SketchesArgumentException("Out of Memory, MemoryRequestServer is null, cannot expand.");
                     } else {
                        WritableMemory newDstMem = this.memReqSvr_.request(this.wmem_, (long)reqBytes);
                        Rebuilder.moveAndResize(this.wmem_, preambleLongs, lgArrLongs, newDstMem, tgtLgArrLongs, thetaLong);
                        this.memReqSvr_.requestClose(this.wmem_, newDstMem);
                        this.wmem_ = newDstMem;
                        this.hashTableThreshold_ = getOffHeapHashTableThreshold(lgNomLongs, tgtLgArrLongs);
                        return UpdateReturnState.InsertedCountIncrementedResized;
                     }
                  }
               }
            } else {
               return UpdateReturnState.InsertedCountIncremented;
            }
         }
      }
   }
}
