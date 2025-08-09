package org.apache.derby.impl.store.access.btree;

import org.apache.derby.iapi.services.io.FormatableBitSet;
import org.apache.derby.iapi.store.access.BackingStoreHashtable;
import org.apache.derby.iapi.store.access.DynamicCompiledOpenConglomInfo;
import org.apache.derby.iapi.store.access.Qualifier;
import org.apache.derby.iapi.store.access.RowUtil;
import org.apache.derby.iapi.store.access.ScanInfo;
import org.apache.derby.iapi.store.access.StaticCompiledOpenConglomInfo;
import org.apache.derby.iapi.store.access.conglomerate.LogicalUndo;
import org.apache.derby.iapi.store.access.conglomerate.ScanManager;
import org.apache.derby.iapi.store.access.conglomerate.TransactionManager;
import org.apache.derby.iapi.store.raw.ContainerHandle;
import org.apache.derby.iapi.store.raw.FetchDescriptor;
import org.apache.derby.iapi.store.raw.Page;
import org.apache.derby.iapi.store.raw.RecordHandle;
import org.apache.derby.iapi.store.raw.Transaction;
import org.apache.derby.iapi.types.DataValueDescriptor;
import org.apache.derby.iapi.types.RowLocation;
import org.apache.derby.shared.common.error.StandardException;

public abstract class BTreeScan extends OpenBTree implements ScanManager {
   protected Transaction init_rawtran = null;
   protected boolean init_forUpdate;
   protected FormatableBitSet init_scanColumnList;
   protected DataValueDescriptor[] init_template;
   protected DataValueDescriptor[] init_startKeyValue;
   protected int init_startSearchOperator = 0;
   protected Qualifier[][] init_qualifier = null;
   protected DataValueDescriptor[] init_stopKeyValue;
   protected int init_stopSearchOperator = 0;
   protected boolean init_hold;
   protected FetchDescriptor init_fetchDesc;
   protected FetchDescriptor init_lock_fetch_desc;
   BTreeRowPosition scan_position;
   protected boolean init_useUpdateLocks = false;
   protected static final int SCAN_INIT = 1;
   protected static final int SCAN_INPROGRESS = 2;
   protected static final int SCAN_DONE = 3;
   protected static final int SCAN_HOLD_INIT = 4;
   protected static final int SCAN_HOLD_INPROGRESS = 5;
   protected int scan_state = 1;
   protected int stat_numpages_visited = 0;
   protected int stat_numrows_visited = 0;
   protected int stat_numrows_qualified = 0;
   protected int stat_numdeleted_rows_visited = 0;
   protected int lock_operation;
   protected DataValueDescriptor[][] fetchNext_one_slot_array = new DataValueDescriptor[1][];

   protected abstract int fetchRows(BTreeRowPosition var1, DataValueDescriptor[][] var2, RowLocation[] var3, BackingStoreHashtable var4, long var5, int[] var7) throws StandardException;

   private void initScanParams(DataValueDescriptor[] var1, int var2, Qualifier[][] var3, DataValueDescriptor[] var4, int var5) throws StandardException {
      this.init_startKeyValue = var1;
      if (RowUtil.isRowEmpty(this.init_startKeyValue)) {
         this.init_startKeyValue = null;
      }

      this.init_startSearchOperator = var2;
      if (var3 != null && var3.length == 0) {
         var3 = null;
      }

      this.init_qualifier = var3;
      this.init_stopKeyValue = var4;
      if (RowUtil.isRowEmpty(this.init_stopKeyValue)) {
         this.init_stopKeyValue = null;
      }

      this.init_stopSearchOperator = var5;
      this.scan_position = new BTreeRowPosition(this);
      this.scan_position.init();
      this.scan_position.current_lock_template = new DataValueDescriptor[this.init_template.length];
      this.scan_position.current_lock_template[this.init_template.length - 1] = this.scan_position.current_lock_row_loc = (RowLocation)this.init_template[this.init_template.length - 1].cloneValue(false);
   }

