package org.apache.derby.impl.store.access.btree;

import org.apache.derby.iapi.services.io.FormatableBitSet;
import org.apache.derby.iapi.store.access.conglomerate.LogicalUndo;
import org.apache.derby.iapi.store.raw.FetchDescriptor;
import org.apache.derby.iapi.store.raw.Page;
import org.apache.derby.iapi.store.raw.RecordHandle;
import org.apache.derby.iapi.types.DataValueDescriptor;
import org.apache.derby.iapi.types.SQLLongint;
import org.apache.derby.shared.common.error.StandardException;

public class BranchControlRow extends ControlRow {
   protected SQLLongint left_child_page = null;
   transient SQLLongint child_pageno_buf = null;
   private static final int CR_LEFTCHILD = 7;
   private static final int CR_COLID_LAST = 7;
   private static final int CR_NCOLUMNS = 8;
   protected static final FormatableBitSet CR_LEFTCHILD_BITMAP = new FormatableBitSet(8);

   public BranchControlRow() {
   }

   public BranchControlRow(OpenBTree var1, Page var2, int var3, ControlRow var4, boolean var5, long var6) throws StandardException {
      super(var1, var2, var3, var4, var5);
      this.left_child_page = new SQLLongint(var6);
      this.row[7] = this.left_child_page;
      this.child_pageno_buf = new SQLLongint();
   }

   protected final void controlRowInit() {
      this.child_pageno_buf = new SQLLongint();
   }

   public boolean isLeftmostLeaf() throws StandardException {
      return false;
   }

   public boolean isRightmostLeaf() throws StandardException {
      return false;
   }

   protected final int getNumberOfControlRowColumns() {
      return 8;
   }

   public static long restartSplitFor(OpenBTree var0, DataValueDescriptor[] var1, BranchControlRow var2, ControlRow var3, DataValueDescriptor[] var4, DataValueDescriptor[] var5, int var6) throws StandardException {
      var2.release();
      var3.release();
      Object var8 = null;
      Object var9 = null;
      ControlRow var7 = ControlRow.get(var0, 1L);
      return var7.splitFor(var0, var1, (BranchControlRow)null, var4, var6);
   }

   public ControlRow search(SearchParameters var1) throws StandardException {
      ControlRow var2 = null;
      boolean var5 = true;

      ControlRow var11;
      try {
         this.searchForEntry(var1);
         if (var1.searchForOptimizer) {
            float var6 = (float)var1.resultSlot;
            int var7 = this.page.recordCount();
            if (this.getIsRoot()) {
               var1.current_fraction = 1.0F;
               var1.left_fraction = 0.0F;
            }

            var1.left_fraction += var1.current_fraction * (var6 / (float)var7);
            var1.current_fraction *= 1.0F / (float)var7;
         }

         var2 = this.getChildPageAtSlot(var1.btree, var1.resultSlot);
         this.release();
         var5 = false;
         var11 = var2.search(var1);
      } finally {
         if (var5) {
            if (var2 != null) {
               var2.release();
            }

            if (this.page.isLatched()) {
               this.release();
            }
         }

      }

      return var11;
   }

   protected ControlRow searchLeft(OpenBTree var1) throws StandardException {
      ControlRow var2 = null;
      boolean var3 = true;

      ControlRow var4;
      try {
         var2 = this.getLeftChild(var1);
         this.release();
         var3 = false;
         var4 = var2.searchLeft(var1);
      } finally {
         if (var3) {
            if (var2 != null) {
               var2.release();
            }

            if (this.page.isLatched()) {
               this.release();
            }
         }

      }

      return var4;
   }

   protected ControlRow searchRight(OpenBTree var1) throws StandardException {
      ControlRow var2 = null;
      boolean var3 = true;

      ControlRow var4;
      try {
         var2 = this.getRightChild(var1);
         this.release();
         var3 = false;
         var4 = var2.searchRight(var1);
      } finally {
         if (var3) {
            if (var2 != null) {
               var2.release();
            }

            if (this.page.isLatched()) {
               this.release();
            }
         }

      }

      return var4;
   }

