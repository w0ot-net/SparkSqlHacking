package org.apache.derby.impl.services.locks;

import java.io.Serializable;
import java.util.Dictionary;
import java.util.Enumeration;
import org.apache.derby.iapi.services.daemon.Serviceable;
import org.apache.derby.iapi.services.locks.CompatibilitySpace;
import org.apache.derby.iapi.services.locks.Limit;
import org.apache.derby.iapi.services.locks.LockFactory;
import org.apache.derby.iapi.services.locks.LockOwner;
import org.apache.derby.iapi.services.locks.Lockable;
import org.apache.derby.iapi.services.property.PropertyUtil;
import org.apache.derby.iapi.util.Matchable;
import org.apache.derby.shared.common.error.StandardException;

abstract class AbstractPool implements LockFactory {
   protected final LockTable lockTable = this.createLockTable();
   int deadlockMonitor;

   protected AbstractPool() {
   }

   protected abstract LockTable createLockTable();

   public boolean lockObject(CompatibilitySpace var1, Object var2, Lockable var3, Object var4, int var5) throws StandardException {
      Lock var6 = this.lockTable.lockObject(var1, var3, var4, var5);
      if (var6 == null) {
         if (var5 == -2) {
            throw StandardException.newException("40XL1", new Object[0]);
         } else {
            return false;
         }
      } else {
         ((LockSpace)var1).addLock(var2, var6);
         return true;
      }
   }

   public CompatibilitySpace createCompatibilitySpace(LockOwner var1) {
      return new LockSpace(var1);
   }

   public int unlock(CompatibilitySpace var1, Object var2, Lockable var3, Object var4) {
      int var5 = ((LockSpace)var1).unlockReference(this.lockTable, var3, var4, var2);
      return var5;
   }

   public void unlockGroup(CompatibilitySpace var1, Object var2) {
      ((LockSpace)var1).unlockGroup(this.lockTable, var2);
   }

   public void unlockGroup(CompatibilitySpace var1, Object var2, Matchable var3) {
      ((LockSpace)var1).unlockGroup(this.lockTable, var2, var3);
   }

   public void transfer(CompatibilitySpace var1, Object var2, Object var3) {
      ((LockSpace)var1).transfer(var2, var3);
   }

   public boolean anyoneBlocked() {
      return this.lockTable.anyoneBlocked();
   }

   public boolean areLocksHeld(CompatibilitySpace var1, Object var2) {
      return ((LockSpace)var1).areLocksHeld(var2);
   }

   public boolean areLocksHeld(CompatibilitySpace var1) {
      return ((LockSpace)var1).areLocksHeld();
   }

   public boolean zeroDurationlockObject(CompatibilitySpace var1, Lockable var2, Object var3, int var4) throws StandardException {
      boolean var5 = this.lockTable.zeroDurationLockObject(var1, var2, var3, var4);
      if (!var5 && var4 == -2) {
         throw StandardException.newException("40XL1", new Object[0]);
      } else {
         return var5;
      }
   }

   public boolean isLockHeld(CompatibilitySpace var1, Object var2, Lockable var3, Object var4) {
      return ((LockSpace)var1).isLockHeld(var2, var3, var4);
   }

   public int getWaitTimeout() {
      return this.lockTable.getWaitTimeout();
   }

   public void setLimit(CompatibilitySpace var1, Object var2, int var3, Limit var4) {
      ((LockSpace)var1).setLimit(var2, var3, var4);
   }

   public void clearLimit(CompatibilitySpace var1, Object var2) {
      ((LockSpace)var1).clearLimit(var2);
   }

   static boolean noLockWait(int var0, CompatibilitySpace var1) {
      if (var0 == 0) {
         return true;
      } else {
         LockOwner var2 = var1.getOwner();
         return var2 != null && var2.noWait();
      }
   }

   public Enumeration makeVirtualLockTable() {
      LockTableVTI var1 = new LockTableVTI(this.lockTable.shallowClone());
      return var1;
   }

   public void init(boolean var1, Dictionary var2) {
      this.getAndApply(var1, var2, "derby.locks.deadlockTimeout");
      this.getAndApply(var1, var2, "derby.locks.waitTimeout");
      this.getAndApply(var1, var2, "derby.locks.monitor");
      this.getAndApply(var1, var2, "derby.locks.deadlockTrace");
   }

   private void getAndApply(boolean var1, Dictionary var2, String var3) {
      try {
         Serializable var4 = PropertyUtil.getPropertyFromSet(var1, var2, var3);
         if (var4 != null) {
            this.validate(var3, var4, var2);
            this.apply(var3, var4, var2);
         }
      } catch (StandardException var5) {
      }

   }

   public boolean validate(String var1, Serializable var2, Dictionary var3) throws StandardException {
      if (!var1.startsWith("derby.locks.")) {
         return false;
      } else {
         if (var2 != null) {
            if (var1.equals("derby.locks.deadlockTimeout")) {
               getWaitValue((String)var2, 20);
            } else if (var1.equals("derby.locks.waitTimeout")) {
               getWaitValue((String)var2, 60);
            } else if (var1.equals("derby.locks.monitor")) {
               PropertyUtil.booleanProperty("derby.locks.monitor", var2, false);
            } else if (var1.equals("derby.locks.deadlockTrace")) {
               PropertyUtil.booleanProperty("derby.locks.deadlockTrace", var2, false);
            }
         }

         return true;
      }
   }

   public Serviceable apply(String var1, Serializable var2, Dictionary var3) throws StandardException {
      if (var2 == null) {
         var2 = PropertyUtil.getPropertyFromSet(var3, var1);
      }

      String var4 = (String)var2;
      if (var1.equals("derby.locks.deadlockTimeout")) {
         this.lockTable.setDeadlockTimeout(getWaitValue(var4, 20));
      } else if (var1.equals("derby.locks.waitTimeout")) {
         this.lockTable.setWaitTimeout(getWaitValue(var4, 60));
      } else if (var1.equals("derby.locks.monitor")) {
         this.deadlockMonitor = PropertyUtil.booleanProperty("derby.locks.monitor", var4, false) ? 2 : 0;
      } else if (var1.equals("derby.locks.deadlockTrace")) {
         this.lockTable.setDeadlockTrace(PropertyUtil.booleanProperty("derby.locks.deadlockTrace", var4, false));
      }

      return null;
   }

   public Serializable map(String var1, Serializable var2, Dictionary var3) {
      return null;
   }

   private static int getWaitValue(String var0, int var1) {
      int var2 = PropertyUtil.handleInt(var0, Integer.MIN_VALUE, 2147483, var1);
      if (var2 < 0) {
         var2 = -1;
      } else {
         var2 *= 1000;
      }

      return var2;
   }
}
