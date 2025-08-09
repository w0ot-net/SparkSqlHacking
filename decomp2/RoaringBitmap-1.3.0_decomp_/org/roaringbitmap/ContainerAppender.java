package org.roaringbitmap;

import java.util.function.Supplier;

public class ContainerAppender implements RoaringBitmapWriter {
   private final boolean doPartialSort;
   private final boolean runCompress;
   private final Supplier newContainer;
   private final Supplier newUnderlying;
   private WordStorage container;
   private BitmapDataProvider underlying;
   private int currentKey;

   ContainerAppender(boolean doPartialSort, boolean runCompress, Supplier newUnderlying, Supplier newContainer) {
      this.doPartialSort = doPartialSort;
      this.runCompress = runCompress;
      this.newUnderlying = newUnderlying;
      this.underlying = (BitmapDataProvider)newUnderlying.get();
      this.newContainer = newContainer;
      this.container = (WordStorage)newContainer.get();
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

      C tmp = (C)((WordStorage)this.container.add(Util.lowbits(value)));
      if (tmp != this.container) {
         this.container = tmp;
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

   public void addMany(int... values) {
      if (this.doPartialSort) {
         Util.partialRadixSort(values);
      }

      for(int i : values) {
         this.add(i);
      }

   }

   public void flush() {
      this.currentKey += this.appendToUnderlying();
   }

   public void reset() {
      this.currentKey = 0;
      this.container = (WordStorage)this.newContainer.get();
      this.underlying = (BitmapDataProvider)this.newUnderlying.get();
   }

   private int appendToUnderlying() {
      if (!this.container.isEmpty()) {
         assert this.currentKey <= 65535;

         ((AppendableStorage)this.underlying).append((char)this.currentKey, this.runCompress ? (WordStorage)this.container.runOptimize() : this.container);
         this.container = (WordStorage)this.newContainer.get();
         return 1;
      } else {
         return 0;
      }
   }
}
