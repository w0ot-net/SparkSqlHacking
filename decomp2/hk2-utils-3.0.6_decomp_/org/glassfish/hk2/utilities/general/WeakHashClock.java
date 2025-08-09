package org.glassfish.hk2.utilities.general;

import java.util.Map;
import org.glassfish.hk2.utilities.cache.CacheKeyFilter;

public interface WeakHashClock {
   void put(Object var1, Object var2);

   Object get(Object var1);

   Object remove(Object var1);

   void releaseMatching(CacheKeyFilter var1);

   int size();

   Map.Entry next();

   void clear();

   void clearStaleReferences();

   boolean hasWeakKeys();
}
