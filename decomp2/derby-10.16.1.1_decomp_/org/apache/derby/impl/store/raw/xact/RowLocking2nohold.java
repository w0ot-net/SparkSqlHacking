package org.apache.derby.impl.store.raw.xact;

import org.apache.derby.iapi.services.locks.LockFactory;
import org.apache.derby.iapi.store.raw.ContainerHandle;
import org.apache.derby.iapi.store.raw.RecordHandle;
import org.apache.derby.iapi.store.raw.RowLock;
import org.apache.derby.iapi.store.raw.Transaction;
import org.apache.derby.shared.common.error.StandardException;

public class RowLocking2nohold extends RowLocking2 {
   protected RowLocking2nohold(LockFactory var1) {
      super(var1);
   }

   public boolean lockRecordForRead(Transaction var1, ContainerHandle var2, RecordHandle var3, boolean var4, boolean var5) throws StandardException {
      return this.lf.zeroDurationlockObject(var1.getCompatibilitySpace(), var3, var5 ? RowLock.RU2 : RowLock.RS2, var4 ? -2 : 0);
   }

   public void unlockRecordAfterRead(Transaction var1, ContainerHandle var2, RecordHandle var3, boolean var4, boolean var5) {
   }
}
