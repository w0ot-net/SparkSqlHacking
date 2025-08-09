package org.glassfish.hk2.utilities.general;

import org.glassfish.hk2.utilities.cache.CacheKeyFilter;

public interface WeakHashLRU {
   void add(Object var1);

   boolean contains(Object var1);

   boolean remove(Object var1);

   void releaseMatching(CacheKeyFilter var1);

   int size();

   Object remove();

   void clear();

   void clearStaleReferences();
}
