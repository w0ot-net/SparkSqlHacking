package org.apache.derby.impl.store.access.btree.index;

import org.apache.derby.iapi.store.access.ConglomerateController;
import org.apache.derby.iapi.store.raw.FetchDescriptor;
import org.apache.derby.iapi.store.raw.LockingPolicy;
import org.apache.derby.iapi.store.raw.Transaction;
import org.apache.derby.iapi.types.DataValueDescriptor;
import org.apache.derby.iapi.types.RowLocation;
import org.apache.derby.impl.store.access.btree.BTree;
import org.apache.derby.impl.store.access.btree.BTreeLockingPolicy;
import org.apache.derby.impl.store.access.btree.BTreeRowPosition;
import org.apache.derby.impl.store.access.btree.LeafControlRow;
import org.apache.derby.impl.store.access.btree.OpenBTree;
import org.apache.derby.shared.common.error.StandardException;

public class B2INoLocking implements BTreeLockingPolicy {
   public B2INoLocking(Transaction var1, int var2, LockingPolicy var3, ConglomerateController var4, OpenBTree var5) {
   }

   protected B2INoLocking() {
   }

   public boolean lockScanCommittedDeletedRow(OpenBTree var1, LeafControlRow var2, DataValueDescriptor[] var3, FetchDescriptor var4, int var5) throws StandardException {
      return true;
   }

   public boolean lockScanRow(OpenBTree var1, BTreeRowPosition var2, FetchDescriptor var3, DataValueDescriptor[] var4, RowLocation var5, boolean var6, boolean var7, int var8) throws StandardException {
      return true;
   }

   public void unlockScanRecordAfterRead(BTreeRowPosition var1, boolean var2) throws StandardException {
   }

   public boolean lockNonScanPreviousRow(LeafControlRow var1, int var2, FetchDescriptor var3, DataValueDescriptor[] var4, RowLocation var5, OpenBTree var6, int var7, int var8) throws StandardException {
      return true;
   }

   public boolean lockNonScanRow(BTree var1, LeafControlRow var2, LeafControlRow var3, DataValueDescriptor[] var4, int var5) throws StandardException {
      return true;
   }

   public boolean lockNonScanRowOnPage(LeafControlRow var1, int var2, FetchDescriptor var3, DataValueDescriptor[] var4, RowLocation var5, int var6) throws StandardException {
      return true;
   }
}
