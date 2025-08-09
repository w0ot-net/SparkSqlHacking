package org.apache.datasketches.tuple.arrayofdoubles;

import org.apache.datasketches.common.SketchesArgumentException;
import org.apache.datasketches.common.SketchesStateException;
import org.apache.datasketches.memory.WritableMemory;
import org.apache.datasketches.tuple.Util;

public abstract class ArrayOfDoublesIntersection {
   private final short seedHash_;
   private final int numValues_;
   private HashTables hashTables_;
   private boolean empty_;
   private boolean firstCall_;
   private long thetaLong_;

   ArrayOfDoublesIntersection(int numValues, long seed) {
      this.seedHash_ = Util.computeSeedHash(seed);
      this.numValues_ = numValues;
      this.hashTables_ = null;
      this.empty_ = false;
      this.thetaLong_ = Long.MAX_VALUE;
      this.firstCall_ = true;
   }

   public void intersect(ArrayOfDoublesSketch tupleSketch, ArrayOfDoublesCombiner combiner) {
      if (tupleSketch == null) {
         throw new SketchesArgumentException("Sketch must not be null");
      } else {
         Util.checkSeedHashes(this.seedHash_, tupleSketch.getSeedHash());
         if (tupleSketch.numValues_ != this.numValues_) {
            throw new SketchesArgumentException("Input tupleSketch cannot have different numValues from the internal numValues.");
         } else {
            boolean isFirstCall = this.firstCall_;
            this.firstCall_ = false;
            boolean emptyIn = tupleSketch.isEmpty();
            if (!this.empty_ && !emptyIn) {
               long thetaLongIn = tupleSketch.getThetaLong();
               this.thetaLong_ = Math.min(this.thetaLong_, thetaLongIn);
               if (tupleSketch.getRetainedEntries() == 0 && this.hashTables_ != null) {
                  this.hashTables_.clear();
               }

               if (isFirstCall) {
                  this.hashTables_ = new HashTables(tupleSketch);
               } else {
                  assert this.hashTables_ != null;

                  if (this.hashTables_.getNumKeys() == 0) {
                     return;
                  }

                  this.hashTables_ = this.hashTables_.getIntersectHashTables(tupleSketch, this.thetaLong_, combiner);
               }

            } else {
               this.resetToEmpty();
            }
         }
      }
   }

   public ArrayOfDoublesCompactSketch getResult() {
      return this.getResult((WritableMemory)null);
   }

   public ArrayOfDoublesCompactSketch getResult(WritableMemory dstMem) {
      if (this.firstCall_) {
         throw new SketchesStateException("getResult() with no intervening intersections is not a legal result.");
      } else {
         long[] hashArrOut = new long[0];
         double[] valuesArrOut = new double[0];
         if (this.hashTables_ != null && this.hashTables_.getHashTable() != null) {
            int numKeys = this.hashTables_.getNumKeys();
            if (numKeys > 0) {
               int tableSize = this.hashTables_.getHashTable().length;
               hashArrOut = new long[numKeys];
               valuesArrOut = new double[numKeys * this.numValues_];
               int cnt = 0;
               long[] hashTable = this.hashTables_.getHashTable();
               double[][] valueTable = this.hashTables_.getValueTable();

               for(int i = 0; i < tableSize; ++i) {
                  long hash = hashTable[i];
                  if (hash != 0L && hash <= this.thetaLong_) {
                     hashArrOut[cnt] = hash;
                     System.arraycopy(valueTable[i], 0, valuesArrOut, cnt * this.numValues_, this.numValues_);
                     ++cnt;
                  }
               }

               assert cnt == numKeys;
            }
         }

         return (ArrayOfDoublesCompactSketch)(dstMem == null ? new HeapArrayOfDoublesCompactSketch(hashArrOut, valuesArrOut, this.thetaLong_, this.empty_, this.numValues_, this.seedHash_) : new DirectArrayOfDoublesCompactSketch(hashArrOut, valuesArrOut, this.thetaLong_, this.empty_, this.numValues_, this.seedHash_, dstMem));
      }
   }

   public void reset() {
      this.hardReset();
   }

   private void hardReset() {
      this.empty_ = false;
      this.firstCall_ = true;
      this.thetaLong_ = Long.MAX_VALUE;
      if (this.hashTables_ != null) {
         this.hashTables_.clear();
      }

   }

   private void resetToEmpty() {
      this.empty_ = true;
      this.firstCall_ = false;
      this.thetaLong_ = Long.MAX_VALUE;
      if (this.hashTables_ != null) {
         this.hashTables_.clear();
      }

   }

   protected abstract ArrayOfDoublesQuickSelectSketch createSketch(int var1, int var2, long var3);
}
