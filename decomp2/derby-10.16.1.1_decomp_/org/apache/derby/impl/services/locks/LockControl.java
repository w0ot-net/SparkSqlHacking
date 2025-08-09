package org.apache.derby.impl.services.locks;

import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import org.apache.derby.iapi.services.locks.CompatibilitySpace;
import org.apache.derby.iapi.services.locks.Latch;
import org.apache.derby.iapi.services.locks.LockOwner;
import org.apache.derby.iapi.services.locks.Lockable;

final class LockControl implements Control {
   private final Lockable ref;
   private Lock firstGrant;
   private List granted;
   private List waiting;
   private Lock lastPossibleSkip;

   protected LockControl(Lock var1, Lockable var2) {
      this.ref = var2;
      this.firstGrant = var1;
   }

   private LockControl(LockControl var1) {
      this.ref = var1.ref;
      this.firstGrant = var1.firstGrant;
      if (var1.granted != null) {
         this.granted = new LinkedList(var1.granted);
      }

      if (var1.waiting != null) {
         this.waiting = new LinkedList(var1.waiting);
      }

      this.lastPossibleSkip = var1.lastPossibleSkip;
   }

   public LockControl getLockControl() {
      return this;
   }

   public boolean isEmpty() {
      if (!this.isUnlocked()) {
         return false;
      } else {
         return this.waiting == null || this.waiting.isEmpty();
      }
   }

   void grant(Lock var1) {
      var1.grant();
      List var2 = this.granted;
      if (var2 == null) {
         if (this.firstGrant == null) {
            this.firstGrant = var1;
         } else {
            var2 = this.granted = new LinkedList();
            var2.add(this.firstGrant);
            var2.add(var1);
            this.firstGrant = null;
         }
      } else {
         var2.add(var1);
      }

   }

   public boolean unlock(Latch var1, int var2) {
      if (var2 == 0) {
         var2 = var1.getCount();
      }

      List var3 = this.granted;
      int var4 = 0;

      while(var2 > 0) {
         Lock var5;
         if (this.firstGrant != null) {
            var5 = this.firstGrant;
         } else {
            var4 = var3.indexOf(var1);
            var5 = (Lock)var3.get(var4);
         }

         var2 -= var5.unlock(var2);
         if (var5.getCount() == 0) {
            if (this.firstGrant == var5) {
               this.firstGrant = null;
            } else {
               var3.remove(var4);
            }
         }
      }

      return true;
   }

   public boolean isGrantable(boolean var1, CompatibilitySpace var2, Object var3) {
      if (this.isUnlocked()) {
         return true;
      } else {
         boolean var4 = false;
         Lockable var5 = this.ref;
         List var6 = this.granted;
         boolean var7 = var5.lockerAlwaysCompatible();
         int var8 = 0;
         int var9 = this.firstGrant == null ? var6.size() : 0;

         do {
            Lock var10 = this.firstGrant == null ? (Lock)var6.get(var8) : this.firstGrant;
            boolean var11 = var10.getCompatabilitySpace() == var2;
            if (var11 && var7) {
               var4 = true;
            } else {
               if (!var5.requestCompatible(var3, var10.getQualifier())) {
                  var4 = false;
                  break;
               }

               if (var11 || var1) {
                  var4 = true;
               }
            }

            ++var8;
         } while(var8 < var9);

         return var4;
      }
   }

   public Lock addLock(LockTable var1, CompatibilitySpace var2, Object var3) {
      boolean var4 = false;
      boolean var5 = this.firstWaiter() != null;
      Lock var6 = null;
      Lockable var7 = this.ref;
      boolean var8 = false;
      boolean var9 = false;
      if (!this.isUnlocked()) {
         boolean var10 = var7.lockerAlwaysCompatible();
         int var11 = 0;
         int var12 = this.firstGrant == null ? this.granted.size() : 0;

         do {
            Lock var13 = this.firstGrant == null ? (Lock)this.granted.get(var11) : this.firstGrant;
            boolean var14 = var13.getCompatabilitySpace() == var2;
            if (var14 && var10) {
               var8 = true;
               if (var9) {
                  break;
               }

               if (var3 == var13.getQualifier()) {
                  var6 = var13;
               }

               var4 = true;
            } else {
               if (!var7.requestCompatible(var3, var13.getQualifier())) {
                  var4 = false;
                  var6 = null;
                  if (var8) {
                     break;
                  }

                  var9 = true;
               }

               if (!var9 && (var14 || !var5)) {
                  var4 = true;
               }
            }

            ++var11;
         } while(var11 < var12);
      }

      if (var6 != null) {
         ++var6.count;
         return var6;
      } else if (var4) {
         var6 = new Lock(var2, var7, var3);
         this.grant(var6);
         return var6;
      } else {
         ActiveLock var16 = new ActiveLock(var2, var7, var3);
         if (var8) {
            var16.canSkip = true;
         }

         if (this.waiting == null) {
            this.waiting = new LinkedList();
         }

         this.addWaiter(var16, var1);
         if (var16.canSkip) {
            this.lastPossibleSkip = var16;
         }

         return var16;
      }
   }

