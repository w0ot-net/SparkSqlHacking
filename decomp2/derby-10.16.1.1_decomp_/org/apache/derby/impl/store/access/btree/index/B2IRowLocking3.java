package org.apache.derby.impl.store.access.btree.index;

import org.apache.derby.iapi.store.access.ConglomerateController;
import org.apache.derby.iapi.store.raw.FetchDescriptor;
import org.apache.derby.iapi.store.raw.LockingPolicy;
import org.apache.derby.iapi.store.raw.RecordHandle;
import org.apache.derby.iapi.store.raw.Transaction;
import org.apache.derby.iapi.types.DataValueDescriptor;
import org.apache.derby.iapi.types.RowLocation;
import org.apache.derby.impl.store.access.btree.BTree;
import org.apache.derby.impl.store.access.btree.BTreeLockingPolicy;
import org.apache.derby.impl.store.access.btree.BTreeRowPosition;
import org.apache.derby.impl.store.access.btree.ControlRow;
import org.apache.derby.impl.store.access.btree.LeafControlRow;
import org.apache.derby.impl.store.access.btree.OpenBTree;
import org.apache.derby.impl.store.access.btree.WaitError;
import org.apache.derby.impl.store.access.heap.HeapController;
import org.apache.derby.shared.common.error.StandardException;

class B2IRowLocking3 implements BTreeLockingPolicy {
   protected ConglomerateController base_cc;
   protected OpenBTree open_btree;
   private Transaction rawtran;

   B2IRowLocking3(Transaction var1, int var2, LockingPolicy var3, ConglomerateController var4, OpenBTree var5) {
      this.rawtran = var1;
      this.base_cc = var4;
      this.open_btree = var5;
   }

   private boolean lockPreviousToFirstKey(LeafControlRow var1, LeafControlRow var2, int var3, int var4) throws StandardException {
      boolean var5 = this.base_cc.lockRow(1L, 3, var3, false, var4);
      if (!var5) {
         var1.release();
         Object var6 = null;
         if (var2 != null) {
            var2.release();
            Object var7 = null;
         }

         this.base_cc.lockRow(1L, 3, var3, true, var4);
      }

      return var5;
   }

   private boolean lockRowOnPage(LeafControlRow var1, LeafControlRow var2, int var3, BTreeRowPosition var4, FetchDescriptor var5, DataValueDescriptor[] var6, RowLocation var7, int var8, int var9) throws StandardException {
      var1.getPage().fetchFromSlot((RecordHandle)null, var3, var6, var5, true);
      boolean var11 = this.base_cc.lockRow(var7, var8, false, var9);
      if (!var11) {
         if (var4 != null) {
            var4.saveMeAndReleasePage();
         } else if (var1 != null) {
            var1.release();
            Object var12 = null;
         }

         if (var2 != null) {
            var2.release();
            Object var13 = null;
         }

         if ((((HeapController)this.base_cc).getOpenConglomerate().getOpenMode() & '耀') != 0) {
            throw StandardException.newException("40XL1", new Object[0]);
         }

         this.base_cc.lockRow(var7, var8, true, var9);
      }

      return var11;
   }

   private boolean searchLeftAndLockPreviousKey(LeafControlRow var1, FetchDescriptor var2, DataValueDescriptor[] var3, RowLocation var4, OpenBTree var5, int var6, int var7) throws StandardException {
      boolean var8 = false;

      LeafControlRow var9;
      try {
         var9 = (LeafControlRow)var1.getLeftSibling(var5);
      } catch (WaitError var14) {
         long var12 = var1.getleftSiblingPageNumber();
         var1.release();
         var1 = null;
         var9 = (LeafControlRow)ControlRow.get(var5, var12);
         var8 = true;
      }

      while(true) {
         try {
            if (var9.getPage().recordCount() > 1) {
               boolean var20 = this.lockRowOnPage(var9, var1, var9.getPage().recordCount() - 1, (BTreeRowPosition)null, var2, var3, var4, var6, var7);
               if (!var20) {
                  var9 = null;
                  Object var17 = null;
                  var8 = true;
               }
               break;
            }

            if (var9.isLeftmostLeaf()) {
               boolean var11 = this.lockPreviousToFirstKey(var9, var1, var6, var7);
               if (!var11) {
                  var9 = null;
                  Object var16 = null;
                  var8 = true;
               }
               break;
            }

            LeafControlRow var10 = (LeafControlRow)var9.getLeftSibling(var5);
            var9.release();
            var9 = var10;
            var10 = null;
         } catch (WaitError var15) {
            long var21 = var9.getleftSiblingPageNumber();
            if (var1 != null) {
               var1.release();
               var1 = null;
            }

            var9.release();
            Object var18 = null;
            var9 = (LeafControlRow)ControlRow.get(var5, var21);
            var8 = true;
         }
      }

      if (var9 != null) {
         var9.release();
      }

      return !var8;
   }

