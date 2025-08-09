package org.apache.derby.iapi.store.access;

import org.apache.derby.iapi.types.DataValueDescriptor;
import org.apache.derby.shared.common.error.StandardException;

public interface Qualifier {
   int VARIANT = 0;
   int SCAN_INVARIANT = 1;
   int QUERY_INVARIANT = 2;
   int CONSTANT = 3;

   int getColumnId();

   DataValueDescriptor getOrderable() throws StandardException;

   int getOperator();

   boolean negateCompareResult();

   boolean getOrderedNulls();

   boolean getUnknownRV();

   void clearOrderableCache();

   void reinitialize();
}
