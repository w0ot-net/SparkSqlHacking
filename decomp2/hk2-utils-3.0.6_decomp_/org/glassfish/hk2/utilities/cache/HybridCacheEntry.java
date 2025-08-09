package org.glassfish.hk2.utilities.cache;

public interface HybridCacheEntry extends CacheEntry {
   Object getValue();

   boolean dropMe();
}
