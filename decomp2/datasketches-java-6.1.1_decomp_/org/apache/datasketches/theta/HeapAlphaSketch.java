package org.apache.datasketches.theta;

import java.util.Arrays;
import java.util.Objects;
import org.apache.datasketches.common.Family;
import org.apache.datasketches.common.ResizeFactor;
import org.apache.datasketches.common.SketchesArgumentException;
import org.apache.datasketches.common.Util;
import org.apache.datasketches.memory.Memory;
import org.apache.datasketches.memory.WritableMemory;
import org.apache.datasketches.thetacommon.HashOperations;
import org.apache.datasketches.thetacommon.ThetaUtil;

final class HeapAlphaSketch extends HeapUpdateSketch {
   private static final int ALPHA_MIN_LG_NOM_LONGS = 9;
   private final double alpha_;
   private final long split1_;
   private int lgArrLongs_;
   private int hashTableThreshold_;
   private int curCount_ = 0;
   private long thetaLong_;
   private boolean empty_ = true;
   private long[] cache_;
   private boolean dirty_ = false;

   private HeapAlphaSketch(int lgNomLongs, long seed, float p, ResizeFactor rf, double alpha, long split1) {
      super(lgNomLongs, seed, p, rf);
      this.alpha_ = alpha;
      this.split1_ = split1;
   }

   static HeapAlphaSketch newHeapInstance(int lgNomLongs, long seed, float p, ResizeFactor rf) {
      if (lgNomLongs < 9) {
         throw new SketchesArgumentException("This sketch requires a minimum nominal entries of 512");
      } else {
         double nomLongs = (double)(1L << lgNomLongs);
         double alpha = nomLongs / (nomLongs + (double)1.0F);
         long split1 = (long)((double)p * (alpha + (double)1.0F) / (double)2.0F * (double)Long.MAX_VALUE);
         HeapAlphaSketch has = new HeapAlphaSketch(lgNomLongs, seed, p, rf, alpha, split1);
         int lgArrLongs = ThetaUtil.startingSubMultiple(lgNomLongs + 1, rf.lg(), 5);
         has.lgArrLongs_ = lgArrLongs;
         has.hashTableThreshold_ = setHashTableThreshold(lgNomLongs, lgArrLongs);
         has.curCount_ = 0;
         has.thetaLong_ = (long)((double)p * (double)Long.MAX_VALUE);
         has.empty_ = true;
         has.cache_ = new long[1 << lgArrLongs];
         return has;
      }
   }

   static HeapAlphaSketch heapifyInstance(Memory srcMem, long expectedSeed) {
      Objects.requireNonNull(srcMem, "Source Memory must not be null");
      Util.checkBounds(0L, 24L, srcMem.getCapacity());
      int preambleLongs = PreambleUtil.extractPreLongs(srcMem);
      int lgNomLongs = PreambleUtil.extractLgNomLongs(srcMem);
      int lgArrLongs = PreambleUtil.extractLgArrLongs(srcMem);
      checkAlphaFamily(srcMem, preambleLongs, lgNomLongs);
      checkMemIntegrity(srcMem, expectedSeed, preambleLongs, lgNomLongs, lgArrLongs);
      float p = PreambleUtil.extractP(srcMem);
      int memlgRF = PreambleUtil.extractLgResizeFactor(srcMem);
      ResizeFactor memRF = ResizeFactor.getRF(memlgRF);
      double nomLongs = (double)(1L << lgNomLongs);
      double alpha = nomLongs / (nomLongs + (double)1.0F);
      long split1 = (long)((double)p * (alpha + (double)1.0F) / (double)2.0F * (double)Long.MAX_VALUE);
      if (isResizeFactorIncorrect(srcMem, lgNomLongs, lgArrLongs)) {
         memRF = ResizeFactor.X2;
      }

      HeapAlphaSketch has = new HeapAlphaSketch(lgNomLongs, expectedSeed, p, memRF, alpha, split1);
      has.lgArrLongs_ = lgArrLongs;
      has.hashTableThreshold_ = setHashTableThreshold(lgNomLongs, lgArrLongs);
      has.curCount_ = PreambleUtil.extractCurCount(srcMem);
      has.thetaLong_ = PreambleUtil.extractThetaLong(srcMem);
      has.empty_ = PreambleUtil.isEmptyFlag(srcMem);
      has.cache_ = new long[1 << lgArrLongs];
      srcMem.getLongArray((long)(preambleLongs << 3), has.cache_, 0, 1 << lgArrLongs);
      return has;
   }

