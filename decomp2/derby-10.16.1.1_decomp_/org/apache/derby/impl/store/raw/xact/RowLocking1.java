package org.apache.derby.impl.store.raw.xact;

import org.apache.derby.iapi.services.locks.LockFactory;
import org.apache.derby.iapi.store.raw.ContainerHandle;
import org.apache.derby.iapi.store.raw.RecordHandle;
import org.apache.derby.iapi.store.raw.Transaction;
import org.apache.derby.shared.common.error.StandardException;

public class RowLocking1 extends RowLocking2 {
   protected RowLocking1(LockFactory var1) {
      super(var1);
   }

   public boolean lockRecordForRead(Transaction var1, ContainerHandle var2, RecordHandle var3, boolean var4, boolean var5) throws StandardException {
      return !var5 ? true : super.lockRecordForRead(var1, var2, var3, var4, var5);
   }

   public void unlockRecordAfterRead(Transaction var1, ContainerHandle var2, RecordHandle var3, boolean var4, boolean var5) throws StandardException {
      if (var4) {
         super.unlockRecordAfterRead(var1, var2, var3, var4, var5);
      }

   }
}