   protected boolean shrinkFor(OpenBTree var1, DataValueDescriptor[] var2) throws StandardException {
      Object var3 = null;
      boolean var4 = false;

      try {
         BranchRow var5 = BranchRow.createEmptyTemplate(var1.getRawTran(), var1.getConglomerate());
         SearchParameters var6 = new SearchParameters(var2, 1, var5.getRow(), var1, false);
         this.searchForEntry(var6);
         ControlRow var12 = this.getChildPageAtSlot(var6.btree, var6.resultSlot);
         if (var12.shrinkFor(var1, var2)) {
            if (var6.resultSlot != 0) {
               this.page.purgeAtSlot(var6.resultSlot, 1, true);
            } else if (this.page.recordCount() > 1) {
               long var7 = this.getChildPageIdAtSlot(var1, 1);
               this.setLeftChildPageno(var7);
               this.page.purgeAtSlot(1, 1, true);
            } else if (this.getIsRoot()) {
               LeafControlRow var13 = new LeafControlRow(var1, this.page, (ControlRow)null, true);
               var13.page.updateAtSlot(0, var13.getRow(), (FormatableBitSet)null);
               var13.release();
               var4 = true;
            } else if (this.unlink(var1)) {
               var4 = true;
            }
         }
      } finally {
         if (!var4) {
            this.release();
         }

      }

      return var4;
   }

   protected long splitFor(OpenBTree var1, DataValueDescriptor[] var2, BranchControlRow var3, DataValueDescriptor[] var4, int var5) throws StandardException {
      if (this.page.recordCount() - 1 < BTree.maxRowsPerPage && this.page.spaceForInsert(var4, (FormatableBitSet)null, 50)) {
         if (var3 != null) {
            var3.release();
         }

         BranchRow var19 = BranchRow.createEmptyTemplate(var1.getRawTran(), var1.getConglomerate());
         SearchParameters var20 = new SearchParameters(var4, 1, var19.getRow(), var1, false);
         this.searchForEntry(var20);
         ControlRow var18 = this.getChildPageAtSlot(var1, var20.resultSlot);
         return var18.splitFor(var1, var2, this, var4, var5);
      } else if (this.page.recordCount() == 1) {
         throw StandardException.newException("XSCB6.S", new Object[0]);
      } else if (this.getIsRoot()) {
         growRoot(var1, var2, this);
         var3 = (BranchControlRow)ControlRow.get(var1, 1L);
         return var3.splitFor(var1, var2, (BranchControlRow)null, var4, var5);
      } else {
         int var8 = (this.page.recordCount() - 1) / 2 + 1;
         if ((var5 & 4) != 0) {
            var8 = 1;
         } else if ((var5 & 1) != 0) {
            var8 = this.page.recordCount() - 1;
         }

         BranchRow var9 = BranchRow.createEmptyTemplate(var1.getRawTran(), var1.getConglomerate());
         this.page.fetchFromSlot((RecordHandle)null, var8, var9.getRow(), (FetchDescriptor)null, true);
         BranchRow var10 = var9.createBranchRowFromOldBranchRow(-1L);
         if (!var3.page.spaceForInsert(var10.getRow(), (FormatableBitSet)null, 50)) {
            return restartSplitFor(var1, var2, var3, this, var10.getRow(), var4, var5);
         } else {
            ControlRow var7 = this.getChildPageAtSlot(var1, var8);
            BranchControlRow var11 = allocate(var1, var7, this.getLevel(), var3);
            var11.linkRight(var1, this);
            var7.release();
            var10.setPageNumber(var11.page.getPageNumber());
            BranchRow var12 = BranchRow.createEmptyTemplate(var1.getRawTran(), var1.getConglomerate());
            SearchParameters var13 = new SearchParameters(var10.getRow(), 1, var12.getRow(), var1, false);
            var3.searchForEntry(var13);
            byte var14 = 0;
            var14 = (byte)(var14 | 1);
            var14 = (byte)(var14 | 2);
            if (var3.page.insertAtSlot(var13.resultSlot + 1, var10.getRow(), (FormatableBitSet)null, (LogicalUndo)null, var14, 50) == null) {
               throw StandardException.newException("XSCB6.S", new Object[0]);
            } else {
               Object var21 = null;
               int var15 = this.page.recordCount() - (var8 + 1);
               if (var15 > 0) {
                  this.page.copyAndPurge(var11.page, var8 + 1, var15, 1);
               }

               this.page.purgeAtSlot(var8, 1, true);
               var11.fixChildrensParents(var1, (ControlRow)null);
               var1.getXactMgr().commit();
               BranchControlRow var16;
               if (compareIndexRowToKey(var4, var9.getRow(), var9.getRow().length - 1, 0, var1.getConglomerate().ascDescInfo) >= 0) {
                  var16 = var11;
                  this.release();
               } else {
                  var16 = this;
                  var11.release();
               }

               return var16.splitFor(var1, var2, var3, var4, var5);
            }
         }
      }
   }

