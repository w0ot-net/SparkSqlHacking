package org.roaringbitmap.buffer;

import org.roaringbitmap.BatchIterator;
import org.roaringbitmap.ContainerBatchIterator;

public final class RoaringBatchIterator implements BatchIterator {
   private MappeableContainerPointer containerPointer;
   private int key;
   private ContainerBatchIterator iterator;
   private ArrayBatchIterator arrayBatchIterator;
   private BitmapBatchIterator bitmapBatchIterator;
   private RunBatchIterator runBatchIterator;

   public RoaringBatchIterator(MappeableContainerPointer containerPointer) {
      this.containerPointer = containerPointer;
      this.nextIterator();
   }

   public int nextBatch(int[] buffer) {
      int consumed = 0;

      while(this.iterator != null && consumed < buffer.length) {
         consumed += this.iterator.next(this.key, buffer, consumed);
         if (consumed < buffer.length || !this.iterator.hasNext()) {
            this.nextContainer();
         }
      }

      return consumed;
   }

   public boolean hasNext() {
      return null != this.iterator;
   }

   public void advanceIfNeeded(int target) {
      while(null != this.iterator && this.key >>> 16 < target >>> 16) {
         this.nextContainer();
      }

      if (null != this.iterator && this.key >>> 16 == target >>> 16) {
         this.iterator.advanceIfNeeded((char)target);
         if (!this.iterator.hasNext()) {
            this.nextContainer();
         }
      }

   }

   public BatchIterator clone() {
      try {
         RoaringBatchIterator it = (RoaringBatchIterator)super.clone();
         if (null != this.iterator) {
            it.iterator = this.iterator.clone();
         }

         if (null != this.containerPointer) {
            it.containerPointer = this.containerPointer.clone();
         }

         it.arrayBatchIterator = null;
         it.bitmapBatchIterator = null;
         it.runBatchIterator = null;
         return it;
      } catch (CloneNotSupportedException var2) {
         throw new IllegalStateException();
      }
   }

   private void nextContainer() {
      this.containerPointer.advance();
      this.nextIterator();
   }

   private void nextIterator() {
      if (null != this.iterator) {
         this.iterator.releaseContainer();
      }

      if (null != this.containerPointer && this.containerPointer.hasContainer()) {
         MappeableContainer container = this.containerPointer.getContainer();
         if (container instanceof MappeableArrayContainer) {
            this.nextIterator((MappeableArrayContainer)container);
         } else if (container instanceof MappeableBitmapContainer) {
            this.nextIterator((MappeableBitmapContainer)container);
         } else if (container instanceof MappeableRunContainer) {
            this.nextIterator((MappeableRunContainer)container);
         }

         this.key = this.containerPointer.key() << 16;
      } else {
         this.iterator = null;
      }

   }

   private void nextIterator(MappeableArrayContainer array) {
      if (null == this.arrayBatchIterator) {
         this.arrayBatchIterator = new ArrayBatchIterator(array);
      } else {
         this.arrayBatchIterator.wrap(array);
      }

      this.iterator = this.arrayBatchIterator;
   }

   private void nextIterator(MappeableBitmapContainer bitmap) {
      if (null == this.bitmapBatchIterator) {
         this.bitmapBatchIterator = new BitmapBatchIterator(bitmap);
      } else {
         this.bitmapBatchIterator.wrap(bitmap);
      }

      this.iterator = this.bitmapBatchIterator;
   }

   private void nextIterator(MappeableRunContainer run) {
      if (null == this.runBatchIterator) {
         this.runBatchIterator = new RunBatchIterator(run);
      } else {
         this.runBatchIterator.wrap(run);
      }

      this.iterator = this.runBatchIterator;
   }
}
