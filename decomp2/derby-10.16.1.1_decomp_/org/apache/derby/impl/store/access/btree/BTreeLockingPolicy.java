package org.apache.derby.impl.store.access.btree;

import org.apache.derby.iapi.store.raw.FetchDescriptor;
import org.apache.derby.iapi.types.DataValueDescriptor;
import org.apache.derby.iapi.types.RowLocation;
import org.apache.derby.shared.common.error.StandardException;

public interface BTreeLockingPolicy {
   boolean lockScanCommittedDeletedRow(OpenBTree var1, LeafControlRow var2, DataValueDescriptor[] var3, FetchDescriptor var4, int var5) throws StandardException;

   boolean lockScanRow(OpenBTree var1, BTreeRowPosition var2, FetchDescriptor var3, DataValueDescriptor[] var4, RowLocation var5, boolean var6, boolean var7, int var8) throws StandardException;

   void unlockScanRecordAfterRead(BTreeRowPosition var1, boolean var2) throws StandardException;

   boolean lockNonScanPreviousRow(LeafControlRow var1, int var2, FetchDescriptor var3, DataValueDescriptor[] var4, RowLocation var5, OpenBTree var6, int var7, int var8) throws StandardException;

   boolean lockNonScanRow(BTree var1, LeafControlRow var2, LeafControlRow var3, DataValueDescriptor[] var4, int var5) throws StandardException;

   boolean lockNonScanRowOnPage(LeafControlRow var1, int var2, FetchDescriptor var3, DataValueDescriptor[] var4, RowLocation var5, int var6) throws StandardException;
}
