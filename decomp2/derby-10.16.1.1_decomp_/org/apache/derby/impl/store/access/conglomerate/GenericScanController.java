package org.apache.derby.impl.store.access.conglomerate;

import org.apache.derby.iapi.services.io.FormatableBitSet;
import org.apache.derby.iapi.store.access.BackingStoreHashtable;
import org.apache.derby.iapi.store.access.Qualifier;
import org.apache.derby.iapi.store.access.RowUtil;
import org.apache.derby.iapi.store.access.ScanInfo;
import org.apache.derby.iapi.store.access.conglomerate.LogicalUndo;
import org.apache.derby.iapi.store.access.conglomerate.ScanManager;
import org.apache.derby.iapi.store.raw.ContainerHandle;
import org.apache.derby.iapi.store.raw.FetchDescriptor;
import org.apache.derby.iapi.store.raw.Page;
import org.apache.derby.iapi.store.raw.RecordHandle;
import org.apache.derby.iapi.types.DataValueDescriptor;
import org.apache.derby.iapi.types.RowLocation;
import org.apache.derby.shared.common.error.StandardException;

public abstract class GenericScanController extends GenericController implements ScanManager {
   public static final int SCAN_INIT = 1;
   public static final int SCAN_INPROGRESS = 2;
   public static final int SCAN_DONE = 3;
   public static final int SCAN_HOLD_INIT = 4;
   public static final int SCAN_HOLD_INPROGRESS = 5;
   private FormatableBitSet init_scanColumnList;
   private DataValueDescriptor[] init_startKeyValue;
   private int init_startSearchOperator;
   private Qualifier[][] init_qualifier;
   private DataValueDescriptor[] init_stopKeyValue;
   private int init_stopSearchOperator;
   private FetchDescriptor init_fetchDesc;
   private int scan_state;
   protected boolean rowLocationsInvalidated = false;
   private long reusableRecordIdSequenceNumber = 0L;
   protected RowPosition scan_position;
   protected int stat_numpages_visited = 0;
   protected int stat_numrows_visited = 0;
   protected int stat_numrows_qualified = 0;

   private final void repositionScanForUpateOper() throws StandardException {
      if (this.scan_state != 2) {
         throw StandardException.newException("XSAM5.S", new Object[0]);
      } else if (!this.open_conglom.latchPage(this.scan_position)) {
         throw StandardException.newException("XSAM6.S", new Object[]{this.open_conglom.getContainer().getId(), this.scan_position.current_rh.getPageNumber(), this.scan_position.current_rh.getId()});
      } else {
         if (this.open_conglom.isUseUpdateLocks()) {
            this.open_conglom.lockPositionForWrite(this.scan_position, true);
         }

      }
   }

   protected void positionAtInitScan(DataValueDescriptor[] var1, int var2, Qualifier[][] var3, DataValueDescriptor[] var4, int var5, RowPosition var6) throws StandardException {
      this.init_startKeyValue = var1;
      if (RowUtil.isRowEmpty(this.init_startKeyValue)) {
         this.init_startKeyValue = null;
      }

      this.init_startSearchOperator = var2;
      if (var3 != null && var3.length == 0) {
         var3 = null;
      }

      this.init_qualifier = var3;
      this.init_fetchDesc = new FetchDescriptor(this.open_conglom.getRuntimeMem().get_scratch_row(this.open_conglom.getRawTran()).length, this.init_scanColumnList, this.init_qualifier);
      this.init_stopKeyValue = var4;
      if (RowUtil.isRowEmpty(this.init_stopKeyValue)) {
         this.init_stopKeyValue = null;
      }

      this.init_stopSearchOperator = var5;
      var6.init();
      this.scan_state = 1;
   }

   protected void positionAtResumeScan(RowPosition var1) throws StandardException {
      this.open_conglom.latchPageAndRepositionScan(this.scan_position);
   }

