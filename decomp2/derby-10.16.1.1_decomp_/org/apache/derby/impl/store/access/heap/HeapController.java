package org.apache.derby.impl.store.access.heap;

import org.apache.derby.iapi.services.io.FormatableBitSet;
import org.apache.derby.iapi.store.access.ConglomerateController;
import org.apache.derby.iapi.store.access.DynamicCompiledOpenConglomInfo;
import org.apache.derby.iapi.store.access.RowLocationRetRowSource;
import org.apache.derby.iapi.store.access.RowUtil;
import org.apache.derby.iapi.store.access.conglomerate.TransactionManager;
import org.apache.derby.iapi.store.raw.ContainerHandle;
import org.apache.derby.iapi.store.raw.Page;
import org.apache.derby.iapi.store.raw.RecordHandle;
import org.apache.derby.iapi.types.DataValueDescriptor;
import org.apache.derby.iapi.types.RowLocation;
import org.apache.derby.impl.store.access.conglomerate.GenericConglomerateController;
import org.apache.derby.impl.store.access.conglomerate.OpenConglomerate;
import org.apache.derby.impl.store.access.conglomerate.RowPosition;
import org.apache.derby.shared.common.error.StandardException;

public class HeapController extends GenericConglomerateController implements ConglomerateController {
   protected final void getRowPositionFromRowLocation(RowLocation var1, RowPosition var2) throws StandardException {
      var2.current_rh = ((HeapRowLocation)var1).getRecordHandle(this.open_conglom.getContainer());
      var2.current_rh_qualified = true;
   }

   protected void queueDeletePostCommitWork(RowPosition var1) throws StandardException {
      TransactionManager var2 = this.open_conglom.getXactMgr();
      var2.addPostCommitWork(new HeapPostCommit(var2.getAccessManager(), var1.current_page.getPageKey()));
   }

   protected final boolean purgeCommittedDeletes(Page var1) throws StandardException {
      boolean var2 = false;
      int var3 = var1.recordCount() - var1.nonDeletedRecordCount();
      if (var3 > 0) {
         for(int var4 = var1.recordCount() - 1; var4 >= 0; --var4) {
            boolean var5 = var1.isDeletedAtSlot(var4);
            if (var5) {
               RecordHandle var6 = var1.fetchFromSlot((RecordHandle)null, var4, RowUtil.EMPTY_ROW, RowUtil.EMPTY_ROW_FETCH_DESCRIPTOR, true);
               var5 = this.lockRowAtSlotNoWaitExclusive(var6);
               if (var5) {
                  var2 = true;
                  var1.purgeAtSlot(var4, 1, false);
               }
            }
         }
      }

      if (var1.recordCount() == 0) {
         this.removePage(var1);
         var2 = true;
      }

      return var2;
   }

   private RecordHandle doInsert(DataValueDescriptor[] var1) throws StandardException {
      Page var2 = null;
      var2 = this.open_conglom.getContainer().getPageForInsert(0);
      if (var2 != null) {
         int var3 = var2.recordCount() == 0 ? 8 : 1;
         RecordHandle var4 = var2.insert(var1, (FormatableBitSet)null, (byte)var3, 100);
         var2.unlatch();
         var2 = null;
         if (var4 != null) {
            return var4;
         }
      }

      var2 = this.open_conglom.getContainer().getPageForInsert(1);
      if (var2 != null) {
         int var11 = var2.recordCount() == 0 ? 8 : 1;
         RecordHandle var12 = var2.insert(var1, (FormatableBitSet)null, (byte)var11, 100);
         var2.unlatch();
         var2 = null;
         if (var12 != null) {
            return var12;
         }
      }

      var2 = this.open_conglom.getContainer().addPage();
      RecordHandle var13 = var2.insert(var1, (FormatableBitSet)null, (byte)8, 100);
      var2.unlatch();
      var2 = null;
      return var13;
   }

   protected long load(TransactionManager var1, Heap var2, boolean var3, RowLocationRetRowSource var4) throws StandardException {
      long var5 = 0L;
      int var7 = 5;
      if (var3) {
         var7 |= 2;
      }

      OpenHeap var8 = new OpenHeap();
      if (((OpenConglomerate)var8).init((ContainerHandle)null, var2, var2.format_ids, var2.collation_ids, var1, var1.getRawStoreXact(), false, var7, 7, var1.getRawStoreXact().newLockingPolicy(2, 5, true), (DynamicCompiledOpenConglomInfo)null) == null) {
         throw StandardException.newException("XSCH1.S", new Object[]{var2.getId().getContainerId()});
      } else {
         this.init(var8);
         Page var9 = ((OpenConglomerate)var8).getContainer().addPage();
         boolean var10 = var4.needsRowLocation();
         HeapRowLocation var12;
         if (!var10 && !var4.needsRowLocationForDeferredCheckConstraints()) {
            var12 = null;
         } else {
            var12 = new HeapRowLocation();
         }

         FormatableBitSet var13 = var4.getValidColumns();

         try {
            DataValueDescriptor[] var14;
            while((var14 = var4.getNextRowFromRowSource()) != null) {
               ++var5;
               RecordHandle var11;
               if ((var11 = var9.insert(var14, var13, (byte)1, 100)) == null) {
                  var9.unlatch();
                  Object var18 = null;
                  var9 = ((OpenConglomerate)var8).getContainer().addPage();
                  var11 = var9.insert(var14, var13, (byte)8, 100);
               }

               if (var10) {
                  var12.setFrom(var11);
                  var4.rowLocation(var12);
               }

               if (var4.needsRowLocationForDeferredCheckConstraints()) {
                  var12.setFrom(var11);
                  var4.offendingRowLocation(var12, var2.getContainerid());
               }
            }

            var9.unlatch();
            Object var19 = null;
            if (!var2.isTemporary()) {
               ((OpenConglomerate)var8).getContainer().flushContainer();
            }
         } finally {
            this.close();
         }

         return var5;
      }
   }

