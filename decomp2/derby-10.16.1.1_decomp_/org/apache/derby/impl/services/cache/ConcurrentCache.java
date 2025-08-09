package org.apache.derby.impl.services.cache;

import java.util.ArrayList;
import java.util.Collection;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
import org.apache.derby.iapi.services.cache.CacheManager;
import org.apache.derby.iapi.services.cache.Cacheable;
import org.apache.derby.iapi.services.cache.CacheableFactory;
import org.apache.derby.iapi.services.daemon.DaemonService;
import org.apache.derby.iapi.services.jmx.ManagementService;
import org.apache.derby.iapi.services.monitor.Monitor;
import org.apache.derby.iapi.util.Matchable;
import org.apache.derby.mbeans.CacheManagerMBean;
import org.apache.derby.shared.common.error.StandardException;

final class ConcurrentCache implements CacheManager {
   private final ConcurrentHashMap cache;
   private final CacheableFactory holderFactory;
   private final String name;
   private final int maxSize;
   private final ReplacementPolicy replacementPolicy;
   private Object mbean;
   private volatile boolean collectAccessCounts;
   private final AtomicLong hits = new AtomicLong();
   private final AtomicLong misses = new AtomicLong();
   private final AtomicLong evictions = new AtomicLong();
   private volatile boolean stopped;
   private BackgroundCleaner cleaner;

   ConcurrentCache(CacheableFactory var1, String var2, int var3, int var4) {
      this.cache = new ConcurrentHashMap(var3);
      this.replacementPolicy = new ClockPolicy(this, var3, var4);
      this.holderFactory = var1;
      this.name = var2;
      this.maxSize = var4;
   }

   ReplacementPolicy getReplacementPolicy() {
      return this.replacementPolicy;
   }

   private CacheEntry getEntry(Object var1) {
      CacheEntry var2 = (CacheEntry)this.cache.get(var1);

      while(true) {
         while(var2 == null) {
            CacheEntry var3 = new CacheEntry();
            var3.lock();
            CacheEntry var4 = (CacheEntry)this.cache.putIfAbsent(var1, var3);
            if (var4 == null) {
               return var3;
            }

            var2 = var4;
         }

         var2.lock();
         var2.waitUntilIdentityIsSet();
         if (var2.isValid()) {
            return var2;
         }

         var2.unlock();
         var2 = (CacheEntry)this.cache.get(var1);
      }
   }

   private void removeEntry(Object var1) {
      CacheEntry var2 = (CacheEntry)this.cache.remove(var1);
      Cacheable var3 = var2.getCacheable();
      if (var3 != null && var3.getIdentity() != null) {
         var3.clearIdentity();
      }

      var2.free();
   }

   void evictEntry(Object var1) {
      CacheEntry var2 = (CacheEntry)this.cache.remove(var1);
      var2.getCacheable().clearIdentity();
      var2.setCacheable((Cacheable)null);
      this.countEviction();
   }

   private Cacheable insertIntoFreeSlot(Object var1, CacheEntry var2) throws StandardException {
      try {
         this.replacementPolicy.insertEntry(var2);
      } catch (StandardException var4) {
         this.removeEntry(var1);
         throw var4;
      }

      Cacheable var3 = var2.getCacheable();
      if (var3 == null) {
         var3 = this.holderFactory.newCacheable(this);
      }

      var2.keep(true);
      return var3;
   }

   private void settingIdentityComplete(Object var1, CacheEntry var2, Cacheable var3) {
      var2.lock();

      try {
         var2.settingIdentityComplete();
         if (var3 != null) {
            var2.setCacheable(var3);
         } else {
            this.removeEntry(var1);
         }
      } finally {
         var2.unlock();
      }

   }

   public Cacheable find(Object var1) throws StandardException {
      if (this.stopped) {
         return null;
      } else {
         CacheEntry var2 = this.getEntry(var1);

         Cacheable var13;
         try {
            var13 = var2.getCacheable();
            if (var13 != null) {
               var2.keep(true);
               this.countHit();
               Cacheable var4 = var13;
               return var4;
            }

            var13 = this.insertIntoFreeSlot(var1, var2);
            this.countMiss();
         } finally {
            var2.unlock();
         }

         Cacheable var14 = null;

         try {
            var14 = var13.setIdentity(var1);
         } finally {
            this.settingIdentityComplete(var1, var2, var14);
         }

         return var14;
      }
   }

   public Cacheable findCached(Object var1) throws StandardException {
      if (this.stopped) {
         return null;
      } else {
         CacheEntry var2 = (CacheEntry)this.cache.get(var1);
         if (var2 == null) {
            this.countMiss();
            return null;
         } else {
            var2.lock();

            Cacheable var4;
            try {
               var2.waitUntilIdentityIsSet();
               Cacheable var3 = var2.getCacheable();
               if (var3 != null) {
                  this.countHit();
                  var2.keep(true);
               } else {
                  this.countMiss();
               }

               var4 = var3;
            } finally {
               var2.unlock();
            }

            return var4;
         }
      }
   }

   public Cacheable create(Object var1, Object var2) throws StandardException {
      if (this.stopped) {
         return null;
      } else {
         CacheEntry var3 = new CacheEntry();
         var3.lock();
         if (this.cache.putIfAbsent(var1, var3) != null) {
            throw StandardException.newException("XBCA0.S", new Object[]{this.name, var1});
         } else {
            Cacheable var4;
            try {
               var4 = this.insertIntoFreeSlot(var1, var3);
            } finally {
               var3.unlock();
            }

            Cacheable var5 = null;

            try {
               var5 = var4.createIdentity(var1, var2);
            } finally {
               this.settingIdentityComplete(var1, var3, var5);
            }

            return var5;
         }
      }
   }