   protected void positionAtStartForForwardScan(RowPosition var1) throws StandardException {
      if (var1.current_rh == null) {
         var1.current_page = this.open_conglom.getContainer().getFirstPage();
         var1.current_slot = 0;
      } else {
         this.open_conglom.latchPageAndRepositionScan(var1);
         --var1.current_slot;
      }

      var1.current_rh = null;
      this.stat_numpages_visited = 1;
      this.scan_state = 2;
   }

   protected void positionAtNextPage(RowPosition var1) throws StandardException {
      if (var1.current_page != null) {
         long var2 = var1.current_page.getPageNumber();
         var1.unlatch();
         var1.current_page = this.open_conglom.getContainer().getNextPage(var2);
         var1.current_slot = -1;
      }

   }

   protected void positionAtDoneScan(RowPosition var1) throws StandardException {
      var1.unlatch();
      if (this.scan_position.current_rh != null) {
         this.open_conglom.unlockPositionAfterRead(this.scan_position);
         this.scan_position.current_rh = null;
      }

      this.scan_state = 3;
   }

   public void reopenScanByRowLocation(RowLocation var1, Qualifier[][] var2) throws StandardException {
      throw StandardException.newException("XSCB3.S", new Object[0]);
   }

   protected RowPosition allocateScanPosition() throws StandardException {
      return new RowPosition();
   }

   protected int fetchRows(DataValueDescriptor[][] var1, RowLocation[] var2, BackingStoreHashtable var3, long var4, int[] var6) throws StandardException {
      int var7 = 0;
      DataValueDescriptor[] var8 = null;
      if (var4 == -1L) {
         var4 = Long.MAX_VALUE;
      }

      if (this.scan_state == 2) {
         this.positionAtResumeScan(this.scan_position);
      } else if (this.scan_state == 1) {
         this.positionAtStartForForwardScan(this.scan_position);
      } else if (this.scan_state == 5) {
         this.reopenAfterEndTransaction();
         this.open_conglom.latchPageAndRepositionScan(this.scan_position);
         this.scan_state = 2;
      } else {
         if (this.scan_state != 4) {
            return 0;
         }

         this.reopenAfterEndTransaction();
         this.positionAtStartForForwardScan(this.scan_position);
      }

      while(this.scan_position.current_page != null) {
         while(this.scan_position.current_slot + 1 < this.scan_position.current_page.recordCount()) {
            if (this.scan_position.current_rh != null) {
               this.open_conglom.unlockPositionAfterRead(this.scan_position);
            }

            if (var8 == null) {
               if (var3 == null) {
                  if (var1[var7] == null) {
                     var1[var7] = this.open_conglom.getRuntimeMem().get_row_for_export(this.open_conglom.getRawTran());
                  }

                  var8 = var1[var7];
               } else {
                  var8 = this.open_conglom.getRuntimeMem().get_row_for_export(this.open_conglom.getRawTran());
               }
            }

            this.scan_position.positionAtNextSlot();
            boolean var9 = this.open_conglom.lockPositionForRead(this.scan_position, (RowPosition)null, true, true);
            if (!var9) {
               if (this.scan_position.current_page == null) {
                  break;
               }

               if (this.scan_position.current_slot == -1) {
                  continue;
               }
            }

            ++this.stat_numrows_visited;
            this.scan_position.current_rh_qualified = this.scan_position.current_page.fetchFromSlot(this.scan_position.current_rh, this.scan_position.current_slot, var8, this.init_fetchDesc, false) != null;
            if (this.scan_position.current_rh_qualified) {
               ++var7;
               ++this.stat_numrows_qualified;
               if (var3 == null) {
                  if (var2 != null) {
                     this.setRowLocationArray(var2, var7 - 1, this.scan_position);
                  }

                  var8 = null;
               } else {
                  RowLocation var10 = var3.includeRowLocations() ? this.makeRowLocation(this.scan_position) : null;
                  if (var3.putRow(false, var8, var10)) {
                     var8 = null;
                  }
               }

               if (var4 <= (long)var7) {
                  this.scan_position.unlatch();
                  return var7;
               }
            }
         }

         this.positionAtNextPage(this.scan_position);
         ++this.stat_numpages_visited;
      }

      this.positionAtDoneScan(this.scan_position);
      --this.stat_numpages_visited;
      return var7;
   }