   protected void positionAtStartForForwardScan(BTreeRowPosition var1) throws StandardException {
      while(true) {
         ControlRow var3 = ControlRow.get((OpenBTree)this, 1L);
         this.stat_numpages_visited += var3.getLevel() + 1;
         boolean var4 = true;
         if (this.init_startKeyValue == null) {
            var1.current_leaf = (LeafControlRow)var3.searchLeft(this);
            var1.current_slot = 0;
            boolean var2 = false;
         } else {
            SearchParameters var5 = new SearchParameters(this.init_startKeyValue, this.init_startSearchOperator == 1 ? 1 : -1, this.init_template, this, false);
            var1.current_leaf = (LeafControlRow)var3.search(var5);
            var1.current_slot = var5.resultSlot;
            boolean var6 = var5.resultExact;
            if (var6 && this.init_startSearchOperator == 1) {
               --var1.current_slot;
               if (this.getConglomerate().nUniqueColumns < this.getConglomerate().nKeyFields) {
                  var4 = false;
               }
            }
         }

         boolean var7 = false;
         if (var4) {
            var7 = !this.getLockingPolicy().lockScanRow(this, var1, this.init_lock_fetch_desc, var1.current_lock_template, var1.current_lock_row_loc, true, this.init_forUpdate, this.lock_operation);
         }

         if (!var7) {
            this.scan_state = 2;
            return;
         }

         var1.init();
      }
   }

   protected void positionAtNextPage(BTreeRowPosition var1) throws StandardException {
      var1.next_leaf = (LeafControlRow)var1.current_leaf.getRightSibling(this);
      if (var1.current_rh != null) {
         this.getLockingPolicy().unlockScanRecordAfterRead(var1, this.init_forUpdate);
      }

      var1.current_leaf.release();
      var1.current_leaf = var1.next_leaf;
      var1.current_slot = 0;
      var1.current_rh = null;
   }

   protected void positionAtPreviousPage() throws StandardException, WaitError {
      BTreeRowPosition var1 = this.scan_position;

      LeafControlRow var2;
      LeafControlRow var3;
      for(var2 = (LeafControlRow)var1.current_leaf.getLeftSibling(this); var2 != null && isEmpty(var2.page); var2 = var3) {
         try {
            var3 = (LeafControlRow)var2.getLeftSibling(this);
         } finally {
            var2.release();
         }
      }

      if (var1.current_rh != null) {
         this.getLockingPolicy().unlockScanRecordAfterRead(var1, this.init_forUpdate);
      }

      var1.current_leaf.release();
      var1.current_leaf = var2;
      var1.current_slot = var1.current_leaf == null ? -1 : var1.current_leaf.page.recordCount();
      var1.current_rh = null;
   }

   static boolean isEmpty(Page var0) throws StandardException {
      return var0.recordCount() <= 1;
   }

   abstract void positionAtStartPosition(BTreeRowPosition var1) throws StandardException;

   protected void positionAtDoneScanFromClose(BTreeRowPosition var1) throws StandardException {
      if (var1.current_rh != null && !var1.current_rh_qualified && (var1.current_leaf == null || var1.current_leaf.page == null)) {
         this.getLockingPolicy().unlockScanRecordAfterRead(var1, this.init_forUpdate);
      }

      var1.current_slot = -1;
      var1.current_rh = null;
      var1.current_positionKey = null;
      this.scan_state = 3;
   }

   protected void positionAtDoneScan(BTreeRowPosition var1) throws StandardException {
      var1.current_slot = -1;
      var1.current_rh = null;
      var1.current_positionKey = null;
      this.scan_state = 3;
   }

   protected boolean process_qualifier(DataValueDescriptor[] var1) throws StandardException {
      boolean var2 = true;

      for(int var4 = 0; var4 < this.init_qualifier[0].length; ++var4) {
         var2 = false;
         Qualifier var3 = this.init_qualifier[0][var4];
         DataValueDescriptor var5 = var1[var3.getColumnId()];
         var2 = var5.compare(var3.getOperator(), var3.getOrderable(), var3.getOrderedNulls(), var3.getUnknownRV());
         if (var3.negateCompareResult()) {
            var2 = !var2;
         }

         if (!var2) {
            return false;
         }
      }

      for(int var9 = 1; var9 < this.init_qualifier.length; ++var9) {
         var2 = false;

         for(int var10 = 0; var10 < this.init_qualifier[var9].length; ++var10) {
            Qualifier var8 = this.init_qualifier[var9][var10];
            DataValueDescriptor var6 = var1[var8.getColumnId()];
            var2 = var6.compare(var8.getOperator(), var8.getOrderable(), var8.getOrderedNulls(), var8.getUnknownRV());
            if (var8.negateCompareResult()) {
               var2 = !var2;
            }

            if (var2) {
               break;
            }
         }

         if (!var2) {
            break;
         }
      }

      return var2;
   }

