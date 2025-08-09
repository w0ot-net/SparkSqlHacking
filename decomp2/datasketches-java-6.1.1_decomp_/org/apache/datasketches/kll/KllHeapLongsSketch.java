package org.apache.datasketches.kll;

import java.util.Arrays;
import java.util.Objects;
import org.apache.datasketches.common.ByteArrayUtil;
import org.apache.datasketches.common.SketchesArgumentException;
import org.apache.datasketches.memory.Memory;
import org.apache.datasketches.memory.MemoryRequestServer;
import org.apache.datasketches.memory.WritableMemory;

final class KllHeapLongsSketch extends KllLongsSketch {
   private final int k;
   private final int m;
   private long n;
   private int minK;
   private boolean isLevelZeroSorted;
   private long minLongItem;
   private long maxLongItem;
   private long[] longItems;

   KllHeapLongsSketch(int k, int m) {
      super(KllSketch.SketchStructure.UPDATABLE);
      KllHelper.checkM(m);
      KllHelper.checkK(k, m);
      this.levelsArr = new int[]{k, k};
      this.readOnly = false;
      this.k = k;
      this.m = m;
      this.n = 0L;
      this.minK = k;
      this.isLevelZeroSorted = false;
      this.minLongItem = Long.MAX_VALUE;
      this.maxLongItem = Long.MIN_VALUE;
      this.longItems = new long[k];
   }

   KllHeapLongsSketch(int k, int m, long item, long weight) {
      super(KllSketch.SketchStructure.UPDATABLE);
      KllHelper.checkM(m);
      KllHelper.checkK(k, m);
      this.levelsArr = KllHelper.createLevelsArray(weight);
      this.readOnly = false;
      this.k = k;
      this.m = m;
      this.n = weight;
      this.minK = k;
      this.isLevelZeroSorted = false;
      this.minLongItem = item;
      this.maxLongItem = item;
      this.longItems = KllLongsHelper.createItemsArray(item, weight);
   }

   private KllHeapLongsSketch(Memory srcMem, KllMemoryValidate memValidate) {
      super(KllSketch.SketchStructure.UPDATABLE);
      KllSketch.SketchStructure memStructure = memValidate.sketchStructure;
      this.k = memValidate.k;
      this.m = memValidate.m;
      this.n = memValidate.n;
      this.minK = memValidate.minK;
      this.levelsArr = memValidate.levelsArr;
      this.isLevelZeroSorted = memValidate.level0SortedFlag;
      if (memStructure == KllSketch.SketchStructure.COMPACT_EMPTY) {
         this.minLongItem = Long.MAX_VALUE;
         this.maxLongItem = Long.MIN_VALUE;
         this.longItems = new long[this.k];
      } else if (memStructure == KllSketch.SketchStructure.COMPACT_SINGLE) {
         long item = srcMem.getLong(8L);
         this.minLongItem = this.maxLongItem = item;
         this.longItems = new long[this.k];
         this.longItems[this.k - 1] = item;
      } else if (memStructure == KllSketch.SketchStructure.COMPACT_FULL) {
         int offsetBytes = 20;
         offsetBytes += (this.levelsArr.length - 1) * 4;
         this.minLongItem = srcMem.getLong((long)offsetBytes);
         offsetBytes += 8;
         this.maxLongItem = srcMem.getLong((long)offsetBytes);
         offsetBytes += 8;
         int capacityItems = this.levelsArr[this.getNumLevels()];
         int freeSpace = this.levelsArr[0];
         int retainedItems = capacityItems - freeSpace;
         this.longItems = new long[capacityItems];
         srcMem.getLongArray((long)offsetBytes, this.longItems, freeSpace, retainedItems);
      } else {
         int offsetBytes = 20;
         offsetBytes += this.levelsArr.length * 4;
         this.minLongItem = srcMem.getLong((long)offsetBytes);
         offsetBytes += 8;
         this.maxLongItem = srcMem.getLong((long)offsetBytes);
         offsetBytes += 8;
         int capacityItems = this.levelsArr[this.getNumLevels()];
         this.longItems = new long[capacityItems];
         srcMem.getLongArray((long)offsetBytes, this.longItems, 0, capacityItems);
      }

   }

