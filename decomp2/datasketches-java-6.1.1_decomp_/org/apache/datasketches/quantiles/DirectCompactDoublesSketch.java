package org.apache.datasketches.quantiles;

import java.util.Arrays;
import org.apache.datasketches.common.Family;
import org.apache.datasketches.common.SketchesArgumentException;
import org.apache.datasketches.memory.Memory;
import org.apache.datasketches.memory.WritableMemory;

final class DirectCompactDoublesSketch extends CompactDoublesSketch {
   private static final int MIN_DIRECT_DOUBLES_SER_VER = 3;
   private WritableMemory mem_;

   private DirectCompactDoublesSketch(int k) {
      super(k);
   }

   static DirectCompactDoublesSketch createFromUpdateSketch(UpdateDoublesSketch sketch, WritableMemory dstMem) {
      long memCap = dstMem.getCapacity();
      int k = sketch.getK();
      long n = sketch.getN();
      checkDirectMemCapacity(k, n, memCap);
      dstMem.putLong(0L, 0L);
      PreambleUtil.insertPreLongs(dstMem, 2);
      PreambleUtil.insertSerVer(dstMem, 3);
      PreambleUtil.insertFamilyID(dstMem, Family.QUANTILES.getID());
      PreambleUtil.insertK(dstMem, k);
      int flags = 10;
      if (sketch.isEmpty()) {
         PreambleUtil.insertFlags(dstMem, 14);
      } else {
         PreambleUtil.insertFlags(dstMem, 10);
         PreambleUtil.insertN(dstMem, n);
         PreambleUtil.insertMinDouble(dstMem, sketch.getMinItem());
         PreambleUtil.insertMaxDouble(dstMem, sketch.getMaxItem());
         int bbCount = ClassicUtil.computeBaseBufferItems(k, n);
         DoublesSketchAccessor inputAccessor = DoublesSketchAccessor.wrap(sketch);

         assert bbCount == inputAccessor.numItems();

         long dstMemOffset = 32L;
         double[] bbArray = inputAccessor.getArray(0, bbCount);
         Arrays.sort(bbArray);
         dstMem.putDoubleArray(dstMemOffset, bbArray, 0, bbCount);
         dstMemOffset += (long)(bbCount << 3);
         long bitPattern = ClassicUtil.computeBitPattern(k, n);

         for(int lvl = 0; bitPattern > 0L; bitPattern >>>= 1) {
            if ((bitPattern & 1L) > 0L) {
               inputAccessor.setLevel(lvl);
               dstMem.putDoubleArray(dstMemOffset, inputAccessor.getArray(0, k), 0, k);
               dstMemOffset += (long)(k << 3);
            }

            ++lvl;
         }
      }

      DirectCompactDoublesSketch dcds = new DirectCompactDoublesSketch(k);
      dcds.mem_ = dstMem;
      return dcds;
   }

   static DirectCompactDoublesSketch wrapInstance(Memory srcMem) {
      long memCap = srcMem.getCapacity();
      int preLongs = PreambleUtil.extractPreLongs(srcMem);
      int serVer = PreambleUtil.extractSerVer(srcMem);
      int familyID = PreambleUtil.extractFamilyID(srcMem);
      int flags = PreambleUtil.extractFlags(srcMem);
      int k = PreambleUtil.extractK(srcMem);
      boolean empty = (flags & 4) > 0;
      long n = empty ? 0L : PreambleUtil.extractN(srcMem);
      DirectUpdateDoublesSketchR.checkPreLongs(preLongs);
      ClassicUtil.checkFamilyID(familyID);
      DoublesUtil.checkDoublesSerVer(serVer, 3);
      checkCompact(serVer, flags);
      ClassicUtil.checkK(k);
      checkDirectMemCapacity(k, n, memCap);
      DirectUpdateDoublesSketchR.checkEmptyAndN(empty, n);
      DirectCompactDoublesSketch dds = new DirectCompactDoublesSketch(k);
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

   public boolean isSameResource(Memory that) {
      return this.mem_.isSameResource(that);
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
         int itemCap = ClassicUtil.computeRetainedItems(k, n);
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

   static void checkDirectMemCapacity(int k, long n, long memCapBytes) {
      int reqBufBytes = getCompactSerialiedSizeBytes(k, n);
      if (memCapBytes < (long)reqBufBytes) {
         throw new SketchesArgumentException("Possible corruption: Memory capacity too small: " + memCapBytes + " < " + reqBufBytes);
      }
   }

   static void checkCompact(int serVer, int flags) {
      int compactFlagMask = 24;
      if (serVer != 2 && (flags & 4) == 0 && (flags & 24) != 24) {
         throw new SketchesArgumentException("Possible corruption: Must be v2, empty, or compact and ordered. Flags field: " + Integer.toBinaryString(flags) + ", SerVer: " + serVer);
      }
   }
}
