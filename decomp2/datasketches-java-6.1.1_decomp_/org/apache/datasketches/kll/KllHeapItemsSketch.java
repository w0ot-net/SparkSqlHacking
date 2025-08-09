package org.apache.datasketches.kll;

import java.lang.reflect.Array;
import java.util.Comparator;
import org.apache.datasketches.common.ArrayOfItemsSerDe;
import org.apache.datasketches.common.ByteArrayUtil;
import org.apache.datasketches.common.SketchesArgumentException;
import org.apache.datasketches.memory.Memory;
import org.apache.datasketches.memory.WritableMemory;

final class KllHeapItemsSketch extends KllItemsSketch {
   private final int k;
   private final int m;
   private long n;
   private int minK;
   private boolean isLevelZeroSorted;
   private Object minItem;
   private Object maxItem;
   private Object[] itemsArr;

   KllHeapItemsSketch(int k, int m, Comparator comparator, ArrayOfItemsSerDe serDe) {
      super(KllSketch.SketchStructure.UPDATABLE, comparator, serDe);
      KllHelper.checkM(m);
      KllHelper.checkK(k, m);
      this.levelsArr = new int[]{k, k};
      this.readOnly = false;
      this.k = k;
      this.m = m;
      this.n = 0L;
      this.minK = k;
      this.isLevelZeroSorted = false;
      this.minItem = null;
      this.maxItem = null;
      this.itemsArr = new Object[k];
   }

   KllHeapItemsSketch(int k, int m, Object item, long weight, Comparator comparator, ArrayOfItemsSerDe serDe) {
      super(KllSketch.SketchStructure.UPDATABLE, comparator, serDe);
      KllHelper.checkM(m);
      KllHelper.checkK(k, m);
      this.levelsArr = KllHelper.createLevelsArray(weight);
      this.readOnly = false;
      this.k = k;
      this.m = m;
      this.n = weight;
      this.minK = k;
      this.isLevelZeroSorted = false;
      this.minItem = item;
      this.maxItem = item;
      this.itemsArr = KllItemsHelper.createItemsArray(serDe.getClassOfT(), item, weight);
   }

   KllHeapItemsSketch(Memory srcMem, Comparator comparator, ArrayOfItemsSerDe serDe) {
      super(KllSketch.SketchStructure.UPDATABLE, comparator, serDe);
      KllMemoryValidate memVal = new KllMemoryValidate(srcMem, KllSketch.SketchType.ITEMS_SKETCH, serDe);
      this.k = memVal.k;
      this.m = memVal.m;
      this.levelsArr = memVal.levelsArr;
      this.readOnly = false;
      this.n = memVal.n;
      this.minK = memVal.minK;
      this.isLevelZeroSorted = memVal.level0SortedFlag;
      this.itemsArr = new Object[this.levelsArr[memVal.numLevels]];
      KllSketch.SketchStructure memStruct = memVal.sketchStructure;
      if (memStruct == KllSketch.SketchStructure.COMPACT_EMPTY) {
         this.minItem = null;
         this.maxItem = null;
         this.itemsArr = new Object[this.k];
      } else if (memStruct == KllSketch.SketchStructure.COMPACT_SINGLE) {
         int offset = 8;
         T item = (T)serDe.deserializeFromMemory(srcMem, 8L, 1)[0];
         this.minItem = item;
         this.maxItem = item;
         this.itemsArr[this.k - 1] = item;
      } else {
         if (memStruct != KllSketch.SketchStructure.COMPACT_FULL) {
            throw new SketchesArgumentException("Unsupported operation for this Sketch Type. UPDATABLE");
         }

         int offset = 20 + memVal.numLevels * 4;
         this.minItem = serDe.deserializeFromMemory(srcMem, (long)offset, 1)[0];
         offset += serDe.sizeOf(this.minItem);
         this.maxItem = serDe.deserializeFromMemory(srcMem, (long)offset, 1)[0];
         offset += serDe.sizeOf(this.maxItem);
         int numRetained = this.levelsArr[memVal.numLevels] - this.levelsArr[0];
         Object[] retItems = serDe.deserializeFromMemory(srcMem, (long)offset, numRetained);
         System.arraycopy(retItems, 0, this.itemsArr, this.levelsArr[0], numRetained);
      }

   }

