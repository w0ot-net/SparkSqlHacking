package org.apache.derby.iapi.sql.execute;

import org.apache.derby.iapi.sql.ResultSet;
import org.apache.derby.iapi.types.RowLocation;
import org.apache.derby.shared.common.error.StandardException;

public interface CursorResultSet extends ResultSet {
   RowLocation getRowLocation() throws StandardException;

   ExecRow getCurrentRow() throws StandardException;
}