   public int checkConsistency(OpenBTree var1, ControlRow var2, boolean var3) throws StandardException {
      this.checkGeneric(var1, var2, var3);
      if (var3) {
         this.checkChildOrderAgainstRowOrder(var1);
      }

      int var4 = 0;
      if (var3) {
         var4 = this.checkChildren(var1);
      }

      return var4 + 1;
   }

   private int checkChildren(OpenBTree var1) throws StandardException {
      int var2 = 0;
      ControlRow var3 = null;

      int var12;
      try {
         var3 = this.getLeftChild(var1);
         var2 += var3.checkConsistency(var1, this, true);
         var3.release();
         var3 = null;
         int var4 = this.page.recordCount();

         for(int var5 = 1; var5 < var4; ++var5) {
            var3 = this.getChildPageAtSlot(var1, var5);
            var2 += var3.checkConsistency(var1, this, true);
            var3.release();
            var3 = null;
         }

         var12 = var2;
      } finally {
         if (var3 != null) {
            var3.release();
         }

      }

      return var12;
   }

   private void checkChildOrderAgainstRowOrder(OpenBTree var1) throws StandardException {
      ControlRow var2 = null;
      ControlRow var3 = null;

      try {
         var3 = this.getLeftChild(var1);
         int var4 = this.page.recordCount();

         for(int var5 = 1; var5 < var4; ++var5) {
            var2 = this.getChildPageAtSlot(var1, var5);
            long var6 = var3.getrightSiblingPageNumber();
            long var8 = var2.getleftSiblingPageNumber();
            var3.release();
            var3 = var2;
            var2 = null;
         }

         var3.release();
         var3 = null;
      } finally {
         if (var3 != null) {
            var3.release();
         }

         if (var2 != null) {
            var2.release();
         }

      }

   }

   public void printTree(OpenBTree var1) throws StandardException {
   }

   private static void growRoot(OpenBTree var0, DataValueDescriptor[] var1, BranchControlRow var2) throws StandardException {
      ControlRow var3 = null;
      BranchControlRow var4 = null;

      try {
         var3 = var2.getLeftChild(var0);
         var4 = allocate(var0, var3, var2.getLevel(), var2);
         var2.page.copyAndPurge(var4.page, 1, var2.page.recordCount() - 1, 1);
         var2.setLeftChild(var4);
         var2.setLevel(var2.getLevel() + 1);
         var4.fixChildrensParents(var0, var3);
         var0.getXactMgr().commit();
      } finally {
         var2.release();
         if (var4 != null) {
            var4.release();
         }

         if (var3 != null) {
            var3.release();
         }

      }

   }

