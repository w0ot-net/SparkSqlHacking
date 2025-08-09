package org.apache.derby.iapi.sql.execute;

import org.apache.derby.iapi.store.access.ConglomerateController;
import org.apache.derby.iapi.types.RowLocation;
import org.apache.derby.shared.common.error.StandardException;

public interface RowChanger {
   void open(int var1) throws StandardException;

   void setRowHolder(TemporaryRowHolder var1);

   void setIndexNames(String[] var1);

   void openForUpdate(boolean[] var1, int var2, boolean var3) throws StandardException;

   RowLocation insertRow(ExecRow var1, boolean var2) throws StandardException;

   void deleteRow(ExecRow var1, RowLocation var2) throws StandardException;

   void updateRow(ExecRow var1, ExecRow var2, RowLocation var3) throws StandardException;

   void finish() throws StandardException;

   void close() throws StandardException;

   ConglomerateController getHeapConglomerateController();

   void open(int var1, boolean var2) throws StandardException;

   int findSelectedCol(int var1);
}
