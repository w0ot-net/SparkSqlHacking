package org.apache.derby.iapi.store.access.conglomerate;

import java.io.IOException;
import org.apache.derby.iapi.services.io.LimitObjectInput;
import org.apache.derby.iapi.store.raw.LogicalUndoable;
import org.apache.derby.iapi.store.raw.Page;
import org.apache.derby.iapi.store.raw.Transaction;
import org.apache.derby.shared.common.error.StandardException;

public interface LogicalUndo {
   Page findUndo(Transaction var1, LogicalUndoable var2, LimitObjectInput var3) throws StandardException, IOException;
}
