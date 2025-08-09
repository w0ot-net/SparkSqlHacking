package org.roaringbitmap;

public interface ContainerBatchIterator extends Cloneable {
   int next(int var1, int[] var2, int var3);

   default int next(int key, int[] buffer) {
      return this.next(key, buffer, 0);
   }

   boolean hasNext();

   ContainerBatchIterator clone();

   void releaseContainer();

   void advanceIfNeeded(char var1);
}
