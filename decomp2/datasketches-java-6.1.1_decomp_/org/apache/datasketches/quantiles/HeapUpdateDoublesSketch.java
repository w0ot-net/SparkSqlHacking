package org.apache.datasketches.quantiles;

import java.util.Arrays;
import org.apache.datasketches.common.Family;
import org.apache.datasketches.common.SketchesArgumentException;
import org.apache.datasketches.memory.Memory;
import org.apache.datasketches.memory.WritableMemory;

final class HeapUpdateDoublesSketch extends UpdateDoublesSketch {
   static final int MIN_HEAP_DOUBLES_SER_VER = 1;
   private double minItem_;
   private double maxItem_;
   private long n_;
   private int baseBufferCount_;
   private long bitPattern_;
   private double[] combinedBuffer_;

   private HeapUpdateDoublesSketch(int k) {
      super(k);
   }

   static HeapUpdateDoublesSketch newInstance(int k) {
      HeapUpdateDoublesSketch hqs = new HeapUpdateDoublesSketch(k);
      int baseBufAlloc = 2 * Math.min(2, k);
      hqs.n_ = 0L;
      hqs.combinedBuffer_ = new double[baseBufAlloc];
      hqs.baseBufferCount_ = 0;
      hqs.bitPattern_ = 0L;
      hqs.minItem_ = Double.NaN;
      hqs.maxItem_ = Double.NaN;
      return hqs;
   }

   static HeapUpdateDoublesSketch heapifyInstance(Memory srcMem) {
      long memCapBytes = srcMem.getCapacity();
      if (memCapBytes < 8L) {
         throw new SketchesArgumentException("Source Memory too small: " + memCapBytes + " < 8");
      } else {
         int preLongs = PreambleUtil.extractPreLongs(srcMem);
         int serVer = PreambleUtil.extractSerVer(srcMem);
         int familyID = PreambleUtil.extractFamilyID(srcMem);
         int flags = PreambleUtil.extractFlags(srcMem);
         int k = PreambleUtil.extractK(srcMem);
         boolean empty = (flags & 4) > 0;
         long n = empty ? 0L : PreambleUtil.extractN(srcMem);
         DoublesUtil.checkDoublesSerVer(serVer, 1);
         ClassicUtil.checkHeapFlags(flags);
         checkPreLongsFlagsSerVer(flags, serVer, preLongs);
         ClassicUtil.checkFamilyID(familyID);
         HeapUpdateDoublesSketch hds = newInstance(k);
         if (empty) {
            return hds;
         } else {
            boolean srcIsCompact = serVer == 2 | (flags & 8) > 0;
            checkHeapMemCapacity(k, n, srcIsCompact, serVer, memCapBytes);
            hds.n_ = n;
            int combBufCap = ClassicUtil.computeCombinedBufferItemCapacity(k, n);
            hds.baseBufferCount_ = ClassicUtil.computeBaseBufferItems(k, n);
            hds.bitPattern_ = ClassicUtil.computeBitPattern(k, n);
            hds.srcMemoryToCombinedBuffer(srcMem, serVer, srcIsCompact, combBufCap);
            return hds;
         }
      }
   }

   public double getMaxItem() {
      if (this.isEmpty()) {
         throw new IllegalArgumentException("The sketch must not be empty for this operation. ");
      } else {
         return this.maxItem_;
      }
   }

   public double getMinItem() {
      if (this.isEmpty()) {
         throw new IllegalArgumentException("The sketch must not be empty for this operation. ");
      } else {
         return this.minItem_;
      }
   }

   public long getN() {
      return this.n_;
   }

   public boolean hasMemory() {
      return false;
   }

   public boolean isDirect() {
      return false;
   }

   public boolean isReadOnly() {
      return false;
   }

   public void reset() {
      this.n_ = 0L;
      int combinedBufferItemCapacity = 2 * Math.min(2, this.k_);
      this.combinedBuffer_ = new double[combinedBufferItemCapacity];
      this.baseBufferCount_ = 0;
      this.bitPattern_ = 0L;
      this.minItem_ = Double.NaN;
      this.maxItem_ = Double.NaN;
   }

   public void update(double dataItem) {
      if (!Double.isNaN(dataItem)) {
         if (this.n_ == 0L) {
            this.putMaxItem(dataItem);
            this.putMinItem(dataItem);
         } else {
            if (dataItem > this.getMaxItem()) {
               this.putMaxItem(dataItem);
            }

            if (dataItem < this.getMinItem()) {
               this.putMinItem(dataItem);
            }
         }

         int curBBCount = this.baseBufferCount_;
         int newBBCount = curBBCount + 1;
         long newN = this.n_ + 1L;
         int combBufItemCap = this.combinedBuffer_.length;
         if (newBBCount > combBufItemCap) {
            this.growBaseBuffer();
         }

         this.combinedBuffer_[curBBCount] = dataItem;
         if (newBBCount == this.k_ << 1) {
            int spaceNeeded = DoublesUpdateImpl.getRequiredItemCapacity(this.k_, newN);
            if (spaceNeeded > combBufItemCap) {
               this.growCombinedBuffer(combBufItemCap, spaceNeeded);
            }

            DoublesSketchAccessor bbAccessor = DoublesSketchAccessor.wrap(this, true);
            bbAccessor.sort();
            long newBitPattern = DoublesUpdateImpl.inPlacePropagateCarry(0, (DoublesBufferAccessor)null, bbAccessor, true, this.k_, DoublesSketchAccessor.wrap(this, true), this.bitPattern_);

            assert newBitPattern == ClassicUtil.computeBitPattern(this.k_, newN);

            assert newBitPattern == this.bitPattern_ + 1L;

            this.bitPattern_ = newBitPattern;
            this.baseBufferCount_ = 0;
         } else {
            this.baseBufferCount_ = newBBCount;
         }

         this.n_ = newN;
         this.doublesSV = null;
      }
   }