   public Family getFamily() {
      return Family.ALPHA;
   }

   public HashIterator iterator() {
      return new HeapHashIterator(this.cache_, this.thetaLong_);
   }

   public double getEstimate() {
      return this.thetaLong_ > this.split1_ ? Sketch.estimate(this.thetaLong_, this.curCount_) : (double)(1 << this.lgNomLongs_) * ((double)Long.MAX_VALUE / (double)this.thetaLong_);
   }

   public double getLowerBound(int numStdDev) {
      if (numStdDev >= 1 && numStdDev <= 3) {
         double lb;
         if (this.isEstimationMode()) {
            int validCount = this.getRetainedEntries(true);
            if (validCount > 0) {
               double est = this.getEstimate();
               double var = getVariance((double)(1 << this.lgNomLongs_), (double)this.getP(), this.alpha_, this.getTheta(), validCount);
               lb = est - (double)numStdDev * Math.sqrt(var);
               lb = Math.max(lb, (double)0.0F);
            } else {
               lb = (double)0.0F;
            }
         } else {
            lb = (double)this.curCount_;
         }

         return lb;
      } else {
         throw new SketchesArgumentException("numStdDev can only be the values 1, 2 or 3.");
      }
   }

   public int getRetainedEntries(boolean valid) {
      if (this.curCount_ > 0 && valid && this.isDirty()) {
         int curCount = HashOperations.countPart(this.getCache(), this.getLgArrLongs(), this.getThetaLong());
         return curCount;
      } else {
         return this.curCount_;
      }
   }

   public long getThetaLong() {
      return this.thetaLong_;
   }

   public double getUpperBound(int numStdDev) {
      if (numStdDev >= 1 && numStdDev <= 3) {
         if (this.isEstimationMode()) {
            double var = getVariance((double)(1 << this.lgNomLongs_), (double)this.getP(), this.alpha_, this.getTheta(), this.getRetainedEntries(true));
            return this.getEstimate() + (double)numStdDev * Math.sqrt(var);
         } else {
            return (double)this.curCount_;
         }
      } else {
         throw new SketchesArgumentException("numStdDev can only be the values 1, 2 or 3.");
      }
   }

   public boolean isEmpty() {
      return this.empty_;
   }

   public byte[] toByteArray() {
      return this.toByteArray(Family.ALPHA.getMinPreLongs(), (byte)Family.ALPHA.getID());
   }

   public UpdateSketch rebuild() {
      if (this.isDirty()) {
         this.rebuildDirty();
      }

      return this;
   }

   public final void reset() {
      int lgArrLongs = ThetaUtil.startingSubMultiple(this.lgNomLongs_ + 1, this.getResizeFactor().lg(), 5);
      if (lgArrLongs == this.lgArrLongs_) {
         int arrLongs = this.cache_.length;

         assert 1 << this.lgArrLongs_ == arrLongs;

         Arrays.fill(this.cache_, 0L);
      } else {
         this.cache_ = new long[1 << lgArrLongs];
         this.lgArrLongs_ = lgArrLongs;
      }

      this.hashTableThreshold_ = setHashTableThreshold(this.lgNomLongs_, this.lgArrLongs_);
      this.empty_ = true;
      this.curCount_ = 0;
      this.thetaLong_ = (long)((double)this.getP() * (double)Long.MAX_VALUE);
      this.dirty_ = false;
   }

