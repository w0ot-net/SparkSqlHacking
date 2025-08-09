package org.apache.derby.iapi.store.access;

import org.apache.derby.iapi.services.io.FormatableBitSet;
import org.apache.derby.iapi.types.DataValueDescriptor;
import org.apache.derby.iapi.types.RowLocation;
import org.apache.derby.shared.common.error.StandardException;

public interface ScanController extends GenericScanController {
   int GE = 1;
   int GT = -1;
   int NA = 0;

   boolean delete() throws StandardException;

   void didNotQualify() throws StandardException;

   boolean doesCurrentPositionQualify() throws StandardException;

   boolean isHeldAfterCommit() throws StandardException;

   void fetch(DataValueDescriptor[] var1) throws StandardException;

   void fetchWithoutQualify(DataValueDescriptor[] var1) throws StandardException;

   boolean fetchNext(DataValueDescriptor[] var1) throws StandardException;

   void fetchLocation(RowLocation var1) throws StandardException;

   boolean isCurrentPositionDeleted() throws StandardException;

   boolean next() throws StandardException;

   boolean positionAtRowLocation(RowLocation var1) throws StandardException;

   boolean replace(DataValueDescriptor[] var1, FormatableBitSet var2) throws StandardException;
}