   protected boolean reposition(BTreeRowPosition var1, boolean var2) throws StandardException {
      if (this.scan_state != 2) {
         throw StandardException.newException("XSCB4.S", new Object[]{this.scan_state});
      } else if (var1.current_positionKey == null) {
         throw StandardException.newException("XSCB7.S", new Object[]{var1.current_rh == null, var1.current_positionKey == null});
      } else {
         if (var1.current_rh != null) {
            Page var3 = this.container.getPage(var1.current_rh.getPageNumber());
            if (var3 != null) {
               ControlRow var4 = ControlRow.getControlRowForPage(this.container, var3);
               if (var4 instanceof LeafControlRow && !var4.page.isRepositionNeeded(var1.versionWhenSaved)) {
                  var1.current_leaf = (LeafControlRow)var4;
                  var1.current_slot = var4.page.getSlotNumber(var1.current_rh);
                  var1.current_positionKey = null;
                  return true;
               }

               var4.release();
            }
         }

         SearchParameters var5 = new SearchParameters(var1.current_positionKey, 1, this.init_template, this, false);
         var1.current_leaf = (LeafControlRow)ControlRow.get((OpenBTree)this, 1L).search(var5);
         if (!var5.resultExact && !var2) {
            var1.current_leaf.release();
            var1.current_leaf = null;
            return false;
         } else {
            var1.current_slot = var5.resultSlot;
            if (var1.current_rh != null) {
               var1.current_rh = var1.current_leaf.page.getRecordHandleAtSlot(var1.current_slot);
            }

            var1.current_positionKey = null;
            return true;
         }
      }
   }

   public void init(TransactionManager var1, Transaction var2, boolean var3, int var4, int var5, BTreeLockingPolicy var6, FormatableBitSet var7, DataValueDescriptor[] var8, int var9, Qualifier[][] var10, DataValueDescriptor[] var11, int var12, BTree var13, LogicalUndo var14, StaticCompiledOpenConglomInfo var15, DynamicCompiledOpenConglomInfo var16) throws StandardException {
      super.init(var1, var1, (ContainerHandle)null, var2, var3, var4, var5, var6, var13, var14, var16);
      this.init_rawtran = var2;
      this.init_forUpdate = (var4 & 4) == 4;
      this.init_useUpdateLocks = (var4 & 4096) != 0;
      this.init_hold = var3;
      this.init_template = this.runtime_mem.get_template(this.getRawTran());
      this.init_scanColumnList = var7;
      this.init_lock_fetch_desc = RowUtil.getFetchDescriptorConstant(this.init_template.length - 1);
      this.init_fetchDesc = new FetchDescriptor(this.init_template.length, this.init_scanColumnList, (Qualifier[][])null);
      this.initScanParams(var8, var9, var10, var11, var12);
      this.lock_operation = this.init_forUpdate ? 1 : 0;
      if (this.init_useUpdateLocks) {
         this.lock_operation |= 8;
      }

   }

   public void close() throws StandardException {
      this.positionAtDoneScanFromClose(this.scan_position);
      super.close();
      this.init_rawtran = null;
      this.init_template = null;
      this.init_startKeyValue = null;
      this.init_qualifier = null;
      this.init_stopKeyValue = null;
      this.getXactMgr().closeMe((ScanManager)this);
   }

