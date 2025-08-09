package org.roaringbitmap;

import java.util.Arrays;
import java.util.function.Supplier;

public class ConstantMemoryContainerAppender implements RoaringBitmapWriter {
   private final boolean doPartialSort;
   private final boolean runCompress;
   private static final int WORD_COUNT = 1024;
   private final long[] bitmap;
   private final Supplier newUnderlying;
   private BitmapDataProvider underlying;
   private boolean dirty = false;
   private int currentKey;

   ConstantMemoryContainerAppender(boolean doPartialSort, boolean runCompress, Supplier newUnderlying) {
      this.newUnderlying = newUnderlying;
      this.underlying = (BitmapDataProvider)newUnderlying.get();
      this.doPartialSort = doPartialSort;
      this.runCompress = runCompress;
      this.bitmap = new long[1024];
   }

   public BitmapDataProvider getUnderlying() {
      return this.underlying;
   }

   public void add(int value) {
      int key = Util.highbits(value);
      if (key != this.currentKey) {
         if (key < this.currentKey) {
            this.underlying.add(value);
            return;
         }

         this.appendToUnderlying();
         this.currentKey = key;
      }

      int low = Util.lowbits(value);
      long[] var10000 = this.bitmap;
      var10000[low >>> 6] |= 1L << low;
      this.dirty = true;
   }

   public void addMany(int... values) {
      if (this.doPartialSort) {
         Util.partialRadixSort(values);
      }

      for(int value : values) {
         this.add(value);
      }

   }

   public void add(long min, long max) {
      this.appendToUnderlying();
      this.underlying.add(min, max);
      int mark = (int)((max >>> 16) + 1L);
      if (this.currentKey < mark) {
         this.currentKey = mark;
      }

   }

   public void flush() {
      this.currentKey += this.appendToUnderlying();
   }

   public void reset() {
      this.currentKey = 0;
      this.underlying = (BitmapDataProvider)this.newUnderlying.get();
      this.dirty = false;
   }

   private Container chooseBestContainer() {
      Container container = (new BitmapContainer(this.bitmap, -1)).repairAfterLazy();
      if (this.runCompress) {
         container = container.runOptimize();
      }

      return container instanceof BitmapContainer ? container.clone() : container;
   }

   private int appendToUnderlying() {
      if (this.dirty) {
         assert this.currentKey <= 65535;

         ((AppendableStorage)this.underlying).append((char)this.currentKey, this.chooseBestContainer());
         Arrays.fill(this.bitmap, 0L);
         this.dirty = false;
         return 1;
      } else {
         return 0;
      }
   }
}
