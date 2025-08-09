package org.apache.derby.iapi.store.raw;

import java.util.Properties;
import org.apache.derby.iapi.services.context.ContextManager;
import org.apache.derby.iapi.services.daemon.Serviceable;
import org.apache.derby.iapi.services.locks.CompatibilitySpace;
import org.apache.derby.iapi.services.property.PersistentSet;
import org.apache.derby.iapi.store.access.FileResource;
import org.apache.derby.iapi.store.access.RowSource;
import org.apache.derby.iapi.store.raw.log.LogInstant;
import org.apache.derby.iapi.types.DataValueFactory;
import org.apache.derby.shared.common.error.StandardException;

public interface Transaction {
   int RELEASE_LOCKS = 1;
   int KEEP_LOCKS = 2;
   int XA_RDONLY = 1;
   int XA_OK = 2;

   ContextManager getContextManager();

   CompatibilitySpace getCompatibilitySpace();

   void setNoLockWait(boolean var1);

   void setup(PersistentSet var1) throws StandardException;

   GlobalTransactionId getGlobalId();

   LockingPolicy getDefaultLockingPolicy();

   LockingPolicy newLockingPolicy(int var1, int var2, boolean var3);

   void setDefaultLockingPolicy(LockingPolicy var1);

   LogInstant commit() throws StandardException;

   LogInstant commitNoSync(int var1) throws StandardException;

   void abort() throws StandardException;

   void close() throws StandardException;

   void destroy() throws StandardException;

   int setSavePoint(String var1, Object var2) throws StandardException;

   int releaseSavePoint(String var1, Object var2) throws StandardException;

   int rollbackToSavePoint(String var1, Object var2) throws StandardException;

   ContainerHandle openContainer(ContainerKey var1, int var2) throws StandardException;

   ContainerHandle openContainer(ContainerKey var1, LockingPolicy var2, int var3) throws StandardException;

   long addContainer(long var1, long var3, int var5, Properties var6, int var7) throws StandardException;

   void dropContainer(ContainerKey var1) throws StandardException;

   long addAndLoadStreamContainer(long var1, Properties var3, RowSource var4) throws StandardException;

   StreamContainerHandle openStreamContainer(long var1, long var3, boolean var5) throws StandardException;

   void dropStreamContainer(long var1, long var3) throws StandardException;

   void logAndDo(Loggable var1) throws StandardException;

   void addPostCommitWork(Serviceable var1);

   void addPostAbortWork(Serviceable var1);

   void addPostTerminationWork(Serviceable var1);

   boolean isIdle();

   boolean isPristine();

   FileResource getFileHandler();

   boolean anyoneBlocked();

   void createXATransactionFromLocalTransaction(int var1, byte[] var2, byte[] var3) throws StandardException;

   void xa_commit(boolean var1) throws StandardException;

   int xa_prepare() throws StandardException;

   void xa_rollback() throws StandardException;

   String getActiveStateTxIdString();

   DataValueFactory getDataValueFactory() throws StandardException;
}