   public boolean delete() throws StandardException {
      boolean var1 = false;
      if (this.scan_state != 2) {
         throw StandardException.newException("XSAM5.S", new Object[0]);
      } else {
         try {
            if (!this.reposition(this.scan_position, false)) {
               throw StandardException.newException("XSAM6.S", new Object[]{this.err_containerid, this.scan_position.current_rh.getPageNumber(), this.scan_position.current_rh.getId()});
            }

            if (this.init_useUpdateLocks) {
               boolean var2 = !this.getLockingPolicy().lockScanRow(this, this.scan_position, this.init_lock_fetch_desc, this.scan_position.current_lock_template, this.scan_position.current_lock_row_loc, false, this.init_forUpdate, this.lock_operation);
               if (var2 && !this.reposition(this.scan_position, false)) {
                  throw StandardException.newException("XSAM6.S", new Object[]{this.err_containerid, this.scan_position.current_rh.getPageNumber(), this.scan_position.current_rh.getId()});
               }
            }

            if (this.scan_position.current_leaf.page.isDeletedAtSlot(this.scan_position.current_slot)) {
               var1 = false;
            } else {
               this.scan_position.current_leaf.page.deleteAtSlot(this.scan_position.current_slot, true, this.btree_undo);
               var1 = true;
            }

            if (this.scan_position.current_leaf.page.nonDeletedRecordCount() == 1 && (!this.scan_position.current_leaf.getIsRoot() || this.scan_position.current_leaf.getLevel() != 0)) {
               this.getXactMgr().addPostCommitWork(new BTreePostCommit(this.getXactMgr().getAccessManager(), this.getConglomerate(), this.scan_position.current_leaf.page.getPageNumber()));
            }
         } finally {
            if (this.scan_position.current_leaf != null) {
               this.savePositionAndReleasePage();
            }

         }

         return var1;
      }
   }

   public void didNotQualify() throws StandardException {
   }

   public boolean doesCurrentPositionQualify() throws StandardException {
      if (this.scan_state != 2) {
         throw StandardException.newException("XSAM5.S", new Object[0]);
      } else {
         boolean var1;
         try {
            if (this.reposition(this.scan_position, false)) {
               var1 = !this.scan_position.current_leaf.page.isDeletedAtSlot(this.scan_position.current_slot);
               return var1;
            }

            var1 = false;
         } finally {
            if (this.scan_position.current_leaf != null) {
               this.savePositionAndReleasePage();
            }

         }

         return var1;
      }
   }

   private void fetch(DataValueDescriptor[] var1, boolean var2) throws StandardException {
      if (this.scan_state != 2) {
         throw StandardException.newException("XSAM5.S", new Object[0]);
      } else {
         try {
            if (!this.reposition(this.scan_position, false)) {
               throw StandardException.newException("XSAM6.S", new Object[]{this.err_containerid, this.scan_position.current_rh.getPageNumber(), this.scan_position.current_rh.getId()});
            }

            this.scan_position.current_rh = this.scan_position.current_leaf.page.fetchFromSlot((RecordHandle)null, this.scan_position.current_slot, var1, var2 ? this.init_fetchDesc : null, true);
            if (this.scan_position.current_leaf.page.isDeletedAtSlot(this.scan_position.current_slot)) {
            }
         } finally {
            if (this.scan_position.current_leaf != null) {
               this.savePositionAndReleasePage();
            }

         }

      }
   }

   public boolean isHeldAfterCommit() throws StandardException {
      return this.scan_state == 4 || this.scan_state == 5;
   }

   public void fetch(DataValueDescriptor[] var1) throws StandardException {
      this.fetch(var1, true);
   }

   public void fetchWithoutQualify(DataValueDescriptor[] var1) throws StandardException {
      this.fetch(var1, false);
   }

   public ScanInfo getScanInfo() throws StandardException {
      return new BTreeScanInfo(this);
   }

   public boolean isCurrentPositionDeleted() throws StandardException {
      if (this.scan_state != 2) {
         throw StandardException.newException("XSAM5.S", new Object[0]);
      } else {
         boolean var1;
         try {
            if (this.reposition(this.scan_position, false)) {
               var1 = this.scan_position.current_leaf.page.isDeletedAtSlot(this.scan_position.current_slot);
            } else {
               var1 = false;
            }
         } finally {
            if (this.scan_position.current_leaf != null) {
               this.savePositionAndReleasePage();
            }

         }

         return var1;
      }
   }

   public boolean isKeyed() {
      return true;
   }

   public boolean positionAtRowLocation(RowLocation var1) throws StandardException {
      throw StandardException.newException("XSCB3.S", new Object[0]);
   }

   public boolean next() throws StandardException {
      this.fetchNext_one_slot_array[0] = this.runtime_mem.get_scratch_row(this.getRawTran());
      boolean var1 = this.fetchRows(this.scan_position, this.fetchNext_one_slot_array, (RowLocation[])null, (BackingStoreHashtable)null, 1L, (int[])null) == 1;
      return var1;
   }

   public boolean fetchNext(DataValueDescriptor[] var1) throws StandardException {
      this.fetchNext_one_slot_array[0] = var1;
      boolean var2 = this.fetchRows(this.scan_position, this.fetchNext_one_slot_array, (RowLocation[])null, (BackingStoreHashtable)null, 1L, (int[])null) == 1;
      return var2;
   }

