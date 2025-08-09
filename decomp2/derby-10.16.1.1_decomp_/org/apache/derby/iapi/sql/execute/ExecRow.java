package org.apache.derby.iapi.sql.execute;

import org.apache.derby.iapi.services.io.FormatableBitSet;
import org.apache.derby.iapi.sql.Row;
import org.apache.derby.iapi.types.DataValueDescriptor;

public interface ExecRow extends Row {
   ExecRow getClone();

   ExecRow getClone(FormatableBitSet var1);

   ExecRow getNewNullRow();

   void resetRowArray();

   DataValueDescriptor cloneColumn(int var1);

   DataValueDescriptor[] getRowArrayClone();

   DataValueDescriptor[] getRowArray();

   void setRowArray(DataValueDescriptor[] var1);

   void getNewObjectArray();
}
