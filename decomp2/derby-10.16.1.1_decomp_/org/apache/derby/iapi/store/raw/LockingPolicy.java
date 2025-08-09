package org.apache.derby.iapi.store.raw;

import org.apache.derby.shared.common.error.StandardException;

public interface LockingPolicy {
   int MODE_NONE = 0;
   int MODE_RECORD = 1;
   int MODE_CONTAINER = 2;

   boolean lockContainer(Transaction var1, ContainerHandle var2, boolean var3, boolean var4) throws StandardException;

   void unlockContainer(Transaction var1, ContainerHandle var2);

   boolean lockRecordForRead(Transaction var1, ContainerHandle var2, RecordHandle var3, boolean var4, boolean var5) throws StandardException;

   boolean zeroDurationLockRecordForWrite(Transaction var1, RecordHandle var2, boolean var3, boolean var4) throws StandardException;

   boolean lockRecordForWrite(Transaction var1, RecordHandle var2, boolean var3, boolean var4) throws StandardException;

   void unlockRecordAfterRead(Transaction var1, ContainerHandle var2, RecordHandle var3, boolean var4, boolean var5) throws StandardException;

   int getMode();
}
