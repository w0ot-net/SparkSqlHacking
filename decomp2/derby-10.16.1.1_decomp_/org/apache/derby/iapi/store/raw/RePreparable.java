package org.apache.derby.iapi.store.raw;

import org.apache.derby.shared.common.error.StandardException;

public interface RePreparable {
   void reclaimPrepareLocks(Transaction var1, LockingPolicy var2) throws StandardException;
}
