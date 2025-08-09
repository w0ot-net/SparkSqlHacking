package org.apache.datasketches.theta;

import java.util.Arrays;
import org.apache.datasketches.common.Family;
import org.apache.datasketches.common.ResizeFactor;
import org.apache.datasketches.memory.Memory;
import org.apache.datasketches.memory.WritableMemory;
import org.apache.datasketches.thetacommon.HashOperations;
import org.apache.datasketches.thetacommon.QuickSelect;
import org.apache.datasketches.thetacommon.ThetaUtil;

class HeapQuickSelectSketch extends HeapUpdateSketch {
   private final Family MY_FAMILY;
   private final int preambleLongs_;
   private int lgArrLongs_;
   private int hashTableThreshold_;
   int curCount_;
   long thetaLong_;
   boolean empty_;
   private long[] cache_;

   private HeapQuickSelectSketch(int lgNomLongs, long seed, float p, ResizeFactor rf, int preambleLongs, Family family) {
      super(lgNomLongs, seed, p, rf);
      this.preambleLongs_ = preambleLongs;
      this.MY_FAMILY = family;
   }

   HeapQuickSelectSketch(int lgNomLongs, long seed, float p, ResizeFactor rf, boolean unionGadget) {
      super(lgNomLongs, seed, p, rf);
      if (unionGadget) {
         this.preambleLongs_ = Family.UNION.getMinPreLongs();
         this.MY_FAMILY = Family.UNION;
      } else {
         this.preambleLongs_ = Family.QUICKSELECT.getMinPreLongs();
         this.MY_FAMILY = Family.QUICKSELECT;
      }

      this.lgArrLongs_ = ThetaUtil.startingSubMultiple(lgNomLongs + 1, rf.lg(), 5);
      this.hashTableThreshold_ = getHashTableThreshold(lgNomLongs, this.lgArrLongs_);
      this.curCount_ = 0;
      this.thetaLong_ = (long)((double)p * (double)Long.MAX_VALUE);
      this.empty_ = true;
      this.cache_ = new long[1 << this.lgArrLongs_];
   }

   static HeapQuickSelectSketch heapifyInstance(Memory srcMem, long seed) {
      int preambleLongs = PreambleUtil.extractPreLongs(srcMem);
      int lgNomLongs = PreambleUtil.extractLgNomLongs(srcMem);
      int lgArrLongs = PreambleUtil.extractLgArrLongs(srcMem);
      checkUnionQuickSelectFamily(srcMem, preambleLongs, lgNomLongs);
      checkMemIntegrity(srcMem, seed, preambleLongs, lgNomLongs, lgArrLongs);
      float p = PreambleUtil.extractP(srcMem);
      int memlgRF = PreambleUtil.extractLgResizeFactor(srcMem);
      ResizeFactor memRF = ResizeFactor.getRF(memlgRF);
      int familyID = PreambleUtil.extractFamilyID(srcMem);
      Family family = Family.idToFamily(familyID);
      if (isResizeFactorIncorrect(srcMem, lgNomLongs, lgArrLongs)) {
         memRF = ResizeFactor.X2;
      }

      HeapQuickSelectSketch hqss = new HeapQuickSelectSketch(lgNomLongs, seed, p, memRF, preambleLongs, family);
      hqss.lgArrLongs_ = lgArrLongs;
      hqss.hashTableThreshold_ = getHashTableThreshold(lgNomLongs, lgArrLongs);
      hqss.curCount_ = PreambleUtil.extractCurCount(srcMem);
      hqss.thetaLong_ = PreambleUtil.extractThetaLong(srcMem);
      hqss.empty_ = PreambleUtil.isEmptyFlag(srcMem);
      hqss.cache_ = new long[1 << lgArrLongs];
      srcMem.getLongArray((long)(preambleLongs << 3), hqss.cache_, 0, 1 << lgArrLongs);
      return hqss;
   }

   public double getEstimate() {
      return Sketch.estimate(this.thetaLong_, this.curCount_);
   }

   public Family getFamily() {
      return this.MY_FAMILY;
   }

   public int getRetainedEntries(boolean valid) {
      return this.curCount_;
   }

   public long getThetaLong() {
      return this.empty_ ? Long.MAX_VALUE : this.thetaLong_;
   }

   public boolean isEmpty() {
      return this.empty_;
   }

   public HashIterator iterator() {
      return new HeapHashIterator(this.cache_, this.thetaLong_);
   }

   public byte[] toByteArray() {
      return this.toByteArray(this.preambleLongs_, (byte)this.MY_FAMILY.getID());
   }

