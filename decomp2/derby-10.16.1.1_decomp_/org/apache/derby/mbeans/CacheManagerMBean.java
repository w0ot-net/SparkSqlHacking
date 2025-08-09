package org.apache.derby.mbeans;

public interface CacheManagerMBean {
   void setCollectAccessCounts(boolean var1);

   boolean getCollectAccessCounts();

   long getHitCount();

   long getMissCount();

   long getEvictionCount();

   long getMaxEntries();

   long getAllocatedEntries();

   long getUsedEntries();
}