   int getCompactPreambleLongs() {
      return CompactOperations.computeCompactPreLongs(this.empty_, this.curCount_, this.thetaLong_);
   }

   int getCurrentPreambleLongs() {
      return Family.ALPHA.getMinPreLongs();
   }

   WritableMemory getMemory() {
      return null;
   }

   long[] getCache() {
      return this.cache_;
   }

   boolean isDirty() {
      return this.dirty_;
   }

   boolean isOutOfSpace(int numEntries) {
      return numEntries > this.hashTableThreshold_;
   }

   int getLgArrLongs() {
      return this.lgArrLongs_;
   }

   UpdateReturnState hashUpdate(long hash) {
      HashOperations.checkHashCorruption(hash);
      this.empty_ = false;
      if (HashOperations.continueCondition(this.thetaLong_, hash)) {
         return UpdateReturnState.RejectedOverTheta;
      } else if (this.dirty_) {
         return this.enhancedHashInsert(this.cache_, hash);
      } else if (HashOperations.hashSearchOrInsert(this.cache_, this.lgArrLongs_, hash) >= 0) {
         return UpdateReturnState.RejectedDuplicate;
      } else {
         ++this.curCount_;
         int r = this.thetaLong_ > this.split1_ ? 0 : 1;
         if (r == 0) {
            if (this.curCount_ > 1 << this.lgNomLongs_) {
               this.thetaLong_ = (long)((double)this.thetaLong_ * this.alpha_);
               this.dirty_ = true;
            } else if (this.isOutOfSpace(this.curCount_)) {
               this.resizeClean();
            }
         } else {
            assert this.lgArrLongs_ > this.lgNomLongs_ : "lgArr: " + this.lgArrLongs_ + ", lgNom: " + this.lgNomLongs_;

            this.thetaLong_ = (long)((double)this.thetaLong_ * this.alpha_);
            this.dirty_ = true;
            if (this.isOutOfSpace(this.curCount_)) {
               this.rebuildDirty();
            }
         }

         return UpdateReturnState.InsertedCountIncremented;
      }
   }

   final UpdateReturnState enhancedHashInsert(long[] hashTable, long hash) {
      int arrayMask = (1 << this.lgArrLongs_) - 1;
      int stride = 2 * (int)(hash >>> this.lgArrLongs_ & 127L) + 1;
      int curProbe = (int)(hash & (long)arrayMask);
      long curTableHash = hashTable[curProbe];
      int loopIndex = curProbe;

      while(curTableHash != hash && curTableHash != 0L) {
         if (curTableHash >= this.thetaLong_) {
            curProbe = curProbe + stride & arrayMask;

            for(curTableHash = hashTable[curProbe]; curTableHash != hash && curTableHash != 0L; curTableHash = hashTable[curProbe]) {
               curProbe = curProbe + stride & arrayMask;
            }

            if (curTableHash == hash) {
               return UpdateReturnState.RejectedDuplicate;
            }

            assert curTableHash == 0L;

            hashTable[curProbe] = hash;
            this.thetaLong_ = (long)((double)this.thetaLong_ * this.alpha_);
            this.dirty_ = true;
            return UpdateReturnState.InsertedCountNotIncremented;
         }

         assert curTableHash < this.thetaLong_;

         curProbe = curProbe + stride & arrayMask;
         curTableHash = hashTable[curProbe];
         if (curProbe == loopIndex) {
            throw new SketchesArgumentException("No empty slot in table!");
         }
      }

      if (curTableHash == hash) {
         return UpdateReturnState.RejectedDuplicate;
      } else {
         assert curTableHash == 0L;

         hashTable[curProbe] = hash;
         this.thetaLong_ = (long)((double)this.thetaLong_ * this.alpha_);
         this.dirty_ = true;
         if (++this.curCount_ > this.hashTableThreshold_) {
            this.rebuildDirty();
         }

         return UpdateReturnState.InsertedCountIncremented;
      }
   }

