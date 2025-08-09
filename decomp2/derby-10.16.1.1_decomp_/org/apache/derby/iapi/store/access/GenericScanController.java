package org.apache.derby.iapi.store.access;

import org.apache.derby.iapi.types.DataValueDescriptor;
import org.apache.derby.iapi.types.RowLocation;
import org.apache.derby.shared.common.error.StandardException;

public interface GenericScanController extends RowCountable {
   void close() throws StandardException;

   ScanInfo getScanInfo() throws StandardException;

   boolean isKeyed();

   boolean isTableLocked();

   RowLocation newRowLocationTemplate() throws StandardException;

   void reopenScan(DataValueDescriptor[] var1, int var2, Qualifier[][] var3, DataValueDescriptor[] var4, int var5) throws StandardException;

   void reopenScanByRowLocation(RowLocation var1, Qualifier[][] var2) throws StandardException;
}
