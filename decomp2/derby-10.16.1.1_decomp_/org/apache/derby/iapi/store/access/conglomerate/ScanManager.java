package org.apache.derby.iapi.store.access.conglomerate;

import org.apache.derby.iapi.store.access.BackingStoreHashtable;
import org.apache.derby.iapi.store.access.GroupFetchScanController;
import org.apache.derby.iapi.store.access.ScanController;
import org.apache.derby.shared.common.error.StandardException;

public interface ScanManager extends ScanController, GroupFetchScanController {
   boolean closeForEndTransaction(boolean var1) throws StandardException;

   void fetchSet(long var1, int[] var3, BackingStoreHashtable var4) throws StandardException;
}
