package org.apache.derby.impl.store.raw.xact;

import org.apache.derby.iapi.services.locks.LockFactory;
import org.apache.derby.iapi.store.raw.ContainerHandle;
import org.apache.derby.iapi.store.raw.ContainerLock;
import org.apache.derby.iapi.store.raw.LockingPolicy;
import org.apache.derby.iapi.store.raw.RecordHandle;
import org.apache.derby.iapi.store.raw.RowLock;
import org.apache.derby.iapi.store.raw.Transaction;
import org.apache.derby.shared.common.error.StandardException;

public class RowLocking3 extends NoLocking {
   private static final LockingPolicy NO_LOCK = new NoLocking();
   protected final LockFactory lf;

   protected RowLocking3(LockFactory var1) {
      this.lf = var1;
   }

   protected RowLock getReadLockType() {
      return RowLock.RS3;
   }

   protected RowLock getUpdateLockType() {
      return RowLock.RU3;
   }

   protected RowLock getWriteLockType() {
      return RowLock.RX3;
   }

   public boolean lockContainer(Transaction var1, ContainerHandle var2, boolean var3, boolean var4) throws StandardException {
      ContainerLock var5 = var4 ? ContainerLock.CIX : ContainerLock.CIS;
      boolean var6 = this.lf.lockObject(var1.getCompatibilitySpace(), var1, var2.getId(), var5, var3 ? -2 : 0);
      if (var6 && (this.lf.isLockHeld(var1.getCompatibilitySpace(), var1, var2.getId(), ContainerLock.CX) || !var4 && this.lf.isLockHeld(var1.getCompatibilitySpace(), var1, var2.getId(), ContainerLock.CS))) {
         var2.setLockingPolicy(NO_LOCK);
      }

      return var6;
   }

   public boolean lockRecordForRead(Transaction var1, ContainerHandle var2, RecordHandle var3, boolean var4, boolean var5) throws StandardException {
      RowLock var6 = var5 ? this.getUpdateLockType() : this.getReadLockType();
      return this.lf.lockObject(var1.getCompatibilitySpace(), var1, var3, var6, var4 ? -2 : 0);
   }

   public boolean zeroDurationLockRecordForWrite(Transaction var1, RecordHandle var2, boolean var3, boolean var4) throws StandardException {
      return this.lf.zeroDurationlockObject(var1.getCompatibilitySpace(), var2, var3 ? RowLock.RIP : this.getWriteLockType(), var4 ? -2 : 0);
   }

   public boolean lockRecordForWrite(Transaction var1, RecordHandle var2, boolean var3, boolean var4) throws StandardException {
      return this.lf.lockObject(var1.getCompatibilitySpace(), var1, var2, var3 ? RowLock.RI : this.getWriteLockType(), var4 ? -2 : 0);
   }

   public int getMode() {
      return 1;
   }
}
