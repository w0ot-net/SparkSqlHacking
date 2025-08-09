package org.apache.datasketches.kll;

import org.apache.datasketches.common.ArrayOfItemsSerDe;
import org.apache.datasketches.common.ByteArrayUtil;
import org.apache.datasketches.common.Family;
import org.apache.datasketches.common.SketchesArgumentException;
import org.apache.datasketches.memory.Memory;
import org.apache.datasketches.memory.MemoryRequestServer;
import org.apache.datasketches.memory.WritableMemory;

class KllDirectFloatsSketch extends KllFloatsSketch {
   private WritableMemory wmem;
   private MemoryRequestServer memReqSvr;

   KllDirectFloatsSketch(KllSketch.SketchStructure sketchStructure, WritableMemory wmem, MemoryRequestServer memReqSvr, KllMemoryValidate memVal) {
      super(sketchStructure);
      this.wmem = wmem;
      this.memReqSvr = memReqSvr;
      this.readOnly = wmem != null && wmem.isReadOnly() || sketchStructure != KllSketch.SketchStructure.UPDATABLE;
      this.levelsArr = memVal.levelsArr;
   }

   static KllDirectFloatsSketch newDirectUpdatableInstance(int k, int m, WritableMemory dstMem, MemoryRequestServer memReqSvr) {
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
      dstMem.putFloatArray((long)offset, new float[]{Float.NaN, Float.NaN}, 0, 2);
      offset += 8;
      dstMem.putFloatArray((long)offset, new float[k], 0, k);
      KllMemoryValidate memVal = new KllMemoryValidate(dstMem, KllSketch.SketchType.FLOATS_SKETCH, (ArrayOfItemsSerDe)null);
      return new KllDirectFloatsSketch(KllSketch.SketchStructure.UPDATABLE, dstMem, memReqSvr, memVal);
   }

   String getItemAsString(int index) {
      return this.isEmpty() ? "NaN" : Float.toString(this.getFloatItemsArray()[index]);
   }

   public int getK() {
      return KllPreambleUtil.getMemoryK(this.wmem);
   }

   public float getMaxItem() {
      if (this.sketchStructure != KllSketch.SketchStructure.COMPACT_EMPTY && !this.isEmpty()) {
         if (this.sketchStructure == KllSketch.SketchStructure.COMPACT_SINGLE) {
            return this.getFloatSingleItem();
         } else {
            int offset = 20 + this.getLevelsArrSizeBytes(this.sketchStructure) + 4;
            return this.wmem.getFloat((long)offset);
         }
      } else {
         throw new SketchesArgumentException("The sketch must not be empty for this operation. ");
      }
   }

   float getMaxItemInternal() {
      if (this.sketchStructure != KllSketch.SketchStructure.COMPACT_EMPTY && !this.isEmpty()) {
         if (this.sketchStructure == KllSketch.SketchStructure.COMPACT_SINGLE) {
            return this.getFloatSingleItem();
         } else {
            int offset = 20 + this.getLevelsArrSizeBytes(this.sketchStructure) + 4;
            return this.wmem.getFloat((long)offset);
         }
      } else {
         return Float.NaN;
      }
   }

   String getMaxItemAsString() {
      float maxItem = this.getMaxItemInternal();
      return Float.toString(maxItem);
   }

   public float getMinItem() {
      if (this.sketchStructure != KllSketch.SketchStructure.COMPACT_EMPTY && !this.isEmpty()) {
         if (this.sketchStructure == KllSketch.SketchStructure.COMPACT_SINGLE) {
            return this.getFloatSingleItem();
         } else {
            int offset = 20 + this.getLevelsArrSizeBytes(this.sketchStructure);
            return this.wmem.getFloat((long)offset);
         }
      } else {
         throw new SketchesArgumentException("The sketch must not be empty for this operation. ");
      }
   }

   float getMinItemInternal() {
      if (this.sketchStructure != KllSketch.SketchStructure.COMPACT_EMPTY && !this.isEmpty()) {
         if (this.sketchStructure == KllSketch.SketchStructure.COMPACT_SINGLE) {
            return this.getFloatSingleItem();
         } else {
            int offset = 20 + this.getLevelsArrSizeBytes(this.sketchStructure);
            return this.wmem.getFloat((long)offset);
         }
      } else {
         return Float.NaN;
      }
   }

   String getMinItemAsString() {
      float minItem = this.getMinItemInternal();
      return Float.toString(minItem);
   }

   void setMaxItem(float item) {
      if (this.readOnly) {
         throw new SketchesArgumentException("Target sketch is Read Only, cannot write. ");
      } else {
         int offset = 20 + this.getLevelsArrSizeBytes(this.sketchStructure) + 4;
         this.wmem.putFloat((long)offset, item);
      }
   }

   void setMinItem(float item) {
      if (this.readOnly) {
         throw new SketchesArgumentException("Target sketch is Read Only, cannot write. ");
      } else {
         int offset = 20 + this.getLevelsArrSizeBytes(this.sketchStructure);
         this.wmem.putFloat((long)offset, item);
      }
   }

   public long getN() {
      if (this.sketchStructure == KllSketch.SketchStructure.COMPACT_EMPTY) {
         return 0L;
      } else {
         return this.sketchStructure == KllSketch.SketchStructure.COMPACT_SINGLE ? 1L : KllPreambleUtil.getMemoryN(this.wmem);
      }
   }

