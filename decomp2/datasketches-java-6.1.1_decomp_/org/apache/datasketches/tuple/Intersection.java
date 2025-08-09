package org.apache.datasketches.tuple;

import org.apache.datasketches.common.SketchesArgumentException;
import org.apache.datasketches.common.SketchesStateException;

public class Intersection {
   private final SummarySetOperations summarySetOps_;
   private boolean empty_;
   private long thetaLong_;
   private HashTables hashTables_;
   private boolean firstCall_;

   public Intersection(SummarySetOperations summarySetOps) {
      this.summarySetOps_ = summarySetOps;
      this.empty_ = false;
      this.thetaLong_ = Long.MAX_VALUE;
      this.hashTables_ = new HashTables();
      this.firstCall_ = true;
   }

   public CompactSketch intersect(Sketch tupleSketchA, Sketch tupleSketchB) {
      this.reset();
      this.intersect(tupleSketchA);
      this.intersect(tupleSketchB);
      CompactSketch<S> csk = this.getResult();
      this.reset();
      return csk;
   }

   public CompactSketch intersect(Sketch tupleSketch, org.apache.datasketches.theta.Sketch thetaSketch, Summary summary) {
      this.reset();
      this.intersect(tupleSketch);
      this.intersect(thetaSketch, summary);
      CompactSketch<S> csk = this.getResult();
      this.reset();
      return csk;
   }

   public void intersect(Sketch tupleSketch) {
      if (tupleSketch == null) {
         throw new SketchesArgumentException("Sketch must not be null");
      } else {
         boolean firstCall = this.firstCall_;
         this.firstCall_ = false;
         boolean emptyIn = tupleSketch.isEmpty();
         if (!this.empty_ && !emptyIn) {
            long thetaLongIn = tupleSketch.getThetaLong();
            this.thetaLong_ = Math.min(this.thetaLong_, thetaLongIn);
            if (tupleSketch.getRetainedEntries() == 0) {
               this.hashTables_.clear();
            } else {
               if (firstCall) {
                  this.hashTables_.fromSketch(tupleSketch);
               } else {
                  if (this.hashTables_.numKeys == 0) {
                     return;
                  }

                  this.hashTables_ = this.hashTables_.getIntersectHashTables(tupleSketch, this.thetaLong_, this.summarySetOps_);
               }

            }
         } else {
            this.resetToEmpty();
         }
      }
   }

   public void intersect(org.apache.datasketches.theta.Sketch thetaSketch, Summary summary) {
      if (thetaSketch == null) {
         throw new SketchesArgumentException("Sketch must not be null");
      } else if (summary == null) {
         throw new SketchesArgumentException("Summary cannot be null.");
      } else {
         boolean firstCall = this.firstCall_;
         this.firstCall_ = false;
         boolean emptyIn = thetaSketch.isEmpty();
         if (!this.empty_ && !emptyIn) {
            long thetaLongIn = thetaSketch.getThetaLong();
            this.thetaLong_ = Math.min(this.thetaLong_, thetaLongIn);
            int countIn = thetaSketch.getRetainedEntries();
            if (countIn == 0) {
               this.hashTables_.clear();
            } else {
               if (firstCall) {
                  this.hashTables_.fromSketch(thetaSketch, summary);
               } else {
                  if (this.hashTables_.numKeys == 0) {
                     return;
                  }

                  this.hashTables_ = this.hashTables_.getIntersectHashTables(thetaSketch, thetaLongIn, this.summarySetOps_, summary);
               }

            }
         } else {
            this.resetToEmpty();
         }
      }
   }

   public CompactSketch getResult() {
      if (this.firstCall_) {
         throw new SketchesStateException("getResult() with no intervening intersections is not a legal result.");
      } else {
         int countIn = this.hashTables_.numKeys;
         if (countIn == 0) {
            return new CompactSketch((long[])null, (Summary[])null, this.thetaLong_, this.empty_);
         } else {
            int tableSize = this.hashTables_.hashTable.length;
            long[] hashArr = new long[countIn];
            S[] summaryArr = (S[])Util.newSummaryArray(this.hashTables_.summaryTable, countIn);
            int cnt = 0;

            for(int i = 0; i < tableSize; ++i) {
               long hash = this.hashTables_.hashTable[i];
               if (hash != 0L && hash <= this.thetaLong_) {
                  hashArr[cnt] = hash;
                  summaryArr[cnt] = this.hashTables_.summaryTable[i].copy();
                  ++cnt;
               }
            }

            assert cnt == countIn;

            return new CompactSketch(hashArr, summaryArr, this.thetaLong_, this.empty_);
         }
      }
   }

   public boolean hasResult() {
      return !this.firstCall_;
   }

   public void reset() {
      this.hardReset();
   }

   private void hardReset() {
      this.empty_ = false;
      this.thetaLong_ = Long.MAX_VALUE;
      this.hashTables_.clear();
      this.firstCall_ = true;
   }

   private void resetToEmpty() {
      this.empty_ = true;
      this.thetaLong_ = Long.MAX_VALUE;
      this.hashTables_.clear();
      this.firstCall_ = false;
   }

   static int getLgTableSize(int count) {
      int tableSize = Math.max(org.apache.datasketches.common.Util.ceilingPowerOf2((int)Math.ceil((double)count / (double)0.75F)), 16);
      return Integer.numberOfTrailingZeros(tableSize);
   }
}
