package org.apache.derby.iapi.store.access;

import org.apache.derby.shared.common.error.StandardException;

public interface XATransactionController extends TransactionController {
   int XA_RDONLY = 1;
   int XA_OK = 2;

   void xa_commit(boolean var1) throws StandardException;

   int xa_prepare() throws StandardException;

   void xa_rollback() throws StandardException;
}