   private void srcMemoryToCombinedBuffer(Memory srcMem, int serVer, boolean srcIsCompact, int combBufCap) {
      int preLongs = 2;
      int extra = serVer == 1 ? 3 : 2;
      int preBytes = 2 + extra << 3;
      int bbCnt = this.baseBufferCount_;
      int k = this.getK();
      long n = this.getN();
      double[] combinedBuffer = new double[combBufCap];
      this.putMinItem(srcMem.getDouble(16L));
      this.putMaxItem(srcMem.getDouble(24L));
      if (srcIsCompact) {
         srcMem.getDoubleArray((long)preBytes, combinedBuffer, 0, bbCnt);
         long bitPattern = this.bitPattern_;
         if (bitPattern != 0L) {
            long memOffset = (long)(preBytes + (bbCnt << 3));

            for(int combBufOffset = 2 * k; bitPattern != 0L; bitPattern >>>= 1) {
               if ((bitPattern & 1L) > 0L) {
                  srcMem.getDoubleArray(memOffset, combinedBuffer, combBufOffset, k);
                  memOffset += (long)(k << 3);
               }

               combBufOffset += k;
            }
         }
      } else {
         int levels = ClassicUtil.computeNumLevelsNeeded(k, n);
         int totItems = levels == 0 ? bbCnt : (2 + levels) * k;
         srcMem.getDoubleArray((long)preBytes, combinedBuffer, 0, totItems);
      }

      this.putCombinedBuffer(combinedBuffer);
   }

   int getBaseBufferCount() {
      return this.baseBufferCount_;
   }

   int getCombinedBufferItemCapacity() {
      return this.combinedBuffer_.length;
   }

   double[] getCombinedBuffer() {
      return this.combinedBuffer_;
   }

   long getBitPattern() {
      return this.bitPattern_;
   }

   WritableMemory getMemory() {
      return null;
   }

   void putMinItem(double minItem) {
      this.minItem_ = minItem;
   }

   void putMaxItem(double maxItem) {
      this.maxItem_ = maxItem;
   }

   void putN(long n) {
      this.n_ = n;
   }

   void putCombinedBuffer(double[] combinedBuffer) {
      this.combinedBuffer_ = combinedBuffer;
   }

   void putBaseBufferCount(int baseBufferCount) {
      this.baseBufferCount_ = baseBufferCount;
   }

   void putBitPattern(long bitPattern) {
      this.bitPattern_ = bitPattern;
   }

   double[] growCombinedBuffer(int currentSpace, int spaceNeeded) {
      this.combinedBuffer_ = Arrays.copyOf(this.combinedBuffer_, spaceNeeded);
      return this.combinedBuffer_;
   }

   private void growBaseBuffer() {
      int oldSize = this.combinedBuffer_.length;

      assert oldSize < 2 * this.k_;

      double[] baseBuffer = this.combinedBuffer_;
      int newSize = 2 * Math.max(Math.min(this.k_, oldSize), 2);
      this.combinedBuffer_ = Arrays.copyOf(baseBuffer, newSize);
   }

   static void checkPreLongsFlagsSerVer(int flags, int serVer, int preLongs) {
      boolean empty = (flags & 4) > 0;
      boolean compact = (flags & 8) > 0;
      int sw = (compact ? 1 : 0) + 2 * (empty ? 1 : 0) + 4 * (serVer & 15) + 32 * (preLongs & 63);
      boolean valid = true;
      switch (sw) {
         default:
            valid = false;
         case 38:
         case 42:
         case 46:
         case 47:
         case 72:
         case 76:
         case 77:
         case 78:
         case 79:
         case 164:
            if (!valid) {
               throw new SketchesArgumentException("Possible corruption. Inconsistent state: PreambleLongs = " + preLongs + ", empty = " + empty + ", SerVer = " + serVer + ", Compact = " + compact);
            }
      }
   }

   static void checkHeapMemCapacity(int k, long n, boolean compact, int serVer, long memCapBytes) {
      int metaPre = Family.QUANTILES.getMaxPreLongs() + (serVer == 1 ? 3 : 2);
      int retainedItems = ClassicUtil.computeRetainedItems(k, n);
      int reqBufBytes;
      if (compact) {
         reqBufBytes = metaPre + retainedItems << 3;
      } else {
         int totLevels = ClassicUtil.computeNumLevelsNeeded(k, n);
         reqBufBytes = totLevels == 0 ? metaPre + retainedItems << 3 : metaPre + (2 + totLevels) * k << 3;
      }

      if (memCapBytes < (long)reqBufBytes) {
         throw new SketchesArgumentException("Possible corruption: Memory capacity too small: " + memCapBytes + " < " + reqBufBytes);
      }
   }
}
