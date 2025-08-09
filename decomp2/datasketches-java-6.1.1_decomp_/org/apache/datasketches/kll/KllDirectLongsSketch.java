package org.apache.datasketches.kll;

import org.apache.datasketches.common.ArrayOfItemsSerDe;
import org.apache.datasketches.common.ByteArrayUtil;
import org.apache.datasketches.common.Family;
import org.apache.datasketches.common.SketchesArgumentException;
import org.apache.datasketches.memory.Memory;
import org.apache.datasketches.memory.MemoryRequestServer;
import org.apache.datasketches.memory.WritableMemory;

class KllDirectLongsSketch extends KllLongsSketch {
   private WritableMemory wmem;
   private MemoryRequestServer memReqSvr;

   KllDirectLongsSketch(KllSketch.SketchStructure sketchStructure, WritableMemory wmem, MemoryRequestServer memReqSvr, KllMemoryValidate memVal) {
      super(sketchStructure);
      this.wmem = wmem;
      this.memReqSvr = memReqSvr;
      this.readOnly = wmem != null && wmem.isReadOnly() || sketchStructure != KllSketch.SketchStructure.UPDATABLE;
      this.levelsArr = memVal.levelsArr;
   }

   static KllDirectLongsSketch newDirectUpdatableInstance(int k, int m, WritableMemory dstMem, MemoryRequestServer memReqSvr) {
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
      dstMem.putLongArray((long)offset, new long[]{Long.MAX_VALUE, Long.MIN_VALUE}, 0, 2);
      offset += 16;
      dstMem.putLongArray((long)offset, new long[k], 0, k);
      KllMemoryValidate memVal = new KllMemoryValidate(dstMem, KllSketch.SketchType.LONGS_SKETCH, (ArrayOfItemsSerDe)null);
      return new KllDirectLongsSketch(KllSketch.SketchStructure.UPDATABLE, dstMem, memReqSvr, memVal);
   }

   String getItemAsString(int index) {
      return this.isEmpty() ? "Null" : Long.toString(this.getLongItemsArray()[index]);
   }

   public int getK() {
      return KllPreambleUtil.getMemoryK(this.wmem);
   }

   public long getMaxItem() {
      if (this.sketchStructure != KllSketch.SketchStructure.COMPACT_EMPTY && !this.isEmpty()) {
         if (this.sketchStructure == KllSketch.SketchStructure.COMPACT_SINGLE) {
            return this.getLongSingleItem();
         } else {
            int offset = 20 + this.getLevelsArrSizeBytes(this.sketchStructure) + 8;
            return this.wmem.getLong((long)offset);
         }
      } else {
         throw new SketchesArgumentException("The sketch must not be empty for this operation. ");
      }
   }

   long getMaxItemInternal() {
      if (this.sketchStructure != KllSketch.SketchStructure.COMPACT_EMPTY && !this.isEmpty()) {
         if (this.sketchStructure == KllSketch.SketchStructure.COMPACT_SINGLE) {
            return this.getLongSingleItem();
         } else {
            int offset = 20 + this.getLevelsArrSizeBytes(this.sketchStructure) + 8;
            return this.wmem.getLong((long)offset);
         }
      } else {
         return Long.MAX_VALUE;
      }
   }

   String getMaxItemAsString() {
      long maxItem = this.getMaxItemInternal();
      return Long.toString(maxItem);
   }

   public long getMinItem() {
      if (this.sketchStructure != KllSketch.SketchStructure.COMPACT_EMPTY && !this.isEmpty()) {
         if (this.sketchStructure == KllSketch.SketchStructure.COMPACT_SINGLE) {
            return this.getLongSingleItem();
         } else {
            int offset = 20 + this.getLevelsArrSizeBytes(this.sketchStructure);
            return this.wmem.getLong((long)offset);
         }
      } else {
         throw new SketchesArgumentException("The sketch must not be empty for this operation. ");
      }
   }

   long getMinItemInternal() {
      if (this.sketchStructure != KllSketch.SketchStructure.COMPACT_EMPTY && !this.isEmpty()) {
         if (this.sketchStructure == KllSketch.SketchStructure.COMPACT_SINGLE) {
            return this.getLongSingleItem();
         } else {
            int offset = 20 + this.getLevelsArrSizeBytes(this.sketchStructure);
            return this.wmem.getLong((long)offset);
         }
      } else {
         return Long.MAX_VALUE;
      }
   }

   String getMinItemAsString() {
      long minItem = this.getMinItemInternal();
      return Long.toString(minItem);
   }

   void setMaxItem(long item) {
      if (this.readOnly) {
         throw new SketchesArgumentException("Target sketch is Read Only, cannot write. ");
      } else {
         int offset = 20 + this.getLevelsArrSizeBytes(this.sketchStructure) + 8;
         this.wmem.putLong((long)offset, item);
      }
   }

   void setMinItem(long item) {
      if (this.readOnly) {
         throw new SketchesArgumentException("Target sketch is Read Only, cannot write. ");
      } else {
         int offset = 20 + this.getLevelsArrSizeBytes(this.sketchStructure);
         this.wmem.putLong((long)offset, item);
      }
   }

