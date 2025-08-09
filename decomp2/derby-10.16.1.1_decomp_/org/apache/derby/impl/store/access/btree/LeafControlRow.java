package org.apache.derby.impl.store.access.btree;

import org.apache.derby.iapi.services.io.FormatableBitSet;
import org.apache.derby.iapi.store.access.conglomerate.LogicalUndo;
import org.apache.derby.iapi.store.raw.FetchDescriptor;
import org.apache.derby.iapi.store.raw.Page;
import org.apache.derby.iapi.store.raw.RecordHandle;
import org.apache.derby.iapi.types.DataValueDescriptor;
import org.apache.derby.shared.common.error.StandardException;

public class LeafControlRow extends ControlRow {
   public LeafControlRow() {
   }

   LeafControlRow(OpenBTree var1, Page var2, ControlRow var3, boolean var4) throws StandardException {
      super(var1, var2, 0, var3, var4);
   }

   private static LeafControlRow allocate(OpenBTree var0, ControlRow var1) throws StandardException {
      Page var2 = var0.container.addPage();
      LeafControlRow var3 = new LeafControlRow(var0, var2, var1, false);
      byte var4 = 0;
      var4 = (byte)(var4 | 1);
      var2.insertAtSlot(0, var3.getRow(), (FormatableBitSet)null, (LogicalUndo)null, var4, 50);
      return var3;
   }

   private float get_left_nondeleted_rowcnt(int var1) throws StandardException {
      int var2 = 0;

      for(int var3 = 1; var3 <= var1; ++var3) {
         if (!this.page.isDeletedAtSlot(var3)) {
            ++var2;
         }
      }

      return (float)var2;
   }

   protected final void controlRowInit() {
   }

   public static void initEmptyBtree(OpenBTree var0) throws StandardException {
      Page var1 = var0.container.getPage(1L);
      LeafControlRow var2 = new LeafControlRow(var0, var1, (ControlRow)null, true);
      byte var3 = 0;
      var3 = (byte)(var3 | 1);
      var1.insertAtSlot(0, var2.getRow(), (FormatableBitSet)null, (LogicalUndo)null, var3, 50);
      var1.unlatch();
   }

   protected final int getNumberOfControlRowColumns() {
      return 7;
   }

   public boolean isLeftmostLeaf() throws StandardException {
      return this.getleftSiblingPageNumber() == -1L;
   }

   public boolean isRightmostLeaf() throws StandardException {
      return this.getrightSiblingPageNumber() == -1L;
   }

   public ControlRow search(SearchParameters var1) throws StandardException {
      this.searchForEntry(var1);
      if (var1.searchForOptimizer) {
         int var2 = var1.resultSlot;
         if (var1.resultExact && var1.partial_key_match_op == 1) {
            --var2;
         }

         float var3 = this.get_left_nondeleted_rowcnt(var2);
         int var4 = this.page.nonDeletedRecordCount();
         if (this.getIsRoot()) {
            var1.current_fraction = 1.0F;
            var1.left_fraction = 0.0F;
         }

         if (var4 > 1) {
            var1.left_fraction += var1.current_fraction * (var3 / (float)(var4 - 1));
         }

         if (var4 > 1) {
            var1.current_fraction *= 1.0F / (float)(var4 - 1);
         }
      }

      return this;
   }

   protected ControlRow searchLeft(OpenBTree var1) throws StandardException {
      return this;
   }

   protected ControlRow searchRight(OpenBTree var1) throws StandardException {
      return this;
   }

   protected boolean shrinkFor(OpenBTree var1, DataValueDescriptor[] var2) throws StandardException {
      boolean var3 = false;

      try {
         if (this.page.recordCount() == 1 && !this.getIsRoot()) {
            var3 = this.unlink(var1);
         }
      } finally {
         if (!var3) {
            this.release();
         }

      }

      return var3;
   }

