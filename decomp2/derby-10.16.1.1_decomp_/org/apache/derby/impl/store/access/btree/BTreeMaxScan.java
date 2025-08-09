package org.apache.derby.impl.store.access.btree;

import org.apache.derby.iapi.store.access.BackingStoreHashtable;
import org.apache.derby.iapi.store.raw.Page;
import org.apache.derby.iapi.store.raw.RecordHandle;
import org.apache.derby.iapi.types.DataValueDescriptor;
import org.apache.derby.iapi.types.RowLocation;
import org.apache.derby.shared.common.error.StandardException;

public class BTreeMaxScan extends BTreeScan {
   private boolean moveToLeftSibling() throws StandardException {
      try {
         this.positionAtPreviousPage();
         return true;
      } catch (WaitError var5) {
         long var2 = this.scan_position.current_leaf.getleftSiblingPageNumber();
         if (isEmpty(this.scan_position.current_leaf.getPage())) {
            this.scan_position.current_leaf.release();
            this.scan_position.init();
         } else {
            this.scan_position.current_slot = 1;
            this.savePositionAndReleasePage();
         }

         Page var4 = this.container.getPage(var2);
         if (var4 != null) {
            var4.unlatch();
            Object var6 = null;
         }

         return false;
      }
   }

   protected int fetchRows(BTreeRowPosition var1, DataValueDescriptor[][] var2, RowLocation[] var3, BackingStoreHashtable var4, long var5, int[] var7) throws StandardException {
      throw StandardException.newException("XSCB3.S", new Object[0]);
   }

   protected void positionAtStartPosition(BTreeRowPosition var1) throws StandardException {
      while(true) {
         ControlRow var3 = ControlRow.get((OpenBTree)this, 1L);
         this.stat_numpages_visited += var3.getLevel() + 1;
         if (this.init_startKeyValue == null) {
            var1.current_leaf = (LeafControlRow)var3.searchRight(this);
            var1.current_slot = var1.current_leaf.page.recordCount();
            boolean var2 = false;
            --var1.current_slot;
            boolean var4 = !this.getLockingPolicy().lockScanRow(this, var1, this.init_lock_fetch_desc, var1.current_lock_template, var1.current_lock_row_loc, false, this.init_forUpdate, this.lock_operation);
            ++var1.current_slot;
            if (var4) {
               var1.init();
               continue;
            }

            this.scan_state = 2;
            return;
         }

         throw StandardException.newException("XSCB3.S", new Object[0]);
      }
   }

   public boolean fetchMax(DataValueDescriptor[] var1) throws StandardException {
      BTreeRowPosition var2 = this.scan_position;
      int var3 = 0;
      if (this.scan_state == 2) {
         if (!this.reposition(this.scan_position, true)) {
         }
      } else {
         if (this.scan_state != 1) {
            return false;
         }

         this.positionAtStartPosition(this.scan_position);
      }

      boolean var4 = false;

      while(!var4 && var2.current_leaf != null) {
         if (var2.current_slot <= 1) {
            boolean var7 = !this.moveToLeftSibling();
            if (var7) {
               if (var2.current_positionKey == null) {
                  this.scan_state = 1;
                  this.positionAtStartPosition(var2);
               } else if (!this.reposition(var2, false)) {
                  if (!this.reposition(var2, true)) {
                  }

                  ++var2.current_slot;
               }
            }
         } else {
            --var2.current_slot;

            while(var2.current_slot > 0) {
               ++this.stat_numrows_visited;
               RecordHandle var5 = var2.current_leaf.page.fetchFromSlot((RecordHandle)null, var2.current_slot, var1, this.init_fetchDesc, true);
               boolean var6 = !this.getLockingPolicy().lockScanRow(this, var2, this.init_lock_fetch_desc, var2.current_lock_template, var2.current_lock_row_loc, false, this.init_forUpdate, this.lock_operation);
               var2.current_rh = var5;
               if (var6 && !this.reposition(var2, false)) {
                  if (!this.reposition(var2, true)) {
                  }

                  ++var2.current_slot;
                  break;
               }

               if (var2.current_leaf.page.isDeletedAtSlot(var2.current_slot)) {
                  ++this.stat_numdeleted_rows_visited;
                  var2.current_rh_qualified = false;
               } else if (var1[0].isNull()) {
                  var2.current_rh_qualified = false;
               } else {
                  var2.current_rh_qualified = true;
               }

               if (var2.current_rh_qualified) {
                  ++var3;
                  ++this.stat_numrows_qualified;
                  var2.current_slot = -1;
                  var4 = true;
                  break;
               }

               --var2.current_slot;
            }
         }
      }

      if (var2.current_leaf != null) {
         var2.current_leaf.release();
         var2.current_leaf = null;
      }

      this.positionAtDoneScan(this.scan_position);
      return var4;
   }
}
