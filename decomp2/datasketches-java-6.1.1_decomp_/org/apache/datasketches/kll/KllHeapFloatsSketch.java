package org.apache.datasketches.kll;

import java.util.Arrays;
import java.util.Objects;
import org.apache.datasketches.common.ByteArrayUtil;
import org.apache.datasketches.common.SketchesArgumentException;
import org.apache.datasketches.memory.Memory;
import org.apache.datasketches.memory.MemoryRequestServer;
import org.apache.datasketches.memory.WritableMemory;

final class KllHeapFloatsSketch extends KllFloatsSketch {
   private final int k;
   private final int m;
   private long n;
   private int minK;
   private boolean isLevelZeroSorted;
   private float minFloatItem;
   private float maxFloatItem;
   private float[] floatItems;

   KllHeapFloatsSketch(int k, int m) {
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
      this.minFloatItem = Float.NaN;
      this.maxFloatItem = Float.NaN;
      this.floatItems = new float[k];
   }

   KllHeapFloatsSketch(int k, int m, float item, long weight) {
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
      this.minFloatItem = item;
      this.maxFloatItem = item;
      this.floatItems = KllFloatsHelper.createItemsArray(item, weight);
   }

   private KllHeapFloatsSketch(Memory srcMem, KllMemoryValidate memValidate) {
      super(KllSketch.SketchStructure.UPDATABLE);
      KllSketch.SketchStructure memStructure = memValidate.sketchStructure;
      this.k = memValidate.k;
      this.m = memValidate.m;
      this.n = memValidate.n;
      this.minK = memValidate.minK;
      this.levelsArr = memValidate.levelsArr;
      this.isLevelZeroSorted = memValidate.level0SortedFlag;
      if (memStructure == KllSketch.SketchStructure.COMPACT_EMPTY) {
         this.minFloatItem = Float.NaN;
         this.maxFloatItem = Float.NaN;
         this.floatItems = new float[this.k];
      } else if (memStructure == KllSketch.SketchStructure.COMPACT_SINGLE) {
         float item = srcMem.getFloat(8L);
         this.minFloatItem = this.maxFloatItem = item;
         this.floatItems = new float[this.k];
         this.floatItems[this.k - 1] = item;
      } else if (memStructure == KllSketch.SketchStructure.COMPACT_FULL) {
         int offsetBytes = 20;
         offsetBytes += (this.levelsArr.length - 1) * 4;
         this.minFloatItem = srcMem.getFloat((long)offsetBytes);
         offsetBytes += 4;
         this.maxFloatItem = srcMem.getFloat((long)offsetBytes);
         offsetBytes += 4;
         int capacityItems = this.levelsArr[this.getNumLevels()];
         int freeSpace = this.levelsArr[0];
         int retainedItems = capacityItems - freeSpace;
         this.floatItems = new float[capacityItems];
         srcMem.getFloatArray((long)offsetBytes, this.floatItems, freeSpace, retainedItems);
      } else {
         int offsetBytes = 20;
         offsetBytes += this.levelsArr.length * 4;
         this.minFloatItem = srcMem.getFloat((long)offsetBytes);
         offsetBytes += 4;
         this.maxFloatItem = srcMem.getFloat((long)offsetBytes);
         offsetBytes += 4;
         int capacityItems = this.levelsArr[this.getNumLevels()];
         this.floatItems = new float[capacityItems];
         srcMem.getFloatArray((long)offsetBytes, this.floatItems, 0, capacityItems);
      }

   }

   static KllHeapFloatsSketch heapifyImpl(Memory srcMem) {
      Objects.requireNonNull(srcMem, "Parameter 'srcMem' must not be null");
      KllMemoryValidate memVal = new KllMemoryValidate(srcMem, KllSketch.SketchType.FLOATS_SKETCH);
      return new KllHeapFloatsSketch(srcMem, memVal);
   }

   String getItemAsString(int index) {
      return this.isEmpty() ? "NaN" : Float.toString(this.floatItems[index]);
   }

   public int getK() {
      return this.k;
   }

   float getMaxItemInternal() {
      return this.maxFloatItem;
   }

   public float getMaxItem() {
      if (this.isEmpty()) {
         throw new SketchesArgumentException("The sketch must not be empty for this operation. ");
      } else {
         return this.maxFloatItem;
      }
   }

   String getMaxItemAsString() {
      return Float.toString(this.maxFloatItem);
   }

   float getMinItemInternal() {
      return this.minFloatItem;
   }

   public float getMinItem() {
      if (!this.isEmpty() && !Float.isNaN(this.minFloatItem)) {
         return this.minFloatItem;
      } else {
         throw new SketchesArgumentException("The sketch must not be empty for this operation. ");
      }
   }

   String getMinItemAsString() {
      return Float.toString(this.minFloatItem);
   }

   byte[] getMinMaxByteArr() {
      byte[] bytesOut = new byte[8];
      ByteArrayUtil.putFloatLE(bytesOut, 0, this.minFloatItem);
      ByteArrayUtil.putFloatLE(bytesOut, 4, this.maxFloatItem);
      return bytesOut;
   }

   void setMaxItem(float item) {
      this.maxFloatItem = item;
   }

   void setMinItem(float item) {
      this.minFloatItem = item;
   }

   public long getN() {
      return this.n;
   }

   float[] getFloatItemsArray() {
      return this.floatItems;
   }

   float getFloatSingleItem() {
      if (this.n != 1L) {
         throw new SketchesArgumentException("Sketch does not have just one item. ");
      } else {
         return this.floatItems[this.k - 1];
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
         byte[] bytesOut = new byte[4];
         ByteArrayUtil.putFloatLE(bytesOut, 0, this.getFloatSingleItem());
         return bytesOut;
      } else {
         int retained = this.getNumRetained();
         int bytes = retained * 4;
         byte[] bytesOut = new byte[bytes];
         WritableMemory wmem = WritableMemory.writableWrap(bytesOut);
         wmem.putFloatArray(0L, this.floatItems, this.levelsArr[0], retained);
         return bytesOut;
      }
   }

   byte[] getTotalItemsByteArr() {
      byte[] byteArr = new byte[this.floatItems.length * 4];
      WritableMemory wmem = WritableMemory.writableWrap(byteArr);
      wmem.putFloatArray(0L, this.floatItems, 0, this.floatItems.length);
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

   void setFloatItemsArray(float[] floatItems) {
      this.floatItems = floatItems;
   }

   void setFloatItemsArrayAt(int index, float item) {
      this.floatItems[index] = item;
   }

   void setFloatItemsArrayAt(int dstIndex, float[] srcItems, int srcOffset, int length) {
      System.arraycopy(srcItems, srcOffset, this.floatItems, dstIndex, length);
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

   float[] getFloatRetainedItemsArray() {
      return Arrays.copyOfRange(this.floatItems, this.levelsArr[0], this.levelsArr[this.getNumLevels()]);
   }

   void setWritableMemory(WritableMemory wmem) {
   }
}
