package org.apache.datasketches.kll;

import org.apache.datasketches.common.ArrayOfItemsSerDe;
import org.apache.datasketches.common.ByteArrayUtil;
import org.apache.datasketches.common.Family;
import org.apache.datasketches.common.SketchesArgumentException;
import org.apache.datasketches.memory.Memory;
import org.apache.datasketches.memory.MemoryRequestServer;
import org.apache.datasketches.memory.WritableMemory;

class KllDirectDoublesSketch extends KllDoublesSketch {
   private WritableMemory wmem;
   private MemoryRequestServer memReqSvr;

   KllDirectDoublesSketch(KllSketch.SketchStructure sketchStructure, WritableMemory wmem, MemoryRequestServer memReqSvr, KllMemoryValidate memVal) {
      super(sketchStructure);
      this.wmem = wmem;
      this.memReqSvr = memReqSvr;
      this.readOnly = wmem != null && wmem.isReadOnly() || sketchStructure != KllSketch.SketchStructure.UPDATABLE;
      this.levelsArr = memVal.levelsArr;
   }

   static KllDirectDoublesSketch newDirectUpdatableInstance(int k, int m, WritableMemory dstMem, MemoryRequestServer memReqSvr) {
      KllPreambleUtil.setMemoryPreInts(dstMem, KllSketch.SketchStructure.UPDATABLE.getPreInts());
      KllPreambleUtil.setMemorySerVer(dstMem, KllSketch.SketchStructure.UPDATABLE.getSerVer());
      KllPreambleUtil.setMemoryFamilyID(dstMem, Family.KLL.getID());
      KllPreambleUtil.setMemoryK(dstMem, k);
      KllPreambleUtil.setMemoryM(dstMem, m);
      KllPreambleUtil.setMemoryN(dstMem, 0L);
      KllPreambleUtil.setMemoryMinK(dstMem, k);
      KllPreambleUtil.setMemoryNumLevels(dstMem, 1);
      int offset = 20;
      dstMem.putIntArray((long)offset, new int[]{k, k}, 0, 2);
      offset += 8;
      dstMem.putDoubleArray((long)offset, new double[]{Double.NaN, Double.NaN}, 0, 2);
      offset += 16;
      dstMem.putDoubleArray((long)offset, new double[k], 0, k);
      KllMemoryValidate memVal = new KllMemoryValidate(dstMem, KllSketch.SketchType.DOUBLES_SKETCH, (ArrayOfItemsSerDe)null);
      return new KllDirectDoublesSketch(KllSketch.SketchStructure.UPDATABLE, dstMem, memReqSvr, memVal);
   }

   String getItemAsString(int index) {
      return this.isEmpty() ? "NaN" : Double.toString(this.getDoubleItemsArray()[index]);
   }

   public int getK() {
      return KllPreambleUtil.getMemoryK(this.wmem);
   }

   public double getMaxItem() {
      if (this.sketchStructure != KllSketch.SketchStructure.COMPACT_EMPTY && !this.isEmpty()) {
         if (this.sketchStructure == KllSketch.SketchStructure.COMPACT_SINGLE) {
            return this.getDoubleSingleItem();
         } else {
            int offset = 20 + this.getLevelsArrSizeBytes(this.sketchStructure) + 8;
            return this.wmem.getDouble((long)offset);
         }
      } else {
         throw new SketchesArgumentException("The sketch must not be empty for this operation. ");
      }
   }

   double getMaxItemInternal() {
      if (this.sketchStructure != KllSketch.SketchStructure.COMPACT_EMPTY && !this.isEmpty()) {
         if (this.sketchStructure == KllSketch.SketchStructure.COMPACT_SINGLE) {
            return this.getDoubleSingleItem();
         } else {
            int offset = 20 + this.getLevelsArrSizeBytes(this.sketchStructure) + 8;
            return this.wmem.getDouble((long)offset);
         }
      } else {
         return Double.NaN;
      }
   }

   String getMaxItemAsString() {
      double maxItem = this.getMaxItemInternal();
      return Double.toString(maxItem);
   }

   public double getMinItem() {
      if (this.sketchStructure != KllSketch.SketchStructure.COMPACT_EMPTY && !this.isEmpty()) {
         if (this.sketchStructure == KllSketch.SketchStructure.COMPACT_SINGLE) {
            return this.getDoubleSingleItem();
         } else {
            int offset = 20 + this.getLevelsArrSizeBytes(this.sketchStructure);
            return this.wmem.getDouble((long)offset);
         }
      } else {
         throw new SketchesArgumentException("The sketch must not be empty for this operation. ");
      }
   }

   double getMinItemInternal() {
      if (this.sketchStructure != KllSketch.SketchStructure.COMPACT_EMPTY && !this.isEmpty()) {
         if (this.sketchStructure == KllSketch.SketchStructure.COMPACT_SINGLE) {
            return this.getDoubleSingleItem();
         } else {
            int offset = 20 + this.getLevelsArrSizeBytes(this.sketchStructure);
            return this.wmem.getDouble((long)offset);
         }
      } else {
         return Double.NaN;
      }
   }

