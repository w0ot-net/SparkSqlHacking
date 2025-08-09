package org.apache.datasketches.theta;

import org.apache.datasketches.memory.Memory;
import org.apache.datasketches.memory.WritableMemory;
import org.apache.datasketches.thetacommon.ThetaUtil;

class DirectCompactSketch extends CompactSketch {
   final Memory mem_;

   DirectCompactSketch(Memory mem) {
      this.mem_ = mem;
   }

   static DirectCompactSketch wrapInstance(Memory srcMem, short seedHash) {
      ThetaUtil.checkSeedHashes((short)PreambleUtil.extractSeedHash(srcMem), seedHash);
      return new DirectCompactSketch(srcMem);
   }

   public CompactSketch compact(boolean dstOrdered, WritableMemory dstMem) {
      return CompactOperations.memoryToCompact(this.mem_, dstOrdered, dstMem);
   }

   public int getCurrentBytes() {
      if (SingleItemSketch.otherCheckForSingleItem(this.mem_)) {
         return 16;
      } else {
         int preLongs = PreambleUtil.extractPreLongs(this.mem_);
         int curCount = preLongs == 1 ? 0 : PreambleUtil.extractCurCount(this.mem_);
         return preLongs + curCount << 3;
      }
   }

   public double getEstimate() {
      if (SingleItemSketch.otherCheckForSingleItem(this.mem_)) {
         return (double)1.0F;
      } else {
         int preLongs = PreambleUtil.extractPreLongs(this.mem_);
         int curCount = preLongs == 1 ? 0 : PreambleUtil.extractCurCount(this.mem_);
         long thetaLong = preLongs > 2 ? PreambleUtil.extractThetaLong(this.mem_) : Long.MAX_VALUE;
         return Sketch.estimate(thetaLong, curCount);
      }
   }

   public int getRetainedEntries(boolean valid) {
      if (SingleItemSketch.otherCheckForSingleItem(this.mem_)) {
         return 1;
      } else {
         int preLongs = PreambleUtil.extractPreLongs(this.mem_);
         int curCount = preLongs == 1 ? 0 : PreambleUtil.extractCurCount(this.mem_);
         return curCount;
      }
   }

   public long getThetaLong() {
      int preLongs = PreambleUtil.extractPreLongs(this.mem_);
      return preLongs > 2 ? PreambleUtil.extractThetaLong(this.mem_) : Long.MAX_VALUE;
   }

   public boolean hasMemory() {
      return this.mem_ != null;
   }

   public boolean isDirect() {
      return this.hasMemory() ? this.mem_.isDirect() : false;
   }

   public boolean isEmpty() {
      boolean emptyFlag = PreambleUtil.isEmptyFlag(this.mem_);
      long thetaLong = this.getThetaLong();
      int curCount = this.getRetainedEntries(true);
      return emptyFlag || curCount == 0 && thetaLong == Long.MAX_VALUE;
   }

   public boolean isOrdered() {
      return (PreambleUtil.extractFlags(this.mem_) & 16) > 0;
   }

   public boolean isSameResource(Memory that) {
      return this.hasMemory() ? this.mem_.isSameResource(that) : false;
   }

   public HashIterator iterator() {
      return new MemoryHashIterator(this.mem_, this.getRetainedEntries(true), this.getThetaLong());
   }

   public byte[] toByteArray() {
      int curCount = this.getRetainedEntries(true);
      CompactOperations.checkIllegalCurCountAndEmpty(this.isEmpty(), curCount);
      int preLongs = PreambleUtil.extractPreLongs(this.mem_);
      int outBytes = curCount + preLongs << 3;
      byte[] byteArrOut = new byte[outBytes];
      this.mem_.getByteArray(0L, byteArrOut, 0, outBytes);
      return byteArrOut;
   }

   long[] getCache() {
      if (SingleItemSketch.otherCheckForSingleItem(this.mem_)) {
         return new long[]{this.mem_.getLong(8L)};
      } else {
         int preLongs = PreambleUtil.extractPreLongs(this.mem_);
         int curCount = preLongs == 1 ? 0 : PreambleUtil.extractCurCount(this.mem_);
         if (curCount > 0) {
            long[] cache = new long[curCount];
            this.mem_.getLongArray((long)(preLongs << 3), cache, 0, curCount);
            return cache;
         } else {
            return new long[0];
         }
      }
   }

   int getCompactPreambleLongs() {
      return PreambleUtil.extractPreLongs(this.mem_);
   }

   int getCurrentPreambleLongs() {
      return PreambleUtil.extractPreLongs(this.mem_);
   }

   Memory getMemory() {
      return this.mem_;
   }

   short getSeedHash() {
      return (short)PreambleUtil.extractSeedHash(this.mem_);
   }
}
