package org.apache.datasketches.theta;

import org.apache.datasketches.common.Family;
import org.apache.datasketches.common.ResizeFactor;
import org.apache.datasketches.common.SketchesReadOnlyException;
import org.apache.datasketches.common.SuppressFBWarnings;
import org.apache.datasketches.memory.Memory;
import org.apache.datasketches.memory.WritableMemory;

class DirectQuickSelectSketchR extends UpdateSketch {
   static final double DQS_RESIZE_THRESHOLD = (double)0.9375F;
   final long seed_;
   int hashTableThreshold_;
   WritableMemory wmem_;

   DirectQuickSelectSketchR(long seed, WritableMemory wmem) {
      this.seed_ = seed;
      this.wmem_ = wmem;
   }

   static DirectQuickSelectSketchR readOnlyWrap(Memory srcMem, long seed) {
      int preambleLongs = PreambleUtil.extractPreLongs(srcMem);
      int lgNomLongs = PreambleUtil.extractLgNomLongs(srcMem);
      int lgArrLongs = PreambleUtil.extractLgArrLongs(srcMem);
      UpdateSketch.checkUnionQuickSelectFamily(srcMem, preambleLongs, lgNomLongs);
      checkMemIntegrity(srcMem, seed, preambleLongs, lgNomLongs, lgArrLongs);
      DirectQuickSelectSketchR dqssr = new DirectQuickSelectSketchR(seed, (WritableMemory)srcMem);
      dqssr.hashTableThreshold_ = getOffHeapHashTableThreshold(lgNomLongs, lgArrLongs);
      return dqssr;
   }

   static DirectQuickSelectSketchR fastReadOnlyWrap(Memory srcMem, long seed) {
      int lgNomLongs = srcMem.getByte(3L) & 255;
      int lgArrLongs = srcMem.getByte(4L) & 255;
      DirectQuickSelectSketchR dqss = new DirectQuickSelectSketchR(seed, (WritableMemory)srcMem);
      dqss.hashTableThreshold_ = getOffHeapHashTableThreshold(lgNomLongs, lgArrLongs);
      return dqss;
   }

   public int getCurrentBytes() {
      byte lgArrLongs = this.wmem_.getByte(4L);
      int preLongs = this.wmem_.getByte(0L) & 63;
      int lengthBytes = preLongs + (1 << lgArrLongs) << 3;
      return lengthBytes;
   }

   public double getEstimate() {
      int curCount = PreambleUtil.extractCurCount(this.wmem_);
      long thetaLong = PreambleUtil.extractThetaLong(this.wmem_);
      return Sketch.estimate(thetaLong, curCount);
   }

   public Family getFamily() {
      int familyID = this.wmem_.getByte(2L) & 255;
      return Family.idToFamily(familyID);
   }

   public int getRetainedEntries(boolean valid) {
      return this.wmem_.getInt(8L);
   }

   public long getThetaLong() {
      return this.isEmpty() ? Long.MAX_VALUE : this.wmem_.getLong(16L);
   }

   public boolean hasMemory() {
      return this.wmem_ != null;
   }

   public boolean isDirect() {
      return this.hasMemory() ? this.wmem_.isDirect() : false;
   }

   public boolean isEmpty() {
      return PreambleUtil.isEmptyFlag(this.wmem_);
   }

   public boolean isSameResource(Memory that) {
      return this.hasMemory() ? this.wmem_.isSameResource(that) : false;
   }

   public HashIterator iterator() {
      return new MemoryHashIterator(this.wmem_, 1 << this.getLgArrLongs(), this.getThetaLong());
   }

   public byte[] toByteArray() {
      CompactOperations.checkIllegalCurCountAndEmpty(this.isEmpty(), PreambleUtil.extractCurCount(this.wmem_));
      int lengthBytes = this.getCurrentBytes();
      byte[] byteArray = new byte[lengthBytes];
      WritableMemory mem = WritableMemory.writableWrap(byteArray);
      this.wmem_.copyTo(0L, mem, 0L, (long)lengthBytes);
      long thetaLong = CompactOperations.correctThetaOnCompact(this.isEmpty(), PreambleUtil.extractCurCount(this.wmem_), PreambleUtil.extractThetaLong(this.wmem_));
      PreambleUtil.insertThetaLong(this.wmem_, thetaLong);
      return byteArray;
   }

   public final int getLgNomLongs() {
      return PreambleUtil.extractLgNomLongs(this.wmem_);
   }

   float getP() {
      return this.wmem_.getFloat(12L);
   }

   public ResizeFactor getResizeFactor() {
      return ResizeFactor.getRF(this.getLgRF());
   }

   long getSeed() {
      return this.seed_;
   }

   public UpdateSketch rebuild() {
      throw new SketchesReadOnlyException();
   }

   public void reset() {
      throw new SketchesReadOnlyException();
   }

   long[] getCache() {
      long lgArrLongs = (long)(this.wmem_.getByte(4L) & 255);
      int preambleLongs = this.wmem_.getByte(0L) & 63;
      long[] cacheArr = new long[1 << (int)lgArrLongs];
      WritableMemory mem = WritableMemory.writableWrap(cacheArr);
      this.wmem_.copyTo((long)(preambleLongs << 3), mem, 0L, (long)(8 << (int)lgArrLongs));
      return cacheArr;
   }

   int getCompactPreambleLongs() {
      return CompactOperations.computeCompactPreLongs(this.isEmpty(), this.getRetainedEntries(true), this.getThetaLong());
   }

   int getCurrentPreambleLongs() {
      return PreambleUtil.extractPreLongs(this.wmem_);
   }

   WritableMemory getMemory() {
      return this.wmem_;
   }

   short getSeedHash() {
      return (short)PreambleUtil.extractSeedHash(this.wmem_);
   }

   boolean isDirty() {
      return false;
   }

   boolean isOutOfSpace(int numEntries) {
      return numEntries > this.hashTableThreshold_;
   }

   int getLgArrLongs() {
      return this.wmem_.getByte(4L) & 255;
   }

   int getLgRF() {
      return this.wmem_.getByte(0L) >>> 6 & 3;
   }

   UpdateReturnState hashUpdate(long hash) {
      throw new SketchesReadOnlyException();
   }

   @SuppressFBWarnings(
      value = {"DB_DUPLICATE_BRANCHES"},
      justification = "False Positive, see the code comments"
   )
   protected static final int getOffHeapHashTableThreshold(int lgNomLongs, int lgArrLongs) {
      double fraction = lgArrLongs <= lgNomLongs ? (double)0.9375F : (double)0.9375F;
      return (int)(fraction * (double)(1 << lgArrLongs));
   }
}
