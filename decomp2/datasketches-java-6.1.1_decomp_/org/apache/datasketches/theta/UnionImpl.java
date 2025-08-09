package org.apache.datasketches.theta;

import java.nio.ByteBuffer;
import org.apache.datasketches.common.Family;
import org.apache.datasketches.common.ResizeFactor;
import org.apache.datasketches.common.SketchesArgumentException;
import org.apache.datasketches.memory.Memory;
import org.apache.datasketches.memory.MemoryRequestServer;
import org.apache.datasketches.memory.WritableMemory;
import org.apache.datasketches.thetacommon.HashOperations;
import org.apache.datasketches.thetacommon.QuickSelect;
import org.apache.datasketches.thetacommon.ThetaUtil;

final class UnionImpl extends Union {
   private final UpdateSketch gadget_;
   private final short expectedSeedHash_;
   private long unionThetaLong_;
   private boolean unionEmpty_;

   private UnionImpl(UpdateSketch gadget, long seed) {
      this.gadget_ = gadget;
      this.expectedSeedHash_ = ThetaUtil.computeSeedHash(seed);
   }

   static UnionImpl initNewHeapInstance(int lgNomLongs, long seed, float p, ResizeFactor rf) {
      UpdateSketch gadget = new HeapQuickSelectSketch(lgNomLongs, seed, p, rf, true);
      UnionImpl unionImpl = new UnionImpl(gadget, seed);
      unionImpl.unionThetaLong_ = gadget.getThetaLong();
      unionImpl.unionEmpty_ = gadget.isEmpty();
      return unionImpl;
   }

   static UnionImpl initNewDirectInstance(int lgNomLongs, long seed, float p, ResizeFactor rf, MemoryRequestServer memReqSvr, WritableMemory dstMem) {
      UpdateSketch gadget = new DirectQuickSelectSketch(lgNomLongs, seed, p, rf, memReqSvr, dstMem, true);
      UnionImpl unionImpl = new UnionImpl(gadget, seed);
      unionImpl.unionThetaLong_ = gadget.getThetaLong();
      unionImpl.unionEmpty_ = gadget.isEmpty();
      return unionImpl;
   }

   static UnionImpl heapifyInstance(Memory srcMem, long expectedSeed) {
      Family.UNION.checkFamilyID(PreambleUtil.extractFamilyID(srcMem));
      UpdateSketch gadget = HeapQuickSelectSketch.heapifyInstance(srcMem, expectedSeed);
      UnionImpl unionImpl = new UnionImpl(gadget, expectedSeed);
      unionImpl.unionThetaLong_ = PreambleUtil.extractUnionThetaLong(srcMem);
      unionImpl.unionEmpty_ = PreambleUtil.isEmptyFlag(srcMem);
      return unionImpl;
   }

   static UnionImpl fastWrap(Memory srcMem, long expectedSeed) {
      Family.UNION.checkFamilyID(PreambleUtil.extractFamilyID(srcMem));
      UpdateSketch gadget = DirectQuickSelectSketchR.fastReadOnlyWrap(srcMem, expectedSeed);
      UnionImpl unionImpl = new UnionImpl(gadget, expectedSeed);
      unionImpl.unionThetaLong_ = PreambleUtil.extractUnionThetaLong(srcMem);
      unionImpl.unionEmpty_ = PreambleUtil.isEmptyFlag(srcMem);
      return unionImpl;
   }

   static UnionImpl fastWrap(WritableMemory srcMem, long expectedSeed) {
      Family.UNION.checkFamilyID(PreambleUtil.extractFamilyID(srcMem));
      UpdateSketch gadget = DirectQuickSelectSketch.fastWritableWrap(srcMem, expectedSeed);
      UnionImpl unionImpl = new UnionImpl(gadget, expectedSeed);
      unionImpl.unionThetaLong_ = PreambleUtil.extractUnionThetaLong(srcMem);
      unionImpl.unionEmpty_ = PreambleUtil.isEmptyFlag(srcMem);
      return unionImpl;
   }

