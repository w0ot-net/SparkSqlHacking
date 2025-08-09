package org.apache.derby.impl.store.access.btree;

import java.util.Properties;
import org.apache.derby.iapi.services.io.FormatableBitSet;
import org.apache.derby.iapi.store.access.ConglomerateController;
import org.apache.derby.iapi.store.access.DynamicCompiledOpenConglomInfo;
import org.apache.derby.iapi.store.access.RowLocationRetRowSource;
import org.apache.derby.iapi.store.access.RowUtil;
import org.apache.derby.iapi.store.access.StaticCompiledOpenConglomInfo;
import org.apache.derby.iapi.store.access.conglomerate.LogicalUndo;
import org.apache.derby.iapi.store.access.conglomerate.TransactionManager;
import org.apache.derby.iapi.store.raw.ContainerHandle;
import org.apache.derby.iapi.store.raw.FetchDescriptor;
import org.apache.derby.iapi.store.raw.Page;
import org.apache.derby.iapi.store.raw.RecordHandle;
import org.apache.derby.iapi.store.raw.Transaction;
import org.apache.derby.iapi.types.DataValueDescriptor;
import org.apache.derby.iapi.types.RowLocation;
import org.apache.derby.impl.store.access.conglomerate.ConglomerateUtil;
import org.apache.derby.shared.common.error.StandardException;

public class BTreeController extends OpenBTree implements ConglomerateController {
   transient DataValueDescriptor[] scratch_template = null;
   boolean get_insert_row_lock;
   private static final int NO_MATCH = 0;
   private static final int MATCH_FOUND = 1;
   private static final int RESCAN_REQUIRED = 2;

   private boolean reclaim_deleted_rows(OpenBTree var1, long var2) throws StandardException {
      boolean var4 = false;
      ControlRow var5 = null;

      boolean var6;
      try {
         if ((var5 = ControlRow.get(var1, var2)) != null) {
            LeafControlRow var17 = (LeafControlRow)var5;
            BTreeLockingPolicy var7 = var1.getLockingPolicy();
            int var8 = var17.page.recordCount() - 1 - var17.page.nonDeletedRecordCount();
            if (var8 > 0) {
               Page var9 = var17.page;
               FetchDescriptor var10 = RowUtil.getFetchDescriptorConstant(this.scratch_template.length - 1);

               for(int var11 = var9.recordCount() - 1; var11 > 0; --var11) {
                  if (var9.isDeletedAtSlot(var11) && var7.lockScanCommittedDeletedRow(var1, var17, this.scratch_template, var10, var11)) {
                     var9.purgeAtSlot(var11, 1, true);
                     var4 = true;
                  }
               }
            }

            return var4;
         }

         var6 = false;
      } catch (ClassCastException var15) {
         return var4;
      } finally {
         if (var5 != null) {
            if (var4) {
               var5.page.setRepositionNeeded();
            } else {
               var5.release();
            }
         }

      }

      return var6;
   }

   private long start_xact_and_dosplit(boolean var1, long var2, DataValueDescriptor[] var4, DataValueDescriptor[] var5, int var6) throws StandardException {
      TransactionManager var7 = null;
      OpenBTree var8 = null;
      ControlRow var9 = null;
      var7 = this.init_open_user_scans.getInternalTransaction();
      boolean var10 = true;
      if (var1) {
         ConglomerateController var11 = null;

         try {
            var11 = this.getConglomerate().lockTable(var7, 132, 6, 4);
         } catch (StandardException var13) {
         }

         if (var11 != null) {
            var8 = new OpenBTree();
            var8.init(this.init_open_user_scans, var7, (ContainerHandle)null, var7.getRawStoreXact(), false, 132, 6, this.getConglomerate().getBtreeLockingPolicy(var7.getRawStoreXact(), 6, 1, 4, var11, var8), this.getConglomerate(), (LogicalUndo)null, (DynamicCompiledOpenConglomInfo)null);
            var10 = !this.reclaim_deleted_rows(var8, var2);
            var8.close();
         }
      }

      long var18 = var2;
      if (var10) {
         var8 = new OpenBTree();
         var8.init(this.init_open_user_scans, var7, (ContainerHandle)null, var7.getRawStoreXact(), false, this.getOpenMode(), 5, this.getConglomerate().getBtreeLockingPolicy(var7.getRawStoreXact(), this.init_lock_level, 1, 4, (ConglomerateController)null, var8), this.getConglomerate(), (LogicalUndo)null, (DynamicCompiledOpenConglomInfo)null);
         var9 = ControlRow.get(var8, 1L);
         var18 = var9.splitFor(var8, var4, (BranchControlRow)null, var5, var6);
         var8.close();
      }

      var7.commit();
      var7.destroy();
      return var18;
   }

