package org.apache.derby.impl.store.access.btree;

import org.apache.derby.iapi.store.access.BackingStoreHashtable;
import org.apache.derby.iapi.store.raw.RecordHandle;
import org.apache.derby.iapi.types.DataValueDescriptor;
import org.apache.derby.iapi.types.RowLocation;
import org.apache.derby.shared.common.error.StandardException;

public class BTreeForwardScan extends BTreeScan {
   protected void positionAtStartPosition(BTreeRowPosition var1) throws StandardException {
      this.positionAtStartForForwardScan(var1);
   }

   protected int fetchRows(BTreeRowPosition var1, DataValueDescriptor[][] var2, RowLocation[] var3, BackingStoreHashtable var4, long var5, int[] var7) throws StandardException {
      int var8 = 0;
      DataValueDescriptor[] var9 = null;
      if (var5 == -1L) {
         var5 = Long.MAX_VALUE;
      }

      if (this.scan_state == 2) {
         if (!this.reposition(var1, true)) {
         }
      } else if (this.scan_state == 1) {
         this.positionAtStartPosition(var1);
      } else if (this.scan_state == 5) {
         this.reopen();
         this.scan_state = 2;
         if (!this.reposition(var1, true)) {
         }
      } else {
         if (this.scan_state != 4) {
            return 0;
         }

         this.reopen();
         this.positionAtStartForForwardScan(this.scan_position);
      }

      while(var1.current_leaf != null) {
         label118:
         while(var1.current_slot + 1 < var1.current_leaf.page.recordCount()) {
            if (var1.current_rh != null) {
               this.getLockingPolicy().unlockScanRecordAfterRead(var1, this.init_forUpdate);
               var1.current_rh = null;
            }

            if (var9 == null) {
               if (var4 == null) {
                  if (var2[var8] == null) {
                     var2[var8] = this.runtime_mem.get_row_for_export(this.getRawTran());
                  }

                  var9 = var2[var8];
               } else {
                  var9 = this.runtime_mem.get_row_for_export(this.getRawTran());
               }
            }

            ++var1.current_slot;
            ++this.stat_numrows_visited;
            RecordHandle var10 = var1.current_leaf.page.fetchFromSlot((RecordHandle)null, var1.current_slot, var9, this.init_fetchDesc, true);
            var1.current_rh_qualified = true;
            if (this.init_stopKeyValue != null) {
               int var11 = ControlRow.compareIndexRowToKey(var9, this.init_stopKeyValue, var9.length, 0, this.getConglomerate().ascDescInfo);
               if (var11 == 0 && this.init_stopSearchOperator == 1) {
                  var11 = 1;
               }

               if (var11 > 0) {
                  var1.current_leaf.release();
                  var1.current_leaf = null;
                  this.positionAtDoneScan(var1);
                  return var8;
               }
            }

            boolean var14 = !this.getLockingPolicy().lockScanRow(this, var1, this.init_lock_fetch_desc, var1.current_lock_template, var1.current_lock_row_loc, false, this.init_forUpdate, this.lock_operation);
            var1.current_rh = var10;

            while(var14) {
               if (!this.reposition(var1, false)) {
                  if (!this.reposition(var1, true)) {
                  }
                  continue label118;
               }

               var14 = false;
               if (this.getConglomerate().isUnique()) {
                  var1.current_leaf.page.fetchFromSlot((RecordHandle)null, var1.current_slot, var9, this.init_fetchDesc, true);
                  var14 = !this.getLockingPolicy().lockScanRow(this, var1, this.init_lock_fetch_desc, var1.current_lock_template, var1.current_lock_row_loc, false, this.init_forUpdate, this.lock_operation);
               }
            }

            if (var1.current_leaf.page.isDeletedAtSlot(var1.current_slot)) {
               ++this.stat_numdeleted_rows_visited;
               var1.current_rh_qualified = false;
            } else if (this.init_qualifier != null) {
               var1.current_rh_qualified = this.process_qualifier(var9);
            }

            if (var1.current_rh_qualified) {
               ++var8;
               ++this.stat_numrows_qualified;
               boolean var12 = var5 <= (long)var8;
               if (var12) {
                  int[] var13 = this.init_fetchDesc.getValidColumnsArray();
                  this.savePositionAndReleasePage(var9, var13);
               }

               if (var4 != null) {
                  if (var4.putRow(false, var9, (RowLocation)null)) {
                     var9 = null;
                  }
               } else {
                  var9 = null;
               }

               if (var12) {
                  return var8;
               }
            }
         }

         this.positionAtNextPage(var1);
         ++this.stat_numpages_visited;
      }

      this.positionAtDoneScan(var1);
      --this.stat_numpages_visited;
      return var8;
   }
}
