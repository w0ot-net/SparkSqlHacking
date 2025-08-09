package org.apache.datasketches.kll;

import java.util.Arrays;
import java.util.Objects;
import org.apache.datasketches.common.ByteArrayUtil;
import org.apache.datasketches.common.SketchesArgumentException;
import org.apache.datasketches.memory.Memory;
import org.apache.datasketches.memory.MemoryRequestServer;
import org.apache.datasketches.memory.WritableMemory;

final class KllHeapDoublesSketch extends KllDoublesSketch {
   private final int k;
   private final int m;
   private long n;
   private int minK;
   private boolean isLevelZeroSorted;
   private double minDoubleItem;
   private double maxDoubleItem;
   private double[] doubleItems;

   KllHeapDoublesSketch(int k, int m) {
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
      this.minDoubleItem = Double.NaN;
      this.maxDoubleItem = Double.NaN;
      this.doubleItems = new double[k];
   }

   KllHeapDoublesSketch(int k, int m, double item, long weight) {
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
      this.minDoubleItem = item;
      this.maxDoubleItem = item;
      this.doubleItems = KllDoublesHelper.createItemsArray(item, weight);
   }

   private KllHeapDoublesSketch(Memory srcMem, KllMemoryValidate memValidate) {
      super(KllSketch.SketchStructure.UPDATABLE);
      KllSketch.SketchStructure memStructure = memValidate.sketchStructure;
      this.k = memValidate.k;
      this.m = memValidate.m;
      this.n = memValidate.n;
      this.minK = memValidate.minK;
      this.levelsArr = memValidate.levelsArr;
      this.isLevelZeroSorted = memValidate.level0SortedFlag;
      if (memStructure == KllSketch.SketchStructure.COMPACT_EMPTY) {
         this.minDoubleItem = Double.NaN;
         this.maxDoubleItem = Double.NaN;
         this.doubleItems = new double[this.k];
      } else if (memStructure == KllSketch.SketchStructure.COMPACT_SINGLE) {
         double item = srcMem.getDouble(8L);
         this.minDoubleItem = this.maxDoubleItem = item;
         this.doubleItems = new double[this.k];
         this.doubleItems[this.k - 1] = item;
      } else if (memStructure == KllSketch.SketchStructure.COMPACT_FULL) {
         int offsetBytes = 20;
         offsetBytes += (this.levelsArr.length - 1) * 4;
         this.minDoubleItem = srcMem.getDouble((long)offsetBytes);
         offsetBytes += 8;
         this.maxDoubleItem = srcMem.getDouble((long)offsetBytes);
         offsetBytes += 8;
         int capacityItems = this.levelsArr[this.getNumLevels()];
         int freeSpace = this.levelsArr[0];
         int retainedItems = capacityItems - freeSpace;
         this.doubleItems = new double[capacityItems];
         srcMem.getDoubleArray((long)offsetBytes, this.doubleItems, freeSpace, retainedItems);
      } else {
         int offsetBytes = 20;
         offsetBytes += this.levelsArr.length * 4;
         this.minDoubleItem = srcMem.getDouble((long)offsetBytes);
         offsetBytes += 8;
         this.maxDoubleItem = srcMem.getDouble((long)offsetBytes);
         offsetBytes += 8;
         int capacityItems = this.levelsArr[this.getNumLevels()];
         this.doubleItems = new double[capacityItems];
         srcMem.getDoubleArray((long)offsetBytes, this.doubleItems, 0, capacityItems);
      }

   }

   static KllHeapDoublesSketch heapifyImpl(Memory srcMem) {
      Objects.requireNonNull(srcMem, "Parameter 'srcMem' must not be null");
      KllMemoryValidate memVal = new KllMemoryValidate(srcMem, KllSketch.SketchType.DOUBLES_SKETCH);
      return new KllHeapDoublesSketch(srcMem, memVal);
   }

   String getItemAsString(int index) {
      return this.isEmpty() ? "NaN" : Double.toString(this.doubleItems[index]);
   }

   public int getK() {
      return this.k;
   }

   double getMaxItemInternal() {
      return this.maxDoubleItem;
   }

   public double getMaxItem() {
      if (!this.isEmpty() && !Double.isNaN(this.maxDoubleItem)) {
         return this.maxDoubleItem;
      } else {
         throw new SketchesArgumentException("The sketch must not be empty for this operation. ");
      }
   }

   String getMaxItemAsString() {
      return Double.toString(this.maxDoubleItem);
   }

   double getMinItemInternal() {
      return this.minDoubleItem;
   }

   public double getMinItem() {
      if (!this.isEmpty() && !Double.isNaN(this.minDoubleItem)) {
         return this.minDoubleItem;
      } else {
         throw new SketchesArgumentException("The sketch must not be empty for this operation. ");
      }
   }

   String getMinItemAsString() {
      return Double.toString(this.minDoubleItem);
   }

   byte[] getMinMaxByteArr() {
      byte[] bytesOut = new byte[16];
      ByteArrayUtil.putDoubleLE(bytesOut, 0, this.minDoubleItem);
      ByteArrayUtil.putDoubleLE(bytesOut, 8, this.maxDoubleItem);
      return bytesOut;
   }

   void setMaxItem(double item) {
      this.maxDoubleItem = item;
   }

   void setMinItem(double item) {
      this.minDoubleItem = item;
   }

   public long getN() {
      return this.n;
   }

   double[] getDoubleItemsArray() {
      return this.doubleItems;
   }

   double getDoubleSingleItem() {
      if (this.n != 1L) {
         throw new SketchesArgumentException("Sketch does not have just one item. ");
      } else {
         return this.doubleItems[this.k - 1];
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
         ByteArrayUtil.putDoubleLE(bytesOut, 0, this.getDoubleSingleItem());
         return bytesOut;
      } else {
         int retained = this.getNumRetained();
         int bytes = retained * 8;
         byte[] bytesOut = new byte[bytes];
         WritableMemory wmem = WritableMemory.writableWrap(bytesOut);
         wmem.putDoubleArray(0L, this.doubleItems, this.levelsArr[0], retained);
         return bytesOut;
      }
   }

   byte[] getTotalItemsByteArr() {
      byte[] byteArr = new byte[this.doubleItems.length * 8];
      WritableMemory wmem = WritableMemory.writableWrap(byteArr);
      wmem.putDoubleArray(0L, this.doubleItems, 0, this.doubleItems.length);
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

   void setDoubleItemsArray(double[] doubleItems) {
      this.doubleItems = doubleItems;
   }

   void setDoubleItemsArrayAt(int index, double item) {
      this.doubleItems[index] = item;
   }

   void setDoubleItemsArrayAt(int dstIndex, double[] srcItems, int srcOffset, int length) {
      System.arraycopy(srcItems, srcOffset, this.doubleItems, dstIndex, length);
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

   double[] getDoubleRetainedItemsArray() {
      return Arrays.copyOfRange(this.doubleItems, this.levelsArr[0], this.levelsArr[this.getNumLevels()]);
   }

   void setWritableMemory(WritableMemory wmem) {
   }
}
