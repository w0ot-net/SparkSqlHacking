package org.apache.derby.impl.store.access.btree.index;

import org.apache.derby.iapi.store.access.ConglomerateController;
import org.apache.derby.iapi.store.access.DynamicCompiledOpenConglomInfo;
import org.apache.derby.iapi.store.access.conglomerate.Conglomerate;
import org.apache.derby.iapi.store.access.conglomerate.TransactionManager;
import org.apache.derby.iapi.store.raw.ContainerHandle;
import org.apache.derby.iapi.store.raw.LockingPolicy;
import org.apache.derby.iapi.store.raw.Transaction;
import org.apache.derby.iapi.types.DataValueDescriptor;
import org.apache.derby.impl.store.access.btree.BTreeController;
import org.apache.derby.impl.store.access.btree.BTreeLockingPolicy;
import org.apache.derby.shared.common.error.StandardException;

public class B2IController extends BTreeController {
   private ConglomerateController base_cc_for_locking;

   B2IController() {
   }

   void init(TransactionManager var1, Transaction var2, boolean var3, int var4, int var5, LockingPolicy var6, boolean var7, B2I var8, B2IUndo var9, B2IStaticCompiledInfo var10, DynamicCompiledOpenConglomInfo var11) throws StandardException {
      int var12 = var4 | 64;
      if (var10 != null) {
         this.base_cc_for_locking = var1.openCompiledConglomerate(false, var12, var5, 4, var10.base_table_static_info, ((Conglomerate)var10.getConglom()).getDynamicCompiledConglomInfo());
      } else {
         this.base_cc_for_locking = var1.openConglomerate(var8.baseConglomerateId, false, var12, var5, 4);
      }

      Object var13;
      if (var5 == 7) {
         var13 = new B2ITableLocking3(var2, var5, var6, this.base_cc_for_locking, this);
      } else if (var5 == 6) {
         var13 = new B2IRowLocking3(var2, var5, var6, this.base_cc_for_locking, this);
      } else {
         var13 = null;
      }

      super.init(var1, var3, (ContainerHandle)null, var2, var4, var5, (BTreeLockingPolicy)var13, var8, var9, var10, var11);
   }

   public void close() throws StandardException {
      super.close();
      if (this.base_cc_for_locking != null) {
         this.base_cc_for_locking.close();
         this.base_cc_for_locking = null;
      }

   }

   public int insert(DataValueDescriptor[] var1) throws StandardException {
      return super.insert(var1);
   }
}
