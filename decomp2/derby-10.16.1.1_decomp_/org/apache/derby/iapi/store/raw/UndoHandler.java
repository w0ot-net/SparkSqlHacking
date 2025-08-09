package org.apache.derby.iapi.store.raw;

import org.apache.derby.shared.common.error.StandardException;

public interface UndoHandler {
   void insertUndoNotify(Transaction var1, PageKey var2) throws StandardException;
}
