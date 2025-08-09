package org.apache.datasketches.theta;

import java.util.Arrays;
import org.apache.datasketches.common.Family;
import org.apache.datasketches.common.SketchesArgumentException;
import org.apache.datasketches.common.SketchesReadOnlyException;
import org.apache.datasketches.common.SketchesStateException;
import org.apache.datasketches.memory.Memory;
import org.apache.datasketches.memory.WritableMemory;
import org.apache.datasketches.thetacommon.HashOperations;
import org.apache.datasketches.thetacommon.ThetaUtil;

class IntersectionImpl extends Intersection {
   protected final short seedHash_;
   protected final boolean readOnly_;
   protected final WritableMemory wmem_;
   protected final int maxLgArrLongs_;
   protected int lgArrLongs_;
   protected int curCount_;
   protected long thetaLong_;
   protected boolean empty_;
   protected long[] hashTable_;

   protected IntersectionImpl(WritableMemory wmem, long seed, boolean dstMemFlag, boolean readOnly) {
      this.readOnly_ = readOnly;
      if (wmem != null) {
         this.wmem_ = wmem;
         if (dstMemFlag) {
            checkMinSizeMemory(wmem);
            this.maxLgArrLongs_ = !readOnly ? getMaxLgArrLongs(wmem) : 0;
            this.seedHash_ = ThetaUtil.computeSeedHash(seed);
            this.wmem_.putShort(6L, this.seedHash_);
         } else {
            this.seedHash_ = this.wmem_.getShort(6L);
            ThetaUtil.checkSeedHashes(this.seedHash_, ThetaUtil.computeSeedHash(seed));
            this.maxLgArrLongs_ = 0;
         }
      } else {
         this.wmem_ = null;
         this.maxLgArrLongs_ = 0;
         this.seedHash_ = ThetaUtil.computeSeedHash(seed);
      }

   }

   static IntersectionImpl initNewHeapInstance(long seed) {
      boolean dstMemFlag = false;
      boolean readOnly = false;
      IntersectionImpl impl = new IntersectionImpl((WritableMemory)null, seed, false, false);
      impl.hardReset();
      return impl;
   }

   static IntersectionImpl initNewDirectInstance(long seed, WritableMemory dstMem) {
      dstMem.clear(0L, 24L);
      PreambleUtil.insertPreLongs(dstMem, 3);
      PreambleUtil.insertSerVer(dstMem, 3);
      PreambleUtil.insertFamilyID(dstMem, Family.INTERSECTION.getID());
      PreambleUtil.insertP(dstMem, 1.0F);
      boolean dstMemFlag = true;
      boolean readOnly = false;
      IntersectionImpl impl = new IntersectionImpl(dstMem, seed, true, false);
      impl.hardReset();
      return impl;
   }

   static IntersectionImpl heapifyInstance(Memory srcMem, long seed) {
      boolean dstMemFlag = false;
      boolean readOnly = false;
      IntersectionImpl impl = new IntersectionImpl((WritableMemory)null, seed, false, false);
      memChecks(srcMem);
      impl.lgArrLongs_ = PreambleUtil.extractLgArrLongs(srcMem);
      impl.curCount_ = PreambleUtil.extractCurCount(srcMem);
      impl.thetaLong_ = PreambleUtil.extractThetaLong(srcMem);
      impl.empty_ = (PreambleUtil.extractFlags(srcMem) & 4) > 0;
      if (!impl.empty_ && impl.curCount_ > 0) {
         impl.hashTable_ = new long[1 << impl.lgArrLongs_];
         srcMem.getLongArray(24L, impl.hashTable_, 0, 1 << impl.lgArrLongs_);
      }

      return impl;
   }

   static IntersectionImpl wrapInstance(WritableMemory srcMem, long seed, boolean readOnly) {
      boolean dstMemFlag = false;
      IntersectionImpl impl = new IntersectionImpl(srcMem, seed, false, readOnly);
      memChecks(srcMem);
      impl.lgArrLongs_ = PreambleUtil.extractLgArrLongs(srcMem);
      impl.curCount_ = PreambleUtil.extractCurCount(srcMem);
      impl.thetaLong_ = PreambleUtil.extractThetaLong(srcMem);
      impl.empty_ = (PreambleUtil.extractFlags(srcMem) & 4) > 0;
      return impl;
   }

   public CompactSketch intersect(Sketch a, Sketch b, boolean dstOrdered, WritableMemory dstMem) {
      if (this.wmem_ != null && this.readOnly_) {
         throw new SketchesReadOnlyException();
      } else {
         this.hardReset();
         this.intersect(a);
         this.intersect(b);
         CompactSketch csk = this.getResult(dstOrdered, dstMem);
         this.hardReset();
         return csk;
      }
   }

