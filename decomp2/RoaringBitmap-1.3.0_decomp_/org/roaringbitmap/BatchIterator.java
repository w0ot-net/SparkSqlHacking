package org.roaringbitmap;

public interface BatchIterator extends Cloneable {
   int nextBatch(int[] var1);

   boolean hasNext();

   BatchIterator clone();

   default IntIterator asIntIterator(int[] buffer) {
      return new BatchIntIterator(this, buffer);
   }

   void advanceIfNeeded(int var1);
}
