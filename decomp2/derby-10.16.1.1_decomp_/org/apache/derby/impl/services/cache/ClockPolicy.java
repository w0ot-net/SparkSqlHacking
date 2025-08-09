package org.apache.derby.impl.services.cache;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import org.apache.derby.iapi.services.cache.Cacheable;
import org.apache.derby.shared.common.error.StandardException;

final class ClockPolicy implements ReplacementPolicy {
   private static final int MIN_ITEMS_TO_CHECK = 20;
   private static final float MAX_ROTATION = 0.2F;
   private static final float PART_OF_CLOCK_FOR_SHRINK = 0.1F;
   private final ConcurrentCache cacheManager;
   private final int maxSize;
   private final ArrayList clock;
   private int hand;
   private final AtomicInteger freeEntries = new AtomicInteger();
   private final AtomicBoolean isShrinking = new AtomicBoolean();

   ClockPolicy(ConcurrentCache var1, int var2, int var3) {
      this.cacheManager = var1;
      this.maxSize = var3;
      this.clock = new ArrayList(var2);
   }

   public int size() {
      synchronized(this.clock) {
         return this.clock.size();
      }
   }

   public void insertEntry(CacheEntry var1) throws StandardException {
      int var2;
      synchronized(this.clock) {
         var2 = this.clock.size();
         if (var2 < this.maxSize && this.freeEntries.get() == 0) {
            this.clock.add(new Holder(var1));
            return;
         }
      }

      if (var2 > this.maxSize) {
         BackgroundCleaner var3 = this.cacheManager.getBackgroundCleaner();
         if (var3 != null) {
            var3.scheduleShrink();
         } else {
            this.doShrink();
         }
      }

      Holder var8 = this.rotateClock(var1, var2 >= this.maxSize);
      if (var8 == null) {
         synchronized(this.clock) {
            this.clock.add(new Holder(var1));
         }
      }

   }

   private Holder moveHand() {
      synchronized(this.clock) {
         if (this.clock.isEmpty()) {
            return null;
         } else {
            if (this.hand >= this.clock.size()) {
               this.hand = 0;
            }

            return (Holder)this.clock.get(this.hand++);
         }
      }
   }

   private Holder rotateClock(CacheEntry var1, boolean var2) throws StandardException {
      int var3 = 0;
      if (var2) {
         synchronized(this.clock) {
            var3 = Math.max(20, (int)((float)this.clock.size() * 0.2F));
         }
      }

      while(var3-- > 0 || this.freeEntries.get() > 0) {
         Holder var4 = this.moveHand();
         if (var4 == null) {
            return null;
         }

         CacheEntry var5 = var4.getEntry();
         if (var5 != null) {
            if (var2) {
               var5.lock();

               Cacheable var6;
               try {
                  if (!this.isEvictable(var5, var4, true)) {
                     continue;
                  }

                  Cacheable var7 = var5.getCacheable();
                  if (!var7.isDirty()) {
                     var4.switchEntry(var1);
                     this.cacheManager.evictEntry(var7.getIdentity());
                     Holder var14 = var4;
                     return var14;
                  }

                  BackgroundCleaner var8 = this.cacheManager.getBackgroundCleaner();
                  if (var8 != null && var8.scheduleClean(var5)) {
                     continue;
                  }

                  var5.keep(false);
                  var6 = var7;
               } finally {
                  var5.unlock();
               }

               this.cacheManager.cleanAndUnkeepEntry(var5, var6);
            }
         } else if (var4.takeIfFree(var1)) {
            return var4;
         }
      }

      return null;
   }

   private boolean isEvictable(CacheEntry var1, Holder var2, boolean var3) {
      if (var2.getEntry() != var1) {
         return false;
      } else if (var1.isKept()) {
         return false;
      } else if (var2.recentlyUsed) {
         if (var3) {
            var2.recentlyUsed = false;
         }

         return false;
      } else {
         return true;
      }
   }

   private void removeHolder(int var1, Holder var2) {
      synchronized(this.clock) {
         Holder var4 = (Holder)this.clock.remove(var1);
      }
   }

   public void doShrink() {
      if (this.isShrinking.compareAndSet(false, true)) {
         try {
            this.shrinkMe();
         } finally {
            this.isShrinking.set(false);
         }
      }

   }

   private void shrinkMe() {
      int var1 = Math.max(1, (int)((float)this.maxSize * 0.1F));
      int var2;
      synchronized(this.clock) {
         var2 = this.hand;
      }

      while(var1-- > 0) {
         Holder var3;
         int var4;
         synchronized(this.clock) {
            var4 = this.clock.size();
            if (var2 >= var4) {
               var2 = 0;
            }

            var3 = (Holder)this.clock.get(var2);
         }

         int var5 = var2++;
         if (var4 <= this.maxSize) {
            break;
         }

         CacheEntry var6 = var3.getEntry();
         if (var6 == null) {
            if (var3.evictIfFree()) {
               this.removeHolder(var5, var3);
               var2 = var5;
            }
         } else {
            var6.lock();

            try {
               if (this.isEvictable(var6, var3, false)) {
                  Cacheable var7 = var6.getCacheable();
                  if (!var7.isDirty()) {
                     var3.setEvicted();
                     this.cacheManager.evictEntry(var7.getIdentity());
                     this.removeHolder(var5, var3);
                     var2 = var5;
                  }
               }
            } finally {
               var6.unlock();
            }
         }
      }

   }

   private class Holder implements ReplacementPolicy.Callback {
      boolean recentlyUsed;
      private CacheEntry entry;
      private Cacheable freedCacheable;
      private boolean evicted;

      Holder(CacheEntry var2) {
         this.entry = var2;
         var2.setCallback(this);
      }

      public void access() {
         this.recentlyUsed = true;
      }

      public synchronized void free() {
         this.freedCacheable = this.entry.getCacheable();
         this.entry = null;
         this.recentlyUsed = false;
         int var1 = ClockPolicy.this.freeEntries.incrementAndGet();
      }

      synchronized boolean takeIfFree(CacheEntry var1) {
         if (this.entry == null && !this.evicted) {
            int var2 = ClockPolicy.this.freeEntries.decrementAndGet();
            var1.setCacheable(this.freedCacheable);
            var1.setCallback(this);
            this.entry = var1;
            this.freedCacheable = null;
            return true;
         } else {
            return false;
         }
      }

      synchronized CacheEntry getEntry() {
         return this.entry;
      }

      synchronized void switchEntry(CacheEntry var1) {
         var1.setCallback(this);
         var1.setCacheable(this.entry.getCacheable());
         this.entry = var1;
      }

      synchronized boolean evictIfFree() {
         if (this.entry == null && !this.evicted) {
            int var1 = ClockPolicy.this.freeEntries.decrementAndGet();
            this.evicted = true;
            return true;
         } else {
            return false;
         }
      }

      synchronized void setEvicted() {
         this.evicted = true;
         this.entry = null;
      }

      synchronized boolean isEvicted() {
         return this.evicted;
      }
   }
}
