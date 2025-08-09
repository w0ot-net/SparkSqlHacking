package org.apache.derby.iapi.store.raw.xact;

import org.apache.derby.iapi.services.context.ContextManager;
import org.apache.derby.iapi.services.daemon.Serviceable;
import org.apache.derby.iapi.services.io.Formatable;
import org.apache.derby.iapi.services.locks.CompatibilitySpace;
import org.apache.derby.iapi.services.locks.LockFactory;
import org.apache.derby.iapi.store.access.TransactionInfo;
import org.apache.derby.iapi.store.raw.Corruptable;
import org.apache.derby.iapi.store.raw.RawStoreFactory;
import org.apache.derby.iapi.store.raw.log.LogInstant;
import org.apache.derby.shared.common.error.StandardException;

public interface TransactionFactory extends Corruptable {
   String MODULE = "org.apache.derby.iapi.store.raw.xact.TransactionFactory";

   LockFactory getLockFactory();

   Object getXAResourceManager() throws StandardException;

   RawTransaction startTransaction(RawStoreFactory var1, ContextManager var2, String var3) throws StandardException;

   RawTransaction startNestedReadOnlyUserTransaction(RawStoreFactory var1, RawTransaction var2, CompatibilitySpace var3, ContextManager var4, String var5) throws StandardException;

   RawTransaction startNestedUpdateUserTransaction(RawStoreFactory var1, RawTransaction var2, ContextManager var3, String var4, boolean var5) throws StandardException;

   RawTransaction startGlobalTransaction(RawStoreFactory var1, ContextManager var2, int var3, byte[] var4, byte[] var5) throws StandardException;

   RawTransaction findUserTransaction(RawStoreFactory var1, ContextManager var2, String var3) throws StandardException;

   RawTransaction startNestedTopTransaction(RawStoreFactory var1, ContextManager var2) throws StandardException;

   RawTransaction startInternalTransaction(RawStoreFactory var1, ContextManager var2) throws StandardException;

   boolean findTransaction(TransactionId var1, RawTransaction var2);

   void resetTranId() throws StandardException;

   LogInstant firstUpdateInstant();

   void handlePreparedXacts(RawStoreFactory var1) throws StandardException;

   void rollbackAllTransactions(RawTransaction var1, RawStoreFactory var2) throws StandardException;

   boolean submitPostCommitWork(Serviceable var1);

   void setRawStoreFactory(RawStoreFactory var1) throws StandardException;

   boolean noActiveUpdateTransaction();

   boolean hasPreparedXact();

   void createFinished() throws StandardException;

   Formatable getTransactionTable();

   void useTransactionTable(Formatable var1) throws StandardException;

   TransactionInfo[] getTransactionInfo();

   boolean blockBackupBlockingOperations(boolean var1) throws StandardException;

   void unblockBackupBlockingOperations();
}