   protected boolean isUnlocked() {
      if (this.firstGrant != null) {
         return false;
      } else {
         List var1 = this.granted;
         return var1 == null || var1.isEmpty();
      }
   }

   public ActiveLock firstWaiter() {
      return this.waiting != null && !this.waiting.isEmpty() ? (ActiveLock)this.waiting.get(0) : null;
   }

   ActiveLock getNextWaiter(ActiveLock var1, boolean var2, LockTable var3) {
      ActiveLock var4 = null;
      if (var2 && this.waiting.get(0) == var1) {
         this.popFrontWaiter(var3);
         var4 = this.firstWaiter();
      } else if (this.lastPossibleSkip != null && this.lastPossibleSkip != var1) {
         int var5 = this.waiting.indexOf(var1);
         int var6 = var2 ? var5 : -1;
         if (var5 != this.waiting.size() - 1) {
            ListIterator var7 = this.waiting.listIterator(var5 + 1);

            while(var7.hasNext()) {
               ActiveLock var8 = (ActiveLock)var7.next();
               if (var8.canSkip) {
                  var4 = var8;
                  break;
               }
            }
         }

         if (var2) {
            this.removeWaiter(var6, var3);
         }
      } else if (var2) {
         this.removeWaiter(var1, var3);
      }

      if (var2 && var1 == this.lastPossibleSkip) {
         this.lastPossibleSkip = null;
      }

      if (var4 != null && !var4.setPotentiallyGranted()) {
         var4 = null;
      }

      return var4;
   }

   public Lockable getLockable() {
      return this.ref;
   }

   public Lock getFirstGrant() {
      return this.firstGrant;
   }

   public List getGranted() {
      return this.granted;
   }

   public List getWaiting() {
      return this.waiting;
   }

   protected void giveUpWait(Object var1, LockTable var2) {
      this.removeWaiter(var1, var2);
      if (var1 == this.lastPossibleSkip) {
         this.lastPossibleSkip = null;
      }

   }

   public void addWaiters(Map var1) {
      if (this.waiting != null && !this.waiting.isEmpty()) {
         Object var2 = this;

         ActiveLock var4;
         for(ListIterator var3 = this.waiting.listIterator(); var3.hasNext(); var2 = var4) {
            var4 = (ActiveLock)var3.next();
            CompatibilitySpace var5 = var4.getCompatabilitySpace();
            var1.put(var5, var4);
            var1.put(var4, var2);
         }

      }
   }

   List getGrants() {
      LinkedList var1;
      if (this.firstGrant != null) {
         var1 = new LinkedList();
         var1.add(this.firstGrant);
      } else {
         var1 = new LinkedList(this.granted);
      }

      return var1;
   }

   public final Lock getLock(CompatibilitySpace var1, Object var2) {
      if (this.isUnlocked()) {
         return null;
      } else {
         List var3 = this.granted;
         int var4 = 0;
         int var5 = this.firstGrant == null ? var3.size() : 0;

         do {
            Lock var6 = this.firstGrant == null ? (Lock)var3.get(var4) : this.firstGrant;
            if (var6.getCompatabilitySpace() == var1 && var6.getQualifier() == var2) {
               return var6;
            }

            ++var4;
         } while(var4 < var5);

         return null;
      }
   }

   public boolean blockedByParent(Lock var1) {
      if (this.granted == null) {
         return false;
      } else {
         LockOwner var2 = var1.getCompatabilitySpace().getOwner();
         Object var3 = var1.getQualifier();

         for(Lock var5 : this.granted) {
            LockOwner var6 = var5.getCompatabilitySpace().getOwner();
            if (var2.nestsUnder(var6)) {
               Object var7 = var5.getQualifier();
               if (!var5.getLockable().requestCompatible(var3, var7)) {
                  return true;
               }
            }
         }

         return false;
      }
   }

   public Control shallowClone() {
      return new LockControl(this);
   }

   private void addWaiter(Lock var1, LockTable var2) {
      this.waiting.add(var1);
      var2.oneMoreWaiter();
   }

   private Object popFrontWaiter(LockTable var1) {
      return this.removeWaiter(0, var1);
   }

   private Object removeWaiter(int var1, LockTable var2) {
      var2.oneLessWaiter();
      return this.waiting.remove(var1);
   }

   private int removeWaiter(Object var1, LockTable var2) {
      var2.oneLessWaiter();
      return this.waiting.remove(var1) ? 1 : 0;
   }
}
