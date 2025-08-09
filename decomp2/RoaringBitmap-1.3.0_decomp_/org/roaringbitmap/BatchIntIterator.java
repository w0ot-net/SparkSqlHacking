package org.roaringbitmap;

public class BatchIntIterator implements IntIterator {
   private int i;
   private int mark;
   private int[] buffer;
   private BatchIterator delegate;

   private BatchIntIterator(BatchIterator delegate, int i, int mark, int[] buffer) {
      this.delegate = delegate;
      this.i = i;
      this.mark = mark;
      this.buffer = buffer;
   }

   BatchIntIterator(BatchIterator delegate, int[] buffer) {
      this(delegate, 0, -1, buffer);
   }

   public boolean hasNext() {
      if (this.i < this.mark) {
         return true;
      } else if (this.delegate.hasNext() && (this.mark = this.delegate.nextBatch(this.buffer)) != 0) {
         this.i = 0;
         return true;
      } else {
         return false;
      }
   }

   public int next() {
      return this.buffer[this.i++];
   }

   public IntIterator clone() {
      try {
         BatchIntIterator it = (BatchIntIterator)super.clone();
         it.delegate = this.delegate.clone();
         it.buffer = (int[])this.buffer.clone();
         return it;
      } catch (CloneNotSupportedException var2) {
         throw new IllegalStateException();
      }
   }
}
