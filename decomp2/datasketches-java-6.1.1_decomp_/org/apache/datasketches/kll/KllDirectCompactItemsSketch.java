package org.apache.datasketches.kll;

import java.lang.reflect.Array;
import java.util.Comparator;
import org.apache.datasketches.common.ArrayOfBooleansSerDe;
import org.apache.datasketches.common.ArrayOfItemsSerDe;
import org.apache.datasketches.common.SketchesArgumentException;
import org.apache.datasketches.memory.Memory;
import org.apache.datasketches.memory.WritableMemory;

final class KllDirectCompactItemsSketch extends KllItemsSketch {
   private Memory mem;

   KllDirectCompactItemsSketch(KllMemoryValidate memVal, Comparator comparator, ArrayOfItemsSerDe serDe) {
      super(memVal.sketchStructure, comparator, serDe);
      this.mem = memVal.srcMem;
      this.readOnly = true;
      this.levelsArr = memVal.levelsArr;
   }

   String getItemAsString(int index) {
      return this.isEmpty() ? "Null" : this.serDe.toString(this.getTotalItemsArray()[index]);
   }

   public int getK() {
      return KllPreambleUtil.getMemoryK(this.mem);
   }

   public Object getMaxItem() {
      if (this.sketchStructure != KllSketch.SketchStructure.COMPACT_EMPTY && !this.isEmpty()) {
         if (this.sketchStructure == KllSketch.SketchStructure.COMPACT_SINGLE) {
            return this.serDe.deserializeFromMemory(this.mem, 8L, 1)[0];
         } else {
            int baseOffset = 20 + this.getNumLevels() * 4;
            int offset = baseOffset + this.serDe.sizeOf(this.mem, (long)baseOffset, 1);
            return this.serDe.deserializeFromMemory(this.mem, (long)offset, 1)[0];
         }
      } else {
         throw new SketchesArgumentException("The sketch must not be empty for this operation. ");
      }
   }

   String getMaxItemAsString() {
      return this.isEmpty() ? "Null" : this.serDe.toString(this.getMaxItem());
   }

   public Object getMinItem() {
      if (this.sketchStructure != KllSketch.SketchStructure.COMPACT_EMPTY && !this.isEmpty()) {
         if (this.sketchStructure == KllSketch.SketchStructure.COMPACT_SINGLE) {
            return this.serDe.deserializeFromMemory(this.mem, 8L, 1)[0];
         } else {
            int offset = 20 + this.getNumLevels() * 4;
            return this.serDe.deserializeFromMemory(this.mem, (long)offset, 1)[0];
         }
      } else {
         throw new SketchesArgumentException("The sketch must not be empty for this operation. ");
      }
   }

   String getMinItemAsString() {
      return this.isEmpty() ? "Null" : this.serDe.toString(this.getMinItem());
   }

   public long getN() {
      if (this.sketchStructure == KllSketch.SketchStructure.COMPACT_EMPTY) {
         return 0L;
      } else {
         return this.sketchStructure == KllSketch.SketchStructure.COMPACT_SINGLE ? 1L : KllPreambleUtil.getMemoryN(this.mem);
      }
   }

   private int getCompactDataOffset() {
      return this.sketchStructure == KllSketch.SketchStructure.COMPACT_SINGLE ? 8 : 20 + this.getNumLevels() * 4 + this.getMinMaxSizeBytes();
   }

   int getM() {
      return KllPreambleUtil.getMemoryM(this.mem);
   }

   int getMinK() {
      return this.sketchStructure != KllSketch.SketchStructure.COMPACT_EMPTY && this.sketchStructure != KllSketch.SketchStructure.COMPACT_SINGLE ? KllPreambleUtil.getMemoryMinK(this.mem) : KllPreambleUtil.getMemoryK(this.mem);
   }

   byte[] getMinMaxByteArr() {
      int offset = 20 + this.getNumLevels() * 4;
      int bytesMinMax = this.serDe.sizeOf(this.mem, (long)offset, 2);
      byte[] byteArr = new byte[bytesMinMax];
      this.mem.getByteArray((long)offset, byteArr, 0, bytesMinMax);
      return byteArr;
   }

   int getMinMaxSizeBytes() {
      int offset = 20 + this.getNumLevels() * 4;
      return this.serDe instanceof ArrayOfBooleansSerDe ? 2 : this.serDe.sizeOf(this.mem, (long)offset, 2);
   }