   protected boolean lockRow(RecordHandle var1, int var2, boolean var3, int var4) throws StandardException {
      boolean var6 = (1 & var2) != 0;
      boolean var7 = (8 & var2) != 0;
      boolean var5;
      if (var6 && !var7) {
         boolean var8 = (2 & var2) != 0;
         boolean var9 = (4 & var2) != 0;
         if (var4 == 1) {
            var5 = this.open_conglom.getContainer().getLockingPolicy().zeroDurationLockRecordForWrite(this.open_conglom.getRawTran(), var1, var9, var3);
         } else {
            var5 = this.open_conglom.getContainer().getLockingPolicy().lockRecordForWrite(this.open_conglom.getRawTran(), var1, var8, var3);
         }
      } else {
         var5 = this.open_conglom.getContainer().getLockingPolicy().lockRecordForRead(this.open_conglom.getRawTran(), this.open_conglom.getContainer(), var1, var3, var6);
      }

      return var5;
   }

   protected Page getUserPageNoWait(long var1) throws StandardException {
      return this.open_conglom.getContainer().getUserPageNoWait(var1);
   }

   protected Page getUserPageWait(long var1) throws StandardException {
      return this.open_conglom.getContainer().getUserPageWait(var1);
   }

   protected boolean lockRowAtSlotNoWaitExclusive(RecordHandle var1) throws StandardException {
      try {
         return this.open_conglom.getContainer().getLockingPolicy().lockRecordForWrite(this.open_conglom.getRawTran(), var1, false, false);
      } catch (StandardException var3) {
         if (var3.isSelfDeadlock()) {
            return false;
         } else {
            throw var3;
         }
      }
   }

   protected void removePage(Page var1) throws StandardException {
      this.open_conglom.getContainer().removePage(var1);
   }

   public int insert(DataValueDescriptor[] var1) throws StandardException {
      if (this.open_conglom.isClosed()) {
         if (!this.open_conglom.getHold()) {
            throw StandardException.newException("XSCH6.S", new Object[]{this.open_conglom.getConglomerate().getId()});
         }

         this.open_conglom.reopen();
      }

      this.doInsert(var1);
      return 0;
   }

   public void insertAndFetchLocation(DataValueDescriptor[] var1, RowLocation var2) throws StandardException {
      if (this.open_conglom.isClosed()) {
         if (!this.open_conglom.getHold()) {
            throw StandardException.newException("XSCH6.S", new Object[]{this.open_conglom.getConglomerate().getId()});
         }

         this.open_conglom.reopen();
      }

      RecordHandle var3 = this.doInsert(var1);
      HeapRowLocation var4 = (HeapRowLocation)var2;
      var4.setFrom(var3);
   }

   public boolean lockRow(RowLocation var1, int var2, boolean var3, int var4) throws StandardException {
      RecordHandle var5 = ((HeapRowLocation)var1).getRecordHandle(this.open_conglom.getContainer());
      return this.lockRow(var5, var2, var3, var4);
   }

   public void unlockRowAfterRead(RowLocation var1, boolean var2, boolean var3) throws StandardException {
      RecordHandle var4 = ((HeapRowLocation)var1).getRecordHandle(this.open_conglom.getContainer());
      this.open_conglom.getContainer().getLockingPolicy().unlockRecordAfterRead(this.open_conglom.getRawTran(), this.open_conglom.getContainer(), var4, this.open_conglom.isForUpdate(), var3);
   }

   public boolean lockRow(long var1, int var3, int var4, boolean var5, int var6) throws StandardException {
      RecordHandle var8 = this.open_conglom.getContainer().makeRecordHandle(var1, var3);
      return this.lockRow(var8, var4, var5, var6);
   }

   public RowLocation newRowLocationTemplate() throws StandardException {
      if (this.open_conglom.isClosed()) {
         if (!this.open_conglom.getHold()) {
            throw StandardException.newException("XSCH6.S", new Object[]{this.open_conglom.getConglomerate().getId()});
         }

         this.open_conglom.reopen();
      }

      return new HeapRowLocation();
   }

   public OpenConglomerate getOpenConglomerate() {
      return this.open_conglom;
   }
}
