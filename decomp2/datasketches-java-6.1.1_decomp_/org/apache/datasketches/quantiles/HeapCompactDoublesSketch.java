package org.apache.datasketches.quantiles;

import java.util.Arrays;
import org.apache.datasketches.common.SketchesArgumentException;
import org.apache.datasketches.memory.Memory;
import org.apache.datasketches.memory.WritableMemory;

final class HeapCompactDoublesSketch extends CompactDoublesSketch {
   static final int MIN_HEAP_DOUBLES_SER_VER = 1;
   private double minItem_;
   private double maxItem_;
   private long n_;
   private int baseBufferCount_;
   private long bitPattern_;
   private double[] combinedBuffer_;

   private HeapCompactDoublesSketch(int k) {
      super(k);
   }

   static HeapCompactDoublesSketch createFromUpdateSketch(UpdateDoublesSketch sketch) {
      int k = sketch.getK();
      long n = sketch.getN();
      HeapCompactDoublesSketch hcds = new HeapCompactDoublesSketch(k);
      hcds.n_ = n;
      hcds.bitPattern_ = ClassicUtil.computeBitPattern(k, n);

      assert hcds.bitPattern_ == sketch.getBitPattern();

      hcds.minItem_ = sketch.isEmpty() ? Double.NaN : sketch.getMinItem();
      hcds.maxItem_ = sketch.isEmpty() ? Double.NaN : sketch.getMaxItem();
      hcds.baseBufferCount_ = ClassicUtil.computeBaseBufferItems(k, n);

      assert hcds.baseBufferCount_ == sketch.getBaseBufferCount();

      int retainedItems = ClassicUtil.computeRetainedItems(k, n);
      double[] combinedBuffer = new double[retainedItems];
      DoublesSketchAccessor accessor = DoublesSketchAccessor.wrap(sketch);

      assert hcds.baseBufferCount_ == accessor.numItems();

      System.arraycopy(accessor.getArray(0, hcds.baseBufferCount_), 0, combinedBuffer, 0, hcds.baseBufferCount_);
      Arrays.sort(combinedBuffer, 0, hcds.baseBufferCount_);
      int combinedBufferOffset = hcds.baseBufferCount_;
      long bitPattern = hcds.bitPattern_;

      for(int lvl = 0; bitPattern > 0L; bitPattern >>>= 1) {
         if ((bitPattern & 1L) > 0L) {
            accessor.setLevel(lvl);
            System.arraycopy(accessor.getArray(0, k), 0, combinedBuffer, combinedBufferOffset, k);
            combinedBufferOffset += k;
         }

         ++lvl;
      }

      hcds.combinedBuffer_ = combinedBuffer;
      return hcds;
   }

   static HeapCompactDoublesSketch heapifyInstance(Memory srcMem) {
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
         HeapUpdateDoublesSketch.checkPreLongsFlagsSerVer(flags, serVer, preLongs);
         ClassicUtil.checkFamilyID(familyID);
         HeapCompactDoublesSketch hds = new HeapCompactDoublesSketch(k);
         if (empty) {
            hds.n_ = 0L;
            hds.combinedBuffer_ = null;
            hds.baseBufferCount_ = 0;
            hds.bitPattern_ = 0L;
            hds.minItem_ = Double.NaN;
            hds.maxItem_ = Double.NaN;
            return hds;
         } else {
            boolean srcIsCompact = serVer == 2 | (flags & 10) > 0;
            HeapUpdateDoublesSketch.checkHeapMemCapacity(k, n, srcIsCompact, serVer, memCapBytes);
            hds.n_ = n;
            hds.baseBufferCount_ = ClassicUtil.computeBaseBufferItems(k, n);
            hds.bitPattern_ = ClassicUtil.computeBitPattern(k, n);
            hds.minItem_ = srcMem.getDouble(16L);
            hds.maxItem_ = srcMem.getDouble(24L);
            int totItems = ClassicUtil.computeRetainedItems(k, n);
            hds.srcMemoryToCombinedBuffer(srcMem, serVer, srcIsCompact, totItems);
            return hds;
         }
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

   public double getMinItem() {
      if (this.isEmpty()) {
         throw new IllegalArgumentException("The sketch must not be empty for this operation. ");
      } else {
         return this.minItem_;
      }
   }

   public double getMaxItem() {
      if (this.isEmpty()) {
         throw new IllegalArgumentException("The sketch must not be empty for this operation. ");
      } else {
         return this.maxItem_;
      }
   }

   private void srcMemoryToCombinedBuffer(Memory srcMem, int serVer, boolean srcIsCompact, int combBufCap) {
      int preLongs = 2;
      int extra = serVer == 1 ? 3 : 2;
      int preBytes = 2 + extra << 3;
      int k = this.getK();
      this.combinedBuffer_ = new double[combBufCap];
      if (srcIsCompact) {
         srcMem.getDoubleArray((long)preBytes, this.combinedBuffer_, 0, combBufCap);
         if (serVer == 2) {
            Arrays.sort(this.combinedBuffer_, 0, this.baseBufferCount_);
         }
      } else {
         srcMem.getDoubleArray((long)preBytes, this.combinedBuffer_, 0, this.baseBufferCount_);
         Arrays.sort(this.combinedBuffer_, 0, this.baseBufferCount_);
         int srcOffset = preBytes + (2 * k << 3);
         int dstOffset = this.baseBufferCount_;

         for(long bitPattern = this.bitPattern_; bitPattern != 0L; bitPattern >>>= 1) {
            if ((bitPattern & 1L) > 0L) {
               srcMem.getDoubleArray((long)srcOffset, this.combinedBuffer_, dstOffset, k);
               dstOffset += k;
            }

            srcOffset += k << 3;
         }
      }

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
}