   private static BranchControlRow allocate(OpenBTree var0, ControlRow var1, int var2, ControlRow var3) throws StandardException {
      Page var4 = var0.container.addPage();
      BranchControlRow var5 = new BranchControlRow(var0, var4, var2, var3, false, var1.page.getPageNumber());
      byte var6 = 0;
      var6 = (byte)(var6 | 1);
      var4.insertAtSlot(0, var5.getRow(), (FormatableBitSet)null, (LogicalUndo)null, var6, 50);
      return var5;
   }

   protected void setLeftChildPageno(long var1) throws StandardException {
      if (this.left_child_page == null) {
         this.left_child_page = new SQLLongint(var1);
      } else {
         this.left_child_page.setValue(var1);
      }

      this.page.updateFieldAtSlot(0, 7, this.left_child_page, (LogicalUndo)null);
   }

   protected void setLeftChild(ControlRow var1) throws StandardException {
      this.setLeftChildPageno(var1.page.getPageNumber());
   }

   private void fixChildrensParents(OpenBTree var1, ControlRow var2) throws StandardException {
      ControlRow var3 = null;

      try {
         if (var2 == null) {
            var3 = this.getLeftChild(var1);
            var3.setParent(this.page.getPageNumber());
            var3.release();
            var3 = null;
         } else {
            var2.setParent(this.page.getPageNumber());
         }

         int var4 = this.page.recordCount();

         for(int var5 = 1; var5 < var4; ++var5) {
            var3 = this.getChildPageAtSlot(var1, var5);
            var3.setParent(this.page.getPageNumber());
            var3.release();
            var3 = null;
         }
      } finally {
         if (var3 != null) {
            var3.release();
         }

      }

   }

   private long getChildPageIdAtSlot(OpenBTree var1, int var2) throws StandardException {
      long var3;
      if (var2 == 0) {
         var3 = this.getLeftChildPageno();
      } else {
         this.page.fetchFieldFromSlot(var2, var1.getConglomerate().nKeyFields, this.child_pageno_buf);
         var3 = this.child_pageno_buf.getLong();
      }

      return var3;
   }

   protected ControlRow getChildPageAtSlot(OpenBTree var1, int var2) throws StandardException {
      ControlRow var3;
      if (var2 == 0) {
         var3 = this.getLeftChild(var1);
      } else {
         this.page.fetchFieldFromSlot(var2, var1.getConglomerate().nKeyFields, this.child_pageno_buf);
         var3 = ControlRow.get(var1, this.child_pageno_buf.getLong());
      }

      return var3;
   }

   public ControlRow getLeftChild(OpenBTree var1) throws StandardException {
      return ControlRow.get(var1, this.getLeftChildPageno());
   }

   protected ControlRow getRightChild(OpenBTree var1) throws StandardException {
      int var3 = this.page.recordCount();
      ControlRow var2 = var3 == 1 ? ControlRow.get(var1, this.getLeftChildPageno()) : this.getChildPageAtSlot(var1, var3 - 1);
      return var2;
   }

   long getLeftChildPageno() throws StandardException {
      if (this.left_child_page == null) {
         this.left_child_page = new SQLLongint();
         this.scratch_row[7] = this.left_child_page;
         this.fetchDesc.setValidColumns(CR_LEFTCHILD_BITMAP);
         this.page.fetchFromSlot((RecordHandle)null, 0, this.scratch_row, this.fetchDesc, false);
      }

      return this.left_child_page.getLong();
   }

   public int getTypeFormatId() {
      return 134;
   }

   public DataValueDescriptor[] getRowTemplate(OpenBTree var1) throws StandardException {
      return BranchRow.createEmptyTemplate(var1.getRawTran(), var1.getConglomerate()).getRow();
   }

   public String toString() {
      return null;
   }

   static {
      CR_LEFTCHILD_BITMAP.set(7);
   }
}
