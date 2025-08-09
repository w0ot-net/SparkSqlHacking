package org.apache.derby.impl.store.raw.xact;

import org.apache.derby.iapi.services.locks.LockFactory;
import org.apache.derby.iapi.store.raw.ContainerHandle;
import org.apache.derby.iapi.store.raw.ContainerLock;
import org.apache.derby.iapi.store.raw.LockingPolicy;
import org.apache.derby.iapi.store.raw.RecordHandle;
import org.apache.derby.iapi.store.raw.RowLock;
import org.apache.derby.iapi.store.raw.Transaction;
import org.apache.derby.shared.common.error.StandardException;

public class RowLocking2 extends RowLockingRR {
   private static final LockingPolicy NO_LOCK = new NoLocking();

   protected RowLocking2(LockFactory var1) {
      super(var1);
   }

   public boolean lockContainer(Transaction var1, ContainerHandle var2, boolean var3, boolean var4) throws StandardException {
      ContainerLock var5 = var4 ? ContainerLock.CIX : ContainerLock.CIS;
      Object var6 = var4 ? var1 : var2.getUniqueId();
      boolean var7 = this.lf.lockObject(var1.getCompatibilitySpace(), var6, var2.getId(), var5, var3 ? -2 : 0);
      if (var7) {
         if (this.lf.isLockHeld(var1.getCompatibilitySpace(), var1, var2.getId(), ContainerLock.CX)) {
            this.lf.unlockGroup(var1.getCompatibilitySpace(), var2.getUniqueId());
            var2.setLockingPolicy(NO_LOCK);
         } else if (!var4 && this.lf.isLockHeld(var1.getCompatibilitySpace(), var1, var2.getId(), ContainerLock.CS)) {
            this.lf.transfer(var1.getCompatibilitySpace(), var6, var1);
            var2.setLockingPolicy(NO_LOCK);
         }
      }

      return var7;
   }

   public boolean lockRecordForRead(Transaction var1, ContainerHandle var2, RecordHandle var3, boolean var4, boolean var5) throws StandardException {
      RowLock var6 = var5 ? RowLock.RU2 : RowLock.RS2;
      return this.lf.lockObject(var1.getCompatibilitySpace(), var2.getUniqueId(), var3, var6, var4 ? -2 : 0);
   }

   public void unlockRecordAfterRead(Transaction var1, ContainerHandle var2, RecordHandle var3, boolean var4, boolean var5) throws StandardException {
      RowLock var6 = var4 ? RowLock.RU2 : RowLock.RS2;
      this.lf.unlock(var1.getCompatibilitySpace(), var2.getUniqueId(), var3, var6);
   }

   public void unlockContainer(Transaction var1, ContainerHandle var2) {
      this.lf.unlockGroup(var1.getCompatibilitySpace(), var2.getUniqueId());
   }
}
