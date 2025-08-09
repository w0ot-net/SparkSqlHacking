package org.apache.derby.iapi.sql.execute;

import org.apache.derby.iapi.sql.ResultSet;
import org.apache.derby.iapi.types.RowLocation;
import org.apache.derby.shared.common.error.StandardException;

public interface TargetResultSet extends ResultSet {
   void changedRow(ExecRow var1, RowLocation var2) throws StandardException;

   void offendingRowLocation(RowLocation var1, long var2) throws StandardException;

   ExecRow preprocessSourceRow(ExecRow var1) throws StandardException;
}
