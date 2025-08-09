package org.apache.derby.iapi.store.access;

import org.apache.derby.catalog.UUID;
import org.apache.derby.iapi.services.context.ContextManager;
import org.apache.derby.iapi.services.locks.LockFactory;
import org.apache.derby.iapi.store.access.conglomerate.MethodFactory;
import org.apache.derby.shared.common.error.StandardException;

public interface AccessFactory {
   String MODULE = "org.apache.derby.iapi.store.access.AccessFactory";

   void registerAccessMethod(MethodFactory var1);

   void createFinished() throws StandardException;

   MethodFactory findMethodFactoryByImpl(String var1) throws StandardException;

   MethodFactory findMethodFactoryByFormat(UUID var1);

   LockFactory getLockFactory();

   Object getXAResourceManager() throws StandardException;

   boolean isReadOnly();

   void createReadMeFiles() throws StandardException;

   TransactionController getTransaction(ContextManager var1) throws StandardException;

   TransactionController getAndNameTransaction(ContextManager var1, String var2) throws StandardException;

   TransactionInfo[] getTransactionInfo();

   Object startXATransaction(ContextManager var1, int var2, byte[] var3, byte[] var4) throws StandardException;

   void startReplicationMaster(String var1, String var2, int var3, String var4) throws StandardException;

   void stopReplicationMaster() throws StandardException;

   void failover(String var1) throws StandardException;

   void freeze() throws StandardException;

   void unfreeze() throws StandardException;

   void backup(String var1, boolean var2) throws StandardException;

   void backupAndEnableLogArchiveMode(String var1, boolean var2, boolean var3) throws StandardException;

   void disableLogArchiveMode(boolean var1) throws StandardException;

   void checkpoint() throws StandardException;

   void waitForPostCommitToFinishWork();
}