   protected boolean _lockScanRow(OpenBTree var1, BTreeRowPosition var2, boolean var3, FetchDescriptor var4, DataValueDescriptor[] var5, RowLocation var6, boolean var7, boolean var8, int var9) throws StandardException {
      boolean var10 = false;
      if (var3) {
         if (var2.current_slot == 0) {
            var10 = !this.lockNonScanPreviousRow(var2.current_leaf, 1, var4, var5, var6, var1, var9, 2);
         } else {
            var10 = !this.lockRowOnPage(var2.current_leaf, (LeafControlRow)null, var2.current_slot, var2, var4, var5, var6, var9, 2);
         }
      }

      return !var10;
   }

   public boolean lockScanCommittedDeletedRow(OpenBTree var1, LeafControlRow var2, DataValueDescriptor[] var3, FetchDescriptor var4, int var5) throws StandardException {
      RowLocation var6 = (RowLocation)var3[((B2I)var1.getConglomerate()).rowLocationColumn];
      var2.getPage().fetchFromSlot((RecordHandle)null, var5, var3, var4, true);
      return this.base_cc.lockRow(var6, 1, false, 2);
   }

   public boolean lockScanRow(OpenBTree var1, BTreeRowPosition var2, FetchDescriptor var3, DataValueDescriptor[] var4, RowLocation var5, boolean var6, boolean var7, int var8) throws StandardException {
      return this._lockScanRow(var1, var2, true, var3, var4, var5, var6, var7, var8);
   }

   public void unlockScanRecordAfterRead(BTreeRowPosition var1, boolean var2) throws StandardException {
   }

   public boolean lockNonScanPreviousRow(LeafControlRow var1, int var2, FetchDescriptor var3, DataValueDescriptor[] var4, RowLocation var5, OpenBTree var6, int var7, int var8) throws StandardException {
      boolean var9;
      if (var2 > 1) {
         var9 = this.lockRowOnPage(var1, (LeafControlRow)null, var2 - 1, (BTreeRowPosition)null, var3, var4, var5, var7, var8);
      } else if (var1.isLeftmostLeaf()) {
         var9 = this.lockPreviousToFirstKey(var1, (LeafControlRow)null, var7, var8);
      } else {
         var9 = this.searchLeftAndLockPreviousKey(var1, var3, var4, var5, var6, var7, var8);
      }

      return var9;
   }

   public boolean lockNonScanRow(BTree var1, LeafControlRow var2, LeafControlRow var3, DataValueDescriptor[] var4, int var5) throws StandardException {
      B2I var6 = (B2I)var1;
      boolean var7 = this.base_cc.lockRow((RowLocation)var4[var6.rowLocationColumn], var5, false, 2);
      if (!var7) {
         if (var2 != null) {
            var2.release();
            Object var8 = null;
         }

         if (var3 != null) {
            var3.release();
            Object var9 = null;
         }

         this.base_cc.lockRow((RowLocation)var4[var6.rowLocationColumn], var5, true, 2);
      }

      return var7;
   }

   public boolean lockNonScanRowOnPage(LeafControlRow var1, int var2, FetchDescriptor var3, DataValueDescriptor[] var4, RowLocation var5, int var6) throws StandardException {
      return this.lockRowOnPage(var1, (LeafControlRow)null, var2, (BTreeRowPosition)null, var3, var4, var5, var6, 2);
   }
}
