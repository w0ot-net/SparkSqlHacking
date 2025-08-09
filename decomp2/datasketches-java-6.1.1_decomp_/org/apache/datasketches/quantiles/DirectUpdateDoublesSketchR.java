package org.apache.datasketches.quantiles;

import org.apache.datasketches.common.SketchesArgumentException;
import org.apache.datasketches.common.SketchesReadOnlyException;
import org.apache.datasketches.memory.Memory;
import org.apache.datasketches.memory.WritableMemory;

class DirectUpdateDoublesSketchR extends UpdateDoublesSketch {
   static final int MIN_DIRECT_DOUBLES_SER_VER = 3;
   WritableMemory mem_;

   DirectUpdateDoublesSketchR(int k) {
      super(k);
   }

   static DirectUpdateDoublesSketchR wrapInstance(Memory srcMem) {
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
      DirectUpdateDoublesSketchR dds = new DirectUpdateDoublesSketchR(k);
      dds.mem_ = (WritableMemory)srcMem;
      return dds;
   }

   public double getMaxItem() {
      if (this.isEmpty()) {
         throw new IllegalArgumentException("The sketch must not be empty for this operation. ");
      } else {
         return this.mem_.getDouble(24L);
      }
   }

   public double getMinItem() {
      if (this.isEmpty()) {
         throw new IllegalArgumentException("The sketch must not be empty for this operation. ");
      } else {
         return this.mem_.getDouble(16L);
      }
   }

   public long getN() {
      return this.mem_.getCapacity() < 32L ? 0L : this.mem_.getLong(8L);
   }

   public boolean hasMemory() {
      return this.mem_ != null;
   }

   public boolean isDirect() {
      return this.mem_ != null ? this.mem_.isDirect() : false;
   }

   public boolean isReadOnly() {
      return true;
   }

   public boolean isSameResource(Memory that) {
      return this.mem_.isSameResource(that);
   }

   public void reset() {
      throw new SketchesReadOnlyException("Call to reset() on read-only buffer");
   }

   public void update(double dataItem) {
      throw new SketchesReadOnlyException("Call to update() on read-only buffer");
   }

   int getBaseBufferCount() {
      return ClassicUtil.computeBaseBufferItems(this.getK(), this.getN());
   }

   int getCombinedBufferItemCapacity() {
      return ((int)this.mem_.getCapacity() - 32) / 8;
   }

   double[] getCombinedBuffer() {
      int k = this.getK();
      if (this.isEmpty()) {
         return new double[k << 1];
      } else {
         long n = this.getN();
         int itemCap = ClassicUtil.computeCombinedBufferItemCapacity(k, n);
         double[] combinedBuffer = new double[itemCap];
         this.mem_.getDoubleArray(32L, combinedBuffer, 0, itemCap);
         return combinedBuffer;
      }
   }

   long getBitPattern() {
      int k = this.getK();
      long n = this.getN();
      return ClassicUtil.computeBitPattern(k, n);
   }

   WritableMemory getMemory() {
      return this.mem_;
   }

   void putMinItem(double minQuantile) {
      throw new SketchesReadOnlyException("Call to putMinQuantile() on read-only buffer");
   }

   void putMaxItem(double maxQuantile) {
      throw new SketchesReadOnlyException("Call to putMaxQuantile() on read-only buffer");
   }

   void putN(long n) {
      throw new SketchesReadOnlyException("Call to putN() on read-only buffer");
   }

   void putCombinedBuffer(double[] combinedBuffer) {
      throw new SketchesReadOnlyException("Call to putCombinedBuffer() on read-only buffer");
   }

   void putBaseBufferCount(int baseBufferCount) {
      throw new SketchesReadOnlyException("Call to putBaseBufferCount() on read-only buffer");
   }

   void putBitPattern(long bitPattern) {
      throw new SketchesReadOnlyException("Call to putBaseBufferCount() on read-only buffer");
   }

   double[] growCombinedBuffer(int curCombBufItemCap, int itemSpaceNeeded) {
      throw new SketchesReadOnlyException("Call to growCombinedBuffer() on read-only buffer");
   }

   static void checkDirectMemCapacity(int k, long n, long memCapBytes) {
      int reqBufBytes = getUpdatableStorageBytes(k, n);
      if (memCapBytes < (long)reqBufBytes) {
         throw new SketchesArgumentException("Possible corruption: Memory capacity too small: " + memCapBytes + " < " + reqBufBytes);
      }
   }

   static void checkCompact(int serVer, int flags) {
      boolean compact = serVer == 2 | (flags & 8) > 0;
      if (compact) {
         throw new SketchesArgumentException("Compact Memory is not supported for Wrap Instance.");
      }
   }

   static void checkPreLongs(int preLongs) {
      if (preLongs < 1 || preLongs > 2) {
         throw new SketchesArgumentException("Possible corruption: PreLongs must be 1 or 2: " + preLongs);
      }
   }

   static void checkDirectFlags(int flags) {
      int allowedFlags = 22;
      int flagsMask = -23;
      if ((flags & -23) > 0) {
         throw new SketchesArgumentException("Possible corruption: Invalid flags field: Cannot be compact! " + Integer.toBinaryString(flags));
      }
   }

   static void checkEmptyAndN(boolean empty, long n) {
      if (empty && n > 0L) {
         throw new SketchesArgumentException("Possible corruption: Empty Flag = true and N > 0: " + n);
      }
   }
}
