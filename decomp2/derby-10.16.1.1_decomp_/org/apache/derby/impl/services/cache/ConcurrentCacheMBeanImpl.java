package org.apache.derby.impl.services.cache;

import org.apache.derby.mbeans.CacheManagerMBean;

final class ConcurrentCacheMBeanImpl implements CacheManagerMBean {
   private final ConcurrentCache cache;

   ConcurrentCacheMBeanImpl(ConcurrentCache var1) {
      this.cache = var1;
   }

   public void setCollectAccessCounts(boolean var1) {
      checkPermission();
      this.cache.setCollectAccessCounts(var1);
   }

   public boolean getCollectAccessCounts() {
      checkPermission();
      return this.cache.getCollectAccessCounts();
   }

   public long getHitCount() {
      checkPermission();
      return this.cache.getHitCount();
   }

   public long getMissCount() {
      checkPermission();
      return this.cache.getMissCount();
   }

   public long getEvictionCount() {
      checkPermission();
      return this.cache.getEvictionCount();
   }

   public long getMaxEntries() {
      checkPermission();
      return this.cache.getMaxEntries();
   }

   public long getAllocatedEntries() {
      checkPermission();
      return this.cache.getAllocatedEntries();
   }

   public long getUsedEntries() {
      checkPermission();
      return this.cache.getUsedEntries();
   }

   private static void checkPermission() {
   }
}