   protected long splitFor(OpenBTree var1, DataValueDescriptor[] var2, BranchControlRow var3, DataValueDescriptor[] var4, int var5) throws StandardException {
      long var6 = this.page.getPageNumber();
      if (this.page.recordCount() - 1 < BTree.maxRowsPerPage && this.page.spaceForInsert(var4, (FormatableBitSet)null, 50)) {
         var1.getXactMgr().commit();
         if (var3 != null) {
            var3.release();
         }

         this.release();
         return var6;
      } else if (this.getIsRoot()) {
         growRoot(var1, var2, this);
         ControlRow var18 = ControlRow.get(var1, 1L);
         return var18.splitFor(var1, var2, (BranchControlRow)null, var4, var5);
      } else {
         int var8 = (this.page.recordCount() - 1) / 2 + 1;
         if ((var5 & 4) != 0) {
            var8 = 1;
         } else if ((var5 & 1) != 0) {
            var8 = this.page.recordCount() - 1;
         }

         DataValueDescriptor[] var9 = var1.getConglomerate().createTemplate(var1.getRawTran());
         this.page.fetchFromSlot((RecordHandle)null, var8, var9, (FetchDescriptor)null, true);
         BranchRow var10 = BranchRow.createBranchRowFromOldLeafRow(var9, -1L);
         if (!var3.page.spaceForInsert(var10.getRow(), (FormatableBitSet)null, 50)) {
            return BranchControlRow.restartSplitFor(var1, var2, var3, this, var10.getRow(), var4, var5);
         } else {
            LeafControlRow var11 = allocate(var1, var3);
            var10.setPageNumber(var11.page.getPageNumber());
            var11.linkRight(var1, this);
            int var12 = this.page.recordCount() - var8;
            if (var12 != 0) {
               this.page.copyAndPurge(var11.page, var8, var12, 1);
            }

            BranchRow var13 = BranchRow.createEmptyTemplate(var1.getRawTran(), var1.getConglomerate());
            SearchParameters var14 = new SearchParameters(var10.getRow(), 1, var13.getRow(), var1, false);
            var3.searchForEntry(var14);
            byte var15 = 0;
            var15 = (byte)(var15 | 1);
            var15 = (byte)(var15 | 2);
            if (var3.page.insertAtSlot(var14.resultSlot + 1, var10.getRow(), (FormatableBitSet)null, (LogicalUndo)null, var15, 50) == null) {
               throw StandardException.newException("XSCB6.S", new Object[0]);
            } else {
               Object var19 = null;
               this.page.setRepositionNeeded();
               var1.getXactMgr().commit();
               var3.release();
               this.release();
               long var16 = var11.page.getPageNumber();
               var11.release();
               return var16;
            }
         }
      }
   }

   private static void growRoot(OpenBTree var0, DataValueDescriptor[] var1, LeafControlRow var2) throws StandardException {
      BranchControlRow var3 = null;
      LeafControlRow var4 = null;
      var4 = allocate(var0, var2);
      var2.page.copyAndPurge(var4.page, 1, var2.page.recordCount() - 1, 1);
      var3 = new BranchControlRow(var0, var2.page, 1, (ControlRow)null, true, var4.page.getPageNumber());
      var2 = null;
      var3.page.updateAtSlot(0, var3.getRow(), (FormatableBitSet)null);
      var3.page.setRepositionNeeded();
      var0.getXactMgr().commit();
      if (var3 != null) {
         var3.release();
      }

      if (var2 != null) {
         var2.release();
      }

      if (var4 != null) {
         var4.release();
      }

   }

   protected ControlRow getLeftChild(OpenBTree var1) throws StandardException {
      return null;
   }

   protected ControlRow getRightChild(OpenBTree var1) throws StandardException {
      return null;
   }

   public int checkConsistency(OpenBTree var1, ControlRow var2, boolean var3) throws StandardException {
      this.checkGeneric(var1, var2, var3);
      return 1;
   }

   public void printTree(OpenBTree var1) throws StandardException {
   }

   public int getTypeFormatId() {
      return 133;
   }
}
