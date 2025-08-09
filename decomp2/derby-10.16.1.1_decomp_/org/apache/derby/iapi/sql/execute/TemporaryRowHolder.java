package org.apache.derby.iapi.sql.execute;

import org.apache.derby.shared.common.error.StandardException;

public interface TemporaryRowHolder {
   void insert(ExecRow var1) throws StandardException;

   CursorResultSet getResultSet();

   void close() throws StandardException;

   long getTemporaryConglomId();

   long getPositionIndexConglomId();

   void setRowHolderTypeToUniqueStream();
}