   public void intersect(Sketch sketchIn) {
      if (sketchIn == null) {
         throw new SketchesArgumentException("Intersection argument must not be null.");
      } else if (this.wmem_ != null && this.readOnly_) {
         throw new SketchesReadOnlyException();
      } else if (!this.empty_ && !sketchIn.isEmpty()) {
         ThetaUtil.checkSeedHashes(this.seedHash_, sketchIn.getSeedHash());
         this.thetaLong_ = Math.min(this.thetaLong_, sketchIn.getThetaLong());
         this.empty_ = false;
         if (this.wmem_ != null) {
            PreambleUtil.insertThetaLong(this.wmem_, this.thetaLong_);
            PreambleUtil.clearEmpty(this.wmem_);
         }

         int sketchInEntries = sketchIn.getRetainedEntries(true);
         if (this.curCount_ != 0 && sketchInEntries != 0) {
            if (this.curCount_ < 0 && sketchInEntries > 0) {
               this.curCount_ = sketchIn.getRetainedEntries(true);
               int requiredLgArrLongs = HashOperations.minLgHashTableSize(this.curCount_, (double)0.9375F);
               int priorLgArrLongs = this.lgArrLongs_;
               this.lgArrLongs_ = requiredLgArrLongs;
               if (this.wmem_ != null) {
                  PreambleUtil.insertCurCount(this.wmem_, this.curCount_);
                  PreambleUtil.insertLgArrLongs(this.wmem_, this.lgArrLongs_);
                  if (requiredLgArrLongs > this.maxLgArrLongs_) {
                     int requiredBytes = (8 << requiredLgArrLongs) + 24;
                     int givenBytes = (8 << priorLgArrLongs) + 24;
                     throw new SketchesArgumentException("Insufficient internal Memory space: " + requiredBytes + " > " + givenBytes);
                  }

                  this.wmem_.clear(24L, (long)(8 << this.lgArrLongs_));
               } else {
                  this.hashTable_ = new long[1 << this.lgArrLongs_];
               }

               this.moveDataToTgt(sketchIn.getCache(), this.curCount_);
            } else if (this.curCount_ > 0 && sketchInEntries > 0) {
               this.performIntersect(sketchIn);
            } else {
               assert false : "Should not happen";
            }
         } else {
            this.curCount_ = 0;
            if (this.wmem_ != null) {
               PreambleUtil.insertCurCount(this.wmem_, 0);
            }

            this.hashTable_ = null;
         }

      } else {
         this.resetToEmpty();
      }
   }

   public CompactSketch getResult(boolean dstOrdered, WritableMemory dstMem) {
      if (this.curCount_ < 0) {
         throw new SketchesStateException("Calling getResult() with no intervening intersections would represent the infinite set, which is not a legal result.");
      } else if (this.curCount_ == 0) {
         long[] compactCache = new long[0];
         boolean srcCompact = true;
         boolean srcOrdered = false;
         return CompactOperations.componentsToCompact(this.thetaLong_, this.curCount_, this.seedHash_, this.empty_, srcCompact, srcOrdered, dstOrdered, dstMem, compactCache);
      } else {
         long[] hashTable;
         if (this.wmem_ != null) {
            int htLen = 1 << this.lgArrLongs_;
            hashTable = new long[htLen];
            this.wmem_.getLongArray(24L, hashTable, 0, htLen);
         } else {
            hashTable = this.hashTable_;
         }

         long[] compactCache = compactCachePart(hashTable, this.lgArrLongs_, this.curCount_, this.thetaLong_, dstOrdered);
         boolean srcCompact = true;
         return CompactOperations.componentsToCompact(this.thetaLong_, this.curCount_, this.seedHash_, this.empty_, srcCompact, dstOrdered, dstOrdered, dstMem, compactCache);
      }
   }

   public boolean hasMemory() {
      return this.wmem_ != null;
   }

   public boolean hasResult() {
      return this.hasMemory() ? this.wmem_.getInt(8L) >= 0 : this.curCount_ >= 0;
   }

   public boolean isDirect() {
      return this.hasMemory() ? this.wmem_.isDirect() : false;
   }

   public boolean isSameResource(Memory that) {
      return this.hasMemory() ? this.wmem_.isSameResource(that) : false;
   }

   public void reset() {
      this.hardReset();
   }

   public byte[] toByteArray() {
      int preBytes = 24;
      int dataBytes = this.curCount_ > 0 ? 8 << this.lgArrLongs_ : 0;
      byte[] byteArrOut = new byte[24 + dataBytes];
      if (this.wmem_ != null) {
         this.wmem_.getByteArray(0L, byteArrOut, 0, 24 + dataBytes);
      } else {
         WritableMemory memOut = WritableMemory.writableWrap(byteArrOut);
         memOut.putByte(0L, (byte)3);
         memOut.putByte(1L, (byte)3);
         memOut.putByte(2L, (byte)Family.INTERSECTION.getID());
         memOut.putByte(3L, (byte)0);
         memOut.putByte(4L, (byte)this.lgArrLongs_);
         if (this.empty_) {
            memOut.setBits(5L, (byte)4);
         } else {
            memOut.clearBits(5L, (byte)4);
         }

         memOut.putShort(6L, this.seedHash_);
         memOut.putInt(8L, this.curCount_);
         memOut.putFloat(12L, 1.0F);
         memOut.putLong(16L, this.thetaLong_);
         if (this.curCount_ > 0) {
            memOut.putLongArray(24L, this.hashTable_, 0, 1 << this.lgArrLongs_);
         }
      }

      return byteArrOut;
   }

