package org.apache.datasketches.quantiles;

import org.apache.datasketches.common.Family;
import org.apache.datasketches.common.SketchesArgumentException;
import org.apache.datasketches.memory.MemoryRequestServer;
import org.apache.datasketches.memory.WritableMemory;

final class DirectUpdateDoublesSketch extends DirectUpdateDoublesSketchR {
   MemoryRequestServer memReqSvr = null;

   private DirectUpdateDoublesSketch(int k) {
      super(k);
   }

   static DirectUpdateDoublesSketch newInstance(int k, WritableMemory dstMem) {
      long memCap = dstMem.getCapacity();
      checkDirectMemCapacity(k, 0L, memCap);
      dstMem.putLong(0L, 0L);
      PreambleUtil.insertPreLongs(dstMem, 2);
      PreambleUtil.insertSerVer(dstMem, 3);
      PreambleUtil.insertFamilyID(dstMem, Family.QUANTILES.getID());
      PreambleUtil.insertFlags(dstMem, 4);
      PreambleUtil.insertK(dstMem, k);
      if (memCap >= 32L) {
         PreambleUtil.insertN(dstMem, 0L);
         PreambleUtil.insertMinDouble(dstMem, Double.NaN);
         PreambleUtil.insertMaxDouble(dstMem, Double.NaN);
      }

      DirectUpdateDoublesSketch dds = new DirectUpdateDoublesSketch(k);
      dds.mem_ = dstMem;
      return dds;
   }

   static DirectUpdateDoublesSketch wrapInstance(WritableMemory srcMem) {
      long memCap = srcMem.getCapacity();
      int preLongs = PreambleUtil.extractPreLongs(srcMem);
      int serVer = PreambleUtil.extractSerVer(srcMem);
      int familyID = PreambleUtil.extractFamilyID(srcMem);
      int flags = PreambleUtil.extractFlags(srcMem);
      int k = PreambleUtil.extractK(srcMem);
      boolean empty = (flags & 4) > 0;
      long n = empty ? 0L : PreambleUtil.extractN(srcMem);
      checkPreLongs(preLongs);
      ClassicUtil.checkFamilyID(familyID);
      DoublesUtil.checkDoublesSerVer(serVer, 3);
      checkDirectFlags(flags);
      ClassicUtil.checkK(k);
      checkCompact(serVer, flags);
      checkDirectMemCapacity(k, n, memCap);
      checkEmptyAndN(empty, n);
      DirectUpdateDoublesSketch dds = new DirectUpdateDoublesSketch(k);
      dds.mem_ = srcMem;
      return dds;
   }

   public boolean isReadOnly() {
      return false;
   }

   public void update(double dataItem) {
      if (!Double.isNaN(dataItem)) {
         int curBBCount = this.getBaseBufferCount();
         int newBBCount = curBBCount + 1;
         int combBufItemCap = this.getCombinedBufferItemCapacity();
         if (newBBCount > combBufItemCap) {
            this.mem_ = this.growCombinedMemBuffer(2 * this.getK());
         }

         long curN = this.getN();
         long newN = curN + 1L;
         if (curN == 0L) {
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

         this.mem_.putDouble(32L + (long)curBBCount * 8L, dataItem);
         this.mem_.putByte(3L, (byte)0);
         if (newBBCount == 2 * this.k_) {
            int curMemItemCap = this.getCombinedBufferItemCapacity();
            int itemSpaceNeeded = DoublesUpdateImpl.getRequiredItemCapacity(this.k_, newN);
            if (itemSpaceNeeded > curMemItemCap) {
               this.mem_ = this.growCombinedMemBuffer(itemSpaceNeeded);
            }

            DoublesSketchAccessor bbAccessor = DoublesSketchAccessor.wrap(this, true);
            bbAccessor.sort();
            long newBitPattern = DoublesUpdateImpl.inPlacePropagateCarry(0, (DoublesBufferAccessor)null, bbAccessor, true, this.k_, DoublesSketchAccessor.wrap(this, true), this.getBitPattern());

            assert newBitPattern == ClassicUtil.computeBitPattern(this.k_, newN);
         }

         this.putN(newN);
         this.doublesSV = null;
      }
   }

   public void reset() {
      if (this.mem_.getCapacity() >= 32L) {
         this.mem_.putByte(3L, (byte)4);
         this.mem_.putLong(8L, 0L);
         this.mem_.putDouble(16L, Double.NaN);
         this.mem_.putDouble(24L, Double.NaN);
      }

   }

   void putMinItem(double minQuantile) {
      assert this.mem_.getCapacity() >= 32L;

      this.mem_.putDouble(16L, minQuantile);
   }

   void putMaxItem(double maxQuantile) {
      assert this.mem_.getCapacity() >= 32L;

      this.mem_.putDouble(24L, maxQuantile);
   }

   void putN(long n) {
      assert this.mem_.getCapacity() >= 32L;

      this.mem_.putLong(8L, n);
   }

   void putCombinedBuffer(double[] combinedBuffer) {
      this.mem_.putDoubleArray(32L, combinedBuffer, 0, combinedBuffer.length);
   }

   void putBaseBufferCount(int baseBufferCount) {
   }

   void putBitPattern(long bitPattern) {
   }

   double[] growCombinedBuffer(int curCombBufItemCap, int itemSpaceNeeded) {
      this.mem_ = this.growCombinedMemBuffer(itemSpaceNeeded);
      double[] newCombBuf = new double[itemSpaceNeeded];
      this.mem_.getDoubleArray(32L, newCombBuf, 0, curCombBufItemCap);
      return newCombBuf;
   }

   private WritableMemory growCombinedMemBuffer(int itemSpaceNeeded) {
      long memBytes = this.mem_.getCapacity();
      int needBytes = (itemSpaceNeeded << 3) + 32;

      assert (long)needBytes > memBytes;

      this.memReqSvr = this.memReqSvr == null ? this.mem_.getMemoryRequestServer() : this.memReqSvr;
      if (this.memReqSvr == null) {
         throw new SketchesArgumentException("A request for more memory has been denied, or a default MemoryRequestServer has not been provided. Must abort. ");
      } else {
         WritableMemory newMem = this.memReqSvr.request(this.mem_, (long)needBytes);
         this.mem_.copyTo(0L, newMem, 0L, memBytes);
         this.memReqSvr.requestClose(this.mem_, newMem);
         return newMem;
      }
   }
}