   private int comparePreviousRecord(int var1, LeafControlRow var2, DataValueDescriptor[] var3, DataValueDescriptor[] var4) throws StandardException {
      Object var5 = null;
      boolean var6 = false;
      LeafControlRow var7 = var2;

      while(var2 != null) {
         if (var1 == 0) {
            LeafControlRow var12 = var2;

            try {
               var2 = (LeafControlRow)var2.getLeftSibling(this);
               if (var6) {
                  var12.release();
               }

               var6 = true;
               if (var2 == null) {
                  return 0;
               }

               var1 = var2.page.recordCount() - 1;
            } catch (WaitError var10) {
               if (var6) {
                  var2.release();
               }

               var7.release();
               return 2;
            }
         } else {
            RecordHandle var11 = var2.page.fetchFromSlot((RecordHandle)null, var1, var3, (FetchDescriptor)null, true);
            if (var11 != null) {
               int var8 = this.compareRowsForInsert(var3, var4, var2, var1);
               boolean var9 = var8 == 1 && var2.page.isDeletedAtSlot(var1);
               if (!var9) {
                  if (var6) {
                     if (var8 == 2) {
                        var7.release();
                     }

                     if (var8 != 2) {
                        var2.release();
                     }
                  }

                  return var8;
               }
            }

            --var1;
         }
      }

      return 0;
   }

   private int compareNextRecord(int var1, LeafControlRow var2, DataValueDescriptor[] var3, DataValueDescriptor[] var4) throws StandardException {
      Object var5 = null;
      boolean var6 = false;
      LeafControlRow var7 = var2;

      while(var2 != null) {
         if (var1 >= var2.page.recordCount()) {
            LeafControlRow var11 = var2;
            var2 = (LeafControlRow)var2.getRightSibling(this);
            if (var6) {
               var11.release();
            }

            var6 = true;
            if (var2 == null) {
               return 0;
            }

            var1 = 1;
         } else {
            RecordHandle var10 = var2.page.fetchFromSlot((RecordHandle)null, var1, var3, (FetchDescriptor)null, true);
            if (var10 != null) {
               int var8 = this.compareRowsForInsert(var3, var4, var2, var1);
               boolean var9 = var8 == 1 && var2.page.isDeletedAtSlot(var1);
               if (!var9) {
                  if (var6) {
                     if (var8 == 2) {
                        var7.release();
                     }

                     if (var8 != 2) {
                        var2.release();
                     }
                  }

                  return var8;
               }
            }

            ++var1;
         }
      }

      return 0;
   }

   private int compareRowsForInsert(DataValueDescriptor[] var1, DataValueDescriptor[] var2, LeafControlRow var3, int var4) throws StandardException {
      for(int var5 = 0; var5 < var1.length - 1; ++var5) {
         if (!var1[var5].equals(var2[var5])) {
            return 0;
         }
      }

      DataValueDescriptor[] var9 = this.runtime_mem.get_template(this.getRawTran());
      FetchDescriptor var6 = RowUtil.getFetchDescriptorConstant(var9.length - 1);
      RowLocation var7 = (RowLocation)this.scratch_template[this.scratch_template.length - 1];
      boolean var8 = !this.getLockingPolicy().lockNonScanRowOnPage(var3, var4, var6, var9, var7, 1);
      if (var8) {
         return 2;
      } else {
         return 1;
      }
   }