   int getRetainedEntries() {
      return this.curCount_;
   }

   boolean isEmpty() {
      return this.empty_;
   }

   long[] getCache() {
      if (this.wmem_ == null) {
         return this.hashTable_ != null ? this.hashTable_ : new long[0];
      } else {
         int arrLongs = 1 << this.lgArrLongs_;
         long[] outArr = new long[arrLongs];
         this.wmem_.getLongArray(24L, outArr, 0, arrLongs);
         return outArr;
      }
   }

   short getSeedHash() {
      return this.seedHash_;
   }

   long getThetaLong() {
      return this.thetaLong_;
   }

   private void performIntersect(Sketch sketchIn) {
      assert this.curCount_ > 0 && !this.empty_;

      long[] cacheIn = sketchIn.getCache();
      int arrLongsIn = cacheIn.length;
      long[] hashTable;
      if (this.wmem_ != null) {
         int htLen = 1 << this.lgArrLongs_;
         hashTable = new long[htLen];
         this.wmem_.getLongArray(24L, hashTable, 0, htLen);
      } else {
         hashTable = this.hashTable_;
      }

      long[] matchSet = new long[Math.min(this.curCount_, sketchIn.getRetainedEntries(true))];
      int matchSetCount = 0;
      if (sketchIn.isOrdered()) {
         for(int i = 0; i < arrLongsIn; ++i) {
            long hashIn = cacheIn[i];
            if (hashIn >= this.thetaLong_) {
               break;
            }

            int foundIdx = HashOperations.hashSearch(hashTable, this.lgArrLongs_, hashIn);
            if (foundIdx != -1) {
               matchSet[matchSetCount++] = hashIn;
            }
         }
      } else {
         for(int i = 0; i < arrLongsIn; ++i) {
            long hashIn = cacheIn[i];
            if (hashIn > 0L && hashIn < this.thetaLong_) {
               int foundIdx = HashOperations.hashSearch(hashTable, this.lgArrLongs_, hashIn);
               if (foundIdx != -1) {
                  matchSet[matchSetCount++] = hashIn;
               }
            }
         }
      }

      this.curCount_ = matchSetCount;
      this.lgArrLongs_ = HashOperations.minLgHashTableSize(matchSetCount, (double)0.9375F);
      if (this.wmem_ != null) {
         PreambleUtil.insertCurCount(this.wmem_, matchSetCount);
         PreambleUtil.insertLgArrLongs(this.wmem_, this.lgArrLongs_);
         this.wmem_.clear(24L, (long)(8 << this.lgArrLongs_));
      } else {
         Arrays.fill(this.hashTable_, 0, 1 << this.lgArrLongs_, 0L);
      }

      if (this.curCount_ > 0) {
         this.moveDataToTgt(matchSet, matchSetCount);
      } else if (this.thetaLong_ == Long.MAX_VALUE) {
         this.empty_ = true;
      }

   }

   private void moveDataToTgt(long[] arr, int count) {
      int arrLongsIn = arr.length;
      int tmpCnt = 0;
      if (this.wmem_ != null) {
         int preBytes = 24;
         int lgArrLongs = this.lgArrLongs_;
         long thetaLong = this.thetaLong_;

         for(int i = 0; i < arrLongsIn; ++i) {
            long hashIn = arr[i];
            if (!HashOperations.continueCondition(thetaLong, hashIn)) {
               HashOperations.hashInsertOnlyMemory(this.wmem_, lgArrLongs, hashIn, 24);
               ++tmpCnt;
            }
         }
      } else {
         for(int i = 0; i < arrLongsIn; ++i) {
            long hashIn = arr[i];
            if (!HashOperations.continueCondition(this.thetaLong_, hashIn)) {
               HashOperations.hashInsertOnly(this.hashTable_, this.lgArrLongs_, hashIn);
               ++tmpCnt;
            }
         }
      }

      assert tmpCnt == count : "Intersection Count Check: got: " + tmpCnt + ", expected: " + count;

   }

   private void hardReset() {
      this.resetCommon();
      if (this.wmem_ != null) {
         PreambleUtil.insertCurCount(this.wmem_, -1);
         PreambleUtil.clearEmpty(this.wmem_);
      }

      this.curCount_ = -1;
      this.empty_ = false;
   }

   private void resetToEmpty() {
      this.resetCommon();
      if (this.wmem_ != null) {
         PreambleUtil.insertCurCount(this.wmem_, 0);
         PreambleUtil.setEmpty(this.wmem_);
      }

      this.curCount_ = 0;
      this.empty_ = true;
   }

   private void resetCommon() {
      if (this.wmem_ != null) {
         if (this.readOnly_) {
            throw new SketchesReadOnlyException();
         }

         this.wmem_.clear(24L, 256L);
         PreambleUtil.insertLgArrLongs(this.wmem_, 5);
         PreambleUtil.insertThetaLong(this.wmem_, Long.MAX_VALUE);
      }

      this.lgArrLongs_ = 5;
      this.thetaLong_ = Long.MAX_VALUE;
      this.hashTable_ = null;
   }
}
