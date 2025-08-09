package org.apache.datasketches.theta;

import org.apache.datasketches.memory.Memory;
import org.apache.datasketches.memory.WritableMemory;

class HeapCompactSketch extends CompactSketch {
   private final long thetaLong_;
   private final int curCount_;
   private final int preLongs_;
   private final short seedHash_;
   private final boolean empty_;
   private final boolean ordered_;
   private final boolean singleItem_;
   private final long[] cache_;

   HeapCompactSketch(long[] cache, boolean empty, short seedHash, int curCount, long thetaLong, boolean ordered) {
      this.seedHash_ = seedHash;
      this.curCount_ = curCount;
      this.empty_ = empty;
      this.ordered_ = ordered;
      this.cache_ = cache;
      this.thetaLong_ = CompactOperations.correctThetaOnCompact(empty, curCount, thetaLong);
      this.preLongs_ = CompactOperations.computeCompactPreLongs(empty, curCount, thetaLong);
      this.singleItem_ = CompactOperations.isSingleItem(empty, curCount, thetaLong);
      CompactOperations.checkIllegalCurCountAndEmpty(empty, curCount);
   }

   public CompactSketch compact(boolean dstOrdered, WritableMemory dstMem) {
      return (CompactSketch)(dstMem != null || dstOrdered && this.ordered_ != dstOrdered ? CompactOperations.componentsToCompact(this.getThetaLong(), this.getRetainedEntries(true), this.getSeedHash(), this.isEmpty(), true, this.ordered_, dstOrdered, dstMem, (long[])this.getCache().clone()) : this);
   }

   public int getCurrentBytes() {
      return this.preLongs_ + this.curCount_ << 3;
   }

   public double getEstimate() {
      return Sketch.estimate(this.thetaLong_, this.curCount_);
   }

   public int getRetainedEntries(boolean valid) {
      return this.curCount_;
   }

   public long getThetaLong() {
      return this.thetaLong_;
   }

   public boolean isEmpty() {
      return this.empty_;
   }

   public boolean isOrdered() {
      return this.ordered_;
   }

   public HashIterator iterator() {
      return new HeapCompactHashIterator(this.cache_);
   }

   long[] getCache() {
      return this.cache_;
   }

   int getCompactPreambleLongs() {
      return this.preLongs_;
   }

   int getCurrentPreambleLongs() {
      return this.preLongs_;
   }

   Memory getMemory() {
      return null;
   }

   short getSeedHash() {
      return this.seedHash_;
   }

   public byte[] toByteArray() {
      int bytes = this.getCurrentBytes();
      byte[] byteArray = new byte[bytes];
      WritableMemory dstMem = WritableMemory.writableWrap(byteArray);
      int emptyBit = this.isEmpty() ? 4 : 0;
      int orderedBit = this.ordered_ ? 16 : 0;
      int singleItemBit = this.singleItem_ ? 32 : 0;
      byte flags = (byte)(emptyBit | 2 | 8 | orderedBit | singleItemBit);
      int preLongs = this.getCompactPreambleLongs();
      CompactOperations.loadCompactMemory(this.getCache(), this.getSeedHash(), this.getRetainedEntries(true), this.getThetaLong(), dstMem, flags, preLongs);
      return byteArray;
   }
}