   private int compareLeftAndRightSiblings(DataValueDescriptor[] var1, int var2, LeafControlRow var3) throws StandardException {
      if (this.getConglomerate().isUniqueWithDuplicateNulls()) {
         int var4 = var1.length - 1;
         boolean var5 = false;

         for(int var6 = 0; var6 < var4; ++var6) {
            if (var1[var6].isNull()) {
               return 0;
            }
         }

         if (!var5) {
            DataValueDescriptor[] var8 = this.runtime_mem.get_template(this.getRawTran());
            int var7 = this.comparePreviousRecord(var2 - 1, var3, var8, var1);
            if (var7 > 0) {
               return var7;
            }

            return this.compareNextRecord(var2, var3, var8, var1);
         }
      }

      return 0;
   }

   private int doIns(DataValueDescriptor[] var1) throws StandardException {
      LeafControlRow var2 = null;
      Object var3 = null;
      int var4 = 0;
      int var5 = 0;
      byte var6 = 0;
      boolean var7 = false;
      if (this.scratch_template == null) {
         this.scratch_template = this.runtime_mem.get_template(this.getRawTran());
      }

      SearchParameters var8 = new SearchParameters(var1, 1, this.scratch_template, this, false);
      FetchDescriptor var9 = RowUtil.getFetchDescriptorConstant(this.scratch_template.length - 1);
      RowLocation var10 = (RowLocation)this.scratch_template[this.scratch_template.length - 1];
      if (this.get_insert_row_lock) {
         this.getLockingPolicy().lockNonScanRow(this.getConglomerate(), (LeafControlRow)null, (LeafControlRow)null, var1, 3);
      }

      while(true) {
         var2 = (LeafControlRow)ControlRow.get((OpenBTree)this, 1L).search(var8);
         int var11 = var8.resultExact ? var8.resultSlot : var8.resultSlot + 1;
         boolean var12 = false;
         var12 = !this.getLockingPolicy().lockNonScanPreviousRow(var2, var11, var9, this.scratch_template, var10, this, 5, 1);
         if (!var12) {
            label147: {
               if (var8.resultExact) {
                  var5 = var4 = var8.resultSlot;
                  if (this.getConglomerate().nKeyFields != this.getConglomerate().nUniqueColumns) {
                     var12 = !this.getLockingPolicy().lockNonScanRowOnPage(var2, var4, var9, this.scratch_template, var10, 1);
                     if (var12) {
                        var2 = null;
                        continue;
                     }
                  }

                  if (!var2.page.isDeletedAtSlot(var4)) {
                     var6 = 1;
                     break label147;
                  }

                  if (this.getConglomerate().nKeyFields == this.getConglomerate().nUniqueColumns) {
                     var2.page.deleteAtSlot(var4, false, this.btree_undo);
                     break label147;
                  }

                  if (this.getConglomerate().nUniqueColumns != this.getConglomerate().nKeyFields - 1) {
                     throw StandardException.newException("XSCB3.S", new Object[0]);
                  }

                  var2.page.deleteAtSlot(var4, false, this.btree_undo);
                  boolean var13 = true;

                  try {
                     if (this.runtime_mem.hasCollatedTypes()) {
                        int var14 = this.getConglomerate().nKeyFields;

                        for(int var15 = 0; var15 < var14; ++var15) {
                           var2.page.updateFieldAtSlot(var4, var15, RowUtil.getColumn(var1, (FormatableBitSet)null, var15), this.btree_undo);
                        }
                     } else {
                        int var28 = this.getConglomerate().nKeyFields - 1;
                        var2.page.updateFieldAtSlot(var4, var28, RowUtil.getColumn(var1, (FormatableBitSet)null, var28), this.btree_undo);
                     }
                  } catch (StandardException var17) {
                     if (!var17.getMessageId().equals("XSDA3.S")) {
                        throw var17;
                     }

                     var13 = false;
                     var2.page.deleteAtSlot(var4, true, this.btree_undo);
                  }

                  if (var13) {
                     break label147;
                  }
               } else if (var2.page.recordCount() - 1 < BTree.maxRowsPerPage) {
                  var4 = var8.resultSlot + 1;
                  var5 = var4 + 1;
                  if (this.getConglomerate().isUniqueWithDuplicateNulls()) {
                     int var25 = this.compareLeftAndRightSiblings(var1, var4, var2);
                     if (var25 == 1) {
                        var6 = 1;
                        break label147;
                     }

                     if (var25 == 2) {
                        continue;
                     }
                  }

                  if (var2.page.insertAtSlot(var4, var1, (FormatableBitSet)null, this.btree_undo, (byte)1, 50) != null) {
                     break label147;
                  }

                  if (var2.page.recordCount() <= 2) {
                     throw StandardException.newException("XSCB6.S", new Object[0]);
                  }
               }

               if (this.getConglomerate().isUniqueWithDuplicateNulls()) {
                  int var26 = this.compareLeftAndRightSiblings(var1, var4, var2);
                  if (var26 == 1) {
                     var6 = 1;
                     break label147;
                  }

                  if (var26 == 2) {
                     continue;
                  }
               }

               int var27 = 0;
               if (var4 == 1) {
                  var27 |= 4;
                  if (var2.isLeftmostLeaf()) {
                     var27 |= 8;
                  }
               } else if (var4 == var2.page.recordCount()) {
                  var27 |= 1;
                  if (var2.isRightmostLeaf()) {
                     var27 |= 2;
                  }
               }

               long var29 = var2.page.getPageNumber();
               if (var2.page.recordCount() - var2.page.nonDeletedRecordCount() <= 0) {
                  var7 = true;
               }

               BranchRow var16 = BranchRow.createBranchRowFromOldLeafRow(var1, var29);
               var2.release();
               var2 = null;
               this.start_xact_and_dosplit(!var7, var29, this.scratch_template, var16.getRow(), var27);
               var7 = true;
               continue;
            }

            var2.last_search_result = var5;
            var2.release();
            var2 = null;
            return var6;
         } else {
            var2 = null;
         }
      }
   }

