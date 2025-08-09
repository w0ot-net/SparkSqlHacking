package org.roaringbitmap;

public final class RoaringBatchIterator implements BatchIterator {
   private final RoaringArray highLowContainer;
   private int index = 0;
   private int key;
   private ContainerBatchIterator iterator;
   private ArrayBatchIterator arrayBatchIterator = null;
   private BitmapBatchIterator bitmapBatchIterator = null;
   private RunBatchIterator runBatchIterator = null;

   public RoaringBatchIterator(RoaringArray highLowContainer) {
      this.highLowContainer = highLowContainer;
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

   public BatchIterator clone() {
      try {
         RoaringBatchIterator it = (RoaringBatchIterator)super.clone();
         if (null != this.iterator) {
            it.iterator = this.iterator.clone();
         }

         it.arrayBatchIterator = null;
         it.bitmapBatchIterator = null;
         it.runBatchIterator = null;
         return it;
      } catch (CloneNotSupportedException var2) {
         throw new IllegalStateException();
      }
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

   private void nextContainer() {
      ++this.index;
      this.nextIterator();
   }

   private void nextIterator() {
      if (null != this.iterator) {
         this.iterator.releaseContainer();
      }

      if (this.index < this.highLowContainer.size()) {
         Container container = this.highLowContainer.getContainerAtIndex(this.index);
         if (container instanceof ArrayContainer) {
            this.nextIterator((ArrayContainer)container);
         } else if (container instanceof BitmapContainer) {
            this.nextIterator((BitmapContainer)container);
         } else if (container instanceof RunContainer) {
            this.nextIterator((RunContainer)container);
         }

         this.key = this.highLowContainer.getKeyAtIndex(this.index) << 16;
      } else {
         this.iterator = null;
      }

   }

   private void nextIterator(ArrayContainer array) {
      if (null == this.arrayBatchIterator) {
         this.arrayBatchIterator = new ArrayBatchIterator(array);
      } else {
         this.arrayBatchIterator.wrap(array);
      }

      this.iterator = this.arrayBatchIterator;
   }

   private void nextIterator(BitmapContainer bitmap) {
      if (null == this.bitmapBatchIterator) {
         this.bitmapBatchIterator = new BitmapBatchIterator(bitmap);
      } else {
         this.bitmapBatchIterator.wrap(bitmap);
      }

      this.iterator = this.bitmapBatchIterator;
   }

   private void nextIterator(RunContainer run) {
      if (null == this.runBatchIterator) {
         this.runBatchIterator = new RunBatchIterator(run);
      } else {
         this.runBatchIterator.wrap(run);
      }

      this.iterator = this.runBatchIterator;
   }
}
