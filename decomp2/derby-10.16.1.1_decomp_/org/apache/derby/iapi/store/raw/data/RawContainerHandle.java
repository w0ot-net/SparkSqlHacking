package org.apache.derby.iapi.store.raw.data;

import org.apache.derby.iapi.store.raw.ContainerHandle;
import org.apache.derby.iapi.store.raw.Page;
import org.apache.derby.iapi.store.raw.log.LogInstant;
import org.apache.derby.iapi.util.ByteArray;
import org.apache.derby.shared.common.error.StandardException;

public interface RawContainerHandle extends ContainerHandle {
   int NORMAL = 1;
   int DROPPED = 2;
   int COMMITTED_DROP = 4;

   int getContainerStatus() throws StandardException;

   void removeContainer(LogInstant var1) throws StandardException;

   void dropContainer(LogInstant var1, boolean var2) throws StandardException;

   long getContainerVersion() throws StandardException;

   Page getAnyPage(long var1) throws StandardException;

   Page reCreatePageForRedoRecovery(int var1, long var2, long var4) throws StandardException;

   ByteArray logCreateContainerInfo() throws StandardException;

   void preDirty(boolean var1) throws StandardException;

   void encryptOrDecryptContainer(String var1, boolean var2) throws StandardException;
}