   private boolean do_load_insert(DataValueDescriptor[] var1, LeafControlRow var2, int var3) throws StandardException {
      Object var4 = null;
      boolean var5 = false;
      int var6 = var2.page.recordCount() - 1;
      if (var6 < BTree.maxRowsPerPage) {
         if (var2.page.insertAtSlot(var3, var1, (FormatableBitSet)null, this.btree_undo, (byte)1, 50) != null) {
            var5 = true;
         } else if (var2.page.recordCount() <= 2) {
            throw StandardException.newException("XSCB6.S", new Object[0]);
         }
      }

      return var5;
   }

   private LeafControlRow do_load_split(DataValueDescriptor[] var1, LeafControlRow var2) throws StandardException {
      LeafControlRow var3 = null;
      BranchRow var4 = BranchRow.createBranchRowFromOldLeafRow(var1, var2.page.getPageNumber());
      long var5 = var2.page.getPageNumber();
      var2.release();
      Object var9 = null;
      long var7 = this.start_xact_and_dosplit(false, var5, this.scratch_template, var4.getRow(), 3);
      var3 = (LeafControlRow)ControlRow.get((OpenBTree)this, var7);
      return var3;
   }

   public void init(TransactionManager var1, boolean var2, ContainerHandle var3, Transaction var4, int var5, int var6, BTreeLockingPolicy var7, BTree var8, LogicalUndo var9, StaticCompiledOpenConglomInfo var10, DynamicCompiledOpenConglomInfo var11) throws StandardException {
      this.get_insert_row_lock = (var5 & 16384) == 0;
      super.init(var1, var1, var3, var4, var2, var5, var6, var7, var8, var9, var11);
   }