   static UnionImpl wrapInstance(Memory srcMem, long expectedSeed) {
      Family.UNION.checkFamilyID(PreambleUtil.extractFamilyID(srcMem));
      UpdateSketch gadget = DirectQuickSelectSketchR.readOnlyWrap(srcMem, expectedSeed);
      UnionImpl unionImpl = new UnionImpl(gadget, expectedSeed);
      unionImpl.unionThetaLong_ = PreambleUtil.extractUnionThetaLong(srcMem);
      unionImpl.unionEmpty_ = PreambleUtil.isEmptyFlag(srcMem);
      return unionImpl;
   }

   static UnionImpl wrapInstance(WritableMemory srcMem, long expectedSeed) {
      Family.UNION.checkFamilyID(PreambleUtil.extractFamilyID(srcMem));
      UpdateSketch gadget = DirectQuickSelectSketch.writableWrap(srcMem, expectedSeed);
      UnionImpl unionImpl = new UnionImpl(gadget, expectedSeed);
      unionImpl.unionThetaLong_ = PreambleUtil.extractUnionThetaLong(srcMem);
      unionImpl.unionEmpty_ = PreambleUtil.isEmptyFlag(srcMem);
      return unionImpl;
   }

   public int getCurrentBytes() {
      return this.gadget_.getCurrentBytes();
   }

   public int getMaxUnionBytes() {
      int lgK = this.gadget_.getLgNomLongs();
      return (16 << lgK) + (Family.UNION.getMaxPreLongs() << 3);
   }

   public CompactSketch getResult() {
      return this.getResult(true, (WritableMemory)null);
   }

   public CompactSketch getResult(boolean dstOrdered, WritableMemory dstMem) {
      int gadgetCurCount = this.gadget_.getRetainedEntries(true);
      int k = 1 << this.gadget_.getLgNomLongs();
      long[] gadgetCacheCopy = this.gadget_.hasMemory() ? this.gadget_.getCache() : (long[])this.gadget_.getCache().clone();
      long curGadgetThetaLong = this.gadget_.getThetaLong();
      long adjGadgetThetaLong = gadgetCurCount > k ? QuickSelect.selectExcludingZeros(gadgetCacheCopy, gadgetCurCount, k + 1) : curGadgetThetaLong;
      long unionThetaLong = this.gadget_.hasMemory() ? this.gadget_.getMemory().getLong(24L) : this.unionThetaLong_;
      long minThetaLong = Math.min(Math.min(curGadgetThetaLong, adjGadgetThetaLong), unionThetaLong);
      int curCountOut = minThetaLong < curGadgetThetaLong ? HashOperations.count(gadgetCacheCopy, minThetaLong) : gadgetCurCount;
      long[] compactCacheOut = CompactOperations.compactCache(gadgetCacheCopy, curCountOut, minThetaLong, dstOrdered);
      boolean empty = this.gadget_.isEmpty() && this.unionEmpty_;
      short seedHash = this.gadget_.getSeedHash();
      return CompactOperations.componentsToCompact(minThetaLong, curCountOut, seedHash, empty, true, dstOrdered, dstOrdered, dstMem, compactCacheOut);
   }

   public boolean hasMemory() {
      return this.gadget_ instanceof DirectQuickSelectSketchR ? this.gadget_.hasMemory() : false;
   }

   public boolean isDirect() {
      return this.gadget_ instanceof DirectQuickSelectSketchR ? this.gadget_.isDirect() : false;
   }

   public boolean isSameResource(Memory that) {
      return this.gadget_ instanceof DirectQuickSelectSketchR ? this.gadget_.isSameResource(that) : false;
   }

   public void reset() {
      this.gadget_.reset();
      this.unionThetaLong_ = this.gadget_.getThetaLong();
      this.unionEmpty_ = this.gadget_.isEmpty();
   }

