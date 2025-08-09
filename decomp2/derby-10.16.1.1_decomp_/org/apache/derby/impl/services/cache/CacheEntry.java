package org.apache.derby.impl.services.cache;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;
import org.apache.derby.iapi.services.cache.Cacheable;

final class CacheEntry {
   private final ReentrantLock mutex = new ReentrantLock();
   private Cacheable cacheable;
   private int keepCount;
   private Condition forRemove;
   private Condition settingIdentity;
   private ReplacementPolicy.Callback callback;

   CacheEntry() {
      this.settingIdentity = this.mutex.newCondition();
   }

   void lock() {
      this.mutex.lock();
   }

   void waitUntilIdentityIsSet() {
      while(this.settingIdentity != null) {
         this.settingIdentity.awaitUninterruptibly();
      }

   }

   void unlock() {
      this.mutex.unlock();
   }

   void settingIdentityComplete() {
      this.settingIdentity.signalAll();
      this.settingIdentity = null;
   }

   void keep(boolean var1) {
      ++this.keepCount;
      if (var1) {
         this.callback.access();
      }

   }

   void unkeep() {
      --this.keepCount;
      if (this.forRemove != null && this.keepCount == 1) {
         this.forRemove.signal();
      }

   }

   boolean isKept() {
      return this.keepCount > 0;
   }

   void unkeepForRemove() {
      if (this.keepCount > 1) {
         this.forRemove = this.mutex.newCondition();

         while(this.keepCount > 1) {
            this.forRemove.awaitUninterruptibly();
         }

         this.forRemove = null;
      }

      --this.keepCount;
   }

   void setCacheable(Cacheable var1) {
      this.cacheable = var1;
   }

   Cacheable getCacheable() {
      return this.cacheable;
   }

   boolean isValid() {
      return this.settingIdentity == null && this.cacheable != null;
   }

   void setCallback(ReplacementPolicy.Callback var1) {
      this.callback = var1;
   }

   void free() {
      if (this.callback != null) {
         this.callback.free();
      }

      this.cacheable = null;
   }
}
