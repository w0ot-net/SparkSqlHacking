package org.glassfish.hk2.utilities.cache;

public interface WeakCARCache {
   Object compute(Object var1);

   int getKeySize();

   int getValueSize();

   int getT1Size();

   int getT2Size();

   int getB1Size();

   int getB2Size();

   void clear();

   int getMaxSize();

   Computable getComputable();

   boolean remove(Object var1);

   void releaseMatching(CacheKeyFilter var1);

   void clearStaleReferences();

   int getP();

   String dumpAllLists();

   double getHitRate();
}
