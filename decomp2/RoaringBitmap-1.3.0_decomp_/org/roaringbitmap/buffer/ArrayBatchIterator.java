package org.roaringbitmap.buffer;

import java.nio.CharBuffer;
import org.roaringbitmap.ContainerBatchIterator;

public final class ArrayBatchIterator implements ContainerBatchIterator {
   private int index = 0;
   private MappeableArrayContainer array;

   public ArrayBatchIterator(MappeableArrayContainer array) {
      this.wrap(array);
   }

   public int next(int key, int[] buffer, int offset) {
      int consumed = 0;

      for(CharBuffer data = this.array.content; offset + consumed < buffer.length && this.index < this.array.getCardinality(); buffer[offset + consumed++] = key + data.get(this.index++)) {
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
      int position = BufferUtil.unsignedBinarySearch(this.array.content, 0, this.array.getCardinality(), target);
      this.index = position < 0 ? -position - 1 : position;
   }

   public void wrap(MappeableArrayContainer array) {
      this.array = array;
      this.index = 0;
   }
}
