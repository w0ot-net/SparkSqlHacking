package org.apache.derby.impl.store.access.btree.index;

import org.apache.derby.iapi.services.io.FormatableBitSet;
import org.apache.derby.iapi.store.access.ConglomerateController;
import org.apache.derby.iapi.store.access.DynamicCompiledOpenConglomInfo;
import org.apache.derby.iapi.store.access.Qualifier;
import org.apache.derby.iapi.store.access.StaticCompiledOpenConglomInfo;
import org.apache.derby.iapi.store.access.conglomerate.TransactionManager;
import org.apache.derby.iapi.store.raw.LockingPolicy;
import org.apache.derby.iapi.store.raw.Transaction;
import org.apache.derby.iapi.types.DataValueDescriptor;
import org.apache.derby.impl.store.access.btree.BTreeLockingPolicy;
import org.apache.derby.impl.store.access.btree.BTreeMaxScan;
import org.apache.derby.shared.common.error.StandardException;

public class B2IMaxScan extends BTreeMaxScan {
   private ConglomerateController base_cc_for_locking;

   B2IMaxScan() {
   }

   public void close() throws StandardException {
      super.close();
      if (this.base_cc_for_locking != null) {
         this.base_cc_for_locking.close();
         this.base_cc_for_locking = null;
      }

   }

   public boolean closeForEndTransaction(boolean var1) throws StandardException {
      boolean var2 = super.closeForEndTransaction(var1);
      if (this.base_cc_for_locking != null) {
         this.base_cc_for_locking.close();
         this.base_cc_for_locking = null;
      }

      return var2;
   }

   public void init(TransactionManager var1, Transaction var2, int var3, int var4, LockingPolicy var5, int var6, boolean var7, FormatableBitSet var8, B2I var9, B2IUndo var10) throws StandardException {
      int var11 = var3 | 64;
      this.base_cc_for_locking = var1.openConglomerate(var9.baseConglomerateId, false, var11, var4, var6);
      BTreeLockingPolicy var12 = var9.getBtreeLockingPolicy(var2, var4, var3, var6, this.base_cc_for_locking, this);
      super.init(var1, var2, false, var3, var4, var12, var8, (DataValueDescriptor[])null, 0, (Qualifier[][])null, (DataValueDescriptor[])null, 0, var9, var10, (StaticCompiledOpenConglomInfo)null, (DynamicCompiledOpenConglomInfo)null);
   }
}
