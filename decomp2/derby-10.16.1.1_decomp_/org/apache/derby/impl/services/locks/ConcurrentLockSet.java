package org.apache.derby.impl.services.locks;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;
import org.apache.derby.iapi.services.locks.CompatibilitySpace;
import org.apache.derby.iapi.services.locks.Latch;
import org.apache.derby.iapi.services.locks.Lockable;
import org.apache.derby.shared.common.error.StandardException;

final class ConcurrentLockSet implements LockTable {
   private final AbstractPool factory;
   private final ConcurrentHashMap locks;
   private ArrayList seenByDeadlockDetection;
   private int deadlockTimeout = 20000;
   private int waitTimeout = 60000;
   private boolean deadlockTrace;
   private final AtomicInteger blockCount;

   ConcurrentLockSet(AbstractPool var1) {
      this.factory = var1;
      this.blockCount = new AtomicInteger();
      this.locks = new ConcurrentHashMap();
   }

   private Entry getEntry(Lockable var1) {
      Entry var2 = (Entry)this.locks.get(var1);

      while(true) {
         if (var2 != null) {
            var2.lock();
            if (var2.control != null) {
               return var2;
            }
         } else {
            var2 = new Entry();
            var2.lock();
         }

         Entry var3 = (Entry)this.locks.putIfAbsent(var1, var2);
         if (var3 == null) {
            return var2;
         }

         var2.unlock();
         var2 = var3;
      }
   }

   private Object[] checkDeadlock(Entry var1, ActiveLock var2, byte var3) {
      LockControl var4 = (LockControl)var1.control;
      var1.enterDeadlockDetection();
      synchronized(Deadlock.class) {
         Object[] var6;
         try {
            var6 = Deadlock.look(this.factory, this, var4, var2, var3);
         } finally {
            for(Entry var11 : this.seenByDeadlockDetection) {
               var11.unlock();
            }

            this.seenByDeadlockDetection = null;
            var1.exitDeadlockDetection();
         }

         return var6;
      }
   }

   public Lock lockObject(CompatibilitySpace var1, Lockable var2, Object var3, int var4) throws StandardException {
      Object var7 = null;
      boolean var8 = false;
      Entry var9 = this.getEntry(var2);

      LockControl var5;
      Lock var6;
      try {
         Control var10 = var9.control;
         if (var10 == null) {
            Lock var45 = new Lock(var1, var2, var3);
            var45.grant();
            var9.control = var45;
            Lock var12 = var45;
            return var12;
         }

         var5 = var10.getLockControl();
         if (var5 != var10) {
            var9.control = var5;
         }

         var6 = var5.addLock(this, var1, var3);
         if (var6.getCount() != 0) {
            Lock var44 = var6;
            return var44;
         }

         var8 = var4 == 0 && var1.getOwner().isNestedOwner() && var5.blockedByParent(var6);
         if (AbstractPool.noLockWait(var4, var1) || var8) {
            var5.giveUpWait(var6, this);
            Object var11 = null;
            return (Lock)var11;
         }
      } finally {
         var9.unlock();
         if (var8) {
            throw StandardException.newException("40XL2", new Object[0]);
         }

      }

      boolean var43 = false;
      int var46;
      if (var4 == -1) {
         var43 = true;
         if ((var46 = this.deadlockTimeout) == -1) {
            var46 = 20000;
         }
      } else {
         if (var4 == -2) {
            var4 = var46 = this.waitTimeout;
         } else {
            var46 = var4;
         }

         if (this.deadlockTimeout >= 0) {
            if (var46 < 0) {
               var43 = true;
               var46 = this.deadlockTimeout;
            } else if (this.deadlockTimeout < var46) {
               var43 = true;
               var46 = this.deadlockTimeout;
               var4 -= this.deadlockTimeout;
            }
         }
      }

      ActiveLock var47 = (ActiveLock)var6;
      var6 = null;
      int var13 = 0;
      long var14 = 0L;

      while(true) {
         byte var16 = 0;
         ActiveLock var17 = null;
         Object[] var18 = null;

         try {
            try {
               var16 = var47.waitForGrant(var46);
            } catch (StandardException var38) {
               var17 = var5.getNextWaiter(var47, true, this);
               throw var38;
            }

            Enumeration var20 = null;
            long var21 = 0L;
            var9.lock();

            boolean var19;
            try {
               if (var5.isGrantable(var5.firstWaiter() == var47, var1, var3)) {
                  var5.grant(var47);
                  var17 = var5.getNextWaiter(var47, true, this);
                  ActiveLock var23 = var47;
                  return var23;
               }

               var47.clearPotentiallyGranted();
               var19 = var16 != 1;
               if (var16 == 0 && var43 || var16 == 2) {
                  var18 = this.checkDeadlock(var9, var47, var16);
                  if (var18 == null) {
                     var43 = false;
                     var46 = var4;
                     var14 = 0L;
                     var19 = false;
                  } else {
                     var19 = true;
                  }
               }

               var17 = var5.getNextWaiter(var47, var19, this);
            } finally {
               var9.unlock();
            }

            if (var19) {
               if (this.deadlockTrace && var18 == null) {
                  var21 = System.currentTimeMillis();
                  var20 = this.factory.makeVirtualLockTable();
               }

               if (var18 == null) {
                  if (var16 == 3) {
                     throw StandardException.newException("08000", new Object[0]);
                  }

                  if (this.deadlockTrace) {
                     throw Timeout.buildException(var47, var20, var21);
                  }

                  StandardException var52 = StandardException.newException("40XL1", new Object[0]);
                  throw var52;
               }

               throw Deadlock.buildException(this.factory, var18);
            }
         } finally {
            if (var17 != null) {
               var17.wakeUp((byte)1);
               Object var49 = null;
            }

         }

         if (var46 != -1) {
            if (var16 != 0) {
               ++var13;
            }

            if (var13 > 5) {
               long var50 = System.currentTimeMillis();
               if (var14 != 0L) {
                  long var51 = var50 - var14;
                  var46 = (int)((long)var46 - var51);
               }

               var14 = var50;
            }
         }
      }
   }

