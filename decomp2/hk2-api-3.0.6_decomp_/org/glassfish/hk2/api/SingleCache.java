package org.glassfish.hk2.api;

public interface SingleCache {
   Object getCache();

   boolean isCacheSet();

   void setCache(Object var1);

   void releaseCache();
}
