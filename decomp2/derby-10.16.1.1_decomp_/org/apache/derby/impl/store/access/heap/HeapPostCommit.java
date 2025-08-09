package org.apache.derby.impl.store.access.heap;

import org.apache.derby.iapi.services.context.ContextManager;
import org.apache.derby.iapi.services.daemon.Serviceable;
import org.apache.derby.iapi.store.access.AccessFactory;
import org.apache.derby.iapi.store.access.DynamicCompiledOpenConglomInfo;
import org.apache.derby.iapi.store.access.RowUtil;
import org.apache.derby.iapi.store.access.StaticCompiledOpenConglomInfo;
import org.apache.derby.iapi.store.access.conglomerate.TransactionManager;
import org.apache.derby.iapi.store.raw.Page;
import org.apache.derby.iapi.store.raw.PageKey;
import org.apache.derby.iapi.store.raw.RecordHandle;
import org.apache.derby.shared.common.error.StandardException;

class HeapPostCommit implements Serviceable {
   private AccessFactory access_factory = null;
   private PageKey page_key = null;

   HeapPostCommit(AccessFactory var1, PageKey var2) {
      this.access_factory = var1;
      this.page_key = var2;
   }

   private final void purgeCommittedDeletes(HeapController var1, long var2) throws StandardException {
      Page var4 = var1.getUserPageWait(var2);
      boolean var5 = false;
      if (var4 != null) {
         try {
            int var6 = var4.recordCount() - var4.nonDeletedRecordCount();
            if (var6 > 0) {
               for(int var7 = var4.recordCount() - 1; var7 >= 0; --var7) {
                  boolean var8 = var4.isDeletedAtSlot(var7);
                  if (var8) {
                     RecordHandle var9 = var4.fetchFromSlot((RecordHandle)null, var7, RowUtil.EMPTY_ROW, RowUtil.EMPTY_ROW_FETCH_DESCRIPTOR, true);
                     var8 = var1.lockRowAtSlotNoWaitExclusive(var9);
                     if (var8) {
                        var5 = true;
                        var4.purgeAtSlot(var7, 1, false);
                     }
                  }
               }
            }

            if (var4.recordCount() == 0) {
               var5 = true;
               var1.removePage(var4);
            }
         } finally {
            if (!var5) {
               var4.unlatch();
               Object var13 = null;
            }

         }
      }

   }

   public boolean serviceASAP() {
      return true;
   }

   public boolean serviceImmediately() {
      return false;
   }

   public int performWork(ContextManager var1) throws StandardException {
      TransactionManager var2 = (TransactionManager)this.access_factory.getAndNameTransaction(var1, "SystemTransaction");
      TransactionManager var3 = var2.getInternalTransaction();
      boolean var4 = false;

      try {
         HeapController var5 = (HeapController)Heap.openByContainerKey(this.page_key.getContainerId(), var3, var3.getRawStoreXact(), false, 132, 6, var3.getRawStoreXact().newLockingPolicy(1, 4, true), (StaticCompiledOpenConglomInfo)null, (DynamicCompiledOpenConglomInfo)null);
         this.purgeCommittedDeletes(var5, this.page_key.getPageNumber());
      } catch (StandardException var7) {
         if (var7.isLockTimeoutOrDeadlock()) {
            var4 = true;
         }
      }

      var3.commitNoSync(1);
      var3.destroy();
      return var4 ? 2 : 1;
   }
}
