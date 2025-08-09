package org.apache.derby.vti;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.apache.derby.iapi.types.DataValueDescriptor;
import org.apache.derby.shared.common.error.StandardException;

public interface IFastPath {
   int SCAN_COMPLETED = -1;
   int GOT_ROW = 0;
   int NEED_RS = 1;

   boolean executeAsFastPath() throws StandardException, SQLException;

   int nextRow(DataValueDescriptor[] var1) throws StandardException, SQLException;

   void currentRow(ResultSet var1, DataValueDescriptor[] var2) throws StandardException, SQLException;

   void rowsDone() throws StandardException, SQLException;
}