   public void release(Cacheable var1) {
      CacheEntry var2 = (CacheEntry)this.cache.get(var1.getIdentity());
      var2.lock();

      try {
         var2.unkeep();
      } finally {
         var2.unlock();
      }

   }

   public void remove(Cacheable var1) throws StandardException {
      Object var2 = var1.getIdentity();
      CacheEntry var3 = (CacheEntry)this.cache.get(var2);
      var3.lock();

      try {
         var3.unkeepForRemove();
         var1.clean(true);
         this.removeEntry(var2);
      } finally {
         var3.unlock();
      }

   }

   public void cleanAll() throws StandardException {
      this.cleanCache((Matchable)null);
   }

   public void clean(Matchable var1) throws StandardException {
      this.cleanCache(var1);
   }

   private void cleanCache(Matchable var1) throws StandardException {
      for(CacheEntry var3 : this.cache.values()) {
         var3.lock();

         Cacheable var4;
         try {
            if (!var3.isValid()) {
               continue;
            }

            Cacheable var5 = var3.getCacheable();
            if (var1 != null && !var1.match(var5.getIdentity()) || !var5.isDirty()) {
               continue;
            }

            var3.keep(false);
            var4 = var5;
         } finally {
            var3.unlock();
         }

         this.cleanAndUnkeepEntry(var3, var4);
      }

   }

   void cleanEntry(CacheEntry var1) throws StandardException {
      var1.lock();

      Cacheable var2;
      label38: {
         try {
            var2 = var1.getCacheable();
            if (var2 != null) {
               var1.keep(false);
               break label38;
            }
         } finally {
            var1.unlock();
         }

         return;
      }

      this.cleanAndUnkeepEntry(var1, var2);
   }

   void cleanAndUnkeepEntry(CacheEntry var1, Cacheable var2) throws StandardException {
      try {
         var2.clean(false);
      } finally {
         var1.lock();

         try {
            var1.unkeep();
         } finally {
            var1.unlock();
         }
      }

   }

   public void ageOut() {
      for(CacheEntry var2 : this.cache.values()) {
         var2.lock();

         try {
            if (!var2.isKept()) {
               Cacheable var3 = var2.getCacheable();
               if (var3 != null && !var3.isDirty()) {
                  this.removeEntry(var3.getIdentity());
               }
            }
         } finally {
            var2.unlock();
         }
      }

   }

   public void shutdown() throws StandardException {
      this.stopped = true;
      this.cleanAll();
      this.ageOut();
      if (this.cleaner != null) {
         this.cleaner.unsubscribe();
      }

      this.deregisterMBean();
   }

   public void useDaemonService(DaemonService var1) {
      if (this.cleaner != null) {
         this.cleaner.unsubscribe();
      }

      this.cleaner = new BackgroundCleaner(this, var1, Math.max(this.maxSize / 10, 1));
   }

   BackgroundCleaner getBackgroundCleaner() {
      return this.cleaner;
   }

   public boolean discard(Matchable var1) {
      boolean var2 = true;

      for(CacheEntry var4 : this.cache.values()) {
         var4.lock();

         try {
            Cacheable var5 = var4.getCacheable();
            if (var5 != null && (var1 == null || var1.match(var5.getIdentity()))) {
               if (var4.isKept()) {
                  var2 = false;
               } else {
                  this.removeEntry(var5.getIdentity());
               }
            }
         } finally {
            var4.unlock();
         }
      }

      return var2;
   }

   public Collection values() {
      ArrayList var1 = new ArrayList();

      for(CacheEntry var3 : this.cache.values()) {
         var3.lock();

         try {
            Cacheable var4 = var3.getCacheable();
            if (var4 != null) {
               var1.add(var4);
            }
         } finally {
            var3.unlock();
         }
      }

      return var1;
   }

   public void registerMBean(String var1) throws StandardException {
      ManagementService var2 = (ManagementService)getSystemModule("org.apache.derby.iapi.services.jmx.ManagementService");
      if (var2 != null) {
         ConcurrentCacheMBeanImpl var10002 = new ConcurrentCacheMBeanImpl(this);
         String var10004 = this.name;
         this.mbean = var2.registerMBean(var10002, CacheManagerMBean.class, "type=CacheManager,name=" + var10004 + ",db=" + var2.quotePropertyValue(var1));
      }

   }

   public void deregisterMBean() {
      if (this.mbean != null) {
         ManagementService var1 = (ManagementService)getSystemModule("org.apache.derby.iapi.services.jmx.ManagementService");
         if (var1 != null) {
            var1.unregisterMBean(this.mbean);
         }

         this.mbean = null;
      }

   }

   private void countHit() {
      if (this.collectAccessCounts) {
         this.hits.getAndIncrement();
      }

   }

   private void countMiss() {
      if (this.collectAccessCounts) {
         this.misses.getAndIncrement();
      }

   }

   private void countEviction() {
      if (this.collectAccessCounts) {
         this.evictions.getAndIncrement();
      }

   }

   void setCollectAccessCounts(boolean var1) {
      this.collectAccessCounts = var1;
   }

   boolean getCollectAccessCounts() {
      return this.collectAccessCounts;
   }

   long getHitCount() {
      return this.hits.get();
   }

   long getMissCount() {
      return this.misses.get();
   }

   long getEvictionCount() {
      return this.evictions.get();
   }

   long getMaxEntries() {
      return (long)this.maxSize;
   }

   long getAllocatedEntries() {
      return (long)this.replacementPolicy.size();
   }

   long getUsedEntries() {
      return (long)this.cache.size();
   }

   private static Object getSystemModule(String var0) {
      return Monitor.getSystemModule(var0);
   }
}
