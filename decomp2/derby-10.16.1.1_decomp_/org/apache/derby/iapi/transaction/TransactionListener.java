package org.apache.derby.iapi.transaction;

import org.apache.derby.shared.common.error.StandardException;

public interface TransactionListener {
   boolean preCommit() throws StandardException;

   void preRollback() throws StandardException;
}
