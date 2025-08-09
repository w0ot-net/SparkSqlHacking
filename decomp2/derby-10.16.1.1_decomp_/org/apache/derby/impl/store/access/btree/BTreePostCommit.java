package org.apache.derby.impl.store.access.btree;

import org.apache.derby.iapi.services.context.ContextManager;
import org.apache.derby.iapi.services.daemon.Serviceable;
import org.apache.derby.iapi.store.access.AccessFactory;
import org.apache.derby.iapi.store.access.ConglomerateController;
import org.apache.derby.iapi.store.access.DynamicCompiledOpenConglomInfo;
import org.apache.derby.iapi.store.access.RowUtil;
import org.apache.derby.iapi.store.access.conglomerate.LogicalUndo;
import org.apache.derby.iapi.store.access.conglomerate.TransactionManager;
import org.apache.derby.iapi.store.raw.ContainerHandle;
import org.apache.derby.iapi.store.raw.FetchDescriptor;
import org.apache.derby.iapi.store.raw.Page;
import org.apache.derby.iapi.store.raw.RecordHandle;
import org.apache.derby.iapi.types.DataValueDescriptor;
import org.apache.derby.shared.common.error.StandardException;

class BTreePostCommit implements Serviceable {
   private AccessFactory access_factory = null;
   private long page_number = -1L;
   protected BTree btree = null;

   BTreePostCommit(AccessFactory var1, BTree var2, long var3) {
      this.access_factory = var1;
      this.btree = var2;
      this.page_number = var3;
   }

   public boolean serviceASAP() {
      return true;
   }

   public boolean serviceImmediately() {
      return false;
   }

   private final void doShrink(OpenBTree var1, DataValueDescriptor[] var2) throws StandardException {
      ControlRow var3 = null;
      var3 = ControlRow.get(var1, 1L);
      var3.shrinkFor(var1, var2);
      var3 = null;
   }

   private final OpenBTree openIndex(TransactionManager var1, int var2, int var3) throws StandardException {
      OpenBTree var4 = new OpenBTree();
      ConglomerateController var5 = this.btree.lockTable(var1, 132, var2, 4);
      var4.init((TransactionManager)null, var1, (ContainerHandle)null, var1.getRawStoreXact(), false, 132, var2, this.btree.getBtreeLockingPolicy(var1.getRawStoreXact(), var2, var3, 4, var5, var4), this.btree, (LogicalUndo)null, (DynamicCompiledOpenConglomInfo)null);
      return var4;
   }

   public int performWork(ContextManager var1) throws StandardException {
      boolean var2 = false;
      TransactionManager var3 = (TransactionManager)this.access_factory.getAndNameTransaction(var1, "SystemTransaction");
      TransactionManager var4 = var3.getInternalTransaction();
      OpenBTree var5 = null;

      try {
         var5 = this.openIndex(var4, 7, 2);
         DataValueDescriptor[] var6 = this.purgeCommittedDeletes(var5, this.page_number);
         if (var6 != null) {
            this.doShrink(var5, var6);
         }
      } catch (StandardException var13) {
         if (var13.isLockTimeoutOrDeadlock()) {
            try {
               var5 = this.openIndex(var4, 6, 1);
               this.purgeRowLevelCommittedDeletes(var5);
            } catch (StandardException var12) {
               if (var12.isLockTimeoutOrDeadlock()) {
                  var2 = true;
               }
            }
         }
      } finally {
         if (var5 != null) {
            var5.close();
         }

         var4.commit();
         var4.destroy();
      }

      return var2 ? 2 : 1;
   }

   private final DataValueDescriptor[] getShrinkKey(OpenBTree var1, ControlRow var2, int var3) throws StandardException {
      DataValueDescriptor[] var4 = var1.getConglomerate().createTemplate(var1.getRawTran());
      var2.page.fetchFromSlot((RecordHandle)null, var3, var4, (FetchDescriptor)null, true);
      return var4;
   }

   private final DataValueDescriptor[] purgeCommittedDeletes(OpenBTree var1, long var2) throws StandardException {
      ControlRow var4 = null;
      DataValueDescriptor[] var5 = null;

      try {
         var4 = ControlRow.getNoWait(var1, var2);
         if (var4 != null) {
            Page var6 = var4.page;
            int var7 = var6.recordCount() - 1 - var6.nonDeletedRecordCount();
            if (var7 > 0) {
               for(int var8 = var6.recordCount() - 1; var8 > 0; --var8) {
                  if (var6.isDeletedAtSlot(var8)) {
                     if (var6.recordCount() == 2) {
                        var5 = this.getShrinkKey(var1, var4, var8);
                     }

                     var6.purgeAtSlot(var8, 1, true);
                     var6.setRepositionNeeded();
                  }
               }
            }

            if (var6.recordCount() == 1) {
            }
         }
      } finally {
         if (var4 != null) {
            var4.release();
         }

      }

      return var5;
   }

   private final void purgeRowLevelCommittedDeletes(OpenBTree var1) throws StandardException {
      LeafControlRow var2 = null;
      var2 = (LeafControlRow)ControlRow.getNoWait(var1, this.page_number);
      if (var2 != null) {
         BTreeLockingPolicy var3 = var1.getLockingPolicy();
         int var4 = var2.page.recordCount() - 1 - var2.page.nonDeletedRecordCount();
         if (var4 > 0) {
            DataValueDescriptor[] var5 = var1.getRuntimeMem().get_template(var1.getRawTran());
            Page var6 = var2.page;
            FetchDescriptor var7 = RowUtil.getFetchDescriptorConstant(var5.length - 1);

            for(int var8 = var6.recordCount() - 1; var8 > 0; --var8) {
               if (var6.isDeletedAtSlot(var8) && var3.lockScanCommittedDeletedRow(var1, var2, var5, var7, var8)) {
                  var6.purgeAtSlot(var8, 1, true);
                  var6.setRepositionNeeded();
               }
            }
         }

      }
   }
}