   Object[] getRetainedItemsArray() {
      int numRet = this.getNumRetained();
      if (this.sketchStructure != KllSketch.SketchStructure.COMPACT_EMPTY && this.getN() != 0L) {
         int offset = this.getCompactDataOffset();
         return this.serDe.deserializeFromMemory(this.mem, (long)offset, numRet);
      } else {
         return Array.newInstance(this.serDe.getClassOfT(), numRet);
      }
   }

   byte[] getRetainedItemsByteArr() {
      if (this.sketchStructure != KllSketch.SketchStructure.COMPACT_EMPTY && this.getN() != 0L) {
         int offset = this.getCompactDataOffset();
         int bytes = this.serDe.sizeOf(this.mem, (long)offset, this.getNumRetained());
         byte[] byteArr = new byte[bytes];
         this.mem.getByteArray((long)offset, byteArr, 0, bytes);
         return byteArr;
      } else {
         return new byte[0];
      }
   }

   int getRetainedItemsSizeBytes() {
      if (this.sketchStructure != KllSketch.SketchStructure.COMPACT_EMPTY && this.getN() != 0L) {
         int offset = this.getCompactDataOffset();
         return this.serDe.sizeOf(this.mem, (long)offset, this.getNumRetained());
      } else {
         return 0;
      }
   }

   Object getSingleItem() {
      if (this.getN() != 1L) {
         throw new SketchesArgumentException("Sketch does not have just one item. ");
      } else {
         int offset = this.getCompactDataOffset();
         return this.serDe.deserializeFromMemory(this.mem, (long)offset, 1)[0];
      }
   }

   byte[] getSingleItemByteArr() {
      if (this.getN() != 1L) {
         throw new SketchesArgumentException("Sketch does not have just one item. ");
      } else {
         int offset = this.getCompactDataOffset();
         int bytes = this.serDe.sizeOf(this.mem, (long)offset, 1);
         byte[] byteArr = new byte[bytes];
         this.mem.getByteArray((long)offset, byteArr, 0, bytes);
         return byteArr;
      }
   }

   int getSingleItemSizeBytes() {
      if (this.getN() != 1L) {
         throw new SketchesArgumentException("Sketch does not have just one item. ");
      } else {
         int offset = this.getCompactDataOffset();
         int bytes = this.serDe.sizeOf(this.mem, (long)offset, 1);
         return bytes;
      }
   }

   Object[] getTotalItemsArray() {
      int k = this.getK();
      if (this.getN() == 0L) {
         return Array.newInstance(this.serDe.getClassOfT(), k);
      } else if (this.getN() == 1L) {
         T[] itemsArr = (T[])((Object[])((Object[])Array.newInstance(this.serDe.getClassOfT(), k)));
         itemsArr[k - 1] = this.getSingleItem();
         return itemsArr;
      } else {
         int offset = this.getCompactDataOffset();
         int numRetItems = this.getNumRetained();
         int numCapItems = this.levelsArr[this.getNumLevels()];
         T[] retItems = (T[])this.serDe.deserializeFromMemory(this.mem, (long)offset, numRetItems);
         T[] capItems = (T[])((Object[])((Object[])Array.newInstance(this.serDe.getClassOfT(), numCapItems)));
         System.arraycopy(retItems, 0, capItems, this.levelsArr[0], numRetItems);
         return capItems;
      }
   }

   WritableMemory getWritableMemory() {
      return (WritableMemory)this.mem;
   }

   void incN(int increment) {
      throw new SketchesArgumentException("Unsupported operation for this Sketch Type. ");
   }

   boolean isLevelZeroSorted() {
      return KllPreambleUtil.getMemoryLevelZeroSortedFlag(this.mem);
   }

   void setItemsArray(Object[] ItemsArr) {
      throw new SketchesArgumentException("Unsupported operation for this Sketch Type. ");
   }

   void setItemsArrayAt(int index, Object item) {
      throw new SketchesArgumentException("Unsupported operation for this Sketch Type. ");
   }

   void setLevelZeroSorted(boolean sorted) {
      throw new SketchesArgumentException("Unsupported operation for this Sketch Type. ");
   }

   void setMaxItem(Object item) {
      throw new SketchesArgumentException("Unsupported operation for this Sketch Type. ");
   }

   void setMinItem(Object item) {
      throw new SketchesArgumentException("Unsupported operation for this Sketch Type. ");
   }

   void setMinK(int minK) {
      throw new SketchesArgumentException("Unsupported operation for this Sketch Type. ");
   }

   void setN(long n) {
      throw new SketchesArgumentException("Unsupported operation for this Sketch Type. ");
   }
}
