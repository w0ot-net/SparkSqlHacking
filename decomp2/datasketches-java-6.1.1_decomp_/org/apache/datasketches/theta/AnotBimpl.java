package org.apache.datasketches.theta;

import java.util.Arrays;
import org.apache.datasketches.common.SketchesArgumentException;
import org.apache.datasketches.common.Util;
import org.apache.datasketches.memory.WritableMemory;
import org.apache.datasketches.thetacommon.HashOperations;
import org.apache.datasketches.thetacommon.ThetaUtil;

final class AnotBimpl extends AnotB {
   private final short seedHash_;
   private boolean empty_;
   private long thetaLong_;
   private long[] hashArr_;
   private int curCount_;

   AnotBimpl(long seed) {
      this(ThetaUtil.computeSeedHash(seed));
   }

   private AnotBimpl(short seedHash) {
      this.hashArr_ = new long[0];
      this.seedHash_ = seedHash;
      this.reset();
   }

   public void setA(Sketch skA) {
      if (skA == null) {
         this.reset();
         throw new SketchesArgumentException("The input argument <i>A</i> must not be null");
      } else if (skA.isEmpty()) {
         this.reset();
      } else {
         ThetaUtil.checkSeedHashes(this.seedHash_, skA.getSeedHash());
         this.hashArr_ = getHashArrA(skA);
         this.empty_ = false;
         this.thetaLong_ = skA.getThetaLong();
         this.curCount_ = this.hashArr_.length;
      }
   }

   public void notB(Sketch skB) {
      if (!this.empty_ && skB != null && !skB.isEmpty()) {
         ThetaUtil.checkSeedHashes(this.seedHash_, skB.getSeedHash());
         this.thetaLong_ = Math.min(this.thetaLong_, skB.getThetaLong());
         this.hashArr_ = getResultHashArr(this.thetaLong_, this.curCount_, this.hashArr_, skB);
         this.curCount_ = this.hashArr_.length;
         this.empty_ = this.curCount_ == 0 && this.thetaLong_ == Long.MAX_VALUE;
      }
   }

   public CompactSketch getResult(boolean reset) {
      return this.getResult(true, (WritableMemory)null, reset);
   }

   public CompactSketch getResult(boolean dstOrdered, WritableMemory dstMem, boolean reset) {
      CompactSketch result = CompactOperations.componentsToCompact(this.thetaLong_, this.curCount_, this.seedHash_, this.empty_, true, false, dstOrdered, dstMem, (long[])this.hashArr_.clone());
      if (reset) {
         this.reset();
      }

      return result;
   }

   public CompactSketch aNotB(Sketch skA, Sketch skB, boolean dstOrdered, WritableMemory dstMem) {
      if (skA != null && skB != null) {
         long minThetaLong = Math.min(skA.getThetaLong(), skB.getThetaLong());
         if (skA.isEmpty()) {
            return skA.compact(dstOrdered, dstMem);
         } else {
            ThetaUtil.checkSeedHashes(skA.getSeedHash(), this.seedHash_);
            if (skB.isEmpty()) {
               return skA.compact(dstOrdered, dstMem);
            } else {
               ThetaUtil.checkSeedHashes(skB.getSeedHash(), this.seedHash_);
               long[] hashArrA = getHashArrA(skA);
               int countA = hashArrA.length;
               long[] hashArrOut = getResultHashArr(minThetaLong, countA, hashArrA, skB);
               int countOut = hashArrOut.length;
               boolean empty = countOut == 0 && minThetaLong == Long.MAX_VALUE;
               CompactSketch result = CompactOperations.componentsToCompact(minThetaLong, countOut, this.seedHash_, empty, true, false, dstOrdered, dstMem, hashArrOut);
               return result;
            }
         }
      } else {
         throw new SketchesArgumentException("Neither argument may be null");
      }
   }

   int getRetainedEntries() {
      return this.curCount_;
   }

   private static long[] getHashArrA(Sketch skA) {
      CompactSketch cskA = skA.compact(false, (WritableMemory)null);
      long[] hashArrA = (long[])cskA.getCache().clone();
      return hashArrA;
   }

   private static long[] getResultHashArr(long minThetaLong, int countA, long[] hashArrA, Sketch skB) {
      long[] thetaCache = skB.getCache();
      int countB = skB.getRetainedEntries(true);
      long[] hashTableB;
      if (skB instanceof CompactSketch) {
         hashTableB = HashOperations.convertToHashTable(thetaCache, countB, minThetaLong, (double)0.9375F);
      } else {
         hashTableB = thetaCache;
      }

      long[] tmpHashArrA = new long[countA];
      int lgHTBLen = Util.exactLog2OfLong((long)hashTableB.length);
      int nonMatches = 0;

      for(int i = 0; i < countA; ++i) {
         long hash = hashArrA[i];
         if (hash != 0L && hash < minThetaLong) {
            int index = HashOperations.hashSearch(hashTableB, lgHTBLen, hash);
            if (index == -1) {
               tmpHashArrA[nonMatches] = hash;
               ++nonMatches;
            }
         }
      }

      return Arrays.copyOfRange(tmpHashArrA, 0, nonMatches);
   }

   private void reset() {
      this.thetaLong_ = Long.MAX_VALUE;
      this.empty_ = true;
      this.hashArr_ = new long[0];
      this.curCount_ = 0;
   }

   long[] getCache() {
      return (long[])this.hashArr_.clone();
   }

   short getSeedHash() {
      return this.seedHash_;
   }

   long getThetaLong() {
      return this.thetaLong_;
   }

   boolean isEmpty() {
      return this.empty_;
   }
}