   float[] getFloatItemsArray() {
      int k = this.getK();
      if (this.sketchStructure == KllSketch.SketchStructure.COMPACT_EMPTY) {
         return new float[k];
      } else if (this.sketchStructure == KllSketch.SketchStructure.COMPACT_SINGLE) {
         float[] itemsArr = new float[k];
         itemsArr[k - 1] = this.getFloatSingleItem();
         return itemsArr;
      } else {
         int capacityItems = KllHelper.computeTotalItemCapacity(k, this.getM(), this.getNumLevels());
         float[] floatItemsArr = new float[capacityItems];
         int offset = 20 + this.getLevelsArrSizeBytes(this.sketchStructure) + 8;
         int shift = this.sketchStructure == KllSketch.SketchStructure.COMPACT_FULL ? this.levelsArr[0] : 0;
         int numItems = this.sketchStructure == KllSketch.SketchStructure.COMPACT_FULL ? this.getNumRetained() : capacityItems;
         this.wmem.getFloatArray((long)offset, floatItemsArr, shift, numItems);
         return floatItemsArr;
      }
   }

   float[] getFloatRetainedItemsArray() {
      if (this.sketchStructure == KllSketch.SketchStructure.COMPACT_EMPTY) {
         return new float[0];
      } else if (this.sketchStructure == KllSketch.SketchStructure.COMPACT_SINGLE) {
         return new float[]{this.getFloatSingleItem()};
      } else {
         int numRetained = this.getNumRetained();
         float[] floatItemsArr = new float[numRetained];
         int offset = 20 + this.getLevelsArrSizeBytes(this.sketchStructure) + 8 + (this.sketchStructure == KllSketch.SketchStructure.COMPACT_FULL ? 0 : this.levelsArr[0] * 4);
         this.wmem.getFloatArray((long)offset, floatItemsArr, 0, numRetained);
         return floatItemsArr;
      }
   }

   float getFloatSingleItem() {
      if (!this.isSingleItem()) {
         throw new SketchesArgumentException("Sketch does not have just one item. ");
      } else if (this.sketchStructure == KllSketch.SketchStructure.COMPACT_SINGLE) {
         return this.wmem.getFloat(8L);
      } else {
         int offset;
         if (this.sketchStructure == KllSketch.SketchStructure.COMPACT_FULL) {
            offset = 20 + this.getLevelsArrSizeBytes(this.sketchStructure) + 8;
         } else {
            offset = 20 + this.getLevelsArrSizeBytes(this.sketchStructure) + (2 + this.getK() - 1) * 4;
         }

         return this.wmem.getFloat((long)offset);
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
      byte[] bytesOut = new byte[8];
      if (this.sketchStructure == KllSketch.SketchStructure.COMPACT_EMPTY) {
         ByteArrayUtil.putFloatLE(bytesOut, 0, Float.NaN);
         ByteArrayUtil.putFloatLE(bytesOut, 4, Float.NaN);
         return bytesOut;
      } else if (this.sketchStructure == KllSketch.SketchStructure.COMPACT_SINGLE) {
         int offset = 8;
         this.wmem.getByteArray((long)offset, bytesOut, 0, 4);
         ByteArrayUtil.copyBytes(bytesOut, 0, bytesOut, 4, 4);
         return bytesOut;
      } else {
         int offset = 20 + this.getLevelsArrSizeBytes(this.sketchStructure);
         this.wmem.getByteArray((long)offset, bytesOut, 0, 4);
         this.wmem.getByteArray((long)(offset + 4), bytesOut, 4, 4);
         return bytesOut;
      }
   }

   byte[] getRetainedItemsByteArr() {
      if (this.sketchStructure == KllSketch.SketchStructure.COMPACT_EMPTY) {
         return new byte[0];
      } else {
         float[] fltArr = this.getFloatRetainedItemsArray();
         byte[] fltByteArr = new byte[fltArr.length * 4];
         WritableMemory wmem2 = WritableMemory.writableWrap(fltByteArr);
         wmem2.putFloatArray(0L, fltArr, 0, fltArr.length);
         return fltByteArr;
      }
   }

   byte[] getTotalItemsByteArr() {
      float[] fltArr = this.getFloatItemsArray();
      byte[] fltByteArr = new byte[fltArr.length * 4];
      WritableMemory wmem2 = WritableMemory.writableWrap(fltByteArr);
      wmem2.putFloatArray(0L, fltArr, 0, fltArr.length);
      return fltByteArr;
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

   void setFloatItemsArray(float[] floatItems) {
      if (this.readOnly) {
         throw new SketchesArgumentException("Target sketch is Read Only, cannot write. ");
      } else {
         int offset = 20 + this.getLevelsArrSizeBytes(this.sketchStructure) + 8;
         this.wmem.putFloatArray((long)offset, floatItems, 0, floatItems.length);
      }
   }

   void setFloatItemsArrayAt(int index, float item) {
      if (this.readOnly) {
         throw new SketchesArgumentException("Target sketch is Read Only, cannot write. ");
      } else {
         int offset = 20 + this.getLevelsArrSizeBytes(this.sketchStructure) + (index + 2) * 4;
         this.wmem.putFloat((long)offset, item);
      }
   }

   void setFloatItemsArrayAt(int index, float[] items, int srcOffset, int length) {
      if (this.readOnly) {
         throw new SketchesArgumentException("Target sketch is Read Only, cannot write. ");
      } else {
         int offset = 20 + this.getLevelsArrSizeBytes(this.sketchStructure) + (index + 2) * 4;
         this.wmem.putFloatArray((long)offset, items, srcOffset, length);
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

   static final class KllDirectCompactFloatsSketch extends KllDirectFloatsSketch {
      KllDirectCompactFloatsSketch(KllSketch.SketchStructure sketchStructure, Memory srcMem, KllMemoryValidate memVal) {
         super(sketchStructure, (WritableMemory)srcMem, (MemoryRequestServer)null, memVal);
      }
   }
}