   public void close() throws StandardException {
      super.close();
      if (this.getXactMgr() != null) {
         this.getXactMgr().closeMe((ConglomerateController)this);
      }

   }

   public boolean closeForEndTransaction(boolean var1) throws StandardException {
      super.close();
      if (this.getHold() && !var1) {
         return false;
      } else {
         if (this.getXactMgr() != null) {
            this.getXactMgr().closeMe((ConglomerateController)this);
         }

         return true;
      }
   }

   public int insert(DataValueDescriptor[] var1) throws StandardException {
      if (this.isClosed()) {
         if (!this.getHold()) {
            throw StandardException.newException("XSCB8.S", new Object[]{this.err_containerid});
         }

         this.reopen();
      }

      return this.doIns(var1);
   }

   public boolean isKeyed() {
      return true;
   }

   public void getTableProperties(Properties var1) throws StandardException {
      if (this.container == null) {
         throw StandardException.newException("XSCB8.S", new Object[]{this.err_containerid});
      } else {
         this.container.getContainerProperties(var1);
      }
   }

   public Properties getInternalTablePropertySet(Properties var1) throws StandardException {
      Properties var2 = ConglomerateUtil.createRawStorePropertySet(var1);
      this.getTableProperties(var2);
      return var2;
   }

   public long load(TransactionManager var1, boolean var2, RowLocationRetRowSource var3) throws StandardException {
      long var4 = 0L;
      if (this.scratch_template == null) {
         this.scratch_template = this.runtime_mem.get_template(this.getRawTran());
      }

      LeafControlRow var6 = null;

      try {
         var6 = (LeafControlRow)ControlRow.get((OpenBTree)this, 1L);
         int var7 = 1;

         DataValueDescriptor[] var9;
         for(FormatableBitSet var8 = var3.getValidColumns(); (var9 = var3.getNextRowFromRowSource()) != null; ++var7) {
            ++var4;

            while(!this.do_load_insert(var9, var6, var7)) {
               var6 = this.do_load_split(var9, var6);
               var7 = var6.page.recordCount();
            }
         }

         var6.release();
         var6 = null;
         if (!this.getConglomerate().isTemporary()) {
            this.container.flushContainer();
         }
      } finally {
         this.close();
      }

      return var4;
   }

   public boolean delete(RowLocation var1) throws StandardException {
      throw StandardException.newException("XSCB3.S", new Object[0]);
   }

   public boolean fetch(RowLocation var1, DataValueDescriptor[] var2, FormatableBitSet var3) throws StandardException {
      throw StandardException.newException("XSCB3.S", new Object[0]);
   }

   public boolean fetch(RowLocation var1, DataValueDescriptor[] var2, FormatableBitSet var3, boolean var4) throws StandardException {
      throw StandardException.newException("XSCB3.S", new Object[0]);
   }

   public void insertAndFetchLocation(DataValueDescriptor[] var1, RowLocation var2) throws StandardException {
      throw StandardException.newException("XSCB3.S", new Object[0]);
   }

   public RowLocation newRowLocationTemplate() throws StandardException {
      throw StandardException.newException("XSCB3.S", new Object[0]);
   }

   public boolean lockRow(RowLocation var1, int var2, boolean var3, int var4) throws StandardException {
      throw StandardException.newException("XSCB3.S", new Object[0]);
   }

   public boolean lockRow(long var1, int var3, int var4, boolean var5, int var6) throws StandardException {
      throw StandardException.newException("XSCB3.S", new Object[0]);
   }

   public void unlockRowAfterRead(RowLocation var1, boolean var2, boolean var3) throws StandardException {
      throw StandardException.newException("XSCB3.S", new Object[0]);
   }

   public boolean replace(RowLocation var1, DataValueDescriptor[] var2, FormatableBitSet var3) throws StandardException {
      throw StandardException.newException("XSCB3.S", new Object[0]);
   }
}
