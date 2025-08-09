package org.apache.derby.impl.store.raw.xact;

import org.apache.derby.iapi.store.raw.ContainerHandle;
import org.apache.derby.iapi.store.raw.LockingPolicy;
import org.apache.derby.iapi.store.raw.RecordHandle;
import org.apache.derby.iapi.store.raw.Transaction;
import org.apache.derby.shared.common.error.StandardException;

class NoLocking implements LockingPolicy {
   protected NoLocking() {
   }

   public boolean lockContainer(Transaction var1, ContainerHandle var2, boolean var3, boolean var4) throws StandardException {
      return true;
   }

   public void unlockContainer(Transaction var1, ContainerHandle var2) {
   }

   public boolean lockRecordForRead(Transaction var1, ContainerHandle var2, RecordHandle var3, boolean var4, boolean var5) throws StandardException {
      return true;
   }

   public boolean zeroDurationLockRecordForWrite(Transaction var1, RecordHandle var2, boolean var3, boolean var4) throws StandardException {
      return true;
   }

   public boolean lockRecordForWrite(Transaction var1, RecordHandle var2, boolean var3, boolean var4) throws StandardException {
      return true;
   }

   public void unlockRecordAfterRead(Transaction var1, ContainerHandle var2, RecordHandle var3, boolean var4, boolean var5) throws StandardException {
   }

   public int getMode() {
      return 0;
   }
}