   public int fetchNextGroup(DataValueDescriptor[][] var1, RowLocation[] var2) throws StandardException {
      return this.fetchRows(this.scan_position, var1, var2, (BackingStoreHashtable)null, (long)var1.length, (int[])null);
   }

   public int fetchNextGroup(DataValueDescriptor[][] var1, RowLocation[] var2, RowLocation[] var3) throws StandardException {
      throw StandardException.newException("XSCB3.S", new Object[0]);
   }

   public void fetchSet(long var1, int[] var3, BackingStoreHashtable var4) throws StandardException {
      this.fetchRows(this.scan_position, (DataValueDescriptor[][])null, (RowLocation[])null, var4, var1, var3);
   }

   public final void reopenScan(DataValueDescriptor[] var1, int var2, Qualifier[][] var3, DataValueDescriptor[] var4, int var5) throws StandardException {
      if (this.scan_position.current_rh != null) {
         this.getLockingPolicy().unlockScanRecordAfterRead(this.scan_position, this.init_forUpdate);
      }

      this.scan_position.current_slot = -1;
      this.scan_position.current_rh = null;
      this.scan_position.current_positionKey = null;
      this.initScanParams(var1, var2, var3, var4, var5);
      if (!this.init_hold) {
         this.scan_state = 1;
      } else {
         this.scan_state = this.container != null ? 1 : 4;
      }

   }

   public void reopenScanByRowLocation(RowLocation var1, Qualifier[][] var2) throws StandardException {
      throw StandardException.newException("XSCB3.S", new Object[0]);
   }

   public void fetchLocation(RowLocation var1) throws StandardException {
      throw StandardException.newException("XSCB3.S", new Object[0]);
   }

   public RowLocation newRowLocationTemplate() throws StandardException {
      throw StandardException.newException("XSCB3.S", new Object[0]);
   }

   public boolean replace(DataValueDescriptor[] var1, FormatableBitSet var2) throws StandardException {
      throw StandardException.newException("XSCB3.S", new Object[0]);
   }

   public boolean closeForEndTransaction(boolean var1) throws StandardException {
      if (this.init_hold && !var1) {
         if (this.scan_state == 2) {
            this.scan_position.current_rh = null;
            this.scan_state = 5;
         } else if (this.scan_state == 1) {
            this.scan_state = 4;
         }

         super.close();
         return false;
      } else {
         this.positionAtDoneScan(this.scan_position);
         super.close();
         this.init_rawtran = null;
         this.init_template = null;
         this.init_startKeyValue = null;
         this.init_qualifier = null;
         this.init_stopKeyValue = null;
         this.getXactMgr().closeMe((ScanManager)this);
         return true;
      }
   }

   void savePositionAndReleasePage(DataValueDescriptor[] var1, int[] var2) throws StandardException {
      Page var3 = this.scan_position.current_leaf.getPage();

      try {
         DataValueDescriptor[] var4 = this.scan_position.getKeyTemplate();
         FetchDescriptor var5 = null;
         boolean var6 = false;
         if (var1 != null) {
            int var7 = 0;
            int var8 = var2 == null ? var1.length : var2.length;

            for(int var9 = 0; var9 < var8; ++var9) {
               if (var2 == null || var2[var9] != 0) {
                  var4[var9].setValue(var1[var9]);
                  ++var7;
               }
            }

            if (var7 < var4.length) {
               var5 = this.scan_position.getFetchDescriptorForSaveKey(var2, var4.length);
            } else {
               var6 = true;
            }
         }

         if (!var6) {
            var3.fetchFromSlot((RecordHandle)null, this.scan_position.current_slot, var4, var5, true);
         }

         this.scan_position.current_positionKey = var4;
         this.scan_position.versionWhenSaved = var3.getPageVersion();
         this.scan_position.current_slot = -1;
      } finally {
         this.scan_position.current_leaf.release();
         this.scan_position.current_leaf = null;
      }

   }

   void savePositionAndReleasePage() throws StandardException {
      this.savePositionAndReleasePage((DataValueDescriptor[])null, (int[])null);
   }

   public RecordHandle getCurrentRecordHandleForDebugging() {
      return this.scan_position.current_rh;
   }

   public String toString() {
      return null;
   }
}
