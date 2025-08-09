package org.apache.derby.impl.store.access.heap;

import org.apache.derby.iapi.store.access.SpaceInfo;
import org.apache.derby.iapi.store.raw.RecordHandle;
import org.apache.derby.iapi.types.DataValueDescriptor;
import org.apache.derby.iapi.types.RowLocation;
import org.apache.derby.impl.store.access.conglomerate.RowPosition;
import org.apache.derby.shared.common.error.StandardException;

class HeapCompressScan extends HeapScan {
   private long pagenum_to_start_moving_rows = -1L;

   public HeapCompressScan() {
   }

   public int fetchNextGroup(DataValueDescriptor[][] var1, RowLocation[] var2, RowLocation[] var3) throws StandardException {
      return this.fetchRowsForCompress(var1, var2, var3);
   }

   private int fetchRowsForCompress(DataValueDescriptor[][] var1, RowLocation[] var2, RowLocation[] var3) throws StandardException {
      int var4 = 0;
      DataValueDescriptor[] var5 = null;
      int var6 = var1.length;
      if (this.getScanState() == 2) {
         this.positionAtResumeScan(this.scan_position);
      } else if (this.getScanState() == 1) {
         SpaceInfo var7 = this.open_conglom.getContainer().getSpaceInfo();
         this.pagenum_to_start_moving_rows = var7.getNumAllocatedPages();
         this.positionAtStartForForwardScan(this.scan_position);
      } else if (this.getScanState() == 5) {
         this.reopenAfterEndTransaction();
         this.open_conglom.latchPageAndRepositionScan(this.scan_position);
         this.setScanState(2);
      } else {
         if (this.getScanState() != 4) {
            return 0;
         }

         this.reopenAfterEndTransaction();
         this.positionAtStartForForwardScan(this.scan_position);
      }

      while(this.scan_position.current_page != null) {
         while(this.scan_position.current_slot + 1 < this.scan_position.current_page.recordCount()) {
            if (var5 == null) {
               if (var1[var4] == null) {
                  var1[var4] = this.open_conglom.getRuntimeMem().get_row_for_export(this.open_conglom.getRawTran());
               }

               var5 = var1[var4];
            }

            this.scan_position.positionAtNextSlot();
            int var11 = this.scan_position.current_slot;
            ++this.stat_numrows_visited;
            if (this.scan_position.current_page.isDeletedAtSlot(this.scan_position.current_slot)) {
               this.scan_position.current_page.purgeAtSlot(this.scan_position.current_slot, 1, false);
               this.scan_position.positionAtPrevSlot();
            } else {
               if (this.scan_position.current_page.getPageNumber() > this.pagenum_to_start_moving_rows) {
                  RecordHandle[] var8 = new RecordHandle[1];
                  RecordHandle[] var9 = new RecordHandle[1];
                  long[] var10 = new long[1];
                  if (this.scan_position.current_page.moveRecordForCompressAtSlot(this.scan_position.current_slot, var5, var8, var9) == 1) {
                     this.scan_position.positionAtPrevSlot();
                     ++var4;
                     ++this.stat_numrows_qualified;
                     this.setRowLocationArray(var2, var4 - 1, var8[0]);
                     this.setRowLocationArray(var3, var4 - 1, var9[0]);
                     var5 = null;
                  }
               }

               if (var4 >= var6) {
                  this.scan_position.current_rh = this.scan_position.current_page.getRecordHandleAtSlot(var11);
                  this.scan_position.unlatch();
                  return var4;
               }
            }
         }

         ++this.stat_numpages_visited;
         if (this.scan_position.current_page.recordCount() == 0) {
            this.scan_position.current_pageno = this.scan_position.current_page.getPageNumber();
            this.open_conglom.getContainer().removePage(this.scan_position.current_page);
            this.scan_position.current_page = null;
         } else {
            this.positionAfterThisPage(this.scan_position);
            this.scan_position.unlatch();
         }

         if (var4 > 0) {
            return var4;
         }

         this.positionAtResumeScan(this.scan_position);
      }

      this.positionAtDoneScan(this.scan_position);
      --this.stat_numpages_visited;
      return var4;
   }

   protected void positionAtResumeScan(RowPosition var1) throws StandardException {
      this.open_conglom.latchPageAndRepositionScan(this.scan_position);
   }

   protected void positionAtStartForForwardScan(RowPosition var1) throws StandardException {
      if (var1.current_rh == null) {
         var1.current_page = this.open_conglom.getContainer().getNextPage(1L);
         var1.current_slot = -1;
      } else {
         this.open_conglom.latchPageAndRepositionScan(var1);
         --var1.current_slot;
      }

      var1.current_rh = null;
      this.stat_numpages_visited = 1;
      this.setScanState(2);
   }

   private void positionAfterThisPage(RowPosition var1) throws StandardException {
      var1.current_rh = null;
      var1.current_pageno = var1.current_page.getPageNumber();
   }
}