   String getMinItemAsString() {
      double minItem = this.getMinItemInternal();
      return Double.toString(minItem);
   }

   void setMaxItem(double item) {
      if (this.readOnly) {
         throw new SketchesArgumentException("Target sketch is Read Only, cannot write. ");
      } else {
         int offset = 20 + this.getLevelsArrSizeBytes(this.sketchStructure) + 8;
         this.wmem.putDouble((long)offset, item);
      }
   }

   void setMinItem(double item) {
      if (this.readOnly) {
         throw new SketchesArgumentException("Target sketch is Read Only, cannot write. ");
      } else {
         int offset = 20 + this.getLevelsArrSizeBytes(this.sketchStructure);
         this.wmem.putDouble((long)offset, item);
      }
   }

   public long getN() {
      if (this.sketchStructure == KllSketch.SketchStructure.COMPACT_EMPTY) {
         return 0L;
      } else {
         return this.sketchStructure == KllSketch.SketchStructure.COMPACT_SINGLE ? 1L : KllPreambleUtil.getMemoryN(this.wmem);
      }
   }

   double[] getDoubleItemsArray() {
      int k = this.getK();
      if (this.sketchStructure == KllSketch.SketchStructure.COMPACT_EMPTY) {
         return new double[k];
      } else if (this.sketchStructure == KllSketch.SketchStructure.COMPACT_SINGLE) {
         double[] itemsArr = new double[k];
         itemsArr[k - 1] = this.getDoubleSingleItem();
         return itemsArr;
      } else {
         int capacityItems = KllHelper.computeTotalItemCapacity(k, this.getM(), this.getNumLevels());
         double[] doubleItemsArr = new double[capacityItems];
         int offset = 20 + this.getLevelsArrSizeBytes(this.sketchStructure) + 16;
         int shift = this.sketchStructure == KllSketch.SketchStructure.COMPACT_FULL ? this.levelsArr[0] : 0;
         int numItems = this.sketchStructure == KllSketch.SketchStructure.COMPACT_FULL ? this.getNumRetained() : capacityItems;
         this.wmem.getDoubleArray((long)offset, doubleItemsArr, shift, numItems);
         return doubleItemsArr;
      }
   }

   double[] getDoubleRetainedItemsArray() {
      if (this.sketchStructure == KllSketch.SketchStructure.COMPACT_EMPTY) {
         return new double[0];
      } else if (this.sketchStructure == KllSketch.SketchStructure.COMPACT_SINGLE) {
         return new double[]{this.getDoubleSingleItem()};
      } else {
         int numRetained = this.getNumRetained();
         double[] doubleItemsArr = new double[numRetained];
         int offset = 20 + this.getLevelsArrSizeBytes(this.sketchStructure) + 16 + (this.sketchStructure == KllSketch.SketchStructure.COMPACT_FULL ? 0 : this.levelsArr[0] * 8);
         this.wmem.getDoubleArray((long)offset, doubleItemsArr, 0, numRetained);
         return doubleItemsArr;
      }
   }

   double getDoubleSingleItem() {
      if (!this.isSingleItem()) {
         throw new SketchesArgumentException("Sketch does not have just one item. ");
      } else if (this.sketchStructure == KllSketch.SketchStructure.COMPACT_SINGLE) {
         return this.wmem.getDouble(8L);
      } else {
         int offset;
         if (this.sketchStructure == KllSketch.SketchStructure.COMPACT_FULL) {
            offset = 20 + this.getLevelsArrSizeBytes(this.sketchStructure) + 16;
         } else {
            offset = 20 + this.getLevelsArrSizeBytes(this.sketchStructure) + (2 + this.getK() - 1) * 8;
         }

         return this.wmem.getDouble((long)offset);
      }
   }

   int getM() {
      return KllPreambleUtil.getMemoryM(this.wmem);
   }

   MemoryRequestServer getMemoryRequestServer() {
      return this.memReqSvr;
   }

   int getMinK() {
      return this.sketchStructure != KllSketch.SketchStructure.COMPACT_FULL && this.sketchStructure != KllSketch.SketchStructure.UPDATABLE ? this.getK() : KllPreambleUtil.getMemoryMinK(this.wmem);
   }

   byte[] getMinMaxByteArr() {
      byte[] bytesOut = new byte[16];
      if (this.sketchStructure == KllSketch.SketchStructure.COMPACT_EMPTY) {
         ByteArrayUtil.putDoubleLE(bytesOut, 0, Double.NaN);
         ByteArrayUtil.putDoubleLE(bytesOut, 8, Double.NaN);
         return bytesOut;
      } else if (this.sketchStructure == KllSketch.SketchStructure.COMPACT_SINGLE) {
         int offset = 8;
         this.wmem.getByteArray((long)offset, bytesOut, 0, 8);
         ByteArrayUtil.copyBytes(bytesOut, 0, bytesOut, 8, 8);
         return bytesOut;
      } else {
         int offset = 20 + this.getLevelsArrSizeBytes(this.sketchStructure);
         this.wmem.getByteArray((long)offset, bytesOut, 0, 8);
         this.wmem.getByteArray((long)(offset + 8), bytesOut, 8, 8);
         return bytesOut;
      }
   }