   public byte[] toByteArray() {
      byte[] gadgetByteArr = this.gadget_.toByteArray();
      WritableMemory mem = WritableMemory.writableWrap(gadgetByteArr);
      PreambleUtil.insertUnionThetaLong(mem, this.unionThetaLong_);
      if (this.gadget_.isEmpty() != this.unionEmpty_) {
         PreambleUtil.clearEmpty(mem);
         this.unionEmpty_ = false;
      }

      return gadgetByteArr;
   }

   public CompactSketch union(Sketch sketchA, Sketch sketchB, boolean dstOrdered, WritableMemory dstMem) {
      this.reset();
      this.union(sketchA);
      this.union(sketchB);
      CompactSketch csk = this.getResult(dstOrdered, dstMem);
      this.reset();
      return csk;
   }

   public void union(Sketch sketchIn) {
      if (sketchIn != null && !sketchIn.isEmpty()) {
         ThetaUtil.checkSeedHashes(this.expectedSeedHash_, sketchIn.getSeedHash());
         if (sketchIn instanceof SingleItemSketch) {
            this.gadget_.hashUpdate(sketchIn.getCache()[0]);
         } else {
            Sketch.checkSketchAndMemoryFlags(sketchIn);
            this.unionThetaLong_ = Math.min(Math.min(this.unionThetaLong_, sketchIn.getThetaLong()), this.gadget_.getThetaLong());
            this.unionEmpty_ = false;
            int curCountIn = sketchIn.getRetainedEntries(true);
            if (curCountIn > 0) {
               if (sketchIn.isOrdered() && sketchIn instanceof CompactSketch) {
                  if (sketchIn.hasMemory()) {
                     Memory skMem = ((CompactSketch)sketchIn).getMemory();
                     int preambleLongs = skMem.getByte(0L) & 63;

                     for(int i = 0; i < curCountIn; ++i) {
                        int offsetBytes = preambleLongs + i << 3;
                        long hashIn = skMem.getLong((long)offsetBytes);
                        if (hashIn >= this.unionThetaLong_) {
                           break;
                        }

                        this.gadget_.hashUpdate(hashIn);
                     }
                  } else {
                     long[] cacheIn = sketchIn.getCache();

                     for(int i = 0; i < curCountIn; ++i) {
                        long hashIn = cacheIn[i];
                        if (hashIn >= this.unionThetaLong_) {
                           break;
                        }

                        this.gadget_.hashUpdate(hashIn);
                     }
                  }
               } else {
                  long[] cacheIn = sketchIn.getCache();
                  int arrLongs = cacheIn.length;
                  int i = 0;

                  for(int c = 0; i < arrLongs && c < curCountIn; ++i) {
                     long hashIn = cacheIn[i];
                     if (hashIn > 0L && hashIn < this.unionThetaLong_) {
                        this.gadget_.hashUpdate(hashIn);
                        ++c;
                     }
                  }
               }
            }

            this.unionThetaLong_ = Math.min(this.unionThetaLong_, this.gadget_.getThetaLong());
            if (this.gadget_.hasMemory()) {
               WritableMemory wmem = (WritableMemory)this.gadget_.getMemory();
               PreambleUtil.insertUnionThetaLong(wmem, this.unionThetaLong_);
               PreambleUtil.clearEmpty(wmem);
            }

         }
      }
   }

   public void union(Memory skMem) {
      if (skMem != null) {
         int cap = (int)skMem.getCapacity();
         if (cap >= 16) {
            int serVer = PreambleUtil.extractSerVer(skMem);
            int fam = PreambleUtil.extractFamilyID(skMem);
            if (serVer == 4) {
               ThetaUtil.checkSeedHashes(this.expectedSeedHash_, (short)PreambleUtil.extractSeedHash(skMem));
               CompactSketch csk = CompactSketch.wrap(skMem);
               this.union((Sketch)csk);
            } else if (serVer == 3) {
               if (fam >= 1 && fam <= 3) {
                  this.processVer3(skMem);
               } else {
                  throw new SketchesArgumentException("Family must be Alpha, QuickSelect, or Compact: " + Family.idToFamily(fam));
               }
            } else if (serVer == 2) {
               ThetaUtil.checkSeedHashes(this.expectedSeedHash_, (short)PreambleUtil.extractSeedHash(skMem));
               CompactSketch csk = ForwardCompatibility.heapify2to3(skMem, this.expectedSeedHash_);
               this.union((Sketch)csk);
            } else if (serVer == 1) {
               CompactSketch csk = ForwardCompatibility.heapify1to3(skMem, this.expectedSeedHash_);
               this.union((Sketch)csk);
            } else {
               throw new SketchesArgumentException("SerVer is unknown: " + serVer);
            }
         }
      }
   }

