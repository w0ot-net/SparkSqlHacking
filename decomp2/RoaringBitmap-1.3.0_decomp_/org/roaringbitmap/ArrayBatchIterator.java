package org.roaringbitmap;

public final class ArrayBatchIterator implements ContainerBatchIterator {
   private int index = 0;
   private ArrayContainer array;

   public ArrayBatchIterator(ArrayContainer array) {
      this.wrap(array);
   }

   public int next(int key, int[] buffer, int offset) {
      int consumed = 0;

      for(char[] data = this.array.content; offset + consumed < buffer.length && this.index < this.array.getCardinality(); buffer[offset + consumed++] = key + data[this.index++]) {
      }

      return consumed;
   }

   public boolean hasNext() {
      return this.index < this.array.getCardinality();
   }

   public ContainerBatchIterator clone() {
      try {
         return (ContainerBatchIterator)super.clone();
      } catch (CloneNotSupportedException e) {
         throw new IllegalStateException(e);
      }
   }

   public void releaseContainer() {
      this.array = null;
   }

   public void advanceIfNeeded(char target) {
      int position = Util.unsignedBinarySearch(this.array.content, 0, this.array.getCardinality(), target);
      this.index = position < 0 ? -position - 1 : position;
   }

   void wrap(ArrayContainer array) {
      this.array = array;
      this.index = 0;
   }
}