   public void unlock(Latch var1, int var2) {
      Entry var3 = (Entry)this.locks.get(var1.getLockable());
      var3.lock();

      try {
         this.unlock(var3, var1, var2);
      } finally {
         var3.unlock();
      }

   }

   private void unlock(Entry var1, Latch var2, int var3) {
      boolean var4 = false;
      ActiveLock var5 = null;
      Control var6 = var1.control;
      var4 = var6.unlock(var2, var3);
      Object var8 = null;
      boolean var7 = true;
      if (var4) {
         var5 = var6.firstWaiter();
         if (var5 != null) {
            var7 = false;
            if (!var5.setPotentiallyGranted()) {
               var5 = null;
            }
         }
      }

      if (var7) {
         if (var6.isEmpty()) {
            this.locks.remove(var6.getLockable());
            var1.control = null;
         }

      } else {
         if (var4 && var5 != null) {
            var5.wakeUp((byte)1);
         }

      }
   }

   public Lock unlockReference(CompatibilitySpace var1, Lockable var2, Object var3, Map var4) {
      Entry var5 = (Entry)this.locks.get(var2);
      if (var5 == null) {
         return null;
      } else {
         var5.lock();

         Lock var7;
         try {
            Control var6 = var5.control;
            if (var6 != null) {
               var7 = var6.getLock(var1, var3);
               if (var7 == null) {
                  Object var14 = null;
                  return (Lock)var14;
               }

               Lock var8 = (Lock)var4.remove(var7);
               if (var8 != null) {
                  this.unlock(var5, var8, 1);
               }

               Lock var9 = var8;
               return var9;
            }

            var7 = null;
         } finally {
            var5.unlock();
         }

         return var7;
      }
   }

   public boolean zeroDurationLockObject(CompatibilitySpace var1, Lockable var2, Object var3, int var4) throws StandardException {
      Entry var5 = (Entry)this.locks.get(var2);
      if (var5 == null) {
         return true;
      } else {
         var5.lock();

         label93: {
            boolean var7;
            try {
               Control var6 = var5.control;
               if (var6 != null) {
                  if (var6.isGrantable(true, var1, var3)) {
                     var7 = true;
                     return var7;
                  }

                  if (!AbstractPool.noLockWait(var4, var1)) {
                     break label93;
                  }

                  var7 = false;
                  return var7;
               }

               var7 = true;
            } finally {
               var5.unlock();
            }

            return var7;
         }

         Lock var11 = this.lockObject(var1, var2, var3, var4);
         this.unlock(var11, 1);
         return true;
      }
   }

   public void setDeadlockTimeout(int var1) {
      this.deadlockTimeout = var1;
   }

   public void setWaitTimeout(int var1) {
      this.waitTimeout = var1;
   }

   public int getWaitTimeout() {
      return this.waitTimeout;
   }

   public void setDeadlockTrace(boolean var1) {
      this.deadlockTrace = var1;
   }

   private String toDebugString() {
      return null;
   }

   public void addWaiters(Map var1) {
      this.seenByDeadlockDetection = new ArrayList(this.locks.size());

      for(Entry var3 : this.locks.values()) {
         this.seenByDeadlockDetection.add(var3);
         var3.lockForDeadlockDetection();
         if (var3.control != null) {
            var3.control.addWaiters(var1);
         }
      }

   }

   public Map shallowClone() {
      HashMap var1 = new HashMap();

      for(Entry var3 : this.locks.values()) {
         var3.lock();

         try {
            Control var4 = var3.control;
            if (var4 != null) {
               var1.put(var4.getLockable(), var4.shallowClone());
            }
         } finally {
            var3.unlock();
         }
      }

      return var1;
   }

   public void oneMoreWaiter() {
      this.blockCount.incrementAndGet();
   }

   public void oneLessWaiter() {
      this.blockCount.decrementAndGet();
   }

   public boolean anyoneBlocked() {
      int var1 = this.blockCount.get();
      return var1 != 0;
   }

   private static final class Entry {
      Control control;
      private final ReentrantLock mutex = new ReentrantLock();
      private Condition deadlockDetection;

      void lock() {
         this.mutex.lock();

         while(this.deadlockDetection != null) {
            this.deadlockDetection.awaitUninterruptibly();
         }

      }

      void unlock() {
         this.mutex.unlock();
      }

      void lockForDeadlockDetection() {
         this.mutex.lock();
      }

      void enterDeadlockDetection() {
         this.deadlockDetection = this.mutex.newCondition();
         this.mutex.unlock();
      }

      void exitDeadlockDetection() {
         this.mutex.lock();
         this.deadlockDetection.signalAll();
         this.deadlockDetection = null;
      }
   }
}