   String getItemAsString(int index) {
      return this.isEmpty() ? "Null" : this.serDe.toString(this.itemsArr[index]);
   }

   public int getK() {
      return this.k;
   }

   public Object getMaxItem() {
      if (this.isEmpty()) {
         throw new SketchesArgumentException("The sketch must not be empty for this operation. ");
      } else {
         return this.maxItem;
      }
   }

   String getMaxItemAsString() {
      return this.isEmpty() ? "Null" : this.serDe.toString(this.maxItem);
   }

   public Object getMinItem() {
      if (this.isEmpty()) {
         throw new SketchesArgumentException("The sketch must not be empty for this operation. ");
      } else {
         return this.minItem;
      }
   }

   String getMinItemAsString() {
      return this.isEmpty() ? "Null" : this.serDe.toString(this.minItem);
   }

   public long getN() {
      return this.n;
   }

   int getM() {
      return this.m;
   }

   int getMinK() {
      return this.minK;
   }

   byte[] getMinMaxByteArr() {
      byte[] minBytes = this.serDe.serializeToByteArray(this.minItem);
      byte[] maxBytes = this.serDe.serializeToByteArray(this.maxItem);
      byte[] minMaxBytes = new byte[minBytes.length + maxBytes.length];
      ByteArrayUtil.copyBytes(minBytes, 0, minMaxBytes, 0, minBytes.length);
      ByteArrayUtil.copyBytes(maxBytes, 0, minMaxBytes, minBytes.length, maxBytes.length);
      return minMaxBytes;
   }

   int getMinMaxSizeBytes() {
      int minBytes = this.serDe.sizeOf(this.minItem);
      int maxBytes = this.serDe.sizeOf(this.maxItem);
      return minBytes + maxBytes;
   }

   Object[] getRetainedItemsArray() {
      int numRet = this.getNumRetained();
      T[] outArr = (T[])((Object[])((Object[])Array.newInstance(this.serDe.getClassOfT(), numRet)));
      System.arraycopy(this.itemsArr, this.levelsArr[0], outArr, 0, numRet);
      return outArr;
   }

   byte[] getRetainedItemsByteArr() {
      T[] retArr = (T[])this.getRetainedItemsArray();
      return this.serDe.serializeToByteArray(retArr);
   }

   int getRetainedItemsSizeBytes() {
      return this.getRetainedItemsByteArr().length;
   }

   Object getSingleItem() {
      if (this.n != 1L) {
         throw new SketchesArgumentException("Sketch does not have just one item. ");
      } else {
         T item = (T)this.itemsArr[this.k - 1];
         return item;
      }
   }

   byte[] getSingleItemByteArr() {
      return this.serDe.serializeToByteArray(this.getSingleItem());
   }

   int getSingleItemSizeBytes() {
      return this.serDe.sizeOf(this.getSingleItem());
   }

   Object[] getTotalItemsArray() {
      if (this.n == 0L) {
         return Array.newInstance(this.serDe.getClassOfT(), this.k);
      } else {
         T[] outArr = (T[])((Object[])((Object[])Array.newInstance(this.serDe.getClassOfT(), this.itemsArr.length)));
         System.arraycopy(this.itemsArr, 0, outArr, 0, this.itemsArr.length);
         return outArr;
      }
   }

   WritableMemory getWritableMemory() {
      return null;
   }

   void incN(int increment) {
      this.n += (long)increment;
   }

   boolean isLevelZeroSorted() {
      return this.isLevelZeroSorted;
   }

   void setLevelZeroSorted(boolean sorted) {
      this.isLevelZeroSorted = sorted;
   }

   void setMinK(int minK) {
      this.minK = minK;
   }

   void setN(long n) {
      this.n = n;
   }

   void setItemsArray(Object[] itemsArr) {
      this.itemsArr = itemsArr;
   }

   void setItemsArrayAt(int index, Object item) {
      this.itemsArr[index] = item;
   }

   void setMaxItem(Object item) {
      this.maxItem = item;
   }

   void setMinItem(Object item) {
      this.minItem = item;
   }
}