   public long getN() {
      if (this.sketchStructure == KllSketch.SketchStructure.COMPACT_EMPTY) {
         return 0L;
      } else {
         return this.sketchStructure == KllSketch.SketchStructure.COMPACT_SINGLE ? 1L : KllPreambleUtil.getMemoryN(this.wmem);
      }
   }

   long[] getLongItemsArray() {
      int k = this.getK();
      if (this.sketchStructure == KllSketch.SketchStructure.COMPACT_EMPTY) {
         return new long[k];
      } else if (this.sketchStructure == KllSketch.SketchStructure.COMPACT_SINGLE) {
         long[] itemsArr = new long[k];
         itemsArr[k - 1] = this.getLongSingleItem();
         return itemsArr;
      } else {
         int capacityItems = KllHelper.computeTotalItemCapacity(k, this.getM(), this.getNumLevels());
         long[] longItemsArr = new long[capacityItems];
         int offset = 20 + this.getLevelsArrSizeBytes(this.sketchStructure) + 16;
         int shift = this.sketchStructure == KllSketch.SketchStructure.COMPACT_FULL ? this.levelsArr[0] : 0;
         int numItems = this.sketchStructure == KllSketch.SketchStructure.COMPACT_FULL ? this.getNumRetained() : capacityItems;
         this.wmem.getLongArray((long)offset, longItemsArr, shift, numItems);
         return longItemsArr;
      }
   }

   long[] getLongRetainedItemsArray() {
      if (this.sketchStructure == KllSketch.SketchStructure.COMPACT_EMPTY) {
         return new long[0];
      } else if (this.sketchStructure == KllSketch.SketchStructure.COMPACT_SINGLE) {
         return new long[]{this.getLongSingleItem()};
      } else {
         int numRetained = this.getNumRetained();
         long[] longItemsArr = new long[numRetained];
         int offset = 20 + this.getLevelsArrSizeBytes(this.sketchStructure) + 16 + (this.sketchStructure == KllSketch.SketchStructure.COMPACT_FULL ? 0 : this.levelsArr[0] * 8);
         this.wmem.getLongArray((long)offset, longItemsArr, 0, numRetained);
         return longItemsArr;
      }
   }

   long getLongSingleItem() {
      if (!this.isSingleItem()) {
         throw new SketchesArgumentException("Sketch does not have just one item. ");
      } else if (this.sketchStructure == KllSketch.SketchStructure.COMPACT_SINGLE) {
         return this.wmem.getLong(8L);
      } else {
         int offset;
         if (this.sketchStructure == KllSketch.SketchStructure.COMPACT_FULL) {
            offset = 20 + this.getLevelsArrSizeBytes(this.sketchStructure) + 16;
         } else {
            offset = 20 + this.getLevelsArrSizeBytes(this.sketchStructure) + (2 + this.getK() - 1) * 8;
         }

         return this.wmem.getLong((long)offset);
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
         ByteArrayUtil.putLongLE(bytesOut, 0, Long.MAX_VALUE);
         ByteArrayUtil.putLongLE(bytesOut, 8, Long.MIN_VALUE);
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
         long[] lngArr = this.getLongRetainedItemsArray();
         byte[] lngByteArr = new byte[lngArr.length * 8];
         WritableMemory wmem2 = WritableMemory.writableWrap(lngByteArr);
         wmem2.putLongArray(0L, lngArr, 0, lngArr.length);
         return lngByteArr;
      }
   }

   byte[] getTotalItemsByteArr() {
      long[] lngArr = this.getLongItemsArray();
      byte[] lngByteArr = new byte[lngArr.length * 8];
      WritableMemory wmem2 = WritableMemory.writableWrap(lngByteArr);
      wmem2.putLongArray(0L, lngArr, 0, lngArr.length);
      return lngByteArr;
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

   void setLongItemsArray(long[] longItems) {
      if (this.readOnly) {
         throw new SketchesArgumentException("Target sketch is Read Only, cannot write. ");
      } else {
         int offset = 20 + this.getLevelsArrSizeBytes(this.sketchStructure) + 16;
         this.wmem.putLongArray((long)offset, longItems, 0, longItems.length);
      }
   }

   void setLongItemsArrayAt(int index, long item) {
      if (this.readOnly) {
         throw new SketchesArgumentException("Target sketch is Read Only, cannot write. ");
      } else {
         int offset = 20 + this.getLevelsArrSizeBytes(this.sketchStructure) + (index + 2) * 8;
         this.wmem.putLong((long)offset, item);
      }
   }

   void setLongItemsArrayAt(int index, long[] items, int srcOffset, int length) {
      if (this.readOnly) {
         throw new SketchesArgumentException("Target sketch is Read Only, cannot write. ");
      } else {
         int offset = 20 + this.getLevelsArrSizeBytes(this.sketchStructure) + (index + 2) * 8;
         this.wmem.putLongArray((long)offset, items, srcOffset, length);
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

   static final class KllDirectCompactLongsSketch extends KllDirectLongsSketch {
      KllDirectCompactLongsSketch(KllSketch.SketchStructure sketchStructure, Memory srcMem, KllMemoryValidate memVal) {
         super(sketchStructure, (WritableMemory)srcMem, (MemoryRequestServer)null, memVal);
      }
   }
}