   protected void reopenScanByRecordHandle(RecordHandle var1, Qualifier[][] var2) throws StandardException {
      this.scan_state = !this.open_conglom.getHold() ? 1 : 4;
      this.scan_position.current_rh = var1;
   }

   protected abstract void setRowLocationArray(RowLocation[] var1, int var2, RowPosition var3) throws StandardException;

   protected abstract RowLocation makeRowLocation(RowPosition var1) throws StandardException;

   public void init(OpenConglomerate var1, FormatableBitSet var2, DataValueDescriptor[] var3, int var4, Qualifier[][] var5, DataValueDescriptor[] var6, int var7) throws StandardException {
      super.init(var1);
      this.scan_position = var1.getRuntimeMem().get_scratch_row_position();
      this.init_scanColumnList = var2;
      this.positionAtInitScan(var3, var4, var5, var6, var7, this.scan_position);
      this.reusableRecordIdSequenceNumber = var1.getContainer().getReusableRecordIdSequenceNumber();
   }

   public final int getNumPagesVisited() {
      return this.stat_numpages_visited;
   }

   public final int getNumRowsVisited() {
      return this.stat_numrows_visited;
   }

   public final int getNumRowsQualified() {
      return this.stat_numrows_qualified;
   }

   public final FormatableBitSet getScanColumnList() {
      return this.init_scanColumnList;
   }

   public final DataValueDescriptor[] getStartKeyValue() {
      return this.init_startKeyValue;
   }

   public final int getStartSearchOperator() {
      return this.init_startSearchOperator;
   }

   public final DataValueDescriptor[] getStopKeyValue() {
      return this.init_stopKeyValue;
   }

   public final int getStopSearchOperator() {
      return this.init_stopSearchOperator;
   }

   public final Qualifier[][] getQualifier() {
      return this.init_qualifier;
   }

   public final int getScanState() {
      return this.scan_state;
   }

   public final void setScanState(int var1) {
      this.scan_state = var1;
   }

   public final RowPosition getScanPosition() {
      return this.scan_position;
   }

   public final void setScanPosition(RowPosition var1) {
      this.scan_position = var1;
   }

   private void closeScan() throws StandardException {
      super.close();
      if (this.open_conglom.getXactMgr() != null) {
         this.open_conglom.getXactMgr().closeMe((ScanManager)this);
      }

      this.init_qualifier = null;
      this.init_scanColumnList = null;
      this.init_startKeyValue = null;
      this.init_stopKeyValue = null;
   }

   public void close() throws StandardException {
      this.positionAtDoneScan(this.scan_position);
      this.closeScan();
   }

   protected final boolean reopenAfterEndTransaction() throws StandardException {
      if (!this.open_conglom.getHold()) {
         return false;
      } else {
         ContainerHandle var1 = this.open_conglom.reopen();
         switch (this.scan_state) {
            case 1:
            case 4:
               this.reusableRecordIdSequenceNumber = var1.getReusableRecordIdSequenceNumber();
               break;
            case 2:
            case 3:
            case 5:
               if (var1.getReusableRecordIdSequenceNumber() != this.reusableRecordIdSequenceNumber) {
                  this.rowLocationsInvalidated = true;
               }
         }

         return true;
      }
   }

   public boolean closeForEndTransaction(boolean var1) throws StandardException {
      if (this.open_conglom.getHold() && !var1) {
         super.close();
         if (this.scan_state == 2) {
            this.scan_state = 5;
         } else if (this.scan_state == 1) {
            this.scan_state = 4;
         }

         return false;
      } else {
         this.scan_state = 3;
         this.closeScan();
         return true;
      }
   }

   public boolean delete() throws StandardException {
      this.repositionScanForUpateOper();
      boolean var1 = true;
      if (this.scan_position.current_page.isDeletedAtSlot(this.scan_position.current_slot)) {
         var1 = false;
      } else {
         this.scan_position.current_page.deleteAtSlot(this.scan_position.current_slot, true, (LogicalUndo)null);
         if (this.scan_position.current_page.nonDeletedRecordCount() == 0) {
            this.queueDeletePostCommitWork(this.scan_position);
         }
      }

      this.scan_position.unlatch();
      return var1;
   }

