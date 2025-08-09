package org.apache.derby.iapi.store.raw;

import java.io.IOException;
import org.apache.derby.iapi.services.io.LimitObjectInput;
import org.apache.derby.shared.common.error.StandardException;

public interface Undoable extends Loggable {
   Compensation generateUndo(Transaction var1, LimitObjectInput var2) throws StandardException, IOException;
}
