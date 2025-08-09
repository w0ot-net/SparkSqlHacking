package org.apache.derby.iapi.store.raw;

import java.io.IOException;
import org.apache.derby.iapi.services.io.LimitObjectInput;
import org.apache.derby.shared.common.error.StandardException;

public interface LogicalUndoable extends Undoable {
   ContainerHandle getContainer() throws StandardException;

   RecordHandle getRecordHandle();

   void restoreLoggedRow(Object[] var1, LimitObjectInput var2) throws StandardException, IOException;

   void resetRecordHandle(RecordHandle var1);
}
