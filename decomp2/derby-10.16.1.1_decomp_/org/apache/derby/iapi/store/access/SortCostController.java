package org.apache.derby.iapi.store.access;

import org.apache.derby.iapi.types.DataValueDescriptor;
import org.apache.derby.shared.common.error.StandardException;

public interface SortCostController {
   void close();

   double getSortCost(DataValueDescriptor[] var1, ColumnOrdering[] var2, boolean var3, long var4, long var6, int var8) throws StandardException;
}
