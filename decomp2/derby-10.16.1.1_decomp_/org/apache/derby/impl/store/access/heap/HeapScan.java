package org.apache.derby.impl.store.access.heap;

import org.apache.derby.iapi.store.access.BackingStoreHashtable;
import org.apache.derby.iapi.store.access.Qualifier;
import org.apache.derby.iapi.store.access.RowUtil;
import org.apache.derby.iapi.store.access.ScanInfo;
import org.apache.derby.iapi.store.access.conglomerate.ScanManager;
import org.apache.derby.iapi.store.access.conglomerate.TransactionManager;
import org.apache.derby.iapi.store.raw.RecordHandle;
import org.apache.derby.iapi.types.DataValueDescriptor;
import org.apache.derby.iapi.types.RowLocation;
import org.apache.derby.impl.store.access.conglomerate.GenericScanController;
import org.apache.derby.impl.store.access.conglomerate.RowPosition;
import org.apache.derby.shared.common.error.StandardException;

class HeapScan extends GenericScanController implements ScanManager {
   private DataValueDescriptor[][] fetchNext_one_slot_array = new DataValueDescriptor[1][];

   public HeapScan() {
   }

   protected void queueDeletePostCommitWork(RowPosition var1) throws StandardException {
      TransactionManager var2 = this.open_conglom.getXactMgr();
      var2.addPostCommitWork(new HeapPostCommit(var2.getAccessManager(), var1.current_page.getPageKey()));
   }

   protected void setRowLocationArray(RowLocation[] var1, int var2, RowPosition var3) throws StandardException {
      if (var1[var2] == null) {
         var1[var2] = new HeapRowLocation(var3.current_rh);
      } else {
         ((HeapRowLocation)var1[var2]).setFrom(var3.current_rh);
      }

   }

   protected RowLocation makeRowLocation(RowPosition var1) throws StandardException {
      return new HeapRowLocation(var1.current_rh);
   }

   protected void setRowLocationArray(RowLocation[] var1, int var2, RecordHandle var3) throws StandardException {
      if (var1[var2] == null) {
         var1[var2] = new HeapRowLocation(var3);
      } else {
         ((HeapRowLocation)var1[var2]).setFrom(var3);
      }

   }

   private boolean reopenScanByRecordHandleAndSetLocks(RecordHandle var1) throws StandardException {
      if (var1 == null) {
         return false;
      } else {
         if (this.scan_position.current_rh != null) {
            this.open_conglom.unlockPositionAfterRead(this.scan_position);
         }

         this.scan_position.current_rh = var1;
         this.scan_position.current_rh_qualified = false;
         boolean var2 = this.open_conglom.latchPageAndRepositionScan(this.scan_position);
         if (!var2) {
            this.setScanState(2);
            this.open_conglom.lockPositionForRead(this.scan_position, (RowPosition)null, true, true);
         }

         this.scan_position.unlatch();
         return !var2;
      }
   }

   public boolean fetchNext(DataValueDescriptor[] var1) throws StandardException {
      if (var1 == null) {
         this.fetchNext_one_slot_array[0] = RowUtil.EMPTY_ROW;
      } else {
         this.fetchNext_one_slot_array[0] = var1;
      }

      boolean var2 = this.fetchRows(this.fetchNext_one_slot_array, (RowLocation[])null, (BackingStoreHashtable)null, 1L, (int[])null) == 1;
      return var2;
   }

   public boolean next() throws StandardException {
      this.fetchNext_one_slot_array[0] = this.open_conglom.getRuntimeMem().get_scratch_row(this.open_conglom.getRawTran());
      boolean var1 = this.fetchRows(this.fetchNext_one_slot_array, (RowLocation[])null, (BackingStoreHashtable)null, 1L, (int[])null) == 1;
      return var1;
   }

   public boolean positionAtRowLocation(RowLocation var1) throws StandardException {
      if (this.open_conglom.isClosed() && !this.rowLocationsInvalidated) {
         this.reopenAfterEndTransaction();
      }

      return this.rowLocationsInvalidated ? false : this.reopenScanByRecordHandleAndSetLocks(((HeapRowLocation)var1).getRecordHandle(this.open_conglom.getContainer()));
   }

   public void fetchLocation(RowLocation var1) throws StandardException {
      if (this.open_conglom.getContainer() != null && this.scan_position.current_rh != null) {
         HeapRowLocation var2 = (HeapRowLocation)var1;
         var2.setFrom(this.scan_position.current_rh);
      } else {
         throw StandardException.newException("XSCH7.S", new Object[0]);
      }
   }

   public int fetchNextGroup(DataValueDescriptor[][] var1, RowLocation[] var2) throws StandardException {
      return this.fetchRows(var1, var2, (BackingStoreHashtable)null, (long)var1.length, (int[])null);
   }

   public int fetchNextGroup(DataValueDescriptor[][] var1, RowLocation[] var2, RowLocation[] var3) throws StandardException {
      throw StandardException.newException("XSCH8.S", new Object[0]);
   }

   public ScanInfo getScanInfo() throws StandardException {
      return new HeapScanInfo(this);
   }

   public void reopenScanByRowLocation(RowLocation var1, Qualifier[][] var2) throws StandardException {
      this.reopenScanByRecordHandle(((HeapRowLocation)var1).getRecordHandle(this.open_conglom.getContainer()), var2);
   }
}
