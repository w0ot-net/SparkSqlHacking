package org.apache.derby.iapi.sql.execute;

import org.apache.derby.iapi.sql.Activation;

public interface CursorActivation extends Activation {
   CursorResultSet getTargetResultSet();

   CursorResultSet getCursorResultSet();
}