   public void didNotQualify() throws StandardException {
   }

   public void fetchSet(long var1, int[] var3, BackingStoreHashtable var4) throws StandardException {
      this.fetchRows((DataValueDescriptor[][])null, (RowLocation[])null, var4, var1, var3);
   }

   public void reopenScan(DataValueDescriptor[] var1, int var2, Qualifier[][] var3, DataValueDescriptor[] var4, int var5) throws StandardException {
      this.scan_state = !this.open_conglom.getHold() ? 1 : 4;
      this.scan_position.current_rh = null;
   }

   public boolean replace(DataValueDescriptor[] var1, FormatableBitSet var2) throws StandardException {
      this.repositionScanForUpateOper();
      Page var3 = this.scan_position.current_page;
      int var4 = this.scan_position.current_slot;
      boolean var5;
      if (var3.isDeletedAtSlot(var4)) {
         var5 = false;
      } else {
         var3.updateAtSlot(var4, var1, var2);
         var5 = true;
      }

      this.scan_position.unlatch();
      return var5;
   }

   public boolean doesCurrentPositionQualify() throws StandardException {
      if (this.scan_state != 2) {
         throw StandardException.newException("XSAM5.S", new Object[0]);
      } else if (!this.open_conglom.latchPage(this.scan_position)) {
         return false;
      } else {
         DataValueDescriptor[] var1 = this.open_conglom.getRuntimeMem().get_scratch_row(this.open_conglom.getRawTran());
         boolean var2 = this.scan_position.current_page.fetchFromSlot(this.scan_position.current_rh, this.scan_position.current_slot, var1, this.init_fetchDesc, false) != null;
         this.scan_position.unlatch();
         return var2;
      }
   }

   public void fetchWithoutQualify(DataValueDescriptor[] var1) throws StandardException {
      this.fetch(var1, false);
   }

   public boolean isHeldAfterCommit() throws StandardException {
      return this.scan_state == 4 || this.scan_state == 5;
   }

   public void fetch(DataValueDescriptor[] var1) throws StandardException {
      this.fetch(var1, true);
   }

   private void fetch(DataValueDescriptor[] var1, boolean var2) throws StandardException {
      if (this.scan_state != 2) {
         throw StandardException.newException("XSAM5.S", new Object[0]);
      } else if (!this.open_conglom.latchPage(this.scan_position)) {
         throw StandardException.newException("XSAM6.S", new Object[]{this.open_conglom.getContainer().getId(), this.scan_position.current_rh.getPageNumber(), this.scan_position.current_rh.getId()});
      } else {
         RecordHandle var3 = this.scan_position.current_page.fetchFromSlot(this.scan_position.current_rh, this.scan_position.current_slot, var1, var2 ? this.init_fetchDesc : null, false);
         this.scan_position.unlatch();
         if (var3 == null) {
            throw StandardException.newException("XSAM6.S", new Object[]{this.open_conglom.getContainer().getId(), this.scan_position.current_rh.getPageNumber(), this.scan_position.current_rh.getId()});
         }
      }
   }

   public void fetchLocation(RowLocation var1) throws StandardException {
      throw StandardException.newException("XSCB3.S", new Object[0]);
   }

   public ScanInfo getScanInfo() throws StandardException {
      throw StandardException.newException("XSCB3.S", new Object[0]);
   }

   public boolean isCurrentPositionDeleted() throws StandardException {
      if (this.scan_state != 2) {
         throw StandardException.newException("XSAM5.S", new Object[0]);
      } else if (!this.open_conglom.latchPage(this.scan_position)) {
         return true;
      } else {
         boolean var1 = this.scan_position.current_page.isDeletedAtSlot(this.scan_position.current_slot);
         this.scan_position.unlatch();
         return var1;
      }
   }
}
