package org.apache.derby.iapi.sql;

import org.apache.derby.iapi.sql.execute.ExecPreparedStatement;
import org.apache.derby.shared.common.error.StandardException;

public interface StorablePreparedStatement extends ExecPreparedStatement {
   void loadGeneratedClass() throws StandardException;
}
