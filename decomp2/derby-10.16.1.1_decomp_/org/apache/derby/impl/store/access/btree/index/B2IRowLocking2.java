package org.apache.derby.impl.store.access.btree.index;

import org.apache.derby.iapi.store.access.ConglomerateController;
import org.apache.derby.iapi.store.raw.LockingPolicy;
import org.apache.derby.iapi.store.raw.Transaction;
import org.apache.derby.impl.store.access.btree.BTreeLockingPolicy;
import org.apache.derby.impl.store.access.btree.BTreeRowPosition;
import org.apache.derby.impl.store.access.btree.OpenBTree;
import org.apache.derby.shared.common.error.StandardException;

class B2IRowLocking2 extends B2IRowLockingRR implements BTreeLockingPolicy {
   B2IRowLocking2(Transaction var1, int var2, LockingPolicy var3, ConglomerateController var4, OpenBTree var5) {
      super(var1, var2, var3, var4, var5);
   }

   public void unlockScanRecordAfterRead(BTreeRowPosition var1, boolean var2) throws StandardException {
      this.base_cc.unlockRowAfterRead(var1.current_lock_row_loc, var2, false);
   }
}
