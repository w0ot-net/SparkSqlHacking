package org.apache.derby.impl.services.locks;

import java.util.ArrayDeque;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import org.apache.derby.iapi.services.locks.CompatibilitySpace;
import org.apache.derby.iapi.services.locks.Limit;
import org.apache.derby.iapi.services.locks.LockOwner;
import org.apache.derby.iapi.services.locks.Lockable;
import org.apache.derby.iapi.util.Matchable;
import org.apache.derby.shared.common.error.StandardException;

final class LockSpace implements CompatibilitySpace {
   private final HashMap groups = new HashMap();
   private final LockOwner owner;
   private static final int MAX_CACHED_GROUPS = 3;
   private final ArrayDeque spareGroups = new ArrayDeque(3);
   private Object callbackGroup;
   private int limit;
   private int nextLimitCall;
   private Limit callback;
   private boolean inLimit;

   LockSpace(LockOwner var1) {
      this.owner = var1;
   }

   public LockOwner getOwner() {
      return this.owner;
   }

   protected synchronized void addLock(Object var1, Lock var2) throws StandardException {
      Lock var3 = null;
      HashMap var4 = (HashMap)this.groups.get(var1);
      if (var4 == null) {
         var4 = this.getGroupMap(var1);
      } else if (var2.getCount() != 1) {
         var3 = (Lock)var4.get(var2);
      }

      if (var3 == null) {
         var3 = var2.copy();
         var4.put(var3, var3);
      }

      ++var3.count;
      if (!this.inLimit) {
         if (var1.equals(this.callbackGroup)) {
            int var5 = var4.size();
            if (var5 > this.nextLimitCall) {
               this.inLimit = true;
               this.callback.reached(this, var1, this.limit, new LockList(Collections.enumeration(var4.keySet())), var5);
               this.inLimit = false;
               int var6 = var4.size();
               if (var6 < this.limit / 2) {
                  this.nextLimitCall = this.limit;
               } else if (var6 < this.nextLimitCall / 2) {
                  this.nextLimitCall -= this.limit;
               } else {
                  this.nextLimitCall += this.limit;
               }
            }

         }
      }
   }

   synchronized void unlockGroup(LockTable var1, Object var2) {
      HashMap var3 = (HashMap)this.groups.remove(var2);
      if (var3 != null) {
         for(Lock var5 : var3.keySet()) {
            var1.unlock(var5, 0);
         }

         if (this.callbackGroup != null && var2.equals(this.callbackGroup)) {
            this.nextLimitCall = this.limit;
         }

         this.saveGroup(var3);
      }
   }

   private HashMap getGroupMap(Object var1) {
      HashMap var2 = (HashMap)this.spareGroups.poll();
      if (var2 == null) {
         var2 = new HashMap(5, 0.8F);
      }

      this.groups.put(var1, var2);
      return var2;
   }

   private void saveGroup(HashMap var1) {
      if (this.spareGroups.size() < 3) {
         this.spareGroups.offer(var1);
         var1.clear();
      }

   }

   synchronized void unlockGroup(LockTable var1, Object var2, Matchable var3) {
      HashMap var4 = (HashMap)this.groups.get(var2);
      if (var4 != null) {
         boolean var5 = true;
         Iterator var6 = var4.keySet().iterator();

         while(var6.hasNext()) {
            Lock var7 = (Lock)var6.next();
            if (!var3.match(var7.getLockable())) {
               var5 = false;
            } else {
               var1.unlock(var7, 0);
               var6.remove();
            }
         }

         if (var5) {
            this.groups.remove(var2);
            this.saveGroup(var4);
            if (this.callbackGroup != null && var2.equals(this.callbackGroup)) {
               this.nextLimitCall = this.limit;
            }
         }

      }
   }

   synchronized void transfer(Object var1, Object var2) {
      HashMap var3 = (HashMap)this.groups.get(var1);
      if (var3 != null) {
         HashMap var4 = (HashMap)this.groups.get(var2);
         if (var4 == null) {
            this.groups.put(var2, var3);
            this.clearLimit(var1);
            this.groups.remove(var1);
         } else {
            if (var4.size() < var3.size()) {
               this.mergeGroups(var4, var3);
               this.groups.put(var2, var3);
            } else {
               this.mergeGroups(var3, var4);
            }

            this.clearLimit(var1);
            this.groups.remove(var1);
         }
      }
   }

   private void mergeGroups(HashMap var1, HashMap var2) {
      for(Lock var4 : var1.keySet()) {
         Lock var5 = (Lock)var2.get(var4);
         if (var5 == null) {
            var2.put(var4, var4);
         } else {
            var5.count += var4.getCount();
         }
      }

   }

   synchronized int unlockReference(LockTable var1, Lockable var2, Object var3, Object var4) {
      HashMap var5 = (HashMap)this.groups.get(var4);
      if (var5 == null) {
         return 0;
      } else {
         Lock var6 = var1.unlockReference(this, var2, var3, var5);
         if (var6 == null) {
            return 0;
         } else if (var6.getCount() == 1) {
            if (var5.isEmpty()) {
               this.groups.remove(var4);
               this.saveGroup(var5);
               if (this.callbackGroup != null && var4.equals(this.callbackGroup)) {
                  this.nextLimitCall = this.limit;
               }
            }

            return 1;
         } else {
            --var6.count;
            var5.put(var6, var6);
            return 1;
         }
      }
   }

   synchronized boolean areLocksHeld(Object var1) {
      return this.groups.containsKey(var1);
   }

   synchronized boolean areLocksHeld() {
      return !this.groups.isEmpty();
   }

   synchronized boolean isLockHeld(Object var1, Lockable var2, Object var3) {
      HashMap var4 = (HashMap)this.groups.get(var1);
      return var4 == null ? false : var4.containsKey(new Lock(this, var2, var3));
   }

   synchronized void setLimit(Object var1, int var2, Limit var3) {
      this.callbackGroup = var1;
      this.nextLimitCall = this.limit = var2;
      this.callback = var3;
   }

   synchronized void clearLimit(Object var1) {
      if (var1.equals(this.callbackGroup)) {
         this.callbackGroup = null;
         this.nextLimitCall = this.limit = Integer.MAX_VALUE;
         this.callback = null;
      }

   }

   synchronized int deadlockCount(int var1) {
      int var2 = 0;

      for(HashMap var4 : this.groups.values()) {
         for(Lock var6 : var4.keySet()) {
            var2 += var6.getCount();
            if (var2 > var1) {
               return var2;
            }
         }
      }

      return var2;
   }
}
