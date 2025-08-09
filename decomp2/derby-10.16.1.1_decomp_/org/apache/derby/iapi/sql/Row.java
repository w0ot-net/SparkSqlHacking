package org.apache.derby.iapi.sql;

import org.apache.derby.iapi.types.DataValueDescriptor;
import org.apache.derby.shared.common.error.StandardException;

public interface Row {
   int nColumns();

   DataValueDescriptor getColumn(int var1) throws StandardException;

   void setColumn(int var1, DataValueDescriptor var2);
}
