package org.apache.derby.impl.store.access.btree.index;

import org.apache.derby.iapi.store.access.ConglomerateController;
import org.apache.derby.iapi.store.raw.FetchDescriptor;
import org.apache.derby.iapi.store.raw.LockingPolicy;
import org.apache.derby.iapi.store.raw.Transaction;
import org.apache.derby.iapi.types.DataValueDescriptor;
import org.apache.derby.iapi.types.RowLocation;
import org.apache.derby.impl.store.access.btree.BTreeLockingPolicy;
import org.apache.derby.impl.store.access.btree.BTreeRowPosition;
import org.apache.derby.impl.store.access.btree.OpenBTree;
import org.apache.derby.shared.common.error.StandardException;

class B2IRowLocking1 extends B2IRowLocking2 implements BTreeLockingPolicy {
   B2IRowLocking1(Transaction var1, int var2, LockingPolicy var3, ConglomerateController var4, OpenBTree var5) {
      super(var1, var2, var3, var4, var5);
   }

   public boolean lockScanRow(OpenBTree var1, BTreeRowPosition var2, FetchDescriptor var3, DataValueDescriptor[] var4, RowLocation var5, boolean var6, boolean var7, int var8) throws StandardException {
      return this._lockScanRow(var1, var2, var7 && !var6, var3, var4, var5, var6, var7, var8);
   }

   public void unlockScanRecordAfterRead(BTreeRowPosition var1, boolean var2) throws StandardException {
      if (var2) {
         super.unlockScanRecordAfterRead(var1, var2);
      }

   }
}