   private void processVer3(Memory skMem) {
      int preLongs = PreambleUtil.extractPreLongs(skMem);
      if (preLongs == 1) {
         if (SingleItemSketch.otherCheckForSingleItem(skMem)) {
            long hash = skMem.getLong(8L);
            this.gadget_.hashUpdate(hash);
         }
      } else {
         ThetaUtil.checkSeedHashes(this.expectedSeedHash_, (short)PreambleUtil.extractSeedHash(skMem));
         int curCountIn;
         long thetaLongIn;
         if (preLongs == 2) {
            curCountIn = PreambleUtil.extractCurCount(skMem);
            if (curCountIn == 0) {
               return;
            }

            thetaLongIn = Long.MAX_VALUE;
         } else {
            curCountIn = PreambleUtil.extractCurCount(skMem);
            thetaLongIn = PreambleUtil.extractThetaLong(skMem);
         }

         this.unionThetaLong_ = Math.min(Math.min(this.unionThetaLong_, thetaLongIn), this.gadget_.getThetaLong());
         this.unionEmpty_ = false;
         int flags = PreambleUtil.extractFlags(skMem);
         boolean ordered = (flags & 16) != 0;
         if (ordered) {
            for(int i = 0; i < curCountIn; ++i) {
               int offsetBytes = preLongs + i << 3;
               long hashIn = skMem.getLong((long)offsetBytes);
               if (hashIn >= this.unionThetaLong_) {
                  break;
               }

               this.gadget_.hashUpdate(hashIn);
            }
         } else {
            boolean compact = (flags & 8) != 0;
            int size = compact ? curCountIn : 1 << PreambleUtil.extractLgArrLongs(skMem);

            for(int i = 0; i < size; ++i) {
               int offsetBytes = preLongs + i << 3;
               long hashIn = skMem.getLong((long)offsetBytes);
               if (hashIn > 0L && hashIn < this.unionThetaLong_) {
                  this.gadget_.hashUpdate(hashIn);
               }
            }
         }

         this.unionThetaLong_ = Math.min(this.unionThetaLong_, this.gadget_.getThetaLong());
         if (this.gadget_.hasMemory()) {
            WritableMemory wmem = (WritableMemory)this.gadget_.getMemory();
            PreambleUtil.insertUnionThetaLong(wmem, this.unionThetaLong_);
            PreambleUtil.clearEmpty(wmem);
         }

      }
   }

   public void update(long datum) {
      this.gadget_.update(datum);
   }

   public void update(double datum) {
      this.gadget_.update(datum);
   }

   public void update(String datum) {
      this.gadget_.update(datum);
   }

   public void update(byte[] data) {
      this.gadget_.update(data);
   }

   public void update(ByteBuffer data) {
      this.gadget_.update(data);
   }

   public void update(char[] data) {
      this.gadget_.update(data);
   }

   public void update(int[] data) {
      this.gadget_.update(data);
   }

   public void update(long[] data) {
      this.gadget_.update(data);
   }

   long[] getCache() {
      return this.gadget_.getCache();
   }

   int getRetainedEntries() {
      return this.gadget_.getRetainedEntries(true);
   }

   short getSeedHash() {
      return this.gadget_.getSeedHash();
   }

   long getThetaLong() {
      return Math.min(this.unionThetaLong_, this.gadget_.getThetaLong());
   }

   boolean isEmpty() {
      return this.gadget_.isEmpty() && this.unionEmpty_;
   }
}
