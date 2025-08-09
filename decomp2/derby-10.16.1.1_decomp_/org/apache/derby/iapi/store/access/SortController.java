package org.apache.derby.iapi.store.access;

import org.apache.derby.iapi.types.DataValueDescriptor;
import org.apache.derby.shared.common.error.StandardException;

public interface SortController {
   void completedInserts();

   void insert(DataValueDescriptor[] var1) throws StandardException;

   SortInfo getSortInfo() throws StandardException;
}
