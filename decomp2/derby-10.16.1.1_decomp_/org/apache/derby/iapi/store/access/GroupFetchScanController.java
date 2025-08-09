package org.apache.derby.iapi.store.access;

import org.apache.derby.iapi.types.DataValueDescriptor;
import org.apache.derby.iapi.types.RowLocation;
import org.apache.derby.shared.common.error.StandardException;

public interface GroupFetchScanController extends GenericScanController {
   int fetchNextGroup(DataValueDescriptor[][] var1, RowLocation[] var2) throws StandardException;

   int fetchNextGroup(DataValueDescriptor[][] var1, RowLocation[] var2, RowLocation[] var3) throws StandardException;

   boolean next() throws StandardException;
}
