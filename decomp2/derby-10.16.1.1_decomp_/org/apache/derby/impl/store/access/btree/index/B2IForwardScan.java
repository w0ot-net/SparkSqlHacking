package org.apache.derby.impl.store.access.btree.index;

import org.apache.derby.iapi.services.io.FormatableBitSet;
import org.apache.derby.iapi.store.access.ConglomerateController;
import org.apache.derby.iapi.store.access.DynamicCompiledOpenConglomInfo;
import org.apache.derby.iapi.store.access.Qualifier;
import org.apache.derby.iapi.store.access.conglomerate.Conglomerate;
import org.apache.derby.iapi.store.access.conglomerate.TransactionManager;
import org.apache.derby.iapi.store.raw.ContainerHandle;
import org.apache.derby.iapi.store.raw.LockingPolicy;
import org.apache.derby.iapi.store.raw.Transaction;
import org.apache.derby.iapi.types.DataValueDescriptor;
import org.apache.derby.impl.store.access.btree.BTreeForwardScan;
import org.apache.derby.impl.store.access.btree.BTreeLockingPolicy;
import org.apache.derby.shared.common.error.StandardException;

public class B2IForwardScan extends BTreeForwardScan {
   private ConglomerateController base_cc_for_locking;
   private int init_isolation_level;

   B2IForwardScan() {
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

   public ContainerHandle reopen() throws StandardException {
      ContainerHandle var1 = super.reopen();
      B2I var2 = (B2I)this.getConglomerate();
      int var3 = this.getOpenMode() | 64;
      this.base_cc_for_locking = this.getXactMgr().openConglomerate(var2.baseConglomerateId, false, var3, this.init_lock_level, this.init_isolation_level);
      this.setLockingPolicy(var2.getBtreeLockingPolicy(this.getXactMgr().getRawStoreXact(), this.getLockLevel(), this.getOpenMode(), this.init_isolation_level, this.base_cc_for_locking, this));
      return var1;
   }

   public void init(TransactionManager var1, Transaction var2, boolean var3, int var4, int var5, LockingPolicy var6, int var7, boolean var8, FormatableBitSet var9, DataValueDescriptor[] var10, int var11, Qualifier[][] var12, DataValueDescriptor[] var13, int var14, B2I var15, B2IUndo var16, B2IStaticCompiledInfo var17, DynamicCompiledOpenConglomInfo var18) throws StandardException {
      int var19 = var4 | 64;
      if (var17 != null) {
         this.base_cc_for_locking = var1.openCompiledConglomerate(false, var19, var5, var7, var17.base_table_static_info, ((Conglomerate)var17.getConglom()).getDynamicCompiledConglomInfo());
      } else {
         this.base_cc_for_locking = var1.openConglomerate(var15.baseConglomerateId, false, var19, var5, var7);
      }

      BTreeLockingPolicy var20 = var15.getBtreeLockingPolicy(var2, var5, var4, var7, this.base_cc_for_locking, this);
      super.init(var1, var2, var3, var4, var5, var20, var9, var10, var11, var12, var13, var14, var15, var16, var17, var18);
      this.init_isolation_level = var7;
   }
}
