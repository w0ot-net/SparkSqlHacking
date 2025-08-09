package org.apache.datasketches.hll;

interface AuxHashMap {
   AuxHashMap copy();

   int getAuxCount();

   int[] getAuxIntArr();

   int getCompactSizeBytes();

   PairIterator getIterator();

   int getLgAuxArrInts();

   int getUpdatableSizeBytes();

   boolean isMemory();

   boolean isOffHeap();

   void mustAdd(int var1, int var2);

   int mustFindValueFor(int var1);

   void mustReplace(int var1, int var2);
}