   public UpdateSketch rebuild() {
      if (this.getRetainedEntries(true) > 1 << this.getLgNomLongs()) {
         this.quickSelectAndRebuild();
      }

      return this;
   }

   public void reset() {
      ResizeFactor rf = this.getResizeFactor();
      int lgArrLongsSM = ThetaUtil.startingSubMultiple(this.lgNomLongs_ + 1, rf.lg(), 5);
      if (lgArrLongsSM == this.lgArrLongs_) {
         int arrLongs = this.cache_.length;

         assert 1 << this.lgArrLongs_ == arrLongs;

         Arrays.fill(this.cache_, 0L);
      } else {
         this.cache_ = new long[1 << lgArrLongsSM];
         this.lgArrLongs_ = lgArrLongsSM;
      }

      this.hashTableThreshold_ = getHashTableThreshold(this.lgNomLongs_, this.lgArrLongs_);
      this.empty_ = true;
      this.curCount_ = 0;
      this.thetaLong_ = (long)((double)this.getP() * (double)Long.MAX_VALUE);
   }

   long[] getCache() {
      return this.cache_;
   }

   int getCompactPreambleLongs() {
      return CompactOperations.computeCompactPreLongs(this.empty_, this.curCount_, this.thetaLong_);
   }

   int getCurrentPreambleLongs() {
      return this.preambleLongs_;
   }

   int getHashTableThreshold() {
      return this.hashTableThreshold_;
   }

   int getLgArrLongs() {
      return this.lgArrLongs_;
   }

   WritableMemory getMemory() {
      return null;
   }

   UpdateReturnState hashUpdate(long hash) {
      HashOperations.checkHashCorruption(hash);
      this.empty_ = false;
      if (HashOperations.continueCondition(this.thetaLong_, hash)) {
         return UpdateReturnState.RejectedOverTheta;
      } else if (HashOperations.hashSearchOrInsert(this.cache_, this.lgArrLongs_, hash) >= 0) {
         return UpdateReturnState.RejectedDuplicate;
      } else {
         ++this.curCount_;
         if (this.isOutOfSpace(this.curCount_)) {
            if (this.lgArrLongs_ <= this.lgNomLongs_) {
               this.resizeCache();
               return UpdateReturnState.InsertedCountIncrementedResized;
            } else {
               assert this.lgArrLongs_ == this.lgNomLongs_ + 1 : "lgArr: " + this.lgArrLongs_ + ", lgNom: " + this.lgNomLongs_;

               this.quickSelectAndRebuild();
               return UpdateReturnState.InsertedCountIncrementedRebuilt;
            }
         } else {
            return UpdateReturnState.InsertedCountIncremented;
         }
      }
   }

   boolean isDirty() {
      return false;
   }

   boolean isOutOfSpace(int numEntries) {
      return numEntries > this.hashTableThreshold_;
   }

   private final void resizeCache() {
      ResizeFactor rf = this.getResizeFactor();
      int lgMaxArrLongs = this.lgNomLongs_ + 1;
      int lgDeltaLongs = lgMaxArrLongs - this.lgArrLongs_;
      int lgResizeFactor = Math.max(Math.min(rf.lg(), lgDeltaLongs), 1);
      this.lgArrLongs_ += lgResizeFactor;
      long[] tgtArr = new long[1 << this.lgArrLongs_];
      int newCount = HashOperations.hashArrayInsert(this.cache_, tgtArr, this.lgArrLongs_, this.thetaLong_);

      assert newCount == this.curCount_;

      this.curCount_ = newCount;
      this.cache_ = tgtArr;
      this.hashTableThreshold_ = getHashTableThreshold(this.lgNomLongs_, this.lgArrLongs_);
   }

   private final void quickSelectAndRebuild() {
      int arrLongs = 1 << this.lgArrLongs_;
      int pivot = (1 << this.lgNomLongs_) + 1;
      this.thetaLong_ = QuickSelect.selectExcludingZeros(this.cache_, this.curCount_, pivot);
      long[] tgtArr = new long[arrLongs];
      this.curCount_ = HashOperations.hashArrayInsert(this.cache_, tgtArr, this.lgArrLongs_, this.thetaLong_);
      this.cache_ = tgtArr;
   }

   private static final int getHashTableThreshold(int lgNomLongs, int lgArrLongs) {
      double fraction = lgArrLongs <= lgNomLongs ? (double)0.5F : (double)0.9375F;
      return (int)(fraction * (double)(1 << lgArrLongs));
   }
}