   byte[] getRetainedItemsByteArr() {
      if (this.sketchStructure == KllSketch.SketchStructure.COMPACT_EMPTY) {
         return new byte[0];
      } else {
         double[] dblArr = this.getDoubleRetainedItemsArray();
         byte[] dblByteArr = new byte[dblArr.length * 8];
         WritableMemory wmem2 = WritableMemory.writableWrap(dblByteArr);
         wmem2.putDoubleArray(0L, dblArr, 0, dblArr.length);
         return dblByteArr;
      }
   }

   byte[] getTotalItemsByteArr() {
      double[] dblArr = this.getDoubleItemsArray();
      byte[] dblByteArr = new byte[dblArr.length * 8];
      WritableMemory wmem2 = WritableMemory.writableWrap(dblByteArr);
      wmem2.putDoubleArray(0L, dblArr, 0, dblArr.length);
      return dblByteArr;
   }

   WritableMemory getWritableMemory() {
      return this.wmem;
   }

   void incN(int increment) {
      if (this.readOnly) {
         throw new SketchesArgumentException("Target sketch is Read Only, cannot write. ");
      } else {
         KllPreambleUtil.setMemoryN(this.wmem, KllPreambleUtil.getMemoryN(this.wmem) + (long)increment);
      }
   }

   void incNumLevels() {
      if (this.readOnly) {
         throw new SketchesArgumentException("Target sketch is Read Only, cannot write. ");
      } else {
         int numLevels = KllPreambleUtil.getMemoryNumLevels(this.wmem);
         ++numLevels;
         KllPreambleUtil.setMemoryNumLevels(this.wmem, numLevels);
      }
   }

   boolean isLevelZeroSorted() {
      return KllPreambleUtil.getMemoryLevelZeroSortedFlag(this.wmem);
   }

   void setDoubleItemsArray(double[] doubleItems) {
      if (this.readOnly) {
         throw new SketchesArgumentException("Target sketch is Read Only, cannot write. ");
      } else {
         int offset = 20 + this.getLevelsArrSizeBytes(this.sketchStructure) + 16;
         this.wmem.putDoubleArray((long)offset, doubleItems, 0, doubleItems.length);
      }
   }

   void setDoubleItemsArrayAt(int index, double item) {
      if (this.readOnly) {
         throw new SketchesArgumentException("Target sketch is Read Only, cannot write. ");
      } else {
         int offset = 20 + this.getLevelsArrSizeBytes(this.sketchStructure) + (index + 2) * 8;
         this.wmem.putDouble((long)offset, item);
      }
   }

   void setDoubleItemsArrayAt(int index, double[] items, int srcOffset, int length) {
      if (this.readOnly) {
         throw new SketchesArgumentException("Target sketch is Read Only, cannot write. ");
      } else {
         int offset = 20 + this.getLevelsArrSizeBytes(this.sketchStructure) + (index + 2) * 8;
         this.wmem.putDoubleArray((long)offset, items, srcOffset, length);
      }
   }

   void setLevelZeroSorted(boolean sorted) {
      if (this.readOnly) {
         throw new SketchesArgumentException("Target sketch is Read Only, cannot write. ");
      } else {
         KllPreambleUtil.setMemoryLevelZeroSortedFlag(this.wmem, sorted);
      }
   }

   void setMinK(int minK) {
      if (this.readOnly) {
         throw new SketchesArgumentException("Target sketch is Read Only, cannot write. ");
      } else {
         KllPreambleUtil.setMemoryMinK(this.wmem, minK);
      }
   }

   void setN(long n) {
      if (this.readOnly) {
         throw new SketchesArgumentException("Target sketch is Read Only, cannot write. ");
      } else {
         KllPreambleUtil.setMemoryN(this.wmem, n);
      }
   }

   void setNumLevels(int numLevels) {
      if (this.readOnly) {
         throw new SketchesArgumentException("Target sketch is Read Only, cannot write. ");
      } else {
         KllPreambleUtil.setMemoryNumLevels(this.wmem, numLevels);
      }
   }

   void setWritableMemory(WritableMemory wmem) {
      this.wmem = wmem;
   }

   static final class KllDirectCompactDoublesSketch extends KllDirectDoublesSketch {
      KllDirectCompactDoublesSketch(KllSketch.SketchStructure sketchStructure, Memory srcMem, KllMemoryValidate memVal) {
         super(sketchStructure, (WritableMemory)srcMem, (MemoryRequestServer)null, memVal);
      }
   }
}