   private final void rebuildDirty() {
      int curCountBefore = this.curCount_;
      this.forceRebuildDirtyCache();
      if (curCountBefore == this.curCount_) {
         this.forceResizeCleanCache(1);
      }

   }

   private final void resizeClean() {
      int lgTgtLongs = this.lgNomLongs_ + 1;
      if (lgTgtLongs > this.lgArrLongs_) {
         ResizeFactor rf = this.getResizeFactor();
         int lgDeltaLongs = lgTgtLongs - this.lgArrLongs_;
         int lgResizeFactor = Math.max(Math.min(rf.lg(), lgDeltaLongs), 1);
         this.forceResizeCleanCache(lgResizeFactor);
      } else {
         this.forceResizeCleanCache(1);
      }

   }

   private final void forceResizeCleanCache(int lgResizeFactor) {
      assert !this.dirty_;

      this.lgArrLongs_ += lgResizeFactor;
      long[] tgtArr = new long[1 << this.lgArrLongs_];
      int newCount = HashOperations.hashArrayInsert(this.cache_, tgtArr, this.lgArrLongs_, this.thetaLong_);

      assert this.curCount_ == newCount;

      this.curCount_ = newCount;
      this.cache_ = tgtArr;
      this.hashTableThreshold_ = setHashTableThreshold(this.lgNomLongs_, this.lgArrLongs_);
   }

   private final void forceRebuildDirtyCache() {
      long[] tgtArr = new long[1 << this.lgArrLongs_];
      this.curCount_ = HashOperations.hashArrayInsert(this.cache_, tgtArr, this.lgArrLongs_, this.thetaLong_);
      this.cache_ = tgtArr;
      this.dirty_ = false;
   }

   private static final double getVariance(double k, double p, double alpha, double theta, int count) {
      double kPlus1 = k + (double)1.0F;
      double y = (double)1.0F / p;
      double ySq = y * y;
      double ySqMinusY = ySq - y;
      int r = getR(theta, alpha, p);
      double result;
      if (r == 0) {
         result = (double)count * ySqMinusY;
      } else if (r == 1) {
         result = kPlus1 * ySqMinusY;
      } else {
         double b = (double)1.0F / alpha;
         double bSq = b * b;
         double x = p / theta;
         double xSq = x * x;
         double term1 = kPlus1 * ySqMinusY;
         double term2 = y / ((double)1.0F - bSq);
         double term3 = y * bSq - y * xSq - b - bSq + x + x * b;
         result = term1 + term2 * term3;
      }

      double term4 = ((double)1.0F - theta) / (theta * theta);
      return result + term4;
   }

   private static final int getR(double theta, double alpha, double p) {
      double split1 = p * (alpha + (double)1.0F) / (double)2.0F;
      if (theta > split1) {
         return 0;
      } else {
         return theta > alpha * split1 ? 1 : 2;
      }
   }

   private static final int setHashTableThreshold(int lgNomLongs, int lgArrLongs) {
      double fraction = lgArrLongs <= lgNomLongs ? (double)0.5F : (double)0.9375F;
      return (int)Math.floor(fraction * (double)(1 << lgArrLongs));
   }

   static void checkAlphaFamily(Memory mem, int preambleLongs, int lgNomLongs) {
      int familyID = PreambleUtil.extractFamilyID(mem);
      Family family = Family.idToFamily(familyID);
      if (family.equals(Family.ALPHA)) {
         if (preambleLongs != Family.ALPHA.getMinPreLongs()) {
            throw new SketchesArgumentException("Possible corruption: Invalid PreambleLongs value for ALPHA: " + preambleLongs);
         } else if (lgNomLongs < 9) {
            throw new SketchesArgumentException("Possible corruption: This sketch requires a minimum nominal entries of 512");
         }
      } else {
         throw new SketchesArgumentException("Possible corruption: Invalid Family: " + family.toString());
      }
   }
}