   static KllHeapLongsSketch heapifyImpl(Memory srcMem) {
      Objects.requireNonNull(srcMem, "Parameter 'srcMem' must not be null");
      KllMemoryValidate memVal = new KllMemoryValidate(srcMem, KllSketch.SketchType.LONGS_SKETCH);
      return new KllHeapLongsSketch(srcMem, memVal);
   }

   String getItemAsString(int index) {
      return this.isEmpty() ? "Null" : Long.toString(this.longItems[index]);
   }

   public int getK() {
      return this.k;
   }

   long getMaxItemInternal() {
      return this.maxLongItem;
   }

   public long getMaxItem() {
      if (this.isEmpty()) {
         throw new SketchesArgumentException("The sketch must not be empty for this operation. ");
      } else {
         return this.maxLongItem;
      }
   }

   String getMaxItemAsString() {
      return Long.toString(this.maxLongItem);
   }

   long getMinItemInternal() {
      return this.minLongItem;
   }

   public long getMinItem() {
      if (this.isEmpty()) {
         throw new SketchesArgumentException("The sketch must not be empty for this operation. ");
      } else {
         return this.minLongItem;
      }
   }

   String getMinItemAsString() {
      return Long.toString(this.minLongItem);
   }

   byte[] getMinMaxByteArr() {
      byte[] bytesOut = new byte[16];
      ByteArrayUtil.putLongLE(bytesOut, 0, this.minLongItem);
      ByteArrayUtil.putLongLE(bytesOut, 8, this.maxLongItem);
      return bytesOut;
   }

   void setMaxItem(long item) {
      this.maxLongItem = item;
   }

   void setMinItem(long item) {
      this.minLongItem = item;
   }

   public long getN() {
      return this.n;
   }

   long[] getLongItemsArray() {
      return this.longItems;
   }

   long getLongSingleItem() {
      if (this.n != 1L) {
         throw new SketchesArgumentException("Sketch does not have just one item. ");
      } else {
         return this.longItems[this.k - 1];
      }
   }

   int getM() {
      return this.m;
   }

   MemoryRequestServer getMemoryRequestServer() {
      return null;
   }

   int getMinK() {
      return this.minK;
   }

   byte[] getRetainedItemsByteArr() {
      if (this.isEmpty()) {
         return new byte[0];
      } else if (this.isSingleItem()) {
         byte[] bytesOut = new byte[8];
         ByteArrayUtil.putLongLE(bytesOut, 0, this.getLongSingleItem());
         return bytesOut;
      } else {
         int retained = this.getNumRetained();
         int bytes = retained * 8;
         byte[] bytesOut = new byte[bytes];
         WritableMemory wmem = WritableMemory.writableWrap(bytesOut);
         wmem.putLongArray(0L, this.longItems, this.levelsArr[0], retained);
         return bytesOut;
      }
   }

   byte[] getTotalItemsByteArr() {
      byte[] byteArr = new byte[this.longItems.length * 8];
      WritableMemory wmem = WritableMemory.writableWrap(byteArr);
      wmem.putLongArray(0L, this.longItems, 0, this.longItems.length);
      return byteArr;
   }

   WritableMemory getWritableMemory() {
      return null;
   }

   void incN(int increment) {
      this.n += (long)increment;
   }

   void incNumLevels() {
   }

   boolean isLevelZeroSorted() {
      return this.isLevelZeroSorted;
   }

   void setLongItemsArray(long[] longItems) {
      this.longItems = longItems;
   }

   void setLongItemsArrayAt(int index, long item) {
      this.longItems[index] = item;
   }

   void setLongItemsArrayAt(int dstIndex, long[] srcItems, int srcOffset, int length) {
      System.arraycopy(srcItems, srcOffset, this.longItems, dstIndex, length);
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

   void setNumLevels(int numLevels) {
   }

   long[] getLongRetainedItemsArray() {
      return Arrays.copyOfRange(this.longItems, this.levelsArr[0], this.levelsArr[this.getNumLevels()]);
   }

   void setWritableMemory(WritableMemory wmem) {
   }
}
