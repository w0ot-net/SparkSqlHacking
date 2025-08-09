package org.apache.derby.iapi.store.access.conglomerate;

import org.apache.derby.iapi.services.daemon.Serviceable;
import org.apache.derby.iapi.store.access.ConglomerateController;
import org.apache.derby.iapi.store.access.SortController;
import org.apache.derby.iapi.store.access.TransactionController;
import org.apache.derby.iapi.store.raw.ContainerKey;
import org.apache.derby.iapi.store.raw.Transaction;
import org.apache.derby.shared.common.error.StandardException;

public interface TransactionManager extends TransactionController {
   int MODE_NONE = 5;
   int LOCK_INSTANT_DURATION = 1;
   int LOCK_COMMIT_DURATION = 2;
   int LOCK_MANUAL_DURATION = 3;

   void addPostCommitWork(Serviceable var1);

   boolean checkVersion(int var1, int var2, String var3) throws StandardException;

   void closeMe(ScanManager var1);

   void closeMe(ConglomerateController var1);

   void closeMe(SortController var1);

   TransactionManager getInternalTransaction() throws StandardException;

   Transaction getRawStoreXact() throws StandardException;

   Conglomerate findExistingConglomerateFromKey(ContainerKey var1) throws StandardException;
}
