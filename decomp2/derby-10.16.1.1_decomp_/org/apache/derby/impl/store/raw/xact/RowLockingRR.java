package org.apache.derby.impl.store.raw.xact;

import org.apache.derby.iapi.services.locks.LockFactory;
import org.apache.derby.iapi.store.raw.ContainerHandle;
import org.apache.derby.iapi.store.raw.RecordHandle;
import org.apache.derby.iapi.store.raw.RowLock;
import org.apache.derby.iapi.store.raw.Transaction;
import org.apache.derby.shared.common.error.StandardException;

public class RowLockingRR extends RowLocking3 {
   protected RowLockingRR(LockFactory var1) {
      super(var1);
   }

   protected RowLock getReadLockType() {
      return RowLock.RS2;
   }

   protected RowLock getUpdateLockType() {
      return RowLock.RU2;
   }

   protected RowLock getWriteLockType() {
      return RowLock.RX2;
   }

   public void unlockRecordAfterRead(Transaction var1, ContainerHandle var2, RecordHandle var3, boolean var4, boolean var5) throws StandardException {
      if (!var5) {
         RowLock var6 = var4 ? RowLock.RU2 : RowLock.RS2;
         this.lf.unlock(var1.getCompatibilitySpace(), var1, var3, var6);
      }

   }
}
